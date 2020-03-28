package com.ddbuy.util;

import java.io.Serializable;

public class SearchCondition extends PageUtil implements Serializable {

    private String title;  //查询条件

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
