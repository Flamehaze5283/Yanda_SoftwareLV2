package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.response.MessageType;

public interface IShippingAddrService {
	MessageType<Object> addAddress(int user_id, String receiverName, String receiverMobile, String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip);//添加收货地址
	MessageType<Object> deleteAddress(int shippingId);//删除收货地址
	MessageType<Object> updateAddress(int id, String name, String mobile, String province, String city, String district, String address, int zip);//修改地址
	MessageType<Object> selectAddress(int id);//查看具体的地址
	MessageType<Object> selectAddressByUserId(int userid);//查找用户所有收货地址
}
