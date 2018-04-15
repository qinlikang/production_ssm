package com.megagao.production.ssm.domain;
import java.util.Date;

import javax.validation.constraints.Size;


public class Condition {
	@Size(max=100, message="{name.length.error}")
    private String name;
    private Date startDate;
    private Date endDate;
    private String startDateString;
    private String endDateString;
    private String userid;
    
	public String getStartDateString() {
		return startDateString;
	}
	public void setStartDateString(String startDateString) {
		this.startDateString = startDateString;
	}
	public String getEndDateString() {
		return endDateString;
	}
	public void setEndDateString(String endDateString) {
		this.endDateString = endDateString;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "Condition [name=" + name + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", startDateString="
				+ startDateString + ", endDateString=" + endDateString
				+ ", userid=" + userid + "]";
	}
	
    
}
