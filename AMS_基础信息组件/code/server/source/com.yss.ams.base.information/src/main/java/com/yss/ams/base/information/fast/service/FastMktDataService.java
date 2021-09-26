package com.yss.ams.base.information.fast.service;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.common.co.Mkt;
import com.yss.ams.base.information.support.bi.mkt.service.IMktDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.commonInfo.pojo.FastMkt;
import com.yss.framework.api.commonInfo.service.IFastMktDataService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JsonUtil;

/**
 * 交易市场Fast服务实现
 * @author lenovo
 *
 */
public class FastMktDataService implements IFastMktDataService{
	
	private List<FastMkt> convert2FastDttdMode(List<BasePojo> basePojos) {
		List<Mkt> mktList = new ArrayList<Mkt>();
		if(null != basePojos){
			for(BasePojo basePojo : basePojos){
				Mkt mkt = (Mkt)basePojo;
				mkt.setC_DV_MKT_TYPE(null);
				mktList.add(mkt);
			}
		}
		return JsonUtil.toList(JsonUtil.toString(mktList), FastMkt.class);
	}

	@Override
	public List<FastMkt> getDataList() throws ServiceException {
		IMktDataService mktDataService = YssServiceFactory.getInstance().createService(IMktDataService.class);
		List<FastMkt> list = convert2FastDttdMode(mktDataService.getDataList());
		return list;
	}

}
