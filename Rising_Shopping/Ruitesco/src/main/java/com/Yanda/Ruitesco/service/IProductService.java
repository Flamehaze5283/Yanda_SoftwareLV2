package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.response.MessageResponse;

public interface IProductService {
	MessageResponse<Object> GetProductInPage(int pageNum, int pageSize, String username);//������ʾҳ���Сѡȡĳҳ�ϵĲ�Ʒ
	MessageResponse<Object> QueryProductByName(int pageNum, int pageSize, String productName, String username);//������Ʒ������
	MessageResponse<Object> QueryProductById(int pageNum, int pageSize, int productId, String username);//������Ʒid����
	MessageResponse<Object> QueryProductById(int productId, String username);//��ѯ��id����Ʒ����
	MessageResponse<Object> SetProductStatus(int productId, int status, String username);//��Ʒ���¼�
}
