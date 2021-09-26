package com.yss.ams.product.information.util.port;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
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
				port.setD_CLEAR(new Date(rs.getDate("D_CLEAR").getTime()));
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
			if(null != rs.getString("C_ASSETS_CODE")){
				port.setC_ASSETS_CODE(rs.getString("C_ASSETS_CODE"));
			}
			
		}catch(Exception ex){
			throw new Exception("结果集转化失败！");
		}
		
		return port;
	}
	
	/*public static com.yss.framework.api.common.co.Port convert2FastPort(Port port) {
		String portStr = JsonUtil.toString(port);
		if (StringUtil.IsNullOrEmpty(portStr)) {
			return new com.yss.framework.api.common.co.Port();
		} else {
			return (com.yss.framework.api.common.co.Port) JsonUtil.toBean(portStr,com.yss.framework.api.common.co.Port.class);
		}
	}*/

	/*public static  List<com.yss.framework.api.common.co.Port> convert2FastListPort(List<Port> portList) {
		String portListJson = JsonUtil.toString(portList);
		if (StringUtil.IsNullOrEmpty(portListJson)) {
			return new ArrayList<com.yss.framework.api.common.co.Port>();
		} else {
			return JsonUtil.toList(portListJson, com.yss.framework.api.common.co.Port.class);
		}
	}*/

	/*public static  List<Port> convert2ListPort(List<com.yss.framework.api.common.co.Port> fastPortList) {
		String fastPortListJson = JsonUtil.toString(fastPortList);
		if (StringUtil.IsNullOrEmpty(fastPortListJson)) {
			return new ArrayList<Port>();
		} else {
			return JsonUtil.toList(fastPortListJson, Port.class);
		}
	}*/
	
	public static List<com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A> convert2FastListAssetsTree(
			List<AssetsTree_A> assetsTreeList) {
		String portListJson = JsonUtil.toString(assetsTreeList);
		if (StringUtil.IsNullOrEmpty(portListJson)) {
			return new ArrayList<com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A>();
		} else {
			return JsonUtil.toList(portListJson, com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A.class);
		}
	}
}
