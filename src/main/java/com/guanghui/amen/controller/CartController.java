package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import com.guanghui.amen.entity.User;
import com.guanghui.amen.mapper.CartMapper;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.ICartService;
import com.guanghui.amen.entity.Cart;

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
@RequestMapping("/cart")
    public class CartController {
    
    @Resource
    private ICartService cartService;

    @Resource
    private CartMapper cartMapper;

    @PostMapping
    public Result save(@RequestBody Cart cart){
        //更新购物车里系统商品的数量
        Integer waresId = cart.getWaresId();
        Integer userId = TokenUtils.getCurrentUser().getId();
        Cart db = cartService.findOne(waresId, userId);
        if (db != null) {
            db.setNum((db.getNum()) + cart.getNum());
            cartService.updateById(db);
            return Result.success();
        }

        //新增或更新
        if (cart.getId() == null) {
            cart.setUserId(TokenUtils.getCurrentUser().getId());
            cart.setTime(DateUtil.now());
        }
        cartService.saveOrUpdate(cart);
        return Result.success();
    }

    @PostMapping("/num/{id}/{num}")
    public Result updateNum(@PathVariable Integer id,@PathVariable Integer num) {
        cartMapper.updateNum(id, num);
        return Result.success();
    }

    @GetMapping
    public Result hhd(){
        return Result.success(cartService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(cartService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(cartService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(cartService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
                           @RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        User currentUser = TokenUtils.getCurrentUser();
        Integer userId = currentUser.getId();
        String role = currentUser.getRole();
        Page<Cart> page = cartService.findPage(new Page<>(pageNum,pageSize),name,userId,role);
        return Result.success(page);
    }

}

