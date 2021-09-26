package com.yss.uco.elecreco.automic.param;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.port.cache.service.IPortCacheDataService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.fast.task.support.automatic.ParamConstants;
import com.yss.fast.task.support.automatic.service.IAutomaticPortMapService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.restful.RestfulConfigServiceImpl;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;
import com.yss.uco.elecreco.automic.util.AutomicUtil;

/**
 * 对账结果检查节点前台传入的参数
 * @author lwz
 *
 */
public class DzResultCheckParam {
	
	private HashMap<String, Object> params = null;
	/**
	 * 特殊指标
	 */
	private List<String> specialZbs = null;
	
	/**
	 * 特殊指标检查条件
	 */
	private List<String> specialChecks = null;
	
	/**
	 * 最新对账结果依据 ；对账时间最新；资产净值市值最新
	 * STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
	 */
	private String dzNewResult = ParamDZCons.PORT_MV_NEW;
	
	/**
	 * 强制通过条件  变量名称
	 */
	private String gloabVarName = "";
	
	/**
	 * 强制通过条件  变量操作符
	 */
	private String gloabVarOper = "";
	
	/**
	 * 强制通过条件  变量值
	 */
	private String gloabVarValue = "";
	
	/**
	 * 流程ID
	 */
	private String processId = "";
	
	/**
	 * 对账类型
	 */
	private List<String> dzTypes = null;
	/**
	 * 绿色通过条件
	 */
	private List<String> greenWhere = null;
	/**
	 * 循环终止条件
	 */
	private List<String> endWhere = null;
	/**
	 * 操作代码
	 */
	private String execOperCode = "";
	/**
	 * 开始日期
	 */
	private String accBeginDate = "";
	/**
	 * 结束日期
	 */
	private String accEndDate = "";
	/**
	 * 任务ID
	 */
	private String taskId = "";
	/**
	 * 检查次数 0-多次 1-单次
	 */
	private String frmCheckCount = "";
	/**
	 * 检查频率 秒
	 */
	private String frmCheckFreq = "1";
	/**
	 * 强制通过  1勾选强制通过；0未勾选强制通过
	 */
	private String forcePass = "";
	/**
	 * 当前用户
	 */
	private String userCode = "";
	/**
	 * 组合
	 */
	private List<String> portList = null;
	
	/**
	 * 超时时间HH:mm:ss
	 */
	private String overTime = "";
	
	/**
	 * 超时结果
	 * 新增参数“超时/ 强制通过结果”，此参数值为下拉框单选，默认为一致。
	 * 参数值包括：一致、不一致，
	 * 该参数只有在勾选“是否设置超时时间”或勾选“强制跳过”的时候，才有效，
	 * 表示超时通过后强制跳过后节点认为对账是一致还是不一致，用于后续的路由条件判断时使用。
	 */
	private String overOrForceRes = ParamDZCons.RESULT_SAME;
	
	/**
	 * 错误信息
	 */
	private String msg = "";
	
	/**
	 * 获取过滤后的对账指标
	 * @param params
	 * @return
	 */
	private List<String> filterZbs(HashMap<String, Object> params)
	{
		List<String> res = new ArrayList<String>();
		@SuppressWarnings("unchecked")
		List<String> list = (List<String>) params.get(ParamDZCons.KEY_SPECIAL_ZB);
		if(list != null && list.size() > 0)
		{
			for (String s : list) {
				if(s.startsWith("PARENT_"))
				{
					continue;
				}
				res.add(s);
			}
		}
		return res;
	}
	
	@SuppressWarnings("unchecked")
	public DzResultCheckParam(HashMap<String, Object> params)
	{
		this.params = params;
		this.dzTypes = ParamUtil.filterDzType((List<String>) params.get(ParamDZCons.DZ_TYPE));
		//特殊指标
		this.specialZbs = filterZbs(params);
		
		this.specialChecks = (List<String>) params.get(ParamDZCons.KEY_CHECK_CONDITON);
		
		//绿色通过条件
		this.greenWhere = params.get(ParamDZCons.KEY_GREEN_WHERE) == null ? null : (List<String>)params.get(ParamDZCons.KEY_GREEN_WHERE);
		//轮询中止条件
		this.endWhere = params.get(ParamDZCons.KEY_END_WHERE) == null ? null : (List<String>)params.get(ParamDZCons.KEY_END_WHERE);
		this.execOperCode = AutomicUtil.getStringParam(params, ParamConstants.EXECUTE_CODE);
		//检查日期 0-业务日期   1-执行日期
		String frmCheckDType = AutomicUtil.getStringParam(params, ParamConstants.frmCheckDType);
		//STORY54412节假日前一工作日自动执行多天做账流程
		if ("0".equalsIgnoreCase(frmCheckDType)) {//业务日期
			this.accBeginDate = AutomicUtil.getStringParam(params, ParamConstants.Date);
		}else {
			//执行日期
			this.accBeginDate =  AutomicUtil.getStringParam(params, ParamConstants.TaskSchedulerDate);
		}
		this.accEndDate = AutomicUtil.getStringParam(params, ParamConstants.EndDate);
		//流程ID
		this.processId = AutomicUtil.getStringParam(params, ParamConstants.ProcessInstanceId);
		//任务ID
		this.taskId = AutomicUtil.getStringParam(params, ParamConstants.TaskInstanceId);
		//检查次数 0-多次 1-单次
		this.frmCheckCount = AutomicUtil.getStringParam(params, ParamConstants.frmCheckCount);
		//检查频率 秒
		this.frmCheckFreq = AutomicUtil.getStringParam(params, ParamConstants.frmCheckFreq);
		if(StringUtil.IsNullOrEmptyT(frmCheckFreq) || frmCheckFreq.trim().equalsIgnoreCase("0")){
			frmCheckFreq = "1";//检查频率未设置则默认为1秒
		}
		//STORY #60305 【紧急】检查任务、监听任务中的配置的组合需要增加对应关系，每个实例只需执行对应关系组合的检查任务
		//STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
		this.dzNewResult = AutomicUtil.getStringParam(params, "dzNewResult");//最新对账结果依据 ；对账时间最新；资产净值市值最新
		this.dzNewResult = StringUtil.IsNullOrEmptyT(dzNewResult) ? "PORT_MV_NEW" : dzNewResult;
		this.forcePass = AutomicUtil.getStringParam(params, "forcePass");//强制通过  1勾选强制通过；0未勾选强制通过
		this.gloabVarName = AutomicUtil.getStringParam(params, "gloabVarName");//强制通过条件  变量名称
		this.gloabVarOper = AutomicUtil.getStringParam(params, "gloabVarOper");//强制通过条件  变量操作符
		this.gloabVarValue = AutomicUtil.getStringParam(params, "gloabVarValue");//强制通过条件  变量值
		//获取组合筛选方式
		String frmCheckPortType = AutomicUtil.getStringParam(params, ParamConstants.frmCheckPortType);
		//获取组合参数值
		List<String> portCodes = (List<String>) params.get(ParamConstants.Datas.toString());
		//获取选择监听的组合
		List<String> selectPortList = (List<String>) params.get("SelectPortInfo");
		
		//超时时间
		this.overTime = ParamUtil.queryOverTime(params);
		
		//超时/强制通过结果
		this.overOrForceRes = StringUtil.IsNullOrEmptyT(AutomicUtil.getStringParam(params, ParamDZCons.KEY_OVER_FORCE_RES)) ? 
				ParamDZCons.RESULT_SAME : AutomicUtil.getStringParam(params, ParamDZCons.KEY_OVER_FORCE_RES);
		
		//根据组合筛选方式
		this.portList = getPortsByFrmCheckPortType(frmCheckPortType, portCodes, selectPortList);
		this.userCode = YssContextFactory.getInstance().getLogInfo().getLoggingUserCode();
	}
	

	@Override
	public String toString() {
		return "DzResultCheckParam [specialZbs="
				+ specialZbs + ", dzNewResult=" + dzNewResult
				+ ", gloabVarName=" + gloabVarName + ", gloabVarOper="
				+ gloabVarOper + ", gloabVarValue=" + gloabVarValue
				+ ", processId=" + processId + ", dzTypes=" + dzTypes
				+ ", greenWhere=" + greenWhere + ", endWhere=" + endWhere
				+ ", execOperCode=" + execOperCode + ", accBeginDate="
				+ accBeginDate + ", accEndDate=" + accEndDate + ", taskId="
				+ taskId + ", frmCheckCount=" + frmCheckCount
				+ ", frmCheckFreq=" + frmCheckFreq + ", forcePass=" + forcePass
				+ ", userCode=" + userCode + ", portList=" + portList
				+ ", msg=" + msg + "]";
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
	 * @param selectPortList 
	 * @return 检查组合信息
	 */
	public List<String> getPortsByFrmCheckPortType(String frmCheckPortType,List<String> portList, List<String> selectPortList) {
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
		}
		else if ("3".equalsIgnoreCase(frmCheckPortType)) {
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
					//failFlag = "2";
					this.msg = new StringBuffer().append("找不到实例组合").append(portCode).append("对应的单元层产品").toString();
					resultList.add(portCode);
				}
			}
			else {
				// 实例的组合“组合层级”不为“组合层”
				//failFlag = "1";
				this.msg = new StringBuffer().append("实例组合").append(portCode).append("的“组合级别”不为“组合层”").toString();
				resultList.add(portCode);
			}
		}
		return resultList;
	}

	public HashMap<String, Object> getParams() {
		return params;
	}

	public List<String> getSpecialZbs() {
		return specialZbs;
	}
	
	public List<String> getSpecialChecks() {
		return specialChecks;
	}

	public String getDzNewResult() {
		return dzNewResult;
	}

	public String getGloabVarName() {
		return gloabVarName;
	}

	public String getGloabVarOper() {
		return gloabVarOper;
	}

	public String getGloabVarValue() {
		return gloabVarValue;
	}

	public String getProcessId() {
		return processId;
	}

	public List<String> getDzTypes() {
		return dzTypes;
	}

	public List<String> getGreenWhere() {
		return greenWhere;
	}

	public List<String> getEndWhere() {
		return endWhere;
	}

	public String getExecOperCode() {
		return execOperCode;
	}

	public String getAccBeginDate() {
		return accBeginDate;
	}

	public String getAccEndDate() {
		return accEndDate;
	}

	public String getTaskId() {
		return taskId;
	}

	public String getFrmCheckCount() {
		return frmCheckCount;
	}

	public String getFrmCheckFreq() {
		return frmCheckFreq;
	}

	public String getForcePass() {
		return forcePass;
	}

	public String getUserCode() {
		return userCode;
	}

	public List<String> getPortList() {
		return portList;
	}

	public String getMsg() {
		return msg;
	}
	
	/**
	 * 获取绿色通过条件名称
	 * @return
	 */
	public String getGreenWhereName()
	{
		StringBuffer sb = new StringBuffer();
		if(this.getGreenWhere() != null && this.getGreenWhere().size() > 0)
		{
			for (String value : this.getGreenWhere()) {
				sb.append(ParamDZCons.getNameByValue(value));
				sb.append(",");
			}
		}
		String res = sb.toString();
		if(res.endsWith(","))
		{
			res = res.substring(0, res.length()-1);
		}
		return res;
	}
	
	/**
	 * 获取循环终止条件名称
	 * @return
	 */
	public String getEndWhereName()
	{
		StringBuffer sb = new StringBuffer();
		if(this.getEndWhere() != null && this.getEndWhere().size() > 0)
		{
			for (String value : this.getEndWhere()) {
				sb.append(ParamDZCons.getNameByValue(value));
				sb.append(",");
			}
		}
		String res = sb.toString();
		if(res.endsWith(","))
		{
			res = res.substring(0, res.length()-1);
		}
		return res;
	}
	
	/**
	 * 超时时间
	 * @return 如果没有设置超时时间，则返回null,否则返回设置的时间（HH:mm:ss）
	 */
	public String getOverTime() {
		return overTime;
	}

	public String getOverOrForceRes() {
		return overOrForceRes;
	}
	
	public void setDzTypes(List<String> dzTypes) {
		this.dzTypes = dzTypes;
	}
	
}
