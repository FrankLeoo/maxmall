package com.maxmall.provider.merchant.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.account.AccountDOMapper;
import com.maxmall.provider.merchant.mapper.account.RoleDOMapper;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.domain.account.AccountRoleDO;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserDto;
import com.maxmall.provider.merchant.model.dto.role.RoleBindUserReqDto;
import com.maxmall.provider.merchant.model.dto.user.QueryRoleDto;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.provider.merchant.model.vo.AccountVo;
import com.maxmall.provider.merchant.model.vo.RoleVo;
import com.maxmall.provider.merchant.service.AccountRoleService;
import com.maxmall.provider.merchant.service.RoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName MenuServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 角色实现类
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl extends BaseService<RoleDO> implements RoleService {

    @Autowired
    private RoleDOMapper roleDOMapper;
    @Autowired
    private AccountRoleService accountRoleService;
    @Autowired
    private AccountDOMapper accountDOMapper;

    /**
     * 描述：根据用户id获取角色列表
     *
     * @author ivoter
     * @date 2019/5/22 11:50 AM
     * @param: userId
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public List<RoleDO> selectRolesByUser(Long userId) {
        AccountRoleDO roleParam = new AccountRoleDO();
        roleParam.setUserId(userId);
        //查询所有角色
        List<AccountRoleDO> roleDOList = accountRoleService.select(roleParam);
        List<Long> roleIds = roleDOList.stream().map(item -> item.getRoleId()).collect(Collectors.toList());

        if (roleIds.isEmpty()){
            throw new BadCredentialsException("用户未分配角色");
        }
        Example roleExample = new Example(RoleDO.class);
        roleExample.createCriteria().andEqualTo("status", GlobalConstant.CommonStatusEnum.ENABLE.getStatus())
                .andIn("id",roleIds);
        List<RoleDO> roleList = this.selectByExample(roleExample);

        return roleList;
    }

    @Override
    public PageInfo<RoleVo> queryRoleListWithPage(QueryRoleDto queryRoleDto) {
        Integer pageNum = queryRoleDto.getPageNum();

        Integer pageSize = queryRoleDto.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(RoleDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("code",queryRoleDto.getCode());

        if (StringUtils.isNotEmpty(queryRoleDto.getName())){
            criteria.andLike("name","%"+queryRoleDto.getName()+"%");
        }
        List<RoleDO> uacUserList = roleDOMapper.selectByExample(example);
        PageInfo<RoleDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<RoleVo> responses = BeanConverter.batchConvert(uacUserList,RoleVo.class);
        PageInfo<RoleVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;

    }

    @Override
    public RoleVo queryRoleInfo(Long id) {
        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(id);
        if (roleDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10012005);
        }
        return BeanConverter.convert(roleDO,RoleVo.class);
    }

    @Override
    public void bindUser4Role(RoleBindUserReqDto roleBindUserReqDto, UserTokenDto loginAuthDto) {
        RoleDO roleDO = roleDOMapper.selectByPrimaryKey(roleBindUserReqDto.getRoleId());
        if (roleDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10012005);
        }
        //获取用户列表
        List<Long> userId = roleBindUserReqDto.getUserIdList();
        Example example = new Example(AccountDO.class);
        example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andIn("id",userId).andEqualTo("status",GlobalConstant.CommonStatusEnum.ENABLE.getStatus());

        List<AccountDO> realUser = accountDOMapper.selectByExample(example);
        //是否存在绑定的用户
        List<Long> realUserIds = realUser.stream().map(item -> item.getId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(realUserIds)){
            return;
        }
        //查询已经绑定了该角色的用户
        Example rexample = new Example(AccountRoleDO.class);
        rexample.createCriteria().andEqualTo("roleId",roleBindUserReqDto.getRoleId())
                .andIn("userId",realUserIds);
        List<AccountRoleDO> bindUser = accountRoleService.selectByExample(rexample);
        Map<Long,AccountRoleDO> bindUserMap = bindUser.stream().collect(Collectors.toMap(AccountRoleDO::getUserId,o -> o));

        //有权限的id并且未绑定角色
        List<AccountRoleDO> realUserList = realUser.stream().filter(item -> !bindUserMap.containsKey(item.getId()))
                .map(item -> {
            AccountRoleDO entity = new AccountRoleDO();
            entity.setRoleId(roleBindUserReqDto.getRoleId());
            entity.setUserId(item.getId());

            return entity;
        }).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(realUserList)){
            return;
        }
        accountRoleService.batchSave(realUserList);
    }

    @Override
    public RoleBindUserDto getRoleBindUserDto(Long roleId, UserTokenDto loginAuthDto) {

        //获取角色用户信息
        AccountRoleDO roleParam = new AccountRoleDO();
        roleParam.setRoleId(roleId);
        //查询所有角色
        List<AccountRoleDO> roleDOList = accountRoleService.select(roleParam);

        //查询已经存在角色的用户
        List<Long> userIds = roleDOList.stream().map(item -> item.getUserId()).collect(Collectors.toList());
        Example example = new Example(AccountDO.class);
        example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andIn("id",userIds).andEqualTo("status",GlobalConstant.CommonStatusEnum.ENABLE.getStatus());

        List<AccountDO> haseRoleList = accountDOMapper.selectByExample(example);

        AccountDO param = new AccountDO();
        param.setStatus(GlobalConstant.CommonStatusEnum.ENABLE.getStatus());
        param.setMerchantId(loginAuthDto.getMerchantId());
        List<AccountDO> allUserList = accountDOMapper.select(param);

        RoleBindUserDto result = new RoleBindUserDto();

        result.setAllUserSet(BeanConverter.batchConvert(allUserList, AccountVo.class));
        result.setAlreadyBindUserSet(BeanConverter.batchConvert(haseRoleList, AccountVo.class));

        return result;
    }
}
