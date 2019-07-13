package com.maxmall.provider.product.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.product.model.domain.BrandDO;
import com.maxmall.provider.product.model.dto.BrandModifyDto;
import com.maxmall.provider.product.model.dto.BrandQueryDto;
import com.maxmall.provider.product.model.vo.BrandVo;

import java.util.List;

/**
 * 品牌管理
 * 商品品牌service
 * @author ivoter
 */
public interface BrandService extends IService<BrandDO> {

    /**
     * 查询全部品牌
     * @param loginAuthDto
     * @return
     */
    List<BrandVo> listAllBrand(UserTokenDto loginAuthDto);

    /**
     * 分页获取品牌列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<BrandVo> queryBrandListWithPage(BrandQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 添加或修改品牌
     * @param brandDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyBrand(BrandModifyDto brandDto, UserTokenDto loginAuthDto);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteBrand(Long id, UserTokenDto loginAuthDto);

    /**
     * 品牌详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    BrandVo brandDetail(Long id, UserTokenDto loginAuthDto);
}
