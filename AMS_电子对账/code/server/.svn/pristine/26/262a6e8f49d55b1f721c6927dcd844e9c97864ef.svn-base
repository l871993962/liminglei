package com.yss.uco.elecreco.er.reverse.manager.result.service.impl;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.uco.elecreco.er.reverse.manager.result.dao.ReveResultDao;
import com.yss.uco.elecreco.er.reverse.manager.result.dao.ReveResultSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.result.service.IReveResultService;

public class ReveResultService extends ServiceBus<ReveResultService> implements IReveResultService {

	private ReveResultDao serviceDao = null;
	public ReveResultService() throws Exception {
		serviceDao = new ReveResultDao(DbPoolFactory.getInstance().getPool(),new ReveResultSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		String fileType = "";
		Class<?> clazz;
		int recCount = 0;
		List<BasePojo> dataList = null;
		try {
			fileType = String.valueOf(paraMap.get("C_FILE_TYPE"));
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = getDefaultPageInation();
			}
			dataList = query(paraMap, page, clazz);
			String dzMenu = getMenuIdByFileType(fileType);
			fillResultObject(queryRes, dataList, recCount, page, dzMenu);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		//前台统一用默认的
		queryRes.setMenuId(menuId);
		return queryRes;
	}
	/**
	 * 根据对账类型获取对应的menuId
	 * @param fileType
	 * @return
	 */
	public String getMenuIdByFileType(String fileType)
	{
		if("1011".equalsIgnoreCase(fileType)){
			return this.menuId;
		}
		if("1001".equalsIgnoreCase(fileType)){
			return "reveDzResultDetail_YE";
		}
		return this.menuId;
		
	}
}