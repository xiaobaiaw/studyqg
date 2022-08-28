package com.guanghui.amen.mapper;

import com.guanghui.amen.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-25
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    @Update("update orders set state = #{state},payment_time = #{paymentTime},alipay_no = #{alipayNo}")
    int updateState(@Param("no") String no, @Param("state") String state, @Param("paymentTime") String getPayment,
                     @Param("alipayNo") String alipayTradeNo);
}
