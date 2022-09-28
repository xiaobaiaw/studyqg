package com.guanghui.amen.service.impl;

import com.guanghui.amen.entity.PaperQuestion;
import com.guanghui.amen.entity.Question;
import com.guanghui.amen.mapper.PaperQuestionMapper;
import com.guanghui.amen.service.IPaperQuestionService;
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
 * @since 2022-09-02
 */
@Service
public class PaperQuestionServiceImpl extends ServiceImpl<PaperQuestionMapper, PaperQuestion> implements IPaperQuestionService {

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    @Override
    public List<Question> selectQuestion(Integer paperId) {
        return paperQuestionMapper.selectQuestion(paperId);
    }
}
