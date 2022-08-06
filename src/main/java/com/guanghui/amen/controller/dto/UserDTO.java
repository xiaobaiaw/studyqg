package com.guanghui.amen.controller.dto;

import com.guanghui.amen.entity.Menu;
import lombok.Data;

import java.util.List;

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
    private String role;
    private List<Menu> menus;
}
