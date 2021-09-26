package com.yss.ams.sec.information.modules.sv.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 期货&期权保证金 dao
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseQhbzjDao  extends GeneralDao {

	private SecBaseQhbzjSqlBuilder sqlBuilder;
	
	public SecBaseQhbzjDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (SecBaseQhbzjSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 据证券内码删除期货&期权保证金信息
	 * @param c_sec_code
	 * @return isSuccess
	 */
	public boolean deleteBySecCode(String c_sec_code){
		boolean isSuccess = false;
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = this.sqlBuilder.getDeleteBySecCodeSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_sec_code);
			pstmt.execute();
			isSuccess = true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("证券信息功能模块：根据证券内码删除期权保证金信息失败", e);
		}finally{
			this.closeStatementFinal(pstmt); //byleeyu20160617
			this.releaseConnection(conn); //byleeyu20160617
		}
		return isSuccess;
		
	}

}
