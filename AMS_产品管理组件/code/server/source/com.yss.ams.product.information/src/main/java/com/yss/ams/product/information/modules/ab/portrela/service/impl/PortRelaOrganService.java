package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.Port_A;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

public class PortRelaOrganService extends ServiceBus<PortRelaOrganService>
		implements IPortRelaOrganServcie {

	private IPortRelaService baseService = null;
	private PortRelaDao serviceDao = null;
	
	public PortRelaOrganService() throws Exception {
		baseService = new PortRelaService();
		serviceDao = new PortRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortRelaSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaOrgan(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes res = new QueryRes();
		res = baseService.queryPortRelaOrgan(paraMap);
		res.setMenuId(menuId);
		res.setHeadKeyList(ServiceAssistance.getListHead(menuId,ProductInfoActivator.class));
		return res;
	}

	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	public QueryRes queryPortRelaOrganPage(HashMap<String, Object> paraMap,
			PageInation page) throws Exception {
		// TODO Auto-generated method stub
		return baseService.queryPortRelaOrganPage(paraMap, page);
	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-21
	 * Status : Add
	 * Comment: 检测组合是否已经关联委托人机构了
	 * @param map
	 * @return
	 */
	public String checkORGConsignerForPort(HashMap<String,String> map){
		return serviceDao.checkORGConsignerForPort(map);
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-11-03
	 * Status : Add
	 * Comment: 查询计税委托人下的组合
	 * @param taxConsigner
	 * @return
	 */
	public List<Port_A> getPortInfoByConsigner(String taxConsigner){
		return serviceDao.getPortInfoByConsigner(taxConsigner);
	}

	@Override
	public HashMap<String, List<String>> getPortRelaOrgByPortAndDvType(
			String portCodes, String dvType) {
		return serviceDao.getPortRelaOrgByPortAndDvType(portCodes, dvType);
	}
}
