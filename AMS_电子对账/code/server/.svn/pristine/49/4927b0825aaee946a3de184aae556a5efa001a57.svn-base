package com.yss.uco.elecreco.er.task.pojo;

public enum ErStatusInfo {
	ER_SENDED("已发送至深证通，等待托管行响应"),

	ER_SENDED_SECCUSS("发送成功，托管行正在处理"),

	ER_SENDED_FAIL("发送失败"),
	
	ER_ACCECPED("对账一致"), 
	
	ER_SEND("生成成功"), 
	
	ER_IDENTICAL("对账不一致"),
	
	//// 停止时状态
	ER_SENDED_END("已发送至深证通，托管行无响应"),

	ER_SENDED_SECCUSS_END("发送成功，托管行没有返回对账结果"),

	ER_SENDED_FAIL_END("发送失败"),
	
	ER_ACCECPED_END("对账一致"), 
	
	ER_SEND_END("生成成功,发送失败"), 
	
	ER_IDENTICAL_END("对账不一致");
	
	private String value;

	private ErStatusInfo(String value) {
		this.value = value;
	}

	public String toString() {
		return this.value.toString();
	}

}
