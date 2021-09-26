package com.yss.ams.product.information.support.modules.dataCopy.service;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.dataCopy.vo.ExeVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyCheckDataVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyDataVo;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * STORY #36524 中信证券-通过http交互方式读入建账信息及产品成立业务
 * 从platform移动到support项目
 * @date 2017-3-9
 * @author huangkaixun
 */
@RestfulSupported
public interface IDataCopyService extends IServiceBus {
	@LinkControllerMethod(value = "exe", arguTypes = ExeVo.class)
	String exe(@LinkControllerMethodArgu("map")HashMap<String,Object> map, @LinkControllerMethodArgu("list")List<BasePojo> list);
	
	@LinkControllerMethod(value = "exe1", arguTypes = {HashMap.class})
	String exe(HashMap<String,Object> map);
	List<String> verify(HashMap<String,Object> map);
	List<String> queryCustom();
	//edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	//参照组合改变时需要传参数
	QueryRes queryCreateCopy(String portCode);
	QueryRes queryPortClsCreateCopy();
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * @param paraMap
	 * @param page
	 * @return
	 */
	@LinkControllerMethod(value="queryCopyCheckData",arguTypes = QueryCopyCheckDataVo.class)
	QueryRes queryCopyCheckData(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,
			@LinkControllerMethodArgu("page")PageInation page);
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * @param paraMap
	 * @param page
	 * @return
	 */
	String getCopyCheckDataTotal(HashMap<String, Object> paraMap);
	
	/**
	 * STORY #36524 中信证券-通过http交互方式读入建账信息及产品成立业务
	 * 后台调用doBusOper时拼接的&与url一样，在解析是会出错
	 * 功能：权限复制
	 * @param map
	 * @return String
	 * @throws YssException
	 * @date 2017-3-9
	 * @author huangkaixun
	 */
	String doBusOperEx(HashMap<String,Object> map) throws Exception;
	
	/**
	 * @Desc  STORY #71081 分布式版本FAST同估值sql表关联解耦 
	 * @author houjiaqi
	 * @date 2019年3月20日 下午4:19:33
	 * @param @return
	 * @param @throws Exception
	 */
	String getService() throws Exception;
	
	/**
	 * @Desc  STORY #71081 分布式版本FAST同估值sql表关联解耦 
	 * @author houjiaqi
	 * @date 2019年3月20日 下午5:01:03
	 * @param @param paraMap
	 * @param @param clazz
	 * @param @return
	 * @param @throws Exception
	 */
	@LinkControllerMethod(value="queryCopyData",arguTypes = QueryCopyDataVo.class)
	List<BasePojo> queryCopyData(@LinkControllerMethodArgu("paraMap")HashMap<String, Object> paraMap,@LinkControllerMethodArgu("clazz")Class<?> clazz) throws Exception;
}
