package com.guanghui.amen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.common.Result;
import com.guanghui.amen.entity.Goods;
import com.guanghui.amen.mapper.GoodsMapper;
import com.guanghui.amen.service.IGoodsService;
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
 * @since 2022-08-21
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Resource
    private GoodsMapper goodsMapper;

    @Override
    public Page<Goods> findPage(Page<Goods> page, String name, String user) {
        return goodsMapper.findPage(page,name,user);
    }

    @Override
    public Page<Goods> selectAllPage(Page<Goods> page) {
        return goodsMapper.selectAllPage(page);
    }
}
