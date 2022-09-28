package com.guanghui.amen.controller.dto;

import lombok.Data;

@Data
public class PaperDTO {

    private Integer type1;  // 选择题数量
    private Integer type2;  // 填空题
    private Integer type3;  // 问答题
    private Integer paperId;// 试卷id
    private Integer courseId;//课程id
}
