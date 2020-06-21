package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.response.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize, String username);//根据显示页面大小选取某页上的产品
	MessageResponse<Object> QueryProductByName(int pageNum, int pageSize, String productName, String username);//根据商品名查找
	MessageResponse<Object> QueryProductById(int pageNum, int pageSize, int productId, String username);//根据商品id查找
	MessageResponse<Object> QueryProductById(int productId, String username);//查询该id的物品详情
	MessageResponse<Object> SetProductStatus(int productId, int status, String username);//产品上下架
}
