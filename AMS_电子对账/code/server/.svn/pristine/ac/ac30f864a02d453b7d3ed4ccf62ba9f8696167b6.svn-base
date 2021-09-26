package com.yss.uco.elecreco.support.dzdz.bus.result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.uco.elecreco.support.dzdz.bus.yue.pojo.ErYuebElement;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

public class ResultRecordBuilder implements IRecordBuilder{
	private IRecordSqlBuilder sqlBuilder = new ResultSqlBuilder();
	private Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
		List<ErYuebElement> list = new ArrayList<ErYuebElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sqlBuilder.getRecordSql());
			stat.setString(1, root.getC_ASS_CODE());
			stat.setString(2, root.getC_SN());
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			while (rs.next()) {
				ErYuebElement e = (ErYuebElement) rsTools.ResultToPojoObject(rs,
						ErYuebElement.class);
				list.add(e);
			}
			root.setErYuebList(list);
		}catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	@Override
	public XmlFile setRecords(Connection conn, XmlFile root) {
		List<ErYuebElement> list = new ArrayList<ErYuebElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			stat = conn.prepareStatement(sqlBuilder.getRecordSql());
			stat.setString(1, root.getC_ASS_CODE());
			stat.setString(2, root.getC_SN());
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			while (rs.next()) {
				ErYuebElement e = (ErYuebElement) rsTools.ResultToPojoObject(rs,
						ErYuebElement.class);
				list.add(e);
			}
			root.setErYuebList(list);
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	
}
