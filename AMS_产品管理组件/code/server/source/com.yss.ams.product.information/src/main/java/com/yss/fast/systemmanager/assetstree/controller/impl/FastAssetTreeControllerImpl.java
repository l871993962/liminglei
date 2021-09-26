package com.yss.fast.systemmanager.assetstree.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.yss.fast.systemmanager.support.assetstree.service.IFastAssetTreeService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.systemmanager.assetstree.controller.IBaseAssetTreeController;

/**
 * STORY #55709 公共组合区树形结构优化
 * 需要远程调用产品组件的服务
 * @author lenovo
 *
 */
public class FastAssetTreeControllerImpl extends AbstractBaseServiceBusController<FastAssetsTree_A,IFastAssetTreeService> implements IBaseAssetTreeController {

	@Override
	public RestfulQueryResult<FastAssetsTree_A> getTreeViewData(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().getTreeViewData(paraMap),FastAssetsTree_A.class);
	}

	@Override
	public List<FastAssetsTree_A> getTreeViewDataList(
			HashMap<String, Object> paraMap) {
		return castToListT(getService().getTreeViewDataList(paraMap));
	}

	@Override
	public RestfulQueryResult<FastAssetsTree_A> getAssetTreeView(
			HashMap<String, Object> paraMap) {
		return queryResToT(getService().getAssetTreeView(paraMap),FastAssetsTree_A.class);
	}

	@Override
	public String insertAssetData(ArrayList<FastAssetsTree_A> pojoList) {
		return getService().insertAssetData((ArrayList<BasePojo>) castToBasePojoList(pojoList));
	}

	@Override
	public String auditAssetDataById(ArrayList<FastAssetsTree_A> pojoList) {
		return getService().auditAssetDataById((ArrayList<BasePojo>) castToBasePojoList(pojoList));
	}

	@Override
	public String unAuditAssetDataById(ArrayList<FastAssetsTree_A> pojoList) {
		return getService().unAuditAssetDataById((ArrayList<BasePojo>) castToBasePojoList(pojoList));
	}

	@Override
	public String insertAssetData(FastAssetsTree_A basePojo) {
		return getService().insertAssetData(basePojo);
	}

	@Override
	public String isSameAssetType(String portCode, String dragPortCode) {
		return getService().isSameAssetType(portCode,dragPortCode);
	}

	@Override
	public int updateOrdelete(String id, String trCode, String isParent,
			String type) {
		return getService().updateOrdelete(id,trCode,isParent,type);
	}

	@Override
	public List<FastAssetsTree_A> queryAssetDataByCond(
			HashMap<String, Object> paraMap) throws Exception {
		return castToListT(getService().queryAssetDataByCond(paraMap));
	}

	
	

	
}
