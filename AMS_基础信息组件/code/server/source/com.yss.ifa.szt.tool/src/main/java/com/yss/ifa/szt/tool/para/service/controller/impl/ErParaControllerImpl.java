package com.yss.ifa.szt.tool.para.service.controller.impl;

import java.util.List;

import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.ifa.szt.tool.para.service.IErParaService;
import com.yss.ifa.szt.tool.para.service.controller.IErParaController;
import com.yss.ifa.szt.tool.pojo.ErPara;
import com.yss.ifa.szt.tool.pojo.MrInfo;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class ErParaControllerImpl extends AbstractBaseServiceBusController<ErPara,IErParaService> implements IErParaController {

    @Override
    public List<MrInfo> queryMrInfos(String c_Para_Code){
        return getService().queryMrInfos(c_Para_Code);
    }

}