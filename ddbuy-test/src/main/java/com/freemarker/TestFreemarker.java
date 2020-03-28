package com.freemarker;


import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.HashMap;

public class TestFreemarker {
    public static void main(String[] args) throws Exception {
        //freemarker基于模板生成新文件的步骤:
        //1.导入freemarker的依赖、创建模板文件
        //2.生成的步骤
          //2.1 创建Configuration对象
          Configuration cfg = new Configuration(Configuration.getVersion());
          //2.2 设置相关参数
            //指定编码
            cfg.setDefaultEncoding("utf-8");
            //指定模板目录
            cfg.setDirectoryForTemplateLoading(new File("F:\\u4-gy_house\\ddbuy-parent\\ddbuy-test\\src\\main\\resources"));
        //3.获取模板对象
        Template template = cfg.getTemplate("first.ftl");
        //4.创建HashMap对象，存放模型数据并填充
        HashMap<String,Object> map = new HashMap<String, Object>();
        //模板文件中一个占位符，对应一个map集合中的键
        map.put("title","K9509高新大佬班");
        map.put("content","1.每天敲代码 2.多背面试题 3.整理好简历 4.强化理解技术");
        map.put("stulist", Arrays.asList("耿垚","陈进能","李昂","吴志辉"));
        //5.基于模板生成新的文件(静态网页)
        Writer writer = new FileWriter("f:\\Freemarker-html\\"+System.currentTimeMillis()+".html");
        template.process(map,writer);
        System.out.println("文件生成完毕！");
        //6.关闭流
        writer.close();
    }

}
