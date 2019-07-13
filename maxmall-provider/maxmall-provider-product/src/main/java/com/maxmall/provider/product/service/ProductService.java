package com.maxmall.provider.product.service;

import com.github.pagehelper.PageInfo;
import com.maxmall.common.base.dto.UserTokenDto;
import com.maxmall.common.util.wrapper.Wrapper;
import com.maxmall.provider.product.model.domain.espojo.EsProduct;
import com.maxmall.provider.product.model.dto.*;
import com.maxmall.provider.product.model.vo.ProductVo;

import java.util.ArrayList;
import java.util.List;

/**
 * 商品service管理类
 */
public interface ProductService {

    /**
     * 查询商品分页信息
     * @param queryParam
     * @param loginAuthDto
     * @return
     */
    PageInfo<ProductVo> queryProductListWithPage(ProductQueryDto queryParam, UserTokenDto loginAuthDto);

    /**
     * 获取商品详情
     * @param id
     * @param loginAuthDto
     * @return
     */
    ProductVo productDetail(Long id, UserTokenDto loginAuthDto);

    /**
     * 创建商品
     * @param modifyDto
     * @param loginAuthDto
     * @return
     */
    int saveOrModifyProduct(ProductModifyDto modifyDto, UserTokenDto loginAuthDto);

    /**
     * 查询es 索引数据
     * @param id
     * @return
     */
    List<EsProduct> getAllEsProductList(Long id);

    /**
     * 根据商品id查询
     * @param productIds
     * @return
     */
    Wrapper<List<ProductDto>> selectByIds(List<Long> productIds,Long merchantId);

    /**
     * 批量删除
     * @param ids
     * @param loginAuthDto
     * @return
     */
    int deleteByIds(ArrayList<Long> ids, UserTokenDto loginAuthDto);

    /**
     * 更新商品label
     * @param modifyDto
     * @param loginAuthDto
     * @return
     */
    int modifyProductLabel(ProductLabelModifyDto modifyDto, UserTokenDto loginAuthDto);

    /**
     * 全部查询
     * @param loginAuthDto
     * @param queryParam
     * @return
     */
    List<ProductVo> productListAll(UserTokenDto loginAuthDto, ProductListQueryDto queryParam);
}
