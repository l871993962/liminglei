package com.yss.ams.base.information.modules.sys.daeelem.service.impl;

import java.util.ArrayList;
import java.util.HashMap;

import com.yss.ams.base.information.modules.sys.daeelem.dao.DaeElemDao;
import com.yss.ams.base.information.modules.sys.daeelem.dao.DaeElemSqlBuilder;
import com.yss.ams.base.information.support.sys.daeelem.service.IDaeElemService;
//import com.yss.dict.daeelem.dao.DaeElemDao;
//import com.yss.dict.daeelem.dao.DaeElemSqlBuilder;
//import com.yss.dict.daeelem.service.IDaeElemService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;
//import com.yss.para.ao.valuelevel.service.impl.DaiService;

public class DaeElemService extends ServiceBus<DaeElemService> implements
		IDaeElemService {

	private DaeElemDao serviceDao = null;

	public DaeElemService() throws Exception {
		serviceDao = new DaeElemDao(DbPoolFactory.getInstance().getPool(), new DaeElemSqlBuilder());
		dao = serviceDao;
	}

	public String getDaeNameByDaeCode(String daeCode) {
         return serviceDao.getDaeNameByCodeDao(daeCode);
	}

	
	/**
	 * 公共信息组件拆分，不能引用其他uco项目类DaiService，因此注释掉，故特此说明
	 */
/*	@Override
	public ArrayList<String> getDaeCodesByCondition(
			HashMap<String, String> paraMap) {
		String planCode = paraMap.get("C_PLAN_CODE");
		ArrayList<String> dataList = new ArrayList<String>();
		if (StringUtil.IsNullOrEmptyT(planCode)) {
			dataList = serviceDao.getDaeCodesByCondition(paraMap);
			String daeCode = paraMap.get("daeCode");
			if (dataList.size() == 0 && daeCode.equals("SEC_VAR")) {
				String parentCode = getParentCodeByCode(paraMap
						.get("daeCodeSub"));
				if (null != parentCode && parentCode.trim().length() != 0) {
					paraMap.put("daeCodeSub", parentCode);
					dataList = serviceDao.getDaeCodesByCondition(paraMap);
				}
			}
		} else {
			try {
				List<String> daiList = Arrays.asList(paraMap.get("daiCode")
						.split(","));
				DaiService daiService = new DaiService();
				for (String daiStr : daiList) {
					HashMap<String, Object> paraMap1 = new HashMap<String, Object>();
					paraMap1.put("C_PLAN_CODE", planCode);
					paraMap1.put("C_DAI_CODE", daiStr);
					paraMap.put("daiCode", daiStr);
					// // 获取明细核算元素
					ArrayList<String> daeList = serviceDao
							.getDaeCodesByCondition(paraMap);
					String daeCode = paraMap.get("daeCode");
					if (daeList.size() == 0 && daeCode.equals("SEC_VAR")) {
						String parentCode = getParentCodeByCode(paraMap
								.get("daeCodeSub"));
						if (null != parentCode
								&& parentCode.trim().length() != 0) {
							paraMap.put("daeCodeSub", parentCode);
							daeList = serviceDao
									.getDaeCodesByCondition(paraMap);
						}
					}

					// // 获取核算项目下的所有核算元素
					ArrayList<String> daeTempList = daiService
							.getDaeList(paraMap1);
					if (0 == daeList.size() || daeList.isEmpty()) {
						for (String str : daeTempList) {
							if (!dataList.contains(str)) {
								dataList.add(str);
							}
						}
					} else {
						for (String str : daeList) {
							if (!dataList.contains(str)) {
								dataList.add(str);
							}
						}
					}
				}
			} catch (Exception e) {
//				e.printStackTrace();
				logger.log("获取核算项目下的所有核算元素时失败!", e);
			}
		}
		return dataList;
	}*/

	public ArrayList<String> getDaeCodesByCondition(HashMap<String, String> paraMap) {
		ArrayList<String> dataList = new ArrayList<String>();
		dataList.addAll(dataList = serviceDao.getDaeCodesByCondition(paraMap));
		return dataList;
	}
	
	/**
	 * 获得证券品种父类code
	 * @param code
	 * @return
	 */
	public String getParentCodeByCode(String code){
		return serviceDao.getParentCodeByCode(code);
	}

	/**
	 * 根据证券品种code判断是否含有子类没有 从而得出数据是否是明细 By Jinghehe
	 * @param code
	 * @return
	 */
	@Override
	public String isDetailByCode(String code) {
		String childCode = serviceDao.getChildCodeByCode(code);
		String flagString = "false";
		if(null == childCode || (null != childCode && childCode.trim().length()==0))
		{
			flagString = "ture";
		}
		return flagString;
	}
	
}
