package com.yss.ams.sec.information.modules.sv.base.dao;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.yss.ams.base.information.support.util.tempTableUtil.TempTableUtil;
import com.yss.ams.sec.information.support.cache.SecBaseCache;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo.SecTransfer;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.CfSec;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecShortPojo;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ShortDataListPackage;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.ams.sec.information.util.ResultSetUtil;

/**
 * 证券信息dao
 * @author 马向峰 拆分 20170627
 */
public class SecBaseDao extends GeneralDao {
	
	
	public static String format = "yyyyMMdd HH:mm:ss";

	public SecBaseDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String value = "";
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
				// 代码合并：STORY #97948 富国基金-债券基本信息界面新增制作时间和审核时间的查询条件选项
				if ("C_CHECK_BEGIN".equals(valueFieldName)) {	
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					pstmt.setString(index, YssFun.formatDate(YssFun.parseDate(value, format), format));
					index++;
					continue;
				}
				if ("D_CREATE_BEGIN".equals(valueFieldName)) {
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					pstmt.setString(index, YssFun.formatDate(YssFun.parseDate(value, format), format));
					index++;
					continue;
				}
				if ("C_CHECK_END".equals(valueFieldName) || "D_CREATE_END".equals(valueFieldName)) {
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					Calendar cal = Calendar.getInstance();
					cal.setTime(YssFun.parseDate(value, format));
					cal.add(Calendar.DATE, 1);
					pstmt.setString(index, YssFun.formatDate(cal.getTime(), format));
					index++;
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
			
			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors((BasePojo)clazz.newInstance());
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, clazz);
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
//				if(rs.getString("C_SEC_VAR_CODE").endsWith("CJ_TY")){
//					SecBase secBase = (SecBase) t;
//					String varDur = secBase.getC_DV_VAR_DUR();
//					secBase.setC_DV_VAR_DUR(varDur.substring(3, varDur.length() - 1) + "天");
//				}
				
				//add by lisi 2018.10.23 STORY 51261 易方达 彭博证券信息读取海外市场
				if(!StringUtil.IsNullOrEmptyT(rs.getString("c_sec_var_code"))// add by wzh 2018-11-08 BUG #228307 人保资产-综合证券页面证券名称中查询“”中石化”报错
						&&(rs.getString("c_sec_var_code").substring(0, 2).equalsIgnoreCase("GP") 
						|| rs.getString("c_sec_var_code").substring(0, 2).equalsIgnoreCase("ZQ")
						|| rs.getString("c_sec_var_code").substring(0, 2).equalsIgnoreCase("HG")) 
						&& rs.getString("C_MKT_CODE_JC") != null){
					SecBase secBase = (SecBase) t;
					 secBase.setC_MKT_CODE_JC(rs.getString("C_MKT_CODE_JC"));
				}

				if (ResultSetUtil.isFindColumn(rs, "C_UPDATE_TIMEWEB")) {
					((SecBase) t).setC_UPDATE_TIMEWEB(rs.getString("C_UPDATE_TIMEWEB"));
				}
				
				if (ResultSetUtil.isFindColumn(rs, "C_SYSOURCE")) {
					((SecBase) t).setC_SYSOURCE(rs.getString("C_SYSOURCE"));
				}
				
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

	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String value = "";
		int pageCnt = 0;
		try {
			paraNameList = getParaNameListByMap(paraMap, paraNameList);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlbuilder.getQueryConditionCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				
				// 代码合并：STORY #97948 富国基金-债券基本信息界面新增制作时间和审核时间的查询条件选项
				if ("C_CHECK_BEGIN".equals(valueFieldName)) {	
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					pstmt.setString(index, YssFun.formatDate(YssFun.parseDate(value, format), format));
					index++;
					continue;
				}
				if ("D_CREATE_BEGIN".equals(valueFieldName)) {
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					pstmt.setString(index, YssFun.formatDate(YssFun.parseDate(value, format), format));
					index++;
					continue;
				}
				if ("C_CHECK_END".equals(valueFieldName) || "D_CREATE_END".equals(valueFieldName)) {
					value = (String) paraMap.get(valueFieldName);
					value = value.replace("-", "").concat(" 00:00:00");
					Calendar cal = Calendar.getInstance();
					cal.setTime(YssFun.parseDate(value, format));
					cal.add(Calendar.DATE, 1);
					pstmt.setString(index, YssFun.formatDate(cal.getTime(), format));
					index++;
					continue;
				}
				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}
				index++;
			}
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				pageCnt = rs.getInt("CNT");
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			// update by chenwenhai 2013073 关闭游标
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pageCnt;
	}

	/**
	 * 解析参数名并封装为list集合
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
	 * 通过证券内码获取其每手数量
	 * 
	 * @param secCode
	 *            证券内码
	 * @return 证券内码对应的每手数量
	 */
	public BigDecimal getHDAmountBySecCode(String secCode) {
		BigDecimal hdAmount = BigDecimal.ONE;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = this.loadNewConnection();
			SecBaseSqlBuilder secBaseSqlBuilder = new SecBaseSqlBuilder();
			sql = secBaseSqlBuilder.getHDAmountBySecCodeSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			pstmt.setString(1, secCode);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				hdAmount = rs.getBigDecimal("N_AMOUNT_HD");
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return hdAmount;
	}
	
	/**
	 * 根据证券代码查询证券信息
	 * @param secCode	证券代码
	 * @return
	 */
	public BasePojo querySecBaseBySecCode(String secCode) {
		BasePojo basePojo = null;
		String sql = " select  * from T_P_SV_SEC_BASE where c_sec_code = ? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, secCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				basePojo = rsTools.ResultToBean(rs, SecBase.class);
			}
		} catch (Exception e) {
			
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return basePojo;
	}
	
	/**
	 * 根据证券的 币种代码、交易市场代码、证券品种代码、资产负债的品种 查询证券信息
	 * @param filter
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getAllDataList(SecBase filter) throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql(filter);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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

	/* START 数据服务方法 */

	/**
	 * 得到所有数据
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getAllDataList() throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(SecBase.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	 *  START 数据服务方法
	 *  BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内
	 *  增加分页
	 * 
    */
	public List<SecBase> getAllDataList(PageInation pageInation) throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();
			sql = buildPagingSql(sql, pageInation);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(SecBase.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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

	
	public SecBase getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		String[] paramArr = null;
		try {
			paramArr = code.split("\t");

			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramArr[0]);
			pstmt.setString(2, paramArr[1]);
			pstmt.setString(3, paramArr[2]);
			pstmt.setString(4, paramArr[3]);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return t;
	}

	/**
	 * 根据证券品种代码查询
	 * @param types 证券品种代码
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListByTypes(String[] types)
			throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTypes(types);

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String type : types) {
				if (type != null && type.trim().length() > 0) {
					pstmt.setString(i++, type + "%");
				}
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	 * 将证券信息封装为map集合
	 * 	Map<证券代码,证券名称>
	 * @param listSevVar
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listSevVar)
			throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		String[] arrSecVar = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		try {

			dsServiceBuilder = new SecBaseInfoSqlBuilder();

			conn = this.loadNewConnection();
			if (listSevVar != null) {
				arrSecVar = new String[listSevVar.size()];
				arrSecVar = listSevVar.toArray(arrSecVar);
				sql = dsServiceBuilder.getDataListByTypes(arrSecVar);
			} else {
				sql = dsServiceBuilder.getAllDataSql();
			}
			pstmt = conn.prepareStatement(sql);
			if (arrSecVar != null) {
				int i = 1;
				for (String type : arrSecVar) {
					if (type != null && type.trim().length() > 0) {
						pstmt.setString(i++, type + "%");
					}
				}
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(rs.getString(SecBaseColumnName.c_SEC_CODE
						.toString()), rs.getString(SecBaseColumnName.c_SEC_NAME
						.toString()));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return keyValueMap;
	}

	/**
	 * dao层方法已注释，返回 null
	 * @author 马向峰 20170627
	 * @param keys
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();
		/*
		 * Connection conn = null; PreparedStatement pstmt = null; ResultSet rs
		 * = null;
		 * 
		 * String sql = ""; ResultSetTools rsTools = null;
		 * 
		 * SecBaseInfoSqlBuilder dsServiceBuilder = null; SecBase t = null; try
		 * { dsServiceBuilder = new SecBaseInfoSqlBuilder(); rsTools = new
		 * ResultSetTools(dbNameResolver, dsServiceBuilder);
		 * 
		 * conn = this.loadNewConnection(); conn.setAutoCommit(false);
		 * 
		 * sql = dsServiceBuilder.getDataByCode();
		 * 
		 * pstmt = conn.prepareStatement(sql); pstmt.setArray(1,
		 * sqlOverLongCondition(keys));
		 * 
		 * logger.debug(sql);
		 * 
		 * rs = pstmt.executeQuery(); conn.commit(); conn.setAutoCommit(true);
		 * 
		 * while (rs.next()) { t = rsTools.ResultToBeanGeneric(rs,
		 * SecBase.class); pojoList.add(t); }
		 * 
		 * } catch (Exception ex) { throw new DataAccessException("查询失败：" +
		 * ex.getMessage(), ex); } finally { closeResultSetFinal(rs);
		 * closeStatementFinal(pstmt); releaseConnection(conn); }
		 */
		return pojoList;
	}

	/**
	 * 根据证券品种类型和交易市场查询
	 * @param types	证券品种类型
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListByTypesAndMkt(String[] types)
			throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTypesAndMkt(types);

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String type : types) {
				if (type != null && type.trim().length() > 0) {
					pstmt.setString(i++, type + "%");
				}
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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

	public List<SecBase> getDataListByDaes(String parameter)
			throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		List<String> lstPara = new ArrayList<String>();
		HashMap<String, String> mapParam = new HashMap<String, String>();
		try {
			if (parameter != null && parameter.trim().length() > 0) {
				String[] arrSplit = parameter.split(";");
				for (String sSplit : arrSplit) {
					if (sSplit != null && sSplit.trim().length() > 0) {
						String[] arrPara = sSplit.split("=");
						String value = "";
						if (arrPara.length > 1) {
							value = arrPara[1];
						}
						if (arrPara.length > 0) {
							lstPara.add(arrPara[0]);
							mapParam.put(arrPara[0], value);
						}
					}
				}
			}
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataListByDae(lstPara);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String key : lstPara) {
				if (key != null && key.trim().length() > 0) {
					pstmt.setString(i++, mapParam.get(key));
				}
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	
	/*
	 * 根据证券内码查询证券信息
	 */
	public BasePojo getSecBaseInfoDataBySecCode(String secCode) {
		SecBase secInfo = null;
		//BUG #226790 【招商基金】证券信息访问量过高造成系统批量闪退 优先获取缓存
		SecBaseCache secCache = CacheManager.getInstance().getCache(CacheGroup.SECBASE);
		if(!StringUtil.IsNullOrEmptyT(secCode)){
			secInfo = secCache.getCacheByKey(secCode);		
		}
		return 	secInfo;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "";
//		ResultSetTools rsTools = null;
//
//		SecBaseInfoSqlBuilder dsServiceBuilder = null;
//		SecBase t = null;
//		try {
//
//			dsServiceBuilder = new SecBaseInfoSqlBuilder();
//			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
//
//			conn = this.loadNewConnection();
//
//			sql = dsServiceBuilder.getDataBySecCode();
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, secCode);
//
//			logger.debug(sql);
//
//			rs = pstmt.executeQuery();
//
//			// 南方基金性能优化 zhanghualin 2017-3-15
//			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
//			while (rs.next()) {
//				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
//				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
//			}
//
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			closeResultSetFinal(rs);
//			closeStatementFinal(pstmt);
//			releaseConnection(conn);
//		}
//		return t;
	}
	
	/*
	 * 根据证券内码查询证券信息
	 */
	public BasePojo getSecBaseInfoDataBySecCodeFromDb(String secCode) {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {

			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataBySecCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, secCode);

			logger.debug(sql);

			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return t;
	}

	/**
	 * BUG #338952 【回归】华夏基金（300.7.20200831.0929）-——缓存问题
	 * 查询后台缓存没有加载的券
	 * @param secCode
	 * @return
	 */
	public List<SecBase> getSecBaseListBySecCodeListFromDb(List<String> secCode){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		List<SecBase>  list = new ArrayList<SecBase>();
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			this.insertSecBase(conn, secCode);
			sql = dsServiceBuilder.getDataBySecList();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
				list.add(t);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 将证券代码插入临时表
	 * @param conn
	 * @param codes
	 * @throws SQLException
	 */
	 private void insertSecBase(Connection conn,List<String> codes) throws SQLException{
         String sql = "INSERT INTO R_D_CLR_PARAM (C_PORT_CODE) VALUES (?)";
         PreparedStatement pst = null;
         try {
             pst = conn.prepareStatement(sql);
             for(int i =0;i<codes.size();i++){
                 pst.setString(1, codes.get(i));
                 pst.addBatch();
             }
             pst.executeBatch();
             pst.clearBatch();
         } catch (SQLException e) {
             logger.log("插入证券代码处理中间参数表时失败!", e);
         } finally{
             if(null != pst)
                 //pst.close();
                 DbFun.closeStatementFinal(pst);
         }
    }
	
	 /**
	 * BUG #338952 【回归】华夏基金（300.7.20200831.0929）-——缓存问题
	 * 查询券的总数
	 * @param secCode
	 * @return
	 */
	public int  getCountFromDb() {		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		int count = 0;
		try {
			StringBuffer buf = new StringBuffer();
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			conn = this.loadNewConnection();
			dsServiceBuilder.getCommonQueryCountSqlBuf(buf);
			pstmt = conn.prepareStatement(buf.toString());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				count = rs.getInt("num");
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return count;
	}

	
	/**
	 * @param types
	 * @param like
	 * @param page
	 * @return
	 */
	public ShortDataListPackage<SecShortPojo> getShortDataList(String[] types,String like,PageInation page) {
		
		ShortDataListPackage<SecShortPojo> shortPackage = new ShortDataListPackage<SecShortPojo>();
		
		List<SecShortPojo> pojoList = new ArrayList<SecShortPojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecShortPojo t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getShortDataList(types,like);
			
			if(page.isUsePage()==true){
				String sqlCount = buildCountSql(sql);
				pstmt = conn.prepareStatement(sqlCount);
				
				int i = 1;
				for (String type : types) {
					if (type != null && type.trim().length() > 0) {
						pstmt.setString(i++, type + "%");
					}
				}
				
				rs = pstmt.executeQuery();
				
				if(rs.next()){
					int count = rs.getInt("COUNT(*)");
					page.setTotalNum(count);
				}
				
				DbFun.closeResultSetFinal(rs);
				DbFun.closeStatementFinal(pstmt);
				rs = null;
				pstmt = null;
				
				sql = buildPagingSql(sql, page);
			}

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String type : types) {
				if (type != null && type.trim().length() > 0) {
					pstmt.setString(i++, type + "%");
				}
			}
			
			logger.debug(sql);
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecShortPojo());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecShortPojo.class);
				t = rsTools.ResultToBeanGeneric(rs, SecShortPojo.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		shortPackage.setDataList(pojoList);
		shortPackage.setPage(page);

		return shortPackage;
	}

	private String buildCountSql(String sql) {
		String sqlCount = "select count(*) from (" + sql + ")";
		return sqlCount;
	}

	/* END 数据服务方法 */
	
	/*start 清算业务*/
	/**
	 * 获取结算帐户信息
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSec(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		SecBaseSqlBuilder secBaseSqlBuilder = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			secBaseSqlBuilder = new SecBaseSqlBuilder();
			// edit by lyj 2016-1-29 STORY #28785 华泰证券-在导入银行间API交易数据后的清算优化需求变更
			// 如果证券基本信息是未审核状态不再自动审核，而是生成未审核的流水，经确认，未审核的流水不会影响划款指令的正常生成
			// 经检查此方法只有外汇交易中心用到，不会影响到其他业务
			// 审核债券信息
//			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildAutiSecSql();
//			String sql = secBaseSqlBuilder.buildAutiSecSql();
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, secBase.getC_SEC_CODE());
//			pst.executeUpdate();
//			sql = ((SecBaseSqlBuilder) sqlbuilder).buildQuerySql();
			String sql = secBaseSqlBuilder.buildQuerySql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_SEC_CODE());
			pst.setString(2, secBase.getC_MKT_CODE());
			//STORY36452【南方基金】银行间API清算进来的回购流水，品种信息要根据计息天数来判断品种信息，不能按照上市代码来判断-查询不到债券信息，导致重复生成报唯一索引 edit by chenchangyou 20161209
//			SimpleDateFormat ss = new SimpleDateFormat("yyyymmdd");
//			java.util.Date dDate = ss.parse(secBase.getD_AI_BEGIN());
//			pst.setDate(3, YssFun.toSqlDate(YssFun.toDate(secBase.getD_AI_BEGIN(), "yyyyMMdd")));
			SimpleDateFormat ss = new SimpleDateFormat("yyyyMMdd");  //BUG #163675 【安信基金】外汇交易中心清算时提示：是否满足上市代码[111797755]交易市场[银行间]，上市日期小于[20170516]退市日期大于[20170516]的条件。  edit by guoguangyi 2017-6-22
			java.util.Date dDate = ss.parse(secBase.getD_AI_BEGIN());
			pst.setDate(3, YssFun.toSqlDate(dDate));
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * BUG #232668 富国基金-【运维】存在私有债品时，外汇交易中心债券代码清算错误
	 * @param secBase
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getSecByPortCode(SecBase secBase,String portCode) throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		SecBaseSqlBuilder secBaseSqlBuilder = null;
		int index =1;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			secBaseSqlBuilder = new SecBaseSqlBuilder();
			String sql = secBaseSqlBuilder.buildQuerySqlByPortCode();
			pst = conn.prepareStatement(sql);
			SimpleDateFormat ss = new SimpleDateFormat("yyyyMMdd");  
			java.util.Date dDate = ss.parse(secBase.getD_AI_BEGIN());
			pst.setString(index++, secBase.getC_SEC_CODE());
			pst.setString(index++, secBase.getC_MKT_CODE());
			pst.setDate(index++, YssFun.toSqlDate(dDate));
			pst.setString(index++, secBase.getC_SEC_CODE());
			pst.setString(index++, secBase.getC_MKT_CODE());
			pst.setDate(index++, YssFun.toSqlDate(dDate));
			pst.setString(index++, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
				pojoList.add(sec);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
	/**
	 * 根据证券品种、交易市场、回购期限获取品种信息
	 * by guohui 20161206 STORY36452【南方基金】银行间API清算进来的回购流水，品种信息要根据计息天数来判断品种信息，不能按照上市代码来判断
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecByVarDur(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		SecBaseSqlBuilder secBaseSqlBuilder = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			secBaseSqlBuilder = new SecBaseSqlBuilder();
			String sql = secBaseSqlBuilder.buildQueryByVarDurSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_SEC_VAR_CODE());
			pst.setString(2, secBase.getC_MKT_CODE());
			pst.setString(3, secBase.getC_DV_VAR_DUR());
			//BUG157453【招商基金】【紧急】2017-04-18外汇交易中心银行间债券交易清算不进来
//			SimpleDateFormat ss = new SimpleDateFormat("yyyymmdd");
//			java.util.Date dDate = ss.parse(secBase.getD_AI_BEGIN());
//			pst.setDate(4, YssFun.toSqlDate(dDate));
			pst.setDate(4, YssFun.toSqlDate(YssFun.toDate(secBase.getD_AI_BEGIN(), "yyyyMMdd")));
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * STORY #38149 需求上海-[光大证券]金融资产管理平台V4.5[高]2017011901(银行间质押式回购基本信息改造)
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecByLimitDays(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		SecBaseSqlBuilder secBaseSqlBuilder = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			secBaseSqlBuilder = new SecBaseSqlBuilder();
			String secVar = secBase.getC_SEC_VAR_CODE();
			String sql = "";
			if(StringUtil.IsNullOrEmptyT(secVar)){
				sql = secBaseSqlBuilder.buildQuerySecByLimitDays();
			}else{
				sql = secBaseSqlBuilder.buildQuerySecByLimitDaysAndVar();
			}
			
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_MKT_CODE());
			pst.setString(2, secBase.getC_DV_VAR_DUR());
			SimpleDateFormat ss = new SimpleDateFormat("yyyymmdd");
			java.util.Date dDate = ss.parse(secBase.getD_AI_BEGIN());
			pst.setString(3, YssFun.formatDate(dDate, "yyyy/mm/dd"));
			if(!StringUtil.IsNullOrEmptyT(secVar)){
				pst.setString(4, secBase.getC_SEC_VAR_CODE());
			}
			
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * STORY #38149 需求上海-[光大证券]金融资产管理平台V4.5[高]2017011901(银行间质押式回购基本信息改造)
	 * @return 中国银行间交易市场的市场代码
	 */
	public String getMktNo(){
		String mktNo = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql = " select C_MKT_NO from T_P_BI_MKT where C_DE_CODE='XCFE' ";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			if (rs.next()) {
				mktNo = rs.getString("C_MKT_NO");
			} else {
				mktNo = "CY";
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			//e.printStackTrace();
			logger.log(e.getMessage(), e);
		} finally{
			//代码检查  使用工具类  edit by  sunyanlin 20180322
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		return mktNo;
	}
	
	/**
	 * 获取税率
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getRate(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildQueryRateSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_SEC_CODE());
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
		} catch (Exception ex) {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			// 异常抛出给上一级方法处理
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, timestamp);
			//// By Jinghehe 2017-12-07 BUG #179190 性能问题-21.5版本证券、科目缓存更新存在性能问题 
			pstmt.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	
	public List<BasePojo> getDataListByTimestampPage(String timestamp, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);
			
//			pstmt.setString(1, timestamp);
			//// By Jinghehe 2017-12-07 BUG #179190 性能问题-21.5版本证券、科目缓存更新存在性能问题 
			pstmt.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	
	
	public List<String> getSecCodeDataListByTimestampPage(String timestamp, PageInation page) {
		List<String> pojoList = new ArrayList<String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				pojoList.add(rs.getString("C_SEC_CODE"));
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
	
	public int getUpdateByTimestampCount(String timestamp){
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		int count = 0;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			conn = this.loadNewConnection();
			String sql = dsServiceBuilder.getDataListByTimestampCount();
			pst = conn.prepareStatement(sql);
//			pst.setString(1, timestamp);
			//// By Jinghehe 2017-12-07 BUG #179190 性能问题-21.5版本证券、科目缓存更新存在性能问题 
			pst.setString(1, YssFun.formatDate(YssFun.parseDate(timestamp,"yyyy-MM-dd HH:mm:ss"), "yyyyMMdd HH:mm:ss"));

			rs = pst.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		
		return count;
	}
	
	/**
	 * 查询指数数据转封装成证券基本信息 
	 * By Jinghehe 2014-8-4
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getAllIndexDataList() throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllIndexDataList();

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	
	/*end 清算业务*/
	
	/**解决 BUG #127360::YSS-版本自动打包报错 获取基金日历表中证券信息缓存
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getSecCache(HashMap<String, String> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			/*Branches V1.20.2.2代码合并
			STORY #26176 新华资产-基金日历表增加从开基业务认申购统计*/
			String sql = "select * from T_P_SV_SEC_BASE where c_sec_mkt_code "
					+ "in (select distinct c_sec_code from (select distinct c_sec_code from t_r_fundtrade  "
					+ "where d_trade >= ? and d_trade <= ? union all select  distinct c_fund_code from "
					+ "t_d_od_trade_sale where d_trade >= ? and d_trade <= ?)) and n_check_state =1 ";
			if(paraMap.get("C_SEC_CODE") != null){
				sql = "select * from T_P_SV_SEC_BASE where c_sec_mkt_code = ? ";
				st = conn.prepareStatement(sql);
				st.setString(1, paraMap.get("C_SEC_CODE"));
			}else{
				st = conn.prepareStatement(sql);
				if(paraMap.get("D_ORD_END") == null){
					st.setDate(1, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
					st.setDate(2, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
					st.setDate(3, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
					st.setDate(4, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
				}else{
					st.setDate(1, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
					st.setDate(2, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_END"))));
					st.setDate(3, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_START"))));
					st.setDate(4, YssFun.toSqlDate(YssFun.toDate((String)paraMap.get("D_ORD_END"))));
				}
			}
			rs = st.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//BasePojo t = setResultSet(rsTools,rs, SecBase.class); 
				BasePojo t = rsTools.ResultToBean(rs, SecBase.class, props);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}
		} catch (Exception e) {
			logger.log("查询基金持仓相关证券信息失败",e);
			throw new DataAccessException("查询基金持仓相关证券信息失败",e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(st);
			releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * 分页查询数据
	 * @param page
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListPage(PageInation page) throws ServiceException {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
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
	 * 查询数据量
	 * @return
	 */
	public int getDataListCount(){
		Connection conn=null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		int count = 0;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			String sql = dsServiceBuilder.getDataListCount();
			
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			
			rs = pst.executeQuery();
			
			if(rs.next()){
				count = rs.getInt(1);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		
		
		return count;
	}

	/**
	 * add by zhd 2016-09-20
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 得到所有实际所属证券为空的证券
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public List<SecBase> getDataListBySjsszq(String[] types) {
		List<SecBase> pojoList = new ArrayList<SecBase>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SecBaseInfoSqlBuilder dsServiceBuilder = null;
		SecBase t = null;
		try {
			dsServiceBuilder = new SecBaseInfoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListBySjsszq(types);

			pstmt = conn.prepareStatement(sql);
			int i = 1;
			for (String type : types) {
				if (type != null && type.trim().length() > 0) {
					pstmt.setString(i++, type + "%");
				}
			}
			logger.debug(sql);
			rs = pstmt.executeQuery();

			// 南方基金性能优化 zhanghualin 2017-3-15
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new SecBase());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, SecBase.class);
				t = rsTools.ResultToBeanGeneric(rs, SecBase.class, props);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	// STORY #33007 【南方基金】【紧急】存放品种信息机构名称字段只显示商业银行的信息
		// 个性化获取全部交易证券
		public List<CfSec> getDataListByIndiv()
				throws ServiceException {
			List<CfSec> pojoList = new ArrayList<CfSec>();

			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "";
			SecBaseInfoSqlBuilder dsServiceBuilder = null;
			
			try {
				dsServiceBuilder = new SecBaseInfoSqlBuilder();

				conn = this.loadNewConnection();

				sql = dsServiceBuilder.getDataListByIndiv();

				pstmt = conn.prepareStatement(sql);

				logger.debug(sql);

				rs = pstmt.executeQuery();
				
				while (rs.next()) {
					CfSec t = new CfSec();	
					t.setC_ORG_CODE_P(rs.getString("C_ORG_CODE_P"));
					t.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
					t.setC_SEC_NAME(rs.getString("C_SEC_NAME"));
					t.setC_SEC_VAR_CODE(rs.getString("C_SEC_VAR_CODE"));
					t.setC_DC_CODE(rs.getString("C_DC_CODE"));
					t.setN_PRICE_ISSUE(rs.getBigDecimal("N_PRICE_ISSUE"));
					t.setC_DV_ISSUE(rs.getString("C_DV_ISSUE"));
					
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
		* Title: STORY #27843 资讯信息调整增加提示功能
		* Author: chenchen
		* Status: Add
		* Date: 2016.8.25
		* Purpose:手工调整债券基本信息、理财产品信息等中的证券品种时，需核对持仓类型与调整后类型是否一致，不一致给予提示，让用户确认是否需要调整证券品种类型
		* Description:根据证券查出来小于当天的最大一个库存日期，然后取出证券投资_成本的库存数据汇总，数据大于零要给出提示，让用户确认是否需要调整。
		*/
		public String secTypeTip(String secCode) {
			PreparedStatement pst = null;
			StringBuffer buf = new StringBuffer();
			Connection conn = null;
			ResultSet rs = null;
			String sql = "";
			String result = "false";
			try {
				conn = this.loadNewConnection();
				buf.append(" SELECT SUM(N_AMOUNT) AS N_AMOUNT ");
				buf.append("    FROM T_D_AI_STOCK A ");
				buf.append("    WHERE A.C_DAI_CODE = 'ZQTZ_CB' ");
				buf.append("    AND A.C_SEC_CODE = ? ");
				buf.append("    AND A.D_STOCK = ( ");
				buf.append("     select * from (SELECT B.D_STOCK ");
				buf.append("      FROM T_D_AI_STOCK B ");
				buf.append("      WHERE B.C_SEC_CODE = ? ");
				buf.append("      AND B.D_STOCK <= SYSDATE  order by B.D_STOCK desc) where rownum = 1 )  ");
				sql = buf.toString();
				pst = conn.prepareStatement(sql);
				pst.setString(1, secCode);
				pst.setString(2, secCode);
				rs = pst.executeQuery();
				if (rs.next()) {
					double amount = rs.getDouble("N_AMOUNT");
					if (amount != 0) {
						result = "true";
					}
				}
			} catch (Exception ex) {
				logger.log("检查该证券是否有持仓时失败!", ex);
			} finally {
				closeResultSetFinal(rs);
				closeStatementFinal(pst);
				releaseConnection(conn);
			}
			return result;
		}
		
		
	/**
	 * 检查某证券是否存在持仓
	 * add by liyanjun 2016-8-29 STORY31079 修改咨询信息品种有持仓的情况下给予提醒
	 * 查询逻辑：查询估值表中是否存在该券（运用索引条件查询）
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStock(String secCode) {
		String result = "false";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = " SELECT '1' FROM T_R_FR_ASTSTAT A WHERE C_NAV_TYPE = 'SEC' AND C_KEY_CODE = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, secCode);
			rs = st.executeQuery();
			if (rs.next()) {
				result = "true";
			}
		} catch (Exception e) {
			throw new DataAccessException("查询失败：",e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(st);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 检查某证券是否存在持仓-凭证
	 *  删除证券基本信息，点删除按钮时，检索历史凭证数据和库存数据,
	 * add by zengpenglin 2018-01-16 STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsAct(String secCode) {
		String result="false";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=this.loadNewConnection();
			String sql=" SELECT '1' FROM T_D_AI_ACT_VAL WHERE C_SEC_CODE = ?  ";
			st=conn.prepareStatement(sql);
			st.setString(1, secCode);
			rs=st.executeQuery();
			if(rs.next()){
				result="true";
			}
		}catch(Exception e){
			throw new DataAccessException("查询失败",e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(st);
			releaseConnection(conn);
		}
		return result;
	}
	
	
	/**
	 * 检查数据库中是否存在相同的ISIN代码
	 * add by qinxinglin 2017-11-6 STORY #48141 维护境外债券品种，同ISIN的债券品种要求提示重复
	 * @param isinCode
	 * 			ISIN代码
	 * @return
	 */
	public String checkIsinCode(String isinCode, String secCode) {
		PreparedStatement pst = null;
		StringBuffer buf = new StringBuffer();
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		String result = "false";
		try {
			conn = this.loadNewConnection();
			buf.append(" SELECT trim(C_SEC_ISIN_CODE) as C_SEC_ISIN_CODE FROM T_P_SV_SEC_BASE WHERE C_SEC_ISIN_CODE = ? and C_SEC_CODE != ?");
			sql = buf.toString();
			pst = conn.prepareStatement(sql);
			pst.setString(1, isinCode);
			pst.setString(2, secCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				String isin = rs.getString("C_SEC_ISIN_CODE");
				if(null != isin){
					result = "true";
				}
			}
		} catch (Exception ex) {
			logger.log("检查数据库中是否存在相同的ISIN代码失败", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return result;
	}
	
	
	/**
	 * 检查某证券是否存在持仓-库存
	 *  删除证券基本信息，点删除按钮时，检索历史凭证数据和库存数据,
	 * add by zengpenglin 2018-01-16 STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStockOfStock(String secCode) {
		String result = "false";
		Connection conn = null;
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = " SELECT '1' FROM T_D_AI_STOCK A WHERE C_SEC_CODE = ? ";
			st = conn.prepareStatement(sql);
			st.setString(1, secCode);
			rs = st.executeQuery();
			if (rs.next()) {
				result = "true";
			}
		} catch (Exception e) {
			throw new DataAccessException("查询失败：",e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(st);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 检查某证券是否存在持仓-stock
	 *  删除证券基本信息，点删除按钮时，检索历史凭证数据和库存数据,
	 * add by zengpenglin 2018-01-16 STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param secCode 证券代码
	 * @return
	 */
	public String isExistsStk(String secCode){
		String result="false";
		Connection conn=null;
		PreparedStatement st=null;
		ResultSet rs=null;
		try{
			conn=this.loadNewConnection();
			String sql=" SELECT '1' FROM T_D_AI_STOCK WHERE C_SEC_CODE = ? ";
			st=conn.prepareStatement(sql);
			st.setString(1, secCode);
			rs=st.executeQuery();
			if(rs.next()){
				result="true";
			}
		}catch(Exception e){
			throw new DataAccessException("查询失败",e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(st);
			releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 重新处理债券信息属性 
	 * added by HeLiang 2017-08-04 STORY #45064 财汇par_bond_info文件读取债券信息时对于系统中已存在的信息进行更新
	 * 
	 * @param updatingData
	 *            待更新的实体列表
	 * @param date
	 *            交易日期
	 * @return List
	 */
	private List<SecBase> refreshZqProperties(List<SecBase> updatingData,
			java.util.Date date) {
		StringBuffer desc = new StringBuffer();
		SecBase oldSecBase = null;
		List<SecBase> resultList = new ArrayList<SecBase>();

		SecBaseCache secBaseCache = CacheManager.getInstance().getCache(
				CacheGroup.SECBASE);

		for (SecBase newSecBase : updatingData) {
			oldSecBase = secBaseCache.getCacheByKey(newSecBase.getC_SEC_CODE());
			desc.append("更新_");
			
	
			if (null != oldSecBase) {
				// 债券名称
				if (null != newSecBase.getC_SEC_NAME()
						&& newSecBase.getC_SEC_NAME().equals(
								oldSecBase.getC_SEC_NAME()) == false) {
					desc.append("债券名称、");
				}
				// 发行价格
				if (null != newSecBase.getN_PRICE_ISSUE()
						&& newSecBase.getN_PRICE_ISSUE().equals(
								oldSecBase.getN_PRICE_ISSUE()) == false) {
					desc.append("发行价格、");
				}
				// 票面利率
				if (null != newSecBase.getN_FV_IR()
						&& newSecBase.getN_FV_IR().equals(
								oldSecBase.getN_FV_IR()) == false) {
					desc.append("票面利率、");
				}
				// 税率
				if (null != newSecBase.getN_RATE()
						&& newSecBase.getN_RATE()
								.equals(oldSecBase.getN_RATE()) == false) {
					desc.append("税率、");
				}
				// 付息频率
				if (null != newSecBase.getC_DV_VAR_DUR()
						&& newSecBase.getC_DV_VAR_DUR().equals(
								oldSecBase.getC_DV_VAR_DUR()) == false) {
					desc.append("付息频率、");
	/*
	/**
	 * 删除证券基本信息，点删除按钮时，检索历史凭证数据和库存数据,
	 * add by zengpenglin 2018-01-16 STORY50949【汇添富专户升级】修改“标准证券”删除逻辑
	 * @param pojoList
	 * 			前台传过来的pojo
	 * @return
	 
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			
			for (int i = 0; i < pojoList.size(); i++) {
					T basePojo = pojoList.get(i);
				
					StringBuffer sql = new StringBuffer();
					sql.append("delete from ");
					sql.append("t_p_sv_sec_base a ");

					String columnName = this.sqlbuilder
							.getColumnNameByProperty(dbNameResolver, "id");
					sql.append(" where ");
					sql.append(columnName);
					sql.append("=");
					sql.append("?");
					sql.append(" and not exists ( select 1 from t_d_ai_stock b  where a.c_sec_code=b.c_sec_code )");
					sql.append(" and not exists ( select 1 from t_d_ai_act_val c where a.c_sec_code=c.c_sec_code ) ");
					pstmt = conn.prepareStatement(sql.toString());
				
				
				String value = basePojo.getId();
				if (value == null) {
					throw new InvalidParametersException("id不能为空");
				}

				pstmt.setString(1, value);
				pstmt.executeUpdate();
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	*/

				}
				// 债券起息日
				if (null != newSecBase.getD_AI_BEGIN()
						&& newSecBase.getD_AI_BEGIN().equals(
								oldSecBase.getD_AI_BEGIN()) == false) {
					desc.append("债券起息日、");
				}
				// 债券结息日
				if (null != newSecBase.getD_AI_END()
						&& newSecBase.getD_AI_END().equals(
								oldSecBase.getD_AI_END()) == false) {
					desc.append("债券结息日、");
				}
				// 含权标志
				if (null != newSecBase.getC_DV_RIGHT()
						&& newSecBase.getC_DV_RIGHT().equals(
								oldSecBase.getC_DV_RIGHT()) == false) {
					desc.append("含权标志、");
				}
				// 含权日期
				if (null != newSecBase.getD_END()
						&& newSecBase.getD_END().equals(oldSecBase.getD_END()) == false) {
					desc.append("含权日期、");
				}
				// 债券信用评级
				if (null != newSecBase.getC_CREDIT_RATING()
						&& newSecBase.getC_CREDIT_RATING().equals(
								oldSecBase.getC_CREDIT_RATING()) == false) {
					desc.append("债券信用评级、");
				}

				desc.deleteCharAt(desc.length() - 1);
				desc.append(YssFun.formatDate(date, "yyyyMMdd"));

				oldSecBase.setC_SEC_NAME(newSecBase.getC_SEC_NAME());
				oldSecBase.setN_PRICE_ISSUE(newSecBase.getN_PRICE_ISSUE());
				oldSecBase.setN_FV_IR(newSecBase.getN_FV_IR());
				oldSecBase.setN_RATE(newSecBase.getN_RATE());
				oldSecBase.setC_DV_VAR_DUR(newSecBase.getC_DV_VAR_DUR());
				oldSecBase.setD_AI_BEGIN(newSecBase.getD_AI_BEGIN());
				oldSecBase.setD_AI_END(newSecBase.getD_AI_END());
				oldSecBase.setC_DV_RIGHT(newSecBase.getC_DV_RIGHT());
				oldSecBase.setD_END(newSecBase.getD_END());
				oldSecBase.setC_CREDIT_RATING(newSecBase.getC_CREDIT_RATING());
				if (1 == oldSecBase.getAuditState()) {
					oldSecBase.setC_DESC(desc.toString());
					//已审核的券不反审核
					//oldSecBase.setAuditState(0);
				}

				resultList.add(oldSecBase);				
			}
			desc.setLength(0);
		}

		return resultList;
	}
	
	/**
	 * add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
	 * / set公司中文名称
	 * @throws Exception
	 */
	public void getIssuersNameByDates(List<BasePojo> dataList) {
		if(dataList!=null){
			List<String> issuersCodeList = new ArrayList<String>();
			for (BasePojo basePojo : dataList) {
				String c_ISSUERS_CODE = ((SecBase)basePojo).getC_ISSUERS_CODE();
				if(c_ISSUERS_CODE==null || "".equals(c_ISSUERS_CODE.trim())){
					continue;
				}
				issuersCodeList.add(c_ISSUERS_CODE);
			}
			
			if(issuersCodeList.size()>0){
				HashMap<String, String> issuersCodeAndNameMap = new HashMap<String,String>();
				String sql="select c_org_code,c_org_name_cn from t_p_BI_org where c_org_code in (SELECT * FROM TABLE(?) ) ";
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				String[] array = issuersCodeList.toArray(new String[issuersCodeList.size()]);
				try {
					conn = this.loadNewConnection();
					pstmt = conn.prepareStatement(sql);
					pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(array,conn));
					rs = pstmt.executeQuery();
					while(rs.next()){
						String key=rs.getString("c_org_code");
						String value=rs.getString("c_org_name_cn");
						issuersCodeAndNameMap.put(key, value);
					}
				} catch (Exception e) {
					
				} finally{
					this.closeResultSetFinal(rs);
					this.closeStatementFinal(pstmt);
					this.releaseConnection(conn);
				}
				
				for (BasePojo basePojo : dataList) {
					SecBase secBase =(SecBase)basePojo;
					String c_ISSUERS_CODE = secBase.getC_ISSUERS_CODE();
					if(c_ISSUERS_CODE==null || "".equals(c_ISSUERS_CODE.trim())){
						continue;
					}
					String value=null;
					if((value=issuersCodeAndNameMap.get(c_ISSUERS_CODE))!=null){
						secBase.setC_ISSUERS_NAME(value);
					}
				}
				
			}
			
		}
	}
	
	/**
	 * add zhangmingbo story 45603 [易方达QDII]证券信息数据导入清算 2017 9 16
	 * / 根据公司代码获取set公司中文名称
	 * @throws Exception
	 */
	public String getIssuersNameByIssuersCode(String issuersCode) {
		if(issuersCode==null ||"".equals(issuersCode.trim())){
			return " ";
		}
		String sql="select c_org_code,c_org_name_cn from t_p_BI_org where c_org_code=? ";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, issuersCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				return  rs.getString("c_org_name_cn");
			}
		} catch (Exception e) {
			
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return " ";
	}
	
	/**
	 * STORY #51855 【南方基金】【紧急】社保资产投资港股通业务需要系统支持
	 * @Title 在代码转换表中查询出非自动的数据
	 * @Description 
	 * @author guohui@ysstech.com
	 * @date 2018年01月19日下午1:33:32
	 * @param SeatCodes
	 * @return
	 * @throws Exception
	 */
	public List<SecTransfer> getFzdTdSeat(String[] SeatCodes){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> seatTrans = new ArrayList<SecTransfer>();
		String sql = " select * from T_D_MP_SEC_TRANSFER where C_TYPE = 'P' and c_data_idf != 'Z' and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
		try{
			conn = loadNewConnection();	
			com.yss.framework.db.OraDbTool dbTool = com.yss.framework.db.OraDbTool.newInstance();
			// 取出待删除数据,更新
			pst = conn.prepareStatement(sql);
			pst.setArray(1, dbTool.sqlOverLongCondition(SeatCodes,conn));
			rs = pst.executeQuery();
			while(rs.next()){
				SecTransfer secTransfer = new SecTransfer();
				secTransfer.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				seatTrans.add(secTransfer);
			}
		}catch(Exception e){
			//e.printStackTrace();
			logger.log(e.getMessage(), e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return seatTrans;
	}
	
	/**
	 *STORY #52773 【易方达基金】【社保】社保基金买卖港股通的需求
	 * @author zhangyu_cs
	 * @date 2018年3月7日
	 * @time 下午6:33:12
	 */
	public List<SecTransfer> getGGTFzdTdSeat(String[] SeatCodes){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> seatTrans = new ArrayList<SecTransfer>();
		// 查找 （C_TYPE='P' 且 手动 ）or   C_TYPE!='P'
		String sql = " select * from T_D_MP_SEC_TRANSFER where ((C_TYPE = 'P' and c_data_idf != 'Z') or (C_TYPE <> 'P')) and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
		try{
			conn = loadNewConnection();	
			com.yss.framework.db.OraDbTool dbTool = com.yss.framework.db.OraDbTool.newInstance();
			// 取出待删除数据,更新
			pst = conn.prepareStatement(sql);
			pst.setArray(1, dbTool.sqlOverLongCondition(SeatCodes,conn));
			rs = pst.executeQuery();
			while(rs.next()){
				SecTransfer secTransfer = new SecTransfer();
				secTransfer.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				seatTrans.add(secTransfer);
			}
		}catch(Exception e){
			//e.printStackTrace();
			logger.log(e.getMessage(), e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return seatTrans;
	}
	
	/**
	 * STORY #51855 【南方基金】【紧急】社保资产投资港股通业务需要系统支持
	 * @Title setSecTran 
	 * @Description 构造证券代码转换信息实体
	 * @author guohui@ysstech.com
	 * @date 2018年1月19日下午2:29:52
	 * @param secCode  证券代码
	 * @param gzCode   规则代码
	 * @param plCode   披露代码
	 * @param date     记录变更事件
	 * @param tdChans     
	 * @return void
	 */
	public void setSecTran(String secCode, String gzCode,String plCode,java.util.Date date,List<SecTransfer> secs){
		SecTransfer secTran =new SecTransfer();
		secTran.setC_SEC_CODE(secCode);
		secTran.setC_PUB_CODE(plCode);
		secTran.setC_DATA_IDF("Z");
		secTran.setC_TRANSFER_CODE(gzCode);
		secTran.setAuditState(!" ".equals(plCode) ? 1 : 0);
		secTran.setModifier(ContextFactory.getContext().getUserCode());
		secTran.setModifyDate(YssFun.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		secTran.setC_TYPE("P");
		if (!" ".equals(plCode)) {
			secTran.setOperator(ContextFactory.getContext().getUserCode());
			secTran.setAuditDate(YssFun.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		}
		secs.add(secTran);
	}
	
	/**
	 * 协议存款业务获取证券基本信息
	 * STORY #41786 安信-系统内部接口，协议存款数据
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecBaseARG(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildQuerySecSqlARG();
			pst = conn.prepareStatement(sql);
//			pst.setDate(1, YssFun.toSqlDate(secBase.getD_TO_LIST()));
//			pst.setDate(2, YssFun.toSqlDate(secBase.getD_OFF_LIST()));
//			pst.setString(3, secBase.getC_DV_VAR_DUR());
//			pst.setString(4, secBase.getC_ORG_CODE());
			//BUG #194439 【安信基金】资金存放业务流水倒置算法默认值修改及成交编号取值逻辑修改 edit by guoguangyi 20180310
			pst.setString(1, secBase.getC_SEC_MKT_CODE());
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
		} catch (Exception ex) {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			// 异常抛出给上一级方法处理
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * 获取证券基本信息根据证券代码（包含未审核的）
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecBaseBySecCode(String c_SEC_CODE) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildQuerySecSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c_SEC_CODE);
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
		} catch (Exception ex) {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			// 异常抛出给上一级方法处理
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * 获取证券基本信息根据上市代码
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecBaseBySecMktCode(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildQuerySecMktCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_SEC_NAME());
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
		} catch (Exception ex) {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			// 异常抛出给上一级方法处理
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * 获取证券基本信息根据回购天数
	 * @param secBase 条件
	 * @return
	 * @throws ServiceException
	 */
	public SecBase getSecBaseByVarDur(SecBase secBase) throws ServiceException {
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		SecBase sec = null;
		ResultSetTools rstool = null;
		try {
			rstool = new ResultSetTools(this.dbNameResolver, this.sqlbuilder);
			conn = this.loadNewConnection();
			String sql = ((SecBaseSqlBuilder) sqlbuilder).buildQueryVarDurSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, secBase.getC_DV_VAR_DUR());
			rs = pst.executeQuery();
			// 取第一条
			if (rs.next()) {
				sec = rstool.ResultToBeanGeneric(rs, SecBase.class);
			}
		} catch (Exception ex) {
			// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复 edit by chenyoulong 20160718
			// 异常抛出给上一级方法处理
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return sec;
	}
	
	/**
	 * STORY #52720 嘉实基金QD-嘉实OMGEO交易数据通过webservice推送 
	 * add by xuhanbing 2019-5-10
	 * @param isinCodes
	 * @return
	 */
	public Map<String, String> getIsinSecMap(List<String> isinCodes) {
        ResultSet rs = null;
        PreparedStatement pst = null;
        Connection conn = null;
        Map<String, String> map = null;
        try {
            map = new HashMap<String,String>();
            conn = this.loadNewConnection();
            conn.setAutoCommit(false);
            TempTableUtil.clearTempFeeId(conn);
            TempTableUtil.insertTempFeeId(conn, isinCodes);
            //String sql = "select * from R_D_FEE_ID";
            String sql = "select ISIN.ID_D_AC_TD_IVT C_SEC_ISIN_CODE, TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT SEC.C_SEC_CODE || '_' || SEC.C_MKT_CODE) AS VARTABLETYPE), ',')) SEC_CODE "
                +" from R_D_FEE_ID ISIN LEFT JOIN T_P_SV_SEC_BASE SEC ON ISIN.ID_D_AC_TD_IVT = SEC.C_SEC_ISIN_CODE AND SEC.N_CHECK_STATE > 0 group by ISIN.ID_D_AC_TD_IVT ";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                //System.out.println(rs.getString(1));
                map.put(rs.getString("C_SEC_ISIN_CODE"), rs.getString("SEC_CODE"));
            }
            conn.commit();
            conn.setAutoCommit(true);
        } catch (Exception ex) {
            throw new ServiceException(ex);
        } finally {
            this.closeResultSetFinal(rs);
            this.closeStatementFinal(pst);
            this.releaseConnection(conn);
        }
        return map;
    }

	@Override
	public <K extends BasePojo> List<K> queryByIds(String ids, Class<?> clazz) {
		List<K> dataList = new ArrayList<K>();
		K pojo = null;
		PreparedStatement ptmt = null;
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		// 构建查询语句
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			pojo = (K) clazz.newInstance();
			StringBuffer fieldNames = new StringBuffer();

			PropertyDescriptor[] proDescriptors = this
					.getPropertyDescriptors(pojo);

			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			for (PropertyDescriptor prop : proDescriptors) {
				// 去掉getClass方法
				if (prop.getPropertyType().isAssignableFrom(Class.class)) {
					continue;
				}

				if ("c_USER_PWD".equals(prop.getName())) {
					continue;
				}
				if ("c_SYSOURCE".equals(prop.getName())) {
					continue;
				}
				if ("c_UPDATE_TIMEWEB".equals(prop.getName())) {
					continue;
				}
				this.buildFieldByCommaQuery(fieldNames, prop, pojo);
			}

			if (fieldNames.length() > 0) {
				StringUtil.delLastSplitMark(fieldNames, ",");
			}

			if (fieldNames.length() > 0) {
				sqlBuffer.append(" SELECT ");
				sqlBuffer.append(fieldNames);
				sqlBuffer.append(" FROM ");
				sqlBuffer.append(this.sqlbuilder
						.getTableName(this.dbNameResolver));
				sqlBuffer.append(" WHERE ");
				sqlBuffer.append(" C_IDEN IN (SELECT * FROM TABLE(?)) ");
				sql = sqlBuffer.toString();
			} else {
				throw new InvalidDataException(pojo.getClass().toString()
						+ "的实例没有属性值");
			}
			ptmt = openPreparedStatement(sql, conn);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(ids),conn));
			
			rs = ptmt.executeQuery();
			
			while (rs.next()) {
				pojo = (K) rsTools.ResultToBean(rs, clazz);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("SQL["+sqlBuffer.toString()+"]查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}
		return dataList;
	}
	
	/*
	 *  BUG #278907 【证券缓存】目前系统启动时,证券缓存加载需要5到10分钟左右,导致系统核算等操作报错，需要等缓存加载完才能正常，需要优化，尽量优化到1分钟以内	
	 */
	public int getSecBaseCacheTotalNum() {
		PreparedStatement ptmt = null;
		Connection conn = null;
		ResultSet rs = null;
		int totalNum = 0;
		// 构建查询语句
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			sqlBuffer.append("SELECT COUNT(1) AS TOTALNUM FROM (");
			sqlBuffer.append("SELECT c_sec_var_code FROM T_P_SV_SEC_BASE union all SELECT c_sec_var_code FROM T_P_NS_BASE ) a ");
			sqlBuffer.append(" left join (select c_da_code, c_sec_var_code from T_S_DA_SEC_VAR) b on a.c_sec_var_code = b.c_sec_var_code WHERE b.c_da_code is not null ");
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sqlBuffer.toString());
			rs = ptmt.executeQuery();
			
			if (rs.next()) {
				totalNum = rs.getInt("TOTALNUM");
			}
		} catch (Exception ex) {
			throw new DataAccessException("SQL["+sqlBuffer.toString()+"]查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return totalNum;
	}
	
	
	
	/**
	 * STORY #85909 银行间质押式回购基本信息标准化 
	 * @param secVarCode 证券品种
	 * @param secMarketCode 上市代码
	 * @param purPeriod 回购期限
	 * @return 是否存在相同的银行间回购券
	 */
	public String hasSameHgSec(String secVarCode, String secMarketCode, String purPeriod, String iden)throws Exception{
		String secCode = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = this.loadNewConnection();
			SecBaseSqlBuilder secBaseSqlBuilder = new SecBaseSqlBuilder();
			sql = secBaseSqlBuilder.hasSameHgSecSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			pstmt.setString(1, secVarCode);
			pstmt.setString(2, secMarketCode);
			pstmt.setString(3, purPeriod);
			pstmt.setString(4, iden);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				secCode = rs.getString("c_sec_code");
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return secCode;
	}
	
	/**
	 * STORY #87186 富国基金定存业务接口需求
	 * 根据证券代码查询是否存在该证券
	 * @param secCode 证券代码
	 * @return
	 */
	public boolean isHasSec(String secCode){
		PreparedStatement ptmt = null;
		Connection conn = null;
		ResultSet rs = null;
		boolean result = false;
		// 构建查询语句
		StringBuffer sqlBuffer = new StringBuffer();
		try {
			sqlBuffer.append(" select c_sec_code from t_p_sv_sec_base a where a.c_sec_code = ? ");
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sqlBuffer.toString());
			ptmt.setString(1, secCode);
			rs = ptmt.executeQuery();
			
			if (rs.next()) {
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询证券代码失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(sqlBuffer);
		}
		return result;
	}
	
	@Override
	public BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs, Class<?> clazz) throws Exception {
		BasePojo basePojo = super.setResultSet(rsTools, rs, clazz);
		setData(rs, clazz, basePojo);
		return basePojo;
	}
	
	@Override
	public BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs, Class<?> clazz, PropertyDescriptor[] props) throws Exception {
		BasePojo basePojo = super.setResultSet(rsTools, rs, clazz, props);
		setData(rs, clazz, basePojo);
		return basePojo;
	}
	
	/**
	 * BUG #317226 【招商基金】【0331】存放流水前后台报错
	 * 处理 C_UPDATE_TIMEWEB、C_SYSOURCE 字段标识符无效报错
	 * @param rs
	 * @param clazz
	 * @param basePojo
	 * @throws Exception
	 */
	private void setData(ResultSet rs, Class<?> clazz, BasePojo basePojo) throws Exception {
		if (SecBase.class == clazz) {
			if (ResultSetUtil.isFindColumn(rs, "C_UPDATE_TIMEWEB")) {
				((SecBase) basePojo).setC_UPDATE_TIMEWEB(rs.getString("C_UPDATE_TIMEWEB"));
			}
			if (ResultSetUtil.isFindColumn(rs, "C_SYSOURCE")) {
				((SecBase) basePojo).setC_SYSOURCE(rs.getString("C_SYSOURCE"));
			}
		}
	}
}
