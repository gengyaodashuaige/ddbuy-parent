package com.ddbuy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.service.Test;
import org.springframework.stereotype.Component;

@Service(interfaceClass = Test.class)  //发布服务
@Component
public class TestServiceImpl implements Test {
    @Override
    public String getHello() {
        return "Hello!";
    }
}
