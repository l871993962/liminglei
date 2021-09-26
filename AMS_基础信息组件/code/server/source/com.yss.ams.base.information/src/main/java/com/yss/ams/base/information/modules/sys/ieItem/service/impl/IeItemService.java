package com.yss.ams.base.information.modules.sys.ieItem.service.impl;

import java.util.List;

import com.yss.ams.base.information.modules.sys.ieItem.dao.IeItemDao;
import com.yss.ams.base.information.modules.sys.ieItem.dao.IeItemSqlBuilder;
import com.yss.ams.base.information.support.sys.ieItem.pojo.IeItem;
import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemService;
//import com.yss.ams.base.information.support.sys.ieItem.service.IIeItemService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
//import com.yss.para.bi.ieItem.dao.IeItemDao;
//import com.yss.para.bi.ieItem.dao.IeItemSqlBuilder;
//import com.yss.para.bi.ieItem.service.IIeItemService;

/**
 * 收支项目字典表T_S_IE_ITEM  Service
 *
 */
@DefaultCacheRefresh(group = CacheGroup.IEITEM)
public class IeItemService extends ServiceBus<IeItemService> implements IIeItemService {

	private IeItemDao serviceDao = null;
	public IeItemService() throws Exception{
		serviceDao = new IeItemDao(DbPoolFactory.getInstance().getPool(), new IeItemSqlBuilder());
		dao = serviceDao;
		}

	public List<IeItem> getAllDataList() throws Exception{
		return serviceDao.getAllDataList();
	}
	
}
