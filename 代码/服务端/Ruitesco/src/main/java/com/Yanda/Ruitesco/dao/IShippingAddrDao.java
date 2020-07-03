package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.ShippingAddr;

public interface IShippingAddrDao {
	public int InsertAddress(int user_id, String receiverName, String receiverMobile,
			String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip);
	public int QueryAddress(int user_id, String receiverName, String receiverMobile,
			String receiverProvince, String receiverCity, String receiverAddress, int receiverZip);//����������Ϣ�����ջ���ַid
	public int DeleteAddress(int id);//ɾ����ַ
	public int UpdateAddress(int id, String name, String mobile, String province,
			String city, String district, String address, int zip);//ɾ����ַ
	public List<ShippingAddr> selectAddress(int id);//�����ջ���ַid��ѯ
	public List<ShippingAddr> selectAddressByUserId(int userid);//��ѯ���û��������ջ���ַ
}
