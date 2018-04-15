package com.megagao.production.ssm.domain;

import java.util.Date;

import javax.validation.constraints.Size;

public class Summary {
	@Size(max=40, message="{id.length.error}")
    private String deviceTypeId;

	@Size(max=100, message="{name.length.error}")
    private String deviceTypeName;
	
	@Size(max=100, message="{name.length.error}")
    private String productName;
	
	private int cuontNum;

	
    public int getCuontNum() {
		return cuontNum;
	}


	public void setCuontNum(int cuontNum) {
		this.cuontNum = cuontNum;
	}


	private Date deviceTypeWarranty;


	public String getDeviceTypeId() {
		return deviceTypeId;
	}


	public void setDeviceTypeId(String deviceTypeId) {
		this.deviceTypeId = deviceTypeId;
	}


	public String getDeviceTypeName() {
		return deviceTypeName;
	}


	public void setDeviceTypeName(String deviceTypeName) {
		this.deviceTypeName = deviceTypeName;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public Date getDeviceTypeWarranty() {
		return deviceTypeWarranty;
	}


	public void setDeviceTypeWarranty(Date deviceTypeWarranty) {
		this.deviceTypeWarranty = deviceTypeWarranty;
	}
    
    
    
}
