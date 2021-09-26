package com.yss.ams.base.information.modules.sys.feeRelation.controller.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.feeRelation.controller.IFeeRelationController;
import com.yss.ams.base.information.support.sys.feeRelation.pojo.FeeRelation;
import com.yss.ams.base.information.support.sys.feeRelation.service.IFeeRelationService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class FeeRelationControllerImpl extends AbstractBaseServiceBusController<FeeRelation,IFeeRelationService> implements IFeeRelationController {

    @Override
    public List<FeeRelation> queryRealDateByCondition(HashMap<String,Object> paraMap){
        return castToListT(getService().queryRealDateByCondition(paraMap));
    }

    @Override
    public RestfulQueryResult<FeeRelation> queryByConditionFee(HashMap<String,Object> paraMap){
        return queryResToT(getService().queryByConditionFee(paraMap),FeeRelation.class);
    }

}