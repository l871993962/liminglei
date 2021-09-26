package com.yss.ams.product.information.modules.ab.portPdAttribute.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.ab.portPdAttribute.dao.PortPdAttributeDao;
import com.yss.ams.product.information.modules.ab.portPdAttribute.dao.PortPdAttributeSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.service.IPortPdAttributeService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * <产品属性分类>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortPdAttributeService extends ServiceBus<PortPdAttributeService>
		implements IPortPdAttributeService {
	private PortPdAttributeDao serviceDao = null;

	public PortPdAttributeService() throws Exception {
		serviceDao = new PortPdAttributeDao(DbPoolFactory.getInstance()
				.getPool(), new PortPdAttributeSqlBuilder());
		dao = serviceDao;
	}

	

	@Override
	public HashMap<String, String> getVocabularyDict() {
		// TODO Auto-generated method stub
		return serviceDao.getVocabularyDict();
	}

	@Override
	public HashMap<String, String> getAssetDict() {
		// TODO Auto-generated method stub
		return serviceDao.getAssetDict();
	}

	@Override
	public HashMap<String, String> getPortPojoList(HashMap<String, String> paraMap) {
		return serviceDao.getPortPojoList(paraMap);
	}

	@Override
	public HashMap<String, String> getPortNameDict(
			HashMap<String, String> paraMap) {
		return serviceDao.getPortNameDict(paraMap);
	}
	
	@Override
	public List<String> queryPortCodesByType(String portType) {
		return serviceDao.queryPortCodesByType(portType);
	}
	
	/**
	 * 获取含有短编码的数据
	 * BUG #325127 
	 */
	@Override
	public List<PortPdAttribute> getShortNumPort() {
		
		return serviceDao.getShortNumPort();
	}
	
	@Override
	public HashMap<String, List<String>> getPortCodeForNDay(String ports) throws Exception {
		return serviceDao.getPortCodeForNDay(ports);
	}
}
