package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.common.Result;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IRoleService;
import com.guanghui.amen.entity.Role;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/role")
        public class RoleController {
    
@Resource
private IRoleService roleService;
@PostMapping
public Result save(@RequestBody Role role){
        return Result.success(roleService.saveOrUpdate(role));
        }

@GetMapping
public Result hhd(){
        return Result.success(roleService.list());
        }

@DeleteMapping("{id}")
public Result delete(@PathVariable Integer id){
        return Result.success(roleService.removeById(id));
        }

@DeleteMapping("del/batch")
public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(roleService.removeById(ids));
        }

@GetMapping("{id}")
public Result findOne(@PathVariable Integer id){
        return Result.success(roleService.getById(id));
        }


@GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
public Result findPage(@RequestParam Integer pageNum,
@RequestParam Integer pageSize
        ){
        QueryWrapper<Role> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(roleService.page(new Page<>(pageNum,pageSize),queryWrapper));
        }
        }

