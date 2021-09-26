package com.yss.uco.elecreco.support.controller;

import javax.ws.rs.Path;

import com.yss.framework.api.distributed.ProductLine;
import com.yss.framework.api.mvc.controller.ICopyAbleFunController;
import com.yss.framework.api.restful.annotations.RestfulClient;
import com.yss.uco.elecreco.support.service.IElecPerRelaCopyService;
import com.yss.uco.elecreco.support.util.ElecrecoServiceIdConstant;

/**
 * 
 * @author zhanghubin 2021-03-11
 */
@Path("")
@RestfulClient(serviceId = ElecrecoServiceIdConstant.OSGI_ELECRECO,
impl = "com.yss.uco.elecreco.bi.elecrela.service.impl.ElecPerRelaCopyService",
interfaceClass = com.yss.uco.elecreco.support.service.IElecPerRelaCopyService.class,
productLine = ProductLine.ELECRECO,serviceMapId = "IElecPerRelaCopyService",menuId = "dzPerRela")
public interface IElecPerRelaCopyServiceController extends ICopyAbleFunController<IElecPerRelaCopyService>{

}
