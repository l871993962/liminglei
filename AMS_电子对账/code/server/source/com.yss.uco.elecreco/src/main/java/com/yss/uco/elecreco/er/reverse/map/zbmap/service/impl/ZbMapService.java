package com.yss.uco.elecreco.er.reverse.map.zbmap.service.impl;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.reverse.map.zbmap.dao.ZbMapDao;
import com.yss.uco.elecreco.er.reverse.map.zbmap.dao.ZbMapSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.zbmap.pojo.ZbMap;
import com.yss.uco.elecreco.er.reverse.map.zbmap.service.IZbMapService;
import com.yss.uco.elecreco.support.bean.ElecRela;

public class ZbMapService extends ServiceBus<ZbMapService> implements IZbMapService {

	private ZbMapDao serviceDao = null;
	public ZbMapService() throws Exception {
		serviceDao = new ZbMapDao(DbPoolFactory.getInstance().getPool(),new ZbMapSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public Map<String, ElecRela> getZbItems(String fileType) {
		return this.serviceDao.getZbItems(fileType);
	}
	@Override
	public List<ZbMap> getCompareZbItems(String portCode, String tgh,
			String fileType) {
		return this.serviceDao.getCompareZbItems(portCode, tgh, fileType);
	}

}