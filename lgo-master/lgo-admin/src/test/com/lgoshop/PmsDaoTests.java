package com.lgoshop;


import cn.hutool.json.JSONUtil;
import com.lgoshop.dto.PmsProductResult;
import com.lgoshop.mapper.PmsMemberPriceMapper;
import com.lgoshop.mapper.PmsProductMapper;
import com.lgoshop.model.PmsMemberPrice;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PmsDaoTests {
    @Autowired
    private PmsMemberPriceMapper memberPriceMapper;
    @Autowired
    private PmsProductMapper productMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(PmsDaoTests.class);
    @Test
    @Transactional
    @Rollback
    public void testInsertBatch(){
        List<PmsMemberPrice> list = new ArrayList<>();
        for(int i=0;i<5;i++){
            PmsMemberPrice memberPrice = new PmsMemberPrice();
            memberPrice.setProductId(1L);
            memberPrice.setMemberLevelId((long) (i+1));
            memberPrice.setMemberPrice(new BigDecimal("22"));
            list.add(memberPrice);
        }
        int count = memberPriceMapper.insertList(list);
        assertEquals(5,count);
    }

    @Test
    public void  testGetProductUpdateInfo(){
        PmsProductResult productResult = productMapper.getUpdateInfo(7L);
        String json = JSONUtil.parse(productResult).toString();
        LOGGER.info(json);
    }
}
