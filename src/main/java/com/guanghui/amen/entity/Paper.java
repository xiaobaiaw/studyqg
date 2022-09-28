package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since 2022-09-02
 */
@Getter
@Setter
  @ApiModel(value = "Paper对象", description = "")
public class Paper implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("总分")
      private Integer score;

      @ApiModelProperty("时长")
      private Integer duration;

      @ApiModelProperty("课程id")
      private Integer courseId;


}
