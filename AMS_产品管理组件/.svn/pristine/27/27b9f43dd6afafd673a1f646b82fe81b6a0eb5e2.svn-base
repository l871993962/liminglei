package com.yss.right.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortService extends IServiceBus,IDataRightProvider,IKeyConvertDataService, IControlDataService {

	/**
	 * 获取组合关联方案新增组合
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes getPlanRelaPortAdd(HashMap<String, Object> paraMap);

	/**
	 * 获取组合关联方案浏览组合
	 * 
	 * @param paraMap
	 * @return
	 */
	QueryRes getPlanRelaPortBrow(HashMap<String, Object> paraMap);

	/**
	 * 获取参数设置中的组合数据
	 * 
	 * @param paraMap
	 *            查询条件参数集合
	 * @return 组合列表
	 */
	public List<Port> getParamSetPortList(HashMap<String, Object> paraMap);

	/**
	 * 获取 参数 投资组合
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes getDspPort(HashMap<String, Object> paraMap);

	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception;

	/**
	 * 查询单条记录,清算综合参数SET界面浏览组合时 added by ll 20130109
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryDataByBrow(HashMap<String, Object> paraMap);

	/**
	 * 投资组合数据（树形结构）
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap);

	/**
	 * 单元层投资组合数据（列表结构）
	 * 
	 * @author tangshifeng
	 * @since 2013-04-17
	 * @param paraMap
	 * @return
	 */
	public QueryRes getUnitPortData(HashMap<String, Object> paraMap);

	public QueryRes getAssetTreeView(HashMap<String, Object> paraMap);

	/* START 资产数列表详细信息界面获取数据列表 */
	public QueryRes getPortAssTreeList(HashMap<String, Object> paraMap)
			throws ServiceException;

	public QueryRes getPortAssTreeListAddForm(HashMap<String, Object> paraMap)
			throws ServiceException;

	/* END 资产数列表详细信息界面获取数据列表 */
	
	/**
	 *  到期确认
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  到期取消
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQX(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算确认
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算取消
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQX(List<Port> lstPort) throws ServiceException;
    
    /**
   	 *  清算关账
   	 *  @param lstPort 组合列表
   	 *  @return 执行状态
   	 */
       String operQSGZ(List<Port> lstPort) throws ServiceException,Exception;

       /**
   	 *  关账撤销
   	 *  @param lstPort 组合列表
   	 *  @return 执行状态
   	 */
       String operGZCX(List<Port> lstPort) throws ServiceException;
    
    /**
     * 获取和目标组合资产代码重复的组合列表
     * @param portCode 组合代码
     * @param assCode 资产代码
     * @return
     * @author liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
     */
    List<Port> getTheSameAssCodeList(String  portCode, String assCode) throws ServiceException;
    
    /**
     * Author : ChenLong
     * Date   : 2016-07-25
     * Status : Add
     * Comment: 获取产品的资产类别关系
     * @param portCodes
     * @return
     */
    public Map<String,String> getPortDatClsMap(String[] portCodes);
    /**
     * 根据组合代码获取组合列表
     * @param portCode
     * @return
     */
    public List<Port> getPortListByPortCode(String portCodes);
}
