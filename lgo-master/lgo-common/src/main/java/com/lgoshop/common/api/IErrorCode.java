package com.lgoshop.common.api;

/**
 * API返回码接口
 * Created by lgo-shop.
 */
public interface IErrorCode {
    /**
     * 返回码
     */
    long getCode();

    /**
     * 返回信息
     */
    String getMessage();
}
