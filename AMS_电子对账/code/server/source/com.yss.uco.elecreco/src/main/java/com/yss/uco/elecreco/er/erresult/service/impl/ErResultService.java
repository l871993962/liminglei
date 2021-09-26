package com.yss.uco.elecreco.er.erresult.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie;
import com.yss.fast.mq.common.exception.MQClientException;
import com.yss.fast.mq.support.api.IProducer;
import com.yss.fast.mq.support.constants.TopicConstants;
import com.yss.fast.mq.support.reqvo.ProducerReqInfo;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Port;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.task.pojo.SchedulerCompleteMessage;
import com.yss.framework.api.task.service.ITaskLogService;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.rptlog.service.IErRptLogService;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.uco.elecreco.er.erbbinfo.service.impl.ErBbInfoService;
import com.yss.uco.elecreco.er.erdblgz.dao.ErDblgzbDao;
import com.yss.uco.elecreco.er.erdblgz.dao.ErDblgzbSqlBuilder;
import com.yss.uco.elecreco.er.erdblgz.pojo.ErDblgzb;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbDao;
import com.yss.uco.elecreco.er.ergzb.dao.ErGzbSqlBuilder;
import com.yss.uco.elecreco.er.erjzcbdb.dao.ErJzcbdbDao;
import com.yss.uco.elecreco.er.erjzcbdb.dao.ErJzcbdbSqlBuilder;
import com.yss.uco.elecreco.er.erjzcbdb.pojo.ErJzcbdb;
import com.yss.uco.elecreco.er.erkmb.dao.ErKmbDao;
import com.yss.uco.elecreco.er.erkmb.dao.ErKmbSqlBuilder;
import com.yss.uco.elecreco.er.erlrb.dao.ErLrbDao;
import com.yss.uco.elecreco.er.erlrb.dao.ErLrbSqlBuilder;
import com.yss.uco.elecreco.er.erlrb.pojo.ErLrb;
import com.yss.uco.elecreco.er.erresult.dao.ErResultDao;
import com.yss.uco.elecreco.er.erresult.dao.ErResultSqlBuilder;
import com.yss.uco.elecreco.er.erresult.pojo.ErResult;
import com.yss.uco.elecreco.er.erresult.pojo.ErResultQuery;
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.er.ersyzqyb.dao.ErSyzqybDao;
import com.yss.uco.elecreco.er.ersyzqyb.dao.ErSyzqybSqlBuilder;
import com.yss.uco.elecreco.er.ersyzqyb.pojo.ErSyzqyb;
import com.yss.uco.elecreco.er.eryeb.dao.ErYebDao;
import com.yss.uco.elecreco.er.eryeb.dao.ErYebSqlBuilder;
import com.yss.uco.elecreco.er.eryeb.pojo.ErYeb;
import com.yss.uco.elecreco.er.erzcfzb.dao.ErZcfzbDao;
import com.yss.uco.elecreco.er.erzcfzb.dao.ErZcfzbSqlBuilder;
import com.yss.uco.elecreco.er.erzcfzb.pojo.ErZcfzb;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.bean.ErGzb;
import com.yss.uco.elecreco.support.dzdz.bus.result.pojo.ErResultElement;
import com.yss.uco.elecreco.support.dzdz.bus.result.pojo.XmlResult;
import com.yss.uco.elecreco.support.dzdz.common.ErConfigUtil;
import com.yss.uco.elecreco.support.dzdz.common.RecordElement;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.service.IErBbInfoService;
import com.yss.uco.elecreco.support.service.IErStepStateService;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;

public class ErResultService extends ServiceBus<ErResultService> implements
		IErResultService {
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	/**
	 * 托管行返回的资产代码，有可能是拆分代码
	 */
	protected String assCode = null; 
	protected TransPojo transPojo = null;
	protected boolean isSplit = false;
	private ErResultDao serviceDao = null;
	private ErGzbDao erGzbDao = null;
	private ErDblgzbDao erDblgzbDao = null;
	private ErYebDao erYebDao = null;
	private ErKmbDao erKmbDao = null;
	private ErZcfzbDao erZcfzbDao = null;
	private ErLrbDao erLrbDao = null;
	private ErSyzqybDao erSyzqybDao = null;
	private ErJzcbdbDao erJzcbdbDao = null;
	
	public ErResultService() throws Exception {
		serviceDao = new ErResultDao(DbPoolFactory.getInstance().getPool(),
				new ErResultSqlBuilder());
		erGzbDao = new ErGzbDao(DbPoolFactory.getInstance().getPool(),
				new ErGzbSqlBuilder());
		erDblgzbDao = new ErDblgzbDao(DbPoolFactory.getInstance().getPool(),
				new ErDblgzbSqlBuilder());
		erYebDao = new ErYebDao(DbPoolFactory.getInstance().getPool(),
				new ErYebSqlBuilder());
		erKmbDao = new ErKmbDao(DbPoolFactory.getInstance().getPool(),
				new ErKmbSqlBuilder());
		erZcfzbDao = new ErZcfzbDao(DbPoolFactory.getInstance().getPool(),
				new ErZcfzbSqlBuilder());
		erLrbDao = new ErLrbDao(DbPoolFactory.getInstance().getPool(),
				new ErLrbSqlBuilder());
		erSyzqybDao = new ErSyzqybDao(DbPoolFactory.getInstance().getPool(),
				new ErSyzqybSqlBuilder());
		erJzcbdbDao = new ErJzcbdbDao(DbPoolFactory.getInstance().getPool(),
				new ErJzcbdbSqlBuilder());
		dao = serviceDao;
	}

	public TransPojo getTransPojo() {
		return transPojo;
	}

	public void setTransPojo(TransPojo transPojo) {
		this.transPojo = transPojo;
	}

	public String getAssCode() {
		return assCode;
	}

	public void setAssCode(String assCode) {
		this.assCode = assCode;
	}
	
	public boolean isSplit() {
		return isSplit;
	}

	public void setSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}

	public void initSplitParams(TransPojo transPojo,String assCode, boolean isSplit)
	{
		this.assCode = assCode;
		this.transPojo = transPojo;
		this.isSplit = isSplit;
	}

	/***
	 * 查询对账数据
	 * modified by liyanjun 2016-4-9 STORY #29166 电子对账解析结果不符合要求
	 * 背景：电子对账中，托管反馈的结果包含所有数据(包含差异数据和核对一致数据)，其中字段“result”表示核对结果，0 表示核对数据一致，
	 *  	 否则是详细对账结果。但是估值系统将托管反馈的核对数据全部当做是差异数据，并将所有结果显示在差异数据查询中，
	 *       现需要对反馈数据进行判断，在所有数据中显示所有数据，差异数据中显示核对不一致的数据。
	 * 适用场景：电子对账管理界面中点击已反馈，查询对账不一致的数据（差异数据和全部数据）
	 * 业务逻辑：
	 *  1、如果选择“差异数据”，则会直接从结果表中查询数据T_D_ER_RESULT（并过滤掉对账一致的数据）
	 *  2、如果选择“全部数据”，则会先查出差异和已生成的数据，再从已生成中过滤掉差异数据
	 * 逻辑更改：查询差异数据时过滤掉对账一致的数据，如果对账结果（C_RESULT）值为0、一致、对账一致时表示对账一致
	 * STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展
	 */
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String ARRAY_C_KM_CODE = "";
		String showType = "";
		String classString = "";
		String fileType = "";
		Class<?> clazz = null;
		List<BasePojo> cyDataList = new ArrayList<BasePojo>();
		List<BasePojo> allDataList = new ArrayList<BasePojo>();
		try {
			if(paraMap != null){
				classString = String.valueOf(paraMap.get("dataClass"));
				clazz = PojoLoader.getPojoClassById(classString, bundleContext);
				paraMap.remove("dataClass");
				if(paraMap.containsKey("ARRAY_C_KM_CODE"))
				{
					ARRAY_C_KM_CODE = String.valueOf(paraMap.get("ARRAY_C_KM_CODE"));
					paraMap.remove("ARRAY_C_KM_CODE");
				}
			}
			String date = null;
			if (null != paraMap) {
				if (paraMap.containsKey("SHOW_TYPE")) {
					showType = (String) paraMap.get("SHOW_TYPE");
					paraMap.remove("SHOW_TYPE");
				}
				// /如果参数中有对标识需要转换
				if (paraMap.containsKey("C_CHECK_FLAG")) {
					ElecGroupRela ecr = ErConfigUtil
							.getElecGroupRelaById((String) paraMap
									.get("C_CHECK_FLAG"));
					fileType = (String) paraMap.get("C_CHECK_FLAG");
					paraMap.remove("C_CHECK_FLAG");
					paraMap.put("C_CHECK_FLAG", ecr.getC_RESULT_CODE()); 
				}
				
				if(paraMap.containsKey("D_DATE")){
					date = (String)paraMap.get("D_DATE");
					paraMap.remove("D_DATE");
				}
			}

			if ("1011".equalsIgnoreCase(fileType)&&!"".equalsIgnoreCase(ARRAY_C_KM_CODE))//估值表
			{
				HashMap<String,Object> rMap = new HashMap<String,Object>();
				rMap.putAll(paraMap);
				rMap.put("ARRAY_C_B_CODE", ARRAY_C_KM_CODE);
				cyDataList = serviceDao.queryByCondition(rMap, clazz);
			}else
			{
				cyDataList = serviceDao.queryByCondition(paraMap, clazz);
			}
			Map<String, String> secCodeMap = new HashMap<String, String>();
			// 只查询差异数据
			if(true){//差异数据一定会查询
//			if ("".equals(showType) || "CY_DATA".equals(showType)) {
				Map<String, String> kmMap = null;
				List<BasePojo> list = new ArrayList<BasePojo>();
				for (BasePojo pojo : cyDataList) {
					ErResultQuery result = (ErResultQuery) pojo;
					if(kmMap == null){
						kmMap = new ErBbInfoService().getKmMap2(result.getC_SN(), fileType, date);
					}
					String res = result.getC_RESULT();
					//如果对账一致（对账结果为0、一致、对账一致），则不显示在差异数据里面
					if (res.trim().equalsIgnoreCase("0") || (res.contains("一致") && !res.contains("不一致"))) {
						continue;
					}else{
						list.add(pojo);
					}
					
					if (result.getC_D_KM_CODE() != null
							&& result.getC_D_KM_CODE().trim().length() > 0) {
						if (kmMap.containsKey(result.getC_D_KM_CODE())) {
							result.setC_D_KM_NAME(kmMap.get(result
									.getC_D_KM_CODE()).split("&&")[0]);
						}
					}

					if (result.getC_B_KM_CODE() != null
							&& result.getC_B_KM_CODE().trim().length() > 0) {
						if (kmMap.containsKey(result.getC_B_KM_CODE())) {
							result.setC_B_KM_NAME(kmMap.get(result
									.getC_B_KM_CODE()).split("&&")[0]);
							result.setC_B_KM_CODE(kmMap.get(result
									.getC_B_KM_CODE()).split("&&")[1]);
						}
					}
					//STORY58400汇添富项目，电子对账，成本包含溢折价的4-2-6科目，需要取到证券名称
					if(!StringUtil.IsNullOrEmpty(result.getC_D_KM_CODE()) && result.getC_D_KM_CODE().trim().length() == 12 && result.getC_D_KM_CODE().trim().startsWith("11")
							&& StringUtil.IsNullOrEmpty(result.getC_D_KM_NAME())){//判断对方科目代码不为空且科目代码长度为12（426），并且对方科目名称为空
						String secCode = result.getC_D_KM_CODE().trim().substring(6, 12);//截取科目代码的后六位
						String secName = "";
						if(secCodeMap.containsKey(secCode)){//map是否已存在该证券代码，若存在从map去证券名称，否则从数据库中取并放入map
							secName = secCodeMap.get(secCode);
						}else{
							Map<String, String> secMap= getSecMap(secCode);
							if (!secMap.isEmpty()) {
								secName = secMap.get(secCode);
								secCodeMap.putAll(secMap);
							}
						}
						result.setC_D_KM_NAME(secName);
					}
					if(!StringUtil.IsNullOrEmpty(result.getC_B_KM_CODE()) && result.getC_B_KM_CODE().trim().length() == 12 && result.getC_B_KM_CODE().trim().startsWith("11")
							&& StringUtil.IsNullOrEmpty(result.getC_B_KM_NAME())){//判断本方科目代码不为空且科目代码长度为12（426），并且本方科目名称为空
						String secCode = result.getC_B_KM_CODE().trim().substring(6, 12);//截取科目代码的后六位
						String secName = "";
						if(secCodeMap.containsKey(secCode)){//map是否已存在该证券代码，若存在从map去证券名称，否则从数据库中取并放入map
							secName = secCodeMap.get(secCode);
						}else{
							Map<String, String> secMap= getSecMap(secCode);
							if (!secMap.isEmpty()) {
								secName = secMap.get(secCode);
								secCodeMap.putAll(secMap);
							}
						}
						result.setC_B_KM_NAME(secName);
					}
				}
				cyDataList = list;
			}
			// 查询本次报文批号对应的所有核对数据
			if ("ALL_DATA".equals(showType)) {
				
				paraMap.remove("C_CHECK_FLAG");
				// 估值表
				if ("1011".equalsIgnoreCase(fileType)) {
					//STORY #41248 【南方基金】电子对账反馈结果中的差异数据下拉框扩展
					//估值表添加主要指标查询条件
					if(!"".equalsIgnoreCase(ARRAY_C_KM_CODE))
					{
						HashMap<String,Object> gzMap = new HashMap<String,Object>();
						gzMap.putAll(paraMap);
						gzMap.put("ARRAY_C_KM_CODE", ARRAY_C_KM_CODE);
						//20171121 wlx BUG180889【中国银行】电子对账生成后已生成记录中指标项重复 
						allDataList = erGzbDao
								.queryGzbData(gzMap, PojoLoader
										.getPojoClassById("ErGzb", bundleContext));
					}else
					{
						//20171121 wlx BUG180889【中国银行】电子对账生成后已生成记录中指标项重复 
						allDataList = erGzbDao
								.queryGzbData(paraMap, PojoLoader
										.getPojoClassById("ErGzb", bundleContext));
					}
					
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.gzToResult(allDataList));
				} else if ("1013".equalsIgnoreCase(fileType)) {
					// 双估值表
					allDataList = erDblgzbDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErDblgzb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.dblgzToResult(allDataList));
				} else if ("1001".equalsIgnoreCase(fileType)) {
					// 余额表
					allDataList = erYebDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErYeb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.yeToResult(allDataList));
				} else if (fileType.indexOf("1701")>0 || fileType.indexOf("1711")>0) {
					// 资产负债表
					allDataList = erZcfzbDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErZcfzb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.zcfzToResult(allDataList));
				} else if (fileType.indexOf("1801")>0 || fileType.indexOf("1811")>0) {
					// 利润表
					allDataList = erLrbDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErLrb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.lrToResult(allDataList));
				} else if (fileType.indexOf("1901")>0) {
					// 所有者权益（基金净值）变动表
					allDataList = erSyzqybDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErSyzqyb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.syzqyToResult(allDataList));
				} else if (fileType.indexOf("1903")>0) {
					// 所有者权益（基金净值）变动表
					allDataList = erJzcbdbDao
							.queryByCondition(paraMap, PojoLoader
									.getPojoClassById("ErJzcbdb", bundleContext));
					// 过滤掉差异数据
					this.filterCYData(allDataList, cyDataList, fileType, date);

					cyDataList.addAll(this.jzcbdbToResult(allDataList));
				} 
			}
			filterResultDetail(cyDataList);
			queryRes.setDataList(cyDataList);
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}
	
	/**
	 * STORY58400汇添富项目，电子对账，成本包含溢折价的4-2-6科目，需要取到证券名称
	 * @param secCode 证券代码或转换后的披露代码
	 * @return 证券代码，证券名称
	 * @throws Exception
	 */
	public Map<String, String> getSecMap(String secCode) throws Exception
	{
		Map<String, String> allmap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try{
			StringBuilder builder = new StringBuilder();

			builder.append("SELECT A.C_TD_CHAN_CODE,A.C_TD_CHAN_NAME,B.C_PUB_CODE FROM T_P_AB_TD_CHAN A ");
			builder.append(" JOIN ( "); 
			builder.append(" SELECT C_SEC_CODE,C_PUB_CODE"); 
			builder.append("  FROM T_D_MP_SEC_TRANSFER "); 
			builder.append(" WHERE C_TRANSFER_CODE = 'TRAN_SBLSH' AND C_TYPE ='R'"); 
			builder.append("   AND N_CHECK_STATE = 1 AND C_PUB_CODE = ?)B"); 
			builder.append("   ON A.C_TD_CHAN_CODE = B.C_SEC_CODE"); 
			builder.append(" UNION ALL");
			builder.append(" SELECT A.C_TD_CHAN_CODE,A.C_TD_CHAN_NAME,NULL FROM T_P_AB_TD_CHAN A ");
			builder.append(" WHERE A.C_TD_CHAN_CODE = ? "); 
			builder.append(" UNION ALL"); 
			builder.append(" SELECT A.C_SEC_CODE,A.C_SEC_NAME,B.C_PUB_CODE FROM T_P_SV_SEC_BASE A"); 
			builder.append("  JOIN(SELECT C_SEC_CODE,C_PUB_CODE"); 
			builder.append("  FROM T_D_MP_SEC_TRANSFER"); 
			builder.append(" WHERE C_TRANSFER_CODE = 'TRAN_SBLSH' AND C_TYPE ='P'"); 
			builder.append("   AND N_CHECK_STATE = 1 AND C_PUB_CODE = ?)B"); 
			builder.append("   ON A.C_SEC_CODE = B.C_SEC_CODE"); 
			builder.append(" UNION ALL");
			builder.append(" SELECT A.C_SEC_CODE,A.C_SEC_NAME,NULL FROM T_P_SV_SEC_BASE A"); 
			builder.append(" WHERE A.C_SEC_CODE LIKE ? ");

			conn = serviceDao.loadNewConnection();
			pst = conn.prepareStatement(builder.toString());
			int index = 1;
			pst.setString(index++, secCode);
			pst.setString(index++, secCode);
			pst.setString(index++, secCode);
			pst.setString(index++, secCode + "%");
			rs = pst.executeQuery();
			while(rs.next())
			{
				if(rs.getString(2) != null && !allmap.containsKey(secCode)){
					allmap.put(secCode, rs.getString(2));
				}
				if (rs.getString(3) != null && !allmap.containsKey(rs.getString(3))) {
					allmap.put(rs.getString(3), rs.getString(2));
				}
			}
		}catch(Exception e){
			logger.log("获取证券代码、证券名称出错！", e);
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}
		return allmap;
	}
	/**
	 * STORY #90279 【华宝信托】电子对账功能优化 
	 * 例：[不一致], [托管行-行情价格 = 0, 公司方-行情价格 = 0, 差异-行情价格 = 0], [托管行-数量 = 0, 公司方-数量 = 0, 差异-数量 = 0], [托管行-成本 = 104125, 公司方-成本 = 104124.98, 差异-成本 = -0.02], 
	 * [托管行-市值 = 104125, 公司方-市值 = 104124.98, 差异-市值 = -0.02], [托管行-估值增值 = 0, 公司方-估值增值 = 0, 差异-估值增值 = 0], 
	 * [托管行-成本占净值比例 = 0.0336, 公司方-成本占净值比例 = 0.0336, 差异-成本占净值比例 = 0], [托管行-市值占净值比例 = 0.0336, 公司方-市值占净值比例 = 0.0336, 差异-市值占净值比例 = 0]
	 * @param dataList
	 */
	private void filterResultDetail(List<BasePojo> dataList){
		Connection conn = null;
		try{
			if(null != dataList){
				String resultDetail = "";
				if(dataList.size() > 0){
					//获取参数值
					conn = YssDbPoolFactory.getInstance().getDbPool("YSSELECRECO").getConnection();// 获取数据库连接
					ErResultQuery erResult = (ErResultQuery) dataList.get(0);
					resultDetail = getResultDetailPara(erResult, conn);
				}
				for (BasePojo basePojo : dataList) {
					ErResultQuery erResult = (ErResultQuery)basePojo;
					if("1".equals(resultDetail) && !StringUtil.IsNullOrEmptyT(erResult.getC_RESULT())){
						String[] results = erResult.getC_RESULT().split("],");
						for (String result : results) {
							String[] resultItem = result.split(",");
							if(null != resultItem && resultItem.length == 3){
								String lastValue = result.substring(result.lastIndexOf("=") + 1);
								if(!StringUtil.IsNullOrEmptyT(lastValue)){
									String sp = "],";
									if(lastValue.lastIndexOf("]") > 0){
										sp = "";
									}
									lastValue = lastValue.replaceAll(" ", "").replace("]", "");
									if(YssFun.isNumeric(lastValue)  && BigDecimal.ZERO.compareTo(new BigDecimal(lastValue)) == 0){
										erResult.setC_RESULT(erResult.getC_RESULT().replace(result+sp, ""));
									}
								}
							}
						}
					}
				}
			}
		}catch(Exception e){
			logger.error("过滤对账结果数据异常：",e);
		}finally {
            DbFun.releaseConnection(conn);
        }
	}

	private String getResultDetailPara(ErResultQuery erResult,Connection conn) throws Exception{
		String resultDetail = "0";
		//根据资产代码取组合代码
		IPortDataService portService = YssServiceFactory.getInstance()
				.createService(IPortDataService.class);
		Port port = portService.getPortByAssCode(erResult.getC_ASS_CODE());
		String portCode = erResult.getC_ASS_CODE();
		if(null != port){
			portCode = port.getC_PORT_CODE();
		}
		//获取参数值
		AdmPortActParams paras = new AdmPortActParams(portCode, new Date());
		paras.setDbConn(conn);
		paras.initActParams();
		/**
		 * 是：只显示托管行返回的不一致数据
		 * 否：显示托管行返回的所有信息（系统现有规则）
		 */
		resultDetail = paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_XQHDJGXS);
		return resultDetail;
	}
	
	/**
	 * 从全部数据中过滤掉差异数据
	 * 
	 * @param allDataList
	 *            全部数据
	 * @param cyDataList
	 *            存在差异的数据
	 * @param resultType
	 *            核对类型
	 * @param date
	 * 			核对日期
	 * @throws Exception
	 */
	private void filterCYData(List<BasePojo> allDataList,
			List<BasePojo> cyDataList, String resultType, String date) throws Exception {
		if (allDataList == null || cyDataList == null) {
			return;
		}
		try {
			RecordElement record = new RecordElement();
			Map<String, String> kmMap = null;
			Map<String,ErResultQuery> cyMap = new HashMap<String, ErResultQuery>();
			for(BasePojo cYPojo:cyDataList)
			{
				ErResultQuery result = (ErResultQuery) cYPojo;
				if(kmMap == null){
					kmMap = new ErBbInfoService().getKmMap2(result.getC_SN(), resultType, date);
				}
				//设置科目代码名称
				if (result.getC_D_KM_CODE() != null
						&& result.getC_D_KM_CODE().trim().length() > 0) {
					if (kmMap.containsKey(result.getC_D_KM_CODE())) {
						result.setC_D_KM_NAME(kmMap.get(result
								.getC_D_KM_CODE()).split("&&")[0]);
					}
				}

				if (result.getC_B_KM_CODE() != null
						&& result.getC_B_KM_CODE().trim().length() > 0) {
					if (kmMap.containsKey(result.getC_B_KM_CODE())) {
						result.setC_B_KM_NAME(kmMap.get(result
								.getC_B_KM_CODE()).split("&&")[0]);
					}
				}
				cyMap.put(result.getC_B_KM_CODE(), result);
			}
			// 将已经存在的科目从结果集中过滤
			for (Iterator<BasePojo> iterator = allDataList.iterator(); iterator
					.hasNext();) {
				BasePojo basePojo = iterator.next();
				Field field = null;
				Field pfield = null;
				if(resultType.indexOf("_")<0) {
					field = basePojo.getClass().getDeclaredField("c_KM_CODE");
					pfield = basePojo.getClass().getDeclaredField("c_KM_CODE_P");
				} else {
					field = basePojo.getClass().getDeclaredField("c_INDEX_CODE");
				}
				
				field.setAccessible(true);
				String oldKmCode = (String) field.get(basePojo);
				
				if(basePojo instanceof ErGzb){
					oldKmCode = ((ErGzb) basePojo).getC_KM_CODE_TEMP();
				}
				
				String resultKmCode =record.getKmCode(oldKmCode);

				if(cyMap.containsKey(resultKmCode))
				{
					ErResultQuery erResult = cyMap.get(resultKmCode);
					erResult.setC_B_KM_CODE(oldKmCode);
					//有父节点的设置父节点
					if(pfield!=null)
					{
						pfield.setAccessible(true);
						if(((String) pfield.get(basePojo)).trim().equalsIgnoreCase(""))
						{
							erResult.setC_B_KM_CODE_P("root");
						}else
						{
							erResult.setC_B_KM_CODE_P((String) pfield.get(basePojo));
						}
					}
					iterator.remove();
				}
				/*for (BasePojo cYPojo : cyDataList) {
					ErResultQuery result = (ErResultQuery) cYPojo;
					
					if(kmMap == null){
						kmMap = new ErBbInfoService().getKmMap2(result.getC_SN(), resultType, date);
					}
					
					if (kmCode.equals(result.getC_B_KM_CODE())) {
						if (kmMap.containsKey(result.getC_B_KM_CODE())) {
							result.setC_B_KM_CODE(kmMap.get(result
									.getC_B_KM_CODE()).split("&&")[1]);
						}
						iterator.remove();
						break;
					}else{
						
						if (result.getC_D_KM_CODE() != null
								&& result.getC_D_KM_CODE().trim().length() > 0) {
							if (kmMap.containsKey(result.getC_D_KM_CODE())) {
								result.setC_D_KM_NAME(kmMap.get(result
										.getC_D_KM_CODE()).split("&&")[0]);
							}
						}

						if (result.getC_B_KM_CODE() != null
								&& result.getC_B_KM_CODE().trim().length() > 0) {
							if (kmMap.containsKey(result.getC_B_KM_CODE())) {
								result.setC_B_KM_NAME(kmMap.get(result
										.getC_B_KM_CODE()).split("&&")[0]);
							}
						}
					}
				}*/
			}
		} catch (Exception e) {
			throw e;
		}
	}

	/**
	 * 将估值pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> gzToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		
		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErGzb erGzb = (ErGzb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_CODE(erGzb.getC_KM_CODE());
				erResult.setC_D_KM_CODE(erGzb.getC_KM_CODE());
				erResult.setC_B_KM_NAME(erGzb.getC_KM_NAME());
				erResult.setC_D_KM_NAME(erGzb.getC_KM_NAME());
				erResult.setN_B_SL(erGzb.getN_AMOUNT());
				erResult.setN_D_SL(erGzb.getN_AMOUNT());
				erResult.setN_B_JE(erGzb.getN_PORT_MV());
				erResult.setN_D_JE(erGzb.getN_PORT_MV());
				erResult.setN_B_JEO(erGzb.getN_PORT_COST());
				erResult.setN_D_JEO(erGzb.getN_PORT_COST());
				erResult.setN_B_JET(erGzb.getN_PORT_IV());
				erResult.setN_D_JET(erGzb.getN_PORT_IV());
//				STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				//STORY41248【南方基金】电子对账反馈结果中的差异数据下拉框扩展	设置父节点
				if("".equalsIgnoreCase(erGzb.getC_KM_CODE_P().trim()))
				{
					erResult.setC_B_KM_CODE_P("root");
				}else
				{
					erResult.setC_B_KM_CODE_P(erGzb.getC_KM_CODE_P());
				}
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}
	
	/**
	 * 将估值pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> dblgzToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		
		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErDblgzb erGzb = (ErDblgzb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_CODE(erGzb.getC_KM_CODE());
				erResult.setC_D_KM_CODE(erGzb.getC_KM_CODE());
				erResult.setC_B_KM_NAME(erGzb.getC_KM_NAME());
				erResult.setC_D_KM_NAME(erGzb.getC_KM_NAME());
				erResult.setN_B_SL(erGzb.getN_AMOUNT());
				erResult.setN_D_SL(erGzb.getN_AMOUNT());
				erResult.setN_B_JE(erGzb.getN_PORT_MV());
				erResult.setN_D_JE(erGzb.getN_PORT_MV());
				erResult.setN_B_JEO(erGzb.getN_PORT_COST());
				erResult.setN_D_JEO(erGzb.getN_PORT_COST());
				erResult.setN_B_JET(erGzb.getN_PORT_IV());
				erResult.setN_D_JET(erGzb.getN_PORT_IV());
//				STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				//STORY41248【南方基金】电子对账反馈结果中的差异数据下拉框扩展	设置父节点
				if("".equals(erGzb.getC_KM_CODE_P().trim()))
				{
					erResult.setC_B_KM_CODE_P("root");
				}else
				{
					erResult.setC_B_KM_CODE_P(erGzb.getC_KM_CODE_P());
				}
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}

	/**
	 * 将余额pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> yeToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErYeb erYeb = (ErYeb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_NAME(erYeb.getC_KM_NAME());
				erResult.setC_D_KM_NAME(erYeb.getC_KM_NAME());
				erResult.setC_B_KM_CODE(erYeb.getC_KM_CODE());
				erResult.setC_D_KM_CODE(erYeb.getC_KM_CODE());
				erResult.setN_B_SL(erYeb.getN_AMOUNT_ENDBAL());
				erResult.setN_D_SL(erYeb.getN_AMOUNT_ENDBAL());
				erResult.setN_B_JE(erYeb.getN_PORT_ENDBAL());
				erResult.setN_D_JE(erYeb.getN_PORT_ENDBAL());
				erResult.setN_B_JEO(erYeb.getN_PORT_CREDIT());
				erResult.setN_D_JEO(erYeb.getN_PORT_CREDIT());
				erResult.setN_B_JET(erYeb.getN_PORT_DEBIT());
				erResult.setN_D_JET(erYeb.getN_PORT_DEBIT());
				//STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				//STORY41248【南方基金】电子对账反馈结果中的差异数据下拉框扩展	设置父节点
				if("".equalsIgnoreCase(erYeb.getC_KM_CODE_P().trim()))
				{
					erResult.setC_B_KM_CODE_P("root");
				}else
				{
					erResult.setC_B_KM_CODE_P(erYeb.getC_KM_CODE_P());
				}
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}

//	/**
//	 * 将科目pojo转换成核对结果对象
//	 * 
//	 * @param astPojoList
//	 * @return
//	 */
//	private List<BasePojo> kmToResult(List<BasePojo> astPojoList) {
//
//		List<BasePojo> pojoList = new ArrayList<BasePojo>();
//
//		if (astPojoList != null && astPojoList.size() > 0) {
//			for (BasePojo pojo : astPojoList) {
//				ErGzb erGzb = (ErGzb) pojo;
//				ErResult erResult = new ErResult();
//				erResult.setC_B_KM_CODE(erGzb.getC_KM_CODE());
//				erResult.setC_D_KM_CODE(erGzb.getC_KM_CODE());
//				erResult.setN_B_SL(erGzb.getN_AMOUNT());
//				erResult.setN_D_SL(erGzb.getN_AMOUNT());
//				erResult.setN_B_JE(erGzb.getN_PORT_MV());
//				erResult.setN_D_JE(erGzb.getN_PORT_MV());
//				erResult.setN_B_JEO(erGzb.getN_PORT_COST());
//				erResult.setN_D_JEO(erGzb.getN_PORT_COST());
//				erResult.setN_B_JET(erGzb.getN_PORT_IV());
//				erResult.setN_D_JET(erGzb.getN_PORT_IV());
//			}
//		}
//
//		return pojoList;
//	}
	/**
	 * 将资产负债pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> zcfzToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErZcfzb er = (ErZcfzb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_D_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_B_KM_CODE(er.getC_INDEX_CODE());
				erResult.setC_D_KM_CODE(er.getC_INDEX_CODE());
				erResult.setN_B_JE(new BigDecimal(er.getN_BEGIN_VALUE().isEmpty()? "0":er.getN_BEGIN_VALUE()));
				erResult.setN_D_JE(new BigDecimal(er.getN_BEGIN_VALUE().isEmpty()? "0":er.getN_BEGIN_VALUE()));
				erResult.setN_B_JEO(new BigDecimal(er.getN_END_VALUE().isEmpty()? "0":er.getN_END_VALUE()));
				erResult.setN_D_JEO(new BigDecimal(er.getN_END_VALUE().isEmpty()? "0":er.getN_END_VALUE()));
//				  STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}
	
	/**
	 * 将利润pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> lrToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErLrb er = (ErLrb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_D_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_B_KM_CODE(er.getC_INDEX_CODE());
				erResult.setC_D_KM_CODE(er.getC_INDEX_CODE());
				erResult.setN_B_JE(new BigDecimal(er.getN_CUR_VALUE().isEmpty()? "0":er.getN_CUR_VALUE()));
				erResult.setN_D_JE(new BigDecimal(er.getN_CUR_VALUE().isEmpty()? "0":er.getN_CUR_VALUE()));
				erResult.setN_B_JEO(new BigDecimal(er.getN_TOL_VALUE().isEmpty()? "0":er.getN_TOL_VALUE()));
				erResult.setN_D_JEO(new BigDecimal(er.getN_TOL_VALUE().isEmpty()? "0":er.getN_TOL_VALUE()));
//				  STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}
	
	/**
	 * 将利润pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> syzqyToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErSyzqyb er = (ErSyzqyb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_D_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_B_KM_CODE(er.getC_INDEX_CODE());
				erResult.setC_D_KM_CODE(er.getC_INDEX_CODE());
				erResult.setN_B_JE(new BigDecimal(er.getN_THIS_NAV().isEmpty()? "0":er.getN_THIS_NAV()));
				erResult.setN_D_JE(new BigDecimal(er.getN_THIS_NAV().isEmpty()? "0":er.getN_THIS_NAV()));
				erResult.setN_B_JEO(new BigDecimal(er.getN_THIS_INTERESTS().isEmpty()? "0":er.getN_THIS_INTERESTS()));
				erResult.setN_D_JEO(new BigDecimal(er.getN_THIS_INTERESTS().isEmpty()? "0":er.getN_THIS_INTERESTS()));
				erResult.setN_B_JET(new BigDecimal(er.getN_LAST_NAV().isEmpty()? "0":er.getN_LAST_NAV()));
				erResult.setN_D_JET(new BigDecimal(er.getN_LAST_NAV().isEmpty()? "0":er.getN_LAST_NAV()));
				erResult.setN_B_JE3(new BigDecimal(er.getN_LAST_UNPROFIT().isEmpty()? "0":er.getN_LAST_UNPROFIT()));
				erResult.setN_D_JE3(new BigDecimal(er.getN_LAST_UNPROFIT().isEmpty()? "0":er.getN_LAST_UNPROFIT()));
				erResult.setN_B_JE4(new BigDecimal(er.getN_LAST_INTERESTS().isEmpty()? "0":er.getN_LAST_INTERESTS()));
				erResult.setN_D_JE4(new BigDecimal(er.getN_LAST_INTERESTS().isEmpty()? "0":er.getN_LAST_INTERESTS()));
				erResult.setN_B_JE5(new BigDecimal(er.getN_THIS_UNPROFIT().isEmpty()? "0":er.getN_THIS_UNPROFIT()));
				erResult.setN_D_JE5(new BigDecimal(er.getN_THIS_UNPROFIT().isEmpty()? "0":er.getN_THIS_UNPROFIT()));
//				  STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}
	
	/**
	 * 将利润pojo转换成核对结果对象
	 * 
	 * @param astPojoList
	 * @return
	 */
	private List<BasePojo> jzcbdbToResult(List<BasePojo> astPojoList) {

		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		if (astPojoList != null && astPojoList.size() > 0) {
			for (BasePojo pojo : astPojoList) {
				ErJzcbdb er = (ErJzcbdb) pojo;
				ErResultQuery erResult = new ErResultQuery();
				erResult.setC_B_KM_CODE(er.getC_INDEX_CODE());
				erResult.setC_D_KM_CODE(er.getC_INDEX_CODE());
				erResult.setC_B_KM_NAME(er.getC_INDEX_NAME());
				erResult.setC_D_KM_NAME(er.getC_INDEX_NAME());
				erResult.setN_B_JE(er.getN_CUR_VALUE());
				erResult.setN_D_JE(er.getN_CUR_VALUE());
				erResult.setN_B_JEO(er.getN_TOL_VALUE());
				erResult.setN_D_JEO(er.getN_TOL_VALUE());
//				  STORY #51518 南方基金-“电子对账详细信息”只显示存在差异的数据。 增加赋值方便前台判断
				erResult.setC_RESULT("核对一致");
				pojoList.add(erResult);
			}
		}

		return pojoList;
	}

	@Override
	public QueryRes queryOrigDataByCondition(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String resultType = "";
		List<HeadKey> listHeadInfo = null;
		List<BasePojo> pojoList = null;
		String classString = "";
		Class<?> clazz;
		try {
			resultType = (String) paraMap.get("RESULT_TYPE");
			paraMap.remove("RESULT_TYPE");
			classString = (String) paraMap.get("dataClass");
			paraMap.remove("dataClass");
			clazz = PojoLoader.getPojoClassById(classString, bundleContext);
			if ("01".equalsIgnoreCase(resultType)) {
				listHeadInfo = ServiceAssistance.getListHead("erGzb");
				pojoList = erGzbDao.queryByCondition(paraMap, clazz);
				menuId = "erGzb";
			} else if ("00".equalsIgnoreCase(resultType)) {
				listHeadInfo = ServiceAssistance.getListHead("erYeb");
				pojoList = erYebDao.queryByCondition(paraMap, clazz);
				menuId = "erYeb";
			} else if ("02".equalsIgnoreCase(resultType)) {
				listHeadInfo = ServiceAssistance.getListHead("erKmb");
				pojoList = erKmbDao.queryByCondition(paraMap, clazz);
				menuId = "erKmb";
			}

			// 差异查询
			if (resultType.equalsIgnoreCase("CY_DATA")) {
				List<BasePojo> list = new ArrayList<BasePojo>();
				for (BasePojo pojo : pojoList) {
					ErResult result = (ErResult) pojo;
					if (result.getN_B_JE() != result.getN_D_JE()
							|| result.getN_B_SL() != result.getN_D_SL()
							|| result.getN_B_JEO() != result.getN_D_JEO()
							|| result.getN_B_JET() != result.getN_D_JET()) {
						list.add(pojo);
					}
				}
				pojoList = list;
			}

			queryRes.setDataList(pojoList);
			queryRes.setHeadKeyList(listHeadInfo);

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}

	/***
	 * 该方法取反，针对正向收到的对账结果
	 * 托管行反馈的数据中本方指的是托管行，对手方指的是估值系统
	 * 所以此处需要将两方进行对调
	 * 对调后本方指估值系统，对手方指托管行
	 * 
	 * modified by liyanjun 2016-4-9 STORY #29166 电子对账解析结果不符合要求
	 * 业务逻辑：处理托管反馈的对账结果，并插入对账结果表中（T_D_ER_RESULT），
	 * 			托管反馈的结果包含所有数据(包含差异数据和核对一致数据)
	 * 逻辑更改：调整差异数据的计数逻辑，如果对账一致则不计入差异数据
	 * @param root
	 * @param checkFlag
	 * @throws Exception
	 */
	public void insert(XmlResult root) throws Exception {
		try {
			long startTime = System.currentTimeMillis();
			String tgh = this.getParaByAssCodeAndDeptCode(root.getC_ASS_CODE(),root.getC_DEPT_CODE());
			String tghInner = "";
			if (!StringUtil.IsNullOrEmptyT(tgh)) {
				tghInner = this.getInnerOrgCodeByOrgCode(tgh);
			}
			
			List<ErResultElement> list = root.getResultList();
			ErBbInfoService service = new ErBbInfoService();
			ArrayList<BasePojo> pojoList = new ArrayList<BasePojo>();
			String state = "ER_ACCECPED";
			String errInfo = "";
			String fileType = "";
			String dealer = "";//托管行处理人
			ErResult result = null;
			Map<String, String> resultMap = ErConfigUtil.getResultMap();  ///// 文件类型放入缓存
			int errCount = 0;
			for (ErResultElement e : list) {
				/**
				 * 判断对账结果
				 */
				String res = e.getC_RESULT();
				/// by weijj 20151231 有些银行返回汉字  bug120751 
				///如果对账结果不为0、一致、对账一致，则表示差异数据
				if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
					errCount++;
				}
				result = new ErResult();
				result.setC_SN(root.getC_SN());
				result.setC_ASS_CODE(root.getC_ASS_CODE());
				result.setC_RPT_TYPE(root.getC_RPT_TYPE());
				result.setC_FILE_TYPE(root.getC_FILE_TYPE());
				String startDate = root.getD_START_DATE();
				if (startDate.trim().length() > 0) {
					Date date = DateUtil.stringtoDate(root.getD_START_DATE(),
							"yyyyMMdd");
					startDate = DateUtil.dateToString(date,
							DateUtil.LONG_DATE_FORMAT);
				} else {
					startDate = DateUtil.getNow();
				}

				result.setD_START_DATE(startDate);
				String endDate = root.getD_END_DATE();
				if (endDate.trim().length() > 0) {
					Date date = DateUtil.stringtoDate(endDate, "yyyyMMdd");
					endDate = DateUtil
							.dateToString(date, DateUtil.LONG_DATE_FORMAT);
				} else {
					endDate = DateUtil.getNow();
				}
				String flag = e.getC_CHECK_FLAG();
				if ("ICBC".equalsIgnoreCase(tghInner)) {
					if ("6".equalsIgnoreCase(flag)) {
						flag = "16";
					}else if ("7".equalsIgnoreCase(flag)) {
						flag = "17";
					}
				}
				//8：净资产变动表(投资户)； 9：净资产变动表(受托户)； 10：净资产变动表(计划)；
				if("8".equalsIgnoreCase(flag) || "9".equalsIgnoreCase(flag) || "10".equalsIgnoreCase(flag) || "8910".equalsIgnoreCase(flag))
				{
					flag = "8";
				}
				fileType = resultMap.get(root.getC_RPT_TYPE()+"_"+flag);
				if(fileType == null){
					logger.info(flag +"  没有文件类型"+JsonUtil.toString(resultMap));
					fileType = " ";
					continue;
				}else if(fileType.indexOf("_")>0){//月报取出来的filetype为03_1701,BUG153072南方基金-招行月报电子对账反馈未显示在前台
					fileType = fileType.split("_",-1)[1];
				}
				result.setC_CHECK_FLAG(flag);
				result.setD_END_DATE(endDate);
				result.setC_B_BY1(e.getC_D_BY1());
				//BUG211948【富国基金】电子对账字段长度不够报错 
				if(e.getC_D_CODE() != null && e.getC_D_CODE().getBytes().length > 200){
					byte[] src = e.getC_D_CODE().getBytes();
					byte[] dest = new byte[200];
					System.arraycopy(src, 0, dest, 0, 200);
					String destStr = new String(dest);
					result.setC_B_KM_CODE(destStr);
				}else{
					result.setC_B_KM_CODE(e.getC_D_CODE());
				}
				result.setC_D_BY1(e.getC_B_BY1());
				if (e.getC_B_CODE() != null && e.getC_B_CODE().getBytes().length > 200) {
					byte[] src = e.getC_B_CODE().getBytes();
					byte[] dest = new byte[200];
					System.arraycopy(src, 0, dest, 0, 200);
					String destStr = new String(dest);
					result.setC_D_KM_CODE(destStr);
				}else {
					result.setC_D_KM_CODE(e.getC_B_CODE());
				}

				dealer = e.getC_DEALER();
				result.setC_DEALER(e.getC_DEALER());
				result.setC_JS_TIME(DateUtil.getNow());
				result.setC_NOTE(e.getC_NOTE());
				result.setC_REF_NO(e.getC_REF_NO());
				//BUG244883托管行反馈余额表1022报文中RESULT字段数值为空时电子对账余额表反馈数据插入报错  RESULT节点为空，默认为对账不一致
				String cResult = StringUtil.IsNullOrEmptyT(e.getC_RESULT()) ? "不一致！" : e.getC_RESULT();
				result.setC_RESULT(subStrLen(cResult, 4000));
				String time = e.getD_TIME();
				if (time.trim().length() > 0) {
					// Date date = DateUtil.stringtoDate(time, "yyyyMMdd HH:mm:ss");
					// time = DateUtil.dateToString(date,
					// DateUtil.LONG_DATE_FORMAT);
				} else {
					time = DateUtil.getNow();
				}
				result.setD_TIME(time);
				result.setN_B_JE(getBigDecimal(e.getN_D_JE()));
				result.setN_B_JE3(getBigDecimal(e.getN_D_JE3()));
				result.setN_B_JE4(getBigDecimal(e.getN_D_JE4()));
				result.setN_B_JE5(getBigDecimal(e.getN_D_JE5()));
				result.setN_B_JEO(getBigDecimal(e.getN_D_JEO()));
				result.setN_B_JET(getBigDecimal(e.getN_D_JET()));
				result.setN_B_SL(getBigDecimal(e.getN_D_SL()));

				result.setN_D_JE(getBigDecimal(e.getN_B_JE()));
				result.setN_D_JE3(getBigDecimal(e.getN_B_JE3()));
				result.setN_D_JE4(getBigDecimal(e.getN_B_JE4()));
				result.setN_D_JE5(getBigDecimal(e.getN_B_JE5()));
				result.setN_D_JEO(getBigDecimal(e.getN_B_JEO()));
				result.setN_D_JET(getBigDecimal(e.getN_B_JET()));
				result.setN_D_SL(getBigDecimal(e.getN_B_SL()));
				pojoList.add(result);
			}
			long forEndTime = System.currentTimeMillis();
			logger.info("遍历对账结果耗时：" + (forEndTime - startTime));
			//如果差异数量大于0，则更改状态
			if (errCount > 0) {
				state = "ER_IDENTICAL";
				errInfo = errCount + "";
			}

			////增加容错机制  托管行系统如果只返回一笔 为空科目代码的对账结果，作为对账一致处理
			////add by lyj 2016-2-3 BUG #121476 电子对账历史不一致数据查看不了
			if(pojoList.size()==1){
				ErResult reault = (ErResult) pojoList.get(0);
				// 根据返回的对账结果来判断是否一致
				String res = reault.getC_RESULT();
			//STORY68731电子对账结果“缺少对账数据”的显示
			//BUG244883托管行反馈余额表1022报文中RESULT字段数值为空时电子对账余额表反馈数据插入报错  RESULT节点为空，默认为对账不一致
			if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
				if(StringUtil.IsNullOrEmptyT(reault.getC_B_KM_CODE())&&StringUtil.IsNullOrEmptyT(reault.getC_D_KM_CODE())){
					state = "ER_IDENTICAL";
					errInfo = "托管行缺少对账数据";
				}else
				{
					state = "ER_IDENTICAL";
					errInfo = "1个";
				}
			}else{
					state = "ER_ACCECPED";
					errInfo = "";
				}
			}
			
			IErBbInfoService erBbInfoService = YssServiceFactory.getInstance().createService(IErBbInfoService.class);
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("ARRAY_C_SN", root.getC_SN());
			paraMap.put("ARRAY_C_DZ_CODE", fileType);
			paraMap.put("dataClass", "ErBbInfo");
			QueryRes res = erBbInfoService.queryByCondition(paraMap);
			if(null != res && res.getDataList() != null && res.getDataList().size() > 0){
				List<BasePojo> basePojoList = res.getDataList();
				for (BasePojo basePojo : basePojoList) {
					ErBbInfo info = (ErBbInfo)basePojo;
					if("01".equalsIgnoreCase(info.getC_RPT_TYPE()) 
							&& ("已确认".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()) || "锁定".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()) 
							|| "LOCK".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()))){
						//更新状态变更记录
						IErStepStateService erStepService = YssServiceFactory.getInstance().createService(IErStepStateService.class);
						ErStepState erStepState = erStepService
								.buildPojo(root.getC_ASS_CODE(),root.getC_SN(),fileType,"",state,"","净值已锁定，不更新");
						erStepService.insertPojo(erStepState);
						logger.info("净值已锁定，不更新："+ root.getC_SN());
						return;
					}
				}
			}
			
			serviceDao.saveResult(pojoList);
			long saveTime = System.currentTimeMillis();
			logger.info("保存对账结果耗时：" + (saveTime - forEndTime));
			IErBbInfoService bbInfoService = YssServiceFactory.getInstance().createService(IErBbInfoService.class);
			if(!bbInfoService.isManualAccept(root.getC_SN()))
			{
				try {
					service.updateStatus(state, root.getC_SN(), fileType, errInfo,
							root.getC_ASS_CODE());
				} catch (Exception e1) {
					logger.error("修改状态失败", e1);
				}
				
				//STORY #109004 【公共需求】电子对账管理中增加显示“反馈联系人”列
				try {
					service.updateDealer(dealer, root.getC_SN());
				} catch (Exception e1) {
					logger.error("修改托管行联系人失败", e1);
				}
			}
			long updateTime = System.currentTimeMillis();
			logger.info("更新对账状态耗时：" + (updateTime - saveTime));
			//STORY #61515 21.6任务调度电子对账在接受到托管行返回数据时将任务调度的消息体推送到MQ
			if(getIsStart()){
				//获取任务调度电子对账执行发送成功以后的任务ID和方案代码
				ITaskLogService taskLogService =  YssServiceFactory.getInstance().createService(ITaskLogService.class);
				String  t_Trade = root.getD_START_DATE();
				if(t_Trade.indexOf("-")==-1){
					t_Trade = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(t_Trade));//执行完成日期
				}
				
				Map<String,String> map1 = taskLogService.getTaskExecuteLog(root.getC_ASS_CODE(), t_Trade);
				//根据日期和基金代码查询当前任务调度方案将结果推送给MQ
				if(!map1.isEmpty()){
					sendCompleteMessage(root.getC_ASS_CODE(),root.getD_START_DATE(),map1,true);
				}
				long mqTime = System.currentTimeMillis();
				logger.info("推送消息耗时：" + (mqTime - updateTime));
			}

		} catch (Exception e) {
			try {
				//在入库操作或者之前的某一步报错时也要将消息推送到MQ
				if (getIsStart()) {
					//获取任务调度电子对账执行发送成功以后的任务ID和方案代码
					ITaskLogService taskLogService =  YssServiceFactory.getInstance().createService(ITaskLogService.class);
					String  t_Trade = root.getD_START_DATE();
					if(t_Trade.indexOf("-")==-1){
						t_Trade = new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(t_Trade));//执行完成日期
					}
					Map<String, String> map1 = taskLogService.getTaskExecuteLog(
							root.getC_ASS_CODE(),t_Trade);
					logger.info("--------获取完executeId:" + map1);
					//根据日期和基金代码查询当前任务调度方案将结果推送给MQ
					if (!map1.isEmpty()) {
						sendCompleteMessage(root.getC_ASS_CODE(),
								root.getD_START_DATE(), map1, false);
					}
				}
			} catch (Exception e2) {
				logger.error("推送MQ消息失败", e2);
			}
			throw e;
		}
	}

	/***
	 * 该方法取反，针对正向收到的对账结果
	 * 托管行反馈的数据中本方指的是托管行，对手方指的是估值系统
	 * 所以此处需要将两方进行对调
	 * 对调后本方指估值系统，对手方指托管行
	 * @param root
	 * @param checkFlag
	 * @throws Exception
	 */
	public void insert2(XmlResult root) throws Exception {
		// /手动查询报文序号 基金代码日期
		try {
			String tgh = this.getParaByAssCodeAndDeptCode(root.getC_ASS_CODE(),root.getC_DEPT_CODE());
			String tghInner = "";
			if (!StringUtil.IsNullOrEmptyT(tgh)) {
				tghInner = this.getInnerOrgCodeByOrgCode(tgh);
			}
			String c_ass_code = root.getC_ASS_CODE();
			ErBbInfoService service = new ErBbInfoService();
			Map<String, String> resultMap = ErConfigUtil.getResultMap();
			List<ErResultElement> list = root.getResultList();
			ArrayList<BasePojo> pojoList = new ArrayList<BasePojo>();
			String state = "ER_ACCECPED";
			ErResult result = null;
			String c_sn = "";
			Set<String> set = new HashSet<String>();
			Map<String, Integer> stateMap = new HashMap<String, Integer>();
			Map<String, String> dealerMap = new HashMap<String, String>();
			Map<String, String> map = null;
			for (ErResultElement e : list) {
				/**
				 * 判断对账结果
				 */
				String res = e.getC_RESULT();
				/// by weijj 20151231 有些银行返回汉字  bug120751 
				if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
					state = "ER_IDENTICAL";
				}else{
					state = "ER_ACCECPED";
				}
				String startDate = root.getD_START_DATE();
				String fileType = "";
				String flag = e.getC_CHECK_FLAG();
				if ("ICBC".equalsIgnoreCase(tghInner)) {
					if ("6".equalsIgnoreCase(flag)) {
						flag = "16";
					}else if ("7".equalsIgnoreCase(flag)) {
						flag = "17";
					}
				}
				if("8".equalsIgnoreCase(flag) || "9".equalsIgnoreCase(flag) || "10".equalsIgnoreCase(flag) || "8910".equalsIgnoreCase(flag))
				{
					flag = "8";
				}

				fileType = resultMap.get(root.getC_RPT_TYPE()+"_"+ flag);
				if(fileType != null && fileType.indexOf("_")>0){
					fileType = fileType.split("_",-1)[1];
				}
				
				if(map == null || !map.containsKey(fileType)){
					if("01".equalsIgnoreCase(root.getC_RPT_TYPE())){
						map = service.getFileMap(c_ass_code, startDate);
					}else{
						map = getFileMap(c_ass_code, fileType, root.getC_RPT_TYPE());
					}
				}
				
				c_sn = map.get(fileType);
				if (c_sn == null) {
					c_sn = " ";
				}
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
				//一个组合会拆分多个托管行的数据
				String splitSn ="";
				if(isSplit && (transPojo != null))
				{
					//限制到估值表
					if("1011".equalsIgnoreCase(fileType))
					{
						//是拆分发送的才能取到值
						splitSn = getSnByPortWithSplitRela(this.assCode,fileType,root.getC_RPT_TYPE(),startDate,transPojo);
					}
				}
				//拆分发送的
				if(isSplit && (!StringUtil.IsNullOrEmptyT(splitSn)))
				{
					c_sn = splitSn;
				}
				result = new ErResult();
				result.setC_SN(c_sn);
				result.setC_ASS_CODE(root.getC_ASS_CODE());
				result.setC_RPT_TYPE(root.getC_RPT_TYPE());
				result.setC_FILE_TYPE(root.getC_FILE_TYPE());
				startDate = root.getD_START_DATE();
				if (startDate.trim().length() > 0) {
					Date date = DateUtil.stringtoDate(root.getD_START_DATE(),
							"yyyyMMdd");
					startDate = DateUtil.dateToString(date,
							DateUtil.LONG_DATE_FORMAT);
				} else {
					startDate = DateUtil.getNow();
				}

				result.setD_START_DATE(startDate);
				String endDate = root.getD_END_DATE();
				if (endDate.trim().length() > 0) {
					Date date = DateUtil.stringtoDate(endDate, "yyyyMMdd");
					endDate = DateUtil.dateToString(date,
							DateUtil.LONG_DATE_FORMAT);
				} else {
					endDate = DateUtil.getNow();
				}

				result.setC_CHECK_FLAG(flag);
				result.setD_END_DATE(endDate);
				result.setC_B_BY1(e.getC_D_BY1());
				if(fileType == null){
					logger.info(flag+"  没有文件类型"+JsonUtil.toString(resultMap));
					continue;
				}
				
				//BUG211948【富国基金】电子对账字段长度不够报错 
				if(e.getC_D_CODE() != null && e.getC_D_CODE().getBytes().length > 200){
					byte[] src = e.getC_D_CODE().getBytes();
					byte[] dest = new byte[200];
					System.arraycopy(src, 0, dest, 0, 200);
					String destStr = new String(dest);
					result.setC_B_KM_CODE(destStr);
				}else{
					result.setC_B_KM_CODE(e.getC_D_CODE());
				}
				result.setC_D_BY1(e.getC_B_BY1());
				if (e.getC_B_CODE() != null && e.getC_B_CODE().getBytes().length > 200) {
					byte[] src = e.getC_B_CODE().getBytes();
					byte[] dest = new byte[200];
					System.arraycopy(src, 0, dest, 0, 200);
					String destStr = new String(dest);
					result.setC_D_KM_CODE(destStr);
				}else {
					result.setC_D_KM_CODE(e.getC_B_CODE());
				}
				
				result.setC_DEALER(e.getC_DEALER());
				result.setC_JS_TIME(DateUtil.getNow());
				result.setC_NOTE(e.getC_NOTE());
				result.setC_REF_NO(c_sn);
				//BUG244883托管行反馈余额表1022报文中RESULT字段数值为空时电子对账余额表反馈数据插入报错  RESULT节点为空，默认为对账不一致
				String cResult = StringUtil.IsNullOrEmptyT(e.getC_RESULT()) ? "不一致！" : e.getC_RESULT();
				result.setC_RESULT(subStrLen(cResult, 4000));
				String time = e.getD_TIME();
				if (time.trim().length() > 0) {
					// Date date = DateUtil.stringtoDate(time,
					// "yyyyMMdd HH:mm:ss");
					// time = DateUtil.dateToString(date,
					// DateUtil.LONG_DATE_FORMAT);
				} else {
					time = DateUtil.getNow();
				}
				result.setD_TIME(time);
				result.setN_B_JE(getBigDecimal(e.getN_D_JE()));
				result.setN_B_JE3(getBigDecimal(e.getN_D_JE3()));
				result.setN_B_JE4(getBigDecimal(e.getN_D_JE4()));
				result.setN_B_JE5(getBigDecimal(e.getN_D_JE5()));
				result.setN_B_JEO(getBigDecimal(e.getN_D_JEO()));
				result.setN_B_JET(getBigDecimal(e.getN_D_JET()));
				result.setN_B_SL(getBigDecimal(e.getN_D_SL()));

				result.setN_D_JE(getBigDecimal(e.getN_B_JE()));
				result.setN_D_JE3(getBigDecimal(e.getN_B_JE3()));
				result.setN_D_JE4(getBigDecimal(e.getN_B_JE4()));
				result.setN_D_JE5(getBigDecimal(e.getN_B_JE5()));
				result.setN_D_JEO(getBigDecimal(e.getN_B_JEO()));
				result.setN_D_JET(getBigDecimal(e.getN_B_JET()));
				result.setN_D_SL(getBigDecimal(e.getN_B_SL()));
				
				String key = c_sn + ":" + fileType;
				if(!set.contains(key)){
					set.add(key);
				}
				if(stateMap.containsKey(key) && state.equalsIgnoreCase("ER_IDENTICAL")){
					Integer i = stateMap.get(key);
					i++;
					stateMap.remove(key);
					stateMap.put(key, i);
				}else if(!stateMap.containsKey(key) && state.equalsIgnoreCase("ER_IDENTICAL")){
					stateMap.put(key, 1);
				}else if(stateMap.containsKey(key) && state.equalsIgnoreCase("ER_ACCECPED")){
					
				}else if(!stateMap.containsKey(key) && state.equalsIgnoreCase("ER_ACCECPED")){
					stateMap.put(key, 0);
				}
				
				if(!dealerMap.containsKey(key)) {
					dealerMap.put(key, e.getC_DEALER());
				}
				
				pojoList.add(result);
			}
			
			Map<String,String> lockSnMap = new HashMap<String, String>();
			for(String ss: set){
				String errinfo = "";
				state = "ER_ACCECPED";
				Integer errCount = stateMap.get(ss);
				/////by weijj 20150918 增加提示对账不一致的个数
				if(errCount != null && errCount > 0){
					state = "ER_IDENTICAL";
					errinfo = errCount + " ";
				}
				////增加容错机制  托管行系统如果只返回一笔 为空科目代码的对账结果，作为对账一致处理
				////add by lyj 2016-2-3 BUG #121476 电子对账历史不一致数据查看不了
				//STORY68731电子对账结果“缺少对账数据”的显示
				if(pojoList.size()==1){
					ErResult reault = (ErResult) pojoList.get(0);
////					if(StringUtil.IsNullOrEmptyT(reault.getC_B_KM_CODE())&&StringUtil.IsNullOrEmptyT(reault.getC_D_KM_CODE())){
////						state = "ER_ACCECPED";
////						errinfo = "";
////					}
					// add by lyj 2016-2-3 BUG #126363 电子对账收到托管行“缺少对账数据”类型的数据解析错误，解析成了对账一致
					// 根据返回的对账结果来判断是否一致
					String res = reault.getC_RESULT();
					if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0")  && !res.trim().equalsIgnoreCase("一致") && !res.trim().equalsIgnoreCase("对账一致"))) {
						if(StringUtil.IsNullOrEmptyT(reault.getC_B_KM_CODE())&&StringUtil.IsNullOrEmptyT(reault.getC_D_KM_CODE()))
						{
							state = "ER_IDENTICAL";
							errinfo = "托管行缺少对账数据";
						}else
						{
							state = "ER_IDENTICAL";
							errinfo = "1个";
						}
					}else{
						state = "ER_ACCECPED";
						errinfo = "";
					}
				}
				
				IErBbInfoService erBbInfoService = YssServiceFactory.getInstance().createService(IErBbInfoService.class);
				HashMap<String, Object> paraMap = new HashMap<String, Object>();
				String sn = ss.split(":")[0];
				String fileType = ss.split(":")[1];
				paraMap.put("ARRAY_C_SN", sn);
				paraMap.put("ARRAY_C_DZ_CODE", fileType);
				paraMap.put("dataClass", "ErBbInfo");
				QueryRes res = erBbInfoService.queryByCondition(paraMap);
				if(null != res && res.getDataList() != null && res.getDataList().size() > 0){
					List<BasePojo> basePojoList = res.getDataList();
					for (BasePojo basePojo : basePojoList) {
						ErBbInfo info = (ErBbInfo)basePojo;
						if("01".equalsIgnoreCase(info.getC_RPT_TYPE()) 
								&& ("已确认".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()) || "锁定".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()) 
								|| "LOCK".equalsIgnoreCase(info.getC_CONFIRM_EXECUTE()))){
							//更新状态变更记录
							IErStepStateService erStepService = YssServiceFactory.getInstance().createService(IErStepStateService.class);
							ErStepState erStepState = erStepService
									.buildPojo(root.getC_ASS_CODE(),sn,fileType,"",state,"","净值已锁定，不更新");
							erStepService.insertPojo(erStepState);
							logger.info("净值已锁定，不更新："+ sn);
							lockSnMap.put(sn, "");
						}else{
							if(!erBbInfoService.isManualAccept(sn))
							{
								service.updateStatus(state, sn, fileType, errinfo, root.getC_ASS_CODE());
								
								//STORY #109004 【公共需求】电子对账管理中增加显示“反馈联系人”列
								try {
									if(dealerMap.containsKey(ss)) {
										service.updateDealer(dealerMap.get(ss), sn);
									}
								} catch (Exception e1) {
									logger.error("修改托管行联系人失败", e1);
								}
							}
						}
					}
				}
			}
			
			ArrayList<BasePojo> resultPojoList = new ArrayList<BasePojo>();
			for (BasePojo basePojo : pojoList) {
				ErResult erResult = (ErResult)basePojo;
				if(!lockSnMap.containsKey(erResult.getC_SN())){
					resultPojoList.add(basePojo);
				}
			}
			
			serviceDao.saveResult(resultPojoList);
			IErRptLogService erRptLogService = YssServiceFactory.getInstance().createService(IErRptLogService.class);
			erRptLogService.updateSN(root.getC_SN(),c_sn);
			
			//STORY #61515 21.6任务调度电子对账在接受到托管行返回数据时将任务调度的消息体推送到MQ
			if(getIsStart()){
				//获取任务调度电子对账执行发送成功以后的任务ID和方案代码
				ITaskLogService taskLogService =  YssServiceFactory.getInstance().createService(ITaskLogService.class);
				Map<String,String> map1 = taskLogService.getTaskExecuteLog(root.getC_ASS_CODE(), root.getD_START_DATE());
				logger.info("--------获取完executeId:"+map1);
				//根据日期和基金代码查询当前任务调度方案将结果推送给MQ
				if(!map1.isEmpty()){
					sendCompleteMessage(root.getC_ASS_CODE(),root.getD_START_DATE(),map1,true);
				}
			}
		} catch (Exception e1) {
			try {
				//在入库操作或者之前的某一步报错时也要将消息推送到MQ
				if (getIsStart()) {
					//获取任务调度电子对账执行发送成功以后的任务ID和方案代码
					ITaskLogService taskLogService =  YssServiceFactory.getInstance().createService(ITaskLogService.class);
					Map<String, String> map1 = taskLogService.getTaskExecuteLog(
							root.getC_ASS_CODE(), root.getD_START_DATE());
					logger.info("--------获取完executeId:" + map1);
					//根据日期和基金代码查询当前任务调度方案将结果推送给MQ
					if (!map1.isEmpty()) {
						sendCompleteMessage(root.getC_ASS_CODE(),
								root.getD_START_DATE(), map1, false);
					}
				}
			} catch (Exception e) {
				logger.error("推送MQ消息失败", e);
			}
			throw e1;
		}
			
		
	}
	/**
	 * 按照一个字节占三个字符截取字符串长度
	 * //BUG244883托管行反馈余额表1022报文中RESULT字段数值为空时电子对账余额表反馈数据插入报错  RESULT节点为空，默认为对账不一致
	 * @param str
	 * @param len
	 * @return
	 */
	private String subStrLen(String str,int len)
	{
		String result = str;
		if(!StringUtil.IsNullOrEmptyT(str))
		{
			int size = len/3;
			if(str.length() > size)
			{
				result = str.substring(0, size);
			}
		}
		return result;
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取报文序号，当托管行没返回报文序号时调用
	 * @param c_ass_code
	 * @param fileType
	 * @param rptType
	 * @param startDate
	 * @param transPojo
	 * @return
	 */
	private String getSnByPortWithSplitRela(String c_ass_code,
			String fileType,String rptType, String startDate, TransPojo transPojo) {
		return serviceDao.getSnByPortWithSplitRela(c_ass_code,fileType,rptType,startDate,transPojo);
	}
	/**
	 * 根据日期+基金代码 将完成的消息体推送到MQ
	 * @param c_Ass_Code
	 * @param t_Trade
	 * @throws ParseException 
	 */
	public  void sendCompleteMessage(String c_Ass_Code,String t_Trade,Map<String,String> map,boolean flag) throws ParseException{
		IProducer producer = YssServiceFactory.getInstance().createService(IProducer.class);
		ProducerReqInfo producerReqInfo = new ProducerReqInfo(TopicConstants.SCHEDULER_COMPLETE);
		SchedulerCompleteMessage schedulerCompleteMessage = new SchedulerCompleteMessage();
		schedulerCompleteMessage.getParams().put("planCode", map.get("planCode"));//方案代码
		if(t_Trade.indexOf("-")==-1){
			schedulerCompleteMessage.getParams().put("executeDate", new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(t_Trade)));//执行完成日期
		}else{
			schedulerCompleteMessage.getParams().put("executeDate", t_Trade);//执行完成日期
		}
		
		schedulerCompleteMessage.getParams().put("executeId", map.get("executeId"));//执行ID
		if(flag){
			schedulerCompleteMessage.getParams().put("result", "000000");//成功
		}else{
			schedulerCompleteMessage.getParams().put("result", "000003");//失败
		}
		List<String> list = new ArrayList<String>();
		list.add(c_Ass_Code);
		schedulerCompleteMessage.getParams().put("portCode", list);//基金代码
		String message = JsonUtil.toString(schedulerCompleteMessage);
		List<String> msgList = new ArrayList<String>();
		msgList.add(message);
		producerReqInfo.setMsgs(msgList);
		logger.info("电子对账完成消息体:"+message);
		try {
			producer.produceMsg(producerReqInfo);
			logger.info("发送方案执行完成主题成功");
		} catch (MQClientException e) {
			logger.error("发送方案执行完成主题失败，原因是：", e);
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 电子对账是否启用推送MQ(针对任务调度)
	 * @return
	 * @throws Exception
	 */
	public boolean getIsStart() throws Exception{
		boolean isEnabled = false;
		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String fileName = fileUtil.getFilePath() + "runtime.properties";
		File file = new File(fileName);
		if(file.exists()){
			Properties properties = propertiesUtil.Properties(fileName);
			isEnabled = Boolean.valueOf(properties.getProperty("isEnabled","false"));
		}
		return isEnabled;
	}
	

	private BigDecimal getBigDecimal(String str) {
		if (str.trim().length() > 0) {
			return new BigDecimal(str.replace(",", ""));
		}
		return new BigDecimal(0);
	}

	/**
	 * 
	 * @param c_ass_code
	 * @param fileType
	 * @param rptType
	 * @return
	 */
	private Map<String, String> getFileMap(String c_ass_code, String fileType, String rptType) {
		Map<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = serviceDao.loadNewConnection();
			//20170411 BUG #156576 南方基金-工行月报电子对账结果处理不正确   先orderby 后再取第一条
			//wlx 20171027 BUG #177775 南方基金--工行电子对账估值表、余额表未接收到反馈结果，状态处于发送成功，但伺服器已收到反馈 
			//根据数据的IDEN最大值往小取，大的取到就不再取，而不是用报文序号的最大值
			StringBuffer buf = new StringBuffer("SELECT C_FILE_TYPE, C_SN FROM (SELECT C_FILE_TYPE, C_SN FROM T_D_ER_INFO ");
			buf.append(" WHERE C_ASS_CODE = ? AND C_FILE_TYPE = ? AND C_RPT_TYPE = ? ORDER BY TO_NUMBER(C_IDEN) DESC ) WHERE ROWNUM = 1 ");
			pst = conn.prepareStatement(buf.toString());
			pst.setString(1, c_ass_code);
			pst.setString(2, fileType);
			pst.setString(3, rptType);
			rs = pst.executeQuery();
			String key;
			String value;
			while (rs.next()) {
				key = rs.getString("C_FILE_TYPE");
				value = rs.getString("C_SN");
				map.put(key, value);
			}
		} catch (Exception e) {
			logger.error("查询报文序号文件类型出错:" + e.getMessage(), e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
			DbFun.releaseConnection(conn);
		}

		return map;
	
	}
	
	/**
	 * STORY34022伺服器改造需支持多应用系统
	 * 电子对账反馈的报文的资产代码是否存在系统中
	 * @param assetCode 资产代码
	 * @return 存在true；否则false
	 */
	public boolean isExistAssetCode(String assetCode) {
		return serviceDao.isExistAssetCode(assetCode);
	}
	

	@Override
	public boolean isSameData(String csn, List<String> list) {
		if(list != null && list.size() > 0)
		{
			Set<String> set = new HashSet<String>();
			set.addAll(list);
			
		}
		return true;
	}
	
	/**
	 * 查询差异行数
	 */
    public int queryUnAcceptCount(String csn){
    	int count=0;
        count=serviceDao.queryUnAcceptCount(csn);
        return count;
    }
    
	/**
	 * 获取组合关联机构设置的托管人
	 * @param portcodes
	 * @return
	 */
	@Override
	public Map<String, String> getPortRelaOrgData(String portcodes){
		Map<String, String> portOrgMap = new HashMap<String, String>();
		IPortRelaOrganServcie  portRelaService = YssServiceFactory.getInstance().createService(IPortRelaOrganServcie.class);
		Map<String, List<String>> portRelaOrgmap = portRelaService.getPortRelaOrgByPortAndDvType(portcodes, "TRUSTEE");
		if(portRelaOrgmap != null && !portRelaOrgmap.isEmpty()){
			for(Entry<String, List<String>> entry: portRelaOrgmap.entrySet()){
				List<String> orgList = entry.getValue();
				if(orgList != null && orgList.size() == 1){
					portOrgMap.put(entry.getKey(), orgList.get(0));
				}
			}
		}
		return portOrgMap;
	}
	
	public String getParaByAssCodeAndDeptCode(String assCode, String deptCode) {
		List<String> list = serviceDao.getParaByAssCodeAndDeptCode(assCode,deptCode);
		if(list == null){
			return null;
		}
		
		if (list.size() > 0) {
			return list.get(0);
		}
		
		return null;
	}
	
	public String getInnerOrgCodeByOrgCode(String orgCode) {
		return serviceDao.getInnerOrgCodeByOrgCode(orgCode);
	}
}
