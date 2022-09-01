package com.guanghui.amen.service.impl;

import com.guanghui.amen.entity.Orders;
import com.guanghui.amen.mapper.OrdersMapper;
import com.guanghui.amen.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-09-01
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
