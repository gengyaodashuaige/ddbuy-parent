package com.ddbuy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbContent;
import com.ddbuy.entity.TbContentExample;
import com.ddbuy.mapper.TbContentMapper;
import com.ddbuy.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service(interfaceClass = TbContentService.class) //暴露服务
@Component
public class TbContentServiceImpl implements TbContentService {

    //注入mapper
    @Autowired(required = false)
    private TbContentMapper tbContentMapper;

    //注入RedisTemplate操作Redis
    @Autowired
    private RedisTemplate redisTemplate;

    //添加广告
    @Override
    public int addTbContent(TbContent tbContent) {
        //解决广告缓存同步问题
        //清楚缓存，当用户访问时，查询数据库
        if(this.redisTemplate.hasKey("tbcontentkey")){  //存在缓存
            //清除缓存中的数据
            this.redisTemplate.delete("tbcontentkey");
        }
        return tbContentMapper.insertSelective(tbContent);
    }

    //查询门户系统的所有广告
    @Override
    public List<TbContent> getAllContent() {
        //添加缓存支持
        //1.springboot整合Redis
        //2.判断，如果Redis中存在数据，就从Redis中拿；没有，则从数据库获取
        List<TbContent> list = null;
      //ValueOperations<String,List<TbContent>> ops = this.redisTemplate.opsForValue();
        ValueOperations<String,List<TbContent>> ops = this.redisTemplate.opsForValue();
        if (redisTemplate.hasKey("tbcontentKey")){
            //存在
            list = ops.get("tbcontentKey");
            System.out.println("从缓存中拿，不操作数据库");
        }else {
            //不存在，查询数据库，并保存到缓存中
            TbContentExample tbContentExample = new TbContentExample();
            TbContentExample.Criteria criteria = tbContentExample.createCriteria();
            criteria.andCategoryIdEqualTo(new Long("1")); //查询门户广告
            list = tbContentMapper.selectByExample(tbContentExample);
            //将数据保存到Redis中
            ops.set("tbcontentKey",list,2, TimeUnit.MINUTES);
            System.out.println("第一次查询数据库，并缓存ing...");
        }
        return list;
    }
}
