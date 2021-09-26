package com.yss.uco.elecreco.er.special.zb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;

public class ErSpecialZbDao extends GeneralDao  {

	private ErSpecialZbSqlBuilder sqlBuilder = null;
	public ErSpecialZbDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErSpecialZbSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 获取所有数据
	 * @return
	 */
	public List<ErSpecialZb> getAllData() {
		List<ErSpecialZb> dataList = new ArrayList<ErSpecialZb>();
		ErSpecialZb pojo = null;
		PreparedStatement ptmt = null;
		Connection conn = null;
		String sql = sqlBuilder.getAllDataSql();
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		try {
			conn = loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			ptmt = conn.prepareStatement(sql);
			rs = ptmt.executeQuery();
			while (rs.next()) {
				pojo = (ErSpecialZb) rsTools.ResultToBean(rs, ErSpecialZb.class);
				dataList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(ptmt);
			releaseConnection(conn);
		}
		return dataList;
	}

}