package com.ddbuy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbContent;
import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.entity.TbContentCategoryExample;
import com.ddbuy.entity.TbContentExample;
import com.ddbuy.mapper.TbContentCategoryMapper;
import com.ddbuy.service.TbContentCategroyService;
import com.ddbuy.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Service(interfaceClass = TbContentCategroyService.class) //暴露当前服务
@Component
public class TbContentCategroyServiceImpl implements TbContentCategroyService {

    //注入mapper
    @Autowired(required = false)
    private TbContentCategoryMapper tbContentCategoryMapper;

    //分页查询所有类别
    @Override
    public PageInfo<TbContentCategory> getContentCategoryByPage(PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询所有
        List<TbContentCategory> list = tbContentCategoryMapper.selectByExample(new TbContentCategoryExample());
        return new PageInfo<TbContentCategory>(list);
    }

    //查询所有广告分类
    @Override
    public List<TbContentCategory> getAllTbContentCategory() {
        return tbContentCategoryMapper.selectByExample(new TbContentCategoryExample());
    }
}
