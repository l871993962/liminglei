package com.yss.ams.base.information.modules.bi.mkt.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;

/**
 * 
 * @author li
 * 仅用于 将所属市场 的代码转中文  by lihaizhi 20130625
 */

/**
 * 
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class MktVarDao extends GeneralDao {

	public MktVarDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 获取市场代码代码对应中文转换
	 * @param 
	 * @return HashMap
	 */
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MktSqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new MktSqlBuilder();
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getDataName();
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				keyValueMap.put(code, name);
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
	 * 获取多个市场代码代码对应中文转换
	 * @param listKey
	 * @return HashMap
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		MktSqlBuilder dsServiceBuilder = null;
		try {
			String[] strArr = new String[listKey.size()];
			dsServiceBuilder = new MktSqlBuilder();

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getAllDataSqlByKeys();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(strArr,conn));
			logger.debug(sql);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String code = rs.getString(1);
				String name = rs.getString(2);
				keyValueMap.put(code, name);
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
	
	/* END 数据服务方法 */
}
