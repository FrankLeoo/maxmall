package com.maxmall.provider.order.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.enums.CommonStatusEnum;
import com.maxmall.common.core.support.BaseService;
import com.maxmall.common.core.utils.PermissionCheckUtil;
import com.maxmall.common.util.converter.BeanConverter;
import com.maxmall.provider.order.exception.OrderBizException;
import com.maxmall.provider.order.mapper.OrderDOMapper;
import com.maxmall.provider.order.model.domain.OrderDO;
import com.maxmall.provider.order.model.domain.OrderItemDO;
import com.maxmall.provider.order.model.dto.OrderDeliveryDto;
import com.maxmall.provider.order.model.dto.OrderQueryDto;
import com.maxmall.provider.order.model.dto.ReceiverInfoDto;
import com.maxmall.provider.order.model.enums.ExpressTypeEnum;
import com.maxmall.provider.order.model.vo.OrderItemVo;
import com.maxmall.provider.order.model.vo.OrderVo;
import com.maxmall.provider.order.service.OrderItemService;
import com.maxmall.provider.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ivoter
 * @ClassName OrderServiceImpl.java
 * @date 2019/05/22 10:26:00
 * @Description 订单管理service
 */
@Service
@Slf4j
@Transactional(rollbackFor = Exception.class)
public class OrderServiceImpl extends BaseService<OrderDO> implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private OrderItemService orderItemService;

    /**
     * 订单分页接口
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public PageInfo<OrderVo> queryOrderListWithPage(OrderQueryDto queryParam, UserTokenDto loginAuthDto) {
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
        Example example = new Example(OrderDO.class);
        Example.Criteria criteria = example.createCriteria().andEqualTo("merchantId",loginAuthDto.getMerchantId())
                .andEqualTo("status", CommonStatusEnum.ENABLE.getStatus()).andEqualTo("orderType",queryParam.getOrderType())
                .andEqualTo("sourceType",queryParam.getSourceType()).andEqualTo("orderStatus",queryParam.getOrderStatus())
                .andEqualTo("promotionId",queryParam.getPromotionId()).andEqualTo("memberId",queryParam.getMemberId());

        if (StringUtils.isNotEmpty(queryParam.getReceiverKeyword())){
            criteria.andLike("receiverName","%"+queryParam.getReceiverKeyword()+"%");
        }
        if (StringUtils.isNotEmpty(queryParam.getOrderSn())){
            criteria.andLike("orderSn","%"+queryParam.getOrderSn()+"%");
        }
        List<OrderDO> uacUserList = orderDOMapper.selectByExample(example);
        PageInfo<OrderDO> pageInfo = new PageInfo<>(uacUserList);

        //转化
        List<OrderVo> responses = BeanConverter.batchConvert(uacUserList,OrderVo.class);
        PageInfo<OrderVo> result = BeanConverter.convert(pageInfo,PageInfo.class);
        if (CollectionUtils.isNotEmpty(responses)){
            //查询商品详情
            Example itemExample = new Example(OrderItemDO.class);
            itemExample.createCriteria().andIn("orderId",uacUserList.stream().map(item -> item.getId()).collect(Collectors.toList()));
            List<OrderItemDO> orderItemDOList = orderItemService.selectByExample(itemExample);

            //数据分组和设置
            Map<Long,List<OrderItemVo>> itemGroupMap = orderItemDOList.stream().map(item -> BeanConverter.convert(item,OrderItemVo.class))
                    .collect(Collectors.groupingBy(OrderItemVo::getOrderId));
            responses.forEach(item -> {
                item.setProductItems(itemGroupMap.get(item.getId()));
            });
        }
        result.setList(responses);

        return result;
    }

    /**
     * 订单批量发货
     * @param deliveryParamList
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deliveryOrder(List<OrderDeliveryDto> deliveryParamList, UserTokenDto loginAuthDto) {
        if (CollectionUtils.isEmpty(deliveryParamList)){
            return 0;
        }
        List<Long> ids = deliveryParamList.stream().map(item -> item.getOrderId()).collect(Collectors.toList());
        Example example = new Example(OrderDO.class);
        Example.Criteria criteria = example.createCriteria().andIn("id", ids);
        List<OrderDO> reasonDOList = orderDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> {
            PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(), loginAuthDto);
        });

        deliveryParamList.stream().forEach(item -> {
            OrderDO update = new OrderDO();
            update.setId(item.getOrderId());
            update.setUpdateInfo(loginAuthDto);
            update.setExpressType(ExpressTypeEnum.MANUAL.getType());
            update.setExpressTime(new Date());
            update.setExpressCompany(item.getDeliveryCompany());
            update.setExpressSn(item.getDeliverySn());

            orderDOMapper.updateByPrimaryKeySelective(update);
        });
        return deliveryParamList.size();
    }

    /**
     * 批量删除订单
     * @param ids
     * @param loginAuthDto
     * @return
     */
    @Override
    public int deleteOrder(List<Long> ids, UserTokenDto loginAuthDto) {
        if (CollectionUtils.isEmpty(ids)){
            return 0;
        }
        Example example = new Example(OrderDO.class);
        Example.Criteria criteria = example.createCriteria().andIn("id", ids);
        List<OrderDO> reasonDOList = orderDOMapper.selectByExample(example);

        //验证
        reasonDOList.stream().forEach(item -> {
            PermissionCheckUtil.checkDataMerchantPermission(item.getMerchantId(), loginAuthDto);
        });
        OrderDO update = new OrderDO();
        update.setStatus(CommonStatusEnum.DISABLE.getStatus());
        update.setUpdateInfo(loginAuthDto);
        return orderDOMapper.updateByExampleSelective(update,example);
    }

    /**
     * 订单详情 包含订单商品
     * @param id
     * @param loginAuthDto
     * @return
     */
    @Override
    public OrderVo orderDetail(Long id, UserTokenDto loginAuthDto) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
        if (orderDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        PermissionCheckUtil.checkDataMerchantPermission(orderDO.getMerchantId(),loginAuthDto);
        OrderVo result = BeanConverter.convert(orderDO, OrderVo.class);
        //查询商品列表信息
        OrderItemDO param = new OrderItemDO();
        param.setOrderId(orderDO.getId());
        List<OrderItemDO> itemDOList = orderItemService.select(param);

        List<OrderItemVo> productItems = Lists.newArrayList();
        if (CollectionUtils.isNotEmpty(itemDOList)){
            productItems = BeanConverter.batchConvert(itemDOList,OrderItemVo.class);
        }
        result.setProductItems(productItems);

        return result;
    }

    /**
     * 更新收货人信息
     *
     * @param receiverInfoParam
     * @param loginAuthDto
     * @return
     */
    @Override
    public int updateReceiverInfo(ReceiverInfoDto receiverInfoParam, UserTokenDto loginAuthDto) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(receiverInfoParam.getOrderId());
        if (orderDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        PermissionCheckUtil.checkDataMerchantPermission(orderDO.getMerchantId(),loginAuthDto);

        OrderDO update = new OrderDO();
        update.setId(receiverInfoParam.getOrderId());
        update.setReceiverName(receiverInfoParam.getReceiverName());
        update.setReceiverPhone(receiverInfoParam.getReceiverPhone());
        update.setReceiverPostCode(receiverInfoParam.getReceiverPostCode());
        update.setReceiverDetailAddress(receiverInfoParam.getReceiverDetailAddress());
        update.setReceiverProvince(receiverInfoParam.getReceiverProvince());
        update.setReceiverCity(receiverInfoParam.getReceiverCity());
        update.setReceiverRegion(receiverInfoParam.getReceiverRegion());

        update.setUpdateInfo(loginAuthDto);
        return orderDOMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 更新订单备注
     * @param id
     * @param note
     * @param loginAuthDto
     * @return
     */
    @Override
    public int updateNote(Long id, String note, UserTokenDto loginAuthDto) {
        OrderDO orderDO = orderDOMapper.selectByPrimaryKey(id);
        if (orderDO == null){
            throw new OrderBizException(ErrorCodeEnum.OMC10031005);
        }
        PermissionCheckUtil.checkDataMerchantPermission(orderDO.getMerchantId(),loginAuthDto);
        OrderDO update = new OrderDO();
        update.setId(id);
        update.setNote(note);
        update.setUpdateInfo(loginAuthDto);
        return orderDOMapper.updateByPrimaryKeySelective(update);
    }
}
