package com.yss.fast.systemmanager.assetstree.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.fast.systemmanager.support.assetstree.service.IFastAssetTreeService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.commonInfo.pojo.FastAssetTreeATreeView;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_A;
import com.yss.framework.api.commonInfo.pojo.FastAssetsTree_B;
import com.yss.framework.api.commonInfo.service.IFastAssetsTreeService;
import com.yss.framework.api.commonInfo.service.IFastPortService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;

/**
 * STORY #55709 公共组合区树形结构优化
 * 需要远程调用产品组件的服务
 * @author lenovo
 *
 */
public class FastAssetTreeService extends ServiceBus<FastAssetTreeService> implements IFastAssetTreeService {
	
	private IFastAssetsTreeService fastAssetsTreeService = null;
	
	private IFastPortService fastPortDataService = null;

	private FastAssetsTree_A convert2FastA(BasePojo pojo) {
		FastAssetsTree_A fastAssetsTree_A = null;
		if (null != pojo) {
			fastAssetsTree_A = JsonUtil.toBean(JsonUtil.toString(pojo), FastAssetsTree_A.class);
		} else {
			fastAssetsTree_A = new FastAssetsTree_A();
		}
		return fastAssetsTree_A;
	}
	
	private FastAssetsTree_B convert2FastB(BasePojo pojo) {
		FastAssetsTree_B fastAssetsTree_B = null;
		if (null != pojo) {
			fastAssetsTree_B = JsonUtil.toBean(JsonUtil.toString(pojo), FastAssetsTree_B.class);
		} else {
			fastAssetsTree_B = new FastAssetsTree_B();
		}
		return fastAssetsTree_B;
	}
	
	@Override
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		QueryRes res = null;
		if(null != fastAssetsTreeService){
			HashMap<String, String> inputParaMap = new HashMap<String, String>();
			for (String key : paraMap.keySet()) {
				inputParaMap.put(key, paraMap.get(key).toString());
			}
			res = fastAssetsTreeService.getTreeViewData(inputParaMap);
			if(null != res && null != res.getDataList()){
				//要设置当前的funCode，否则前端转换有问题
				res.setMenuId(getMenuId());
			}
		}
		return res;
	}

	/**
	 * 插入产品树
	 * @param 
	 * @return
	 */
	public String insert(List<BasePojo> pojoList){
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		if(null != fastAssetsTreeService){
			List<FastAssetsTree_A> fastTreeAList = new ArrayList<FastAssetsTree_A>();
			for (BasePojo pojo : pojoList) {
				fastTreeAList.add(convert2FastA(pojo));
			}
			return fastAssetsTreeService.insertTreeA(fastTreeAList);
		}
		return "";
	}
	
	@Override
	public String updateById(List<BasePojo> pojoList){
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		List<FastAssetsTree_A> fastTreeAList = new ArrayList<FastAssetsTree_A>();
		for (BasePojo pojo : pojoList) {
			fastTreeAList.add(convert2FastA(pojo));
		}
		return fastAssetsTreeService.updateTreeAById(fastTreeAList);
	}

	@Override
	public List<BasePojo> getTreeViewDataList(HashMap<String, Object> paraMap) {
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		List<BasePojo> result = new ArrayList<BasePojo>();
		if(null != fastAssetsTreeService){
			HashMap<String, String> inputParaMap = new HashMap<String, String>();
			for (String key : paraMap.keySet()) {
				inputParaMap.put(key, paraMap.get(key).toString());
			}
			 List<FastAssetTreeATreeView> resultData = fastAssetsTreeService.getTreeViewDataList(inputParaMap);
			 if(null != resultData){
				 for(FastAssetTreeATreeView data : resultData){
					 result.add((BasePojo)data);
				 }
				 return result;
			 }
		}
		return null;
	}
	
	@Override
	public String auditById(BasePojo pojo){
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		return fastAssetsTreeService.auditTreeAById(convert2FastA(pojo));
	}
	
	@Override
	public String unAuditById(BasePojo pojo){
		if(null == fastAssetsTreeService){
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		return fastAssetsTreeService.unAuditTreeAById(convert2FastA(pojo));
	}
	
	@Override
	public <K extends BasePojo> List<K> queryByPojoIds(List<BasePojo> pojos) throws Exception
	{
		if(null != pojos && pojos.size() > 0){
			BasePojo pojo = pojos.get(0);
			if(pojo instanceof FastAssetsTree_A){
				if(null == fastAssetsTreeService){
					fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
				}
				List<FastAssetsTree_A> fastTreeAList = new ArrayList<FastAssetsTree_A>();
				for (BasePojo basePojo : pojos) {
					fastTreeAList.add(convert2FastA(basePojo));
				}
				return (List<K>)fastAssetsTreeService.queryTreeAByPojoIds(fastTreeAList);
			}
			
			if(pojo instanceof FastAssetsTree_B){
				if(null == fastAssetsTreeService){
					fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
				}
				List<FastAssetsTree_B> fastTreeBList = new ArrayList<FastAssetsTree_B>();
				for (BasePojo basePojo : pojos) {
					fastTreeBList.add(convert2FastB(basePojo));
				}
				return (List<K>)fastAssetsTreeService.queryTreeBByPojoIds(fastTreeBList);
			}
		}
		return null;
	}

	@Override
	public QueryRes getAssetTreeView(HashMap<String, Object> paraMap) {
		if(null == fastPortDataService){
			fastPortDataService = YssServiceFactory.getInstance().createService(IFastPortService.class);
		}
		HashMap<String, String> inputParaMap = new HashMap<String, String>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key).toString());
		}
		return fastPortDataService.getAssetTreeView(inputParaMap);
	}

	@Override
	public String insertAssetData(ArrayList<BasePojo> pojoList) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		List<FastAssetsTree_B> fastTreeBList = new ArrayList<FastAssetsTree_B>();
		for (BasePojo pojo : pojoList) {
			fastTreeBList.add(convert2FastB(pojo));
		}
		return fastAssetsTreeService.insertTreeB(fastTreeBList);
	}
	
	@Override
	public String auditAssetDataById(ArrayList<BasePojo> pojoList) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		List<FastAssetsTree_B> fastTreeBList = new ArrayList<FastAssetsTree_B>();
		for (BasePojo pojo : pojoList) {
			fastTreeBList.add(convert2FastB(pojo));
		}
		return fastAssetsTreeService.auditTreeBById(fastTreeBList);
	}

	@Override
	public String unAuditAssetDataById(ArrayList<BasePojo> pojoList) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		List<FastAssetsTree_B> fastTreeBList = new ArrayList<FastAssetsTree_B>();
		for (BasePojo pojo : pojoList) {
			fastTreeBList.add(convert2FastB(pojo));
		}
		return fastAssetsTreeService.unAuditTreeBById(fastTreeBList);
	}

	@Override
	public String isSameAssetType(String portCode, String dragPortCode) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		return fastAssetsTreeService.isSameAssetType(portCode, dragPortCode);
	}

	@Override
	public int updateOrdelete(String id, String trCode, String isParent,
			String type) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		return fastAssetsTreeService.updateOrdelete(id, trCode, isParent, type);
	}

	@Override
	public String insertAssetData(BasePojo basePojo) {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		return fastAssetsTreeService.insertTreeB(convert2FastB(basePojo));
	}

	@Override
	public List<BasePojo> queryAssetDataByCond(HashMap<String, Object> paraMap) throws Exception {
		if(null == fastAssetsTreeService)
		{
			fastAssetsTreeService = YssServiceFactory.getInstance().createService(IFastAssetsTreeService.class);
		}
		HashMap<String, String> inputParaMap = new HashMap<String, String>();
		for (String key : paraMap.keySet()) {
			inputParaMap.put(key, paraMap.get(key).toString());
		}
		inputParaMap.put("dataClass", "AssetsTree_B");
		List<FastAssetsTree_B> fastTreeBlist = fastAssetsTreeService.queryListByCondition(inputParaMap);
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for (FastAssetsTree_B fastAssetsTree_B : fastTreeBlist) {
			basePojoList.add(fastAssetsTree_B);
		}
		return basePojoList;
	}

}
