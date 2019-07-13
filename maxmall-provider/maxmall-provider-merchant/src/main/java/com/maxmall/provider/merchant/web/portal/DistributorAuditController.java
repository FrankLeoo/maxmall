package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.annotation.LogAnnotation;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.distributor.ModifyDistributorDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorAuditDto;
import com.maxmall.provider.merchant.model.vo.DistributorAuditVo;
import com.maxmall.provider.merchant.service.DistributorAuditService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 分销商管理.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/distributor/audit")
@Api(value = "Web-DistributorAuditController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DistributorAuditController extends BaseController {

    @Autowired
    private DistributorAuditService distributorAuditService;

    /**
     * 分页查询分销商审核列表.
     *
     * @param queryDistrbutorDto the uac user
     * @return the wrapper
     */
    @PostMapping(value = "/searchByPage")
    @ApiOperation(httpMethod = "POST", value = "查询分销商申请列表")
    public Wrapper<PageInfo<DistributorAuditVo>> queryDistributorListWithPage(@Validated @RequestBody QueryDistrbutorAuditDto queryDistrbutorDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        logger.info("查询分销商申请列表queryParam={}", queryDistrbutorDto);
        PageInfo<DistributorAuditVo> pageInfo = distributorAuditService.queryAuditListWithPage(queryDistrbutorDto,loginAuthDto);
        return WrapMapper.ok(pageInfo);
    }

    /**
     * 审核分销员申请.
     *
     * @param modifyDistributorDto the modify user status dto
     *
     * @return the wrapper
     */
    @PostMapping(value = "/applyAudit")
    @LogAnnotation
    @ApiOperation(httpMethod = "POST", value = "审核分销员申请")
    public Wrapper<Integer> applyAudit(@Validated @RequestBody ModifyDistributorDto modifyDistributorDto) {
        logger.info(" 审核分销员申请 modifyDistributorDto={}", modifyDistributorDto);
        UserTokenDto loginAuthDto = getLoginAuthDto();

        int result = distributorAuditService.auditDistributor(modifyDistributorDto, loginAuthDto);
        return handleResult(result);
    }

}
