package com.yss.ams.base.information.support.sys.automaticSet.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 自动化业务设置接口
 * @ClassName: IAutomaticSetService
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
@RestfulSupported
public interface IAutomaticSetService extends IServiceBus, IKeyConvertDataService {
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 获取明细业务类型数据
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<Vocabulary> getDataListByType(String type) throws ServiceException;
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 更新业务类型数据
	 * @param type
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="updateDataList", arguTypes = AutomaticSetPojoVo.class)
	public boolean updateDataList(@LinkControllerMethodArgu("type")String type, @LinkControllerMethodArgu("paraMap")HashMap<String, String> paraMap) throws ServiceException;
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据业务类型代码获取组合集合
	 * @param type
	 * @param busiCode
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="getPortListByBusiCode", arguTypes = AutomaticSetPojoVo.class)
	public List<String> getPortListByBusiCode(@LinkControllerMethodArgu("type")String type, @LinkControllerMethodArgu("busiCode")String busiCode) throws ServiceException;
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据业务类型和组合代码获取明细业务类型集合
	 * @param type
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="getBusiInfoByCode", arguTypes = AutomaticSetPojoVo.class)
	public List<Map<String, String>> getBusiInfoByCode(@LinkControllerMethodArgu("type")String type, @LinkControllerMethodArgu("portCode")String portCode) throws ServiceException;
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 获取所有业务类型
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getAllBusiType() throws ServiceException;
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * 根据组合获取业务类型
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getBusiTypeByCode(String portCode) throws ServiceException;
	
	/**
	 * STORY #100516 【华夏基金】系统支持设置组合的责任会计信息（STORY #100065拆出给估值）
	 * 根据组合查询业务类型和组合对应关系
	 * @param type
	 * @param portCodes
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="getBusiTypeByCodes", arguTypes = AutomaticSetPojoVo.class)
	public List<String> getBusiTypeByCodes(@LinkControllerMethodArgu("type")String type, @LinkControllerMethodArgu("portCodes")List<String> portCodes) throws ServiceException;
}
