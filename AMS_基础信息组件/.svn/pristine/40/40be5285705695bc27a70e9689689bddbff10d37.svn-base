
package com.yss.ifa.szt.tool.param;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.StringUtil;

/**
 * @classDesc 组合级别的核算参数
 * @version 1.0 2011-10-12
 * @author yh
 */
public class AdmPortActParams {
	
	private final String GROUP = "GROUP_PARAM_CUSTOM";
	
	private final String PORT = "PORT_PARAM_CUSTOM";
	
	private String port ;
	
	private Date accDate ;
	/**
	 * 电子对账综合参数:key为综合参数代码,value值为参数的值
	 */
	private Map<String,String> elecParams = null;
//	/**
//	 * 核算参数:key为综合参数代码,value值为参数的值
//	 */
//	private Map<String,String> ucoParams = null;
//	/**
//	 * 核算综合参数,key为综合参数代码,value值为参数的值
//	 */
//	public  HashMap<String,String> actSummaryParams = null;
	
	private Connection dbConn = null;
	
	/**
	 * 系统日志类
	 */
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	/**
	 * @return the dbConn
	 */
	public Connection getDbConn() {
		return dbConn;
	}

	/**
	 * @param dbConn the dbConn to set
	 */
	public void setDbConn(Connection dbConn) {
		this.dbConn = dbConn;
	}
	/**
	 * 构造方法
	 * @param port	组合代码
	 * @param accDate 核算日期
	 */
	public AdmPortActParams(String port, Date accDate){
		this.port = port;
		this.accDate = accDate;
	}
	
	/**
	 * 初始化获取核算相关参数
	 * @throws Exception 
	 */
	public void initActParams() throws Exception{
//		//获取核算综合参数
//		if(isGroupPort()){//分组基金
//			ucoParams = getActSummaryParams_Group();
//		}else{
//			ucoParams = getActSummaryParams();
//		}
		elecParams = new HashMap<String, String>();
		getCustomParams(elecParams,this.port, this.PORT);
		getCustomParams(elecParams,this.port, this.GROUP);
		String parentCode = getParentCode(this.port);
		boolean isGroup = !StringUtil.IsNullOrEmptyT(parentCode);
		if(isGroup)
		{
			getCustomParams(elecParams,parentCode, this.PORT);
			getCustomParams(elecParams,parentCode, this.GROUP);
		}
		getTemplateParams(elecParams);
		this.logger.debug(elecParams.toString());
	}
	
	/**
	 * 获取上级组合代码
	 * @param portCode
	 * @return
	 */
	private String getParentCode(String portCode) {
		String parentPortCode = "";
		String sql = "";
		PreparedStatement pst = null;
		ResultSet rs = null;		
		try {
			sql = "SELECT * FROM T_P_AB_PORT A WHERE A.N_CHECK_STATE = 1 AND A.C_PORT_CODE_P IS NOT NULL AND A.C_PORT_CODE_P<>' '  AND A.C_PORT_CODE = ? ";
			pst = this.getDbConn().prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			if(rs.next()){
				parentPortCode = rs.getString("C_PORT_CODE_P");
			}
		} catch (SQLException e) {
			logger.error("判断当前组合是为分组基金组合时失败!", e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return parentPortCode;
	}
	

	/**
	 * STORY #48560整合电子对账业务类参数到便于用户设置和组件解耦
	 * @param actSummaryParams
	 */
	private void getCustomParams(Map<String,String> actSummaryParams,String portCode,String customType)
	{
		ResultSet rs = null;
		PreparedStatement pst = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT C_DSP_CODE, C_DV_PARAMS_VALUE ");
		buffer.append(" FROM T_D_ER_DSP_VALUE  ");
		if(this.GROUP.equalsIgnoreCase(customType))
		{
			buffer.append(" WHERE C_PORT_CODE in ( SELECT C_GROUP_CODE FROM T_P_AB_GROUP_RELA where C_PORT_CODE = ? ) ");
		}else
		{
			buffer.append(" WHERE C_PORT_CODE = ? ");
		}
		buffer.append(" AND D_BEGIN <= ? ");
		buffer.append(" AND D_END >= ? ");
		buffer.append(" AND N_CHECK_STATE = 1 ");
		buffer.append(" AND C_DV_PARAM_TYPE = ? ");
		try {
			pst = this.getDbConn().prepareStatement(buffer.toString());
			int index = 1;
			pst.setString(index++, portCode);
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			pst.setString(index++, customType);
			rs = pst.executeQuery();
			while(rs.next()){
				String dspCode = rs.getString("C_DSP_CODE");
				if(!actSummaryParams.containsKey(dspCode))
				{
					actSummaryParams.put(dspCode, rs.getString("C_DV_PARAMS_VALUE"));
				}
			}
		} catch (SQLException e) {
			logger.error("获取电子对账综合参数时失败!", e);
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY #48560整合电子对账业务类参数到便于用户设置和组件解耦
	 * @param actSummaryParams
	 */
	private void getTemplateParams(Map<String,String> actSummaryParams)
	{
		ResultSet rs = null;
		PreparedStatement pst = null;
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT C_DSP_CODE, C_DV_PLAT_VALUE as C_DV_PARAMS_VALUE ");
		buffer.append(" FROM T_D_ER_DSP_PARA  ");
		try {
			pst = this.getDbConn().prepareStatement(buffer.toString());
			rs = pst.executeQuery();
			while(rs.next()){
				String dspCode = rs.getString("C_DSP_CODE");
				if(!actSummaryParams.containsKey(dspCode))
				{
					actSummaryParams.put(dspCode, rs.getString("C_DV_PARAMS_VALUE"));
				}
			}
		} catch (SQLException e) {
			logger.error("获取电子对账综合参数时失败!", e);
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 获取核算综合参数
	 * 
	 * 添加参数生效起始时间,添加针对特殊参数获取的特殊处理
	 * STORY #38319 上交所深交所 质押式回购计息规则调整评估 
	 * xiaozhilong 20170327
	 * @throws Exception 
	 */
	public HashMap<String,String> getActSummaryParams() throws Exception{
		ResultSet rs = null;
		PreparedStatement pst = null;
		HashMap<String,String> actSummaryParams = new HashMap<String,String>();
		StringBuffer buffer = new StringBuffer();
		buffer.append(" SELECT C_DSP_CODE, C_DV_PLAT_VALUE AS C_DV_PARAMS_VALUE, DATE'1900-1-1' AS D_BEGIN ");
		buffer.append(" FROM T_S_DSP_PARA A ");
		buffer.append(" WHERE NOT EXISTS (SELECT C_DSP_CODE ");
		buffer.append(" FROM V_P_AO_PARAMS B ");
		buffer.append(" WHERE B.C_PORT_CODE = ? ");
		buffer.append(" AND D_BEGIN <= ? ");
		buffer.append(" AND D_END >= ? ");
		buffer.append(" AND N_CHECK_STATE = 1 ");
		buffer.append(" AND B.C_DSP_CODE = A.C_DSP_CODE) ");
		buffer.append(" UNION ALL ");
		buffer.append(" SELECT C_DSP_CODE, C_DV_PARAMS_VALUE, D_BEGIN ");
		buffer.append(" FROM V_P_AO_PARAMS ");
		buffer.append(" WHERE C_PORT_CODE = ? ");
		buffer.append(" AND D_BEGIN <= ? ");
		buffer.append(" AND D_END >= ? ");
		buffer.append(" AND N_CHECK_STATE = 1 ");
		try {
			pst = this.getDbConn().prepareStatement(buffer.toString());
			int index = 1;
			pst.setString(index++, port);
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			pst.setString(index++, port);
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
			rs = pst.executeQuery();
			while(rs.next()){
				actSummaryParams.put(rs.getString("C_DSP_CODE"), rs.getString("C_DV_PARAMS_VALUE"));
			}
		} catch (SQLException e) {
			logger.error("获取核算综合参数时失败!", e);
		} finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
				
		}
		return actSummaryParams;
	}
	
//	/**
//	 * 分组基金获取核算综合参数
//	 * @throws Exception 
//	 */
//	private HashMap<String,String> getActSummaryParams_Group() throws SQLException, YssException{
//		ResultSet rs = null;
//		PreparedStatement pst = null;
//		HashMap<String,String> actSummaryParams = new HashMap<String,String>();
//		String sql = "SELECT C_DSP_CODE ,C_DV_PARAMS_VALUE, D_BEGIN FROM V_P_AO_PARAMS A WHERE C_PORT_CODE = ? AND D_BEGIN <= ? AND D_END >= ? AND N_CHECK_STATE = 1 " +
//        	" UNION ALL " + 
//	        " SELECT C_DSP_CODE ,C_DV_PARAMS_VALUE, D_BEGIN FROM V_P_AO_PARAMS B WHERE C_PORT_CODE = (SELECT C_PORT_CODE_P FROM T_P_AB_PORT  " + 
//	        " WHERE N_CHECK_STATE = 1 AND C_PORT_CODE = ? ) AND N_CHECK_STATE = 1 AND NOT EXISTS (SELECT C_DSP_CODE FROM V_P_AO_PARAMS C  " + 
//	        " WHERE C.C_PORT_CODE = ? AND D_BEGIN <= ? AND D_END >= ? AND N_CHECK_STATE = 1 AND C.C_DSP_CODE = B.C_DSP_CODE) " + 
//	        " UNION ALL " + 
//	        " SELECT C_DSP_CODE ,C_DV_PLAT_VALUE AS C_DV_PARAMS_VALUE, DATE'1900-1-1' AS D_BEGIN FROM V_S_DSP_PARA D WHERE NOT EXISTS(SELECT C_DSP_CODE FROM V_P_AO_PARAMS E  " + 
//	        " WHERE E.C_PORT_CODE = ? AND D_BEGIN <= ? AND D_END >= ? AND N_CHECK_STATE = 1 AND E.C_DSP_CODE = D.C_DSP_CODE) AND NOT EXISTS ( " + 
//	        " SELECT C_DSP_CODE ,C_DV_PARAMS_VALUE FROM V_P_AO_PARAMS F WHERE C_PORT_CODE = (SELECT C_PORT_CODE_P FROM T_P_AB_PORT  " + 
//	        " WHERE N_CHECK_STATE = 1 AND C_PORT_CODE = ?) AND N_CHECK_STATE = 1 AND NOT EXISTS (SELECT C_DSP_CODE FROM V_P_AO_PARAMS G  " + 
//	        " WHERE G.C_PORT_CODE = ? AND D_BEGIN <= ? AND D_END >= ? AND N_CHECK_STATE = 1 AND G.C_DSP_CODE = F.C_DSP_CODE) AND D.C_DSP_CODE = F.C_DSP_CODE)";
//		try {
//			pst = this.getDbConn().prepareStatement(sql);
//			int index = 1;
//			pst.setString(index++, port);
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setString(index++, port);
//			pst.setString(index++, port);
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setString(index++, port);
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setString(index++, port);
//			pst.setString(index++, port);
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			pst.setDate(index++, new java.sql.Date(accDate.getTime()));
//			rs = pst.executeQuery();
//			while(rs.next()){
//				actSummaryParams.put(rs.getString("C_DSP_CODE"), rs.getString("C_DV_PARAMS_VALUE"));
//			}
//		} catch (SQLException e) {
//			logger.error("分组基金获取核算综合参数时失败!", e);
//		} finally{
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst);
//		}
//		return actSummaryParams;
//	}
	
//	/**
//	 * 判断当前组合是为分组基金组合
//	 * @return
//	 * @throws SQLException 
//	 */
//	private boolean isGroupPort() throws SQLException{
//		boolean booGroup = false;
//		String sql = "";
//		PreparedStatement pst = null;
//		ResultSet rs = null;		
//		try {
//			sql = "SELECT * FROM T_P_AB_PORT A WHERE A.N_CHECK_STATE = 1 AND A.C_PORT_CODE_P IS NOT NULL AND A.C_PORT_CODE_P<>' '  AND A.C_PORT_CODE = ? ";
//			pst = this.getDbConn().prepareStatement(sql);
//			pst.setString(1, port);
//			rs = pst.executeQuery();
//			if(rs.next()){
//				booGroup = true;
//			}
//		} catch (SQLException e) {
//			logger.error("判断当前组合是为分组基金组合时失败!", e);
//		}finally{
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst);
//		}
//		
//		return booGroup;
//	}
	/**
	 * 根据参数代码获取电子对账综合参数的值
	 * @param paramCode
	 * @return
	 */
	public String getElecParamValue(ErDspParamCodeEnum code)
	{
		String value = "";
		if(this.elecParams != null && this.elecParams.containsKey(code.toString()))
		{
			return this.elecParams.get(code.toString());
		}else if (this.elecParams != null) //没取到值，记录日志
		{
			this.logger.info("电子对账综合参数"+"("+this.elecParams.toString()+")"+"不包含："+code.toString());
		}
		return value;
	}
	
}
	
	
