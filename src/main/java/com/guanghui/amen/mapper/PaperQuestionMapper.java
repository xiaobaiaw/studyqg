package com.guanghui.amen.mapper;

import com.guanghui.amen.entity.PaperQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guanghui.amen.entity.Question;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
public interface PaperQuestionMapper extends BaseMapper<PaperQuestion> {
    @Delete("delete from paper_question where paper_id = #{paperId}")
    int removePaperId(@Param("paperId") Integer paperId);

    List<Question> selectQuestion(@Param("paperId") Integer paperId);
}
