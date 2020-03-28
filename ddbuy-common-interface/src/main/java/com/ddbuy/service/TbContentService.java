package com.ddbuy.service;

import com.ddbuy.entity.TbContent;

import java.util.List;

public interface TbContentService {
    //添加广告
    public int addTbContent(TbContent tbContent);

    //查询门户系统的所有广告
    public List<TbContent> getAllContent();

}
