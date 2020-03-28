package com.ddbuy.search.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.ddbuy.search.util.PageNavegateUitl;
import com.ddbuy.service.TbItemService;
import com.ddbuy.util.SearchCondition;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class TbItemController {

    @Reference(interfaceClass = TbItemService.class,timeout = 10000,check = false)
    private TbItemService tbItemService;

    //将数据库中的数据导入到索引库
    @RequestMapping("/import")
    @ResponseBody
    public boolean importSolr(){
        //远程调用服务实现导入
        boolean result = tbItemService.importData();
        return result;
    }

    /**
     * 实现分页查询的服务
     * @param searchCondition
     * 传：查询条件title、页码page
     * @return
     */
    @RequestMapping("/search")
    public String search(SearchCondition searchCondition, Model model){
        //设置页大小
        searchCondition.setRows(40);
        //调用服务获取数据
        Map<String, Object> map = tbItemService.searchSorProduct(searchCondition);

        //将当前页数据回填到页面
        model.addAttribute("list",map.get("list"));
        //填充查询条件：回显
        model.addAttribute("title",searchCondition.getTitle());
        //生成导航条
        String nagegateUtil = PageNavegateUitl.build(searchCondition.getPage(),Integer.parseInt(map.get("totalPage").toString()), 9);
        //填充分页导航
        model.addAttribute("nagegateUtil",nagegateUtil);
        return "searchList";
    }
}
