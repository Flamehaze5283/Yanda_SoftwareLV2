package com.Yanda.Ruitesco.service;

import java.math.BigDecimal;
import java.util.List;

import com.Yanda.Ruitesco.utils.response.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize, String username);//������ʾҳ���Сѡȡĳҳ�ϵĲ�Ʒ
	MessageResponse<Object> QueryProductByName(int pageNum, int pageSize, String productName, String username);//������Ʒ������
	MessageResponse<Object> QueryProductById(int pageNum, int pageSize, int productId, String username);//������Ʒid����
	MessageResponse<Object> QueryProductById(int productId, String username);//��ѯ��id����Ʒ����
	MessageResponse<Object> SetProductStatus(int productId, int status, String username);//��Ʒ���¼�
	MessageResponse<String> InsertProduct(int categoryId, String name , String subtitle, String mainImages, String subImages, String detail, BigDecimal price, int stock, int status, String username);//������Ʒ
	MessageResponse<String> UpdateProduct(int id, int categoryId, String name , String subtitle, String mainImages, String subImages, String detail, BigDecimal price, int stock, int status, String username);//������Ʒ
	List<String> getAllCategoryById(int category_id);//Ѱ�Ҹ���·��
}
