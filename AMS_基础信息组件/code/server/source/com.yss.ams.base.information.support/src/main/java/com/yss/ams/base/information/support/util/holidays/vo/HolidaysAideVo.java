package com.yss.ams.base.information.support.util.holidays.vo;

import java.util.Date;

import com.yss.framework.api.restful.annotations.ControllerMixArgu;
@ControllerMixArgu
public class HolidaysAideVo {
	private Date date;
	private String holidaysCode;
	private Date specifiedDate; 
	private int offset; 
	private String holidayCode;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getHolidaysCode() {
		return holidaysCode;
	}
	public void setHolidaysCode(String holidaysCode) {
		this.holidaysCode = holidaysCode;
	}
	public Date getSpecifiedDate() {
		return specifiedDate;
	}
	public void setSpecifiedDate(Date specifiedDate) {
		this.specifiedDate = specifiedDate;
	}
	public int getOffset() {
		return offset;
	}
	public void setOffset(int offset) {
		this.offset = offset;
	}
	public String getHolidayCode() {
		return holidayCode;
	}
	public void setHolidayCode(String holidayCode) {
		this.holidayCode = holidayCode;
	}
	
	
	
}
