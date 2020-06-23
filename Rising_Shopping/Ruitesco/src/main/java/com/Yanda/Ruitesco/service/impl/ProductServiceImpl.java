package com.Yanda.Ruitesco.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.Yanda.Ruitesco.dao.ICategoryDao;
import com.Yanda.Ruitesco.dao.IProductDao;
import com.Yanda.Ruitesco.dao.IUserDao;
import com.Yanda.Ruitesco.dao.impl.CategoryDaoImpl;
import com.Yanda.Ruitesco.dao.impl.ProductDaoImpl;
import com.Yanda.Ruitesco.dao.impl.UserDaoImpl;
import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.Product;
import com.Yanda.Ruitesco.service.IProductService;
import com.Yanda.Ruitesco.utils.response.MessageResponse;
import com.Yanda.Ruitesco.utils.response.responsetype.product.ProductData;
import com.Yanda.Ruitesco.utils.response.responsetype.product.ProductDetail;

public class ProductServiceImpl implements IProductService {
	IProductDao product_dao = new ProductDaoImpl();
	ICategoryDao category_dao = new CategoryDaoImpl();
	IUserDao user_dao = new UserDaoImpl();
	@Override
	public MessageResponse<Object> GetProductInPage(int pageNum, int pageSize, String username) {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			List<Product> list_product = product_dao.GetProductByName();//��ȡ��Ʒ�б�
			if(list_product == null) {
				messageResponse.setStatus(1);
				messageResponse.setMsg("û����Ʒ");
			}
			else {
				int total = list_product.size();//ͳ����Ʒ����
				Page page = CalculatePage(pageNum, pageSize, total);//����ҳ�������
				int startRow = page.getStartRow();//��ʼ�±�
				int endRow = page.getEndRow();//��ֹ�±�
				List<Product> result = new ArrayList<Product>();//��ʼ��ҳ������ʾ����Ʒ����
				for(int row = startRow; row <= endRow; row++) {
					result.add(list_product.get(row - 1));
				}
				ProductData productData = new ProductData(page, result);
				messageResponse.setStatus(0);
				messageResponse.setMsg(productData);
			}		
		}
		return messageResponse;
	}
	
	@Override
	public MessageResponse<Object> QueryProductByName(int pageNum, int pageSize, String productName, String username) {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			List<Product> list_product = product_dao.GetProductByName(productName);//��ȡ��Ʒ�б�
			if(list_product == null) {
				messageResponse.setStatus(1);
				messageResponse.setMsg("û�и���Ʒ");
			}
			else {
				int total = list_product.size();//ͳ����Ʒ����
				Page page = new Page();
				page = CalculatePage(pageNum, pageSize, total);//����ҳ�������
				int startRow = page.getStartRow();//��ʼ�±�
				int endRow = page.getEndRow();//��ֹ�±�
				List<Product> result = new ArrayList<Product>();//��ʼ��ҳ������ʾ����Ʒ����
				for(int row = startRow; row <= endRow; row++) {
					result.add(list_product.get(row - 1));
				}
				ProductData productData = new ProductData(page, result);
				messageResponse.setStatus(0);
				messageResponse.setMsg(productData);
			}		
		}
		return messageResponse;
	}

	@Override
	public MessageResponse<Object> QueryProductById(int pageNum, int pageSize, int productId, String username) {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			List<Product> list_product = product_dao.GetProductById(productId);//��ȡ��Ʒ�б�
			if(list_product == null) {
				messageResponse.setStatus(1);
				messageResponse.setMsg("û�и���Ʒ");
			}
			else {
				int total = list_product.size();//ͳ����Ʒ����
				Page page = new Page();
				page = CalculatePage(pageNum, pageSize, total);//����ҳ�������
				int startRow = page.getStartRow();//��ʼ�±�
				int endRow = page.getEndRow();//��ֹ�±�
				List<Product> result = new ArrayList<Product>();//��ʼ��ҳ������ʾ����Ʒ����
				for(int row = startRow; row <= endRow; row++) {
					result.add(list_product.get(row - 1));
				}
				ProductData productData = new ProductData(page, result);
				messageResponse.setStatus(0);
				messageResponse.setMsg(productData);
			}
		}
		return messageResponse;
	}
	
	@Override
	public MessageResponse<Object> QueryProductById(int productId, String username) {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			String user_role = user_dao.getUserByName(username).getRole();
			if(!user_role.equals("����Ա"))
			{
				messageResponse.setStatus(1);
				messageResponse.setMsg("û��Ȩ��");
			}
			else {
				Product product = product_dao.GetProductById(productId).get(0);
				int parentCategoryId = category_dao.QueryCategory(product.getCategory_id()).getParent_id();
				ProductDetail result = new ProductDetail();
				result.setId(product.getId());
				result.setCategoryId(product.getCategory_id());
				result.setParentCategoryId(parentCategoryId);
				result.setName(product.getName());
				result.setSubtitle(product.getSubtitle());
				result.setMainImage(product.getMainImage());
				result.setSubImages(product.getSubImage());
				result.setDetail(product.getDetail());
				result.setPrice(product.getPrice());
				result.setStock(product.getStock());
				result.setStatus(product.getStatus());
				result.setCreateTime(product.getCreate_time());
				result.setUpdateTime(product.getUpdate_time());
				messageResponse.setStatus(0);
				messageResponse.setMsg(result);
			}
		}
		return messageResponse;
	}
	@Override
	public MessageResponse<Object> SetProductStatus(int productId, int status, String username) {
		// TODO Auto-generated method stub
		MessageResponse<Object> messageResponse = new MessageResponse<Object>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			String user_role = user_dao.getUserByName(username).getRole();
			if(!user_role.equals("����Ա"))
			{
				messageResponse.setStatus(1);
				messageResponse.setMsg("û��Ȩ��");
			}
			else {
				int update_number = product_dao.UpdateProductStatus(productId, status);
				if(update_number > 0) {
					messageResponse.setStatus(0);
					messageResponse.setMsg("�޸Ĳ�Ʒ״̬�ɹ�");
				}
				else {
					messageResponse.setStatus(1);
					messageResponse.setMsg("�޸Ĳ�Ʒ״̬ʧ��");
				}
			}
		}
		return messageResponse;
	}

	@Override
	public MessageResponse<String> InsertProduct(int categoryId, String name, String subtitle, String mainImages,
			String subImages, String detail, BigDecimal price, int stock, int status, String username) {
		MessageResponse<String> messageResponse = new MessageResponse<String>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			String user_role = user_dao.getUserByName(username).getRole();
			if(!user_role.equals("����Ա"))
			{
				messageResponse.setStatus(1);
				messageResponse.setMsg("û��Ȩ��");
			}
			else {
				Product product = new Product();
				product.setCategory_id(categoryId);
				product.setName(name);
				product.setSubtitle(subtitle);
				product.setMainImage(mainImages);
				product.setSubImage(subImages);
				product.setDetail(detail);
				product.setPrice(price);
				product.setStock(stock);
				product.setStatus(status);
				int insert = product_dao.InsertProduct(product);
				if(insert <= 0) {
					messageResponse.setStatus(1);
					messageResponse.setMsg("������Ʒʧ��");
				}
				else {
					messageResponse.setStatus(0);
					messageResponse.setMsg("������Ʒ�ɹ�");
				}
			}
		}
		return messageResponse;
	}

	@Override
	public MessageResponse<String> UpdateProduct(int id, int categoryId, String name, String subtitle,
			String mainImages, String subImages, String detail, BigDecimal price, int stock, int status,
			String username) {
		// TODO Auto-generated method stub
		MessageResponse<String> messageResponse = new MessageResponse<String>();//��ʼ��������ض���
		//���δ��¼������10��msg
		if(username == null || username.equals("")) {
			messageResponse.setStatus(10);
			messageResponse.setMsg("�û�δ��¼");
		}
		else {
			String user_role = user_dao.getUserByName(username).getRole();
			if(!user_role.equals("����Ա"))
			{
				messageResponse.setStatus(1);
				messageResponse.setMsg("û��Ȩ��");
			}
			else {
				Product product = new Product();
				product.setCategory_id(categoryId);
				product.setName(name);
				product.setSubtitle(subtitle);
				product.setMainImage(mainImages);
				product.setSubImage(subImages);
				product.setDetail(detail);
				product.setPrice(price);
				product.setStock(stock);
				product.setStatus(status);
				int update = product_dao.UpdateProduct(product, id);
				if(update <= 0) {
					messageResponse.setStatus(1);
					messageResponse.setMsg("���²�Ʒʧ��");
				}
				else {
					messageResponse.setStatus(0);
					messageResponse.setMsg("���²�Ʒ�ɹ�");
				}
			}
		}
		return messageResponse;
	}
	
	//�����ҳ��̬����
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
}
