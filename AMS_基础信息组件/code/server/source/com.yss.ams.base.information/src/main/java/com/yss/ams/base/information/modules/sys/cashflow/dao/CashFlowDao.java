package com.yss.ams.base.information.modules.sys.cashflow.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

import com.yss.ams.base.information.support.sys.cashflow.pojo.CashFlow;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 现金流标记字典dao层
 * @author yuankai 基础信息拆分  2017.5.31
 *
 */
public class CashFlowDao extends GeneralDao {

	public CashFlowDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 获取所有现金流标记代码和现金流标记名称对应pojo的list集合
	 * @param
	 * @return list
	 */
	public ArrayList<CashFlow> getCashFlowCode() {
		String sql = "";
		Connection  conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ArrayList<CashFlow> resInfolist = new ArrayList<CashFlow>();
		
		try {
			sql = ((CashFlowSqlBuilder)this.sqlbuilder).getCashFlowNameByCodeSql();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			while(rs.next()){
				CashFlow t = new CashFlow();
				t.setC_CASH_FLOW_NAME(rs.getString("C_CASH_FLOW_NAME"));
				t.setC_CASH_FLOW_CODE(rs.getString("C_CASH_FLOW_CODE"));
				resInfolist.add(t);
			}			
		} catch (Exception e) {
			// handle exception
			logger.log("获取现金流标记字典list失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
	}		
		return resInfolist;
	
	}
	
	/**
	 * 根据现金流标记代码获取对应的现金流标记代码和现金流标记名称对应的pojo
	 * @param pojo
	 * @return CashFlow
	 */
	public CashFlow getCashFlowCode(String pojo) {
		String sql = "";
		Connection  conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		CashFlow t = new CashFlow();	
		try {
			sql = ((CashFlowSqlBuilder)this.sqlbuilder).getCashFlowNameByCodeSql(pojo);
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();		
			while(rs.next()){	
				t.setC_CASH_FLOW_NAME(rs.getString("C_CASH_FLOW_NAME"));
				t.setC_CASH_FLOW_CODE(rs.getString("C_CASH_FLOW_CODE"));
			}			
		} catch (Exception e) {
			// handle exception
			logger.log("获取现金流标记字典pojo失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
	}		
		return t;
	
	}

	public HashMap<String, String> getKeyConvertMap() {//luxiaoying:现金流标记中英文转换
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		
		try {
			

			conn = this.loadNewConnection();

			sql = ((CashFlowSqlBuilder)this.sqlbuilder).getAllDataListSql(dbNameResolver);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				// t = rsTools.ResultToBeanGeneric(rs, Port.class);

				keyValueMap.put(rs.getString(CashFlowColumnName.c_CASH_FLOW_CODE
						.toString()), rs
						.getString(CashFlowColumnName.c_CASH_FLOW_NAME 
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


}
