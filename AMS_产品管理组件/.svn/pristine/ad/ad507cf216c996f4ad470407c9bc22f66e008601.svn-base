package com.yss.ams.product.information.modules.ab.assetsTree_a_rule.service.impl;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ams.product.information.modules.ab.assetsTree_a_rule.dao.AssetsTree_A_RuleDao;
import com.yss.ams.product.information.modules.ab.assetsTree_a_rule.dao.AssetsTree_A_RuleSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.service.IAssetsTree_A_RuleService;

/**
 * 树形结构分类规则
 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
 * add by yangru 20190717
 * @author lenovo
 *
 */
public class AssetsTree_A_RuleService extends ServiceBus<AssetsTree_A_RuleService>
		implements IAssetsTree_A_RuleService {
	private AssetsTree_A_RuleDao serviceDao = null;
	
	public AssetsTree_A_RuleService() throws Exception {
		serviceDao = new AssetsTree_A_RuleDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new AssetsTree_A_RuleSqlBuilder());
		dao = serviceDao;
	}
	
	public void deleteByRelaId(String relaId) throws YssException {
        serviceDao.deleteByRelaId(relaId);		
	}
}
