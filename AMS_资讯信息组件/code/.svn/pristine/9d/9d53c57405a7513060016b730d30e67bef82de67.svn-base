package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseQqDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseQqSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQqService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 期权品种信息普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseQqService extends ServiceBus<SecBaseService> implements ISecBaseQqService {

	private SecBaseDao serviceDao = null;
	private SecBaseQqDao serviceQqDao = null;
	public SecBaseQqService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseQqSqlBuilder());
		serviceQqDao = new SecBaseQqDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseQqSqlBuilder());
		dao = serviceDao;
		}
	
	@Override
	public String deleteById(List<BasePojo> pojoList) {
		String result = super.deleteById(pojoList);
		for(BasePojo basePojo : pojoList){
			//add by liyanjun 20150116删除期权品种信息时，级联删除相应的期权保证金数据
			String cSecCode = ((SecBase)basePojo).getC_SEC_CODE();
			serviceQqDao.deleteBzjBySec(cSecCode);
		}
		
		return result;
	}

}
