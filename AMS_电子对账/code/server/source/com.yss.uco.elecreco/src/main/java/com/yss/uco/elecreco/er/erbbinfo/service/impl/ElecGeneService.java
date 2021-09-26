package com.yss.uco.elecreco.er.erbbinfo.service.impl;

import java.util.HashMap;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneService;
import com.yss.uco.elecreco.support.dzdz.common.ErConfigUtil;

public class ElecGeneService extends ServiceBus<IElecGeneService> implements IElecGeneService {

	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap) {

		QueryRes res = new QueryRes();
		
		try {
			res.setDataList(ErConfigUtil.getElecCfg());
			res.setMenuId(menuId);
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		}

		return res;
	}

}
