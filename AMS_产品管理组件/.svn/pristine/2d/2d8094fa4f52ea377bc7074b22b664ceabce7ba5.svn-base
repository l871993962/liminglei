package com.yss.ams.product.information.modules.ab.portrela.service.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaDao;
import com.yss.ams.product.information.modules.ab.portrela.dao.PortRelaSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.busoperservice.ILogger;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.AuditableParamPojo;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.DataProcServiceFactroy;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.dataservice.service.IPortDataService;

public class PortRelaService extends ServiceBus<PortRelaService> implements
		IPortRelaService {

	private PortRelaDao serviceDao = null;

	public PortRelaService() throws Exception {
		serviceDao = new PortRelaDao(DbPoolFactory.getInstance().getPool(
				YssConstant.DBSERVICE_NAME), new PortRelaSqlBuilder());
		dao = serviceDao;
	}
	
	private void init(){
		if(this.bundleContext == null){
			this.bundleContext = YssContextFactory.getInstance().getBundleContext(ProductInfoActivator.class);
		}
	}

	@Override
	public String insert(List<BasePojo> pojoList) {
		// BUG #166485 【海通证券】同一个产品可以选择多个托管人，导致报表数据翻倍。 add by songdabang 20170803
		// 添加判断条件 每个组合只能有一个托管人
		String retInfo = "";
		if (null == pojoList || pojoList.size() <= 0) {
			return "";
		}

		for (BasePojo basePojo : pojoList) {
			// edit by sunyanlin 2017-09-05 STORY #32028 新增产品基本信息时，能同时选择加到哪个产品树形中。
		    if(basePojo instanceof PortRela){
				PortRela portRela = (PortRela) basePojo;
				if (portRela.getC_RELA_TYPE().equalsIgnoreCase("RELA_ORG")
						&& portRela.getC_DV_TYPE_CODE().equalsIgnoreCase("TRUSTEE")) {
					int num = serviceDao.queryOrganCount(portRela.getC_PORT_CODE(),portRela.getC_DV_TYPE_CODE());
					if (num <=0) {
						retInfo = super.insert(basePojo);
					}else {
						retInfo = ReturnInfoGenerator.getSaveWarnStr("008", menuId);
					}
				}else{
					retInfo = super.insert(basePojo);
				}
		    }else{
				retInfo = super.insert(basePojo);
		    }
		}
		return retInfo;
	}
	
	/**
	 * add by liyanjun 2017-2-18 STORY35927新产品成立参数模板需求
	 * @param portCode
	 * @param portCodeList
	 * @param dataCode
	 * @param execProcCode
	 * @return
	 */
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("电子对账参数设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("ARRAY_C_PORT_CODE", portCode);
		paraMap.put("C_RELA_TYPE", "RELA_ORG");//关联机构
		List<BasePojo> basepojos = super.query(paraMap, new PageInation(), PortRela.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("organ");
		for (int i = 0; i < portCodeList.length; i++) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			for (int j = 0; j < basepojos.size(); j++) {
				PortRela pojo = ((PortRela) basepojos.get(j));
				if (pojo.getAuditState() == 1) {
					//add by liyanjun 2017-2-18 STORY35927新产品成立参数模板需求
					//当开启了审核机制，并且“生成审核数据”未勾选，则产生未审核数据
//					新增审核机制参数‘复制创建默认审核’，该参数基于审核机制开启的情况下，控制产品参数复制的模块继承数据的审核状态，并且只有当前产品复制参数中有的模块支持设置。
//					当【数据安全】模块中‘复制创建默认审核’勾选，则复制继承的模块数据均为已审核。
//					若某一模块未开启审核机制，则生成的该模块数据不受‘复制创建默认审核’控制。  STORY64774【招商基金】【紧急】产品参数复制优化 addby wsm 2018-12-15
					if (!StringUtil.IsNullOrEmptyT(checkState)) {
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& ( "0"
										.equals(checkState))) {
							pojo.setAuditState(0);
						}
					}else{
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& (safeData.getN_COPY_CHECK() != 1)) {
							pojo.setAuditState(0);
						}
					}
					pojo.setC_PORT_CODE(portCodeList[i]);
					pojo.setId("");
					pojo.setOperator(userCode);
					pojo.setModifier(userCode);
					pojo.setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					pojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					saveList.add(pojo);
				}
			}
			IPortDataService portDataService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			HashMap<String, String> map = portDataService.getKeyConvertMap();
			String pre = "组合" + map.get(portCodeList[i]) + "电子对账参数设置";
			try {
				ben_Record.init(portCodeList[i], dataCode, null);
				ben_Record.BeginLog();
				log.write(execProcCode,
						ben_Record);
				ben_Record.appendDetailMes("组合代码：" + portCodeList[i]
						+ "，数据项代码：" + dataCode);
				dao.insert(saveList);
				String end = "复制成功" + saveList.size() + "条数据，参照组合共"+saveList.size()+"条数据！";
				ben_Record.appendDetailMes(pre + end);
				ben_Record.EndLog_Success(end);
			} catch (Exception e) {
				logger.log("复制数据错误失败", e);
				ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
				ben_Record.EndLog_Fail("复制数据错误！");
			} finally {
				saveList.clear();
				ben_Record.setC_User_Code(userCode);
				log.write(execProcCode,
						ben_Record);
			}
			logger.debug("复制组合  " + portCodeList[i]);
		}
		logger.debug("电子对账参数设置复制结束");
		return null;
	}
	
	public QueryRes queryPortRelaCashAccountPage(
			HashMap<String, Object> paraMap, PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryCashAccountDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryCashAccountDaoCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
				
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portcashaccount",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portcashaccount");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息交易账户分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes queryPortRelaCashAccountIdPage(
			HashMap<String, Object> paraMap, PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryCashAccountIdDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			// 上面已经对page作了Null值处理，在这里没有必要再处理一次
			// Update By Huxingtao 2013-01-28
			recCount = serviceDao.queryCashAccountDaoIdCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portcashaccountid",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portcashaccountid");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息交易账户分页数据记录数失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes queryPortRelaTradeSeatPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTradeSeatDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryTradeSeatCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
				
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portTradeSeat",ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portTradeSeat");
			setShowConvertAssemble(queryRes);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息交易席位分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * 期货公司
	 */
	public QueryRes queryPortRelaTradeOrgPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTradeOrgDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryTradeOrgCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
				
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portTradeOrg",ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portTradeOrg");
			setShowConvertAssemble(queryRes);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息交易机构分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 期货公司SET
	 */
	public QueryRes queryPortRelaTradeOrgSetPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryTradeOrgSetDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryTradeOrgCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
				
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portTradeOrg",ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portTradeOrg");
			setShowConvertAssemble(queryRes);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息期货公司分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	
	/**
	 * 期货公司SET(新增)
	 */
	public QueryRes getPortRelaTdOrg(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(9999);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortRelaTdOrgDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryTradeOrgCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
				
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portTradeOrg",ProductInfoActivator.class));
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portTradeOrg");
			setShowConvertAssemble(queryRes);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息期货公司新增界面数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 投资经理
	 */
	public QueryRes queryPortRelaInvestMgrPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryInvestMgrDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryInvestMgrCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portinvestmanager",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portinvestmanager");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息投资经理分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * 关联机构
	 */
	public QueryRes queryPortRelaOrganPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryOrganDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryOrganCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portorgan",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portorgan");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息关联机构分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	public QueryRes queryPortRelaCashAccountId(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaCashAccountIdPage(paraMap, null);
	}

	public QueryRes queryPortRelaInvestMgr(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaInvestMgrPage(paraMap, null);
	}

	public QueryRes queryPortRelaOrgan(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaOrganPage(paraMap, null);
	}

	public QueryRes queryPortRelaTradeSeat(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaTradeSeatPage(paraMap, null);
	}

	public QueryRes queryPortRelaTradeOrg(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaTradeOrgPage(paraMap, null);
	}
	public QueryRes getPortRelaTdOrg(HashMap<String, Object> paraMap) {
		this.init();
		return this.getPortRelaTdOrg(paraMap, null);
	}
	
	public QueryRes queryPortRelaTradeOrgSet(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaTradeOrgSetPage(paraMap, null);
	}
	
	public QueryRes queryPortRelaCashAccount(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaCashAccountPage(paraMap, null);
	}

	@Override
	public QueryRes queryPortRelaIndexPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryIndexDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryIndexCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portindex",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portindex");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息指标分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	@Override
	public QueryRes queryPortRelaIndex(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaIndexPage(paraMap, null);
	}
	

	public String delInsert(List<BasePojo> pojoList) {
		String retInfo = "";
		try {

			// 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时,将第一个POJO中放入审核信息 byleeyu20130718
			if (safeData != null && safeData.getN_CHECK() <= 0) {
				if (pojoList != null && pojoList.size() > 0
						&& pojoList.get(0) instanceof AuditableParamPojo) {
					AuditableParamPojo auditPojo = (AuditableParamPojo) pojoList
							.get(0);
					auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
					auditPojo.setOperator(ContextFactory.getContext()
							.getUserCode()); // 设置已审核的用户
					auditPojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
			}
			if (pojoList != null && pojoList.size() > 0){
				serviceDao.delInsert(pojoList);
				retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
			}
		} catch (Exception ex) {
			String errorMess = "";
			if (pojoList != null && pojoList.size() > 0){
				if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
					errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
							ex.getMessage(), dao, pojoList.get(0));
				} else if (ex.getMessage().contains(
						"Invalid Date Data While oper UpdateById ! ")) {
					errorMess = ReturnInfoGenerator.getDateErrStr();
				} else {
					errorMess = ReturnInfoGenerator.getOperErrMsg(
							MvcConstant._CodeSaveErr, menuId);
				}
			}else{
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}

			throw new ErrorMessageException(ex, errorMess);
		}

		return retInfo;
	}
	
	
	public String delByYwId(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			serviceDao.deleteByYwId(pojoList);
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			String errorMess = "";
			if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
						ex.getMessage(), dao, pojoList.get(0));
			} else if (ex.getMessage().contains(
					"Invalid Date Data While oper UpdateById ! ")) {
				errorMess = ReturnInfoGenerator.getDateErrStr();
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}

			throw new ErrorMessageException(ex, errorMess);
		}

		return retInfo;
	}
	
	
	@Override
	public List<String> getPortEexptExistByCopy(String ports, BasePojo pojo) {
		List<String> portList = new ArrayList<String>();
		List<String> portsRes = new ArrayList<String>();
		String[] portRes = null; 
		String sql = "select distinct c_port_code,c_rela_code,c_dv_type_code from t_p_ab_port_rela t "
				+ "where t.c_rela_type = ?";
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		try{
			if(null != ports){
				portRes = ports.split("\\|");
				conn = this.dao.loadNewConnection();
			    pstmt = conn.prepareStatement(sql);
			    pstmt.setString(1, ((PortRela)pojo).getC_RELA_TYPE());
			    rs = pstmt.executeQuery();
				while(rs.next()){
					portList.add(rs.getString("c_port_code")+"\t"+rs.getString("c_rela_code")+"\t"+rs.getString("c_dv_type_code"));
				}

				for(String port : portRes){
					if(portList.contains(port+"\t"+((PortRela)pojo).getC_RELA_CODE()+"\t"+((PortRela)pojo).getC_DV_TYPE_CODE())){
						continue;
					}else{
						portsRes.add(port);
					}
				}
			}
		}catch(Exception e){
//			e.printStackTrace();
			logger.log("产品关联信息：根据关联类型查询关联信息失败", e);
		}finally{
			this.dao.closeResultSetFinal(rs);
			this.dao.closeStatementFinal(pstmt);
			this.dao.releaseConnection(conn);
		}
		return portsRes;
	}
	
	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 */
	@Override
	public QueryRes queryPortRelaMember(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaMemberPage(paraMap, null);
	}
	
	/**
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 */
	@Override
	public QueryRes queryPortRelaCashAcc(HashMap<String, Object> paraMap) {
		this.init();
		return this.queryPortRelaCashAccPage(paraMap, null);
	}

	/**
	 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
	 */
	@Override
	public QueryRes queryPortRelaMemberPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryMemberDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryMemberCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portOrgMbr",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portOrgMbr");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品关联信息：查询产品关联信息客户编号分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * @author guohui 2017-09-04 STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 */
	@Override
	public QueryRes queryPortRelaCashAccPage(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		try {
			this.init();
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			if (page == null) {
				page = new PageInation();
				page.setCurrPage(1);
				page.setPageSize(5);
				page.setUsePage(true);
			}

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryCashAccDao(paraMap, page, clazz);
			queryRes.setDataList(dataList);

			recCount = serviceDao.queryMemberCount(paraMap);
			page.setTotalNum(recCount);
			queryRes.setPage(page);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead("portCashAcc",ProductInfoActivator.class));
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("portCashAcc");
		} catch (Exception ex) {
			logger.log("产品关联信息：查询产品关联信息现金账户信息分页数据失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

    /** 
     * @Title: insert
     * @Desc: 
     * @param pojo
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService#insert(com.yss.framework.api.common.co.BasePojo, java.sql.Connection) 
     */
    @Override
    public <K extends BasePojo> String insert(K pojo, Connection conn) {
        String retInfo = "";
        try {
            // 添加对Pojo类型的判断
            // Update By Huxingtao 2013-2-22
            if (pojo instanceof ParamPojo) {
                ((ParamPojo) pojo).setModifyDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            // 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时 byleeyu20130718
            if (safeData != null && safeData.getN_CHECK() <= 0
                    && pojo instanceof AuditableParamPojo) {
                AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
                auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
                auditPojo
                        .setOperator(ContextFactory.getContext().getUserCode()); // 设置已审核的用户
                auditPojo.setAuditDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            /* 添加自定义日志功能 记录执行时间 */
            // StringBuffer buf = new StringBuffer();
            // buf.append("serviceName:").append(this.getClass().getName())
            // .append("  ");
            // buf.append("methodName:").append("insert insertData").append("  ");
            // buf.append("menuId:").append(getMenuId());
            //
            // WriteLog.newInstance().startLog();

            /*
             * Author : ChenLong Date : 2013-11-21 Status : Add Comment:
             * 插入数据C_IDEN返回值集合
             */
            List<String> cidenList = new ArrayList<String>();
            String ciden = dao.insert(pojo, conn);
            //String ciden = insertData(pojo);
            cidenList.add(ciden);

            // WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
            // StringUtil.clearStringBuffer(buf);
            // buf = null;

            retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);
        } catch (Exception ex) {
            String errorMess = "";
            // 添加了对唯一性索引异常的区分
            if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
                // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
                errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex
                        .getMessage(), dao, pojo);
            } else {
                errorMess = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeSaveErr, menuId);
            }

            throw new ErrorMessageException(ex,errorMess);
        }
        return retInfo;
    }

    /** 
     * @Title: delete
     * @Desc: 
     * @param list
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService#deleteById(java.util.List, java.sql.Connection) 
     */
    @Override
    public String deleteById(List<BasePojo> list, Connection conn) {
        String retInfo = "";

        try {
            dao.deleteById(list);
            if (safeData != null && safeData.getN_RECYCLE() > 0) {
                retInfo = saveDelRecord(list);
            }
            retInfo = getDelInfo(retInfo);
        } catch (Exception ex) {
            if(ex instanceof YssRuntimeException){
                throw new ServiceException(ex);
            }else{
                retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeDelErr, menuId);
                logger.log(ex.getMessage());
                throw new ErrorMessageException(ex,retInfo);
            }
        }

        return retInfo;
    }

    /** 
     * @Title: updateById
     * @Desc: 
     * @param pojo
     * @param conn
     * @return 
     * @see com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService#insert(com.yss.framework.api.common.co.BasePojo, java.sql.Connection) 
     */
    @Override
    public <K extends BasePojo> String updateById(K pojo, Connection conn) {
        String operRes = "";
        try {
            if (pojo instanceof ParamPojo) {
                ((ParamPojo) pojo).setModifyDate(DateUtil
                        .getNow(MvcConstant._DATA_STD_DATE_FORMAT));
            }

            // 当审核状态未开启且ＰＯＪＯ类继承了审核POJO基类时 byleeyu20130718
            if (safeData != null && safeData.getN_CHECK() <= 0
                    && pojo instanceof AuditableParamPojo) {
                AuditableParamPojo auditPojo = (AuditableParamPojo) pojo;
                auditPojo.setAuditState(YssConstant.STATE_AUDIT); // 设置已审核
                auditPojo
                        .setOperator(ContextFactory.getContext().getUserCode()); // 设置已审核的用户
                auditPojo.setAuditDate(DateUtil.getCurrent());
            }

            dao.updateById(pojo, conn);
            operRes = ReturnInfoGenerator.getUpdateOKStr(menuId);
        } catch (Exception ex) {
            String errorMess = "";
            // 添加了对唯一性索引异常的区分
            if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
                // 修改对数据唯一提示参数 需求号: YSSUCO赢时胜2013年03月28日01_A byleeyu 20130910
                errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex
                        .getMessage(), dao, pojo);
            } else {
                errorMess = ReturnInfoGenerator.getOperErrMsg(
                        MvcConstant._CodeSaveErr, menuId);
            }

            throw new ErrorMessageException(ex, errorMess);
        }
        return operRes;
    }
}
