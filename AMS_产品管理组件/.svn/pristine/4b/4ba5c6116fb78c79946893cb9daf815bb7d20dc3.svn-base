package com.yss.ams.product.information.support.modules.ab.assetsTree_a.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * <A区资产树型结构>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
public interface IAssetsTree_AService extends IServiceBus {
	
	/**
	 * 根据查询条件获取A区资产树形结构数据
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap);
	
	/**
	 * 根据查询条件获取A区资产树形结构数据列表
	 * 
	 * @param paraMap 查询条件
	 * @return List<BasePojo>
	 */
	public List<AssetTreeATreeView> getTreeViewDataList(HashMap<String, Object> paraMap);
	
	/**
	 * 获取轧差分组
	 * 
	 * @return List<AssetsTree_A>
	 */
	public List<AssetsTree_A> getNettingGroup();
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Comment: 更新【产品树形结构】A区产品类型中的执行顺序
	 * @param 更新产品树形结构集合	
	 */
	public String updateAssOrder(List<String> pojoList);
}
