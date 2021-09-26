package com.yss.uco.elecreco.er.reverse.out.erkmb.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.out.erkmb.pojo.ErKmbOut;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErKmbOutDao extends GeneralDao  {

private ErKmbOutSqlBuilder sqlBuilder = null;
	public ErKmbOutDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErKmbOutSqlBuilder) sqlBuilder;
	}
	@Override
	public <T extends BaseBean> String insert(T baseBean, Connection conn)
			throws DataAccessException {
		List<T> list = new ArrayList<T>();
		list.add(baseBean);
		List<String> result = insert(list, conn);
		if(result != null&&result.size() > 0)
		{
			return result.get(0);
		}
		return "";
	}
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list, Connection conn)
			throws DataAccessException {
		List<String> cidenList = new ArrayList<String>();
		PreparedStatement pstmt = null;
		String sql = "";
		ErKmbOut km = null;
		int index = 1;
		try {
			sql = this.sqlBuilder.getInsertSql();
			if(list!=null&&list.size()>0)
			{
				pstmt = conn.prepareStatement(sql);
				for(T pojo : list)
				{
					km = (ErKmbOut) pojo;
					cidenList.add("");
					index = 1;
					pstmt.setString(index++, km.getC_ASS_CODE());
					pstmt.setString(index++, km.getC_ASS_CODE());
					pstmt.setString(index++, km.getC_TGH_CODE());
					pstmt.setString(index++, km.getC_TGH_CODE());
					pstmt.setString(index++, km.getC_KM_CODE());
					pstmt.setString(index++, km.getC_KM_NAME());
					pstmt.setString(index++, km.getC_DV_KM_CLS());
					pstmt.addBatch();
				}

				pstmt.executeBatch();
			}
			
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
		return cidenList;
	}
	/**
	 * 根据托管行，组合，科目代码删除数据
	 * @param pojoList
	 * @param conn
	 * @throws DataAccessException
	 */
	private <T extends BasePojo> void deleteById(List<T> pojoList,
			Connection conn) throws DataAccessException {
		PreparedStatement pstmt = null;
		int index = 0;
		ErKmbOut km = null;
		try {
			StringBuffer sql = new StringBuffer();
			sql.append("delete from ");
			sql.append(this.sqlbuilder.getTableName(dbNameResolver));
			sql.append(" where ");
			sql.append(" C_TGH_CODE = ? ");
			sql.append(" and C_ASS_CODE = ? ");
			sql.append(" and C_KM_CODE = ? ");
			pstmt = conn.prepareStatement(sql.toString());
			for (int i = 0; i < pojoList.size(); i++) {
				km = (ErKmbOut)pojoList.get(i);
				index = 1;
				pstmt.setString(index++, km.getC_TGH_CODE());
				pstmt.setString(index++, km.getC_ASS_CODE());
				pstmt.setString(index++, km.getC_KM_CODE());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	
	
	/**
	 *  没有ID，调用本地方法
	 */
	@Override
	public <T extends BasePojo> void deleteById(T basePojo, Connection conn)
			throws DataAccessException {
		if (basePojo == null) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}
		
		List<T> pojoList = new ArrayList<T>();
		pojoList.add(basePojo);
		deleteById(pojoList, conn);
	}
	/**
	 * 没有ID，调用本地方法
	 */
	@Override
	public <T extends BasePojo> void updateById(T basePojo, Connection conn)
			throws DataAccessException {
		if (basePojo == null) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}
		
		List<T> pojoList = new ArrayList<T>();
		pojoList.add(basePojo);
		updateById(pojoList, conn);
		
	}
	/**
	 * 没有ID，调用本地删除方法
	 */
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			deleteById(pojoList, conn);
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	/**
	 * 没有ID，调用本地方法
	 */
	@Override
	public <T extends BasePojo> void updateById(T basePojo)
			throws DataAccessException {
		if (basePojo == null) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}
		
		List<T> pojoList = new ArrayList<T>();
		pojoList.add(basePojo);		
		updateById(pojoList);
	}
	
	/**
	 * 没有ID，调用本地删除方法
	 */
	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;			
			updateById(pojoList, conn);			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	/**
	 * 更新基础方法，根据组合，托管行，科目代码更新
	 * @param pojoList
	 * @param conn
	 * @throws DataAccessException
	 */
	private <T extends BasePojo> void updateById(List<T> pojoList,
			Connection conn) throws DataAccessException {
		PreparedStatement pstmt = null;
		int index = 1;
		ErKmbOut km = null;
		try {
			StringBuffer sqlBuffer = new StringBuffer();
			sqlBuffer.append("update ");
			sqlBuffer.append(this.sqlbuilder
					.getTableName(this.dbNameResolver));
			sqlBuffer.append(" set ");
			sqlBuffer.append(" C_ASS_CODE = ?, ");
			sqlBuffer.append(" C_TGH_CODE = ?, ");
			sqlBuffer.append(" C_KM_CODE = ?, ");
			sqlBuffer.append(" C_KM_NAME = ?, ");
			sqlBuffer.append(" C_DV_KM_CLS = ? ");
			sqlBuffer.append(" where ");
			sqlBuffer.append(" C_ASS_CODE = ? ");
			sqlBuffer.append(" and C_TGH_CODE = ? ");
			sqlBuffer.append(" and C_KM_CODE = ? ");

			pstmt = conn.prepareStatement(sqlBuffer.toString());
			for (int i = 0; i < pojoList.size(); i++) {
				T basePojo = pojoList.get(i);
				km = (ErKmbOut) basePojo;
				index = 1;
				//set
				pstmt.setString(index++, km.getC_ASS_CODE());
				pstmt.setString(index++, km.getC_TGH_CODE());
				pstmt.setString(index++, km.getC_KM_CODE());
				pstmt.setString(index++, km.getC_KM_NAME());
				pstmt.setString(index++, km.getC_DV_KM_CLS());
				//where
				pstmt.setString(index++, km.getC_ASS_CODE());
				pstmt.setString(index++, km.getC_TGH_CODE());
				pstmt.setString(index++, km.getC_KM_CODE());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	
}