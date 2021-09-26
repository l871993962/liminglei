package com.yss.ams.sec.information.modules.mp.secequ.service.impl;


import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquDao;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecLtService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;




/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
/**
 * 证券流通
 * @author ll
 * 20121009
 */
public class SecLtService extends ServiceBus<SecLtService> implements
	ISecLtService {

	private SecEquDao serviceDao = null;

	public SecLtService() throws Exception {
		serviceDao = new SecEquDao(DbPoolFactory.getInstance().getPool(), new SecEquSqlBuilder());
		dao = serviceDao;
	}
//	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
//			PageInation page) {
//		ISecEquService secEquService = null;
//		QueryRes queryRes = null;
//		try {
//			secEquService = (ISecEquService) ServiceInstance.getInstance("ISecEquService");
//			queryRes = secEquService.queryByCondition(paraMap, page);
//			BEN_LIST_HEAD_DTL listHeadInfo = MvcContext.listHeadMap.get("securitycirculate");
//			List<BEN_HEAD_KEY> headKeyList = listHeadInfo.getHeadKeyList();
//			queryRes.setHeadKeyList(headKeyList);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//			queryRes.setOperRes(MvcConstant._Fault);
//		}
//		return queryRes;
//	}

}
