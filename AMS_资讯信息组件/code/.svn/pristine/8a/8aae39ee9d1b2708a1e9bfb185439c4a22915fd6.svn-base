package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseCjDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseCjSqlBuilder;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseDao;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseCjService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
/**
 * 同业拆借信息 普通服务类
 * @author 马向峰
 *
 */
@DefaultCacheRefresh(group = CacheGroup.SECBASE)
public class SecBaseCjService extends ServiceBus<SecBaseService> implements ISecBaseCjService {

	private SecBaseDao serviceDao = null;
	public SecBaseCjService() throws Exception{
		serviceDao = new SecBaseDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseCjSqlBuilder());
		dao = serviceDao;
		}
	
	/**
	 * add by zhd 2016-09-07
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 查询实际所属证券
	 */
	public List<BasePojo> queryForSjsszq() {
		List<BasePojo> resultList=new ArrayList<BasePojo>();
		SecBaseCjDao cjDao = new SecBaseCjDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseCjSqlBuilder());
		try{
			resultList = cjDao.queryForSjsszq();
		}catch(Exception ex){
			logger.log(ex.getMessage(), ex);
		}
		return resultList;
	}
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 重写插入
	 */
	public String insert(List<BasePojo> pojoList) {
		String retInfo = "";
		SecBaseCjDao cjDao = new SecBaseCjDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseCjSqlBuilder());
		try {
			logger.debug("================================= " + "\r\n"
					+ "  Start Insert Data... ");

			if (safeData != null && safeData.getN_CHECK() <= 0) {

				for (BasePojo pojo : pojoList) {
					if (pojo instanceof AuditableParamPojo) {
						AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
						auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
						auditPojo.setOperator(ContextFactory.getContext()
								.getUserCode()); // 设置已审核的用户
						auditPojo.setAuditDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
				}
			}

			List<String> cidenList = cjDao.insert(pojoList);
			
			// BUG #313253 【同业拆借信息】界面，复制成功后，实际数据并没有新增
			if (cidenList.isEmpty()) {
				retInfo = ReturnInfoGenerator.getSaveErr(menuId, "数据保存失败！");
			}
			else {
				retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);
			}

			logger.debug("  Complete " + "\r\n"
					+ "================================= ");
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		}

		return retInfo;
	}
	
	/**
	 * add by zhd 2016-09-09
	 * STORY34120【南方基金】【紧急】现金存放业务，流水加入标识，4级科目不明确到品种，而是按照标识来区分
	 * 重写更新
	 */
	public String updateById(List<BasePojo> pojoList) {
		String retInfo = "";
		SecBaseCjDao cjDao = new SecBaseCjDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseCjSqlBuilder());
		try {
			if (safeData != null && safeData.getN_CHECK() <= 0) {
				if (pojoList != null && pojoList.size() > 0
						&& pojoList.get(0) instanceof AuditableParamPojo) {
					AuditableParamPojo auditPojo = (AuditableParamPojo) pojoList
							.get(0);
					auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
					auditPojo.setOperator(ContextFactory.getContext()
							.getUserCode()); // 设置已审核的用户
					auditPojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
			}

			cjDao.updateById(pojoList);

			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			logger.log(ex.getMessage(), ex);
		}
		return retInfo;
	}

}
