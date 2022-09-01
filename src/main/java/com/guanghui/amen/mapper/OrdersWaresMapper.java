package com.guanghui.amen.mapper;

import com.guanghui.amen.entity.Orders;
import com.guanghui.amen.entity.OrdersWares;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-09-01
 */
public interface OrdersWaresMapper extends BaseMapper<OrdersWares> {

    @Select("select * from orders_wares where order_id = #{id}")
    List<OrdersWares> listById(@Param("id") Integer id);
}
