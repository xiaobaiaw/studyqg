package com.guanghui.amen.mapper;

import com.guanghui.amen.entity.Notice;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-30
 */
public interface NoticeMapper extends BaseMapper<Notice> {
    @Select("select * from notice where type = #{type}")
    List<Notice> listType(@Param("type") Integer type);
}
