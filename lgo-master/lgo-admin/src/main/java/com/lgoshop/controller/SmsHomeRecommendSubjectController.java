package com.lgoshop.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;

import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.SmsHomeRecommendSubject;
import com.lgoshop.service.SmsHomeRecommendSubjectService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 首页专题推荐管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "SmsHomeRecommendSubjectController")
@Tag(name = "SmsHomeRecommendSubjectController", description = "首页专题推荐管理")
@RequestMapping("/home/recommendSubject")
@LgoWebLog
public class SmsHomeRecommendSubjectController {
    @Autowired
    private SmsHomeRecommendSubjectService recommendSubjectService;

    @ApiOperation("批量添加首页专题推荐")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody List<SmsHomeRecommendSubject> homeRecommendSubjectList) {
        int count = recommendSubjectService.create(homeRecommendSubjectList);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改专题推荐排序")
    @RequestMapping(value = "/update/sort/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateSort(@PathVariable Long id, Integer sort) {
        int count = recommendSubjectService.updateSort(id, sort);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量删除专题推荐")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = recommendSubjectService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("批量修改专题推荐状态")
    @RequestMapping(value = "/update/recommendStatus", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateRecommendStatus(@RequestParam("ids") List<Long> ids, @RequestParam Integer recommendStatus) {
        int count = recommendSubjectService.updateRecommendStatus(ids, recommendStatus);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("分页查询专题推荐")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<SmsHomeRecommendSubject>> list(@RequestParam(value = "subjectName", required = false) String subjectName,
                                                                  @RequestParam(value = "recommendStatus", required = false) Integer recommendStatus,
                                                                  @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
IPage<SmsHomeRecommendSubject> homeRecommendSubjectList = recommendSubjectService.list(subjectName, recommendStatus, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(homeRecommendSubjectList));
    }
}
