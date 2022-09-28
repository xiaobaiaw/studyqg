package com.guanghui.amen.service;

import com.guanghui.amen.entity.PaperQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guanghui.amen.entity.Question;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
public interface IPaperQuestionService extends IService<PaperQuestion> {

    List<Question> selectQuestion(Integer paperId);
}
