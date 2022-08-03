package com.guanghui.amen.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author 林同学
 * @since 2022-08-03
 */
@Data
@TableName("sys_dict")
public class Dict implements Serializable {

    private static final long serialVersionUID = 1L;

      @ApiModelProperty("名称")
      private String name;

      @ApiModelProperty("内容")
      private String value;

      @ApiModelProperty("类型")
      private String type;


}
