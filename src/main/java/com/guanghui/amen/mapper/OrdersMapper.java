package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Orders;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-09-01
 */
public interface OrdersMapper extends BaseMapper<Orders> {
    @Update("update orders set state = #{state},payment_time = #{paymentTime},alipay_no = #{alipayNo} where no = #{no}")
    int updateState(@Param("no") String no, @Param("state") String state, @Param("paymentTime") String getPayment,
                    @Param("alipayNo") String alipayTradeNo);
    @Select("select * from orders where no = #{traceNo}")
    Orders getByNo(@Param("traceNo") String traceNo);
    @Update("update orders set state = #{state},return_time = #{returnTime} where no = #{traceNo}")
    int updatePayState(@Param("traceNo") String traceNo, @Param("state") String state, @Param("returnTime") String now);

    Page<Orders> findPage(Page<Orders> page, @Param("name") String name, @Param("role") String role, @Param("userId") Integer userId);

}
