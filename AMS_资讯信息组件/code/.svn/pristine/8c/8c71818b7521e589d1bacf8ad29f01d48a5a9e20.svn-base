package com.yss.ams.sec.information.modules.sv.secbasejf.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import com.yss.ams.sec.information.support.modules.sv.secbasejf.pojo.SecBaseJf;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.db.OraDbTool;

/**
 * 计费证券信息dao层
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseJfDao extends GeneralDao {

	private SecBaseJfSqlBuilder secBaseJfSqlBuilder = null;
	
	public SecBaseJfDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.secBaseJfSqlBuilder=(SecBaseJfSqlBuilder)sqlBuilder;
	}
	
	/**
	 * 根据前台查询条件获取计费证券信息数据对应的list
	 * @param paraMap
	 * @return pojoList
	 */
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaNameListByMap(paraMap, paraNameList);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			Object paraValue;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					paraValue = paraMap.get(valueFieldName);
					if (java.util.Date.class.equals(paraValue)) {
						Date dateValue = new Date(
								((java.util.Date) paraValue).getTime());
						pstmt.setDate(index, dateValue);
					} else {
						pstmt.setObject(index, paraMap.get(valueFieldName));
					}
				}
				index++;
			}
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			//由于拆借期限在订单模块引用到，所以此处修改的时候并未改动拆借期限在数据库中的存储格式，
			//而现在拆借期限字段的list界面显现在也不能通过缓存，所以做一下修改：
			//当所进行的业务为拆借时，就将C_DV_VAR_DUR 单独去除，拼接成"n天"的格式显示在list界面
			//修改时间：20130805 
			//修改人： zhengguiyu
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
//				if(rs.getString("C_SEC_VAR_CODE").endsWith("CJ_TY")){
//					SecBase secBase = (SecBase) t;
//					String varDur = secBase.getC_DV_VAR_DUR();
//					secBase.setC_DV_VAR_DUR(varDur.substring(3, varDur.length() - 1) + "天");
//				}
				
				
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
	
	/**
	 * 获取查询条件的list集合
	 * @param paraMap
	 * @param paraNameList
	 * @return
	 */
	protected List<String> getParaNameListByMap(
			HashMap<String, Object> paraMap, List<String> paraNameList) {
		String checkStateValue = "";
		if (paraMap.containsKey("C_SEC_CODE")) {
			paraNameList.add("C_SEC_CODE");
		}

		if (paraMap.containsKey("C_SEC_MKT_CODE")) {
			paraNameList.add("C_SEC_MKT_CODE");
		}

		if (paraMap.containsKey("C_SEC_ISIN_CODE")) {
			paraNameList.add("C_SEC_ISIN_CODE");
		}

		Iterator<Entry<String, Object>> it = paraMap.entrySet().iterator();
		Entry<String, Object> entry = null;

		while (it.hasNext()) {
			entry = it.next();

			if ("C_SEC_CODE".equals(entry.getKey())
					|| "C_SEC_MKT_CODE".equals(entry.getKey())
					|| "C_SEC_ISIN_CODE".equals(entry.getKey())) {
				continue;
			}

			if ("dataClass".equals(entry.getKey())) {
				continue;
			}

			if ("N_CHECK_STATE".equals(entry.getKey())) {
				checkStateValue = String.valueOf(entry.getValue());
				continue;
			}

			paraNameList.add(entry.getKey());
		}

		if (!"".equals(checkStateValue)) {
			paraNameList.add(checkStateValue);
		}

		return paraNameList;
	}
	
	/**
	 * 嵌套窗体组合关联计费证券信息SET界面查询
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
//	public List<BasePojo> getPortRelaChargingSec(HashMap<String, Object> paraMap,
//			Class<?> clazz) {
//		List<BasePojo> pojoList = new ArrayList<BasePojo>();
//		List<String> paraNameList;
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "";
//		ResultSetTools rsTools = null;
//		try {
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
//			paraNameList = getParaName(paraMap);
//			conn = this.loadNewConnection();
//			sql = this.secBaseJfSqlBuilder.getPortRelaChargingSecSql(paraNameList);
//			pstmt = conn.prepareStatement(sql);
//			// pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
//			pstmt.setString(1, paraMap.get("ARRAY_C_PORT_CODE").toString());
//			
//			rs = pstmt.executeQuery();
//
//			while (rs.next()) {
//				BasePojo t = rsTools.ResultToBean(rs, clazz);
//				pojoList.add(t);
//			}
//
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//
//		return pojoList;
//	}
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public List<BasePojo> queryRelaChargingSec(
			HashMap<String, Object> paraMap, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.secBaseJfSqlBuilder
					.getQueryRelaChargingSecSql(getParaName(paraMap));
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			/**
			 * Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
			 * 添加非空判断
			 */
			pstmt.setArray(
					1,
					OraDbTool.newInstance().sqlOverLongCondition(
							paraMap.get("ARRAY_C_SEC_CODE") == null ? ""
									: paraMap.get("ARRAY_C_SEC_CODE")
											.toString(), conn));
			/** End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错 */

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, SecBaseJf.class);
				/*
				 * BUG #108593 产品信息》产品基本信息》交易渠道设置
				 * 此处审核状态显示为交易渠道关联纪录是否审核,而非交易渠道审核状态
				 */
				((SecBaseJf) t).setAuditState(rs.getInt("N_CHECK_STATE"));
				pojoList.add(t);
//				SecBase secBase = new SecBase();
//				secBase.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
//				secBase.setC_CHARGE(rs.getString("C_CHARGE"));
//				secBase.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
//				secBase.setAuditState(rs.getInt("N_CHECK_STATE"));
//				secBase.setOperator(rs.getString("C_CHECK_BY"));
//				secBase.setAuditDate(rs.getString("C_CHECK_TIME"));				
//				secBase.setModifier(rs.getString("C_UPDATE_BY"));
//				secBase.setModifyDate(rs.getString("C_UPDATE_TIME"));
//				//secBase.setId(rs.getString("C_IEDN"));
//				pojoList.add(secBase);
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
	
	/**
	 * 嵌套窗体LIST界面查询计费证券信息的数量
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @return
	 */
	public int queryRelaChargingSecCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = this.secBaseJfSqlBuilder
					.getQueryRelaChargingSecCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			/**
			 * Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
			 * 添加非空判断
			 */
			pstmt.setArray(
					1,
					OraDbTool.newInstance().sqlOverLongCondition(
							paraMap.get("ARRAY_C_SEC_CODE") == null ? ""
									: paraMap.get("ARRAY_C_SEC_CODE")
											.toString(), conn));
			/** End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错 */
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @param page
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryPortRelaChargingSec(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = secBaseJfSqlBuilder.getQueryPortRelaChargingSecSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;			
			pstmt.setObject(index++, paraMap.get("C_SEC_VAR_CODE"));
			pstmt.setArray(
					index++,
					OraDbTool.newInstance().sqlOverLongCondition(
							String.valueOf(paraMap.get("ARRAY_C_PORT_CODE")),
							conn));
			if (paraMap.containsKey("C_SFJT")) {
				pstmt.setObject(index++, paraMap.get("C_SFJT"));
			}
			
			
//			Object paraValue;
//			for (String valueFieldName : paraNameList) {
//				if ("N_CHECK_STATE".equals(valueFieldName)) {
//					continue;
//				}
//
//				if (valueFieldName.startsWith("ARRAY_")) {
//					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//							.valueOf(paraMap.get(valueFieldName)),conn));
//				} else {
//					paraValue = paraMap.get(valueFieldName);
//					if (java.util.Date.class.equals(paraValue)) {
//						Date dateValue = new Date(
//								((java.util.Date) paraValue).getTime());
//						pstmt.setDate(index, dateValue);
//					} else {
//						pstmt.setObject(index, paraMap.get(valueFieldName));
//					}
//
//				}
//
//				index++;
//			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
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
	
	/**
	 * 嵌套窗体【选择】窗体查询计费证券信息的数量
	 * 
	 * added by HeLiang.2016-09-08.STORY #31596 运营费用-支持资产净值扣不计费证券需求 
	 * 
	 * @param paraMap
	 * @return
	 */
	public int queryPortRelaChargingSecCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = secBaseJfSqlBuilder.getQueryPortRelaChargingSecCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;			
			pstmt.setObject(index++, paraMap.get("C_SEC_VAR_CODE"));
			pstmt.setArray(
					index++,
					OraDbTool.newInstance().sqlOverLongCondition(
							String.valueOf(paraMap.get("ARRAY_C_PORT_CODE")),
							conn));
			if (paraMap.containsKey("C_SFJT")) {
				pstmt.setObject(index++, paraMap.get("C_SFJT"));
			}
			
//			Object paraValue;
//			for (String valueFieldName : paraNameList) {
//				if ("N_CHECK_STATE".equals(valueFieldName)) {
//					continue;
//				}
//
//				if (valueFieldName.startsWith("ARRAY_")) {
//					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//							.valueOf(paraMap.get(valueFieldName)),conn));
//				} else {
//					paraValue = paraMap.get(valueFieldName);
//					if (java.util.Date.class.equals(paraValue)) {
//						Date dateValue = new Date(
//								((java.util.Date) paraValue).getTime());
//						pstmt.setDate(index, dateValue);
//					} else {
//						pstmt.setObject(index, paraMap.get(valueFieldName));
//					}
//
//				}
//
//				index++;
//			}
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				recCount = rs.getInt("CNT");
			}

		} catch (Exception ex) {
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return recCount;
	}
	
}
