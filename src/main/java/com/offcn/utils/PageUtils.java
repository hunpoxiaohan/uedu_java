package com.offcn.utils;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Data //生成 空参构造  get、set、 tostring、 equals、 hascode
public class PageUtils<T> {

    private int totalCount;//总记录数   依赖查询，外界传入
    private int pageSize;//页面容量    依赖外界传入
    private int currentPage;//当前页码    依赖外界传入
    private int totalPage;//总页数 √
    private int startIndex;//每一页的起始索引
    private int prePage;//上一页    √
    private int nextPage;//下一页   √
    private List<T> list;//分页查询出的数据本身

    public PageUtils(int totalCount, int pageSize, String currentPage) {
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        initCurrentPage(currentPage);
        initTotalPage();
        initStartIndex();
        initPrePage();
        initNextPage();
    }

    //初始化当前页码
    public void initCurrentPage(String currentPage) {
        if(currentPage == null) {
            //第一次加载页面时没有当前页码，则默认为1
            this.currentPage = 1;
        } else {
            //后续操作分页工具条，都是基于当前页在操作的
            this.currentPage = Integer.parseInt(currentPage);
        }
    }

    //初始化每一页的起始索引
    public void initStartIndex() {
        this.startIndex = (this.currentPage - 1) * this.pageSize;
    }

    //初始化上一页
    public void initPrePage() {
        if(this.currentPage == 1) {
            //如果本身就是第一页，则前一页依然是第一页
            this.prePage = 1;
        } else {
            //如果是2到更大的页码，则有前一页
            this.prePage = this.currentPage - 1;
        }
    }

    //初始化总页数
    public void initTotalPage() {
        this.totalPage = (this.totalCount % this.pageSize == 0) ? (this.totalCount / this.pageSize) : (this.totalCount / this.pageSize + 1);
    }

    //初始化下一页
    public void initNextPage() {
        if(this.currentPage == this.totalPage) {
            //如果本身就是最后一页，则下一页依然是最后一页
            this.nextPage = totalPage;
        } else {
            //如果是倒数第二页以及以前，则有下一页
            this.nextPage = this.currentPage + 1;
        }
    }

}
