package com.yss.ams.base.information.modules.bi.accountTreeB.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.accountTreeB.dao.AccountTreeBDao;
import com.yss.ams.base.information.modules.bi.accountTreeB.dao.AccountTreeBSqlBuilder;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeAService;
import com.yss.ams.base.information.support.bi.accountTreeB.pojo.AccountTreeB;
import com.yss.ams.base.information.support.bi.accountTreeB.service.IAccountTreeBService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.service.ServiceAssistance;

public class AccountTreeBService extends ServiceBus<AccountTreeBService> implements IAccountTreeBService{
	private AccountTreeBDao serviceDao = null;

	private static List<String> defaultTrees = new ArrayList<String>();
	
	public AccountTreeBService() throws Exception {
		serviceDao = new AccountTreeBDao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new AccountTreeBSqlBuilder());
		dao = serviceDao;

		defaultTrees.add("DEFAUTL_TGR");
		defaultTrees.add("DEFAUTL_GLR");
		defaultTrees.add("DEFAUTL_TX");
		defaultTrees.add("DEFAUTL_XM");
	}

	@SuppressWarnings("unchecked")
	@Override
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			String nodeCodeP = paraMap.get("C_NODE_CODE_P").toString();
			List<BasePojo> dataList = null;
			if(defaultTrees.contains(nodeCodeP)){
				dataList = serviceDao.getDefaultTreeViewData(nodeCodeP);
			}else{
				IAccountTreeAService accountTreeAService = YssServiceFactory.getInstance()
						.createService(IAccountTreeAService.class);
				dataList = (List<BasePojo>) serviceDao.queryByCondition(paraMap, AccountTreeB.class);
				List<BasePojo> nodePList = accountTreeAService.getTreeViewData(paraMap).getDataList();
				for (BasePojo basePojo : nodePList) {
					AccountTreeA treeA = (AccountTreeA) basePojo;
					if(!"[root]".equals(treeA.getC_NODE_CODE_P())){
						AccountTreeB treeP = new AccountTreeB();
						treeP.setC_NODE_CODE(treeA.getC_NODE_CODE());
						treeP.setC_NODE_CODE_P(treeA.getC_NODE_CODE_P());
						treeP.setC_OPEN_ACC_NAME(treeA.getC_NODE_NAME());
						treeP.setAuditState(treeA.getAuditState());
						dataList.add(treeP);
					}
				}
			}
			
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					InformationActivator.class));
		
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("base_accountTreeB");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("账户树形结构A区：查询账户树形结构数据出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
}
