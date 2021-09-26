package com.yss.ams.base.information.modules.bi.salesnet.service.impl;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetDao;
import com.yss.ams.base.information.modules.bi.salesnet.dao.SalesNetSqlBuilder;
import com.yss.ams.base.information.support.bi.salesnet.pojo.SalesNet;
import com.yss.ams.base.information.support.bi.salesnet.service.ISalesNetService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

/**
 * 销售网点设置服务类
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class SalesNetService extends ServiceBus<SalesNetService> implements ISalesNetService {

	private SalesNetDao serviceDao = null;
	public SalesNetService() throws Exception{
		serviceDao = new SalesNetDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new SalesNetSqlBuilder());
		dao = serviceDao;
		
		serviceBindingClazz = SalesNet.class;
	}

	/**
	 * 查询产品计划录入收益率时加载的销售网点,只包含分行和村镇银行
	 */
	public List<BasePojo> queryPdNet(HashMap<String, String> paraDict) {
		List<BasePojo> dataList = null;
		dataList = serviceDao.queryPdNet(paraDict);
		return dataList;
	}

	/**
	 * Author : ChenLong
	 * Date   : 2017-12-29
	 * Status : Add
	 * Comment: 根据销售商代码删除网点信息失败
	 * @param vendorCodes 销售商代码集合
	 * @param conn
	 */
	public void deleteNetInfoByVendorCode(String[] vendorCodes,Connection conn){
		serviceDao.deleteNetInfoByVendorCode(vendorCodes, conn);
	}
}
