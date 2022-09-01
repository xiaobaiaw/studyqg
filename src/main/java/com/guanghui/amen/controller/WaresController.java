package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.config.interceptor.AuthAccess;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IWaresService;
import com.guanghui.amen.entity.Wares;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
@RestController
@RequestMapping("/wares")
    public class WaresController {
    
    @Resource
    private IWaresService waresService;
    @PostMapping
    public Result save(@RequestBody Wares wares){
        if (wares.getId() == null) {
            wares.setTime(DateUtil.now());
        }
        waresService.saveOrUpdate(wares);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(waresService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(waresService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(waresService.removeById(ids));
    }

    @AuthAccess
    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(waresService.getById(id));
    }

    @AuthAccess
    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        Page<Wares> page = waresService.findPage(new Page<>(pageNum, pageSize), name);
        return Result.success(page);
    }
}

