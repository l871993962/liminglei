/**
 *
 * @Title: BusinessTypeService.java 
 * @Package com.yss.ams.base.information.support.sys.businesstype.service 
 * @date 2019年5月13日 下午3:34:26 
 * @version V1.0
 * @Stroy/Bug
 * @author xiadeqi   
 */
package com.yss.ams.base.information.support.sys.portbusinessrange.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.portbusinessrange.pojo.PortBusinessRangePojoVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/** 
 * 产品业务范围接口
 * @ClassName: BusinessTypeService 
 * @date 2019年5月13日 下午3:34:26
 * @Stroy72335/Bug
 * @author xiadeqi 
 */
@RestfulSupported
public interface IPortBusinessRangeService extends IServiceBus, IKeyConvertDataService {
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * 获取业务类型数据
	 * @param type
	 * @return
	 * @throws ServiceException
	 */
	public List<Vocabulary> getDataListByType(String type) throws ServiceException;
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * 更新业务类型数据
	 * @param type
	 * @param paraMap
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="updateDataList",arguTypes = PortBusinessRangePojoVo.class)
	public boolean updateDataList(@LinkControllerMethodArgu("type")String type, @LinkControllerMethodArgu("paraMap")HashMap<String, String> paraMap) throws ServiceException;
	
	/**
	 * STORY #86378 【华宝基金】二期自动化应用范围增加其他自动化组合
	 * 根据业务类型代码获取组合集合
	 * @param busiCode
	 * @return
	 * @throws ServiceException
	 */
	public List<String> getPortListByBusiCode(String busiCode) throws ServiceException;
	
	/**
	 * STORY #96878 【富国基金】自动化参数复制通过产品参数复制
	 * @param pojoList
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "insertPortBusinessRange", arguTypes = List.class)
	public void insertPortBusinessRange(List<BasePojo> pojoList) throws ServiceException;
}
