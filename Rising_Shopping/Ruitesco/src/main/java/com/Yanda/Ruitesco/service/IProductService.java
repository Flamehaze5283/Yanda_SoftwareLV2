package com.Yanda.Ruitesco.service;

import java.math.BigDecimal;
import java.util.List;

import com.Yanda.Ruitesco.utils.response.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize, String username);//根据显示页面大小选取某页上的产品
	MessageResponse<Object> QueryProductByName(int pageNum, int pageSize, String productName, String username);//根据商品名查找
	MessageResponse<Object> QueryProductById(int pageNum, int pageSize, int productId, String username);//根据商品id查找
	MessageResponse<Object> QueryProductById(int productId, String username);//查询该id的物品详情
	MessageResponse<Object> SetProductStatus(int productId, int status, String username);//产品上下架
	MessageResponse<String> InsertProduct(int categoryId, String name , String subtitle, String mainImages, String subImages, String detail, BigDecimal price, int stock, int status, String username);//新增商品
	MessageResponse<String> UpdateProduct(int id, int categoryId, String name , String subtitle, String mainImages, String subImages, String detail, BigDecimal price, int stock, int status, String username);//更新商品
	List<String> getAllCategoryById(int category_id);//寻找该类路径
}
