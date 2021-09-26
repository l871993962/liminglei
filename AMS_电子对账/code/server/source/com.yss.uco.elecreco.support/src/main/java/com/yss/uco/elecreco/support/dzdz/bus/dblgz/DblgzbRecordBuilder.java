package com.yss.uco.elecreco.support.dzdz.bus.dblgz;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLWarning;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.support.dzdz.bus.dblgz.pojo.ErDblgzbElement;
import com.yss.uco.elecreco.support.dzdz.bus.gz.pojo.ErGzbElement;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

public class DblgzbRecordBuilder implements IRecordBuilder {
	private Logger logger = LogManager.getLogger(this.getClass());
	private IRecordSqlBuilder sqlBuilder = new DblgzSqlBuilder();

	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
		List<ErDblgzbElement> list = new ArrayList<ErDblgzbElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			////By Jinghehe 2015-8-15 工行对账模式修改
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
//			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
//			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
//					sqlBuilder);
			while (rs.next()) {
				ErDblgzbElement e = new ErDblgzbElement();
				//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
				e.setParaMap(kmMap);
				e.setC_PORT_CLS_CODE(rs.getString("C_PORT_CLS_CODE"));
				e.setC_ASS_CODE(root.getC_ASS_CODE());
				e = (ErDblgzbElement) ResultToPojoObject(rs, e);
				
				e.setC_KM_CODE(e.getKmCode(rs.getString("C_KM_CODE")));
				list.add(e);
				kmMap.put(e.getC_KM_CODE(), rs.getString("C_KM_CODE"));
			}
			
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
				logger.info("查询sql:" + sqlBuilder.getRecordSql());
				logger.info(String.format("参数(按参数顺序)：%s,%s", root.getC_ASS_CODE(), root.getC_SN()));
				DatabaseMetaData databaseMetaData = conn.getMetaData();
				if(databaseMetaData != null)
				{
					logger.info("数据库连接：" + databaseMetaData.getURL());
					logger.info("连接用户：" + databaseMetaData.getUserName());
					logger.info("数据库驱动名称：" + databaseMetaData.getDriverName());
					logger.info("数据库驱动版本：" + databaseMetaData.getDriverVersion());
					logger.info("数据库产品名称：" + databaseMetaData.getDatabaseProductName());
					logger.info("数据库产品版本：" + databaseMetaData.getDatabaseProductVersion());
					logger.info("底层数据库的主版本号" + databaseMetaData.getDatabaseMajorVersion());
					logger.info("底层数据库的次版本号" + databaseMetaData.getDatabaseMinorVersion());
				}
				if(rs.getWarnings() != null)
				{
					SQLWarning warnings = rs.getWarnings();
					logger.info(String.format("获取结果警告：%s", warnings.getMessage()), warnings.getCause());
				}
				if(stat.getWarnings() != null)
				{
					SQLWarning warnings = stat.getWarnings();
					logger.info(String.format("查询警告：%s", warnings.getMessage()), warnings.getCause());
				}
				logger.info("连接对象：" + conn.getClass().getName());
				logger.info("语句对象：" + stat.getClass().getName());
				logger.info("结果集对象：" + rs.getClass().getName());
			}
			
			root.setErDblgzbList(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	
	public Object ResultToPojoObject(ResultSet rs, Object pojo)
			throws Exception {
		PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
		PropertyDescriptor prop = null;
		DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
		String name = "";
		Object resValue = null;

		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			if (DaoAssistance.isSetValue(prop)) {
				name = prop.getName();

				if (pojo instanceof IEffectivable) {
				} else {
					if ("startUseDate".equals(name)
							|| "endUseDate".equals(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = this.sqlBuilder.getColumnNameByProperty(dbNameResolver, name);
				}
				catch(Exception ex){
				}
				if (!"".equals(columnName)) {
					try {
						resValue = rs.getObject(columnName);
					} catch (Exception e) {
						resValue = null;
//						throw new Exception(e.getMessage() + " : " + columnName);
					}

					if (resValue != null) {
						resValue = DaoAssistance.resultSetValueConvert(
								resValue, prop);

						try {
							prop.getWriteMethod().invoke(pojo, resValue);
						} catch (Exception e) {
							throw new Exception(e.getMessage() + " : "
									+ columnName);
						}
					}
				}
			}
		}

		return pojo;
	}
	@Override
	public XmlFile setRecords(Connection conn, XmlFile root) {
		List<ErGzbElement> list = new ArrayList<ErGzbElement>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			////By Jinghehe 2015-8-15 工行对账模式修改
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
				ErGzbElement e = (ErGzbElement) rsTools.ResultToPojoObject(rs,
						ErGzbElement.class);
				list.add(e);
			}
			
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			
			root.setErGzbList(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}

}
