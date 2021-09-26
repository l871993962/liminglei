package com.yss.ams.base.information.modules.sys.sclindex.service.impl;

//import com.yss.bundle.activator.UcoActivator;
import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.sclindex.dao.SClIndexDao;
import com.yss.ams.base.information.modules.sys.sclindex.dao.SClIndexSqlBuilder;
import com.yss.ams.base.information.support.sys.sclindex.service.ISClIndexService;
//import com.yss.ams.base.information.support.sys.sclindex.service.ISClIndexService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.para.cl.sclindex.dao.SClIndexDao;
//import com.yss.para.cl.sclindex.dao.SClIndexSqlBuilder;
//import com.yss.para.cl.sclindex.service.ISClIndexService;

/**
 * 合规指标项目字典T_S_CL_INDEX Service
 *
 */
public class SClIndexService extends ServiceBus<SClIndexService> implements ISClIndexService {
	
	private SClIndexDao serviceDao = null;
	
	public  SClIndexService()throws Exception{
		
		serviceDao = new SClIndexDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SClIndexSqlBuilder());
		dao = serviceDao;
	}

	
	
	
}
