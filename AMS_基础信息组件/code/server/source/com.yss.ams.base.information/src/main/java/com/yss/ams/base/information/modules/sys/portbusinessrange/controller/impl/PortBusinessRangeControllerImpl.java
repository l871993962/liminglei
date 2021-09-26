package com.yss.ams.base.information.modules.sys.portbusinessrange.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.portbusinessrange.controller.IPortBusinessRangeController;
import com.yss.ams.base.information.support.sys.portbusinessrange.pojo.PortBusinessRangePojo;
import com.yss.ams.base.information.support.sys.portbusinessrange.pojo.PortBusinessRangePojoVo;
import com.yss.ams.base.information.support.sys.portbusinessrange.service.IPortBusinessRangeService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class PortBusinessRangeControllerImpl extends AbstractBaseServiceBusController<PortBusinessRangePojo,IPortBusinessRangeService> implements IPortBusinessRangeController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<PortBusinessRangePojo> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortBusinessRangePojo> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortBusinessRangePojo.class);
    }

    @Override
    public PortBusinessRangePojo getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public List<Vocabulary> getDataListByType(String type){
        return getService().getDataListByType(type);
    }

    //String type, HashMap<String, String> paraMap
    @Override
	public boolean updateDataList(PortBusinessRangePojoVo vo) {
		return getService().updateDataList(vo.getType(),vo.getParaMap());
	}


    @Override
    public List<String> getPortListByBusiCode(String busiCode){
        return getService().getPortListByBusiCode(busiCode);
    }

	@Override
	public void insertPortBusinessRange(List<BasePojo> pojoList) {
		getService().insertPortBusinessRange(pojoList);
	}
	
}