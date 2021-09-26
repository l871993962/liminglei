package com.yss.uco.elecreco.er.erzcfzb.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erzcfzb.dao.ErZcfzbDao;
import com.yss.uco.elecreco.er.erzcfzb.dao.ErZcfzbSqlBuilder;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;
import com.yss.uco.elecreco.er.erzcfzb.service.IErZcfzbService;

public class ErZcfzbService extends ServiceBus<ErZcfzbService> implements IErZcfzbService {

	private ErZcfzbDao serviceDao = null;
	
	public ErZcfzbService() throws Exception {
		serviceDao = new ErZcfzbDao(DbPoolFactory.getInstance().getPool(), new ErZcfzbSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 根据条件查询电子对账资产负债信息
	 */
	public List<ErZcfzb> getZcfzData(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		List<ErZcfzb> erZcfzbList = new ArrayList<ErZcfzb>();
		ErZcfzb erZcfzb = null;
		if (!paraMap.isEmpty()) {
			List<BasePojo> dataList = serviceDao.queryByCondition(paraMap, ErZcfzb.class);
			if (null != dataList && !dataList.isEmpty()) {
				for (BasePojo pojo : dataList) {
					erZcfzb = (ErZcfzb) pojo;
					erZcfzbList.add(erZcfzb);
				}
			}
		}
		return erZcfzbList;
		
	}

}
