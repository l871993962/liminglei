package com.yss.uco.elecreco.er.erdata.control.service.impl;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.yss.framework.api.busoperservice.BaseOper;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.sessionmsg.msgmgr.MsgCache;
import com.yss.uco.elecreco.er.erdata.control.service.IErDataControlService;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDao;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDaoSqlBuilder;
import com.yss.uco.elecreco.er.template.dao.DzTemplateDao;
import com.yss.uco.elecreco.er.template.dao.DzTemplateSqlBuilder;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;


public class ErDataControlService extends BaseOper implements
		IErDataControlService {

	private DzTemplateDao templateDao = null;
	public ErDataControlService() {
		templateDao = new DzTemplateDao(DbPoolFactory.getInstance().getPool(), new DzTemplateSqlBuilder());
		dbLog = true;
	}
	private HashMap<String, Object> paraMap = null;
	/**
	 * 产生电子对账数据
	 */
	/**
	 * 此方法调整：将连接按组合创建，并按组合开启线程执行byleeyu20160527<br>
	 * BUG #131638 太平资产系统在生成电子对账时特别慢
	 */
	public String genErDataOper(HashMap<String, Object> paraMap) {
		Connection conn = null;
		//boolean bTrans = false;
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			//conn.setAutoCommit(bTrans);
			//bTrans = true;

			// 1. 获取前台传过来的参数
			String[] ports = ((String) paraMap.get("ARRAY_C_PORT_CODE")).split(",");
			final String dzCode = ((String) paraMap.get("ARRAY_C_DZ_CODE"));
			final Date startDate = YssFun.toDate((String) paraMap.get("D_START_DATE"));
			Date endDate = YssFun.toDate((String) paraMap.get("D_END_DATE"));
			execProcCode = (String) paraMap.get("C_OPER_CODE");
			//serviceDao.setExecProcCode(execProcCode);
			final int days = YssFun.dateDiff(startDate, endDate);
			final String userCode = ContextFactory.getContext().getUserCode();
			// edit by liuxiang 2015/2/16 STORY #20392 华泰证券：电子对账可自定义发送估值表中的列
			final HashMap<String, DzTemplate> map = templateDao.getTemplateByTypeCodeAndPortCodes(dzCode, (String) paraMap.get("ARRAY_C_PORT_CODE"),conn);
//			DbFun.releaseConnection(conn);
			List<Future<BEN_RECORD>> futureList = new ArrayList<Future<BEN_RECORD>>();
			
			// 2. 循环组合执行划款指令生成操作
//			for (final String port : ports) {
//				Runnable runableDzdz = new Runnable() {
//					@Override
//					public void run() {
//						ErDataDao serviceDao = null;
//						DzTemplate template = null;
//						try{
//							template = (map != null && map.containsKey(port)) ? map.get(port) : null;
//							serviceDao = new ErDataDao(DbPoolFactory.getInstance().getPool(), new ErDataDaoSqlBuilder());
//							// 2.1 循环日期，生成划款指令
//							for (int i = 0; i <= days; i++) {
//								Date currentDate = YssFun.addDay(startDate, i);
//								serviceDao.setExecProcCode(execProcCode);
//								serviceDao.init(port, dzCode, currentDate, template, userCode);
//								serviceDao.doOper();
//							}
//						}catch(Exception ex){
//							logger.error(ex.getMessage(), ex);
//						}finally{
//							serviceDao = null;
//							template = null;
//						}
//					}
//				};
//				Thread tDzdz = new Thread(runableDzdz);
//				tDzdz.start();
//			}
			//wlx 2016-7-7 BUG133989电子对账生成余额表太慢 Runnable修改为Callable 是避免在后台没执行完成时前台获取日志线程的线程就停止
			for (final String port : ports) {
				DzTemplate template = (map != null && map.containsKey(port)) ? map.get(port) : null;
				for (int i = 0; i <= days; i++) {
					Date currentDate = YssFun.addDay(startDate, i);
					GeneDataTask geneDataTask = new GeneDataTask();
					geneDataTask.init(template, port, currentDate, dzCode, userCode);
					futureList.add(executorService.submit(geneDataTask));
				}
			}
			for (Future<BEN_RECORD> future : futureList) {
				BEN_RECORD bRecord = future.get();
				if(bRecord != null){
					this.listRecord.add(bRecord);
				}
			}
			//conn.commit();
			//conn.setAutoCommit(bTrans);
			//bTrans = false;
			return YssCons.YSS_DBUPDATE_SUCCESS;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return YssCons.YSS_DBUPDATE_FAIL;
		} finally {
			DbFun.releaseConnection(conn);
			executorService.shutdown();
		}
	}

	class GeneDataTask implements Callable<BEN_RECORD>{

		private DzTemplate template = null;
		private String port = "";
		private Date currentDate = null;
		private String dzCode = "";
		private String userCode = "";
		public void init(DzTemplate template, String port, Date currentDate, String dzCode, String userCode) {
			this.template = template;
			this.port = port;
			this.currentDate = currentDate;
			this.dzCode = dzCode;
			this.userCode = userCode;
		}
		@Override
		public BEN_RECORD call() throws Exception {
			ErDataDao serviceDao = null;
			BEN_RECORD ben_RECORD = null;
			try{
				serviceDao = new ErDataDao(DbPoolFactory.getInstance().getPool(), new ErDataDaoSqlBuilder());
				serviceDao.setExecProcCode(execProcCode);
				serviceDao.init(port, dzCode, currentDate, template, userCode);
				ben_RECORD = serviceDao.doOper();
			}catch(Exception ex){
				logger.error(ex.getMessage(), ex);
			}finally{
				serviceDao = null;
				template = null;
			}
			return ben_RECORD;
		}
	}

	@Override
	public String doBusOper(HashMap<String, Object> hmData)
			throws ServiceException {
		String retInfo = "";
		retInfo = genErDataOper(hmData);
		return retInfo;
	}

	@Override
	public void init(Object... args) throws ServiceException {
		// TODO Auto-generated method stub

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
	public void setMsgCache(MsgCache cache) {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(HashMap<String, Object> paraMap) {
		this.paraMap = paraMap;
	}

	@Override
	public List<BizItem> getBizItems() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BizItem> getRootBizItems() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	/**
	 * //BUG19529821.6版本回归测试bug 1.产生电子对账页面业务历史日志查询为空
	 */
	public Entry<String, List<BEN_RECORD>> execute() throws Exception {
		doBusOper(this.paraMap);
		Map<String, List<BEN_RECORD>> map = new HashMap<String, List<BEN_RECORD>>();
		map.put("", this.listRecord);
		return (Map.Entry<String, List<BEN_RECORD>>)map.entrySet().toArray()[0];
	}
	
}
