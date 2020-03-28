package com.ddbuy.sso.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.service.TbUserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class TbUserController {

    @Reference(interfaceClass = TbUserService.class,check = false)
    private TbUserService tbUserService;

    //单点登陆服务
    @RequestMapping("/loginAction")
    public String loginAction(String username, String password,
                              HttpServletResponse response){
        //远程调用服务
        String token = tbUserService.login(username, password);
        if (token==null){ //登陆不成功
            return "Login";
        }else {
            //登陆成功，写入cookie
            //创建cookie
            Cookie cookie = new Cookie("token",token);
            cookie.setPath("/");  //解决cookie不同子系统中跨域
            cookie.setMaxAge(20*60); //设置cookie有效时间20分钟
            //写入到客户端
            response.addCookie(cookie);
            return "success";
        }

    }

    //通过token获取用户名称
    @RequestMapping("/getLoginName")
    @ResponseBody
    @CrossOrigin(value = "*",maxAge = 3600) //允许所有的地址过来(支持跨域)
    public String getLoginName(String token){
        //远程调用服务，获取用户名
        String str = this.tbUserService.getUserName(token);
        return str;
    }

}
