package com.lgoshop.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.*;
import com.lgoshop.model.*;
import com.lgoshop.portal.dao.PortalProductDao;
import com.lgoshop.portal.domain.PmsPortalProductDetail;
import com.lgoshop.portal.domain.PmsProductCategoryNode;
import com.lgoshop.portal.service.PmsPortalProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 前台订单管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsPortalProductServiceImpl implements PmsPortalProductService {
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsProductCategoryMapper productCategoryMapper;
    @Autowired
    private PmsBrandMapper brandMapper;
    @Autowired
    private PmsProductAttributeMapper productAttributeMapper;
    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PortalProductDao portalProductDao;

    @Override
    public IPage<PmsProduct> search(String keyword, Long brandId, Long productCategoryId, Integer pageNum, Integer pageSize, Integer sort) {
        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProduct> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(PmsProduct::getDeleteStatus, 0)
               .eq(PmsProduct::getPublishStatus, 1)
               .like(StrUtil.isNotEmpty(keyword), PmsProduct::getName, keyword)
               .eq(brandId != null, PmsProduct::getBrandId, brandId)
               .eq(productCategoryId != null, PmsProduct::getProductCategoryId, productCategoryId);
        if (sort == 1) wrapper.orderByDesc(PmsProduct::getId);
        else if (sort == 2) wrapper.orderByDesc(PmsProduct::getSale);
        else if (sort == 3) wrapper.orderByAsc(PmsProduct::getPrice);
        else if (sort == 4) wrapper.orderByDesc(PmsProduct::getPrice);
        return productMapper.selectPage(page, wrapper);
    }

    @Override
    public List<PmsProductCategoryNode> categoryTreeList() {
        List<PmsProductCategory> allList = productCategoryMapper.selectList(null);
        List<PmsProductCategoryNode> result = allList.stream()
                .filter(item -> item.getParentId().equals(0L))
                .map(item -> covert(item, allList))
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public PmsPortalProductDetail detail(Long id) {
        PmsPortalProductDetail result = new PmsPortalProductDetail();
        PmsProduct product = productMapper.selectById(id);
        result.setProduct(product);
        PmsBrand brand = brandMapper.selectById(product.getBrandId());
        result.setBrand(brand);
        PmsProductAttributeExample attributeExample = new PmsProductAttributeExample();
        attributeExample.createCriteria().andProductAttributeCategoryIdEqualTo(product.getProductAttributeCategoryId());
        List<PmsProductAttribute> productAttributeList = productAttributeMapper.selectList(null);
        result.setProductAttributeList(productAttributeList);
        if (CollUtil.isNotEmpty(productAttributeList)) {
            List<Long> attributeIds = productAttributeList.stream().map(PmsProductAttribute::getId).collect(Collectors.toList());
            PmsProductAttributeValueExample attributeValueExample = new PmsProductAttributeValueExample();
            attributeValueExample.createCriteria().andProductIdEqualTo(product.getId())
                    .andProductAttributeIdIn(attributeIds);
            List<PmsProductAttributeValue> productAttributeValueList = productAttributeValueMapper.selectList(null);
            result.setProductAttributeValueList(productAttributeValueList);
        }
        PmsSkuStockExample skuExample = new PmsSkuStockExample();
        skuExample.createCriteria().andProductIdEqualTo(product.getId());
        List<PmsSkuStock> skuStockList = skuStockMapper.selectList(null);
        result.setSkuStockList(skuStockList);
        if (product.getPromotionType() == 3) {
            PmsProductLadderExample ladderExample = new PmsProductLadderExample();
            ladderExample.createCriteria().andProductIdEqualTo(product.getId());
            List<PmsProductLadder> productLadderList = productLadderMapper.selectList(null);
            result.setProductLadderList(productLadderList);
        }
        if (product.getPromotionType() == 4) {
            PmsProductFullReductionExample fullReductionExample = new PmsProductFullReductionExample();
            fullReductionExample.createCriteria().andProductIdEqualTo(product.getId());
            List<PmsProductFullReduction> productFullReductionList = productFullReductionMapper.selectList(null);
            result.setProductFullReductionList(productFullReductionList);
        }
        result.setCouponList(portalProductDao.getAvailableCouponList(product.getId(), product.getProductCategoryId()));
        return result;
    }

    private PmsProductCategoryNode covert(PmsProductCategory item, List<PmsProductCategory> allList) {
        PmsProductCategoryNode node = new PmsProductCategoryNode();
        BeanUtils.copyProperties(item, node);
        List<PmsProductCategoryNode> children = allList.stream()
                .filter(subItem -> subItem.getParentId().equals(item.getId()))
                .map(subItem -> covert(subItem, allList)).collect(Collectors.toList());
        node.setChildren(children);
        return node;
    }
}
