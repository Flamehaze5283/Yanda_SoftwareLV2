package com.Yanda.Ruitesco.dataResp.type;

public class CartUpdateDataT {
	private int productId;
	private int quantity;
	
	
	
	public CartUpdateDataT(int product_id,int product_quantity)
	{
		productId=product_id;
		quantity=product_quantity;
	}



	public int getProductId() {
		return productId;
	}



	public void setProductId(int productId) {
		this.productId = productId;
	}



	public int getQuantity() {
		return quantity;
	}



	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
