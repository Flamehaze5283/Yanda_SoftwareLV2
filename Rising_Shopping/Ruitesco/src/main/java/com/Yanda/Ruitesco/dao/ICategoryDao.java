package com.Yanda.Ruitesco.dao;

import com.Yanda.Ruitesco.utils.MessageResponse;

public interface ICategoryDao {
	public MessageResponse<Object> GetCategory(int id);	//����id��ȡ�����Ϣ
}
