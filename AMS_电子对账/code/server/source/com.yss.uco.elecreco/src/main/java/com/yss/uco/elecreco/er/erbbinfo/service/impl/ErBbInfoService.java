package com.yss.uco.elecreco.er.erbbinfo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.ifa.szt.tool.thread.DzdzMgr;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.uco.elecreco.er.erbbinfo.dao.ErBbInfoDao;
import com.yss.uco.elecreco.er.erbbinfo.dao.ErBbInfoSqlBuilder;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.erdblgz.service.impl.ErDblgzbService;
import com.yss.uco.elecreco.er.ergzb.service.impl.ErGzbService;
import com.yss.uco.elecreco.er.erinfostate.service.ErStepStateService;
import com.yss.uco.elecreco.er.erjzcbdb.pojo.ErJzcbdb;
import com.yss.uco.elecreco.er.erjzcbdb.service.impl.ErJzcbdbService;
import com.yss.uco.elecreco.er.erlrb.pojo.ErLrb;
import com.yss.uco.elecreco.er.erlrb.service.impl.ErLrbService;
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.er.erresult.service.impl.ErResultService;
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;
import com.yss.uco.elecreco.er.ersyzqyb.service.impl.ErSyzqybService;
import com.yss.uco.elecreco.er.erunport.service.impl.ErUnPortService;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;
import com.yss.uco.elecreco.er.eryeb.service.impl.ErYebService;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;
import com.yss.uco.elecreco.er.erzcfzb.service.impl.ErZcfzbService;
import com.yss.uco.elecreco.er.mrapi.thread.SendThreadPool;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.dzdz.common.RecordElement;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.service.IAutoStateService;
import com.yss.uco.elecreco.support.service.IErBbInfoService;
import com.yss.uco.support.comm.pojo.EConfirm;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;

import dayfsupport.service.econfirm.IEConfirmService;


public class ErBbInfoService extends ServiceBus<ErBbInfoService> implements
		IErBbInfoService {

	private ErBbInfoDao serviceDao = null;
	
	private ErStepStateService erStepService = null;
	private ErUnPortService erUnPortService = null;
	private IAutoStateService autoStateService = null;
	
	private IErResultService erResultService = null;
	
	public ErBbInfoService() throws Exception {
		// TODO 启动本应用的时候启动接收线程
		serviceDao = new ErBbInfoDao(DbPoolFactory.getInstance().getPool(),
				new ErBbInfoSqlBuilder());
		dao = serviceDao;
		erStepService = new ErStepStateService();
		erUnPortService = new ErUnPortService();
		erResultService = new ErResultService();
		autoStateService = YssServiceFactory.getInstance().createService(IAutoStateService.class);
	}

	/**
	 * 根据基金代码和日期获得每个文件类型的报文序号 key为去除.号的科目，value 系统中科目
	 * 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getKmMap(String c_ass_code, String c_sn, String fileType)
			throws Exception {
		Map<String, String> reMap = new HashMap<String, String>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("C_ASS_CODE", c_ass_code); ////基金代码 缩小查询范围 提高性能 by weijj 20151219
		map.put("C_SN", c_sn);
		if (fileType.trim().equalsIgnoreCase("1001")) {
			ErYebService service = new ErYebService();
			List<ErYeb> list = service.getYeData(map);
			String key;
			String value;
			RecordElement record = new RecordElement();
			for (ErYeb er : list) {
				value = er.getC_KM_CODE();
				key = record.getKmCode(value);
				reMap.put(key, value);
			}
		} else if (fileType.trim().equalsIgnoreCase("1011")) {
			ErGzbService service = new ErGzbService();
			List<ErGzb> list = service.getGzData(map);
			String key;
			String value;
			RecordElement record = new RecordElement();
			for (ErGzb er : list) {
				value = er.getC_KM_CODE();
				key = record.getKmCode(value);
				reMap.put(key, value);
			}
		}
		return reMap;
	}
	/**
	 * 根据基金代码和日期获得每个文件类型的报文序号 key为去除.号的科目，value 系统中科目
	 * @param date 
	 * @return
	 * @throws Exception
	 */
	public Map<String, String> getKmMap2(String c_sn, String fileType, String date)
			throws Exception {
		Map<String, String> reMap = new HashMap<String, String>();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("C_SN", c_sn);
		if (fileType.trim().equalsIgnoreCase("1001")) {
			ErYebService service = new ErYebService();
			reMap = service.getKmMap(c_sn, date);
		} else if (fileType.trim().equalsIgnoreCase("1011")) {
			ErGzbService service = new ErGzbService();
			List<ErGzb> list = service.getGzData(map);
			String key;
			String value;
			RecordElement record = new RecordElement();
			for (ErGzb er : list) {
				value = er.getC_KM_CODE();
				//BUG221290【大成基金】估值表指标项电子对账反馈时无科目名称时，本方科目名称要根据对账指标关联进行匹配展示
				if(value != null && value.contains("(")){//指标代码的科目代码=指标代码（指标名称）
					er.setC_KM_NAME(value.substring(value.indexOf("(") + 1, value.lastIndexOf(")")));//有些指标的指标名称是具体数据，需转换为指标名称
					value = value.substring(0, value.indexOf("("));//取指标代码
				}
				key = record.getKmCode(value);
				reMap.put(key, er.getC_KM_NAME() + "&&" + value);
				
			}
		} else if (fileType.trim().equals("1013")) {//STORY74798双估值报表电子对账功能
			ErDblgzbService service = new ErDblgzbService();
			List<ErDblgzb> list = service.getGzData(map);
			String key;
			String value;
			RecordElement record = new RecordElement();
			for (ErDblgzb er : list) {
				value = er.getC_KM_CODE();
				//BUG221290【大成基金】估值表指标项电子对账反馈时无科目名称时，本方科目名称要根据对账指标关联进行匹配展示
				if(value != null && value.contains("(")){//指标代码的科目代码=指标代码（指标名称）
					er.setC_KM_NAME(value.substring(value.indexOf("(") + 1, value.lastIndexOf(")")));//有些指标的指标名称是具体数据，需转换为指标名称
					value = value.substring(0, value.indexOf("("));//取指标代码
				}
				key = record.getKmCode(value);
				reMap.put(key, er.getC_KM_NAME() + "&&" + value);
			}
		} else if (fileType.trim().indexOf("1701")>0 || fileType.trim().indexOf("1711")>0) {
			map.put("C_RPT_TYPE", fileType.split("_")[0]);
			ErZcfzbService service = new ErZcfzbService();
			List<ErZcfzb> list = service.getZcfzData(map);
			String value;
			for (ErZcfzb er : list) {
				value = er.getC_INDEX_CODE();
				reMap.put(value, er.getC_INDEX_NAME() + "&&" + value);
			}
		} else if (fileType.trim().indexOf("1801")>0 || fileType.trim().indexOf("1811")>0) {
			map.put("C_RPT_TYPE", fileType.split("_")[0]);
			ErLrbService service = new ErLrbService();
			List<ErLrb> list = service.getLrData(map);
			String value;
			for (ErLrb er : list) {
				value = er.getC_INDEX_CODE();
				reMap.put(value, er.getC_INDEX_NAME() + "&&" + value);
			}
		} else if (fileType.trim().indexOf("1901")>0) {
			map.put("C_RPT_TYPE", fileType.split("_")[0]);
			ErSyzqybService service = new ErSyzqybService();
			List<ErSyzqyb> list = service.getSyzqyData(map);
			String value;
			for (ErSyzqyb er : list) {
				value = er.getC_INDEX_CODE();
				reMap.put(value, er.getC_INDEX_NAME() + "&&" + value);
			}
		}else if (fileType.trim().indexOf("1903")>0) {
			map.put("C_RPT_TYPE", fileType.split("_")[0]);
			ErJzcbdbService service = new ErJzcbdbService();
			List<ErJzcbdb> list = service.getErJzcbdbData(map);
			String value;
			for (ErJzcbdb er : list) {
				value = er.getC_INDEX_CODE();
				reMap.put(value, er.getC_INDEX_NAME() + "&&" + value);
			}
		}
		return reMap;
	}
	
	/**
	 * 根据基金代码和日期获得每个文件类型的报文序号 key为文件类型，value 为报文序号
	 * 
	 * @return
	 */
	public Map<String, String> getFileMap(String c_ass_code, String startDate) {
		return serviceDao.getFileMap(c_ass_code, startDate);
	}

	/**
	 * 审核状态修改 由基类审核方法去做 修改电子对账信息 发送状态
	 * 
	 * @param bbInfoList
	 * @param status
	 * @return
	 */
	public String updateBbInfo(List<ErBbInfo> bbInfoList, String status) {
		return serviceDao.updateBbInfo(bbInfoList, status);
	}

	public String deleteBbInfo(List<ErBbInfo> bbInfoList) {
		return serviceDao.deleteBbInfo(bbInfoList);
	}

	/**只会在对账一致和不一致时进入此方法处理
	 * @param status
	 * @param fsn
	 * @param fileType
	 * @param err
	 * @param cAssCode
	 * 
	 */
	public void updateStatus(String status, String fsn, String fileType,
			String err, String cAssCode) {
		logger.debug("修改状态：[托管行返回对账一致或者不一致]" + status + ":" + fsn + ":" + fileType + ":"
				+ cAssCode + ":" + err);
		serviceDao.updateBbInfo(status, fsn, fileType, err, cAssCode);
		//更新状态变更记录
		ErStepState erStepState = erStepService.buildPojo(cAssCode,fsn,fileType,"",status,"",err);
		erStepService.insertPojo(erStepState);
		logger.debug("保存修改状态历史" + status + ":" + fsn + ":" + fileType + ":"
				+ cAssCode + ":" + erStepState.getC_STEP_DATE());
		//STORY54581电子对账接收对账结果后发送消息
		autoStateService.sendAutoMessage(status, fsn, fileType, cAssCode);
	}
	
	/**
	 * 更新托管行联系人
	 * @param dealer
	 * @param fsn
	 */
	public void updateDealer(String dealer, String fsn) {
		logger.debug("修改联系人,sn：" + fsn + "dealer:" + dealer);
		serviceDao.updateDealer(dealer, fsn);
	}
	
	@Override
	public String getXmlFile(ErBbInfo erBbInfo) {
//		ErFileService service = new ErFileService();
		String content = "";
		try {
//			content = service.getFileContent(fsn, fileType, cAssCode);
			content = serviceDao.getXmlFile(erBbInfo);
		} catch (Exception e) {
			logger.error("查询数据出错:" + e.getMessage(), e);
		}
		return content;
	}
	
	/**
	 * 将选择的电子对账数据放到待发送线程池中
	 */
	public String sendBbInfo(List<ErBbInfo> list) {
		SendThreadPool.sendData(list);
		return MvcConstant._Success;
	}
	
	/**
	 * ////STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
	 * 把基类的方法重写了，造成日志取数据取不到
	 */
	public List<ErBbInfo> getSelectSqlByIdx(String[] list) {
		return serviceDao.getSelectSqlByIdx(list);
	}
	
	public String updateBbInfo(List<ErBbInfo> list, String status,
			String cERRINFO) {
		for (ErBbInfo bbInfo : list) {
			bbInfo.setC_ERR_INFO(cERRINFO);
		}
		return updateBbInfo(list, status);
	}


	@Override
	public ErBbInfo getBbInfoById(String id) {
		return serviceDao.getBbInfoById(id);
	}
	
	@Override
	public String reStartDzMgr() {
		DzdzMgr.newInstence().stop();
		DzdzMgr.newInstence().start();
		return "ok";
	}

	@Override
	/**
	 * STORY31217【招商财富】电子对账管理已反馈新增一个人工可以修改处理状态的功能
	 * @param bbInfoList 
	 * @return
	 */
	public String acceptBbInfo(List<ErBbInfo> pojoList) {
		serviceDao.acceptBbInfo(pojoList);
		//STORY54581电子对账接收对账结果后发送消息
		for (ErBbInfo erBbInfo : pojoList) {
			autoStateService.sendAutoMessage(erBbInfo.getC_STATE(), erBbInfo.getC_SN(), 
					erBbInfo.getC_FILE_TYPE(), erBbInfo.getC_ASS_CODE());
		}

		return "";
	}
	
	
	@Override
	/**
	 * STORY54447反馈不一致结果人工对账一致处理需填写原因与说明信息 新增其他对账中的对账一致方法
	 * @param bbInfoList 
	 * @return
	 */
	public String acceptBbInfoForQTDZ(List<ErBbInfo> pojoList) {
		return serviceDao.acceptBbInfoForQTDZ(pojoList);
	}
	
	/**
	 * 是否已经被人工一致
	 * STORY #84310【华宝基金】自动化二期电子对账人工设置一致不在获取托管行反馈
	 * @param csn 报文序号
	 * @return
	 */
	public boolean isManualAccept(String csn) {
		return serviceDao.isManualAccept(csn);
	}

	@Override
	/**
	 * STORY #50374 电子对账功能优化 
	 * 操作不对账组合
	 * @param operType：add-设置为不对账，remove-解除不对账设置 
	 */
	public String UnPortOper(List<ErBbInfo> pojoList,String operType) {
		//去除重复组合代码的数据
		HashSet<String> portSet  =   new  HashSet<String>();
		for (ErBbInfo erBbInfo : pojoList) {
			String c_PORT_CODE = erBbInfo.getC_PORT_CODE();
			portSet.add(c_PORT_CODE);
		}
		List<String> portList = new ArrayList<String>();
		portList.addAll(portSet);
		if("add".equalsIgnoreCase(operType)){
			//设置组合为不对账
			return erUnPortService.insertListByPortCodes(portList);
		}else if("remove".equalsIgnoreCase(operType)){
			//解除不对账限制
			return erUnPortService.deletByPortCodes(portList);
		}else{
			return "";
		}
	}

	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		if(paraMap.get("ARRAY_C_STATE")!= null && paraMap.get("ARRAY_C_STATE").toString().equalsIgnoreCase("ER_BDZ")){//如果是其他对账分页查询，则列头另外取
			QueryRes queryRes = super.queryByCondition(paraMap, page);
			ListHeadDtl listHeadInfo = null;
			boolean isOSGI = YssContextFactory.getInstance().getOSGI();
			if(isOSGI){
				Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
				listHeadInfo = context.getListHeadMap("dzBdzInfo");
			}else{
				listHeadInfo = AppContext.getInstance().getListHeadMap("dzBdzInfo");
			}
			
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setHeadKeyList(headKeyList);
			return queryRes;
		}else{
			return super.queryByCondition(paraMap, page);
		}
	}

	@Override
	public String lockEconfirm(List<ErBbInfo> pojos, String execProcCode,
			String isCheckExe,String executeType) {
		String res = "";
		IEConfirmService eConfirmSvc = YssServiceFactory.getInstance().createService(IEConfirmService.class);
		try {
			Map<String, EConfirm> groupEconfirm = groupEconfirm(pojos, eConfirmSvc);
			//STORY #104480 净值确认管理优化整合需求对电子对账管理的影响评估优化
			if("AUDIT".equalsIgnoreCase(executeType)) {
				//先检查，校验不能审核的净值
				Map<String,String> checkMap = new HashMap<String, String>();
				if(null != pojos) {
					StringBuffer msg = new StringBuffer("");
					List<EConfirm> pojoList = new ArrayList<EConfirm>();
					for (ErBbInfo info : pojos) {
						EConfirm econfirm = new EConfirm();
						econfirm.setId(info.getC_CONFIRM_ID());
						econfirm.setC_PORT_CODE(info.getC_PORT_CODE());
						econfirm.setD_BIZ_DATE(info.getD_DATE());
						econfirm.setC_BIZ_CLS("eConfirm");
						econfirm.setC_BIZ_ITEM("eConfirm");
						econfirm.setModifier(ContextFactory.getContext().getUserCode());
						econfirm.setOperator(ContextFactory.getContext().getUserCode());
						pojoList.add(econfirm);
					}
					List<EConfirm> confirmList = eConfirmSvc.querycheckrule(pojoList);
					for (EConfirm eConfirm : confirmList) {
						String key = eConfirm.getC_PORT_CODE() + eConfirm.getD_BIZ_DATE();
						if(!checkMap.containsKey(key)){
							checkMap.put(key, eConfirm.getC_PORT_CODE());
							msg.append(eConfirm.getC_PORT_CODE()).append(",");					
						}
					}
					
					res = msg.toString();
				}
				
				List<BasePojo> pojoList = new ArrayList<BasePojo>();
				if(null != pojos) {
					for (ErBbInfo info : pojos) {
						String key = info.getC_PORT_CODE() + info.getD_DATE();
						String executeCode = info.getC_CONFIRM_EXECUTE();
						if(null != groupEconfirm && groupEconfirm.containsKey(key)) {
							EConfirm eConfirm = groupEconfirm.get(key);
							executeCode = eConfirm.getC_EXECUTE();
						}
						if(!checkMap.containsKey(key) && "LOCK".equalsIgnoreCase(executeCode)) {
							EConfirm econfirm = new EConfirm();
							econfirm.setId(info.getC_CONFIRM_ID());
							econfirm.setC_PORT_CODE(info.getC_PORT_CODE());
							econfirm.setD_BIZ_DATE(info.getD_DATE());
							econfirm.setC_BIZ_CLS("eConfirm");
							econfirm.setC_BIZ_ITEM("eConfirm");
							econfirm.setModifier(ContextFactory.getContext().getUserCode());
							econfirm.setOperator(ContextFactory.getContext().getUserCode());
							pojoList.add(econfirm);					
						}
					}
					eConfirmSvc.auditById(pojoList);
				}
			}else {
	//			serviceDao.updateErBbInfoJzQr(pojos);
				List<BasePojo> pojoList = new ArrayList<BasePojo>();
				for (ErBbInfo info : pojos) {
					if ("ECONFIRM".equals(executeType)) {
						EConfirm econfirm = new EConfirm();
						String econfirmKey = info.getC_PORT_CODE()+info.getD_DATE();
						String confirmId = info.getC_CONFIRM_ID();
						if(StringUtil.IsNullOrEmptyT(confirmId) && groupEconfirm.containsKey(econfirmKey)) {
							EConfirm eConfirm = groupEconfirm.get(econfirmKey);
							confirmId = null == eConfirm ? "" :eConfirm.getId();
						}
						econfirm.setId(confirmId);
						econfirm.setC_PORT_CODE(info.getC_PORT_CODE());
						econfirm.setD_BIZ_DATE(info.getD_DATE());
						econfirm.setC_EXECUTE("UNLOCK");
						// econfirm.setN_LOCK_STATUS("已锁定".equals(info.getC_LOCK_EXECUTE())
						// ? 1 : 0);
						// econfirm.setN_CONFIRM_STATUS("已确认".equals(info.getC_CONFIRM_EXECUTE())
						// ? 1 : 0);
						econfirm.setC_BIZ_CLS("eConfirm");
						econfirm.setC_BIZ_ITEM("eConfirm");
						econfirm.setModifier(ContextFactory.getContext().getUserCode());
						pojoList.add(econfirm);
					}
				}
				
				if (pojoList != null && pojoList.size() > 0){
					logger.info("start getEconfirmAllRecord pojoList:" + JsonUtil.toString(pojoList));
		            res = eConfirmSvc.updateById(pojoList, execProcCode, isCheckExe, "LOCK");
		            List<BEN_RECORD> econfirmList = eConfirmSvc.getAllRecord();
		            logger.info("end getEconfirmAllRecord econfirmList:" + JsonUtil.toString(econfirmList));
		            StringBuffer result = new StringBuffer("");
		            if(null != econfirmList){
		                for (BEN_RECORD ben_RECORD : econfirmList) {
		                    if(ben_RECORD.getC_Doing_Type() == DoingType.Fail){
		                        result.append(ben_RECORD.getC_Mes_Text()).append("/r/n");
		                    }
		                }
		            }
		            logger.info("result:" + result.toString());
		            res = result.toString();
		        }
			}
		} catch(Exception e) {
			logger.error("lockEconfirm error", e);
		}
		return res;
	}
	
	private Map<String, EConfirm> groupEconfirm(List<ErBbInfo> pojos, IEConfirmService eConfirmSvc){
		Map<String, EConfirm> resultMap = new HashMap<String, EConfirm>();
		try {
			//1 先根据日期分组勾选的组合数据
			Map<String,List<String>> portCodeMap = new HashMap<String, List<String>>();
			for (ErBbInfo info : pojos) {
				if(portCodeMap.containsKey(info.getD_DATE())) {
					portCodeMap.get(info.getD_DATE()).add(info.getC_PORT_CODE());
				}else {
					List<String> portCodes = new ArrayList<String>();
					portCodes.add(info.getC_PORT_CODE());
					portCodeMap.put(info.getD_DATE(), portCodes);
				}
			}
			
			//2.查询净值确认单编号
			if(null != portCodeMap && portCodeMap.size() > 0) {
				 for (Map.Entry<String,List<String>> portCodesEntry : portCodeMap.entrySet()) {
					 HashMap<String, Object> paraMap = new HashMap<String, Object>();
					 paraMap.put("ARRAY_C_PORT_CODE", StringUtil.join(portCodesEntry.getValue(), ","));
					 paraMap.put("D_START_DATE", portCodesEntry.getKey());
					 paraMap.put("D_END_DATE", portCodesEntry.getKey());
					 paraMap.put("C_BIZ_CLS", "eConfirm");
					 QueryRes confirmRes = eConfirmSvc.queryByCondition(paraMap);
					 if(null != confirmRes && null != confirmRes.getDataList() 
							 && confirmRes.getDataList().size() > 0) {
						 EConfirm eConfirm = null;
						 for (BasePojo confirmBasePojo : confirmRes.getDataList()) {
							 eConfirm = (EConfirm)confirmBasePojo;
							 String econfirmKey = eConfirm.getC_PORT_CODE() + eConfirm.getD_BIZ_DATE();
							 if(!resultMap.containsKey(econfirmKey)) {
								 resultMap.put(econfirmKey, eConfirm);
							 }
						}
					 }
				 }
			}
		}catch(Exception e) {
			logger.error("groupEconfirm error",e);
		}
		return resultMap;
	}

	@Override
	public Map<String, ErBbInfo> getDzResultInfo(Date dDate, String assCodes) {
		return serviceDao.getDzResultInfo(dDate, assCodes);
	}

	/**
	 * wSTORY #77811 要可以撤销手工设置的对帐一致 修改或删除对账一致数据
	 * 判断是电子对账还是手工对账分别进行更新和删除对账数据
	 * @param pojoList
	 * @return
	 */
	@Override
	public String unAcceptClick(List<ErBbInfo> pojoList) {
		int count = 0;
		String result = "";
		try {
			for (int i = 0; i < pojoList.size(); i++) {
				ErBbInfo erBbInfo = pojoList.get(i);
				if (erUnPortService.queryByCode(erBbInfo.getC_PORT_CODE())) {
					// 手工对账
					serviceDao.deleteByCsn(erBbInfo);
				} else {
					// 电子对账
					count = erResultService.queryUnAcceptCount(erBbInfo.getC_SN());
					erBbInfo.setC_ERR_INFO(count + "");
					serviceDao.updateErBbInfo(erBbInfo);
				}
			}
			result = MvcConstant._Success;
		} catch (Exception ex) {
			// TODO: handle exception
			logger.error("撤回对账一致数据出错：" + ex.getMessage(), ex);
			result = MvcConstant._Fault;
		}
		return result;
	}
	
	/**
	 *  wSTORY #77811 要可以撤销手工设置的对帐一致 修改或删除对账一致数据
	 *  查询差异行数
	 */
	@Override
	public String queryNumberOfRows(ErBbInfo erBbInfo) {
		int count = 0;
		String rows = "";
		try {
			count = erResultService.queryUnAcceptCount(erBbInfo.getC_SN());
			if (count > 0) {
				rows = "差异行数: " + count;
				return rows;
			} else {
				String state = erBbInfo.getC_STATE();
				if ("ER_IDENTICAL".equals(state)) {
					return "托管行缺少对账数据";
				} else {
					rows = "差异行数: " + count;
					return rows;
				}
			}
		} catch (Exception ex) {
			// TODO: handle exception
			logger.error("撤回对账一致数据出错：" + ex.getMessage(), ex);
		}
		return rows;
	}
}
