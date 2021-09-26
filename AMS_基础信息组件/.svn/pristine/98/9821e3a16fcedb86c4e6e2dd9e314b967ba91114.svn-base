package com.yss.ams.base.information.modules.bi.salesnet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.imp.api.CodeNameInfo;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;

/**
 * 销售网点设置dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class SalesNetDao extends GeneralDao {

	private SalesNetSqlBuilder salesNetSqlBuilder;
	
	public SalesNetDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		salesNetSqlBuilder = (SalesNetSqlBuilder)sqlBuilder;
	}
	
	/**
	 * 查询所有已审核的销售网点的信息
	 * @param paraDict
	 * @return
	 */
	public List<BasePojo> queryPdNet(HashMap<String, String> paraDict) {
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		SalesNet pojo = null;
		Statement ptmt = null;
		Connection conn = null;
		String sql = "";
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			pojo = SalesNet.class.newInstance();
			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);			
			sql = salesNetSqlBuilder.getPdNetSql();
			ptmt = conn.createStatement();
			rs = ptmt.executeQuery(sql);
			while (rs.next()) {
				pojo = (SalesNet) rsTools.ResultToBean(rs, SalesNet.class);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询销售网点失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}

		return dataList;	
	}

	/* START 数据服务方法 */
	/**
	 * 获取所有销售网点设置数据
	 * @return
	 * @throws ServiceException
	 */
	public List<SalesNet> getAllDataList() throws ServiceException {
		List<SalesNet> pojoList = new ArrayList<SalesNet>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
		try {
			dsServiceBuilder = new SalesNetSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
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
	 * 根据销售网点代码获取销售网点设置
	 * @param code
	 * @return
	 * @throws ServiceException
	 */
	public SalesNet getDataByCode(String code) throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
//		String[] paramArr = null;
		try {
//			paramArr = code.split("\t");
			dsServiceBuilder = new SalesNetSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
//			pstmt.setString(2, paramArr[1]);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
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
	 * 根据销售网点类型获取销售网点设置
	 * @param types
	 * @return
	 * @throws ServiceException
	 */
	public List<SalesNet> getDataListByTypes(String[] types)
			throws ServiceException {
		List<SalesNet> pojoList = new ArrayList<SalesNet>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
		try {
			dsServiceBuilder = new SalesNetSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,types[0]);
			pstmt.setString(2,types[1]);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
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
	 * 获取销售网点代码转换MAP
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
		try {
			dsServiceBuilder = new SalesNetSqlBuilder();
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
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
				keyValueMap.put(t.getC_NET_CODE(), t.getC_NET_NAME());
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
	 * 根据销售网点代码集获取销售网点代码转换MAP
	 * @param listKey
	 * @return
	 * @throws ServiceException
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
		try {
			String[] strArr = new String[listKey.size()];
			dsServiceBuilder = new SalesNetSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSqlByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
				keyValueMap.put(t.getC_NET_CODE(), t.getC_NET_NAME());
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
	 * 根据多个销售网点代码获取销售网点设置list
	 * @param keys
	 * @return
	 * @throws ServiceException
	 */
	public List<SalesNet> getDataListByKeys(String[] keys)
			throws ServiceException {
		List<SalesNet> pojoList = new ArrayList<SalesNet>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		SalesNetSqlBuilder dsServiceBuilder = null;
		SalesNet t = null;
		try {
			dsServiceBuilder = new SalesNetSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,keys[0]);
			pstmt.setString(2,keys[1]);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, SalesNet.class);
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
	
	/**
	 * 获取所有销售网点代码和销售网点名称结果集
	 * @return
	 * @throws ServiceException
	 */
	public List<SalesNet> getAllShortDataList() throws ServiceException {
		List<SalesNet> pojoList = new ArrayList<SalesNet>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		SalesNet t = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = salesNetSqlBuilder.getAllShortDataListSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = new SalesNet();
				t.setC_NET_CODE(rs.getString("C_NET_CODE"));
				t.setC_NET_NAME(rs.getString("C_NET_NAME"));
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
	 * 获取所有销售网点代码和销售网点名称MAP
	 * @return
	 */
	public HashMap<String, String> getShortDataMap() {
		HashMap<String, String> map= new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = salesNetSqlBuilder.getAllShortDataListSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				map.put(rs.getString("C_NET_CODE"), rs.getString("C_NET_NAME"));
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

	public SalesNet getSalesNetByCode(String code) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SalesNet salesNet = null;
		try {
			conn = this.loadNewConnection();
			sql = "select a.*  from T_P_BI_SALES_NET a  where   a.C_NET_CODE = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				salesNet = new SalesNet();
				salesNet.setId(rs.getString("c_iden"));
				salesNet.setC_NET_NAME(rs.getString("c_net_name"));
				salesNet.setC_VENDOR_CODE(rs.getString("c_VENDOR_CODE"));
				salesNet.setC_CHANNEL_TYPE(rs.getString("c_CHANNEL_TYPE"));
			}
		} catch (Exception e) {
			logger.log("查询失败:" + sql);
			throw new BusinessException("查询失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return salesNet;
	}

	public HashMap<String, String> getAllNetCodeAndName() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			conn = this.loadNewConnection();
			sql = "select c_net_code,c_net_Name  from T_P_BI_SALES_NET";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("c_net_code"), rs.getString("c_net_Name"));
			}
		} catch (Exception e) {
			logger.log("查询失败:" + sql);
			throw new BusinessException("查询失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	public SalesNet getSalesNetByVendorCode(String vendorCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SalesNet salesNet = null;
		try {
			conn = this.loadNewConnection();
			sql = "select c_net_code,c_net_Name,c_iden  from T_P_BI_SALES_NET n where n.c_VENDOR_CODE=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vendorCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				salesNet = new SalesNet();
				salesNet.setC_NET_CODE(rs.getString("c_net_code"));
				salesNet.setC_NET_NAME(rs.getString("c_net_Name"));
				salesNet.setId(rs.getString("c_iden"));
			}
		} catch (Exception e) {
			logger.log("查询失败:" + sql);
			throw new BusinessException("查询失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return salesNet;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2017-12-29
	 * Status : Add
	 * Comment: 根据销售商代码删除网点信息失败
	 * @param vendorCodes 销售商代码集合
	 * @param conn
	 */
	public void deleteNetInfoByVendorCode(String[] vendorCodes,Connection conn){
		PreparedStatement pstmt = null;
		try{
			String sql = "delete from t_p_bi_sales_net where c_vendor_code in (select * from table(?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, sqlOverLongCondition(vendorCodes, conn));
			pstmt.executeUpdate();
		}catch(Exception ex){
			throw new BusinessException("根据销售商代码删除网点信息失败!", ex);
		}finally{
			this.closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 
	 * @Title getSaleNetCodeNameInfo 
	 * @Description 获取网点的代码名称
	 * @author lixiang@ysstech.com
	 * @date 2018年5月31日下午2:11:37
	 * @return
	 * @throws ServiceException
	 * @return List<CodeNameInfo>
	 */
	public List<CodeNameInfo> getSaleNetCodeNameInfo() throws ServiceException {
		List<CodeNameInfo> pojoList = new ArrayList<CodeNameInfo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		SalesNetSqlBuilder dsServiceBuilder = null;
		CodeNameInfo t = null;
		try {
			dsServiceBuilder = new SalesNetSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = new CodeNameInfo();
				t.setId("C_IDEN");
				t.setCode(rs.getString("C_NET_CODE"));
				t.setName(rs.getString("C_NET_NAME"));
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
