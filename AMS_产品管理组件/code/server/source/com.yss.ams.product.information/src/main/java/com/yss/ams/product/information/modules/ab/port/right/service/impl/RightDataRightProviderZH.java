package com.yss.ams.product.information.modules.ab.port.right.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcustom.service.IPortCustomService;
import com.yss.ams.product.information.support.modules.ab.port.right.service.IRightDataRightProviderZH;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IPortDataService;
import com.yss.platform.support.dataservice.service.IVocDataService;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;
import com.yss.right.service.IPortService;

/**
 * 查询组合
 * 
 * @author gongjinzhi
 * 
 */
/**
 * <组合数据权限>权限服务实现类
 * 
 * 2017-07-07
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class RightDataRightProviderZH extends ServiceBus<IRightDataRightProviderZH> implements IRightDataRightProviderZH {

	@Override
	public List<DataRight> query() {
		return HttpServiceFactory.getInstance()
				.createService(IPortService.class).query();
	}

	@Override
	public List<DataDimension> queryDataDimensions() {
		ArrayList<String> dstr = null;
		try {
			dstr = YssServiceFactory.getInstance().createService(IPortCustomService.class).getAssetType();
		} catch (ServiceException e) {
			logger.error(e.getMessage());
		}
		List<DataDimension> dataDimensions = new ArrayList<DataDimension>();
 		if(dstr!=null&&dstr.size()>0){
 			for (String str : dstr) {
				String [] dstrs = str.split("\t");
				DataDimension dataDimension = new DataDimension();
				dataDimension.setCode(dstrs[0]);
				dataDimension.setName(dstrs[1]);
				dataDimensions.add(dataDimension);
			}
 		}
		return dataDimensions;
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, HashMap<String,String> customParam) {
		IPortDataService portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
		
		return portDataService.queryDataRight(dimensionType);
	}

	@Override
	public List<DataRight> query(String[] codes, HashMap<String,String> customParam) {
		
		return HttpServiceFactory.getInstance()
				.createService(IPortService.class).query(codes,customParam);
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, String[] codes, HashMap<String,String> customParam) {
		IPortDataService portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
		
		return portDataService.queryDataRight(dimensionType,codes,customParam);
	}

	@Override
	public HashMap<String, String> queryConditions() {
		IVocDataService vocDataService = YssServiceFactory.getInstance().createService(IVocDataService.class);
		//组合数据的状态
		String[] types = {"PD_STATUS"};
		List<Vocabulary> list =  vocDataService.getDataListByTypes(types);
		HashMap<String, String> map = new HashMap<String, String>();
		if(null != list){
			for(Vocabulary voc : list){
				map.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
			}
		}
		return map;
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		return HttpServiceFactory.getInstance()
				.createService(IPortService.class).query(customParam);
	}

}
