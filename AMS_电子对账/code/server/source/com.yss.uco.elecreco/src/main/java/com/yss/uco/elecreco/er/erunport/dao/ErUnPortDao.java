package com.yss.uco.elecreco.er.erunport.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

public class ErUnPortDao extends GeneralDao {


	public ErUnPortDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public String deletByPortCodes(List<String> portCodeList) {
		Connection conn = null;
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
		try{
			conn = this.loadNewConnection();
			sql.append(" delete from "+sqlbuilder.getTableName(dbNameResolver));
			sql.append(" where C_PORT_CODE in (");
			for (int i = 0; i < portCodeList.size(); i++) {
				sql.append("?");
				if(i<portCodeList.size() - 1){
					sql.append(",") ;
				}
			}
			sql.append(")");
			pst = conn.prepareStatement(sql.toString());
			for (int i = 0; i < portCodeList.size(); i++) {
				pst.setString(i+1, portCodeList.get(i));
			}
			pst.executeUpdate();
		} catch(Exception e) {
			throw new DataAccessException("删除失败：", e);
		} finally {
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return "";
	}
	/**
	 * 过滤掉已经做过其他对账的组合代码
	 * BUG244963电子对账管理界面总览分页勾选数据点击其他对账可以保存多条其他对账数据
	 * @param portCodeList
	 * @return
	 */
	public Set<String> filterUnPortCodes(List<String> portCodeList)
	{
		Set<String> set = new HashSet<String>();
		if(portCodeList == null || portCodeList.size() == 0)
		{
			return set;
		}
		set.addAll(portCodeList);
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			pst = conn.prepareStatement(((ErUnPortSqlBuilder)this.sqlbuilder).filterUnPortCodes());
			pst.setArray(1, DbFun.sqlOverLongCondition(portCodeList.toArray(new String[portCodeList.size()]), conn));
			rs = pst.executeQuery();
			while(rs.next())
			{
				set.remove(rs.getString(1));
			}
		} catch(Exception e) {
			throw new DataAccessException("删除失败：", e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return set;
	}
	

	/**
	 * 判断是其他对账还是电子对账
	 * @param portCode
	 * @return
	 */
	public boolean queryByCode(String portCode) {
		Connection conn = null;
		PreparedStatement pst = null;
		String sql = null;
		ResultSet rs=null;	
		try{
			conn = this.loadNewConnection();
			 sql= " select C_IDEN,C_PORT_CODE,C_OPER_BY,C_OPER_TIME from T_D_ER_UN_PORT where C_PORT_CODE= ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			 rs = pst.executeQuery();
			 if(rs.next()){
					//其他对账
					return true;	
				}else{
					//电子对账
					return false;
				}
		} catch(Exception e) {
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}	
	}

}
