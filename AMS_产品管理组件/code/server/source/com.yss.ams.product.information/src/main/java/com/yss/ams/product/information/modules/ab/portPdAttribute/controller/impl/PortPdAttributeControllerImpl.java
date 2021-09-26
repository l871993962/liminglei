package com.yss.ams.product.information.modules.ab.portPdAttribute.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portPdAttribute.controller.IPortPdAttributeController;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.pojo.PortPdAttribute;
import com.yss.ams.product.information.support.modules.ab.portPdAttribute.service.IPortPdAttributeService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortPdAttributeControllerImpl extends AbstractBaseServiceBusController<PortPdAttribute,IPortPdAttributeService> implements IPortPdAttributeController {

    @Override
    public HashMap<String,String> getVocabularyDict(){
        return getService().getVocabularyDict();
    }

    @Override
    public HashMap<String,String> getAssetDict(){
        return getService().getAssetDict();
    }

    @Override
    public HashMap<String,String> getPortPojoList(HashMap<String,String> paraMap){
        return getService().getPortPojoList(paraMap);
    }

    @Override
    public HashMap<String,String> getPortNameDict(HashMap<String,String> paraMap){
        return getService().getPortNameDict(paraMap);
    }

    @Override
    public List<String> queryPortCodesByType(String portType){
        return getService().queryPortCodesByType(portType);
    }

    @Override
    public List<PortPdAttribute> getShortNumPort(){
        return getService().getShortNumPort();
    }

	@Override
	public HashMap<String, List<String>> getPortCodeForNDay(String ports) throws Exception {
		return getService().getPortCodeForNDay(ports);
	}

}