package com.Yanda.Ruitesco.dataResp.type;

public class CartUnSelectDataT {
	private int productId;
	
	public CartUnSelectDataT(int product_id)
	{
		productId=product_id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}
	
}
