package com.ddbuy.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.entity.TbContent;
import com.ddbuy.service.TbContentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    //调用远程服务
    @Reference(interfaceClass = TbContentService.class,check = false)
    private TbContentService tbContentService;

    //查询门户系统的所有广告
    @RequestMapping("/getIndex")
    public String getIndex(Model model){
        //调用服务
        //1.查询广告
        List<TbContent> list = tbContentService.getAllContent();
        //填充数据
        model.addAttribute("list",list);
        return "index";
    }

}