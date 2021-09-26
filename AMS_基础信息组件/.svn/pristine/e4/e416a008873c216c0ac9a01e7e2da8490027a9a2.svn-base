package com.yss.ams.base.information.modules.bi.org.service.impl;

import java.util.List;

import com.yss.ams.base.information.modules.bi.org.dao.FastPortOrgDaoImpl;
import com.yss.framework.api.commonInfo.pojo.FastPortOrg;
import com.yss.framework.api.commonInfo.service.IFastPortOrgService;
import com.yss.framework.db.DbPoolFactory;

/**
 * STORY #69702 华宝兴业：会计大屏只看关注的组合进度 (#2 #1 ) 
 * @author lenovo
 *
 */
public class FastPortOrgServiceImpl implements IFastPortOrgService {

	private FastPortOrgDaoImpl serviceDao = null;
	
	public FastPortOrgServiceImpl() {
		serviceDao = new FastPortOrgDaoImpl(DbPoolFactory.getInstance().getPool(), null);
	}
	
	@Override
	public List<FastPortOrg> queryTrusteeByPorts(List<String> ports) {
		return serviceDao.queryTrusteeByPorts(ports);
	}

}
