package com.yss.ams.product.information.modules.aa.portcustom.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcustom.controller.IPortCustomDataController;
import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortCustomDataControllerImpl extends AbstractBaseController<IPortCustomDataService> implements IPortCustomDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<PortCustom> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortCustom> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortCustom.class);
    }

    @Override
    public PortCustom getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public String getShowType(HashMap<String,String> codeMap){
        return getService().getShowType(codeMap);
    }

    @Override
    public ArrayList<String> getAssetTypeOnlyCode(){
        return getService().getAssetTypeOnlyCode();
    }

}