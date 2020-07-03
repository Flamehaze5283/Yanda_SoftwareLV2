package com.Yanda.Ruitesco.service;

import com.Yanda.Ruitesco.utils.response.MessageType;

public interface IShippingAddrService {
	MessageType<Object> addAddress(int user_id, String receiverName, String receiverMobile, String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip);//����ջ���ַ
	MessageType<Object> deleteAddress(int shippingId);//ɾ���ջ���ַ
	MessageType<Object> updateAddress(int id, String name, String mobile, String province, String city, String district, String address, int zip);//�޸ĵ�ַ
	MessageType<Object> selectAddress(int id);//�鿴����ĵ�ַ
	MessageType<Object> selectAddressByUserId(int userid);//�����û������ջ���ַ
}
