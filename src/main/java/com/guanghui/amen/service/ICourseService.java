package com.guanghui.amen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-08
 */
public interface ICourseService extends IService<Course> {

    Page<Course> findPage(Page<Course> objectPage, String name);

    void setStudentCourse(Integer courseId, Integer studentId);

    List<Course> stuCourses(Integer studentId);
}
