package com.guanghui.amen.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-08-25
 */
@Getter
@Setter
  @ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("id")
      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("订单编号")
      private String no;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("订单时间")
      private String time;

      @ApiModelProperty("支付状态")
      private String state;

      @TableField(exist = false)
      private Integer userId;

      @ApiModelProperty("订单总价")
      private BigDecimal total;
      //支付宝订单流水号
      @ApiModelProperty("支付宝交易号")
      private String alipayNo;

      @ApiModelProperty("支付时间")
      private String paymentTime;

}
