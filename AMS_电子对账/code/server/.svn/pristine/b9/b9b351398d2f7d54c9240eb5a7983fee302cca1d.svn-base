package com.yss.uco.elecreco.er.task.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.support.bean.ErTask;

public class ErTaskDao extends GeneralDao  {

	private ErTaskSqlBuilder sqlBuilder = null;
	public ErTaskDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErTaskSqlBuilder) sqlBuilder;
	}
	public ErTask getErTaskByCode(String taskCode) {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getErTaskByCodeSql(taskCode);

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, taskCode);
			rs = pstmt.executeQuery();

			ErTask task = new ErTask();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(task);
			if (rs.next()) {
				task = (ErTask) setResultSet(rsTools,rs, ErTask.class, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,task);
				return task;
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return null;
	}
	public List<ErTask> getErAllTasks() {
		List<ErTask> pojoList = new ArrayList<ErTask>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = sqlBuilder.getAllTasksSql();

			pstmt = conn.prepareStatement(sql);
			try{
				rs = pstmt.executeQuery();
			}catch(SQLSyntaxErrorException e){
				return null;
			}

			ErTask task = new ErTask();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(task);
			while (rs.next()) {
				task = (ErTask) setResultSet(rsTools,rs, ErTask.class, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,task);
				pojoList.add(task);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
}