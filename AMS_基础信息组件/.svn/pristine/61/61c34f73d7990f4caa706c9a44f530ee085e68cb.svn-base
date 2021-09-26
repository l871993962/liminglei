package com.yss.ams.base.information.support.bi.dactype.service;

import java.util.List;

import com.yss.ams.base.information.support.bi.dactype.pojo.AccountType;
import com.yss.ams.base.information.support.bi.dactype.pojo.AccountTypeTree;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;

/**
 * @ClassName IHkTypeUnifypayService
 * @Description 款项类型设置
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
@RestfulSupported
public interface IAccountTypeService extends IServiceBus {
	
	/**
	 * 根据指定子节点code去加载树形结构
	 * zhangyongzhao
	 * 2017年11月14日
	 */
	public List<AccountTypeTree> accordingChildrenGetTree(List<String> list,String type);
	/**
	 * 根据code查询数据
	 * @param list
	 * @param type
	 * @return
	 */
	public List<AccountTypeTree> getAccByCode(List<String> list);
	
	/**
	 * 根据父节点获取子账户类型
	 * STORY #58361 【账户管理】树形结构中，同类账户合并  By XHM
	 * @param parentNode
	 * @return
	 */
	public List<AccountType> getSubAccountsByParent(String parentNode);
}
