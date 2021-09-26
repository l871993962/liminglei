package com.yss.ams.product.information.modules.ab.port.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_report.controller.IAssetsTreeReportController;
import com.yss.ams.product.information.support.modules.ab.assetsTree_report.service.IAssetsTreeReportService;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.restful.base.AbstractBaseController;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class AssetsTreeReportControllerImpl extends AbstractBaseController<IAssetsTreeReportService> implements IAssetsTreeReportController {

    @Override
    public List<Port> portFilter(boolean isDataRight,String datClass,String dvPortCode,String trCode,String userCode) throws Exception{
        return castToListT(getService().portFilter(isDataRight,datClass,dvPortCode,trCode,userCode),Port.class);
    }

}