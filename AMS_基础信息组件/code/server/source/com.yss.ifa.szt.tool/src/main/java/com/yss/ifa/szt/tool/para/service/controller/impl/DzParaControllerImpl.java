package com.yss.ifa.szt.tool.para.service.controller.impl;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IDzParaService;
import com.yss.ifa.szt.tool.para.service.controller.IDzParaController;
import com.yss.ifa.szt.tool.pojo.DzPara;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class DzParaControllerImpl extends AbstractBaseServiceBusController<DzPara,IDzParaService> implements IDzParaController {

    @Override
    public DzPara getParaByAssCode(String c_ASS_CODE){
        return getService().getParaByAssCode(c_ASS_CODE);
    }

    @Override
    public String encryptData(String encryptStr) throws Exception{
        return getService().encryptData(encryptStr);
    }

    @Override
    public String decryptData(String encryptStr) throws Exception{
        return getService().decryptData(encryptStr);
    }

}