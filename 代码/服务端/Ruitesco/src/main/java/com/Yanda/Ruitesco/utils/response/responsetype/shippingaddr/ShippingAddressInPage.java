package com.Yanda.Ruitesco.utils.response.responsetype.shippingaddr;

import java.util.List;

import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.ShippingAddr;

public class ShippingAddressInPage {
	int pageNum;
	int pageSize;
	int size;
	String orderBy = null;
	int startRow;
	int endRow;
	int total;
	int pages;
	List<ShippingAddr> list;
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
	
	public ShippingAddressInPage(List<ShippingAddr> result, Page page) {
		// TODO Auto-generated constructor stub
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.size = page.getSize();
		this.orderBy = page.getOrderBy();
		this.startRow = page.getStartRow();
		this.endRow = page.getEndRow();
		this.total = page.getTotal();
		this.pages = page.getPages();
		this.list = result;
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
