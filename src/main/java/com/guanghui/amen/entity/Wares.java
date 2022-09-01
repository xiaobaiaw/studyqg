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
  @ApiModel(value = "Wares对象", description = "")
public class Wares implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("商品名称")
      private String name;

      @ApiModelProperty("价格")
      private BigDecimal price;

      @ApiModelProperty("商品描述")
      private String description;

      @ApiModelProperty("单位")
      private String unit;

      @ApiModelProperty("库存")
      private Integer store;

      @ApiModelProperty("图片")
      private String img;

      @ApiModelProperty("上架时间")
      private String time;

      @TableField(exist = false)
      private Integer num;


}
