package com.yss.ams.base.information.modules.sys.automaticSet.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 自动化业务设置dao层
 * @ClassName: AutomaticSetDao
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
public class AutomaticSetDao extends GeneralDao {

	AutomaticSetSqlBuilder sqlBuilder = null; 
	
	public AutomaticSetDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (AutomaticSetSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 根据组合代码查询业务类型code
	 * @param portCode
	 * @return
	 */
	public Map<String, List<String>> queryBusiCodeByPortCode(String portCode) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryBusiCodeByPortCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				String type = rs.getString("C_BUSINESS_TYPE_CODE");
				if (!map.containsKey(type)) {
					map.put(type, new ArrayList<String>());
				}
				map.get(type).add(rs.getString("C_BUSINESS_CODE"));
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return map;
	}
	
	/**
	 * 根据组合查询业务类型和组合对应关系
	 * @param type
	 * @param portCodes
	 * @return
	 */
	public Map<String, List<String>> queryBusiCodeByPortCodes(String type, List<String> portCodes) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryBusiCodeByPortCodes();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			pst.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(portCodes.toArray(new String[0]), conn));
			rs = pst.executeQuery();
			while (rs.next()) {
				String portCode = rs.getString("C_PORT_CODE");
				if (!map.containsKey(portCode)) {
					map.put(portCode, new ArrayList<String>());
				}
				map.get(portCode).add(rs.getString("C_BUSINESS_CODE"));
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return map;
	}
	
	/**
	 * 根据产品代码，业务范围查询产品业务范围审核状态
	 * @param portCode
	 * @param busiCode
	 * @return
	 */
	public boolean queryCheckStatusByPortCodeAndBusiCode(String portCode, String type, String busiCode) {
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.queryCheckStatusByPortCodeAndBusiCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			pst.setString(2, type);
			pst.setString(3, busiCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				int count = rs.getInt("CNUM");
				if (count > 0) {
					return true;
				}
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return false;
	}
	
	/**
	 * 根据业务类型代码获取组合集合
	 * @param type
	 * @param busiCode
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getPortListByBusiCode(String type, String busiCode) throws ServiceException {
		List<String> list = new ArrayList<String>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getPortListByBusiCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			pst.setString(2, busiCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("C_PORT_CODE"));
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 根据业务类型和组合代码获取明细业务类型集合
	 * @param type
	 * @param busiCode
	 * @return
	 * @throws ServiceException
	 */
	public List<Map<String, String>> getBusiInfoByCode(String type, String portCode) throws ServiceException {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getBusiInfoByCodeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			pst.setString(2, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				Map<String, String> map = new HashMap<String, String>();
				map.put("C_BUSINESS_CODE", rs.getString("C_BUSINESS_CODE"));
				map.put("C_BUSINESS_NAME", rs.getString("C_BUSINESS_NAME"));
				list.add(map);
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 获取所有业务类型数据
	 * @return
	 * @throws ServiceException
	 */
	public List<Vocabulary> getDataTypeList() throws ServiceException {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getDataTypeListSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setC_DV_CODE(rs.getString("C_BUSINESS_TYPE_CODE"));
				vocabulary.setC_DV_NAME(rs.getString("C_BUSINESS_TYPE_NAME"));
				list.add(vocabulary);
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 获取明细类型数据
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<Vocabulary> getDataListByType(String type) throws ServiceException {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = sqlBuilder.getDataListByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			rs = pst.executeQuery();
			while (rs.next()) {
				Vocabulary vocabulary = new Vocabulary();
				vocabulary.setC_DV_CODE(rs.getString("C_BUSINESS_CODE"));
				vocabulary.setC_DV_NAME(rs.getString("C_BUSINESS_NAME"));
				list.add(vocabulary);
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * 更新业务类型数据
	 * @param type
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateDataList(String type, HashMap<String, String> paraMap) throws ServiceException {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			String[] types = type.split("#");
			String typeCode = types[0];
			String typeName = types[1];
			
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 删除数据
			sql = sqlBuilder.getDeleteDataByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, typeCode);
			pst.executeUpdate();
			this.closeStatementFinal(pst);
			// 插入数据
			String userCode = ContextFactory.getContext().getUserCode();
			sql = sqlBuilder.getInsertDataListSql();
			pst = conn.prepareStatement(sql);
			for (int i = 0; i < paraMap.size(); i++) {
				String vocabularyStr = paraMap.get(String.valueOf(i));
				if (vocabularyStr != null && vocabularyStr.contains("#")) {
					int index = vocabularyStr.indexOf("#");
					String businessCode = vocabularyStr.substring(0, index);
					String businessName = vocabularyStr.substring(index + 1);
					if (!"".equals(businessCode.trim()) && !"".equals(businessName.trim())) {
						pst.setInt(1, i);
						pst.setString(2, typeCode);
						pst.setString(3, typeName);
						pst.setString(4, businessCode);
	            		pst.setString(5, businessName);
	            		pst.setString(6, userCode);
	            		pst.addBatch();
					}
				}
			}
			pst.executeBatch();
            conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}
	
	/**
	 * 更新业务设置数据
	 * @param type
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	public boolean updateSetDataList(String type, HashMap<String, Object> paraMap) throws ServiceException {
		PreparedStatement pst = null;
		Connection conn = null;
		boolean ret = false;
		String sql = "";
		try {
			String[] portCodes = null;
			if (paraMap.containsKey("ALL_C_PORT_CODE")) {
				String allPortCodes = (String) paraMap.get("ALL_C_PORT_CODE");
				portCodes = allPortCodes.split(",");
			}
			
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			// 删除数据
			sql = sqlBuilder.getDeleteSetDataByTypeSql();
			pst = conn.prepareStatement(sql);
			pst.setString(1, type);
			pst.executeUpdate();
			this.closeStatementFinal(pst);
			// 插入数据
			if ("ZRKJ_TYPE".equals(type) && portCodes != null && portCodes.length > 0) {
				sql = sqlBuilder.getInsertSetDataListSql();
				pst = conn.prepareStatement(sql);
				for (String portCode : portCodes) {
					pst.setString(1, "T" + portCode);
					pst.setString(2, type);
					pst.setString(3, portCode);
					pst.setString(4, type);
					pst.setString(5, portCode);
	        		pst.addBatch();
				}
				pst.executeBatch();
			}
            conn.commit();
			conn.setAutoCommit(true);
			ret = true;
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return ret;
	}
}
