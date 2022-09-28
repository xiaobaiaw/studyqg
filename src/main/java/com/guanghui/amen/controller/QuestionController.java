package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.mapper.QuestionMapper;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IQuestionService;
import com.guanghui.amen.entity.Question;

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
@RequestMapping("/question")
    public class QuestionController {
    
    @Resource
    private IQuestionService questionService;

    @Resource
    private QuestionMapper questionMapper;

    @PostMapping
    public Result save(@RequestBody Question question){
        if (question.getId() == null) {
            question.setUserId(TokenUtils.getCurrentUser().getId());
            question.setTime(DateUtil.now());
        }
        questionService.saveOrUpdate(question);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(questionService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(questionService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(questionService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(questionService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam(required = false) Integer courseId,
                           @RequestParam(required = false) Integer type,
                           @RequestParam Integer pageNum,
                           @RequestParam Integer pageSize
    ){
        Page<Question> page;
        if (courseId != null) {
            page = questionMapper.findcourseIdPage(new Page<>(pageNum, pageSize), name, courseId);
        } else if (type != null) {
            page = questionMapper.findTypePage(new Page<>(pageNum, pageSize), name, type);
        } else if (courseId != null && type != null){
            page = questionMapper.findAllPage(new Page<>(pageNum, pageSize), name, courseId, type);
        } else {
            page = questionMapper.findPage(new Page<>(pageNum, pageSize), name);
        }
//        QueryWrapper<Question> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        if (!"".equals(name)) {
//            queryWrapper.like("name",name);
//        }
//        if (courseId != null) {
//            queryWrapper.eq("course_id",courseId);
//        }
//        if (type != null) {
//            queryWrapper.eq("type",type);
//        }questionService.page(new Page<>(pageNum,pageSize),queryWrapper)
        return Result.success(page);
    }
}

