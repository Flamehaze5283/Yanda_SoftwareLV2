package com.Yanda.Ruitesco.javabean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class Order {
	int id;
	int order_no;
	int user_id;
	int shipping_id;
	BigDecimal payment;			//ʵ�ʸ�����
	int payment_type;			//���ʽ(֧������΢�š���������)
	BigDecimal postage;			//�ʷ�
	int status;					//״̬(δ���� = 1����ȡ��  = 0���Ѹ��� = 2���ѷ��� = 3�����׳ɹ� = 4�����׹ر� = 5)
	String comment;				//��ע
	Timestamp payment_time;		//����ʱ��
	Timestamp send_time;		//����ʱ��
	Timestamp end_time;			//����ʱ��
	Timestamp close_time;		//�ر�ʱ��
	Timestamp create_time;
	Timestamp update_time;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrder_no() {
		return order_no;
	}

	public void setOrder_no(int order_no) {
		this.order_no = order_no;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public int getShipping_id() {
		return shipping_id;
	}

	public void setShipping_id(int shipping_id) {
		this.shipping_id = shipping_id;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public int getPayment_type() {
		return payment_type;
	}

	public void setPayment_type(int payment_type) {
		this.payment_type = payment_type;
	}

	public BigDecimal getPostage() {
		return postage;
	}

	public void setPostage(BigDecimal postage) {
		this.postage = postage;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Timestamp getPayment_time() {
		return payment_time;
	}

	public void setPayment_time(Timestamp payment_time) {
		this.payment_time = payment_time;
	}

	public Timestamp getSend_time() {
		return send_time;
	}

	public void setSend_time(Timestamp send_time) {
		this.send_time = send_time;
	}

	public Timestamp getEnd_time() {
		return end_time;
	}

	public void setEnd_time(Timestamp end_time) {
		this.end_time = end_time;
	}

	public Timestamp getClose_time() {
		return close_time;
	}

	public void setClose_time(Timestamp close_time) {
		this.close_time = close_time;
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

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
