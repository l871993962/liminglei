package com.yss.uco.elecreco.er.eryeb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.uco.elecreco.support.dzdz.common.RecordElement;

public class ErYebDao extends GeneralDao {

	public ErYebDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 * @param fsn
	 * @param date 
	 * @param portCode
	 * @return
	 */
	public Map<String, String> getKmMap(String fsn, String date) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, String> map = new HashMap<String, String>();
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = ((ErYebSqlBuilder) sqlbuilder).getKmSql();
			pstmt = conn.prepareStatement(sql);
			//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
			//pstmt.setString(1, date);
			//pstmt.setString(2, fsn);
			pstmt.setString(1, fsn);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				RecordElement record = new RecordElement();
				String key = record.getKmCode(rs.getString("c_km_code"));
				if (!map.containsKey(key)) {
					map.put(key, rs.getString("c_km_name") + "&&" + rs.getString("c_km_code"));
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

}
