package com.yss.uco.elecreco.er.task.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

import com.yss.framework.api.bundle.BundleContextWrapper;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SysFun;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.dataservice.IFunDataService;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.thread.DzdzMgr;
import com.yss.uco.elecreco.activator.ErActivator;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDao;
import com.yss.uco.elecreco.er.erdata.dao.ErDataDaoSqlBuilder;
import com.yss.uco.elecreco.er.template.dao.DzTemplateDao;
import com.yss.uco.elecreco.er.template.dao.DzTemplateSqlBuilder;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.dzdz.common.ErConfigUtil;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;
import com.yss.uco.elecreco.support.service.IElecTaskService;
import com.yss.uco.elecreco.support.service.IErBbInfoService;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

public class ElecTaskService implements IElecTaskService {
	private String menuId = "elecGene";
	private HashMap<String, Object> paramMap = null; // 原始的参数
	private List<BEN_RECORD> recordLst = new ArrayList<BEN_RECORD>();
	private String executeId = ""; // 执行的ID号
	private ErDataDao serviceDao = null;
	private DzTemplateDao templateDao = null;
	
	private Logger logger = LogManager.getLogger(getClass());
	
    /**Start 20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看*/
    //本次操作关联的调度方案的执行编号
	private String c_Dispatch_ID = " ";
	//功能代码
	private String c_Fun_Code = " ";
	//方案代码
	private String c_PLAN_CODE = " ";
    /**End 20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看*/
	private String userCode = "";
	// key 为executeId+时间戳 value为业务主键
	public ElecTaskService() {
		serviceDao = new ErDataDao(DbPoolFactory.getInstance().getPool(),
				new ErDataDaoSqlBuilder());
		templateDao = new DzTemplateDao(DbPoolFactory.getInstance().getPool(),
				new DzTemplateSqlBuilder());
	}

	@Override
	public void init(Object... args) throws ServiceException {
		// TODO Auto-generated method stub
	}

	@Override
	public String doBusOper(HashMap<String, Object> paraMap)
			throws ServiceException {
		Connection conn = null;
		ExecutorService executorService = null;
		boolean bTrans = false;
		String sendResult = YssCons.YSS_DBUPDATE_SUCCESS;
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;

			// 1. 获取前台传过来的参数
			String[] ports = ((String) paraMap.get("ARRAY_C_PORT_CODE"))
					.split(",");
			String[] dzCodes = ((String) paraMap.get("ARRAY_C_DZ_CODE"))
					.split(",");
			Date startDate = YssFun
					.toDate((String) paraMap.get("D_START_DATE"));
			Date endDate = YssFun.toDate((String) paraMap.get("D_END_DATE"));
			userCode = ContextFactory.getContext().getUserCode();
			if(StringUtil.IsNullOrEmpty(userCode)){
				userCode = "SYS";
			}
			executeId = (String) paraMap.get("C_OPER_CODE");
			serviceDao.setExecProcCode(executeId);
			
			
			if(paraMap.get("C_OPER_CODE")!=null){
				c_Dispatch_ID = (String) paraMap.get("C_OPER_CODE");
			}
			if(paraMap.get("C_DISPATCH_ID")!=null){
				c_Dispatch_ID = (String) paraMap.get("C_DISPATCH_ID");
			}
			c_PLAN_CODE = paraMap.get("C_IDF_CODE") == null ? " " : (String) paraMap.get("C_IDF_CODE");
			c_Fun_Code = paraMap.get("C_FUN_CODE") == null ? " " : (String) paraMap.get("C_FUN_CODE");
			int days = YssFun.dateDiff(startDate, endDate);

			int dayCount = days > 0 ? days : 1;
			int portCount = (ports == null || ports.length == 0) ? 1 : ports.length;
			int dzCount = (dzCodes == null || dzCodes.length == 0) ? 1 : dzCodes.length;
			int count = portCount * dzCount * dayCount;
			count = count > 5 ? 5 : count;
			executorService = Executors.newFixedThreadPool(count);
			
			List<Future<BEN_RECORD>> futureList = new ArrayList<Future<BEN_RECORD>>();
			String operType = null; // 为空时代表生成并发送；不为空时为生成或发送
			if(paraMap.get("OPER_TYPE") != null){
				operType = paraMap.get("OPER_TYPE").toString();
			}
//			// 2. 循环组合执行划款指令生成操作
//			for (String port : ports) {
//				// edit by liuxiang 2015/2/16 STORY #20392 华泰证券：电子对账可自定义发送估值表中的列
//				// 2.1循环对账类型， 执行产生对账数据
//				for (String dzcode : dzCodes) {
//					DzTemplate template = templateDao
//							.getTemplateByTypeCodeAndPortCode(dzcode, port);
//					// 2.1.1 循环日期，生成划款指令
//					for (int i = 0; i <= days; i++) {
//						Date currentDate = YssFun.addDay(startDate, i);
//						// 2.1.1.1.1
//						// 开始执行生成电子对账操作
//						serviceDao.init(port, dzcode, currentDate, template,
//								conn);
//						serviceDao.deleteByStatus();
//						BEN_RECORD ben_Record = serviceDao.doOper();
//						ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
//						ben_Record.setC_Fun_Code(c_Fun_Code);
//						if (ben_Record.getC_Doing_Type() != DoingType.Success) {
//							serviceDao.getSender().write(executeId, ben_Record);
//						} else {
//							String sn = serviceDao.getSn();
//							ben_Record.setC_Doing_Type(DoingType.Doing);
//							ben_Record
//									.appendDetailMes("***********发送电子对账数据开始***************");
//							// /生成成功 发送电子对账数据
//							serviceDao.getSender().write(executeId, ben_Record);
//							ben_Record.setC_Report_Code(sn);
//							recordLst.add(ben_Record);
//						}
//					}
//				}
//			}
			//STORY #35703 估值表自检以及自动生成发送电子对账
			if(operType == null || "GENE".equalsIgnoreCase(operType)){ //生成电子对账数据
				for (String port : ports) {
					for (String dzCode : dzCodes) {
						if("elecGene".equalsIgnoreCase(dzCode)){//任务调度时项目多出一条elecGene
							continue;
						}
						DzTemplate template = templateDao.getTemplateByTypeCodeAndPortCode(dzCode, port);
						for (int i = 0; i <= days; i++) {
							Date currentDate = YssFun.addDay(startDate, i);
							GeneDataTask geneDataTask = new GeneDataTask();
							geneDataTask.init(template, port, currentDate, dzCode, conn);
							futureList.add(executorService.submit(geneDataTask));
						}
					}
				}
				for (Future<BEN_RECORD> future : futureList) {
					try {
						BEN_RECORD ben_Record = future.get();
						if(ben_Record.getC_Fun_Code() == null || ben_Record.getC_Fun_Code().trim().length() == 0){
							ben_Record.setC_Fun_Code(c_Fun_Code);
						}
						ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
						ben_Record.setC_Idf_Code(c_PLAN_CODE);
						recordLst.add(ben_Record);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}

				conn.commit();
				conn.setAutoCommit(bTrans);
				bTrans = false;
			}
			if(operType == null || "SEND".equalsIgnoreCase(operType)){//发送电子对账数据
				sendResult = sendData(paraMap, ports, dzCodes, startDate, endDate, conn, executeId);
				conn.commit();
				conn.setAutoCommit(bTrans);
				bTrans = false;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			sendResult = YssCons.YSS_DBUPDATE_FAIL;
		} finally {
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
			if(executorService != null){
				executorService.shutdown();
			}
		}
		return sendResult;
	}
	/**
	 * STORY #35703 估值表自检以及自动生成发送电子对账
	 * 多线程生成电子对账数据
	 * @author wlx
	 *
	 */
	class GeneDataTask implements Callable<BEN_RECORD>{
		private DzTemplate template = null;
		private String port = "";
		private Date currentDate = null;
		private String dzCode = "";
		public void init(DzTemplate template, String port, Date currentDate, String dzCode, Connection conn) {
			this.template = template;
			this.port = port;
			this.currentDate = currentDate;
			this.dzCode = dzCode;
		}
		@Override
		public BEN_RECORD call() throws Exception {
			ErDataDao serviceDao = null;
			BEN_RECORD ben_Record = null;
			try{
				serviceDao = new ErDataDao(DbPoolFactory.getInstance().getPool(), new ErDataDaoSqlBuilder());
				serviceDao.setExecProcCode(executeId);
				serviceDao.init(port, dzCode, currentDate, template, userCode);
				serviceDao.initExeLog(c_Fun_Code, c_Dispatch_ID, c_PLAN_CODE);
				ben_Record = serviceDao.doOper();
//				if (ben_Record.getC_Doing_Type() == DoingType.Success) {
//					String sn = serviceDao.getSn();
//					ben_Record.setC_Report_Code(sn);
//					recordLst.add(ben_Record);
//				}
			}catch(Exception ex){
				throw ex;
			}finally{
				serviceDao = null;
				template = null;
			}
			return ben_Record;
		}
	}

	/**
	 * STORY #35703 估值表自检以及自动生成发送电子对账
	 * 定时发送电子对账数据
	 * @param paras 
	 * @param ports
	 * @param dzCodes
	 * @param startDate
	 * @param endDate
	 * @param conn
	 * @param executeId
	 * @return
	 */
	private String sendData(HashMap<String, Object> paras, String[] ports, String[] dzCodes, Date startDate, Date endDate, Connection conn, String executeId){
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sendResult = YssCons.YSS_DBUPDATE_SUCCESS;
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		try {
			
			IErBbInfoService erBbInfoService = YssServiceFactory.getInstance().createService(IErBbInfoService.class);
			BundleContextWrapper bundleContext = YssContextFactory.getInstance().getBundleContext(ErActivator.class);
			erBbInfoService.setBundlContext(bundleContext);
			erBbInfoService.setMenuId("dzBbInfo");
			List<BasePojo> basePojos = null;
//			StringBuffer buffer = new StringBuffer();
//			for (int i = 0; i < recordLst.size(); i++) {
//				BEN_RECORD ben_Record = recordLst.get(i);
//				String sn = ben_Record.getC_Report_Code();
//				buffer.append(sn).append(",");
//			}
			for (int i = 0; i < dzCodes.length; i++) {
				if(dzCodes[i].indexOf("_") >=0){
					dzCodes[i]= dzCodes[i].split("_", -1)[1];
				}
			}
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("ARRAY_C_PORT_CODE", org.apache.commons.lang.StringUtils.join(ports, ","));
			paraMap.put("D_TRADE_START", YssFun.formatDate(startDate));
			paraMap.put("D_TRADE_END", YssFun.formatDate(endDate));
			paraMap.put("C_STATE", "ER_SEND");
			paraMap.put("ARRAY_C_DZ_CODE", org.apache.commons.lang.StringUtils.join(dzCodes, ","));
//			if(buffer.toString().length() > 0){
//				paraMap.put("ARRAY_C_SN", buffer.toString());
//			}
			paraMap.put("dataClass", "ErBbInfo");
			if(paras.containsKey("C_RPT_TYPE"))
			{
				paraMap.put("C_RPT_TYPE", paras.get("C_RPT_TYPE"));
			}
			
			QueryRes queryRes = erBbInfoService.queryByCondition(paraMap);
			basePojos = queryRes.getDataList();
			
			List<Future<HashMap<String, Object>>> futureList = new ArrayList<Future<HashMap<String, Object>>>();
			
			StringBuilder sqlBuilder = new StringBuilder();

			sqlBuilder.append("SELECT R1.C_HIGH_TIME, R1.C_INTERVAL\n");
			sqlBuilder.append("  FROM T_D_ER_RELA R1\n");
			sqlBuilder.append("  JOIN (SELECT R.C_RELA_CODE\n" ); 
			sqlBuilder.append("          FROM T_P_AB_PORT_RELA R\n");
			sqlBuilder.append("          JOIN (SELECT C_ASS_CODE, C_PORT_CODE\n"); 
			sqlBuilder.append("                 FROM T_P_AB_PORT\n"); 
			sqlBuilder.append("                WHERE C_ASS_CODE = ?) P\n"); 
			sqlBuilder.append("            ON R.C_PORT_CODE = P.C_PORT_CODE\n");
			sqlBuilder.append("           AND R.C_RELA_TYPE = 'RELA_ORG') P1\n"); 
			sqlBuilder.append("    ON P1.C_RELA_CODE = R1.C_TGH_CODE");
			sqlBuilder.append("    AND R1.C_BUS_TYPE = 'BUSI_DZ'");
			
			HashMap<String, BEN_RECORD> recordMap = new HashMap<String, BEN_RECORD>();
			String userCode = ContextFactory.getContext().getUserCode();
			int size = basePojos.size();
			for(int i= 0; i < size; i++){
				ErBbInfo erBbInfo = (ErBbInfo) basePojos.get(i);
				List<ErBbInfo> dataList = new ArrayList<ErBbInfo>();
				dataList.add(erBbInfo);
				erBbInfoService.sendBbInfo(dataList);
				
				BEN_RECORD ben_Record = new BEN_RECORD(userCode);
				ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
				ben_Record.setC_Fun_Code(c_Fun_Code);
				ben_Record.setC_Report_Code(erBbInfo.getC_SN());
				ben_Record.init(erBbInfo.getC_PORT_CODE(), erBbInfo.getC_FILE_TYPE(), YssFun.toDate(erBbInfo.getD_DATE()));
				ben_Record.setC_Item_Name(FileTypeEnum.getValueByKey(erBbInfo.getC_FILE_TYPE()));
				ben_Record.BeginLog();
				ben_Record.setC_Idf_Code(c_PLAN_CODE);
				recordLst.add(ben_Record);
				serviceDao.getSender().write(executeId, ben_Record);
				if(!DzdzMgr.newInstence().isConnect()){
					ben_Record.EndLog_Fail("伺服器没有连通 ");
					ben_Record.appendDetailMes("伺服器没有连通 ");
					serviceDao.getSender().write(executeId, ben_Record);
					sendResult = YssCons.YSS_DBUPDATE_FAIL;
					continue;
				}
				ben_Record.appendDetailMes("***********发送电子对账数据***************");
				ben_Record.appendDetailMes("开始时间："+ YssFun.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
				recordMap.put(erBbInfo.getC_SN(), ben_Record);
				
				if(this.paramMap != null && this.paramMap.get("AUTOSEND") != null && 
						"1".equalsIgnoreCase(this.paramMap.get("AUTOSEND").toString())){
					/**STORY54581电子对账接收对账结果后发送消息
					 * *
					 * 若是流程自动发送电子对账，并不启动线程实时扫描反馈结果
					 */
					ben_Record.EndLog_Success("电子对账发送成功");
					ben_Record.appendDetailMes("结束时间："+ YssFun.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
					serviceDao.getSender().write(executeId, ben_Record);
				}else{
					long frequency = 5L;//默认5秒钟，再发送一次
					int maxRunTime = 0;
					pst = conn.prepareStatement(sqlBuilder.toString());
					pst.setString(1, erBbInfo.getC_ASS_CODE());
					rs = pst.executeQuery();
					if(rs.next()){
						String runTime = rs.getString(1);
						String preiod = rs.getString(2);
						if(runTime != null && runTime.trim().length() > 0 ){
							maxRunTime = Integer.valueOf(runTime);
						}
						if(preiod != null && preiod.trim().length() > 0 && !"0".equalsIgnoreCase(preiod)){
							frequency = Long.valueOf(preiod);
						}
					}
					logger.debug("maxRunTime:"+ maxRunTime);
					logger.debug("frequency:"+ frequency);
					DbFun.closeResultSetFinal(rs);
					DbFun.closeStatementFinal(pst);
					ErSendTimer sendTimer = new ErSendTimer(frequency * 1000,frequency * 1000,maxRunTime,conn,erBbInfo);
					futureList.add(executorService.submit(sendTimer));
				}
			}
			if(this.paramMap != null && this.paramMap.get("AUTOSEND") != null && 
					"1".equalsIgnoreCase(this.paramMap.get("AUTOSEND").toString())){
				/**STORY54581电子对账接收对账结果后发送消息
				 * *
				 * 若是流程自动发送电子对账，并不启动线程实时扫描反馈结果
				 */
				//wlx BUG20260621.6鹏华基金-统计分析中执行发送电子对账无日志
				if(size == 0){
					int days = YssFun.dateDiff(startDate, endDate);
					for (String port : ports) {
						for (String dzCode : dzCodes) {
							if("elecSend".equalsIgnoreCase(dzCode)){//任务调度时项目多出一条elecSend
								continue;
							}
							for (int i = 0; i <= days; i++) {
								Date currentDate = YssFun.addDay(startDate, i);
								BEN_RECORD ben_Record = new BEN_RECORD(userCode);
								sendResult = YssCons.YSS_DBUPDATE_FAIL;
								ben_Record.setC_Item_Code(dzCode);
								ben_Record.setC_Item_Name(FileTypeEnum.getValueByKey(dzCode));
								ben_Record.setC_Port_Code(port);
								ben_Record.setD_Trade(currentDate);
								ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
								ben_Record.setC_Fun_Code(c_Fun_Code);
								ben_Record.BeginLog();
								serviceDao.getSender().write(executeId, ben_Record);
								ben_Record.appendDetailMes("未查询到该电子对账数据，请确认是否已生成！");
								ben_Record.EndLog_Fail("未查询到该电子对账数据，请确认是否已生成！");					
								ben_Record.setC_Idf_Code(c_PLAN_CODE);
								recordLst.add(ben_Record);
								serviceDao.getSender().write(executeId, ben_Record);	
							}
						}
					}
				}
			}else{
				//wlx BUG20260621.6鹏华基金-统计分析中执行发送电子对账无日志
				if(size == 0){
					int days = YssFun.dateDiff(startDate, endDate);
					for (String port : ports) {
						for (String dzCode : dzCodes) {
							if("elecSend".equalsIgnoreCase(dzCode)){//任务调度时项目多出一条elecSend
								continue;
							}
							for (int i = 0; i <= days; i++) {
								Date currentDate = YssFun.addDay(startDate, i);
								BEN_RECORD ben_Record = new BEN_RECORD(userCode);
								sendResult = YssCons.YSS_DBUPDATE_FAIL;
								ben_Record.setC_Item_Code(dzCode);
								ben_Record.setC_Item_Name(FileTypeEnum.getValueByKey(dzCode));
								ben_Record.setC_Port_Code(port);
								ben_Record.setD_Trade(currentDate);
								ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
								ben_Record.setC_Fun_Code(c_Fun_Code);
								ben_Record.BeginLog();
								serviceDao.getSender().write(executeId, ben_Record);
								ben_Record.appendDetailMes("未查询到该电子对账数据，请确认是否已生成！");
								ben_Record.EndLog_Fail("未查询到该电子对账数据，请确认是否已生成！");					

								ben_Record.setC_Idf_Code(c_PLAN_CODE);
								recordLst.add(ben_Record);
								serviceDao.getSender().write(executeId, ben_Record);	
							}
						}
					}
				}else{
					for (Future<HashMap<String, Object>> future : futureList) {
						HashMap<String, Object> recMap = null;
						try {
							recMap = future.get();
							if(recMap != null){
								String Status = recMap.get("STATUS").toString();
								ErBbInfo erBbInfo = (ErBbInfo) recMap.get("POJO");
								BEN_RECORD bRecord = recordMap.get(erBbInfo.getC_SN());
								if(bRecord != null && YssCons.YSS_DBUPDATE_SUCCESS.equals(Status)){
									bRecord.EndLog_Success("电子对账发送成功");
								}else if(bRecord != null && YssCons.YSS_DBUPDATE_FAIL.equals(Status)){
									sendResult = YssCons.YSS_DBUPDATE_FAIL;
									bRecord.EndLog_Fail("电子对账发送失败:"+ recMap.get("ERRINFO").toString());
								}
								if(bRecord != null){
									bRecord.appendDetailMes("结束时间："+ YssFun.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));	
								}
								serviceDao.getSender().write(executeId, bRecord);
							}
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
					}
				}
			}
		}  catch (Exception e) {
			logger.error(e.getMessage(),e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			executorService.shutdown();
		}
		return sendResult;
	}

	@Override
	public String getMenuId() {
		return menuId;
	}

	@Override
	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public void init(HashMap<String, Object> paraMap) {
		this.paramMap = paraMap;
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
				itemP.setC_Fun_Code("elecGene");
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
			sysFunPojo = funService.getDataByCode("elecGene");
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

	@SuppressWarnings("unchecked")
	@Override
	public Entry<String, List<BEN_RECORD>> execute() throws Exception {
		String result = doBusOper(this.paramMap);
		Map<String, List<BEN_RECORD>> map = new HashMap<String, List<BEN_RECORD>>();
		map.put(result, recordLst);
		return (Map.Entry<String, List<BEN_RECORD>>) map.entrySet().toArray()[0];
	}
	
	/**
	 * 接口增加方法，给调度方案使用，所以要保持原来的逻辑
	 */
	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		return this.getBizItems();
	}

	@Override
	public List<BEN_RECORD> getListRecord() 
	{
		return recordLst;
	}

	@Override
	public List<BEN_RECORD> doRecordConvert(List<BEN_RECORD> orignalRecordList) {
		// TODO Auto-generated method stub
		return null;
	}

}
