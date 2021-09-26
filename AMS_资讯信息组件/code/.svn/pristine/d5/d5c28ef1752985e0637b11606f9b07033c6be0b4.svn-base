package com.yss.ams.sec.information.modules.mp.ckmkt.service.impl;



import java.util.HashMap;

import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktDao;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktSqlBuilder;
import com.yss.ams.sec.information.modules.mp.secmkt.service.impl.SecMktService;
import com.yss.ams.sec.information.support.modules.mp.ckmkt.service.ICkMktService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
//import com.yss.data.mp.ckmkt.service.ICkMktService;
//import com.yss.data.mp.secmkt.dao.SecMktDao;
//import com.yss.data.mp.secmkt.dao.SecMktSqlBuilder;
//import com.yss.data.mp.secmkt.service.ISecMktService;
//import com.yss.data.mp.secmkt.service.impl.SecMktService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
/**
 * 存款利率行情
 * @author ll
 *
 */
public class CkMktService extends ServiceBus<CkMktService> implements
		ICkMktService {

	public GeneralDao serviceDao = null;

	public CkMktService() throws Exception {
		serviceDao = new SecMktDao(DbPoolFactory.getInstance().getPool(), new SecMktSqlBuilder());
		dao = serviceDao;
	
	}
	
	/**
	 * 调证券行情的查询方法
	 */
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		ISecMktService marketValueService;
		QueryRes queryRes = new QueryRes();
		try {
			marketValueService = new SecMktService();
			marketValueService.setMenuId("sv_depositinterest");
			queryRes = marketValueService.queryByCondition(paraMap, page);
//			BEN_LIST_HEAD_DTL listHeadInfo = MvcContext.listHeadMap.get("sv_depositinterest");
//			List<BEN_HEAD_KEY> headKeyList = listHeadInfo.getHeadKeyList();
//			queryRes.setHeadKeyList(headKeyList);
//			List<HeadKey> list = ContextFactory.getContext().getListHeadMap("sv_depositinterest").getHeadKeyList();
//			queryRes.setHeadKeyList(list);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券行情查询时失败!", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

}
