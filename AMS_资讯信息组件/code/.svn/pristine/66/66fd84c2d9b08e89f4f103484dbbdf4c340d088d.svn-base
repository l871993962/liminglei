package com.yss.ams.sec.information.modules.mp.secequ.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquDao;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquService;
import com.yss.ams.sec.information.modules.mp.secequ.service.impl.SecEquService;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecPrePubService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;





/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecPrePubService extends ServiceBus<SecPrePubService> implements
		ISecPrePubService {

	private SecEquDao serviceDao = null;

	public SecPrePubService() throws Exception {
		serviceDao = new SecEquDao(DbPoolFactory.getInstance().getPool(),
				new SecEquSqlBuilder());
		dao = serviceDao;
		setMenuId("sv_securityPrePublish");
	}

	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		ISecEquService secEquService = null;
		QueryRes queryRes = new QueryRes();
		try {
			secEquService = new SecEquService();
			secEquService.setMenuId("sv_securityPrePublish");
			queryRes = secEquService.queryByCondition(paraMap, page);
			Context context = YssContextFactory.getInstance().getAppContext(SecInfoActivator.class);
			List<HeadKey> list = context.getListHeadMap("sv_securityPrePublish").getHeadKeyList();
			queryRes.setHeadKeyList(list);
			fillResultObject(queryRes, queryRes.getDataList(), queryRes.getDataList().size(), page, "sv_securityPrePublish");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("重写基类查询方法查询时出错！", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
}
