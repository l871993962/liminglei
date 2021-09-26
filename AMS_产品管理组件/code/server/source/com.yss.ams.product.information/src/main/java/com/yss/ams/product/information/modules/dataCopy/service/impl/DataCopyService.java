package com.yss.ams.product.information.modules.dataCopy.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.CollectionUtils;

import com.yss.ams.product.information.modules.dataCopy.dao.DataCopyBuilder;
import com.yss.ams.product.information.modules.dataCopy.dao.DataCopyCustomBuilder;
import com.yss.ams.product.information.modules.dataCopy.dao.DataCopyCustomDao;
import com.yss.ams.product.information.modules.dataCopy.dao.DataCopyDao;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyData;
import com.yss.ams.product.information.support.modules.dataCopy.pojo.CopyDataCheck;
import com.yss.ams.product.information.support.modules.dataCopy.service.IDataCopyService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.commonInfo.service.IFastUcoService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.ICopyAbleFun;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.PojoLoader;

/**
 * 
 * @author weijj 先遍历fun_code,再遍历 port_code
 * 
 */
public class DataCopyService extends ServiceBus<IDataCopyService> implements
		IDataCopyService {
	private DataCopyDao dcDao = null;
	private DataCopyCustomDao dccDao = null;
	
	/**
	 * 影响清算的参数dataCode
	 * [产品群组设置][交易费用方案][交易结算账户][销售结转设置][清算综合参数][结算风险基金][投资分类设置][交易渠道设置][汇集调尾设置]
	 * [产品销售数据]
	 */
	String ONETYPE = "portGroupRela,AO_FEE,carryoverAccount,tasettle,clearParams,settleRisk,investClassify,tradeSeat,solutionSet,"
			+ "tatrade,";
	
	/**
	 * 影响核算的参数dataCode
	 * [产品群组设置][核算级别方案][交易费用方案][汇率转换方案][债券核算方案][产品估值方法][估值方法模板][增值税费方案][汇集调尾设置][合规指标设置]
	 * [运营费用设置][预提待摊设置][账户计息设置][待结款计息设置][资金汇划设置][收支结转设置][核算综合参数][产品销售数据][产品指标配置]
	 */
	String TWOTYPE = "portGroupRela,AO_LEVEL,AO_FEE,AO_ROE,AO_FI,ProductValueMethod,AO_VAL,valPlan,AO_VAT_PLAN,solutionSet,clIndex"
			+ ",investFee,accrdefe,cashaccaccrue,clearingAmount,fundtransset,paySettleSet,acctparams,tatrade,indexmanage,";
	
	/**
	 * 影响估值表的参数dataCode
	 * [产品群组设置][核算级别方案][交易费用方案][汇率转换方案][债券核算方案][净值指标方案][产品估值方法][产品指标配置][估值方法模板][产品指标配置]
	 * [增值税费方案][汇集调尾设置][产品估值参数][产品销售数据][交易结算账户][销售结转设置][清算综合参数][结算风险基金][投资分类设置][交易渠道设置]
	 * [合规指标设置][运营费用设置][预提待摊设置][账户计息设置][待结款计息设置][资金汇划设置][收支结转设置][核算综合参数]
	 */
	String THREETYPE = "portGroupRela,AO_LEVEL,AO_FEE,AO_ROE,AO_FI,netIndicatorsPlan,ProductValueMethod,AO_VAL,valPlan,indexmanage"
			+ ",AO_VAT_PLAN,solutionSet,securitiesvaluation,tatrade,carryoverAccount,tasettle,clearParams,settleRisk,investClassify,tradeSeat,"
			+ "clIndex,investFee,accrdefe,cashaccaccrue,clearingAmount,fundtransset,paySettleSet,acctparams,";

	public DataCopyService() {
		dao = new DataCopyDao(DbPoolFactory.getInstance().getPool(),
				new DataCopyBuilder());
		dcDao = (DataCopyDao) dao;
		dccDao = new DataCopyCustomDao(DbPoolFactory.getInstance().getPool(),
				new DataCopyCustomBuilder());
	}

	@Override
	public String exe(HashMap<String, Object> map, List<BasePojo> list) {
		try {
			return doBusOper(map, list);
		} catch (YssException e) {
			logger.log(e.getMessage(),e);
		}
		return "false";
	}

	/**
	 * 
	 * @param svcId
	 *            serviceId
	 * @return 返回可复制的服务
	 */
	private ICopyAbleFun getCopyFun(String svcId) {
		ICopyAbleFun fun = null;
		try {
			//Object o = YssServiceFactory.getInstance().createService(svcId);
			Object o = YssServiceFactory.getInstance().createServiceByServiceId(ICopyAbleFun.class, svcId);
			// Object o = ServiceInstance.getInstance(svcId);
			if (o != null && (o instanceof ICopyAbleFun)) {
				fun = (ICopyAbleFun) o;
			}
		} catch (Exception e) {
			logger.log("反射失败! " + svcId,e);
		}
		return fun;
	}

	private HashMap<String, ICopyAbleFun> getDataServiceMap(String[] args) {
		HashMap<String, ICopyAbleFun> map = new HashMap<String, ICopyAbleFun>();
		
		for (int i = 0; i < args.length; i++) {
			String[] arr = args[i].split("=");
			if(null != arr && arr.length>1 && null != arr[1] && arr[1].trim().length()>0){
				ICopyAbleFun ableCopyFun = getCopyFun(arr[1]);
				map.put(arr[0], ableCopyFun);
			}
			
		}

		return map;
	}

	/**
	 * 验证serviceid配置是否正确 验证目标组合是否已经有业务数据
	 */
	@Override
	public List<String> verify(HashMap<String, Object> map) {
		List<String> val = new ArrayList<String>();
		Set<String> set = new HashSet<String>();
		// //验证
		try {
			String[] dataSVCList = map.get("DATA_SVC_LIST").toString()
					.split("&");
			HashMap<String, ICopyAbleFun> dataServiceMap = getDataServiceMap(dataSVCList);
			for (Map.Entry<String, ICopyAbleFun> o : dataServiceMap.entrySet()) {
				String dataCode = o.getKey();
				ICopyAbleFun fun = o.getValue();
				if (fun == null) {
					logger.log(" service配置错误  验证失败 " + dataCode);
					set.add(dataCode);
				}
			}
			// //如果配置验证没有通过
			if (set.size() > 0) {
				val.addAll(set);
			}
		} catch (Exception e) {
			logger.log(e.getMessage(),e);
		}

		return val;
	}

	/**
	 * 执行复制功能 思路 1.从前台接受map, 2.从map中取出data_code 列表，遍历列表 　　
	 * 3.根据data_code从表中取出svcId; 　　 4.反射出Service, 　　 5.执行每个Service的copy方法
	 * 7.返回结果集。
	 * 
	 * 返回list.size()==0表示验证通过， 复制执行失败写在日志里
	 * 
	 * modified by liyanjun 2017-2-21 STORY35927新产品成立参数模板需求
	 */
	public String doBusOper(HashMap<String, Object> hmData, List<BasePojo> copyDataList) throws YssException {
		String execProcCode = hmData.get("C_OPER_CODE").toString();
		// String isSave = hmData.get("isSave").toString(); // /标识是否选中所有的数据项
		String portCode = hmData.get("C_PORT_CODE").toString();
		String checkState = "";
		if(hmData.get("N_CHECK_STATE") != null){
			checkState = hmData.get("N_CHECK_STATE").toString();//是否生成审核数据。1：是，0：否
		}			
		String[] portCodeList = hmData.get("PORT_CODE_LIST").toString()
				.split("&");
		String[] dataSVCList = hmData.get("DATA_SVC_LIST").toString()
				.split("&");
		String isClear = "NO";
		if(hmData.get("isClear") != null){
			isClear = hmData.get("isClear").toString(); // 是否从清算调用进来的（默认为“NO”）
		}
		// edit by Yuntao Lau 修复“权限设置复制不包含当前用户”问题 STORY #24464 
//		String currentUser = hmData.get("CurrentUser").toString();
//		String currentUserPost = hmData.get("CurrentUserPost").toString();

		HashMap<String, ICopyAbleFun> dataServiceMap = getDataServiceMap(dataSVCList);
		// /判断如果前台返回

		dccDao.insertData(new ArrayList<String>(dataServiceMap.keySet()));

		/* 获取满足如下条件的组合map : <组合代码, 类型>
		 * 如组合 A已净值确认即类型为“THREETYPE” (影响估值表的参数不能再次复制)
		 *        已存在凭证即类型为“TWOTYPE” (影响核算的参数不能再次复制)
		 *        已存在流水即类型为“ONETYPE” (影响清算的参数不能再次复制)
		 * add by zhaijiajia 20190223 */
		//HashMap<String, String> portMap = dccDao.getPortCopyType(portCodeList);
		HashMap<String, String> portMap = null;
		
		//STORY #68965 FAST分布式分库情况下的sql解耦 
		if(YssServiceFactory.getInstance().isServiceExists(IFastUcoService.class)){
			IFastUcoService ucoService = YssServiceFactory.getInstance().createService(IFastUcoService.class);
			try{
				portMap = ucoService.queryPortType(portCodeList);
			}catch(Exception ex){
				logger.error("从估值获取去重后的参数数据失败！",ex);
			}
		}else{
			logger.warn("IFastUcoService未找到实现!");
		}
		
		/* 从前台传来的pojolist 转换为map 用于获取对应项目名称 
		 * 以及保存复制记录时使用 <项目, <组合, 复制记录>>*/
		HashMap<String, HashMap<String, BasePojo>> copyDataMap = new HashMap<String, HashMap<String, BasePojo>>();
		if (copyDataList != null) {
			for (BasePojo basePojo : copyDataList) {
				CopyData copyData = (CopyData)basePojo;
				/* 保存复制记录时使用 <组合, 复制记录> */
				HashMap<String, BasePojo> copyMap = new HashMap<String, BasePojo>();
				for (String portTemp : portCodeList) {
					CopyDataCheck copyCheck = new CopyDataCheck();
					copyCheck.setC_COPY_STATE("0");
					copyCheck.setC_PORT_CODE(portTemp);
					copyCheck.setC_SOURCE_CODE(portCode);
					copyCheck.setC_DATA_CODE(copyData.getC_DATA_CODE());
					copyCheck.setC_DATA_NAME(copyData.getC_DATA_NAME());
//					copyCheck.setC_DATA_TYPE(copyData.getC_DATA_TYPE());
//					copyCheck.setC_SOURCE_CODE(portCode);
					copyMap.put(portTemp, copyCheck);
				}
				copyDataMap.put(copyData.getC_DATA_CODE(), copyMap);
			}
		}
		String result = "";
		try {
			if (!dccDao.existsTable("T_S_DATA_COPY_CHECK")) {
				for (Map.Entry<String, ICopyAbleFun> o : dataServiceMap.entrySet()) {
					String dataCode = o.getKey();
					ICopyAbleFun fun = o.getValue();
					// edit by Yuntao Lau 修复“权限设置复制不包含当前用户”问题 STORY #24464
					// 权限复制时，把currentUser和currentUserPost拼接在C_OPER_CODE唯一id后逗号隔开
//					if (fun instanceof IRightManageCopyService) {
//						execProcCode += "," + currentUser + "," + currentUserPost;
//					}
					if(fun == null){
						continue;
					}
					try {
						// Edit By HeMing 20190227 STORY #63869 中信证券-“产品成立信息导入接口”新增excel导入接口并且添加清算逻辑。
						// 如果是执行清算调用此方法的话就不打印产品复制的日志 isClear = false
						fun.copy(portCode, portCodeList, dataCode, execProcCode, checkState, isClear);
					} catch (Exception e) {
						logger.log(e.getMessage(),e);
						continue;
					}
				}
			} else {
				for (Map.Entry<String, ICopyAbleFun> o : dataServiceMap.entrySet()) {
					String dataCode = o.getKey();
					ICopyAbleFun fun = o.getValue();
					// edit by Yuntao Lau 修复“权限设置复制不包含当前用户”问题 STORY #24464
					// 权限复制时，把currentUser和currentUserPost拼接在C_OPER_CODE唯一id后逗号隔开
//					if (fun instanceof IRightManageCopyService) {
//						execProcCode += "," + currentUser + "," + currentUserPost;
//					}
					/* start STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况 */
//					String portCodeStringTemp = portCodeString;
					
					
					HashMap<String, String> portParaMap = new HashMap<String, String>();
					HashMap<String, BasePojo> copyMap = copyDataMap.get(dataCode);
					if (copyMap == null) {
						copyMap = new HashMap<String, BasePojo>();
					}
					for (String portString : portCodeList) {
						String params = "";
						String type = null;
						if (null != portMap) {
							type = portMap.get(portString);
						}
						CopyDataCheck copyData = (CopyDataCheck)copyMap.get(portString);
						if (copyData == null) {
							copyData = new CopyDataCheck();
							copyData.setC_COPY_STATE("0");
							copyData.setC_PORT_CODE(portString);
							copyData.setC_SOURCE_CODE(portCode);
							copyData.setC_DATA_CODE(dataCode);
							copyData.setC_DATA_NAME(dataCode);
						}
						
						//影响估值表的参数类型
						if (type != null && "THREETYPE".equalsIgnoreCase(type)) {
							if (THREETYPE.contains(dataCode) || TWOTYPE.contains(dataCode) || ONETYPE.contains(dataCode)) {
//								portCodeStringTemp = portCodeStringTemp.replace(portString+"&", "");
								result = "[" + copyData.getC_DATA_NAME() + "]项目参数不能复制到[" + 
								         portString + "]组合，因组合已经净值确认 ";
								params += "0";
							} else {
								copyData.setC_COPY_STATE("1");
								params += "1";
							}
						} 
						//影响核算的参数类型
						else if (type != null && "TWOTYPE".equalsIgnoreCase(type)) {
							if (TWOTYPE.contains(dataCode) || ONETYPE.contains(dataCode)) {
//								portCodeStringTemp = portCodeStringTemp.replace(portString+"&", "");
								result = "[" + copyData.getC_DATA_NAME() + "]项目参数不能复制到[" + 
								         portString + "]组合，因组合已经存在凭证 ";
								params += "0";
							} else {
								copyData.setC_COPY_STATE("1");
								params += "1";
							}
						} 
						//影响清算的参数类型
						else if (type != null && "ONETYPE".equalsIgnoreCase(type)) {
							if (ONETYPE.contains(dataCode)) {
//								portCodeStringTemp = portCodeStringTemp.replace(portString+"&", "");
								result = "[" + copyData.getC_DATA_NAME() + "]项目参数不能复制到[" + 
								         portString + "]组合，因组合已经存在流水 ";
								params += "0";
							} else {
								copyData.setC_COPY_STATE("1");
								params += "1";
							}
						}
						//没匹配到表示可以复制
						else {
							copyData.setC_COPY_STATE("1");
							params += "1";
						}
						
						params += "=" + result;
						portParaMap.put(portString, params);
					}
					
//					String[] portCodeListTemp = portCodeStringTemp.split("&");
					/* end STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况 */
					List<String> failPorts = null;
					//BUG #332899 【富国基金】【0630.0723版本】产品参数复制时全选了但有漏复制参数
					//现场使用的分库，指标关联设置 是另一个库，勾选“指标关联设置”会导致“增值税方案”不能正常复制，此处加上try处理
					try{
						failPorts = fun.copy(portCode, portParaMap, dataCode, execProcCode, checkState);
					}catch(Exception ex){
						logger.log(ex.getMessage(),ex);
					}
					if (failPorts != null)
					{
						for (String failport : failPorts) {
							CopyDataCheck failCopy = (CopyDataCheck)copyMap.get(failport);
							if (failCopy != null) {
								failCopy.setC_COPY_STATE("0");
							}
						}
					}
					
					copyDataMap.put(dataCode, copyMap);
				}
				
				/* 前台A区界面选择M个组合，B区展示所有明细项目N个，则保存M*N条复制记录 */
				dccDao.insertCopyInfos(copyDataMap);
			}
			
		} catch (Exception e) {
			logger.log(e.getMessage(),e);
		}
		return "";
	}

	@Override
	protected List<BasePojo> query(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> list = super.query(paraMap, clazz);
		// 过滤重复的数据
		HashMap<String, CopyData> removeBojo = new HashMap<String, CopyData>();
		List<CopyData> copyList = new ArrayList<CopyData>();
		for (BasePojo pojo : list) {
			CopyData copyData = (CopyData) pojo;
			if (copyData.getC_DATA_CODE() == null
					|| copyData.getC_DATA_CODE().trim().length() == 0) {
				removeBojo.put(copyData.getC_DATA_CODE_P().trim(), copyData);
				copyList.add(copyData);
			}
		}
		list.removeAll(copyList);
		for (BasePojo pojo : list) {
			CopyData copyData = (CopyData) pojo;
			CopyData copyData2 = removeBojo.get(copyData.getC_DATA_CODE());
			if (copyData2 != null) {
				copyData.setC_SERVICE_CODE(copyData2.getC_SERVICE_CODE());
			}
		}
		return list;
	}

	@Override
	public List<String> queryCustom() {
		List<String> list = dccDao.getQuerySql("dataCopy");
		return list;
	}

	/*
	 * {产品基本信息}中复制创建功能 获取初始数据
	 * 
	 */
	@Override
	//edit by huangjin 2016-9-22 STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	//参照组合改变时需要传参数
	public QueryRes queryCreateCopy(String portCode) {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		dataList =  dccDao.queryCreateCopy(portCode);
		//STORY #68965 FAST分布式分库情况下的sql解耦 
		if(YssServiceFactory.getInstance().isServiceExists(IFastUcoService.class)){
			IFastUcoService ucoService = YssServiceFactory.getInstance().createService(IFastUcoService.class);
			try{
				List<Map<String,String>> dataFromUcoFee = ucoService.dataCopyInveFee(portCode);
				List<Map<String,String>> dataFromUcoIndex = ucoService.dataCopyAoIndex(portCode);
				List<Map<String,String>> dataFromUco = new ArrayList<Map<String,String>>();
				dataFromUco.addAll(dataFromUcoFee);
				dataFromUco.addAll(dataFromUcoIndex);
				if(!CollectionUtils.isEmpty(dataFromUco)){
					for(Map<String,String> mapTemp : dataFromUco){
						CopyData cd = new CopyData();
						cd.setC_DATA_NAME(mapTemp.get("C_DATA_NAME"));
						cd.setC_DATA_CODE(mapTemp.get("C_DATA_CODE"));
						cd.setC_DATA_CODE_P(mapTemp.get("C_DATA_CODE_P"));
						cd.setC_DV_STATE(mapTemp.get("C_DV_STATE"));
						cd.setC_SERVICE_CODE(mapTemp.get("C_SERVICE_CODE"));
						cd.setC_DATA_PARA(mapTemp.get("C_DATA_PARA"));
						dataList.add(cd);
					}
				}
			}catch(Exception ex){
				logger.error("从估值获取产品复制参数出错",ex);
			}
		}else{
			logger.warn("IFastUcoService未找到实现，估值的产品复制参数列表不加载");
		}
		
		res.setDataList(dataList);
		res.setMenuId(menuId);
		res.setOperRes(MvcConstant._Success);
		return res;
	}

	/*
	 * {产品分级参数}中复制创建功能 获取初始数据
	 * 
	 */
	@Override
	public QueryRes queryPortClsCreateCopy() {
		QueryRes res = new QueryRes();
		List<BasePojo> dataList = null;
		dataList =  dccDao.queryPortClsCreateCopy();
		res.setDataList(dataList);
		res.setMenuId(menuId);
		res.setOperRes(MvcConstant._Success);
		return res;
	}
	
	/**
	 * STORY #36524 中信证券-通过http交互方式读入建账信息及产品成立业务
	 * 后台调用doBusOper时拼接的&与url一样，在解析是会出错
	 * 功能：权限复制
	 * @param map
	 * @return String
	 * @throws YssException
	 * @date 2017-3-9
	 * @author huangkaixun
	 */
	public String doBusOperEx(HashMap<String, Object> hmData) throws Exception {
		String execProcCode = hmData.get("C_OPER_CODE").toString();
		// String isSave = hmData.get("isSave").toString(); // /标识是否选中所有的数据项
		String isClear = "NO";
		if(hmData.get("isClear") != null){
			isClear = hmData.get("isClear").toString(); // 是否从清算调用进来
		}
		String portCode = hmData.get("C_PORT_CODE").toString();
		String[] portCodeList = hmData.get("PORT_CODE_LIST").toString()
				.split("@@");
		String[] dataSVCList = hmData.get("DATA_SVC_LIST").toString()
				.split("@@");

		HashMap<String, ICopyAbleFun> dataServiceMap = getDataServiceMap(dataSVCList);
		dccDao.insertData(new ArrayList<String>(dataServiceMap.keySet()));
		try {
			for (Map.Entry<String, ICopyAbleFun> o : dataServiceMap.entrySet()) {
				String dataCode = o.getKey();
				ICopyAbleFun fun = o.getValue();
				if(fun != null){
					fun.copy(portCode, portCodeList, dataCode, execProcCode,"1", isClear);
				}
			}
		} catch (Exception e) {
			logger.log("产品参数复制创建错误"+e.getMessage(), e);
			throw e;
		}
		return "true";
	}

	/**
	 * @Desc  STORY #71081 分布式版本FAST同估值sql表关联解耦 
	 * @author houjiaqi
	 * @date 2019年3月20日 下午4:19:42
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public String getService() throws Exception {
		return dcDao.getService();
	}

	
	/**
	 * @Desc  STORY #71081 分布式版本FAST同估值sql表关联解耦
	 * @author houjiaqi
	 * @date 2019年3月20日 下午5:01:15
	 * @param @param paraMap
	 * @param @param clazz
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<BasePojo> queryCopyData(HashMap<String, Object> paraMap,
			Class<?> clazz) throws Exception {
		return query(paraMap, clazz);
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * add by zhaijiajia 20190222
	 * {产品分级参数}检查-查询数据
	 */
	@Override
	public QueryRes queryCopyCheckData(HashMap<String, Object> paraMap, PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = getDefaultPageInation();
			}

			dataList = dccDao.queryCopyCheckData(paraMap, page, clazz);
			fillResultObject(queryRes, dataList, recCount, page, "dataCopyCheck");
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * add by zhaijiajia 20190222
	 * @param paraMap
	 * @return
	 */
	@Override
	public String getCopyCheckDataTotal(HashMap<String, Object> paraMap) {
		if (dao != null) {
			return String.valueOf(dccDao.queryCopyCheckDataCount(paraMap));
		} else {
			return "0";
		}
	}

	@Override
	public String exe(HashMap<String, Object> map) {
		try {
			return doBusOper(map, null);
		} catch (YssException e) {
			logger.log(e.getMessage(),e);
		}
		return "false";
	}
}
