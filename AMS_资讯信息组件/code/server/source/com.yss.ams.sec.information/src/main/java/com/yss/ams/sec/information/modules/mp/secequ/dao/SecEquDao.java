package com.yss.ams.sec.information.modules.mp.secequ.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.DateUtil;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecEquDao extends GeneralDao {

	private SecEquSqlBuilder sqlBuilder = null;
	
	//为防止报错而添加的getset方法  by lihaizhi 20130620
	public SecEquSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(SecEquSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}

	public SecEquDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (SecEquSqlBuilder) sqlbuilder;
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-03-17
	 * Status : Add
	 * Comment: 对价派息消息提醒
	 * @param days
	 * @return
	 */
	public List<SecBase> getRemindDJPX(int days){
		List<SecBase> list = new ArrayList<SecBase>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" SELECT b.C_SEC_NAME, a.C_SEC_CODE, a.D_EXR AS D_WARN");
			sqlBuff.append(" FROM T_D_MP_SEC_EQU a");
			sqlBuff.append(" LEFT JOIN T_P_SV_SEC_BASE b");
			sqlBuff.append(" ON b.C_SEC_CODE = a.C_SEC_CODE");
			sqlBuff.append(" WHERE a.N_CHECK_STATE = 1");
			sqlBuff.append(" AND a.C_EQU_CLS = 'DJ'");
			sqlBuff.append(" AND a.D_EXR BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE + ?)");
			pstmt = conn.prepareStatement(sqlBuff.toString());
			pstmt.setInt(1, days);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SecBase secBase = new SecBase();
				secBase.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				secBase.setC_SEC_NAME(rs.getString("C_SEC_NAME"));
				secBase.setD_END(DateUtil.dateToString(rs.getDate("D_WARN"), "yyyy-MM-dd"));
				list.add(secBase);
			}
		}catch(Exception ex){
			throw new BusinessException("对价派息提醒信息出错!" + ex.getMessage());
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-03-17
	 * Status : Add
	 * Comment: 证券派送消息提醒
	 * @param days
	 * @return
	 */
	public List<SecBase> getRemindZQPS(int days){
		List<SecBase> list = new ArrayList<SecBase>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" SELECT b.C_SEC_NAME, a.C_SEC_CODE, a.D_EXR AS D_WARN");
			sqlBuff.append(" FROM T_D_MP_SEC_EQU a");
			sqlBuff.append(" LEFT JOIN T_P_SV_SEC_BASE b");
			sqlBuff.append(" ON b.C_SEC_CODE = a.C_SEC_CODE");
			sqlBuff.append(" WHERE a.N_CHECK_STATE = 1");
			sqlBuff.append(" AND a.C_EQU_CLS = 'SP'");   
			sqlBuff.append(" AND a.D_EXR BETWEEN TRUNC(SYSDATE) AND TRUNC(SYSDATE + ?)");  
			pstmt = conn.prepareStatement(sqlBuff.toString());
			pstmt.setInt(1, days);
			rs = pstmt.executeQuery();
			while(rs.next()){
				SecBase secBase = new SecBase();
				secBase.setC_SEC_CODE(rs.getString("C_SEC_CODE"));
				secBase.setC_SEC_NAME(rs.getString("C_SEC_NAME"));
				secBase.setD_END(DateUtil.dateToString(rs.getDate("D_WARN"), "yyyy-MM-dd"));
				list.add(secBase);
			}
		}catch(Exception ex){
			throw new BusinessException("对价派息提醒信息出错!" + ex.getMessage());
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	
}
