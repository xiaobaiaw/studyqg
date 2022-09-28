package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IExamService;
import com.guanghui.amen.entity.Exam;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/exam")
    public class ExamController {
    
    @Resource
    private IExamService examService;
    @PostMapping
    public Result save(@RequestBody Exam exam){
        examService.saveOrUpdate(exam);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(examService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(examService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(examService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(examService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        QueryWrapper<Exam> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(examService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}

