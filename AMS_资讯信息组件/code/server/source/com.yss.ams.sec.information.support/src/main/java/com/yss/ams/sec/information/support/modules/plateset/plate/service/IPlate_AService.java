package com.yss.ams.sec.information.support.modules.plateset.plate.service;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;

/**
 *板块信息左侧树栏服务接口 
 * @author zhao
 *
 */
@RestfulSupported
public interface IPlate_AService extends IServiceBus {
	public QueryRes getTreeViewData(HashMap<String, Object> paraMap);
	public String unAuditById(List<BasePojo> pojoList);
	public String getSUBData(String data) throws ServiceException;
}
