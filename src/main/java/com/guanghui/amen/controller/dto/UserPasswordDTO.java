package com.guanghui.amen.controller.dto;

import lombok.Data;

@Data
public class UserPasswordDTO {
    private String password;
    private String newPassword;
    private String username;
}
