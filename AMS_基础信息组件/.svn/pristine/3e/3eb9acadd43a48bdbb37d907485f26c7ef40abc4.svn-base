package com.yss.ifa.szt.tool.rptlog.dao;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.ifa.szt.tool.pojo.ErRptLog;

public class ErRptLogDao extends GeneralDao  {

	public ErRptLogDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public void deleteRptLog(String date) {
		Connection conn = null;
		boolean bTrans = false;
		String sql = "";
		PreparedStatement st = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			sql = ((ErRptLogSqlBuilder)this.sqlbuilder).getDeleteRptLogSql();
			st = conn.prepareStatement(sql);
			st.setString(1, date);
			int num = st.executeUpdate();
			this.logger.log("清楚历史报文数据"+num+"条!");
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(st);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}

//	@Override
//	public <T extends BaseBean> String insert(T baseBean, Connection conn)
//			throws DataAccessException {
//		ErRptLog log = (ErRptLog) baseBean;
//		String ciden = "";
//		String sql = "";
//		PreparedStatement pstmt = null;
//		StringReader enReader = null;
//		StringReader deReader = null;
//		try {
//			sql = ((ErRptLogSqlBuilder)sqlbuilder).getInsertSql();
//			ciden = getSequenceNextNumber(conn,getSequanceName(sqlbuilder.getTableName(dbNameResolver)));
//			pstmt = conn.prepareStatement(sql);
//			int i = 1;
//			logger.debug("Executing Sql : "+"\r\n"+sql+"\r\n"+"=============================="+"\r\n"+"Property Value Set : ");
//			pstmt.setString(i++, ciden);
//			pstmt.setString(i++, log.getC_ASS_CODE());
//			pstmt.setString(i++, log.getC_CERT_ID());
//			pstmt.setString(i++, log.getC_DEPT_CODE());
//			pstmt.setString(i++, log.getC_DV_CHARSET());
//			pstmt.setString(i++, log.getC_DV_LOG_TYPE());
//			pstmt.setString(i++, log.getC_DV_SECRETTYPE());
//			pstmt.setString(i++, log.getC_ERR_INFO());
//			pstmt.setString(i++, log.getC_FILE_TYPE());
//			pstmt.setString(i++, log.getC_PKG_PSD());
//			pstmt.setString(i++, log.getC_RPT_TYPE());
//			pstmt.setString(i++, log.getC_SN());
//			pstmt.setString(i++, log.getC_SRC_APPID());
//			pstmt.setString(i++, log.getC_SRC_USERID());
//			pstmt.setString(i++, log.getC_TARGET_APPID());
//			pstmt.setString(i++, log.getC_TARGET_USERID());
//			pstmt.setTimestamp(i++, log.getD_HANDLE_TIME());
//			deReader = new StringReader(log.getC_DECRYPT_LOG());
//			enReader = new StringReader(log.getC_ENCRYPT_LOG());
//			pstmt.setClob(i++, deReader, log.getC_DECRYPT_LOG().length());
//			pstmt.setClob(i++, enReader, log.getC_ENCRYPT_LOG().length());
//			//pstmt.setObject(i++, log.getD_HANDLE_TIME());
//			logger.debug("==============================");
//			
//			pstmt.executeUpdate();			
//			
//		} catch (Exception ex) {
//			if(ex instanceof YssRuntimeException){
//				throw (DataAccessException)ex;
//			}else{
//				logger.log("保存失败：" + ex.getMessage());
//				throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
//			}			
//		} finally {
//			this.closeStatementFinal(pstmt);
//			if(deReader!= null)
//			{
//				deReader.close();
//			}
//			if(enReader != null)
//			{
//				enReader.close();
//			}
//		}
//		 if(baseBean instanceof BasePojo){
//			   ((BasePojo) baseBean).setId(ciden);
//		 }
//		return ciden;
//	}
	/**
	 * 提高插入效率，clob先插入空clob，再更新
	 */
	@Override
	public <T extends BaseBean> String insert(T baseBean, Connection conn)
			throws DataAccessException {
		ErRptLog log = (ErRptLog) baseBean;
		String ciden = "";
		String sql = "";
		PreparedStatement pstmt = null;
		PreparedStatement pstmtS = null;
		PreparedStatement pstmtU = null;
		ResultSet rs = null;
		try {
			sql = ((ErRptLogSqlBuilder)sqlbuilder).getInsertSqlNoClob();
			ciden = getSequenceNextNumber(conn,getSequanceName(sqlbuilder.getTableName(dbNameResolver)));
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			logger.debug("Executing Sql : "+"\r\n"+sql+"\r\n"+"=============================="+"\r\n"+"Property Value Set : ");
			pstmt.setString(i++, ciden);
			pstmt.setString(i++, log.getC_ASS_CODE());
			pstmt.setString(i++, log.getC_CERT_ID());
			pstmt.setString(i++, log.getC_DEPT_CODE());
			pstmt.setString(i++, log.getC_DV_CHARSET());
			pstmt.setString(i++, log.getC_DV_LOG_TYPE());
			pstmt.setString(i++, log.getC_DV_SECRETTYPE());
			pstmt.setString(i++, log.getC_ERR_INFO());
			pstmt.setString(i++, log.getC_FILE_TYPE());
			pstmt.setString(i++, log.getC_PKG_PSD());
			pstmt.setString(i++, log.getC_RPT_TYPE());
			pstmt.setString(i++, log.getC_SN());
			pstmt.setString(i++, log.getC_SRC_APPID());
			pstmt.setString(i++, log.getC_SRC_USERID());
			pstmt.setString(i++, log.getC_TARGET_APPID());
			pstmt.setString(i++, log.getC_TARGET_USERID());
			pstmt.setTimestamp(i++, log.getD_HANDLE_TIME());
			pstmt.setString(i++, log.getC_PKG_ID());
			logger.debug("==============================");
			
			pstmt.executeUpdate();	
			pstmtS = conn.prepareStatement("select C_ENCRYPT_LOG,C_DECRYPT_LOG from T_D_ER_RPT_LOG where C_IDEN = ?");
			pstmtS.setString(1, ciden);
			rs = pstmtS.executeQuery();
			if(rs.next())
			{
				Clob en = rs.getClob("C_ENCRYPT_LOG");
				en.setString(1, log.getC_ENCRYPT_LOG());
				Clob de = rs.getClob("C_DECRYPT_LOG");
				de.setString(1, log.getC_DECRYPT_LOG());
				pstmtU = conn.prepareStatement(" update T_D_ER_RPT_LOG set C_ENCRYPT_LOG = ?,C_DECRYPT_LOG=? where C_IDEN = ? ");
				pstmtU.setClob(1, en);
				pstmtU.setClob(2, de);
				pstmtU.setString(3, ciden);
			}
			
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (DataAccessException)ex;
			}else{
				logger.log("保存失败：" + ex.getMessage());
				throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
			}			
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.closeStatementFinal(pstmtU);
			this.closeStatementFinal(pstmtS);
		}
		log.setId(ciden);
		return ciden;
	}

	public String queryLogById(String id) 
	{
		String log = "";
		String sql = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Connection conn = null;
		try {
			sql = ((ErRptLogSqlBuilder)sqlbuilder).queryLogByIdSql();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setString(i++, id);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				log = rs.getString("C_DECRYPT_LOG");
			}
			
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (DataAccessException)ex;
			}else{
				logger.log("查询失败：" + ex.getMessage());
				throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
			}			
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return log;
	}

	/**
	 * 更新报文序号
	 * @param oldSn
	 * @param newSn
	 */
	public void updateSN(String oldSn, String newSn) {
		Connection conn = null;
		boolean bTrans = false;
		String sql = "";
		PreparedStatement st = null;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			sql = ((ErRptLogSqlBuilder)this.sqlbuilder).getUpdateSNSql();
			st = conn.prepareStatement(sql);
			st.setString(1, newSn);
			st.setString(2, oldSn);
			st.executeUpdate();
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("更新日志报文序号失败：" + ex.getMessage(), ex);
		} finally {
			closeStatementFinal(st);
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	

}