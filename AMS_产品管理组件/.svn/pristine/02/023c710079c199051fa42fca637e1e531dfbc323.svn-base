package com.yss.ams.product.information.modules.aa.portcls.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.controller.IClsPortCacheDataController;
import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.service.IClsPortCacheDataService;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetCashListByPortWDQVo;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class ClsPortCacheDataControllerImpl extends AbstractBaseController<IClsPortCacheDataService> implements IClsPortCacheDataController{



	@Override
	public String getKey(String cPortCode, String cPortCls) {
		return getService().getKey(cPortCode,cPortCls);
	}

	@Override
	public PortCls getCacheByKey(String key) {
		return getService().getCacheByKey(key);
	}

	@Override
	public PortCls getCacheByPortAndPortCls(String portCode, String portClsCode) {
		return getService().getCacheByPortAndPortCls(portCode,portClsCode);
	}

	@Override
	public List<PortCls> getCashListByPort(String portCode) {
		return getService().getCashListByPort(portCode);
	}

	@Override
	public List<PortCls> getCashListByPortWDQ(GetCashListByPortWDQVo vo) {
		return getService().getCashListByPortWDQ(vo.getPortCode(),vo.getAccDate());
	}

	@Override
	public List<PortCls> getCacheList() {
		return getService().getCacheList();
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
	public void reloadData() {
		getService().reloadData();
	}

}