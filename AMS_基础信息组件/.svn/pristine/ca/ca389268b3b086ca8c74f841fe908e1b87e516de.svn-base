package com.yss.ams.base.information.support.bi.mkt.pojo;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;

/**
 * 交易市场实体类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class MarketVoc extends BasePojo {
	/**
	 * 增加序列 by lihaizhi 20130620
	 */
	private static final long serialVersionUID = 8513103796400103894L;
	/**
	 * Field C_DV_TYPE.
	 */
	private String C_DV_MKT_TYPE = ""; // 词汇类型
	/**
	 * Field C_DV_CODE.
	 */
	private String C_MKTVOC_CODE = ""; // 词汇代码
	/**
	 * Field C_DV_NAME.
	 */
	private String C_MKTVOC_NAME = ""; // 词汇名称
	/**
	 * Field C_DESC.
	 */
	private String C_DESC = "";// 描述

	/**
	 * Method getC_DESC.
	 * 
	 * @return String
	 */
	public String getC_DESC() {
		return C_DESC;
	}

	/**
	 * Method setC_DESC.
	 * 
	 * @param c_DESC
	 *            String
	 */
	public void setC_DESC(String c_DESC) {
		C_DESC = c_DESC;
	}

	public String getC_DV_MKT_TYPE() {
		return C_DV_MKT_TYPE;
	}

	public void setC_DV_MKT_TYPE(String cDVMKTTYPE) {
		C_DV_MKT_TYPE = cDVMKTTYPE;
	}

	public String getC_MKTVOC_CODE() {
		return C_MKTVOC_CODE;
	}

	public void setC_MKTVOC_CODE(String cMKTCODE) {
		C_MKTVOC_CODE = cMKTCODE;
	}

	public String getC_MKTVOC_NAME() {
		return C_MKTVOC_NAME;
	}

	public void setC_MKTVOC_NAME(String cMKTNAME) {
		C_MKTVOC_NAME = cMKTNAME;
	}

	/**
	 * parseRowStr 解析词汇数据
	 * 
	 * 
	 * @param reqAry
	 *            String[]
	 * @param pub
	 *            YssPub
	 * @throws YssException
	 */
	public void parseRowStr(String[] reqAry) throws YssException {
		try {
			this.setC_DV_MKT_TYPE(reqAry[0]);
			this.setC_MKTVOC_CODE(reqAry[1]);
			this.setC_MKTVOC_NAME(reqAry[2]);

		} catch (Exception e) {
			throw new YssException("解析词汇数据出错", e);
		}
	}

	/**
	 * buildRowStr 按一定格式拼接数据
	 * 
	 * 
	 * @return String
	 */
	public String buildRowStr() {
		StringBuffer buf = new StringBuffer();// buf 拼接sql语句
		buf.append(C_MKTVOC_CODE).append('\t');
		buf.append(C_MKTVOC_NAME).append('\t');
		buf.append(C_DV_MKT_TYPE).append('\t');

		return buf.toString();

	}

	/**
	 * Method buildAttrToStr.
	 * 
	 * @return String
	 */
	public String buildAttrToStr() {
		return buildRowStr();
	}

	public void parseStrToAttr(String reqStr) throws YssException {
		String[] reqAry = reqStr.split("\t");
		this.setC_MKTVOC_CODE(reqAry[0]);
		this.setC_MKTVOC_NAME(reqAry[1]);
		this.setC_DV_MKT_TYPE(reqAry[2]);

	}

	/**
	 * 设置实体Bean值
	 * 
	 * @param rs
	 * 
	 * 
	 * @throws SQLException
	 *             * @throws YssException
	 */
	public void parseRsToAttr(ResultSet rs) throws SQLException {
		C_MKTVOC_CODE = rs.getString("C_MKT_CODE");
		C_MKTVOC_NAME = rs.getString("C_MKT_NAME");
		C_DV_MKT_TYPE = rs.getString("C_DV_MKT_TYPE");
	}

}
