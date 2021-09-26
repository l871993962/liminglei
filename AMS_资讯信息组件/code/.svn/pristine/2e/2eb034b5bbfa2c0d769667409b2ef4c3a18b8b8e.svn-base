package com.yss.ams.sec.information.modules.sv.suspendedcond.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.suspendedcond.dao.SuspendedCondDao;
import com.yss.ams.sec.information.modules.sv.suspendedcond.dao.SuspendedCondSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond;
import com.yss.ams.sec.information.support.modules.sv.suspendedcond.service.ISuspendedCondService;


/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SuspendedCondService extends ServiceBus<SuspendedCondService>
		implements ISuspendedCondService {

	private SuspendedCondDao condDao = null;

	public SuspendedCondService() throws Exception {
		condDao = new SuspendedCondDao(YssDbPoolFactory.getInstance()
				.getDbPool(SecInfoActivator.class), new SuspendedCondSqlBuilder());
		dao = condDao;
	}

	@Override
	protected void fillResultObject(QueryRes queryRes, List<BasePojo> dataList,
			int countAll, PageInation page, String menuId) throws Exception {
		String menuIdd = "sv_suspended_para";
		//// addbyliyongjun 2016-08-07  对界面的显示进行修改 BUG #136107 停牌股票信息，生成，报错
		for(BasePojo basePojo : dataList){
			SuspendedCond suspendedCond = null;
			 suspendedCond = (com.yss.ams.sec.information.support.modules.sv.suspendedcond.pojo.SuspendedCond) basePojo;
			 if("LJ_AND".equalsIgnoreCase(suspendedCond.getC_LOGICAL_JUDGMENT())){
				 suspendedCond.setC_LOGICAL_JUDGMENT("并且");
			 }else if("LJ_OR".equalsIgnoreCase(suspendedCond.getC_LOGICAL_JUDGMENT())){
				 suspendedCond.setC_LOGICAL_JUDGMENT("或者");
			 }else if("LJ_NOT_ENABLED".equalsIgnoreCase(suspendedCond.getC_LOGICAL_JUDGMENT())){
				 suspendedCond.setC_LOGICAL_JUDGMENT("不启用");
			 }
			 
			 if("VT_WORKDAY".equalsIgnoreCase(suspendedCond.getC_VALUE_TYPE())){
				 suspendedCond.setC_VALUE_TYPE("工作日");
			 }else if("VT_NATURAL".equalsIgnoreCase(suspendedCond.getC_VALUE_TYPE())){
				 suspendedCond.setC_VALUE_TYPE("自然日");
			 }
			 
			 if("AJUST_DAY_MP".equalsIgnoreCase(suspendedCond.getC_ITEM_VALUE())){
				 suspendedCond.setC_ITEM_VALUE("调日指数估值价格");
			 }else if("RECENT_MP".equalsIgnoreCase(suspendedCond.getC_ITEM_VALUE())){
				 suspendedCond.setC_ITEM_VALUE("最近指数估值价格");
			 }
			 //add by wzh 2017-4-21 BUG #157749 停牌股票信息选项中，点开查看已设置内容时，各字段显示代码不显示文字
		 	  else if ("SUSPEND_MP".equalsIgnoreCase(suspendedCond.getC_ITEM_VALUE())) {
		 		suspendedCond.setC_ITEM_VALUE("停牌日指数估值价格");
		 	 }
			 basePojo = suspendedCond;
		}
		// 对查找出来的停牌股票参数进行升序排序
		Collections.sort(dataList,new Comparator<BasePojo>() {
			@Override
			public int compare(BasePojo o1, BasePojo o2) {
				//BUG #348777 【停牌股票信息】点击选项报错，点击选项的下拉框‘私有化’正常的
				if((Long.parseLong(o1.getId()) - Long.parseLong(o1.getId())) >= 0){
					return 1;
				}
				return -1;
			}
		});
		queryRes.setDataList(dataList);
		queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuIdd,
				SecInfoActivator.class));

		if (page == null) {
			page = new PageInation();
		} else {
			page.setTotalNum(countAll);
		}
		queryRes.setPage(page);

		setShowConvertAssemble(queryRes);
		queryRes.setOperRes(MvcConstant._Success);
		queryRes.setMenuId(menuIdd);
	}

	/**
	 * 修改功能选项参数
	 * 
	 * @param pojoList
	 * @return
	 */
	public String updateConds(List<BasePojo> pojoList) {
		String retInfo = "";
		BasePojo currPojo = null;
		try {
			for (BasePojo pojo : pojoList) {
				SuspendedCond cond = (SuspendedCond) pojo;
				currPojo = pojo;
				condDao.updateById(cond);
			}
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			String errorMess = "";
			if (ex.getMessage().contains("ORA-00001")) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
						ex.getMessage(), condDao, currPojo);
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}
			throw new ErrorMessageException(ex, errorMess);
			// if (ex.getMessage().contains("ORA-00001")) {
			// try {
			// // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
			// retInfo =
			// ReturnInfoGenerator.getChkUniqueErrStr(ex.getMessage(),condDao,currPojo);
			// } catch (Exception e) {
			// e.printStackTrace();
			// }
			// } else{
			// retInfo = ReturnInfoGenerator.getOperErrMsg(
			// MvcConstant._CodeSaveErr, menuId);
			// }
			// ex.printStackTrace();
		}
		return retInfo;
	}

	@Override
	public List<SuspendedCond> getCondList(String port) {
		return condDao.getCondList(port);
	}

}
