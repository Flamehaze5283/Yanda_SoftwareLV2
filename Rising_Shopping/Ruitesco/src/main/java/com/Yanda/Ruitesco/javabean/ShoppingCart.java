package com.Yanda.Ruitesco.javabean;

import java.sql.Timestamp;

public class ShoppingCart {
	int id;					//���ﳵid
	int user_id;			//�û�id(Ϊ�û�id�������)
	int product_id;			//��Ʒid
	int quantity;			//����Ʒ��������
	boolean checked;		//����Ʒ�ڹ��ﳵ���Ƿ�ѡ��
	Timestamp create_time;	//����Ŀ����ʱ��(��Ҫ�ڴ���ʱ�������)
	Timestamp update_time;	//����Ŀ����ʱ��(�����ݿ��Զ�����)
}
