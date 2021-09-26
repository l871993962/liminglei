package com.yss.ams.base.information.modules.sys.daeelem.controller.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.yss.ams.base.information.support.sys.daeelem.controller.IDaeElemController;
import com.yss.ams.base.information.support.sys.daeelem.pojo.DaeElem;
import com.yss.ams.base.information.support.sys.daeelem.service.IDaeElemService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DaeElemControllerImpl extends AbstractBaseServiceBusController<DaeElem,IDaeElemService> implements IDaeElemController {

    @Override
    public String getDaeNameByDaeCode(String daeCode){
        return getService().getDaeNameByDaeCode(daeCode);
    }

    @Override
    public ArrayList<String> getDaeCodesByCondition(HashMap<String,String> paraMap){
        return getService().getDaeCodesByCondition(paraMap);
    }

    @Override
    public String isDetailByCode(String code){
        return getService().isDetailByCode(code);
    }

    @Override
    public String getParentCodeByCode(String code){
        return getService().getParentCodeByCode(code);
    }

}