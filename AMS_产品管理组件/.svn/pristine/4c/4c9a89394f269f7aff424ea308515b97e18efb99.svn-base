package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaInvestMgrPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaInvestMgrService extends IServiceBus {
	/**
	 * 组合关联投资经理
	 * 
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaInvestMgrPage",arguTypes = QueryPortRelaInvestMgrPageVo.class)
	public QueryRes queryPortRelaInvestMgrPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page) throws Exception;

	/**
	 * 组合关联投资经理
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaInvestMgr(HashMap<String, Object> paraMap)
			throws Exception;
}
