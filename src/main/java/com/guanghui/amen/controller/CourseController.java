package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.service.IUserService;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.ICourseService;
import com.guanghui.amen.entity.Course;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-08
 */
@RestController
@RequestMapping("/course")
public class CourseController {
    
    @Resource
    private ICourseService courseService;

    @PostMapping
    public Result save(@RequestBody Course course){
        courseService.saveOrUpdate(course);
        return Result.success();
    }

    @PostMapping("studentCourse/{courseId}/{studentId}")
    public Result studentCourse(@PathVariable Integer courseId, @PathVariable Integer studentId){
        courseService.setStudentCourse(courseId,studentId);
        return Result.success();
    }

//    @PostMapping("stuCourses/{studentId}")
//    public Result stuCourses(@PathVariable Integer studentId){
//        return Result.success(courseService.stuCourses(studentId));
//    }

    @GetMapping
    public Result hhd(){
    return Result.success(courseService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
    return Result.success(courseService.removeById(id));
    }

    @DeleteMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
    return Result.success(courseService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
    return Result.success(courseService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(
            @RequestParam String name,
            @RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
//    QueryWrapper<Course> queryWrapper=new QueryWrapper<>();
//    queryWrapper.orderByDesc("id");
        Page<Course> page = courseService.findPage(new Page<>(pageNum, pageSize), name);
//        List<Course> records = page.getRecords();
//        for (Course record : records) {
//            User user = userService.getById(record.getTeacherId());
//            if (user != null) {
//                record.setTeacher(user.getNickname());
//            }
//        }
        return Result.success(page);
    }
}

