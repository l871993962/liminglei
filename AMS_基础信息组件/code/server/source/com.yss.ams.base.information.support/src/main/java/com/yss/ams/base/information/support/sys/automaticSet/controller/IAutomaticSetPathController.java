package com.yss.ams.base.information.support.sys.automaticSet.controller;

import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPathPojo;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetPathService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.uco.dataintegration.support.dataservice.pojo.ImpCfgGroup;

/**
* STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
* @author zhuziqing
* @date 2021-05-29
*/  
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.automaticSet.service.impl.AutomaticSetPathServiceImpl",
interfaceClass = com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetPathService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAutomaticSetPathService", menuId = "automaticSet")
public interface IAutomaticSetPathController  extends IBaseServiceBusController<AutomaticSetPathPojo, IAutomaticSetPathService>{

	    @POST
	    @Path("/getAllProductType")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<Vocabulary> getAllProductType();
	    
	    @POST
	    @Path("/getDataList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<AutomaticSetPathPojo> getDataList();
	    
	    @POST
	    @Path("/queryDataList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public QueryRes queryDataList(HashMap<String,Object> paraMap);
	    
	    @POST
	    @Path("/getInterfaceClass")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<Vocabulary> getInterfaceClass();
	 
	    @POST
	    @Path("/updateDataList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public boolean updateDataList(AutomaticSetPojoVo vo);
	    
	    @POST
	    @Path("/getProductType")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<AutomaticSetPathPojo> getProductType();
	    
	    @POST
	    @Path("/getInterfaceData")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<ImpCfgGroup> getInterfaceData(List<String> productName);
	    
	    @POST
	    @Path("/saveDataList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public boolean saveDataList(AutomaticSetPojoVo vo);
	    
	    @POST
	    @Path("/copy")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public boolean copy(AutomaticSetPojoVo vo);
	    
	    @POST
	    @Path("/getAllIndex")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<AutomaticSetPathPojo> getAllIndex();
	    
	    @POST
	    @Path("/saveList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public boolean saveList(AutomaticSetPojoVo vo);
	    
	    @POST
	    @Path("/getRePortCodeList")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<AutomaticSetPathPojo> getRePortCodeList();
	    
	    @POST
	    @Path("/queryByCodeAndName")
	    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
	    public List<BasePojo> queryByCodeAndName(String portCode, List<String> productName);
	}

