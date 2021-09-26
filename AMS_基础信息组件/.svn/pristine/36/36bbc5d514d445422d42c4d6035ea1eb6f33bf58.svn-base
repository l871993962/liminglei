package com.yss.ams.base.information.modules.bi.tdchan.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.bi.tdchan.pojo.TdChanExtend;
import com.yss.ams.base.information.support.bi.tdchan.pojo.Chan;
import com.yss.ams.base.information.support.bi.tdchan.pojo.SecTransfer;
import com.yss.ams.base.information.support.bi.tdchan.pojo.TdChan;
import com.yss.ams.base.information.util.cache.CacheUtil;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;


public class TdChanDao extends GeneralDao {

	private TdChanSqlBuilder tdChanSqlBuilder = null;

	public TdChanDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.tdChanSqlBuilder = (TdChanSqlBuilder)sqlBuilder;
	}
	

	public List<BasePojo> getPortRelaTdChanDao(HashMap<String, Object> paraMap,
			Class<?> clazz) {
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
			sql = this.tdChanSqlBuilder.getPortRelaTdChanSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors((BasePojo)clazz.newInstance());
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, clazz);
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
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
	
	public List<BasePojo> getPortRelaTdOrgDao(HashMap<String, Object> paraMap,
			Class<?> clazz) {
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
			sql = this.tdChanSqlBuilder.getPortRelaTdOrgSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors((BasePojo)clazz.newInstance());
			while (rs.next()) {
				TdChan t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
				t.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				pojoList.add(t);
				TdChan chan = new TdChan();
				chan.setAuditDate(rs.getString("C_CHECK_TIME_B"));
				chan.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				chan.setC_DESC(rs.getString("C_DESC_B"));
				chan.setId(rs.getString("C_IEDN_B"));
				chan.setModifier(rs.getString("C_UPDATE_BY_B"));
				chan.setModifyDate(rs.getString("C_UPDATE_TIME_B"));
				chan.setC_DV_CHAN_TYPE(rs.getString("C_DV_TYPE_CODE"));
				chan.setC_ORG_CODE(rs.getString("C_PORT_CODE"));
				chan.setC_TD_CHAN_CODE(rs.getString("C_RELA_CODE"));
				chan.setC_MKT_CODE(rs.getString("C_RELA_TYPE"));
				pojoList.add(chan);
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
	 * BUG #172866 【加急】【南方基金】支持场外申赎业务流水的EXCEL格式的导入--存在问题汇总
	 * edit by zouyuan 20170914 根据选择的组合信息，加载关联的交易渠道信息，以及所有机构信息
	 * @param types
	 * @return
	 */
	/* START 数据服务方法 */
	/*修改ＰＯＪＯ类，实现树形结构展示byleeyu20130524*/
	public List<BasePojo> getDataListByPort(String[] types){
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChanExtend t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getCommonQuerySqlBufByPort();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			
	        List<Vocabulary> vocList = CacheUtil.getDataListByTypes("CHAN_TYPE");
			for (int i = 0; i < vocList.size(); i++) {
				//Fortify 规范代码改造避免空指针异常
				t = new TdChanExtend();
				t.setId(" ");
				t.setC_DV_CHAN_TYPE(vocList.get(i).getC_DV_CODE());
				t.setC_P_CODE("[root]");
				t.setC_TD_CHAN_CODE(vocList.get(i).getC_DV_CODE());
				t.setC_TD_CHAN_NAME(vocList.get(i).getC_DV_NAME());
				t.setC_ORG_CODE(" ");
				t.setC_MKT_CODE("交易市场代码");
				t.setC_DESC(" ");
				t.setAuditState(1);
				t.setModifier(" ");
				t.setOperator(" ");
				t.setModifyDate(" ");
				t.setAuditDate(" ");
				pojoList.add(t);
			}
	         //游标回滚到初始位置
             rs.first();
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChanExtend());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
				t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class, props);
				t.setC_P_CODE("[root]");
				t.setC_MKT_NAME("交易市场");
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
	/*修改ＰＯＪＯ类，实现树形结构展示byleeyu20130524*/
	public List<TdChanExtend> getAllDataList() throws Exception {
		List<TdChanExtend> pojoList = new ArrayList<TdChanExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChanExtend t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChanExtend());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
				t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class, props);
				t.setC_P_CODE(rs.getString(TdChanExtendColumnName.c_P_CODE.toString()));
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

	public TdChan getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChan t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, TdChan.class);
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

	public List<TdChanExtend> getDataListByTypes(String[] types) throws Exception {
		List<TdChanExtend> pojoList = new ArrayList<TdChanExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChanExtend t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChanExtend());
			while (rs.next()) {
				//String parentId = rs.getString(3);
				//t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
				//t.setC_P_CODE(parentId);
				t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class, props);
				t.setC_P_CODE(rs.getString(3));
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

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChan t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChan());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, TdChan.class);
				t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
				keyValueMap.put(t.getC_TD_CHAN_CODE(), t.getC_TD_CHAN_NAME());
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
	
	public List<TdChan> getDataListByKeys(String[] keys) throws Exception {
		List<TdChan> pojoList = new ArrayList<TdChan>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChan t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChan());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, TdChan.class);
				t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
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
	/* END 数据服务方法 */


	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChan t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, timestamp);

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChan());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, TdChan.class);
				t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
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


	public List<BasePojo> queryPortRelaTdChan(HashMap<String, Object> paraMap, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.tdChanSqlBuilder.queryPortRelaTdChanSql(getParaName(paraMap));
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
		  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
		   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
		  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChan());
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, TdChan.class);
				TdChan t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
				/*BUG #108593 产品信息》产品基本信息》交易渠道设置
				 * 此处审核状态显示为交易渠道关联纪录是否审核,而非交易渠道审核状态*/
				//((TdChan)t).setAuditState(rs.getInt("N_CHECK_STATE_B"));
				t.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				pojoList.add(t);
				TdChan chan = new TdChan();
				chan.setAuditDate(rs.getString("C_CHECK_TIME_B"));
				chan.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				chan.setC_DESC(rs.getString("C_DESC_B"));
				chan.setId(rs.getString("C_IEDN_B"));
				chan.setModifier(rs.getString("C_UPDATE_BY_B"));
				chan.setModifyDate(rs.getString("C_UPDATE_TIME_B"));
				chan.setC_DV_CHAN_TYPE(rs.getString("C_DV_TYPE_CODE"));
				chan.setC_ORG_CODE(rs.getString("C_PORT_CODE"));
				chan.setC_TD_CHAN_CODE(rs.getString("C_RELA_CODE"));
				chan.setC_MKT_CODE(rs.getString("C_RELA_TYPE"));
				pojoList.add(chan);
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


	public int queryPortRelaTdChanCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = this.tdChanSqlBuilder.getqueryPortRelaTdChanCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("交易席位功能模块：根据条件查询席位信息失败", ex);
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	public List<BasePojo> queryPortRelaTdOrg(HashMap<String, Object> paraMap, PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.tdChanSqlBuilder.queryPortRelaTdOrgSql(getParaName(paraMap));
			sql = buildPagingSql(sql, page);
			pstmt = conn.prepareStatement(sql);
		  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
		   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
		  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/

			rs = pstmt.executeQuery();

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChan());
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, TdChan.class);
				TdChan t = rsTools.ResultToBeanGeneric(rs, TdChan.class, props);
				/*BUG #108593 产品信息》产品基本信息》交易渠道设置
				 * 此处审核状态显示为交易渠道关联纪录是否审核,而非交易渠道审核状态*/
				//((TdChan)t).setAuditState(rs.getInt("N_CHECK_STATE_B"));
				t.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				pojoList.add(t);
				TdChan chan = new TdChan();
				chan.setAuditDate(rs.getString("C_CHECK_TIME_B"));
				chan.setAuditState(rs.getInt("N_CHECK_STATE_B"));
				chan.setC_DESC(rs.getString("C_DESC_B"));
				chan.setId(rs.getString("C_IEDN_B"));
				chan.setModifier(rs.getString("C_UPDATE_BY_B"));
				chan.setModifyDate(rs.getString("C_UPDATE_TIME_B"));
				chan.setC_DV_CHAN_TYPE(rs.getString("C_DV_TYPE_CODE"));
				chan.setC_ORG_CODE(rs.getString("C_PORT_CODE"));
				chan.setC_TD_CHAN_CODE(rs.getString("C_RELA_CODE"));
				chan.setC_MKT_CODE(rs.getString("C_RELA_TYPE"));
				pojoList.add(chan);
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


	public int queryPortRelaTdOrgCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = this.tdChanSqlBuilder.getqueryPortRelaTdOrgCountSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
  /**Start 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错
   * 添加非空判断*/
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("ARRAY_C_PORT_CODE") == null ? "" : paraMap.get("ARRAY_C_PORT_CODE").toString(),conn));
  /**End 20150318 modified by liubo.BUG #109422 产品基本信息，关联界面查询报错*/
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("交易席位功能模块：根据条件查询席位信息失败", ex);
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return recCount;
	}
	
	/**
	 * 获取渠道信息表中最大的渠道代码，通过正则表达式，只获取数值类型的渠道代码
	 * BUG #105790 宏源证券：银行间API回购交易清算中交易渠道未取得bug 
	 * @return 返回最大渠道代码
	 */
	public String getMaxTdChanCode(){
		String orgCode = "";
		StringBuffer bufSql = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			/*bufSql.append(" select max(to_number(substr(a.c_td_chan_code,5))) as c_td_chan_code ");
			bufSql.append(" from T_P_AB_TD_CHAN a ");
			bufSql.append(" where substr(a.c_td_chan_code,1,4) = 'JYXW' and regexp_like(substr(a.c_td_chan_code,5), '^[[:digit:]]+$')");
			*/
			
			bufSql.append(" select c_td_chan_code														");
			bufSql.append("   from (select to_number(substr(a.c_td_chan_code, 5)) c_td_chan_code        ");
			bufSql.append("           from T_P_AB_TD_CHAN a                                             ");
			bufSql.append("          where substr(a.c_td_chan_code, 1, 4) = 'JYXW'                      ");
			bufSql.append("            and regexp_like(substr(a.c_td_chan_code, 5), '^[[:digit:]]+$')   ");
			bufSql.append("          order by to_number(substr(a.c_td_chan_code, 5)) desc ) tab         ");
			bufSql.append("  where rownum <= 1                                                          ");
			
			pstmt = conn.prepareStatement(bufSql.toString());
			
			logger.debug(bufSql.toString());

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				orgCode = rs.getString("c_td_chan_code");
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("交易席位功能模块：获取渠道信息表中最大的渠道代码失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return orgCode;
	}
	
	/**
	 * 根据交易对手方(关联机构代码)查询对应的交易席位信息
	 * BUG #105790 宏源证券：银行间API回购交易清算中交易渠道未取得bug 
	 * @param orgCode 关联机构名称
	 * @return
	 */
	public BasePojo getDataByOrgCode(String orgCode){
		String sql = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BasePojo pojo = null;
		try {
			ResultSetTools rsTools = null;
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = "select a.* from t_p_ab_td_chan a where a.n_check_state = 1 and a.c_dv_chan_type = 'CHAN_ORG' and a.c_org_code = ? ";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orgCode);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			if (rs.next()) {
				pojo = rsTools.ResultToBeanGeneric(rs, TdChan.class);
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("交易席位功能模块：根据交易对手方(关联机构代码)查询对应的交易席位信息失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return pojo;
	}
	

	/**
	 * By Jinghehe 2015-9-29 
	 * 获取所有渠道数据，包括ALL 构造的数据
	 * @param types
	 * @return
	 * @throws Exception
	 */
	public List<TdChanExtend> getDataListByComm(String[] types) throws Exception {
		List<TdChanExtend> pojoList = new ArrayList<TdChanExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChanExtend t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByComm();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChanExtend());
			while (rs.next()) {
				//String parentId = rs.getString(3);
				//t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
				//t.setC_P_CODE(parentId);
				t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class, props);
				t.setC_P_CODE(rs.getString(3));
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
	 * 组合关联交易席位DAO层
	 * zhoushuhang 2016-4-7  在ETF补票日期界面中增加补票席位。通过选择组合带出组合对应补票席位。
	 */
	public List<BasePojo> queryTradeSeatDao(String portCode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;

			String sql = "";
			ResultSetTools rsTools = null;

			TdChanExtend t = null;
			try {
				rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
				conn = this.loadNewConnection();
				conn.setAutoCommit(false);
				sql = ((TdChanSqlBuilder)(sqlbuilder)).queryPortRelaTradeSeatsql();

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, portCode);
				rs = pstmt.executeQuery();
				//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
				// 南方基金性能优化 zhanghualin 2017-3-17
				PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new TdChanExtend());
				while (rs.next()) {
					//t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
					t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class, props);
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
	 * STORY37763【南方基金】复制建仓功能增加席位复制
	 * @Title query 
	 * @Description 
	 * @author liulei@ysstech.com
	 * @date 2017年1月18日下午1:33:32
	 * @param portCode
	 * @return
	 * @throws Exception
	 */
	public List<Chan> query(String portCode) throws Exception {
		List<Chan> pojoList = new ArrayList<Chan>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;

		Chan t = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = " select a.* from T_P_AB_PORT_RELA  a "
			    + " join t_p_ab_td_chan c on c.C_TD_CHAN_CODE = a.C_RELA_CODE "
			    + "      AND C.N_CHECK_STATE = 1 "
				+ " where a.c_port_code = ? ";
			//	+ "where c.c_port_code = ? "
			//	+ "order by a.n_check_state asc, a.c_update_time desc, a.rowid asc";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			//update by zouyuan 【合并代码】STORY #39432 【南方基金】【QDII】QD产品交易流水的交易渠道优化需求
			// 南方基金性能优化 zhanghualin 2017-3-17
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Chan());
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, Chan.class);
				t = rsTools.ResultToBeanGeneric(rs, Chan.class, props);
				pojoList.add(t);
			} 
		}catch (SQLException e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		}finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	/**
	 * STORY37763【南方基金】复制建仓功能增加席位复制
	 * @Title query 
	 * @Description 
	 * @author liulei@ysstech.com
	 * @date 2017年1月18日下午1:33:32
	 * @param portCode
	 * @return
	 * @throws Exception
	 */
	public void insert(Chan basepojo,String portCodeList){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(true);
			
			sql = " insert into T_P_AB_PORT_RELA (c_iden,c_port_code, c_rela_type,c_rela_code, "
				+ " c_dv_type_code,c_desc,n_check_state,c_update_by,c_update_time,c_check_by, "
				+ " c_check_time,c_ca_code) values (sequ_P_AB_PORT_RELA.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,portCodeList);
			pstmt.setString(2,basepojo.getC_RELA_TYPE());
			pstmt.setString(3,basepojo.getC_RELA_CODE());
			pstmt.setString(4,basepojo.getC_DV_TYPE_CODE());
			pstmt.setString(5,basepojo.getC_DESC());
			pstmt.setDouble(6, basepojo.getAuditState());
			pstmt.setString(7, basepojo.getModifier());
			pstmt.setString(8, basepojo.getModifyDate());
			pstmt.setString(9, basepojo.getOperator());
			pstmt.setString(10, basepojo.getAuditDate());
			pstmt.setString(11, "");
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException("插入失败：" + e.getMessage(), e);
		}finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @Title 在代码转换表中查询出非自动的数据
	 * @Description 
	 * @author guohui@ysstech.com
	 * @date 2017年02月08日下午1:33:32
	 * @param SeatCodes
	 * @return
	 * @throws Exception
	 */
	public List<SecTransfer> getFzdTdSeat(String[] SeatCodes){
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		List<SecTransfer> seatTrans = new ArrayList<SecTransfer>();
		String sql = " select * from T_D_MP_SEC_TRANSFER where C_TYPE = 'R' and c_data_idf != 'Z' and C_SEC_CODE in (SELECT * FROM TABLE(?)) ";
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
			logger.error("getFzdTdSeat->"+e.getMessage());
		}finally{
			DbFun.closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return seatTrans;
	}
	
	/**
	 * STORY38131【南方基金】【紧急】社保理事会要求交易席位科目是6位，不足6位前面补0
	 * @Title setSecTran 
	 * @Description 构造证券代码转换信息实体
	 * @author guohui@ysstech.com
	 * @date 2016年11月2日下午2:29:52
	 * @param secCode  渠道代码
	 * @param gzCode   规则代码
	 * @param plCode   披露代码
	 * @param date     记录变更事件
	 * @param tdChans     
	 * @return void
	 */
	public void setSeatTran(String secCode, String gzCode,String plCode,Date date,List<SecTransfer> secs){
		SecTransfer secTran =new SecTransfer();
		secTran.setC_SEC_CODE(secCode);
		secTran.setC_PUB_CODE(plCode);
		secTran.setC_DATA_IDF("Z");
		secTran.setC_TRANSFER_CODE(gzCode);
		secTran.setAuditState(!" ".equals(plCode) ? 1 : 0);
		secTran.setModifier(ContextFactory.getContext().getUserCode());
		secTran.setModifyDate(YssFun.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		secTran.setC_TYPE("R");
		if (!" ".equals(plCode)) {
			secTran.setOperator(ContextFactory.getContext().getUserCode());
			secTran.setAuditDate(YssFun.formatDate(date, "yyyy-MM-dd HH:mm:ss"));
		}
		secs.add(secTran);
	}
	
	/**
	 * 删除需要复制的交易渠道数据
	 * 由于原来该功能是【产品基本信息】中"复制创建"使用的，复制给新组合，所以不用考虑重复的
	 * 但是在【产品参数复制】中复制给现有组合就需要考虑重复数据了
	 * 
	 * BUG #169088 【易方达】【紧急】产品参数复制设置不了交易渠道
	 * 
	 * @Title deleteCopyData 
	 * @Description 
	 * @author heliang@ysstech.com
	 * @date 2017年8月16日下午17:42:32
	 * @param basepojo
	 * @param portCodeList
	 */
	public void deleteCopyData(Chan basepojo, String portCodeList) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();

		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			buf.append(" DELETE FROM T_P_AB_PORT_RELA ");
			buf.append(" WHERE C_PORT_CODE = ? ");
			buf.append(" AND C_RELA_TYPE = ? ");
			buf.append(" AND C_RELA_CODE = ? ");
			buf.append(" AND C_DV_TYPE_CODE = ? ");
			
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, portCodeList);
			pstmt.setString(2, basepojo.getC_RELA_TYPE());
			pstmt.setString(3, basepojo.getC_RELA_CODE());
			pstmt.setString(4, basepojo.getC_DV_TYPE_CODE());
			pstmt.executeUpdate();

			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			throw new DataAccessException("删除复制的交易渠道数据失败：" + e.getMessage(), e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	public List<TdChanExtend> getDataListByPorts(String[] types) throws Exception {
		List<TdChanExtend> pojoList = new ArrayList<TdChanExtend>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		TdChanSqlBuilder dsServiceBuilder = null;
		TdChanExtend t = null;
		try {
			dsServiceBuilder = new TdChanSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataListByPorts();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(types,conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String parentId = rs.getString(3);
				t = rsTools.ResultToBeanGeneric(rs, TdChanExtend.class);
				t.setC_P_CODE(parentId);
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
}
