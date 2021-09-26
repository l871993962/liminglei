package com.yss.ams.base.information.modules.bi.accountType.controller.impl;

import java.util.List;

import com.yss.ams.base.information.support.bi.dactype.controller.IAccountTypeController;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeTree;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeVo;
import com.yss.ams.base.information.support.bi.dactype.service.IAccountTypeService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class AccountTypeControllerImpl extends AbstractBaseServiceBusController<AccountType,IAccountTypeService> implements IAccountTypeController{

  
    @Override
    public List<AccountTypeTree> getAccByCode(List<String> list){
        return getService().getAccByCode(list);
    }

    @Override
    public List<AccountType> getSubAccountsByParent(String parentNode){
        return getService().getSubAccountsByParent(parentNode);
    }

	@Override
	public List<AccountTypeTree> accordingChildrenGetTree(AccountTypeVo vo) {
		// TODO Auto-generated method stub
		return getService().accordingChildrenGetTree(vo.getList(),vo.getType());
	}

}