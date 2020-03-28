package com.ddbuy.service;

import com.ddbuy.util.PageUtil;
import com.ddbuy.util.SearchCondition;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DdbuyCommonServiceApplicationTests {
    //注入业务
    @Autowired
    private TbItemService tbItemService;

    @Test
    public void contextLoads() {
        SearchCondition searchCondition = new SearchCondition();
        searchCondition.setPage(1);
        searchCondition.setRows(40);

        Map<String, Object> map = tbItemService.searchSorProduct(searchCondition);
        System.out.println("数据是："+map.get("list"));
        System.out.println("总记录数是："+map.get("totalrecord"));
        System.out.println("总页数是："+map.get("totalPage"));

    }

}