package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.yss.ams.api.support.service.gz.IStockApiService;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.sec.information.support.modules.mp.secmkt.service.ISecMktService;
import com.yss.fast.task.support.automatic.AutomaticResultConstants;
import com.yss.fast.task.support.automatic.ParamConstants;
import com.yss.fast.task.support.automatic.pojo.AutoTaskHandleVo;
import com.yss.fast.task.support.automatic.pojo.ProcessInstance;
import com.yss.fast.task.support.automatic.service.IAutoGloabValCheckService;
import com.yss.fast.task.support.automatic.service.IAutomaticPortMapService;
import com.yss.fast.task.support.automatic.service.IAutomaticTaskHandleService;
import com.yss.fast.task.support.automatic.service.IProcessInstanceService;
import com.yss.fast.task.support.task.support.pojo.AutomaticBaseTask;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.common.co.bizlog.BEN_RECORD_DETAIL_TEXT;
import com.yss.framework.api.common.co.bizlog.Status;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.pojo.automatic.ParamProperty;
import com.yss.framework.api.task.xmlPojo.CustomParam;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.executor.LifecycleException;
import com.yss.framework.executor.StandardThreadExecutor;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;
import com.yss.uco.elecreco.automic.param.ParamUtil;
import com.yss.uco.elecreco.automic.util.AutomicUtil;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.service.IDzdzAutomaticParamService;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

/**
 * 
 * @ClassName AutomaticElecResultCheckTask
 * @Description 估值自动化电子对账发送结果检查任务执行类
 * @author wulongxing@ysstech.com
 * @CreateDate 2018年7月6日
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 * @Task	STORY57962新增检查任务节点：净值确认，对帐结果，具体文件到达
 */
public class AutomaticElecResultCheckTask extends AutomaticBaseTask{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 增加日志
	 */
	private static final Logger logger = LogManager.getLogger(AutomaticElecResultCheckTask.class);
	
	private HashMap<String, String> routeParam = new HashMap<String, String>();
	/**
	 * 当检查任务选择“子帐组合”时，检查实例组合结果标识
	 * STORY #63033 中信证券：自动化流程设计工具箱需支持汇集调尾处理 (#2 #1 ) 
	 */
	private String failFlag = "0";
	private static final String ROUTE_PARAM_CODE = "ElecResult";
	private static final String RESULT_SUCCESS = "0";
	private static final String RESULT_FAIL = "1";
	private static final String RESULT_NORESULT = "4";
	private static final String SLIPT_MARK = "<br>";
	private static final String SPACE_TAB = "	";
	private List<String> selectPortList = null;
	private static final String PASS = "0";
	//STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
	private String dzNewResult = ParamDZCons.DZ_TIME_NEW;//最新对账结果依据 ；对账时间最新；资产净值市值最新
	private String forcePass = "0";//强制通过  1勾选强制通过；0未勾选强制通过
	private String gloabVarName = "";//强制通过条件  变量名称
	private String gloabVarOper = "";//强制通过条件  变量操作符
	private String gloabVarValue = "";//强制通过条件  变量值
	private IAutoGloabValCheckService gloabValCheckService = null;
	//BUG274130【华宝基金】自动化流程实例停止了但是检查电子对账的任务一直在后台执行
	private IProcessInstanceService processInstanceService = null;
	private String processId ="";
	//BUG235267【易方达基金】【21.6】自动化流程临时表空间爆满问题   线程池控制线程数量，内存减少轮询次数
	private HashMap<String, BEN_RECORD> dzResultCache = new HashMap<String, BEN_RECORD>();
	public static StandardThreadExecutor TaskExecutor ;
	//TASK #720795 代码开发-自动化优化：增加智能映射方式  baoql 20190827
	private String failFlag5 = "0";
	// 任务开始日期
	private Date beginDate = null;
	
	/**
	 * 超时时间
	 */
	private String overTime = null;
	
	/**
	 * 超时结果
	 * 新增参数“超时/ 强制通过结果”，此参数值为下拉框单选，默认为一致。
	 * 参数值包括：一致、不一致，
	 * 该参数只有在勾选“是否设置超时时间”或勾选“强制跳过”的时候，才有效，
	 * 表示超时通过后强制跳过后节点认为对账是一致还是不一致，用于后续的路由条件判断时使用。
	 */
	private String overOrForceRes = "";
	
	private String frmCheckCount = "";

	static {
		TaskExecutor = new StandardThreadExecutor();
		TaskExecutor.setMaxThreads(50);
		TaskExecutor.setMinSpareThreads(1);
		TaskExecutor.setNamePrefix("AutomaticElecResultCheckTask_TaskExecutor");
		try {
			TaskExecutor.start();
		} catch (LifecycleException e) {
			logger.error("AutomaticElecResultCheckTask_TaskExecutor 启动失败！");
		}
	}
	
    @Override
    public ParamProperty queryParamProperties(CustomParam param, HashMap<String, String> depandVaules) {
       ParamProperty result = new ParamProperty();
       if (param.getCode().equalsIgnoreCase("CheckNoResultPass")) {
               if(depandVaules.containsKey("frmCheckCount") && "0".equals(depandVaules.get("frmCheckCount"))){
                       result.setEnableType("0");
                       result.setVisibaleType("0");
               } else {
                       result.setEnableType("1");
                       result.setVisibaleType("1");
               }
       }
       
       return result;
    }
	
	@SuppressWarnings("unchecked")
	@Override
	public void execute(HashMap<String, Object> params) throws Exception {
		IAutoStateService autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
		gloabValCheckService = YssServiceFactory.getInstance().createService(IAutoGloabValCheckService.class);
		this.processInstanceService = ((IProcessInstanceService)YssServiceFactory.getInstance().createService(IProcessInstanceService.class));
		try {
			String busiResult = "000000";
			//获取组合参数值
			List<String> portCodes = (List<String>) params.get(ParamConstants.Datas.toString());
			//获取选择监听的组合
			selectPortList = (List<String>) params.get("SelectPortInfo");
			List<String> dzTypes = (List<String>) params.get(ParamDZCons.DZ_TYPE);
			String execOperCode = AutomicUtil.getStringParam(params, ParamConstants.EXECUTE_CODE);
			//STORY54412节假日前一工作日自动执行多天做账流程
			String accBeginDate = AutomicUtil.getStringParam(params, ParamConstants.Date);
			beginDate = YssFun.parseDate(accBeginDate);
			String accEndDate = AutomicUtil.getStringParam(params, ParamConstants.EndDate);
			//流程ID
			String processId = AutomicUtil.getStringParam(params, ParamConstants.ProcessInstanceId);
			this.processId = processId;
			//任务ID
			String taskId = AutomicUtil.getStringParam(params, ParamConstants.TaskInstanceId);
			//检查日期 0-业务日期   1-执行日期
			String frmCheckDType = AutomicUtil.getStringParam(params, ParamConstants.frmCheckDType);
			//检查次数 0-多次 1-单次
			frmCheckCount = AutomicUtil.getStringParam(params, ParamConstants.frmCheckCount);
			//检查频率 秒
			String frmCheckFreq = AutomicUtil.getStringParam(params, ParamConstants.frmCheckFreq);
			//检查结果  0-通过  1-不通过
			String frmCheckResult = AutomicUtil.getStringParam(params, ParamConstants.frmCheckResult);
			//wulongxing 20191023 STORY80030【鹏华基金】对账结果检查节点对反馈结果的检查增加无反馈结果的判断 0未勾选；1勾选
			String CheckNoResultPass =  AutomicUtil.getStringParam(params, ParamDZCons.CheckNoResultPass);
			if(StringUtil.IsNullOrEmpty(CheckNoResultPass)){
				CheckNoResultPass = "0";
			}
			if(StringUtil.IsNullOrEmpty(frmCheckResult)){
				frmCheckResult = "0";
			}
			//STORY #60305 【紧急】检查任务、监听任务中的配置的组合需要增加对应关系，每个实例只需执行对应关系组合的检查任务
			//获取组合筛选方式
			String frmCheckPortType = AutomicUtil.getStringParam(params, ParamConstants.frmCheckPortType);
			//STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
			dzNewResult = AutomicUtil.getStringParam(params, "dzNewResult");//最新对账结果依据 ；对账时间最新；资产净值市值最新
			logger.debug("dzNewResult:" + dzNewResult);
			forcePass = AutomicUtil.getStringParam(params, "forcePass");//强制通过  1勾选强制通过；0未勾选强制通过
			logger.debug("forcePass:" + forcePass);
			gloabVarName = AutomicUtil.getStringParam(params, "gloabVarName");//强制通过条件  变量名称
			logger.debug("gloabVarName:" + gloabVarName);
			gloabVarOper = AutomicUtil.getStringParam(params, "gloabVarOper");//强制通过条件  变量操作符
			logger.debug("gloabVarOper:" + gloabVarOper);
			gloabVarValue = AutomicUtil.getStringParam(params, "gloabVarValue");//强制通过条件  变量值
			logger.debug("gloabVarValue:" + gloabVarValue);
			//根据组合筛选方式
			selectPortList = getPortsByFrmCheckPortType(frmCheckPortType, portCodes);
			this.overTime = ParamUtil.queryOverTime(params);
			logger.info("获取到的超时时间：" + overTime);
			this.overOrForceRes = StringUtil.IsNullOrEmptyT(AutomicUtil.getStringParam(params, ParamDZCons.KEY_OVER_FORCE_RES)) ? 
					ParamDZCons.RESULT_SAME : AutomicUtil.getStringParam(params, ParamDZCons.KEY_OVER_FORCE_RES);
			
			if (selectPortList.isEmpty()) {
				 if ("5".equals(frmCheckPortType)) { 
					//STORY #75718 自动化优化：增加智能映射方式   failFlag5不等于0时表示 关联证券行情映射 查询结果为空  add baoql 20190826
					List<BEN_RECORD> lstRecord = new ArrayList<BEN_RECORD>();
					lstRecord.add(getFailRecordZQHQYS(params, beginDate));					
					taskExecuteResult.setListRecord(lstRecord);					
					taskExecuteResult.setBusiExeMsg("对账结果检查完成");
					routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
					taskExecuteResult.setRouteParams(routeParam);
					return;
				}
			}
			StringBuilder dzTypeBuilder = new StringBuilder();   
			for (int i = 0; i < dzTypes.size(); i++) { 
				if(dzTypes.get(i) != null && dzTypes.get(i).length() == 4){//排除掉报表类型，如日报，月报，季报，半年报，年报
					dzTypeBuilder.append(dzTypes.get(i));      
					if (i < dzTypes.size() - 1) {       
						dzTypeBuilder.append(",");    
					} 
				}  
			}  
			if ("0".equalsIgnoreCase(frmCheckDType)) {//业务日期
				
			}else {
				//执行日期
				accBeginDate =  AutomicUtil.getStringParam(params, ParamConstants.TaskSchedulerDate);
			}
			if(frmCheckFreq == null || frmCheckFreq.trim().length() == 0 || frmCheckFreq.equalsIgnoreCase("0")){
				frmCheckFreq = "1";//检查频率未设置则默认为1秒
			}
			boolean isSingleCheck = false;//是否进行单次轮询
			List<BEN_RECORD> ben_RecordList = new ArrayList<BEN_RECORD>();
			String result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;//任务检查结果，通过则任务为绿色，否则任务为红色，然后进入轮询
			String dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;//对账结果，统计组合对应文件类型的对账结果
			String userCode = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();
			HashMap<String, Object> hmData = new HashMap<String, Object>();
			hmData.put("ARRAY_C_PORT_CODE", selectPortList);
			hmData.put("ARRAY_C_DZ_CODE", dzTypes);
			hmData.put("C_START_DATE", accBeginDate);
			hmData.put("C_END_DATE", accEndDate);
			hmData.put("C_OPER_CODE", execOperCode);
			hmData.put("C_DISPATCH_ID", execOperCode);
			hmData.put("C_FUN_CODE", "elecSend");
			hmData.put("OPER_TYPE", "SEND");
			hmData.put("AUTOSEND","1");
			hmData.put("C_PROCESS_ID", processId);
			hmData.put("C_TASK_ID", taskId);
			hmData.put("frmCheckResult", frmCheckResult);
			
			HashMap<String, StringBuilder> msgMap = new HashMap<String, StringBuilder>();
			Date startDate = YssFun.toDate(accBeginDate);
			Date endDate = YssFun.toDate(accEndDate);
			int days = YssFun.dateDiff(startDate, endDate);
			for (int i = 0; i <= days; i++) {
				for (String dztype : dzTypes) {
					if(dztype == null || dztype.length() != 4){
						continue;
					}
					for (String port : selectPortList) {
						Date currentDate = YssFun.addDay(startDate, i);
						HashMap<String, String> mapData = new HashMap<String, String>();
						mapData.put("ARRAY_C_PORT_CODE",port);
						mapData.put("ARRAY_C_DZ_CODE", dztype);
						mapData.put("C_START_DATE", YssFun.formatDate(currentDate));
						mapData.put("C_END_DATE", YssFun.formatDate(currentDate));
						mapData.put("C_OPER_CODE", execOperCode);
						mapData.put("C_DISPATCH_ID", execOperCode);
						mapData.put("C_FUN_CODE", "elecSend");
						mapData.put("OPER_TYPE", "SEND");
						mapData.put("AUTOSEND","1");
						mapData.put("C_PROCESS_ID", processId);
						mapData.put("C_TASK_ID", taskId);
						mapData.put("frmCheckResult", frmCheckResult);
						mapData.put("dzNewResult", dzNewResult);
						//当failFlag不等于0时表示失败
						if (!"0".equalsIgnoreCase(failFlag)) {
							BEN_RECORD ben_RECORD = createRecord(userCode, dztype, mapData, currentDate);
							ben_RECORD.setC_Doing_Type(DoingType.Warn);
							ben_RecordList.add(ben_RECORD);
							dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
							continue;
						}
						//BUG235267【易方达基金】【21.6】自动化流程临时表空间爆满问题 优先从内存减少轮询次数
						List<BEN_RECORD> lstRecord = null;
						if(dzResultCache.containsKey(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate))){
							BEN_RECORD record = dzResultCache.get(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate));
							lstRecord = new ArrayList<BEN_RECORD>();
							lstRecord.add(record);
							logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":取内存");
						}else{
							lstRecord = autoStateService.getDZResult(mapData);
							logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":重新查询");
							////BUG274694【华宝基金】对账结果检查新版本目前节点失败且无日志
							if(lstRecord != null && lstRecord.size() > 0){
								logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":状态为:" + lstRecord.get(0).getC_Doing_Type());
							}
						}
						String fileTypeName = FileTypeEnum.getValueByKey(dztype);
						if (lstRecord != null && lstRecord.size() > 0) {
							for (BEN_RECORD record : lstRecord) {
								StringBuilder sameBuilder = null;
								StringBuilder diffBuilder = null;
								StringBuilder forcePassBuilder = null;
								BEN_RECORD ben_RECORD = this.createRecord(userCode, record.getC_Item_Code(), mapData, currentDate);
								ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
								String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
								String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
								String forcekey = YssFun.formatDate(currentDate) + "_" + dztype + "_2";
								if(msgMap.containsKey(samekey)){
									sameBuilder = msgMap.get(samekey);
								}else{
									sameBuilder = new StringBuilder();
									msgMap.put(samekey, sameBuilder);
								}
								if(msgMap.containsKey(diffkey)){
									diffBuilder = msgMap.get(diffkey);
								}else{
									diffBuilder = new StringBuilder();
									msgMap.put(diffkey, diffBuilder);
								}
								if(msgMap.containsKey(forcekey)){
									forcePassBuilder = msgMap.get(forcekey);
								}else{
									forcePassBuilder = new StringBuilder();
									msgMap.put(forcekey, forcePassBuilder);
								}
								
								if(PASS.equalsIgnoreCase(frmCheckResult)){//只查询通过的
									if(record.getC_Doing_Type() == DoingType.Success){//对账一致
										sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
										}
										if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
										}
										//对账一致的放入内存中，下次轮询则不再从数据库再取一遍
										dzResultCache.put(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate), record);
										ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
									}else if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致
										diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
										dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
										ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
									}
									else{
										if("1".equalsIgnoreCase(CheckNoResultPass)){
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											ben_RECORD.setC_Doing_Type(DoingType.NoResult);
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										}
										else if(checkForcePass(processId, taskId)){
											forcePassBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
										}else {
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
											if("1".equalsIgnoreCase(frmCheckCount)){//如果是单次检查,则设置为轮询多次，并轮询间隔设置为1秒
												isSingleCheck = true;
												frmCheckFreq = "1";
											}
										}
									}
								}else{
									if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致
										diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
										}
										dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
										ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
									}else if(record.getC_Doing_Type() == DoingType.Success){//对账一致
										sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
										if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
										}
										//对账一致的放入内存中，下次轮询则不再从数据库再取一遍
										dzResultCache.put(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate), record);
										ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
									}
									else{
										if("1".equalsIgnoreCase(CheckNoResultPass)){
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											ben_RECORD.setC_Doing_Type(DoingType.NoResult);
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
										}else if(checkForcePass(processId, taskId)){
											forcePassBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
										}else {
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
											if("1".equalsIgnoreCase(frmCheckCount)){//如果是单次检查,则设置为轮询多次，并轮询间隔设置为1秒
												isSingleCheck = true;
												frmCheckFreq = "1";
											}
										}
									}
								}
								
								ben_RecordList.add(ben_RECORD);
							}
						}else {
							//20181016 wlx STORY63013对帐检查节点：接收所有结果，并且让用户选择是否通过。
							//没有结果，任务结点为进行中，检查结果都为失败
							result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
							dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
							if("1".equalsIgnoreCase(frmCheckCount)){//如果是单次检查,则设置为轮询多次，并轮询间隔设置为1秒
								isSingleCheck = true;
								frmCheckFreq = "1";
							}
							StringBuilder sameBuilder = null;
							StringBuilder diffBuilder = null;
							BEN_RECORD ben_RECORD = this.createRecord(userCode, dztype, mapData, currentDate);
							if("1".equalsIgnoreCase(CheckNoResultPass)){
								ben_RECORD.setC_Doing_Type(DoingType.NoResult);
							}else{
								ben_RECORD.setC_Doing_Type(DoingType.Fail);
							}
							ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
							String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
							String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
							if(msgMap.containsKey(samekey)){
								sameBuilder = msgMap.get(samekey);
							}else{
								sameBuilder = new StringBuilder();
								msgMap.put(samekey, sameBuilder);
							}
							if(msgMap.containsKey(diffkey)){
								diffBuilder = msgMap.get(diffkey);
							}else{
								diffBuilder = new StringBuilder();
								msgMap.put(diffkey, diffBuilder);
							}
//							if(PASS.equals(frmCheckResult)){
//								if (ben_RECORD.getC_Doing_Type() == DoingType.Success) {
//									sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//									if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//									}
//								}else{
									diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
									result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//								}
//							}else {
//								if (ben_RECORD.getC_Doing_Type() != DoingType.Success) {
//									diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//									if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//									}
//								}else{
//									sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//									result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//								}
//							}
							ben_RecordList.add(ben_RECORD);
						}
					}
				}
			}
			
			if(selectPortList == null || selectPortList.isEmpty()) {
				busiResult = "100000";
				StringBuilder sameBuilder = new StringBuilder();
				StringBuilder diffBuilder = new StringBuilder();
				BEN_RECORD ben_RECORD = new BEN_RECORD(userCode);
				ben_RECORD.setC_Fun_Code("automaticElecResultCheckTask");
				ben_RECORD.init(" ", " ",  startDate);
				ben_RECORD.setC_Idf_Code(" ");
				ben_RECORD.setC_Dispatch_ID(execOperCode);
				ben_RECORD.setProcessInstanceId(processId);
				ben_RECORD.setTaskInstanceId(taskId);
				ben_RECORD.BeginLog("");
				ben_RECORD.setC_Doing_Type(DoingType.Fail);
				ben_RECORD.setC_Item_Name("检查对账情况-无组合");
				result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
				dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
				String samekey = YssFun.formatDate(startDate) + "_ _0";
				String diffkey = YssFun.formatDate(startDate) + "_ _1";
				if(msgMap.containsKey(samekey)){
					sameBuilder = msgMap.get(samekey);
				}else{
					sameBuilder = new StringBuilder();
					msgMap.put(samekey, sameBuilder);
				}
				if(msgMap.containsKey(diffkey)){
					diffBuilder = msgMap.get(diffkey);
				}else{
					diffBuilder = new StringBuilder();
					msgMap.put(diffkey, diffBuilder);
				}
//				if(ben_RECORD.getC_Doing_Type() == DoingType.Success){
					diffBuilder.append(SPACE_TAB).append("未能获取到组合信息，请检查配置！").append(SLIPT_MARK);
					result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//				}else {
//					sameBuilder.append(SPACE_TAB).append("未能获取到组合信息，请检查配置！").append(SLIPT_MARK);
//					result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//				}
				ben_RecordList.add(ben_RECORD);
			}
			
			boolean isNoRecord = false;
			StringBuilder resultMsg = null;
			StringBuilder sameBuilder = null;
			StringBuilder diffBuilder = null;
			StringBuilder forcePassBuilder = null;
			for (BEN_RECORD ben_RECORD : ben_RecordList) {
				BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("对账结果检查", null, true);
				resultMsg = new StringBuilder();
				sameBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_0");
				diffBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_1");
				forcePassBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_2");
				if(sameBuilder == null || sameBuilder.length() == 0){
					sameBuilder = new StringBuilder();
					sameBuilder.append(SPACE_TAB).append("无");
				}
				if(diffBuilder == null || diffBuilder.length() == 0){
					diffBuilder = new StringBuilder();
					diffBuilder.append(SPACE_TAB).append("无");
				}
				if(forcePassBuilder == null || forcePassBuilder.length() == 0){
					forcePassBuilder = new StringBuilder();
				}
				
				if((!DoingType.NoResult.equals(ben_RECORD.getC_Doing_Type())) && dzState.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
					isNoRecord = false;
					if(forcePassBuilder.length() > 0){
						resultMsg.append("对账结果检查为：强制通过！");
					}else {
						resultMsg.append("对账结果检查为：通过！");
					}
				}else if(DoingType.NoResult.equals(ben_RECORD.getC_Doing_Type()) && "1".equalsIgnoreCase(CheckNoResultPass)){//无结果允许通过
					isNoRecord = true;
					dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
					result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
				}else{
					isNoRecord = false;
					resultMsg.append("对账结果检查为：不通过！");
				}
				
				if ("1".equalsIgnoreCase(failFlag)) {
					String portCode = selectPortList.get(0);
					resultMsg.append("实例组合").append(portCode).append("的“组合级别”不为“组合层”").append(SLIPT_MARK);
				}
				else if ("2".equalsIgnoreCase(failFlag)) {
					String portCode = selectPortList.get(0);
					resultMsg.append("找不到实例组合").append(portCode).append("对应的单元层产品").append(SLIPT_MARK);
				}
				else if ("8".equals(frmCheckPortType)) {
					String portCode = (null == selectPortList || selectPortList.size() <= 0)  ? portCodes.get(0) : selectPortList.get(0);
					if ("3".equals(failFlag)) {
						resultMsg.append("找不到实例组合").append(portCode).append("对应的子账资金层组合！").append(SLIPT_MARK);
					}
					else if ("1".equals(failFlag)) {
						resultMsg.append("实例组合").append(portCode).append("的“组合级别”不为“组合层”").append(SLIPT_MARK);
					}
					else {
						resultMsg.append("实例组合").append(portCode).append("无检查组合对象！").append(SLIPT_MARK);
					}
				}
				else if ("9".equals(frmCheckPortType)) {
					String portCode = (null == selectPortList || selectPortList.size() <= 0)  ? portCodes.get(0) : selectPortList.get(0);
					if ("4".equals(failFlag)) {
						resultMsg.append("找不到实例组合").append(portCode).append("对应的母账组合！").append(SLIPT_MARK);
					}
					else {
						resultMsg.append("实例组合").append(portCode).append("无检查组合对象！").append(SLIPT_MARK);
					}
				}
				else {
					resultMsg.append("当前任务检查次数为 ：").append("0".equalsIgnoreCase(frmCheckCount)?"多次":"单次").append("! <br> ");
					resultMsg.append("检测到检查结果为通过的数据组合:").append(SLIPT_MARK).append(sameBuilder.toString()).append(SLIPT_MARK);
					resultMsg.append("检测到检查结果为不通过的数据组合:").append(SLIPT_MARK).append(diffBuilder.toString()).append(SLIPT_MARK);
					if("1".equalsIgnoreCase(forcePass)){
						resultMsg.append("强制通过条件显示设置的变量条件:").append(gloabVarName).append(gloabVarOper).append(gloabVarValue).append(SLIPT_MARK);
						resultMsg.append("检测到检查结果为强制通过的数据组合:").append(SLIPT_MARK).append(forcePassBuilder.toString().length()==0?"无":forcePassBuilder.toString()).append(SLIPT_MARK);
					}
				}
				
				arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("开始执行对账结果检查", null, false));
				arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(resultMsg.toString(), null, false));
				ben_RECORD.addDetailParent(arugTitle);
				if(ben_RECORD.getC_Doing_Type() == DoingType.Success){
					ben_RECORD.EndLog_Success("对账结果检查完成");
					ben_RECORD.setC_Doing_Type(DoingType.Approve);
				} else if (ben_RECORD.getC_Doing_Type() == DoingType.ForcePass){
					ben_RECORD.EndLog_Success("对账结果强制通过");
					ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
				} else if(ben_RECORD.getC_Doing_Type() == DoingType.NoResult){
					ben_RECORD.EndLog_Success("无对账结果");
					ben_RECORD.setC_Doing_Type(DoingType.NoResult);
				} else {
					ben_RECORD.EndLog_Fail("对账结果检查完成");
					if(selectPortList == null || selectPortList.isEmpty()){
						ben_RECORD.setC_Doing_Type(DoingType.Warn);
					}else{
						ben_RECORD.setC_Doing_Type(DoingType.Deny);
					}
				}
			}
			
			taskExecuteResult.setWait(false);
			
			if("0".equalsIgnoreCase(frmCheckCount) && selectPortList != null && selectPortList.size() > 0){//多次检查
				if(!result.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){//第一次检查已成功
					ben_RecordList.clear();//如果需要多次检查，并且第一次检查结果不通过，就不需要反馈日志。
					taskExecuteResult.setWait(true);
					SynCheckResultThread synCheckThread = new SynCheckResultThread(frmCheckFreq,hmData,processId,taskId);

					TaskExecutor.execute(synCheckThread);
				}
			}
			if("1".equalsIgnoreCase(frmCheckCount) && isSingleCheck && selectPortList != null && selectPortList.size() > 0 && "0".equalsIgnoreCase(CheckNoResultPass)){//单次检查不通过，继续轮询,并且未勾选“无结果允许通过”
				if(!result.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){//第一次检查已成功
					ben_RecordList.clear();//如果需要多次检查，并且第一次检查结果不通过，就不需要反馈日志。
					taskExecuteResult.setWait(true);
					SynSingleCheckResultThread synCheckThread = new SynSingleCheckResultThread(frmCheckFreq,hmData,processId,taskId);

					TaskExecutor.execute(synCheckThread);
				}
			}
			
			/* 处理设置业务执行结果日志 */
			taskExecuteResult.setListRecord(ben_RecordList);
			
			if(checkForcePass(processId, taskId) || isOverTime())//先判断强置通过或者超时
			{
				//根据设置的强制通过结果返回对账结果
				logger.info("根据设置的强制通过或者超时结果返回对账结果：" + getOverOrForceRes());
				if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(getOverOrForceRes())){
					routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
				}else {
					routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
				}
				taskExecuteResult.setBusiExeMsg("对账结果检查成功，强制通过");
				busiResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
			}else if(!isNoRecord && dzState.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
				routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
				taskExecuteResult.setBusiExeMsg("完成对账结果检查");
			}else if(isNoRecord && "1".equalsIgnoreCase(CheckNoResultPass)){//无结果允许通过
				//FAST调整成4
				routeParam.put(ROUTE_PARAM_CODE, RESULT_NORESULT);
				taskExecuteResult.setBusiExeMsg("完成对账结果检查");
			}else {
				routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
				taskExecuteResult.setBusiExeMsg("对账结果检查失败");
			}
			taskExecuteResult.setBusiExeResult(busiResult);
			taskExecuteResult.setRouteParams(routeParam);
			if (!"0".equalsIgnoreCase(failFlag)) {
				taskExecuteResult.setBusiExeResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_WARN_CODE);
			}
		} catch (Exception e) {
			logger.error("执行电子对账发送自动化任务失败:" + e.getMessage(), e);
			throw e;
		} finally {
		}
	}
	
	/**
	 * 是否超时
	 * @param param
	 * @return
	 */
	private boolean isOverTime()
	{
		logger.info("判断是否超超过设置的超时时间：" + overTime);
		if(!StringUtil.IsNullOrEmptyT(overTime))
		{
			return DateUtil.getNow("HH:mm:ss").compareTo(overTime) > 0;
		}
		return false;
	}
	
	/**
	 * add by 2018-07-23 STORY57962新增检查任务节点：净值确认，对帐结果，具体文件到达
	 * 构建执行日志
	 * @param userCode
	 * @param cfgInfo
	 * @param params
	 * @param info
	 * @param status
	 * @param dDate
	 * @param arugTitle
	 * @return
	 * @throws Exception 
	 */
	private BEN_RECORD createRecord(String userCode, String cfgInfo, HashMap<String, String> params, Date dDate) {
		String execOperCode = (String) params.get("C_OPER_CODE");
		String processId = (String) params.get("C_PROCESS_ID");
		String taskId = (String) params.get("C_TASK_ID");
		String portCode =  params.get("ARRAY_C_PORT_CODE");
		BEN_RECORD record = new BEN_RECORD(userCode);
		record.setC_Fun_Code("automaticElecResultCheckTask");
		record.init(portCode, cfgInfo,  dDate);
		record.setC_Idf_Code(" ");
		record.setC_Dispatch_ID(execOperCode);
		record.setProcessInstanceId(processId);
		record.setTaskInstanceId(taskId);
		record.BeginLog("");

		return record;
	}
	
	class SynCheckResultThread extends Thread{
		private String frmCheckFreq = "";
		private HashMap<String, Object>hmData = null;
		private String processId = "";
		private String taskId = "";
		private IAutoStateService autoStateService = null;
		private IAutomaticTaskHandleService handleService = null;
		
		public SynCheckResultThread(String frmCheckFreq, HashMap<String, Object>hmData,String processId, String taskId){
			this.frmCheckFreq = frmCheckFreq;
			this.hmData = hmData;
			this.processId = processId;
			this.taskId = taskId;
			autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
			handleService = YssServiceFactory.getInstance().createService(IAutomaticTaskHandleService.class);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			long checkFreq = Long.parseLong(frmCheckFreq);
			List<String> selectPortList = (List<String>) hmData.get("ARRAY_C_PORT_CODE");
			List<String> dzTypes = (List<String>) hmData.get("ARRAY_C_DZ_CODE");
			String execOperCode = (String) hmData.get("C_OPER_CODE");
			String accBeginDate = (String) hmData.get("C_START_DATE");
			String accEndDate = (String) hmData.get("C_END_DATE");
			String frmCheckResult = (String) hmData.get("frmCheckResult");
			
			String result = "";
			String dzState = "";
			boolean isContinue = true;//是否可以退出轮询
			int count = 0;//设置检查次数为10次，若10次之后还没有获取到结果则先释放线程池，进入线程池排队
			while(isContinue && count < 10 && checkState()){
				count++;
				isContinue = false;
				try {
					TimeUnit.SECONDS.sleep(checkFreq);
				} catch (Exception e) {
					logger.error("检查频率等待出错:" + e.getMessage(), e);
					break;
				}
				result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
				dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
				List<BEN_RECORD> ben_RecordList = new ArrayList<BEN_RECORD>();
				String userCode = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();

				HashMap<String, StringBuilder> msgMap = new HashMap<String, StringBuilder>();
				Date startDate = null;
				try {
					startDate = YssFun.toDate(accBeginDate);
				} catch (YssException ex) {
					logger.error(ex.getMessage(), ex);
					break;
				}
				Date endDate = null;
				try {
					endDate = YssFun.toDate(accEndDate);
				} catch (YssException ex) {
					logger.error(ex.getMessage(), ex);
					break;
				}
				
				int days = YssFun.dateDiff(startDate, endDate);
				for (int i = 0; i <= days; i++) {
					for (String dztype : dzTypes) {
						if(dztype == null || dztype.length() != 4){
							continue;
						}
						for (String port : selectPortList) {
							Date currentDate = YssFun.addDay(startDate, i);
							HashMap<String, String> mapData = new HashMap<String, String>();
							mapData.put("ARRAY_C_PORT_CODE",port);
							mapData.put("ARRAY_C_DZ_CODE", dztype);
							mapData.put("C_START_DATE", YssFun.formatDate(currentDate));
							mapData.put("C_END_DATE", YssFun.formatDate(currentDate));
							mapData.put("C_OPER_CODE", execOperCode);
							mapData.put("C_DISPATCH_ID", execOperCode);
							mapData.put("C_FUN_CODE", "elecSend");
							mapData.put("OPER_TYPE", "SEND");
							mapData.put("AUTOSEND","1");
							mapData.put("C_PROCESS_ID", processId);
							mapData.put("C_TASK_ID", taskId);
							mapData.put("frmCheckResult", frmCheckResult);
							mapData.put("dzNewResult", dzNewResult);

							//BUG235267【易方达基金】【21.6】自动化流程临时表空间爆满问题 优先从内存减少轮询次数
							List<BEN_RECORD> lstRecord = null;
							if(dzResultCache.containsKey(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate))){
								BEN_RECORD record = dzResultCache.get(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate));
								lstRecord = new ArrayList<BEN_RECORD>();
								lstRecord.add(record);
								logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":取内存");
							}else{
								lstRecord = autoStateService.getDZResult(mapData);
								logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":重新查询");
							}
							String fileTypeName = FileTypeEnum.getValueByKey(dztype);
							if (lstRecord != null && lstRecord.size() > 0) {
								for (BEN_RECORD record : lstRecord) {
									StringBuilder sameBuilder = null;
									StringBuilder diffBuilder = null;
									StringBuilder forcePassBuilder = null;
									BEN_RECORD ben_RECORD = createRecord(userCode, record.getC_Item_Code(), mapData, currentDate);
									ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
									String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
									String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
									String forcekey = YssFun.formatDate(currentDate) + "_" + dztype + "_2";
									if(msgMap.containsKey(samekey)){
										sameBuilder = msgMap.get(samekey);
									}else{
										sameBuilder = new StringBuilder();
										msgMap.put(samekey, sameBuilder);
									}
									if(msgMap.containsKey(diffkey)){
										diffBuilder = msgMap.get(diffkey);
									}else{
										diffBuilder = new StringBuilder();
										msgMap.put(diffkey, diffBuilder);
									}
									if(msgMap.containsKey(forcekey)){
										forcePassBuilder = msgMap.get(forcekey);
									}else{
										forcePassBuilder = new StringBuilder();
										msgMap.put(forcekey, forcePassBuilder);
									}
									
									if(PASS.equalsIgnoreCase(frmCheckResult)){//只查询通过的
										if(record.getC_Doing_Type() == DoingType.Success){//对账一致的
											sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											//对账一致的放入内存中，下次轮询则不再从数据库再取一遍
											dzResultCache.put(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate), record);
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}else if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致的
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}else{//其他状态的
											if(checkForcePass(processId, taskId)){
												forcePassBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
													result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
													dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
											}else {
												diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
												isContinue = true;
											}
										}
									}else{
										if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致的
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}else if(record.getC_Doing_Type() == DoingType.Success){//对账一致的
											sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											//对账一致的放入内存中，下次轮询则不再从数据库再取一遍
											dzResultCache.put(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate), record);
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}
										else {//其他状态的
											if(checkForcePass(processId, taskId)){
												forcePassBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
													result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(dzState)){
													dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
											}else {
												diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
												isContinue = true;
											}
										}
									}
									
									ben_RecordList.add(ben_RECORD);
								}
							}else {
								//20181016 wlx STORY63013对帐检查节点：接收所有结果，并且让用户选择是否通过。
								//没有结果，任务结点为进行中，检查结果都为失败
								result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
								dzState = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
								isContinue = true;
								StringBuilder sameBuilder = null;
								StringBuilder diffBuilder = null;
								BEN_RECORD ben_RECORD = createRecord(userCode, dztype, mapData, currentDate);
								ben_RECORD.setC_Doing_Type(DoingType.Fail);
								ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
								String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
								String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
								if(msgMap.containsKey(samekey)){
									sameBuilder = msgMap.get(samekey);
								}else{
									sameBuilder = new StringBuilder();
									msgMap.put(samekey, sameBuilder);
								}
								if(msgMap.containsKey(diffkey)){
									diffBuilder = msgMap.get(diffkey);
								}else{
									diffBuilder = new StringBuilder();
									msgMap.put(diffkey, diffBuilder);
								}
//								if(PASS.equals(frmCheckResult)){
//									if (ben_RECORD.getC_Doing_Type() == DoingType.Success) {
//										sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//										}
//									}else{
										diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//									}
//								}else {
//									if (ben_RECORD.getC_Doing_Type() != DoingType.Success) {
//										diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//										}
//									}else{
//										sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//									}
//								}
								ben_RecordList.add(ben_RECORD);
							}
						}
					}
				}
				
				boolean forcePassRes = checkForcePass(processId, taskId);
				boolean isOver = isOverTime();
				StringBuilder resultMsg = null;
				StringBuilder sameBuilder = null;
				StringBuilder diffBuilder = null;
				StringBuilder forcePassBuilder = null;
				for (BEN_RECORD ben_RECORD : ben_RecordList) {
					BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("对账结果检查", null, true);
					resultMsg = new StringBuilder();
					sameBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_0");
					diffBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_1");
					forcePassBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_2");
					if(sameBuilder == null || sameBuilder.length() == 0){
						sameBuilder = new StringBuilder();
						sameBuilder.append(SPACE_TAB).append("无");
					}
					if(diffBuilder == null || diffBuilder.length() == 0){
						diffBuilder = new StringBuilder();
						diffBuilder.append(SPACE_TAB).append("无");
					}
					if(forcePassBuilder == null || forcePassBuilder.length() == 0){
						forcePassBuilder = new StringBuilder();
					}
					if(dzState.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
						if(forcePassBuilder.length() > 0){
							resultMsg.append("对账结果检查为：强制通过！");
						}else {
							resultMsg.append("对账结果检查为：通过！");
						}
					}else if(isOver)
					{
						resultMsg.append("对账结果检查为：超时通过！");
					}else{
						resultMsg.append("对账结果检查为：不通过！");
					}
					
					resultMsg.append("当前任务检查次数为 ：多次! <br> ");
					resultMsg.append("检测到检查结果为通过的数据组合:").append(SLIPT_MARK).append(sameBuilder.toString()).append(SLIPT_MARK);
					resultMsg.append("检测到检查结果为不通过的数据组合:").append(SLIPT_MARK).append(diffBuilder.toString()).append(SLIPT_MARK);
					if("1".equalsIgnoreCase(forcePass)){
						resultMsg.append("强制通过条件显示设置的变量条件:").append(gloabVarName).append(gloabVarOper).append(gloabVarValue).append(SLIPT_MARK);
						resultMsg.append("检测到检查结果为强制通过的数据组合:").append(SLIPT_MARK).append(forcePassBuilder.toString().length()==0?"无":forcePassBuilder.toString()).append(SLIPT_MARK);
					}
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("开始执行对账结果检查", null, false));
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(resultMsg.toString(), null, false));
					ben_RECORD.addDetailParent(arugTitle);
					if(ben_RECORD.getC_Doing_Type() == DoingType.Success){
						ben_RECORD.EndLog_Success("对账结果检查完成");
						ben_RECORD.setC_Doing_Type(DoingType.Approve);
					} else if (ben_RECORD.getC_Doing_Type() == DoingType.ForcePass){
						ben_RECORD.EndLog_Success("对账结果强制通过");
						ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
					}else if(forcePassRes){
						ben_RECORD.EndLog_Success("对账结果强制通过");
						ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
					}else if (isOver){
						ben_RECORD.EndLog_Success("对账结果超时通过");
						ben_RECORD.setC_Doing_Type(DoingType.Approve);
					} else {
						ben_RECORD.EndLog_Fail("对账结果检查完成");
						ben_RECORD.setC_Doing_Type(DoingType.Deny);
					}
				}
				
				if(!isContinue  || isOver || forcePassRes){
					if(forcePassRes)//先判断强置通过
					{
						//根据设置的强制通过结果返回对账结果
						logger.info("根据设置的强制通过或者超时结果返回对账结果：" + getOverOrForceRes());
						if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(getOverOrForceRes())){
							routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
						}else {
							routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
						}
						result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
					}else  if(dzState == AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE){
						routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
					}else {
						routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
					}
					
					if(isOver)
					{
						logger.info("根据设置的强制通过或者超时结果返回对账结果：" + getOverOrForceRes());
						if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(getOverOrForceRes())){
							routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
						}else {
							routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
						}
						result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
					}
					
					//状态是对账终态时通知
					AutoTaskHandleVo handleVo = new AutoTaskHandleVo();
					handleVo.setProcInstId(processId);
					handleVo.setTaskInstId(taskId);
					handleVo.setListRecord(ben_RecordList);
					handleVo.setBusiExeResult(result);
					handleVo.setBusiExeMsg("完成对账结果检查");
					handleVo.setRouteParams(routeParam);
					handleService.handTask(handleVo);
					break;
				}
			}
			//如果获取10次之后仍未获取到结果，则先让出线程，进去线程池等待
			if(isContinue && checkState()){
				try {
					TimeUnit.SECONDS.sleep(checkFreq);
				} catch (Exception e) {
					logger.error("检查频率等待出错:" + e.getMessage(), e);
				}
				SynCheckResultThread synCheckThread = new SynCheckResultThread(frmCheckFreq,hmData,processId,taskId);

				TaskExecutor.execute(synCheckThread);
			}
		}
	}
	
	public String getOverOrForceRes() {
		return overOrForceRes;
	}
	
	/**
	 * STORY #60305 【紧急】检查任务、监听任务中的配置的组合需要增加对应关系，每个实例只需执行对应关系组合的检查任务
	 * 规则如下：
	 * 1.当任务节点的“组合信息”=“关联组合映射”时，当执行对应的检查任务以及监听任务时，各实例在任务节点运行中，
	 * 需要根据【关联组合映射】，找到实例的组合代码，匹配“源组合字段”，获取对应的“关联组合字段”，传入关联组合
	 * 代码进行检查，即各实例只需要检查设置的关联组合的检查结果或监听结果即可。若未在【关联组合映射】中未找到
	 * 实例对应的“源组合字段”以及“关联组合字段”信息，则默认为对应实例的组合代码；
	 * 2.当任务节点的“组合信息”=“本实例”时，当执行对应的检查任务以及监听任务时，各实例在任务节点运行中，检查的组合代码为实例的组合代码
	 * 3.当任务节点的“组合信息”=“应用范围”时，当执行对应的检查任务以及监听任务时，各实例在任务节点运行中，检查的组合代码“应用范围”选框
	 * 中设置的组合代码。若设置了多个组合代码，即表示每个实例都需要检查这些组合代码的对应结果，节点才可通过。若没有在“应用范围”选框中设置
	 * 组合代码，则默认为实例对应的组合代码
	 * 4.当检查任务选择“子帐组合”时判断实例的组合A的产品基本信息“组合层级”是否为“组合层”，若不是，任务执行状态为警告；若是，检查A对应的单元层产品
	 * @param frmCheckPortType  组合筛选方式
	 * @param portList  组合			
	 * @return 检查组合信息
	 */
	private List<String> getPortsByFrmCheckPortType(String frmCheckPortType,List<String> portList) {
		List<String> resultList =new ArrayList<String>();
		if ("0".equalsIgnoreCase(frmCheckPortType)) {//关联组合映射
			List<String> queryList =new ArrayList<String>();
			IAutomaticPortMapService automaticPortMapService = YssServiceFactory.getInstance().createService(IAutomaticPortMapService.class);
			if(portList != null && !portList.isEmpty()){
				queryList = automaticPortMapService.queryMapPortListByPortCode(portList.get(0));
				//没有设置关联映射组合，默认为实例组合
				if (null == queryList || queryList.size() == 0) {
					queryList = portList ;
				}
			}
			resultList = queryList;
		}
		else if ("1".equalsIgnoreCase(frmCheckPortType)) {
			//获取本实例组合 流程的组合
			if(portList != null){
				resultList = portList;
			}
		}
		else if ("2".equalsIgnoreCase(frmCheckPortType)){
			//获取应用范围的组合  该任务自己的组合
			List<String> setList = selectPortList;
			if (null == setList || setList.size() == 0) {
				if(portList != null){
					setList = portList;
				}
			}
			resultList = setList;
		}else if ("5".equals(frmCheckPortType)) {
			//add baoql 20190826 STORY #75718 自动化优化：增加智能映射方式 
			//当任务节点的“组合信息”=“关联证券行情映射”时,通过portList实例组合参数和业务日期（全局变量）关联T_D_AI_STOCK[库存数据表]获取证券代码,
			//通过证券代码和业务日期关联T_P_AB_PORT_SEC[证券行情映射表]获取组合代码			
			String portCode = portList.get(0);
			List<String> queryList =new ArrayList<String>();
			try {
				IStockApiService stockApiService = YssServiceFactory.getInstance().createService(IStockApiService.class);
				queryList=stockApiService.getStockByPortCode(DbPoolFactory.getInstance().getPool().getConnection(), portCode, beginDate);
				if (null != queryList && queryList.size() > 0) {					 
					ISecMktService secMktService = YssServiceFactory.getInstance().createService(ISecMktService.class);
					List<String> secMktList = secMktService.getPortCodeBySecCodeList(queryList,beginDate);
					if (null!=secMktList&&secMktList.size()>0){
						//2）如果持仓证券在【证券行情映射】中找到对应的数据，且估值表已生成，则该节点结果为通过；找到相应数据，但没生成估值表，则节点结果不通过；
						//在 isGenerateGZB 中判断
						resultList = secMktList ;
					}else{
						//日志详情弹框中显示为：当前组合【XXXXXXX】在【证券行情映射】中找不到对应的映射组合，无投资于本公司的产品，检查直接通过。
						failFlag5="2";						
					}					
				}else{
					//日志详情弹框中显示为：当前组合【XXXXX】没有任何日期的库存数据，请确认!
					failFlag5="1";
				}
			} catch (Exception e) {
				failFlag5="9";
				logger.error("自动化：查询证券行情映射组合代码异常", e);
			}
		}else if ("3".equalsIgnoreCase(frmCheckPortType)) {
			String portCode = portList.get(0);
			Port port = null;
			if(new RestfulConfigServiceImpl().getConfig().isShell()){
				IPortCacheDataService portCacheDataService = YssServiceFactory.getInstance().createService(IPortCacheDataService.class);
				port = portCacheDataService.getCacheByKey(portCode);
			}else{
				PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
				port = portCache.getCacheByKey(portCode);
			}
			if ("PORT_LAYER".equalsIgnoreCase(port.getC_DV_PORT_CODE())) {
				// 获取实例组合的单元层产品
				IPortDataService portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
				List<BasePojo> pojoList = portDataService.getUnitLayerPort(new String[] { portCode });
				for (BasePojo pojo : pojoList) {
					resultList.add(((Port)pojo).getC_PORT_CODE());
				}
				if (resultList.size() == 0) {
					// 找不到实例组合对应的单元层组合
					failFlag = "2";
					resultList.add(portCode);
				}
			}
			else {
				// 实例的组合“组合层级”不为“组合层”
				failFlag = "1";
				resultList.add(portCode);
			}
		//STORY #90284 【富国基金】ETF重新跑账后对应的联接基金自动重新跑 
		// ETF基金对应的联接基金
		} else if ("7".equalsIgnoreCase(frmCheckPortType)) {
			IDzdzAutomaticParamService automaticService = YssServiceFactory.getInstance().createService(IDzdzAutomaticParamService.class);
			Map<String, List<String>> dataMap = automaticService.getLinkPortbyEtfPort(portList, beginDate);
			for (List<String> dataList : dataMap.values()) {
				resultList.addAll(dataList);
			}
		}// 子账资金层组合
		else if ("8".equals(frmCheckPortType)) {
			if (portList.isEmpty()) {
				logger.warn("获取不到实例组合");
				return resultList;
			}
			String portCode = portList.get(0);
			Port port = null;
			if(new RestfulConfigServiceImpl().getConfig().isShell()){
				IPortCacheDataService portCacheDataService = YssServiceFactory.getInstance().createService(IPortCacheDataService.class);
				port = portCacheDataService.getCacheByKey(portCode);
			}else{
				PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
				port = portCache.getCacheByKey(portCode);
			}
			if (port != null && "PORT_LAYER".equals(port.getC_DV_PORT_CODE())) {
				// 获取实例组合的资金层产品
				IPortDataService portDataService = YssServiceFactory.getInstance().createService(IPortDataService.class);
				HashMap<String, Object> map = new HashMap<String, Object>();
				map.put("N_STATE", "1");
				map.put("C_PORT_CODE_P", portCode);
				map.put("C_DV_PROD_STATE_EQU", "PS4");
				map.put("C_DV_PORT_CODE", "CASH_LAYER");
				map.put("D_BUILD", YssFun.formatDate(beginDate));
				List<Port> pojoList = portDataService.getPortInfoList(map);
				for (Port portPojo : pojoList) {
					resultList.add(portPojo.getC_PORT_CODE());
				}
				if (resultList.size() == 0) {
					// 找不到实例组合对应的资金层组合
					failFlag = "3";
					logger.warn("找不到实例组合" + portCode + "对应的资金层组合");
				}
			} else {
				// 实例的组合“组合层级”不为“组合层”
				failFlag = "1";
				logger.warn("实例的组合" + portCode + "的“组合层级”不为“组合层”");
			}
		}
		// 母账组合
		else if ("9".equals(frmCheckPortType)) {
			if (portList.isEmpty()) {
				logger.warn("获取不到实例组合");
				return resultList;
			}
			String portCode = portList.get(0);
			Port port = null;
			if(new RestfulConfigServiceImpl().getConfig().isShell()){
				IPortCacheDataService portCacheDataService = YssServiceFactory.getInstance().createService(IPortCacheDataService.class);
				port = portCacheDataService.getCacheByKey(portCode);
			}else{
				PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
				port = portCache.getCacheByKey(portCode);
			}
			if (port != null && !"".equals(port.getC_PORT_CODE_P().trim())) {
				resultList.add(port.getC_PORT_CODE_P());
			}
			if (resultList.size() == 0) {
				// 找不到实例组合对应的母账组合
				failFlag = "4";
				logger.warn("找不到实例组合" + portCode + "对应的母账组合");
			}
		}
		return resultList;
	}
	class SynSingleCheckResultThread extends Thread{
		private String frmCheckFreq = "";
		private HashMap<String, Object>hmData = null;
		private String processId = "";
		private String taskId = "";
		private IAutoStateService autoStateService = null;
		private IAutomaticTaskHandleService handleService = null;
		
		public SynSingleCheckResultThread(String frmCheckFreq, HashMap<String, Object>hmData,String processId, String taskId){
			this.frmCheckFreq = frmCheckFreq;
			this.hmData = hmData;
			this.processId = processId;
			this.taskId = taskId;
			autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
			handleService = YssServiceFactory.getInstance().createService(IAutomaticTaskHandleService.class);
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public void run() {
			long checkFreq = Long.parseLong(frmCheckFreq);
			List<String> selectPortList = (List<String>) hmData.get("ARRAY_C_PORT_CODE");
			List<String> dzTypes = (List<String>) hmData.get("ARRAY_C_DZ_CODE");
			String execOperCode = (String) hmData.get("C_OPER_CODE");
			String accBeginDate = (String) hmData.get("C_START_DATE");
			String accEndDate = (String) hmData.get("C_END_DATE");
			String frmCheckResult = (String) hmData.get("frmCheckResult");
			
			String result = "";
			String nodeResult = "";
			boolean isContinue = true;//是否可以退出轮询
			int count = 0;//设置检查次数为10次，若10次之后还没有获取到结果则先释放线程池，进入线程池排队
			while(isContinue && count < 10 && checkState()){
				count ++;
				isContinue = false;
				try {
					TimeUnit.SECONDS.sleep(checkFreq);
				} catch (Exception e) {
					logger.error("检查频率等待出错:" + e.getMessage(), e);
					break;
				}
				result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;//流程状态
				nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;//节点状态
				List<BEN_RECORD> ben_RecordList = new ArrayList<BEN_RECORD>();
				String userCode = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();

				HashMap<String, StringBuilder> msgMap = new HashMap<String, StringBuilder>();
				Date startDate = null;
				try {
					startDate = YssFun.toDate(accBeginDate);
				} catch (YssException ex) {
					logger.error(ex.getMessage(), ex);
					break;
				}
				Date endDate = null;
				try {
					endDate = YssFun.toDate(accEndDate);
				} catch (YssException ex) {
					logger.error(ex.getMessage(), ex);
					break;
				}
				
				int days = YssFun.dateDiff(startDate, endDate);
				for (int i = 0; i <= days; i++) {
					for (String dztype : dzTypes) {
						if(dztype == null || dztype.length() != 4){
							continue;
						}
						for (String port : selectPortList) {
							Date currentDate = YssFun.addDay(startDate, i);
							HashMap<String, String> mapData = new HashMap<String, String>();
							mapData.put("ARRAY_C_PORT_CODE",port);
							mapData.put("ARRAY_C_DZ_CODE", dztype);
							mapData.put("C_START_DATE", YssFun.formatDate(currentDate));
							mapData.put("C_END_DATE", YssFun.formatDate(currentDate));
							mapData.put("C_OPER_CODE", execOperCode);
							mapData.put("C_DISPATCH_ID", execOperCode);
							mapData.put("C_FUN_CODE", "elecSend");
							mapData.put("OPER_TYPE", "SEND");
							mapData.put("AUTOSEND","1");
							mapData.put("C_PROCESS_ID", processId);
							mapData.put("C_TASK_ID", taskId);
							mapData.put("frmCheckResult", frmCheckResult);
							mapData.put("dzNewResult", dzNewResult);
							//BUG235267【易方达基金】【21.6】自动化流程临时表空间爆满问题 优先从内存减少轮询次数
							List<BEN_RECORD> lstRecord = null;
							if(dzResultCache.containsKey(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate))){
								BEN_RECORD record = dzResultCache.get(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate));
								lstRecord = new ArrayList<BEN_RECORD>();
								lstRecord.add(record);
								logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":取内存");
							}else{
								lstRecord = autoStateService.getDZResult(mapData);
								logger.debug(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate) + ":重新查询");
							}
							String fileTypeName = FileTypeEnum.getValueByKey(dztype);
							if (lstRecord != null && lstRecord.size() > 0) {
								for (BEN_RECORD record : lstRecord) {
									StringBuilder sameBuilder = null;
									StringBuilder diffBuilder = null;
									StringBuilder forcePassBuilder = null;
									BEN_RECORD ben_RECORD = createRecord(userCode, record.getC_Item_Code(), mapData, currentDate);
									ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
									String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
									String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
									String forcekey = YssFun.formatDate(currentDate) + "_" + dztype + "_2";
									if(msgMap.containsKey(samekey)){
										sameBuilder = msgMap.get(samekey);
									}else{
										sameBuilder = new StringBuilder();
										msgMap.put(samekey, sameBuilder);
									}
									if(msgMap.containsKey(diffkey)){
										diffBuilder = msgMap.get(diffkey);
									}else{
										diffBuilder = new StringBuilder();
										msgMap.put(diffkey, diffBuilder);
									}
									if(msgMap.containsKey(forcekey)){
										forcePassBuilder = msgMap.get(forcekey);
									}else{
										forcePassBuilder = new StringBuilder();
										msgMap.put(forcekey, forcePassBuilder);
									}
//									if(PASS.equals(frmCheckResult)){//只查询通过的
										if(record.getC_Doing_Type() == DoingType.Success){//对账一致的
											sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(nodeResult)){
												nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											}
											//对账一致的放入内存中，下次轮询则不再从数据库再取一遍
											dzResultCache.put(port + "_"+ dztype + "_" + YssFun.formatDate(currentDate), record);
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}else if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致的
											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);
											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
											nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
										}else{//其他状态的
											if(checkForcePass(processId, taskId)){
												forcePassBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(result)){
													result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(nodeResult)){
													nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
												}
												ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
											}else {
												diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
												ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
												isContinue = true;
											}
										}
//									}else{
//										if(record.getC_Doing_Type() == DoingType.Warn){//对账不一致的
//											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
//											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equals(result)){
//												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//											}
//											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equals(nodeResult)){
//												nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//											}
//											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
//										}else if(record.getC_Doing_Type() == DoingType.Success){//对账一致的
//											sameBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
//											if(!AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equals(result)){
//												result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//											}
//											nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
//										}
//										else {//其他状态的
//											diffBuilder.append(SPACE_TAB).append(record.getC_Port_Code()).append("，文件类型：").append(fileTypeName).append(SLIPT_MARK);;
//											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//											nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//											ben_RECORD.setC_Doing_Type(record.getC_Doing_Type());
//											isContinue = true;
//										}
//									}
									
									ben_RecordList.add(ben_RECORD);
								}
							}else {
								//20181016 wlx STORY63013对帐检查节点：接收所有结果，并且让用户选择是否通过。
								//没有结果，任务结点为进行中，检查结果都为失败
								result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
								nodeResult = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
								isContinue = true;
								StringBuilder sameBuilder = null;
								StringBuilder diffBuilder = null;
								BEN_RECORD ben_RECORD = createRecord(userCode, dztype, mapData, currentDate);
								ben_RECORD.setC_Doing_Type(DoingType.Fail);
								ben_RECORD.setC_Item_Name("检查对账情况-" + port + " " + fileTypeName);
								String samekey = YssFun.formatDate(currentDate) + "_" + dztype + "_0";
								String diffkey = YssFun.formatDate(currentDate) + "_" + dztype + "_1";
								if(msgMap.containsKey(samekey)){
									sameBuilder = msgMap.get(samekey);
								}else{
									sameBuilder = new StringBuilder();
									msgMap.put(samekey, sameBuilder);
								}
								if(msgMap.containsKey(diffkey)){
									diffBuilder = msgMap.get(diffkey);
								}else{
									diffBuilder = new StringBuilder();
									msgMap.put(diffkey, diffBuilder);
								}
//								if(PASS.equals(frmCheckResult)){
//									if (ben_RECORD.getC_Doing_Type() == DoingType.Success) {
//										sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//										}
//									}else{
										diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//									}
//								}else {
//									if (ben_RECORD.getC_Doing_Type() != DoingType.Success) {
//										diffBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										if(!result.equals(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE)){
//											result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
//										}
//									}else{
//										sameBuilder.append(SPACE_TAB).append(port).append("，文件类型：").append(fileTypeName).append("，未生成").append(SLIPT_MARK);
//										result = AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE;
//									}
//								}
								ben_RecordList.add(ben_RECORD);
							}
						}
					}
				}
				
				boolean forcePassRes = checkForcePass(processId, taskId);
				boolean isOver = isOverTime();
				StringBuilder resultMsg = null;
				StringBuilder sameBuilder = null;
				StringBuilder diffBuilder = null;
				StringBuilder forcePassBuilder = null;
				for (BEN_RECORD ben_RECORD : ben_RecordList) {
					BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("对账结果检查", null, true);
					resultMsg = new StringBuilder();
					sameBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_0");
					diffBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_1");
					forcePassBuilder = msgMap.get(YssFun.formatDate(ben_RECORD.getD_Trade()) + "_" + ben_RECORD.getC_Item_Code() + "_2");
					if(sameBuilder == null || sameBuilder.length() == 0){
						sameBuilder = new StringBuilder();
						sameBuilder.append(SPACE_TAB).append("无");
					}
					if(diffBuilder == null || diffBuilder.length() == 0){
						diffBuilder = new StringBuilder();
						diffBuilder.append(SPACE_TAB).append("无");
					}
					if(forcePassBuilder == null || forcePassBuilder.length() == 0){
						forcePassBuilder = new StringBuilder();
					}
					if(nodeResult.equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
						if(forcePassBuilder.length() > 0){
							resultMsg.append("对账结果检查为：强制通过！");
						}else {
							resultMsg.append("对账结果检查为：通过！");
						}
					}else if(isOver)
					{
						resultMsg.append("对账结果检查为：超时通过！");
					}else{
						resultMsg.append("对账结果检查为：不通过！");
					}
					resultMsg.append("当前任务检查次数为 ：单次! <br> ");
					resultMsg.append("检测到检查结果为通过的数据组合:").append(SLIPT_MARK).append(sameBuilder.toString()).append(SLIPT_MARK);
					resultMsg.append("检测到检查结果为不通过的数据组合:").append(SLIPT_MARK).append(diffBuilder.toString()).append(SLIPT_MARK);
					if("1".equalsIgnoreCase(forcePass)){
						resultMsg.append("强制通过条件显示设置的变量条件:").append(gloabVarName).append(gloabVarOper).append(gloabVarValue).append(SLIPT_MARK);
						resultMsg.append("检测到检查结果为强制通过的数据组合:").append(SLIPT_MARK).append(forcePassBuilder.toString().length()==0?"无":forcePassBuilder.toString()).append(SLIPT_MARK);
					}
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("开始执行对账结果检查", null, false));
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(resultMsg.toString(), null, false));
					ben_RECORD.addDetailParent(arugTitle);
					if(ben_RECORD.getC_Doing_Type() == DoingType.Success){
						ben_RECORD.EndLog_Success("对账结果检查完成");
						ben_RECORD.setC_Doing_Type(DoingType.Approve);
					} else if (ben_RECORD.getC_Doing_Type() == DoingType.ForcePass){
						ben_RECORD.EndLog_Success("对账结果强制通过");
						ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
					}else if(forcePassRes){
						ben_RECORD.EndLog_Success("对账结果强制通过");
						ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
					} else if (isOver){
						ben_RECORD.EndLog_Success("对账结果超时通过");
						ben_RECORD.setC_Doing_Type(DoingType.Approve);
					}else {
						ben_RECORD.EndLog_Fail("对账结果检查完成");
						ben_RECORD.setC_Doing_Type(DoingType.Deny);
					}
				}
				
				if(!isContinue || isOver || forcePassRes){
					if(forcePassRes)//先判断强置通过
					{
						//根据设置的强制通过结果返回对账结果
						logger.info("根据设置的强制通过或者超时结果返回对账结果：" + getOverOrForceRes());
						if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(getOverOrForceRes())){
							routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
						}else {
							routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
						}
						result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
					}else if(nodeResult == AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE){
						routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
					}else {
						routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
					}
					
					if(isOver)
					{
						logger.info("根据设置的强制通过或者超时结果返回对账结果：" + getOverOrForceRes());
						if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(getOverOrForceRes())){
							routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
						}else {
							routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
						}
						result = AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE;
					}
					
					//状态是对账终态时通知
					AutoTaskHandleVo handleVo = new AutoTaskHandleVo();
					handleVo.setProcInstId(processId);
					handleVo.setTaskInstId(taskId);
					handleVo.setListRecord(ben_RecordList);
					handleVo.setBusiExeResult(result);
					handleVo.setBusiExeMsg("完成对账结果检查");
					handleVo.setRouteParams(routeParam);
					handleService.handTask(handleVo);
					break;
				}
			}
			//如果获取10次之后仍未获取到结果，则先让出线程，进去线程池等待
			if(isContinue && checkState()){
				try {
					TimeUnit.SECONDS.sleep(checkFreq);
				} catch (Exception e) {
					logger.error("检查频率等待出错:" + e.getMessage(), e);
				}
				SynSingleCheckResultThread synCheckThread = new SynSingleCheckResultThread(frmCheckFreq,hmData,processId,taskId);

				TaskExecutor.execute(synCheckThread);
			}
		}
	}
	/**
	 * 检查强制通过条件是否符合
	 * @param processId
	 * @param taskId
	 * @return 返回true即满足强制通过，false则要么不检查强制通过条件，要么没有满足条件
	 */
	private boolean checkForcePass(String processId, String taskId) {
		boolean isPass = false;
		if("1".equalsIgnoreCase(forcePass)){//勾选了强制通过
			String checkRes = gloabValCheckService.check(processId, taskId, gloabVarName, gloabVarOper, gloabVarValue);
			logger.debug("强制条件检查结果：" + checkRes);
			if("true".equalsIgnoreCase(checkRes)){
				isPass = true;
			}
		}
		return isPass;
	}
	/**
	 * 检查流程实例当前状态是否在执行中
	 * BUG274130【华宝基金】自动化流程实例停止了但是检查电子对账的任务一直在后台执行
	 * @return
	 */
	private boolean checkState()
    {
      boolean state = true;
      ProcessInstance instance = this.processInstanceService.queryDataByProcInstId(this.processId);
      if ((instance == null) || (!"EXECUTEING".equals(instance.getC_STATUS()))) {
        state = false;
      }
      return state;
    }
	/**
	 * STORY #75718 自动化优化：增加智能映射方式    add baoql 20190826
	 * 获取当检查任务选择“证券行情映射”时的失败日志
	 * @param params
	 * @param accDate
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private BEN_RECORD getFailRecordZQHQYS(HashMap<String, Object> params, Date accDate) {
		String info = "";
		List<String> portCodes = (List<String>) params.get(ParamConstants.Datas.toString());
		String portCode = portCodes.get(0);
		String itemInfo = "对账结果检查情况—" + portCode;
		BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("对账结果检查", null, true);
		BEN_RECORD record=null;
		if ("1".equals(failFlag5)) {
			info = "当前组合[" + portCode + "]没有任何日期的库存数据，请确认!";
			record = createRecord(portCode, itemInfo, params, info, Status.SUCESS, accDate, arugTitle,DoingType.Approve);
			taskExecuteResult.setBusiExeResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		} else if ("2".equals(failFlag5)) {
			info = "当前组合[" + portCode + "]在[证券行情映射]中找不到对应的映射组合，无投资于本公司的产品，检查直接通过。";
			record = createRecord(portCode, itemInfo, params, info, Status.SUCESS, accDate, arugTitle,DoingType.Approve);
			taskExecuteResult.setBusiExeResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		} else if ("9".equals(failFlag5)) {
			info = "当前组合[" + portCode + "]在查询[证券行情映射]组合代码异常";
			record = createRecord(portCode, itemInfo, params, info, Status.FAIL, accDate, arugTitle,DoingType.Deny);
			taskExecuteResult.setBusiExeResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
		}	
		return record;
	}
	/**
	 * STORY #75718 自动化优化：增加智能映射方式    add baoql 20190826
	 * @param portCode
	 * @param cfgInfo
	 * @param params
	 * @param info
	 * @param status
	 * @param dDate
	 * @param arugTitle
	 * @param doingType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private BEN_RECORD createRecord(String portCode, String cfgInfo, HashMap<String, Object> params, String info,
			Status status, Date dDate, BEN_RECORD_DETAIL_TEXT arugTitle,DoingType doingType) {
		String userCode = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();
		String execOperCode = ((List<String>) params.get(ParamConstants.EXECUTE_CODE.toString())).get(0);
		BEN_RECORD record = new BEN_RECORD(userCode);
		record.setC_Fun_Code("automaticEconfirmCheckTask");
		record.init(portCode, cfgInfo, dDate);
		record.setC_Idf_Code(" ");
		record.setC_Dispatch_ID(execOperCode);
		record.setProcessInstanceId(((List<String>) params.get(ParamConstants.ProcessInstanceId.toString())).get(0));
		record.setTaskInstanceId(((List<String>) params.get(ParamConstants.TaskInstanceId.toString())).get(0));
		record.BeginLog("");
		arugTitle.setStatus(status);
		BEN_RECORD_DETAIL_TEXT detail = new BEN_RECORD_DETAIL_TEXT(info,null, false);
		detail.setStatus(status);
		arugTitle.appendChildText(detail);
		record.addDetailParent(arugTitle);
		if (status != null) {
			switch (status) {
			case FAIL:
				record.EndLog_Fail(info);
				break;
			case SUCESS:
				record.EndLog_Success(info);
				break;
			case WARNING:
				record.EndLog_Warn(info);
				break;
			default:
				break;
			}
		}
		record.setC_Doing_Type(doingType);

		return record;
	}
}
