package com.yss.ams.product.information.modules.ab.port.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.controller.IPortDocDynamicCheckDefaultController;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDocDynamicCheckDefaultService;
import com.yss.ams.product.information.support.modules.ab.port.vo.DynamicCheckVo;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.resource.mgr.pojo.BusinessData;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortDocDynamicCheckDefaultControllerImpl extends AbstractBaseController<IPortDocDynamicCheckDefaultService> implements IPortDocDynamicCheckDefaultController {

	@Override
	public String dynamicCheck(DynamicCheckVo vo) {
		return getService().dynamicCheck(vo.getDocTypeName(),vo.getMgrList());
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataTime(String funCode,
			String businessBeginDate, String businessEndDate) {
		return getService().getAllDataByFunCodeAndDataTime(funCode,businessBeginDate,businessEndDate);
	}

	@Override
	public List<BusinessData> getAllDataByFunCodeAndDataIds(String funCode,
			String dataIds) {
		return getService().getAllDataByFunCodeAndDataIds(funCode,dataIds);
	}

}