package com.yss.ams.sec.information.modules.sv.suspendedcond.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SuspendedCondDao extends GeneralDao {

	private SuspendedCondSqlBuilder suspendCondSqlBuilder = null;

	public SuspendedCondDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		suspendCondSqlBuilder = (SuspendedCondSqlBuilder) sqlBuilder;
	}

	@Override
	public <T extends BasePojo> void updateById(T basePojo)
			throws DataAccessException {
		String sql = suspendCondSqlBuilder.buildUpdateSql(null);

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.loadNewConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ((SuspendedCond) basePojo).getC_ITEM_CODE());
			pstmt.setString(2, ((SuspendedCond) basePojo).getC_ITEM_NAME());
			pstmt.setString(3, ((SuspendedCond) basePojo).getC_ITEM_VALUE());
			pstmt.setString(4, ((SuspendedCond) basePojo).getC_LOGICAL_JUDGMENT());
			pstmt.setString(5, ((SuspendedCond) basePojo).getC_VALUE_TYPE());
			pstmt.setString(6, ((SuspendedCond) basePojo).getId());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new DataAccessException("更新功能选项失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}

	public List<SuspendedCond> getGgCondList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SuspendedCondSqlBuilder condSqlBuilder = null;
		List<SuspendedCond> condList = null;
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			condSqlBuilder = new SuspendedCondSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, condSqlBuilder);
			sql = condSqlBuilder.getGgCondList();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (null == condList) {
					condList = new ArrayList<SuspendedCond>();
				}
				SuspendedCond cond = (SuspendedCond) rsTools.ResultToBean(rs,
						SuspendedCond.class);
				condList.add(cond);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("停牌股票信息：查询停牌股票信息出错", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return condList;
	}
	
	public List<SuspendedCond> getSyCondList() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SuspendedCondSqlBuilder condSqlBuilder = null;
		List<SuspendedCond> condList = null;
		ResultSetTools rsTools = null;
		try {
			conn = this.loadNewConnection();
			condSqlBuilder = new SuspendedCondSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, condSqlBuilder);
			sql = condSqlBuilder.getSyCondList();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (null == condList) {
					condList = new ArrayList<SuspendedCond>();
				}
				SuspendedCond cond = (SuspendedCond) rsTools.ResultToBean(rs,
						SuspendedCond.class);
				condList.add(cond);
			}

		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("停牌股票信息：查询停牌股票信息出错", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return condList;
	}
	
	public List<SuspendedCond> getCondList(String port) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		SuspendedCondSqlBuilder condSqlBuilder = null;
		List<SuspendedCond> condList = new ArrayList<SuspendedCond>();
		ResultSetTools rsTools = null;
		List<String> removeportList = new ArrayList<String>();
		List<String> portList = new ArrayList<String>();  
		for(String t : port.split(",")){  
			portList.add(t);  
		}
		// add by zhd 2016-12-23
		// STORY36994【南方基金】停牌股票指数收益法逻辑变更
//		List<SuspendedCond> condListGg = this.getGgCondList();
//		for (SuspendedCond cond : condListGg) {
//			condList.add(cond);
//		}
		try {
			conn = this.loadNewConnection();
			condSqlBuilder = new SuspendedCondSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, condSqlBuilder);
			sql = condSqlBuilder.getCondPortList();
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(String
					.valueOf(port),conn));
			rs = pstmt.executeQuery();

			while (rs.next()) {
				// add by zhd 2016-12-23
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
//				if (null == condList) {
//					condList = new ArrayList<SuspendedCond>();
//				}
				SuspendedCond cond = (SuspendedCond) rsTools.ResultToBean(rs,
						SuspendedCond.class);
				if(!removeportList.contains(cond.getC_PORT_CODE())){
					removeportList.add(cond.getC_PORT_CODE());
				}
				condList.add(cond);
			}
			portList.removeAll(removeportList);
			if (portList.size() > 0) {
				// add by zhd 2016-12-23
				// STORY36994【南方基金】停牌股票指数收益法逻辑变更
				// 还有未设置私有化的组合，默认取公有的值赋给它
//				List<SuspendedCond> condListSy = this.getSyCondList();
				// add by zhd 2016-11-25
				// STORY32313停牌股票信息生成做成公共层面，不关联组合
				// 与特定组合无关，是所有组合共有
//				for (String portCond : portList) {
//					for (SuspendedCond cond : condListSy) {
//						cond.setC_PORT_CODE(portCond);
//						condList.add(cond);
//					}
//				}
				List<SuspendedCond> condListGy = this.getGgCondList();
				for(String portCond : portList) {
					for(SuspendedCond cond : condListGy) {
						cond.setC_PORT_CODE(portCond);
						condList.add(cond);
					}
				}
			}

		} catch (Exception e) {
			logger.log("停牌股票信息：查询停牌股票信息出错", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return condList;
	}
}
