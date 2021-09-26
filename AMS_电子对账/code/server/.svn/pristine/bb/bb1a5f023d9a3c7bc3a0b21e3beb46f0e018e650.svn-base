package com.yss.uco.elecreco.support.restservice.pojo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ResponseInfo implements Serializable {

	private static final long serialVersionUID = 4501917362383001112L;
	
	@JsonProperty
	private String session;

	@JsonProperty
	private String code = "";

	@JsonProperty
	private String message ="";

	@JsonProperty
	private int total = 0;

	@JsonProperty
	private Object data = null;

	@JsonProperty
	private boolean result = true;

	@JsonIgnore
	public String getSession() {
		return session;
	}

	@JsonIgnore
	public void setSession(String session) {
		this.session = session;
	}

	@JsonIgnore
	public String getCode() {
		return code;
	}

	@JsonIgnore
	public void setCode(String code) {
		this.code = code;
	}

	@JsonIgnore
	public String getMessage() {
		return message;
	}

	@JsonIgnore
	public void setMessage(String message) {
		this.message = message;
	}

	@JsonIgnore
	public int getTotal() {
		return total;
	}

	@JsonIgnore
	public void setTotal(int total) {
		this.total = total;
	}

	@JsonIgnore
	public Object getData() {
		return data;
	}

	@JsonIgnore
	public void setData(Object data) {
		this.data = data;
	} 
	
	@JsonIgnore
	public boolean isResult() {
		return result;
	}

	@JsonIgnore
	public void setResult(boolean result) {
		this.result = result;
	}
	
	
}
