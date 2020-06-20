package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize);//根据显示页面大小选取某页上的产品
	
}
