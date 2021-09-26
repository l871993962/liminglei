package com.yss.ams.base.information.modules.bi.org.controller.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yss.ams.base.information.support.bi.org.controller.IOrgController;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.pojo.OrgVo;
import com.yss.ams.base.information.support.bi.org.service.IOrgService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.resource.mgr.pojo.FileTrans;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class OrgControllerImpl extends AbstractBaseServiceBusController<Org,IOrgService> implements IOrgController {

    @Override
    public RestfulQueryResult<Org> getPortRelaOrg(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortRelaOrg(paraMap),Org.class);
    }

    @Override
    public List<Org> getOrgVoc(){
        return castToListT(getService().getOrgVoc());
    }

    //HashMap<String, Object> paraMap, PageInation page
    @Override
    public RestfulQueryResult<Org> queryPortRelaOrg(OrgVo vo){
        return queryResToT(getService().queryPortRelaOrg(vo.getParaMap(),vo.getPage()),Org.class);
    }

    @Override
    public String uploadLogo(FileTrans file){
        return getService().uploadLogo(file);
    }

    @Override
    public String queryImage(String imagePath){
        return getService().queryImage(imagePath);
    }

    @Override
    public List<Org> getOrgByConsignerType(){
        return getService().getOrgByConsignerType();
    }
    
    @Override
    public CacheData updateByIds(String ids){
        return getService().updateByIds(ids);
    }

    @Override
    public void deleteThenInsert(Org org){
        getService().deleteThenInsert(org);
    }

    @Override
    public List<Org> getDataListByTypes(String[] types) throws Exception{
        return	getService().getDataListByTypes(types);
    }

    @Override
    public String getManagerNameByPortCode(String portCode){
        return getService().getManagerNameByPortCode(portCode);
    }

    @Override
    public List<Org> getOrgByOrgCodes(String orgCodes) throws Exception{
        return getService().getOrgByOrgCodes(orgCodes);
    }

    @Override
    public List<String> getPortCodeListByRelaOrgCode(String orgCode){
        return getService().getPortCodeListByRelaOrgCode(orgCode);
    }

    @Override
    public List<Org> getOrgLinkManList(String orgCode){
        return getService().getOrgLinkManList(orgCode);
    }

    //BasePojo pojo,Connection conn
	@Override
	public String connInsert(OrgVo vo) {
		return getService().connInsert(vo.getPojo(),vo.getConn());
	}

	//List<BasePojo> list,Connection conn
	@Override
	public void connDelete(OrgVo vo) {
		List<BasePojo> result = new ArrayList<BasePojo>();
		if (CollectionUtils.isNotEmpty(vo.getList())) {
			for (BasePojo basePojo : vo.getList()) {
				result.add(castToT(basePojo));
			}
		}
		 getService().connDelete(result,vo.getConn());
	}

	@Override
	public void connUpdate(OrgVo vo) {
		getService().connUpdate(vo.getPojo(),vo.getConn());
	}

}