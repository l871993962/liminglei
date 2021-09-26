package com.yss.ams.sec.information.modules.mp.indexmp.service.impl;

import com.yss.ams.sec.information.modules.mp.indexmp.dao.IndexMpDao;
import com.yss.ams.sec.information.modules.mp.indexmp.dao.IndexMpSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.indexmp.service.IIndexMpService;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
/**
 * 指数行情资料 普通服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class IndexMpService extends ServiceBus<IndexMpService> implements
		IIndexMpService {

	private IndexMpDao serviceDao = null;

	public IndexMpService() {
		serviceDao = new IndexMpDao(DbPoolFactory.getInstance().getPool(),
				new IndexMpSqlBuilder());
		dao = serviceDao;
	}

}
