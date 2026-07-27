package com.lgoshop.portal.controller;

import com.lgoshop.common.api.LgoResult;
import com.lgoshop.model.UmsMemberReceiveAddress;
import com.lgoshop.portal.service.UmsMemberReceiveAddressService;
import io.swagger.annotations.Api;
import com.lgoshop.common.annotation.LgoWebLog;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 会员收货地址管理Controller
 * Created by lgo-shop.
 */
@Controller
@Api(tags = "UmsMemberReceiveAddressController")
@Tag(name = "UmsMemberReceiveAddressController", description = "会员收货地址管理")
@RequestMapping("/member/address")
@LgoWebLog
public class UmsMemberReceiveAddressController {
    @Autowired
    private UmsMemberReceiveAddressService memberReceiveAddressService;

    @ApiOperation("添加收货地址")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult add(@RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.add(address);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("删除收货地址")
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult delete(@PathVariable Long id) {
        int count = memberReceiveAddressService.delete(id);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("修改收货地址")
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public LgoResult update(@PathVariable Long id, @RequestBody UmsMemberReceiveAddress address) {
        int count = memberReceiveAddressService.update(id, address);
        if (count > 0) {
            return LgoResult.success(count);
        }
        return LgoResult.failed();
    }

    @ApiOperation("获取所有收货地址")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<List<UmsMemberReceiveAddress>> list() {
        List<UmsMemberReceiveAddress> addressList = memberReceiveAddressService.list();
        return LgoResult.success(addressList);
    }

    @ApiOperation("获取收货地址详情")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public LgoResult<UmsMemberReceiveAddress> getItem(@PathVariable Long id) {
        UmsMemberReceiveAddress address = memberReceiveAddressService.getItem(id);
        return LgoResult.success(address);
    }
}
