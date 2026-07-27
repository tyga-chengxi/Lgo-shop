package com.lgoshop.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import jakarta.validation.constraints.NotEmpty;

/**
 * 修改用户名密码参数
 * Created by lgo-shop.
 */
@Getter
@Setter
public class UpdateAdminPasswordParam {
    @NotEmpty
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @NotEmpty
    @ApiModelProperty(value = "旧密码", required = true)
    private String oldPassword;
    @NotEmpty
    @ApiModelProperty(value = "新密码", required = true)
    private String newPassword;
}
