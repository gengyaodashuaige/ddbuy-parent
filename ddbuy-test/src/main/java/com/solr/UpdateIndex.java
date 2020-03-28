package com.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

//操作索引库--修改
public class UpdateIndex {
    public static void main(String[] args) throws Exception {
        //1.创建solr的服务器对象
        String solrUrl="http://localhost:8081/solr/new_core";
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //2.使用solr服务对象操作索引(修改)
          //2.1 创建索引对象
          SolrInputDocument document = new SolrInputDocument();
          //设置字段值   指定id值，添加时发生替换
          document.addField("id","fef4e1bd-5e23-4eaa-a042-d41711fcb106");
          document.addField("xh",203);
          document.addField("name","鞠婧祎");
          document.addField("age",21);
          document.addField("sex","女");
          //2.3 添加索引   添加、修改、删除时，需要提交！！！
          solr.add(document);
        //3.提交
        solr.commit();
        System.out.println("修改成功！");

    }
}
