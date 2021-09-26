package com.yss.uco.elecreco.er.reverse.map.assmap.service.impl;
import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.map.assmap.dao.AssMapDao;
import com.yss.uco.elecreco.er.reverse.map.assmap.dao.AssMapSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.assmap.pojo.AssMap;
import com.yss.uco.elecreco.er.reverse.map.assmap.service.IAssMapService;

public class AssMapService extends ServiceBus<AssMapService> implements IAssMapService {

	private AssMapDao serviceDao = null;
	public AssMapService() throws Exception {
		serviceDao = new AssMapDao(DbPoolFactory.getInstance().getPool(),new AssMapSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public String getDzMode(String portCode, String fileType) {
		// TODO Auto-generated method stub
		return serviceDao.getDzMode(portCode,fileType);
	}
	@Override
	public List<AssMap> getCommonAssMapByPortCode(String portCode) {
		// TODO Auto-generated method stub
		return serviceDao.getCommonAssMapByPortCode(portCode);
	}
	@Override
	public List<AssMap> getAssMapByPortCodeAndFileType(String portCode,
			String fileType) {
		// TODO Auto-generated method stub
		return serviceDao.getAssMapByPortCodeAndFileType(portCode, fileType);
	}
	@Override
	public List<String> getTghCodesByPortCode(String portCode) {
		return serviceDao.getTghCodesByPortCode(portCode);
	}

}