package com.yss.ams.product.information.modules.ab.portrela.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.portrela.controller.IPortRelaController;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAcc;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaCashAccount;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaIndex;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaInvestMgr;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaMember;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaOrgan;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeOrg;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaTradeSeat;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.GetPortEexptExistByCopyVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelaDeleteByIdVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelaUpdateByIdVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.PortRelainsertVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountIdPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaCashAccountPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaIndexPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaInvestMgrPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaMemberPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaOrganPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeOrgPageVo;
import com.yss.ams.product.information.support.modules.ab.portrela.vo.QueryPortRelaTradeSeatPageVo;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortRelaControllerImpl extends AbstractBaseServiceBusController<PortRela,IPortRelaService> implements IPortRelaController {


	@Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountPage(QueryPortRelaCashAccountPageVo vo){
        return queryResToT(getService().queryPortRelaCashAccountPage(vo.getParaMap(),vo.getPage()),PortRelaCashAccount.class);
    }

	@Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccount(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaCashAccount(paraMap),PortRelaCashAccount.class);
    }

	@Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountIdPage(QueryPortRelaCashAccountIdPageVo vo){
        return queryResToT(getService().queryPortRelaCashAccountIdPage(vo.getParaMap(),vo.getPage()),PortRelaCashAccount.class);
    }
	
	//HashMap<String, Object> paraMap,PageInation page
	@Override
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeatPage(QueryPortRelaTradeSeatPageVo vo){
        return queryResToT(getService().queryPortRelaTradeSeatPage(vo.getParaMap(),vo.getPage()),PortRelaTradeSeat.class);
    }
	
	//HashMap<String, Object> paraMap,PageInation page
	@Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgPage(QueryPortRelaTradeOrgPageVo vo){
        return queryResToT(getService().queryPortRelaTradeOrgPage(vo.getParaMap(),vo.getPage()),PortRelaTradeOrg.class);
    }
	
	@Override
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgrPage(QueryPortRelaInvestMgrPageVo vo){
        return queryResToT(getService().queryPortRelaInvestMgrPage(vo.getParaMap(),vo.getPage()),PortRelaInvestMgr.class);
    }

	@Override
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrganPage(QueryPortRelaOrganPageVo vo){
        return queryResToT(getService().queryPortRelaOrganPage(vo.getParaMap(),vo.getPage()),PortRelaOrgan.class);
    }

	@Override
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountId(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaCashAccountId(paraMap),PortRelaCashAccount.class);
    }

	@Override
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeat(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaTradeSeat(paraMap),PortRelaTradeSeat.class);
    }

	@Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrg(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaTradeOrg(paraMap),PortRelaTradeOrg.class);
    }

	@Override
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgSet(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaTradeOrgSet(paraMap),PortRelaTradeOrg.class);
    }

	@Override
    public RestfulQueryResult<PortRelaTradeOrg> getPortRelaTdOrg(HashMap<String,Object> paraMap){
        return queryResToT(getService().getPortRelaTdOrg(paraMap),PortRelaTradeOrg.class);
    }

	@Override
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgr(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaInvestMgr(paraMap),PortRelaInvestMgr.class);
    }

	@Override
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrgan(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaOrgan(paraMap),PortRelaOrgan.class);
    }

	@Override
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndexPage(QueryPortRelaIndexPageVo vo){
        return queryResToT(getService().queryPortRelaIndexPage(vo.getParaMap(),vo.getPage()),PortRelaIndex.class);
    }

	@Override
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndex(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaIndex(paraMap),PortRelaIndex.class);
    }

	@Override
    public String delInsert(List<PortRela> pojoList){
        return getService().delInsert(castToBasePojoList(pojoList));
    }

	@Override
    public String delByYwId(List<PortRela> pojoList){
        return getService().delByYwId(castToBasePojoList(pojoList));
    }

	//String ports, BasePojo pojo
	@Override
    public List<String> getPortEexptExistByCopy(GetPortEexptExistByCopyVo vo){
        return getService().getPortEexptExistByCopy(vo.getPorts(),vo.getPojo());
    }
	
    @Override
    public RestfulQueryResult<PortRelaMember> queryPortRelaMember(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaMember(paraMap),PortRelaMember.class);
    }

    @Override
    public RestfulQueryResult<PortRelaMember> queryPortRelaMemberPage(QueryPortRelaMemberPageVo vo){
        return queryResToT(getService().queryPortRelaMemberPage(vo.getParaMap(),vo.getPage()),PortRelaMember.class);
    }
    @Override
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAccPage(QueryPortRelaCashAccPageVo vo){
        return queryResToT(getService().queryPortRelaCashAccPage(vo.getParaMap(),vo.getPage()),PortRelaCashAcc.class);
    }
    @Override
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAcc(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryPortRelaCashAcc(paraMap),PortRelaCashAcc.class);
    }

    @Override
    public String insert(PortRelainsertVo vo){
    	return getService().insert(vo.getPojo(),vo.getConn());
    }

    //List<BasePojo> list, Connection conn
    @Override
    public String deleteById(PortRelaDeleteByIdVo vo){
    	return getService().deleteById(castToBasePojoList(vo.getList()),vo.getConn());
    }

    @Override
    public String updateById(PortRelaUpdateByIdVo vo){
    	return getService().updateById(vo.getPojo(),vo.getConn());
    }

}