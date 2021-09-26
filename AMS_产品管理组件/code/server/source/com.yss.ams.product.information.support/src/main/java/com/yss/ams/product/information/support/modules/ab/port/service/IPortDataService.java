package com.yss.ams.product.information.support.modules.ab.port.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.port.pojo.PortCacheData;
import com.yss.ams.product.information.support.modules.ab.port.vo.DoPortFilterResVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetGroupDataTreeVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.GetListByCodeAndBuildDateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight1Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryDataRight2Vo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortMapVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.QueryPortVo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.commonInfo.pojo.FastPortData;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.right.pojo.DataRight;

/**
 * <产品基本信息>数据服务接口，主要进行跨应用数据获取
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortDataService  extends IDataService, 
IControlDataService,IKeyConvertDataService,IDataServiceForCache{
	public List<Port> getRightManagePortList(
			HashMap<String, String> paraMap) throws ServiceException;

	public List<Port> getTreeViewListByCondion(
			HashMap<String, Object> paraMap) throws ServiceException;
	
	/**
	 * trCdoe为null或空时查询组合信息，
	 * 
	 * */
	HashMap<String, Port> getPortDataMapWithNullTrCode();
	
	
	/**
	 * 根据查询条件获取树型显示的组合数据集
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 * @throws ServiceException
	 */
	public QueryRes getTreeViewResByCondion(HashMap<String, Object> paraMap)
			throws ServiceException;

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
	@LinkControllerMethod(value="getListByCodeAndBuildDate",arguTypes = GetListByCodeAndBuildDateVo.class)
	public List<Port> getListByCodeAndBuildDate(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("buildDate")Date buildDate)
			throws ServiceException;

	/**
	 * 保存组合信息
	 * 
	 * @param port 组合实体
	 * @throws ServiceException
	 */
	public void insert(Port port) throws ServiceException;
	
	/**
	 * 保存组合信息，可返回插入数据的C_IDEN值
	 * 
	 * @param port 组合实体
	 * @return 插入数据的C_IDEN值
	 * @throws ServiceException
	 */
	public String insertWithRetInfo(Port port) throws ServiceException;

	/**
	 * 更新组合信息
	 * 
	 * @param port 组合实体
	 * @throws ServiceException
	 */
	public void updateById(Port port) throws ServiceException;

	/**
	 * 根绝查询条件获取组合列表
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 * @throws ServiceException
	 */
	public List<Port> queryByCondition(HashMap<String, Object> paraMap)
			throws ServiceException;

	/**
	 * 删除组合信息
	 * 
	 * @param pojo 组合实体
	 * @throws ServiceException
	 */
	public void deleteById(Port pojo) throws ServiceException;

	/**
	 * 审核组合信息
	 * 
	 * @param pojo 组合实体
	 * @throws ServiceException
	 */
	public void audit(Port pojo) throws ServiceException;

	/**
	 * 反审核组合信息
	 * 
	 * @param pojo 组合实体
	 * @throws ServiceException
	 */
	public void antiAudit(Port pojo) throws ServiceException;

	/**
	 * 根据组合分级获取组合列表
	 * 
	 * @param datClass 资产类别
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	public List<BasePojo> getPortListByDatClass(String datClass)
			throws ServiceException;

	/* START 条件筛选方法 */
	
	/**
	 * 组合筛选操作
	 * 
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilter")
	public List<BasePojo> doPortFilter() throws ServiceException;

	/**
	 * 组合筛选操作
	 * 
	 * @return QueryRes 组合结果集
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilterRes")
	public QueryRes doPortFilterRes() throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilter1", arguTypes = {String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass)
			throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @return QueryRes 组合结果集
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilterRes1", arguTypes = {String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass)
			throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilter2", arguTypes = {String.class,String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @return QueryRes 组合结果集
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilterRes2", arguTypes = {String.class,String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode) throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @param trCode 组合树结构代码
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilter3", arguTypes = {String.class,String.class,String.class,String.class})
	public List<BasePojo> doPortFilter(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException;
	
	/**
	 * 根据组合树结构代码查询其结构下组合的权限数据
	 * 
	 * @param trCode 组合树结构代码
	 * @return List<DataRight> 数据权限列表
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryDataRight", arguTypes = {String.class})
	public List<DataRight> queryDataRight(String trCode) throws ServiceException;

	/**
	 * 根据参数进行组合筛选操作
	 * 
	 * @param isDataRight 是否有数据权限
	 * @param datClass 资产类别
	 * @param dvPortCode 组合级别
	 * @param trCode 组合树结构代码
	 * @return QueryRes 组合结果集
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "doPortFilterRes3", arguTypes = {String.class,String.class,String.class,String.class})
	public QueryRes doPortFilterRes(String isDataRight, String datClass,
			String dvPortCode, String trCode) throws ServiceException;
	
	/**
	 * 根据组合级别加载组合列表，不受权限控制
	 * 
	 * @param dvPortCode 组合级别
	 * @return List<BasePojo> 组合列表
	 * @throws ServiceException
	 */
	public List<BasePojo> getPortListByDvPortCode(String dvPortCode)
			throws ServiceException;
	
	/**
	 * 根据组合代码查询组合信息
	 * 
	 * @param cPortCode 组合代码
	 * @return Port 组合信息
	 * @throws ServiceException
	 */
	public Port getPortInfo(String cPortCode) throws ServiceException;
	
	/**
	 * 根据资产代码获取组合信息
	 * 
	 * @param assCode 资产代码
	 * @return Port 组合信息
	 * @throws ServiceException
	 */
	public Port getPortByAssCode(String assCode) throws ServiceException;
	
	/* END 条件筛选方法 */
	
	/**
	 * 根据用户代码和岗位代码获得组合列表
	 * 
	 * @param userCode 用户代码
	 * @param postCodes 岗位代码
	 * @return List<BasePojo> 组合列表
	 */
	public List<BasePojo> getPortListByUserAndPost(String userCode,
			String postCodes) throws ServiceException;
	
	/**
	 * 根据用户代码和岗位代码获得组合列表
	 * 
	 * @param userCode 用户代码
	 * @param postCodes 岗位代码
	 * @return List<Port> 组合列表
	 */
	public List<Port> getPortByUserAndPost(String userCode,
			String postCodes) throws ServiceException;
	
	/**
	 * 获取证券品种树
	 * added by wangzhiye 2014-03-18
	 * 
	 * @param userCode 用户代码
	 * @return List<BasePojo> 证券品种列表
	 */
	@LinkControllerMethod(value = "getStockTypeList", arguTypes = {String.class})
	public List<BasePojo> getStockTypeList(String userCode) throws ServiceException;

	/**
	 * 根据用户代码和岗位代码获得组合树
	 * 
	 * @param userCode 用户代码
	 * @param postCodes 岗位代码
	 * @return 指定用户的指定岗位的组合树
	 */
	public QueryRes getPortResByUserAndPost(String userCode, String postCodes)
			throws ServiceException;
	
	/**
	 * 根据组合代码获取组合数据
	 * 不用进行条件筛选的方法,不进行审核状态的判断
	 * 
	 * @param keys 代码
	 * @return List 组合列表
	 */
	public <K extends BasePojo> List<K> getPortDataByKeysNoFilter(String[] keys)
			throws ServiceException;
	
	/**
	 * 根据条件查询组合信息
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 */
	public List<Port> getPortInfoList(HashMap<String, Object> paraMap) throws ServiceException;
	
	/**
	 * 获取所有组合数据
	 * 
	 * @return List 组合列表
	 */
	public <K extends BasePojo> List<K> getAllDataList() throws ServiceException;
	
	/**
	 * 根据组合名称取组合信息，用于银行间API
	 * 
	 * @param portName 组合名称
	 * @return Port 组合信息
	 */
	public Port getPortByPortName(String portName) throws ServiceException;
	
	/**
     * 根据组合代码获取下级组合数量 用于定向产品基本信息
     * 
     * @param portCodeP 组合代码
     * @return 下级组合数
     */
    public String getPortCodePSubCount(String portCodeP);
    
    /**
	 *  到期确认操作 By Jinghehe 2014-3-19
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  到期撤销操作 By Jinghehe 2014-3-19
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQX(List<Port> lstPort) throws ServiceException;

   /* *//**
	 *  清算确认 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 *//*
    String operQSQR(List<Port> lstPort) throws ServiceException;

    *//**
	 *  清算撤销 By Jinghehe 2014-3-19
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 *//*
    String operQSQX(List<Port> lstPort) throws ServiceException;*/
    
    /**
     * 根据树型结构代码获取资产树型结构
     * 
     * @param trCode 资产树型结构代码
     * @return List<Port> 组合列表
     * @throws Exception
     */
    List<Port> getAssPort(String trCode) throws Exception;
    
    /**
     * 查询A区群组数据，以树形展示
     * STORY #16818 产品群组需求 add by chenwenhai 20140603
     * 
     * @param trCode 资产树型结构代码
     * @return List<Port> 组合列表
     * @throws Exception
     */
    @LinkControllerMethod(value = "getGroupDataTree", arguTypes = {String.class})
    List<Port> getGroupDataTree(String trCode) throws Exception;
    
    @LinkControllerMethod(value = "getGroupDataTree1", arguTypes = GetGroupDataTreeVo.class)
    List<Port> getGroupDataTree(@LinkControllerMethodArgu("trCode")String trCode, @LinkControllerMethodArgu("portList")List<String> portList) throws Exception;
    /**
     * 资产类别数据数据，以树形展示
     *STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
     * neil
     * 2019-07-26
     * @param trCode 资产树型结构代码
     * @return List<Port> 组合列表
     * @throws Exception
     */
    List<Port> getAssGroupDataTree(String trCode) throws Exception;
    
    /**
     * 检查群组代码是否在组合中已经存在 true:已经存在；false:不存在
     * 
     * @param groupCode 群组代码
     * @return String 是否存在
     */
    String checkPortCode(String groupCode);
    
    /**
     * 查询下面存在子组合的组合信息 By Jinghehe 2014-7-28
	 * 根据用户和用户岗位来加载数据
	 * 
     * @param userCode 用户代码
     * @param postCode 岗位代码
     * @return QueryRes 含子组合的组合信息
     * @throws ServiceException
     */
    @LinkControllerMethod(value = "queryParentPortTreeViewData", arguTypes = {String.class,String.class})
    public QueryRes queryParentPortTreeViewData(String userCode,String postCode) throws ServiceException;
    
    /**
     * 查询产品和组合信息 
     * 
     * @return QueryRes
     * @throws ServiceException
     */
    public QueryRes queryProductORPort() throws ServiceException;
    
    /**
     * 根据组合代码获取组合树列表（包括其下的分级组合）
	 * 
     * @param keys 组合代码
     * @return List<BasePojo> 组合列表
     * @throws ServiceException
     */
    public List<BasePojo> getTreePortDataByCodes(String[] keys) throws ServiceException;

    /**
     * 获取群组和组合(有权限控制) add by liuxiang 2014-3-11
     * 
     * @return List<BasePojo>
     * @throws Exception
     */
    public List<BasePojo> getAllGroupAndPort() throws Exception;
    /**
     * 获取群组和组合(无权限控制)
     * @return
     * @throws Exception
     */
    public List<BasePojo> getAllGroupAndPortNoRight() throws Exception;

	
    /**
     * 获取群组和组合(有权限控制)
     *STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
     * neil
     * 2019-07-26
     */
    public List<BasePojo> getAllAssGroupAndPort() throws Exception;
	
	/**
     * 查询A区群组数据，以树形展示，无用户和岗位权限
     * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
     * 
     * @param trCode 资产树型结构代码
     * @return List<Port> 组合列表
     * @throws Exception
     */
    List<Port> getGroupDataTreeWithoutRight(String trCode) throws Exception;
	
	/**
     * 根据条件查询已清算到期组合
     * 
     * @param paraMap 查询条件
     * @return List<Port> 组合列表
     */
    public List<Port> dueClearedPorts(HashMap<String, String> paraMap);
    
    /**
     * 根据用户代码和岗位代码获取所绑定的组合信息
     * 
     * @param userCode 用户代码
     * @param postCodes 岗位代码
     * @return Map<String,String>
     */
    public Map<String,String> getBindPortAndPost(String userCode,String postCodes);
    
    /**
     * 过滤获得有权限的组合
     * 
     * @param menuId 功能代码
     * @return List
     */
    public <K extends BasePojo> List<K> getFilterPortDataForOperRight(String menuId)throws YssException;
    
    /**
	 * 根据用户代码、岗位代码和功能代码查询下面存在子组合的组合信息
	 * By Jinghehe 2014-7-28 根据用户和用户岗位来加载数据
	 * 
	 * @param userCode 用户代码
	 * @param postCode 岗位代码
	 * @param menuId 功能代码
	 * @return QueryRes
	 * @throws ServiceException
	 */
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
	 * 
  	 * @param assCodes 资产代码列表
  	 * @return List<Port> 组合信息列表
  	 */
	public List<Port> getPortByAssCodes(List<String> assCodes);
		
	/**
	 * 查询数据权限
	 * 
	 * @param trCode 数据维度代码
	 * @param portCodes 数据集合
	 * @return List<DataRight> 
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryDataRight", arguTypes = QueryDataRight1Vo.class)
	public List<DataRight> queryDataRight(@LinkControllerMethodArgu("trCode")String trCode,@LinkControllerMethodArgu("portCodes")String[] portCodes);
	
	/**
	 * 查询数据权限
	 * 
	 * @param trCode 数据维度代码
	 * @param portCodes 数据集合
	 * @return List<DataRight> 
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryDataRight", arguTypes = QueryDataRight2Vo.class)
	public List<DataRight> queryDataRight(@LinkControllerMethodArgu("trCode")String trCode,@LinkControllerMethodArgu("portCodes")String[] portCodes,@LinkControllerMethodArgu("customParam")HashMap<String,String> customParam);
	
	/**
	 * 根据组合代码和状态查询组合信息
	 * 
	 * @Title queryPort 
	 * @Description 根据组合代码和状态查询组合信息
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes 组合代码
	 * @param productState 状态
	 * @return List<Port>
	 */
	@LinkControllerMethod(value="queryPort",arguTypes = QueryPortVo.class)
	public List<Port> queryPort(@LinkControllerMethodArgu("portCodes")List<String> portCodes,@LinkControllerMethodArgu("productState")List<String> productState);
	
	/**
	 * 根据组合代码和状态查询组合信息
	 * 
	 * @Title queryPortMap 
	 * @Description 根据组合代码和状态查询组合信息
	 * @author gongjinzhi@ysstech.com
	 * @date 2017年2月20日下午6:50:24
	 * @param portCodes 组合代码
	 * @param productState 状态
	 * @return HashMap<String, String>
	 */
	@LinkControllerMethod(value="queryPortMap",arguTypes = QueryPortMapVo.class)
	public HashMap<String, String> queryPortMap(@LinkControllerMethodArgu("portCodes")List<String> portCodes,@LinkControllerMethodArgu("productState")List<String> productState);

	/**
	 * 根据属性标识 获取数据对象列表中所有该属性的值
	 * <产品信息组件拆分>
	 * 
	 * @Title getDataPropertiesListByCode 
	 * @Description 根据属性标识 获取数据对象列表中所有该属性的值
	 * @author HeLiang@ysstech.com
	 * @date 2017年6月12日下午3:47:24
	 * @param propertyCode 属性代码（字段列名）
	 * @return List
	 */
	public <K extends Object> List<K> getDataPropertiesListByCode(String propertyCode)
			throws ServiceException;

    /**
	 * STORY #45114 估值系统，根据组合树类型获取组合树
	 * 根据用户、岗位列表、组合树类型，查询组合树列表
	 * @param userCode
	 * @param postCode
	 * @param portTreeType
	 * @return
	 */
	@LinkControllerMethod(value = "getPortTreeList", arguTypes = {String.class,String.class,String.class})
	public List<BasePojo> getPortTreeList(String userCode,String postCode, String portTreeType) ;
	
	/**
	 * STORY #45114 估值系统，根据组合树类型获取组合树
	 * 根据组合树类型，查询组合树列表
	 * @param portTreeType
	 * @return
	 */
	@LinkControllerMethod(value = "getPortTreeList1", arguTypes = {String.class})
	public List<BasePojo> getPortTreeList(String portTreeType) ;
	
	/**
	 * STORY #45114 估值系统，获取组合树的类型
	 * @return
	 * @throws ServiceException
	 */
	public ArrayList<String> getAssetType() throws ServiceException;
	
	/**
	 * STORY #45114 估值系统，获取公用固定的组合树的类型
	 * @return
	 * @throws ServiceException
	 */
	public ArrayList<String> getCommonAssetType() throws ServiceException;
	
	

	 /*需要用反射调用，之前在接口不存在声明的类方法*/
	 public <K extends BasePojo> List<K> getTreeViewList();
	 
	 public QueryRes getTreeViewListRes() throws ServiceException;
	 
	 
	 
	 /*从框架移过来的方法*/
	 public List<Port> doPortFilterPort() throws ServiceException;
	 
	 @LinkControllerMethod(value = "getPortTreeList2")
	 public List<BasePojo> getPortTreeList() throws ServiceException;
	 
	 @LinkControllerMethod(value = "getStockTypeList1")
	 public List<BasePojo> getStockTypeList() throws ServiceException;
	 
	 /*获取群组下的所有（已审核但无权限控制）组合*/
	public FastPortData getAllPortByGroups(List<String> groupCodes) throws Exception;
	
	 /**
	     * 获取资产类别下的所有（已审核但无权限控制）组合
	     *STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
	     * neil
	     * 2019-07-26
	     */
		public Map<String,List<Port>> getAllAssPortByGroups(List<String> groupCodes) throws Exception;
	public <K extends BasePojo> List<K> getDataListByCodes(String codes);
	
	/**
	 * 查询子组合
	 * @param protPCode
	 * @return
	 * @throws ServiceException
	 */
	 public List<BasePojo> getUnitLayerPort(String[] protPCode) throws ServiceException;

	 /**
	  * 获取当前已关联流程的组合
	  * src：STORY #62048 新增加的组合自动关联自动化估值方案
	  * author：shijian@ysstech.com
	  * date：2018年10月22日
	  */
	<K extends BasePojo> List<K> getRelevancePort();
	
	/**
	 * 根据前端的权限过滤组合
	 * @param isDataRight 是否根据权限过滤数据
	 * @param datClass
	 * @param dvPortCode
	 * @param trcode
	 * @param dataRights 有权限的组合（前端传过来,避免重复查询）
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="doPortFilterRes4",arguTypes = DoPortFilterResVo.class)
	public QueryRes doPortFilterRes(@LinkControllerMethodArgu("isDataRight")String isDataRight, @LinkControllerMethodArgu("datClass")String datClass,
			@LinkControllerMethodArgu("dvPortCode")String dvPortCode, @LinkControllerMethodArgu("trcode")String trcode, @LinkControllerMethodArgu("dataRights")List<String> dataRights) throws ServiceException;
	
	/**
	 * 根据资产树节点获取组合树MAP（没有数据权限过滤）
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public PortCacheData getPortDataMapByTrCode(String trCode) throws ServiceException;
	
	 /**
	  * @Desc  获取所有组合数据（默认资产树结构）
	  * @author houjiaqi
	  * @date 2019年3月12日 下午4:49:39
	  * @param @return
	  * @param @throws Exception
	  */
	 List<Map<String, String>> getAllPortData() throws Exception;
	 
	 /**
	  * 查组合PojoBy组合Code
	  * 去除权限过滤
	  * @param conds
	  * @return
	  * @throws Exception
	  */
	List<Port> queryPortByPortCode(String[] keys) throws ServiceException;
	/**
	 * STORY #69369 易方达：【自动化】关于流程管理组合维护的需求  baoqiaolin 20190910
	 * @param isDataRight
	 * @param datClass
	 * @param dvPortCode
	 * @param trCode
	 * @return
	 * @throws Exception
	 */
	public List<Port> PortFilter_ByTrCode_YFP(boolean isDataRight,String datClass, String dvPortCode, String trCode) throws Exception;
	

	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
     * 查询带子结构的树形结构
     * @return
     */
	public List<AssetsTree_A> queryAssetTreeWithLeafNode() throws Exception;
	
	/**
	 * STORY #90556 【中信】树形结构支持显示下级节点包括自动化流程执行弹出框 (#2 #1 )
	 * 查询树形结构节点下的组合
	 * @param isDataRight
	 * @param trCode
	 * @param trCodeR
	 * @return
	 * @throws Exception
	 */
	public List<Port> getTreePortByCode(boolean isDataRight, String trCode, String trCodeR) throws Exception;
	
	/**
	 * STORY #105671 工银瑞信—流程实例新增按资产类型等筛选
     * 根据类型条件 查询 组合数据
     * @param type C_DAT_CODE 资产类型  C_DAT_CLS 资产类别 GROUP 群组 
     * 过滤掉未审核的 群组 和 组合， 组合信息需要产品状态，组合层级等字段 等信息
     * @param codes
     * @param isDataRight 是否过滤权限
     * @return
     */
    public List<Port> queryPortByTypeCondition(String type, List<String> codes, boolean isDataRight) throws Exception;
}
