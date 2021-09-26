package com.yss.ams.sec.information.modules.mp.secequ.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquDao;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquService;
import com.yss.ams.sec.information.modules.mp.secequ.service.impl.SecEquService;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecPxService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;



/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 对价派息
 * 
 * @author ll 20121009
 */
public class SecPxService extends ServiceBus<SecPxService> implements
	ISecPxService {

	private SecEquDao serviceDao = null;

	public SecPxService() throws Exception {
		serviceDao = new SecEquDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecEquSqlBuilder());
		dao = serviceDao;
	}

	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		ISecEquService secEquService = null;
		QueryRes queryRes = new QueryRes();
		try {
			secEquService = new SecEquService();
			secEquService.setMenuId("sv_dividend");
			queryRes = secEquService.queryByCondition(paraMap, page);
			Context context = YssContextFactory.getInstance().getAppContext(SecInfoActivator.class);
			List<HeadKey> list = context.getListHeadMap("sv_dividend").getHeadKeyList();
			queryRes.setHeadKeyList(list);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("重写基类查询方法查询时出错！", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

}
