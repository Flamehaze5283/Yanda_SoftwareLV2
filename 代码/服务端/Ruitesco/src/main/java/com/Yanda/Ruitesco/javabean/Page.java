package com.Yanda.Ruitesco.javabean;

import java.util.ArrayList;
import java.util.List;

public class Page {
	int pageNum = 1;//��ǰҳ�룬Ĭ��ֵΪ1
	int pageSize = 10;//��ǰÿҳ����ʾ������Ĭ��ֵΪ10
	int size = 8;//��ҳʵ����ʾ����Ŀ��
	String orderBy = null;//��Ʒ���з�ʽ
	int startRow;//�����п�ʼ��ʾ���±�
	int endRow;//�����н�����ʾ���±�
	int total;//����Ԫ������
	int pages;//��ҳ��
	int firstPage;//��������ʾ�ĵ�һ��ҳ��
	int prePage;//��һҳҳ�룬����������Ϊ0
	int nextPage;//��һҳҳ�룬����������Ϊ0
	int lastPage;//���һҳҳ��
	boolean isFirstPage;//��ǰҳ�Ƿ�Ϊ��ҳ
	boolean isLastPage;//��ǰҳ�Ƿ�Ϊβҳ
	boolean hasPreviousPage;//�Ƿ�����һҳ
	boolean hasNextPage;//�Ƿ���һҳ
	int navigatePages = 6;//��������ʾ���ҳ����
	List<Integer> navigatepageNums;//��������ǰ��ʾҳ������
	public Page() {
		// TODO Auto-generated constructor stub
		this.navigatepageNums = new ArrayList<Integer>();
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
	public int getNavigatePages() {
		return navigatePages;
	}
	public void setNavigatePages(int navigatePages) {
		this.navigatePages = navigatePages;
	}
	public List<Integer> getNavigatepageNums() {
		return navigatepageNums;
	}
	public void setNavigatepageNums(List<Integer> nacigatepageNums) {
		this.navigatepageNums = nacigatepageNums;
	}
	
	public void CalculatePage(int total) {
		int startRow = (this.pageNum - 1) * this.pageSize + 1;
		this.startRow = startRow;
		int endRow = (this.pageNum * this.pageSize <= total) ? (this.pageNum * this.pageSize) : total;
		this.endRow = endRow;
		int size = this.endRow - this.startRow + 1;
		this.size = size;
		this.total = total;
		int pages = (this.total % this.pageSize == 0) ? (this.total / this.pageSize) : (this.total / this.pageSize + 1);
		this.pages = pages;
		List<Integer> navigatepageNums = new ArrayList<Integer>();
		for(int i = 1; i <= this.pageNum; i++) {
			if(navigatepageNums.size() < this.navigatePages) {
				navigatepageNums.add(i);
			}
			else {
				if(navigatepageNums.get(navigatepageNums.size() - 1) >= this.pages)
					break;
				else {
					while(navigatepageNums.get(navigatepageNums.size() - 1) < this.pages)
					{
						if(navigatepageNums.get(navigatepageNums.size() - 1) - i < this.navigatePages / 2) {
							for(int j = 0; j < navigatepageNums.size(); j++)
								navigatepageNums.set(j, navigatepageNums.get(j) + 1);
						}
						else
							break;
					}
				}
			}		
		}
		this.navigatepageNums = navigatepageNums;
		int firstPage = this.navigatepageNums.get(0);
		this.firstPage = firstPage;
		int prePage = pageNum - 1;
		this.prePage = prePage;
		int nextPage = (pageNum + 1 <= pages) ? (pageNum + 1) : 0;
		this.nextPage = nextPage;
		int lastPage = navigatepageNums.get(navigatepageNums.size() - 1);
		this.lastPage = lastPage;
		boolean isFirstPage = (pageNum == 1) ? true : false;
		this.isFirstPage = isFirstPage;
		boolean isLastPage = (pageNum == total) ? true : false;
		this.isLastPage = isLastPage;
		boolean hasPreviousPage = !isFirstPage;
		this.hasPreviousPage = hasPreviousPage;
		boolean hasNextPage = !isLastPage;
		this.hasNextPage = hasNextPage;
	}
	
	public void CalculatePage(int pageNum, int pageSize, int total) {
		int startRow = (this.pageNum - 1) * this.pageSize + 1;
		this.startRow = startRow;
		int endRow = (this.pageNum * this.pageSize <= total) ? (this.pageNum * this.pageSize) : total;
		this.endRow = endRow;
		int size = this.endRow - this.startRow + 1;
		this.size = size;
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.total = total;
		int pages = (this.total % this.pageSize == 0) ? (this.total / this.pageSize) : (this.total / this.pageSize + 1);
		this.pages = pages;
		List<Integer> navigatepageNums = new ArrayList<Integer>();
		for(int i = 1; i <= this.pageNum; i++) {
			if(navigatepageNums.size() < this.navigatePages) {
				navigatepageNums.add(i);
			}
			else {
				if(navigatepageNums.get(navigatepageNums.size() - 1) >= this.pages)
					break;
				else {
					while(navigatepageNums.get(navigatepageNums.size() - 1) < this.pages)
					{
						if(navigatepageNums.get(navigatepageNums.size() - 1) - i < this.navigatePages / 2) {
							for(int j = 0; j < navigatepageNums.size(); j++)
								navigatepageNums.set(j, navigatepageNums.get(j) + 1);
						}
						else
							break;
					}
				}
			}		
		}
		this.navigatepageNums = navigatepageNums;
		int firstPage = this.navigatepageNums.get(0);
		this.firstPage = firstPage;
		int prePage = pageNum - 1;
		this.prePage = prePage;
		int nextPage = (pageNum + 1 <= pages) ? (pageNum + 1) : 0;
		this.nextPage = nextPage;
		int lastPage = navigatepageNums.get(navigatepageNums.size() - 1);
		this.lastPage = lastPage;
		boolean isFirstPage = (pageNum == 1) ? true : false;
		this.isFirstPage = isFirstPage;
		boolean isLastPage = (pageNum == total) ? true : false;
		this.isLastPage = isLastPage;
		boolean hasPreviousPage = !isFirstPage;
		this.hasPreviousPage = hasPreviousPage;
		boolean hasNextPage = !isLastPage;
		this.hasNextPage = hasNextPage;
	}

}