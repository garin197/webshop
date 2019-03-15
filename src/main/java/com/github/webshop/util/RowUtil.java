package com.github.webshop.util;

import com.github.webshop.pojo.Row;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页工具
 */
public class RowUtil {
    public static Row getRow(HttpServletRequest request){
        String page=request.getParameter("page");
        String limit=request.getParameter("limit");
        Row row=new Row();
        row.setPage(Integer.parseInt(page));
        row.setLimit(Integer.parseInt(limit));
        return row;
    }
}
