package com.yss.ams.visaval.support.service;

import java.util.List;

import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;

@GenericPojo(pojo=AdvAlgo.class)
public interface IVisAlgoDataService extends IControlDataService,IKeyConvertDataService,IDataServiceForCache,IServiceBus {
	
	/**
	 * Fixed by huangsq 20160729 BUG #135169 算法公式重新导入后不启作用<br />
	 * 重新加载缓存
	 */
	public void reloadCache();

	/**
	 * add by wangtangyao 20160804 BUG #128486 【紧急】【广发证券】导入分级产品参数公式无效
	 * 由于跑业务调用算法取得是Python中存储的缓存而不是算法缓存中取，所以只刷新算法缓存无用。此处导入更新对应算法的Python缓存
	 */
	public void updateimportAlgo(List<String> list);
	
	/**
	 * 根据配置文件中的SQL查出参数
	 * @param sql
	 * @return
	 */
	public List<ParamFromSql> getParamFromSql(String sql);
	
	public BasePojo getAlgoByCode(String code);
	
}
