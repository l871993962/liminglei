package com.yss.ams.sec.information.modules.mp.hggthq.controller.impl;

import com.yss.ams.sec.information.support.modules.mp.hggthq.controller.ICounterRateController;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRate;
import com.yss.ams.sec.information.support.modules.mp.hggthq.pojo.CounterRateVo;
import com.yss.ams.sec.information.support.modules.mp.hggthq.service.ICounterRateService;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;


/**
*
* @author neil
* @date 2020-09-10 15:18:12
*/
public class CounterRateControllerImpl extends AbstractBaseServiceBusController<CounterRate,ICounterRateService> implements ICounterRateController {

	//Date mktDate, int duration, double rate
    @Override
    public int updateSecRate(CounterRateVo vo){
        return getService().updateSecRate(vo.getMktDate(),vo.getDuration(),vo.getRate());
    }

}