package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaCashAccountService extends IServiceBus{
	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaCashAccountPage",arguTypes = QueryPortRelaCashAccountPageVo.class)
	public QueryRes queryPortRelaCashAccountPage(
			@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap, @LinkControllerMethodArgu("page")PageInation page) throws Exception;

	/**
	 * 组合关联股东账户
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaCashAccount(HashMap<String, Object> paraMap)
			throws Exception;
	
	

}
