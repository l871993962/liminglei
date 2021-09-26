package com.yss.ams.visaval.support.util.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.YssCons;

/**
 * @classDesc 高级算法函数bean
 * @version 1.0 2012-5-15
 * @author yh
 */
public class FunctionBean extends BasePojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2728652621006558726L;

	/**
	 * 函数代码
	 */
	private String C_FUNC_CODE  = null;
	
	/**
	 * 函数名称
	 */
	private String C_FUNC_NAME  = null;

	/***
	 * 函数类型
	 */
	private String C_DV_FUNC_TYPE = null;
	
	/***
	 * 描述
	 */
	private String C_DESC = null;
	
	/***
	 * 参数数据
	 */
	private String PARAINFO = null;
	
	/***
	 * 函数在系统的的使用
	 */
	private String c_FUNC_USED = null;
	
	/***
	 * 函数在前台的展示
	 */
	private String c_FUNC_SHOW = null;  
	
	/***
	 * 参数列表
	 */
	private ArrayList<FuncParaBean> paraList= null;

	public String getC_FUNC_SHOW() {
		return c_FUNC_SHOW;
	}

	public void setC_FUNC_SHOW(String cFUNCSHOW) {
		c_FUNC_SHOW = cFUNCSHOW;
	}

	/***
	 * 函数在系统的的使用
	 * @return
	 */
	public String getC_FUNC_USED() {
		return c_FUNC_USED;
	}

	/***
	 * 函数在系统的的使用
	 * @param cFUNCUSED
	 */
	public void setC_FUNC_USED(String cFUNCUSED) {
		c_FUNC_USED = cFUNCUSED;
	}

	/***
	 * 参数列表
	 * @return
	 */
	public ArrayList<FuncParaBean> getParaList() {
		return paraList;
	}

	/***
	 * 参数列表
	 * @param paraList
	 */
	public void setParaList(ArrayList<FuncParaBean> paraList) {
		this.paraList = paraList;
	}

	/***
	 * 参数数据
	 * @return 参数数据
	 */
	public String getPARAINFO() {
		return PARAINFO;
	}

	/***
	 * 参数数据
	 * @param pARAINFO 参数数据
	 */
	public void setPARAINFO(String pARAINFO) {
		PARAINFO = pARAINFO;
	}

	/***
	 * 函数代码
	 * @return 函数代码
	 */
	public String getC_FUNC_CODE() {
		return C_FUNC_CODE;
	}

	/***
	 * 函数代码
	 * @param cFUNCCODE 函数代码
	 */
	public void setC_FUNC_CODE(String cFUNCCODE) {
		C_FUNC_CODE = cFUNCCODE;
	}

	/***
	 * 函数名称
	 * @return 函数名称
	 */
	public String getC_FUNC_NAME() {
		return C_FUNC_NAME;
	}

	/***
	 * 函数名称
	 * @param cFUNCNAME 函数名称
	 */
	public void setC_FUNC_NAME(String cFUNCNAME) {
		C_FUNC_NAME = cFUNCNAME;
	}

	/***
	 * 函数类型
	 * @return 函数类型
	 */
	public String getC_DV_FUNC_TYPE() {
		return C_DV_FUNC_TYPE;
	}

	/***
	 * 函数类型
	 * @param cDVFUNCTYPE
	 */
	public void setC_DV_FUNC_TYPE(String cDVFUNCTYPE) {
		C_DV_FUNC_TYPE = cDVFUNCTYPE;
	}

	/***
	 * 描述
	 * @return 描述
	 */
	public String getC_DESC() {
		return C_DESC;
	}

	/***
	 * 描述
	 * @param cDESC 描述
	 */
	public void setC_DESC(String cDESC) {
		C_DESC = cDESC;
	}
	
	/**
	 * 解析数据
	 * 
	 * @param strPojo
	 * @throws YssException
	 */
	public void parseStrToAttr(String strPojo) {
		// 拆分字符串，获取每个对象的属性
		String[] reqAry = strPojo.split("\t");
		C_FUNC_CODE = reqAry[0]; // 函数代码
		C_FUNC_NAME = reqAry[1]; // 函数名称
		C_DV_FUNC_TYPE = reqAry[2]; // 函数类型
		C_DESC = reqAry[3]; // 描述
	}

	/**
	 * buildRowStr 按一定格式拼接数据
	 * 
	 * @return String
	 */
	public String buildAttrToStr() {
		StringBuffer buf = new StringBuffer();
		buf.append(C_FUNC_CODE).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(C_FUNC_NAME).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(C_DV_FUNC_TYPE).append(YssCons.YSS_REPORTSHOWDETAIL);
		buf.append(C_DESC).append(YssCons.YSS_REPORTSHOWDETAIL); // 描述
		buf.append(PARAINFO).append(YssCons.YSS_REPORTSHOWDETAIL); // 参数
		buf.append(c_FUNC_SHOW).append(YssCons.YSS_REPORTSHOWDETAIL); // 参数
		return buf.toString();
	}
	

	/**
	 * 设置实体Bean值
	 * 
	 * @param rs
	 * @throws SQLException
	 * @throws YssException
	 */
	public void parseRsToAttr(ResultSet rs) throws SQLException, YssException {
		C_FUNC_CODE = rs.getString("C_FUNC_CODE");
		C_FUNC_NAME = rs.getString("C_FUNC_NAME");
		C_DV_FUNC_TYPE = rs.getString("C_DV_FUNC_TYPE");
		C_DESC = rs.getString("C_DESC"); // 描述
		PARAINFO = rs.getString("PARAINFO"); // 参数信息
		c_FUNC_USED = rs.getString("C_FUNC_USED");
		c_FUNC_SHOW = rs.getString("C_FUNC_SHOW"); // 获取函数的前台展示
		paraList = new ArrayList<FuncParaBean>();
		paraList = parseArray(PARAINFO);
	}
	
	/**
	 * 解析数据
	 * 
	 * @param strPojo
	 * @throws YssException
	 */
	public  ArrayList<FuncParaBean> parseArray(String strPojo) {
		ArrayList<FuncParaBean> list = null;
		FuncParaBean paraBen = null;
		String[] benArray = strPojo.split(",");// 获取所有的对象数据
		for (String str : benArray) {
			paraBen = new FuncParaBean();
			if(null == list)
			{
				list = new ArrayList<FuncParaBean>();
			}
			paraBen.parseStrToAttr(str);
			list.add(paraBen);
		}
		return list;
		
	}
	
}
