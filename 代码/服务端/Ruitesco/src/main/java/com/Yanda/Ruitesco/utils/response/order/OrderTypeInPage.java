package com.Yanda.Ruitesco.utils.response.order;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Page;

public class OrderTypeInPage {
	int pageNum;
	int pageSize;
	int size;
	String orderBy = null;
	int startRow;
	int endRow;
	int total;
	int pages;
	List<OrderType> list;
	int firstPage;
	int prePage;
	int nextPage;
	int lastPage;
	boolean isFirstPage;
	boolean isLastPage;
	boolean hasPreviousPage;
	boolean hasNextPage;
	int navigatePages;
	List<Integer> navigatepageNums;
	
	public OrderTypeInPage(List<OrderType> orderTypes, Page page) {
		// TODO Auto-generated constructor stub
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.size = page.getSize();
		this.orderBy = page.getOrderBy();
		this.startRow = page.getStartRow();
		this.endRow = page.getEndRow();
		this.total = page.getTotal();
		this.pages = page.getPages();
		this.list = orderTypes;
		this.firstPage = page.getFirstPage();
		this.prePage = page.getPrePage();
		this.nextPage = page.getNextPage();
		this.lastPage = page.getLastPage();
		this.isFirstPage = page.isFirstPage();
		this.isLastPage = page.isLastPage();
		this.hasPreviousPage = page.isHasPreviousPage();
		this.hasNextPage = page.isHasNextPage();
		this.navigatePages = page.getNavigatePages();
		this.navigatepageNums = page.getNavigatepageNums();
	}
}
