package com.yss.uco.elecreco.support.dzdz.bus.gz;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.sec.information.support.modules.sv.base.cache.service.ISecbaseCacheDataService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.DaoAssistance;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.support.dzdz.bus.gz.pojo.ErGzbElement;
import com.yss.uco.elecreco.support.dzdz.bus.gz.pojo.ErXmlGzb;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

public class GzbRecordBuilder implements IRecordBuilder {
	private Logger logger = LogManager.getLogger(this.getClass());
	private IRecordSqlBuilder sqlBuilder = new GzSqlBuilder();

	@Override
	public XmlRoot setRecords(Connection conn, XmlRoot root, Map<String, String> kmMap) {
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
//			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
//			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
//					sqlBuilder);
			while (rs.next()) {
				ErGzbElement e = new ErGzbElement();
				//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
				e.setParaMap(kmMap);
				e.setC_PORT_CLS_CODE(rs.getString("C_PORT_CLS_CODE"));
				e.setC_ASS_CODE(root.getC_ASS_CODE());
				e = (ErGzbElement) ResultToPojoObject(rs, e);
				
				e.setC_KM_CODE(e.getKmCode(rs.getString("C_KM_CODE")));
				if(!exceptKM(rs.getString("C_KM_CODE"), kmMap)){
					list.add(e);
					kmMap.put(e.getC_KM_CODE(), rs.getString("C_KM_CODE"));
				}
				
	
				e.setC_SN(rs.getString("C_SN"));
			}
			
			if (list.size() == 0) {
				logger.debug(root.getC_SN() + ":" + root.getC_ASS_CODE() + "没有明细数据！");
			}
			
			root.setErGzbList(list);
			saveXmlGzData(list);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	
	/**
	 * STORY #103644 华夏基金-新增托管行电子对账结果数据查询视图 
	 * @param list
	 */
	private void saveXmlGzData(List<ErGzbElement> list){
		try {
			IErXmlGzbService erXmlGzbService = YssServiceFactory.getInstance().createService(IErXmlGzbService.class);
			List<BasePojo> xmlData = new ArrayList<BasePojo>();
			ErXmlGzb data = null;
			String sn = "";
			if(null != xmlData){
				sn = list.get(0).getC_SN();
				for (ErGzbElement erGzbElement : list) {
					data = new ErXmlGzb();
					data.setC_SN(erGzbElement.getC_SN());
					data.setC_KM_CODE(erGzbElement.getC_KM_CODE());
					data.setC_KM_NAME(erGzbElement.getC_KM_NAME());
					data.setC_PORT_CLS_CODE(erGzbElement.getC_PORT_CLS_CODE());
					data.setN_AMOUNT(erGzbElement.getN_AMOUNT());
					data.setN_CB_JZ_BL(erGzbElement.getN_CB_JZ_BL());
					data.setN_DETAIL(erGzbElement.getN_DETAIL());
					data.setN_PORT_COST(erGzbElement.getN_PORT_COST());
					data.setN_PORT_IV(erGzbElement.getN_PORT_IV());
					data.setN_PORT_MV(erGzbElement.getN_PORT_MV());
					data.setN_QUOT_LOGO(erGzbElement.getN_QUOT_LOGO());
					data.setN_SZ_JZ_BL(erGzbElement.getN_SZ_JZ_BL());
					data.setN_VA_PRICE(erGzbElement.getN_VA_PRICE());
					xmlData.add(data);
				}
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("C_SN", sn);
			String count = erXmlGzbService.queryDataTotal(paraMap);
			if(count != null && Integer.parseInt(count) > 0){
				erXmlGzbService.deleteBySn(sn);
			}
			erXmlGzbService.insert(xmlData);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**
	 * STORY #94514 【招商财富】无法与托管行核对协议回购明细科目 
	 * 是：发送数据时，排除估值表中协议回购代码的数据
	 * 否：系统数据正常加载和显示（系统现有规则）
	 * @param e
	 * @param kmMap
	 */
	private boolean exceptKM(String kmCode, Map<String, String> kmMap){
		boolean bool = false;
		if(!StringUtil.IsNullOrEmpty(kmCode) && kmMap != null && "1".equalsIgnoreCase(kmMap.get("DZ_BB_DZDZ_BFSHGMX"))){
			String s[] = kmCode.split("\\.");
			String secCode = "";
			for (int i = 0; i < s.length; i++) {
				if (s[i].contains(" ")) {
					secCode = s[i];
				}
			}
			
			ISecbaseCacheDataService secBaseService = YssServiceFactory.getInstance().createService(ISecbaseCacheDataService.class);
			SecBase sec = secBaseService.getCacheByKey(secCode);
			if(null != sec && "HG_ZYS_XY_ZQ".equalsIgnoreCase(sec.getC_SEC_VAR_CODE())){
				bool = true;
			}
		}
		return bool;
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
					if ("startUseDate".equalsIgnoreCase(name)
							|| "endUseDate".equalsIgnoreCase(name)) {
						continue;
					}
				}
				String columnName = "";
				try{
					columnName = this.sqlBuilder.getColumnNameByProperty(dbNameResolver, name);
				}
				catch(Exception ex){
				}
				if (!"".equalsIgnoreCase(columnName)) {
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
