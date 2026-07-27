package com.lgoshop.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lgoshop.common.api.CommonPage;
import com.lgoshop.common.api.LgoResult;
import com.lgoshop.dto.OmsOrderReturnApplyResult;
import com.lgoshop.dto.OmsReturnApplyQueryParam;
import com.lgoshop.dto.OmsUpdateStatusParam;
import com.lgoshop.model.OmsOrderReturnApply;
import com.lgoshop.service.OmsOrderReturnApplyService;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 订单退货申请管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "OmsOrderReturnApplyController")
@Tag(name = "OmsOrderReturnApplyController", description = "订单退货申请管理")
@LgoWebLog
@RequestMapping("/returnApply")
public class OmsOrderReturnApplyController {
    @Autowired
    private OmsOrderReturnApplyService returnApplyService;

    @ApiOperation("分页查询退货申请")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<CommonPage<OmsOrderReturnApply>> list(OmsReturnApplyQueryParam queryParam,
                                                              @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                                                              @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        IPage<OmsOrderReturnApply> returnApplyList = returnApplyService.list(queryParam, pageSize, pageNum);
        return LgoResult.success(CommonPage.restPage(returnApplyList));
    }

    @ApiOperation("批量删除退货申请")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@RequestParam("ids") List<Long> ids) {
        int count = returnApplyService.delete(ids);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取退货申请详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult getItem(@PathVariable Long id) {
        OmsOrderReturnApplyResult result = returnApplyService.getItem(id);
        return LgoResult.success(result);
    }

    @ApiOperation("修改退货申请状态")
    @RequestMapping(value = "/update/status/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult updateStatus(@PathVariable Long id, @RequestBody OmsUpdateStatusParam statusParam) {
        int count = returnApplyService.updateStatus(id, statusParam);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

}
