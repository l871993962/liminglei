package com.yss.ams.product.information.support.modules.ab.port.service;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.support.modules.ab.port.vo.ConnDeleteVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnInsertVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.ConnUpdateVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.OperDQQRVo;
import com.yss.ams.product.information.support.modules.ab.port.vo.UpdateDataByPortCodeVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.resource.mgr.service.IDocumentDynamicCheckBaseService;
import com.yss.right.service.IDataRightProvider;

/**
 * <产品基本信息>普通服务接口，主要进行增删改查操作
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
@RestfulSupported
@GenericPojo(pojo = Port.class)
public interface IPortService extends IServiceBus, IDataRightProvider, IKeyConvertDataService, IControlDataService, IDocumentDynamicCheckBaseService {

	/**
	 * 根据查询条件获取组合关联方案新增组合
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	QueryRes getPlanRelaPortAdd(HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取组合关联方案浏览组合
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	QueryRes getPlanRelaPortBrow(HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取参数设置中的组合数据
	 * 
	 * @param paraMap 查询条件参数集合
	 * @return List<Port> 组合列表
	 */
	public List<Port> getParamSetPortList(HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取 参数 投资组合
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes
	 */
	public QueryRes getDspPort(HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取权限管理列表
	 * 
	 * @param paraMap 查询条件
	 * @return List<Port> 组合列表
	 */
	public List<Port> getRightManagePortListExpertAdd(
			HashMap<String, Object> paraMap) throws Exception;

	/**
	 * 根据查询条件查询单条记录（清算综合参数SET界面浏览组合时）
	 * added by ll 20130109
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 结果集
	 */
	public QueryRes queryDataByBrow(HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取投资组合数据（树形结构）
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap);
	
	/**
	 * BUG #378219 【深国投信托】【0611.0729】部分界面权限优化
	 * 根据查询条件获取投资组合数据（树形结构）
	 * @param isDataRight
	 * @param paraMap
	 * @return
	 */
	public QueryRes getTreeViewDataRight(String isDataRight, HashMap<String, Object> paraMap);
	
	/**
	 * BUG #378219 【深国投信托】【0611.0729】部分界面权限优化
	 * 获取当前登录用户有权限的组合代码
	 * @return
	 */
	public List<String> getDataRightListForReportCenter();

	/**
	 * 根据查询条件获取单元层投资组合数据（列表结构）
	 * 
	 * @author tangshifeng
	 * @since 2013-04-17
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	public QueryRes getUnitPortData(HashMap<String, Object> paraMap);
	
	/**
	 * BUG #377903 【深国投信托】【0611.0729】汇集调尾处理问题
	 * 根据查询条件获取单元层投资组合数据（列表结构）
	 * @param isDataRight
	 * @param paraMap
	 * @return
	 */
	public QueryRes getUnitPortDataRight(String isDataRight, HashMap<String, Object> paraMap);

	/**
	 * 根据查询条件获取显示用的资产树形结构数据
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	public QueryRes getAssetTreeView(HashMap<String, Object> paraMap);

	/* START 资产数列表详细信息界面获取数据列表 */
	
	/**
	 * 根据查询条件获取组合资产树列表（资产树列表详细信息界面获取数据列表）
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	public QueryRes getPortAssTreeList(HashMap<String, Object> paraMap)
			throws ServiceException;

	/**
	 * 根据查询条件获取组合资产树列表（资产树列表详细信息界面 新增 时获取数据列表）
	 * 
	 * @param paraMap 查询条件
	 * @return QueryRes 组合结果集
	 */
	public QueryRes getPortAssTreeListAddForm(HashMap<String, Object> paraMap)
			throws ServiceException;

	/* END 资产数列表详细信息界面获取数据列表 */
	
	/**
	 *  到期确认
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operDQQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  到期确认
	 *  edit by yuanayfeng 20180522 STORY #54942 【鹏华基金】产品基本信息维护了到期及清算处理连带处理
     *  组合到期确认是否更新
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    @LinkControllerMethod(value="SendMail",arguTypes = OperDQQRVo.class)
    String operDQQR(@LinkControllerMethodArgu("lstPort")List<Port> lstPort, @LinkControllerMethodArgu("date")String date, @LinkControllerMethodArgu("isUpdate")String isUpdate) throws ServiceException;
    
    /**
	 *  到期取消
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
	@LinkControllerMethod(value = "operDQQR", arguTypes = {List.class})
    String operDQQX(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算确认
	 *  
	 *  @param lstPort 组合列表
	 *  @return 执行状态
	 */
    String operQSQR(List<Port> lstPort) throws ServiceException;

    /**
	 *  清算取消
	 *  
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
     * 
     * @param portCode 组合代码
     * @param assCode 资产代码
     * @return List<Port> 组合列表
     * @author liuxiang 2015-7-16 BUG #115824 [紧急][招商证券]资产代码修改的问题
     */
    List<Port> getTheSameAssCodeList(String  portCode, String assCode) throws ServiceException;
    
    /**
     * 根据组合代码获取产品的资产类别关系
     * 
     * Author : ChenLong
     * Date   : 2016-07-25
     * Status : Add     * 
     * @param portCodes 组合代码
     * @return Map<String,String>
     */
    public Map<String,String> getPortDatClsMap(String[] portCodes);
    
    /**
	 * 产品生命周期 产品管理功能  提供控制事务的插入方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
    @LinkControllerMethod(value="connInsert",arguTypes = ConnInsertVo.class)
	public String connInsert(@LinkControllerMethodArgu("pojo")BasePojo pojo,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;
	
	/**
	 * 产品生命周期 产品管理功能   提供控制事务的删除方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
    @LinkControllerMethod(value="connDelete",arguTypes = ConnDeleteVo.class)
	public void connDelete(@LinkControllerMethodArgu("list")List<BasePojo> list,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;
	
	/**
     * BUG #217658 权限浏览和权限设置列表界面组合分页不显示非组合节点数据
	 * 获取组合类型
	 * @return
	 */
	public List<Port> getPortType();
	
	/**
	 * 产品生命周期 产品管理功能  提供控制事务的修改方法
	 * @author jiangzhichao
	 * @param pojo
	 * @param conn
	 * @return
	 */
	@LinkControllerMethod(value="connUpdate",arguTypes = ConnUpdateVo.class)
	public void connUpdate(@LinkControllerMethodArgu("pojo")BasePojo pojo,@LinkControllerMethodArgu("conn")Connection conn) throws ServiceException;


	public List<Port> getRightManagePortList(HashMap<String, Object> paraMap) throws Exception;
	
	/**<summary>
     * 通过参数代码查询综合参数值
     * </summary>
     * <param name="paraCode">paraCode</param>
     * <returns>paramValues</returns>
     */
	 public String queryByParaCode(String paramCode); 
	 
	 /**
	  * 清算确认
	  * 组件拆分，将估值与产品组件解耦，此处由产品组件提供服务，给估值调用
	  * @param lstPort
	  * @return
	  */
	 public String operQSQRForGz(List<Port> lstPort)  throws ServiceException;
	 
	 /**
	  * 清算撤销
	  * 组件拆分，将估值与产品组件解耦，此处由产品组件提供服务，给估值调用
	  * @param lstPort
	  * @return
	  */
	 public String operQSQXForGz(List<Port> lstPort)  throws ServiceException;
	 
	/**
	 * 
	 * 根据业务类型获取没有关联此业务的组合
	 * @Title: getBusinessRangePortAdd 
	 * @param @param paraMap
	 * @param @return
	 * @return QueryRes    
	 * @throws 
	 * @Stroy72335/Bug
	 * @author xiadeqi
	 */
	 public QueryRes getBusinessRangePortAdd(HashMap<String, Object> paraMap);

	/**
	 * 
	 * 根据业务类型、组合代码获取组合
	 * @Title: getBusinessRangePortBrow
	 * @param @param paraMap
	 * @param @return
	 * @return QueryRes
	 * @throws
	 * @Stroy72335/Bug
	 * @author xiadeqi
	 */
	 public QueryRes getBusinessRangePortBrow(HashMap<String, Object> paraMap);
	 
	 /**
	  * 
	  * @Title updateDataByPortCode 
	  * @Description 根据产品代码更新产品基本信息
	  * @author yinyuyi
	  * @date 2019年6月13日下午5:13:07
	  * @param updatePortData 待更新的产品信息
	  * @param portCode 产品代码
	  * @param conn 数据库连接
	  * @return
	  */
	 @LinkControllerMethod(value="updateDataByPortCode",arguTypes = UpdateDataByPortCodeVo.class)
	 public void updateDataByPortCode(@LinkControllerMethodArgu("updatePortData")Port updatePortData,@LinkControllerMethodArgu("portCode")String portCode,@LinkControllerMethodArgu("conn")Connection conn);
	 
	 /**
	  * 根据组合代码获取组合列表
	  * @param portCodes
	  * @return
	  */
     public List<Port> getPortListByPortCode(String portCodes);
	 
	 /**
	 * Author : licao
	 * Date   : 2020-03-06
	 * Status : Add
	 * Comment: STORY #83454 【招商基金】产品信息建好后又删除重新创建，但系统删除动作时没有将其对应关联表相关数据删除 
	 * 删除 T_P_AB_PORT_RELA 对应数据
	 * @param portCodes 
	 * @return
	 */
	public void delPortRela(String[] portCodes);
	
	/**
	 * 根据总账组合 查找分账组合
	 * STORY #87435 华夏基金-MOM产品需求-个性化部分
	 * add by sunyanlin 20200525
	 * 
	 */
	public QueryRes getMomPortSub(HashMap<String, Object> paraMap);
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据业务类型获取没有关联此业务的组合
	 * @param paraMap
	 * @return
	 */
	public QueryRes getAutomaticSetPortAdd(HashMap<String, Object> paraMap);
	
	/**
	 * STORY #75531 【广发基金】支持美元本位币记账组合的人民币转换和核对的需求 (#2 #1 ) 
	 * 查询并行组合
	 * @author zengguowei
	 * @since 2019-07-18
	 * @param paraMap
	 * @return
	 */
	public QueryRes getSourcesBxzhViewData(HashMap<String, Object> paraMap);
	
	/**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * @return
	 */
	public String getCacodeByAccountType(String portCode,String dcCode);
	
	
	/**
	 * STORY #102352 账户信息设置中现金账户“默认值”
	 * @return
	 */
	public String getCacodeByAccountType1(String openName,String openNo,String openAddr,String dcCode);
	
} 