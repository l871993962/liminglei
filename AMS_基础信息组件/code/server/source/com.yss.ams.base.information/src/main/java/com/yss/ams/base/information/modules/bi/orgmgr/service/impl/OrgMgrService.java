package com.yss.ams.base.information.modules.bi.orgmgr.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.orgmgr.dao.OrgMgrDao;
import com.yss.ams.base.information.modules.bi.orgmgr.dao.OrgMgrSqlBuilder;
import com.yss.ams.base.information.support.bi.orgmgr.service.IOrgMgrService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 结算会员服务类
 * @author 马向峰  拆分
 * @Date 20170531
 */
public class OrgMgrService extends ServiceBus<OrgMgrService> implements IOrgMgrService {

	private OrgMgrDao serviceDao = null;
	public OrgMgrService() throws Exception{
		serviceDao = new OrgMgrDao(DbPoolFactory.getInstance().getPool(), new OrgMgrSqlBuilder());
		dao = serviceDao;
		}

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-10
	 * Status : Add
	 * Comment: 获取所有结算会员代码
	 */
	public List<String> getAllMBRCodes(){
		return serviceDao.getAllMBRCodes();
	}

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 */
	@Override
	public QueryRes getPortRelaMember(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortRelaMember(paraMap, clazz);
			queryRes.setDataList(dataList);

			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,InformationActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("机构会员设置：查询组合关联客户编号出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
}
