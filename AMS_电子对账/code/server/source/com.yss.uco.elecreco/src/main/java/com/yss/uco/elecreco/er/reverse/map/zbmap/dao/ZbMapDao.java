package com.yss.uco.elecreco.er.reverse.map.zbmap.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.support.bean.ElecRela;
/**
 * 查询数据时向上查找（要包含其上级的数据）
 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
 * 查找特定托管行数据时，同时要加上公共数据
 * @author lwz
 *
 */
public class ZbMapDao extends GeneralDao  {

	public ZbMapDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
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
	 * key:zbcode
	 * @param fileType
	 * @return
	 */
	public Map<String,ElecRela> getZbItems(String fileType)
	{
		Map<String,ElecRela> map = new HashMap<String, ElecRela>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, new ElecRelaSqlBuilder());
			// conn.setAutoCommit(false);
			sql = ((ZbMapSqlBuilder) sqlbuilder).getInnerZbItemDataSql();

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, fileType);
			rs = pstmt.executeQuery();
			ElecRela rela = new ElecRela();
			BasePojo pojo = (BasePojo) rela;
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, rela.getClass(), props); // 提供可以重写的方法byleeyu20130420 
				rela = (ElecRela) t;
				if(rela != null && !StringUtil.IsNullOrEmptyT(rela.getC_ZB_CODE()))
				{
					String code = rela.getC_ZB_CODE().trim();
					rela.setC_ZB_CODE(code);
					map.put(code, rela);
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}
	

	public List<ZbMap> getCompareZbItems(String portCode,String tgh,String fileType) {
		List<ZbMap> pojoList = new ArrayList<ZbMap>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = ((ZbMapSqlBuilder) sqlbuilder).getCompareZbMapSql();
			pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, portCode);
			pstmt.setString(i++, tgh);
			pstmt.setString(i++, fileType);
			rs = pstmt.executeQuery();
			ZbMap pojo = new ZbMap();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				ZbMap t = (ZbMap) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
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