package com.lgoshop.common.api;

import lombok.Data;

/**
 * 统一数据响应，返回封装结果
 * LGO-Shop 自研统一返回体，基于若依风格 + timestamp
 *
 * @描述: 数据响应，统一返回结果
 * @作者: 太馨元
 * @创建时间: 2026/7/14 14:39
 */
@Data
public class LgoResult<T> {

    private int code;

    private String msg;

    private T data;

    private long timestamp;

    private LgoResult() {
        this.timestamp = System.currentTimeMillis();
    }

    private LgoResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    // ========== 成功响应 ==========

    public static <T> LgoResult<T> success() {
        return new LgoResult<>(200, "操作成功", null);
    }

    public static <T> LgoResult<T> success(T data) {
        return new LgoResult<>(200, "操作成功", data);
    }

    public static <T> LgoResult<T> success(String msg, T data) {
        return new LgoResult<>(200, msg, data);
    }

    // ========== 失败响应 ==========

    public static <T> LgoResult<T> failed() {
        return new LgoResult<>(500, "操作失败", null);
    }

    public static <T> LgoResult<T> failed(String msg) {
        return new LgoResult<>(500, msg, null);
    }

    // ========== 参数校验失败 =========

    public static <T> LgoResult<T> validateFailed(String msg) {
        return new LgoResult<>(400, msg, null);
    }

    // ========== 认证/授权失败 ==========

    public static <T> LgoResult<T> unauthorized(T data) {
        return new LgoResult<>(401, "未登录或token已过期", data);
    }

    public static <T> LgoResult<T> forbidden(T data) {
        return new LgoResult<>(403, "没有相关权限", data);
    }

    // ========== 错误/警告 ==========

    public static <T> LgoResult<T> error(String msg) {
        return new LgoResult<>(500, msg, null);
    }

    public static <T> LgoResult<T> error(int code, String msg) {
        return new LgoResult<>(code, msg, null);
    }

    public static <T> LgoResult<T> warn(String msg) {
        return new LgoResult<>(400, msg, null);
    }

    public static <T> LgoResult<T> warn(T data, int code, String msg) {
        return new LgoResult<>(code, msg, data);
    }

    // ========== 兼容 CommonResult.getMessage() 调用 ==========

    public String getMessage() {
        return this.msg;
    }
}
