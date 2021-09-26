package com.yss.ams.visaval.support.util.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;

/***
 * 参数ben
 * @author ru
 *
 */
public class FuncParaBean extends BasePojo{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6529756281265600610L;

	/***
	 * 函数代码
	 */
	private String c_FUNC_CODE = "";
	
	/***
	 * 参数代码
	 */
	private String c_PARA_CODE = null;
	
	/***
	 * 参数名称
	 */
	private String c_PARA_NAME = null;
	
	/***
	 * 参数类型
	 */
	private String c_DV_PARA_TYPE = null;
	
	/***
	 * 描述
	 */
	private String c_DESC = null;
	
	public String getC_FUNC_CODE() {
		return c_FUNC_CODE;
	}

	public void setC_FUNC_CODE(String cFUNCCODE) {
		c_FUNC_CODE = cFUNCCODE;
	}

	/***
	 * 顺序
	 */
	private int n_ORDER = 0;

	/***
	 * 参数代码
	 * @return
	 */
	public String getC_PARA_CODE() {
		return c_PARA_CODE;
	}

	/***
	 * 参数代码
	 * @param cPARACODE
	 */
	public void setC_PARA_CODE(String cPARACODE) {
		c_PARA_CODE = cPARACODE;
	}

	/***
	 * 参数名称
	 * @return
	 */
	public String getC_PARA_NAME() {
		return c_PARA_NAME;
	}

	/***
	 * 参数名称
	 * @param cPARANAME
	 */
	public void setC_PARA_NAME(String cPARANAME) {
		c_PARA_NAME = cPARANAME;
	}

	/***
	 * 参数类型
	 * @return
	 */
	public String getC_DV_PARA_TYPE() {
		return c_DV_PARA_TYPE;
	}

	/***
	 * 参数类型
	 * @param cDVPARATYPE
	 */
	public void setC_DV_PARA_TYPE(String cDVPARATYPE) {
		c_DV_PARA_TYPE = cDVPARATYPE;
	}

	/***
	 * 描述
	 */
	public String getC_DESC() {
		return c_DESC;
	}

	/***
	 * 描述
	 */
	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	/***
	 * 顺序
	 * @return
	 */
	public int getN_ORDER() {
		return n_ORDER;
	}

	/***
	 * 顺序  
	 * @param nORDER
	 */
	public void setN_ORDER(int nORDER) {
		n_ORDER = nORDER;
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
		c_FUNC_CODE = reqAry[0]; // 函数代码
		c_PARA_CODE = reqAry[1]; // 参数代码
		c_PARA_NAME = reqAry[2]; // 参数名称
		c_DV_PARA_TYPE = reqAry[3]; // 函数类型
		c_DESC = reqAry[4]; // 描述
		n_ORDER = Integer.parseInt(reqAry[5]); // 顺序
	}
	
	/**
	 * 设置实体Bean值
	 * 
	 * @param rs
	 * @throws SQLException
	 * @throws YssException
	 */
	public void parseRsToAttr(ResultSet rs) throws SQLException, YssException {
		c_PARA_CODE = rs.getString("c_PARA_CODE");
		c_PARA_NAME = rs.getString("c_PARA_NAME");
		c_DV_PARA_TYPE = rs.getString("c_DV_PARA_TYPE");
		c_DESC = rs.getString("c_DESC"); // 描述
		n_ORDER = rs.getInt("n_ORDER"); // 参数信息
		c_FUNC_CODE = rs.getString("c_FUNC_CODE");//函数代码
	}

}
