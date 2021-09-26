package com.yss.ams.base.information.modules.sys.convertdict.zdorg.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.mvc.pojo.PojoLoader;

public class ZdOrgDao extends GeneralDao {

	private ZdOrgSqlBuilder sqlBuilder;
	public ZdOrgDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ZdOrgSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 根据CorpOrgSqlBuilder类中拼接的sql语句 ，以及参数，进行参数绑定，然后查询数据
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<BasePojo> queryTreeViewData(HashMap<String, Object> paraMap,BundleContextWrapper bundleContext) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";
		Class<?> clazz = null;

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = sqlBuilder.getTreeViewQuerySql(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			clazz = PojoLoader.getPojoClassById("ZdOrgTreeView",bundleContext);
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);
			BasePojo t = null;
			while (rs.next()) {
				t = rsTools.ResultToBean(rs, clazz);
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
	 * 是否有根节点
	 * @return
	 */
	public String getCheckHasRootNode(){
		String nodeCount = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			String sql = sqlBuilder.getCheckHasRootNode();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				nodeCount = String.valueOf(rs.getInt(1));
			}
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return nodeCount;
	}

}
