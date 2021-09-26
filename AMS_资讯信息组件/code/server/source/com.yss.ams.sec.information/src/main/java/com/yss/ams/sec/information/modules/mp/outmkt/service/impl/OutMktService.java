package com.yss.ams.sec.information.modules.mp.outmkt.service.impl;


import java.util.HashMap;
import java.util.List;







import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktDao;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktSqlBuilder;
import com.yss.ams.sec.information.modules.mp.secmkt.service.impl.SecMktService;
import com.yss.ams.sec.information.support.modules.mp.outmkt.service.IOutMktService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.data.mp.outmkt.service.IOutMktService;
//import com.yss.data.mp.secmkt.dao.SecMktDao;
//import com.yss.data.mp.secmkt.dao.SecMktSqlBuilder;
//import com.yss.data.mp.secmkt.service.ISecMktService;
//import com.yss.data.mp.secmkt.service.impl.SecMktService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;

/**
 * 场外行情
 * @author ll
 * 20121009
 */
public class OutMktService extends ServiceBus<OutMktService> implements
		IOutMktService {

	private SecMktDao serviceDao = null;

	public OutMktService() throws Exception {
		serviceDao = new SecMktDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecMktSqlBuilder());
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
			marketValueService.setMenuId("sv_outmarketvalue");
			queryRes = marketValueService.queryByCondition(paraMap, page);
			Context context = YssContextFactory.getInstance().getAppContext(SecInfoActivator.class);
			List<HeadKey> list = context.getListHeadMap("sv_outmarketvalue").getHeadKeyList();
			queryRes.setHeadKeyList(list);

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券行情查询时失败!", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * 根据证券代码获取付息公式
	 * Parameters: secCode 证券代码
	 * Returns: 证券代码对应的付息公式
	 */
	@Override
	public String getSYLX(String secCode) {
		String sylx = "";
		try {
			sylx =  serviceDao.getSYLX(secCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log("失败!", e);
		}
		return sylx;
	}

	/**
	 * BUG #318348 多行情同时存在  add by zmk 2020-06-15
	 * 检查数据库中是否已有证券内码、行情日期、行情分类、行情来源相同的数据
	 * @param secCode 证券内码
	 * @param d_mkt 行情日期
	 * @param mktCls 行情分类
	 * @param dvPlat 行情来源
	 * @return
	 * @throws Exception
	 */
	@Override
	public String checkDuplicate(String secCode, String d_mkt, String mktCls, String dvPlat) throws Exception {
		return serviceDao.checkDuplicate(secCode, d_mkt, mktCls, dvPlat);
	}
}
