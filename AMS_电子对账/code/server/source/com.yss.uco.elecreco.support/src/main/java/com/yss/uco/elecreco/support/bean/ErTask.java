package com.yss.uco.elecreco.support.bean;
import com.yss.framework.api.common.co.ParamPojo;
public class ErTask extends ParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*任务代码

	*/
	private String c_DV_TASK_CODE = "";
	/**
	*运行间隔(分钟)
	*/
	private long n_RUN_INTERVAL = 0L;
	/**
	*提醒用户
	*/
	private String c_CALL_USER = "";
	/**
	*当前执行状态(1-正常，0-异常)
	*/
	private int n_RUN_STATE = 1;
	/**
	*当前执行详细信息

	*/
	private String c_RUN_INFO = "";
	/**
	*当前执行结果（0-失败，1-成功）
	*/
	private int n_RUN_RESULT = 1;
	/**
	*执行结果详细信息
	*/
	private String c_RESULT_INFO = "";
	public String getC_DV_TASK_CODE(){
		return c_DV_TASK_CODE;
	}
	public void setC_DV_TASK_CODE(String c_DV_TASK_CODE){
		 this.c_DV_TASK_CODE = c_DV_TASK_CODE;
	}
	public long getN_RUN_INTERVAL(){
		return n_RUN_INTERVAL;
	}
	public void setN_RUN_INTERVAL(long n_RUN_INTERVAL){
		 this.n_RUN_INTERVAL = n_RUN_INTERVAL;
	}
	public String getC_CALL_USER(){
		return c_CALL_USER;
	}
	public void setC_CALL_USER(String c_CALL_USER){
		 this.c_CALL_USER = c_CALL_USER;
	}
	public int getN_RUN_STATE(){
		return n_RUN_STATE;
	}
	public void setN_RUN_STATE(int n_RUN_STATE){
		 this.n_RUN_STATE = n_RUN_STATE;
	}
	public String getC_RUN_INFO(){
		return c_RUN_INFO;
	}
	public void setC_RUN_INFO(String c_RUN_INFO){
		 this.c_RUN_INFO = c_RUN_INFO;
	}
	public int getN_RUN_RESULT(){
		return n_RUN_RESULT;
	}
	public void setN_RUN_RESULT(int n_RUN_RESULT){
		 this.n_RUN_RESULT = n_RUN_RESULT;
	}
	public String getC_RESULT_INFO(){
		return c_RESULT_INFO;
	}
	public void setC_RESULT_INFO(String c_RESULT_INFO){
		 this.c_RESULT_INFO = c_RESULT_INFO;
	}
	@Override
	public String toString() {
		return "ErTask [任务代码：" + c_DV_TASK_CODE + ", 运行间隔（分钟）："
				+ n_RUN_INTERVAL + ", 通知用户：" + c_CALL_USER + "]";
	}
	
}