package com.yss.ams.sec.information.modules.plateset.plate.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.plateset.plate.admin.PlateAAdmin;
import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateDao;
import com.yss.ams.sec.information.modules.plateset.plate.dao.PlateSqlBuilder;
import com.yss.ams.sec.information.support.modules.plateset.plate.pojo.Plate;
import com.yss.ams.sec.information.support.modules.plateset.plate.service.IPlate_AService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;

/**
 * 板块服务类
 * @author 马向峰 拆分
 *@Date 20170531
 */
public class Plate_AService extends ServiceBus<Plate_AService> implements
		IPlate_AService {

	private PlateDao serviceDao = null;
	private PlateAAdmin dataAdmin = null;

	public Plate_AService() throws Exception {
		serviceDao = new PlateDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class),
				new PlateSqlBuilder());
		dao = serviceDao;
		dataAdmin = new PlateAAdmin();
	}

	public QueryRes getTreeViewData(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTreeViewData(paraMap);

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,SecInfoActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("板块信息功能模块：查询板块信息出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public String updateById(List<BasePojo> pojoList) {
		String retInfo = "";

		try {
			((PlateDao) dao).updatePlateRela(pojoList);
			for (BasePojo pojo : pojoList) {
				((ParamPojo) pojo).setModifyDate(DateUtil
						.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				dao.updateById(pojo);
			}

			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			retInfo = ReturnInfoGenerator.getUpdateErrMsg(menuId);
//			ex.printStackTrace();
			logger.log("板块信息功能模块：修改板块信息出错", ex);
		}
		return retInfo;
	}

	/**
	 * 板块分类
	 * 
	 * @return
	 */
	public List<Plate> getPlateCategory() {
//		add by zhoushuhang 20170519 BUG #160288 点击资本币种下拉框报错  
//		 定义一个参数。flag 为true取所有板块信息。为false 加载版块信息设置中版块代码为SAC的数据
		return serviceDao.getPlateCategory(true);
	}

	/**
	 * Author : zhoushuhang
	 * Date   : 2017-05-19
	 * Status : Add
	 * Comment: BUG #160288 点击资本币种下拉框报错
	 * 加载版块信息设置中版块代码为SAC的数据
	 * @return  行业类别(板块分类)对象集合
	 */
	public List<Plate> getSacPlateCategory() {
//		加载版块信息设置中版块代码为SAC的数据
		return serviceDao.getPlateCategory(false);
	}
	
	/**
	 * 查询板块信息是否有子节点
	 */
	@Override
	public String getSUBData(String data) throws ServiceException {
		String result = "";// 返回的结果
		try {
			result = dataAdmin.checkSubData(data.split(","));
		} catch (Exception e) {
			throw new ServiceException(e.getMessage());
		} 
		
		return result;
	}

}
