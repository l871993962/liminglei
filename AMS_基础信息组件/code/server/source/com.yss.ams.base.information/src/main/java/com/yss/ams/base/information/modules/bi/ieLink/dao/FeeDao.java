package com.yss.ams.base.information.modules.bi.ieLink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 收支费用dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class FeeDao extends GeneralDao {

	private FeeSqlBuilder feeSqlbuilder = null;

	public FeeDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		feeSqlbuilder = (FeeSqlBuilder) sqlBuilder;
	}

	/**
	 * 获取收支项目设置中所有收支代码和收支名称的集合map
	 * @return
	 * @throws Exception
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = feeSqlbuilder.getKeyConvertMapSql();
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				map.put(rs.getString(FeeColumnName.c_FEE_CODE.toString()), rs
						.getString(FeeColumnName.c_FEE_NAME.toString()));
			}
			return map;
		} catch (Exception ex) {
			throw ex;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
	
	/**
	 * 获取质押物
	 * @param relaId
	 * @param tdType
	 * @return
	 */
//  因为前后台都未进行调用，拆分过程中报错，所以在此进行注释
//	public List<Underlyings> getUnderlyings(String relaId, String tdType) {
//		List<Underlyings> underlyingsList = new ArrayList<Underlyings>();
//		Connection conn = null;
//		ResultSet rs = null;
//		String sql = "";
//		PreparedStatement pst = null;
//		Underlyings underlyings = null;
//		try {
//			conn = this.loadNewConnection();
//			sql = feeSqlbuilder.getUnderlyingsql();
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, relaId);
//			pst.setString(2, tdType);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				underlyings = new Underlyings();
//				if (!StringUtil.IsNullOrEmpty(rs.getString("c_sec_code_tag").trim())) {
//					underlyings.setC_UnSecurityID(rs.getString("c_sec_code_tag"));
//				}
//				underlyingsList.add(underlyings);
//			}
//		} catch (Exception e) {
//			throw new DataAccessException(e);
//		} finally {
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pst);
//			this.releaseConnection(conn);
//		}
//		return underlyingsList;
//	}

}
