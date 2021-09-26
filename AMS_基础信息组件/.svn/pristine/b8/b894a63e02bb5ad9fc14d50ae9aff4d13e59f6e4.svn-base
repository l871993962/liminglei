package com.yss.ams.base.information.modules.bi.accountTreeA.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.accountTreeA.dao.AccountTreeADao;
import com.yss.ams.base.information.modules.bi.accountTreeA.dao.AccountTreeASqlBuilder;
import com.yss.ams.base.information.support.bi.accountTreeA.pojo.AccountTreeA;
import com.yss.ams.base.information.support.bi.accountTreeA.service.IAccountTreeAService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.mvc.service.ServiceAssistance;

public class AccountTreeAService extends ServiceBus<AccountTreeAService> implements IAccountTreeAService  {
	private AccountTreeADao serviceDao = null;

	public AccountTreeAService() throws Exception {
		serviceDao = new AccountTreeADao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new AccountTreeASqlBuilder());
		dao = serviceDao;
	}

	@Override
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			boolean getDefaultNode = false;
			String currentUserPostCodes = ContextFactory.getContext().getLogInfo()
					.getLoggingUserPostCode();
			if(paraMap.containsKey("DEFAULT_NODE") && "true".equals(paraMap.get("DEFAULT_NODE"))){
				getDefaultNode = true;
				paraMap.remove("DEFAULT_NODE");
			}
			
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryByCondition(paraMap, AccountTreeA.class);
			List<BasePojo> newList = new ArrayList<BasePojo>();
			if(getDefaultNode){
				List<BasePojo> defaultTree = getDefaultAccountTree();
				newList.addAll(defaultTree);
				for (BasePojo basePojo : dataList) {
					String postCode = ((AccountTreeA)basePojo).getC_POST_CODE();
					if(StringUtil.IsNullOrEmptyT(postCode)){
						newList.add(basePojo);
					}else{
						String[] postAry = postCode.split("|");
						for (String post : postAry) {
							if(currentUserPostCodes.contains(post)){
								newList.add(basePojo);
							}
						}
					}
				}

			}else{
				newList = dataList;
			}
			queryRes.setDataList(newList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					InformationActivator.class));
		
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("base_accountTreeA");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("账户树形结构A区：查询账户树形结构数据出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	private List<BasePojo> getDefaultAccountTree(){
		List<BasePojo> defaultTrees = new ArrayList<BasePojo>();
		AccountTreeA pojo = new AccountTreeA();
		pojo.setC_NODE_CODE("DEFAUTL_TGR");
		pojo.setC_NODE_CODE_P("[root]");
		pojo.setC_NODE_NAME("托管人");
		defaultTrees.add(pojo);
		pojo = new AccountTreeA();
		pojo.setC_NODE_CODE("DEFAUTL_GLR");
		pojo.setC_NODE_CODE_P("[root]");
		pojo.setC_NODE_NAME("管理人");
		defaultTrees.add(pojo);
		pojo = new AccountTreeA();
		pojo.setC_NODE_CODE("DEFAUTL_TX");
		pojo.setC_NODE_CODE_P("[root]");
		pojo.setC_NODE_NAME("条线");
		defaultTrees.add(pojo);
		pojo = new AccountTreeA();
		pojo.setC_NODE_CODE("DEFAUTL_XM");
		pojo.setC_NODE_CODE_P("[root]");
		pojo.setC_NODE_NAME("项目");
		defaultTrees.add(pojo);
		
		return defaultTrees;
	}
}
