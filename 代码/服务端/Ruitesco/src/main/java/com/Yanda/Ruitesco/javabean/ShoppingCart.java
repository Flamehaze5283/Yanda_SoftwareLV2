package com.Yanda.Ruitesco.javabean;

import java.sql.Timestamp;
import java.util.Date;

public class ShoppingCart {
	private int id;					//���ﳵid
	private int user_id;			//�û�id(Ϊ�û�id�������)
	private int product_id;			//��Ʒid
	private int quantity;			//����Ʒ��������
	private int checked;		//����Ʒ�ڹ��ﳵ���Ƿ�ѡ��
	private Timestamp create_time;	//����Ŀ����ʱ��(��Ҫ�ڴ���ʱ�������)
	private Timestamp update_time;	//����Ŀ����ʱ��(�����ݿ��Զ�����)
	
	public ShoppingCart()
	{
		
	}
	public ShoppingCart(int u_id,int p_id,int count)
	{
		id=-1;
		user_id=u_id;
		product_id=p_id;
		quantity=count;
		checked=1;
		Date date = new Date();
		create_time = new Timestamp(date.getTime());
		update_time = create_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getProduct_id() {
		return product_id;
	}
	public void setProduct_id(int product_id) {
		this.product_id = product_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
	public Timestamp getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Timestamp create_time) {
		this.create_time = create_time;
	}
	public Timestamp getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Timestamp update_time) {
		this.update_time = update_time;
	}
	
	
}
