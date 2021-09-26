package com.yss.uco.elecreco.bi.elecrela.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.support.bean.ElecRela;

public class ElecPerRelaDao extends GeneralDao{

	private ElecPerRelaSqlBuilder perRelaSqlBuilder;
	public ElecPerRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.perRelaSqlBuilder = (ElecPerRelaSqlBuilder) sqlBuilder;
	}
	
	public HashMap<String, ElecPerRela> getPerRelaByPortAndDZCode(
			String c_PORT_CODE, String c_DZ_CODE)
	{
		HashMap<String, ElecPerRela> map = new HashMap<String, ElecPerRela>(); 
		ElecPerRela pojo = null;
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = perRelaSqlBuilder.getPerRelaByPortAndDZCodeSql();
			pst = conn.prepareStatement(sql);
			int index = 1;
			pst.setString(index++, c_PORT_CODE);
			pst.setString(index++, c_DZ_CODE);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				pojo = (ElecPerRela)rsTools.ResultToPojoObject(rs, ElecPerRela.class);
				map.put(pojo.getC_ZB_CODE(), pojo);
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return map;
	}
	
	public BasePojo getPerRelaByCodeAndName(String c_ZB_CODE,
			String c_ZB_Name, String c_DZ_CODE) {
		BasePojo pojo = null;
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = perRelaSqlBuilder.getPerRelaSqlByPara();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c_ZB_CODE);
			pst.setString(2, c_ZB_Name);
			pst.setString(3, c_DZ_CODE);
			pst.setString(4, c_ZB_CODE);
			pst.setString(5, c_ZB_Name);
			pst.setString(6, c_DZ_CODE);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				pojo = (BasePojo)rsTools.ResultToPojoObject(rs, ElecRela.class);
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return pojo;
	}
	
	public BasePojo getPerRelaByCode(String c_ZB_CODE){
		BasePojo pojo = null;
		Connection conn = this.loadNewConnection();
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql;
		try {
			sql = perRelaSqlBuilder.getPerRelaByCode();
			pst = conn.prepareStatement(sql);
			pst.setString(1, c_ZB_CODE);
			pst.setString(2, c_ZB_CODE);
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				pojo = (BasePojo)rsTools.ResultToPojoObject(rs, ElecRela.class);
			}
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return pojo;
	}
	
	/**
	 * STORY #95888 【招商基金】【0331】【公募】新基金成立自动复制''对账指标关联'指标
	 * add by zhanghubin 20210315
	 * @param portCode
	 * @return
	 * @throws DataAccessException
	 */
	public int deleteBeforeCopy(String portCode)
			throws DataAccessException {
		ElecPerRelaSqlBuilder elecPerRelaBuilder = new ElecPerRelaSqlBuilder();
		Connection conn = null;
		PreparedStatement pstmt = null;
		int count = 0;
		try {
			conn = this.loadNewConnection();
			String sql = elecPerRelaBuilder.getdeleteBeforeCopySql(this.dbNameResolver);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			count = pstmt.executeUpdate();
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("对账指标关联功能模块：产品参数复制前删除失败", e);
			throw new DataAccessException("删除失败：" + e.getMessage(), e);
		} finally {
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return count;
	}
}
