package com.yss.uco.elecreco.er.ws.cons;

public enum StateCons {

	S000("S000","成功"),
	S001("S001","通讯失败"),
	S002("S002","报文解析失败"),
	E001("E001","功能码无效"),
	E002("E002","无效流水号"),
	E003("E003","文件不存在"),
	E004("E004","无效数据库"),
	E005("E005","无效表名"),
	E006("E006","无效数据库字段"),
	E007("E007","数据库字段超长"),
	E008("E008","文件字段数量不匹配"),
	E999("E999","运行错误"),
	NULL("","");

 	private String value;
    
    private String msg;

    private StateCons(String value,String msg) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public String getMsg() {
        return msg;
    }
}
