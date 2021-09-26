package com.yss.ams.base.information.modules.sys.dsppara.service.impl;

import com.yss.ams.base.information.modules.sys.dsppara.dao.DspParaDao;
import com.yss.ams.base.information.modules.sys.dsppara.dao.DspParaSqlBuilder;
import com.yss.ams.base.information.support.sys.dsppara.service.IDspParaService;
//import com.yss.ams.base.information.support.sys.dsppara.service.IDspParaService;
import com.yss.framework.api.mvc.biz.ServiceBus;
//import com.yss.para.bi.dsppara.dao.DspParaDao;
//import com.yss.para.bi.dsppara.dao.DspParaSqlBuilder;
//import com.yss.para.bi.dsppara.service.IDspParaService;
import com.yss.framework.db.DbPoolFactory;

/**
 * 综合参数字典T_S_DSP_PARA service
 *
 */
public class DspParaService extends ServiceBus<DspParaService> implements IDspParaService{
	
	public DspParaService(){
		dao = new DspParaDao(DbPoolFactory.getInstance().getPool(),new DspParaSqlBuilder());
	}
}
