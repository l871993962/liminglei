package com.yss.uco.elecreco.er.erlrb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erlrb.dao.ErLrbDao;
import com.yss.uco.elecreco.er.erlrb.dao.ErLrbSqlBuilder;
import com.yss.uco.elecreco.er.erlrb.pojo.ErLrb;
import com.yss.uco.elecreco.er.erlrb.service.IErLrbService;

public class ErLrbService extends ServiceBus<ErLrbService> implements IErLrbService {

	private ErLrbDao serviceDao = null;
	
	public ErLrbService() throws Exception {
		serviceDao = new ErLrbDao(DbPoolFactory.getInstance().getPool(), new ErLrbSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 根据条件查询电子对账利润信息
	 */
	public List<ErLrb> getLrData(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		List<ErLrb> erLrbList = new ArrayList<ErLrb>();
		ErLrb erLrb = null;
		if (!paraMap.isEmpty()) {
			List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErLrb.class);
			if (null != dataList && !dataList.isEmpty()) {
				for (BasePojo pojo : dataList) {
					erLrb = (ErLrb) pojo;
					erLrbList.add(erLrb);
				}
			}
		}
		return erLrbList;
	}

}
