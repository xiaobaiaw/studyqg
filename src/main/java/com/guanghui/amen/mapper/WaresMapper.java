package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Wares;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
public interface WaresMapper extends BaseMapper<Wares> {
    @Select("select * from wares where name like concat('%',#{name},'%')")
    Page<Wares> findPage(Page<Wares> objectPage,@Param("name") String name);
}
