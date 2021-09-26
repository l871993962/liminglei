package com.yss.ams.sec.information.modules.sv.base.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseQhbzjDao;
import com.yss.ams.sec.information.modules.sv.base.dao.SecBaseQhbzjSqlBuilder;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBaseQhbzj;
import com.yss.ams.sec.information.support.modules.sv.base.service.ISecBaseQhbzjService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

/**
 * 期货保证金  普通服务类
 * @author shiliang
 *资讯信息拆分	STORY #42948 资讯信息管理组件化拆分
 */
public class SecBaseQhbzjService extends ServiceBus<SecBaseService> implements ISecBaseQhbzjService{

	private SecBaseQhbzjDao serviceDao = null;
	
	public SecBaseQhbzjService() throws Exception{
		serviceDao = new SecBaseQhbzjDao(YssDbPoolFactory.getInstance().getDbPool(SecInfoActivator.class), new SecBaseQhbzjSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 根据前台条件查询期货保证金信息
	 * @param paraMap
	 * @return queryRes
	 */
	public QueryRes querybzj(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
//		List<BasePojo> dataList = new ArrayList<BasePojo>();
		menuId  = "sv_qhbzj";
		try {
			
			if(paraMap.containsKey("C_TYPE"))
			{
				menuId = paraMap.get("C_TYPE").toString();
				paraMap.remove("C_TYPE");
			}
			
			queryRes = super.queryByCondition(paraMap);
//			fillResultObject(queryRes, dataList, 0, null, menuId);
		
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券信息功能模块：查询期货保证金信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 根据前台传的pojolist插入期货保证金数据
	 * @param pojoList
	 * @return returnStr
	 */
	@Override
	public String insert(List<BasePojo> pojoList) {
		String returnStr = "";
		// 1.先删除原来的数据
		String c_sec_code = "";
		String id = "";
		/*for(BasePojo pojo : pojoList){
			SecBaseQhbzj p = (SecBaseQhbzj) pojo;
			c_sec_code = p.getC_SEC_CODE();
			id = p.getId();
			break;
		}*/
		SecBaseQhbzj p = (SecBaseQhbzj)pojoList.get(0);
		c_sec_code = p.getC_SEC_CODE();
		id = p.getId();
		
		serviceDao.deleteBySecCode(c_sec_code);
		// 2.将新数据保存
		if(!"-1".equals(id)){
			returnStr = super.insert(pojoList); 
		}else{
			returnStr = ReturnInfoGenerator.getSaveOKStr(menuId, new ArrayList<String>());
		}
		return returnStr; 
	}

	

	/**
	 * 条件查询
	 * 
	 * @param paraMap
	 *            参数集合
	 * 
	 * @return 查询结果对象
	 */
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		menuId  = "sv_qhbzj";
		try {
			if(paraMap.containsKey("C_TYPE"))
			{
				menuId = paraMap.get("C_TYPE").toString();
				paraMap.remove("C_TYPE");
			}
			
			queryRes = super.queryByCondition(paraMap);
		
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("证券信息功能模块：查询期货保证金信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

}
