package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Paper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
public interface PaperMapper extends BaseMapper<Paper> {
    @Select("select * from paper left join course on paper.course_id = course.id where paper.name like concat('%',#{name},'%')")
    Page<Paper> findPage(Page<Paper> page,@Param("name") String name);
}
