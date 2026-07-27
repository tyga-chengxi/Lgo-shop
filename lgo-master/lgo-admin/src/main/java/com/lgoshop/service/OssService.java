package com.lgoshop.service;

import com.lgoshop.dto.OssCallbackResult;
import com.lgoshop.dto.OssPolicyResult;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Service
 * Created by lgo-shop.
 */
public interface OssService {
    /**
     * Oss上传策略生成
     */
    OssPolicyResult policy();
    /**
     * Oss上传成功回调
     */
    OssCallbackResult callback(HttpServletRequest request);
}
