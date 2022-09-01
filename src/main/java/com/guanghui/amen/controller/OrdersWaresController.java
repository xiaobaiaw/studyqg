package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IOrdersWaresService;
import com.guanghui.amen.entity.OrdersWares;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-09-01
 */
@RestController
@RequestMapping("/orders-wares")
    public class OrdersWaresController {
    
    @Resource
    private IOrdersWaresService ordersWaresService;
    @PostMapping
    public Result save(@RequestBody OrdersWares ordersWares){
        ordersWaresService.saveOrUpdate(ordersWares);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(ordersWaresService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(ordersWaresService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(ordersWaresService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(ordersWaresService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        QueryWrapper<OrdersWares> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(ordersWaresService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}

