package com.lgoshop.portal.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.UmsMember;
import com.lgoshop.portal.service.UmsMemberService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoOperateLog;
import com.lgoshop.common.annotation.LgoWebLog;
import com.lgoshop.common.enums.BusinessType;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

/**
 * 会员管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsMemberController")
@Tag(name = "UmsMemberController", description = "会员登录注册管理")
@RequestMapping("/sso")
@LgoWebLog
public class UmsMemberController {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("会员注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @LgoOperateLog(title = "会员管理", businessType = BusinessType.INSERT)
    @ResponseBody
    public LgoResult register(@RequestParam String username,
                                 @RequestParam String password,
                                 @RequestParam String telephone,
                                 @RequestParam String authCode) {
        memberService.register(username, password, telephone, authCode);
        return LgoResult.success("注册成功", null);
    }

    @ApiOperation("会员登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @LgoOperateLog(title = "会员管理", businessType = BusinessType.LOGIN)
    @ResponseBody
    public LgoResult login(@RequestParam String username,
                              @RequestParam String password) {
        String token = memberService.login(username, password);
        if (token == null) {
            return LgoResult.validateFailed("用户名或密码错误");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return LgoResult.success(tokenMap);
    }

    @ApiOperation("获取会员信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult info(Principal principal) {
        if(principal==null){
            return LgoResult.unauthorized(null);
        }
        UmsMember member = memberService.getCurrentMember();
        return LgoResult.success(member);
    }

    @ApiOperation("获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult getAuthCode(@RequestParam String telephone) {
        String authCode = memberService.generateAuthCode(telephone);
        return LgoResult.success("获取验证码成功", authCode);
    }

    @ApiOperation("会员修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updatePassword(@RequestParam String telephone,
                                 @RequestParam String password,
                                 @RequestParam String authCode) {
        memberService.updatePassword(telephone,password,authCode);
        return LgoResult.success("密码修改成功", null);
    }


    @ApiOperation(value = "刷新token")
    @RequestMapping(value = "/refreshToken", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult refreshToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String refreshToken = memberService.refreshToken(token);
        if (refreshToken == null) {
            return LgoResult.failed("token已经过期！");
        }
        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", refreshToken);
        tokenMap.put("tokenHead", tokenHead);
        return LgoResult.success(tokenMap);
    }
}
