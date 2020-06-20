package com.Yanda.Ruitesco.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.Yanda.Ruitesco.dao.IProductDao;
import com.Yanda.Ruitesco.dao.impl.ProductDaoImpl;
import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.Product;
import com.Yanda.Ruitesco.service.IProductService;
import com.Yanda.Ruitesco.utils.response.MessageResponse;

public class ProductServiceImpl implements IProductService {
	IProductDao product_dao = new ProductDaoImpl();
	
	@Override
	public MessageResponse<Object> GetProductInPage(int pageNum, int pageSize) {
		// TODO Auto-generated method stub
		List<Product> list_product = product_dao.GetProductByName();
		int total = list_product.size();
		Page page = CalculatePage(pageNum, pageSize, total);
		
		return null;
	}
	
	public static Page CalculatePage(int pageNum, int pageSize, int total) {
		List<Integer> temp_list = new ArrayList<Integer>();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		int startRow = pageNum * (pageSize - 1) + 1;
		page.setStartRow(startRow);
		int endRow = (pageNum * pageSize <= total) ? (pageNum * pageSize) : total;
		page.setEndRow(endRow);
		page.setTotal(total);
		int pages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
		page.setPages(pages);
		List<Integer> nacigatepageNums = new ArrayList<Integer>();
		for(int i = 1; i <= pages; i++) {
			if(nacigatepageNums.size() < page.getNavigatePages()) {
				temp_list = page.getNacigatepageNums();
				temp_list.add(i);
				page.setNacigatepageNums(temp_list);
			}
			else {
				if(nacigatepageNums.get(page.getNavigatePages() - 1) >= total)
					break;
				else {
					for(int j = 0; j < page.getNavigatePages(); j++) {
						nacigatepageNums.set(j, nacigatepageNums.get(j) + 1);
					}
				}
			}		
		}
		page.setNacigatepageNums(nacigatepageNums);
		int firstPage = nacigatepageNums.get(0);
		page.setFirstPage(firstPage);
		int prePage = pageNum - 1;
		page.setPrePage(prePage);
		int nextPage = (pageNum + 1 > pages) ? (pageNum + 1) : 0;
		page.setNextPage(nextPage);
		int lastPage = nacigatepageNums.get(page.getNavigatePages());
		page.setLastPage(lastPage);
		boolean isFirstPage = (pageNum == 1) ? true : false;
		page.setFirstPage(isFirstPage);
		boolean isLastPage = (pageNum == total) ? true : false;
		page.setLastPage(isLastPage);
		boolean hasPreviousPage = !isFirstPage;
		page.setHasPreviousPage(hasPreviousPage);
		boolean hasNextPage = !isLastPage;
		page.setHasNextPage(hasNextPage);
		return page;
	}
}
