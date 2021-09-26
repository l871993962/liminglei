package com.yss.uco.elecreco.er.reverse.compare.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.yss.uco.elecreco.er.reverse.compare.dao.DataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzDataCompareDao;
import com.yss.uco.elecreco.er.reverse.compare.gz.dao.GzDataCompareSqlBuilder;
import com.yss.uco.elecreco.er.reverse.compare.service.DataCompareServiceFactory;
import com.yss.uco.elecreco.er.reverse.compare.service.IDataCompareService;
import com.yss.uco.elecreco.er.reverse.compare.service.IReveDzService;
import com.yss.uco.elecreco.er.reverse.cons.ReveDzCons;
import com.yss.uco.elecreco.er.reverse.manager.info.pojo.ErReveInfo;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;

public class ReveDzService implements IReveDzService {
	
	protected Logger logger = LogManager.getLogger(this.getClass());
	
	public String compareErDataOper(List<ErReveInfo> infos) {
		//Connection conn = null;
		//boolean bTrans = false;
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		try {
			//conn = DbPoolFactory.getInstance().getPool().getConnection();
			

			final String userCode = ContextFactory.getContext().getUserCode();

			List<Future<Map<String, Object>>> futureList = new ArrayList<Future<Map<String, Object>>>();


			for(ErReveInfo info : infos)
			{
				GeneDataTask geneDataTask = new GeneDataTask();
				geneDataTask.init(info, userCode);
				futureList.add(executorService.submit(geneDataTask));
			}
			int suceSize = 0;
			int failSize = 0;
			for (Future<Map<String, Object>> future : futureList) {
				Map<String, Object> map = future.get();
				if(map != null && map.containsKey(ReveDzCons.INFO_HANDLE_STATE))
				{
					if(ReveDzCons.INFO_HS_SUCCESS.equals(String.valueOf(map.get(ReveDzCons.INFO_HANDLE_STATE))))
					{
						suceSize++;
					}else
					{
						failSize++;
					}
				}else
				{
					failSize++;
				}
			}
			return "对账成功："+suceSize+"条,对账失败："+failSize+"条。";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return "对账失败！";
		} finally {
			//DbFun.releaseConnection(conn);
			executorService.shutdown();
		}
	}
	
	class GeneDataTask implements Callable<Map<String, Object>>{

		private ErReveInfo info = null;
		private String userCode = "";
		public void init(ErReveInfo info, String userCode) {
			this.info = info;
			this.userCode = userCode;
		}
		@Override
		public Map<String, Object> call() throws Exception {
			IDataCompareService serviceDao = null;
			Map<String, Object> map = null;
			try{
				serviceDao = DataCompareServiceFactory.createService(info.getC_FILE_TYPE());
				if(serviceDao == null)
				{
					info.setC_DV_HANDLE_STATE(ReveDzCons.INFO_HS_FAIL);
					info.setC_HANDLE_INFO("不支持的对账类型!");
					info.setModifier(userCode);
					info.setModifyDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					info.setAuditState(1);
					DataCompareDao dataCompareDao = new GzDataCompareDao(DbPoolFactory.getInstance().getPool(),new GzDataCompareSqlBuilder());
					dataCompareDao.saveErReveInfo(info);
					map = new HashMap<String, Object>();
					map.put(ReveDzCons.INFO_HANDLE_STATE, ReveDzCons.INFO_HS_FAIL);
					return map;
				}
				serviceDao.init(info, userCode);
				map = serviceDao.doOper();
			}catch(Exception ex){
				logger.error(ex.getMessage(), ex);
			}finally{
				serviceDao = null;
			}
			return map;
		}
	}
}

