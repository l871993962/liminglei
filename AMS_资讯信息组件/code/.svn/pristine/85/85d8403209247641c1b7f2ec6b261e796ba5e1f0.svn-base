package com.yss.ams.sec.information.modules.pub.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.yss.ams.sec.information.support.modules.pub.pojo.SysInitItemBean;
//import com.yss.dayf.assetStats.pojo.SysInitItemBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.util.StringUtil;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
/**
 * 删除无用的import by lihaizhi 20130620
 */
public class DayfSysInitDao extends GeneralDao {
	private DayfSysInitSqlBuilder sqlBuilder = null;
	
	//防止报错，暂时添加getset方法
	public DayfSysInitSqlBuilder getSqlBuilder() {
		return sqlBuilder;
	}

	public void setSqlBuilder(DayfSysInitSqlBuilder sqlBuilder) {
		this.sqlBuilder = sqlBuilder;
	}

	public DayfSysInitDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = new DayfSysInitSqlBuilder();
	}

	public List<BasePojo> getInitData(HashMap<String, String> paraMap) {
		PreparedStatement pstmt = null;
		Connection conn = null;
		String strSql = null; // strSql sql查询语句
		ResultSet rs = null; // ResultSet 返回一个查询结果集
		ResultSetTools rsTools = null;

		String code = paraMap.get("C_DV_TYPE");
		List<BasePojo> dataList = new ArrayList<BasePojo>();

		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT ");
		buf.append(" 	a.C_DV_CODE AS C_DV_ITEM_CODE, ");
		buf.append(" 	a.C_DV_NAME AS C_DESC, "); // 这里改为用词汇名称
		buf.append(" 	1 AS N_CHECK_STATE ");
		buf.append(" FROM v_s_dv_voc a ");
		buf.append(" where C_DV_TYPE = ?");
		strSql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		try {
			conn = this.loadNewConnection();
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			pstmt = conn.prepareStatement(strSql);
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				SysInitItemBean sysBean = rsTools.ResultToBeanGeneric(rs,
						SysInitItemBean.class);
				sysBean.setAuditState(1);
				dataList.add(sysBean);
			}
		} catch (DataAccessException e) {
//			e.printStackTrace();
			logger.log("日终处理：参数初始化失败", e);
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("日终处理：参数初始化失败", e);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("日终处理：参数初始化失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);			
		}
		return dataList;
	}
}
