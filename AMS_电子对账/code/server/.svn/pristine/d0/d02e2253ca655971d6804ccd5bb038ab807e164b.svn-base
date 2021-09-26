package com.yss.uco.elecreco.er.erinfostate.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erinfostate.dao.ErStepStateDao;
import com.yss.uco.elecreco.er.erinfostate.dao.ErStepStateSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.service.IErStepStateService;

public class ErStepStateService extends ServiceBus<ErStepStateService> implements IErStepStateService {
	
	private ErStepStateDao serviceDao = null; 
	
	public ErStepStateService() {
		serviceDao = new ErStepStateDao(DbPoolFactory.getInstance().getPool(),
				new ErStepStateSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public String insertPojo(ErStepState erStatePojo) {
		return serviceDao.insertPojo(erStatePojo);
	}
	
	public ErStepState buildPojo(String assCode,String fsn,String c_FILE_TYPE,String c_RPT_TYPE,String c_STATE,String d_DATE,String errInfo){
		ErStepState state = new ErStepState();
		state.setC_ASS_CODE(assCode);
		state.setC_SN(fsn);//唯一编号
		state.setC_FILE_TYPE(c_FILE_TYPE);
		state.setC_RPT_TYPE(c_RPT_TYPE);
		state.setC_STEP_DATE(YssFun.formatDatetime(new Date()));
		state.setC_STATE(c_STATE);
		state.setD_DATE(d_DATE);
		state.setErrInfo(errInfo);
		return state;
	}
	
	public List<BasePojo> queryListByTypes(HashMap<String, String> paraMap){
		return serviceDao.queryListByTypes(paraMap);
	}
}
