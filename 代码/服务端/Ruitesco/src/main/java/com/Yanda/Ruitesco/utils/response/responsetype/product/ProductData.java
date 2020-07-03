package com.Yanda.Ruitesco.utils.response.responsetype.product;

import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.Product;

public class ProductData {
	int pageNum;
	int pageSize;
	int size;
	String orderBy;
	int startRow;
	int endRow;
	int total;
	int pages;
	List<MessageProductType> list;
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
	
	public ProductData(Page page, List<Product> result) {
		// TODO Auto-generated constructor stub
		this.pageNum = page.getPageNum();
		this.pageSize = page.getPageSize();
		this.size = page.getSize();
		this.orderBy = page.getOrderBy();
		this.startRow = page.getStartRow();
		this.endRow = page.getEndRow();
		this.total = page.getTotal();
		this.pages = page.getPages();
		List<MessageProductType> list = new ArrayList<MessageProductType>();
		for(int i = 0; i < result.size(); i++)
		{
			Product product = result.get(i);
			MessageProductType messageProductType = new MessageProductType();
			messageProductType.setId(product.getId());
			messageProductType.setCategory_id(product.getCategory_id());
			messageProductType.setName(product.getName());
			messageProductType.setSubtitle(product.getSubtitle());
			messageProductType.setMainImage(product.getMainImage());
			messageProductType.setStatus(product.getStatus());
			messageProductType.setPrice(product.getPrice());
			list.add(messageProductType);
		}
		this.list = list;
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
