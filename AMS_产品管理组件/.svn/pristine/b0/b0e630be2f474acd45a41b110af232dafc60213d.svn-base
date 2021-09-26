package com.yss.ams.product.information.modules.pg.portgroup.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.pg.portgroup.controller.IPortGroupDataController;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupDataControllerImpl extends AbstractBaseController<IPortGroupDataService> implements IPortGroupDataController {

    @Override
    public HashMap<String,String> getKeyConvertMap(){
        return getService().getKeyConvertMap();
    }

    @Override
    public HashMap<String,String> getKeyConvertMap(List<String> listKey){
        return getService().getKeyConvertMap(listKey);
    }

    @Override
    public List<PortGroup> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortGroup> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortGroup.class);
    }

    @Override
    public PortGroup getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

    @Override
    public PortGroup getPojoByCode(String pojoCode){
        return getService().getPojoByCode(pojoCode);
    }

    @Override
    public List<PortGroup> getDataListByTypes(String[] types){
        return getService().getDataListByTypes(types);
    }

    @Override
    public RestfulQueryResult<PortGroup> getQueryResByTypes(String[] types){
        return queryResToT(getService().getQueryResByTypes(types),PortGroup.class);
    }

    @Override
    public List<PortGroup> getDataListByKeys(String[] keys){
        return getService().getDataListByKeys(keys);
    }

    @Override
    public RestfulQueryResult<PortGroup> getQueryResByKeys(String[] keys){
        return queryResToT(getService().getQueryResByKeys(keys),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> getPortGroupA(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortGroupA(paraMap),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> querySelectedPort(String c_group_code){
        return queryResToT(getService().querySelectedPort(c_group_code),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> querySelectedPortWithoutRight(String c_group_code){
        return queryResToT(getService().querySelectedPortWithoutRight(c_group_code),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> getGroupDataTreeWithoutRight(){
        return queryResToT(getService().getGroupDataTreeWithoutRight(),PortGroup.class);
    }

    @Override
    public RestfulQueryResult<Port> getGroupDataTree(){
        return queryResToT(getService().getGroupDataTree(),Port.class);
    }

    @Override
    public RestfulQueryResult<PortGroup> getGroupDataTree(List<String> portList){
        return queryResToT(getService().getGroupDataTree(portList),PortGroup.class);
    }

    @Override
    public String checkPortCode(String groupCode){
        return getService().checkPortCode(groupCode);
    }

    @Override
    public String deleteByPortCodes(String[] portCodes){
        return getService().deleteByPortCodes(portCodes);
    }

    @Override
    public List<PortGroup> getAuthorityGroup(){
        return getService().getAuthorityGroup();
    }

	@Override
	public List<String> querySelectedPortCode(String c_group_code) {
		return getService().querySelectedPortCode(c_group_code);
	}

}