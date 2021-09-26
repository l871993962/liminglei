package com.yss.uco.elecreco.er.reverse.portrela.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.portrela.dao.ElecPortRelaDao;
import com.yss.uco.elecreco.er.reverse.portrela.dao.ElecPortRelaSqlBuilder;
import com.yss.uco.elecreco.er.reverse.portrela.service.IElecPortRelaService;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.PojoLoader;



public class ElecPortRelaService  extends ServiceBus<ElecPortRelaService> implements IElecPortRelaService {
	
	private ElecPortRelaDao serviceDao = null;

	public ElecPortRelaService() throws Exception {
		serviceDao = new ElecPortRelaDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new ElecPortRelaSqlBuilder());
		dao = serviceDao;
	}
	
	
	/**
	 * 关联机构
	 */
	public List<BasePojo> queryPortRelaOrgan(HashMap<String, Object> paraMap) {
		List<BasePojo> dataList = new ArrayList<BasePojo>();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);


			dataList = (List<BasePojo>) serviceDao
					.queryOrganDao(paraMap, clazz);
			
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.error("产品关联信息：查询产品关联信息关联机构数据失败", ex);
		}
		return dataList;
	}
	
	

	
}
