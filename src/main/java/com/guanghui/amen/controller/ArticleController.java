package com.guanghui.amen.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.guanghui.amen.common.Constants;
import com.guanghui.amen.mapper.ArticleMapper;
import com.guanghui.amen.utils.TokenUtils;
import org.springframework.web.bind.annotation.*;
import com.guanghui.amen.common.Result;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IArticleService;
import com.guanghui.amen.entity.Article;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-08-17
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    
    @Resource
    private IArticleService articleService;

    @PostMapping
    public Result save(@RequestBody Article article){
        if (article.getId() == null) {
            article.setTime(DateUtil.now());
            article.setName(TokenUtils.getCurrentUser().getNickname());
        }

        articleService.saveOrUpdate(article);
    return Result.success();
    }

    @GetMapping
    public Result hhd(){
    return Result.success(articleService.list());
    }

    @DeleteMapping("{id}")
    public Result delete(@PathVariable Integer id){
    return Result.success(articleService.removeById(id));
    }

    @PostMapping("del/batch")
    public Result deleteBatch(@PathVariable Integer ids){
    return Result.success(articleService.removeById(ids));
    }

    @GetMapping("{id}")
    public Result findOne(@PathVariable Integer id){
    return Result.success(articleService.getById(id));
    }


    @GetMapping("/page")//MybatisPlus方式实现分页查询和模糊查询
    public Result findPage(@RequestParam String name,
                            @RequestParam Integer pageSize,
                           @RequestParam Integer pageNum
    ){
        return Result.success(articleService.likePage(new Page<>(pageNum,pageSize),name));
    }
}

