package com.yss.ams.base.information.modules.bi.mkt.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.mkt.dao.MktDao;
import com.yss.ams.base.information.modules.bi.mkt.dao.MktSqlBuilder;
import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.ams.base.information.support.bi.mkt.service.IMktService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 交易市场服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
@DefaultCacheRefresh(group = CacheGroup.MKT)
public class MktService extends ServiceBus<MktService> implements IMktService {

	private MktDao serviceDao = null;
	
	public MktService() throws Exception{
		serviceDao = new MktDao(DbPoolFactory.getInstance().getPool(), new MktSqlBuilder());
		dao = serviceDao;
		}
	
	/**
	 * 查询交易市场设置信息
	 * @param paraMap page queryMenuId
	 */
	public QueryRes selectByConditionExtend(HashMap<String, Object> paraMap,
			PageInation page, String queryMenuId) {
		QueryRes queryRes = new QueryRes();
		int recCount = 0;
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryMktExtendDataList(paraMap, page,this.bundleContext);

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,InformationActivator.class));
			
			recCount = serviceDao.queryMktExtendDataListCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(queryMenuId);
		} catch (Exception ex) {
			logger.log("交易市场设置：查询交易市场设置信息出粗", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 获取交易市场代码转换MAP用于界面展示
	 * 
	 * @param queryRes
	 */
	@Override
	protected void setShowConvertAssemble(QueryRes queryRes) throws Exception {
		super.setShowConvertAssemble(queryRes);
		serviceDao.buildTradeMKTConvert(queryRes);
	}

	/**
	 * 查询所有可用交易市场字典信息
	 * 
	 * @return
	 */
	public List<MarketVoc> getAllMkt(){
		return serviceDao.getAllMkt();
	}
	
	/**
	 * 检查相同地区是否存在相同的交易所
	 * 
	 * @param MktCode
	 *            待检查交易所代码
	 * @return String
	 */
	public String getCheckStatus(String MktCode){
		return serviceDao.getCheckStatus(MktCode);
	}
	
	/**
	 * 判断交易市场代码是否与清算机构重复<br>
	 * STORY36399【招商基金】【紧急】股票、债券、基金等交易流水界面增加“交易市场”字段供TB导出接口取对应的清算信息<br>
	 * @param mktCode 市场代码
	 * @return
	 */
	public String compareQsjg(String mktCode) {
		try {
			String a = "0";
			a = serviceDao.compareQsjg(mktCode);
			return a;
		} catch (Exception ex) {
			logger.log(ex.getMessage());
			throw new ServiceException("在词汇表清算机构中查询是否含有交易市场代码出错:"+ex.getMessage());
		}
	}
}
