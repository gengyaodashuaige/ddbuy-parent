package com.ddbuy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbItem;
import com.ddbuy.entity.TbUser;
import com.ddbuy.entity.TbUserExample;
import com.ddbuy.mapper.TbItemMapper;
import com.ddbuy.mapper.TbUserMapper;
import com.ddbuy.service.TbUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TbUserService.class,timeout = 3000)
@Component
public class TbUserServiceImpl implements TbUserService {

    @Autowired(required = false)
    private TbUserMapper tbUserMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    //单点登陆服务
    @Override
    public String login(String username, String password) {
        //1.判断用户在数据库中是否存在
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password); //后期MD5加密
        List<TbUser> list = tbUserMapper.selectByExample(tbUserExample);
        //2.如果存在：创建token，并保存到redis
        if (list!=null && list.size()!=0){  //存在
            //2.1 创建token
            //System.currentTimeMillis(); 用时间生成
            String token = UUID.randomUUID().toString();//用UUID生成token
            //2.2 将token保存到redis
            ValueOperations vo = redisTemplate.opsForValue();
            vo.set("sessionid:"+token,list.get(0),20, TimeUnit.MINUTES);
        //3.返回token
            return token;
        }
        return null;
    }

    //通过token获取用户名称
    @Override
    public String getUserName(String token) {
        if (this.redisTemplate.hasKey("sessionid:"+token)){
            ValueOperations<String,TbUser> vo = this.redisTemplate.opsForValue();
            TbUser tbUser = (TbUser)vo.get("sessionid:"+token);
            return tbUser.getUsername();
        }else {
            return "";
        }
    }
}
