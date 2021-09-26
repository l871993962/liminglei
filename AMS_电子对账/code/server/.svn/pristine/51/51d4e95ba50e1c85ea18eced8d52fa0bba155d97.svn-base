package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaSingleReocrd;
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

public class KmRelaSingleRecordDao extends GeneralDao  {

private KmRelaSingleRecordSqlBuilder sqlBuilder = null;
	public KmRelaSingleRecordDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (KmRelaSingleRecordSqlBuilder) sqlBuilder;
	}
	
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			String item = sqlBuilder.getSelectItem();
			String bTable = sqlBuilder.getKmMapTable();
			
			sql = buildPagingSql(paraNameList, null);
			sql = sqlBuilder.getQueryConditionSql(paraNameList,item,sql,bTable);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
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
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
//			long begin = System.currentTimeMillis();
//			System.out.println("打开结果集当前时间戳:" + begin);
			rs = pstmt.executeQuery();
//			long mid = System.currentTimeMillis();
//			System.out.println("打开结果集完成，花费时间:" + (mid - begin) + "ms");
			
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools, rs, clazz, props);
				getConvertKey(props, t);
				pojoList.add(t);
			}

//			long end = System.currentTimeMillis();
//			System.out.println("结果集滚动完成，花费时间： " + (end - mid) + "ms");

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		// TODO Auto-generated method stub
		//return super.queryByConditionPage(paraMap, page, clazz);
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);

			String item = sqlBuilder.getSelectItem();
			String bTable = sqlBuilder.getKmMapTable();
			
			sql = buildPagingSql(paraNameList, page);
			sql = sqlBuilder.getQueryConditionSql(paraNameList,item,sql,bTable);
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
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
								.valueOf(paraMap.get(valueFieldName)),conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (paraValue instanceof java.util.Date) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}
//			long begin = System.currentTimeMillis();
//			System.out.println("打开结果集当前时间戳:" + begin);
			rs = pstmt.executeQuery();
//			long mid = System.currentTimeMillis();
//			System.out.println("打开结果集完成，花费时间:" + (mid - begin) + "ms");
			
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools, rs, clazz, props);
				getConvertKey(props, t);
				pojoList.add(t);
			}

//			long end = System.currentTimeMillis();
//			System.out.println("结果集滚动完成，花费时间： " + (end - mid) + "ms");

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return super.queryByConditionCount(paraMap);
	}
	

	
	private String buildPagingSql(List<String> paraNameList, PageInation page)
			throws Exception {
		StringBuffer sPageSql = new StringBuffer();
		if(page == null)
		{
			return sqlBuilder.getKmRelaTable(paraNameList);
		}
		int begin = 0; // 起始行
		int end = 100; // 结束行
		int pageSize = 0; // 分页显示的行数
		// 根据当前页计算出要查询的起始行数和结束行数
		// 获取每页数据的大小
		pageSize = page.getPageSize();
		begin = (page.getCurrPage() - 1) * pageSize + 1;
		end = page.getCurrPage() * pageSize;
		try {
			sPageSql
					.append(" ( select a3.* from (select a2.* from  (select  a1.*, rownum rn1 from (");
			sPageSql.append(sqlBuilder.getKmRelaTable(paraNameList)).append(") a1");
			sPageSql.append(" where rownum < =").append(end);
			sPageSql.append(") a2").append(" ) a3 where rn1 >= ").append(begin).append(" ) ");
		} catch (Exception ex) {
			throw new Exception("拼接分页查询语句出现异常");
		}
		return sPageSql.toString();
	}
	/**
	 * 获取组合和公共级别的映射关系
	 * @param portCode
	 * @return
	 */
	public List<KmRelaSingleReocrd> getPortAndCommKmMap(String portCode) {
		List<KmRelaSingleReocrd> pojoList = new ArrayList<KmRelaSingleReocrd>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);
			conn = this.loadNewConnection();
			String item = sqlBuilder.getSelectItem();
			sql = sqlBuilder.getPortAndCommKmMapSql(item);
			pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, portCode);
			rs = pstmt.executeQuery();
			KmRelaSingleReocrd pojo = new KmRelaSingleReocrd();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				KmRelaSingleReocrd t = (KmRelaSingleReocrd) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
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
	
	public List<KmRelaSingleReocrd> getCompareKmMap(String portCode,String tgh) {
		List<KmRelaSingleReocrd> pojoList = new ArrayList<KmRelaSingleReocrd>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);
			conn = this.loadNewConnection();
			String item = sqlBuilder.getSelectItem();
			sql = sqlBuilder.getCompareKmMapSql(item);
			pstmt = conn.prepareStatement(sql);
			int i=1;
			pstmt.setString(i++, portCode);
			pstmt.setString(i++, tgh);
			rs = pstmt.executeQuery();
			KmRelaSingleReocrd pojo = new KmRelaSingleReocrd();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				KmRelaSingleReocrd t = (KmRelaSingleReocrd) setResultSet(rsTools,rs, pojo.getClass(), props); // 提供可以重写的方法byleeyu20130420 
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