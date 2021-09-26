package com.yss.ifa.szt.tool.pojo;

import com.yss.framework.api.common.co.BasePojo;

/**
 * STORY42784中国银行_深证通伺服器要求采用热备模式
 * STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: MrInfo 
 * @Description:伺服器连接设置pojo
 * @author wulongxing
 * @date 2017年6月12日 下午6:10:19 
 *
 */
public class MrInfo extends BasePojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 序号
	 */
	private int n_Order = 0;
	/**
	 * 参数代码
	 */
	public String c_Para_Code = "";

	/**
	 * 主备标识
	 */
	public String c_Dv_Switch_Mark = "";

	/**
	 * 伺服器IP
	 */
	public String c_Mr_Ip = "";
	/**
	 * 伺服器端口
	 */
	public String c_Mr_Port = "";
	
	/**
	 * 深证通连接状态(0：到深证通不通；1：到伺服器不通 ，-1：链路通畅)
	 */
	private int n_Link_Status = 9;
	
	/**
	 * 深证通连接状态(0：到深证通不通；1：到伺服器不通 ，-1：链路通畅)
	 * @return
	 */
	public int getN_Link_Status() {
		return n_Link_Status;
	}
	/**
	 * 深证通连接状态(0：到深证通不通；1：到伺服器不通 ，-1：链路通畅)
	 * @param n_Link_status
	 */
	public void setN_Link_Status(int n_Link_Status) {
		this.n_Link_Status = n_Link_Status;
	}
	public int getN_Order() {
		return n_Order;
	}
	public void setN_Order(int n_Order) {
		this.n_Order = n_Order;
	}
	public String getC_Para_Code() {
		return c_Para_Code;
	}
	public void setC_Para_Code(String c_Para_Code) {
		this.c_Para_Code = c_Para_Code;
	}
	
	public String getC_Dv_Switch_Mark() {
		return c_Dv_Switch_Mark;
	}
	public void setC_Dv_Switch_Mark(String c_Dv_Switch_Mark) {
		this.c_Dv_Switch_Mark = c_Dv_Switch_Mark;
	}
	public String getC_Mr_Ip() {
		return c_Mr_Ip;
	}
	public void setC_Mr_Ip(String c_Mr_Ip) {
		this.c_Mr_Ip = c_Mr_Ip;
	}
	public String getC_Mr_Port() {
		return c_Mr_Port;
	}
	public void setC_Mr_Port(String c_Mr_Port) {
		this.c_Mr_Port = c_Mr_Port;
	}

}
