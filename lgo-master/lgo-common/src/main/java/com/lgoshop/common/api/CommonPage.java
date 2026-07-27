package com.lgoshop.common.api;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * 通用分页数据封装类
 * Created by lgo-shop.
 */
public class CommonPage<T> {
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 每页数量
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 分页数据
     */
    private List<T> list;

    /**
     * MP分页结果转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> pageResult) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage((int)pageResult.getPages());
        result.setPageNum((int)pageResult.getCurrent());
        result.setPageSize((int)pageResult.getSize());
        result.setTotal(pageResult.getTotal());
        result.setList(pageResult.getRecords());
        return result;
    }

    /**
     * Spring Data 分页结果转为分页信息（用于 MongoDB）
     */
    public static <T> CommonPage<T> restPage(org.springframework.data.domain.Page<T> pageResult) {
        CommonPage<T> result = new CommonPage<T>();
        result.setPageNum(pageResult.getNumber() + 1);
        result.setPageSize(pageResult.getSize());
        result.setTotalPage(pageResult.getTotalPages());
        result.setTotal(pageResult.getTotalElements());
        result.setList(pageResult.getContent());
        return result;
    }

    /**
     * 普通list转分页信息
     */
    public static <T> CommonPage<T> restPage(List<T> list) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage(1);
        result.setPageNum(1);
        result.setPageSize(list.size());
        result.setTotal((long)list.size());
        result.setList(list);
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
