package com.guanghui.amen.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.guanghui.amen.common.Constants;
import com.guanghui.amen.common.Result;
import com.guanghui.amen.entity.Dict;
import com.guanghui.amen.mapper.DictMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import javax.annotation.Resource;

import com.guanghui.amen.service.IMenuService;
import com.guanghui.amen.entity.Menu;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 林同学
 * @since 2022-07-24
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    
        @Resource
        private IMenuService menuService;

        @Resource
        private DictMapper dictMapper;

        @PostMapping
        public Result save(@RequestBody Menu menu){
                return Result.success(menuService.saveOrUpdate(menu));
                }

        @GetMapping
        public Result hhd(@RequestParam(defaultValue = "") String name){
                        return Result.success(menuService.findMenus(name));
                }

        @GetMapping("ids")
        public Result findAllIds(@RequestParam(defaultValue = "") String name){
                return Result.success(menuService.list().stream().map(Menu::getId));
        }

        @DeleteMapping("{id}")
        public Result delete(@PathVariable Integer id){
                return Result.success(menuService.removeById(id));
                }

        @DeleteMapping("del/batch")
        public Result deleteBatch(@PathVariable Integer ids){
                return Result.success(menuService.removeById(ids));
                }

        @GetMapping("{id}")
        public Result findOne(@PathVariable Integer id){
                return Result.success(menuService.getById(id));
                }

        @GetMapping("page")//MybatisPlus方式实现分页查询和模糊查询
        public Result findPage(@RequestParam Integer pageNum,
                               @RequestParam Integer pageSize,
                               @RequestParam String name
                ){
                QueryWrapper<Menu> queryWrapper=new QueryWrapper<>();
                queryWrapper.like("name",name);
                queryWrapper.orderByDesc("id");
                return Result.success(menuService.page(new Page<>(pageNum,pageSize),queryWrapper));
                }
        @GetMapping("/icons")
        public Result getIcons(){
            QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
            return Result.success(dictMapper.selectList(queryWrapper));
        }

}

