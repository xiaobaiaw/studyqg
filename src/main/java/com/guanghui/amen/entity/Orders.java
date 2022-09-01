package com.guanghui.amen.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

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
 * @since 2022-09-01
 */
@Getter
@Setter
  @ApiModel(value = "Orders对象", description = "")
public class Orders implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("编号")
      private String no;

      @ApiModelProperty("总金额")
      private BigDecimal totalPrice;

      @ApiModelProperty("状态")
      private String state;

      @ApiModelProperty("下单时间")
      private String time;

      @ApiModelProperty("付款时间")
      private String payTime;

      @ApiModelProperty("用户id")
      private Integer userId;

      @TableField(exist = false)
      private String username;

      @TableField(exist = false)
      private String nickname;

      @TableField(exist = false)
      private List<Cart> carts;
}
