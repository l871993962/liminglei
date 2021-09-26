package com.yss.uco.elecreco.er.org.service.impl;

import java.util.List;

import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.org.dao.ErOrgDao;
import com.yss.uco.elecreco.er.org.dao.ErOrgSqlBuilder;
import com.yss.uco.elecreco.er.org.pojo.ErOrg;
import com.yss.uco.elecreco.er.org.service.IErOrgService;

/**
 * 
 * @author Lenovo
 *
 */
public class ErOrgService extends ServiceBus<ErOrgService> implements IErOrgService {
	private ErOrgDao serviceDao = null;

	public ErOrgService() throws Exception {
		serviceDao = new ErOrgDao(DbPoolFactory.getInstance().getPool(),
				new ErOrgSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public List<ErOrg> getTrusteeOrgs() {
		return serviceDao.getTrusteeOrgs();
	}

	@Override
	public List<ErOrg> getManagerOrgs() {
		return serviceDao.getManagerOrgs();
	}
}
