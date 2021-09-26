package com.yss.ams.base.information.modules.sys.automaticSet.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.automaticSet.controller.IAutomaticSetPathController;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPathPojo;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetPathService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.uco.dataintegration.support.dataservice.pojo.ImpCfgGroup;

/**
* STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
* @author zhuziqing
* @date 2021-06-01
*/
public class AutomaticSetPathControllerImpl extends AbstractBaseServiceBusController<AutomaticSetPathPojo, IAutomaticSetPathService> implements IAutomaticSetPathController{

	@Override
	public List<Vocabulary> getAllProductType() {
	return getService().getAllProductType();
	}

	@Override
	public List<AutomaticSetPathPojo> getDataList() {
		return getService().getDataList();
	}

	@Override
	public QueryRes queryDataList(HashMap<String, Object> paraMap) {
       return getService().queryDataList(paraMap);
	}

	@Override
	public List<Vocabulary> getInterfaceClass() {
		return  getService().getInterfaceClass();
	}

	@Override
	public boolean updateDataList(AutomaticSetPojoVo vo) {
		return  getService().updateDataList(vo.getDataList());
	}

	@Override
	public List<AutomaticSetPathPojo> getProductType() {
		return getService().getProductType();
	}

	@Override
	public List<ImpCfgGroup> getInterfaceData(List<String> productName) {
		return getService().getInterfaceData(productName);
	}

	@Override
	public boolean saveDataList(AutomaticSetPojoVo vo) {	
		return getService().saveDataList(vo.getPortCodes(),vo.getDataList());
	}

	@Override
	public boolean copy(AutomaticSetPojoVo pojo) {
		return getService().copy(pojo.getParaMap());
	}

	@Override
	public List<AutomaticSetPathPojo> getAllIndex() {
		return getService().getAllIndex();
	}

	@Override
	public boolean saveList(AutomaticSetPojoVo vo) {
		return getService().saveList(vo.getDataList());
	}

	@Override
	public List<AutomaticSetPathPojo> getRePortCodeList() {
		return getService().getRePortCodeList();
	}

	@Override
	public List<BasePojo> queryByCodeAndName(String portCode, List<String> productName) {
		return getService().queryByCodeAndName(portCode,productName);
	}
}
