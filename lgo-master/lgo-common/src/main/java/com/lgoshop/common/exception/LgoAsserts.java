package com.lgoshop.common.exception;/**
 * @description TODO
 * @author 太馨元
 * @date 2026-07-14 15:08
 */

/**
 * @描述: 简化参数校验
 * @作者: 太馨元
 * @创建时间: 2026/7/14 15:08
 */
public class LgoAsserts {

    public static void fail(String message) {
        throw new LgoBusinessException(message);
    }

    public static void fail(int code, String message) {
        throw new LgoBusinessException(code, message);
    }

    public static void notNull(Object obj, String message) {
        if (obj == null) {
            throw new LgoBusinessException(message);
        }
    }
}