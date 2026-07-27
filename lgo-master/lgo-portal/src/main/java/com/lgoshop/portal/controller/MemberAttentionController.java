package com.lgoshop.portal.controller;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.portal.domain.MemberBrandAttention;
import com.lgoshop.portal.service.MemberAttentionService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * 会员品牌关注管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "MemberAttentionController")
@Tag(name = "MemberAttentionController",description = "会员关注品牌管理")
@RequestMapping("/member/attention")
@LgoWebLog
public class MemberAttentionController {
    @Autowired
    private MemberAttentionService memberAttentionService;
    @ApiOperation("添加品牌关注")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult add(@RequestBody MemberBrandAttention memberBrandAttention) {
        int count = memberAttentionService.add(memberBrandAttention);
        if(count>0){
            return LgoResult.success(count);
        }else{
            return LgoResult.failed();
        }
    }

    @ApiOperation("取消品牌关注")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(Long brandId) {
        int count = memberAttentionService.delete(brandId);
        if(count>0){
            return LgoResult.success(count);
        }else{
            return LgoResult.failed();
        }
    }

    @ApiOperation("分页查询当前用户品牌关注列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<MemberBrandAttention>> list(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                                                               @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        org.springframework.data.domain.Page<MemberBrandAttention> springPage = memberAttentionService.list(pageNum,pageSize);
        CommonPage<MemberBrandAttention> page = new CommonPage<>();
        page.setPageNum(springPage.getNumber() + 1);
        page.setPageSize(springPage.getSize());
        page.setTotalPage(springPage.getTotalPages());
        page.setTotal(springPage.getTotalElements());
        page.setList(springPage.getContent());
        return LgoResult.success(page);
    }

    @ApiOperation("根据品牌ID获取品牌关注详情")
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<MemberBrandAttention> detail(@RequestParam Long brandId) {
        MemberBrandAttention memberBrandAttention = memberAttentionService.detail(brandId);
        return LgoResult.success(memberBrandAttention);
    }

    @ApiOperation("清空当前用户品牌关注列表")
    @RequestMapping(value = "/clear", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult clear() {
        memberAttentionService.clear();
        return LgoResult.success(null);
    }
}
