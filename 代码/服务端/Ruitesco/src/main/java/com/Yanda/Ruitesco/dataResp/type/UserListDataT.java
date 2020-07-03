package com.Yanda.Ruitesco.dataResp.type;

import java.util.List;

public class UserListDataT {
	private int pageNum;
	private int pageSize;
	private int size;
	private int orderBy;
	private int startRow;
	private int endRow;
	private int total;
	private int pages;
	private List<UserData> userl;
	private int firstPage;
	private int prePage;
	private int nextPage;
	private int lastPage;
	private boolean isFirstPage;
	private boolean isLastPage;
	private boolean hasPreviousPage;
	private boolean hasNextPage;
	private int navigatePage;
	private int navigatepageNums[];
	
	
	
	
	public UserListDataT(int pageNum, int pageSize, int size, int orderBy, int startRow, int endRow, int total,
			int pages, List<UserData> userl, int firstPage, int prePage, int nextPage, int lastPage,
			boolean isFirstPage, boolean isLastPage, boolean hasPreviousPage, boolean hasNextPage, int navigatePage,
			int[] navigatepageNums) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.size = size;
		this.orderBy = orderBy;
		this.startRow = startRow;
		this.endRow = endRow;
		this.total = total;
		this.pages = pages;
		this.userl = userl;
		this.firstPage = firstPage;
		this.prePage = prePage;
		this.nextPage = nextPage;
		this.lastPage = lastPage;
		this.isFirstPage = isFirstPage;
		this.isLastPage = isLastPage;
		this.hasPreviousPage = hasPreviousPage;
		this.hasNextPage = hasNextPage;
		this.navigatePage = navigatePage;
		this.navigatepageNums = navigatepageNums;
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
	public int getOrderBy() {
		return orderBy;
	}
	public void setOrderBy(int orderBy) {
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
	public List<UserData> getUserl() {
		return userl;
	}
	public void setUserl(List<UserData> userl) {
		this.userl = userl;
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
	public int[] getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(int[] navigatepageNums) {
		this.navigatepageNums = navigatepageNums;
	}
	
	
}
