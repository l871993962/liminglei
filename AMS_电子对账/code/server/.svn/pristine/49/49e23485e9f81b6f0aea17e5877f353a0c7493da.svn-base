package com.yss.uco.elecreco.er.erjzcbdb.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erjzcbdb.dao.ErJzcbdbDao;
import com.yss.uco.elecreco.er.erjzcbdb.dao.ErJzcbdbSqlBuilder;
import com.yss.uco.elecreco.er.erjzcbdb.pojo.ErJzcbdb;
import com.yss.uco.elecreco.er.erjzcbdb.service.IErJzcbdbService;

public class ErJzcbdbService extends ServiceBus<ErJzcbdbService> implements IErJzcbdbService {

	private ErJzcbdbDao serviceDao = null;
	public ErJzcbdbService() throws Exception {
		serviceDao = new ErJzcbdbDao(DbPoolFactory.getInstance().getPool(),new ErJzcbdbSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 根据条件查询电子对账所有者权益（基金净值）变动信息
	 */
	public List<ErJzcbdb> getErJzcbdbData(HashMap<String, Object> paraMap) {
		List<ErJzcbdb> erPojos = new ArrayList<ErJzcbdb>();
		ErJzcbdb erPojo = null;
		if (!paraMap.isEmpty()) {
			List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErJzcbdb.class);
			if (null != dataList && !dataList.isEmpty()) {
				for (BasePojo pojo : dataList) {
					erPojo = (ErJzcbdb) pojo;
					erPojos.add(erPojo);
				}
			}
		}
		return erPojos;
	}

}