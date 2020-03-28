package com.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

//操作索引库--添加
public class AddIndex {
    public static void main(String[] args) throws Exception {
        //1.创建solr的服务器对象
        String solrUrl="http://localhost:8081/solr/new_core";
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //2.使用solr服务对象操作索引(添加)
          //2.1 创建索引
          SolrInputDocument document = new SolrInputDocument();
          //2.2 索引赋值
          document.addField("xh",201);
          document.addField("name","小耿");
          document.addField("age",22);
          document.addField("sex","男");
          //2.3 添加索引   添加、修改、删除时，需要提交！！！
          solr.add(document);
        //3.提交
        solr.commit();
        System.out.println("添加成功！");

    }
}
