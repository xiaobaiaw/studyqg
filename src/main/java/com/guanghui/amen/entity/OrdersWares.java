package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林同学
 * @since 2022-09-01
 */
@Getter
@Setter
  @TableName("orders_wares")
@ApiModel(value = "OrdersWares对象", description = "")
public class OrdersWares implements Serializable {

    private static final long serialVersionUID = 1L;
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("订单id")
      private Integer orderId;

      @ApiModelProperty("商品id")
      private Integer waresId;

      @ApiModelProperty("商品数量")
      private Integer num;


}
