package com.yss.uco.elecreco.er.ergzb.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbDao;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbSqlBuilder;
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.service.IErGzbService;

public class ErGzbService extends ServiceBus<ErGzbService> implements IErGzbService {

	private ErGzbDao serviceDao = null;
	public ErGzbService() throws Exception{
		serviceDao = new ErGzbDao(DbPoolFactory.getInstance().getPool(), new ErGzbSqlBuilder());
		dao = serviceDao;
		}
	
	public List<ErGzb> getGzData(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		List<ErGzb> erGzbList = new ArrayList<ErGzb>();
		ErGzb erGzb = null;
		
		List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErGzb.class);
		if(null != dataList && !dataList.isEmpty()){
			for(BasePojo pojo : dataList){
				
				erGzb = (ErGzb) pojo;
				erGzbList.add(erGzb);
			}
		}
		
		return erGzbList;
	}
	
	@Override
	public List<String> getRealIndexCode(String dzCode, String zbCode) {
		List<String> indexCodeList = new ArrayList<String>();
		indexCodeList = serviceDao.getRealIndexCode(dzCode,zbCode);
		return indexCodeList;
	}

	@Override
	public String formatSSZBValue(String port, String d_trade, BigDecimal value) {
		return serviceDao.formatSSZBValue(port,d_trade,value);
	}

	@Override
	public HashMap<String, String> formatedData(String ports,HashMap<String, String> formatData) {
		return serviceDao.formatedData(ports,formatData);
	}
}
