package com.yss.ams.product.information.modules.aa.portcustom.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcustom.controller.IPortCustomController;
import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService;
import com.yss.ams.product.information.support.modules.aa.portcustom.vo.InsertCustomPortVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortCustomControllerImpl extends AbstractBaseServiceBusController<PortCustom,IPortCustomService> implements IPortCustomController {

    @Override
    public ArrayList<String> getUserDefaultPort(HashMap<String,String> paradict){
        return getService().getUserDefaultPort(paradict);
    }

    @Override
    public ArrayList<String> getAssetType(){
        return getService().getAssetType();
    }

    @Override
    public String getShowType(HashMap<String,String> codeMap){
        return getService().getShowType(codeMap);
    }

    @Override
    public String deleteCustomPort(HashMap<String,String> param){
        return getService().deleteCustomPort(param);
    }

    //List<PortCustom> list,String type
    @Override
    public String insertCustomPort(InsertCustomPortVo vo){
        return getService().insertCustomPort(vo.getList(),vo.getType());
    }


}