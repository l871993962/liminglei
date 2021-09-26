package com.yss.uco.elecreco.er.erkmb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.uco.elecreco.er.erkmb.dao.ErKmbDao;
import com.yss.uco.elecreco.er.erkmb.dao.ErKmbSqlBuilder;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.erkmb.service.IErKmbService;

public class ErKmbService extends ServiceBus<ErKmbService> implements
		IErKmbService {

	private ErKmbDao serviceDao = null;

	public ErKmbService() throws Exception {
		serviceDao = new ErKmbDao(DbPoolFactory.getInstance().getPool(),
				new ErKmbSqlBuilder());
		dao = serviceDao;
	}

	/**
	 * 根据条件查询电子对账科目信息
	 */
	public List<ErKmb> getKmData(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		List<ErKmb> erKmbList = new ArrayList<ErKmb>();
		ErKmb erKmb = null;
		if (!paraMap.isEmpty()) {
			List<BasePojo> dataList = serviceDao.queryByCondition(paraMap,
					ErKmb.class);
			if (null != dataList && !dataList.isEmpty()) {
				for (BasePojo pojo : dataList) {
					erKmb = (ErKmb) pojo;
					erKmbList.add(erKmb);
				}
			}
		}
		return erKmbList;
	}

}
