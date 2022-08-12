package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Course;
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
 * @since 2022-08-08
 */
public interface CourseMapper extends BaseMapper<Course> {

    Page<Course> findPage(Page<Course> page,@Param("name") String name);
    void deleteStudentCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);
    void setStudentCourse(@Param("courseId") Integer courseId, @Param("studentId") Integer studentId);
    @Select("select course.*,student_course.student_id from student_course left join course " +
            "on student_course.course_id = course.id where student_course.student_id = #{studentId}")
    List<Course> stuCourses(@Param("studentId") Integer studentId);
}
