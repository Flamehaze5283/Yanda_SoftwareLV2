package com.Yanda.Ruitesco.dataResp.type;

import java.util.List;

import com.Yanda.Ruitesco.javabean.PageUserList;

public class OrderDataListT {
	private int pageNum;
	private int pageSize;
	private int size;
	private String orderBy;
	private int startRow;
	private int endRow;
	private int total;
	private int pages;
	private List<OrderData> list;
	private int firstPage;
	private int prePage;
	private int nextPage;
	private int lastPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	private int navigatePage;
	private List<Integer> navigatepageNums;
	
	public OrderDataListT(PageUserList pageUserList, List<OrderData> datal) {
		this.pageNum=pageUserList.getPageNum();
		this.pageSize=pageUserList.getPageSize();
		this.size=pageUserList.getSize();
		this.orderBy=pageUserList.getOrderBy();
		this.startRow=pageUserList.getStartRow();
		this.endRow=pageUserList.getEndRow();
		this.total=pageUserList.getTotal();
		this.pages=pageUserList.getPages();
		this.list=datal;
		this.firstPage=pageUserList.getFirstPage();
		this.prePage=pageUserList.getPrePage();
		this.nextPage=pageUserList.getNextPage();
		this.lastPage=pageUserList.getLastPage();
		this.isFirstPage=pageUserList.isFirstPage();
		this.isLastPage=pageUserList.isLastPage();
		this.hasPreviousPage=pageUserList.isHasPreviousPage();
		this.hasNextPage=pageUserList.isHasNextPage();
		this.navigatePage=pageUserList.getNavigatePages();
		this.navigatepageNums=pageUserList.getNavigatepageNums();
		
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public List<OrderData> getList() {
		return list;
	}

	public void setList(List<OrderData> list) {
		this.list = list;
	}

	public int getFirstPage() {
		return firstPage;
	}

	public void setFirstPage(int firstPage) {
		this.firstPage = firstPage;
	}

	public int getPrePage() {
		return prePage;
	}

	public void setPrePage(int prePage) {
		this.prePage = prePage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public void setNextPage(int nextPage) {
		this.nextPage = nextPage;
	}

	public int getLastPage() {
		return lastPage;
	}

	public void setLastPage(int lastPage) {
		this.lastPage = lastPage;
	}

	public boolean isFirstPage() {
		return isFirstPage;
	}

	public void setFirstPage(boolean isFirstPage) {
		this.isFirstPage = isFirstPage;
	}

	public boolean isLastPage() {
		return isLastPage;
	}

	public void setLastPage(boolean isLastPage) {
		this.isLastPage = isLastPage;
	}

	public boolean isHasPreviousPage() {
		return hasPreviousPage;
	}

	public void setHasPreviousPage(boolean hasPreviousPage) {
		this.hasPreviousPage = hasPreviousPage;
	}

	public boolean isHasNextPage() {
		return hasNextPage;
	}

	public void setHasNextPage(boolean hasNextPage) {
		this.hasNextPage = hasNextPage;
	}

	public int getNavigatePage() {
		return navigatePage;
	}

	public void setNavigatePage(int navigatePage) {
		this.navigatePage = navigatePage;
	}

	public List<Integer> getNavigatepageNums() {
		return navigatepageNums;
	}

	public void setNavigatepageNums(List<Integer> navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}
	
	
	
}
