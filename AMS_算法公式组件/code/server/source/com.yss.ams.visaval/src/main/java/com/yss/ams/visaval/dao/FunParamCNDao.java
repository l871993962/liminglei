package com.yss.ams.visaval.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * Author : ChenLong
 * Date   : 2017-08-22
 * Status : Add
 * Comment: 函数参数中英文转换
 */
public class FunParamCNDao extends GeneralDao{

	public FunParamCNDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 获取中文所对应的英文代码
	 * @param sql  中文所在的表执行SQL
	 * @param name 需要翻译的中文
	 * @return
	 */
	public String getCode(String sql, String name){
		String code = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()){
				code = rs.getString("code");
			}
		}catch(Exception ex){
			throw new BusinessException("根据中文获取英文代码失败!" + ex.getMessage(), ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		
		return code;
	}
}
