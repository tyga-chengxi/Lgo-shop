package com.lgoshop.common.service;

import com.lgoshop.common.log.LgoOperateLogRecord;

import java.util.List;

/**
 * LGO-Shop 自研操作日志服务
 * <p>基于 Redis List 存储操作日志记录，无数据库依赖</p>
 *
 * @author lgo-shop
 */
public interface LgoOperateLogService {

    /**
     * Redis 存储键
     */
    String REDIS_KEY = "lgo:operate:log:list";

    /**
     * List 最大保留条数（防止无限膨胀）
     */
    long MAX_SIZE = 2000;

    /**
     * 记录操作日志（LPUSH 写入 Redis List）
     */
    void record(LgoOperateLogRecord record);

    /**
     * 分页查询操作日志（LRANGE 读取）
     *
     * @param pageNum  页码，从 1 开始
     * @param pageSize 每页条数
     * @return 操作日志列表
     */
    List<LgoOperateLogRecord> list(int pageNum, int pageSize);
}
