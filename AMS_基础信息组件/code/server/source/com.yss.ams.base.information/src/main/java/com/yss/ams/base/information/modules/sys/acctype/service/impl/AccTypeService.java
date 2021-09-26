package com.yss.ams.base.information.modules.sys.acctype.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.modules.sys.acctype.dao.AccTypeDao;
import com.yss.ams.base.information.modules.sys.acctype.dao.AccTypeSqlBuilder;
import com.yss.ams.base.information.support.sys.acctype.pojo.AccType;
import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.ams.base.information.support.sys.acctype.service.IAccTypeService;
import com.yss.framework.db.DbPoolFactory;
//import com.yss.para.bi.acctype.dao.AccTypeDao;
//import com.yss.para.bi.acctype.dao.AccTypeSqlBuilder;
//import com.yss.para.bi.acctype.service.IAccTypeService;

public class AccTypeService extends ServiceBus<AccTypeService> implements
		IAccTypeService {
	private AccTypeDao serviceDao = null;

	public AccTypeService() throws Exception {
		//serviceDao = new AccTypeDao(DbPoolFactory.getInstance().getPool(
			//	YssConstant.DBSERVICE_NAME), new AccTypeSqlBuilder());
		serviceDao = new AccTypeDao(DbPoolFactory.getInstance().getPool(), new AccTypeSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * ===================
	 * 改变功能实现位置，迁移到估值
	 * ===================
	 * Author : ChenLong
	 * Date   : 2017-05-11
	 * Status : Add
	 * Comment: 增值税方案关联资产类别
	 * @param paraMap
	 * @return
	 */
//	public List<AccType> queryVATPlanRelaCls(HashMap<String, String> paraMap){
//		return serviceDao.queryVATPlanRelaCls(paraMap);
//	}
}
