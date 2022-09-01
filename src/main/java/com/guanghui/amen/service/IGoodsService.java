package com.guanghui.amen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-21
 */
public interface IGoodsService extends IService<Goods> {

    Page<Goods> findPage(Page<Goods> page, String name, String user);

    Page<Goods> selectAllPage(Page<Goods> page);

}
