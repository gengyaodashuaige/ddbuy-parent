package com.ddbuy.sso.util;

/**
 * @author 王建兵
 * @Classname PageNavegateUitl
 * @Description TODO
 * @Date 2019/7/29 15:29
 * @Created by Administrator
 */
public class PageNavegateUitl {

    /**
     * 分页导航1
     *
     * @param total
     *            总记录数
     * @param per
     *            每页记录数
     * @param page
     *            当前页数
     * @param query_stringUrl
     *            连接页面地址
     **/
    public static String paging(int total, int per, int page,
                                String query_string) {
        int allpage = 0;
        int next = 0;
        int pre = 0;
        int startcount = 0;
        int endcount = 0;
        String pagestr = "";
        if (page < 1) {
            page = 1;
        }
        // 计算总页数
        if (per != 0) {
            allpage = (total / per);
            allpage = ((total % per) != 0 ? allpage + 1 : allpage);
            allpage = (allpage == 0 ? 1 : allpage);
        }
        next = page + 1;
        pre = page - 1;
        startcount = (page + 5) > allpage ? allpage - 9 : page - 4;// 中间页起始序号
        // 中间页终止序号
        endcount = page < 5 ? per : page + 5;
        if (startcount < 1) {
            startcount = 1;
        } // 为了避免输出的时候产生负数，设置如果小于1就从序号1开始
        if (allpage < endcount) {
            endcount = allpage;
        }// 页码+5的可能性就会产生最终输出序号大于总页码，那么就要将其控制在页码数之内
        pagestr = "共" + allpage + "页      ";

        pagestr += page > 1 ? "首页  上一页" : "首页 上一页";
        // 中间页处理，这个增加时间复杂度，减小空间复杂度
        for (int i = startcount; i <= endcount; i++) {
            pagestr += page == i ? "  " + i
                    + "" : "  " + i + "";
        }
        pagestr += page != allpage ? "  下一页  末页" : " 下一页 末页";

        return pagestr;
    }


    /**
     * 分页导航3
     * @param pageSize 分页大小
     * @param currentPage 当前页
     * @param totalCount 总记录数
     * */
    public static String showPageNavigate(int pageSize, int currentPage,
                                          int totalCount) {
        String redirecTo = "";// 跳转的页面
        // 如果没有pageSize，默认设置为3
        pageSize = pageSize == 0 ? 3 : pageSize;
        int totalPages = Math.max((totalCount + pageSize - 1) / pageSize, 1);// 总分页数
        StringBuilder output = new StringBuilder();
        if (totalPages > 1) {
            if (currentPage != 1) {// 处理首页链接
                output.append("首页");
            }
            if (currentPage > 1) { // 处理上一页
                output.append("上一页");
            }
            output.append(" ");
            int currint = 5;
            for (int i = 0; i < 10; i++) {// 一共最多显示10个页码
                if ((currentPage + i - currint) >= 1
                        && (currentPage + i - currint) <= totalPages) {// 处理当前页面的前面5个，后面5个
                    if (currentPage == (currentPage + i - currint)) { // 处理当前页
                        output.append("" + currentPage + "");
                    } else {// 处理其它页
                        output.append(""
                                + (currentPage + i - currint) + "");
                    }
                }
                output.append(" ");
            }
            if (currentPage < totalPages) { // 处理下一页
                output.append("下一页");
            }
            if (currentPage != totalPages) { // 处理末页
                output.append("末页");
            }
            output.append(" ");
        }
        output.append("第" + currentPage + "页/共" + totalPages + "页");// 统计
        return output.toString();
    }

//////////////////////////////////////////////////////////////////
    /**
     * 分页导航2 最好用
     *
     * @param pageIndex
     *            索引页码,从1开始
     * @param pageCount
     *            总页数
     * @param showPageCount
     *            显示分页个数(奇数)
     * */
    public static String build(int pageIndex, int pageCount, int showPageCount) {
        if (pageCount == 1)
            return "";

        StringBuilder sb = new StringBuilder();
        int span = showPageCount / 2; // 前后对称的个数
        int from, to;

        if (pageCount > showPageCount + 1) // 导航中出现省略号
        {
            if (pageIndex <= span + 1) // 省略号出现在右边
            {
                from = 1;
                to = showPageCount;

                if (pageIndex != 1)
                    sb.append("<a href='javascript:goPage("+(pageIndex-1)+")' class=\"p_pre\">上一页</a>");
                sb.append(showPageNavigation(pageIndex, from, to));
                sb.append("...");
                sb.append("<a href='javascript:goPage("+pageCount+")' class=\"p_pre\">"+pageCount+"</a>");
                sb.append("<a href='javascript:goPage("+(pageIndex+1)+")' class=\"p_pre\">下一页</a>");
            } else if (pageIndex >= pageCount - span) // 省略号出现在左边
            {
                from = pageCount + 1 - showPageCount;
                to = pageCount;

                sb.append("<a href='javascript:goPage("+(pageIndex-1)+")' class=\"p_pre\">上一页</a>");
                sb.append("<a href='javascript:goPage(1)' class=\"p_pre\">1</a>");
                sb.append("...");
                sb.append(showPageNavigation(pageIndex, from, to));
                if (pageIndex != pageCount)
                    sb.append("<a href='javascript:goPage("+(pageIndex+1)+")' class=\"p_pre\">下一页</a>");
            } else // 省略号出现在两边
            {
                from = pageIndex - span;
                to = pageIndex + span;

                sb.append("<a href='javascript:goPage("+(pageIndex-1)+")' class=\"p_pre\">上一页</a>");
                sb.append("<a href='javascript:goPage(1)' class=\"p_pre\">1</a>");
                sb.append("...");
                sb.append(showPageNavigation(pageIndex, from, to));
                sb.append("...");
                sb.append("<a href='javascript:goPage("+pageCount+")' class=\"p_pre\">" + pageCount
                        + "</a>");
                sb.append("<a href='javascript:goPage("+(pageIndex+1)+")' class=\"p_pre\">下一页</a>");
            }
        } else // 导航中不出现省略号
        {
            from = 1;
            to = pageCount;

            if (pageIndex != 1)
                sb.append("<a href='javascript:goPage("+(pageIndex-1)+")' class=\"p_pre\">上一页</a>");
            sb.append(showPageNavigation(pageIndex, from, to));
            if (pageIndex != pageCount)
                sb.append("<a href='javascript:goPage("+(pageIndex+1)+")' class=\"p_pre\">下一页</a>");
        }
        return sb.toString();
    }

    private static String showPageNavigation(int pageIndex, int from, int to) {
        StringBuilder sb = new StringBuilder();

        for (int i = from; i <= to; i++) {
            if (i == pageIndex) {
                sb.append("<a href='javascript:goPage("+i+")' class=\"cur\">" + i + "</a>");
            } else {
                sb.append("<a href='javascript:goPage("+i+")'  class=\"p_pre\">" + i + "</a>");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(PageNavegateUitl.build(8,20,9));
    }
}
