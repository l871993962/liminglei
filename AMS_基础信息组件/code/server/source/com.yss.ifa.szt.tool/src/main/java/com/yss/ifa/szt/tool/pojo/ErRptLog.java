package com.yss.ifa.szt.tool.pojo;
import java.sql.Timestamp;

import com.yss.framework.api.common.co.BasePojo;
public class ErRptLog extends BasePojo  {
	private static final long serialVersionUID = 1L;

	/**
	*报文序号
	*/
	private String c_SN = "";
	/**
	*文件类型
	*/
	private String c_FILE_TYPE = "";
	/**
	*资产代码
	*/
	private String c_ASS_CODE = "";
	/**
	*报表类型
	*/
	private String c_RPT_TYPE = "";
	/**
	*开始日期
	*/
	private String c_BEGIN_DATE = "";
	/**
	*源用户
	*/
	private String c_SRC_USERID = "";
	/**
	*源应用
	*/
	private String c_SRC_APPID = "";
	/**
	*发送密码
	*/
	private String c_PKG_PSD = "";
	/**
	*公司代码
	*/
	private String c_DEPT_CODE = "";
	/**
	*证书ID
	*/
	private String c_CERT_ID = "";
	/**
	*密钥
	*/
	private String c_SECRETKEY = "";
	/**
	*加密方式ELEC_SECRETTYPE
	*/
	private String c_DV_SECRETTYPE = "";
	/**
	*编码CHR_CODE
	*/
	private String c_DV_CHARSET = "";
	/**
	*目标用户
	*/
	private String c_TARGET_USERID = "";
	/**
	*目标应用
	*/
	private String c_TARGET_APPID = "";
	/**
	*日志类型ER_LOG_TYPE
	*/
	private String c_DV_LOG_TYPE = "";
	/**
	*加密日志
	*/
	private String c_ENCRYPT_LOG = "";
	/**
	*解密日志
	*/
	private String c_DECRYPT_LOG = "";
	/**
	*错误信息
	*/
	private String c_ERR_INFO = "";
	/**
	*处理时间
	*/
	private Timestamp d_HANDLE_TIME = null;
	
	/**
	 * 深证通数据包ID
	 */
	private String c_PKG_ID = "";
	
	public Timestamp getD_HANDLE_TIME() {
		return d_HANDLE_TIME;
	}
	public void setD_HANDLE_TIME(Timestamp d_HANDLE_TIME) {
		this.d_HANDLE_TIME = d_HANDLE_TIME;
	}
	public String getC_SN(){
		return c_SN;
	}
	public void setC_SN(String c_SN){
		 this.c_SN = c_SN;
	}
	public String getC_FILE_TYPE(){
		return c_FILE_TYPE;
	}
	public void setC_FILE_TYPE(String c_FILE_TYPE){
		 this.c_FILE_TYPE = c_FILE_TYPE;
	}
	public String getC_ASS_CODE(){
		return c_ASS_CODE;
	}
	public void setC_ASS_CODE(String c_ASS_CODE){
		 this.c_ASS_CODE = c_ASS_CODE;
	}
	public String getC_RPT_TYPE(){
		return c_RPT_TYPE;
	}
	public void setC_RPT_TYPE(String c_RPT_TYPE){
		 this.c_RPT_TYPE = c_RPT_TYPE;
	}
	public String getC_SRC_USERID(){
		return c_SRC_USERID;
	}
	public void setC_SRC_USERID(String c_SRC_USERID){
		 this.c_SRC_USERID = c_SRC_USERID;
	}
	public String getC_SRC_APPID(){
		return c_SRC_APPID;
	}
	public void setC_SRC_APPID(String c_SRC_APPID){
		 this.c_SRC_APPID = c_SRC_APPID;
	}
	public String getC_PKG_PSD() {
		return c_PKG_PSD;
	}
	public void setC_PKG_PSD(String c_PKG_PSD) {
		this.c_PKG_PSD = c_PKG_PSD;
	}
	public String getC_DEPT_CODE(){
		return c_DEPT_CODE;
	}
	public void setC_DEPT_CODE(String c_DEPT_CODE){
		 this.c_DEPT_CODE = c_DEPT_CODE;
	}
	public String getC_CERT_ID(){
		return c_CERT_ID;
	}
	public void setC_CERT_ID(String c_CERT_ID){
		 this.c_CERT_ID = c_CERT_ID;
	}
	public String getC_SECRETKEY(){
		return c_SECRETKEY;
	}
	public void setC_SECRETKEY(String c_SECRETKEY){
		 this.c_SECRETKEY = c_SECRETKEY;
	}
	public String getC_DV_SECRETTYPE(){
		return c_DV_SECRETTYPE;
	}
	public void setC_DV_SECRETTYPE(String c_DV_SECRETTYPE){
		 this.c_DV_SECRETTYPE = c_DV_SECRETTYPE;
	}
	public String getC_DV_CHARSET(){
		return c_DV_CHARSET;
	}
	public void setC_DV_CHARSET(String c_DV_CHARSET){
		 this.c_DV_CHARSET = c_DV_CHARSET;
	}
	public String getC_TARGET_USERID(){
		return c_TARGET_USERID;
	}
	public void setC_TARGET_USERID(String c_TARGET_USERID){
		 this.c_TARGET_USERID = c_TARGET_USERID;
	}
	public String getC_TARGET_APPID(){
		return c_TARGET_APPID;
	}
	public void setC_TARGET_APPID(String c_TARGET_APPID){
		 this.c_TARGET_APPID = c_TARGET_APPID;
	}
	public String getC_DV_LOG_TYPE(){
		return c_DV_LOG_TYPE;
	}
	public void setC_DV_LOG_TYPE(String c_DV_LOG_TYPE){
		 this.c_DV_LOG_TYPE = c_DV_LOG_TYPE;
	}
	public String getC_ENCRYPT_LOG(){
		return c_ENCRYPT_LOG;
	}
	public void setC_ENCRYPT_LOG(String c_ENCRYPT_LOG){
		 this.c_ENCRYPT_LOG = c_ENCRYPT_LOG;
	}
	public String getC_DECRYPT_LOG(){
		return c_DECRYPT_LOG;
	}
	public void setC_DECRYPT_LOG(String c_DECRYPT_LOG){
		 this.c_DECRYPT_LOG = c_DECRYPT_LOG;
	}
	public String getC_ERR_INFO(){
		return c_ERR_INFO;
	}
	public void setC_ERR_INFO(String c_ERR_INFO){
		 this.c_ERR_INFO = c_ERR_INFO;
	}
	public String getC_BEGIN_DATE() {
		return c_BEGIN_DATE;
	}
	public void setC_BEGIN_DATE(String c_BEGIN_DATE) {
		this.c_BEGIN_DATE = c_BEGIN_DATE;
	}
	public String getC_PKG_ID() {
		return c_PKG_ID;
	}
	public void setC_PKG_ID(String c_PKG_ID) {
		this.c_PKG_ID = c_PKG_ID;
	}
}