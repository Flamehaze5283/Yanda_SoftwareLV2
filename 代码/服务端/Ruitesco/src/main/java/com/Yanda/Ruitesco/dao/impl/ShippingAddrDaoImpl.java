package com.Yanda.Ruitesco.dao.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.Yanda.Ruitesco.dao.IShippingAddrDao;
import com.Yanda.Ruitesco.javabean.ShippingAddr;
import com.Yanda.Ruitesco.utils.JdbcUtil;

public class ShippingAddrDaoImpl implements IShippingAddrDao {

	@Override
	public int InsertAddress(int user_id, String receiverName, String receiverMobile,
			String receiverProvince, String receiverDistrict, String receiverCity, String receiverAddress, int receiverZip) {
		// TODO Auto-generated method stub
		String sql = "Insert into shipping_addr(user_id, receiver_name, receiver_mobile, receiver_province,"
						+ " receiver_district, receiver_city, receiver_address, receiver_zip, create_time, update_time) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		Date date = new Date();
		 //从前端或者自己模拟一个日期格式，转为String即可
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateStr = format.format(date);
		int rs = JdbcUtil.executeUpdate(sql, user_id, receiverName, receiverMobile, receiverProvince, receiverDistrict, receiverCity, receiverAddress, receiverZip, dateStr, dateStr);
		return rs;
	}

	@Override
	public int QueryAddress(int user_id, String receiverPhone,
			String receiverMobile, String receiverProvince, String receiverCity, String receiverAddress,
			int receiverZip) {
		// TODO Auto-generated method stub
		String sql = "select * from shipping_addr where user_id = ? and receiver_name = ? and receiver_mobile = ? and receiver_province = ? and receiver_city = ? and receiver_address = ? and receiver_zip = ?";
		List<ShippingAddr> addr = JdbcUtil.executeQuery(sql, ShippingAddr.class, user_id, receiverPhone, receiverMobile, receiverProvince, receiverCity, receiverAddress, receiverZip);
		int result = 0;
		if(addr != null)
			result = addr.get(0).getId();
		return result;
	}

	@Override
	public int DeleteAddress(int id) {
		// TODO Auto-generated method stub
		String sql = "delete from shipping_addr where id = ?";
		int rs = JdbcUtil.executeUpdate(sql, id);
		return rs;
	}

	@Override
	public int UpdateAddress(int id, String name, String mobile, String province, String city,
			String district, String address, int zip) {
		// TODO Auto-generated method stub
		String sql = "update shipping_addr set receiver_name = ?, receiver_mobile = ?, receiver_province = ?,"
					+ " receiver_district = ?, receiver_city = ?, receiver_address = ?, receiver_zip = ? where id = ?";
		System.out.println(sql);
		int rs = JdbcUtil.executeUpdate(sql, name, mobile, province, district, city, address, zip, id);
		return rs;
	}

	@Override
	public List<ShippingAddr> selectAddress(int id) {
		// TODO Auto-generated method stub
		String sql = "select * from shipping_addr where id = ?";
		List<ShippingAddr> rs = JdbcUtil.executeQuery(sql, ShippingAddr.class, id);
		return rs;
	}

	@Override
	public List<ShippingAddr> selectAddressByUserId(int userid) {
		// TODO Auto-generated method stub
		String sql = "select * from shipping_addr where user_id = ?";
		List<ShippingAddr> rs = JdbcUtil.executeQuery(sql, ShippingAddr.class, userid);
		return rs;
	}

}
