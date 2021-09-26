package com.yss.uco.elecreco.er.ersyzqyb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.ersyzqyb.dao.ErSyzqybDao;
import com.yss.uco.elecreco.er.ersyzqyb.dao.ErSyzqybSqlBuilder;
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;
import com.yss.uco.elecreco.er.ersyzqyb.service.IErSyzqybService;

public class ErSyzqybService extends ServiceBus<ErSyzqybService> implements IErSyzqybService {

	private ErSyzqybDao serviceDao = null;
	
	public ErSyzqybService() throws Exception {
		serviceDao = new ErSyzqybDao(DbPoolFactory.getInstance().getPool(), new ErSyzqybSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 根据条件查询电子对账所有者权益（基金净值）变动信息
	 */
	public List<ErSyzqyb> getSyzqyData(HashMap<String, Object> paraMap) {
		List<ErSyzqyb> erSyzqybList = new ArrayList<ErSyzqyb>();
		ErSyzqyb erSyzqyb = null;
		if (!paraMap.isEmpty()) {
			List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErSyzqyb.class);
			if (null != dataList && !dataList.isEmpty()) {
				for (BasePojo pojo : dataList) {
					erSyzqyb = (ErSyzqyb) pojo;
					erSyzqybList.add(erSyzqyb);
				}
			}
		}
		return erSyzqybList;
	}

}
