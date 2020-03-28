package com.ddbuy.service;

import com.ddbuy.entity.TbUser;

public interface TbUserService {

    /**
     * 单点登陆服务
     * @param username  用户名
     * @param password  密码
     * @return  返回token
     */
    public String  login(String username, String password);

    /**
     * 通过token获取用户名称
     * @param token
     * @return  用户名称
     */
    public String getUserName(String token);
}
