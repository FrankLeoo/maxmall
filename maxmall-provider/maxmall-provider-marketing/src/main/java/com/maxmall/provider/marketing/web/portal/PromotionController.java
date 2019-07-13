package com.maxmall.provider.marketing.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.base.enums.ErrorCodeEnum;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.marketing.model.dto.PromotionModifyDto;
import com.maxmall.provider.marketing.model.dto.PromotionQueryDto;
import com.maxmall.provider.marketing.model.enums.CouponUseTypeEnum;
import com.maxmall.provider.marketing.model.enums.PromotionUseTypeEnum;
import com.maxmall.provider.marketing.model.vo.PromotionVo;
import com.maxmall.provider.marketing.service.PromotionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * 活动管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/promotion")
@Api(value = "WEB - PromotionController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PromotionController extends BaseController {

    @Autowired
    private PromotionService promotionService;

    @ApiOperation(value = "添加或更新活动", httpMethod = "POST")
    @PostMapping(value = "/modify")
    public Wrapper<Integer> savePromotion(@Validated @RequestBody PromotionModifyDto promotionParam) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PromotionUseTypeEnum type = PromotionUseTypeEnum.getByType(promotionParam.getUseType());
        if (type != PromotionUseTypeEnum.ALL && CollectionUtils.isEmpty(promotionParam.getRelationList())){
            return WrapMapper.error(ErrorCodeEnum.TPC100600002);
        }

        int count = promotionService.saveOrModifyPromotion(promotionParam,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "删除活动", httpMethod = "POST")
    @PostMapping(value = "/delete/{id}")
    public Wrapper<Integer> deletePromotion(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int count = promotionService.deletePromotion(id,loginAuthDto);
        if(count>0){
            return WrapMapper.ok(count);
        }
        return WrapMapper.error();
    }

    @ApiOperation(value = "分页获取活动列表", httpMethod = "POST")
    @PostMapping(value = "/searchByPage")
    public Wrapper<PageInfo<PromotionVo>> searchByPage(@Validated @RequestBody PromotionQueryDto queryParam){
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PageInfo<PromotionVo> orderList = promotionService.queryPromotionListWithPage(queryParam, loginAuthDto);
        return WrapMapper.ok(orderList);
    }

    @ApiOperation(value = "获取单个活动的详细信息", httpMethod = "GET")
    @GetMapping(value = "/detail/{id}")
    public Wrapper<PromotionVo> promotionDetail(@PathVariable Long id) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        PromotionVo promotionParam = promotionService.promotionDetail(id,loginAuthDto);
        return WrapMapper.ok(promotionParam);
    }

}
