package com.yss.uco.elecreco.er.spilt.rela.service.impl;
import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.spilt.rela.dao.ErSplitRelaDao;
import com.yss.uco.elecreco.er.spilt.rela.dao.ErSplitRelaSqlBuilder;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rela.service.IErSplitRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;

public class ErSplitRelaService extends ServiceBus<ErSplitRelaService> implements IErSplitRelaService {

	private ErSplitRelaDao serviceDao = null;
	public ErSplitRelaService() throws Exception {
		serviceDao = new ErSplitRelaDao(DbPoolFactory.getInstance().getPool(),new ErSplitRelaSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public List<ErSplitRela> getErSplitRelasByPortCode(String port) {
		return serviceDao.getErSplitRelasByPortCode(port);
	}
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		if(paraMap.containsKey("C_SHOW_TYPE"))
		{
			if(!"IGNORE".equalsIgnoreCase((String)paraMap.get("C_SHOW_TYPE")))
			{
				paraMap.remove("C_SHOW_TYPE");
			}
		}
		return super.queryByCondition(paraMap, page);
	}
}