package com.yss.ams.sec.information.modules.sv.secbasejf.controller.impl;

import java.util.HashMap;

import com.yss.ams.sec.information.support.modules.sv.secbasejf.controller.ISecBaseJfController;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.pojo.SecBaseJf;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.service.ISecBaseJfService;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.vo.QueryPortRelaChargingSecVo;
import com.yss.ams.sec.information.support.modules.sv.secbasejf.vo.QueryRelaChargingSecVo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-10 15:18:13
*/
public class SecBaseJfControllerImpl extends AbstractBaseServiceBusController<SecBaseJf,ISecBaseJfService> implements ISecBaseJfController {

	//HashMap<String, Object> paraMap, PageInation pageIns
	@Override
	public RestfulQueryResult<SecBaseJf> queryRelaChargingSec(
			QueryRelaChargingSecVo vo) {
		 return queryResToT(getService().queryRelaChargingSec(vo.getParaMap(),vo.getPageIns()),SecBaseJf.class);
	}

	//HashMap<String, Object> paraMap,PageInation pageIns
	@Override
	public RestfulQueryResult<SecBaseJf> queryPortRelaChargingSec(
			QueryPortRelaChargingSecVo vo) throws Exception {
		 return queryResToT(getService().queryPortRelaChargingSec(vo.getParaMap(),vo.getPageIns()),SecBaseJf.class);
	}

}