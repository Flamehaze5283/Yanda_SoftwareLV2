package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.response.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize);//������ʾҳ���Сѡȡĳҳ�ϵĲ�Ʒ
	
}
