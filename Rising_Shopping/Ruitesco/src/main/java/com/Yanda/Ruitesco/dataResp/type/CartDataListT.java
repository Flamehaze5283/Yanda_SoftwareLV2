package com.Yanda.Ruitesco.dataResp.type;

import java.util.List;

public class CartDataListT {
	private List<CartData> cartProductVolist;

	public CartDataListT(List<CartData> cartdatal)
	{
		cartProductVolist=cartdatal;
	}
	public List<CartData> getList() {
		return cartProductVolist;
	}
	public void setList(List<CartData> list) {
		this.cartProductVolist = list;
	}
	
}
