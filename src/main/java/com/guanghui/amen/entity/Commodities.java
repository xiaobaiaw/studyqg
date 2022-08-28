package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
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
 * @since 2022-08-25
 */
@Getter
@Setter
  @ApiModel(value = "Commodities对象", description = "")
public class Commodities implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("单价")
      private BigDecimal price;

      @ApiModelProperty("库存")
      private Integer store;

      @ApiModelProperty("单位")
      private String unit;

      @ApiModelProperty("封面")
      private String img;


}
