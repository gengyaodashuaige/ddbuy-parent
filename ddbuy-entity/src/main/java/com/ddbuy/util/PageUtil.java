package com.ddbuy.util;

import java.io.Serializable;

/**
 * @author 王建兵
 * @Classname PageUtil
 * @Description TODO
 * @Date 2019/12/20 16:44
 * @Created by Administrator
 */
//封装分页参数   针对 easyui
public class PageUtil  implements Serializable {
   private int page; //取名叫page,因为前端前传的参数叫page
    private int rows=5;  //封装页大小

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
