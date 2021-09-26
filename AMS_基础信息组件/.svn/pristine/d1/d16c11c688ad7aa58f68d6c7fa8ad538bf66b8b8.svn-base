package com.yss.ams.base.information.modules.bi.orgmgr.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 机构结算会员设置 dao
 * @author 马向峰 拆分
 * @Date 20170531
 *
 */
public class OrgMgrDao extends GeneralDao {

	public OrgMgrDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-10
	 * Status : Add
	 * Comment: 获取所有结算会员代码
	 * @return
	 */
	public List<String> getAllMBRCodes(){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			String sql = ((OrgMgrSqlBuilder)this.sqlbuilder).getAllMBRCodesSQL();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("c_mbr_code"));
			}
		}catch(Exception ex){
			throw new BusinessException("获取所有结算会员代码失败!" + ex.getMessage(),ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> getPortRelaMember(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = ((OrgMgrSqlBuilder)sqlbuilder).getPortRelaMember(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paraMap.get("ARRAY_C_PORT_CODE").toString());

			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				BasePojo t = rsTools.ResultToBean(rs, clazz);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
}
