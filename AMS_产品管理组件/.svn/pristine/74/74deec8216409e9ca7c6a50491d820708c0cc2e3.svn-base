package com.yss.platform.support.dataservice.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.dataservice.ITreeViewDataService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.right.pojo.DataRight;

@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortDataService extends IControlDataService,
		IKeyConvertDataService, ITreeViewDataService,IDataServiceForCache {
	public List<Port> getRightManagePortList(
			HashMap<String, String> paraMap) throws ServiceException;

	public List<Port> getTreeViewListByCondion(
			HashMap<String, Object> paraMap) throws ServiceException;

	public QueryRes getTreeViewResByCondion(HashMap<String, Object> paraMap)
			throws ServiceException;
	
	public List<Port> doPortFilterPort() throws ServiceException ;

	/**
	 * 获取用户自定义组合
	 * 
	 * @param ports
	 *            组合代码列表
	 * @param cTrCode
	 *            结构代码
	 * @return
	 * @throws ServiceException
	 */
	QueryRes getDefaultPort(String ports, String cTrCode)
			throws ServiceException;

	/**
	 * 根据代码和启用日期查询组合
	 * 
	 * @param portCode
	 * @param buildDate
	 * @return
	 * @throws ServiceException
	 */
	public List<Port> getListByCodeAndBuildDate(String portCode, Date buildDate)
			throws ServiceException;

	public void insert(Port port) throws ServiceException;
	public String insertWithRetInfo(Port port) throws ServiceException;

	public void updateById(Port port) throws ServiceException;

	public List<Port> queryByCondition(HashMap<String, Object> paraMap)
			throws ServiceException;

	public void deleteById(Port pojo) throws ServiceException;

	public void audit(Port pojo) throws ServiceException;

	public void antiAudit(Port pojo) throws ServiceException;

	/**
	 * 根据组合分级获取组合列表
	 * 
	 * @param datClass
	 * @return
	 * @throws ServiceException
	 */
	public List<BasePojo> getPortListByDatClass(String datClass)
			throws ServiceException;

	/* START 条件筛选方法 */
	@LinkControllerMethod(value = "doPortFilter")
	public List<BasePojo> doPortFilter() throws ServiceException;

	@LinkControllerMethod(value = "doPortFilterRes")
	public QueryRes doPortFilterRes() throws ServiceException;

	@LinkControllerMethod(value = "doPortFilter", arguTypes = {String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass)
			throws ServiceException;

	@LinkControllerMethod(value = "doPortFilterRes", arguTypes = {String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass)
			throws ServiceException;

	@LinkControllerMethod(value = "doPortFilter", arguTypes = {String.class,String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException;

	@LinkControllerMethod(value = "doPortFilterRes", arguTypes = {String.class,String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException;

	@LinkControllerMethod(value = "doPortFilter", arguTypes = {String.class,String.class,String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException;
	
	@LinkControllerMethod(value = "queryDataRight", arguTypes = {String.class})
	public List<DataRight> queryDataRight(String trCode) throws ServiceException;

	@LinkControllerMethod(value = "doPortFilterRes", arguTypes = {String.class,String.class,String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException;
	
	/**
	 * 根据组合级别加载组合列表，不受权限控制
	 * 
	 * @param dvPortCode 组合级别
	 * @return
	 * @throws ServiceException
	 */
	public List<BasePojo> getPortListByDvPortCode(String dvPortCode)
			throws ServiceException;
	
	/**
	 * 根据组合代码查询组合信息
	 * @param cPortCode 组合代码
	 * @return 组合信息
	 * @throws ServiceException
	 */
	public Port getPortInfo(String cPortCode) throws ServiceException;
	
	/**
	 * 根据资产代码获取组合信息
	 * @param assCode 资产代码
	 * @return
	 * @throws ServiceException
	 */
	public Port getPortByAssCode(String assCode) throws ServiceException;
	
	/* END 条件筛选方法 */
	
	public List<BasePojo> getPortTreeList() throws ServiceException;
	/**
	 * 获取证券品种树
	 * @return
	 */
	@LinkControllerMethod(value = "getStockTypeList")
	public List<BasePojo> getStockTypeList() throws ServiceException;

	public List<BasePojo> getPortListByUserAndPost(String userCode,
			String postCodes) throws ServiceException;
	
	public List<Port> getPortByUserAndPost(String userCode,
			String postCodes) throws ServiceException;
	
	//added by wangzhiye 2014-03-18  获取证券品种树  
	@LinkControllerMethod(value = "getStockTypeList", arguTypes = {String.class})
	public List<BasePojo> getStockTypeList(String userCode) throws ServiceException;

	public QueryRes getPortResByUserAndPost(String userCode, String postCodes)
			throws ServiceException;
	
	/* 不用进行条件进行筛选的方法,不进行审核状态的判断 */
	public <K extends BasePojo> List<K> getPortDataByKeysNoFilter(String[] keys)
			throws ServiceException;
	
	public List<Port> getPortInfoList(HashMap<String, Object> paraMap) throws ServiceException;
	
	public <K extends BasePojo> List<K> getAllDataList() throws ServiceException;
	
	/**
	 * 根据组合名称取组合代码，用于银行间Api
	 */
	public Port getPortByPortName(String portName) throws ServiceException;
	
	/**
     * 根据组合代码获取下级组合数量 用于定向产品基本信息
     * @param portCodeP 组合代码
     * @return 下级组合数
     */
    public String getPortCodePSubCount(String portCodeP);
    
    /**
	 *  到期确认 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  到期撤销 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQX(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算确认 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算撤销 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQX(List<Port> lstPort) throws ServiceException;
    
    /**
     * 获得资产树型结构
     * @param trCode
     * @return
     * @throws Exception
     */
    List<Port> getAssPort(String trCode) throws Exception;
    
    /**
     * 查询A区群组数据，以树形展示 STORY #16818 产品群组需求 add by chenwenhai 20140603
     * @param trCode
     * @return
     * @throws Exception
     */
    List<Port> getGroupDataTree(String trCode) throws Exception;
    
    /**
     * 检查群组代码是否在组合中已经存在 true:已经存在；false:不存在
     * @param groupCode
     * @return
     */
    String checkPortCode(String portCode);
    
    /**
     * 询下面存在子组合的组合信息 By Jinghehe 2014-7-28
	 * 根据用户和用户岗位来加载数据 
     * @param userCode
     * @param postCode
     * @return
     * @throws ServiceException
     */
	@LinkControllerMethod(value = "queryParentPortTreeViewData", arguTypes = {String.class,String.class})
    public QueryRes queryParentPortTreeViewData(String userCode,String postCode) throws ServiceException;
    
    /**
     * 询产品和组合信息 
     * @return
     * @throws ServiceException
     */
    public QueryRes queryProductORPort() throws ServiceException;
    
    public List<BasePojo> getTreePortDataByCodes(String[] keys) throws ServiceException;

    /**
     * 获取群组和组合(有权限控制) add by liuxiang 2014-3-11
     * @return
     * @throws Exception
     */
    public List<BasePojo> getAllGroupAndPort() throws Exception;

	
	/**
     * 查询已清算到期组合
     * @param paraMap
     * @return
     */
    public List<Port> dueClearedPorts(HashMap<String, String> paraMap);
    
    public Map<String,String> getBindPortAndPost(String userCode,String portCodes);
    
    public <K extends BasePojo> List<K> getFilterPortDataForOperRight(String menuId)throws YssException;
    
	@LinkControllerMethod(value = "queryParentPortTreeViewData", arguTypes = {String.class,String.class,String.class})
    public QueryRes queryParentPortTreeViewData(String userCode,String postCode,String menuId);
	    
    /**
     * 20150424 added by liubo.STORY #22372 【紧急】招商现场提出的关于报表中组合下拉树调整
     * 为报表中心的查询出符合用户权限和产品树的trcode，获取组合列表数据。
     * 不用之前的旧方法，是因为包装处理后的List对象包含的都是BasePojo,在报表中心的包中无法转换成子类Port
     * 这就必须写一个方法，专门用来返回一个List<Port>
     * @param isDataRight
     * @param datClass
     * @param dvPortCode
     * @param trCode
     * @return
     * @throws Exception
     */
    public List<Port> PortFilter_ReportCenter(boolean isDataRight,String datClass, String dvPortCode, String trCode) throws Exception;

  	/**
  	 * 根据资产代码列表获取组合信息列表
		 * Fixed by huangsq 20160811 STORY #26296 [招商证券]估值表发布及获取接口需求
  	 * @param assCodes 资产代码列表
  	 * @return
  	 */
	public List<Port> getPortByAssCodes(List<String> assCodes);
		
	/**
	 * 查询数据权限
	 * @param trCode 数据维度代码
	 * @param portCodes 数据集合
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryDataRight", arguTypes = {String.class,String[].class})
	public List<DataRight> queryDataRight(String trCode,String[] portCodes);
	
	/**
	 * 查询数据权限
	 * @param trCode 数据维度代码
	 * @param portCodes 数据集合
	 * @param customParam 组合状态
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryDataRight", arguTypes = {String.class,String[].class,HashMap.class})
	public List<DataRight> queryDataRight(String trCode,String[] portCodes,HashMap<String,String> customParam);
	
	/**
	 * 根据编号和状态查询
	 * @Title queryPort 
	 * @Description 根据编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	public List<Port> queryPort(List<String> portCodes,List<String> productState);
	
	/**
	 * 根据编号和状态查询
	 * @Title queryPort 
	 * @Description 根据编号和状态查询
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes
	 * @param productState
	 * @return
	 * @return List<Port>
	 */
	public HashMap<String, String> queryPortMap(List<String> portCodes,List<String> productState);


	/*
     * 手工映射科目过滤已映射科目
     * 
     */
    public QueryRes doPortFilterResKMmap(String isDataRight, String datClass, String dvPortCode, String trcode, String getKM, String mapyear, String unMapkm) throws ServiceException;

	 /**
	  * @Desc  获取所有组合数据（默认资产树结构）
	  * @author houjiaqi
	  * @date 2019年3月12日 下午4:49:39
	  * @param @return
	  * @param @throws Exception
	  */
	 List<Map<String, String>> getAllPortData() throws Exception;
}
