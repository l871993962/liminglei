package com.yss.ams.base.information.support.bi.account.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.ams.base.information.restFul.vo.FundAccRetFulVo;
import com.yss.ams.base.information.support.bi.account.pojo.CashAcc;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.ams.base.information.support.bi.account.service.IFundAccService;

/**
*
* @author neil
* @date 2020-08-27 16:02:35
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.account.service.impl.FundAccService",
interfaceClass = com.yss.ams.base.information.support.bi.account.service.IFundAccService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFundAccService", menuId = "fundAccInfo")
public interface IFundAccController extends IBaseServiceBusController<FundAcc,IFundAccService> {




    @POST
    @Path("/getFundAccOrgcodeByAccNo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getFundAccOrgcodeByAccNo(String accNo);

    @POST
    @Path("/updateFundAccUnifyPay")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateFundAccUnifyPay(String accountNo);

    @POST
    @Path("/getDataListByAccTypes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public  List<FundAcc> getDataListByAccTypes(List<String> types);

    @POST
    @Path("/getDataListByAccTypes2")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getDataListByAccTypes2(List<String> types);

    @POST
    @Path("/isAccOfAccTypeExit")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String isAccOfAccTypeExit(String type);

    @POST
    @Path("/getFundAccByType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getFundAccByType(FundAccRetFulVo vo);

    @POST
    @Path("/updateOrgInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String updateOrgInfo(@FormParam("cPAYCODE") String cPAYCODE,@FormParam("cBANKCODE") String cBANKCODE,@FormParam("id") String id);

    @POST
    @Path("/getDataListByPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getDataListByPort(String portCode);

    @POST
    @Path("/getDataListByPortlist")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,List<FundAcc>> getDataListByPortlist(List<String> portCodeList);

    @POST
    @Path("/getFundAccByAccNo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public FundAcc getFundAccByAccNo(String accNo);

    @POST
    @Path("/getFundAccById")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public FundAcc getFundAccById(String id);

    @POST
    @Path("/getFundAccByAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public FundAcc getFundAccByAcc(FundAcc fundAcc);

    @POST
    @Path("/getIdAfterSave")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getIdAfterSave(FundAcc pojo);

    @POST
    @Path("/savePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Boolean savePortFundRela(@FormParam("portCodes") String portCodes,@FormParam("fundAccID") String fundAccID,@FormParam("accountType") String accountType);

    @POST
    @Path("/deletePortFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Boolean deletePortFundRela(@FormParam("relaCode") String relaCode,@FormParam("port") String port);

    @POST
    @Path("/deletePortsFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public Boolean deletePortsFundRela(@FormParam("relaCode") String relaCode,@FormParam("port") String port);

    @POST
    @Path("/getPortsByRelaCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getPortsByRelaCode(@FormParam("id") String id,@FormParam("portCode") String portCode);

    @POST
    @Path("/getPortsByRelaCode1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getPortsByRelaCode1(String id);

    @POST
    @Path("/deleteUnusePortRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Boolean deleteUnusePortRela();

    @POST
    @Path("/insert")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String insert(FundAccRetFulVo pojo);

    @POST
    @Path("/getDataListByAssCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getDataListByAssCode(String portCode);

    @POST
    @Path("/getCADataListByPortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public List<CashAcc> getCADataListByPortCode(@FormParam("portCode") String portCode,@FormParam("dcCode") String dcCode);

    @POST
    @Path("/queryZfbyPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String queryZfbyPort(@FormParam("c_open_addr") String c_open_addr,@FormParam("c_open_acc_no") String c_open_acc_no,@FormParam("c_open_acc_name") String c_open_acc_name);

    @POST
    @Path("/saveRelaInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String saveRelaInfo(FundAccRetFulVo pojo);

    @POST
    @Path("/deleteByRealId")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Boolean deleteByRealId(List<String> realIds);

    @POST
    @Path("/getFundAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortRela> getFundAcc(List<String> recordIds);

    @POST
    @Path("/getFundAccPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<PortRela> getFundAccPort(String portCode);

    @POST
    @Path("/readProperty")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String readProperty();

    @POST
    @Path("/updateProperty")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateProperty(String checkInfo);

    @POST
    @Path("/showAreaA")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String showAreaA();

    @POST
    @Path("/updatePortCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void updatePortCode(@FormParam("id") String id,@FormParam("fundId") String fundId);

    @POST
    @Path("/updateFundAccType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void updateFundAccType(@FormParam("id") String id,@FormParam("accountType") String accountType);

    @POST
    @Path("/getFundAccByInfo")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public FundAcc getFundAccByInfo(HashMap<String,String> paraMap);

    @POST
    @Path("/getFundAccByInfo1")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public FundAcc getFundAccByInfo1(Map<String,Object> paraMap);

    @POST
    @Path("/getFundAccListByCache")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getFundAccListByCache();

    @POST
    @Path("/getAccByRmHkType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String getAccByRmHkType(String search);

    @POST
    @Path("/getFundAccNoAndAddrList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getFundAccNoAndAddrList();

    @POST
    @Path("/getUniqueAccountTypeByPorts")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public HashMap<String,FundAcc> getUniqueAccountTypeByPorts(@FormParam("portCodes") String portCodes,@FormParam("accountType") String accountType);

    @POST
    @Path("/getAllFundAccByType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ArrayList<FundAcc> getAllFundAccByType(HashMap<String,String> paraMap) throws Exception;

    @POST
    @Path("/deleteThenSaveFundRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public void deleteThenSaveFundRela(@FormParam("id") String id,@FormParam("c_PORTFOLIOID") String c_PORTFOLIOID,@FormParam("string") String string);

    @POST
    @Path("/getFundAccByNoAddrName")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> getFundAccByNoAddrName(List<FundAcc> list);

    @POST
    @Path("/getAccInAccountTreeView")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<FundAcc> getAccInAccountTreeView(HashMap<String,Object> paraMap);

    @POST
    @Path("/queryAccNoByfun")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ArrayList<FundAcc> queryAccNoByfun(HashMap<String,String> paraMap);

    @POST
    @Path("/updatePaycodeByAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updatePaycodeByAcc(HashMap<String,String> map);

    @POST
    @Path("/updateFileMsg")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateFileMsg(FundAccRetFulVo pojo);

    @POST
    @Path("/fileDown")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String fileDown(String filePath);

    @POST
    @Path("/filesDownLoad")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public HashMap<String,String> filesDownLoad(List<String> fileList);

    @POST
    @Path("/delFile")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String delFile(List<String> filePathList);

    @POST
    @Path("/UpLoadByNewPath")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public ResourceMgr UpLoadByNewPath(FundAccRetFulVo pojo) throws Exception;

    @POST
    @Path("/getAssCodeByPortcode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public HashMap<String,String> getAssCodeByPortcode(@FormParam("codes") String codes,@FormParam("type") String type);

    @POST
    @Path("/updateFundAccCache")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public void updateFundAccCache(FundAccRetFulVo pojo);

	
    @POST
    @Path("/getAccByRunningAccs")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<FundAcc> getAccByRunningAccs(String openAccNo,String openAddr, String openAccName, String dcCode);  
    
    @POST
    @Path("/savePortFundRelaWithDate")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public Boolean savePortFundRelaWithDate(HashMap<String,String> paraMap);

    @POST
    @Path("/getTimeByRelaPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getTimeByRelaPort(@FormParam("portCode") String portCode,@FormParam("fundAccId") String fundAccId);
  
    @POST
    @Path("/updateTimeByRelaPort")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public String updateTimeByRelaPort(HashMap<String,String> paraMap);
    @POST
    @Path("/getAccListByOpenNoAndOpenAddr")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
    public String getAccListByOpenNoAndOpenAddr(@FormParam("openNo") String openNo, @FormParam("openAddr") String openAddr, @FormParam("iden") String iden);
    
    @POST
    @Path("/insertFundAcc")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public String insertFundAcc(FundAcc fa);
    
    @POST
    @Path("/addPortCodeRela")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public String addPortCodeRela(@FormParam("id") String id, @FormParam("fundId") String fundId);
    
    @POST
    @Path("/getFundAccByGF")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_FORM_URLENCODED + ";charset=UTF-8" })
	public FundAcc getFundAccByGF(Map<String, String> paraMap);

}


