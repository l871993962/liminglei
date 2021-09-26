package com.yss.ams.base.information.modules.bi.accountTreeB.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.bi.accountTreeB.pojo.AccountTreeB;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.PojoUtils;

public class AccountTreeBDao extends GeneralDao {
	
	private AccountTreeBSqlBuilder builder = null;
	
	public AccountTreeBDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (AccountTreeBSqlBuilder) sqlBuilder;
	}

	public List<BasePojo> getDefaultTreeViewData(String nodeCodeP) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			
			if("DEFAUTL_TGR".equals(nodeCodeP)){
				sql = builder.getDefaultTGHTreeViewData();
			}else if("DEFAUTL_GLR".equals(nodeCodeP)){
				sql = builder.getDefaultGLRTreeViewData();
			}else if("DEFAUTL_TX".equals(nodeCodeP)){
				// TODO 还未有条线和项目属性未确定
//				sql = builder.getDefaultTXTreeViewData();
			}else if("DEFAUTL_XM".equals(nodeCodeP)){
//				sql = builder.getDefaultXMTreeViewData();
			}
			if(!StringUtil.IsNullOrEmptyT(sql)){
				pstmt = conn.prepareStatement(sql);
//				pstmt.setString(1, nodeCodeP);
				rs = pstmt.executeQuery();

				BasePojo pojo = (BasePojo) AccountTreeB.class.newInstance();
				PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
				while (rs.next()) {
					BasePojo t = setResultSet(rsTools,rs, AccountTreeB.class, props); // 提供可以重写的方法byleeyu20130420 
					getConvertKey(props,t);
					pojoList.add(t);
				}
				
				AccountTreeB others = new AccountTreeB();
				others.setC_NODE_CODE("OTHERS");
				others.setC_NODE_CODE_P("[root]");
				others.setC_OPEN_ACC_NAME("其他");
				others.setAuditState(1);
				pojoList.add(others);
			}
			conn.commit();
			conn.setAutoCommit(true);
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
