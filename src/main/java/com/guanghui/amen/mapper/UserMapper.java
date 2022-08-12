package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-06-23
 */
public interface UserMapper extends BaseMapper<User> {
    @Update("UPDATE sys_user SET password = #{newPassword} WHERE (username = #{username})")
    Boolean updatePassword(@Param("username") String username,@Param("newPassword") String newPassword);

    Page<User> findPage(Page<User> page, @Param("username") String username, @Param("email") String email, @Param("address") String address);
}
