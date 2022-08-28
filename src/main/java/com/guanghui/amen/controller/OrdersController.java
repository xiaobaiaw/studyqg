package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.entity.Commodities;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.exception.ServiiceException;
import com.guanghui.amen.service.ICommoditiesService;
import com.guanghui.amen.utils.TokenUtils;
import org.apache.commons.lang.enums.Enum;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IOrdersService;
import com.guanghui.amen.entity.Orders;

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
@RequestMapping("/orders")
    public class OrdersController {
    
    @Resource
    private IOrdersService ordersService;

    @Resource
    private ICommoditiesService commoditiesService;

    @PostMapping
    public Result save(@RequestBody Orders orders){
        ordersService.saveOrUpdate(orders);
        return Result.success();
    }

    @PostMapping("/{commoditiesId}/{num}")
    public Result createOrder(@PathVariable Integer commoditiesId,@PathVariable Integer num){
        Commodities commodities = commoditiesService.getById(commoditiesId);
        if (commodities == null) {
            throw new ServiiceException("-1","未找到该商品");
        }
        User currentUser = TokenUtils.getCurrentUser();
        Integer userId = currentUser.getId();

        Orders orders = new Orders();
        orders.setName(commodities.getName());
        orders.setNo(DateUtil.format(new Date(),"yyyyMMdd") + System.currentTimeMillis());
        orders.setTime(DateUtil.now());
        orders.setUserId(userId);
        orders.setTotal(commodities.getPrice().multiply(BigDecimal.valueOf(num)));
        ordersService.saveOrUpdate(orders);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(ordersService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(ordersService.removeById(id));
    }

    @DeleteMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(ordersService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(ordersService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        QueryWrapper<Orders> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("id");
        return Result.success(ordersService.page(new Page<>(pageNum,pageSize),queryWrapper));
    }
}

