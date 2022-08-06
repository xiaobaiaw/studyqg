package com.guanghui.amen.service;

import com.guanghui.amen.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-07-24
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);
}
