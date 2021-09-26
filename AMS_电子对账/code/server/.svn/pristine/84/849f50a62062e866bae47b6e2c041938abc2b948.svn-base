package com.yss.uco.elecreco.er.autostate.service;

import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.er.autostate.dao.AutoStateDao;
import com.yss.uco.elecreco.er.autostate.dao.AutoStateSqlBuilder;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.service.IAutoStateService;
/**
 * STORY54581电子对账接收对账结果后发送消息
* @ClassName: AutoStateService 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author wulongxing
* @date 2018年4月25日 下午3:02:49 
*
 */
public class AutoStateService extends ServiceBus<AutoStateService> implements IAutoStateService{

	private AutoStateDao serviceDao = null;
	
	public AutoStateService() throws Exception {
		serviceDao = new AutoStateDao(DbPoolFactory.getInstance().getPool(),
				new AutoStateSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public void sendAutoMessage(String status, String fsn, String fileType,
			String cAssCode) {
		serviceDao.sendAutoMessage(status, fsn, fileType,
				cAssCode);
		//是否是对应的自动化任务发送的
		
		//同一任务下的发送记录是否都已反馈
		
		//发送消息通知流程引擎
		
	}

	@Override
	public List<BEN_RECORD> getSendResult(Map<String, String> conditionMap) {
		return serviceDao.getSendResult(conditionMap);
	}

	@Override
	public List<BEN_RECORD> getDZResult(Map<String, String> conditionMap) {
		return serviceDao.getDZResult(conditionMap);
	}
	
	@Override
	public List<ErBbInfo> getDZResultInfo(
			Map<String, String> conditionMap) {
		return serviceDao.getDZResultInfo(conditionMap);
	}
	
	@Override
	public Map<String, String> getDiffData(ErBbInfo erBbInfo, String bfCodes, String checkCondition) throws Exception {
		return serviceDao.getDiffData(erBbInfo, bfCodes, checkCondition);
	}
}
