package com.lgoshop.portal.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lgoshop.mapper.OmsCartItemMapper;
import com.lgoshop.model.OmsCartItem;
import com.lgoshop.model.UmsMember;
import com.lgoshop.portal.dao.PortalProductDao;
import com.lgoshop.portal.domain.CartProduct;
import com.lgoshop.portal.domain.CartPromotionItem;
import com.lgoshop.portal.service.OmsCartItemService;
import com.lgoshop.portal.service.OmsPromotionService;
import com.lgoshop.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 购物车管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class OmsCartItemServiceImpl implements OmsCartItemService {
    @Autowired
    private OmsCartItemMapper cartItemMapper;
    @Autowired
    private PortalProductDao productDao;
    @Autowired
    private OmsPromotionService promotionService;
    @Autowired
    private UmsMemberService memberService;

    @Override
    public int add(OmsCartItem cartItem) {
        int count;
        UmsMember currentMember = memberService.getCurrentMember();
        cartItem.setMemberId(currentMember.getId());
        cartItem.setMemberNickname(currentMember.getNickname());
        cartItem.setDeleteStatus(0);
        OmsCartItem existCartItem = getCartItem(cartItem);
        if (existCartItem == null) {
            cartItem.setCreateDate(new Date());
            count = cartItemMapper.insert(cartItem);
        } else {
            cartItem.setModifyDate(new Date());
            existCartItem.setQuantity(existCartItem.getQuantity() + cartItem.getQuantity());
            count = cartItemMapper.updateById(existCartItem);
        }
        return count;
    }

    private OmsCartItem getCartItem(OmsCartItem cartItem) {
        LambdaQueryWrapper<OmsCartItem> wrapper = new LambdaQueryWrapper<OmsCartItem>()
                .eq(OmsCartItem::getMemberId, cartItem.getMemberId())
                .eq(OmsCartItem::getProductId, cartItem.getProductId())
                .eq(OmsCartItem::getDeleteStatus, 0);
        if (cartItem.getProductSkuId() != null) {
            wrapper.eq(OmsCartItem::getProductSkuId, cartItem.getProductSkuId());
        }
        List<OmsCartItem> cartItemList = cartItemMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(cartItemList)) {
            return cartItemList.get(0);
        }
        return null;
    }

    @Override
    public List<OmsCartItem> list(Long memberId) {
        LambdaQueryWrapper<OmsCartItem> wrapper = new LambdaQueryWrapper<OmsCartItem>()
                .eq(OmsCartItem::getDeleteStatus, 0)
                .eq(OmsCartItem::getMemberId, memberId);
        return cartItemMapper.selectList(wrapper);
    }

    @Override
    public List<CartPromotionItem> listPromotion(Long memberId, List<Long> cartIds) {
        List<OmsCartItem> cartItemList = list(memberId);
        if (CollUtil.isNotEmpty(cartIds)) {
            cartItemList = cartItemList.stream().filter(item -> cartIds.contains(item.getId())).collect(Collectors.toList());
        }
        List<CartPromotionItem> cartPromotionItemList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(cartItemList)) {
            cartPromotionItemList = promotionService.calcCartPromotion(cartItemList);
        }
        return cartPromotionItemList;
    }

    @Override
    public int updateQuantity(Long id, Long memberId, Integer quantity) {
        OmsCartItem cartItem = new OmsCartItem();
        cartItem.setQuantity(quantity);
        return cartItemMapper.update(cartItem, new LambdaUpdateWrapper<OmsCartItem>()
                .eq(OmsCartItem::getDeleteStatus, 0)
                .eq(OmsCartItem::getId, id)
                .eq(OmsCartItem::getMemberId, memberId));
    }

    @Override
    public int delete(Long memberId, List<Long> ids) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        return cartItemMapper.update(record, new LambdaUpdateWrapper<OmsCartItem>()
                .eq(OmsCartItem::getMemberId, memberId)
                .in(OmsCartItem::getId, ids));
    }

    @Override
    public CartProduct getCartProduct(Long productId) {
        return productDao.getCartProduct(productId);
    }

    @Override
    public int updateAttr(OmsCartItem cartItem) {
        OmsCartItem updateCart = new OmsCartItem();
        updateCart.setId(cartItem.getId());
        updateCart.setModifyDate(new Date());
        updateCart.setDeleteStatus(1);
        cartItemMapper.updateById(updateCart);
        cartItem.setId(null);
        add(cartItem);
        return 1;
    }

    @Override
    public int clear(Long memberId) {
        OmsCartItem record = new OmsCartItem();
        record.setDeleteStatus(1);
        return cartItemMapper.update(record, new LambdaUpdateWrapper<OmsCartItem>()
                .eq(OmsCartItem::getMemberId, memberId));
    }
}
