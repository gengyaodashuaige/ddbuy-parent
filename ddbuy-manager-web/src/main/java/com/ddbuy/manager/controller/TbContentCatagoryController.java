package com.ddbuy.manager.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.entity.TbContentCategory;
import com.ddbuy.service.TbContentCategroyService;
import com.ddbuy.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TbContentCatagoryController {

    //注入远程服务
    @Reference(interfaceClass = TbContentCategroyService.class)
    private TbContentCategroyService tbContentCategroyService;

    //分页查询所有类别
    @RequestMapping("/getTbContentCatagory")
    @ResponseBody
    public Map<String,Object> getTbContentCatagory(PageUtil pageUtil){
        //调用服务
        PageInfo<TbContentCategory> pageInfo = tbContentCategroyService.getContentCategoryByPage(pageUtil);
        //创建map返回异步数据对接easyui
        Map<String,Object> map = new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

    //查询所有广告分类
    @RequestMapping("/getAllContentCatagory")
    @ResponseBody
    public List<TbContentCategory> getAllContentCatagory(){
        //调用远程服务
        return tbContentCategroyService.getAllTbContentCategory();
    }

}
