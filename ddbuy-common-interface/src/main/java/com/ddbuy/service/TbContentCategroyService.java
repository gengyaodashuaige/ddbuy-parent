package com.ddbuy.service;

import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface TbContentCategroyService {
    //分页查询所有的类别
    PageInfo<TbContentCategory> getContentCategoryByPage(PageUtil pageUtil);

    //查询所有广告分类
    List<TbContentCategory> getAllTbContentCategory();
}
