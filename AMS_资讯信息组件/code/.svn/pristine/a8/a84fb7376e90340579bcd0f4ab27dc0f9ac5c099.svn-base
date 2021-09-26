package com.yss.ams.sec.information.modules.mp.secequ.service.impl;

import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquDao;
import com.yss.ams.sec.information.modules.mp.secequ.dao.SecEquSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secequ.service.ISecEquService;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;



/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecEquService extends ServiceBus<SecEquService> implements
		ISecEquService {

	private SecEquDao serviceDao = null;

	public SecEquService() throws Exception {
		this.bundleContext = YssContextFactory.getInstance().getBundleContext(SecInfoActivator.class);
		serviceDao = new SecEquDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecEquSqlBuilder());
		dao = serviceDao;
	}

	
	/// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
	/////// getRemindZQPS getRemindDJPX 挪到 UCORemindService  
	/////// secEquService 服务中的 getRemindZQPS getRemindDJPX 方法废弃掉  
	/**
	 * Author : ChenLong
	 * Date   : 2016-03-17
	 * Status : Add
	 * Comment: 对价派息消息提醒
	 * @param policyMap
	 * @return
	 */
	public List<SecBase> getRemindDJPX(int days){
//		List<Remind> list = new ArrayList<Remind>();
//		RemindPolicy remindPolicy = policyMap.get(UCORemindService.REMIND17);
//		if(remindPolicy != null){
			List<SecBase> secBaseList = serviceDao.getRemindDJPX(days);
//			for(SecBase secBase : secBaseList){
//				Remind remind = new Remind();
//				remind.setMescontent("证券：" + secBase.getC_SEC_NAME() + "将于" + secBase.getD_END() + "到期！");
//				remind.setMestype(remindPolicy.getMessname());
//				remind.setC_SEC_CODE(secBase.getC_SEC_CODE());
//				remind.setC_SEC_NAME(secBase.getC_SEC_NAME());
//				remind.setDqdate(secBase.getD_END());
//				remind.setIsQr(remindPolicy.getN_qr_setup());  //是否需要确认 add by guohui STORY37347【南方基金】【紧急】业务提醒功能改造
//				list.add(remind);
//			}
//		}
		return secBaseList;
	}

	/// By Jinghehe 2017-8-4 BUG #168158 资讯组件拆分出来，原来部分获取参数方法写的不规范 代码调整 
	/////// getRemindZQPS getRemindDJPX 挪到 UCORemindService  
	/////// secEquService 服务中的 getRemindZQPS getRemindDJPX 方法废弃掉  
	/**
	 * Author : ChenLong
	 * Date   : 2016-03-17
	 * Status : Add
	 * Comment: 证券送配信息提醒
	 * @param policyMap
	 * @return
	 */
	public List<SecBase> getRemindZQPS(int days){
//		List<Remind> list = new ArrayList<Remind>();
//		RemindPolicy remindPolicy = policyMap.get(UCORemindService.REMIND18);
//		if(remindPolicy != null){
			List<SecBase> secBaseList = serviceDao.getRemindZQPS(days);
//			for(SecBase secBase : secBaseList){
//				Remind remind = new Remind();
//				remind.setMescontent("证券：" + secBase.getC_SEC_NAME() + "将于" + secBase.getD_END() + "到期！");
//				remind.setMestype(remindPolicy.getMessname());
//				remind.setC_SEC_CODE(secBase.getC_SEC_CODE());
//				remind.setC_SEC_NAME(secBase.getC_SEC_NAME());
//				remind.setDqdate(secBase.getD_END());
//				remind.setIsQr(remindPolicy.getN_qr_setup());  //是否需要确认 add by guohui STORY37347【南方基金】【紧急】业务提醒功能改造
//				list.add(remind);
//			}
//		}
		return secBaseList;
	}
}
