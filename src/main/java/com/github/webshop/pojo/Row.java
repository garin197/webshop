package com.github.webshop.pojo;

public class Row {
    private int page;
    private int limit;
    private int start;


    public Row() {
    }

    public Row(int page, int limit) {
        this.page = page;
        this.limit = limit;
    }

    public int getStart() {
        start = (page - 1) * limit;
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
