package com.yss.ams.sec.information.modules.sv.indexinfo.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.indexinfo.dao.IndexInfoDao;
import com.yss.ams.sec.information.modules.sv.indexinfo.dao.IndexInfoSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.indexinfo.service.IIndexInfoService;


/**
 * 
 * @author chenbo
 *2017-06-22
 *#42948 资讯信息管理组件化拆分
 */
public class IndexInfoService extends ServiceBus<IndexInfoService> implements
		IIndexInfoService {
	private IndexInfoDao serviceDao = null;

	public IndexInfoService() {
		serviceDao = new IndexInfoDao(DbPoolFactory.getInstance().getPool(),
				new IndexInfoSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes getPortRelaIndex(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortRelaIndex(paraMap, clazz);
			queryRes.setDataList(dataList);

			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,SecInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("指数基本信息模块：查询组合关联指数信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

}
