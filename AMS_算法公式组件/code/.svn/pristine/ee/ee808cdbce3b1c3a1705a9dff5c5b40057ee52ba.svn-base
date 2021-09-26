package com.yss.ams.visaval.support.util.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.YssCons;

/**
 * @classDesc 系统关键字pojo
 * @version 1.0 2012-5-15
 * @author yh
 */
public class SysWordBean extends BasePojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1000458781688482488L;

	/***
	 * 关键字代码
	 */
	private String C_KEY_CODE = null;
	
	/***
	 * 关键字名称
	 */
	private String C_KEY_NAME = null;
	
	/***
	 * 关键字类型
	 */
	private String C_DV_KEY_TYPE = null;
	
	/***
	 * 描述
	 */
	private String C_DESC = null;
	
	/***
	 * 关键字的使用
	 */
	private String C_KEY_USED = null;
	
	/***
	 * 前台展示
	 */
	private String C_KEY_SHOW = null;
	
	/***
	 * 参数类型
	 */
	private String C_DV_PARA_TYPE = null;

	public String getC_DV_PARA_TYPE() {
		return C_DV_PARA_TYPE;
	}

	public void setC_DV_PARA_TYPE(String cDVPARATYPE) {
		C_DV_PARA_TYPE = cDVPARATYPE;
	}

	/***
	 * 前台展示
	 */
	public String getC_KEY_SHOW() {
		return C_KEY_SHOW;
	}

	/***
	 * 前台展示
	 */
	public void setC_KEY_SHOW(String cKEYSHOW) {
		C_KEY_SHOW = cKEYSHOW;
	}

	/***
	 * 关键字的使用
	 * @return
	 */
	public String getC_KEY_USED() {
		return C_KEY_USED;
	}

	/***
	 * 关键字的使用
	 * @param cKEYUEED
	 */
	public void setC_KEY_USED(String cKEYUEED) {
		C_KEY_USED = cKEYUEED;
	}

	/****
	 * 关键字代码
	 * @return
	 */
	public String getC_KEY_CODE() {
		return C_KEY_CODE;
	}

	/***
	 * 关键字代码
	 * @param cKEYCODE
	 */
	public void setC_KEY_CODE(String cKEYCODE) {
		C_KEY_CODE = cKEYCODE;
	}

	/***
	 * 关键字名称
	 * @return
	 */
	public String getC_KEY_NAME() {
		return C_KEY_NAME;
	}

	/***
	 * 关键字名称
	 * @param cKEYNAME
	 */
	public void setC_KEY_NAME(String cKEYNAME) {
		C_KEY_NAME = cKEYNAME;
	}

	/***
	 * 关键字类型
	 * @return
	 */
	public String getC_DV_KEY_TYPE() {
		return C_DV_KEY_TYPE;
	}

	/***
	 * 关键字类型
	 * @param cDVKEYTYPE
	 */
	public void setC_DV_KEY_TYPE(String cDVKEYTYPE) {
		C_DV_KEY_TYPE = cDVKEYTYPE;
	}

	/***
	 * 描述
	 * @return
	 */
	public String getC_DESC() {
		return C_DESC;
	}

	/***
	 * 描述
	 * @param cDESC
	 */
	public void setC_DESC(String cDESC) {
		C_DESC = cDESC;
	}
	
	/**
	 * 设置实体Bean值
	 * 
	 * @param rs
	 * @throws SQLException
	 * @throws YssException
	 */
	public void parseRsToAttr(ResultSet rs) throws SQLException, YssException {
		C_KEY_CODE = rs.getString("C_KEY_CODE");
		C_KEY_NAME = rs.getString("C_KEY_NAME");
		C_DV_KEY_TYPE = rs.getString("C_DV_KEY_TYPE");
		C_DESC = rs.getString("C_DESC"); // 描述
		C_KEY_USED = rs.getString("C_KEY_USED"); // 描述
		C_KEY_SHOW = rs.getString("C_KEY_SHOW"); // 前台展示
		C_DV_PARA_TYPE = rs.getString("c_DV_PARA_TYPE");
	}
	
	/**
	 * buildRowStr 按一定格式拼接数据
	 * 
	 * @return String
	 */
	public String buildAttrToStr() {
		StringBuffer buf = new StringBuffer();
		buf.append(C_KEY_CODE).append(YssCons.YSS_ITEMSPLITMARK1);
		buf.append(C_KEY_NAME).append(YssCons.YSS_ITEMSPLITMARK1);
		buf.append(C_DV_KEY_TYPE).append(YssCons.YSS_ITEMSPLITMARK1);
		buf.append(C_DESC).append(YssCons.YSS_ITEMSPLITMARK1);
		buf.append(C_KEY_USED).append(YssCons.YSS_ITEMSPLITMARK1);
		buf.append(C_KEY_SHOW).append(YssCons.YSS_ITEMSPLITMARK1);
		return buf.toString();
	}
	
}
