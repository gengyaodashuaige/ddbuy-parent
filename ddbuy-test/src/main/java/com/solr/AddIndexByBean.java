package com.solr;

import com.solr.entity.Student;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.common.SolrInputDocument;

//以Javabean的方式添加
public class AddIndexByBean {
    public static void main(String[] args) throws Exception {
        //1.创建solr的服务器对象
        String solrUrl="http://localhost:8081/solr/new_core";
        HttpSolrClient solr = new HttpSolrClient.Builder(solrUrl).withConnectionTimeout(10000).withSocketTimeout(60000).build();
        //2.使用solr服务对象操作索引(添加)
          //2.1 以bean对象的方式添加
          Student student = new Student();
          student.setXh(205);
          student.setName("小钰");
          student.setAge(18);
          student.setSex("女");
          //2.2 添加
          solr.addBean(student);
        //3.提交
        solr.commit();
        System.out.println("添加成功！");

    }
}
