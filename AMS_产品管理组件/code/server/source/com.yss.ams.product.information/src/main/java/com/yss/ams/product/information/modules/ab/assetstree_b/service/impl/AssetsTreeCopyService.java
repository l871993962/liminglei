package com.yss.ams.product.information.modules.ab.assetstree_b.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTreeCopyService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;


/**
 * 功能简介：新增'产品树形设置'复制
 * @author gh 2016-9-27
 * STORY33240【南方基金】复制建仓继承内容增加群组功能的勾选选项
 */
/**
 * <产品树型结构>复制服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTreeCopyService implements IAssetsTreeCopyService{
	
	private Logger logger = LogManager.getLogger(this.getClass());

	@Override
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState) {
		try {
			return new AssetsTreeService().copy(portCode, portCodeList, dataCode,
					execProcCode, checkState);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("产品树形设置", e);
		}
		return null;
	}

	@Override
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState, String isClear) {
		try {
			return new AssetsTreeService().copy(portCode, portCodeList, dataCode,
					execProcCode, checkState, isClear);
		} catch (Exception e) {
			logger.log("产品树形设置", e);
		}
		return null;
	}
	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
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
	public List<String> copy(String portCode,
			HashMap<String, String> portParamsMap, String dataCode,
			String execProcCode, String checkState) {
		try {
			return new AssetsTreeService().copy(portCode, portParamsMap, dataCode,
					execProcCode, checkState);
		} catch (Exception e) {
			logger.log("复制产品树形设置失败", e);
		}
		return null;
	}

}
