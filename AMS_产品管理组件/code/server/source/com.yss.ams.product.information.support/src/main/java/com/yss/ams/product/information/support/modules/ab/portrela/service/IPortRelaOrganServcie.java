package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaOrganPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.Port_A;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaOrganServcie extends IServiceBus {
	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaOrgan(HashMap<String, Object> paraMap)
			throws Exception;

	/**
	 * 组合关联机构
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaOrganPage",arguTypes = QueryPortRelaOrganPageVo.class)
	public QueryRes queryPortRelaOrganPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page) throws Exception;
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-09-21
	 * Status : Add
	 * Comment: 检测组合是否已经关联委托人机构了
	 * @param map
	 * @return
	 */
	public String checkORGConsignerForPort(HashMap<String,String> map);
	
	/**
	 * Author : ChenLong
	 * Date   : 2016-11-03
	 * Status : Add
	 * Comment: 查询计税委托人下的组合
	 * @param taxConsigner
	 * @return
	 */
	public List<Port_A> getPortInfoByConsigner(String taxConsigner);
	
	/**
	 * 根据组合和机构资质获取组合关联的架构
	 * @param portCodes	组合代码
	 * @param dvType 机构资质
	 * @return
	 */
	public HashMap<String, List<String>> getPortRelaOrgByPortAndDvType(String portCodes, String dvType);
}
