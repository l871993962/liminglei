package com.yss.ams.sec.information.modules.sv.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 期权信息 dao
 * @author shiliang
 * 资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseQqDao extends GeneralDao {

	public SecBaseQqDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 根据证券内码查询保证金信息
	 * @param cSecCode
	 */
	public void deleteBzjBySec(String cSecCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		String sql = " delete from T_P_SV_SEC_BASE_BAIL where C_SEC_CODE = ? ";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cSecCode);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("证券信息功能模块：根据证券内码查询保证金信息失败", e);
		} finally {
			DbFun.closeStatementFinal(pstmt);
			DbFun.releaseConnection(conn); //释放数据库链接
		}
	}

}
