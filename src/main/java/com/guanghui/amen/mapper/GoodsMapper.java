package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-21
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    @Select("select * from goods where name like concat('%',#{name},'%' ) and user = #{user} order by id DESC")
    List<Goods> findPage(Page<Goods> page, @Param("name") String name,@Param("user") String user);

    @Select("select * from goods ORDER BY id DESC")
    List<Goods> selectAllPage(Page<Goods> page);
}
