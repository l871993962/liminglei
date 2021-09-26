package com.yss.ams.base.information.modules.bi.mkt.controller.impl;

import java.util.List;
import com.yss.ams.base.information.support.bi.mkt.controller.IMktController;
import com.yss.ams.base.information.support.bi.mkt.pojo.MarketVoc;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktExtend;
import com.yss.ams.base.information.support.bi.mkt.pojo.MktVo;
import com.yss.ams.base.information.support.bi.mkt.service.IMktService;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.restful.base.AbstractBaseServiceBusController;
import com.yss.framework.api.restful.base.vo.RestfulQueryResult;


/**
*
* @author neil
* @date 2020-09-07 18:11:12
*/
public class MktControllerImpl extends AbstractBaseServiceBusController<Mkt,IMktService> implements IMktController {

    @Override
    public String getCheckStatus(String MktCode){
        return getService().getCheckStatus(MktCode);
    }

    @Override
    public List<MarketVoc> getAllMkt(){
        return getService().getAllMkt();
    }

    @Override
    public String compareQsjg(String mktCode){
        return getService().compareQsjg(mktCode);
    }

    //HashMap<String, Object> paraMap,PageInation page,String queryMenuID
	@Override
	public RestfulQueryResult<MktExtend> selectByConditionExtend(MktVo vo) {
		 return queryResToT(getService().selectByConditionExtend(vo.getParaMap(),vo.getPage(),vo.getQueryMenuID()),MktExtend.class);
	}

}