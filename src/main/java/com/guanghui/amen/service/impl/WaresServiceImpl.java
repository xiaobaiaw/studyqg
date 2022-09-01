package com.guanghui.amen.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Wares;
import com.guanghui.amen.mapper.WaresMapper;
import com.guanghui.amen.service.IWaresService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
@Service
public class WaresServiceImpl extends ServiceImpl<WaresMapper, Wares> implements IWaresService {

    @Resource
    private WaresMapper waresMapper;

    @Override
    public Page<Wares> findPage(Page<Wares> objectPage, String name) {
        return  waresMapper.findPage(objectPage,name);
    }
}
