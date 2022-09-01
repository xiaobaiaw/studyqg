package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.math.BigDecimal;

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
 * @since 2022-08-31
 */
@Getter
@Setter
  @ApiModel(value = "Cart对象", description = "")
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品id")
      private Integer waresId;

      @ApiModelProperty("用户id")
      private Integer userId;

      @ApiModelProperty("商品数量")
      private Integer num;

      @ApiModelProperty("添加时间")
      private String time;

      @TableField(exist = false)
      private String waresName;
      @TableField(exist = false)
      private String waresImg;
      @TableField(exist = false)
      private BigDecimal price;
      @TableField(exist = false)
      private String username;
      @TableField(exist = false)
      private String nickname;
}
