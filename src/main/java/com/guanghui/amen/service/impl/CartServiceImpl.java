package com.guanghui.amen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Cart;
import com.guanghui.amen.mapper.CartMapper;
import com.guanghui.amen.service.ICartService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
@Service
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {

    @Resource
    private CartMapper cartMapper;

    @Override
    public Page<Cart> findPage(Page<Cart> page, String name, Integer userId, String role) {
        return cartMapper.findPage(page, name, userId, role);
    }

    @Override
    public Cart findOne(Integer waresId, Integer userId) {
        return cartMapper.findOne(waresId, userId);
    }

}
