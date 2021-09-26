package com.yss.ams.product.information.fast.controller;

import com.yss.ams.product.information.support.fast.controller.IBaseFastAssetsTreeController;
import com.yss.framework.api.commonInfo.pojo.FastAssetTreeATreeView;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_B;
import com.yss.framework.api.commonInfo.service.IFastAssetsTreeService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import java.util.HashMap;
import java.util.List;

public class FastAssetsTreeControllerImpl extends AbstractBaseController<IFastAssetsTreeService> implements IBaseFastAssetsTreeController {

	@Override
	public String insertTreeA(List<FastAssetsTree_A> pojoList)
			throws ServiceException {
		return getService().insertTreeA(pojoList);
	}

	@Override
	public String updateTreeAById(List<FastAssetsTree_A> pojoList)
			throws ServiceException {
		return getService().updateTreeAById(pojoList);
	}

	@Override
	public String auditTreeAById(FastAssetsTree_A pojo) throws ServiceException {
		return getService().auditTreeAById(pojo);
	}

	@Override
	public String unAuditTreeAById(FastAssetsTree_A pojo)
			throws ServiceException {
		return getService().unAuditTreeAById(pojo);
	}

	@Override
	public List<FastAssetsTree_A> queryTreeAByPojoIds(
			List<FastAssetsTree_A> pojos) throws Exception {
		return getService().queryTreeAByPojoIds(pojos);
	}

	@Override
	public String insertTreeB(List<FastAssetsTree_B> pojoList)
			throws ServiceException {
		return getService().insertTreeB(pojoList);
	}

	@Override
	public String insertTreeB(FastAssetsTree_B pojo) throws ServiceException {
		return getService().insertTreeB(pojo);
	}

	@Override
	public String auditTreeBById(List<FastAssetsTree_B> pojo)
			throws ServiceException {
		return getService().auditTreeBById(pojo);
	}

	@Override
	public String unAuditTreeBById(List<FastAssetsTree_B> pojo)
			throws ServiceException {
		return getService().unAuditTreeBById(pojo);
	}

	@Override
	public List<FastAssetsTree_B> queryTreeBByPojoIds(
			List<FastAssetsTree_B> pojos) throws Exception {
		return getService().queryTreeBByPojoIds(pojos);
	}

	@Override
	public List<FastAssetsTree_B> queryListByCondition(
			HashMap<String, String> paraMap) throws Exception {
		return getService().queryListByCondition(paraMap);
	}

	@Override
	public QueryRes getTreeViewData(HashMap<String, String> paraMap)
			throws ServiceException {
		return getService().getTreeViewData(paraMap);
	}

	@Override
	public List<FastAssetTreeATreeView> getTreeViewDataList(
			HashMap<String, String> paraMap) throws ServiceException {
		return getService().getTreeViewDataList(paraMap);
	}

	@Override
	public String isSameAssetType(String portCode, String dragPortCode)
			throws ServiceException {
		return getService().isSameAssetType(portCode,dragPortCode);
	}

	@Override
	public int updateOrdelete(String id, String trCode, String isParent,
			String type) throws ServiceException {
		return getService().updateOrdelete(id,trCode,isParent,type);
	}
	
}
