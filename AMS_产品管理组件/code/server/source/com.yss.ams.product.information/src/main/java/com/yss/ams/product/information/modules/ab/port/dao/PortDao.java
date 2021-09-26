package com.yss.ams.product.information.modules.ab.port.dao;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortTreeView;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.ams.product.information.util.port.PortDataType;
import com.yss.ams.product.information.util.port.PortUtil;
import com.yss.deploy.vo.AddDeployGroupVo;
import com.yss.fast.right.support.authbail.service.IAuthBailService;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.fast.right.support.right.service.IUserPostDataService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.LoggingInfo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidDataException;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.log.runtime.WriteLog;
import com.yss.framework.util.DateUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.platform.support.comm.pojo.StockTypeTree;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;
import com.yss.right.constants.RightConstants;
import com.yss.right.pojo.DataRight;

/**
 * <产品基本信息>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortDao extends GeneralDao {
	private PortSqlBuilder portSqlBuilder;

	public PortDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portSqlBuilder = (PortSqlBuilder) sqlbuilder;
	}
	
	/**
	 * 查询组合树信息，add by lidaoshen
	 * 
	 * @return List<BasePojo>
	 */
	public List<Port> queryPortType() {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.queryPortTypeSql();

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Port());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
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
	 * 查询组合树信息，add by gongjinzhi 20161208
	 * 
	 * @return List<DataRight>
	 */
	public List<DataRight> queryPortTree() {
		List<DataRight> pojoList = new ArrayList<DataRight>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.queryPortTreeSql();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, Port.class);
				//结果集数据较多时，上述方法通过反射存在性能问题  对于组合对象，抽取公用方法，直接对属性进行赋值，不再通过反射，提升效率  BUG #220367 系统模块打开时间长,约4-20s 
				DataRight t = new DataRight();
				t.setC_DATA_CODE(rs.getString("C_PORT_CODE"));
				t.setC_DATA_NAME(rs.getString("C_PORT_NAME"));
				//组合
				t.setC_DATA_TYPE("1");
				t.setC_DATA_CODE_P(rs.getString("C_PORT_CODE_P"));
				if ("PORT_TYPE".equalsIgnoreCase(rs.getString("DATA_TYPE"))) {
					t.setAss_Port_Type(1);
				}
				//// 根节点是没有id值的
				t.setId(rs.getString("C_IDEN"));
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
	 * 根据传入的组合状态过滤数据
	 * @param customParam
	 * @return
	 */
	public List<DataRight> queryPortTree(HashMap<String, String> customParam) {
		List<DataRight> pojoList = new ArrayList<DataRight>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String portState = "";
		try {
			portState = customParam.get("dataParam");
			conn = this.loadNewConnection();
			sql = portSqlBuilder.queryPortTreeSql();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				//BasePojo t = rsTools.ResultToBean(rs, Port.class);
				//结果集数据较多时，上述方法通过反射存在性能问题  对于组合对象，抽取公用方法，直接对属性进行赋值，不再通过反射，提升效率  BUG #220367 系统模块打开时间长,约4-20s 
				DataRight t = new DataRight();
				t.setC_DATA_CODE(rs.getString("C_PORT_CODE"));
				t.setC_DATA_NAME(rs.getString("C_PORT_NAME"));
				//组合
				t.setC_DATA_TYPE("1");
				t.setC_DATA_CODE_P(rs.getString("C_PORT_CODE_P"));
				if ("PORT_TYPE".equalsIgnoreCase(rs.getString("DATA_TYPE"))) {
					t.setAss_Port_Type(1);
				}
				//// 根节点是没有id值的
				t.setId(rs.getString("C_IDEN"));
				
				if((portState != null && portState.contains(rs.getString("C_DV_PROD_STATE")))
						|| "ASS_TYPE".equalsIgnoreCase(rs.getString("DATA_TYPE"))){
					pojoList.add(t);
				} else if (portState == null){
					pojoList.add(t);
				}
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

	public List<BasePojo> getPlanRelaPortAdd(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			
			sql = portSqlBuilder.getPlanRelaPortAdd();
			String[] portCodes = paraMap.get("C_PORT_CODE").toString().split(",");
			insertTempPortCodeTable(conn, portCodes);
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, paraMap.get("C_PLAN_TYPE").toString());
			pstmt.setString(2, paraMap.get("C_PLAN_CODE").toString());
			pstmt.setString(3, paraMap.get("D_BEGIN").toString());
			pstmt.setString(4, paraMap.get("D_END").toString());
			pstmt.setString(5, paraMap.get("D_BEGIN").toString());
			pstmt.setString(6, paraMap.get("D_END").toString());
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
				
				pojoList.add(t);
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

		return pojoList;
	}

	public List<BasePojo> getPlanRelaPortBrow(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getPlanRelaPortBrow();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			pstmt.setString(1, paraMap.get("C_PORT_CODE").toString());

			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 资产树详细界面获取数据列表
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortAssTreeList(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		Port t = null;
		PortAssTreeSqlBuilder portAssTreeSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			portAssTreeSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portAssTreeSqlBuilder);

			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = portSqlBuilder.getPortAssTreeListSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("C_PORT_CODE".equals(valueFieldName)) {
					continue;
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			if (paraMap.containsKey("C_PORT_CODE")) {
				pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(paraMap.get("C_PORT_CODE")), conn));
			}

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
			// try {
			// if (conn != null) {
			// conn.close();
			// }
			// } catch (SQLException e) {
			// e.printStackTrace();
			// }
		}

		return pojoList;
	}

	/**
	 * 资产树详细界面新增时取数据列表
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortAssTreeListAddForm(
			HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		Port t = null;
		PortAssTreeSqlBuilder portAssTreeSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			portAssTreeSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portAssTreeSqlBuilder);

			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = portSqlBuilder.getPortAssTreeListAddFormSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("C_PORT_CODE".equals(valueFieldName)) {
					continue;
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			if (paraMap.containsKey("C_PORT_CODE")) {
				pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(paraMap.get("C_PORT_CODE")), conn));
			}

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 资产树详细界面新增时取数据列表
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> getPortAssTreeListALL(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		Port t = null;
		PortAssTreeSqlBuilder portAssTreeSqlBuilder = null;
		ResultSetTools rsTools = null;
		try {
			portAssTreeSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portAssTreeSqlBuilder);

			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = portSqlBuilder.getPortAssTreeListALLSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("C_PORT_CODE".equals(valueFieldName)) {
					continue;
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			if (paraMap.containsKey("C_PORT_CODE")) {
				pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(paraMap.get("C_PORT_CODE")), conn));
			}

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<BasePojo> getAssetTreeView(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		Port t = null;
		PortAssTreeSqlBuilder portAssTreeSqlBuilder = null;
		ResultSetTools rsTools = null;

		try {
			portAssTreeSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portAssTreeSqlBuilder);

			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = portSqlBuilder.getAssetTreeView(paraNameList);

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			int index = 1;
			for (String valueFieldName : paraNameList) {
				if ("C_PORT_CODE".equals(valueFieldName)) {
					continue;
				} else if ("status".equals(valueFieldName)) { // 资产树查询状态
					continue;
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			if (paraMap.containsKey("C_PORT_CODE")) {
				pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(paraMap.get("C_PORT_CODE")), conn));
			}

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Port());
			List<String> parentList = new ArrayList<String>();
			while (rs.next()) {
				//t = rsTools.ResultToBeanGeneric(rs, Port.class);
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				pojoList.add(t);
				if(!StringUtil.IsNullOrEmptyT(t.getC_PORT_CODE_P())
						&& !parentList.contains(t.getC_PORT_CODE_P()))
				{
					parentList.add(t.getC_PORT_CODE_P());
				}
			}
			
			//// By Jinghehe 2017-8-28 BUG #163743 【平安大华】产品树形结构界面点击新增与复制后，界面加载时间过长
			//// start with.....connect by 性能很慢，这边处理成，获取组合数据时候，在根据父节点代码查询父节点
			pojoList.addAll(this.getParentList(parentList));
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}

	public List<Port> getParamSetPortList(HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getParamSetPortListSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			setParamSetPortQuery(pstmt, paraMap);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 功能描述：获取权限管理列表
	 * 
	 * @param paraMap
	 *            参数集合
	 * @return
	 */
	public List<Port> getRightManagePortList(HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			convertNewFastRightParam(paraMap);
			sql = portSqlBuilder.getRightManagePortList(paraMap);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			setRightManageSetPortQuery(pstmt, paraMap);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 功能描述：获取权限管理列表
	 * 
	 * @param paraMap
	 *            参数集合
	 * @return
	 */
	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getRightManagePortListExpertAddSql(paraMap);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			setRightManageSetPortQueryExpAdd(pstmt, paraMap);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	private void setParamSetPortQuery(PreparedStatement ptmt,
			HashMap<String, Object> paraMap) throws Exception {
		String datCode = (String) paraMap.get("C_DAT_CODE");
		String portCode = (String) paraMap.get("C_PORT_CODE");
		String beginDate = (String) paraMap.get("D_BEGIN");
		String endDate = (String) paraMap.get("D_END");
		String dspCode = (String) paraMap.get("C_DSP_CODE");
		try {
			ptmt.setObject(1, datCode);
			ptmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCode, ptmt.getConnection()));
			ptmt.setObject(3, datCode);
			ptmt.setObject(4, dspCode);
			ptmt.setObject(5, beginDate);
			ptmt.setObject(6, endDate);
			ptmt.setObject(7, endDate);
			ptmt.setObject(8, beginDate);
			ptmt.setObject(9, beginDate);
			ptmt.setObject(10, endDate);
			ptmt.setArray(11, OraDbTool.newInstance().sqlOverLongCondition(portCode, ptmt.getConnection()));
		} catch (SQLException e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
			throw e;
		}

	}

	/**
	 * 功能描述：获取权限管理设置参数
	 * 
	 * @param ptmt
	 * @param paraMap
	 * @throws Exception
	 */
	private void setRightManageSetPortQuery(PreparedStatement ptmt,
			HashMap<String, Object> paraMap) throws Exception {
		String jobTitle = (String) paraMap.get("jobTitle");
		String portCode = "";
		String postCode = "";
		String userCode = "";
		String portArrCode = "";
		try {
		    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
			userCode = (String) paraMap.get("C_USER_CODE"); //此处已经将FAST权限中的组合信息进行了组装
			portCode = (String) paraMap.get("C_PORT_CODE");
//			postCode = (String) paraMap.get("C_POST_CODE");
			portArrCode = (String) paraMap.get("ARRAY_PORT_CODE");
			portArrCode = (portArrCode != null && portArrCode.trim().length() > 0) ? portArrCode
					.replaceAll("'", "") : portArrCode;
			if (portCode == null)
				portCode = "";

			if (portArrCode == null)
				portArrCode = "";

			//ptmt.setString(1, userCode);
			//ptmt.setString(2, postCode);
			
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(userCode, ptmt.getConnection()));
			if (!YssCons.JOB_TITLE.equals(jobTitle)) {
				if (!"".equals(portArrCode)) {
					ptmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portArrCode, ptmt.getConnection()));
				} else if (!"".equals(portCode)) {
					ptmt.setString(2, portCode);
				}

			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
			throw new Exception(e);
		}
	}

	/**
	 * 功能描述：获取权限管理设置参数
	 * 
	 * @param ptmt
	 * @param paraMap
	 * @throws Exception
	 */
	private void setRightManageSetPortQueryExpAdd(PreparedStatement ptmt,
			HashMap<String, Object> paraMap) throws Exception {
		String jobTitle = (String) paraMap.get("jobTitle");
		String portCode = "";
		String portArrCode = "";
		try {
			portCode = (String) paraMap.get("C_PORT_CODE");
			portArrCode = (String) paraMap.get("ARRAY_PORT_CODE");

			if (portCode == null)
				portCode = "";

			if (portArrCode == null)
				portArrCode = "";

			if (!YssCons.JOB_TITLE.equals(jobTitle)) {
				if (!"".equals(portArrCode)) {
					ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portArrCode, ptmt.getConnection()));
				} else if (!"".equals(portCode)) {
					ptmt.setString(1, portCode);
				}

			}
		} catch (Exception e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
			throw new Exception(e);
		}
	}

	public List<BasePojo> queryDspPortDao(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDspPortSql(paraMap);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			setDspParaPstmt(pstmt, paraMap);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	private void setDspParaPstmt(PreparedStatement pstmt,
			HashMap<String, Object> paraMap) {
		String strPort = "";
		String datCode = "";
		// String startDate = "";
		// String endDate = "";
		// String dspCode = "";

		try {
			strPort = paraMap.get("C_PORT_CODE").toString();
			// startDate = paraMap.get("D_BEGIN").toString();
			// endDate = paraMap.get("D_END").toString();
			// dspCode = paraMap.get("C_DSP_CODE").toString();
			if (paraMap.containsKey("C_DAT_CODE")) {
				datCode = paraMap.get("C_DAT_CODE").toString();
				pstmt.setString(1, datCode);
				pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
				pstmt.setString(3, datCode);
				// pstmt.setString(4, dspCode);
				// pstmt.setString(5, startDate);
				// pstmt.setString(6, endDate);
				// pstmt.setString(7, endDate);
				// pstmt.setString(8, startDate);
				pstmt.setArray(4, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
			} else if (paraMap.containsKey("C_DAT_CLS")) {// add by zhaoxianlin
				// 20130518 story
				// #3659
				datCode = paraMap.get("C_DAT_CLS").toString();
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
				pstmt.setString(2, datCode);
				pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
			} else {
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
				// pstmt.setString(2, dspCode);
				// pstmt.setString(3, startDate);
				// pstmt.setString(4, endDate);
				// pstmt.setString(5, endDate);
				// pstmt.setString(6, startDate);
				pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(strPort, pstmt.getConnection()));
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	public List<BasePojo> queryDataByBrowDao(HashMap<String, Object> paraMap,
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
			conn = this.loadNewConnection();
			paraNameList = getParaName(paraMap);
			sql = ((PortSqlBuilder) sqlbuilder).getDataByBrowSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			int index = 1;
			for (String valueFieldName : paraNameList) {
				pstmt.setObject(index, paraMap.get(valueFieldName));
				index++;
			}

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	public List<Port> getAllDataList() throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataListSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<Port> getPortTreeViewCommon() throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getPortTreeViewCommonSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		PortSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataListSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// t = rsTools.ResultToBeanGeneric(rs, Port.class);

				keyValueMap.put(rs.getString(PortAssTreeColumnName.c_PORT_CODE
						.toString()), rs
						.getString(PortAssTreeColumnName.c_PORT_NAME_ST // 投资组合列显示为投资简称
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

	public HashMap<String, String> getKeyConvertMap2() {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		PortSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataListSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// t = rsTools.ResultToBeanGeneric(rs, Port.class);

				keyValueMap.put(rs.getString(PortAssTreeColumnName.c_PORT_CODE
						.toString()), rs
						.getString(PortAssTreeColumnName.c_ASS_CODE // 投资组合列显示为投资简称
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
	 * Author : ChenLong Date : 2015-09-24 Status : Modify Comment:
	 * 清算codeStr条件为多组合，SQL参数值才改成组合方式
	 * 
	 * @param codeStr
	 * @return
	 */
	public Port getDataByCode(String codeStr) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataByCodeSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance()
					.sqlOverLongCondition(codeStr.split(","), conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			if (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<Port> getDataListByTypes(String[] types) throws Exception {
		List<Port> portList = null;
		Port port = null;
		Connection conn = null;
		PreparedStatement ptmt = null;
		ResultSet rs = null;
		String sql = "";
		PortSqlBuilder portBuilder = null;
		ResultSetTools rsTool = null;
		try {
			portList = new ArrayList<Port>();

			portBuilder = new PortSqlBuilder();
			rsTool = new ResultSetTools(dbNameResolver, portBuilder);

			sql = portBuilder.getListByTypes(dbNameResolver);
			conn = loadNewConnection();
			ptmt = conn.prepareStatement(sql);

			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types, conn));

			rs = ptmt.executeQuery();

			while (rs.next()) {
				port = rsTool.ResultToBeanGeneric(rs, Port.class);
				port.setdATA_TYPE(rs.getString("DATA_TYPE"));
				portList.add(port);
			}

		} catch (Exception e) {
			throw new DataAccessException("获取数据出错。", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return portList;
	}

	/**
	 * 根据资产树节点获取组合树（没有数据权限过滤）
	 * @param cTRCode
	 * @return
	 * @throws Exception
	 */
	public List<BasePojo> getDefaultAllPort(String cTRCode)
			throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultAllAssPort();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			pstmt.setString(3, cTRCode);
			pstmt.setString(4, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	
	public List<BasePojo> getDefaultUserPort(String ports, String cTRCode)
			throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultUserAssPort();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(ports, conn));
			pstmt.setString(3, cTRCode);
			pstmt.setString(4, cTRCode);
			pstmt.setArray(5, OraDbTool.newInstance().sqlOverLongCondition(ports, conn));
			pstmt.setString(6, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求   借用C_DESC字段存储组合关联的股东代码和席位代码
				t.setC_DESC(rs.getString("C_DESC1"));
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

	public List<BasePojo> getDefaultPort(String cTRCode) throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultAssPort();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 获得资产树形结构
	 * 
	 * @param cTRCode
	 * @return
	 * @throws Exception
	 */
	public List<Port> getAssPort(String cTRCode) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAssPort();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public HashMap<String, String> getDefaultUserPortMap(String ports,
			String cTRCode) throws Exception {
		HashMap<String, String> defaultPortMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		String portCode = "";
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultUserPort();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(ports, conn));
			pstmt.setString(3, cTRCode);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				portCode = rs.getString("C_PORT_CODE");

				defaultPortMap.put(portCode, portCode);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return defaultPortMap;
	}

	public boolean flag = true;
	
	public HashMap<String, Port> getPortNoClsDataMap() {
		HashMap<String, Port> dataMap = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = this.loadNewConnection();
			if(getShowChildPort() && getPortCls()){
				sql = portSqlBuilder.getPortTreeViewCommonSqlChild(dbNameResolver,false);
			}else{
				sql = portSqlBuilder.getPortTreeViewCommonSql(dbNameResolver);
			}
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-19 蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());

			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					t.setdATA_TYPE(rs.getString("DATA_TYPE"));

					portCode = t.getC_PORT_CODE();

					if (portCode == null) {
						portCode = "";
					}

					if (!dataMap.containsKey(portCode)) {
						dataMap.put(portCode, t);
					}
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataMap;
	}
	
	
	/**
	 * @see getPortDataMap 查询组合时将 T_S_DAT_ASS_TYPE中资产类型一起查询，
	 * 
	 * 组合放在缓存中查询之后，因资产类型数据没有缓存更新机制，所以还需单独查询出来，
	 * 
	 * 该方法只查询资产类型数据作为组合对象封装 {@link #getPortDataMap()}
	 * 
	 * */
	public HashMap<String, Port> getPortDataMapOnlyWithTssType() {
		HashMap<String, Port> dataMap = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
			conn = this.loadNewConnection();
			if(getShowChildPort() && getPortCls()){
				sql = portSqlBuilder.getPortTreeViewCommonSqlChildOnlyWithTssType(dbNameResolver,true);
			}else{
				sql = portSqlBuilder.getPortTreeViewCommonSqlOnlyWithTssType(dbNameResolver);
			}
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					t.setdATA_TYPE(rs.getString("DATA_TYPE"));

					portCode = t.getC_PORT_CODE();

					if (portCode == null) {
						portCode = "";
					}
					if (!dataMap.containsKey(portCode)) {
						dataMap.put(portCode, t);
					}
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
	}
	
	
	
	public HashMap<String, Port> getPortDataMap() {
		HashMap<String, Port> dataMap = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = this.loadNewConnection();
			if(getShowChildPort() && getPortCls()){
				sql = portSqlBuilder.getPortTreeViewCommonSqlChild(dbNameResolver,true);
			}else{
				sql = portSqlBuilder.getPortTreeViewCommonSql(dbNameResolver);
			}
			
			//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求        获取与组合关联的股东和席位，按|拼接，展示在前台股东和席位列
			sql = "select a.*,NVL(g.c_rela_code,'') || ',' || NVL(h.c_rela_code,'') as C_DESC1 from (" + sql + ") a"
				+ " LEFT JOIN (    "
			    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
			    + " FROM T_P_AB_PORT_RELA  "
			    + " WHERE c_rela_type='RELA_SH_ACC' and n_check_state=1"
			    + " group by c_port_code "
			    + " ) g    "
			    + " on g.c_port_code = a.c_port_code  "
			    + " LEFT JOIN (    "
			    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
			    + " FROM T_P_AB_PORT_RELA  "
			    + " WHERE c_rela_type='RELA_TD_SEAT' and n_check_state=1"
			    + " group by c_port_code "
			    + " ) h  "
			    + " on h.c_port_code = a.c_port_code ";
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-19 蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());

			while (rs.next()) {
				/*Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					t.setdATA_TYPE(rs.getString("DATA_TYPE"));

					portCode = t.getC_PORT_CODE();

					if (portCode == null) {
						portCode = "";
					}

					if (!dataMap.containsKey(portCode)) {
						dataMap.put(portCode, t);
					}
				}*/
				
				//BUG #220367 系统模块打开时间长,约4-20s 
				Port t = PortUtil.ResultToBean(rs);
				portCode = (t.getC_PORT_CODE() != null) ? t.getC_PORT_CODE() : "";
				if (null != t) {
					//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求      借用C_DESC字段存储组合关联的股东代码和席位代码
					t.setC_DESC(rs.getString("C_DESC1"));
				}
				if (!dataMap.containsKey(portCode)) {
					dataMap.put(portCode, t);
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataMap;
	}
	/**
	 * Fortify 规范代码改造
	 * @param sql_zclx
	 * @return
	 */
	private String getsqlForZCTG(String sql_zclx){
		//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求        获取与组合关联的股东和席位，按|拼接，展示在前台股东和席位列
	  String sql = "select a.*,NVL(g.c_rela_code,'') || ',' || NVL(h.c_rela_code,'') as C_DESC1 from (" 
			+  portSqlBuilder.getPortTreeViewZCTGSql(dbNameResolver,sql_zclx) 
			+ ") a"
			+ " LEFT JOIN (    "
		    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
		    + " FROM T_P_AB_PORT_RELA  "
		    + " WHERE c_rela_type='RELA_SH_ACC' and n_check_state=1"
		    + " group by c_port_code "
		    + " ) g    "
		    + " on g.c_port_code = a.c_port_code  "
		    + " LEFT JOIN (    "
		    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
		    + " FROM T_P_AB_PORT_RELA  "
		    + " WHERE c_rela_type='RELA_TD_SEAT' and n_check_state=1"
		    + " group by c_port_code "
		    + " ) h  "
		    + " on h.c_port_code = a.c_port_code ";
		logger.debug(sql);
	  return sql;
		
		
	}
	
	
	 /**
	 * Author : huangshui
	 * Date   : 2016-12-10
	 * Status : add
	 * STORY #34789 南方基金-产品树形结构按托管行自动分类(增加资产托管及托管资产两个默认项)
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZCTGMap() {
		HashMap<String, Port> dataMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_zclx = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql_zclx = "";
		String sql_build= "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		HashMap<String, String> zclxMap = new HashMap<String, String>();
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
			conn = this.loadNewConnection();
			String sqlst = "select distinct a.C_DAT_CODE ,b.C_DAT_NAME from t_p_ab_port  a join T_S_DAT_ASS_TYPE b on a.C_DAT_CODE=b.C_DAT_CODE";
			pstmt_zclx = conn.prepareStatement(sqlst);
			rs_zclx = pstmt_zclx.executeQuery();
			while(rs_zclx.next())
			{
				zclxMap.put(rs_zclx.getString("C_DAT_CODE"), rs_zclx.getString("C_DAT_NAME"));
				sql_build = portSqlBuilder.getPortTreeViewZCTGSqlBuild();
				sql_zclx =sql_zclx+sql_build;
			}
			
			String sqlstr = this.getsqlForZCTG(sql_zclx);
			if(!StringUtil.IsNullOrEmpty(sqlstr)){
				pstmt = conn.prepareStatement(sqlstr);
				int index = 1;
				 for (Map.Entry<String, String> entry : zclxMap.entrySet()) {
					   pstmt.setString(index++,entry.getKey().toString());
					   pstmt.setString(index++,entry.getKey().toString());
				}
				 rs = pstmt.executeQuery();
				 /*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
				  2016-8-19 蒋锦 南方基金现场性能优化 */
				PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
					if (t != null) {
						t.setdATA_TYPE(rs.getString("DATA_TYPE"));
						portCode = t.getC_PORT_CODE();
						//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求   借用C_DESC字段存储组合关联的股东代码和席位代码
						t.setC_DESC(rs.getString("C_DESC1"));
						if(rs.getString("DATA_TYPE").equals("ASS_TYPE"))
						{
							Port wtg = rsTools.ResultToBeanGeneric(rs, Port.class, props);
							wtg.setC_PORT_CODE_P(rs.getString("C_PORT_CODE"));
							wtg.setC_PORT_CODE("WTG"+rs.getString("C_PORT_CODE"));
							wtg.setC_PORT_NAME_ST("无托管行");
							dataMap.put(wtg.getC_PORT_CODE(), wtg);
						}
						if (portCode == null) {
							portCode = "";
						}
	
						if (!dataMap.containsKey(portCode)) {
							dataMap.put(portCode, t);
							//dataMap.put(portCode, t);
						}
					}
				} 
			}
			
			
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeResultSetFinal(rs_zclx);
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt_zclx);
			releaseConnection(conn);
		}

		return dataMap;
	}
	 /**
	 * Author : huangshui
	 * Date   : 2016-12-10
	 * Status : add
	 * STORY #34789 南方基金-产品树形结构按托管行自动分类(增加资产托管及托管资产两个默认项)
	 * @return
	 */
	public HashMap<String, Port> getPortDataForTGZCMap() {
		HashMap<String, Port> dataMap = null;
		HashMap<String, String> zclxMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_zclx = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql = "";
		String sql_zclx = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
            boolean wtg_boolean=true;
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getPortTreeViewTGZCSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			
			//BUG #240627 【代码检查】获取产品树形结构代码中游标未正确关闭
			zclxMap = new HashMap<String, String>();
			sql_zclx ="select distinct a.C_DAT_CODE ,b.C_DAT_NAME as C_PORT_NAME_ST from t_p_ab_port  a join T_S_DAT_ASS_TYPE b on a.C_DAT_CODE=b.C_DAT_CODE";
			pstmt_zclx = conn.prepareStatement(sql_zclx);
			logger.debug(sql_zclx);
			rs_zclx = pstmt_zclx.executeQuery();
			while (rs_zclx.next()) {
				zclxMap.put(rs_zclx.getString("C_DAT_CODE"), rs_zclx.getString("C_PORT_NAME_ST"));
			}
			
			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率
			  2016-8-19 蒋锦 南方基金现场性能优化 */
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
					t.setC_DESC(rs.getString("C_DESC1"));
					if (t != null) {
						t.setdATA_TYPE(rs.getString("DATA_TYPE"));
						portCode = t.getC_PORT_CODE();
						if(rs.getString("DATA_TYPE").equals("TG_TYPE"))
						{
							for(String dataCode : zclxMap.keySet()){
								Port port = rsTools.ResultToBeanGeneric(rs, Port.class, props);
								port.setC_PORT_CODE_P(rs.getString("C_PORT_CODE"));
								port.setC_PORT_CODE(rs.getString("C_PORT_CODE")+dataCode);
								port.setdATA_TYPE("ASS_TYPE");
								port.setC_PORT_NAME_ST(zclxMap.get(dataCode));
								dataMap.put(port.getC_PORT_CODE(), port);
							}
						}
						if(wtg_boolean)
						{
							Port wtg = rsTools.ResultToBeanGeneric(rs, Port.class, props);
							wtg.setC_PORT_CODE_P("[root]");
							wtg.setC_PORT_CODE("WTG");
							wtg.setC_PORT_NAME_ST("无托管行");
							dataMap.put("WTG", wtg);
							for(String dataCode : zclxMap.keySet()){	
								Port port = rsTools.ResultToBeanGeneric(rs, Port.class, props);
								port.setC_PORT_CODE_P("WTG");
								port.setC_PORT_CODE("WTG"+dataCode);
								port.setdATA_TYPE("ASS_TYPE");
								port.setC_PORT_NAME_ST(zclxMap.get(dataCode));
								dataMap.put(port.getC_PORT_CODE(), port);
							}
							wtg_boolean =false;
						}
						if (portCode == null) {
							portCode = "";
						}
	
						if (!dataMap.containsKey(portCode)) {
							dataMap.put(portCode, t);
						}
					}
			} 
				
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs_zclx);
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt, pstmt_zclx);
			releaseConnection(conn);
		}

		return dataMap;
	}

	/* END 数据服务方法 */

	/**
	 * 功能描述：获取权限管理列表 --------------------------------------------
	 * 
	 * @param paraMap
	 *            参数集合
	 * @return
	 */
	public List<Port> getPortDataSql(HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			//通过用户ID 以及用户角色从fast权限体系获取对应的组合
			convertNewFastRightParam(paraMap);
			List<String> paraNameList = this.getParaName(paraMap);
			sql = portSqlBuilder.getPortDataSql(paraNameList);
			
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			// setRightManageSetPortQuery(pstmt, paraMap);
			for (int i = 0; i < paraNameList.size(); i++) {
			    if("APPY_USER_REAL".equals(paraNameList.get(i))) {
			        pstmt.setArray(i + 1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraNameList.get(i)).toString(),conn));
			    } else {
			        pstmt.setString(i + 1, (String) paraMap.get(paraNameList.get(i)));
			    }
				logger.debug((String) paraMap.get(paraNameList.get(i)));
			}
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<Port> getPortTreeForRight(HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
            //通过用户ID 以及用户角色从fast权限体系获取对应的组合
            convertNewFastRightParam(paraMap);
			List<String> paraNameList = this.getParaName(paraMap);
			sql = portSqlBuilder.getPortTreeForRight(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			for (int i = 0; i < paraNameList.size(); i++) {
                if ("APPY_USER_REAL".equals(paraNameList.get(i))) {
                    pstmt.setArray(i + 1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraNameList.get(i)).toString(),conn));
                } else {
                    pstmt.setObject(i + 1, paraMap.get(paraNameList.get(i)));
                }
				logger.debug((String) paraMap.get(paraNameList.get(i)));
			}
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			// edit by 龚金枝 20170123 TASK #268054
			// 需求设计-申万宏源证券-性能优化对用户新增权限管理时出现卡顿的需求 提升查询性能
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<BasePojo> getPortTreeByUserAndPostAndProdState(
			HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			//通过用户ID 以及用户角色从fast权限体系获取对应的组合
            convertNewFastRightParam(paraMap);
			String prodState = (String) paraMap.get("C_DV_PROD_STATE");
			paraMap.remove("C_DV_PROD_STATE");
			List<String> paraNameList = this.getParaName(paraMap);
			sql = portSqlBuilder
					.getPortTreeByUserAndPostAndProdState(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(prodState, conn));
			int index = 2;
			for (String paraName : paraNameList) {
				if ("C_USER_CODE".equalsIgnoreCase(paraName)) {
                    pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraName).toString(),conn));
                } else if ("APPY_USER_REAL".equalsIgnoreCase(paraName)) {
                    pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraName).toString(),conn));
                } else {
                    pstmt.setString(index, (String) paraMap.get(paraName));
                }
				logger.debug((String) paraMap.get(paraName));
			}
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	// added by wangzhiye 2014-03-18 获取证券品种树
	public List<BasePojo> getStockTypeTree(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		// ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			List<String> paraNameList = this.getParaName(paraMap);
			sql = portSqlBuilder.getStockTypeTree(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			/*
			 * pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(prodState)); for (int
			 * i = 1; i < paraNameList.size(); i++) { pstmt.setString(i + 1,
			 * (String) paraMap .get(paraNameList.get(i)));
			 * logger.debug((String) paraMap.get(paraNameList.get(i))); }
			 */
			rs = pstmt.executeQuery();

			while (rs.next()) {
				StockTypeTree t = new StockTypeTree();
				t.setC_DA_CODE(rs.getString("C_DA_CODE"));
				t.setC_DA_NAME(rs.getString("C_SEC_VAR_NAME"));
				t.setC_DA_CODE_P(rs.getString("C_DA_CODE_P"));
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

	/***
	 * 根据组合代码获取组合树
	 * 
	 * @param portCodes
	 * @return
	 */
	public List<BasePojo> getPortTreeByCodes(String[] portCodes) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = portSqlBuilder.getPortTreeByCodesSql();

			//为组合临时表添加数据 BUG #281983 基准测试 发现慢SQL update wwt
			insertTempPortCodeTable(conn, portCodes);
			pstmt = conn.prepareStatement(sql);
			//pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			//pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				pojoList.add(t);
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

		return pojoList;
	}

	public List<BasePojo> getPortTreeByUserAndPost(
			HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			convertNewFastRightParam(paraMap);
			List<String> paraNameList = this.getParaName(paraMap);
			sql = portSqlBuilder.getPortTreeByUserAndPost(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			for (int i = 0; i < paraNameList.size(); i++) {
				String paraName = paraNameList.get(i);
				if ("C_POST_CODE".equalsIgnoreCase(paraName)) {
					pstmt.setArray(
							i + 1,
							OraDbTool.newInstance().sqlOverLongCondition((String) paraMap.get(paraName), conn));
				} else if ("C_USER_CODE".equalsIgnoreCase(paraName)) {
				    pstmt.setArray(i + 1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraName).toString(),conn));
				}
			}
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<Port> getPortList(HashMap<String, Object> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
            //通过用户ID 以及用户角色从fast权限体系获取对应的组合
            convertNewFastRightParam(paraMap);
			List<String> paraNameList = getParaName(paraMap);
			sql = portSqlBuilder.getQueryConditionSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			for (int i = 0; i < paraNameList.size(); i++) {
			    if ("APPY_USER_REAL".equals(paraNameList.get(i))) {
                    pstmt.setArray(i + 1, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(paraNameList.get(i)).toString(),conn));
			    } else {
			        pstmt.setObject(i + 1, paraMap.get(paraNameList.get(i)));
			    }
			}
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());

			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-23蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());

			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public Port getPortInfo(String cPortCode) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Port t = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			List<String> paraNameList = new ArrayList<String>();
			paraNameList.add("C_PORT_CODE_EQUAL");
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getQueryConditionSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setObject(1, cPortCode);
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			if (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public Port getPortInfoByPortName(String portName) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Port t = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			List<String> paraNameList = new ArrayList<String>();
			paraNameList.add("C_PORT_NAME_EQUAL");
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getQueryConditionSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setObject(1, portName);
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			if (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据portSqlBuilder类中拼接的sql语句 ，以及参数，进行参数绑定，然后查询数据 查询下面存在单元层组合的组合层组合、计划层组合信息
	 * 
	 * @author tangshifeng
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> queryTreeViewData(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getTreeViewQuerySql(paraNameList);

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
				if("APPY_USER_REAL".equals(valueFieldName)) {
                    pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get(valueFieldName).toString(),conn));
                } else if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)), conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			rs = pstmt.executeQuery();
			//BUG #312701 [总分对账处理]A区展开总分账卡顿 将反射获取字段提到循环外面，提高效率 by lwz
			PropertyDescriptor[] propertys = PojoUtils.getPropertyDescriptors(new Port());
			while (rs.next()) {

				Port t = new Port();

//				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("endUseDate".equals(prop.getName())) {
						continue;
					}

					if ("startUseDate".equals(prop.getName())) {
						continue;
					}

					Object resValue = new Object();
					String name = prop.getName();
					String columnName = portSqlBuilder.getColumnNameByProperty(
							dbNameResolver, name);
					if ("".equals(columnName)) {
						continue;
					}

					else if ("C_IDEN".equals(columnName)) {
						continue;
					} else {
						resValue = rs.getObject(columnName);
					}

					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(t, resValue);

						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);

						}

					}

				}

				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据portSqlBuilder类中拼接的sql语句 ，以及参数，进行参数绑定，然后查询数据
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> queryUnitPortData(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getUnitPortQuerySql(paraNameList);

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

				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)), conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {

				/*Port t = new Port();

				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("endUseDate".equals(prop.getName())) {
						continue;
					}

					if ("startUseDate".equals(prop.getName())) {
						continue;
					}

					Object resValue = new Object();
					String name = prop.getName();
					String columnName = portSqlBuilder.getColumnNameByProperty(
							dbNameResolver, name);
					if ("".equals(columnName)) {
						continue;
					}

					else if ("C_IDEN".equals(columnName)) {
						continue;
					} else if ("C_DAT_CODE".equals(columnName)) {
						continue;
					} else {
						resValue = rs.getObject(columnName);
					}

					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(t, resValue);

						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);

						}

					}

				}*/
				BasePojo t = rsTools.ResultToBean(rs, Port.class, props);
				((Port)t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 业务系统需要根据启用日期查询组合
	 * 
	 * @param portCode
	 * @param buildDate
	 * @return
	 */
	public List<Port> getListByCodeAndBuildDate(String portCode, Date buildDate) {
		String sql = "";
		List<Port> tradePojos = new ArrayList<Port>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			// 将拼接sql转移到sqlbuilder中，dao的子类中不允许出现拼接sql的代码
			// Modified By Huxingtao 2013-6-2
			sql = new PortSqlBuilder().getListByCodeAndBuildDateSql();
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			pst.setDate(2, new java.sql.Date((buildDate.getTime())));
			rs = pst.executeQuery();
			while (rs.next()) {
				Port t = new Port();
				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("endUseDate".equals(prop.getName())) {
						continue;
					}

					if ("startUseDate".equals(prop.getName())) {
						continue;
					}

					Object resValue = new Object();
					String name = prop.getName();
					String columnName = portSqlBuilder.getColumnNameByProperty(
							dbNameResolver, name);
					if ("".equals(columnName)) {
						continue;
					}

					else if ("C_IDEN".equals(columnName)) {
						continue;
					} else if ("C_DAT_CODE".equals(columnName)) {
						continue;
					} else {
						resValue = rs.getObject(columnName);
					}

					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(t, resValue);

						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);

						}

					}

				}

				tradePojos.add(t);
			}
		} catch (Exception e) {
			throw new DataAccessException(e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return tradePojos;
	}

	/**
	 * 到期确认
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 * @throws YssException 
	 */
	public String operDQQR(List<Port> lstPort) throws ServiceException, YssException {
		//this.updateById(lstPort);
		SimpleDateFormat format= new SimpleDateFormat(YssCons.YSS_DATETIMEFORMAT);
		for(Port port:lstPort){
			String dClose=port.getD_CLOSE();
			// update zhangyongzhao 20170711  需求318197 产品的清算时间与当前时间比较，从而判断产品是否到期，再设置产品状态
			if(YssFun.toDate(dClose).after(new Date())){
				port.setC_DV_PROD_STATE("PS4");
			}
			 port.setAuditDate(format.format(new Date()));
			this.updateById(port);
		}
		return MvcConstant._Success;
	}

	/**
	 * 到期撤消
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operDQQX(List<Port> lstPort) throws ServiceException {
		SimpleDateFormat format= new SimpleDateFormat(YssCons.YSS_DATETIMEFORMAT);
		for (Port port : lstPort) {
			 port.setAuditDate(format.format(new Date()));
		}
		this.updateById(lstPort);
		return MvcConstant._Success;
	}

	/**
	 * 清算确认
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSQR(List<Port> lstPort) throws ServiceException {
		//this.updateById(lstPort);
		SimpleDateFormat format= new SimpleDateFormat(YssCons.YSS_DATETIMEFORMAT);
		for(Port port:lstPort){
			Date dClear=port.getD_CLEAR();
			 port.setAuditDate(format.format(new Date()));
			// update zhangyongzhao 20170711  需求318197 产品的清算时间与当前时间比较，从而判断产品是否清算，再设置产品状态
			if(dClear.after(new Date())){
				port.setC_DV_PROD_STATE("PS5");
			}
			this.updateById(port);
		}
		return MvcConstant._Success;
	}

	/**
	 * 清算撤消
	 * 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSQX(List<Port> lstPort) throws ServiceException {
		SimpleDateFormat format= new SimpleDateFormat(YssCons.YSS_DATETIMEFORMAT);
		for (Port p : lstPort) {
			java.util.Date date = DateUtil.stringtoDate(p.getD_CLOSE(),
					DateUtil.LONG_DATE_FORMAT);
			p.setD_CLEAR(date);
			 p.setAuditDate(format.format(new Date()));
		}
		this.updateById(lstPort);
		return MvcConstant._Success;
	}

	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList) {
		Connection conn = null;
		StringBuffer buf = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			/* 添加自定义日志功能 记录执行时间 */
			buf.append("DaoName:").append(this.getClass().getName())
					.append("  ");
			buf.append("methodName:").append("deleteById executeUpdate")
					.append("  ");
			buf.append("dataCount:").append(pojoList.size());
			WriteLog.newInstance().startLog();

			/* 用户关联表SQL */
//			RightManageSqlBuilder riSqlBuilder = new RightManageSqlBuilder();
//			String delUserRelaSql = riSqlBuilder
//					.getDeleRelaByPortCodeSQL(dbNameResolver);
			
			// modified by HeLiang 2017-06-11 产品信息组件拆分
			// 构造用户关联表SQL需要引用com.yss.platform的RightManageSqlBuilder
			// 但由于拆分不能进行引用，所以重写了获取SQL语句的方法，保证语句不变
//			String delUserRelaSql = this.getDeleRelaByPortCodeSQL();

			/* 权限委托表SQL */
//			AuthBailSqlBuilder auSqlBuilder = new AuthBailSqlBuilder();
//			String delBallSql = auSqlBuilder
//					.getDelBallByPortCodeSQL(dbNameResolver);
			
			// modified by HeLiang 2017-06-11 产品信息组件拆分
			// 构造权限委托表SQL需要引用com.yss.platform的AuthBailSqlBuilder
			// 但由于拆分不能进行引用，所以重写了获取SQL语句的方法，保证语句不变
			// String delBallSql = this.getDelBallByPortCodeSQL();
			//STORY #69753 业务基础组件对FAST平台表结构的解耦  改为调用FAST服务
			IAuthBailService authBailService = YssServiceFactory.getInstance().createService(IAuthBailService.class);

			/* 组合基本信息SQL */
			String delPortSQL = ((PortSqlBuilder) sqlbuilder)
					.getDeletePortByIdSQL(dbNameResolver);
			List<String> portCodes  = new ArrayList<String> ();
			for (int i = 0; i < pojoList.size(); i++) {
				Port port = (Port) pojoList.get(i);
				/* 组合基本信息表数据 */
				deleteDatasByUserCode(port.getId(), conn, delPortSQL);

				/* 删除用户关联表数据 */
//				deleteDatasByUserCode(port.getC_PORT_CODE(), conn,
//						delUserRelaSql);

				/* 删除权限委托表数据 */
				//deleteDatasByUserCode(port.getC_PORT_CODE(), conn, delBallSql);
				portCodes.add(port.getC_PORT_CODE());
			}
			
			authBailService.deleteBailByPortCodes(portCodes);
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			releaseConnection(conn);
			WriteLog.newInstance().write(buf);
		}
	}

	public void deleteDatasByUserCode(String value, Connection conn, String sql) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, value);
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
	}

	public String getPortSubCount(String portCode) {
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		PortSqlBuilder sqlBuilder = null;
		ResultSet rs = null;
		try {
			sqlBuilder = new PortSqlBuilder();
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlBuilder.getPortSubCount(portCode);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				count = rs.getInt("NUM");
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return String.valueOf(count);
	}

	public List<BasePojo> getPortTreeWithNode(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		PortSqlBuilder sqlBuilder = null;
		try {
			sqlBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlBuilder.getPortTreeWithNode(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			PortTreeView t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(PortTreeView.class.newInstance());
			while (rs.next()) {
				t = (PortTreeView) rsTools.ResultToBean(rs, PortTreeView.class, props);
				t.setNodeCode(rs.getString("NODECODE"));
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp(dbNameResolver);

			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, timestamp);

			rs = pstmt.executeQuery();

			/*
			 * 将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率 2016-8-23蒋锦 南方基金现场性能优化
			 */
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据资产代码获取组合信息
	 * 
	 * @param assCode
	 *            资产代码
	 * @return
	 * @throws ServiceException
	 */
	public Port getPortByAssCode(String assCode) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getPortByAssCodeSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, assCode);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			if (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据资产代码获取组合信息 Fixed by huangsq 20160811 STORY #26296 [招商证券]估值表发布及获取接口需求
	 * 
	 * @param assCode
	 *            资产代码
	 * @return
	 * @throws ServiceException
	 */
	public List<Port> getPortByAssCodes(List<String> assCodes)
			throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		PortSqlBuilder dsServiceBuilder = null;
		List<Port> portList = new ArrayList<Port>();
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getPortByAssCodesSql();
			// com.yss.framework.db.OraDbTool dbTool =
			// com.yss.framework.db.OraDbTool
			// .newInstance();
			// dbTool.set(YssConstant.DBSERVICE_NAME);
			java.sql.Array array = OraDbTool.newInstance().sqlOverLongCondition(
					StringUtil.join(assCodes, ","), conn);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, array);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port port = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				port.setdATA_TYPE(rs.getString("DATA_TYPE"));
				portList.add(port);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return portList;
	}

	/**
	 * 查询A区群组数据，以树形展示 STORY #16818 产品群组需求 add by chenwenhai 20140603
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Port> getGroupDataTree(String trCode) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getGroupDataTree();
			
			IFASTDataAuthorityService fda = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
            List<String> portList = fda.queryByUser(RightConstants.portType, ContextFactory.getContext().getLogInfo().getLoggingUserCode());
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(StringUtil.join(portList, ","),conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	public List<Port> getGroupDataTree(String trCode, List<String> portList) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			
			if(getPortClsExist() == null)
			{
				dsServiceBuilder = new PortSqlBuilder();
				rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
				conn = this.loadNewConnection();
				sql = dsServiceBuilder.getGroupDataTree();
				pstmt = conn.prepareStatement(sql);
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(StringUtil.join(portList, ","),conn));
				logger.debug(sql);
			}else
			{
				dsServiceBuilder = new PortSqlBuilder();
				rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
				sql = dsServiceBuilder.getGroupDataTreeTAQS();
				conn = this.loadNewConnection();
				pstmt = conn.prepareStatement(sql);
			}
			
			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 查询A区群组数据，以树形展示，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * @return
	 * @throws Exception
	 */
	public List<Port> getGroupDataTreeWithoutRight(String trCode) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getGroupDataTreeWithoutRight();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 查询A区资产类型数据，以树形展示
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public List<Port> getAssGroupDataTree(String trCode) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder dsServiceBuilder = null;
		Port t = null;
		try {
			dsServiceBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getAssGroupDataTree(trCode);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1,ContextFactory.getContext().getLogInfo().getLoggingUserCode());
			if(!StringUtil.IsNullOrEmpty(trCode)){
				pstmt.setString(2,trCode);
			}
			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	public String checkPortCode(String portCode) {
		String returnInfo = "false";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		PortSqlBuilder dsServiceBuilder = null;
		try {
			conn = this.loadNewConnection();
			dsServiceBuilder = new PortSqlBuilder();
			sql = dsServiceBuilder.checkPortCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				returnInfo = "true";
				break;
			}
		} catch (SQLException e) {
			// e.printStackTrace();
			logger.log("查询失败", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return returnInfo;
	}

	/**
	 * 询下面存在子组合的组合信息 By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
	 * 
	 * @param userCode
	 * @param postCode
	 * @return
	 */
	public List<BasePojo> queryParentPortTreeViewData(String userCode,
			String postCode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getParentPortTreeViewSql();

            IFASTDataAuthorityService fda = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
            List<String> listPorts = fda.queryByUser(RightConstants.portType, userCode, postCode);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(listPorts.toArray(new String[listPorts.size()]),conn));
			//pstmt.setString(2, userCode);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				Port t = new Port();

				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("endUseDate".equals(prop.getName())) {
						continue;
					}

					if ("startUseDate".equals(prop.getName())) {
						continue;
					}

					Object resValue = new Object();
					String name = prop.getName();
					String columnName = portSqlBuilder.getColumnNameByProperty(
							dbNameResolver, name);
					if ("".equals(columnName)) {
						continue;
					}

					else if ("C_IDEN".equals(columnName)) {
						continue;
					} else {
						resValue = rs.getObject(columnName);
					}

					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(t, resValue);

						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);

						}

					}

				}

				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public List<BasePojo> queryProductORPort() {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					new PortSqlBuilder());

			conn = this.loadNewConnection();
			sql = portSqlBuilder.getProductORPortSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
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

	public List<BasePojo> getTreePortDataByCodes(String[] keys) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					new PortSqlBuilder());

			conn = this.loadNewConnection();
			sql = portSqlBuilder.getTreePortDataByCodes();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys, conn));
			rs = pstmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
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
	 * 获取所有群组和组合 add by liuxiang 2015/3/11
	 * 
	 * @param rightsList
	 *            权限列表
	 * @return
	 */
	public List<BasePojo> getAllGroupAndPort(List<String> rightsList) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAllGroupAndPortSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(rightsList
					.toArray(new String[rightsList.size()]), conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, Port.class, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 获取所有群组和组合(无权限控制)
	 * 
	 * @param rightsList
	 *            权限列表
	 * @return
	 */
	public List<BasePojo> getAllGroupAndPortNoRight() {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAllGroupAndPortNoRightSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, Port.class, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	
	/* 查询资产类型和资产组合数据
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * neil 2019-07-26
	 * @return
	 */
	public List<BasePojo> getAllAssGroupAndPort(List<String> rightsList) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAllAssGroupAndPortSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(rightsList
					.toArray(new String[rightsList.size()])));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			BasePojo t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, Port.class, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 获取所有群组和组合 add by lds 2018/2/27
	 * 
	 * @param rightsList
	 *            权限列表
	 * @return
	 */
	public Map<String,List<Port>> getAllPortByGroups(List<String> groupCodes) {
		Map<String,List<Port>> map = new HashMap<String,List<Port>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAllPortByGroupsSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(groupCodes
					.toArray(new String[groupCodes.size()]),conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			Port t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				String groupCode = rs.getString("C_GROUP_CODE");
				List<Port> pojoList = null;
				if(null != map.get(groupCode)){
					pojoList = map.get(groupCode);
				}else{
					pojoList = new ArrayList<Port>();
					map.put(groupCode, pojoList);
				}
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				pojoList.add(t);
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

	/**
	 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	 * 获取所有资产类型和组合	 
	 * neil
	 * 2019-07-26 
	 * @param rightsList
	 *            权限列表
	 * @return
	 */
	public Map<String,List<Port>> getAllAssPortByGroups(List<String> groupCodes) {
		Map<String,List<Port>> map = new HashMap<String,List<Port>>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAllAssPortByGroupsSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(groupCodes
					.toArray(new String[groupCodes.size()]),conn));
			logger.debug(sql);
			rs = pstmt.executeQuery();
			Port t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				String groupCode = rs.getString("C_GROUP_CODE");
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				if(null != map.get(groupCode)){
					 map.get(groupCode).add(t);
				}else{
					List<Port>	pojoList = new ArrayList<Port>();
					pojoList.add(t);
					map.put(groupCode, pojoList);
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

	public List<Port> duePorts(HashMap<String, String> paraMap) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.duePortSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("C_DV_PROD_STATE"));
			pstmt.setString(2, paraMap.get("D_CLOSE"));
			pstmt.setString(3, paraMap.get("C_PORT_CODE_P"));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setC_DESC(paraMap.get("C_DV_PROD_STATE"));
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
	 * 获取和目标组合资产代码重复的组合列表
	 * 
	 * @param portCode
	 *            组合代码
	 * @param assCode
	 *            资产代码
	 * @return
	 * @author liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
	 */
	public List<Port> getTheSameAssCodeList(String portCode, String assCode) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getTheSameAssCodeListSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, assCode);
			pstmt.setString(2, portCode);
			rs = pstmt.executeQuery();
			Port t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
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
	 * Author : ChenLong Date : 2016-07-26 Status : Add Comment: 产品资产类别关系
	 * 
	 * @param portCodes
	 * @return
	 */
	public Map<String, String> getPortDatClsMap(String[] portCodes) {
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String sql = "select c_port_code,c_dat_cls from t_p_ab_port where c_port_code in (select * from table(?))";
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(
					1,
					OraDbTool.newInstance().sqlOverLongCondition(portCodes,
							conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("c_port_code"), rs.getString("c_dat_cls"));
			}
		} catch (Exception ex) {
			throw new BusinessException("查询产品资产类别关系失败!" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	public List<BasePojo> getDefaultPort(String cTRCode, String[] portCodes)
			throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultAssPortByCodes();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			pstmt.setArray(
					3,
					OraDbTool.newInstance().sqlOverLongCondition(portCodes,
							conn));
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据产品编号和状态查询
	 * 
	 * @Title queryPort
	 * @Description 根据产品编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午7:16:01
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	public List<Port> queryPort(List<String> portCodes,
			List<String> productState) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.queryByPortCodeAndStateSql();

			pstmt = conn.prepareStatement(sql);
			String[] portCodesArr = new String[portCodes.size()];
			String[] productStateArr = new String[productState.size()];
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(portCodes.toArray(portCodesArr), conn));
			pstmt.setArray(
					2,
					OraDbTool.newInstance().sqlOverLongCondition(productState.toArray(productStateArr),
							conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				Port port = new Port();
				port.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				port.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
				port.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				pojoList.add(port);
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
	 * 根据产品编号和状态查询
	 * 
	 * @Title queryPort
	 * @Description 根据产品编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午7:16:01
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	public HashMap<String, String> queryPortMap(List<String> portCodes,
			List<String> productState) {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.queryByPortCodeAndStateSql();

			pstmt = conn.prepareStatement(sql);
			String[] portCodesArr = new String[portCodes.size()];
			String[] productStateArr = new String[productState.size()];
			pstmt.setArray(1,
					OraDbTool.newInstance().sqlOverLongCondition(portCodes.toArray(portCodesArr), conn));
			pstmt.setArray(
					2,
					OraDbTool.newInstance().sqlOverLongCondition(productState.toArray(productStateArr),
							conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_PORT_CODE"),
						rs.getString("C_PORT_CODE"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return map;

	}
	
	/**
	 * ==============
	 * 分布式改造，删除该部分逻辑，旧权限体系作废
	 * ==============
	 * 构造用户关联表删除SQL
	 * 这里表名暂时写死，若枚举类RightManageTableName迁移出com.yss.platform
	 * 再使用dbNameResolver.getTableName的方式
	 * 
	 * @Title getDeleRelaByPortCodeSQL
	 * @Description 构造用户关联表删除SQL
	 * @author HeLiang@ysstech.com
	 * @date 2017年6月11日下午4:43:01
	 * @return
	 * @return String
	 */
//	private String getDeleRelaByPortCodeSQL(){
//		StringBuffer sqlBuff = new StringBuffer();
//		sqlBuff.append(" delete from ");
//		sqlBuff.append(" T_S_USER_RELA ");
//		sqlBuff.append(" where c_port_code = ? ");
//		return sqlBuff.toString();
//	}
	
	
	
	/**
	 * 构造权限委托表删除SQL
	 * 这里表名暂时写死，若枚举类AuthBailTableName迁移出com.yss.platform
	 * 再使用dbNameResolver.getTableName的方式
	 * 
	 * @Title getDelBallByPortCodeSQL
	 * @Description 构造权限委托表删除SQL
	 * @author HeLiang@ysstech.com
	 * @date 2017年6月11日下午4:43:01
	 * @return
	 * @return String
	 */
	//STORY #69753 业务基础组件对FAST平台表结构的解耦  改为调用FAST服务
//	private String getDelBallByPortCodeSQL() {
//		StringBuffer sqlBuff = new StringBuffer();
//		sqlBuff.append(" delete from ");
//		sqlBuff.append(" T_S_BAIL ");
//		sqlBuff.append(" where c_port_code = ? ");
//		return sqlBuff.toString();
//	}
	
	/**
	 * //// By Jinghehe 2017-8-28 BUG #163743 【平安大华】产品树形结构界面点击新增与复制后，界面加载时间过长
	 * start with.....connect by 性能很慢，这边处理成，获取组合数据时候，在根据父节点代码查询父节点
	 * @param parentList
	 * @return
	 */
	private  List<BasePojo> getParentList(
			List<String> parentList) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		Port t = null;
		PortAssTreeSqlBuilder portAssTreeSqlBuilder = null;
		ResultSetTools rsTools = null;

		try {
			portAssTreeSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portAssTreeSqlBuilder);

			conn = this.loadNewConnection();

			sql = portSqlBuilder.getPortParentPojoSql();

			pstmt = conn.prepareStatement(sql);

			logger.debug(sql);
			StringBuffer buffer = new StringBuffer();
			for (String portP : parentList) {
				buffer.append(portP+",");
			}
			if(buffer.length() >0)
			{
				StringUtil.delLastSplitMark(buffer, " , ");
			}
			
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String
						.valueOf(buffer.toString()), conn));
			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Port());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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

	public ArrayList<String> getAssetType() throws ServiceException {
 		String strSql = null;// 定义查询语句字符串
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		//java.sql.Statement pst = null; //addbyleeyu20151015
		PreparedStatement pst = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			strSql = portSqlBuilder.getAssSql();
			//rs = openResultSet(strSql, conn);//byleeyu20151015
			//pst = conn.createStatement(); //byleeyu20151015
			//BUG #162973 by liulei
			pst = conn.prepareStatement(strSql);
			//pst.setString(1, AppContext.getInstance().getUserCode());
			rs = pst.executeQuery();//byleeyu20151015
			list.add("ASS\t资产类型");
//			list.add("ZCTG\t资产类型-托管行");
//			list.add("TGZC\t托管行-资产类型");
			while (rs.next()) {
				list.add(rs.getString("C_TR_CODE") + "\t"
						+ rs.getString("C_TR_NAME"));
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			closeResultSetFinal(rs);
			this.closeStatementFinal(pst);//byleeyu20151015
			releaseConnection(conn);
		}
		return list;
	}
	
	public List<BasePojo> getDefaultUserPortNoRight(String cTRCode)
			throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultUserAssPortNoRight();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			pstmt.setString(3, cTRCode);
			pstmt.setString(4, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			// 南方基金性能优化 zhanghualin 2017-3-21
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new Port());
			while (rs.next()) {
				//Port t = (Port) rsTools.ResultToBean(rs, Port.class);
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 产品树形结构按管理人自动分类 add by chenyoucai 20180120 STORY #51993 财务报表优化-区分不同管理人出具纳税报表
	 * @return
	 */
	public HashMap<String, Port> getPortDataForGLZCMap() {
		HashMap<String, Port> dataMap = null;
		HashMap<String, String> zclxMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_zclx = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql = "";
		String sql_zclx = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
            boolean wglr_boolean=true;
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getPortTreeViewGLZCSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			
			//BUG #240627 【代码检查】获取产品树形结构代码中游标未正确关闭
			zclxMap = new HashMap<String, String>();
			sql_zclx ="select distinct a.C_DAT_CODE ,b.C_DAT_NAME as C_PORT_NAME_ST from t_p_ab_port  a join T_S_DAT_ASS_TYPE b on a.C_DAT_CODE=b.C_DAT_CODE";
			pstmt_zclx = conn.prepareStatement(sql_zclx);
			logger.debug(sql_zclx);
			rs_zclx = pstmt_zclx.executeQuery();
			while (rs_zclx.next()) {
				zclxMap.put(rs_zclx.getString("C_DAT_CODE"), rs_zclx.getString("C_PORT_NAME_ST"));
			}
			
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
					if (t != null) {
						t.setdATA_TYPE(rs.getString("DATA_TYPE"));
						portCode = t.getC_PORT_CODE();
						t.setC_DESC(rs.getString("C_DESC1"));
						if(rs.getString("DATA_TYPE").equals("GLR_TYPE"))
						{
							for(String dataCode : zclxMap.keySet()){
								Port port = rsTools.ResultToBeanGeneric(rs, Port.class, props);
								port.setC_PORT_CODE_P(rs.getString("C_PORT_CODE"));
								port.setC_PORT_CODE(rs.getString("C_PORT_CODE")+dataCode);
								port.setdATA_TYPE("ASS_TYPE");
								port.setC_PORT_NAME_ST(zclxMap.get(dataCode));
								dataMap.put(port.getC_PORT_CODE(), port);
							}
						}
						if(wglr_boolean)
						{
							Port wtg = rsTools.ResultToBeanGeneric(rs, Port.class, props);
							wtg.setC_PORT_CODE_P("[root]");
							wtg.setC_PORT_CODE("WGLR");
							wtg.setC_PORT_NAME_ST("无管理人");
							dataMap.put("WGLR", wtg);
							for(String dataCode : zclxMap.keySet()){
								Port port = rsTools.ResultToBeanGeneric(rs, Port.class, props);
								port.setC_PORT_CODE_P("WGLR");
								port.setC_PORT_CODE("WGLR"+dataCode);
								port.setdATA_TYPE("ASS_TYPE");
								port.setC_PORT_NAME_ST(zclxMap.get(dataCode));
								dataMap.put(port.getC_PORT_CODE(), port);
							}
							wglr_boolean =false;
						}
						if (portCode == null) {
							portCode = "";
						}
	
						if (!dataMap.containsKey(portCode)) {
							dataMap.put(portCode, t);
						}
					}
			} 
				
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs_zclx);
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt, pstmt_zclx);
			releaseConnection(conn);
		}

		return dataMap;
	}

	/**
	 * Fortify 规范代码改造
	 * @param sql_zclx
	 * @return
	 */
	private String getsqlForZCGL(String sql_zclx){
		//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求        获取与组合关联的股东和席位，按|拼接，展示在前台股东和席位列
	String sql = "select a.*,NVL(g.c_rela_code,'') || ',' || NVL(h.c_rela_code,'') as C_DESC1 from (" 
			+ portSqlBuilder.getPortTreeViewZCGLSql(dbNameResolver,sql_zclx) 
			+ ") a"
			+ " LEFT JOIN (    "
		    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
		    + " FROM T_P_AB_PORT_RELA  "
		    + " WHERE c_rela_type='RELA_SH_ACC' and n_check_state=1"
		    + " group by c_port_code "
		    + " ) g    "
		    + " on g.c_port_code = a.c_port_code  "
		    + " LEFT JOIN (    "
		    + " select c_port_code,TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY(CAST(COLLECT(DISTINCT c_rela_code) AS VARTABLETYPE), '|')) as c_rela_code"
		    + " FROM T_P_AB_PORT_RELA  "
		    + " WHERE c_rela_type='RELA_TD_SEAT' and n_check_state=1"
		    + " group by c_port_code "
		    + " ) h  "
		    + " on h.c_port_code = a.c_port_code ";
		logger.debug(sql);
		return sql;
	}
	
	/**
	 * xyh<br>
	 * 2018-1-22<br>
	 * 资产类型-管理人 <br>
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZCGLMap() {
		HashMap<String, Port> dataMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_zclx = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql = "";
		String sql_zclx = "";
		String sql_build= "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		HashMap<String, String> zclxMap = new HashMap<String, String>();
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = this.loadNewConnection();
			sql ="select distinct a.C_DAT_CODE ,b.C_DAT_NAME from t_p_ab_port  a join T_S_DAT_ASS_TYPE b on a.C_DAT_CODE=b.C_DAT_CODE";
			pstmt_zclx = conn.prepareStatement(sql);
			rs_zclx = pstmt_zclx.executeQuery();
			while(rs_zclx.next())
			{
				zclxMap.put(rs_zclx.getString("C_DAT_CODE"), rs_zclx.getString("C_DAT_NAME"));
				sql_build = portSqlBuilder.getPortTreeViewZCGLSqlBuild();
				sql_zclx =sql_zclx+sql_build;
			}
			String sqlstr = this.getsqlForZCGL(sql_zclx);
			if(!StringUtil.IsNullOrEmpty(sqlstr)){
				pstmt = conn.prepareStatement(sqlstr);
				
				int index = 1;
				 for (Map.Entry<String, String> entry : zclxMap.entrySet()) {
					   pstmt.setString(index++,entry.getKey().toString());
					   pstmt.setString(index++,entry.getKey().toString());
				}
				 rs = pstmt.executeQuery();
				/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率*/
				PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
					if (t != null) {
						t.setdATA_TYPE(rs.getString("DATA_TYPE"));
						portCode = t.getC_PORT_CODE();
						//STORY60459【招商基金公募升级】【紧急】席位佣金设置需求   借用C_DESC字段存储组合关联的股东代码和席位代码
						t.setC_DESC(rs.getString("C_DESC1"));
						if(rs.getString("DATA_TYPE").equals("ASS_TYPE"))
						{
							Port wgl = rsTools.ResultToBeanGeneric(rs, Port.class, props);
							wgl.setC_PORT_CODE_P(rs.getString("C_PORT_CODE"));
							wgl.setC_PORT_CODE("WGL"+rs.getString("C_PORT_CODE"));
							wgl.setC_PORT_NAME_ST("无管理人");
							dataMap.put(wgl.getC_PORT_CODE(), wgl);
						}
						if (portCode == null) {
							portCode = "";
						}
	
						if (!dataMap.containsKey(portCode)) {
							dataMap.put(portCode, t);
							//dataMap.put(portCode, t);
						}
					}
				} 
			}
		
		
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeResultSetFinal(rs_zclx);
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt_zclx);
			releaseConnection(conn);
		}

		return dataMap;
		
	}

	/**
	 * //add by xyh 20180122  纳税人类型-“结转频率”-管理人名称-资产类型-组合代码
	 * @return
	 */
	public HashMap<String, Port> getPortDataForNSPLMap() {
	HashMap<String, Port> dataMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql = "";
		try {
	        Map<String, Vocabulary> payerTypes = getVocCacheByType("VAT_PAYER");//增值税纳税人类型
            Map<String, Vocabulary> periodTypes = getVocCacheByType("CF_PERIOD");//结转频率
            String key = "";
            String noneVatPayer = "无纳税类型";
            String noneJZPL = "无结转频率";
		    
			dataMap = new HashMap<String, Port>();
			conn = this.loadNewConnection();
			/**
			 * 查询出所有的与组合相关的信息
			 */
			sql = portSqlBuilder.getPortALLINFOSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(periodTypes.keySet().toArray(new String[periodTypes.keySet().size()]), conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				//纳税人类型
				Port p1 = createPort();
				p1.setC_PORT_CODE_P("[root]");
				p1.setC_PORT_CODE("[root]"+rs.getString("C_DV_TAXPAYER_TYPE"));
				key = rs.getString("C_DV_TAXPAYER_TYPE"); //纳税人类型
				if (payerTypes.containsKey(key)) {
                    p1.setC_PORT_NAME_ST(payerTypes.get(key).getC_DV_NAME());
                    p1.setC_PORT_NAME_EN(payerTypes.get(key).getC_DV_NAME());
                    p1.setC_PORT_NAME(payerTypes.get(key).getC_DV_NAME());
				} else {
                    p1.setC_PORT_NAME_ST(noneVatPayer);
                    p1.setC_PORT_NAME_EN(noneVatPayer);
                    p1.setC_PORT_NAME(noneVatPayer);
				}
//					p1.setC_PORT_NAME_ST(rs.getString("C_DV_TAXPAYER_TYPE_NAME"));
//					p1.setC_PORT_NAME_EN(rs.getString("C_DV_TAXPAYER_TYPE_NAME"));
//					p1.setC_PORT_NAME(rs.getString("C_DV_TAXPAYER_TYPE_NAME"));
				p1.setdATA_TYPE("ASS_TYPE");
				
				if(!dataMap.containsKey(p1.getC_PORT_CODE())){
					dataMap.put(p1.getC_PORT_CODE(), p1);
				}
				//结转频率
				Port p2 = createPort();
				p2.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_TAXPAYER_TYPE"));
				p2.setC_PORT_CODE("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ"));
				key = rs.getString("C_JZ_FREQ"); //结转频率
                if (periodTypes.containsKey(key)) {
                    p2.setC_PORT_NAME_ST(periodTypes.get(key).getC_DV_NAME());
                    p2.setC_PORT_NAME_EN(periodTypes.get(key).getC_DV_NAME());
                    p2.setC_PORT_NAME(periodTypes.get(key).getC_DV_NAME());
                } else {
                    p2.setC_PORT_NAME_ST(noneJZPL);
                    p2.setC_PORT_NAME_EN(noneJZPL);
                    p2.setC_PORT_NAME(noneJZPL);
                }
				
//					p2.setC_PORT_NAME_ST(rs.getString("C_JZ_FREQ_NAME"));
//					p2.setC_PORT_NAME_EN(rs.getString("C_JZ_FREQ_NAME"));
//					p2.setC_PORT_NAME(rs.getString("C_JZ_FREQ_NAME"));
				p2.setdATA_TYPE("ASS_TYPE");
				
				if(!dataMap.containsKey(p2.getC_PORT_CODE())){
					dataMap.put(p2.getC_PORT_CODE(), p2);
				}
				//管理人
				Port p3 = createPort();
				p3.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ"));
				p3.setC_PORT_CODE("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ")+rs.getString("C_ORG_CODE"));
				p3.setC_PORT_NAME_ST(rs.getString("C_ORG_NAME"));
				p3.setC_PORT_NAME_EN(rs.getString("C_ORG_NAME"));
				p3.setC_PORT_NAME(rs.getString("C_ORG_NAME"));
				p3.setdATA_TYPE("ASS_TYPE");
				
				if(!dataMap.containsKey(p3.getC_PORT_CODE())){
					dataMap.put(p3.getC_PORT_CODE(), p3);
				}
				//资产类型
				Port p4 = createPort();
				p4.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ")+rs.getString("C_ORG_CODE"));
				p4.setC_PORT_CODE("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ")+rs.getString("C_ORG_CODE")+rs.getString("C_DAT_CODE"));
				p4.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
				p4.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
				p4.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
				p4.setdATA_TYPE("ASS_TYPE");
				
				if(!dataMap.containsKey(p4.getC_PORT_CODE())){
					dataMap.put(p4.getC_PORT_CODE(), p4);
				}
				//组合类型
				Port p5 = createPort();
				p5.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_TAXPAYER_TYPE")+rs.getString("C_JZ_FREQ")+rs.getString("C_ORG_CODE")+rs.getString("C_DAT_CODE"));
				p5.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				p5.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME"));
				p5.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME"));
				p5.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				p5.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				p5.setC_DV_PROD_STATE(rs.getString("C_DV_PROD_STATE"));
				p5.setdATA_TYPE("PORT_TYPE");
				p5.setC_DESC(rs.getString("C_DESC"));
				if(!dataMap.containsKey(p5.getC_PORT_CODE())){
					dataMap.put(p5.getC_PORT_CODE(), p5);
				}
		} 
				
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs_zclx);
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
	}
	
	/**
	 * //add by yz 20181219  资产类型-种类
	 * STORY #61285 【中金公司】资产类型调整
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZCZLMap() {
		HashMap<String, Port> dataMap = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			dataMap = new HashMap<String, Port>();
			conn = this.loadNewConnection();
			/**
			 * 查询出所有的与组合相关的信息
			 */
			sql = portSqlBuilder.getPortTreeViewZCZLSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			
			Map<String, Vocabulary> vocmap = getVocCacheByType("ASS_ZCGLJH");
	        
			while (rs.next()) {
				// 资产类型
				Port p1 = createPort();
				p1.setC_PORT_CODE_P("[root]");
				p1.setC_PORT_CODE(rs.getString("C_DAT_CODE"));
				p1.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
				p1.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
				p1.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
				p1.setdATA_TYPE("ASS_TYPE");

				if (!dataMap.containsKey(p1.getC_PORT_CODE())) {
					dataMap.put(p1.getC_PORT_CODE(), p1);
				}
				// 资产种类
//				Port p2 = createPort();
//				p2.setC_PORT_CODE_P(rs.getString("C_DAT_CODE"));
//				p2.setC_PORT_CODE(rs.getString("C_DV_CODE"));
//				p2.setC_PORT_NAME_ST(rs.getString("C_DV_NAME"));
//				p2.setC_PORT_NAME_EN(rs.getString("C_DV_NAME"));
//				p2.setC_PORT_NAME(rs.getString("C_DV_NAME"));
//				p2.setdATA_TYPE("ZL_TYPE");
				
				
				// 资产种类
				Port p2 = createPort();
				p2.setC_PORT_CODE_P(rs.getString("C_DAT_CODE"));
				if (vocmap.containsKey(rs.getString("C_ASSETS_CODE"))) {
					p2.setC_PORT_CODE(vocmap.get(rs.getString("C_ASSETS_CODE")).getC_DV_CODE());
					p2.setC_PORT_NAME_ST(vocmap.get(rs.getString("C_ASSETS_CODE")).getC_DV_NAME());
					p2.setC_PORT_NAME_EN(vocmap.get(rs.getString("C_ASSETS_CODE")).getC_DV_NAME());
					p2.setC_PORT_NAME(vocmap.get(rs.getString("C_ASSETS_CODE")).getC_DV_NAME());
					//STORY #72592中金资产类型调整完善，增加已清算，已关账的判断   lixu 20190613
				}else if (("ASS_YQZ").equals(rs.getString("C_ASSETS_CODE"))) {
					p2.setC_PORT_CODE("WZL" + rs.getString("C_ASSETS_CODE"));
					p2.setC_PORT_NAME_ST("已清算");
					p2.setC_PORT_NAME_EN("已清算");
					p2.setC_PORT_NAME("已清算");
				}else if (("ASS_YGZ").equals(rs.getString("C_ASSETS_CODE"))) {
					p2.setC_PORT_CODE("WZL" + rs.getString("C_ASSETS_CODE"));
					p2.setC_PORT_NAME_ST("已关账");
					p2.setC_PORT_NAME_EN("已关账");
					p2.setC_PORT_NAME("已关账");
				}else {
					p2.setC_PORT_CODE("WZL" + rs.getString("C_DAT_CODE"));
					p2.setC_PORT_NAME_ST("无种类");
					p2.setC_PORT_NAME_EN("无种类");
					p2.setC_PORT_NAME("无种类");
				}

				// p2.setC_PORT_NAME(rs.getString("C_DV_NAME"));
				p2.setdATA_TYPE("ZL_TYPE");

				if (!dataMap.containsKey(p2.getC_PORT_CODE())) {
					dataMap.put(p2.getC_PORT_CODE(), p2);
				}
				// 组合类型

				Port p3 = createPort();
				if (vocmap.containsKey(rs.getString("C_ASSETS_CODE"))) {
					p3.setC_PORT_CODE_P(vocmap.get(rs.getString("C_ASSETS_CODE")).getC_DV_CODE());
				}else if(("ASS_QTCP").equals(rs.getString("C_DAT_CODE"))){
					p3.setC_PORT_CODE_P("WZL" + rs.getString("C_ASSETS_CODE"));
				} 
				else {
					p3.setC_PORT_CODE_P("WZL" + rs.getString("C_DAT_CODE"));
				}
				
//				p3.setC_PORT_CODE_P(rs.getString("C_DV_CODE"));
				p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME"));
				p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME"));
				p3.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				p3.setdATA_TYPE("PORT_TYPE");

				if (!dataMap.containsKey(p3.getC_PORT_CODE())) {
					dataMap.put(p3.getC_PORT_CODE(), p3);
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
	}
	
		/**
	 * //add by yz 20190711  资产类型-委托人
	 * STORY #67195 人保资产-4.5测试系统中产品建账环节：产品树信息建议设置为账户基本信息【3其它需求-079】
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZCWTMap() {
		HashMap<String, Port> dataMap = null;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			dataMap = new HashMap<String, Port>();
			conn = this.loadNewConnection();
			/**
			 * 查询出所有的与组合相关的信息
			 */
			sql = portSqlBuilder.getPortTreeViewZCWTSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				// 资产类型
				Port p1 = createPort();
				p1.setC_PORT_CODE_P("[root]");
				p1.setC_PORT_CODE(rs.getString("C_DAT_CODE"));
				p1.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
				p1.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
				p1.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
				p1.setdATA_TYPE("ASS_TYPE");

				if (!dataMap.containsKey(p1.getC_PORT_CODE())) {
					dataMap.put(p1.getC_PORT_CODE(), p1);
				}
				// 委托人
				Port p2 = createPort();
				p2.setC_PORT_CODE_P(rs.getString("C_DAT_CODE"));
				p2.setC_PORT_CODE(rs.getString("C_ORG_CODE"));
				p2.setC_PORT_NAME_ST(rs.getString("C_ORG_NAME"));
				p2.setC_PORT_NAME_EN(rs.getString("C_ORG_NAME"));
				p2.setC_PORT_NAME(rs.getString("C_ORG_NAME"));
				p2.setdATA_TYPE("WT_TYPE");

				if (!dataMap.containsKey(p2.getC_PORT_CODE())) {
					dataMap.put(p2.getC_PORT_CODE(), p2);
				}
				// 组合类型
				Port p3 = createPort();
				p3.setC_PORT_CODE_P(rs.getString("C_ORG_CODE"));
				p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME"));
				p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME"));
				p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				p3.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				p3.setdATA_TYPE("PORT_TYPE");

				if (!dataMap.containsKey(p3.getC_PORT_CODE())) {
					dataMap.put(p3.getC_PORT_CODE(), p3);
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
	}
	
	public HashMap<String, Port> getPortDataForCPZTMap() {
		HashMap<String, Port> dataMap = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getPortTreeViewCPZTSql(dbNameResolver);
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils
					.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					t.setdATA_TYPE(rs.getString("DATA_TYPE"));
					portCode = t.getC_PORT_CODE();
					if (portCode == null) {
						portCode = "";
					}
					if (!dataMap.containsKey(portCode)) {
						dataMap.put(portCode, t);
					}
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
	}
	
	/**
	 * 资产类型-明细资产类型
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZCMXLXMap() {
		HashMap<String, Port> dataMap = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt_zclx = null;
		ResultSet rs = null;
		ResultSet rs_zclx = null;
		String sql = "";
		String sql_zclx = "";
		String sql_build= "";
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		String portCode = "";
		try {
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);

			conn = this.loadNewConnection();
			//查询所有资产类型
			sql_zclx ="select distinct a.C_DAT_CODE ,b.C_DAT_NAME from t_p_ab_port  a join T_S_DAT_ASS_TYPE b on a.C_DAT_CODE=b.C_DAT_CODE";
			pstmt_zclx = conn.prepareStatement(sql_zclx);
			sql_zclx ="";
			rs_zclx = pstmt_zclx.executeQuery();
			while(rs_zclx.next())//每个资产类型下都有明细项，遍历所有资产类型，拼接sql
			{
				sql_build = portSqlBuilder.getPortTreeViewZCMXLXSqlBuild(rs_zclx.getString("C_DAT_CODE"),rs_zclx.getString("C_DAT_NAME"));
				sql_zclx =sql_zclx+sql_build;
			}
			sql = portSqlBuilder.getPortTreeViewZCMXLXSql(dbNameResolver,sql_zclx);//这里拼接具体到组合的sql

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			
			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
					if (t != null) {
						t.setdATA_TYPE(rs.getString("DATA_TYPE"));
						portCode = t.getC_PORT_CODE();
						if (portCode == null) {
							portCode = "";
						}
	
						if (!dataMap.containsKey(portCode)) {
							dataMap.put(portCode, t);
						}
					}
			} 
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeResultSetFinal(rs_zclx);
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt_zclx);
			releaseConnection(conn);
		}

		return dataMap;
	}
	
	/**
	 * 创建一个组合对象模板
	 * @return
	 */
	public Port createPort() {
		Port t =new Port();
		try {
			//BUG #261247【海通回归测试】估值报表界面选择组合时选择树形结构为（资产类型-种类）会报错
			t.setD_BUILD("1990-01-01");
			t.setD_CLOSE("1990-01-01");
			t.setD_CLEAR(YssFun.toDate("9998-12-31", "yyyy-MM-dd"));
			t.setC_DAT_CLS("CLS_PT");
			t.setAuditState(1);
		} catch (YssException e) {
			//e.printStackTrace();
			logger.log("YssFun.toDate 格式化日期失败！", e);
		}
		return t;
	}
	
	private boolean getShowChildPort(){
		boolean mark = false;
		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String fileName = fileUtil.getFilePath() + "runtime.properties";
		Properties properties = null;
		File file = new File(fileName);
		if(file.exists()){
			try {
				properties = propertiesUtil.Properties(fileName);
				mark = Boolean.valueOf(properties.getProperty("showChildPort","false"));
			} catch (Exception e) {
				//e.printStackTrace();
				logger.log("读取文件失败！", e);
				return mark;
			}
		}
		return mark;
	}
	
	/**
	 * 读取配置文件
	 * @return
	 */
	public static  boolean getPortCls(){
		boolean mark = false;
		FileStorePathUtil fileUtil = new FileStorePathUtil();
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String fileName = fileUtil.getFilePath() + "taqsConfig/taqs.properties";
		Properties properties = null;
		File file = new File(fileName);
		if(file.exists()){
			try {
				properties = propertiesUtil.Properties(fileName);
				mark = Boolean.valueOf(properties.getProperty("port_cls","false"));
			} catch (Exception e) {
				//e.printStackTrace();
				return mark;
			}
		}
		return mark;
	}
	
	/**
	 * 读取配置文件
	 * @return
	 */
	public static String getPortClsExist(){
		String mark = null;
		FileStorePathUtil fileUtil = new FileStorePathUtil();
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String fileName = fileUtil.getFilePath() + "taqsConfig/taqs.properties";
		Properties properties = null;
		File file = new File(fileName);
		if(file.exists()){
			try {
				properties = propertiesUtil.Properties(fileName);
				mark = properties.getProperty("port_cls",null);
			} catch (Exception e) {
				//e.printStackTrace();
				return mark;
			}
		}
		return mark;
	}
	
	/**
	 * 清算关账
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operQSGZ(List<Port> lstPort) throws ServiceException {
		this.updateById(lstPort);
		return MvcConstant._Success;
	}
	
	/**
	 * 关账撤消
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param lstPort
	 * @return
	 * @throws ServiceException
	 */
	public String operGZCX(List<Port> lstPort) throws ServiceException {
		for (Port p : lstPort) {
			java.util.Date date = DateUtil.stringtoDate("9998-12-31",
					DateUtil.LONG_DATE_FORMAT);
			p.setD_COLSE_ACC(date);
		}
		this.updateById(lstPort);
		return MvcConstant._Success;
	}
	
	/**
	 * 删除清算关账的核算数据
	 * add by liushifa 20170608 STORY #41658 产品清盘核算需求 
	 * @param port
	 * @return
	 */
	public String deleteByGZCX(List<Port> lstPort) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = "";
		String reInfo = "";
		
		try {
			conn = this.loadNewConnection();
				sql = "DELETE FROM T_D_AI_ACT_VAL WHERE  N_CHECK_STATE = 1 AND C_DVA_ITEM_CODE in ('SYJZ_JZSY', 'QSGZ')"
						+ " AND C_PORT_CODE = ? AND D_CHK_ACC = ?  AND C_DESC LIKE '%[清算关账]'";
				pst = conn.prepareStatement(sql);
			for (Port port : lstPort) {
				pst.setString(1, port.getC_PORT_CODE());
				pst.setDate(2, DbFun.sqlDate(port.getD_COLSE_ACC()));
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (Exception e) {
			logger.log(e.getMessage(), e);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return reInfo;
	}
	/**
	 * modified by yangweijie 2017-7-11 STORY #41439 华泰产品要区分集合、定向、专项
	 * @param port
	 */
	public void deleteByPortCode(String port, Connection conn){
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = "delete from T_P_AB_PORT_PD where C_PORT_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, port);
			pstmt.executeUpdate();	
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);

		}
	}
	
	/**
	 * BUG #220911 【招商基金公募升级】修改产品基本信息中的任何地方，产品属性分类中的的数据会消失 eidt by chenchangyou 20180920
	 * @param port 组合
	 * @param conn
	 */
	public boolean isExistPortPd(String port, Connection conn){
		PreparedStatement pstmt = null;
		String sql = "";
		boolean result = false;
		ResultSet rs = null;
		try {
			sql = "select 1 from T_P_AB_PORT_PD where C_PORT_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, port);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				result = true;
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询产品分类属性：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
		}
		return result;
	}
	
	/**
	 * modified by yangweijie 2017-7-11 STORY #41439 华泰产品要区分集合、定向、专项
	 * @param port 保存后再另一个表插入数据
	 */
    public void insertPortPd(Port port){
    	Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		String ciden = "";
		try {
			conn = this.loadNewConnection();			
			//BUG #220911 【招商基金公募升级】修改产品基本信息中的任何地方，产品属性分类中的的数据会消失 eidt by chenchangyou 20180920
			if(isExistPortPd(port.getC_PORT_CODE(), conn)){
				//BUG #364281 【东证资管】【300.7-0331-0420】修改了产品基本信息中的资产类型产品属性分类未跟着一起改
				sql = " update T_P_AB_PORT_PD set C_ASSETS_CODE = ? , C_DAT_CODE = ? where C_PORT_CODE = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, port.getC_ASSETS_CODE());
				pstmt.setString(2, port.getC_DAT_CODE());
				pstmt.setString(3, port.getC_PORT_CODE());
				pstmt.executeUpdate();	
			} else {
				conn.setAutoCommit(false);
				// 插入前先删除
				this.deleteByPortCode(port.getC_PORT_CODE(), conn);
				ciden = getSequenceNextNumber(conn,
						getSequanceName("T_P_AB_PORT_PD"));
				sql = "insert into T_P_AB_PORT_PD (C_IDEN,C_PORT_CODE,C_DAT_CODE,C_ASSETS_CODE,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME,C_KH_NATURE)"
						+ "values (?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, ciden);
				pstmt.setString(2, port.getC_PORT_CODE());
				pstmt.setString(3, port.getC_DAT_CODE());
				pstmt.setString(4, port.getC_ASSETS_CODE());
				pstmt.setInt(5, YssConstant.STATE_AUDIT);
				pstmt.setString(6, port.getModifier());
				pstmt.setString(7, port.getModifyDate());
				pstmt.setString(8, port.getModifier());
				pstmt.setString(9, port.getModifyDate());
				pstmt.setString(10, port.getC_KH_NATURE());
				pstmt.executeUpdate();
				conn.commit();
				conn.setAutoCommit(true);
			}
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);

		}
    }
    
    public void insertPortPd(PortPdAttribute port,Connection conn){
		PreparedStatement pstmt = null;
		String sql = "";
		String ciden = "";
		try {
			//插入前先删除
			this.deleteByPortCode(port.getC_PORT_CODE(), conn);
			ciden = getSequenceNextNumber(conn,getSequanceName("T_P_AB_PORT_PD"));
			sql = "insert into T_P_AB_PORT_PD (C_IDEN,C_PORT_CODE,C_DAT_CODE,C_ASSETS_CODE,C_COLLECT_CODE,C_INVEST_CODE,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME)"
					+ "values (?,?,?,?,?,?,?,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ciden);
			pstmt.setString(2, port.getC_PORT_CODE());
			pstmt.setString(3, port.getC_DAT_CODE());
			pstmt.setString(4, port.getC_ASSETS_CODE());
			pstmt.setString(5, port.getC_COLLECT_CODE());
			pstmt.setString(6, port.getC_INVEST_CODE());
			pstmt.setInt(7, YssConstant.STATE_AUDIT);
			pstmt.setString(8, port.getModifier());
			pstmt.setString(9, port.getModifyDate());
			pstmt.setString(10, port.getModifier());
			pstmt.setString(11, port.getModifyDate());
			pstmt.executeUpdate();	
		} catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(pstmt);
		}
    }
    
    /**
     * 
    * @param portCode
    * @return String
    * @author mazhongyuan
    * @date 2018年5月11日下午7:02:15
     */
    public Map<String,String> getPortStateByCode(List<String> portCodes) {
		Map<String,String> resultMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getPortStateByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(StringUtil.strlistToArray(portCodes), conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				resultMap.put(rs.getString("C_PORT_CODE"), rs.getString("C_DV_PROD_STATE")) ;
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return resultMap;
	}
    
    /**
	 * edit by yuanyafeng 20180522 STORY #54942 【鹏华基金】产品基本信息维护了到期及清算处理连带处理
	 * 更新运营费用设置中的组合的结束日期
	 * @param lstPort
	 */
	public void updateInveFeeDate(List<Port> lstPort){
		Connection conn = null;
//		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
//		ResultSet rs = null;
//		Map<String,Date> portMap = new HashMap<String, Date>();
		try{
//			String sql1 = "SELECT * FROM T_P_AB_INVE_FEE WHERE C_PORT_CODE IN (SELECT C_PORT_CODE FROM R_D_CLR_PORT)";
			conn = this.loadNewConnection();
//			insertPort(lstPort, conn);
//			pstmt = conn.prepareStatement(sql1);
//			rs = pstmt.executeQuery();
//			
//			while(rs.next()){
//				portMap.put(rs.getString("C_PORT_CODE"), rs.getDate("D_END"));
//			}
			
			
			String user = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();
			String sql2 = "UPDATE T_P_AB_INVE_FEE SET D_END = ?, C_UPDATE_BY = ?, C_UPDATE_TIME = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss'), C_CHECK_BY = ?, C_CHECK_TIME = to_char(sysdate,'yyyy-mm-dd hh24:mi:ss') WHERE C_PORT_CODE = ? AND D_END > ? ";
			pstmt2 = conn.prepareStatement(sql2);
			for(Port port: lstPort){
//				Date d_end = portMap.get(port.getC_PORT_CODE());
				Date date = YssFun.toDate(port.getD_CLOSE());
				pstmt2.setDate(1, YssFun.toSqlDate(date));
				pstmt2.setString(2, user);
				pstmt2.setString(3, user);
				pstmt2.setString(4, port.getC_PORT_CODE());
				pstmt2.setDate(5, YssFun.toSqlDate(date));
				pstmt2.executeUpdate();
			}
		} catch(Exception ex){
			throw new BusinessException("更新运营费用设置失败!" + ex.getMessage(),ex);
		}finally {
//			closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt2);
//			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
	/**
	 * edit by yuanyafeng 20180522 STORY #54942 【鹏华基金】产品基本信息维护了到期及清算处理连带处理
	 * 组合通过临时表处理
	 * @param portCodes
	 */
	private void insertPort(List<Port> lstPort, Connection conn){
		String sql = "INSERT INTO R_D_CLR_PORT VALUES (?) ";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for(Port port : lstPort){
				pst.setString(1, port.getC_PORT_CODE());
				pst.addBatch();
			}
			conn.setAutoCommit(false);
			pst.executeBatch();
			pst.clearBatch();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("插入产品组合失败!"+e.getMessage());
		} finally{
			this.closeStatementFinal(pst);
		}
	}

	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 * 插入产品关联机构
	 * @param prLisr
	 * @param conn
	 * @throws Exception 
	 */
	public void insertPortRela(List<PortRela> prLisr, Connection conn) throws Exception {
		PreparedStatement pstmt = null;
		String sql = "";
		try {
			sql = "insert into t_p_ab_port_rela (C_IDEN, C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME) values(sequ_p_ab_port_rela.nextval,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			for(PortRela pr : prLisr) {
				deletePortRela(pr,conn);
				pstmt.setString(1, pr.getC_PORT_CODE());
				pstmt.setString(2, pr.getC_RELA_TYPE());
				pstmt.setString(3, pr.getC_RELA_CODE());
				pstmt.setString(4, pr.getC_DV_TYPE_CODE());
				pstmt.setInt(5, pr.getAuditState());
				pstmt.setString(6, pr.getModifier());
				pstmt.setString(7, pr.getModifyDate());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeStatementFinal(pstmt);
		}
 	}

	private void deletePortRela(PortRela pr, Connection conn) throws Exception {
		PreparedStatement pst = null;
		String sql = "delete from t_p_ab_port_rela where c_port_code=? and c_rela_type=? and c_dv_type_code=? and c_rela_code=?";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, pr.getC_PORT_CODE());
			pst.setString(2, pr.getC_RELA_TYPE());
			pst.setString(3, pr.getC_DV_TYPE_CODE());
			pst.setString(4, pr.getC_RELA_CODE());
			pst.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			this.closeStatementFinal(pst);
		}
	}
	
	/***
	 * STORY #41142 南方基金-“指令已发送告知邮件”需支持参数控制是否发送
	 * @param paraCode
	 * @return
	 */
	@SuppressWarnings("finally")
	public String queryByParaCode(String paramCode) {
		String result = null;
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean bTrans = true;
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(" select A.C_PARAMS_VALUE from T_S_UNIFYPAY_PARAMS A where C_PARAMS_CODE=? and N_CHECK_STATE=1 AND (C_PARAMS_TYPE = 'UNIFY_PUBLIC' OR C_PARAMS_TYPE IS NULL) ");
			pst.setString(1, paramCode);
			rs = pst.executeQuery();
			if(rs.next()){
				result = rs.getString("C_PARAMS_VALUE");
			}
		} catch (Exception e) {
			logger.log("查询支付平台参数失败！",e);
			throw new DataAccessException("查询支付平台参数失败：" + e.getMessage(), e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			endTransFinal(conn, bTrans);
			releaseConnection(conn);
		}
		return result;
	}

	/**
	 * BUG #327651【中银基金】【0228-0710】试算平衡报错
	 */
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

				if ("c_USER_PWD".equalsIgnoreCase(prop.getName())) {
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
				sqlBuffer.append(" C_IDEN IN (SELECT * FROM table(?)) ");
				sql = sqlBuffer.toString();
			} else {
				throw new InvalidDataException(pojo.getClass().toString()
						+ "的实例没有属性值");
			}
			ptmt = openPreparedStatement(sql, conn);
			ptmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String.valueOf(ids),conn));
			
			rs = ptmt.executeQuery();
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				pojo = (K) rsTools.ResultToBean(rs, clazz, props);
				((Port) pojo).setdATA_TYPE("PORT_TYPE");
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			//Orlando 20150707 异常信息中增加执行的SQL打印
			throw new DataAccessException("SQL["+sqlBuffer.toString()+"]查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	/**
	 * 查询子组合
	 * @param protPCode
	 * @return
	 */
	public List<BasePojo> getUnitLayerPort(String[] protPCode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getUnitLayerPort();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, protPCode[0]);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
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
	 * 根据组合代码查询组合信息
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public List<Port> getPortListByCodes(String[] codes) throws Exception {
		List<Port> pojoList = new ArrayList<Port>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortSqlBuilder portSqlBuilder = null;
		Port t = null;
		try {
			portSqlBuilder = new PortSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, portSqlBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = portSqlBuilder.getPortByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(codes,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
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
	 * 将APPY_USER_REAL 用户ID  USER_POST_CODE 岗位ID 通过FAST权限查询出对应的结果集拼接成
	 * MAP.put(APPY_USER_REAL,"portcode1,portcode2 ..... ,portcodeN"
	 * @Title: convertNewFastRightParam 
	 * @param paraMap
	 */
    private void convertNewFastRightParam(HashMap<String, Object> paraMap) {
        List<String> portList = null;
        try {
            String userCode = "";
            String postCode = "";
            // 通过用户ID 以及用户角色从fast权限体系获取对应的组合
            if (paraMap.containsKey("APPY_USER_REAL")) {
                userCode = (String)paraMap.get("APPY_USER_REAL");
            } else if (paraMap.containsKey("C_USER_CODE")) {
                userCode = (String)paraMap.get("C_USER_CODE");
            }

            if (paraMap.containsKey("USER_POST_CODE")) {
                postCode = (String)paraMap.get("USER_POST_CODE");
                paraMap.remove("USER_POST_CODE");
            }
            
//            else {
//                IPostRightService postRightService =
//                    YssServiceFactory.getInstance().createService(IPostRightService.class);
//                List<String> postList = postRightService.getPostsByPostType("RIGHT");
//                postCode = StringUtil.join(postList, ",");
//            }

            //分布式环境启动，加载缓存报错
            if(!"".equals(userCode)) {
	            IFASTDataAuthorityService fda = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
	            if(StringUtil.IsNullOrEmpty(postCode)) {
	                portList = fda.queryByUser(RightConstants.portType, userCode);
	            } else {
	                portList = fda.queryByUser(RightConstants.portType, userCode, postCode);
	            }
        	}
            
            if (paraMap.containsKey("APPY_USER_REAL")) {
                paraMap.put("APPY_USER_REAL", StringUtil.join(portList, ","));
            } else if (paraMap.containsKey("C_USER_CODE")) {
                paraMap.put("C_USER_CODE", StringUtil.join(portList, ","));
            }
            
        } catch (Exception ex) {
            throw new BusinessException("查询组合失败!" + ex.getMessage());
        }
    }
    
    /**
     * 根据数据字典类型获取Map<KEY, Vocabulary>结果集
     * @Title: getVocCacheByType 
     * @param vocType 数据字典类型
     * @return Map<String, Vocabulary>
     */
    public Map<String, Vocabulary> getVocCacheByType(String vocType){
        Map<String, Vocabulary> map = new HashMap<String, Vocabulary>();
        IVocCacheDataService vocService = null;
        //依赖FASTVOC缓存加载，临时处理等待1秒
        //BUG #364807 【招银理财】V1.300.7.0.20210331 服务重启组合缓存启动失败
        for(int i = 0; null == CacheManager.getInstance().getCache(CacheGroup.FASTVOC) && i < 60; i++){
        	try {
				Thread.sleep(1000);
				if(i%5==0){
					if(null == vocService){
						vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService");
					}
					// vocService.getDataListByTypes 最终还是要从缓存里取数据
					if(null != CacheManager.getInstance().getCache(CacheGroup.FASTVOC)) {
						break;
					}
				}
			} catch (InterruptedException e) {
				logger.error("getVocCacheByType--InterruptedException：" + e.getMessage(), e);
				Thread.currentThread().interrupt();
			}
        }
        if(vocService== null){
        	vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService");
        }
        List<Vocabulary> vocList = vocService.getDataListByTypes(new String [] {vocType});
        if (vocList!= null && vocList.size()>0) {
            for (Vocabulary voc : vocList) {
                map.put(voc.getC_DV_CODE(), voc);
            }
        }
        return map;
    }

	public List<String> findIsPortData(List<String> sourceData) throws DataAccessException {
		List<String> result = new ArrayList<String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = "select c_port_code from t_P_AB_Port where c_port_code in (SELECT * FROM TABLE(?))";

			pstmt = conn.prepareStatement(sql);
			String[] sourceDataArr = new String[sourceData.size()];
			pstmt.setArray(1,OraDbTool.newInstance().sqlOverLongCondition(sourceData.toArray(sourceDataArr), conn));
			rs = pstmt.executeQuery();
			while (rs.next()) {				
				result.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return result;	
	}
	
	public List<Port> queryPortByCodes(List<String> sourceData) throws DataAccessException {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		Port t = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = "SELECT A.* FROM T_P_AB_PORT A WHERE a.N_CHECK_STATE = 1 and a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ";
			pstmt = conn.prepareStatement(sql);
			String[] sourceDataArr = new String[sourceData.size()];
			pstmt.setArray(1,OraDbTool.newInstance().sqlOverLongCondition(sourceData.toArray(sourceDataArr), conn));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {				
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;	
	}
	
	/**
	 * STORY STORY31414 保险估值表导出DBF文件名特殊要求
	 * 增加产品编号查询修改文件名称   
	 * @date 2016-9-5 
	 * @author 黄凯旋
	 * @param portCode 组合代码
	 * @return productNum 产品编号
	 */
	public String getProductNum(String portCode) throws DataAccessException {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String productNum = "";
		try{
			conn = this.loadNewConnection();
			String sql = "select C_PRODUCT_NUM from T_P_AB_PORT_PD where N_CHECK_STATE = 1 and C_PORT_CODE = ?";
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while(rs.next()){
				productNum = rs.getString("C_PRODUCT_NUM");
			}
		}catch(Exception ex){
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException(ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return productNum;
	}
	
	public List<String> getPortCodes4ReportSet() throws DataAccessException {
		List<String> portCodes = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = "select p.c_port_code from T_P_AB_PORT P "
					+ "where P.C_DAT_CODE IN ('ASS_ZQTZJJ', 'ASS_QDII') "
					+ "AND P.C_DV_PROD_STATE = 'PS4' "
					+ "AND P.N_CHECK_STATE = 1";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				portCodes.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception e) {
			logger.error("查询失败：" + e.getMessage(), e);
			throw new DataAccessException(e);
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return portCodes;
	}
	
	public List<String> getPortCodes4ReportSetOfPortCls() throws DataAccessException {
		List<String> portCodes = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = "SELECT C.C_PORT_CODE FROM T_P_AA_PORT_CLS C "
					+ "WHERE C_PORT_CODE "
					+ 		"IN (SELECT P.C_PORT_CODE FROM T_P_AB_PORT P "
								+ "WHERE P.C_DAT_CODE IN ('ASS_ZQTZJJ', 'ASS_QDII') "
								+ "AND P.C_DV_PROD_STATE = 'PS4' "
								+ "AND P.N_CHECK_STATE = 1) "
					+ "AND N_CHECK_STATE = 1";
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while(rs.next()){
				portCodes.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception e) {
			logger.error("查询失败：" + e.getMessage(), e);
			throw new DataAccessException(e);
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return portCodes;
	}
	
	public List<Map<String, String>> getAllPortData() throws DataAccessException {
		List<Map<String, String>> result = new ArrayList<Map<String, String>>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			sql.append("select c.c_port_code, c.C_PORT_CODE_P, c.c_port_name_st from (");
			sql.append("	select a.c_port_code, ");
			sql.append("		(case when trim(a.C_PORT_CODE_P) is null then a.C_DAT_CODE else a.C_PORT_CODE_P end) as C_PORT_CODE_P, ");
			sql.append("		a.c_port_name_st ");
			sql.append("		from t_p_ab_port a ");
			sql.append("		where a.N_CHECK_STATE = 1 ");
			sql.append("	union all ");
			sql.append("	select b.c_dat_code as c_port_code, ");
			sql.append("		b.c_dat_code_p as C_PORT_CODE_P, ");
			sql.append("		b.C_DAT_NAME as C_PORT_NAME_ST ");
			sql.append("		from T_S_DAT_ASS_TYPE b ) c");
			sql.append(" start with c.C_PORT_CODE_P = '[root]'");
			sql.append("connect by prior c.c_port_code = c.C_PORT_CODE_P");
			pst = conn.prepareStatement(sql.toString());
			rs = pst.executeQuery();
			while(rs.next()){
				Map<String, String> map = new HashMap<String, String>();
				map.put("C_PORT_CODE", rs.getString("C_PORT_CODE"));
				map.put("C_PORT_CODE_P", rs.getString("C_PORT_CODE_P"));
				map.put("C_PORT_NAME_ST", rs.getString("C_PORT_NAME_ST"));
				result.add(map);
			}
		} catch (Exception e) {
			logger.error("查询失败："+e.getMessage(), e);
			throw new DataAccessException(e);
		} finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 通过组合CODE找组合Pojo
	 * @param portCodes
	 * @return
	 */
	public List<Port> queryPortByPortCode(String[] portCodes){
		List<Port> list = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		if(portCodes == null || portCodes.length <=0)
			return list;
		try{
			conn = this.loadNewConnection();
			String sql = " SELECT * FROM T_P_AB_PORT where C_PORT_CODE in(select * from table(?)) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				list.add(t);
			}
		}catch(Exception ex){
			throw new BusinessException("查询组合Pojo失败!"+ex.getMessage());
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 
	 *根据业务类型获取没有关联此业务的组合
	 * @Title: getBusinessRangePortAdd 
	 * @param @param paraMap
	 * @param @param clazz
	 * @param @return
	 * @return List<BasePojo>    
	 * @throws 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	public List<BasePojo> getBusinessRangePortAdd(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getBusinessRangePortAdd();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, paraMap.get("C_BUSINESS_CODE").toString());
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("C_PORT_CODE").toString(), conn));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * 根据业务类型、组合代码获取组合
	 *
	 * @Title: getBusinessRangePortBrow 
	 * @param @param paraMap
	 * @param @param clazz
	 * @param @return
	 * @return List<BasePojo>    
	 * @throws 
	 * @Stroy/Bug
	 * @author xiadeqi
	 */
	public List<BasePojo> getBusinessRangePortBrow(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getBusinessRangePortBrow();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, paraMap.get("C_PORT_CODE").toString());
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * @Desc  BUG #260010 [webservice接口]估值表下载接口
	 * 根据用户先从组合岗位权限数据中关联组合数据，再拼装资产树结构
	 * @author houjiaqi
	 * @date 2019年5月27日 上午10:44:09
	 * @param @param userCode
	 * @param @return
	 * @param @throws Exception
	 */
	public List<Port> getPortByUser(String userCode) {
		List<String> portCodes = new ArrayList<String>();
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		String sql1 = "";
		String sql2 = "";
		ResultSetTools rsTools = null;
		try {
			// 使用Set去重
			Set<String> postSet = new HashSet<String>();
			IUserPostDataService userPostDataService = YssServiceFactory.getInstance().createService(IUserPostDataService.class);
			List<UserPostData> postList = userPostDataService.queryByUserCode(userCode, "1");
			for (UserPostData post : postList) {
				postSet.add(post.getC_DATA_CODE());
			}
			portCodes.addAll(postSet);
			
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
//			sql1 = "select c_data_code from t_s_user_post_data where c_user_code = ? and c_data_type = '1' group by c_data_code";
//			pstmt = conn.prepareStatement(sql1);
//			pstmt.setString(1, userCode);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				portCodes.add(rs.getString("C_DATA_CODE"));
//			}
			//为组合临时表添加数据 BUG #281983 基准测试 发现慢SQL update wwt
			String[] Codesarr = portCodes.toArray(new String[portCodes.size()]);
			insertTempPortCodeTable(conn, Codesarr);
			sql2 = portSqlBuilder.getPortTreeByCodesSql();
			pstmt = conn.prepareStatement(sql2);
			//pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes.toArray(new String[portCodes.size()]), conn));
			//pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCodes.toArray(new String[portCodes.size()]), conn));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				pojoList.add(t);
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
		return pojoList;
	}
	
	/**
	 * 
	 * @Title updateDataByPortCode 
	 * @Description 根据产品代码更新产品信息
	 * @author yinyuyi
	 * @date 2019年6月13日下午7:06:51
	 * @param updatePort
	 * @param portCode
	 * @param conn
	 * @return void
	 */
    public void updateDataByPortCode(Port updatePort, String portCode, Connection conn) {

        Statement st = null;

        StringBuffer sql = new StringBuffer();

        StringBuffer condition = new StringBuffer();
        try {
            sql.append(" UPDATE ").append(this.sqlbuilder.getTableName(dbNameResolver));
            sql.append(" SET ");

            
            //产品名称
            if (updatePort.getC_PORT_NAME() != null) {
                condition.append(" C_PORT_NAME = '").append(updatePort.getC_PORT_NAME()).append("',");
            }
            //产品简称
            if (updatePort.getC_PORT_NAME_ST() != null) {
                condition.append(" C_PORT_NAME_ST = '").append(updatePort.getC_PORT_NAME_ST()).append("',");
            }
            //产品英文名称
            if (updatePort.getC_PORT_NAME_EN() != null) {
                condition.append(" C_PORT_NAME_EN = '").append(updatePort.getC_PORT_NAME_EN()).append("',");
            }
            
            if(condition.length()>0){
                sql.append(condition.substring(0,condition.length()-1));
            }

            sql.append(" WHERE C_PORT_CODE = '").append(portCode).append("' ");

            st = conn.createStatement();

            st.executeUpdate(sql.toString());
        }
        catch (SQLException e) {
            logger.error("根据产品代码更新产品信息时出错！", e);
        }
        finally {
            this.closeStatementFinal(st);
        }

    }
    /**
     * 根据组合代码获取组合列表
     * @param portCode
     * @return
     */
	public List<Port> getPortListByPortCode(String portCodes) {
		List<Port> pojoList = new ArrayList<Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			if(StringUtil.IsNullOrEmptyT(portCodes)){
				sql = "SELECT * FROM T_P_AB_PORT A ";
			}else{
				sql = "SELECT * FROM T_P_AB_PORT A WHERE A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) ";
			}
			pstmt = conn.prepareStatement(sql);
			if(!StringUtil.IsNullOrEmptyT(portCodes)){
				pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));
			}
			rs = pstmt.executeQuery();
			Port t = null;
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				t = (Port) rsTools.ResultToBean(rs, Port.class, props);
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
	 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
	 * @param cTRCode
	 * @return
	 * @throws Exception
	 */
	public List<BasePojo> getDefaultAllPort_YFP(String cTRCode)
			throws Exception {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getDefaultAllAssPort_YFP();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, cTRCode);
			pstmt.setString(2, cTRCode);
			rs = pstmt.executeQuery();

			rsTools = new ResultSetTools(dbNameResolver,
					new PortAssTreeSqlBuilder());
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * STORY #70664 【深国投】产品基本信息功能中需要根据有组合权限的用户来控制 
	 * 重写产品基本信息查询方法
	 * @param paraMap
	 * @return
	 */
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		String sql = "";
		StringBuffer buf = new StringBuffer();
		// Object resValue = null;
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = portSqlBuilder.getQueryConditionSql(paraNameList);
			/*String portListStr = (String) paraMap.get("DATARIGHTlIST");
			String[] portList = portListStr.split(",");*/
			String pubVal = (String)paraMap.get("SV_XS_CPZSSQXKZMK");
			
			if("CPMK".equals(pubVal)){
				buf.append(" select * from ( ");
				buf.append(sql);
				String sql2 = "INSERT INTO R_D_CLR_PORT (C_PORT_CODE) VALUES (?)";
				pstmt2 = conn.prepareStatement(sql2);
				// 使用Set去重
				LoggingInfo log =ContextFactory.getContext().getLogInfo();
				String currenUserCode = log.getLoggingUserCode();//当前用户
				String currenPostCode = log.getLoggingPostCode();//当前岗位
				IFASTDataAuthorityService fastDataAuthorityService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
	            List<String> portList = fastDataAuthorityService.queryByUser(RightConstants.portType, currenUserCode, currenPostCode);
				for (String postCode : portList) {
					pstmt2.setString(1, postCode);
					pstmt2.addBatch();
				}
				pstmt2.executeBatch();
				buf.append(" and a.c_port_code in(SELECT c_port_code FROM R_D_CLR_PORT )");
				buf.append(" union ");
				buf.append(portSqlBuilder.getQueryCPMKConditionSql(paraNameList));
				buf.append(" ) a  ");
				
			}else{
				buf.append(sql);
			}
			
			buf.append(" order by decode(a.N_CHECK_STATE,'0',1,2),a.c_Dat_Code,a.D_BUILD  asc,a.c_iden ");
			
			sql = buf.toString();
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
				for (String valueFieldName : paraNameList) {
					if ("SV_XS_CPZSSQXKZMK".equals(valueFieldName)) {
						continue;
					}
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
				
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					if ("N_AUTHORG".equals(valueFieldName)) {
                        continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/

					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)).split(","), conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, (java.sql.Date) dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
					
					
				}
				if("CPMK".equals(pubVal)){
					for (String valueFieldName : paraNameList) {
						if ("SV_XS_CPZSSQXKZMK".equals(valueFieldName)) {
							continue;
						}
						if ("N_CHECK_STATE".equals(valueFieldName)) {
							continue;
						}
						
						if ("QUERY_SOURCE".equals(valueFieldName)) {
							continue;
						}
						if ("N_AUTHORG".equals(valueFieldName)) {
	                        continue;
						}
						if (valueFieldName.startsWith("ARRAY_")) {
							pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
									.valueOf(paraMap.get(valueFieldName)).split(","), conn));
						} else {
							paraValue = paraMap.get(valueFieldName);
							if (java.util.Date.class.equals(paraValue)) {
								Date dateValue = new Date(
										((java.util.Date) paraValue).getTime());
								pstmt.setDate(index, (java.sql.Date) dateValue);
							} else {
								pstmt.setObject(index, paraValue);
							}
						}
						index++;
					}
					
				}
			}
			
			

			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}
			deleteTempPortCodeTable(conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt, pstmt2);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buf);
		}

		return pojoList;
	}

	/**
	 * STORY #70664 【深国投】产品基本信息功能中需要根据有组合权限的用户来控制 
	 * 重写产品基本信息分页方法
	 * @param recCount
	 * @return
	 */
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs = null;

		StringBuffer buf = new StringBuffer();
		String sql = "";
		int recCount = 0;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			
			String pubVal = (String)paraMap.get("SV_XS_CPZSSQXKZMK");
			if("CPMK".equals(pubVal)){
				sql = portSqlBuilder.getQueryConditionSql(paraNameList);
				buf.append(" select COUNT(*) AS CNT from ( ");
				buf.append(sql);
				String sql2 = "INSERT INTO R_D_CLR_PORT (C_PORT_CODE) VALUES (?)";
				pstmt2 = conn.prepareStatement(sql2);
				// 使用Set去重
				LoggingInfo log =ContextFactory.getContext().getLogInfo();
				String currenUserCode = log.getLoggingUserCode();//当前用户
				IFASTDataAuthorityService fastDataAuthorityService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
	            List<String> portList = fastDataAuthorityService.queryByUser(RightConstants.portType, currenUserCode);
				for (String postCode : portList) {
					pstmt2.setString(1, postCode);
					pstmt2.addBatch();
				}
				pstmt2.executeBatch();
				buf.append(" and a.c_port_code in(SELECT c_port_code FROM R_D_CLR_PORT )");
				buf.append(" union ");
				buf.append(portSqlBuilder.getQueryCPMKConditionSql(paraNameList));
				buf.append(" ) a  ");
			}else{
				buf.append(sqlbuilder.getQueryConditionCountSql(paraNameList));
			}
			
			
			sql = buf.toString();
			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
				for (String valueFieldName : paraNameList) {
					if ("SV_XS_CPZSSQXKZMK".equals(valueFieldName)) {
						continue;
					}
					if ("N_CHECK_STATE".equals(valueFieldName)) {
						continue;
					}
					
					/**Start 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错
					 * 过滤掉QUERY_SOURCE的赋值，数据审核管理有几个界面会直接走基类查询，然后传入这个参数，这样产生无效的列索引的异常*/
					if ("QUERY_SOURCE".equals(valueFieldName)) {
						continue;
					}
					/**End 20150430 added by liubo.BUG #111830 数据审核管理点击A区可选项目，有些界面报错*/
					if ("N_AUTHORG".equals(valueFieldName)) {
                        continue;
					}
					if (valueFieldName.startsWith("ARRAY_")) {
						pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
								.valueOf(paraMap.get(valueFieldName)).split(","), conn));
					} else {
						paraValue = paraMap.get(valueFieldName);
						if (java.util.Date.class.equals(paraValue)) {
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, (java.sql.Date) dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
				if("CPMK".equals(pubVal)){
					for (String valueFieldName : paraNameList) {
						if ("SV_XS_CPZSSQXKZMK".equals(valueFieldName)) {
							continue;
						}
						if ("N_CHECK_STATE".equals(valueFieldName)) {
							continue;
						}
						if ("QUERY_SOURCE".equals(valueFieldName)) {
							continue;
						}
						if ("N_AUTHORG".equals(valueFieldName)) {
	                        continue;
						}
						if (valueFieldName.startsWith("ARRAY_")) {
							pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
									.valueOf(paraMap.get(valueFieldName)).split(","), conn));
						} else {
							paraValue = paraMap.get(valueFieldName);
							if (java.util.Date.class.equals(paraValue)) {
								Date dateValue = new Date(
										((java.util.Date) paraValue).getTime());
								pstmt.setDate(index, (java.sql.Date) dateValue);
							} else {
								pstmt.setObject(index, paraValue);
							}
						}
						index++;
					}
					
				}
			}
			
			rs = pstmt.executeQuery();

			while (rs.next()) {
				recCount = rs.getInt(1);
			}
			deleteTempPortCodeTable(conn);
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			//ex.printStackTrace();
			throw new DataAccessException("条件查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt, pstmt2);
			releaseConnection(conn);
			StringUtil.clearStringBuffer(buf);
		}

		return recCount;
	}
	
	/**
	 * 调用临时表
	 * @param conn
	 * @param portCodes
	 * @throws SQLException
	 */
	public void insertTempPortCodeTable(Connection conn, String[] portCodes) throws SQLException{	
		PreparedStatement pst = null;
		String[] codes = portCodes;
		String sql = "INSERT INTO R_D_CLR_PORT (C_PORT_CODE) VALUES (?)";
		
		try{
			pst = conn.prepareStatement(sql);
            if(codes.length > 0){
            	for(int i = 0; i < codes.length; i++){
            		//清算综合参数:对于公共清算参数,当更改后保存后,组合传进来时值为" ",因此这里加入空格判断
            		if(codes[i].trim().length() <= 0 && !" ".equals(codes[i])){
            			continue;
            		}
    				pst.setString(1, codes[i]);
    				pst.addBatch();
    			}
            	
            	pst.executeBatch();
            }
			
		} finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	public void deleteTempPortCodeTable(Connection conn) throws SQLException {
		PreparedStatement pst = null;
		String sql = "DELETE FROM R_D_CLR_PORT ";
		try{
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		} finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
     * STORY #73984 增值税纳税报表辅助部分优化需求
	 * @return
	 */
	public HashMap<String, String> portDeduction(List<String> portList) {
		HashMap<String, String> dataMap = null;
		Set<String> portSet=new HashSet<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			dataMap = new HashMap<String, String>();
			conn = this.loadNewConnection();
			List<String> portGroupedList=getgetPortGroupeds(conn);

			for (String groupCode : portGroupedList) {
				portList.remove(groupCode);//删除分组编码设置的组合
			}
			
			sql = portSqlBuilder.getPortDeductionSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			
			String code="";
			StringBuilder stringBuilder=new StringBuilder();
			int index=0;
			while (rs.next()) {
				String ddtCode=rs.getString("C_NOT_DDT");
				String portCode=rs.getString("C_PORT_CODE");
				portSet.add(ddtCode);
				if(index++>0){
					if(!(ddtCode.equals(code))){
						stringBuilder.setLength(0);
						code=ddtCode;
					}
				}else{
					code=ddtCode;
				}
				
				stringBuilder.append(portCode).append(",");
				dataMap.put(ddtCode, stringBuilder.toString());
			} 
			
			for (String ddtCode : portSet) {
				List<String> portList2=new ArrayList<String>();
				portList2.addAll(portList);
				String portCodes=dataMap.get(ddtCode);
				for (String string : portCodes.split(",")) {
					portList2.remove(string);
				}
				//BUG #351968 【申万宏源】增值税业务-抵扣方案设置选择一条点修改，系统直接卡掉，一直处于未响应状态（300.7-0630）
				if(portList.toString().length() > 202){
					dataMap.put(ddtCode, portList2.toString().replace("[", "").replace("]", "").substring(0,200)+"...");
				}else{
				dataMap.put(ddtCode, portList2.toString().replace("[", "").replace("]", ""));
				}
			}
				
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataMap;
	}
	
	/**
     * STORY #73984 增值税纳税报表辅助部分优化需求
	 * @param conn
	 * @return 查询分组编码设置的组合
	 */
	private List<String> getgetPortGroupeds(Connection conn) {
		List<String> dataList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			sql = portSqlBuilder.getPortGroupedSql();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				dataList.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
		}

		return dataList;
	}
	
	/**
     * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合
     * 根据组合Code查询返回 它所属的树形结构Code集合
     * @param portCode 组合代码
     * @return
     */
	public List<String> queryAssetTreeByPortCode(String portCode) {
		List<String> assetTreeList = new ArrayList<String>();
		// 添加默认节点
		assetTreeList.add("ASS");
		assetTreeList.add("ZCTG");
		assetTreeList.add("ZCGL");
		assetTreeList.add("ZCWT");
		assetTreeList.add("ZCZL");
		assetTreeList.add("TGZC");
		assetTreeList.add("GLZC");
		assetTreeList.add("NSPL");
		assetTreeList.add("ZCMXLX");
		assetTreeList.add("CPZT");
		
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = portSqlBuilder.getAssetTreeByPortCodeSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, AppContext.getInstance().getUserCode());
			pstmt.setString(2, portCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String trCode = rs.getString("C_TR_CODE");
				if (!assetTreeList.contains(trCode)) {
					assetTreeList.add(trCode);
				}
			}
		} catch (Exception e) {
			logger.error("查询失败：", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return assetTreeList;
	}
	
	/**
	 * STORY #83454 【招商基金】产品信息建好后又删除重新创建，但系统删除动作时没有将其对应关联表相关数据删除 
	 * @param portCodes 
	 * @throws Exception
	 */
	public void delPortRela(String[] portCodes) throws Exception {
		PreparedStatement pst = null;
		Connection conn = null;
		String sql = "delete from t_p_ab_port_rela where c_port_code in (select * from table(?))";
		try {
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(sql);
			pst.setArray(1,OraDbTool.newInstance().sqlOverLongCondition(portCodes,conn));
			pst.execute();
		} catch (Exception e) {
			throw e;
		} finally {
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 根据总账组合代码 查找总账下面的分账组合 
	 * MOM流水清算设置界面 过滤组合加载逻辑
	 * STORY #87435 华夏基金-MOM产品需求-个性化部分  add by sunyanlin 20200525
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> queryMomPortSub(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ResultSetTools rsTools = null;

		try {
			
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			String sql = portSqlBuilder.getMomPortSubSql();
			
			String portCode = (String)paraMap.get("C_PORT_CODE");

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, Port.class);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
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
	 * 根据树形结构代码获取树形结构设置
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 * @param trCode
	 * @return
	 */
	public HashMap<String, String> getAssetsTree(String trCode){
		HashMap<String, String> assetsTree = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		try{
			conn = this.loadNewConnection();
			buf.append("SELECT TR.C_TR_CODE AS C_TR_CODE, ");
			buf.append("       TR.C_DV_TR AS C_DV_TR, ");
			buf.append("       TR.C_AUTO_ZR_TYPE AS C_AUTO_ZR_TYPE, ");
			buf.append("       TRRULE.C_CPSJWD AS C_CPSJWD, ");
			buf.append("       TRRULE.C_CPSJWD_FLCJ AS C_CPSJWD_FLCJ, ");
			buf.append("       TRRULE.C_ZCSXWD AS C_ZCSXWD, ");
			buf.append("       TRRULE.C_ZCSXWD_FLCJ AS C_ZCSXWD_FLCJ ");
			buf.append("  FROM T_P_AB_ASS_TR TR ");
			buf.append("  LEFT JOIN T_P_AB_ASS_TR_RULE TRRULE ");
			buf.append("    ON TR.C_IDEN = TRRULE.C_IDEN_RELA ");
			buf.append(" WHERE TR.C_TR_CODE = ? ");
			buf.append("   AND N_CHECK_STATE = 1 ");
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, trCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				assetsTree.put("C_TR_CODE", rs.getString("C_TR_CODE"));
				assetsTree.put("C_DV_TR", rs.getString("C_DV_TR"));
				assetsTree.put("C_AUTO_ZR_TYPE", rs.getString("C_AUTO_ZR_TYPE"));
				assetsTree.put("C_CPSJWD", rs.getString("C_CPSJWD"));
				assetsTree.put("C_CPSJWD_FLCJ", rs.getString("C_CPSJWD_FLCJ"));
				assetsTree.put("C_ZCSXWD", rs.getString("C_ZCSXWD"));
				assetsTree.put("C_ZCSXWD_FLCJ", rs.getString("C_ZCSXWD_FLCJ"));
			}
			
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return assetsTree;
	}
	/**
	 * 获取规则为"自定义“自动转入类型不是“转入动态组合”的树形信息
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 * @param ports
	 * @param assetsTree
	 * @return
	 */
	public HashMap<String, Port> getPortDataForZdyMap(String ports,HashMap<String, String> assetsTree, boolean isAllPort) {
		HashMap<String, Port> dataMap = null;
		//BUG #332591 【国寿安保】【06300827】系统升级后统计分析估值表报错
		if(StringUtil.IsNullOrEmptyT(assetsTree.get("C_CPSJWD")) || StringUtil.IsNullOrEmptyT(assetsTree.get("C_ZCSXWD"))
				|| StringUtil.IsNullOrEmptyT(assetsTree.get("C_CPSJWD_FLCJ")) || StringUtil.IsNullOrEmptyT(assetsTree.get("C_ZCSXWD_FLCJ"))){
			return new HashMap<String, Port>();
		}
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String trCodeStr = assetsTree.get("C_TR_CODE");
		String cpsjwdStr = assetsTree.get("C_CPSJWD");
		String zcsxwdStr = assetsTree.get("C_ZCSXWD");
		String cpsjwdFlcj = assetsTree.get("C_CPSJWD_FLCJ");
		String zcsxwdFlcj = assetsTree.get("C_ZCSXWD_FLCJ");
		int index = 1;
		try{
			conn = this.loadNewConnection();
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
			sql = portSqlBuilder.getPortTreeViewZdySql(dbNameResolver,isAllPort);
			if("2".equals(cpsjwdFlcj) && "1".equals(zcsxwdFlcj)){
				sql += " ORDER BY A.C_DAT_CODE,A.C_DV_PROD_STATE,A.C_PORT_CODE ";
			}else{
				sql += "  ORDER BY A.C_DV_PROD_STATE,A.C_DAT_CODE,A.C_PORT_CODE ";
			}
			pstmt = conn.prepareStatement(sql);		
			pstmt.setString(index++, trCodeStr);
			if(!isAllPort){
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
			}
			pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(zcsxwdStr,conn));
			pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(cpsjwdStr,conn));
			rs = pstmt.executeQuery();			
			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					if("2".equals(cpsjwdFlcj) && "1".equals(zcsxwdFlcj)){
						
						//资产属性维度
						Port p1 = createPort();
						p1.setC_PORT_CODE_P("[root]");
						p1.setC_PORT_CODE("[root]"+rs.getString("C_DAT_CODE"));
						p1.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
						p1.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
						p1.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
						p1.setdATA_TYPE("ASS_TYPE");
						p1.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p1.getC_PORT_CODE())){
							dataMap.put(p1.getC_PORT_CODE(), p1);
						}
						
						//产品时间维度
						Port p2 = createPort();
						p2.setC_PORT_CODE_P("[root]"+rs.getString("C_DAT_CODE"));
						p2.setC_PORT_CODE("[root]"+rs.getString("C_DAT_CODE")+rs.getString("C_DV_CODE"));
						p2.setC_PORT_NAME_ST(rs.getString("C_DV_NAME"));
						p2.setC_PORT_NAME_EN(rs.getString("C_DV_NAME"));
						p2.setC_PORT_NAME(rs.getString("C_DV_NAME"));
						p2.setdATA_TYPE("CPSJ_TYPE");
						p2.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p2.getC_PORT_CODE())){
							dataMap.put(p2.getC_PORT_CODE(), p2);
						}
						
						//组合类型
						Port p3 = (Port) rsTools.ResultToBean(rs, Port.class, props);
						if(rs.getString("C_PORT_CODE_P") == null || "".equals(rs.getString("C_PORT_CODE_P").trim())){
							p3.setC_PORT_CODE_P("[root]"+rs.getString("C_DAT_CODE")+rs.getString("C_DV_CODE"));
						}else{
							p3.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
						}
						p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
						p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
						p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
						p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
						p3.setdATA_TYPE("PORT_TYPE");
						
						if(!dataMap.containsKey(p3.getC_PORT_CODE())){
							dataMap.put(p3.getC_PORT_CODE(), p3);
						}
						
					}else{
						//产品时间维度
						Port p1 = createPort();
						p1.setC_PORT_CODE_P("[root]");
						p1.setC_PORT_CODE("[root]"+rs.getString("C_DV_CODE"));
						p1.setC_PORT_NAME_ST(rs.getString("C_DV_NAME"));
						p1.setC_PORT_NAME_EN(rs.getString("C_DV_NAME"));
						p1.setC_PORT_NAME(rs.getString("C_DV_NAME"));
						p1.setdATA_TYPE("CPSJ_TYPE");
						p1.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p1.getC_PORT_CODE())){
							dataMap.put(p1.getC_PORT_CODE(), p1);
						}
						
						//资产属性维度
						Port p2 = createPort();
						p2.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_CODE"));
						p2.setC_PORT_CODE("[root]"+rs.getString("C_DV_CODE")+rs.getString("C_DAT_CODE"));
						p2.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
						p2.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
						p2.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
						p2.setdATA_TYPE("ASS_TYPE");
						p2.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p2.getC_PORT_CODE())){
							dataMap.put(p2.getC_PORT_CODE(), p2);
						}
						//组合类型
						Port p3 = (Port) rsTools.ResultToBean(rs, Port.class, props);
						if(rs.getString("C_PORT_CODE_P") == null || "".equals(rs.getString("C_PORT_CODE_P").trim())){
							p3.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_CODE")+rs.getString("C_DAT_CODE"));
						}else{
							p3.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
						}
						p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
						p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
						p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
						p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
						p3.setdATA_TYPE("PORT_TYPE");
						
						if(!dataMap.containsKey(p3.getC_PORT_CODE())){
							dataMap.put(p3.getC_PORT_CODE(), p3);
						}
						
					}
				}
			} 
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
		
	}
	
	
	/**
	 * 获取规则为"自定义“自动转入类型为“转入动态组合”的树形信息
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 * @param ports
	 * @param assetsTree
	 * @return
	 */
	public HashMap<String, Port> getPortDataForDtcrMap(String ports,HashMap<String, String> assetsTree, boolean isAllPort) {
		HashMap<String, Port> dataMap = null;
		ResultSetTools rsTools = null;
		PortAssTreeSqlBuilder dsSqlBuilder = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		String cpsjwdStr = assetsTree.get("C_CPSJWD")== null ? "" : assetsTree.get("C_CPSJWD") ;
		String zcsxwdStr = assetsTree.get("C_ZCSXWD")== null ? "" : assetsTree.get("C_ZCSXWD") ;
		String cpsjwdFlcj = ("").equals(assetsTree.get("C_CPSJWD_FLCJ")) ? "1" : assetsTree.get("C_CPSJWD_FLCJ");
		String zcsxwdFlcj = ("").equals(assetsTree.get("C_ZCSXWD_FLCJ")) ? "2" : assetsTree.get("C_ZCSXWD_FLCJ");
		int index = 1;
		try{
			conn = this.loadNewConnection();
			dataMap = new HashMap<String, Port>();
			dsSqlBuilder = new PortAssTreeSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsSqlBuilder);
			sql = portSqlBuilder.getPortTreeViewDtcrSql(dbNameResolver,isAllPort);
			if("2".equals(cpsjwdFlcj) && "1".equals(zcsxwdFlcj)){
				sql += " ORDER BY A.C_DAT_CODE,A.C_DV_PROD_STATE,A.C_PORT_CODE ";
			}else{
				sql += "  ORDER BY A.C_DV_PROD_STATE,A.C_DAT_CODE,A.C_PORT_CODE ";
			}
			pstmt = conn.prepareStatement(sql);		
			if(!isAllPort){
				pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(ports,conn));
			}
			pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(zcsxwdStr,conn));
			pstmt.setArray(index++, OraDbTool.newInstance().sqlOverLongCondition(cpsjwdStr,conn));
			rs = pstmt.executeQuery();			
			/*将获取PropertyDescriptor提出到滚动之外，提高rs滚动效率*/
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = rsTools.ResultToBeanGeneric(rs, Port.class, props);
				if (t != null) {
					if("2".equals(cpsjwdFlcj) && "1".equals(zcsxwdFlcj)){
						
						//资产属性维度
						Port p1 = createPort();
						p1.setC_PORT_CODE_P("[root]");
						p1.setC_PORT_CODE("[root]"+rs.getString("C_DAT_CODE"));
						p1.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
						p1.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
						p1.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
						p1.setdATA_TYPE("ASS_TYPE");
						p1.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p1.getC_PORT_CODE())){
							dataMap.put(p1.getC_PORT_CODE(), p1);
						}
						
						//产品时间维度
						Port p2 = createPort();
						p2.setC_PORT_CODE_P("[root]"+rs.getString("C_DAT_CODE"));
						p2.setC_PORT_CODE("[root]"+rs.getString("C_DAT_CODE")+rs.getString("C_DV_CODE"));
						p2.setC_PORT_NAME_ST(rs.getString("C_DV_NAME"));
						p2.setC_PORT_NAME_EN(rs.getString("C_DV_NAME"));
						p2.setC_PORT_NAME(rs.getString("C_DV_NAME"));
						p2.setdATA_TYPE("CPSJ_TYPE");
						p2.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p2.getC_PORT_CODE())){
							dataMap.put(p2.getC_PORT_CODE(), p2);
						}
						
						//组合类型
						Port p3 = (Port) rsTools.ResultToBean(rs, Port.class, props);
						if(rs.getString("C_PORT_CODE_P") == null || "".equals(rs.getString("C_PORT_CODE_P").trim())){
							p3.setC_PORT_CODE_P("[root]"+rs.getString("C_DAT_CODE")+rs.getString("C_DV_CODE"));
						}else{
							p3.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
						}
						p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
						p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
						p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
						p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
						p3.setdATA_TYPE("PORT_TYPE");
						
						if(!dataMap.containsKey(p3.getC_PORT_CODE())){
							dataMap.put(p3.getC_PORT_CODE(), p3);
						}
						
					}else{
						//产品时间维度
						Port p1 = createPort();
						p1.setC_PORT_CODE_P("[root]");
						p1.setC_PORT_CODE("[root]"+rs.getString("C_DV_CODE"));
						p1.setC_PORT_NAME_ST(rs.getString("C_DV_NAME"));
						p1.setC_PORT_NAME_EN(rs.getString("C_DV_NAME"));
						p1.setC_PORT_NAME(rs.getString("C_DV_NAME"));
						p1.setdATA_TYPE("CPSJ_TYPE");
						p1.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p1.getC_PORT_CODE())){
							dataMap.put(p1.getC_PORT_CODE(), p1);
						}
						
						//资产属性维度
						Port p2 = createPort();
						p2.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_CODE"));
						p2.setC_PORT_CODE("[root]"+rs.getString("C_DV_CODE")+rs.getString("C_DAT_CODE"));
						p2.setC_PORT_NAME_ST(rs.getString("C_DAT_NAME"));
						p2.setC_PORT_NAME_EN(rs.getString("C_DAT_NAME"));
						p2.setC_PORT_NAME(rs.getString("C_DAT_NAME"));
						p2.setdATA_TYPE("ASS_TYPE");
						p2.setC_DAT_CLS("");
						
						if(!dataMap.containsKey(p2.getC_PORT_CODE())){
							dataMap.put(p2.getC_PORT_CODE(), p2);
						}
						//组合类型
						Port p3 = (Port) rsTools.ResultToBean(rs, Port.class, props);
						if(rs.getString("C_PORT_CODE_P") == null || "".equals(rs.getString("C_PORT_CODE_P").trim())){
							p3.setC_PORT_CODE_P("[root]"+rs.getString("C_DV_CODE")+rs.getString("C_DAT_CODE"));
						}else{
							p3.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
						}
						p3.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
						p3.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
						p3.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
						p3.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
						p3.setdATA_TYPE("PORT_TYPE");
						
						if(!dataMap.containsKey(p3.getC_PORT_CODE())){
							dataMap.put(p3.getC_PORT_CODE(), p3);
						}
						
					}
				}
			} 
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return dataMap;
		
	}
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 * 查询产品树形结构所有已审核的非明细的层级
	 * @return
	 * @throws Exception
	 */
	public List<AssetsTree_A> queryAssetTreeWithLeafNode() throws Exception {
		List<AssetsTree_A> list = new ArrayList<AssetsTree_A>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			AssetsTree_A assetsTree = new AssetsTree_A();
			assetsTree.setC_TR_CODE("ASS");
			assetsTree.setC_TR_NAME("资产类型");
			assetsTree.setC_TR_CODE_R("ASS");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("ZCTG");
            assetsTree.setC_TR_NAME("资产类型-托管行");
            assetsTree.setC_TR_CODE_R("ZCTG");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("ZCGL");
            assetsTree.setC_TR_NAME("资产类型-管理人");
            assetsTree.setC_TR_CODE_R("ZCGL");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("ZCWT");
            assetsTree.setC_TR_NAME("资产类型-委托人");
            assetsTree.setC_TR_CODE_R("ZCWT");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("ZCZL");
            assetsTree.setC_TR_NAME("资产类型-种类");
            assetsTree.setC_TR_CODE_R("ZCZL");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("TGZC");
            assetsTree.setC_TR_NAME("托管行-资产类型");
            assetsTree.setC_TR_CODE_R("TGZC");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("GLZC");
            assetsTree.setC_TR_NAME("管理人-资产类型");
            assetsTree.setC_TR_CODE_R("GLZC");
			assetsTree.setC_TR_CODE_P("[root]");
            list.add(assetsTree);
            assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("NSPL");
            assetsTree.setC_TR_NAME("纳税人类型-结转频率");
            assetsTree.setC_TR_CODE_R("NSPL");
			assetsTree.setC_TR_CODE_P("[root]");
			list.add(assetsTree);
			assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("ZCMXLX");
            assetsTree.setC_TR_NAME("资产类型-明细资产类型");
            assetsTree.setC_TR_CODE_R("ZCMXLX");
			assetsTree.setC_TR_CODE_P("[root]");
			list.add(assetsTree);
			assetsTree = new AssetsTree_A();
            assetsTree.setC_TR_CODE("CPZT");
            assetsTree.setC_TR_NAME("产品状态");
            assetsTree.setC_TR_CODE_R("CPZT");
			assetsTree.setC_TR_CODE_P("[root]");
			list.add(assetsTree);
            
			conn = this.loadNewConnection();
			String sql = portSqlBuilder.getQueryAssetTreeWithLeafNode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, AppContext.getInstance().getUserCode());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				assetsTree = new AssetsTree_A();
				assetsTree.setC_TR_CODE(rs.getString("C_TR_CODE"));
				assetsTree.setC_TR_NAME(rs.getString("C_TR_NAME"));
				assetsTree.setC_TR_CODE_R(rs.getString("C_TR_CODE_R"));
				assetsTree.setC_TR_CODE_P(rs.getString("C_TR_CODE_P"));
				list.add(assetsTree);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 * 查询树形结构节点所有组合信息
	 * @return
	 * @throws Exception
	 */
	public List<Port> getTreePortByCode(String trCode, String trCodeR) throws Exception {
		List<Port> list = new ArrayList<Port>();
		PreparedStatement pstmt = null;
		ResultSetTools rsTools = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			HashMap<String, Port> dataMap = null;
			if ("ASS".equals(trCodeR)) {
				String key = "NULL_TRCODE";
				dataMap = PortCache.getCachedTrCodeMap().get(key);
				if (dataMap == null) {
					dataMap = getPortDataMap();
					//synchronized (PortCache.getCachedTrCodeMap()) {
						PortCache.getCachedTrCodeMap().clear();
						PortCache.getCachedTrCodeMap().put(key, dataMap);
					//}
				} else {
					HashMap<String, Port> tssTypeMap = getPortDataMapOnlyWithTssType();
					if (tssTypeMap != null) {
						//synchronized (PortCache.getCachedTrCodeMap()) {
							PortCache.getCachedTrCodeMap().get(key).putAll(tssTypeMap);
						//}
					}
				}
			} else if ("ZCTG".equals(trCodeR)) {
				dataMap = getPortDataForZCTGMap();			
			} else if ("TGZC".equals(trCodeR)) {
				dataMap = getPortDataForTGZCMap();
			} else if ("GLZC".equals(trCodeR)) {
				dataMap = getPortDataForGLZCMap();
			} else if ("ZCGL".equals(trCodeR)) {
				dataMap = getPortDataForZCGLMap();
			} else if ("NSPL".equals(trCodeR)) {
				dataMap = getPortDataForNSPLMap();
			} else if ("ZCZL".equals(trCodeR)) {
				dataMap = getPortDataForZCZLMap();
			} else if ("ZCWT".equals(trCodeR)) {
				dataMap = getPortDataForZCWTMap();
			} else if ("TAQS".equals(trCodeR)) {
				dataMap = getPortNoClsDataMap();
			} else if ("CPZT".equals(trCodeR)) {
				dataMap = getPortDataForCPZTMap();
			} else if ("ZCMXLX".equals(trCodeR)) {
				dataMap = getPortDataForZCMXLXMap();
			}
			else {
				rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
				conn = this.loadNewConnection();
				String sql = portSqlBuilder.getTreePortByCode(trCode, trCodeR);
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, trCodeR);
				if (!trCode.equals(trCodeR)) {
					pstmt.setString(2, trCode);
				}
				rs = pstmt.executeQuery();
				//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
				PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
				while (rs.next()) {
					Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
					t.setdATA_TYPE(rs.getString("DATA_TYPE"));
					list.add(t);
				}
			}
			
			if (dataMap != null && !dataMap.isEmpty()) {
				Set<String> postSet = new HashSet<String>();
				for (Port port : dataMap.values()) {
					String portCode = port.getC_PORT_CODE();
					String portCodeP = port.getC_PORT_CODE_P();
					if (!"[root]".equals(portCodeP)) {
						if (port.getAuditState() == 0) {
							continue;
						}
						if (!PortDataType._Port.toString().equals(port.getdATA_TYPE())) {
							continue;
						}
						if (postSet.contains(portCode)) {
							continue;
						}
						postSet.add(portCode);
						list.add(port);
					}
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据业务类型获取没有关联此业务的组合
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> getAutomaticSetPortAdd(HashMap<String, Object> paraMap, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getAutomaticSetPortAdd();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, paraMap.get("C_BUSINESS_TYPE_CODE").toString());
			pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("C_BUSINESS_CODE").toString(), conn));
			pstmt.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(paraMap.get("C_PORT_CODE").toString(), conn));
			rs = pstmt.executeQuery();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			//BUG #358407 【性能测试】0831版本，流程管理-流程参数设置很卡，请分析优化
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(clazz.newInstance());
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz, props);
				((Port) t).setdATA_TYPE(rs.getString("DATA_TYPE"));
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
	 * STORY #75531 【广发基金】支持美元本位币记账组合的人民币转换和核对的需求 (#2 #1 ) 
	 * 查询并行组合
	 * @author zengguowei
	 * @since 2019-07-18
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> querySourcesBxzhViewDataPojos(HashMap<String, Object> paraMap) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;
		ResultSetTools rsTools = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = portSqlBuilder.getSourcesBxzhViewQueryPojosSql(paraNameList);
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			pstmt = conn.prepareStatement(sql);
			//logger.debug(sql);
			
			rs = pstmt.executeQuery();
			
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(Port.class.newInstance());
			while (rs.next()) {
				Port t = (Port) rsTools.ResultToBean(rs, Port.class, props);
				t.setdATA_TYPE(rs.getString("DATA_TYPE"));
				pojoList.add(t);
			}

		} catch (Exception ex) {
			//ex.printStackTrace();
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}

	/**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * 20210821 add bu pya 
	 * @param portCode
	 * @param dcCode
	 * @return
	 */
	public String getCacodeByAccountType(String portCode, String dcCode) {
		String caCode = "";
		//账户list
		List<String> caCodeList = new ArrayList<String>();
		//组合数组
		String[] port;
		Connection conn = null;
		PreparedStatement pst = null;
		PreparedStatement pst1 = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		//多组合插入到数组中
		portCode = portCode.replace("|", ",");
		port = portCode.split(",");
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			String sql = " INSERT INTO R_D_CLR_PORT C_PORT_CODE VALUES(?) " ;
			pst1 = conn.prepareStatement(sql);
			for(String port1 :port) {
				//若组合为空，直接跳出循环，并不插入到临时表
				if(port1.isEmpty()) {
					break;
				}
			    pst1.setString(1, port1);
			    pst1.addBatch();
			}
			pst1.executeBatch();
			pst1.clearBatch();
			StringUtil.clearStringBuffer(buf);
			buf.append(" SELECT B.C_CA_CODE AS C_CA_CODE FROM T_P_AB_PORT_RELA A ");
			buf.append(" LEFT JOIN T_P_BI_CASH_ACC B ON A.C_RELA_CODE = B.C_CA_CODE ");
			buf.append(" LEFT JOIN T_P_AB_PORT C ON A.C_PORT_CODE = C.C_PORT_CODE  ");
			buf.append(" WHERE  B.C_DV_ACC_TYPE = 'ACC_SAV'AND B.C_DV_ACC_NATURE = 'NA_INT' ");
			buf.append(" AND C.C_DAT_CODE != 'ASS_QDII' ");
			buf.append(" AND B.N_CHECK_STATE = 1 AND C.N_CHECK_STATE = 1 AND A.N_CHECK_STATE = 1 ");
			buf.append(" AND A.C_PORT_CODE IN(SELECT C_PORT_CODE FROM R_D_CLR_PORT) AND B.C_DC_CODE = ? ");
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1, dcCode);
			rs = pst.executeQuery();
			while(rs.next()){
				String value = rs.getString("C_CA_CODE");
				caCodeList.add(value);
			}
			StringUtil.clearStringBuffer(buf);
			//匹配不到 或 匹配多条，返回空。
			if(caCodeList.size() == 1) {
				caCode = caCodeList.get(0);
			}
			conn.setAutoCommit(true);
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst,pst1);
			this.releaseConnection(conn);
		}
		return caCode;
	}

	/**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * 20210824
	 * 修改时，控件上并没有组合相关值，需要根据名称，账号，开户行关联查询
	 * @param portCode
	 * @param dcCode
	 * @return
	 */
	public String getCacodeByAccountType1(String openName, String openNo, String openAddr, String dcCode) {
		String caCode = "";
		//账户list
	    List<String> caCodeList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer buf = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			buf.append(" SELECT B.C_CA_CODE AS C_CA_CODE FROM T_P_AB_PORT_RELA A ");
			buf.append(" LEFT JOIN T_P_BI_CASH_ACC B ON A.C_RELA_CODE = B.C_CA_CODE ");
			buf.append(" LEFT JOIN T_P_AB_PORT C ON A.C_PORT_CODE = C.C_PORT_CODE ");
			buf.append(" WHERE  B.C_DV_ACC_TYPE = 'ACC_SAV'AND B.C_DV_ACC_NATURE = 'NA_INT' ");
			buf.append(" AND C.C_DAT_CODE != 'ASS_QDII' ");
			buf.append(" AND B.N_CHECK_STATE = 1 AND C.N_CHECK_STATE = 1 AND A.N_CHECK_STATE = 1  ");
			buf.append(" AND B.C_DC_CODE = ? ");
			buf.append(" AND A.C_PORT_CODE IN (SELECT B.C_PORT_CODE FROM T_P_BI_FUND_ACC A  ");
			buf.append(" LEFT JOIN T_P_AB_PORT_ACC_RELA B  ");
			buf.append(" ON A.C_IDEN = B.C_RELA_CODE ");
			buf.append(" WHERE A.C_OPEN_ACC_NAME = ? AND A.C_OPEN_ACC_NO = ? AND A.C_OPEN_ADDR = ?) ");
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1, dcCode);
			pst.setString(2, openName);
			pst.setString(3, openNo);
			pst.setString(4, openAddr);
			rs = pst.executeQuery();
			while(rs.next()) {
				String value = rs.getString("C_CA_CODE");
				caCodeList.add(value);
			}
			//匹配不到 或 匹配多条，返回空。
			if(caCodeList.size() == 1) {
				caCode = caCodeList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return caCode;
	}
	
}
