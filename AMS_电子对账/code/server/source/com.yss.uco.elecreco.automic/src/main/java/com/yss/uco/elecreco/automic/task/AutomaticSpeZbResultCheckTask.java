package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.yss.fast.task.support.automatic.AutomaticResultConstants;
import com.yss.fast.task.support.automatic.pojo.AutoTaskHandleVo;
import com.yss.fast.task.support.automatic.pojo.ProcessInstance;
import com.yss.fast.task.support.automatic.service.IAutoGloabValCheckService;
import com.yss.fast.task.support.automatic.service.IAutomaticTaskHandleService;
import com.yss.fast.task.support.automatic.service.IProcessInstanceService;
import com.yss.fast.task.support.task.support.pojo.AutomaticBaseTask;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.bizlog.BEN_RECORD_DETAIL;
import com.yss.framework.api.common.co.bizlog.BEN_RECORD_DETAIL_STYLE;
import com.yss.framework.api.common.co.bizlog.BEN_RECORD_DETAIL_STYLE_COLOR;
import com.yss.framework.api.common.co.bizlog.BEN_RECORD_DETAIL_TEXT;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.executor.LifecycleException;
import com.yss.framework.executor.StandardThreadExecutor;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;
import com.yss.uco.elecreco.automic.param.DzResultCheckParam;
import com.yss.uco.elecreco.automic.pojo.ElecAutoInfo;
import com.yss.uco.elecreco.automic.util.FieldCheck;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.service.IErSpecialZbService;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

/**
 * automaticSpeZbResultCheckTask
 * @author lwz
 *
 */
public class AutomaticSpeZbResultCheckTask extends AutomaticBaseTask{

	private static final long serialVersionUID = 1L;
	
	private ElecAutoInfo info = null;
	
	/**
	 * 增加日志
	 */
	private static final Logger logger = LogManager.getLogger(AutomaticSpeZbResultCheckTask.class);
	
	private HashMap<String, String> routeParam = new HashMap<String, String>();
	private static final String ROUTE_PARAM_CODE = "ElecResult";
	private static final String RESULT_SUCCESS = "0";
	private static final String RESULT_FAIL = "1";
	private static final String SLIPT_MARK = "<br>";
	private static final String SPACE_TAB = "	";
	
	private IAutoGloabValCheckService gloabValCheckService = null;
	//BUG274130【华宝基金】自动化流程实例停止了但是检查电子对账的任务一直在后台执行
	private IProcessInstanceService processInstanceService = null;
	
	public static StandardThreadExecutor TaskExecutor ;
	
	private IAutoStateService autoStateService = null;
	
	private Map<String, String> zbNames = null;
	
	static {
		TaskExecutor = new StandardThreadExecutor();
		TaskExecutor.setMaxThreads(50);
		TaskExecutor.setMinSpareThreads(1);
		TaskExecutor.setNamePrefix("AutomaticSpeZbResultCheckTask_TaskExecutor");
		try {
			TaskExecutor.start();
		} catch (LifecycleException e) {
			logger.error("AutomaticSpeZbResultCheckTask_TaskExecutor 启动失败！");
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void execute(HashMap<String, Object> params) throws Exception {
		DzResultCheckParam param = new DzResultCheckParam(params);
		logger.info("开始执行对账结果特殊指标节点检查任务：" + param.toString());
		//BUG297531【对账结果检查（特殊指标）】节点中“对账类型”参数需隐藏
		List<String> dzs = new ArrayList<String>();
		dzs.add("1011");
		param.setDzTypes(dzs);
		gloabValCheckService = YssServiceFactory.getInstance().createService(IAutoGloabValCheckService.class);
		info = new ElecAutoInfo(param);
		String msg = checkParams(param);
		if(!StringUtil.IsNullOrEmptyT(msg)) {//参数检查不通过的话直接返回
			logger.info("AutomaticSpeZbResultCheckTask参数检查不通过：" + msg);
			info.addErrMsg(msg);
			info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
			info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
			BEN_RECORD ben_RECORD = createErrBEN_RECORD(param, msg);
			info.getBen_RecordList().add(ben_RECORD);
			taskExecuteResult.setWait(false);
			/* 处理设置业务执行结果日志 */
			taskExecuteResult.setListRecord(info.getBen_RecordList());
			
			routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
			taskExecuteResult.setBusiExeMsg("对账结果检查失败");
			taskExecuteResult.setBusiExeResult(info.getResult());
			taskExecuteResult.setRouteParams(routeParam);
			return;
		}
		initZbNames();
		Date startDate = YssFun.toDate(param.getAccBeginDate());
		Date endDate = YssFun.toDate(param.getAccEndDate());
		int days = YssFun.dateDiff(startDate, endDate);
		for (int i = 0; i <= days; i++) {
			for (String dztype : param.getDzTypes()) {
				for (String port : param.getPortList()) {
					Date currentDate = YssFun.addDay(startDate, i);
					BEN_RECORD record = getDzResult(currentDate, dztype, port, param);
					if(isEndLoop(param, record))
					{
						appendRecordDetail(record, "检查循环终止条件:满足");
						info.getBen_RecordList().add(record);
					}
					else if(checkForcePass(param))
					{
						appendRecordDetail(record, "检查循环终止条件:不满足");
						appendRecordDetail(record, "检查强制通过条件:满足");
						info.getBen_RecordList().add(record);
					}else
					{
						appendRecordDetail(record, "检查循环终止条件:不满足");
						appendRecordDetail(record, "检查强制通过条件:不满足");
						appendRecordDetail(record, "开始循环检查");
						info.getNoResult().add(record);
					}
				}//end port
			}//end dztype
		}//end day
		taskExecuteResult.setWait(false);
		String count = param.getFrmCheckCount();//检查次数 0-多次 1-单次
		if("1".equalsIgnoreCase(count))
		{
			info.getBen_RecordList().addAll(info.getNoResult());
			setExeResult(param);
			setAllDetailMsg();
			/* 处理设置业务执行结果日志 */
			taskExecuteResult.setListRecord(info.getBen_RecordList());
			
			if(checkForcePass(param) || isOverTime(param))//先判断强置通过或者超时
			{
				//根据设置的强制通过结果返回对账结果
				logger.info("根据设置的强制通过或者超时结果返回对账结果：" + param.getOverOrForceRes());
				if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(param.getOverOrForceRes())){
					routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
					info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
				}else {
					routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
				}
				info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
			}else if(!info.getDzState().equalsIgnoreCase(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE)){
				routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
				taskExecuteResult.setBusiExeMsg("对账结果检查失败");
			}else {
				routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
				taskExecuteResult.setBusiExeMsg("完成对账结果检查");
			}
			taskExecuteResult.setBusiExeResult(info.getResult());
			taskExecuteResult.setRouteParams(routeParam);
		}else//多次
		{
			taskExecuteResult.setWait(true);
			SynCheckResultThread synCheckThread = new SynCheckResultThread(param);
			TaskExecutor.execute(synCheckThread);
		}
	}
	
	/**
	 * 初始化指标名称映射map
	 */
	private void initZbNames()
	{
		zbNames = new HashMap<String, String>();
		IErSpecialZbService specialZbService = YssServiceFactory.getInstance().createService(IErSpecialZbService.class);
		List<ErSpecialZb> allData = specialZbService.getAllData();
		if(allData != null)
		{
			for (ErSpecialZb zb : allData) {
				zbNames.put(zb.getC_KEY_CODE(), zb.getC_KEY_NAME());
			}
		}
	}
	
	/**
	 * 设置每条数据的执行信息
	 */
	private void setAllDetailMsg()
	{
		BEN_RECORD_DETAIL_TEXT arugTitle;
		for (BEN_RECORD ben_RECORD : info.getBen_RecordList()) {
			String msg = String.format("特殊指标对账结果检查:通过（%d） 强制通过（%d） 不通过（%d）" + SLIPT_MARK + this.info.getResultMsg(), 
					this.info.getPassSize(), this.info.getForceSize(), this.info.getNoPassSize());
			if(ben_RECORD.getDetails() != null && ben_RECORD.getDetails().size() > 0)
			{
				arugTitle = (BEN_RECORD_DETAIL_TEXT) ben_RECORD.getDetails().get(0);
				arugTitle.setContent(msg);
			}else
			{
				arugTitle = new BEN_RECORD_DETAIL_TEXT(msg, null, true);
			}
			
			if(ben_RECORD.getC_Doing_Type() == DoingType.Success){
				ben_RECORD.setC_Doing_Type(DoingType.Approve);
				appendRecordDetail(ben_RECORD, "对账结果检查完成:通过");
				ben_RECORD.EndLog_Success("对账结果检查完成:通过");
			} else if (ben_RECORD.getC_Doing_Type() == DoingType.ForcePass){
				appendRecordDetail(ben_RECORD, "对账结果检查完成：强制通过");
				ben_RECORD.setC_Doing_Type(DoingType.ForcePass);
				ben_RECORD.EndLog_Success("对账结果检查完成：强制通过");
			} else {
				appendRecordDetail(ben_RECORD, "对账结果检查完成:未通过");
				ben_RECORD.setC_Doing_Type(DoingType.Deny);
				ben_RECORD.EndLog_Fail("对账结果检查完成:未通过");
			}
		}
	}
	
	/**
	 * 设置节点执行结果：绿色成功000000，红色失败100000
	 * @param param
	 * @param record
	 * @return
	 */
	private void setExeResult(DzResultCheckParam param)
	{
		List<String> where = param.getGreenWhere();
		String result = "";
		String msg = "";
		for (BEN_RECORD record : info.getBen_RecordList()) {
			msg = new StringBuffer().append(record.getC_Port_Code()).append("(")
					.append(FileTypeEnum.getValueByKey(record.getC_Item_Code())).append(")").toString();
			if(DoingType.Success.equals(record.getC_Doing_Type()))
			{
				result = ParamDZCons.RESULT_SAME;
			}else if(DoingType.Warn.equals(record.getC_Doing_Type()))
			{
				result = ParamDZCons.RESULT_DIFF;
			}
			else
			{
				result = ParamDZCons.RESULT_NO;
			}
			
			if(where.contains(result))
			{
				if(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE.equalsIgnoreCase(info.getDzState()))
				{
					info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
				}
				if(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE.equalsIgnoreCase(info.getResult()))
				{
					info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
				}
				info.addPassMsg(msg);
				appendRecordDetail(record, "检查节点绿色通过条件：通过");
				record.setC_Doing_Type(DoingType.Success);
				
			}else if(checkForcePass(param))
			{
				if(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE.equalsIgnoreCase(info.getDzState()))
				{
					info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
				}
				if(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE.equalsIgnoreCase(info.getResult()))
				{
					info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
				}
				info.addforcePassMsg(msg);
				appendRecordDetail(record, "检查节点绿色通过条件：不通过");
				appendRecordDetail(record, "检查强制通过条件：通过");
				record.setC_Doing_Type(DoingType.ForcePass);
			}else
			{
				info.addNoPassMsg(msg);
				appendRecordDetail(record, "检查节点绿色通过条件：不通过");
				appendRecordDetail(record, "检查强制通过条件：不通过");
				record.setC_Doing_Type(DoingType.Deny);
				info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
				info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
			}
		}
	}
	
	/**
	 * 停止循环检查任务
	 * @param param
	 */
	private void stopLoopTask(DzResultCheckParam param)
	{
		IAutomaticTaskHandleService handleService = YssServiceFactory.getInstance().createService(IAutomaticTaskHandleService.class);
		//状态是对账终态时通知
		AutoTaskHandleVo handleVo = new AutoTaskHandleVo();
		handleVo.setProcInstId(param.getProcessId());
		handleVo.setTaskInstId(param.getTaskId());
		handleVo.setListRecord(info.getBen_RecordList());
		handleVo.setBusiExeResult(info.getResult());
		handleVo.setBusiExeMsg("完成对账结果检查");
		handleVo.setRouteParams(routeParam);
		handleService.handTask(handleVo);
	}
	
	
	/**
	 * 设置多次检查的结果
	 * @param ben_RecordList
	 * @param param
	 */
	private void setMoreCheckResult(DzResultCheckParam param)
	{
		setExeResult(param);
		setAllDetailMsg();
		if(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE.equalsIgnoreCase(info.getDzState())){
			routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
		}else {
			routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
		}
		stopLoopTask(param);
	}
	
	/**
	 * 循环遍历没有符合循环终止条件的数据
	 * @param param
	 * @param ben_RecordList
	 * @param noResult
	 * @throws Exception 
	 */
	private List<BEN_RECORD> loopCheck(DzResultCheckParam param, ElecAutoInfo info) throws Exception
	{
		List<BEN_RECORD> noResult = new ArrayList<BEN_RECORD>();
		for (BEN_RECORD res : info.getNoResult()) {
			BEN_RECORD record = getDzResult(res.getD_Trade(), res.getC_Item_Code(), res.getC_Port_Code(), param);
			if(res.getDetails() != null && res.getDetails().size() > 0)
			{
				if(record.getDetails() != null && record.getDetails().size() > 0)
				{
					BEN_RECORD_DETAIL parent = record.getDetails().get(0);
					if(parent.getChildren() != null && parent.getChildren().size() > 0)
					{
						res.getDetails().get(0).getChildren().addAll(parent.getChildren());
					}
				}
				record.setDetails(res.getDetails());
			}
			if(isEndLoop(param, record))
			{
				appendRecordDetail(record, "检查循环终止条件结果:满足");
				String msg = String.format("循环检查%s(%s)终止条件结束", record.getC_Port_Code(), FileTypeEnum.getValueByKey(record.getC_Item_Code()));
				appendRecordDetail(record, msg);
				info.getBen_RecordList().add(record);
			}else if(checkForcePass(param))
			{
				appendRecordDetail(record, "检查循环终止条件结果:不满足");
				appendRecordDetail(record, "检查强制通过条件结果:满足");
				String msg = String.format("循环检查%s(%s)终止条件结束", record.getC_Port_Code(), FileTypeEnum.getValueByKey(record.getC_Item_Code()));
				appendRecordDetail(record, msg);
				info.getBen_RecordList().add(record);
			}
			else
			{
				appendRecordDetail(record, "检查循环终止条件结果:不满足");
				appendRecordDetail(record, "检查强制通过条件结果:不满足");
				appendRecordDetail(record, "继续下次循环检查");
				noResult.add(record);
			}
		}
		info.getNoResult().clear();
		info.getNoResult().addAll(noResult);
		return noResult;
	}
	
	/**
	 * 追加子信息
	 * @param record
	 * @param msg
	 */
	private void appendRecordDetail(BEN_RECORD record, String msg)
	{
		List<BEN_RECORD_DETAIL> details = record.getDetails();
		if(details != null && details.size() > 0)
		{
			BEN_RECORD_DETAIL_TEXT arugTitle = (BEN_RECORD_DETAIL_TEXT) details.get(0);
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(msg, null, false));
		}
	}
	
	/**
	 * 判断是否满足循环终止条件
	 * @param param
	 * @param record
	 * @return
	 */
	private boolean isEndLoop(DzResultCheckParam param, BEN_RECORD record)
	{
		List<String> endWhere = param.getEndWhere();
		String result = "";
		if(record == null)
		{
			result = ParamDZCons.RESULT_NO;
		}
		
		if(DoingType.Success.equals(record.getC_Doing_Type()))
		{
			result = ParamDZCons.RESULT_SAME;
			
		}else if(DoingType.Warn.equals(record.getC_Doing_Type()))
		{
			result = ParamDZCons.RESULT_DIFF;
		}else
		{
			result = ParamDZCons.RESULT_NO;
		}
		return endWhere.contains(result);
	}
	
	/**
	 * 创建错误的执行记录
	 * @param param 参数
	 * @param msg 错误信息
	 * @return
	 */
	@SuppressWarnings("deprecation")
	private BEN_RECORD createErrBEN_RECORD(DzResultCheckParam param, String msg)
	{
		BEN_RECORD ben_RECORD = new BEN_RECORD(param.getUserCode());
		ben_RECORD.setC_Fun_Code("automaticSpeZbResultCheckTask");
		Date date = null;
		try {
			date = YssFun.toDate(param.getAccBeginDate());
		} catch (YssException e) {
			logger.error(e.getMessage(), e);
		}
		ben_RECORD.init(" ", " ",  date);
		ben_RECORD.setC_Idf_Code(" ");
		ben_RECORD.setC_Dispatch_ID(param.getExecOperCode());
		ben_RECORD.setProcessInstanceId(param.getProcessId());
		ben_RECORD.setTaskInstanceId(param.getTaskId());
		ben_RECORD.setC_Item_Name(" ");
		ben_RECORD.BeginLog("");
		BEN_RECORD_DETAIL_STYLE style = BEN_RECORD_DETAIL_STYLE.TEXT;
		BEN_RECORD_DETAIL_STYLE_COLOR color = new BEN_RECORD_DETAIL_STYLE_COLOR(255, 0, 0);
		style.setFontColor(color);
		BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("特殊指标对账结果检查失败", null, true);
		arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("开始执行对账结果检查", null, style, false));
		arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(info.getResultMsg(), null, style, false));
		arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("结束执行对账结果检查", null, style, false));
		ben_RECORD.addDetailParent(arugTitle);
		ben_RECORD.setC_Doing_Type(DoingType.Fail);
		ben_RECORD.EndLog_Fail("特殊指标对账结果检查失败！");
		return ben_RECORD;
	}
	
	/**
	 * 获取对账结果
	 * @param currentDate 业务日期
	 * @param dztype 对账类型
	 * @param port 组合
	 * @param param 参数
	 * @return
	 * @throws Exception
	 */
	private BEN_RECORD getDzResult(Date currentDate, String dztype,
			String port, DzResultCheckParam param) throws Exception {
		HashMap<String, String> mapData = new HashMap<String, String>();
		mapData.put("ARRAY_C_PORT_CODE",port);
		mapData.put("ARRAY_C_DZ_CODE", dztype);
		mapData.put("C_START_DATE", YssFun.formatDate(currentDate));
		mapData.put("C_END_DATE", YssFun.formatDate(currentDate));
		mapData.put("C_OPER_CODE", param.getExecOperCode());
		mapData.put("C_DISPATCH_ID", param.getExecOperCode());
		mapData.put("C_FUN_CODE", "elecSend");
		mapData.put("OPER_TYPE", "SEND");
		mapData.put("AUTOSEND","1");
		mapData.put("C_PROCESS_ID", param.getProcessId());
		mapData.put("C_TASK_ID", param.getTaskId());
		mapData.put("dzNewResult", param.getDzNewResult());
		String zbs = "";
		if(param.getSpecialZbs() != null && param.getSpecialZbs().size() > 0)
		{
			zbs = StringUtil.join(param.getSpecialZbs(), ",");
		}
		mapData.put("ARRAY_C_BF_CODE", zbs);
		BEN_RECORD record = null;
		if(autoStateService == null)
		{
			autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
		}
		List<ErBbInfo> infos = autoStateService.getDZResultInfo(mapData);
		if(infos != null && infos.size() > 0){
			ErBbInfo bbInfo = infos.get(0);
			record = createRecord(bbInfo, currentDate, dztype, port, param);
		}else
		{
			record = initRecord(currentDate, dztype, port, param);
			record.setC_Doing_Type(DoingType.Doing);
			BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("", null, true);
			record.addDetailParent(arugTitle);
			String msg = String.format("开始检查对账结果【组合(%s) 对账类型(%s)】：", port, FileTypeEnum.getValueByKey(dztype));
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(msg, null, false));
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：无数据", null, false));
		}
		return record;
	}
	
	/***
	 * 初始化执行信息
	 * @param currentDate
	 * @param dztype
	 * @param port
	 * @param param
	 * @return
	 */
	private BEN_RECORD initRecord(Date currentDate, String dztype,
			String port, DzResultCheckParam param)
	{
		BEN_RECORD record = new BEN_RECORD();
		record.init(port, dztype, currentDate);
		record.BeginLog();
		record.setC_Fun_Code("automaticSpeZbResultCheckTask");
		record.setC_Dispatch_ID(param.getExecOperCode());//execOperCode
		record.setProcessInstanceId(param.getProcessId());
		record.setTaskInstanceId(param.getTaskId());
		record.setC_Item_Code(dztype);
		record.setC_Item_Name(FileTypeEnum.getValueByKey(dztype));
		record.setC_Port_Code(port);
		return record;
	}
	
	/**
	 * 根据对账结果创建执行记录
	 * @param erBbInfo
	 * @param currentDate
	 * @param dztype
	 * @param port
	 * @param param
	 * @return
	 * @throws Exception 
	 */
	private BEN_RECORD createRecord(ErBbInfo erBbInfo,Date currentDate, String dztype,
			String port, DzResultCheckParam param) throws Exception
	{
		BEN_RECORD record = initRecord(currentDate, dztype, port, param);
		BEN_RECORD_DETAIL_TEXT arugTitle = new BEN_RECORD_DETAIL_TEXT("", null, true);
		record.addDetailParent(arugTitle);
		String msg = String.format("开始检查对账结果【组合(%s) 对账类型(%s)】：", port, FileTypeEnum.getValueByKey(dztype));
		arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(msg, null, false));
		if("ER_ACCECPED".equalsIgnoreCase(erBbInfo.getC_STATE())){//对账一致
			record.setC_Doing_Type(DoingType.Success);
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：对账一致", null, false));
		}else if("ER_IDENTICAL".equalsIgnoreCase(erBbInfo.getC_STATE())){//对账不一致
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：对账不一致", null, false));
			record.setC_Doing_Type(DoingType.Warn);
			if("1011".equalsIgnoreCase(dztype) || "1013".equalsIgnoreCase(dztype))
			{
				if(param.getSpecialZbs() != null && param.getSpecialZbs().size() > 0)
				{
					String zbs = StringUtil.join(param.getSpecialZbs(), ",");
					String checkCondition = StringUtil.join(param.getSpecialChecks(), ",");
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("开始检查特殊对账指标：" + zbs, null, false));
					arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("特殊对账指标检查结果：" + checkCondition, null, false));
					Map<String, String> diffData = autoStateService.getDiffData(erBbInfo, zbs, checkCondition);
					if(diffData != null && diffData.size() > 0)
					{
						for (String ele : diffData.keySet()) {
							String zbx = diffData.get(ele);
							String detail = String.format("检查特殊指标[%s(%s) 对应指标代码(%s)]:对账不一致", zbx, zbNames.get(zbx), ele);
							arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT(detail, null, false));
						}
						arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查设置的特殊指标结果：对账不一致", null, false));
						record.setC_Doing_Type(DoingType.Warn);
					}else
					{
						arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查设置的特殊指标：对账一致", null, false));
						record.setC_Doing_Type(DoingType.Success);
					}
				}
			}
		}else if("ER_SENDED_FAIL".equalsIgnoreCase(erBbInfo.getC_STATE())){//发送失败
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：发送失败", null, false));
			record.setC_Doing_Type(DoingType.Fail);
		}else if("ER_SENDED_SECCUSS".equalsIgnoreCase(erBbInfo.getC_STATE()) && "1031".equalsIgnoreCase(erBbInfo.getC_FILE_TYPE())){
			record.setC_Doing_Type(DoingType.Success);
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：科目表发送成功", null, false));
		}else{
			arugTitle.appendChildText(new BEN_RECORD_DETAIL_TEXT("检查到对账结果：对账处理中", null, false));
			record.setC_Doing_Type(DoingType.Doing);
		}
		return record;
	}

	/**
	 * 检查前台传入的参数
	 * @param param
	 * @return
	 */
	private String checkParams(DzResultCheckParam param) {
		List<String> dzTypes = param.getDzTypes();
		List<String> portList = param.getPortList();
		List<String> endWhere = param.getEndWhere();
		List<String> greenWhere = param.getGreenWhere();
		FieldCheck check = new FieldCheck(SPACE_TAB, SLIPT_MARK);
		check.checkListNull(dzTypes, "对账类型不能为空！").checkListNull(portList, "组合不能为空！")
		.checkListNull(endWhere, "轮询中止条件不能为空！").checkListNull(greenWhere, "节点绿色通过条件不能为空！");
		return check.getResultStr();
	}
	
	/**
	 * 是否超时
	 * @param param
	 * @return
	 */
	private boolean isOverTime(DzResultCheckParam param)
	{
		String overTime = param.getOverTime();
		logger.info("判断是否超超过设置的超时时间：" + overTime);
		if(!StringUtil.IsNullOrEmptyT(overTime))
		{
			return DateUtil.getNow("HH:mm:ss").compareTo(overTime) > 0;
		}
		return false;
	}
	
	class SynCheckResultThread extends Thread{
		private DzResultCheckParam param = null;
		public SynCheckResultThread(DzResultCheckParam param){
			this.param = param;
		}
		
		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			int i = 0;
			while(checkState(param.getProcessId()))
			{
				try{
					if(isOverTime(param))
					{
						logger.info("检查结果：超时通过");
						setOverTimeResult(param);
						return;
					}
					if(checkForcePass(param))
					{
						logger.info("检查结果：强制通过");
						setForcePassResult(param);
						return;
					}
					
					i++;
					logger.debug(String.format("第%d次循环开始：%s", i, info.getNoResult().toString()));
					List<BEN_RECORD> res = loopCheck(param, info);
					logger.debug(String.format("第%d次循环结束：%s", i, info.getNoResult().toString()));
					if(res.size() == 0)
					{
						setMoreCheckResult(param);
						return;
					}
					if(i == 10)
					{
						break;
					}
					TimeUnit.SECONDS.sleep(Long.parseLong(param.getFrmCheckFreq()));
				}catch(Exception e){
					logger.error("AutomaticSpeZbResultCheckTask:循环对账结果失败！");
					logger.error(e.getMessage(), e);
					info.addErrMsg(e.getMessage());
					info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
					info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_ERROR_CODE);
					BEN_RECORD ben_RECORD = createErrBEN_RECORD(param, e.getMessage());
					info.getBen_RecordList().add(ben_RECORD);
					stopLoopTask(param);
					return;
				}
			}
			//如果获取10次之后仍未获取到结果，则先让出线程，进去线程池等待
			if(checkState(param.getProcessId())){
				try {
					TimeUnit.SECONDS.sleep(Long.parseLong(param.getFrmCheckFreq()));
				} catch (Exception e) {
					logger.error("检查频率等待出错:" + e.getMessage(), e);
				}
				SynCheckResultThread synCheckThread = new SynCheckResultThread(param);

				TaskExecutor.execute(synCheckThread);
			}
		}
	}
	
	/**
	 *  设置强制通过结果
	 *  新增参数“超时/ 强制通过结果”，
	 *  此参数值为下拉框单选，默认为一致。参数值包括：一致、不一致，
	 *  该参数只有在勾选“是否设置超时时间”或勾选“强制跳过”的时候，才有效，
	 *  表示超时通过后强制跳过后节点认为对账是一致还是不一致，用于后续的路由条件判断时使用。
	 * @param param
	 */
	private void setForcePassDataDetail(DzResultCheckParam param)
	{
		for (BEN_RECORD ben_RECORD : info.getNoResult()) {
			setRecordTitle(ben_RECORD);
			ben_RECORD.setC_Doing_Type(DoingType.Approve);
			appendRecordDetail(ben_RECORD, "符合强制通过条件，不再继续扫描结果");
			appendRecordDetail(ben_RECORD, "对账结果检查完成:强制通过");
			ben_RECORD.EndLog_Success("对账结果检查完成:强制通过");
		}
	}
	
	private void setRecordTitle(BEN_RECORD ben_RECORD)
	{
		BEN_RECORD_DETAIL_TEXT arugTitle;
		String msg = String.format("特殊指标对账结果检查:通过（%d） 强制通过（%d） 不通过（%d）" + SLIPT_MARK + this.info.getResultMsg(), 
				this.info.getPassSize(), this.info.getForceSize(), this.info.getNoPassSize());
		if(this.info.isOverTimePass())
		{
			msg = String.format("特殊指标对账结果检查:通过（%d） 强制通过（%d） 不通过（%d）超时通过（%d）" + SLIPT_MARK + this.info.getResultMsg(), 
					this.info.getPassSize(), this.info.getForceSize(), this.info.getNoPassSize(), this.info.getNoResult().size());
		}
		if(ben_RECORD.getDetails() != null && ben_RECORD.getDetails().size() > 0)
		{
			arugTitle = (BEN_RECORD_DETAIL_TEXT) ben_RECORD.getDetails().get(0);
			arugTitle.setContent(msg);
		}else
		{
			arugTitle = new BEN_RECORD_DETAIL_TEXT(msg, null, true);
		}
	}
	
	/**
	 *  设置强制通过结果
	 *  新增参数“超时/ 强制通过结果”，
	 *  此参数值为下拉框单选，默认为一致。参数值包括：一致、不一致，
	 *  该参数只有在勾选“是否设置超时时间”或勾选“强制跳过”的时候，才有效，
	 *  表示超时通过后强制跳过后节点认为对账是一致还是不一致，用于后续的路由条件判断时使用。
	 * @param param
	 */
	private void setForcePassResult(DzResultCheckParam param) {
		setExeResult(param);
		setAllDetailMsg();
		setForcePassDataDetail(param);
		info.getBen_RecordList().addAll(info.getNoResult());
		if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(param.getOverOrForceRes())){
			routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
			info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		}else {
			routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
		}
		info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		stopLoopTask(param);
	}
	
	/**
	 *  设置超时结果
	 *  新增参数“超时/ 强制通过结果”，
	 *  此参数值为下拉框单选，默认为一致。参数值包括：一致、不一致，
	 *  该参数只有在勾选“是否设置超时时间”或勾选“强制跳过”的时候，才有效，
	 *  表示超时通过后强制跳过后节点认为对账是一致还是不一致，用于后续的路由条件判断时使用。
	 * @param param
	 */
	private void setOverTimeResult(DzResultCheckParam param) {
		info.setOverTimePass(true);
		setExeResult(param);
		setAllDetailMsg();
		setOverTimePassDataDetail(param);
		info.getBen_RecordList().addAll(info.getNoResult());
		if(ParamDZCons.RESULT_SAME.equalsIgnoreCase(param.getOverOrForceRes())){
			routeParam.put(ROUTE_PARAM_CODE, RESULT_SUCCESS);
			info.setDzState(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		}else {
			routeParam.put(ROUTE_PARAM_CODE, RESULT_FAIL);
		}
		info.setResult(AutomaticResultConstants.CONSTANT_BUSI_EXE_SUCCESS_CODE);
		stopLoopTask(param);
	}
	
	private void setOverTimePassDataDetail(DzResultCheckParam param)
	{
		for (BEN_RECORD ben_RECORD : info.getNoResult()) {
			setRecordTitle(ben_RECORD);
			ben_RECORD.setC_Doing_Type(DoingType.Approve);
			appendRecordDetail(ben_RECORD, "到达超时时间：" + param.getOverTime());
			appendRecordDetail(ben_RECORD, "对账结果检查完成:超时通过");
			ben_RECORD.EndLog_Success("对账结果检查完成:超时通过");
		}
	}
	
	/**
	 * 检查强制通过条件是否符合
	 * @param processId
	 * @param taskId
	 * @return 返回true即满足强制通过，false则要么不检查强制通过条件，要么没有满足条件
	 */
	private boolean checkForcePass(DzResultCheckParam param) {
		boolean isPass = false;
		if("1".equalsIgnoreCase(param.getForcePass())){//勾选了强制通过
			String checkRes = gloabValCheckService.check(param.getProcessId(), param.getTaskId(), param.getGloabVarName(), param.getGloabVarOper(), param.getGloabVarValue());
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
	private boolean checkState(String processId)
    {
      boolean state = true;
      if(processInstanceService == null)
      {
    	  processInstanceService = YssServiceFactory.getInstance().createService(IProcessInstanceService.class);
      }
      ProcessInstance instance = this.processInstanceService.queryDataByProcInstId(processId);
      if ((instance == null) || (!"EXECUTEING".equals(instance.getC_STATUS()))) {
        state = false;
      }
      return state;
    }
}
