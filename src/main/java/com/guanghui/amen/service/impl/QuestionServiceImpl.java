package com.guanghui.amen.service.impl;

import com.guanghui.amen.entity.Question;
import com.guanghui.amen.mapper.QuestionMapper;
import com.guanghui.amen.service.IQuestionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {

}
