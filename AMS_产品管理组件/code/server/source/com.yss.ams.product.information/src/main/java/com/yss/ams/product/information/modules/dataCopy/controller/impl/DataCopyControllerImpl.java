package com.yss.ams.product.information.modules.dataCopy.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.dataCopy.controller.IDataCopyController;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyDataCheck;
import com.yss.ams.product.information.support.modules.dataCopy.service.IDataCopyService;
import com.yss.ams.product.information.support.modules.dataCopy.vo.ExeVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyCheckDataVo;
import com.yss.ams.product.information.support.modules.dataCopy.vo.QueryCopyDataVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class DataCopyControllerImpl extends AbstractBaseServiceBusController<CopyData,IDataCopyService> implements IDataCopyController {

	//HashMap<String,Object> map, List<BasePojo> list
    @Override
    public String exe(ExeVo vo){
        return getService().exe(vo.getMap(),castToBasePojoList(vo.getList()));
    }

    @Override
    public String exe(HashMap<String,Object> map){
        return getService().exe(map);
    }

    @Override
    public List<String> verify(HashMap<String,Object> map){
        return getService().verify(map);
    }

    @Override
    public List<String> queryCustom(){
        return getService().queryCustom();
    }

   
    @Override
    public String getCopyCheckDataTotal(HashMap<String,Object> paraMap){
        return getService().getCopyCheckDataTotal(paraMap);
    }

    @Override
    public String doBusOperEx(HashMap<String,Object> map) throws Exception{
        return getService().doBusOperEx(map);
    }

	@Override
	public RestfulQueryResult<CopyData> queryCreateCopy(String portCode) {
		return queryResToT(getService().queryCreateCopy(portCode),CopyData.class);
	}

	@Override
	public RestfulQueryResult<CopyData> queryPortClsCreateCopy() {
		return queryResToT(getService().queryPortClsCreateCopy(),CopyData.class);
	}

	//HashMap<String, Object> paraMap,PageInation page
	@Override
	public RestfulQueryResult<CopyDataCheck> queryCopyCheckData(
			QueryCopyCheckDataVo vo) {
		return queryResToT(getService().queryCopyCheckData(vo.getParaMap(),vo.getPage()),CopyDataCheck.class);
	}

	@Override
	public String getDataCopyService() throws Exception {
		return getService().getService();
	}

	//HashMap<String, Object> paraMap, Class<?> clazz
	@Override
	public List<CopyData> queryCopyData(QueryCopyDataVo vo) throws Exception {
		return castToListT(getService().queryCopyData(vo.getParaMap(),vo.getClazz()));
	}

   

}