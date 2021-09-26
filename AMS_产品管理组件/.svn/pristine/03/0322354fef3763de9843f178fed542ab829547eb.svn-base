package com.yss.ams.product.information.modules.ab.port.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

public class PortFundRelaDao extends GeneralDao{
	
	private PortFundRelaSqlBuilder portFundRelaBuilder = null;
	
	public PortFundRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		portFundRelaBuilder = (PortFundRelaSqlBuilder) sqlBuilder;
	}
	
	public String deletePortFundRela(String portCodes, String fundAccId){
		String result = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "";
		try{
			sql = portFundRelaBuilder.deletePortFundRela();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portCodes, conn));
			pstmt.setString(2, fundAccId);
			int len = pstmt.executeUpdate();
			if(len > 0){
				result = "Success";
			}
		}catch (Exception e){
			logger.error("删除组合账户关联关系失败：" + e.getMessage());
			throw new DataAccessException("删除组合账户关联关系失败：" + e.getMessage());
		}finally{
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return result;
	}
}
