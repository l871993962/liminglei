package com.yss.ams.product.information.fast.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年4月4日 下午3:25:07
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastCommonInfoDao extends GeneralDao {

	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年4月4日 下午3:25:11
	 * @param @param pool
	 * @param @param sqlBuilder
	 */
	public FastCommonInfoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public List<String> getDictDataByKey(String key) {
		List<String> pojoList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = ((FastCommonInfoSqlBuilder)sqlbuilder).getDictDataByKey();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, key);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				pojoList.add(rs.getString("C_GROUP_CODE") + "\t"
						+ rs.getString("C_GROUP_NAME") + "\t"
						+ rs.getString("C_GROUP_CODE_P"));
			}
		} catch (Exception ex) {
			logger.error("查询失败：" + ex.getMessage(), ex);
			throw new DataAccessException(ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
}
