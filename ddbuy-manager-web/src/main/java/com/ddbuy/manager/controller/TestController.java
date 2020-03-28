package com.ddbuy.manager.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.service.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

    @Reference(interfaceClass = Test.class,check = false)
    private Test test;

    @RequestMapping("/getH")
    @ResponseBody
    public String getH(){
        //调用远程服务
        String hello = test.getHello();
        return hello;
    }

}
