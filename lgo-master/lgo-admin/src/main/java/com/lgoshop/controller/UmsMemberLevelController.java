package com.lgoshop.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.UmsMemberLevel;
import com.lgoshop.service.UmsMemberLevelService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 会员等级管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsMemberLevelController")
@Tag(name = "UmsMemberLevelController", description = "会员等级管理")
@RequestMapping("/memberLevel")
@LgoWebLog
public class UmsMemberLevelController {
    @Autowired
    private UmsMemberLevelService memberLevelService;

    @ApiOperation("查询所有会员等级")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsMemberLevel>> list(@RequestParam("defaultStatus") Integer defaultStatus) {
        List<UmsMemberLevel> memberLevelList = memberLevelService.list(defaultStatus);
        return LgoResult.success(memberLevelList);
    }
}
