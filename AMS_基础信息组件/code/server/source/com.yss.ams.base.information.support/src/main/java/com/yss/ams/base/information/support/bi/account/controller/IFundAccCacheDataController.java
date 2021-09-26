package com.yss.ams.base.information.support.bi.account.controller;

import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseController;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.service.IFundAccCacheDataService;

/**
*
* @author neil
* @date 2020-08-27 16:02:35
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.bi.account.service.impl.FundAccCacheDataService",
interfaceClass = com.yss.ams.base.information.support.bi.account.service.IFundAccCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IFundAccCacheDataService", menuId = "IFundAccCacheDataService")
public interface IFundAccCacheDataController extends IBaseController<IFundAccCacheDataService> {


		@POST
	    @Path("/getCacheList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
		public List<FundAcc> getCacheList();
		
		@POST
	    @Path("/bindServiceNames")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
		public List<String> bindServiceNames();
		
		@POST
	    @Path("/getCacheByKey")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
		public FundAcc getCacheByKey(String key);
		
   

}


