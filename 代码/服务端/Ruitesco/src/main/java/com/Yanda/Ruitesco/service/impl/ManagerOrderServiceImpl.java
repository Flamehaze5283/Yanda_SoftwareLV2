package com.Yanda.Ruitesco.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.dao.IManagerOrderDao;
import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.dao.impl.ManagerOrderDaoImpl;
import com.Yanda.Ruitesco.dao.impl.UserDaoImpl;
import com.Yanda.Ruitesco.dataResp.type.OrderData;
import com.Yanda.Ruitesco.javabean.Order;
import com.Yanda.Ruitesco.javabean.PageUserList;
import com.Yanda.Ruitesco.service.IManagerOrderService;

public class ManagerOrderServiceImpl implements IManagerOrderService{
	IManagerOrderDao manager_order_dao= new ManagerOrderDaoImpl();
	@Override
	public List<OrderData> GetOrderList(int startRow, int endRow) {
		return manager_order_dao.getOrderList(startRow, endRow);
	}

	public PageUserList GetNextPage(PageUserList pageUserList)
	{

		int pageNum=pageUserList.getPageNum();
		int pageSize=pageUserList.getPageSize();
		String orderBy=pageUserList.getOrderBy();
		int startRow=pageSize*(pageNum-1);
		int endRow=pageSize*pageNum-1;
		System.out.println(startRow);
		System.out.println(endRow);
		int total=manager_order_dao.getNumOfAllOrder();
		int size=0;
		if(endRow>total)
			size=total-startRow;
		else
			size=pageSize;
		int pages=total/pageSize;
		if(total%pageSize!= 0)	++pages;
		int firstPage=1;
		int lastPage=pages;
		int prePage=pageNum-1;
		if(prePage<1)	prePage=0;
		int nextPage=pageNum+1;
		if(nextPage>pages)	nextPage=0;
		boolean isFirstPage=true;
		boolean isLastPage=true;
		boolean hasPreviousPage=true;
		boolean hasNextPage=true;
		if(pageNum!=1)	isFirstPage=false;
		if(pageNum!=pages) isLastPage=false;
		if(isFirstPage) hasPreviousPage=false;
		if(isLastPage) hasNextPage=false;
		int navigatePages=pageUserList.getNavigatePages();
		List<Integer> navigatepageNums=new ArrayList<Integer>();
		int count=pageNum/navigatePages;
		if(pageNum%navigatePages==0)
			--count;
		if((count+1)*navigatePages>pages)
		{
			for(int i=count*navigatePages;i<pages;i++)
				navigatepageNums.add(i+1);
		}
		else
		{
			for(int i=count*navigatePages;i<count*navigatePages+navigatePages;i++)
				navigatepageNums.add(i+1);
		}
		
		PageUserList result=new PageUserList();
		result.setPageNum(pageNum);
		result.setPageSize(pageSize);
		result.setSize(size);
		result.setOrderBy(orderBy);
		result.setStartRow(startRow);
		result.setEndRow(endRow);
		result.setTotal(total);
		result.setPages(pages);
		result.setFirstPage(firstPage);
		result.setPrePage(prePage);
		result.setNextPage(nextPage);
		result.setLastPage(lastPage);
		result.setFirstPage(isFirstPage);
		result.setLastPage(isLastPage);
		result.setHasPreviousPage(hasPreviousPage);
		result.setHasNextPage(hasNextPage);
		result.setNavigatePages(navigatePages);
		result.setNavigatepageNums(navigatepageNums);
		
		return result;
		
	}

	@Override
	public List<OrderData> GetOrderListByUserName(String username,int startRow,int endRow) {
		return manager_order_dao.getOrderByUserName(username,startRow,endRow);
	}

	@Override
	public OrderData GetOrder(int orderNo) {
		return manager_order_dao.getOrder(orderNo);
	}

	@Override
	public boolean SendGoods(int orderNo) {
		return manager_order_dao.sendGoods(orderNo);
	}

}
