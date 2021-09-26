package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeOrgPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaTradeOrgService extends IServiceBus {
	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaTradeOrgPage",arguTypes = QueryPortRelaTradeOrgPageVo.class)
	public QueryRes queryPortRelaTradeOrgPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page) throws Exception;

	/**
	 * 组合关联期货公司
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaTradeOrg(HashMap<String, Object> paraMap)
			throws Exception;
	
	/**
	 * 组合关联期货公司SET
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaTradeOrgSet(HashMap<String, Object> paraMap)
			throws Exception;
}
