package com.solr;


import com.solr.entity.Student;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.QueryResponse;

import java.util.List;

//查询索引
public class SelectIndex {
    public static void main(String[] args) throws Exception {
        //1.创建solr的服务器对象
        String solrUrl="http://localhost:8081/solr/new_core";
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //2.使执行查询操作
          //2.1 封装查询条件
          SolrQuery query = new SolrQuery();
            //a.封装查询条件
            //query.set("q","域:域值");
            query.set("q","*:*");  //查询所有
            //b.指定排序
            query.addSort("xh",SolrQuery.ORDER.asc);
            //c.指定分页
            query.setStart(0);  //指定开始位置(0开始)   (页码-1)*页大小
            query.setRows(5);   //指定读取的长度        页大小
          //2.2 执行查询
          QueryResponse queryResponse = solr.query(query);
          //以bean的方式处理返回结果
          List<Student> list = queryResponse.getBeans(Student.class);
          //2.3 处理结果
          System.out.println("学号\t姓名\t年龄\t性别");
          list.forEach(s ->{
              System.out.println(s.getXh()+"\t"+s.getName()
                      +"\t"+s.getAge()+"\t"+s.getSex());
          });
    }
}
