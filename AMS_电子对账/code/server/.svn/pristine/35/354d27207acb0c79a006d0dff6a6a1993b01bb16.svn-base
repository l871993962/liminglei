package com.yss.uco.elecreco.support.dzdz.bus.km;

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
import com.yss.uco.elecreco.support.dzdz.bus.km.pojo.ErKmElement;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

public class KmRecordBuilder implements IRecordBuilder{
	private Logger logger = LogManager.getLogger(this.getClass());
	private IRecordSqlBuilder sqlBuilder = new KmSqlBuilder();
	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
		List<ErKmElement> list = new ArrayList<ErKmElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
//			IDzParaService svc = YssServiceFactory.getInstance().createService(IDzParaService.class);
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
				ErKmElement e = (ErKmElement) rsTools.ResultToPojoObject(rs,
						ErKmElement.class);
				//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
				e.setParaMap(kmMap);
				e.setC_KM_CODE(e.getKmCode(rs.getString("C_KM_CODE")));
				list.add(e);
				kmMap.put(e.getC_KM_CODE(), rs.getString("C_KM_CODE"));
			}
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			root.setErKmList(list);
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
		List<ErKmElement> list = new ArrayList<ErKmElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
//			IDzParaService svc = YssServiceFactory.getInstance().createService(IDzParaService.class);
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
				ErKmElement e = (ErKmElement) rsTools.ResultToPojoObject(rs,
						ErKmElement.class);
				list.add(e);
			}
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			root.setErKmList(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	
}
