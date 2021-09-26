package com.yss.ams.product.information.modules.pg.operport.impl;

import java.util.Map;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupDao;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupSqlBuilder;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaDao;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaSqlBuilder;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.pg.operport.service.IOperPortService;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * <产品群组管理>组合相关操作服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class OperPortService extends ServiceBus<OperPortService> implements IOperPortService{

	private PortGroupDao groupDao = null;
	
	private PortGroupRelaDao groupRelaDao = null;
	
	public OperPortService(){
		DbPool pool = YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class);
		groupDao = new PortGroupDao(pool, new PortGroupSqlBuilder());
		groupRelaDao = new PortGroupRelaDao(pool, new PortGroupRelaSqlBuilder());
	}
	
	@Override
	public Map<String, Port> getPortByGroupCode(String groupCode) {
		Map<String,Port> map = groupRelaDao.getPortByGroupCode(groupCode);
		return map;
	}

	@Override
	public Map<String, String> getAllPortGroup() {
		return groupDao.getAllPortGroup();
	}

}
