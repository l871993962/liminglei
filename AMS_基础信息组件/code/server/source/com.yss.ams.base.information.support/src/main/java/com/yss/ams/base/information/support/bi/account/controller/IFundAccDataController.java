package com.yss.ams.base.information.support.bi.account.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccDataService;

/**
*
* @author neil
* @date 2020-08-27 16:02:35
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.account.service.impl.FundAccDataService",
interfaceClass = com.yss.ams.base.information.support.bi.account.service.IFundAccDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFundAccDataService", menuId = "fundAccInfo")
public interface IFundAccDataController extends IBaseController<IFundAccDataService> {


		@POST
	    @Path("/updateByTimestamp")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public CacheData updateByTimestamp(String timestamp);

	    @POST
	    @Path("/queryByIds")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> queryByIds(String ids);

	    @POST
	    @Path("/getKeyConvertMap")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public HashMap<String,String> getKeyConvertMap();

	    @POST
	    @Path("/getKeyConvertMapByList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public HashMap<String,String> getKeyConvertMap(List<String> listKey);

	    @POST
	    @Path("/getDataList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataList();

	    @POST
	    @Path("/getDataListRes")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public RestfulQueryResult<FundAcc> getDataListRes();

	    @POST
	    @Path("/getDataByCode")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public FundAcc getDataByCode(String dataCode);

	    @POST
	    @Path("/getPojoByCode")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public FundAcc getPojoByCode(String pojoCode);

	    @POST
	    @Path("/getDataListByTypes")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByTypes(String[] types);

	    @POST
	    @Path("/getQueryResByTypes")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public RestfulQueryResult<FundAcc> getQueryResByTypes(String[] types);

	    @POST
	    @Path("/getDataListByKeys")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByKeys(String[] keys);

	    @POST
	    @Path("/getQueryResByKeys")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public RestfulQueryResult<FundAcc> getQueryResByKeys(String[] keys);

	    @POST
	    @Path("/getDataListByOrg")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByOrg(String orgCode);

	    @POST
	    @Path("/getAllDataByPort")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getAllDataByPort(String portCode);

	    @POST
	    @Path("/getDataListByPort")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByPort(String portCode);

	    @POST
	    @Path("/getDataListByAssCode")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByAssCode(String assCode);

	    @POST
	    @Path("/getDataListByIds")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getDataListByIds(String ids);

	    @POST
	    @Path("/getAccNameAndCaCode")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<FundAcc> getAccNameAndCaCode();
   

}


