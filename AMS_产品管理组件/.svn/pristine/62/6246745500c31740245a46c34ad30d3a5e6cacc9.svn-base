package com.yss.ams.product.information.modules.pg.portgrouprela.controller.impl;

import java.util.List;

import com.yss.ams.product.information.support.modules.pg.portgrouprela.controller.IPortGroupRelaCopyController;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.pojo.PortGroupRela;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.service.IPortGroupRelaCopyService;
import com.yss.framework.api.restful.base.AbstractBaseController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-14 11:34:50
*/
public class PortGroupRelaCopyControllerImpl extends AbstractBaseController<IPortGroupRelaCopyService> implements IPortGroupRelaCopyController {

    @Override
    public List<PortGroupRela> getDataList(){
        return getService().getDataList();
    }

    @Override
    public RestfulQueryResult<PortGroupRela> getDataListRes(){
        return queryResToT(getService().getDataListRes(),PortGroupRela.class);
    }

    @Override
    public PortGroupRela getDataByCode(String dataCode){
        return getService().getDataByCode(dataCode);
    }

}