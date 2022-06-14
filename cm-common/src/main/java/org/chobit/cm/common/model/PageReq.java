package org.chobit.cm.common.model;

import static org.chobit.common.utils.StrKit.isBlank;

public class PageReq {


    private int pageNo;

    private int pageSize;

    private String sort;

    private String keywords;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getKeywords() {
        return isBlank(keywords) ? "" : keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "PageReq{" +
                "pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                ", sort='" + sort + '\'' +
                ", keywords='" + keywords + '\'' +
                '}';
    }
}
