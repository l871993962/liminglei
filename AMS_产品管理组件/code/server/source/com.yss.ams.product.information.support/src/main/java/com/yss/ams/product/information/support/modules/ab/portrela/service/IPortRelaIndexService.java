package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaIndexPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaIndexService extends IServiceBus {

	/**
	 * 分页查询方法(组合关联指数信息)
	 * 
	 * @param paraMap
	 *            参数集合
	 * @param page
	 *            分页参数
	 * @return 查询结果集
	 */
	@LinkControllerMethod(value="queryPortRelaIndexPage",arguTypes = QueryPortRelaIndexPageVo.class)
	QueryRes queryPortRelaIndexPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);

	/**
	 * 查询列表方法(组合关联指数信息)
	 * 
	 * @param paraMap
	 *            参数集合
	 * @return 查询结果集
	 */
	QueryRes queryPortRelaIndex(HashMap<String, Object> paraMap);
}
