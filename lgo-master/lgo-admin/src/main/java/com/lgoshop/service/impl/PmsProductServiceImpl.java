package com.lgoshop.service.impl;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.*;
import com.lgoshop.dto.PmsProductParam;
import com.lgoshop.dto.PmsProductQueryParam;
import com.lgoshop.dto.PmsProductResult;
import com.lgoshop.model.PmsProduct;
import com.lgoshop.model.PmsProductAttributeValue;
import com.lgoshop.model.PmsProductAttributeValueExample;
import com.lgoshop.model.PmsProductCategory;
import com.lgoshop.model.PmsProductExample;
import com.lgoshop.model.PmsProductFullReduction;
import com.lgoshop.model.PmsProductFullReductionExample;
import com.lgoshop.model.PmsProductLadder;
import com.lgoshop.model.PmsProductLadderExample;
import com.lgoshop.model.PmsProductVertifyRecord;
import com.lgoshop.model.PmsSkuStock;
import com.lgoshop.model.PmsSkuStockExample;
import com.lgoshop.model.PmsMemberPrice;
import com.lgoshop.model.PmsMemberPriceExample;
import com.lgoshop.model.CmsSubjectProductRelation;
import com.lgoshop.model.CmsSubjectProductRelationExample;
import com.lgoshop.model.CmsPrefrenceAreaProductRelation;
import com.lgoshop.model.CmsPrefrenceAreaProductRelationExample;
import com.lgoshop.service.PmsProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class PmsProductServiceImpl implements PmsProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsProductServiceImpl.class);
    @Autowired
    private PmsProductMapper productMapper;
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsProductLadderMapper productLadderMapper;
    @Autowired
    private PmsProductFullReductionMapper productFullReductionMapper;
    @Autowired
    private PmsSkuStockMapper skuStockMapper;
    @Autowired
    private PmsProductAttributeValueMapper productAttributeValueMapper;
    @Autowired
    private CmsSubjectProductRelationMapper subjectProductRelationMapper;
    @Autowired
    private CmsPrefrenceAreaProductRelationMapper prefrenceAreaProductRelationMapper;
    @Autowired
    private PmsProductVertifyRecordMapper productVertifyRecordMapper;

    @Override
    public int create(PmsProductParam productParam) {
        PmsProduct product = productParam;
        product.setId(null);
        productMapper.insert(product);
        Long productId = product.getId();
        relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), productId);
        relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), productId);
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), productId);
        handleSkuStockCode(productParam.getSkuStockList(), productId);
        relateAndInsertList(skuStockMapper, productParam.getSkuStockList(), productId);
        relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), productId);
        relateAndInsertList(subjectProductRelationMapper, productParam.getSubjectProductRelationList(), productId);
        relateAndInsertList(prefrenceAreaProductRelationMapper, productParam.getPrefrenceAreaProductRelationList(), productId);
        return 1;
    }

    private void handleSkuStockCode(List<PmsSkuStock> skuStockList, Long productId) {
        if (CollectionUtils.isEmpty(skuStockList)) return;
        for (int i = 0; i < skuStockList.size(); i++) {
            PmsSkuStock skuStock = skuStockList.get(i);
            if (StrUtil.isEmpty(skuStock.getSkuCode())) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                StringBuilder sb = new StringBuilder();
                sb.append(sdf.format(new Date()));
                sb.append(String.format("%04d", productId));
                sb.append(String.format("%03d", i + 1));
                skuStock.setSkuCode(sb.toString());
            }
        }
    }

    @Override
    public PmsProductResult getUpdateInfo(Long id) {
        return productMapper.getUpdateInfo(id);
    }

    @Override
    public int update(Long id, PmsProductParam productParam) {
        PmsProduct product = productParam;
        product.setId(id);
        productMapper.updateById(product);
        memberPriceMapper.delete(new LambdaQueryWrapper<PmsMemberPrice>());
        relateAndInsertList(memberPriceMapper, productParam.getMemberPriceList(), id);
        productLadderMapper.delete(new LambdaQueryWrapper<PmsProductLadder>());
        relateAndInsertList(productLadderMapper, productParam.getProductLadderList(), id);
        productFullReductionMapper.delete(new LambdaQueryWrapper<PmsProductFullReduction>());
        relateAndInsertList(productFullReductionMapper, productParam.getProductFullReductionList(), id);
        handleUpdateSkuStockList(id, productParam);
        productAttributeValueMapper.delete(new LambdaQueryWrapper<PmsProductAttributeValue>());
        relateAndInsertList(productAttributeValueMapper, productParam.getProductAttributeValueList(), id);
        subjectProductRelationMapper.delete(new LambdaQueryWrapper<CmsSubjectProductRelation>());
        relateAndInsertList(subjectProductRelationMapper, productParam.getSubjectProductRelationList(), id);
        prefrenceAreaProductRelationMapper.delete(new LambdaQueryWrapper<CmsPrefrenceAreaProductRelation>());
        relateAndInsertList(prefrenceAreaProductRelationMapper, productParam.getPrefrenceAreaProductRelationList(), id);
        return 1;
    }

    private void handleUpdateSkuStockList(Long id, PmsProductParam productParam) {
        List<PmsSkuStock> currSkuList = productParam.getSkuStockList();
        if (CollUtil.isEmpty(currSkuList)) {
            skuStockMapper.delete(new LambdaQueryWrapper<PmsSkuStock>());
            return;
        }
        List<PmsSkuStock> oriStuList = skuStockMapper.selectList(new LambdaQueryWrapper<>());
        List<PmsSkuStock> insertSkuList = currSkuList.stream().filter(item -> item.getId() == null).collect(Collectors.toList());
        List<PmsSkuStock> updateSkuList = currSkuList.stream().filter(item -> item.getId() != null).collect(Collectors.toList());
        List<Long> updateSkuIds = updateSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
        List<PmsSkuStock> removeSkuList = oriStuList.stream().filter(item -> !updateSkuIds.contains(item.getId())).collect(Collectors.toList());
        handleSkuStockCode(insertSkuList, id);
        handleSkuStockCode(updateSkuList, id);
        if (CollUtil.isNotEmpty(insertSkuList)) {
            relateAndInsertList(skuStockMapper, insertSkuList, id);
        }
        if (CollUtil.isNotEmpty(removeSkuList)) {
            List<Long> removeSkuIds = removeSkuList.stream().map(PmsSkuStock::getId).collect(Collectors.toList());
            skuStockMapper.delete(new LambdaQueryWrapper<PmsSkuStock>());
        }
        if (CollUtil.isNotEmpty(updateSkuList)) {
            for (PmsSkuStock pmsSkuStock : updateSkuList) {
                skuStockMapper.updateById(pmsSkuStock);
            }
        }
    }

    @Override
    public IPage<PmsProduct> list(PmsProductQueryParam productQueryParam, Integer pageSize, Integer pageNum) {
        Page<PmsProduct> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<PmsProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(PmsProduct::getDeleteStatus, 0)
                .eq(productQueryParam.getPublishStatus() != null, PmsProduct::getPublishStatus, productQueryParam.getPublishStatus())
                .eq(productQueryParam.getVerifyStatus() != null, PmsProduct::getVerifyStatus, productQueryParam.getVerifyStatus())
                .like(StrUtil.isNotEmpty(productQueryParam.getKeyword()), PmsProduct::getName, productQueryParam.getKeyword())
                .eq(StrUtil.isNotEmpty(productQueryParam.getProductSn()), PmsProduct::getProductSn, productQueryParam.getProductSn())
                .eq(productQueryParam.getBrandId() != null, PmsProduct::getBrandId, productQueryParam.getBrandId())
                .eq(productQueryParam.getProductCategoryId() != null, PmsProduct::getProductCategoryId, productQueryParam.getProductCategoryId());
        return productMapper.selectPage(page, queryWrapper);
    }

    @Override
    public int updateVerifyStatus(List<Long> ids, Integer verifyStatus, String detail) {
        PmsProduct product = new PmsProduct();
        product.setVerifyStatus(verifyStatus);
        int count = productMapper.update(product, new LambdaUpdateWrapper<>());
        List<PmsProductVertifyRecord> list = new ArrayList<>();
        for (Long id : ids) {
            PmsProductVertifyRecord record = new PmsProductVertifyRecord();
            record.setProductId(id);
            record.setCreateTime(new Date());
            record.setDetail(detail);
            record.setStatus(verifyStatus);
            record.setVertifyMan("test");
            list.add(record);
        }
        productVertifyRecordMapper.insertList(list);
        return count;
    }

    @Override
    public int updatePublishStatus(List<Long> ids, Integer publishStatus) {
        PmsProduct record = new PmsProduct();
        record.setPublishStatus(publishStatus);
        return productMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public int updateRecommendStatus(List<Long> ids, Integer recommendStatus) {
        PmsProduct record = new PmsProduct();
        record.setRecommandStatus(recommendStatus);
        return productMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public int updateNewStatus(List<Long> ids, Integer newStatus) {
        PmsProduct record = new PmsProduct();
        record.setNewStatus(newStatus);
        return productMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public int updateDeleteStatus(List<Long> ids, Integer deleteStatus) {
        PmsProduct record = new PmsProduct();
        record.setDeleteStatus(deleteStatus);
        return productMapper.update(record, new LambdaUpdateWrapper<>());
    }

    @Override
    public List<PmsProduct> list(String keyword) {
        LambdaQueryWrapper<PmsProduct> wrapper = new LambdaQueryWrapper<PmsProduct>()
                .eq(PmsProduct::getDeleteStatus, 0);
        if (StrUtil.isNotEmpty(keyword)) {
            wrapper.and(w -> w.like(PmsProduct::getName, keyword)
                    .or()
                    .eq(PmsProduct::getDeleteStatus, 0)
                    .like(PmsProduct::getProductSn, keyword));
        }
        return productMapper.selectList(wrapper);
    }

    private void relateAndInsertList(Object dao, List dataList, Long productId) {
        try {
            if (CollectionUtils.isEmpty(dataList)) return;
            for (Object item : dataList) {
                Method setId = item.getClass().getMethod("setId", Long.class);
                setId.invoke(item, (Long) null);
                Method setProductId = item.getClass().getMethod("setProductId", Long.class);
                setProductId.invoke(item, productId);
            }
            Method insertList = dao.getClass().getMethod("insertList", List.class);
            insertList.invoke(dao, dataList);
        } catch (Exception e) {
            LOGGER.warn("创建商品出错:{}", e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }
}
