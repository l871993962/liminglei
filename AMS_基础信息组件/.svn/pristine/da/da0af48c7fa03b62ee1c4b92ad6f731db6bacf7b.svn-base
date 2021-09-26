package com.yss.ams.syncdata.business.productinfo.util;

import java.sql.ResultSet;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.util.DateUtil;

/**
 * 组合工具类
 * @author SYL
 * 2018-09-29
 *
 */
public class PortUtil {
	
	/**
	 * BUG #220367 系统模块打开时间长,约4-20s 
	 * 将结果集转化为对象，不走反射逻辑，直接对属性进行赋值，数据量大时，通过反射进行赋值性能低
	 * @param rs
	 * @return
	 * @throws Exception
	 */
	public static Port ResultToBean(ResultSet rs) throws Exception{
		
		Port port = new Port();
		try{
			if(null != rs.getString("C_IDEN")){
				port.setId(rs.getString("C_IDEN"));
			}
			if(null != rs.getString("C_DAT_CODE")){
				port.setC_DAT_CODE(rs.getString("C_DAT_CODE"));
			}
			if(null != rs.getString("C_PORT_CODE_P")){
				port.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
			}
			if(null != rs.getString("C_PORT_CODE")){
				port.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
			}
			if(null != rs.getString("C_PORT_NAME_ST")){
				port.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
			}
			if(null != rs.getString("C_PORT_NAME_EN")){
				port.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
			}
			if(null != rs.getString("C_ASS_CODE")){
				port.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
			}
			if(null != rs.getString("C_DC_CODE")){
				port.setC_DC_CODE(rs.getString("C_DC_CODE"));
			}
			if(null != rs.getDate("D_BUILD")){
				port.setD_BUILD(DateUtil.dateToString(rs.getDate("D_BUILD"), DateUtil.LONG_DATE_FORMAT));
			}
			if(null != rs.getDate("D_CLOSE")){
				port.setD_CLOSE(DateUtil.dateToString(rs.getDate("D_CLOSE"), DateUtil.LONG_DATE_FORMAT));
			}
			if(null != rs.getDate("D_CLEAR")){
				port.setD_CLEAR(rs.getDate("D_CLEAR"));
			}
			if(null != rs.getString("C_DESC")){
				port.setC_DESC(rs.getString("C_DESC"));
			}
			if(null != rs.getString("C_PORT_NAME")){
				port.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
			}
			if(null != rs.getString("DATA_TYPE")){
				port.setdATA_TYPE(rs.getString("DATA_TYPE"));
			}
			if(null != rs.getString("C_DV_PORT_CODE")){
				port.setC_DV_PORT_CODE(rs.getString("C_DV_PORT_CODE"));
			}
			if(null != rs.getString("C_HDAY_CODE")){
				port.setC_HDAY_CODE(rs.getString("C_HDAY_CODE"));
			}
			if(null != rs.getString("C_DV_PROD_STATE")){
				port.setC_DV_PROD_STATE(rs.getString("C_DV_PROD_STATE"));
			}
			if(null != rs.getString("C_PORT_UNIT")){
				port.setC_PORT_UNIT(rs.getString("C_PORT_UNIT"));
			}
			if(null != rs.getString("C_DAT_CLS")){
				port.setC_DAT_CLS(rs.getString("C_DAT_CLS"));
			}
			if(null != rs.getString("C_CHECK_BY")){
				port.setOperator(rs.getString("C_CHECK_BY"));
			}
			if(null != rs.getString("C_CHECK_TIME")){
				port.setAuditDate(rs.getString("C_CHECK_TIME"));
			}
			if(null != rs.getString("C_UPDATE_BY")){
				port.setModifier(rs.getString("C_UPDATE_BY"));
			}
			if(null != rs.getString("C_UPDATE_TIME")){
				port.setModifyDate(rs.getString("C_UPDATE_TIME"));
			}
			if(null != rs.getString("N_CHECK_STATE")){
				port.setAuditState(rs.getInt("N_CHECK_STATE"));
			}
			
		}catch(Exception ex){
			throw new Exception("结果集转化失败！");
		}
		
		return port;
	}
}
