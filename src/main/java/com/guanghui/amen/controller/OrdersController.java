package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.entity.*;
import com.guanghui.amen.mapper.OrdersMapper;
import com.guanghui.amen.mapper.OrdersWaresMapper;
import com.guanghui.amen.service.ICartService;
import com.guanghui.amen.service.IOrdersWaresService;
import com.guanghui.amen.service.IWaresService;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;
import javax.xml.crypto.Data;

import com.guanghui.amen.service.IOrdersService;

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
@RequestMapping("/orders")
    public class OrdersController {
    
    @Resource
    private IOrdersService ordersService;

    @Resource
    private IOrdersWaresService ordersWaresService;

    @Resource
    private OrdersMapper ordersMapper;

    @Resource
    private OrdersWaresMapper ordersWaresMapper;

    @Resource
    private ICartService cartService;

    @Resource
    private IWaresService waresService;

    @PostMapping
    public Result save(@RequestBody Orders orders){
        if (orders.getId() == null) {
            Date date = new Date();
            orders.setNo(DateUtil.format(date,"yyMMdd") + System.currentTimeMillis());
            orders.setTime(DateUtil.now());
            orders.setUserId(TokenUtils.getCurrentUser().getId());
            ordersService.saveOrUpdate(orders);
            // 创建订单
            List<Cart> carts = orders.getCarts();
            for (Cart cart : carts) {
                OrdersWares ordersWares = new OrdersWares();
                ordersWares.setWaresId(cart.getWaresId());
                ordersWares.setNum(cart.getNum());
                ordersWares.setOrderId(orders.getId());
                ordersWaresService.save(ordersWares);

                // 删除购物车数据
                cartService.removeById(cart.getId());
            }
        } else {
            ordersService.updateById(orders);
        }

        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(ordersService.list());
    }

    @GetMapping("getWaresById/{id}")
    public Result getWaresById(@PathVariable Integer id){
        ArrayList<Wares> waresList = new ArrayList<>();
        List<OrdersWares> ordersWaresList = ordersWaresMapper.listById(id);
        for (OrdersWares ordersWares : ordersWaresList) {
            Integer waresId = ordersWares.getWaresId();
            Wares wares = waresService.getById(waresId);
            wares.setNum(ordersWares.getNum());
            waresList.add(wares);
        }
        return Result.success(waresList);
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(ordersService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(ordersService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(ordersService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        User currentUser = TokenUtils.getCurrentUser();
        return Result.success(ordersMapper.findPage(new Page<>(pageNum,pageSize), name, currentUser.getRole(), currentUser.getId()));
    }
}

