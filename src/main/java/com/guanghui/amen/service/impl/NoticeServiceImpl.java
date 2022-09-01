package com.guanghui.amen.service.impl;

import com.guanghui.amen.entity.Notice;
import com.guanghui.amen.mapper.NoticeMapper;
import com.guanghui.amen.service.INoticeService;
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
 * @since 2022-08-30
 */
@Service
public class NoticeServiceImpl extends ServiceImpl<NoticeMapper, Notice> implements INoticeService {

    @Resource
    private NoticeMapper noticeMapper;

    @Override
    public List<Notice> listType(Integer type) {
        return noticeMapper.listType(type);
    }
}
