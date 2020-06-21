package com.Yanda.Ruitesco.utils;

import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.javabean.Page;
import com.google.gson.Gson;

public class Test {
	public static Page CalculatePage(int pageNum, int pageSize, int total) {
		List<Integer> temp_list = new ArrayList<Integer>();
		Page page = new Page();
		page.setPageNum(pageNum);
		page.setPageSize(pageSize);
		int startRow = (pageNum - 1) * pageSize + 1;
		page.setStartRow(startRow);
		int endRow = (pageNum * pageSize <= total) ? (pageNum * pageSize) : total;
		page.setEndRow(endRow);
		int size = endRow - startRow + 1;
		page.setSize(size);
		page.setTotal(total);
		int pages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
		page.setPages(pages);
		List<Integer> navigatepageNums = new ArrayList<Integer>();
		for(int i = 1; i <= pageNum; i++) {
			if(navigatepageNums.size() < page.getNavigatePages()) {
				temp_list = page.getNavigatepageNums();
				temp_list.add(i);
				navigatepageNums = temp_list;
			}
			else {
				if(navigatepageNums.get(navigatepageNums.size() - 1) >= pages)
					break;
				else {
					while(navigatepageNums.get(navigatepageNums.size() - 1) < pages)
					{
						if(navigatepageNums.get(navigatepageNums.size() - 1) - i < page.getNavigatePages() / 2) {
							for(int j = 0; j < navigatepageNums.size(); j++)
								navigatepageNums.set(j, navigatepageNums.get(j) + 1);
						}
						else
							break;
					}
				}
			}		
		}
		page.setNavigatepageNums(navigatepageNums);
		int firstPage = navigatepageNums.get(0);
		page.setFirstPage(firstPage);
		int prePage = pageNum - 1;
		page.setPrePage(prePage);
		int nextPage = (pageNum + 1 <= pages) ? (pageNum + 1) : 0;
		page.setNextPage(nextPage);
		int lastPage = navigatepageNums.get(navigatepageNums.size() - 1);
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
	public static List<String> getList(String...strings){
		List<String> list = new ArrayList<String>();
		int size = strings.length;
		System.out.println("strings.length = " + size);
		for(int i = 0; i < strings.length; i++) {
			list.add(strings[i]);
		}
		return list;
	}
	public static void main(String[] args) {
//		List<String> list = getList();
		Page page = CalculatePage(5, 6, 30);
		Gson gson = new Gson();
		String json = gson.toJson(page);
		System.out.println(json);
	}
}