package com.ddbuy.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.ddbuy.entity.TbItem;
import com.ddbuy.entity.TbItemExample;
import com.ddbuy.mapper.TbItemMapper;
import com.ddbuy.service.TbItemService;
import com.ddbuy.solr.SolrProduct;
import com.ddbuy.util.SearchCondition;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.zookeeper.common.PathTrie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(interfaceClass = TbItemService.class,timeout = 10000)
@Component
public class TbItemServiceImpl implements TbItemService {

    @Autowired
    private SolrClient solrClient;

    @Autowired(required = false)
    private TbItemMapper tbItemMapper;

    @Autowired
    private Configuration configuration;

    //将数据库中的数据导入到索引库
    @Override
    public boolean importData() {
        try {
            //1.获取数据库中所有商品的信息
            List<SolrProduct> products = tbItemMapper.getAllSolrProduct();
            //2.将所有的信息导入到索引库
            //springboot整合solr
            solrClient.addBeans(products);
            solrClient.commit();  //提交
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    //实现分页查询的服务
    @Override
    public Map<String, Object> searchSorProduct(SearchCondition searchCondition) {
        try {
            //1.根据查询条件获取当前页的数据
            SolrQuery solrQuery = new SolrQuery();
            //设置条件 (给条件，按条件搜；不给，搜所有)
            if (searchCondition.getTitle()!=null && !searchCondition.getTitle().equals("")){
                solrQuery.set("q","title:"+searchCondition.getTitle());
            }else {
                solrQuery.set("q","*:*");
            }
            //设置分页
              // 指定开始位置(0开始)  页码-1)*页大小
              solrQuery.setStart((searchCondition.getPage()-1)*searchCondition.getRows());
              //指定读取的长度       页大小
              solrQuery.setRows(searchCondition.getRows());
            QueryResponse query = solrClient.query(solrQuery);
            List<SolrProduct> list = query.getBeans(SolrProduct.class);

            //2.获取总记录数及总页数
              //2.1 获取总记录数
              long totalNum = query.getResults().getNumFound();
              //2.2 获取总页数
              long totalPage = 0;
              //方法一
              /*if (totalNum%searchCondition.getRows()==0)
                  totalPage=totalNum%searchCondition.getRows();
              else
                  totalPage=totalNum%searchCondition.getRows()+1;*/
              //方法二
              totalPage=(long)Math.ceil(totalNum*1.0/searchCondition.getRows());

            //3.将数据填充到map集合并返回
            Map<String ,Object> map = new HashMap<>();
            map.put("list",list);  //当前页的数据
            map.put("totalrecord",totalNum); //总记录数
            map.put("totalPage",totalPage);  //总页数
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //查询所有商品，生成静态页面
    @Override
    public boolean processHtml() {
        try {
            //1.查询所有商品
            List<TbItem> list = tbItemMapper.selectByExample(new TbItemExample());
            //2.使用freemark生成静态网页
            for (TbItem tbitem : list) {  //循环一次一个商品一个静态网页
                //2.1 指定模板
                Template template = configuration.getTemplate("Product.ftl");
                //2.2 确定数据
                Map<String ,Object> map = new HashMap<>();
                map.put("p",tbitem); //将商品对象填入模板
                //2.3 生成文件  生成到某目录中，再复制到商品t详情子系统
                String path="F:\\u4-gy_house\\ddbuy-parent\\ddbuy-item-web\\src\\main\\webapp\\";
                //2.4 基于模板生成新的文件(静态网页)
                Writer w=new FileWriter(path+tbitem.getId()+".html");
                template.process(map,w);
                //2.5 关闭流
                w.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
