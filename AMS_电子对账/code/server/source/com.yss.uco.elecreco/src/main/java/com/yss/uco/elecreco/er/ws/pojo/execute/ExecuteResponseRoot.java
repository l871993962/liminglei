package com.yss.uco.elecreco.er.ws.pojo.execute;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.yss.uco.elecreco.er.ws.pojo.ResponseHeader;

@XmlRootElement(name="RESPONSE")
@XmlAccessorType(XmlAccessType.FIELD)
public class ExecuteResponseRoot extends ResponseHeader{

	/**
	 * 响应实体数据集合
	 */
	@XmlElement(name = "PROT_DZDZ")
	private List<ExecuteResponseBody> responseBody;

	@XmlTransient
	public List<ExecuteResponseBody> getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(List<ExecuteResponseBody> responseBody) {
		this.responseBody = responseBody;
	}
	
}
