package com.yss.uco.elecreco.er.eryeb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.uco.elecreco.er.eryeb.dao.ErYebDao;
import com.yss.uco.elecreco.er.eryeb.dao.ErYebSqlBuilder;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;
import com.yss.uco.elecreco.er.eryeb.service.IErYebService;

public class ErYebService extends ServiceBus<ErYebService> implements IErYebService {

	private ErYebDao serviceDao = null;
	public ErYebService() throws Exception{
		serviceDao = new ErYebDao(DbPoolFactory.getInstance().getPool(), new ErYebSqlBuilder());
		dao = serviceDao;
		}
	
	public List<ErYeb> getYeData(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		List<ErYeb> erYebList = new ArrayList<ErYeb>();;
		ErYeb erYeb = null;
		this.setMenuId("dzBbInfo");
		List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErYeb.class);
		if(null != dataList && !dataList.isEmpty()){
			for(BasePojo pojo : dataList){
				erYeb = (ErYeb) pojo;
				erYebList.add(erYeb);
			}
		}
		
		return erYebList;
	}
	
	/**
	 * ��������ȡ fsn
	 * @param fsn
	 * @param date 
	 * @param portCode
	 * @return
	 */
	public Map<String,String> getKmMap(String fsn, String date){
		return serviceDao.getKmMap(fsn, date);
	}
}
