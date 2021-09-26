package com.yss.ams.product.information.support.modules.ab.portrela.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
import com.yss.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;

/**
*
* @author neil
* @date 2020-09-14 11:34:49
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_PRODUCTINFO,
impl = "com.yss.ams.product.information.modules.ab.portrela.service.impl.PortRelaService",
interfaceClass = com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService.class,
productLine = ProductLine.PRODUCTINFO, serviceMapId = "IPortRelaService", menuId = "pd_portrelation")
public interface IPortRelaController extends IBaseServiceBusController<PortRela,IPortRelaService> {


    @POST
    @Path("/queryPortRelaCashAccountPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountPage(QueryPortRelaCashAccountPageVo vo);

    @POST
    @Path("/queryPortRelaCashAccount")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccount(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaCashAccountIdPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountIdPage(QueryPortRelaCashAccountIdPageVo vo);

    @POST
    @Path("/queryPortRelaTradeSeatPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeatPage(QueryPortRelaTradeSeatPageVo vo);

    @POST
    @Path("/queryPortRelaTradeOrgPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgPage(QueryPortRelaTradeOrgPageVo vo);

    @POST
    @Path("/queryPortRelaInvestMgrPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgrPage(QueryPortRelaInvestMgrPageVo vo);

    @POST
    @Path("/queryPortRelaOrganPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrganPage(QueryPortRelaOrganPageVo vo);

    @POST
    @Path("/queryPortRelaCashAccountId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAccount> queryPortRelaCashAccountId(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaTradeSeat")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeSeat> queryPortRelaTradeSeat(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaTradeOrg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrg(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaTradeOrgSet")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> queryPortRelaTradeOrgSet(HashMap<String,Object> paraMap);

    @POST
    @Path("/getPortRelaTdOrg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaTradeOrg> getPortRelaTdOrg(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaInvestMgr")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaInvestMgr> queryPortRelaInvestMgr(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaOrgan")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaOrgan> queryPortRelaOrgan(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaIndexPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndexPage(QueryPortRelaIndexPageVo vo);

    @POST
    @Path("/queryPortRelaIndex")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaIndex> queryPortRelaIndex(HashMap<String,Object> paraMap);

    @POST
    @Path("/delInsert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String delInsert(List<PortRela> pojoList);

    @POST
    @Path("/delByYwId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String delByYwId(List<PortRela> pojoList);

    @POST
    @Path("/getPortEexptExistByCopy")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getPortEexptExistByCopy(GetPortEexptExistByCopyVo vo);

    @POST
    @Path("/queryPortRelaMember")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaMember> queryPortRelaMember(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryPortRelaMemberPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaMember> queryPortRelaMemberPage(QueryPortRelaMemberPageVo vo);

    @POST
    @Path("/queryPortRelaCashAccPage")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAccPage(QueryPortRelaCashAccPageVo vo);

    @POST
    @Path("/queryPortRelaCashAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<PortRelaCashAcc> queryPortRelaCashAcc(HashMap<String,Object> paraMap);

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insert(PortRelainsertVo vo);

    @POST
    @Path("/deleteById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String deleteById(PortRelaDeleteByIdVo vo);

    @POST
    @Path("/updateById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateById(PortRelaUpdateByIdVo vo);

}