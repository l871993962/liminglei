package com.yss.ams.base.information.modules.bi.account.controller.impl;

import com.yss.ams.base.information.support.bi.account.controller.IPortRelaDataController;
import com.yss.ams.base.information.support.bi.account.service.IPortRelaDataService;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-07 14:27:02
*/
public class PortRelaDataControllerImpl extends AbstractBaseController<IPortRelaDataService> implements IPortRelaDataController{

    @Override
    public String getCashPortByInfo(String relaCode) throws Exception{
        return getService().getCashPortByInfo(relaCode);
    }

    @Override
    public void deletePortRela(String relaCode,String portCodes) throws Exception{
         getService().deletePortRela(relaCode,portCodes);
    }

    @Override
    public void updatePortRela(String relaCode,String portCodes) throws Exception{
         getService().updatePortRela(relaCode,portCodes);
    }

	@Override
	public void insertPortRela(String relaCode, String portCodes) throws Exception{
			getService().insertPortRela(relaCode,portCodes);
	}

  

}