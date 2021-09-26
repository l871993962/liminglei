package com.yss.ams.sec.information.modules.mp.secTransferPara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.ams.sec.information.support.modules.mp.secTransferPara.pojo.SecTransferPara;




/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecTransferParaDao extends GeneralDao {


	public SecTransferParaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlbuilder  = (SecTransferParaSqlBuilder) sqlBuilder;
	}

	@Override
	public <T extends BasePojo> void updateById(T basePojo)
			throws DataAccessException {
		String sql = "UPDATE T_D_MP_SEC_TRANSFERPARA SET C_ITEM_CODE=?,C_ITEM_VALUE = ?,C_LOGICAL_JUDGMENT =? WHERE C_IDEN = ? ";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = this.loadNewConnection();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ((SecTransferPara) basePojo).getC_ITEM_CODE());
			pstmt.setString(2, ((SecTransferPara) basePojo).getC_ITEM_VALUE());
			pstmt.setString(3, ((SecTransferPara) basePojo).getC_LOGICAL_JUDGMENT());
			pstmt.setString(4, ((SecTransferPara) basePojo).getId());

			pstmt.executeUpdate();

		} catch (Exception ex) {
			throw new DataAccessException("更新功能选项失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
}
