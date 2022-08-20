package com.guanghui.amen.service;

import com.guanghui.amen.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-18
 */
public interface ICommentService extends IService<Comment> {
    List<Comment> selectListId(Integer articleId);
}
