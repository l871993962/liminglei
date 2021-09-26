package com.yss.uco.elecreco.bi.elecrela.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.bi.elecrela.service.IElecIndexDataService;

public class ElecIndexDataService implements IElecIndexDataService{

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			sql = "SELECT C_KEY_CODE,C_KEY_NAME FROM T_S_INDEX ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(rs.getString("C_KEY_CODE"), rs.getString("C_KEY_NAME"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		return keyValueMap;
	
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		String[] keys = new String[listKey.size()];
		keys = listKey.toArray(keys);
		try {
			conn = DbPoolFactory.getInstance()
					.getPool().getConnection();
			sql = "SELECT C_KEY_CODE,C_KEY_NAME FROM T_S_INDEX WHERE C_KEY_CODE IN (SELECT * FROM TABLE(?))";
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				keyValueMap.put(rs.getString("C_KEY_CODE"), rs.getString("C_KEY_NAME"));
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn);
		}
		return keyValueMap;
	}

}
