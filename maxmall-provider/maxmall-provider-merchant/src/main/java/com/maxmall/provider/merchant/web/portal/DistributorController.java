package com.maxmall.provider.merchant.web.portal;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.merchant.model.dto.distributor.DistributorRadioDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrHistoryDto;
import com.maxmall.provider.merchant.model.dto.distributor.QueryDistrbutorDto;
import com.maxmall.provider.merchant.model.vo.DistrMoneyHistoryVo;
import com.maxmall.provider.merchant.model.vo.DistributorVo;
import com.maxmall.provider.merchant.service.DistributorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 分销商管理.
 *
 * @author maxmall.net@gmail.com
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH+"/distributor")
@Api(value = "Web-DistributorController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class DistributorController extends BaseController {

    @Autowired
    private DistributorService distributorService;

    /**
     * 分页查询分销商列表.
     *
     * @param queryDistrbutorDto the uac user
     * @return the wrapper
     */
    @PostMapping(value = "/searchByPage")
    @ApiOperation(httpMethod = "POST", value = "查询分销商户列表")
    public Wrapper<PageInfo<DistributorVo>> queryListWithPage(@Validated @RequestBody QueryDistrbutorDto queryDistrbutorDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        logger.info("查询分销商户列表queryParam={}", queryDistrbutorDto);
        PageInfo<DistributorVo> pageInfo = distributorService.queryDistributorListWithPage(queryDistrbutorDto,loginAuthDto);
        return WrapMapper.ok(pageInfo);
    }

    /**
     * 根据Id查询分销商信息.
     *
     * @param id the user id
     * @return the uac user by id
     */
    @GetMapping(value = "/detail/{id}")
    @ApiOperation(httpMethod = "GET", value = "根据Id查询分销商信息")
    public Wrapper<DistributorVo> getUserById(@ApiParam(name = "id", value = "分销商ID") @PathVariable Long id) {
        logger.info("getUacUserById - 根据Id查询分销商信息. distributorId={}", id);
        UserTokenDto loginAuthDto = getLoginAuthDto();
        DistributorVo uacUser = distributorService.queryByDistributorId(id,loginAuthDto);

        return WrapMapper.ok(uacUser);
    }

    /**
     * 根据Id修改分销商分销比例.
     *
     * @param distributorRadioDto the user id
     * @return the uac user by id
     */
    @PostMapping(value = "/modifySalesRatio")
    @ApiOperation(httpMethod = "POST", value = "根据Id修改分销商分销比例")
    public Wrapper<Integer> modifySalesRatio(@Validated @RequestBody DistributorRadioDto distributorRadioDto) {
        logger.info("getUacUserById - 根据Id修改分销商分销比例. distributorRadioDto={}", distributorRadioDto);

        UserTokenDto loginAuthDto = getLoginAuthDto();
        Integer result = distributorService.modifySalesRatio(distributorRadioDto,loginAuthDto);

        return WrapMapper.ok(result);
    }

    /**
     * 分页查询分销商列表.
     *
     * @return the wrapper
     */
    @PostMapping(value = "/listAll")
    @ApiOperation(httpMethod = "POST", value = "全部分销商户列表")
    public Wrapper<List<DistributorVo>> queryListWithPage() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        List<DistributorVo> result = distributorService.distributorListAll(loginAuthDto);
        return WrapMapper.ok(result);
    }

    /**
     * 分页查询分销商列表.
     *
     * @param queryDistrHistoryDto the uac user
     * @return the wrapper
     */
    @PostMapping(value = "/history/searchByPage")
    @ApiOperation(httpMethod = "POST", value = "查询分销商账户操作记录")
    public Wrapper<PageInfo<DistrMoneyHistoryVo>> queryHistoryListWithPage(@Validated @RequestBody QueryDistrHistoryDto queryDistrHistoryDto) {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        logger.info("查询分销商户列表queryParam={}", queryDistrHistoryDto);
        PageInfo<DistrMoneyHistoryVo> pageInfo = distributorService.queryHistoryListWithPage(queryDistrHistoryDto,loginAuthDto);
        return WrapMapper.ok(pageInfo);
    }

}
