package com.lgoshop.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lgoshop.mapper.SmsHomeAdvertiseMapper;
import com.lgoshop.model.SmsHomeAdvertise;
import com.lgoshop.model.SmsHomeAdvertiseExample;
import com.lgoshop.service.SmsHomeAdvertiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 首页广告管理Service实现类
 * Created by lgo-shop.
 */
@Service
public class SmsHomeAdvertiseServiceImpl implements SmsHomeAdvertiseService {
    @Autowired
    private SmsHomeAdvertiseMapper advertiseMapper;

    @Override
    public int create(SmsHomeAdvertise advertise) {
        advertise.setClickCount(0);
        advertise.setOrderCount(0);
        return advertiseMapper.insert(advertise);
    }

    @Override
    public int delete(List<Long> ids) {
        return advertiseMapper.delete(new LambdaQueryWrapper<SmsHomeAdvertise>());
    }

    @Override
    public int updateStatus(Long id, Integer status) {
        SmsHomeAdvertise record = new SmsHomeAdvertise();
        record.setId(id);
        record.setStatus(status);
        return advertiseMapper.updateById(record);
    }

    @Override
    public SmsHomeAdvertise getItem(Long id) {
        return advertiseMapper.selectById(id);
    }

    @Override
    public int update(Long id, SmsHomeAdvertise advertise) {
        advertise.setId(id);
        return advertiseMapper.updateById(advertise);
    }

    @Override
    public IPage<SmsHomeAdvertise> list(String name, Integer type, String endTime, Integer pageSize, Integer pageNum) {
        Page<SmsHomeAdvertise> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<SmsHomeAdvertise> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(name), SmsHomeAdvertise::getName, name)
               .eq(type != null, SmsHomeAdvertise::getType, type);
        if (StrUtil.isNotEmpty(endTime)) {
            String startStr = endTime + " 00:00:00";
            String endStr = endTime + " 23:59:59";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try {
                Date start = sdf.parse(startStr);
                Date end = sdf.parse(endStr);
                wrapper.between(SmsHomeAdvertise::getEndTime, start, end);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return advertiseMapper.selectPage(page, wrapper);
    }
}
