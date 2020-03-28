package com.solr.entity;


import org.apache.solr.client.solrj.beans.Field;

//实体类 与 索引对应
public class Student {
    //一个索引的域字段对应一个类的属性
    //@Field指定哪个域与属性对应
    //@Field(value = "域名称") 如果属性和域名称相同，可以不指定
    @Field(value = "xh")
    private Integer xh;
    @Field
    private String name;
    @Field
    private String sex;
    @Field
    private Integer age;

    public Integer getXh() {
        return xh;
    }

    public void setXh(Integer xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
