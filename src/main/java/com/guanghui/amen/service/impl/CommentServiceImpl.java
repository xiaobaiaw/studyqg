package com.guanghui.amen.service.impl;

import com.guanghui.amen.entity.Comment;
import com.guanghui.amen.mapper.CommentMapper;
import com.guanghui.amen.service.ICommentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-18
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements ICommentService {

    @Resource
    private CommentMapper commentMapper;

    @Override
    public List<Comment> selectListId(Integer articleId) {
        return commentMapper.selectListId(articleId);
    }
}
