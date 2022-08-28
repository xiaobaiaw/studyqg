package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.ICommoditiesService;
import com.guanghui.amen.entity.Commodities;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-25
 */
@RestController
@RequestMapping("/commodities")
    public class CommoditiesController {
    
    @Resource
    private ICommoditiesService commoditiesService;
    @PostMapping
    public Result save(@RequestBody Commodities commodities){
        commoditiesService.saveOrUpdate(commodities);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(commoditiesService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(commoditiesService.removeById(id));
    }

    @DeleteMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(commoditiesService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(commoditiesService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        QueryWrapper<Commodities> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(commoditiesService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}

