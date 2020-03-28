package com.solr;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

//操作索引库--删除
public class DelIndex {
    public static void main(String[] args) throws Exception {
        //1.创建solr的服务器对象
        String solrUrl="http://localhost:8081/solr/new_core";
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //2.使用solr服务对象操作索引(删除)
          //2.1 通过索引的id删除
          //solr.deleteById("15ffa467-e073-4c32-8ccf-fe8fadd80f2e");
          //2.2 通过指定条件删除   域名:值
          solr.deleteByQuery("xh:222");
        //3.提交
        solr.commit();
        System.out.println("删除成功！");

    }
}
