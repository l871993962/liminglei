package com.yss.ams.product.information.modules.pg.portgroup.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupDataProviderController;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataProvider;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestFulDataRightCodesParamVo;
import com.yss.framework.api.restful.base.vo.RestFulDataRightDimensionVo;
import com.yss.framework.api.restful.base.vo.RestFulDataRightTypeCodesParamVo;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupDataProviderControllerImpl extends AbstractBaseServiceBusController<DataRight,IPortGroupDataProvider> implements IPortGroupDataProviderController {

	@Override
	public List<DataRight> query() {
		return getService().query();
	}

	@Override
	public List<DataRight> queryByDimension(RestFulDataRightDimensionVo vo) {
		return getService().queryByDimension(vo.getDimensionType(),vo.getCustomParam());
	}

	@Override
	public List<DataDimension> queryDataDimensions() {
		return getService().queryDataDimensions();
	}

	@Override
	public List<DataRight> query(RestFulDataRightCodesParamVo vo) {
		return getService().query(vo.getCodes().toArray(new String[vo.getCodes().size()]),vo.getCustomParam());
	}

	@Override
	public List<DataRight> queryByDimension(RestFulDataRightTypeCodesParamVo vo) {
		return getService().queryByDimension(vo.getDimensionType(), vo.getCodes().toArray(new String[vo.getCodes().size()]), vo.getCustomParam());
	}

	@Override
	public HashMap<String, String> queryConditions() {
		return getService().queryConditions();
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		return getService().query(customParam);
	}

	
}