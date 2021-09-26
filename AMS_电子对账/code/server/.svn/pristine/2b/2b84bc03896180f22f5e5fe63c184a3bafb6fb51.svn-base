package com.yss.uco.elecreco.er.erbbinfo.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.uco.elecreco.er.erbbinfo.service.IElecGeneDataService;
import com.yss.uco.elecreco.support.dzdz.common.ErConfigUtil;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;

/**
 * 
 * @author weijj
 *
 */
public class ElecGeneDataService implements IElecGeneDataService{

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();
		List<ElecGroupRela> list = this.getDataList();
		if(list!=null&&list.size()>0){
			for(ElecGroupRela e: list){
				if(e.getC_ELEC_CODE().indexOf("_")>-1) {
					e.setC_ELEC_CODE(e.getC_ELEC_CODE().split("_")[1]);
				}
				keyValueMap.put(e.getC_ELEC_CODE(), e.getC_ELEC_NAME());
			}
		}
		return keyValueMap;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		try {
			return (List<K>) ErConfigUtil.getElecCfg();
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
