package com.ddbuy.solr;


import org.apache.solr.client.solrj.beans.Field;

import java.io.Serializable;

public class SolrProduct implements Serializable {
    @Field(value = "pid")
    private Long pid;      //存放商品编号
    @Field
    private String title;  //存放标签
    @Field
    private String image;  //图片
    @Field
    private Long price;    //价格

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
