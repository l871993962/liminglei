package com.yss.ams.base.information.modules.bi.org.service.impl;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.bi.org.dao.OrgDao;
import com.yss.ams.base.information.modules.bi.org.dao.OrgSqlBuilder;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgLinkRelaService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;


/**
 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
 * @author lenovo
 *
 */
public class OrgLinkRelaService extends ServiceBus<OrgLinkRelaService> implements IOrgLinkRelaService {

	private OrgDao serviceDao = null;

	public OrgLinkRelaService() throws Exception {
		serviceDao = new OrgDao(DbPoolFactory.getInstance().getPool(), new OrgSqlBuilder());
		dao = serviceDao;
	}
	
	@Override
	public List<Org> getOrgLinkManList(String orgCode) {
		return serviceDao.getOrgLinkManList(orgCode);
	}
	
	@Override
	public Map<String, Org> getOrgLinkManData(String orgCode) {
		return serviceDao.getOrgLinkManData(orgCode);
	}

	@Override
	public void updateOrgLinkMan(String orgCode, List<Org> orgList) {
		serviceDao.updateOrgLinkMan(orgCode, orgList);
	}

	@Override
	public void deleteOrgLinkMan(String orgCode) {
		serviceDao.deleteOrgLinkMan(orgCode);
	}
}
