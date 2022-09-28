package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Question;
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
 * @since 2022-09-02
 */
public interface QuestionMapper extends BaseMapper<Question> {
    @Select("select * from question q left join course c on q.course_id = c.id where c.name like concat('%',#{name},'%') and course_id = #{courseId} and type = #{type}")
    Page<Question> findAllPage(Page<Question> page,@Param("name") String name,@Param("courseId") Integer courseId,@Param("type") Integer type);
    @Select("select * from question where course_id = #{courseId}")
    List<Question> listType(@Param("courseId") Integer courseId);
    @Select("select * from question q left join course c on q.course_id = c.id where c.name like concat('%',#{name},'%') and course_id = #{courseId}")
    Page<Question> findcourseIdPage(Page<Question> page,@Param("name") String name,@Param("courseId") Integer courseId);
    @Select("select * from question q left join course c on q.course_id = c.id where c.name like concat('%',#{name},'%') and type = #{type}")
    Page<Question> findTypePage(Page<Question> page,@Param("name") String name,@Param("type") Integer type);
    @Select("select * from question q left join course c on q.course_id = c.id where c.name like concat('%',#{name},'%')")
    Page<Question> findPage(Page<Question> page,@Param("name") String name);
}
