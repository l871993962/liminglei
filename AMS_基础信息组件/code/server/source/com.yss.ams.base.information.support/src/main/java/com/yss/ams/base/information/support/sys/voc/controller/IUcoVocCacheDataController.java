package com.yss.ams.base.information.support.sys.voc.controller;

import javax.ws.rs.Path;

import com.yss.ams.base.information.common.YssServiceIdConstant;
import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.platform.support.dataservice.controller.IVocCacheDataServiceController;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
*
* @author neil
* @date 2020-09-07 18:11:11
*/
@Path("")
@RestfulClient(serviceId = YssServiceIdConstant.OSGI_BASEINFO,
impl = "com.yss.ams.base.information.modules.sys.voc.service.impl.VocCacheDataService",
interfaceClass = com.yss.platform.support.dataservice.service.IVocCacheDataService.class,
productLine = ProductLine.BASEINFO, serviceMapId = "IVocCacheDataService" , menuId = "pubvocabulary")
public interface IUcoVocCacheDataController extends IVocCacheDataServiceController {


  

}