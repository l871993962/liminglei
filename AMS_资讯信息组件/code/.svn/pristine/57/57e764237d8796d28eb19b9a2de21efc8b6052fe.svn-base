package com.yss.ams.sec.information.modules.mp.outmkt.controller.impl;

import com.yss.ams.sec.information.support.modules.mp.outmkt.controller.IOutMktController;
import com.yss.ams.sec.information.support.modules.mp.outmkt.service.IOutMktService;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class OutMktControllerImpl extends AbstractBaseController<IOutMktService> implements IOutMktController {

    @Override
    public String getSYLX(String secCode){
        return getService().getSYLX(secCode);
    }

    @Override
    public String checkDuplicate(String secCode,String d_mkt,String mktCls,String dvPlat) throws Exception{
        return getService().checkDuplicate(secCode,d_mkt,mktCls,dvPlat);
    }

}