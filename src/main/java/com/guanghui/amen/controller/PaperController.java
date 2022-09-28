package com.guanghui.amen.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.controller.dto.HandPaperDTO;
import com.guanghui.amen.controller.dto.PaperDTO;
import com.guanghui.amen.entity.PaperQuestion;
import com.guanghui.amen.entity.Question;
import com.guanghui.amen.exception.ServiiceException;
import com.guanghui.amen.mapper.PaperMapper;
import com.guanghui.amen.mapper.PaperQuestionMapper;
import com.guanghui.amen.mapper.QuestionMapper;
import com.guanghui.amen.service.IPaperQuestionService;
import com.guanghui.amen.service.IQuestionService;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IPaperService;
import com.guanghui.amen.entity.Paper;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/paper")
    public class PaperController {
    
    @Resource
    private IPaperService paperService;

    @Resource
    private IPaperQuestionService paperQuestionService;

    @Resource
    private PaperQuestionMapper paperQuestionMapper;

    @Resource
    private PaperMapper paperMapper;

    @Resource
    private QuestionMapper questionMapper;

    @PostMapping
    public Result save(@RequestBody Paper paper){
        paperService.saveOrUpdate(paper);
        return Result.success();
    }

    @PostMapping("/takePaper")
    public Result takePaper(@RequestBody PaperDTO paperDTO){
        //删除老的数据
        Integer paperId = paperDTO.getPaperId();
        paperQuestionMapper.removePaperId(paperId);
        //  根据课程id查出所有该课程的题目，然后再根据type去分
        List<Question> questionList = questionMapper.listType(paperDTO.getCourseId());
        List<Question> typeList1 = questionList.stream().filter(question -> question.getType() == 1).collect(Collectors.toList());//选择
        List<Question> typeList2 = questionList.stream().filter(question -> question.getType() == 2).collect(Collectors.toList());//填空
        List<Question> typeList3 = questionList.stream().filter(question -> question.getType() == 3).collect(Collectors.toList());//问答
        if (typeList1.size() < paperDTO.getType1()) {
            throw new ServiiceException("-1", "选择题数量不足");
        }
        if (typeList2.size() < paperDTO.getType2()) {
            throw new ServiiceException("-1", "判断题数量不足");
        }
        if (typeList3.size() < paperDTO.getType3()) {
            throw new ServiiceException("-1", "问答题数量不足");
        }
        //开始随机组卷
        //选择题
        List<PaperQuestion> paperQuestion = getPaperQuestion(typeList1.size(), paperDTO.getType1(), typeList1, paperId);

        //判断题
        paperQuestion.addAll(getPaperQuestion(typeList2.size(), paperDTO.getType2(), typeList2, paperId));

        //问答题
        paperQuestion.addAll(getPaperQuestion(typeList3.size(), paperDTO.getType3(), typeList3, paperId));

        paperQuestionService.saveBatch(paperQuestion);// 批量插入关联关系表
        return Result.success();
    }


    @PostMapping("/handPaper")
    public Result handPaper(@RequestBody HandPaperDTO handPaperDTO) {

        //删除老的数据
        Integer paperId = handPaperDTO.getPaperId();
        paperQuestionMapper.removePaperId(paperId);
        if (CollUtil.isEmpty(handPaperDTO.getHandleQuestionIds())) {
            throw new ServiiceException("-1", "题目数量不足");
        }

        List<Integer> handleQuestionIds = handPaperDTO.getHandleQuestionIds();
        List<PaperQuestion> list = new ArrayList<>();
        for (Integer handleQuestionId : handleQuestionIds) {
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setPaperId(handPaperDTO.getPaperId());
            paperQuestion.setQuestionId(handleQuestionId);
            list.add(paperQuestion);
        }
        paperQuestionService.saveBatch(list);
        return Result.success();
    }
    //封装一个获取试卷和题目关系list的方法
    private List<PaperQuestion> getPaperQuestion(int questionSize, int paperQuestionSize, List<Question> source, Integer paperId) {
        List<Integer> typeRandomList = getEleList(questionSize, paperQuestionSize);
        List<PaperQuestion> list = new ArrayList<>();
        for (Integer index : typeRandomList) {
            Question question = source.get(index);
            PaperQuestion paperQuestion = new PaperQuestion();
            paperQuestion.setPaperId(paperId);
            paperQuestion.setQuestionId(question.getId());
            list.add(paperQuestion);
        }
        return list;
    }
    //封装一个获取随机数的方法
    private List<Integer> getEleList(int sourceSize,int resultSize) {
        List<Integer> list = CollUtil.newArrayList();
        for (int j = 0; j < sourceSize; j++) {
            list.add(j);
        }
        return RandomUtil.randomEleList(list, resultSize);
    }

    @GetMapping("/view/{paperId}")
    public Result view(@PathVariable Integer paperId){
        List<Question> list = paperQuestionService.selectQuestion(paperId);
        return Result.success(list);
    }

    @GetMapping
    public Result hhd(){
        return Result.success(paperService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
        return Result.success(paperService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
        return Result.success(paperService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
        return Result.success(paperService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam(defaultValue = "") String name,
            @RequestParam Integer pageNum,
    @RequestParam Integer pageSize
    ){
        return Result.success(paperMapper.findPage(new Page<>(pageNum,pageSize),name));
    }
}

