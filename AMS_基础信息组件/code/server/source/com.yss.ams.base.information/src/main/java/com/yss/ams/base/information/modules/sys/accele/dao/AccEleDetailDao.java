package com.yss.ams.base.information.modules.sys.accele.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.accele.pojo.AccEleDetail;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

//import dataservice.comm.pojo.AccEleDetail;

public class AccEleDetailDao extends GeneralDao {

	
	public AccEleDetailDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public List<BasePojo> getAllDataList() {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = sqlbuilder.getQueryConditionSql(null);

			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, AccEleDetail.class);
				pojoList.add(t);
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
	
	/**
	 * 获取所有科目辅助元素
	 * add by Yuntao Lau 2015.12.01 STORY #26998
	 * @param paraMap
	 * @return 所有科目辅助元素
	 */
	public List<BasePojo> getDetailDataByCondition(HashMap<String, String> paraMap) {
		String sql = "";
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		ResultSetTools rsTools = null;
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = "SELECT * FROM T_S_DAE_ELEM_DETAIL WHERE C_LOAD_MODE = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("loadMode"));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, AccEleDetail.class);
				pojoList.add(t);
			}
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
}
