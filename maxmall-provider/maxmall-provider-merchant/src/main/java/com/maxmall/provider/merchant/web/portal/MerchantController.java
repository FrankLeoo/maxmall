package com.maxmall.provider.merchant.web.portal;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.annotation.LogAnnotation;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantConfigDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantPayDto;
import com.maxmall.provider.merchant.model.dto.merchant.MerchantTimeDto;
import com.maxmall.provider.merchant.model.vo.MerchantConfigVo;
import com.maxmall.provider.merchant.model.vo.MerchantVo;
import com.maxmall.provider.merchant.service.MerchantConfigService;
import com.maxmall.provider.merchant.service.MerchantService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商户管理.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/merchant")
@Api(value = "Web-MerchantController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MerchantController extends BaseController {

    @Autowired
    private MerchantConfigService merchantConfigService;
    @Autowired
    private MerchantService merchantService;

    /**
     * 获取当前登录用户的商户信息
     *
     * @return the uac user by id
     */
    @PostMapping(value = "/current/detail")
    @ApiOperation(httpMethod = "POST", value = "获取当前登录用户的商户信息")
    public Wrapper<MerchantVo> getCurrentMerchant() {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        MerchantVo uacUser = merchantService.getMerchantById(loginAuthDto.getMerchantId(),loginAuthDto);

        return WrapMapper.ok(uacUser);
    }

    /**
     * 查询商户的配置信息.
     *
     * @return the uac user by id
     */
    @PostMapping(value = "/current/config")
    @ApiOperation(httpMethod = "POST", value = "查询商户的配置信息")
    public Wrapper<MerchantConfigVo> getCurrentConfig() {
        UserTokenDto loginAuthDto = getLoginAuthDto();
        MerchantConfigVo configVo = merchantConfigService.getConfigByMerchantId(loginAuthDto.getMerchantId(),loginAuthDto);

        return WrapMapper.ok(configVo);
    }

    /**
     * 更新支付配置信息.
     *
     * @param merchantPayDto the modify user status dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/modify/payconfig")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "更新支付配置信息")
    public Wrapper<Integer> modifyPayconfig(@Validated @RequestBody MerchantPayDto merchantPayDto) {
        logger.info(" 更新支付配置信息 merchantPayDto={}", merchantPayDto);
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int result = merchantConfigService.modifyPayconfig(merchantPayDto, loginAuthDto);
        return handleResult(result);
    }

    /**
     * 更新订单时间配置信息.
     *
     * @param merchantTimeDto the modify user status dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/modify/timeconfig")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "更新订单时间配置信息")
    public Wrapper<Integer> modifyTimeconfig(@Validated @RequestBody MerchantTimeDto merchantTimeDto) {
        logger.info(" 更新订单时间配置信息 merchantPayDto={}", merchantTimeDto);
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int result = merchantConfigService.modifyTimeconfig(merchantTimeDto, loginAuthDto);
        return handleResult(result);
    }

    /**
     * 更新基础配置信息.
     *
     * @param merchantConfigDto the modify user status dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/modify/config")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "更新基础配置信息")
    public Wrapper<Integer> modifyConfig(@Validated @RequestBody MerchantConfigDto merchantConfigDto) {
        logger.info(" 更新基础配置信息 merchantPayDto={}", merchantConfigDto);
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int result = merchantConfigService.modifyConfig(merchantConfigDto, loginAuthDto);
        return handleResult(result);
    }

}
