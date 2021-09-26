package com.yss.uco.elecreco.er.reverse.manager.info.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

public class ErReveInfoDao extends GeneralDao  {

	private ErReveInfoSqlBuilder sqlBuilder = null;
	private Set<String> noNeedFileds = null;
	public ErReveInfoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErReveInfoSqlBuilder) sqlBuilder;
	}

	
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(paraMap.containsKey("C_FK_STATE"))
		{
			if("FK_YFK".equalsIgnoreCase(paraMap.get("C_FK_STATE").toString()))
			{
				paraMap.put("C_FK_STATE_YFK", "0");
			}else
			{
				paraMap.put("C_FK_STATE_WFK", "0");
			}
			paraMap.remove("C_FK_STATE");
		}
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.putAll(paraMap);
		//只展示日报
		String[] rptTypes = new String[]{"01"};
		//String[] rptTypes = new String[]{"01","03","04","05","06"};
		if(paraMap.containsKey("C_FILE_TYPE"))
		{
			String fileType = String.valueOf(paraMap.get("C_FILE_TYPE"));
			if("1011".equalsIgnoreCase(fileType)||"1001".equalsIgnoreCase(fileType)||"1031".equalsIgnoreCase(fileType))
			{
				rptTypes = new String[]{"01"};
			}else
			{
				rptTypes = new String[]{"03","04","05","06"};
			}
		}
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			
			conn = this.loadNewConnection();

			sql = sqlBuilder.getQueryConditionSql(paraNameList, paras, rptTypes);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paras.get(valueFieldName)),conn));
					} else {
						paraValue = paras.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue =  new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			
			
			rs = pstmt.executeQuery();
			
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			ErReveInfo info = null ;
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools, rs, clazz, props);
				getConvertKey(props, t);
				info = (ErReveInfo) t;
				info.setC_B_DATA(rs.getString("C_B_DATA"));
				info.setC_D_DATA(rs.getString("C_D_DATA"));
				info.setC_FK_STATE(rs.getString("C_FK_STATE"));
				pojoList.add(t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	protected Set<String> getNoNeedFiled()
	{
		if(this.noNeedFileds == null || this.noNeedFileds.size() == 0)
		{
			this.noNeedFileds = new HashSet<String>();
			this.noNeedFileds.add("c_B_DATA");
			this.noNeedFileds.add("c_D_DATA");
			this.noNeedFileds.add("c_FK_STATE");
		}
		return this.noNeedFileds;
	}


	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		int  i= 0;
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		if(paraMap.containsKey("C_FK_STATE"))
		{
			if("FK_YFK".equalsIgnoreCase(paraMap.get("C_FK_STATE").toString()))
			{
				paraMap.put("C_FK_STATE_YFK", "0");
			}else
			{
				paraMap.put("C_FK_STATE_WFK", "0");
			}
			paraMap.remove("C_FK_STATE");
		}
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.putAll(paraMap);
		//只展示日报
		String[] rptTypes = new String[]{"01"};
		//String[] rptTypes = new String[]{"01","03","04","05","06"};
		if(paraMap.containsKey("C_FILE_TYPE"))
		{
			String fileType = String.valueOf(paraMap.get("C_FILE_TYPE"));
			if("1011".equalsIgnoreCase(fileType)||"1001".equalsIgnoreCase(fileType)||"1031".equalsIgnoreCase(fileType))
			{
				rptTypes = new String[]{"01"};
			}else
			{
				rptTypes = new String[]{"03","04","05","06"};
			}
		}
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getQueryConditionSql(paraNameList, paras, rptTypes);
			sql = "select count(1) from ( "+sql+" ) A ";
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("N_CHECK_STATE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equalsIgnoreCase(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paras.get(valueFieldName)),conn));
					} else {
						paraValue = paras.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							java.sql.Date dateValue =  new java.sql.Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
			
			
			rs = pstmt.executeQuery();
			
			
			while (rs.next()) {
				i = rs.getInt(1);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return i;
	}


	public String unSdDzResult(List<ErReveInfo> list) {
		
		String result = "fail";
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			for(ErReveInfo info : list)
			{
				updateLockStateById(info, ReveDzCons.INFO_RLS_SDZT_BSD, conn);
			}
			conn.commit();
			conn.setAutoCommit(true);
			result = "success";
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			releaseConnection(conn);
		}
		return result;
	}

	
	private void updateLockStateById(ErReveInfo erinfo,String lockState,Connection conn)
	{
		PreparedStatement pstmt = null;
		try {
			//conn = this.loadNewConnection();
			String id = erinfo.getId();
			if(id == null || "".equalsIgnoreCase(id.trim()))
			{
				throw new Exception("ID不能为空！");
			}
			String sql = sqlBuilder.getUpdateLockStateSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, lockState);
			pstmt.setString(i++,erinfo.getId());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}

	public String sdDzResult(List<ErReveInfo> list) {
		String result = "fail";
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			for(ErReveInfo info : list)
			{
				updateLockStateById(info, ReveDzCons.INFO_RLS_SDZT_SD, conn);
			}
			conn.commit();
			conn.setAutoCommit(true);
			result = "success";
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			releaseConnection(conn);
		}
		return result;
	}

	private void updateDzResultById(ErReveInfo erinfo,String dzResult,String xgsm, Connection conn)
	{
		PreparedStatement pstmt = null;
		try {
			//conn = this.loadNewConnection();
			String id = erinfo.getId();
			if(id == null || "".equalsIgnoreCase(id.trim()))
			{
				throw new Exception("ID不能为空！");
			}
			String sql = sqlBuilder.getUpdateDzResultSql();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, dzResult);
			pstmt.setString(i++, xgsm);
			pstmt.setString(i++,erinfo.getId());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}

	public String editDzResult(List<ErReveInfo> list,String dzResult, String xgsm) {
		String result = "fail";
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			for(ErReveInfo info : list)
			{
				updateDzResultById(info, dzResult,xgsm, conn);
			}
			conn.commit();
			conn.setAutoCommit(true);
			result = "success";
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			releaseConnection(conn);
		}
		return result;
	}


	
}