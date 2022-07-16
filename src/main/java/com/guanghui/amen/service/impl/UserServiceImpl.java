package com.guanghui.amen.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.common.Constants;
import com.guanghui.amen.controller.dto.UserDTO;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.exception.ServiiceException;
import com.guanghui.amen.mapper.UserMapper;
import com.guanghui.amen.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-06-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private static final Log log = Log.get();

    @Override
    public UserDTO login(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one != null) {
            BeanUtil.copyProperties(one,userDTO,true);
            //设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            return userDTO;
        }else {
            throw new ServiiceException(Constants.CODE_600,"用户名或密码错误");
        }
    }
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO,one,true);
            save(one);//把copy完之后的用户对象存储到数据库
        }else {
            throw new ServiiceException(Constants.CODE_600,"用户名已存在");
        }
        return one;
    }
    private User getUserInfo(UserDTO userDTO){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",userDTO.getUsername());
        queryWrapper.eq("password",userDTO.getPassword());
        User one;
        try{
            one = getOne(queryWrapper);//从数据库查询用户信息
        }catch (Exception e){
            log.error(e);
            throw new ServiiceException (Constants.CODE_500,"系统错误");
        }
        return one;
    }
}
