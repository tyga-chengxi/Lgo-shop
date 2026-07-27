package com.lgoshop.controller;


import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.OssCallbackResult;
import com.lgoshop.dto.OssPolicyResult;
import com.lgoshop.service.OssService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Oss对象存储管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "OssController")
@Tag(name = "OssController", description = "Oss对象存储管理")
@RequestMapping("/aliyun/oss")
@LgoWebLog
public class OssController {
    @Autowired
    private OssService ossService;

    @ApiOperation(value = "Oss上传签名生成")
    @RequestMapping(value = "/policy", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<OssPolicyResult> policy() {
        OssPolicyResult result = ossService.policy();
        return LgoResult.success(result);
    }

    @ApiOperation(value = "Oss上传成功回调")
    @RequestMapping(value = "callback", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult<OssCallbackResult> callback(HttpServletRequest request) {
        OssCallbackResult ossCallbackResult = ossService.callback(request);
        return LgoResult.success(ossCallbackResult);
    }

}
