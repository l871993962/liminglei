package com.yss.ams.base.information.support.sys.automaticSet.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPathPojo;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.fast.task.support.automatic.service.IAutomaticInterfaceExtChannelService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.uco.dataintegration.support.dataservice.pojo.ImpCfgGroup;

/** 
 * 自动化业务外部渠道组合路径设置接口
 * @ClassName: IAutomaticSetPathService
 * @date 2021年05月29日
 * @Stroy105821
 * @author zhuziqing
 */
@RestfulSupported
public interface IAutomaticSetPathService extends IServiceBus, IKeyConvertDataService,IAutomaticInterfaceExtChannelService{
	
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 获取产品业务分类数据
	 * @return  
	 * @throws ServiceException
	 */
	public List<Vocabulary> getAllProductType() throws ServiceException;

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 条件查询
	 * @return
	 * @throws ServiceException
	 */
	public QueryRes queryDataList(HashMap<String, Object> paraMap) throws ServiceException;
	
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 获取产品业务分类、接口代码数据
	 * @return  
	 * @throws ServiceException
	 */
	public List<Vocabulary> getInterfaceClass() throws ServiceException;

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 保存产品业务分类、接口代码数据
	 * @return  
	 * @throws ServiceException
	 */
	public boolean updateDataList(List<HashMap<String, String>> paraMap);

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 产品业务分类加载接口名称
	 * @return
	 */
	public List<AutomaticSetPathPojo> getProductType();
	
	
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 通过产品业务分类加载对应的导入接口信息
	 * @return
	 */
	public List<ImpCfgGroup> getInterfaceData(List<String> productName);
	

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 新增数据
	 * @param proList
	 * @param dataList
	 * @return
	 */
    public boolean saveDataList(List<String> proList,List<HashMap<String, String>> dataList);
    
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 复制
	 * @param data
	 * @return
	 */
    public boolean copy(HashMap<String, String> data);
    
    /**
     * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
     * 获取所有的估值指标
     * @return
     */
    public List<AutomaticSetPathPojo> getAllIndex();
    
    /**
     * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
     * 新增
     * @param dataList
     * @return
     */
    public boolean saveList(List<HashMap<String, String>> dataList);
   
    /**
     * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 查询所有可参照的组合
     * @return
     */
    public List<AutomaticSetPathPojo> getRePortCodeList();
    
    /**
     * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 通过参照组合代码和产品业务分类查询对应存的接口信息
     * @param portCode
     * @param productName
     * @return
     */
    public List<BasePojo> queryByCodeAndName(String portCode, List<String> productName);
}
