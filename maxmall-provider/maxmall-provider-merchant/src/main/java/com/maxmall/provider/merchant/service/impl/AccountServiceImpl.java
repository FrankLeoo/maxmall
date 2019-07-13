package com.maxmall.provider.merchant.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.util.PublicUtil;
import com.maxmall.common.util.RedisKeyUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.merchant.exception.UacBizException;
import com.maxmall.provider.merchant.mapper.account.AccountDOMapper;
import com.maxmall.provider.merchant.model.constant.MenuConstant;
import com.maxmall.provider.merchant.model.domain.account.AccountDO;
import com.maxmall.provider.merchant.model.domain.account.AccountRoleDO;
import com.maxmall.provider.merchant.model.domain.account.MenuDO;
import com.maxmall.provider.merchant.model.domain.account.RoleDO;
import com.maxmall.provider.merchant.model.domain.merchant.MerchantAuditDO;
import com.maxmall.provider.merchant.model.dto.account.AccountRegisterDto;
import com.maxmall.provider.merchant.model.dto.user.*;
import com.maxmall.provider.merchant.model.enums.UserSourceEnum;
import com.maxmall.provider.merchant.model.vo.*;
import com.maxmall.provider.merchant.service.*;
import com.maxmall.provider.merchant.utils.Md5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.maxmall.provider.merchant.utils.PermissionUtil.checkDataPermission;


/**
 * The class 商户用户管理.
 *
 * @author maxmall.net@gmail.com
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class AccountServiceImpl extends BaseService<AccountDO> implements AccountService {

    @Autowired
    private AccountDOMapper accountDOMapper;
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private MerchantAuditService merchantAuditService;
    @Autowired
    private AccountRoleService accountRoleService;

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AccountDO findByLoginName(String loginName) {
        logger.info("findByLoginName - 根据用户名查询用户信息. loginName={}", loginName);

        return accountDOMapper.findByLoginName(loginName);
    }

    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AccountDO findByPhone(String phone) {

        return accountDOMapper.findByPhone(phone);
    }

    /**
     * 描述：获取当前登录用户的Menus
     *
     * @author ivoter
     * @date 2019/5/21 6:10 PM
     * @param: userTokenDto
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public LoginRespDto findMenusByAccount(UserTokenDto loginAuthDto) {
        LoginRespDto loginRespDto = new LoginRespDto();

        List<RoleDO> roleList = roleService.selectRolesByUser(loginAuthDto.getUserId());
        List<Long> roleIds = roleList.stream().map(item -> item.getId()).distinct().collect(Collectors.toList());
        //查询角色下menu
        List<MenuVo> menuVoList = menuService.treeMenuByRoleIds(roleIds);
        //查询数据库
        if (PublicUtil.isNotEmpty(menuVoList) && MenuConstant.MENU_ROOT.equals(menuVoList.get(0).getMenuCode())) {
            menuVoList = menuVoList.get(0).getSubMenu();
        }
        loginRespDto.setLoginAuthDto(loginAuthDto);
        loginRespDto.setMenuList(menuVoList);
        return loginRespDto;
    }

    /**
     * 描述：获取当前登录用户的Menus
     *
     * @author ivoter
     * @date 2019/5/21 6:10 PM
     * @param: userTokenDto
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Collection<GrantedAuthority> loadUserAuthorities(Long userId) {

        List<RoleDO> roleList = roleService.selectRolesByUser(userId);
        List<Long> roleIds = roleList.stream().map(item -> item.getId()).distinct().collect(Collectors.toList());
        //查询角色下menu
        List<MenuDO> menuDOList = menuService.selectMenuByRoleIds(roleIds);

        List<GrantedAuthority> authList = Lists.newArrayList();
        for (MenuDO menu : menuDOList) {
            if (StringUtils.isEmpty(menu.getPermission())) {
                continue;
            }
            GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(menu.getPermission());
            authList.add(grantedAuthority);
        }
        return authList;
    }

    /**
     * 验证登录名是否存在
     *
     * @param loginName
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean checkLoginName(String loginName) {
        Preconditions.checkArgument(!StringUtils.isEmpty(loginName), ErrorCodeEnum.UAC10011007.msg());

        AccountDO uacUser = new AccountDO();
        uacUser.setLoginName(loginName);
        int result = 1;
        try {
            result = accountDOMapper.selectCount(uacUser);
        } catch (Exception e) {
            logger.error(" 验证用户名是否存在,出现异常={}", e.getMessage(), e);
        }
        return result < 1;
    }

    /**
     * 验证手机号是否存在
     *
     * @param phone
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public boolean checkPhone(String phone) {
        Preconditions.checkArgument(!StringUtils.isEmpty(phone), "手机号码不能为空");

        AccountDO uacUser = new AccountDO();
        uacUser.setPhone(phone);
        int result = 1;
        try {
            result = accountDOMapper.selectCount(uacUser);
        } catch (Exception e) {
            logger.error(" 验证email是否存在,出现异常={}", e.getMessage(), e);
        }
        return result < 1;
    }

    /**
     * 重置登录密码，手机短信
     *
     * @param resetLoginPwdDto
     */
    @Override
    public void resetLoginPwd(ResetLoginPwdDto resetLoginPwdDto) {
        String confirmPwd = resetLoginPwdDto.getConfirmPwd();
        String newPassword = resetLoginPwdDto.getNewPassword();
        String validCode = resetLoginPwdDto.getValidCode();
        String mobile = resetLoginPwdDto.getPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(newPassword), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(confirmPwd), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobile), "手机验不存在");
        Preconditions.checkArgument(!StringUtils.isEmpty(validCode), "手机验证码失效");
        Preconditions.checkArgument(newPassword.equals(confirmPwd), "两次输入密码不一致");

        String resetPwdKey = mobile + "::" + validCode;

        String resetPwdTokenKey = RedisKeyUtil.getResetPwdTokenKey(resetPwdKey);
        AccountDO uacUser = (AccountDO) redisTemplate.opsForValue().get(resetPwdTokenKey);

        if (uacUser == null) {
            throw new UacBizException(ErrorCodeEnum.UAC10011028);
        }

        UserTokenDto loginAuthDto = new UserTokenDto();
        loginAuthDto.setUserName(uacUser.getUserName());
        loginAuthDto.setLoginName(uacUser.getLoginName());
        loginAuthDto.setUserId(uacUser.getId());

        AccountDO update = new AccountDO();
        update.setLoginPwd(Md5Util.encrypt(newPassword));
        update.setId(uacUser.getId());
        update.setUpdateInfo(loginAuthDto);

        int result = accountDOMapper.updateByPrimaryKeySelective(update);
        if (result < 1) {
            throw new UacBizException(ErrorCodeEnum.UAC10011029);
        }
        redisTemplate.delete(resetPwdTokenKey);
    }

    /**
     * 商户申请
     *
     * @param registerDto
     */
    @Override
    public int register(AccountRegisterDto registerDto) {
        // 校验注册信息
        validateRegisterInfo(registerDto);
        Date row = new Date();
        // 封装注册信息
        AccountDO uacUser = BeanConverter.convert(registerDto,AccountDO.class);
        //状态为审核中
        uacUser.setIsMaster(true);
        uacUser.setStatus(GlobalConstant.CommonStatusEnum.AUDIT.getStatus());
        uacUser.setUserSource(UserSourceEnum.REGISTER.getKey());
        uacUser.setCreateTime(row);
        uacUser.setUpdateTime(row);
        uacUser.setCreator(registerDto.getLoginName());
        uacUser.setLastOperator(registerDto.getLoginName());
        accountDOMapper.insert(uacUser);

        //封装审核信息
        MerchantAuditDO auditDO = BeanConverter.convert(registerDto.getMerchant(),MerchantAuditDO.class);
        auditDO.setManagerUserId(uacUser.getId());
        auditDO.setManagerUserName(uacUser.getUserName());
        auditDO.setManagerUserPic(uacUser.getIcon());
        auditDO.setStatus(GlobalConstant.CommonStatusEnum.ENABLE.getStatus());
        auditDO.setCreateTime(row);
        auditDO.setUpdateTime(row);
        auditDO.setCreator(registerDto.getLoginName());
        auditDO.setLastOperator(registerDto.getLoginName());

        return merchantAuditService.save(auditDO);
    }

    /**
     * 分页查询商户子用户
     *
     * @param queryUserDto
     * @param loginAuthDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public PageInfo<AccountVo> queryUserListWithPage(QueryUserDto queryUserDto, UserTokenDto loginAuthDto) {
        Integer pageNum = queryUserDto.getPageNum();

        Integer pageSize = queryUserDto.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(AccountDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("loginName", queryUserDto.getLoginName()).andEqualTo("status", queryUserDto.getStatus())
                .andEqualTo("userSource", queryUserDto.getUserSource());

        if (StringUtils.isNotEmpty(queryUserDto.getUserName())){
            criteria.andLike("userName","%"+queryUserDto.getUserName()+"%");
        }
        if (StringUtils.isNotEmpty(queryUserDto.getPhone())){
            criteria.andLike("phone","%"+queryUserDto.getPhone()+"%");
        }
        List<AccountDO> uacUserList = accountDOMapper.selectByExample(example);
        PageInfo<AccountDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<AccountVo> responses = BeanConverter.batchConvert(uacUserList,AccountVo.class);
        PageInfo<AccountVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    /**
     * 根据用id更新状态
     * @param modifyUserStatusDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int modifyUserStatusById(ModifyUserStatusDto modifyUserStatusDto, UserTokenDto loginAuthDto) {

        Long loginUserId = loginAuthDto.getUserId();
        Long userId = modifyUserStatusDto.getUserId();
        if (loginUserId.equals(userId)) {
            throw new UacBizException(ErrorCodeEnum.UAC10011023);
        }

        checkDataPermission(userId,loginAuthDto);

        AccountDO update = new AccountDO();
        update.setId(modifyUserStatusDto.getUserId());
        update.setStatus(modifyUserStatusDto.getStatus());
        // 更新用户最后修改人与修改时间
        update.setUpdateInfo(loginAuthDto);
        return accountDOMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 根据ID删除用户
     * @param userId
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deleteUserById(Long userId, UserTokenDto loginAuthDto) {
        Long loginUserId = loginAuthDto.getUserId();
        if (loginUserId.equals(userId)) {
            throw new UacBizException(ErrorCodeEnum.UAC10011023);
        }
        AccountDO accountDO = checkDataPermission(userId,loginAuthDto);
        if (accountDO.getIsMaster()){
            throw new UacBizException(ErrorCodeEnum.GL99990401);
        }

        return accountDOMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 根据ID查询用户
     * @param userId
     * @param loginAuthDto
     * @return
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public AccountVo queryByUserId(Long userId, UserTokenDto loginAuthDto) {
        AccountDO accountDO = checkDataPermission(userId,loginAuthDto);
        return BeanConverter.convert(accountDO,AccountVo.class);
    }

    /**
     * 修改用户密码
     * @param userModifyPwdDto
     * @param loginAuthDto
     * @return
     */
    @Override
    public int userModifyPwd(UserModifyPwdDto userModifyPwdDto, UserTokenDto loginAuthDto) {
        AccountDO accountDO = checkDataPermission(userModifyPwdDto.getUserId(),loginAuthDto);

        if (!userModifyPwdDto.getNewPassword().equals(userModifyPwdDto.getConfirmPwd())){
            throw new UacBizException(ErrorCodeEnum.UAC10011010);
        }
        AccountDO update = new AccountDO();
        update.setId(userModifyPwdDto.getUserId());
        update.setLoginPwd(Md5Util.encrypt(userModifyPwdDto.getNewPassword()));

        update.setUpdateInfo(loginAuthDto);
        return accountDOMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 绑定用户角色
     * @param bindUserRolesDto
     * @param loginAuthDto
     */
    @Override
    public int bindUserRoles(BindUserRolesDto bindUserRolesDto, UserTokenDto loginAuthDto) {
        AccountDO accountDO = checkDataPermission(bindUserRolesDto.getUserId(),loginAuthDto);

        //解绑用户
        accountRoleService.unbindRole(accountDO.getId());
        //给用户绑定新角色
        return accountRoleService.bindRoleForUser(bindUserRolesDto.getUserId(),bindUserRolesDto.getRoleIdList());
    }

    /**
     * 获取用户绑定的角色
     * @param userId
     * @return
     */
    @Override
    public List<UserBindRoleVo> getUserBindRoleDto(Long userId) {
        Example example = new Example(RoleDO.class);
        example.createCriteria().andEqualTo("status",GlobalConstant.CommonStatusEnum.ENABLE.getStatus());
        List<RoleDO> allRole = roleService.selectByExample(example);

        Example uexample = new Example(AccountRoleDO.class);
        uexample.createCriteria().andEqualTo("userId",userId);
        List<AccountRoleDO> accountRoleDOS = accountRoleService.selectByExample(uexample);

        Map<Long, Long> bindRoles = accountRoleDOS.stream().collect(Collectors.toMap(AccountRoleDO::getRoleId, AccountRoleDO::getRoleId));

        List<UserBindRoleVo> result = allRole.stream().map(item -> {
            UserBindRoleVo vo = BeanConverter.convert(item, UserBindRoleVo.class);
            if (bindRoles.containsKey(item.getId())) {
                vo.setBinded(true);
            }else{
                vo.setBinded(false);
            }
            return vo;
        }).collect(Collectors.toList());
        return result;
    }

    /**
     * 添加子用户
     * @param userModifyDto
     * @param loginAuthDto
     */
    @Override
    public int saveOrModifyUser(UserModifyDto userModifyDto, UserTokenDto loginAuthDto) {
        AccountDO accountDO = BeanConverter.convert(userModifyDto,AccountDO.class);

        Long id = accountDO.getId();
        //添加
        if (id == null){
            // 验证
            validateRegisterInfo(userModifyDto);

            accountDO.setLoginPwd(Md5Util.encrypt(userModifyDto.getLoginPwd()));
            accountDO.setMerchantId(loginAuthDto.getMerchantId());
            accountDO.setMerchantSn(loginAuthDto.getMerchantSn());
            accountDO.setIsMaster(false);
            accountDO.setStatus(GlobalConstant.CommonStatusEnum.ENABLE.getStatus());
            accountDO.setUpdateInfo(loginAuthDto);

            accountDOMapper.insert(accountDO);
            id = accountDO.getId();
        }else{
            validateUpdateInfo(userModifyDto);
            accountDO.setLoginPwd(Md5Util.encrypt(userModifyDto.getLoginPwd()));
            accountDO.setUpdateInfo(loginAuthDto);

            accountDOMapper.updateByPrimaryKey(accountDO);
            //删除角色
            accountRoleService.unbindRole(id);
        }
        //绑定新角色
        return accountRoleService.bindRoleForUser(id,userModifyDto.getRoleIdList());
    }

    @Override
    public AccountInfoVo accountInfo(UserTokenDto loginAuthDto) {

        AccountDO accountDO = accountDOMapper.selectByPrimaryKey(loginAuthDto.getUserId());
        if (accountDO == null){
            throw new UacBizException(ErrorCodeEnum.UAC10011011,loginAuthDto.getUserId());
        }

        AccountInfoVo infoVo = BeanConverter.convert(accountDO,AccountInfoVo.class);
        List<RoleDO> roleList = roleService.selectRolesByUser(loginAuthDto.getUserId());

        List<RoleVo> roles = roleList.stream().map(item -> {
            return BeanConverter.convert(item,RoleVo.class);
        }).collect(Collectors.toList());

        infoVo.setRoles(roles);
        return infoVo;
    }

    /**
     * 验证是否可以更新
     * @param userModifyDto
     */
    private void validateUpdateInfo(UserModifyDto userModifyDto) {
        String mobileNo = userModifyDto.getPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(userModifyDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(userModifyDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());

        Example example = new Example(AccountDO.class);
        example.createCriteria().andEqualTo("loginName",userModifyDto.getLoginName())
                .andNotEqualTo("id",userModifyDto.getId());
        try {
            int result = accountDOMapper.selectCountByExample(example);
            if (result > 0){
                throw new UacBizException(ErrorCodeEnum.UAC10011012);
            }
        } catch (Exception e) {
            logger.error(" 验证用户名是否存在,出现异常={}", e.getMessage(), e);
        }

        Example pexample = new Example(AccountDO.class);
        pexample.createCriteria().andEqualTo("phone",userModifyDto.getPhone())
                .andNotEqualTo("id",userModifyDto.getId());
        try {
            int result = accountDOMapper.selectCountByExample(pexample);
            if (result > 0){
                throw new UacBizException(ErrorCodeEnum.UAC10011013);
            }
        } catch (Exception e) {
            logger.error(" 验证用户名是否存在,出现异常={}", e.getMessage(), e);
        }
    }

    private void validateRegisterInfo(UserModifyDto userModifyDto) {
        String mobileNo = userModifyDto.getPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(userModifyDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(userModifyDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());

        if (!this.checkLoginName(userModifyDto.getLoginName())){
            throw new UacBizException(ErrorCodeEnum.UAC10011012);
        }

        if (!this.checkPhone(mobileNo)){
            throw new UacBizException(ErrorCodeEnum.UAC10011013);
        }
    }

    private void validateRegisterInfo(AccountRegisterDto registerDto) {
        String mobileNo = registerDto.getPhone();

        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getLoginName()), ErrorCodeEnum.UAC10011007.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(mobileNo), "手机号不能为空");
        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getLoginPwd()), ErrorCodeEnum.UAC10011014.msg());
        Preconditions.checkArgument(!StringUtils.isEmpty(registerDto.getConfirmPwd()), ErrorCodeEnum.UAC10011009.msg());
        Preconditions.checkArgument(registerDto.getLoginPwd().equals(registerDto.getConfirmPwd()), "两次密码不一致");

        if (!this.checkLoginName(registerDto.getLoginName())){
            throw new UacBizException(ErrorCodeEnum.UAC10011012);
        }

        if (!this.checkPhone(mobileNo)){
            throw new UacBizException(ErrorCodeEnum.UAC10011013);
        }
    }
}
