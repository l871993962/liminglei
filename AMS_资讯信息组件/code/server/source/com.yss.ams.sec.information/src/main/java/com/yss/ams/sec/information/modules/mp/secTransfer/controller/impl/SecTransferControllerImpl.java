package com.yss.ams.sec.information.modules.mp.secTransfer.controller.impl;

import java.util.HashMap;

import com.yss.ams.sec.information.support.modules.mp.secTransfer.controller.ISecTransferController;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.pojo.SecTransfer;
import com.yss.ams.sec.information.support.modules.mp.secTransfer.service.ISecTransferService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class SecTransferControllerImpl extends AbstractBaseServiceBusController<SecTransfer,ISecTransferService> implements ISecTransferController {

    @Override
    public HashMap<String,String> getSecTranMapByCondition(HashMap<String,String> paramList){
        return getService().getSecTranMapByCondition(paramList);
    }

    @Override
    public String getParamValue(String portCode,String dateStr,String dspCode){
        return getService().getParamValue(portCode,dateStr,dspCode);
    }

}