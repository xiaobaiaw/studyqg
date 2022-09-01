package com.guanghui.amen.service;

import com.guanghui.amen.entity.Notice;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 林同学
 * @since 2022-08-30
 */
public interface INoticeService extends IService<Notice> {

    List<Notice> listType(Integer type);
}
