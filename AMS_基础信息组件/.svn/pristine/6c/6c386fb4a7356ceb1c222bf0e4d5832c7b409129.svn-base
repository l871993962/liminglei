package com.yss.ams.base.information.modules.bi.accountType.service.imp;

import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.accountType.dao.AccountTypeDao;
import com.yss.ams.base.information.modules.bi.accountType.dao.AccountTypeSqlBuilder;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeTree;
import com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeService;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

/**
 * @ClassName HkTypeUnifyPayService
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
public class AccountTypeService extends ServiceBus<AccountTypeService>
		implements IAccountTypeService {

	private AccountTypeDao serviceDao;

	public AccountTypeService() {
		serviceDao = new AccountTypeDao(DbPoolFactory.getInstance()
				.getPool(), new AccountTypeSqlBuilder());
		dao = serviceDao;
		this.bundleContext = YssContextFactory.getInstance().getBundleContext(
				InformationActivator.class);
	}
	
	/**
	 * 根据指定子节点code去加载树形
	 */
	public List<AccountTypeTree> accordingChildrenGetTree(List<String> list,String type){
		return serviceDao.accordingChildrenGetTree(list,type);
	}
	/**
	 * 根据code去查询数据
	 */
	public List<AccountTypeTree> getAccByCode(List<String> list){
		return serviceDao.getAccByCodeData(list);
	}
	
	/**
	 * 根据父节点获取所有子账户
	 * @param parentAcc
	 * @return
	 */
	public List<AccountType> getSubAccountsByParent(String parentNode){
		return serviceDao.getSubAccountsByParent(parentNode);
	}
}
