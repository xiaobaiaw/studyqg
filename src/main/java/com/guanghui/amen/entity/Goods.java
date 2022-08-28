package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

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
 * @since 2022-08-21
 */
@Getter
@Setter
  @ApiModel(value = "Goods对象", description = "")
public class Goods implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("所属人")
      private String user;

      @ApiModelProperty("物品名称")
      private String name;

      @ApiModelProperty("报修时间")
      private String time;

      @ApiModelProperty("物品图片")
      private String img;

      @ApiModelProperty("保修状态")
      private String state;

      @TableField(exist = false)
      private List<Goods> records;


}
