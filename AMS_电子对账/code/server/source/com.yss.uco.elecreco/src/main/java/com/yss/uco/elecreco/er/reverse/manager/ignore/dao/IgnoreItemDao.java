package com.yss.uco.elecreco.er.reverse.manager.ignore.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

public class IgnoreItemDao extends GeneralDao  {

private IgnoreItemSqlBuilder sqlBuilder = null;
	public IgnoreItemDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (IgnoreItemSqlBuilder) sqlBuilder;
	}
	
	
	
	private HashMap<String, Object> setParaMap(HashMap<String, Object> paraMap)
	{
		if(paraMap.containsKey("ARRAY_C_PORT_CODE_OR_NULL"))//是否
		{
			if(StringUtil.IsNullOrEmptyT((String) paraMap.get("ARRAY_C_PORT_CODE_OR_NULL")))
			{
				return paraMap;
			}
			if(!paraMap.containsKey("ARRAY_C_TGH_CODE_OR_NULL"))
			{
				paraMap.put("ARRAY_C_TGH_CODE_OR_NULL", getOrgCodeByPortCodes((String) paraMap.get("ARRAY_C_PORT_CODE_OR_NULL")));
			}
		}
		return paraMap;
	}
	
	private String getOrgCodeByPortCodes(String portCodes) {
		if(null == portCodes||"".equalsIgnoreCase(portCodes.trim()))
		{
			return "";
		}
		StringBuffer orgCodes = new StringBuffer();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT C_RELA_CODE FROM T_P_AB_PORT_RELA where C_RELA_TYPE = 'RELA_ORG' and C_DV_TYPE_CODE in ('TRUSTEE','TRUSTEE_SEC','TRUSTEE_MA') and C_PORT_CODE in (select * from table(?)) ";

		try {
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));

			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				orgCodes.append(rs.getString(1));
				orgCodes.append(",");
			}
			

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		//去掉最后一个逗号
		if(orgCodes.length()>1&&",".equalsIgnoreCase(orgCodes.substring(orgCodes.length()-1)))
		{
			return orgCodes.substring(0, orgCodes.length()-1);
		}else
		{
			return orgCodes.toString();
		}
	
	}
	




	@Override
	public int queryByCondition(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return super.queryByCondition(setParaMap(paraMap));
	}



	/**
	 * 在查询数据时向上查找（要包含其上级的数据）
	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
	 * 查找特定托管行数据时，同时要加上公共数据
	 * @author lwz
	 *
	 */
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		// TODO Auto-generated method stub
		return super.queryByConditionPage(setParaMap(paraMap), page, clazz);
	}
	/**
	 * 在查询数据时向上查找（要包含其上级的数据）
	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
	 * 查找特定托管行数据时，同时要加上公共数据
	 * @author lwz
	 *
	 */
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return super.queryByConditionCount(setParaMap(paraMap));
	}
	
	/**
	 * 根据组合代码获取该组合的忽略设置
	 * @param portCode
	 * @param fileType
	 * @return
	 */
	public List<IgnoreItem> getPortDataByPortCode(String portCode) {
		List<IgnoreItem> pojoList = new ArrayList<IgnoreItem>();
	

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getPortDataSqlByPortCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			IgnoreItem pojo = new IgnoreItem();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				IgnoreItem t = (IgnoreItem) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				pojoList.add(t);
			}
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	
	public List<IgnoreItem> getCompareIgnoreItem(String portCode,String tgh,String fileType) {
		List<IgnoreItem> pojoList = new ArrayList<IgnoreItem>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getCompareIgnoreSql();
			pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, portCode);
			pstmt.setString(i++, tgh);
			pstmt.setString(i++, fileType);
			rs = pstmt.executeQuery();
			IgnoreItem pojo = new IgnoreItem();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				IgnoreItem t = (IgnoreItem) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				pojoList.add(t);
			}
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
}