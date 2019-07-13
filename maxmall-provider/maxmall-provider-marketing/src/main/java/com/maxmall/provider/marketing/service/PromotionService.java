package com.maxmall.provider.marketing.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.marketing.model.domain.market.PromotionDO;
import com.maxmall.provider.marketing.model.dto.PromotionModifyDto;
import com.maxmall.provider.marketing.model.dto.PromotionQueryDto;
import com.maxmall.provider.marketing.model.vo.PromotionVo;

/**
 * @author ivoter
 * @ClassName OrderItemService.java
 * @date 2019/05/21 17:18:00
 * @Description 活动service
 */
public interface PromotionService extends IService<PromotionDO> {

    /**
     * 新增或更新活动
     * @param promotionParam
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyPromotion(PromotionModifyDto promotionParam, UserTokenDto loginAuthDto);

    /**
     * 删除活动
     * @param id
     * @param loginAuthDto
     * @return
     */
    int deletePromotion(Long id, UserTokenDto loginAuthDto);

    /**
     * 分页查询互动
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<PromotionVo> queryPromotionListWithPage(PromotionQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 查询活动详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    PromotionVo promotionDetail(Long id, UserTokenDto loginAuthDto);
}
