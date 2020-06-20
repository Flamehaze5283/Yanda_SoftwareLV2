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
		int startRow = pageNum * (pageSize - 1) + 1;
		page.setStartRow(startRow);
		int endRow = (pageNum * pageSize <= total) ? (pageNum * pageSize) : total;
		page.setEndRow(endRow);
		page.setTotal(total);
		int pages = (total % pageSize == 0) ? (total / pageSize) : (total / pageSize + 1);
		page.setPages(pages);
		List<Integer> navigatepageNums = new ArrayList<Integer>();
		for(int i = 1; i <= pageNum; i++) {
			if(navigatepageNums.size() < page.getNavigatePages()) {
				temp_list = page.getNacigatepageNums();
				if(i == 1)
					temp_list.set(0, i);
				else
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
		page.setNacigatepageNums(navigatepageNums);
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
	public static void main(String[] args) {
		Page page = CalculatePage(19, 15, 300);
		Gson gson = new Gson();
		String json = gson.toJson(page);
		System.out.println(json);
	}
}
//	public static void main(String[] args) {
//		System.out.println("*************");
//		JdbcUtil.executeUpdate("insert into user(name,age) values(?,?)","¶¹°ü",10);
//		List<User> userList = JdbcUtil.executeQuery("select id,name from user",User.class);
//		for(User u : userList)
//		{
//			System.out.println(u.toString());
//		}
//		System.out.println("id:3"+JdbcUtil.getById("user", User.class, 3));
//		System.out.println("+++++++++++");
//	}
//	
//}
