package org.chobit.cm.common.model;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class PageResult<T> {

    private long total;

    private int pageNo;

    private int totalPage;

    private final List<T> data = new LinkedList<>();

    public void add(T item) {
        data.add(item);
    }

    public void addData(Collection<T> coll) {
        data.addAll(coll);
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getData() {
        return data;
    }
}
