package com.lgoshop.common.log;

import lombok.Data;

import java.io.Serializable;

/**
 * LGO-Shop 自研操作日志记录模型
 * <p>
 * 由 {@code LgoOperateLogAspect} 自动构建，经 {@code LgoOperateLogService}
 * 以 JSON 格式写入 Redis List（LPUSH），支持 LRANGE 分页查询。
 * </p>
 *
 * @author lgo-shop
 */
@Data
public class LgoOperateLogRecord implements Serializable {

    /** 固定值 1L，所有版本序列化兼容（JSON序列化为主，不依赖Java原生序列化） */
    private static final long serialVersionUID = 1L;

    /** 日志主键 UUID */
    private String operId;

    /** 模块标题（如 "用户管理"、"订单管理"） */
    private String title;

    /** 业务类型（INSERT / UPDATE / DELETE / LOGIN / IMPORT） */
    private String businessType;

    /** 全限定类方法名 */
    private String method;

    /** HTTP 请求方式（GET / POST / PUT / DELETE） */
    private String requestMethod;

    /** 操作人 */
    private String operName;

    /** 客户端 IP */
    private String operIp;

    /** 请求 URL */
    private String operUrl;

    /** 执行耗时（毫秒） */
    private Long costTime;

    /** 响应状态码（200 成功 / 500 异常） */
    private Integer status;

    /** 操作时间戳 */
    private Long operTime;
}
