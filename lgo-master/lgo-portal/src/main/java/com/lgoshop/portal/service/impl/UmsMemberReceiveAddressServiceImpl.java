package com.lgoshop.portal.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.lgoshop.mapper.UmsMemberReceiveAddressMapper;
import com.lgoshop.model.UmsMember;
import com.lgoshop.model.UmsMemberReceiveAddress;
import com.lgoshop.portal.service.UmsMemberReceiveAddressService;
import com.lgoshop.portal.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 用户地址管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class UmsMemberReceiveAddressServiceImpl implements UmsMemberReceiveAddressService {
    @Autowired
    private UmsMemberService memberService;
    @Autowired
    private UmsMemberReceiveAddressMapper addressMapper;

    @Override
    public int add(UmsMemberReceiveAddress address) {
        UmsMember currentMember = memberService.getCurrentMember();
        address.setMemberId(currentMember.getId());
        return addressMapper.insert(address);
    }

    @Override
    public int delete(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        return addressMapper.delete(new LambdaQueryWrapper<UmsMemberReceiveAddress>()
                .eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId())
                .eq(UmsMemberReceiveAddress::getId, id));
    }

    @Override
    public int update(Long id, UmsMemberReceiveAddress address) {
        address.setId(null);
        UmsMember currentMember = memberService.getCurrentMember();
        if (address.getDefaultStatus() == null) {
            address.setDefaultStatus(0);
        }
        if (address.getDefaultStatus() == 1) {
            UmsMemberReceiveAddress record = new UmsMemberReceiveAddress();
            record.setDefaultStatus(0);
            addressMapper.update(record, new LambdaUpdateWrapper<UmsMemberReceiveAddress>()
                    .eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId())
                    .eq(UmsMemberReceiveAddress::getDefaultStatus, 1));
        }
        return addressMapper.update(address, new LambdaUpdateWrapper<UmsMemberReceiveAddress>()
                .eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId())
                .eq(UmsMemberReceiveAddress::getId, id));
    }

    @Override
    public List<UmsMemberReceiveAddress> list() {
        UmsMember currentMember = memberService.getCurrentMember();
        LambdaQueryWrapper<UmsMemberReceiveAddress> wrapper = new LambdaQueryWrapper<UmsMemberReceiveAddress>()
                .eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId());
        return addressMapper.selectList(wrapper);
    }

    @Override
    public UmsMemberReceiveAddress getItem(Long id) {
        UmsMember currentMember = memberService.getCurrentMember();
        LambdaQueryWrapper<UmsMemberReceiveAddress> wrapper = new LambdaQueryWrapper<UmsMemberReceiveAddress>()
                .eq(UmsMemberReceiveAddress::getMemberId, currentMember.getId())
                .eq(UmsMemberReceiveAddress::getId, id);
        List<UmsMemberReceiveAddress> addressList = addressMapper.selectList(wrapper);
        if (!CollectionUtils.isEmpty(addressList)) {
            return addressList.get(0);
        }
        return null;
    }
}
