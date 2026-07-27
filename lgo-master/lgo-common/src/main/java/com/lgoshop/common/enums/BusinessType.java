package com.lgoshop.common.enums;

/**
 * LGO-Shop 自研业务操作类型枚举
 * <p>用于 {@code @LgoOperateLog} 注解的 {@code businessType} 属性</p>
 *
 * @author lgo-shop
 */
public enum BusinessType {

    /**
     * 新增
     */
    INSERT,

    /**
     * 修改
     */
    UPDATE,

    /**
     * 删除
     */
    DELETE,

    /**
     * 登录 / 登出
     */
    LOGIN,

    /**
     * 导入
     */
    IMPORT,
}
