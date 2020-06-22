package com.Yanda.Ruitesco.service.impl;

import com.Yanda.Ruitesco.dao.IShoppingCartDao;
import com.Yanda.Ruitesco.dao.impl.ShoppingCartDaoImpl;
import com.Yanda.Ruitesco.service.IShoppingCartService;

public class ShoppingCartServiceImpl implements IShoppingCartService {
	IShoppingCartDao cart_dao = new ShoppingCartDaoImpl();
}
