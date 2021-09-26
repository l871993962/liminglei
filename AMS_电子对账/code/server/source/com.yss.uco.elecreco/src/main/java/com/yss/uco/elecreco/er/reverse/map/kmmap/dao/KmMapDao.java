package com.yss.uco.elecreco.er.reverse.map.kmmap.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.AutoDateProc;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.context.ContextFactory;

public class KmMapDao extends GeneralDao  {

	private KmMapSqlBuilder sqlBuilder = null;
	public KmMapDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (KmMapSqlBuilder) sqlBuilder;
	}
	

	/**
	 * 将父类私有的方法改为公有
	 * @param pojoList
	 * @param conn
	 * @throws DataAccessException
	 */
	public <T extends BasePojo> void deleteById(List<T> pojoList,
			Connection conn) throws DataAccessException {
		PreparedStatement pstmt = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from ");
			sql.append(this.sqlbuilder.getTableName(dbNameResolver));

			String columnName = this.sqlbuilder
					.getColumnNameByProperty(dbNameResolver, "id");
			sql.append(" where ");
			sql.append(columnName);
			sql.append("=");
			sql.append("?");
			pstmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < pojoList.size(); i++) {
				T basePojo = pojoList.get(i);
				if (basePojo instanceof IEffectivable) {
					if (!ContextFactory.getContext().isCheck()) {
						autDateProc = new AutoDateProc(dbNameResolver,
								sqlbuilder, conn);
						autDateProc.inEffectiveDate((ParamPojo) basePojo);
					}
				}
				
				String value = basePojo.getId();
				if (value == null) {
					throw new InvalidParametersException("id不能为空");
				}

				pstmt.setString(1, value);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	
	
}