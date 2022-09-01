package com.guanghui.amen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Cart;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
public interface ICartService extends IService<Cart> {

    Page<Cart> findPage(Page<Cart> page, String name, Integer userId, String role);

    Cart findOne(Integer waresId, Integer userId);
}
