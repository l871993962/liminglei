package com.yss.ams.product.information.modules.pg.portgroup.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataProvider;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.right.constants.RightConstants;
import com.yss.right.pojo.DataDimension;
import com.yss.right.pojo.DataRight;

/**
 * STORY #47085 【数据权限分配】增加“产品群组”
 * @author lenovo
 *
 */
public class PortGroupDataProvider extends ServiceBus<PortGroupDataProvider> implements IPortGroupDataProvider{

	@Override
	public List<DataRight> query() {
		List<DataRight> groups = new ArrayList<DataRight>();
		try {
			IPortDataService portService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			List<Port> list = portService.getGroupDataTreeWithoutRight("");
			
			if(list == null) {
				return groups;
			}
			
			for(int i = 0; i < list.size(); i++){
				Port port = list.get(i);
				DataRight dataRight = new DataRight();
				dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
				dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
				dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
				dataRight.setC_DATA_TYPE(RightConstants.groupType);//群组数据
				groups.add(dataRight);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return groups;
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, HashMap<String,String> customParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataDimension> queryDataDimensions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRight> query(String[] codes, HashMap<String,String> customParam) {
		if(null == codes || codes.length == 0){
			return null;
		}
		List<String> codeList = Arrays.asList(codes);
		List<DataRight> groups = null;
		try {
			IPortDataService portService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			List<Port> list = portService.getGroupDataTreeWithoutRight("");
			groups = new ArrayList<DataRight>();
			
			if(list == null) {
				return groups;
			}
			
			for(int i = 0; i < list.size(); i++){
				Port port = list.get(i);
				if(null != port.getC_PORT_CODE() && codeList.contains(port.getC_PORT_CODE())){
					DataRight dataRight = new DataRight();
					dataRight.setC_DATA_CODE(port.getC_PORT_CODE());
					dataRight.setC_DATA_CODE_P(port.getC_PORT_CODE_P());
					dataRight.setC_DATA_NAME(port.getC_PORT_NAME());
					dataRight.setC_DATA_TYPE(RightConstants.groupType);//群组数据
					groups.add(dataRight);
				}
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return groups;
	}

	@Override
	public List<DataRight> queryByDimension(String dimensionType, String[] codes, HashMap<String,String> customParam) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> queryConditions() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DataRight> query(HashMap<String, String> customParam) {
		// TODO Auto-generated method stub
		return query();
	}

}
