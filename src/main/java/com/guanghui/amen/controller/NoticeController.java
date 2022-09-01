package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.INoticeService;
import com.guanghui.amen.entity.Notice;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-30
 */
@RestController
@RequestMapping("/notice")
    public class NoticeController {
    
    @Resource
    private INoticeService noticeService;
    @PostMapping
    public Result save(@RequestBody Notice notice){
        if (notice.getId() == null) {
            notice.setUser(TokenUtils.getCurrentUser().getNickname());
            notice.setTime(DateUtil.now());
        }
        noticeService.saveOrUpdate(notice);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(noticeService.list());
    }

    @GetMapping("/type/{type}")
    public Result findType(@PathVariable Integer type){
        return Result.success(noticeService.listType(type));
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(noticeService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(noticeService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(noticeService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        QueryWrapper<Notice> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(noticeService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}

