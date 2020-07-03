package com.Yanda.Ruitesco.dataResp.type;

import java.util.List;

public class CartSelectDataListT {
	private List<CartSelectDataListT> cartProductVolist;

	public CartSelectDataListT(List<CartSelectDataListT> cartdatal)
	{
		cartProductVolist=cartdatal;
	}
	public List<CartSelectDataListT> getList() {
		return cartProductVolist;
	}
	public void setList(List<CartSelectDataListT> list) {
		this.cartProductVolist = list;
	}
	
}
