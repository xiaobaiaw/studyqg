package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.common.RoleEnum;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.utils.TokenUtils;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.Arrays;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import javax.rmi.CORBA.Util;

import com.guanghui.amen.service.IGoodsService;
import com.guanghui.amen.entity.Goods;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-21
 */
@RestController
@RequestMapping("/goods")
    public class GoodsController {
    
    @Resource
    private IGoodsService goodsService;
    @PostMapping
    public Result save(@RequestBody Goods goods){
        if (goods.getId() == null) {
            // 新增
            goods.setTime(DateUtil.today());
            goods.setUser(TokenUtils.getCurrentUser().getUsername());
        }
        goodsService.saveOrUpdate(goods);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(goodsService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(goodsService.removeById(id));
    }

    @DeleteMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(goodsService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(goodsService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
    @RequestParam Integer pageSize,
                           Goods goods
    ){
        User currentUser = TokenUtils.getCurrentUser();
        String user = currentUser.getUsername();
        
        if (!"".equals(name) || RoleEnum.ROLE_TEACHER.toString().equals(currentUser.getRole()) || RoleEnum.ROLE_STUDENT.toString().equals(currentUser.getRole())) {
            List<Goods> goodsPage = goodsService.findPage(new Page<>(pageNum, pageSize), name, user);
            goods.setRecords(goodsPage);
            return Result.success(goods);
        }else{
            List<Goods> goodsList = goodsService.selectAllPage(new Page<>(pageNum, pageSize));
            goods.setRecords(goodsList);
            return Result.success(goods);
        }
//        QueryWrapper<Goods> queryWrapper = new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
//        if (!"".equals(name)) {
//            queryWrapper.like("name", name);
//        }
//        if (RoleEnum.ROLE_USER.toString().equals(currentUser.getRole())) {  // 角色是普通用户
//            queryWrapper.eq("user", currentUser.getUsername());
//        }
//        return Result.success(goodsService.page(new Page<>(pageNum, pageSize), queryWrapper));

    }


}

