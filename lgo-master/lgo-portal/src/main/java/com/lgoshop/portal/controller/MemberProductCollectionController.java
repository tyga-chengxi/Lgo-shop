package com.lgoshop.portal.controller;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.portal.domain.MemberProductCollection;
import com.lgoshop.portal.service.MemberCollectionService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会员商品收藏管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "MemberCollectionController")
@Tag(name = "MemberCollectionController",description = "会员收藏管理")
@RequestMapping("/member/productCollection")
@LgoWebLog
public class MemberProductCollectionController {
    @Autowired
    private MemberCollectionService memberCollectionService;

    @ApiOperation("添加商品收藏")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult add(@RequestBody MemberProductCollection productCollection) {
        int count = memberCollectionService.add(productCollection);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("删除商品收藏")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(Long productId) {
        int count = memberCollectionService.delete(productId);
        if (count > 0) {
            return LgoResult.success(count);
        } else {
            return LgoResult.failed();
        }
    }

    @ApiOperation("显示当前用户商品收藏列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<MemberProductCollection>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        org.springframework.data.domain.Page<MemberProductCollection> springPage = memberCollectionService.list(pageNum,pageSize);
        CommonPage<MemberProductCollection> page = new CommonPage<>();
        page.setPageNum(springPage.getNumber() + 1);
        page.setPageSize(springPage.getSize());
        page.setTotalPage(springPage.getTotalPages());
        page.setTotal(springPage.getTotalElements());
        page.setList(springPage.getContent());
        return LgoResult.success(page);
    }

    @ApiOperation("显示商品收藏详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<MemberProductCollection> detail(@RequestParam Long productId) {
        MemberProductCollection memberProductCollection = memberCollectionService.detail(productId);
        return LgoResult.success(memberProductCollection);
    }

    @ApiOperation("清空当前用户商品收藏列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult clear() {
        memberCollectionService.clear();
        return LgoResult.success(null);
    }
}
