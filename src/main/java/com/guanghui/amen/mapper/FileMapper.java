package com.guanghui.amen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.guanghui.amen.entity.Files;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface FileMapper extends BaseMapper<Files> {
    @Select("SELECT * from sys_file")
    List<Files> selectFilesList();
    @Select("SELECT * from sys_file WHERE #{md5}")
    List<Files> selectMd5List(@Param("md5") String md5);

    List<Files> selectIdsList(@Param("ids") List<Integer> ids);
}