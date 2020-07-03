package com.Yanda.Ruitesco.dao;

import java.util.List;

import com.Yanda.Ruitesco.javabean.ShippingAddr;

public interface IShippingAddrDao {
	public int InsertAddress(int user_id, String receiverName, String receiverMobile,
			String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip);
	public int QueryAddress(int user_id, String receiverName, String receiverMobile,
			String receiverProvince, String receiverCity, String receiverAddress, int receiverZip);//根据其他信息查找收货地址id
	public int DeleteAddress(int id);//删除地址
	public int UpdateAddress(int id, String name, String mobile, String province,
			String city, String district, String address, int zip);//删除地址
	public List<ShippingAddr> selectAddress(int id);//根据收货地址id查询
	public List<ShippingAddr> selectAddressByUserId(int userid);//查询该用户的所有收货地址
}
