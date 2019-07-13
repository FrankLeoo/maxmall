package com.maxmall.provider.product.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.core.support.IService;
import com.maxmall.provider.product.model.domain.BrandDO;
import com.maxmall.provider.product.model.domain.ProductCategoryDO;
import com.maxmall.provider.product.model.dto.BrandModifyDto;
import com.maxmall.provider.product.model.dto.BrandQueryDto;
import com.maxmall.provider.product.model.dto.CategoryModifyDto;
import com.maxmall.provider.product.model.dto.CategoryQueryDto;
import com.maxmall.provider.product.model.vo.BrandVo;
import com.maxmall.provider.product.model.vo.CategoryVo;

import java.util.List;

/**
 * 品牌管理
 * 商品品牌service
 * @author ivoter
 */
public interface CategoryService extends IService<ProductCategoryDO> {

    /**
     * 查询全部品牌
     * @param loginAuthDto
     * @return
     */
    List<CategoryVo> listAllCategory(UserTokenDto loginAuthDto);

    /**
     * 分页获取品牌列表
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<CategoryVo> queryCategoryListWithPage(CategoryQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 添加或修改品牌
     * @param categoryDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyCategory(CategoryModifyDto categoryDto, UserTokenDto loginAuthDto);

    /**
     * 删除品牌
     * @param id
     * @return
     */
    int deleteCategory(Long id, UserTokenDto loginAuthDto);

    /**
     * 品牌详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    CategoryVo categoryDetail(Long id, UserTokenDto loginAuthDto);

    /**
     * 更新显示状态
     * @param ids
     * @param showStatus
     * @return
     */
    int updateShowStatus(List<Long> ids, Integer showStatus,UserTokenDto loginAuthDto);

    /**
     * 获取树列表
     * @param loginAuthDto
     * @return
     */
    List<CategoryVo> getTreeCategory(UserTokenDto loginAuthDto,CategoryQueryDto queryParam);
}
