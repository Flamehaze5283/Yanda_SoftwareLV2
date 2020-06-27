package com.Yanda.Ruitesco.service.impl;

import java.util.List;

import com.Yanda.Ruitesco.dao.IShippingAddrDao;
import com.Yanda.Ruitesco.dao.impl.ShippingAddrDaoImpl;
import com.Yanda.Ruitesco.javabean.Page;
import com.Yanda.Ruitesco.javabean.ShippingAddr;
import com.Yanda.Ruitesco.service.IShippingAddrService;
import com.Yanda.Ruitesco.utils.response.MessageType;
import com.Yanda.Ruitesco.utils.response.responsetype.shippingaddr.AddressIdType;
import com.Yanda.Ruitesco.utils.response.responsetype.shippingaddr.ShippingAddressInPage;

public class ShippingAddrServiceImpl implements IShippingAddrService {
	IShippingAddrDao addr_dao = new ShippingAddrDaoImpl();
	
	@Override
	public MessageType<Object> addAddress(int user_id, String receiverName, String receiverPhone, String receiverMobile,
			String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		int result = addr_dao.InsertAddress(user_id, receiverName, receiverPhone, receiverMobile, receiverProvince, receiverDistrict, receiverCity, receiverAddress, receiverZip);
		if(result <= 0) {
			messageType = new MessageType<Object>(2, "添加失败", null);
		}
		else {
			int ship_id = addr_dao.QueryAddress(user_id, receiverName, receiverPhone, receiverMobile, receiverProvince, receiverCity, receiverAddress, receiverZip);
			if(ship_id == 0)
				messageType = new MessageType<Object>(3, "返回id失败", null);
			else {
				AddressIdType addr = new AddressIdType(ship_id);
				messageType = new MessageType<Object>(0, null, addr);
			}
		}
		return messageType;
	}

	@Override
	public MessageType<Object> deleteAddress(int shippingId) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		int result = addr_dao.DeleteAddress(shippingId);
		if(result <= 0) {
			messageType = new MessageType<Object>(2, "删除失败", null);
		}
		else {
			messageType = new MessageType<Object>(0, "删除成功", null);
		}
		return messageType;
	}

	@Override
	public MessageType<Object> updateAddress(int id, String name, String phone, String mobile, String province,
			String city, String district, String address, int zip) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		
		int result = addr_dao.UpdateAddress(id, name, phone, mobile, province, city, district, address, zip);
		if(result <= 0) {
			messageType = new MessageType<Object>(2, "修改失败", null);
		}
		else {
			messageType = new MessageType<Object>(0, "修改成功", null);
		}
		return messageType;
	}

	@Override
	public MessageType<Object> selectAddress(int id) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		ShippingAddr addr = new ShippingAddr();
		if(addr_dao.selectAddress(id) != null) {
			addr = addr_dao.selectAddress(id).get(0);
			messageType = new MessageType<Object>(0, "获取成功", addr);
		}
		else {
			messageType = new MessageType<Object>(0, "获取失败", null);
		}
		return messageType;
	}

	@Override
	public MessageType<Object> selectAddressByUserId(int userid) {
		// TODO Auto-generated method stub
		MessageType<Object> messageType;
		if(addr_dao.selectAddressByUserId(userid) != null)
		{
			List<ShippingAddr> result = addr_dao.selectAddressByUserId(userid);
			Page page = new Page();
			page.CalculatePage(result.size());
			ShippingAddressInPage finalResult = new ShippingAddressInPage(result, page);
			messageType = new MessageType<Object>(0, "获取成功", finalResult);
		}
		else
			messageType = new MessageType<Object>(1, "获取失败", null);
		return messageType;
	}

}
