package com.yss.ams.sec.information.modules.sv.base.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 票据基本信息 Dao层
 * @author Tangshifeng 2013-03-14
 *
 */
public class SecBasePjDao extends GeneralDao {

	private SecBasePjSqlBuilder pjBuiler = null;
	
	public SecBasePjDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.pjBuiler = (SecBasePjSqlBuilder)sqlBuilder;
	}

	/**
	 * 删除数据列表中的数据
	 * 删除条件:证券代码
	 * @param pojoList
	 * @param conn
	 * @throws SQLException
	 */
	public void deleteBySecCode(List<BasePojo> pojoList, Connection conn) throws SQLException {
		String sql = this.pjBuiler.delDateBySecCode();
		PreparedStatement pstmt = null;
		try{
			pstmt = conn.prepareStatement(sql);
			for(BasePojo basePojo : pojoList){
				pstmt.setString(1, ((SecBase)basePojo).getC_SEC_CODE());
				pstmt.addBatch();
			}
			
			pstmt.executeBatch();
		}catch(SQLException e ){
			
		}finally{
			// 关闭pstmt modified by wangpeixu 20170321
			DbFun.closeStatementFinal(pstmt);
		}
		
	}
}
