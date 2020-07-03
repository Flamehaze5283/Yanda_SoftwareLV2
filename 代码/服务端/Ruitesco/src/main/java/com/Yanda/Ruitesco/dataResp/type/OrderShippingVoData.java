package com.Yanda.Ruitesco.dataResp.type;

import com.Yanda.Ruitesco.javabean.ShippingAddr;

public class OrderShippingVoData {
	private String receiverName;
	private String receiverPhone;
	private String reveiverMobile;
	private String receiverProvince;
	private String receiverCity;
	private String receiverDistrict;
	private String receiverAddress;
	private int receiverZip;
	
	
	
	public OrderShippingVoData(String receiverName, String receiverPhone, String reveiverMobile,
			String receiverProvince, String receiverCity, String receiverDistrict, String receiverAddress,
			int receiverZip) {
		this.receiverName = receiverName;
		this.receiverPhone = receiverPhone;
		this.reveiverMobile = reveiverMobile;
		this.receiverProvince = receiverProvince;
		this.receiverCity = receiverCity;
		this.receiverDistrict = receiverDistrict;
		this.receiverAddress = receiverAddress;
		this.receiverZip = receiverZip;
	}
	
	public OrderShippingVoData(ShippingAddr shippingAddr) {
		this.receiverName = shippingAddr.getReceiver_name();
		this.receiverPhone = shippingAddr.getReceiver_phone();
		this.reveiverMobile = shippingAddr.getReceiver_mobile();
		this.receiverProvince = shippingAddr.getReceiver_province();
		this.receiverCity = shippingAddr.getReceiver_city();
		this.receiverDistrict = shippingAddr.getReceiver_district();
		this.receiverAddress = shippingAddr.getReceiver_address();
		this.receiverZip = shippingAddr.getReceiver_zip();
	}
	
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	public String getReceiverPhone() {
		return receiverPhone;
	}
	public void setReceiverPhone(String receiverPhone) {
		this.receiverPhone = receiverPhone;
	}
	public String getReveiverMobile() {
		return reveiverMobile;
	}
	public void setReveiverMobile(String reveiverMobile) {
		this.reveiverMobile = reveiverMobile;
	}
	public String getReceiverProvince() {
		return receiverProvince;
	}
	public void setReceiverProvince(String receiverProvince) {
		this.receiverProvince = receiverProvince;
	}
	public String getReceiverCity() {
		return receiverCity;
	}
	public void setReceiverCity(String receiverCity) {
		this.receiverCity = receiverCity;
	}
	public String getReceiverDistrict() {
		return receiverDistrict;
	}
	public void setReceiverDistrict(String receiverDistrict) {
		this.receiverDistrict = receiverDistrict;
	}
	public String getReceiverAddress() {
		return receiverAddress;
	}
	public void setReceiverAddress(String receiverAddress) {
		this.receiverAddress = receiverAddress;
	}
	public int getReceiverZip() {
		return receiverZip;
	}
	public void setReceiverZip(int receiverZip) {
		this.receiverZip = receiverZip;
	}
	
	
}
