package com.guanghui.amen.controller.dto;

import lombok.Data;

/**
 * 接受前端登录请求的实例
 */
@Data
public class UserDTO {
    private String username;
    private String password;
    private String tpassword;
    private String nickname;
    private String avatarUrl;
    private String token;
}
