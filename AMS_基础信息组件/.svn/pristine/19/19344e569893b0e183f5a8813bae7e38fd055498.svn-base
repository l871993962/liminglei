package com.yss.ams.base.information.modules.bi.mkt.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktExtend;
import com.yss.ams.base.information.util.cache.CacheUtil;
import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
 * 交易市场dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class MktDao extends GeneralDao {

	private MktSqlBuilder mktSqlBuilder;

	public MktDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.mktSqlBuilder = (MktSqlBuilder) sqlBuilder;
	}

	/**
	 * 根据条件查询市场设置
	 * 
	 * @param paraMap
	 *            参数列表
	 * @param page
	 *            分页
	 * @param bundleContext
	 *            bundle上下文
	 * @return 市场设置信息列表
	 */
	public List<BasePojo> queryMktExtendDataList(
			HashMap<String, Object> paraMap, PageInation page,
			BundleContextWrapper bundleContext) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools resTools = null;
		BasePojo t = null;
		Class<?> clazz = null;
		String sql = "";
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = this.mktSqlBuilder.getMktExtendSql(paraNameList);
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(
							index,
							OraDbTool
									.newInstance()
									.sqlOverLongCondition(
											String.valueOf(paraMap
													.get(valueFieldName)), conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}
				index++;
			}
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			resTools = new ResultSetTools(dbNameResolver, mktSqlBuilder);
			clazz = PojoLoader.getPojoClassById("MktExtend", bundleContext);
			while (rs.next()) {
				t = resTools.ResultToBean(rs, clazz);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			logger.log("交易市场设置：查询交易市场失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * 根据条件查询市场设置条数
	 * 
	 * @param paraMap
	 *            参数列表
	 * @return 交易市场设置条数
	 */
	public int queryMktExtendDataListCount(HashMap<String, Object> paraMap) {
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
			sql = this.mktSqlBuilder.getMktExtendSqlCount(paraNameList);
			pstmt = conn.prepareStatement(sql);
			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("N_CHECK_STATE".equals(valueFieldName)) {
					continue;
				}
				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(
							index,
							OraDbTool
									.newInstance()
									.sqlOverLongCondition(
											String.valueOf(paraMap
													.get(valueFieldName)), conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}
				index++;
			}
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

	/**
	 * 获取交易市场代码转换MAP用于界面展示
	 * 
	 * @param queryRes
	 */
	public void buildTradeMKTConvert(QueryRes queryRes) {
		HashMap<String, String> hmWay = null;
		if (queryRes != null && queryRes.getShowConvertAssemble() != null) {
			hmWay = queryRes.getShowConvertAssemble().get("C_MKT_CODE");
			if (null == hmWay) {
				return;
			}
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = " SELECT A.C_MKT_CODE , A.C_MKT_NAME FROM T_P_BI_MKT A UNION ALL SELECT B.C_MKT_CODE , B.C_MKT_NAME FROM T_S_MKT_VAR B ";
			try {
				conn = this.loadNewConnection();
				conn.setAutoCommit(false);
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while (rs.next()) {
					hmWay.put(rs.getString(1), rs.getString(2));
				}
				conn.commit();
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				throw new DataAccessException("查询交易市场失败:" + e.getMessage(), e);
			} finally {
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pstmt);
				this.releaseConnection(conn);
			}
		}
	}

	/**
	 * 获取所有交易市场设置数据
	 * 
	 * @return
	 * @throws ServiceException
	 */
	public List<MktExtend> getAllDataList() throws ServiceException {
		List<MktExtend> pojoList = new ArrayList<MktExtend>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		MktExtend t = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver,
					new MktExtendSqlBuilder());
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getAllDataSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-19 蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(MktExtend.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, MktExtend.class, props);
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
	 * 根据市场代码获取交易市场设置
	 * 
	 * @param code
	 *            市场代码
	 * @return MKt
	 * @throws ServiceException
	 */
	public Mkt getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataByCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return mkt;
	}

	/**
	 * 根据交易市场类型获取交易市场设置
	 * 
	 * @param types
	 *            市场类型
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	public List<Mkt> getDataListByTypes(String[] types) throws ServiceException {
		List<Mkt> pojoList = new ArrayList<Mkt>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataListByTypes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				pojoList.add(mkt);
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
	 * 获取交易市场代码转换MAP
	 * 
	 * @return 交易市场代码转换MAP
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getAllDataSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				keyValueMap.put(mkt.getC_MKT_CODE(), mkt.getC_MKT_NAME());
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
	 * 根据多个交易市场类型获取交易市场设置
	 * 
	 * @param keys
	 *            市场类型
	 * @return 市场列表
	 * @throws ServiceException
	 */
	public List<Mkt> getDataListByKeys(String[] keys) throws ServiceException {
		List<Mkt> pojoList = new ArrayList<Mkt>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getDataListByTypes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(keys, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				pojoList.add(mkt);
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
	 * 查询所有可用交易市场字典信息
	 * 
	 * @return
	 */
	public List<MarketVoc> getAllMkt() {
		List<MarketVoc> mktList = new ArrayList<MarketVoc>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = mktSqlBuilder.getAllMktSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MarketVoc mktvoc = new MarketVoc();
				mktvoc.setC_MKTVOC_CODE(rs.getString(1));
				mktvoc.setC_MKTVOC_NAME(rs.getString(2));
				mktvoc.setC_DV_MKT_TYPE(rs.getString(3));
				mktList.add(mktvoc);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return mktList;
	}

	/**
	 * 根据时间戳获取市场，用于缓存刷新
	 * 
	 * @param timestamp
	 *            时间戳
	 * @return 市场列表
	 */
	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataListByTimestamp();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, timestamp);
			rs = pstmt.executeQuery();
			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-19 蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Mkt.class.newInstance());
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class, props);
				pojoList.add(mkt);
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
	 * 检查相同地区是否存在相同的交易所
	 * 
	 * @param MktCode
	 *            待检查交易所代码
	 * @return unExist||unAudit||exist
	 */
	public String getCheckStatus(String MktCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buffer = new StringBuffer();
		String status = "unExist";
		try {
			conn = this.loadNewConnection();
			buffer.append(" SELECT A.C_DE_CODE, B.N_CHECK_STATE FROM T_P_BI_MKT A ");
			buffer.append(" LEFT JOIN T_P_BI_AREA B ON A.C_AREA_CODE = B.C_AREA_CODE ");
			buffer.append(" WHERE A.C_DE_CODE = ? ");
			pstmt = conn.prepareStatement(buffer.toString());
			pstmt.setString(1, MktCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				if (rs.getString("n_check_state") == null) {
					status = "unExist";
				} else if (rs.getString("n_check_state").equals("0")) {
					status = "unAudit";
				} else {
					status = "exist";
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buffer);
		}
		return status;
	}

	/**
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息<br>
	 * 关联结算机构查询交易市场
	 * 
	 * @return 交易市场列表
	 * @throws ServiceException
	 */
	public List<MktExtend> getAllDataListAux() throws ServiceException {
		List<MktExtend> pojoList = new ArrayList<MktExtend>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		MktSqlBuilder dsServiceBuilder = null;
		MktExtend t = null;
		 
        List<Vocabulary> vocList = CacheUtil.getDataListByTypes("ZQ_JSJG");
        for (int i = 0; i < vocList.size(); i++) {
        	//Fortify 规范代码改造避免空指针异常
			    t = new MktExtend();
        	    t.setC_DV_MKT_TYPE(" ");
				t.setC_PARAENT_CODE("root");
				t.setC_MKT_CODE(vocList.get(i).getC_DV_CODE());
				t.setC_MKT_NAME(vocList.get(i).getC_DV_NAME());
				t.setC_MKT_NAME_EN(" ");
				t.setC_MKT_NAME_ST(" ");
				t.setC_SWIFT_CODE(" ");
				t.setC_DE_CODE(" ");
				t.setC_HDAY_CODE(" ");
				t.setC_AREA_CODE(" ");
				t.setN_SETT_DAYS(0);
				t.setC_DESC(" ");
				t.setAuditState(1);
				t.setModifier(" ");
				t.setOperator(" ");
				t.setModifyDate(" ");
				t.setAuditDate(" ");
				t.setC_FIX_CODE(" ");
				t.setId(" ");
				t.setC_MKT_NO(vocList.get(i).getC_DV_CODE());
				
				pojoList.add(t);
		}
        
     
		
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver,
					new MktExtendSqlBuilder());
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = dsServiceBuilder.getAllAuxDataSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, MktExtend.class);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			logger.log("获取清算机构出错！", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}

	/**
	 * 判断交易市场代码是否与清算机构重复<br>
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息
	 * 
	 * @param mktCode
	 *            市场代码
	 * @return
	 */
	public String compareQsjg(String MktCode) {
        String status = "0";
        Map<String, Vocabulary> vocmap = CacheUtil.getVocCacheByType("ZQ_JSJG");
        if (vocmap.containsKey(MktCode)) {
            status = "1";
        }
        return status;
	}

	/**
	 * 根据市场代码取值 STORY #19553 最低备付金调整
	 * 
	 * @author dingshalu
	 * @date 2015-8-5
	 * @return
	 */
	public List<Mkt> getAllDataSqlByKeys(String[] types)
			throws ServiceException {
		List<Mkt> pojoList = new ArrayList<Mkt>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		MktSqlBuilder dsServiceBuilder = null;
		Mkt mkt = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataByCodes();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				mkt = rsTools.ResultToBeanGeneric(rs, Mkt.class);
				pojoList.add(mkt);
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
}
