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
  @ApiModel(value = "Exam对象", description = "")
public class Exam implements Serializable {

    private static final long serialVersionUID = 1L;

      @TableId(value = "id", type = IdType.AUTO)
      private Integer id;

      @ApiModelProperty("考试名称")
      private String name;

      @ApiModelProperty("考试时长")
      private String duration;

      @ApiModelProperty("教室")
      private String room;

      @ApiModelProperty("时间")
      private String time;

      @ApiModelProperty("监考老师")
      private String teacher;

      @ApiModelProperty("总分")
      private Integer score;

      @ApiModelProperty("考试状态")
      private String state;


}
