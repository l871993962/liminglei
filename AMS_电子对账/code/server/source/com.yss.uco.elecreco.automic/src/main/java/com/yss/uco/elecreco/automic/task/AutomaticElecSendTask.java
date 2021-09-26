package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.fast.task.support.automatic.ParamConstants;
import com.yss.fast.task.support.task.support.pojo.AutomaticBaseTask;
import com.yss.framework.api.busoperservice.IBusiness;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;
import com.yss.uco.elecreco.automic.util.AutomicUtil;
import com.yss.uco.elecreco.automic.util.DateSplit;
import com.yss.uco.elecreco.automic.util.RptUtil;
import com.yss.uco.elecreco.support.bean.AutoState;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.service.IElecTaskService;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

/**
 * 
 * @ClassName AutomaticElecSendTask
 * @Description 估值自动化电子对账发送任务执行类
 * @author wulongxing@ysstech.com
 * @CreateDate 2018年3月16日
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 * @Task	STORY53584估值自动化-产生电子对账的功能需要注册到自动化平台
 */
public class AutomaticElecSendTask extends AutomaticBaseTask{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 增加日志
	 */
	private static final Logger logger = LogManager.getLogger(AutomaticElecSendTask.class);

	@SuppressWarnings("unchecked")
	@Override
	public void execute(HashMap<String, Object> params) throws Exception {
		IElecTaskService elecTaskService = YssServiceFactory.getInstance().createService(IElecTaskService.class);
		String dzStatesString = ParamDZCons.WARNING;
		List<BEN_RECORD> lstRecord = null;
		String result = "200000";
		String resultMsg = "";
		String processId = "";
		List<String> errorItemList = new ArrayList<String>();
		try {
			logger.info("开始发送电子对账：" + params.toString());
			Map<String, List<String>> rptMap = new HashMap<String, List<String>>();
			//获取组合参数值
			List<String> portCodes = (List<String>) params.get(ParamConstants.Datas.toString());
			List<String> dzTypes = (List<String>) params.get(ParamDZCons.DZ_TYPE);
			//流程ID
			processId = AutomicUtil.getStringParam(params, ParamConstants.ProcessInstanceId);
			//任务ID
			String taskId = AutomicUtil.getStringParam(params, ParamConstants.TaskInstanceId);
			String execOperCode =  AutomicUtil.getStringParam(params, ParamConstants.EXECUTE_CODE);
			//STORY54412节假日前一工作日自动执行多天做账流程
			String accBeginDate =  AutomicUtil.getStringParam(params, ParamConstants.Date);
			String accEndDate =  AutomicUtil.getStringParam(params, ParamConstants.EndDate);
			List<String> dzStates = (List<String>) params.get(ParamDZCons.DZ_NODE_STATE);
			if (null != dzStates && dzStates.size()>0 && !StringUtil.IsNullOrEmptyT(dzStates.get(0))) {
				dzStatesString = dzStates.get(0);
			}
			logger.info("dzStatesString" + dzStatesString);
			StringBuilder dzTypeBuilder = new StringBuilder();   
			for (int i = 0; i < dzTypes.size(); i++) { 
				String dz = dzTypes.get(i);
				if(dz != null && dz.length() == 4){//排除掉报表类型，如日报，月报，季报，半年报，年报
					dzTypeBuilder.append(dz);       
					if (i < dzTypes.size() - 1) {       
						dzTypeBuilder.append(",");    
					} 
				}
				else if(dz != null && dz.contains("_"))//STORY84473【华宝基金】二期自动化电子发送月报
				{
					String[] ss = dz.split("_");
					if(rptMap.containsKey(ss[0]))
					{
						rptMap.get(ss[0]).add(dz);
					}else
					{
						List<String> list = new ArrayList<String>();
						list.add(dz);
						rptMap.put(ss[0], list);
					}
				}  
			}  
			HashMap<String, Object> hmData = new HashMap<String, Object>();
			hmData.put("ARRAY_C_PORT_CODE", AutomicUtil.listToString(portCodes, ","));
			hmData.put("ARRAY_C_DZ_CODE", dzTypeBuilder.toString());
			hmData.put("D_START_DATE", accBeginDate);
			hmData.put("D_END_DATE", accEndDate);
			hmData.put(ParamDZCons.KEY_ARRAY_C_DZ_CODE, dzTypeBuilder.toString() );
			hmData.put(ParamDZCons.KEY_D_START_DATE, accBeginDate);
			hmData.put(ParamDZCons.KEY_D_END_DATE, accEndDate);
			hmData.put("C_OPER_CODE", execOperCode);
			hmData.put("C_DISPATCH_ID", execOperCode);
			hmData.put("C_FUN_CODE", "elecGene");
			hmData.put("OPER_TYPE", "SEND");
			hmData.put("AUTOSEND","1");//STORY54581电子对账接收对账结果后发送消息

			//发送电子对账处理
//			elecTaskService.init(hmData);
//			elecTaskService.doBusOper(hmData);
//			String result = "200000";
//			String resultMsg = "";
//			List<BEN_RECORD> lstRecord = ((IBusiness)elecTaskService).getListRecord();
		    lstRecord = sendData(hmData, rptMap);
			List<BasePojo> stateList = new ArrayList<BasePojo>();
			AutoState autoState = null;
			////STORY56593优先处理：实例管理：（进度，跳过，筛选，状态，业务日期）
			logger.info("lstRecord:" + lstRecord.size());
			if (lstRecord != null && lstRecord.size() > 0) {
				for (BEN_RECORD record : lstRecord) {
					record.setProcessInstanceId(processId);
					record.setTaskInstanceId(taskId);
//					record.setC_Item_Name(FileTypeEnum.getValueByKey(record.getC_Item_Code()));
					record.setC_Fun_Code("automaticElecSendTask");
//					logger.info("发送1" + record.getC_Item_Name() + "[" + record.getC_Report_Code() + "]结果：" + record.getC_Doing_Type());
//					if(record.getC_Doing_Type() == DoingType.Fail && ParamDZCons.FAIL.equals(dzStatesString)){
//						result = "100000";
//						errorItemList.add(record.getC_Item_Name());
//					}else if(record.getC_Doing_Type() == DoingType.Success && !"100000".equalsIgnoreCase(result)){
//						result = "000000";
//					}else {
//						record.setC_Doing_Type(DoingType.Warn);
//					}
//					
					if (record.getC_Doing_Type() == DoingType.Fail || record.getC_Doing_Type() == DoingType.Warn) {
						if (ParamDZCons.FAIL.equals(dzStatesString)) {
							result = "100000";
							errorItemList.add(record.getC_Item_Name());
						}else if (ParamDZCons.WARNING.equals(dzStatesString)) {
							result = "200000";
							record.setC_Doing_Type(DoingType.Warn);
						}
					}else if(record.getC_Doing_Type() == DoingType.Success && !"100000".equalsIgnoreCase(result)){
						result = "000000";
					}else {
						record.setC_Doing_Type(DoingType.Warn);
					}
					
					autoState = new AutoState();
					autoState.setC_process_id(processId);
					autoState.setC_task_id(taskId);
					autoState.setC_port_code(record.getC_Port_Code());
					autoState.setC_file_type(record.getC_Item_Code());
					autoState.setC_sn(record.getC_Report_Code());
					autoState.setC_state("ER_SENDED");
					stateList.add(autoState);

					logger.info("发送" + record.getC_Item_Name() + "[" + record.getC_Report_Code() + "]结果：" + record.getC_Doing_Type());
				}
			}else {
				if ( ParamDZCons.FAIL.equals(dzStatesString)) {
					result = "100000";
				}
			}
			if(stateList != null && stateList.size() > 0){
				IAutoStateService autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
				autoStateService.insert(stateList);
			}
//			/* 处理设置业务执行结果日志 */
//			taskExecuteResult.setWait(false);
//			taskExecuteResult.setListRecord(lstRecord);
//			taskExecuteResult.setBusiExeResult(result);
//			taskExecuteResult.setBusiExeMsg(resultMsg);
//			taskExecuteResult.setErrorItemList(errorItemList);
			logger.info("电子对账发送任务执行完成：" + processId);
		} catch (Exception e) {
			/* 处理设置业务执行结果日志 */
			if ( ParamDZCons.FAIL.equals(dzStatesString)) {
				result = "100000";
			}else {
				result = "200000";
			}
			logger.error("执行电子对账发送自动化任务失败:" + e.getMessage(), e);
//			throw e;
		} finally {
			/* 处理设置业务执行结果日志 */
			taskExecuteResult.setWait(false);
			taskExecuteResult.setListRecord(lstRecord);
			taskExecuteResult.setBusiExeResult(result);
			taskExecuteResult.setBusiExeMsg(resultMsg);
			taskExecuteResult.setErrorItemList(errorItemList);
			logger.info("电子对账发送任务执行完成：" + processId);
			elecTaskService = null;
		}
	}

	/**
	 * 生成数据
	 * @param hmData
	 * @param rptMap
	 * @return
	 * @throws Exception
	 */
	public List<BEN_RECORD> sendData(HashMap<String, Object> hmData, Map<String, List<String>> rptMap) throws Exception
	{
		List<BEN_RECORD> lstRecord = new ArrayList<BEN_RECORD>();
		List<BEN_RECORD> dayData = sendDayData(hmData);
		if(dayData != null)
		{
			for (BEN_RECORD ben_RECORD : dayData) {
				logger.info("日报发送C_Item_Code:" + ben_RECORD.getC_Item_Code());
				ben_RECORD.setC_Item_Code(ben_RECORD.getC_Item_Code());
				ben_RECORD.setC_Item_Name("日报-" + FileTypeEnum.getValueByKey(ben_RECORD.getC_Item_Code()));
				lstRecord.add(ben_RECORD);
			}
		}
		List<BEN_RECORD> monData = sendMonData(hmData, rptMap);
		if(monData != null)
		{
			lstRecord.addAll(monData);
		}
		return lstRecord;
	}
	
	/**
	 * 发送日报数据
	 * @param hmData
	 * @return
	 * @throws Exception
	 */
	private List<BEN_RECORD> sendDayData(HashMap<String, Object> hmData) throws Exception
	{
		List<BEN_RECORD> lstRecord = null;
		IElecTaskService elecTaskService = YssServiceFactory.getInstance().createService(IElecTaskService.class);
		try{
			if(hmData.containsKey(ParamDZCons.KEY_ARRAY_C_DZ_CODE) && !StringUtil.IsNullOrEmptyT((String)hmData.get(ParamDZCons.KEY_ARRAY_C_DZ_CODE))){
				elecTaskService.init(hmData);
				elecTaskService.doBusOper(hmData);
				lstRecord = ((IBusiness)elecTaskService).getListRecord();
			}
		}catch(Exception e){
			logger.error("发送数据错误:" + hmData.toString());
			throw e;
		}finally{
			elecTaskService = null;
		}
		return lstRecord;
	}
	
	/**
	 * 发送月报数据
	 * @param hmData	参数
	 * @param rptMap	按报表类型将对账类型分批 {key:报表类型 value:对账类型}
	 * @return
	 * @throws Exception
	 */
	private List<BEN_RECORD> sendMonData(HashMap<String, Object> hmData, Map<String, List<String>> rptMap) throws Exception
	{
		List<BEN_RECORD> list = new ArrayList<BEN_RECORD>();
		HashMap<String, Object> paras = new HashMap<String, Object>();
		paras.putAll(hmData);
		String start = (String)paras.remove(ParamDZCons.KEY_D_START_DATE);
		String end = (String)paras.remove(ParamDZCons.KEY_D_END_DATE);
		Date startDate = DateUtil.stringtoDate(start, ParamDZCons.FORMAT);
		Date endDate = DateUtil.stringtoDate(end, ParamDZCons.FORMAT);
		for (String rptType : rptMap.keySet()) {
			List<String> dates = DateSplit.getStartDateAndEndDateWithRptType(startDate, endDate, rptType);
			logger.info("根据开始日期[" + start + "],结束日期["+ end +"]报表类型["+ rptType +"]获取生成月报时间：" + dates.toString());
			for (String date : dates) {
				String[] ss = date.split("_");
				paras.put(ParamDZCons.KEY_D_START_DATE, ss[0]);
				paras.put(ParamDZCons.KEY_D_END_DATE, ss[1]);
				paras.put(ParamDZCons.KEY_ARRAY_C_DZ_CODE, StringUtil.join(rptMap.get(rptType), ","));
				paras.put("C_RPT_TYPE", rptType);
				List<BEN_RECORD> res = sendDayData(paras);
				if(res != null)
				{
					for (BEN_RECORD ben_RECORD : res) {
						logger.info("月报发送C_Item_Code:" + ben_RECORD.getC_Item_Code());
						ben_RECORD.setC_Item_Name(RptUtil.getName(rptType) + "-" + FileTypeEnum.getValueByKey(ben_RECORD.getC_Item_Code()));
						list.add(ben_RECORD);
					}
				}
			}
		}
		return list;
	}
}
