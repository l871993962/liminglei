package com.yss.ams.product.information.modules.aa.portcls.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.platform.support.dataservice.service.IRightManageDataService;

/**
 * 分级产品DAO层
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
public class PortClsDao extends GeneralDao {

	PortClsSqlBuilder sqlbuilder = null;
	
	public PortClsDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		sqlbuilder = (PortClsSqlBuilder)sqlBuilder;
	}

	/* START 数据服务方法 */
	public List<PortCls> getAllDataList() throws ServiceException {
		List<PortCls> pojoList = new ArrayList<PortCls>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
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

	public PortCls getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		String[] paramArr = null;
		try {
			paramArr = code.split("/t");
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramArr[0]);
			pstmt.setString(2, paramArr[1]);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
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

	public List<PortCls> getDataListByTypes(String[] types) throws ServiceException {
		List<PortCls> pojoList = new ArrayList<PortCls>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
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

	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
				keyValueMap.put(t.getC_PORT_CLS_CODE(), t.getC_PORT_CLS_NAME());
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
	 * @Title STORY36440【南方基金】增加参数控制，当分级产品某一级别全部赎回后，被赎回的分级单位净值直接取其他级别 
	 * @Description 获取所选分组下的所有分级组合+‘系统默认算法’
	 * @author zhaijiajia@ysstech.com
	 * @date 2016年12月8日上午11:08:11
	 * @param types
	 * @return
	 * @throws ServiceException
	 * @return List<K>
	 */
	public List<PortCls> getDataListByPorts(String[] types) throws ServiceException {
		List<PortCls> pojoList = new ArrayList<PortCls>();
		StringBuffer buf = new StringBuffer();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();
			buf.append(sql);
			// 参数值下拉框可以选择所属组合下的分级组合+'系统默认算法'
			buf.append(" UNION ALL SELECT ? as C_PORT_CODE, ");
			buf.append(" 'default' as C_PORT_CLS_CODE,'系统默认算法' as C_PORT_CLS_NAME, ");
			buf.append(" ' ' C_DV_PORT_CLS_TYPE,' ' C_DV_PORT_CLS_LEVEL, ");
			buf.append(" ' ' C_DV_PORT_CLS, ' ' C_DC_CODE,");
			buf.append(" to_date('1900-01-01','YYYY/MM/DD') D_TO_LIST ,to_date('1900-01-01','YYYY/MM/DD') D_OFF_LIST, ");
			buf.append(" ' ' C_ALGO_CODE,'' C_DESC,1 N_CHECK_STATE, ");
			buf.append(" 'user' C_UPDATE_BY,'user' C_CHECK_BY,to_char(sysdate,'yyyyMMdd HH:mm:ss') C_UPDATE_TIME , ");
			buf.append(" to_char(sysdate,'yyyyMMdd HH:mm:ss') C_CHECK_TIME,'[root]' C_PORT_CLS_CODE_P, ");
			buf.append(" ' ' C_DV_NETTING,' ' C_DV_INC_DIS,' ' C_IDEN, ");
			buf.append(" ' ' C_ALGO_CODE_I,0 N_YEAR_INCOME,' ' C_INCOME_TYPE,' ' C_FORMULA_CODE,date'9998-12-31' as D_LIQUID_DATE ");
			buf.append(" FROM DUAL ");

			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));
			pstmt.setString(2, types[0].toString());
			/*for(String portCode : types) {
				pstmt.setString(2, portCode);
				break;
			}*/
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
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

	public List<PortCls> getDataListByKeys(String[] keys) throws ServiceException {
		List<PortCls> pojoList = new ArrayList<PortCls>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = sqlbuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
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

	public String getPortClsCode(String code) throws DataAccessException{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();

			sql = sqlbuilder.getPortClsCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return t != null ? t.getC_PORT_CLS_CODE() : "";
	}

	/* END 数据服务方法 */
	
	/**
	 * 用于更新
	 * 新产品起息确认后 分级产品参数的截止日期改为 起息截止日期
	 * @param portCode
	 * @param dueDate
	 */
	public void updateDueDate(String portCode,String dueDate){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
//		PortClsSqlBuilder sqlBuilder = null;
		
		try {
//			sqlBuilder = new PortClsSqlBuilder();
			conn = this.loadNewConnection();
			
			sql = sqlbuilder.getUpdateDateSql();
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, dueDate);
			pstmt.setString(2, portCode);
			
			logger.debug(sql);
			
			int num = pstmt.executeUpdate();
			
			logger.debug("成功更新数据" + num + "条");
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("分级产品参数功能模块:更新分级产品的到期日期", e);
		}finally{
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	
	/**
	 * 根据组合code 和分级组合类型 获得对应的分级组合code By Jinghehe 2014-1-7
	 * @param portCode
	 * @param portClsType
	 * @return
	 */
	public String getPortClsCode(String portCode, String portClsType){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		String portClsCode = "";
//		PortClsSqlBuilder sqlBuilder = null;
		ResultSet rs = null;
		try {
//			sqlBuilder = new PortClsSqlBuilder();
			conn = this.loadNewConnection();
			
			sql = sqlbuilder.getPortClsCodeByCond();
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, portCode);
			pstmt.setString(2, portClsType);
			
			logger.debug(sql);
			
			rs = pstmt.executeQuery();

			if (rs.next()) {
				portClsCode = rs.getString("C_PORT_CLS_CODE");
			}
		
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("分级产品参数功能模块:根据组合代码及组合类型查询分级产品参数出错", e);
		}finally{
			this.closeResultSetFinal(rs);//addbyleeyu20151015
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return portClsCode;
	}
	
	public List<PortCls> getPortClsByUser(String userCode)
			throws DataAccessException {
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
//		PortClsSqlBuilder sqlBuilder = null;
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
//			sqlBuilder = new PortClsSqlBuilder();
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.getPortClsByUser();
			pstmt = connection.prepareStatement(sqlString);
			
	        IFASTDataAuthorityService fda = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
            List<String> portList = fda.queryByUser("1", userCode);
            String[] portCodes = new String[portList.size()];
            portCodes = portList.toArray(portCodes);
			pstmt.setArray(1, this.sqlOverLongCondition(portCodes, connection));
			//pstmt.setString(1, userCode);

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new PortCls());
			while (rSet.next()) {
			    portCls = resultSetTools.ResultToBeanGeneric(rSet, PortCls.class, props);
//				portCls = resultSetTools.ResultToBeanGeneric(rSet,
//						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return dataList;
	}

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

//		PortClsSqlBuilder dsServiceBuilder = null;
		PortCls t = null;
		try {
//			dsServiceBuilder = new PortClsSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			conn = this.loadNewConnection();

			sql = sqlbuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, timestamp);

			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new PortCls());
			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, PortCls.class, props);
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
	
	public BasePojo getPojoByCode(String pojoCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSetTools rsTools = null;
//		PortClsSqlBuilder sqlBuilder = null;
		ResultSet rs = null;
		BasePojo pojo = new BasePojo();
		try {
//			sqlBuilder = new PortClsSqlBuilder();
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sql = sqlbuilder.getPojoByCode();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, pojoCode);
			logger.debug(sql);
			
			rs = pstmt.executeQuery();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(new PortCls());
			if (rs.next()) {
				pojo = rsTools.ResultToBeanGeneric(rs, PortCls.class, props);
			}
		
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("分级产品参数功能模块:根据组合代码查询分级产品参数出错", e);
		}finally{
			this.closeResultSetFinal(rs);//addbyleeyu20151015
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return pojo;
	}

	/**
	 * 根据组合代码和级别类型获取分级代码
	 * 
	 * @author liuxiang
	 * @date 2016-9-1 STORY #28429 【广发证券】TA净值表导出设置中导出级别的优化
	 * @param portCode
	 *            组合代码
	 * @param types
	 *            级别类型(多个类型以逗号分割)
	 * @return 分级代码(以逗号分割)
	 */
	public String getClsCodesByPortCodeAndType(String portCode, String types) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String result = "";
		try {
			conn = this.loadNewConnection();
			String sql = ((PortClsSqlBuilder) sqlbuilder)
					.getClsCodesByPortCodeAndType();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			pstmt.setArray(2,
					OraDbTool.newInstance().sqlOverLongCondition(types, conn));
			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getString("C_PORT_CLS_CODE");
			}

			// 是否包含母级别
			if (types.contains("PORT_CLS")) {
				if (!StringUtil.IsNullOrEmptyT(result)) {
					result = result + "," + portCode;
				} else {
					result = portCode;
				}
			}
		} catch (Exception ex) {
			logger.log("查询分级代码出错", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return result;
	}
	
	/**
	 * 校验日期是否重叠
	 * add by Yuntao Lau 2015.12.19 BUG #123015
	 * @param paraMap 条件
	 * @return 日期是否重叠
	 */
	public String checkDate(HashMap<String, String> paraMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		String isOverLap = "false";
		try {
			String id = paraMap.get("ID");
			conn = this.loadNewConnection();
			sql = sqlbuilder.checkDate(id);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, paraMap.get("portCode"));
			pstmt.setString(i++, paraMap.get("portCodeCls"));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("foundDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("foundDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("expiryDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("expiryDate")));			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isOverLap = "true";
			}
		
		} catch (Exception e) {
			throw new BusinessException("检查日期是否重叠失败!", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return isOverLap;
	}	

	/**
	 * 校验日期是否重叠
	 * add by xuhanbing 2016.12.7 
	 * STORY #35787 海通资管 赢财升鑫产品的 每年基准收益率参数优化
	 * @param paraMap 条件
	 * @return 日期是否重叠
	 */
	public String checkDateQSRQ(HashMap<String, String> paraMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		ResultSet rs = null;
		String isOverLap = "false";
		try {
			String id = paraMap.get("ID");
			conn = this.loadNewConnection();
			sql = ((PortClsSqlBuilder)sqlbuilder).checkDateQSRQ(id);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, paraMap.get("portCode"));
			pstmt.setString(i++, paraMap.get("portCodeCls"));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("foundDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("foundDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("expiryDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("expiryDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("foundDate")));
			pstmt.setDate(i++, YssFun.toSqlDate(paraMap.get("expiryDate")));
			
			rs = pstmt.executeQuery();
			if (rs.next()) {
				isOverLap = "true";
			}
		
		} catch (Exception ex) {
//			e.printStackTrace();
			logger.log("查询分级代码失败",ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return isOverLap;
	}
	
	/**
	 * 根据组合日期返回对应的分级组合
	 * @author shiliang
	 * @date 2017-6-14
	 * @param PortCode 组合代码
	 * @param dueDate 日期
	 * @return
	 */
	public List<PortCls> getPortClsByDate(String PortCode, Date dueDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.getPortClsByDate();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, PortCode);
			pstmt.setString(2, YssFun.formatDate(dueDate));
			pstmt.setString(3, YssFun.formatDate(dueDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public PortCls queryPortCls(String portCode, String classPort, Date actDate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortCls();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, classPort);
			pstmt.setString(3, YssFun.formatDate(actDate));
			
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
	}
	
	public PortCls queryPortCls(String portCode, String classPort){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsNotDate();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, classPort);
			
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
	}
	
	public List<PortCls> portClsRecords(Date actDate, String port){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.getportClsRecords();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, YssFun.formatDate(actDate));
			pstmt.setString(2, port);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public PortCls queryPortCls_Date(String portCode, String classPort, Date actDate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPortCls_Date();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, classPort);
			pstmt.setString(3, YssFun.formatDate(actDate));

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
		
	}
	
	public List<PortCls> queryPortCls(String portCode){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortCls_Port();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public PortCls queryPortClsByTypeAndClass(String portCode,String portClsType, String classPort){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPortClsByTypeAndClass();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portClsType);
			pstmt.setString(2, classPort);
			pstmt.setString(3, portCode);

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
		
	}
	
	public List<PortCls> queryPortClsByClass(String portCode,Date actDate, String classPort){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByClass();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, YssFun.formatDate(actDate));
			pstmt.setString(2, classPort);
			pstmt.setString(3, portCode);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortCls(String portCode, String portClsType,String portClsLevel){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByLevel();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portClsType);
			pstmt.setString(2, portClsLevel);
			pstmt.setString(3, portCode);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortClsMx(String portCode){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsMx();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, portCode);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortClsList(String portCode){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsList();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, portCode);
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public PortCls queryPortClsYxq(String portCode, String classPort, Date actDate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPortClsYxq();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, YssFun.formatDate(actDate));
			pstmt.setString(3, classPort);

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
		
	}
	
	public List<PortCls> queryPortCls(String port, Date accDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortCls_Port_Date();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, port);
			pstmt.setString(2, YssFun.formatDate(accDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortClsByLiquid(String port, Date accDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByLiquid();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, port);
			pstmt.setString(2, YssFun.formatDate(accDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public PortCls queryPreviousPortCls(String portCode, String classPort, Date actDate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPreviousPortCls();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, portCode);
			pstmt.setString(3, classPort);
			pstmt.setString(4, YssFun.formatDate(actDate));
			pstmt.setString(5, classPort);

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
		
	}
	
	public List<PortCls> queryPortClsByDvCls(String portCode,String dvCls,Date actDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByDvCls();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, dvCls);
			pstmt.setString(3, YssFun.formatDate(actDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortClsByDvCls(String portCode, Date actDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByDvCls_Port_Date();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, YssFun.formatDate(actDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
		
	}
	
	public List<PortCls> queryPortClsByDvClsAndDate(String portCode,String clsLevel, Date actDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryPortClsByDvClsAndDate();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, clsLevel);
			pstmt.setString(3, YssFun.formatDate(actDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
	}
	
	public PortCls queryPortClsByClsLevel(String portCode,String clsLevel, Date actDate){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPortClsByClsLevel();
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, clsLevel);
			pstmt.setString(3, YssFun.formatDate(actDate));

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
	}

	public PortCls queryPortClsSort(String portCode, Date actDate, boolean sort){
		Connection connection = null;
		PreparedStatement pstmt = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		PortCls portCls1 = null;
		List<PortCls> dataList = new ArrayList<PortCls>();
		try {
			connection = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			sqlString = sqlbuilder.queryPortClsSort(sort);
			pstmt = connection.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, YssFun.formatDate(actDate));

			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls1 = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls1);
			}
			if (dataList != null && dataList.size() != 0) {
				portCls = dataList.get(0);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rSet);//addbyleeyu20151015
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(connection); //addbyleeyu20151015
		}

		return portCls;
		
	}
	
	public List<PortCls> queryFjSyfpInfo(String portCode, Date actDate){
		PreparedStatement pstmt = null;
		Connection conn = null;
		String sqlString = "";
		ResultSet rSet = null;
		ResultSetTools resultSetTools = null;
		PortCls portCls = null;
		List<PortCls> dataList = new ArrayList<PortCls>();	
		try {
			// connection = this.loadNewConnection();
			conn = this.loadNewConnection();
			resultSetTools = new ResultSetTools(dbNameResolver, sqlbuilder);

			sqlString = sqlbuilder.queryFjSyfpInfo();
			pstmt = conn.prepareStatement(sqlString);
			pstmt.setString(1, portCode);
			pstmt.setString(2, YssFun.formatDate(actDate));
			pstmt.setString(3, YssFun.formatDate(actDate));
			logger.debug(sqlString);

			rSet = pstmt.executeQuery();
			while (rSet.next()) {
				portCls = resultSetTools.ResultToBeanGeneric(rSet,
						PortCls.class);
				dataList.add(portCls);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rSet);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return dataList;
	}

}
