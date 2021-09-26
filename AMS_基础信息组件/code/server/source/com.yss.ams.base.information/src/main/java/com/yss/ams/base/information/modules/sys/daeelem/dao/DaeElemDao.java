package com.yss.ams.base.information.modules.sys.daeelem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;

/**
 * 删除一些无用的import by lihaizhi 20130620
 */
public class DaeElemDao extends GeneralDao {

	public DaeElemDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public String getDaeNameByCodeDao(String daeCode) {
		String sql = "";
		Connection  conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		String resInfo = "";
		
		try {
			sql = ((DaeElemSqlBuilder)this.sqlbuilder).getDaeNameByCodeSql();
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, daeCode);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				resInfo = rs.getString("C_DAE_CODE")+"_"+rs.getString("C_DAE_NAME");
			}
			

		} catch (Exception e) {
			// handle exception
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
//			if (null != rs) {
//				try {
//					rs.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
//			if (null != conn) {
//				try {
//					conn.close();
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}
	}
		
		return resInfo;
	
	}
	
	/**
	 * 根据条件获得必须的核算元素 By Jinghehe 
	 * @param paraMap
	 * @return
	 */
	public ArrayList<String> getDaeCodesByCondition(HashMap<String, String> paraMap) {
		ArrayList<String> dataList = new ArrayList<String>();
		String sql = "";
		Connection  conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		try {
			String daeCodeSub = paraMap.get("daeCodeSub");
			String daeCode = paraMap.get("daeCode");
			String loadMode = paraMap.get("C_LOAD_MODE");
			List<String> daiList = Arrays.asList(paraMap.get("daiCode").split(","));
			conn = this.loadNewConnection();	
			sql = ((DaeElemSqlBuilder)this.sqlbuilder).getDaeCodesByConditionSql(daiList.size());
			pstmt = conn.prepareStatement(sql);
			for (int i= 0;i< daiList.size();i++) 
			{
				pstmt.setString((4*i+1),daeCode);
				pstmt.setString((4*i+2),daeCodeSub);
				pstmt.setString((4*i+3),daiList.get(i));
				pstmt.setString((4*i+4), loadMode);
			}
			
			String daeTempCode =null;
			rs = pstmt.executeQuery();	
			while(rs.next()){
				daeTempCode = rs.getString("dae_code");
				dataList.add(daeTempCode);
			}
			
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return dataList;
	}

	/**
	 * 获得证券品种父类code
	 * @param daeCode
	 * @return
	 */
	public String getParentCodeByCode(String daeCode) {
		String sql = "";
		Connection  conn = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		String parentCode = "";
		try {
			conn = this.loadNewConnection();	
			sql = ((DaeElemSqlBuilder)this.sqlbuilder).getParentCodeByCodeSql();
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, daeCode);
			rs1 = pstmt1.executeQuery();	
			if(rs1.next()){
				parentCode = rs1.getString("C_DA_CODE_P");
			}
			
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		}finally{
			this.closeResultSetFinal(rs1);
			this.closeStatementFinal(pstmt1);
			this.releaseConnection(conn);
		}
		return parentCode;
	}
	
	/**
	 * 根据证券品种code判断是否含有子类没有 从而得出数据是否是明细 By Jinghehe
	 * @param code
	 * @return
	 */
	public String getChildCodeByCode(String daeCode) {
		String sql = "";
		Connection  conn = null;
		ResultSet rs1 = null;
		PreparedStatement pstmt1 = null;
		String parentCode = null;
		try {
			conn = this.loadNewConnection();	
			sql = ((DaeElemSqlBuilder)this.sqlbuilder).getChildCodeByCodeSql();
			pstmt1 = conn.prepareStatement(sql);
			pstmt1.setString(1, daeCode);
			rs1 = pstmt1.executeQuery();	
			if(rs1.next()){
				parentCode = rs1.getString("C_DA_CODE");
			}
			
		} catch (Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		}finally{
			this.closeResultSetFinal(rs1);
			this.closeStatementFinal(pstmt1);
			this.releaseConnection(conn);
		}
		return parentCode;
	}
	
	
}
