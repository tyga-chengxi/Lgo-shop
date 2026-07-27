package com.lgoshop.portal.controller;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.portal.domain.MemberReadHistory;
import com.lgoshop.portal.service.MemberReadHistoryService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员商品浏览记录管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "MemberReadHistoryController")
@Tag(name = "MemberReadHistoryController", description = "会员商品浏览记录管理")
@RequestMapping("/member/readHistory")
@LgoWebLog
public class MemberReadHistoryController {
    @Autowired
    private MemberReadHistoryService memberReadHistoryService;

    @ApiOperation("创建浏览记录")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody MemberReadHistory memberReadHistory) {
        int count = memberReadHistoryService.create(memberReadHistory);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("删除浏览记录")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<String> ids) {
        int count = memberReadHistoryService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("清空浏览记录")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult clear() {
        memberReadHistoryService.clear();
        return LgoResult.success(null);
    }

    @ApiOperation("分页获取浏览记录")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<MemberReadHistory>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        org.springframework.data.domain.Page<MemberReadHistory> springPage = memberReadHistoryService.list(pageNum, pageSize);
        CommonPage<MemberReadHistory> page = new CommonPage<>();
        page.setPageNum(springPage.getNumber() + 1);
        page.setPageSize(springPage.getSize());
        page.setTotalPage(springPage.getTotalPages());
        page.setTotal(springPage.getTotalElements());
        page.setList(springPage.getContent());
        return LgoResult.success(page);
    }
}
