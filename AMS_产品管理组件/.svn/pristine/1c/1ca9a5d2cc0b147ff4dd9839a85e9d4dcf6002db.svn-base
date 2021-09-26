package com.yss.ams.product.information.support.modules.ab.portrela.service;

import java.util.HashMap;

import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccPageVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

@RestfulSupported
public interface IPortRelaCashAccService extends IServiceBus {
	
	/**
	 * 现金账户信息
	 * 
	 * @author add by guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryPortRelaCashAccPage",arguTypes = QueryPortRelaCashAccPageVo.class)
	public QueryRes queryPortRelaCashAccPage(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page) throws Exception;
	
	/**
	 * 组合关联现金账户信息
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryPortRelaCashAcc(HashMap<String, Object> paraMap)
			throws Exception;
}
