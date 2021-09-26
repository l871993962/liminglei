package com.yss.uco.elecreco.er.task.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SysFun;
import com.yss.framework.api.dataservice.IFunDataService;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.uco.elecreco.support.dzdz.common.ErConfigUtil;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;
import com.yss.uco.elecreco.support.service.ITaskSendService;

public class TaskSendService implements ITaskSendService{

	@Override
	public void init(Object... args) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String doBusOper(HashMap<String, Object> hmData)
			throws ServiceException {
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
	public void init(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		return this.getBizItems();
	}

	@Override
	public List<BizItem> getBizItems() throws ServiceException {
		List<BizItem> list = new ArrayList<BizItem>();
		try {
			List<BasePojo> listConfig = ErConfigUtil.getElecCfg();
			for (BasePojo pojo : listConfig) {
				ElecGroupRela rela = (ElecGroupRela) pojo;
				BizItem itemP = new BizItem();
				itemP.setC_BizItem_Code(rela.getC_ELEC_CODE());
				itemP.setC_BizItem_Name(rela.getC_ELEC_NAME());
				itemP.setC_BizItem_Code_P(rela.getC_PARENT_CODE());
				itemP.setC_Fun_Code("elecSend");
				list.add(itemP);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		}

		return list;
	}

	@Override
	public List<BizItem> getRootBizItems() throws ServiceException {
		List<BizItem> list = new ArrayList<BizItem>();
		try {
			SysFun sysFunPojo = null;
			IFunDataService funService = HttpServiceFactory.getInstance()
					.createService(IFunDataService.class);
			sysFunPojo = funService.getDataByCode("elecSend");
			if (sysFunPojo == null) {
				return list;
			}
			BizItem itemP = new BizItem();
			itemP.setC_BizItem_Code(sysFunPojo.getC_FUN_CODE());
			itemP.setC_BizItem_Name(sysFunPojo.getC_FUN_NAME());
			itemP.setC_BizItem_Code_P("[root]");
			itemP.setC_Fun_Code(sysFunPojo.getC_FUN_CODE());
			list.add(itemP);
			return list;
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}


	@Override
	public Entry<String, List<BEN_RECORD>> execute() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BEN_RECORD> getListRecord() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BEN_RECORD> doRecordConvert(List<BEN_RECORD> orignalRecordList) {
		// TODO Auto-generated method stub
		return null;
	}

}
