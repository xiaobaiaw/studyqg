package com.guanghui.amen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Course;
import com.guanghui.amen.mapper.CourseMapper;
import com.guanghui.amen.service.ICourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-08
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Resource
    private CourseMapper courseMapper;

    @Override
    public Page<Course> findPage(Page<Course> page, String name) {
        return courseMapper.findPage(page,name);
    }
    @Transactional
    @Override
    public void setStudentCourse(Integer courseId, Integer studentId) {
        courseMapper.deleteStudentCourse(courseId,studentId);
        courseMapper.setStudentCourse(courseId,studentId);
    }

    @Override
    public List<Course> stuCourses(Integer studentId) {
        return courseMapper.stuCourses(studentId);
    }
}
