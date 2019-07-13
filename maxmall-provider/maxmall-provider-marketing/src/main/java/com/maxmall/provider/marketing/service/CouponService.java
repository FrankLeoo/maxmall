package com.maxmall.provider.marketing.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.marketing.model.domain.market.CouponDO;
import com.maxmall.provider.marketing.model.dto.CouponModifyDto;
import com.maxmall.provider.marketing.model.dto.CouponQueryDto;
import com.maxmall.provider.marketing.model.vo.CouponVo;

import java.util.List;

/**
 * @author ivoter
 * @ClassName OrderItemService.java
 * @date 2019/05/21 17:18:00
 * @Description 优惠券service
 */
public interface CouponService extends IService<CouponDO> {

    /**
     * 删除优惠券
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deleteCoupon(Long id, UserTokenDto loginAuthDto);

    /**
     * 新增或
     * @param couponParam
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyCoupon(CouponModifyDto couponParam, UserTokenDto loginAuthDto);

    /**
     * 分页查询优惠券列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<CouponVo> queryCouponListWithPage(CouponQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 优惠券详情
     * @param id
     * @return
     */
    CouponVo couponDetail(Long id, UserTokenDto loginAuthDto);

    /**
     * 获取优惠券列表
     * @param loginAuthDto
     * @param queryParam
     * @return
     */
    List<CouponVo> couponListAll(UserTokenDto loginAuthDto, CouponQueryDto queryParam);

}
