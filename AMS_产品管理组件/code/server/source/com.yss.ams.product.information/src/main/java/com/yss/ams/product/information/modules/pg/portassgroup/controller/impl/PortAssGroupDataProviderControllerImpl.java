package com.yss.ams.product.information.modules.pg.portassgroup.controller.impl;

import java.util.HashMap;
import java.util.List;
import com.yss.ams.product.information.support.modules.pg.portassgroup.controller.IPortAssGroupDataProviderController;
import com.yss.ams.product.information.support.modules.pg.portassgroup.service.IPortAssGroupDataProvider;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestFulDataRightCodesParamVo;
import com.yss.framework.api.restful.base.vo.RestFulDataRightDimensionVo;
import com.yss.framework.api.restful.base.vo.RestFulDataRightTypeCodesParamVo;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;

/**
 * STORY #66347 【招商基金】系统实现自动授权及权限优化需求 
 * 2019-07-25
 * @author neil
 *
 */
public class PortAssGroupDataProviderControllerImpl extends AbstractBaseServiceBusController<DataRight,IPortAssGroupDataProvider> implements IPortAssGroupDataProviderController{

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
