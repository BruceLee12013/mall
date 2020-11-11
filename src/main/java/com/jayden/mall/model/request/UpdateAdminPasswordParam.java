package com.jayden.mall.model.request;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * 修改用户名密码参数
 * Created by macro on 2019/10/9.
 */

public class UpdateAdminPasswordParam {
    @NotNull(message = "用户名不能为空")
    private String username;
    @NotNull(message = "旧密码不能为空")
    private String oldPassword;
    @NotEmpty
    @NotNull(message = "新密码不能为空")
    private String newPassword;
}
