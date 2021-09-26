package com.yss.ams.base.information.modules.sys.dccury.service.impl;

import java.util.List;

import com.yss.ams.base.information.modules.sys.dccury.dao.DcCuryDao;
import com.yss.ams.base.information.modules.sys.dccury.dao.DcCurySqlBuilder;
import com.yss.ams.base.information.support.sys.dccury.service.IDcCuryQuyService;
import com.yss.ams.base.information.support.sys.dccury.service.IDcCuryService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * 国际货币服务类
 * @author 马向峰  拆分 2017.0527
 *
 */
@DefaultCacheRefresh(group = CacheGroup.DC)
public class DcCuryService extends ServiceBus<DcCuryService> implements IDcCuryService,IDcCuryQuyService {

	private DcCuryDao serviceDao = null;
	public DcCuryService() throws Exception{
		//serviceDao = new DcCuryDao(DbPoolFactory.getInstance().getPool(
				//YssConstant.DBSERVICE_NAME), new DcCurySqlBuilder());
		serviceDao = new DcCuryDao(DbPoolFactory.getInstance().getPool(), new DcCurySqlBuilder());
		dao = serviceDao;
		}

	/**
	 * Added by huangsq 20160728 STORY #32244 词汇资源可控制是否显示
	 * 重写根据主键更新对象方法
	 */
	@Override
	public String updateById(List<BasePojo> pojoList) {
		return super.updateById(pojoList);
	}
}
