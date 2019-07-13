package com.maxmall.provider.order.web.portal;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.order.model.vo.CompanyAddressVo;
import com.maxmall.provider.order.service.CompanyAddressService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


/**
 * 收货地址管理Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.PORTAL_PATH + "/companyAddress")
@Api(value = "WEB - CompanyAddressController", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class CompanyAddressController extends BaseController {

    @Autowired
    private CompanyAddressService companyAddressService;

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/listAll", method = RequestMethod.GET)
    public Wrapper<List<CompanyAddressVo>> listAll() {
        UserTokenDto loginAuthDto = getLoginAuthDto();

        List<CompanyAddressVo> companyAddressList = companyAddressService.listAllAddress(loginAuthDto);
        return WrapMapper.ok(companyAddressList);
    }
}
