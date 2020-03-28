package com.ddbuy.item.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.service.TbItemService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TbItemController {

    @Reference(interfaceClass = TbItemService.class,check = false,timeout = 10)
    private TbItemService tbItemService;

    //查询所有商品，生成静态页面
    @RequestMapping("/processHtml")
    @ResponseBody
    public boolean processHtml(){
        boolean flag = tbItemService.processHtml();
        return flag;
    }

}
