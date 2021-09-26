package com.yss.ams.product.information.modules.pg.portgroup.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupDao;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupSqlBuilder;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;

/**
 * <群组管理>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupService extends ServiceBus<PortGroupService> implements IPortGroupService{
	
	private PortGroupDao serviceDao = null;
	
    // modified by HeLiang 2017-06-22 STORY #42921 产品信息组件拆分开发
    // FunCode使用新增的，避免获取映射关系错误
	String portGroupMemuId = "pd_portGroup";
	
	public PortGroupService() throws Exception{
		serviceDao = new PortGroupDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortGroupSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 查询群组A区数据
	 */
	public QueryRes getPortGroupA(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortGroupA(paraMap,this.bundleContext);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap(portGroupMemuId);
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(portGroupMemuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：A区查询群组失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		this.menuId = this.portGroupMemuId;
		QueryRes r =super.queryByCondition(paraMap,page); 
		return r;
	}
	
	/**
	 * 获取群组列表
	 */
	public QueryRes getPortGroupListData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortGroupListData(paraMap,this.bundleContext);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap(portGroupMemuId);
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(portGroupMemuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：查询群组信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/* *
	 * By Jinghehe 2014-6-3 获取群组
	 * 获取群组关联方案界面的新增群组
	 */
	@Override
	public QueryRes getPlanRelaPortGroupAdd(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPlanRelaPortGroupAdd(paraMap);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap(portGroupMemuId);
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(portGroupMemuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：获取群组关联方案界面的新增群组失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/* *
	 * By Jinghehe 2014-6-3 获取群组
	 * 获取群组关联方案界面的浏览群组
	 */
	@Override
	public QueryRes getPlanRelaPortGroupBrow(HashMap<String, Object> paraMap)
			throws Exception {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPlanRelaPortGroupBrow(paraMap);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap(portGroupMemuId);
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(portGroupMemuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：获取群组关联方案界面的新增群组失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	@Override
	public String checkGroupCode(String groupCode){
		return serviceDao.checkGroupCode(groupCode);
	}
	
	/**
	 * 派工单 #2333 估值_V1.300.7.0_UI自动化测试_自动化测试(272)
	 * @return
	 */
	@Override
	public String checkGroupCode(String groupCode, String ciden){
		return serviceDao.checkGroupCode(groupCode, ciden);
	}
}
