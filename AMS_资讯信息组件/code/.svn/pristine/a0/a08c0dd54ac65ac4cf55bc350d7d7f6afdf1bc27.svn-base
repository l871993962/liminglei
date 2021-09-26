package com.yss.ams.sec.information.modules.sv.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 理财产品信息dao
 * @author 马向峰
 *
 */
public class SecBaseLcDao extends GeneralDao {

	private SecBaseLcSqlBuilder lcSqlBuilder;
	
	public SecBaseLcDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		lcSqlBuilder = (SecBaseLcSqlBuilder)sqlBuilder;
	}

	/**
	 * 获取分红转投日提醒的证券
	 * 
	 * @return 证券信息列表
	 * @throws Exception 
	 */
	public List<SecBase> remindFhztDate() throws Exception {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<SecBase> secBaseList = new ArrayList<SecBase>();
		SecBase secBase = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = lcSqlBuilder.getRemindFhztDateSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				secBase = new SecBase();
				secBase.setN_PRICE_ISSUE(rs.getBigDecimal("N_PRICE_ISSUE"));// 偏移日期
				secBase.setC_DV_ASSURE(rs.getString("C_DV_ASSURE"));		// 日期类型
				secBase.setC_DV_ISSUE(rs.getString("C_DV_ISSUE"));    		// 货基分投类型
				secBase.setC_MKT_CODE(rs.getString("C_MKT_CODE"));			// 交易市场
				secBase.setC_SEC_CODE(rs.getString("C_SEC_CODE"));			// 证券内码
				secBase.setC_SEC_NAME(rs.getString("C_SEC_NAME"));			// 证券名称
				secBase.setC_ORG_CODE(rs.getString("C_PORT_CODE"));			// 组合代码
				secBase.setC_ORG_NAME(rs.getString("C_PORT_NAME"));			// 组合名称
				secBaseList.add(secBase);
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("证券信息功能模块：获取分红转投日提醒的证券信息失败", e);
			throw e;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}

		return secBaseList;
	}
}
