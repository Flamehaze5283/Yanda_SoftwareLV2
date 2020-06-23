package com.Yanda.Ruitesco.dataResp.type;

public class CartRemoveDataT {
	private int productId;
	
	public CartRemoveDataT(int product_id)
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
