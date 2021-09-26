package com.yss.uco.elecreco.er.erdata.service.impl;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDao;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDaoSqlBuilder;
import com.yss.uco.elecreco.er.erdata.service.IErDataService;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;

public class ErDataService extends ServiceBus<ErDataService> implements
		IErDataService {

	private ErDataDao serviceDao = null;

	public ErDataService() {
		serviceDao = new ErDataDao(DbPoolFactory.getInstance().getPool(),
				new ErDataDaoSqlBuilder());
		dao = serviceDao;
	}

	public XmlFile getXmlFileRoot(String fsn,String fileType,String cAssCode){
			return serviceDao.getXmlFileRoot(fsn,fileType,cAssCode);
	}
}
