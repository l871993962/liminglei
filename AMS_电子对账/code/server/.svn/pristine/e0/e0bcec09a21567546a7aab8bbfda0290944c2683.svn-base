package com.yss.uco.elecreco.er.erdblgz.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erdblgz.dao.ErDblgzbDao;
import com.yss.uco.elecreco.er.erdblgz.dao.ErDblgzbSqlBuilder;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.service.IErDblgzbService;

public class ErDblgzbService extends ServiceBus<ErDblgzbService> implements IErDblgzbService {

	private ErDblgzbDao serviceDao = null;
	public ErDblgzbService() throws Exception{
		serviceDao = new ErDblgzbDao(DbPoolFactory.getInstance().getPool(), new ErDblgzbSqlBuilder());
		dao = serviceDao;
	}
	
	public List<ErDblgzb> getGzData(HashMap<String, Object> paraMap) {
		List<ErDblgzb> erGzbList = new ArrayList<ErDblgzb>();
		ErDblgzb erGzb = null;
		
		List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErDblgzb.class);
		if(null != dataList && !dataList.isEmpty()){
			for(BasePojo pojo : dataList){
				
				erGzb = (ErDblgzb) pojo;
				erGzbList.add(erGzb);
			}
		}
		
		return erGzbList;
	}

	@Override
	public void insertDatas(List<ErDblgzb> list,Connection conn) {
		this.serviceDao.insertDatas(list, conn);
	}
	
}
