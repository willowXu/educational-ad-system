package pers.xls.util;

import java.util.List;

/**
 * @Author: xuliushen
 * @Description:
 * @Date Created in 2021-09-26 18:30
 * @Modified by :
 */
public class PageUtil {
    //1.页码值 2.显示条数 3.查询结果 4.总条数 5.总页数
    private int pageIndex;
    private int pageSize = 5;
    private List dataList;
    private int total;
    private int totalPages;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotalPages() {
        return total%pageSize>0?total/pageSize+1:total/pageSize;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
}
