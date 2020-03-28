package com.ddbuy.service;


import com.ddbuy.util.SearchCondition;

import java.util.Map;

public interface TbItemService {

    //将数据库中的数据导入到索引库
    public boolean importData();

    /**
     * 实现分页查询的服务
     * @param searchCondition
     * searchCondition 包含查询条件title、page页码
     * @return  Map<String,Object>集合
     * 包含两个值：当前页的数、当前总页数、总记录数
     */
    public Map<String,Object> searchSorProduct(SearchCondition searchCondition);

    /**
     * 查询所有商品，生成静态页面
     * @return  true成功、false失败
     */
    public boolean processHtml();
}
