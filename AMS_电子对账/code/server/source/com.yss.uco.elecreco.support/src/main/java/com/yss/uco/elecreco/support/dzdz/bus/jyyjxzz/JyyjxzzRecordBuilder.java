package com.yss.uco.elecreco.support.dzdz.bus.jyyjxzz;

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
import com.yss.uco.elecreco.support.dzdz.bus.lr.pojo.ErLrElement;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

public class JyyjxzzRecordBuilder implements IRecordBuilder {

	private Logger logger = LogManager.getLogger(this.getClass());
	private IRecordSqlBuilder sqlBuilder = new JyyjxzzSqlBuilder();
	
	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
		List<ErLrElement> list = new ArrayList<ErLrElement>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		//BUG296998电子对账生成财务报表数据时期初日期和期末日期取值错误
		String startDate = "";
		String endDate = "";
		try {
//			IDzParaService svc = YssServiceFactory.getInstance().createService(IDzParaService.class);
//			DzPara para = svc.getParaByAssCode(root.getC_ASS_CODE());
			pst = conn.prepareStatement(sqlBuilder.getRecordSql());
			int index = 1;
			// 这里替换问号
			pst.setString(index++, root.getC_ASS_CODE());
			pst.setString(index++, root.getC_SN());
			pst.setString(index++, root.getC_RPT_TYPE());
			
			rs = pst.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);
			while(rs.next()) {
				ErLrElement e = (ErLrElement) rsTools.ResultToPojoObject(rs, ErLrElement.class);
				list.add(e);
				startDate = rs.getString("D_START_DATE");
				endDate = rs.getString("D_END_DATE");
			}
			if(list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			root.setErLrList(list);
			root.setD_START_DATE(startDate);
			root.setD_END_DATE(endDate);
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return root;
	}

	@Override
	public XmlFile setRecords(Connection conn, XmlFile root) {
		List<ErLrElement> list = new ArrayList<ErLrElement>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
//			IDzParaService svc = YssServiceFactory.getInstance().createService(IDzParaService.class);
//			DzPara para = svc.getParaByAssCode(root.getC_ASS_CODE());
			pst = conn.prepareStatement(sqlBuilder.getRecordSql());
			int index = 1;
			// 这里替换问号
			pst.setString(index++, root.getC_ASS_CODE());
			pst.setString(index++, root.getC_SN());
			pst.setString(index++, root.getC_RPT_TYPE());
			
			rs = pst.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlBuilder);
			while(rs.next()) {
				ErLrElement e = (ErLrElement) rsTools.ResultToPojoObject(rs, ErLrElement.class);
				list.add(e);
			}
			if(list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			root.setErLrList(list);
		}  catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return root;
	}

}
