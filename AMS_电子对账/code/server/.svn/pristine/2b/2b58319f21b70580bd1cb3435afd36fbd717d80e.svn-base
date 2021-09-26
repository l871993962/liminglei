package com.yss.uco.elecreco.er.erdztype.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.uco.elecreco.support.bean.ErDzType;
import com.yss.uco.elecreco.support.controller.IErDzTypeDataServiceController;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;
import com.yss.uco.elecreco.support.service.IErDzTypeDataService;

/**
 * 
 * @author tongdengke
 * @date 2020-09-19 11:12:16
 */
public class ErDzTypeDataServiceControllerImpl extends
		AbstractBaseController<IErDzTypeDataService> implements
		IErDzTypeDataServiceController {

	@Override
	public List<ElecGroupRela> getGeneDzType() {
		return castToListT(getService().getGeneDzType(), ElecGroupRela.class);
	}

	@Override
	public List<ElecGroupRela> getAllDzTypeMap() {
		return castToListT(getService().getAllDzTypeMap(), ElecGroupRela.class);
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() {
		return getService().getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) {
		return getService().getKeyConvertMap(listKey);
	}

	@Override
	public List<ErDzType> getDataList() {
		return getService().getDataList();
	}

	@Override
	public RestfulQueryResult<ErDzType> getDataListRes() {
		return queryResToT(getService().getDataListRes(),ErDzType.class);
	}

	@Override
	public ErDzType getDataByCode(String dataCode) {
		return getService().getDataByCode(dataCode);
	}

	@Override
	public ErDzType getPojoByCode(String pojoCode) {
		return getService().getPojoByCode(pojoCode);
	}

	@Override
	public List<ErDzType> getDataListByTypes(String[] types) {
		return getService().getDataListByTypes(types);
	}

	@Override
	public RestfulQueryResult<ErDzType> getQueryResByTypes(String[] types) {
		return queryResToT(getService().getQueryResByTypes(types),ErDzType.class);
	}

	@Override
	public List<ErDzType> getDataListByKeys(String[] keys) {
		return getService().getDataListByKeys(keys);
	}

	@Override
	public RestfulQueryResult<ErDzType> getQueryResByKeys(String[] keys) {
		return  queryResToT(getService().getQueryResByKeys(keys),ErDzType.class);
	}

}