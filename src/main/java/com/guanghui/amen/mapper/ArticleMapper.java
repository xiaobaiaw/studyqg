package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Article;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-08-17
 */
public interface ArticleMapper extends BaseMapper<Article> {
    @Select("select * from article where name like concat('%',#{name},'%') order by id DESC")
    Page<Article> likeName(Page<Article> page, @Param("name") String name);
}
