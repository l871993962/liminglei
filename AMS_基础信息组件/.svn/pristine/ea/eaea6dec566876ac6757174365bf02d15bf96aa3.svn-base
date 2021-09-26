package com.yss.ams.base.information.support.sys.automaticSet.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojo;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojoVo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetService;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.framework.api.restful.base.IBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
* STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
* @author yangze
* @date 2020-12-24
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.automaticSet.service.impl.AutomaticSetServiceImpl",
interfaceClass = com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IAutomaticSetService", menuId = "automaticSet")
public interface IAutomaticSetController extends IBaseServiceBusController<AutomaticSetPojo, IAutomaticSetService> {

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
    public List<AutomaticSetPojo> getDataList();

    @POST
    @Path("/getDataListRes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public RestfulQueryResult<AutomaticSetPojo> getDataListRes();

    @POST
    @Path("/getDataByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public AutomaticSetPojo getDataByCode(String dataCode);

    @POST
    @Path("/getDataListByType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Vocabulary> getDataListByType(String type);

    @POST
    @Path("/updateDataList")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public boolean updateDataList(AutomaticSetPojoVo vo);

    @POST
    @Path("/getPortListByBusiCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getPortListByBusiCode(AutomaticSetPojoVo vo);
    
    @POST
    @Path("/getBusiInfoByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<Map<String, String>> getBusiInfoByCode(AutomaticSetPojoVo vo);
    
    @POST
    @Path("/getAllBusiType")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getAllBusiType();
    
    @POST
    @Path("/getBusiTypeByCode")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getBusiTypeByCode(String portCode);
    
    @POST
    @Path("/getBusiTypeByCodes")
    @Produces({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    @Consumes({ MediaType.APPLICATION_JSON + ";charset=UTF-8" })
    public List<String> getBusiTypeByCodes(AutomaticSetPojoVo vo);
}