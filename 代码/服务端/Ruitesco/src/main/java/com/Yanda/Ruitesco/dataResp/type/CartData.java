package com.Yanda.Ruitesco.dataResp.type;

import com.Yanda.Ruitesco.javabean.ShoppingCart;

public class CartData {
	private int id;
	private int quantity;
	private int checked;
	
	
	public CartData(int product_id,int product_quantity,int ischecked)
	{
		id=product_id;
		quantity=product_quantity;
		checked=ischecked;
	}
	public CartData(ShoppingCart data)
	{
		id=data.getProduct_id();
		quantity=data.getQuantity();
		checked=(data.getChecked());
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	
	
	
	
	
}
