package com.yss.uco.elecreco.er.ergzb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErXmlGzbDao extends GeneralDao {
	
	public ErXmlGzbDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	public String deleteBySn(String sn){
		
		String res = "S";
		String sql = "delete from t_d_er_xml_gz t where t.c_sn = ? ";
		Connection conn = null;
		boolean bTrans = false;
		PreparedStatement st = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			st = conn.prepareStatement(sql);
			st.setString(1, sn);
			st.executeUpdate();
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			res = "F";
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(st);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
		return res;
	}
}
