package com.yss.ams.product.information.fast.service;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.service.IAssetsTree_AService;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.commonInfo.pojo.FastAssetTreeATreeView;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_B;
import com.yss.framework.api.commonInfo.service.IFastAssetsTreeService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FastAssetsTreeService implements IFastAssetsTreeService {
	private IAssetsTree_AService getServiceByAssetsTree_AService() {
		return (IAssetsTree_AService) YssServiceFactory.getInstance().createService(IAssetsTree_AService.class);
	}

	private IAssetsTree_BService getServiceByAssetsTree_BService() {
		return (IAssetsTree_BService) YssServiceFactory.getInstance().createService(IAssetsTree_BService.class);
	}

	private AssetsTree_A convert2TreeA(FastAssetsTree_A fastPojo) {
		if (fastPojo == null) {
			return null;
		}
		String fastPojoJson = JsonUtil.toString(fastPojo);
		AssetsTree_A treeA = (AssetsTree_A) JsonUtil.toBean(fastPojoJson, AssetsTree_A.class);
		return treeA;
	}

	private List<AssetsTree_A> convert2ListTreeA(List<FastAssetsTree_A> fastPojo) {
		if (fastPojo == null) {
			return null;
		} else if (fastPojo.size() == 0) {
			return new ArrayList<AssetsTree_A>();
		}
		String fastPojoJson = JsonUtil.toString(fastPojo);
		List<AssetsTree_A> listTreeA = JsonUtil.toList(fastPojoJson, AssetsTree_A.class);
		return listTreeA;
	}

	private AssetsTree_B convert2TreeB(FastAssetsTree_B fastPojo) {
		if (fastPojo == null) {
			return null;
		}
		String fastPojoJson = JsonUtil.toString(fastPojo);
		AssetsTree_B treeB = (AssetsTree_B) JsonUtil.toBean(fastPojoJson, AssetsTree_B.class);
		return treeB;
	}

	private List<AssetsTree_B> convert2ListTreeB(List<FastAssetsTree_B> fastPojo) {
		if (fastPojo == null) {
			return null;
		} else if (fastPojo.size() == 0) {
			return new ArrayList<AssetsTree_B>();
		}
		String fastPojoJson = JsonUtil.toString(fastPojo);
		List<AssetsTree_B> listTreeB = JsonUtil.toList(fastPojoJson, AssetsTree_B.class);
		return listTreeB;
	}

	private List<FastAssetsTree_A> convert2FastListTreeA(
			List<AssetsTree_A> treeA) {
		if (treeA == null) {
			return null;
		} else if (treeA.size() == 0) {
			return new ArrayList<FastAssetsTree_A>();
		}
		String treeAPojoJson = JsonUtil.toString(treeA);
		List<FastAssetsTree_A> fastTreeA = JsonUtil.toList(treeAPojoJson, FastAssetsTree_A.class);
		return fastTreeA;
	}

	private List<FastAssetsTree_B> convert2FastListTreeB(
			List<AssetsTree_B> treeB) {
		if (treeB == null) {
			return null;
		} else if (treeB.size() == 0) {
			return new ArrayList<FastAssetsTree_B>();
		}
		String treeBPojoJson = JsonUtil.toString(treeB);
		List<FastAssetsTree_B> fastTreeB = JsonUtil.toList(treeBPojoJson, FastAssetsTree_B.class);
		return fastTreeB;
	}

	private List<FastAssetTreeATreeView> convert2ListTreeView(
			List<AssetTreeATreeView> treeView) {
		if (treeView == null) {
			return null;
		} else if (treeView.size() == 0) {
			return new ArrayList<FastAssetTreeATreeView>();
		}
		String treeViewPojoJson = JsonUtil.toString(treeView);
		List<FastAssetTreeATreeView> fastTreeView = JsonUtil.toList(treeViewPojoJson, FastAssetTreeATreeView.class);
		return fastTreeView;
	}

	public String insertTreeA(List<FastAssetsTree_A> pojoList)
			throws ServiceException {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_A> listTreeA = convert2ListTreeA(pojoList);
		for (AssetsTree_A AssetsTree_A : listTreeA) {
			basePojoList.add(AssetsTree_A);
		}
		return getServiceByAssetsTree_AService().insert(basePojoList);
	}

	public String updateTreeAById(List<FastAssetsTree_A> pojoList)
			throws ServiceException {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_A> listTreeA = convert2ListTreeA(pojoList);
		for (AssetsTree_A AssetsTree_A : listTreeA) {
			basePojoList.add(AssetsTree_A);
		}
		return getServiceByAssetsTree_AService().updateById(basePojoList);
	}

	public String auditTreeAById(FastAssetsTree_A pojo) throws ServiceException {
		return getServiceByAssetsTree_AService().auditById(convert2TreeA(pojo));
	}

	public String unAuditTreeAById(FastAssetsTree_A pojo)
			throws ServiceException {
		return getServiceByAssetsTree_AService().unAuditById(
				convert2TreeA(pojo));
	}

	public List<FastAssetsTree_A> queryTreeAByPojoIds(
			List<FastAssetsTree_A> pojos) throws Exception {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_A> listTreeA = convert2ListTreeA(pojos);
		for (AssetsTree_A AssetsTree_A : listTreeA) {
			basePojoList.add(AssetsTree_A);
		}
		List<AssetsTree_A> treeAList = new ArrayList<AssetsTree_A>();
		IAssetsTree_AService assetsTree_AService = getServiceByAssetsTree_AService();
		assetsTree_AService.setServiceBindingClass(AssetsTree_A.class);
		List<BasePojo> returnBasePojoList = assetsTree_AService
				.queryByPojoIds(basePojoList);
		for (BasePojo fastTree_A : returnBasePojoList) {
			treeAList.add((AssetsTree_A) fastTree_A);
		}
		return convert2FastListTreeA(treeAList);
	}

	public String insertTreeB(List<FastAssetsTree_B> pojoList)
			throws ServiceException {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_B> listTreeB = convert2ListTreeB(pojoList);
		for (AssetsTree_B AssetsTree_B : listTreeB) {
			basePojoList.add(AssetsTree_B);
		}
		return getServiceByAssetsTree_BService().insert(basePojoList);
	}

	public String insertTreeB(FastAssetsTree_B pojo) throws ServiceException {
		return getServiceByAssetsTree_BService().insert(convert2TreeB(pojo));
	}

	public String auditTreeBById(List<FastAssetsTree_B> pojoList)
			throws ServiceException {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_B> listTreeB = convert2ListTreeB(pojoList);
		for (AssetsTree_B AssetsTree_B : listTreeB) {
			basePojoList.add(AssetsTree_B);
		}
		return getServiceByAssetsTree_BService().auditById(basePojoList);
	}

	public String unAuditTreeBById(List<FastAssetsTree_B> pojoList)
			throws ServiceException {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_B> listTreeB = convert2ListTreeB(pojoList);
		for (AssetsTree_B AssetsTree_B : listTreeB) {
			basePojoList.add(AssetsTree_B);
		}
		return getServiceByAssetsTree_BService().unAuditById(basePojoList);
	}

	public List<FastAssetsTree_B> queryTreeBByPojoIds(
			List<FastAssetsTree_B> pojos) throws Exception {
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		List<AssetsTree_B> listTreeB = convert2ListTreeB(pojos);
		for (AssetsTree_B AssetsTree_B : listTreeB) {
			basePojoList.add(AssetsTree_B);
		}
		List<AssetsTree_B> treeBList = new ArrayList<AssetsTree_B>();
		IAssetsTree_BService assetsTree_BService = getServiceByAssetsTree_BService();
		assetsTree_BService.setServiceBindingClass(AssetsTree_B.class);
		List<BasePojo> returnBasePojoList = assetsTree_BService
				.queryByPojoIds(basePojoList);
		for (BasePojo fastTree_B : returnBasePojoList) {
			treeBList.add((AssetsTree_B) fastTree_B);
		}
		return convert2FastListTreeB(treeBList);
	}

	public List<FastAssetsTree_B> queryListByCondition(
			HashMap<String, String> paraMap) throws Exception {
		HashMap<String, Object> InputParaMap = new HashMap<String, Object>();
		for (String str : paraMap.keySet()) {
			InputParaMap.put(str, paraMap.get(str));
		}
		List<AssetsTree_B> treeBList = new ArrayList<AssetsTree_B>();
		List<BasePojo> returnBasePojoList = getServiceByAssetsTree_BService().queryListByCondition(InputParaMap);
		for (BasePojo fastTree_B : returnBasePojoList) {
			treeBList.add((AssetsTree_B) fastTree_B);
		}
		return convert2FastListTreeB(treeBList);
	}

	public QueryRes getTreeViewData(HashMap<String, String> paraMap)
			throws ServiceException {
		HashMap<String, Object> InputParaMap = new HashMap<String, Object>();
		for (String str : paraMap.keySet()) {
			InputParaMap.put(str, paraMap.get(str));
		}
		QueryRes res = getServiceByAssetsTree_AService().getTreeViewData(
				InputParaMap);
		List<AssetsTree_A> listTreeA = new ArrayList<AssetsTree_A>();
		for (BasePojo pojo : res.getDataList()) {
			listTreeA.add((AssetsTree_A) pojo);
		}
		List<FastAssetsTree_A> fastListTreeA = convert2FastListTreeA(listTreeA);
		List<BasePojo> listBasePojo = new ArrayList<BasePojo>();
		for (FastAssetsTree_A treeA : fastListTreeA) {
			listBasePojo.add(treeA);
		}
		res.setDataList(listBasePojo);
		res.setListDataClass(FastAssetsTree_A.class.getSimpleName());
		return res;
	}

	public List<FastAssetTreeATreeView> getTreeViewDataList(
			HashMap<String, String> paraMap) throws ServiceException {
		HashMap<String, Object> InputParaMap = new HashMap<String, Object>();
		for (String str : paraMap.keySet()) {
			InputParaMap.put(str, paraMap.get(str));
		}
		return convert2ListTreeView(getServiceByAssetsTree_AService().getTreeViewDataList(InputParaMap));
	}

	public String isSameAssetType(String portCode, String dragPortCode)
			throws ServiceException {
		return getServiceByAssetsTree_BService().isSameAssetType(portCode, dragPortCode);
	}

	public int updateOrdelete(String id, String trCode, String isParent,
			String type) throws ServiceException {
		return getServiceByAssetsTree_BService().updateOrdelete(id, trCode, isParent, type);
	}
}
