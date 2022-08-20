package com.guanghui.amen.mapper;

import com.guanghui.amen.entity.Comment;
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
 * @since 2022-08-18
 */
public interface CommentMapper extends BaseMapper<Comment> {
    @Select("select c.*,u.nickname,u.avatar_url from t_comment c left join sys_user u on c.user_id = u.id where c.article_id = #{articleId} order by id desc")
    List<Comment> selectListId(@Param("articleId") Integer articleId);
}
