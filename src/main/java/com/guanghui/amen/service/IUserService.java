package com.guanghui.amen.service;

import com.guanghui.amen.controller.dto.UserDTO;
import com.guanghui.amen.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-06-23
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);
}
