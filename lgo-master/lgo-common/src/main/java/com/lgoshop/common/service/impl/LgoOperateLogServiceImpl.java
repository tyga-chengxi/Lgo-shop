package com.lgoshop.common.service.impl;

import com.lgoshop.common.log.LgoOperateLogRecord;
import com.lgoshop.common.service.LgoOperateLogService;
import com.lgoshop.common.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * LGO-Shop 自研操作日志服务实现
 * <p>
 * LPUSH 写入 Redis List，LRANGE 分页读取。
 * 写入后自动裁剪超出 {@code MAX_SIZE} 的旧数据。
 * </p>
 *
 * @author lgo-shop
 */
@Service
public class LgoOperateLogServiceImpl implements LgoOperateLogService {

    private static final Logger log = LoggerFactory.getLogger(LgoOperateLogServiceImpl.class);

    @Autowired
    private RedisService redisService;

    @Override
    public void record(LgoOperateLogRecord record) {
        try {
            redisService.lPush(REDIS_KEY, record);
            // LPUSH 新数据在头部（索引 0），保留最新 MAX_SIZE 条
            redisService.lTrim(REDIS_KEY, 0, MAX_SIZE - 1);
        } catch (Exception e) {
            log.error("【操作日志】写入 Redis 失败", e);
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<LgoOperateLogRecord> list(int pageNum, int pageSize) {
        try {
            // Redis List LPUSH 写入，最新数据在头部（索引 0）
            // 分页：第 1 页 = 0 ~ pageSize-1
            long start = (long) (pageNum - 1) * pageSize;
            long end = start + pageSize - 1;
            List<Object> list = redisService.lRange(REDIS_KEY, start, end);
            if (list == null || list.isEmpty()) {
                return Collections.emptyList();
            }
            return list.stream()
                    .map(obj -> obj instanceof LgoOperateLogRecord ? (LgoOperateLogRecord) obj : null)
                    .filter(r -> r != null)
                    .collect(Collectors.toList());
        } catch (Exception e) {
            log.error("【操作日志】查询 Redis 失败", e);
            return Collections.emptyList();
        }
    }
}
