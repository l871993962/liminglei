package com.yss.ams.base.information.modules.sys.convertdict.zhzd.service.impl;

import java.util.HashMap;

import com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao.ZhzdDao;
import com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao.ZhzdSqlBuilder;
import com.yss.ams.base.information.support.sys.convertdict.zhzd.service.IZhzdService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

public class ZhzdService extends ServiceBus<ZhzdService> implements IZhzdService{
	private ZhzdDao zhzdDao = null;

	public ZhzdService() throws Exception {
		
		zhzdDao = new ZhzdDao(DbPoolFactory.getInstance().getPool(), new ZhzdSqlBuilder());
		dao = zhzdDao;
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = super.queryByCondition(paraMap, page);
		////第一次打开界面时不显示B区list数据
		//if(null == paraMap.get("C_GROUP_CODE")){
		//	List<BasePojo> basePojo = new ArrayList<BasePojo>();
		//	queryRes.setDataList(basePojo);
		//}
		return queryRes;
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap) {
		QueryRes queryRes = super.queryByCondition(paraMap);
		////第一次打开界面时不显示B区list数据
		//if(null == paraMap.get("C_GROUP_CODE")){
		//	List<BasePojo> basePojo = new ArrayList<BasePojo>();
		//	queryRes.setDataList(basePojo);
		//}
		return queryRes;
	}
}
