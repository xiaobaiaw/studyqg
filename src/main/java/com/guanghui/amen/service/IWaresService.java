package com.guanghui.amen.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanghui.amen.entity.Wares;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-31
 */
public interface IWaresService extends IService<Wares> {

    Page<Wares> findPage(Page<Wares> objectPage, String name);
}
