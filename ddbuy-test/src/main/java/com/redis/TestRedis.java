package com.redis;

import redis.clients.jedis.Jedis;

public class TestRedis {
    public static void main(String[] args) {
        //使用jedis操作Redis的步骤
        //1.创建Jedis对象
          Jedis jedis = new Jedis("localhost",6379);
        //2.使用Jedis对象操作Redis
          //2.1 存一个字符串
          jedis.set("name","耿垚");
          //2.2 设置键的有效期
          jedis.expire("name",300);
          //2.3 获取值
          String str = jedis.get("name");
          System.out.println("值是："+str);
        //3.关闭资源
          jedis.close();

    }
}
