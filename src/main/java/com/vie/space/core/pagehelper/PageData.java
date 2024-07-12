package com.vie.space.core.pagehelper;

import java.io.Serializable;
import java.util.List;

import com.github.pagehelper.Page;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PageData<T> implements Serializable {

    private long total;    //总记录数
    private List<T> list;  //结果集
    private int pageNum;    //页码
    private int pages; //总页数
    private int size;   //集合元素数量
    private boolean hasNextPage;    //是否有后一页

    /**
     * 私有构造函数
     */
    private PageData() {}

    /**
     * 私有构造函数
     * @param list 封装的集合
     */
    private PageData(List<T> list) {

        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            this.total = page.getTotal();
            this.list = list;
            this.pageNum = page.getPageNum();
            this.pages = page.getPages();
            this.size = page.size();

            this.judgePageBoundary();
        }
    }

    /**
     * 封装集合，处理分页
     * @param list 封装的集合
     */
    public static <T> PageData<T> pack(List<T> list) {
        return new PageData<>(list);
    }

    /**
     * 分页边界
     */
    private void judgePageBoundary() {
        this.hasNextPage = this.pageNum < this.pages;
    }
    
}
