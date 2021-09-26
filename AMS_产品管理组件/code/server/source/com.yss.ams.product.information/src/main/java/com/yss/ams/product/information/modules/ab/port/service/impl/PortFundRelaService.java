package com.yss.ams.product.information.modules.ab.port.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.account.cache.BaseFundAccCache;
import com.yss.ams.product.information.modules.ab.port.dao.PortFundRelaDao;
import com.yss.ams.product.information.modules.ab.port.dao.PortFundRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortFundRelaService;
import com.yss.fast.mq.support.constants.TopicConstants;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.msgcenter.IFrameworkPublisher;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;

public class PortFundRelaService extends ServiceBus<PortFundRelaService> 
	implements IPortFundRelaService{
	
	private PortFundRelaDao serviceDao;
	
	public PortFundRelaService(){
		serviceDao = new PortFundRelaDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortFundRelaSqlBuilder());
		dao = serviceDao;
	}
	
	@Override
	public String deletePortFundRela(String portCodes, String fundAccId) {
		String result = serviceDao.deletePortFundRela(portCodes, fundAccId);
		if("Success".equals(result)){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			idsList.add(fundAccId);
			info.setIdList(idsList);
			fundAccCache.update(info);
			
			////BUG #361947 ���Ϸ����𡿡�0831�������˻�ɾ�����޸ĺ� �������ֹ��������Զ�ָ��󣬼��ص��˻����Ǿɵ��˻���Ϣ
			try {
				IFrameworkPublisher ifc=(IFrameworkPublisher)YssServiceFactory.getInstance().createService(IFrameworkPublisher.class);
				Map<String, Object> sendDetail = new HashMap<String, Object>();
				sendDetail.put("FUN_CODE", "pd_relaPort");
				sendDetail.put("updateFundAccById", fundAccId);
				sendDetail.put("PORT_CODE", portCodes.replace(",", "#"));
			    String detailMsg = JsonUtil.toString(sendDetail);
			    ifc.send(detailMsg, TopicConstants.UFP_NOTICE.toString());
	            logger.info("向支付发送账户关联更新消息:"+detailMsg);
			} catch (Exception e) {
	            logger.error("给综合指令界面发送更新账户关联消息失败", e);
	        }
		}
		return result;
	}

}
