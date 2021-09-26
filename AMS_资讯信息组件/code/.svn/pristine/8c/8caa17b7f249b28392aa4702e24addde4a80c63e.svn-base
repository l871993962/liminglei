
package com.yss.ams.sec.information.support.modules.pub.pojo;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * @classDesc 系统初始化项目
 * @version 1.0 2012-2-11
 * @author yh
 */
public class SysInitItemBean extends AuditableParamPojo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7724553034869888803L;
	private String C_DV_ITEM_CODE = null;
	private String C_DESC = null;
	
	/**
	 * @return the c_DV_ITEM_CODE
	 */
	public String getC_DV_ITEM_CODE() {
		return C_DV_ITEM_CODE;
	}

	/**
	 * @param cDVITEMCODE the c_DV_ITEM_CODE to set
	 */
	public void setC_DV_ITEM_CODE(String cDVITEMCODE) {
		C_DV_ITEM_CODE = cDVITEMCODE;
	}

	/**
	 * @return the c_DESC
	 */
	public String getC_DESC() {
		return C_DESC;
	}

	/**
	 * @param cDESC the c_DESC to set
	 */
	public void setC_DESC(String cDESC) {
		C_DESC = cDESC;
	}

	public void parseRsToAttr(ResultSet rs) throws SQLException
	{
		C_DV_ITEM_CODE = rs.getString("C_DV_ITEM_CODE");
		C_DESC = rs.getString("C_DESC");
		setAuditState(1);

	}
	
	/**
	 * 将后台数据拼接成字符串传到前台进行解析
	 * @return buf.toString() 返回拼接好的数据
	 */
	public String buildAttrToStr() 
	{
		StringBuffer buf = null;//变量声明
		buf = new StringBuffer();
		buf.append(C_DV_ITEM_CODE).append('\t');// 自动ID
		buf.append(C_DESC).append('\t');// 年月
		return buf.toString();
	}
	
	public void parseStrToAttr(String str){
		String[] strArrays = str.split("\t");
		C_DV_ITEM_CODE = strArrays[0];
		C_DESC = strArrays[1];
	}
}
