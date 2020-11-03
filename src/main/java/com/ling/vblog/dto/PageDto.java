package com.ling.vblog.dto;

import com.ling.vblog.entity.Blog;

import java.io.Serializable;
import java.util.List;

public class PageDto implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer total;//总共多少页
    private Integer currentPage;//当前第几页
    private Integer pageSize;//一页多少数据
    private List<Blog> records;//当前页的数据对象列表

    public List<Blog>  getRecords() {
        return records;
    }

    public void setRecords(List<Blog>  recoders) {
        this.records = recoders;
    }

    public PageDto() {
    }

    public PageDto(Integer total, Integer currentPage, Integer pageSize) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
    }

    public PageDto(Integer total, Integer currentPage, Integer pageSize, List<Blog>  recoders) {
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.records = recoders;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

}
