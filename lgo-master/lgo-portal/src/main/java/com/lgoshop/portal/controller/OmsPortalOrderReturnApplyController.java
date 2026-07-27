package com.lgoshop.portal.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.portal.domain.OmsOrderReturnApplyParam;
import com.lgoshop.portal.service.OmsPortalOrderReturnApplyService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 退货申请管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "OmsPortalOrderReturnApplyController")
@Tag(name = "OmsPortalOrderReturnApplyController",description = "退货申请管理")
@RequestMapping("/returnApply")
@LgoWebLog
public class OmsPortalOrderReturnApplyController {
    @Autowired
    private OmsPortalOrderReturnApplyService returnApplyService;

    @ApiOperation("申请退货")
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult create(@RequestBody OmsOrderReturnApplyParam returnApply) {
        int count = returnApplyService.create(returnApply);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }
}
