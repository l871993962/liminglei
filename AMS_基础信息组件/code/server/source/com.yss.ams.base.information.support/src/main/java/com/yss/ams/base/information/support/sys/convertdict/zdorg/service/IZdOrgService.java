package com.yss.ams.base.information.support.sys.convertdict.zdorg.service;

import java.util.HashMap;

import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 * 机构转换字典普通服务接口，主要进行增删改查操作
 *
 */
@RestfulSupported
public interface IZdOrgService extends IServiceBus{

	/**
	 * 查询A区数据方法定义
	 * 
	 * @param paraMap
	 * @return
	 */
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap);
}
