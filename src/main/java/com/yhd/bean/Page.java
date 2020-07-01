package com.yhd.bean;
import java.util.List;

/**
 * @Author 杨航
 * @Date 2020/6/7 19:41
 * @Since 1.8
 */
public class Page<T> {
    private long totalPageCount; // 总页数
    private int pageSize;   // 每页显示记录数
    private int currPageNo; // 当前页面
    private int maxPagination; // 最多存放的页码数量
    private int pageStart;  // 开始的页面
    private int pageEnd;    // 结束的页面
    private List<T> newsList; // 存储的数据

    public Page(long totalPageCount, int currPageNo, int pageSize, int maxPagination, List<T> newsList) {
        this.pageSize = pageSize;   // 设置每页显示的记录数
        setTotalPageCount(totalPageCount); // 设置总页数
        setMaxPagination(maxPagination);  // 设置每页最多存放的页码数量
        setCurrPageNo(currPageNo);  // 设置当前的页数
        this.pageStart = this.currPageNo - this.maxPagination / 2;
        if(pageStart <= 0) {
            this.pageStart = 1;
        }
        this.pageEnd = this.currPageNo  + this.maxPagination / 2;
        if(pageEnd >= this.totalPageCount) {
            pageStart = (int) (this.totalPageCount - this.maxPagination + 1);
        }
        this.newsList = newsList;
    }

    public int getMaxPagination() {
        return maxPagination;
    }

    public void setMaxPagination(int maxPagination) {
        this.maxPagination = (int) Math.min(totalPageCount, maxPagination);;
    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    public long getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(long totalPageCount) {
        this.totalPageCount = (totalPageCount + pageSize - 1) / pageSize;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(int currPageNo) {
        if(currPageNo < 0) {
            this.currPageNo = 1;
        } else if(currPageNo > this.totalPageCount) {
            this.currPageNo = (int) this.totalPageCount;
        }
        this.currPageNo = currPageNo;
    }

    public List<T> getNewsList() {
        return newsList;
    }

    public void setNewsList(List<T> newsList) {
        this.newsList = newsList;
    }

    @Override
    public String toString() {
        return "Page{" +
                "totalPageCount=" + totalPageCount +
                ", pageSize=" + pageSize +
                ", currPageNo=" + currPageNo +
                ", maxPagination=" + maxPagination +
                ", pageStart=" + pageStart +
                ", pageEnd=" + pageEnd +
                ", newsList=" + newsList +
                '}';
    }
}
