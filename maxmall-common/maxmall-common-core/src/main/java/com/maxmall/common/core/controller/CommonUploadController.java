package com.maxmall.common.core.controller;

import com.maxmall.common.base.constant.GlobalConstant;
import com.maxmall.common.core.support.BaseController;
import com.maxmall.common.core.support.TxCosManager;
import com.maxmall.common.util.wrapper.WrapMapper;
import com.maxmall.common.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 文件上传Controller
 * Created by maxmall on 2018/4/26.
 */
@RestController
@RequestMapping(value = GlobalConstant.COMMON_PATH + "/upload")
@Api(value = "WEB - CommonUploadController")
@ConditionalOnProperty(name = "maxmall.cos.endable", havingValue = "true", matchIfMissing = false)
public class CommonUploadController extends BaseController {

    @Autowired
    private TxCosManager txCosManager;

    @PostMapping("/upload")
    public Wrapper<List<String>> upload(@RequestBody MultipartFile[] files) throws Exception {
        List<String> rs = new ArrayList<String>();
        for (MultipartFile file : files) {
            String originName = file.getOriginalFilename();
            String name = UUID.randomUUID().toString().replace("-", "") + originName.substring(originName.lastIndexOf("."), originName.length());

            String filePath = txCosManager.imageUpload(file, name);
            rs.add(filePath);
        }
        return WrapMapper.ok(rs);
    }
}
