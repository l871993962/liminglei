package com.yss.ams.sec.information.modules.aa.etf.service.impl;

import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.ams.sec.information.modules.aa.etf.dao.EtfDao;
import com.yss.ams.sec.information.modules.aa.etf.dao.EtfSqlBuilder;
import com.yss.ams.sec.information.support.modules.aa.etf.service.IEtfService;
/**
 * ETF基本信息 普通服务类
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class EtfService extends ServiceBus<EtfService> implements IEtfService {

	private EtfDao etfDao = null;

	public EtfService() throws Exception {
		etfDao = new EtfDao(DbPoolFactory.getInstance().getPool(),
				new EtfSqlBuilder());
		dao = etfDao;
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-04
	 * Status : Add
	 * Task   : TASK #203469 ETF非担保交收指令生成表
	 * Comment: 获取组合相应的交易代码
	 */
	public String getTradeCodeByPortCode(String portCode){
		return etfDao.getTradeCodeByPortCode(portCode);
	}
}
