package com.yss.ams.sec.information.modules.mp.secmkt.service.impl;

import java.util.Date;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktDao;
import com.yss.ams.sec.information.modules.mp.secmkt.dao.SecMktSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
//import com.yss.bundle.activator.UcoActivator;
//import com.yss.data.mp.secmkt.dao.SecMktDao;
//import com.yss.data.mp.secmkt.dao.SecMktSqlBuilder;
//import com.yss.data.mp.secmkt.service.ISecMktService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;

public class SecMktService extends ServiceBus<SecMktService> implements
		ISecMktService {

	public SecMktDao serviceDao = null;

	public SecMktService() throws Exception {
		this.bundleContext = YssContextFactory.getInstance().getBundleContext(SecInfoActivator.class);
		serviceDao = new SecMktDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecMktSqlBuilder());
		dao = serviceDao;
	}
	
	//因为此方法要调用SecMktDao的doSecMpMap方法，又因为SecMktDao的doSecMpMap方法由于引用了非拆分pojo EConfirm已经注释掉了，所以此方法也先行注释掉了
//	public void doSecMpMap(List<BasePojo> pojoList) throws Exception{
//		try{
//			serviceDao.doSecMpMap(pojoList);
//		}catch(Exception e){
//			throw e;
//		}
//	}
	
	/**
	 * 查询手工添加的映射证券行情
	 * @param PortCode 组合代码
	 * @param date 行情日期
	 * @return 返回手工添加的证券行情信息
	 * @throws Exception
	 */
	public List<String> QuerySGSecMpMap(String PortCode,Date date) throws Exception{
		try{
			return serviceDao.QuerySGSecMpMap(PortCode,date);
		}catch(Exception e){
			throw e;
		}
	}

	/**
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 业务逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 *		根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList 净值确认数据对象
	 */
	//因为此方法要调用SecMktDao的doSecMpMapForUnlock方法，又因为SecMktDao的doSecMpMapForUnlock方法由于引用了非拆分pojo EConfirm已经注释掉了，所以此方法也先行注释掉了
//	public void doSecMpMapForUnlock(List<BasePojo> pojoList) {
//		serviceDao.doSecMpMapForUnlock(pojoList);
//		
//	}
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-05-30
	 * Status : Add
	 * Comment: 查询汇率
	 */
	public double getRate(Date date,String secCode){
		return serviceDao.getRate(date, secCode);
	}

	/**
	 * 净值确认后，若证券行情映射中维护相关信息，则生成一笔证券行情数据
	 * ADD BY ZXL 20141029 招商现场
	 * 
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * 现有逻辑：针对主、从基金业务特点（从基金投资主基金，主基金净值确认后、作为从基金对应持仓证券的行情），
	 *		用户在｛净值确认管理｝中进行锁定操作时，系统根据｛证券行情映射｝中设置，判别本基金/本分级基金是否与某一证券有映射关系。如有，则对应生成一笔证券行情资料。
	 * 逻辑变更：根据 证券持仓+用户权限 数据，确定需要向哪些用户推送提醒；并以即时通讯模块的飘窗形式进行提醒。
	 * 适用场景：净值反确认操作时触发
	 * @param pojoList
	 * @throws Exception
	 */
	@Override
	public void deleteSecMpMap(List<BasePojo> lstMp) {
		try {
			serviceDao.deleteSecMpMap(lstMp);
		} catch (Exception e) {
			//e.printStackTrace();
			logger.error(e.getMessage());
		}
	}

	/**
	 * 根据库存数据表的seccode list查询 证券行情映射表 的  标的组合代码
	 * add baoql 20190827 
	 * STORY #75718 自动化优化：增加智能映射方式 
	 */
	@Override
	public List<String> getPortCodeBySecCodeList(List<String> secCode, Date date)
			throws Exception {
		try{
			return serviceDao.getPortCodeBySecCodeList(secCode,date);
		}catch(Exception e){
			throw e;
		}
	}
}
