package com.maxmall.provider.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.enums.TrueOrFalseEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.order.exception.OrderBizException;
import com.maxmall.provider.order.mapper.OrderReturnApplyDOMapper;
import com.maxmall.provider.order.model.domain.OrderReturnApplyDO;
import com.maxmall.provider.order.model.dto.ApplyStatusDto;
import com.maxmall.provider.order.model.dto.ReceiveConfirmDto;
import com.maxmall.provider.order.model.dto.ReturnApplyQueryDto;
import com.maxmall.provider.order.model.enums.ApplyStatusEnum;
import com.maxmall.provider.order.model.enums.ReceiveTypeEnum;
import com.maxmall.provider.order.model.vo.OrderReturnApplyVo;
import com.maxmall.provider.order.service.OrderReturnApplyService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderReturnApplyServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 退款申请管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderReturnApplyServiceImpl extends BaseService<OrderReturnApplyDO> implements OrderReturnApplyService {

    @Autowired
    private OrderReturnApplyDOMapper orderReturnApplyDOMapper;

    /**
     * 分页查询退款申请
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<OrderReturnApplyVo> queryApplyListWithPage(ReturnApplyQueryDto queryParam, UserTokenDto loginAuthDto) {
        Integer pageNum = queryParam.getPageNum();

        Integer pageSize = queryParam.getPageSize();
        if (pageNum ==null || pageNum <=0){
            pageNum = 1;
        }
        if (pageSize ==null || pageSize <=0 || pageSize>100){
            pageSize = 20;
        }
        PageHelper.startPage(pageNum, pageSize);
        //查询参数
        Example example = new Example(OrderReturnApplyDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status",CommonStatusEnum.ENABLE.getStatus())
                .andEqualTo("applyStatus", queryParam.getApplyStatus()).andEqualTo("id",queryParam.getId());

        if (StringUtils.isNotEmpty(queryParam.getReceiverKeyword())){
            criteria.andLike("receivedManName","%"+queryParam.getReceiverKeyword()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getHandleManName())){
            criteria.andLike("handleManName","%"+queryParam.getHandleManName()+"%");
        }
        List<OrderReturnApplyDO> uacUserList = orderReturnApplyDOMapper.selectByExample(example);
        PageInfo<OrderReturnApplyDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<OrderReturnApplyVo> responses = BeanConverter.batchConvert(uacUserList,OrderReturnApplyVo.class);
        PageInfo<OrderReturnApplyVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        result.setList(responses);

        return result;
    }

    @Override
    public int deleteApply(List<Long> ids, UserTokenDto loginAuthDto) {
        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        Example example = new Example(OrderReturnApplyDO.class);
        Example.Criteria criteria = example.createCriteria().andIn("id", ids);
        List<OrderReturnApplyDO> reasonDOList = orderReturnApplyDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> {
            PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(), loginAuthDto);
        });
        OrderReturnApplyDO update = new OrderReturnApplyDO();
        update.setStatus(CommonStatusEnum.DISABLE.getStatus());
        update.setUpdateInfo(loginAuthDto);
        return orderReturnApplyDOMapper.updateByExampleSelective(update,example);
    }

    /**
     * 查询详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public OrderReturnApplyVo getDetailApply(Long id, UserTokenDto loginAuthDto) {
        OrderReturnApplyDO applyDO = orderReturnApplyDOMapper.selectByPrimaryKey(id);
        if (applyDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        PermissionCheckUtil.checkDataMerchantPermission(applyDO.getMerchantId(), loginAuthDto);
        return BeanConverter.convert(applyDO,OrderReturnApplyVo.class);
    }

    /**
     * 跟新审核状态
     * @param statusParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int applyOrder(ApplyStatusDto statusParam, UserTokenDto loginAuthDto) {
        OrderReturnApplyDO applyDO = orderReturnApplyDOMapper.selectByPrimaryKey(statusParam.getId());
        if (applyDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        if (ApplyStatusEnum.WAIT.getStatus() != applyDO.getApplyStatus()){
            throw new OrderBizException(ErrorCodeEnum.OMC10031017);
        }
        PermissionCheckUtil.checkDataMerchantPermission(applyDO.getMerchantId(),loginAuthDto);

        Integer applyStatus = statusParam.getApplyStatus();
        //审核通过需要走 审核逻辑
        OrderReturnApplyDO updateDO = new OrderReturnApplyDO();
        updateDO.setUpdateInfo(loginAuthDto);
        if(ApplyStatusEnum.REFUSE.getStatus() == applyStatus){
            //拒绝
            updateDO.setId(statusParam.getId());
            updateDO.setApplyStatus(applyStatus);
            updateDO.setHandleTime(new Date());
            updateDO.setHandleNote(statusParam.getHandleNote());

        }else if(ApplyStatusEnum.REFUND.getStatus() == applyStatus){
            //完成确认退货
            updateDO.setId(statusParam.getId());
            updateDO.setApplyStatus(applyStatus);
            updateDO.setReceiveType(statusParam.getReceiveType());
            updateDO.setReceiveMan(statusParam.getReceiveMan());
            updateDO.setReceivePhone(statusParam.getReceivePhone());
            updateDO.setReceiveAddress(statusParam.getReceiveAddress());
            updateDO.setHandleTime(new Date());
            updateDO.setHandleNote(statusParam.getHandleNote());

            if (statusParam.getReceiveType()== ReceiveTypeEnum.NO_RECEIVE.getType()){
                //无需退货直接完成
                updateDO.setReceivedNote("无需退货");
                updateDO.setApplyStatus(ApplyStatusEnum.FINISH.getStatus());
                updateDO.setIsReceived(TrueOrFalseEnum.TRUE.getStatus());
                updateDO.setReceivedManId(loginAuthDto.getUserId());
                updateDO.setReceivedManName(loginAuthDto.getUserName());
                updateDO.setReceivedTime(new Date());

                //TODO 退款
            }
        }else{
            return 0;
        }
        return orderReturnApplyDOMapper.updateByPrimaryKeySelective(updateDO);
    }

    /**
     * 确认收货
     * @param statusParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int receiveConfirm(ReceiveConfirmDto statusParam, UserTokenDto loginAuthDto) {
        OrderReturnApplyDO applyDO = orderReturnApplyDOMapper.selectByPrimaryKey(statusParam.getId());
        if (applyDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        if (ApplyStatusEnum.REFUND.getStatus() != applyDO.getApplyStatus()){
            throw new OrderBizException(ErrorCodeEnum.OMC10031018);
        }
        PermissionCheckUtil.checkDataMerchantPermission(applyDO.getMerchantId(),loginAuthDto);

        OrderReturnApplyDO updateDO = new OrderReturnApplyDO();
        updateDO.setUpdateInfo(loginAuthDto);
        updateDO.setId(statusParam.getId());
        updateDO.setReceivedNote(statusParam.getReceivedNote());
        updateDO.setApplyStatus(ApplyStatusEnum.FINISH.getStatus());
        updateDO.setIsReceived(TrueOrFalseEnum.TRUE.getStatus());
        updateDO.setReceivedManId(loginAuthDto.getUserId());
        updateDO.setReceivedManName(loginAuthDto.getUserName());
        updateDO.setReceivedTime(new Date());

        //TODO 退款

        return orderReturnApplyDOMapper.updateByPrimaryKeySelective(updateDO);
    }
}
