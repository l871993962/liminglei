package com.yss.uco.elecreco.support.dzdz.bus.yue;

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

public class YuebRecordBuilder implements IRecordBuilder {
	private IRecordSqlBuilder sqlBuilder = new YueSqlBuilder();
	private Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
		List<ErYuebElement> list = new ArrayList<ErYuebElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			// //By Jinghehe 2015-8-15 工行对账模式修改
//			IDzParaService svc = YssServiceFactory.getInstance().createService(
//					IDzParaService.class);
//			DzPara para = svc.getParaByAssCode(root.getC_ASS_CODE());
			stat = conn.prepareStatement(sqlBuilder.getRecordSql());
			int index = 1;
			//wlx 20161116 BUG145118工商银行电子对账问题 不对工行对账模式特殊处理
//			stat.setString(index++, para.getC_DZ_MODE());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			
//			stat.setString(index++, para.getC_DZ_MODE());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
			
			stat.setString(index++, root.getC_ASS_CODE());
			stat.setString(index++, root.getC_SN());
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			while (rs.next()) {
				ErYuebElement e = (ErYuebElement) rsTools.ResultToPojoObject(
						rs, ErYuebElement.class);
				//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
				e.setParaMap(kmMap);
				e.setC_KM_CODE(e.getKmCode(rs.getString("C_KM_CODE")));
				list.add(e);
				kmMap.put(e.getC_KM_CODE(), rs.getString("C_KM_CODE"));
			}
			
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			
			root.setErYuebList(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
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
			// //By Jinghehe 2015-8-15 工行对账模式修改
//			IDzParaService svc = YssServiceFactory.getInstance().createService(
//					IDzParaService.class);
//			DzPara para = svc.getParaByAssCode(root.getC_ASS_CODE());
			stat = conn.prepareStatement(sqlBuilder.getRecordSql());
			int index = 1;
			//wlx 20161116 BUG145118工商银行电子对账问题 不对工行对账模式特殊处理
//			stat.setString(index++, para.getC_DZ_MODE());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			
//			stat.setString(index++, para.getC_DZ_MODE());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
//			stat.setString(index++, root.getC_ASS_CODE());
//			stat.setString(index++, root.getC_SN());
			
			stat.setString(index++, root.getC_ASS_CODE());
			stat.setString(index++, root.getC_SN());
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			while (rs.next()) {
				ErYuebElement e = (ErYuebElement) rsTools.ResultToPojoObject(
						rs, ErYuebElement.class);
				list.add(e);
				// list.add(getElement(rs));
			}
			root.setErYuebList(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}

	// private ErYuebElement getElement(ResultSet rs) throws SQLException{
	// ErYuebElement e = new ErYuebElement();
	// e.setC_KM_CODE(rs.getString("C_KM_CODE"));
	// e.setC_DC_CODE(rs.getString("C_DC_CODE"));
	// e.setN_AMOUNT_CREDIT(rs.getString("N_AMOUNT_CREDIT"));
	// e.setN_AMOUNT_DEBIT(rs.getString("N_AMOUNT_DEBIT"));
	// e.setN_AMOUNT_ENDBAL(rs.getString("N_AMOUNT_ENDBAL"));
	// e.setN_AMOUNT_STARTBAL(rs.getString("N_AMOUNT_STARTBAL"));
	// e.setN_D_TOLTAL_AMOUNT(rs.getString("N_D_TOLTAL_AMOUNT"));
	// e.setN_DETAIL(rs.getInt("N_DETAIL"));
	// e.setN_J_TOLTAL_AMOUNT(rs.getString("N_J_TOLTAL_AMOUNT"));
	// e.setN_ORIG_CREDIT(rs.getString("N_ORIG_CREDIT"));
	// e.setN_ORIG_DEBIT(rs.getString("N_ORIG_DEBIT"));
	// e.setN_ORIG_ENDBAL(rs.getString("N_ORIG_ENDBAL"));
	// e.setN_ORIG_STARTBAL(rs.getString("N_ORIG_STARTBAL"));
	// e.setN_PORT_CREDIT(rs.getString("N_PORT_CREDIT"));
	// e.setN_PORT_DEBIT(rs.getString("N_PORT_DEBIT"));
	// e.setN_PORT_ENDBAL(rs.getString("N_PORT_ENDBAL"));
	// e.setN_PORT_STARTBAL(rs.getString("N_PORT_STARTBAL"));
	// return e;
	// }
}
