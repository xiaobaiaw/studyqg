package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Cart;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
public interface CartMapper extends BaseMapper<Cart> {
    Page<Cart> findPage(Page<Cart> page,@Param("name") String name,@Param("userId") Integer userId,@Param("role") String role);
    @Select("select * from cart where user_id = #{userId} and wares_id = #{waresId}")
    Cart findOne(@Param("waresId") Integer waresId, @Param("userId") Integer userId);
    @Update("update cart set num = #{num} where id = #{id}")
    void updateNum(@Param("id") Integer id, @Param("num") Integer num);
}
