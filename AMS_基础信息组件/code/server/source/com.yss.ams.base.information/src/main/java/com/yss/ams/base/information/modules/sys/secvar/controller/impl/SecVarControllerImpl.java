package com.yss.ams.base.information.modules.sys.secvar.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.secvar.controller.ISecVarController;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVar;
import com.yss.ams.base.information.support.sys.secvar.pojo.SecVarVo;
import com.yss.ams.base.information.support.sys.secvar.service.ISecVarService;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class SecVarControllerImpl extends AbstractBaseServiceBusController<SecVar,ISecVarService> implements ISecVarController {

   

    @Override
    public RestfulQueryResult<SecVar> queryIdxCtrlSec(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryIdxCtrlSec(paraMap),SecVar.class);
    }

    @Override
    public RestfulQueryResult<SecVar> queryVarcodeByCode(String seccode){
        return queryResToT(getService().queryVarcodeByCode(seccode),SecVar.class);
    }

    @Override
    public HashMap<String,List<SecVar>> querySecVar() throws Exception{
        return getService().querySecVar();
    }

    //HashMap<String, Object> paraMap, PageInation page, String queryMenuId
	@Override
	public RestfulQueryResult<SecVar> selectWithSecAttr(SecVarVo vo) {
		return queryResToT(getService().selectWithSecAttr(vo.getParaMap(),vo.getPage(),vo.getQueryMenuId()),SecVar.class);
	}

}