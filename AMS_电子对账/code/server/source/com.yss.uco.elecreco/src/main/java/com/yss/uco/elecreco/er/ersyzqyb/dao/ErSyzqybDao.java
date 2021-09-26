package com.yss.uco.elecreco.er.ersyzqyb.dao;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErSyzqybDao extends GeneralDao {

	public ErSyzqybDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

//	public List<BasePojo> getKmMap(HashMap<String, Object> paraMap,
//			Class<?> clazz) {
//		List<BasePojo> pojoList = new ArrayList<BasePojo>();
//
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//
//		String sql = "";
//		ResultSetTools rsTools = null;
//		try {
//			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
//			conn = this.loadNewConnection();
//			sql = ((ErSyzqybSqlBuilder) sqlbuilder).getKmSql();
//			
//			pstmt = conn.prepareStatement(sql);
//
//			pstmt.setObject(1, paraMap.get("C_SN"));
//			pstmt.setObject(2, paraMap.get("C_RPT_CODE"));
//
//			rs = pstmt.executeQuery(); 
////			while (rs.next()) {
////				BasePojo t = setResultSet(rsTools,rs, clazz); 
////				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
////				pojoList.add(t);
////			}
//		} catch (Exception ex) {
//			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//
//		return pojoList;
//	}
}
