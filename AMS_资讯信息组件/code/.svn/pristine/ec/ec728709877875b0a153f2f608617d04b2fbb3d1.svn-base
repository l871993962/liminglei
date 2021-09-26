package com.yss.ams.sec.information.modules.pub.service.impl;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


//import com.yss.data.mp.secequ.pojo.SecEqu;
//import com.yss.dayf.act.consts.VocabularyConsts;
//import com.yss.dayf.assetStats.pojo.SysInitItemBean;
//import com.yss.dayf.assetStats.service.IDayfSysInitService;
import com.yss.framework.api.busoperservice.BaseOper;
import com.yss.framework.api.busoperservice.BizItem;
import com.yss.framework.api.busoperservice.IBusiness;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SysFun;
import com.yss.framework.api.dataservice.IFunDataService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.ams.base.information.support.util.holidays.HolidaysAide;
import com.yss.ams.sec.information.modules.pub.dao.AdmFiHistoryPay;
import com.yss.ams.sec.information.modules.pub.dao.AdmFiLcEqu;
import com.yss.ams.sec.information.modules.pub.dao.AdmFiPerHundredInterest;
import com.yss.ams.sec.information.modules.pub.dao.FiHundFactorService;
import com.yss.ams.sec.information.support.modules.mp.secequ.pojo.SecEqu;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiHundIntFactorBean;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiPayFactorBean;
import com.yss.ams.sec.information.support.modules.pub.pojo.FiPerHundredInterestBean;
import com.yss.ams.sec.information.support.modules.pub.pojo.SysInitItemBean;
//import com.yss.para.co.fipay.pojo.FiPay;
//import com.yss.pub.func.fi.adm.AdmFiHistoryPay;
//import com.yss.pub.func.fi.adm.AdmFiLcEqu;
//import com.yss.pub.func.fi.adm.AdmFiPerHundredInterest;
//import com.yss.pub.func.fi.adm.FiHundFactorService;
//import com.yss.pub.func.fi.pojo.FiHundIntFactorBean;
//import com.yss.pub.func.fi.pojo.FiPayFactorBean;
//import com.yss.pub.func.fi.pojo.FiPerHundredInterestBean;
//import com.yss.pub.util.HolidaysAide;
//
//import dataservice.comm.pojo.SecBase;
//import dayfsupport.service.assetStats.IAssetClearCtlService;
//import dayfsupport.service.assetStats.IAssetStatsCtlInitService;
import com.yss.ams.sec.information.support.modules.pub.service.IAssetStatsCtlInitService;
import com.yss.ams.sec.information.support.modules.pub.service.IDayfSysInitService;
import com.yss.ams.sec.information.support.modules.sv.base.pojo.SecBase;
import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;

/**
 * chenbo 2017-08-19 TASK #332232 
 * 公共信息处理界面中的系统初始化下的接口拆分到资讯组件中，以提供每百元利息生成功能
 * 
 */
public class AssetStatsCtlInitService extends BaseOper implements
		IAssetStatsCtlInitService ,IBusiness {

	/**
	 * 业务开始日期
	 */
	private java.util.Date d_begin_date;

	/**
	 * 业务结束日期
	 */
	private java.util.Date d_end_date;

	/**
	 * 项目集
	 */
	private String c_Itesms = "";

	private String menuId = "";
	
	private List<FiPayFactorBean> singleSecInit = new ArrayList<FiPayFactorBean>();
	
    /**Start 20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看*/
     //本次操作关联的调度方案的执行编号*/
	private String c_Dispatch_ID = " ";
	//功能代码
	private String c_Fun_Code = " ";
    /**End 20150720 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看*/
	
	//edit by shijian 2017-04-24 BUG #157646 【紧急】嘉实基金：日常调度任务定时任务执行失败
	//增加保存功能ID的成员变量
	private String funRelaId = " ";
	
	/**
	 * 参数
	 */
	private HashMap<String,Object> hmParams ;

	private ArrayList<BEN_RECORD> lstRecord = new ArrayList<BEN_RECORD>();

	/***
	 * modified by liyanjun 2016-12-29 BUG #148057 【公共信息处理】系统初始化下面的几个接口处理节假日逻辑不一致【优化】
	 * 系统初始化下面的所有清算接口，在勾选节假日前一个工作日时，都会处理节假日期间的信息。
	 */
	public String doBusOper(HashMap<String, Object> hmData) {
		try {
			parse(hmData);
			if (null != c_Itesms) {
				Date end_date = d_end_date;
				boolean isholidays = HolidaysAide.isHoliday(YssFun.addDay(d_end_date, 1), "CN");
				if(isholidays){
					//业务日期+1工作日
					Date add1Wd = HolidaysAide.getTargetDateOnHoliday(d_end_date, "CN", 1,VocabularyConsts.DATETYPE_WORK );
					end_date = HolidaysAide.getTargetDateOnHoliday(add1Wd, "CN", -1,VocabularyConsts.DATETYPE_NATURE );
				}
				String[] arrItems = c_Itesms.split(",");
				for (String c_Item : arrItems) {
					if (c_Item.equalsIgnoreCase("initBondPerHundInter")) {
						//add by zhaijiajia 合并需求：STORY #34354 【南方基金】【紧急】公共信息处理，初始化每百元利息，处理工作日最后一天的时候，自动处理节假日那几天的
						//如果执行的基准日期是节假日前最后一个工作日（举例周五）时，
						//需要处理债券（或理财产品）的本期截息日 bwteen 执行的基准日期 and 执行的基准日期下一个工作日前最后一个节假日（举例周五到周日）的证券生成【对价派息信息】数据
//						Date end_date = d_end_date;
//						boolean isholidays = HolidaysAide.isHoliday(YssFun.addDay(d_end_date, 1), "CN");
//						if(isholidays){
//							//业务日期+1工作日
//							Date add1Wd = HolidaysAide.getTargetDateOnHoliday(d_end_date, "CN", 1,VocabularyConsts.DATETYPE_WORK );
//							end_date = HolidaysAide.getTargetDateOnHoliday(add1Wd, "CN", -1,VocabularyConsts.DATETYPE_NATURE );
//						}
						for (int i = 0, j = YssFun.dateDiff(d_begin_date,end_date); i <= j; i++) {
							Date markDate = YssFun.addDay(d_begin_date, i);
							initBondPerHundInterest(markDate);
						}
					}
					//CL 20130617 STORY #4080 资产估值_证券兑付与兑息  自动产生权益数据
					if (c_Item.equalsIgnoreCase("initBondEqu")) {
						//STORY #21836 初始化证券权益信息和财汇权益信息功能调整
						//如果执行的基准日期是节假日前最后一个工作日（举例周五）时，
						//需要处理债券（或理财产品）的本期截息日 bwteen 执行的基准日期 and 执行的基准日期下一个工作日前最后一个节假日（举例周五到周日）的证券生成【对价派息信息】数据
//						Date end_date = d_end_date;
//						boolean isholidays = HolidaysAide.isHoliday(YssFun.addDay(d_end_date, 1), "CN");
//						if(isholidays){
//							//业务日期+1工作日
//							Date add1Wd = HolidaysAide.getTargetDateOnHoliday(d_end_date, "CN", 1,VocabularyConsts.DATETYPE_WORK );
//							end_date = HolidaysAide.getTargetDateOnHoliday(add1Wd, "CN", -1,VocabularyConsts.DATETYPE_NATURE );
//						}
						for(int i=0,j= YssFun.dateDiff(d_begin_date,end_date);i<=j;i++){
							Date markDate = YssFun.addDay(d_begin_date, i);
							initBondEqu(markDate);
						}
					}
					// 初始化优先股 addbyliyongjun 2015-1222STORY18596优先股业务
					if (c_Item.equalsIgnoreCase("initBondYxgPerHund")) {
						for (int i = 0, j = YssFun.dateDiff(d_begin_date,end_date); i <= j; i++) {
							Date markDate = YssFun.addDay(d_begin_date, i);
							initBondYxgPerHund(markDate);
						}
					}
				}
			}
			return YssCons.YSS_DBUPDATE_SUCCESS;
		} catch (Exception ex) {
			return YssCons.YSS_DBUPDATE_FAIL;
		}finally{
			/**20150724 added by liubo.STORY #24163 #26344任务调度日志修改成前台可查看
			 * 将子类的业务日志集合赋值给基类的集合，供调度方案自动处理时调用*/
			this.listRecord = lstRecord;
		}
	}

	private void parse(HashMap<String, Object> hmData) throws YssException {
		// TODO by ChenLong（处理方式不再用JSON对象 集合直接存储SQL条件）
		c_Itesms = (String) hmData.get("C_ITEM_CODE");
		d_begin_date = YssFun.toDate((String) hmData.get("D_BEGIN_DATE"));
		// 如果需要初始化的所有债券，结息日期都小于hmData.get("D_END_DATE")
		// 即：都小于当前日期，取最大的结息日期作为结束日期
		// added by xzl BUG #105709
		if (singleSecInit.size() > 0) {
			d_end_date = null;
			for (FiPayFactorBean secInit : singleSecInit) {
				if (null == d_end_date) {
					d_end_date = secInit.getBondEndDate();
					continue;
				}
				if (secInit.getBondEndDate().after(d_end_date)
						|| secInit.getBondEndDate().equals(d_end_date)) {
					d_end_date = secInit.getBondEndDate();
				}
			}
			if (d_end_date.after(YssFun.toDate((String) hmData.get("D_END_DATE")))
					|| d_end_date.equals(YssFun.toDate((String) hmData.get("D_END_DATE")))) {
				d_end_date = YssFun.toDate((String) hmData.get("D_END_DATE"));
			}
		}else {
			d_end_date = YssFun.toDate((String) hmData.get("D_END_DATE"));
		}
		execProcCode = (String) hmData.get("C_OPER_CODE");
		
		if(hmData.get("C_OPER_CODE")!=null){
			c_Dispatch_ID = (String) hmData.get("C_OPER_CODE");
		}
		if(hmData.get("C_DISPATCH_ID")!=null){
			c_Dispatch_ID = (String) hmData.get("C_DISPATCH_ID");
		}
		
		c_Fun_Code = hmData.get("C_FUN_CODE") == null ? " " : (String) hmData.get("C_FUN_CODE");
		
		//edit by shijian 2017-04-24 BUG #157646 【紧急】嘉实基金：日常调度任务定时任务执行失败
		//保存功能ID
		funRelaId = hmData.get("C_RELA_ID") == null ? " " : (String) hmData.get("C_RELA_ID");
	}

	/**
	 * 债券每百元利息初始化计算，为证券信息表中债券计算基准日期所在付息期间每天的每百元债券利息
	 * By Jinghehe 2014-3-15 新增一个根据日期段 和 证券内码secCodeList 进行初始化 每百元债券利息
	 * @param startDate 起始时间
	 * @param endDate 结束时间
	 * @param secCodeList 证券内码list
	 * @throws Exception
	 */
	 public void initBondPerHundInterest(Date startDate,Date endDate,List<String> secCodeList) throws Exception {
		try {
			if (null == startDate || null == endDate || null == secCodeList
					||(null != secCodeList && secCodeList.isEmpty()))
			{
				return;
			}
			AdmFiPerHundredInterest admFiPerHundInterest = new AdmFiPerHundredInterest();
			Date markDate = null;
			List<FiPerHundredInterestBean> allHundredInterestPojos = new ArrayList<FiPerHundredInterestBean>();
			for (int i = 0, j = YssFun.dateDiff(startDate,endDate); i <= j; i++) {
				markDate = YssFun.addDay(startDate, i);
				// 计算出每百元利息
				List<FiPerHundredInterestBean> hunderedInterestPojos = admFiPerHundInterest.calcPerHundredInterest(secCodeList, markDate);
				if (null != hunderedInterestPojos) {
					allHundredInterestPojos.addAll(hunderedInterestPojos);
				}
			}
			// 删除每百元利息
			//admFiPerHundInterest.deletePerHundInt(allHundredInterestPojos);
			// 保存每百元利息
			//admFiPerHundInterest.savePerHundInt(allHundredInterestPojos);
			//#155809 删除保存进行事务控制
			admFiPerHundInterest.insertAllHundredInterest(allHundredInterestPojos);
		} catch (Throwable ex) {
//			ex.printStackTrace();
			logger.log("债券每百元利息初始化计算失败", ex);
			throw new Exception(ex);
		} 
	}
	 /**
		 * 债券每百元利息初始化计算，为证券信息表中债券计算基准日期所在付息期间每天的每百元债券利息
		 * By Jinghehe 2014-3-15 新增一个根据日期段 和 证券内码secCodeList 进行初始化 每百元债券利息
		 * @param startDate 起始时间
		 * @param endDate 结束时间
		 * @param secCodeList 证券内码list
		 * @throws Exception
		 */
		 public void initBondPerHundInterest(Date startDate,Date endDate, String secCode) throws Exception {
			try {
				if (null == startDate || null == endDate || null == secCode)
				{
					return;
				}
				AdmFiPerHundredInterest admFiPerHundInterest = new AdmFiPerHundredInterest();
				Date markDate = null;
				List<FiPerHundredInterestBean> allHundredInterestPojos = new ArrayList<FiPerHundredInterestBean>();
				List<String> secCodes = new ArrayList<String>();
				secCodes.add(secCode);
				FiHundFactorService factorService = new FiHundFactorService();
				// 查询出所有的计息因子，因为传入是结束日期
				// 首先查询出数据，避免多次查询。
				List<FiHundIntFactorBean> allFactors  = factorService.queryFiHundIntFactors(secCode);
				// 因为查询出的历史付息期间是升序排列的。
				HashMap<String, String> accrualExpressions = admFiPerHundInterest.getHundIntExpressions();
				List<FiHundIntFactorBean> singleFactor = new ArrayList<FiHundIntFactorBean>();
				for (int i = 0, j = YssFun.dateDiff(startDate,endDate); i <= j; i++) {
					markDate = YssFun.addDay(startDate, i);
					singleFactor.clear();
					// 找到当前日期所在历史付息期间计息因子对象
					for(FiHundIntFactorBean eachFactor : allFactors){
						Date curPeriodBeginDate = eachFactor.getCurPeriodBeginDate();
						Date curPeriodEndDate = eachFactor.getCurPeriodEndDate();
						if(YssFun.dateDiff(curPeriodBeginDate, markDate) >=0 && YssFun.dateDiff(markDate,curPeriodEndDate)>=0 ){
							singleFactor.add(eachFactor);
							continue;
						}
					}
					FiPerHundredInterestBean hunderedInterestPojo = admFiPerHundInterest.calcPerHunderedInterest(secCode, markDate,singleFactor,accrualExpressions);
					if (null != hunderedInterestPojo) {
						allHundredInterestPojos.add(hunderedInterestPojo);
					}
				}
				// 删除每百元利息
				//admFiPerHundInterest.deletePerHundInt(secCode, startDate, endDate);
				// 保存每百元利息
				//admFiPerHundInterest.savePerHundInt(allHundredInterestPojos);
				//
				admFiPerHundInterest.insertAllHundredInterest(allHundredInterestPojos);
			} catch (Throwable ex) {
//				ex.printStackTrace();
				logger.log("债券每百元利息初始化计算失败", ex);
				throw new Exception(ex);
			} 
		}
	 
	@Override
	public void initSingleSecBond(SecBase secBase,
			HashMap<String, Object> hmData) throws Exception {
		AdmFiHistoryPay admFiHistoryPay = new AdmFiHistoryPay(YssFun.toDate(secBase.getD_AI_END()));
		//删除 该只证券的历史付息与历史每百元利息数据
		//admFiHistoryPay.deleteHistoryPayData(secBase.getC_SEC_CODE());
		admFiHistoryPay.deleteHistoryPerHundData(secBase.getC_SEC_CODE());
		//产生债券的历史付息数据
		FiPayFactorBean fiPayFactor = buildFiPayFactorBeanBySec(secBase);
		List<FiPayFactorBean> factors = new ArrayList<FiPayFactorBean>();
		factors.add(fiPayFactor);
		admFiHistoryPay.generateFiHistoryPayData(factors,new BEN_RECORD());
		singleSecInit.add(fiPayFactor);
		//this.doBusOper(hmData);
		singleSecInit.clear();
		// 重新产生整个期间的每百元利息数据
		if(fiPayFactor.getLicaiBeginDate().before(fiPayFactor.getBondBeginDate()))
		{
			fiPayFactor.setBondBeginDate(fiPayFactor.getLicaiBeginDate());
		}
		this.initBondPerHundInterest(fiPayFactor.getBondBeginDate(),fiPayFactor.getBondEndDate(), secBase.getC_SEC_CODE());
	}
	

	/**
	 * 债券每百元利息初始化计算，为证券信息表中债券计算基准日期所在付息期间每天的每百元债券利息
	 * 
	 * @param markDate
	 * @throws Exception
	 */
	private void initBondPerHundInterest(Date markDate) throws Exception {
		BEN_RECORD ben_Record = new BEN_RECORD(ContextFactory.getContext().getUserCode());
		try {
			ben_Record.init("", "initBondPerHundInter", markDate);
			ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
			ben_Record.setC_Item_Name("初始化债券每百元利息");
			ben_Record.setC_Fun_Code(c_Fun_Code);
			//edit by shijian 2017-04-24 BUG #157646 【紧急】嘉实基金：日常调度任务定时任务执行失败
            //日志中保存功能ID，任务调度时前台可查询
			ben_Record.setC_FunRela_Id(funRelaId);
			ben_Record.BeginLog();
			ben_Record.appendDetailMes("开始计算 " + YssFun.formatDate(markDate)+ " 日债券每百元利息...");
			sender.write(execProcCode, ben_Record);
			// 债券历史付息信息初始化
			// 查询证券信息中债券计息起始日和计息截止日包含基准日期的债券，为这些债券产生完整的历史付息数据
			AdmFiHistoryPay admFiHistoryPay = new AdmFiHistoryPay(markDate);
			List<FiPayFactorBean> fiPayFactors = new ArrayList<FiPayFactorBean>();
			if(singleSecInit.size()>0) {
					fiPayFactors = singleSecInit;
			}else {
					fiPayFactors = admFiHistoryPay.getFiPayFactorsWhereMarkDateInPayPeriod();
			}
			/*STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢
			 * 将数据进行分组，5000个一组进行拆分，并发处理*/
			int count = 0;// 初始化债券数量
			int splitCnt = 5000;// 分组数量
			// 线程池取CPU数乘以2
			int threadCnt = Runtime.getRuntime().availableProcessors() * 2;
			if(fiPayFactors!= null){
				if(fiPayFactors.size() <= splitCnt || threadCnt < 4){
					// 小于5000则直接执行 CPU核数较少时也直接执行
					count = execute(markDate, fiPayFactors, ben_Record);
				}else{
					// 大于5000分组放入线程执行
					List<FiPayFactorBean> splitFiPayFactors = new ArrayList<FiPayFactorBean>();
					List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
					ExecutorService threadExe = Executors.newFixedThreadPool(threadCnt);
					for(int i = 0;i < fiPayFactors.size();i++){
						splitFiPayFactors.add(fiPayFactors.get(i));
						if((i + 1) % splitCnt == 0 || i == (fiPayFactors.size() - 1)){
							// 5000个/组提交一个线程
							ExecutorThread  exeThread = new ExecutorThread(
									markDate, splitFiPayFactors, ben_Record);
							futureList.add(threadExe.submit(exeThread));
							splitFiPayFactors = new ArrayList<FiPayFactorBean>();
						}
						
					}
					
					// 获取线程执行结果
					for (Future<Integer> future : futureList) {
						try{
							count += future.get();
						}catch(Exception ex){
							threadExe.shutdown();
							throw ex;
						}
					}
					
					threadExe.shutdown();
				}
			}
			
			//-----结束产生债券每百元利息
			// 记录每百元利息的业务处理日志
			ben_Record.appendDetailMes(YssFun.formatDate(markDate)+ " 日债券每百元利息计算完成");
			ben_Record.appendDetailMes("合计处理的债券数量为 " + count);
			ben_Record.appendDetailMes("合计产生每百元利息记录总数为 " + count);
			if (ben_Record.getC_Doing_Type() == DoingType.Fail)
				ben_Record.EndLog_Fail("初始化计算债券每百元利息失败");
			else
				ben_Record.EndLog_Success("初始化计算债券每百元利息完毕");
		} catch (Throwable ex) {
//			ex.printStackTrace();
			logger.log("债券每百元利息初始化计算失败", ex);
			ben_Record.appendDetailMes((ex.getCause() == null ? ex.getStackTrace()[0].toString() : ex.getCause().getMessage()));
			ben_Record.EndLog_Fail("初始化计算债券每百元利息失败");
			throw new Exception(ex);
		} finally {
			lstRecord.add(ben_Record);
			sender.write(execProcCode, ben_Record);
		}

	}
	
	/**
	 * @ClassName ExecutorThread
	 * @Description callable线程，执行每百元利息初始化操作
	 * @author liminghong@ysstech.com
	 * @CreateDate 2017年2月16日
	 */
	public class ExecutorThread implements Callable<Integer>{

		private Date markDate;
		private List<FiPayFactorBean> fiPayFactors;
		private BEN_RECORD ben_Record;
		
		public ExecutorThread(Date markDate, 
				List<FiPayFactorBean> fiPayFactors, 
				BEN_RECORD ben_Record){
			this.markDate = markDate;
			this.fiPayFactors = fiPayFactors;
			this.ben_Record = ben_Record;
		}
		
		@Override
		public Integer call() throws Exception {
			return execute(markDate, fiPayFactors, ben_Record);
		}
		
	}
	
	/** 
	 * @Title execute 
	 * @Description STORY #38597 【南方基金】公共信息处理-初始化债券每百元利息 执行速度慢
	 * 代码抽离，以List<FiPayFactorBean>分组执行初始化操作
	 * @author liminghong@ysstech.com
	 * @date 2017年2月16日
	 * @param markDate 初始化日期
	 * @param fiPayFactors 初始化对象
	 * @param ben_Record 日志对象
	 * @return 初始化债券数量
	 * @throws Exception
	 */
	private int execute(Date markDate, List<FiPayFactorBean> fiPayFactors, BEN_RECORD ben_Record) throws Exception{
		AdmFiHistoryPay admFiHistoryPay = new AdmFiHistoryPay(markDate);
		admFiHistoryPay.generateFiHistoryPayData(fiPayFactors,ben_Record);
		// -----开始产生债券每百元利息
		int count = 0;
		if (null != fiPayFactors && !fiPayFactors.isEmpty()) {
			AdmFiPerHundredInterest admFiPerHundInterest = new AdmFiPerHundredInterest();
			List<String> secCodeList = new ArrayList<String>();
			for(FiPayFactorBean payFactor : fiPayFactors){
				secCodeList.add(payFactor.getSecCode());
			}
			List<FiPerHundredInterestBean> hunderedInterestPojos = admFiPerHundInterest.calcPerHundredInterest(secCodeList, markDate);
			if (null != hunderedInterestPojos) {
				count = hunderedInterestPojos.size();
				// 删除每百元利息
				//admFiPerHundInterest.deletePerHundInt(hunderedInterestPojos);
				// 插入每百元利息
				//admFiPerHundInterest.savePerHundInt(hunderedInterestPojos);
				admFiPerHundInterest.insertAllHundredInterest(hunderedInterestPojos);		
			}
		}
		return count;
	}
	
	
	/**
	 * 初始化证券权益信息，如果当天已经读入证券权益信息，系统不在自动产生
	 * @param markDate
	 * @throws Exception
	 */
	private void initBondEqu(Date markDate) throws Exception{
		BEN_RECORD ben_Record = new BEN_RECORD(ContextFactory.getContext().getUserCode());
		try {
			ben_Record.init("", "initBondEqu", markDate);
			ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
			ben_Record.setC_Item_Name("初始化证券权益信息");
			ben_Record.setC_Fun_Code(c_Fun_Code);
			//edit by shijian 2017-04-24 BUG #157646 【紧急】嘉实基金：日常调度任务定时任务执行失败
            //日志中保存功能ID，任务调度时前台可查询
			ben_Record.setC_FunRela_Id(funRelaId);
			ben_Record.BeginLog();
			ben_Record.appendDetailMes("开始初始化 " + YssFun.formatDate(markDate)+ " 日证券权益信息...");
			//cache.put(InfoType._BUSNESSLOG_SI.name(), ben_Record);
			sender.write(execProcCode, 
					ben_Record);
			
			//债券历史付息信息初始化
			//查询证券信息中债券计息起始日和计息截止日包含基准日期的债券，为这些债券产生完整的历史付息数据
			AdmFiHistoryPay admFiHistoryPay = new AdmFiHistoryPay(markDate);
			List<FiPayFactorBean> fiPayFactors = admFiHistoryPay.getFiPayFactorsWhereEqualsPayInterestDate();
			// 优化：在遍历之前取出证券信息与支付信息 byleeyu 20140306
			HashMap<String,FiPay> mapFiPay = admFiHistoryPay.getUniqueHistoryPay(markDate);
			HashMap<String,SecBase> mapSec = admFiHistoryPay.getFiInfo();
			int count = 0;
			if(null != fiPayFactors && ! fiPayFactors.isEmpty()){
				List<SecEqu> listEqu = null;
				DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");				
				for(FiPayFactorBean payFactor : fiPayFactors){
					FiPay secHistory = null;
					SecBase secBase = null;
					// 证券代码
					String secCode = payFactor.getSecCode();
					// 获取债券历史付息信息
					//FiPay secHistory = admFiHistoryPay.getUniqueHistoryPay(secCode,markDate);
					// 代码优化,byleeyu 20140306
					if(mapFiPay.containsKey(secCode)){
						secHistory = mapFiPay.get(secCode);
					}
					// ***************暂时先不考虑兑付***************
					// 获取债券基本信息
					// SecBase secBase = admFiHistoryPay.getFiInfo(secCode);
					// 代码优化,byleeyu 20140306
					if(mapSec.containsKey(secCode)){
						secBase = mapSec.get(secCode);
					}
					if(null != secBase){
						// 当日等于基本信息中退市日期，产生兑付交易数据
						if(YssFun.dateDiff(markDate, secBase.getD_OFF_LIST())==0){
							//记录日志
//							ben_Record.appendDetailMes(secBase.getC_SEC_CODE());
						}
						else{
							if(null != secHistory){
								// 当日等于历史付息信息中本期止息日，产生对息权益数据 并且当日不等于证券期止息日 By Jinghehe 2014-4-21
								if(YssFun.dateDiff(markDate, YssFun.toDate(secHistory.getD_END()))==0
										){
									// STORY29319股票、债券、基金派息，债券还本、兑付优化
									// 最后一期的对价派息信息（兑付）在初始化的时候也得产生
									// 删除结息日不等于当日的逻辑
									// xiaozhilong 20160422
//									&& 0 !=YssFun.dateDiff(markDate, YssFun.toDate(secBase.getD_AI_END()))
									//记录日志
									ben_Record.appendDetailMes(secHistory.getC_SEC_CODE());
									SecEqu equ = new SecEqu();
									//BEN_SEC_EQU equ = new BEN_SEC_EQU();
									equ.setC_DATA_IDF("Z");
									equ.setC_EQU_CLS("DJ");
									equ.setC_SEC_CODE(secHistory.getC_SEC_CODE());
									equ.setC_DS_CODE("DJPX_FHPX");
									// 登记日
									equ.setD_REG(dateFormat.format(markDate));
									// 除权日：登记日下一个自然日
									equ.setD_EXR(dateFormat.format(YssFun.addDay(markDate, 1)));
									// 到帐日：默认除权日，遇到节假日取下一个工作日
									if(HolidaysAide.isHoliday(YssFun.toDate(equ.getD_EXR()), HolidaysAide.getHolidaysCode(secHistory.getC_MKT_CODE()))){
										equ.setD_FINAL(dateFormat.format(HolidaysAide.getWorkDay(YssFun.toDate(equ.getD_EXR()), 1, secHistory.getC_MKT_CODE())));
									}else{
										equ.setD_FINAL(equ.getD_EXR());
									}
									// 币种从基本信息中取  --liuchi/2013.10.24
									equ.setC_DC_CODE(secBase.getC_DC_CODE());
									equ.setC_MKT_CODE(secHistory.getC_MKT_CODE());
									if(null == listEqu){
										listEqu = new ArrayList<SecEqu>();
									}
									AdmFiLcEqu filc = new AdmFiLcEqu();
									//生成债券（或理财产品）的【对价派息信息】数据时，检查【对价派息信息】中如果存在证券内码相同、登记日期相同、除权日期相同的手工数据时，不产生该条证券的数据
									// 判断是否已经存在数据
									boolean isExistEqu = filc.isExistEqu(equ);
									if(!isExistEqu){
										listEqu.add(equ);
									}
								}
							}
						}
					}
				}
				
				if(null != listEqu){
					count = listEqu.size();
					AdmFiLcEqu filc = new AdmFiLcEqu();
					// 删除已经存在数据
					//filc.deleteEqu(listEqu);
					// 保存债券、理财对息权益数据
					//filc.saveEqu(listEqu);
					//#bug155809 删除保存进行事务控制	
					filc.insertEqu(listEqu);
				}			
			}
			ben_Record.appendDetailMes(YssFun.formatDate(markDate) + " 日证券权益信息初始化完成");
			ben_Record.appendDetailMes("合计处理的证券数量为 " + count);
			ben_Record.appendDetailMes("合计产生证券权益记录总数为 " + count);
			ben_Record.EndLog_Success("初始化证券权益信息完毕");
		} catch (Throwable ex) {
//			ex.printStackTrace();
			logger.log("初始化证券权益信息失败", ex);
			ben_Record.appendDetailMes((ex.getCause() == null ? ex.getStackTrace()[0].toString():ex.getCause().getMessage()));
			ben_Record.EndLog_Fail("初始化证券权益信息失败");
			throw new Exception(ex);
		} finally {
			lstRecord.add(ben_Record);
			//cache.put(InfoType._BUSNESSLOG_SI.name(), ben_Record);
			sender.write(execProcCode, 
					ben_Record);
		}
	}
	
	/**
	 * 根据证券信息对象构造债券计息因子对象
	 * @param secBase
	 * @return
	 * @throws YssException
	 */
	private FiPayFactorBean buildFiPayFactorBeanBySec(SecBase secBase) throws YssException{
		FiPayFactorBean fiPayFactor = new FiPayFactorBean();
		fiPayFactor.setSecCode(secBase.getC_SEC_CODE());
		fiPayFactor.setMarket(secBase.getC_MKT_CODE());
		fiPayFactor.setIssueFaceValue(Double.parseDouble(secBase.getN_FV_ISSUE()));
		fiPayFactor.setCouponRate(secBase.getN_FV_IR().doubleValue());
		fiPayFactor.setPayFrequency(secBase.getC_DV_VAR_DUR());
		fiPayFactor.setBondBeginDate(YssFun.toDate(secBase.getD_AI_BEGIN()));
		fiPayFactor.setLicaiBeginDate(YssFun.toDate(StringUtil.IsNullOrEmpty(secBase.getD_SQAI_BEGIN())?secBase.getD_AI_BEGIN():secBase.getD_SQAI_BEGIN()));//edit by guoguangyi 2017-6-15 BUG #162776 【安信基金】债券审核报错
		fiPayFactor.setBondEndDate(YssFun.toDate(secBase.getD_AI_END()));
		return fiPayFactor;
		
	}
	
	public List<BEN_RECORD> getLogBeans() throws ServiceException {
		return lstRecord;
	}

	public void setLogBean(BEN_RECORD arg0) throws ServiceException {
		// TODO Auto-generated method stub

	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	@Override
	public void init(Object... args) throws ServiceException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Map.Entry<String, List<BEN_RECORD>> execute() throws Exception {
		String result = doBusOper(this.hmParams);
		Map<String, List<BEN_RECORD>> map = new HashMap<String, List<BEN_RECORD>>();
		map.put(result, lstRecord);
		//Map.Entry<String, List<BEN_RECORD>> retVal = new Map.Entry<String, List<BEN_RECORD>>();
		//recList = lstRecord;
		return (Map.Entry<String, List<BEN_RECORD>>)map.entrySet().toArray()[0];
	}

	@Override
	public List<BizItem> getBizItems() throws ServiceException {
		List<BizItem> list = new ArrayList<BizItem>();
		SysFun sysFunPojo = null;
		IFunDataService funService = YssServiceFactory.getInstance()
				.createService(IFunDataService.class);
		sysFunPojo = funService.getDataByCode("sysInit");
		if (sysFunPojo == null) {
			return list;
		}
		BizItem itemP = new BizItem();
		itemP.setC_BizItem_Code(sysFunPojo.getC_FUN_CODE());
		itemP.setC_BizItem_Name(sysFunPojo.getC_FUN_NAME());
		itemP.setC_BizItem_Code_P("[root]");
		itemP.setC_Fun_Code(sysFunPojo.getC_FUN_CODE());
		list.add(itemP);
		IDayfSysInitService dayfSysInitService = YssServiceFactory.getInstance().createService(IDayfSysInitService.class);
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("C_DV_TYPE", "INIT_ITEMS");
		QueryRes res = dayfSysInitService.queryData(map);
		List<BasePojo> dataList =res.getDataList();
		for (BasePojo pojo : dataList) {
			SysInitItemBean inti = (SysInitItemBean)pojo;
			BizItem item = new BizItem();
			item.setC_BizItem_Code(inti.getC_DV_ITEM_CODE());
			item.setC_BizItem_Name(inti.getC_DESC());
			item.setC_BizItem_Code_P(sysFunPojo.getC_FUN_CODE());
			item.setC_Fun_Code(sysFunPojo.getC_FUN_CODE());
			item.setN_Deatil(1);
//			item.setN_Order(YssFun.toInt(voc.getN_ORDER()));
			list.add(item);
		}
		
		logger.log("sec**********getBizItems");
		
		// 公共的清算接口
		//公共接口不迁移 chenbo
//		IAssetClearCtlService dd = YssServiceFactory.getInstance().createService(IAssetClearCtlService.class);
//		List<BizItem> a = dd.getBizInterface();
//		list.addAll(a);
		
		return list;
	}

	@Override
	public void init(HashMap<String, Object> paraMap) {
		hmParams = paraMap;		
	}

	@Override
	public List<BizItem> getRootBizItems() throws ServiceException {
		List<BizItem> list = new ArrayList<BizItem>();
		SysFun sysFunPojo = null;
		IFunDataService funService = YssServiceFactory.getInstance()
				.createService(IFunDataService.class);
		sysFunPojo = funService.getDataByCode("sysInit");
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
	}

	/**
	 * 接口增加方法，给调度方案使用，所以要保持原来的逻辑
	 */
	@Override
	public List<BizItem> getBizItems(List<String> codes)
			throws ServiceException {
		return this.getBizItems();
	}
	/**
	 * 初始化优先股每百元利息
	 * addby liyongjun 2015-12-23 STORY18596优先股业务
	 * @param markDate
	 * @throws Exception
	 */
	private void initBondYxgPerHund(Date markDate) throws Exception {
		BEN_RECORD ben_Record = new BEN_RECORD(ContextFactory.getContext().getUserCode());
		try {
			ben_Record.init("", "initBondYxgPerHund", markDate);
			ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
			ben_Record.setC_Item_Name("初始化优先股每百元利息");
			ben_Record.setC_Fun_Code(c_Fun_Code);
			ben_Record.BeginLog();
			ben_Record.appendDetailMes("开始计算 " + YssFun.formatDate(markDate)+ " 日优先股每百元利息...");
			sender.write(execProcCode, ben_Record);
			// 查询证券信息中优先股计息起始日和计息截止日包含基准日期的债券，为这些优先股产生完整的历史付息数据
			AdmFiHistoryPay admFiHistoryPay = new AdmFiHistoryPay(markDate);
			List<FiPayFactorBean> fiPayFactors = new ArrayList<FiPayFactorBean>();
			if(singleSecInit.size()>0) {
					fiPayFactors = singleSecInit;
			}else {
					fiPayFactors = admFiHistoryPay.getFiPayFactorsWhereMarkDateInPayPeriodYxg();
			}
			admFiHistoryPay.generateFiHistoryPayData(fiPayFactors,ben_Record);
			// -----开始产生优先股每百元利息
			int count = 0;
			if (null != fiPayFactors && !fiPayFactors.isEmpty()) {
				AdmFiPerHundredInterest admFiPerHundInterest = new AdmFiPerHundredInterest();
				List<String> secCodeList = new ArrayList<String>();
				for(FiPayFactorBean payFactor : fiPayFactors){
					secCodeList.add(payFactor.getSecCode());
				}
				List<FiPerHundredInterestBean> hunderedInterestPojos = admFiPerHundInterest.calcPerHundredInterest(secCodeList, markDate);
				if (null != hunderedInterestPojos) {
					count = hunderedInterestPojos.size();
					// 删除每百元利息
//					admFiPerHundInterest.deletePerHundInt(hunderedInterestPojos);
					// 插入每百元利息
//					admFiPerHundInterest.savePerHundInt(hunderedInterestPojos);
					//#155809 删除保存进行事务控制
					admFiPerHundInterest.insertAllHundredInterest(hunderedInterestPojos);
				}
			}
			//-----结束产生债券每百元利息
			// 记录每百元利息的业务处理日志
			ben_Record.appendDetailMes(YssFun.formatDate(markDate)+ " 日优先股每百元利息计算完成");
			ben_Record.appendDetailMes("合计处理优先股的数量为 " + count);
			ben_Record.appendDetailMes("合计产生每百元利息记录总数为 " + count);
			if (ben_Record.getC_Doing_Type() == DoingType.Fail)
				ben_Record.EndLog_Fail("初始化计算优先股每百元利息失败");
			else
				ben_Record.EndLog_Success("初始化计算优先股每百元利息完毕");
		} catch (Throwable ex) {
			logger.log("初始化计算优先股每百元利息失败",ex);
			ben_Record.appendDetailMes((ex.getCause() == null ? ex.getStackTrace()[0].toString() : ex.getCause().getMessage()));
			ben_Record.EndLog_Fail("初始化计算优先股每百元利息失败");
			throw new Exception(ex);
		} finally {
			lstRecord.add(ben_Record);
			sender.write(execProcCode, ben_Record);
		}
	}
	
	public String getFunRelaId() {
		return funRelaId;
	}

	public void setFunRelaId(String funRelaId) {
		this.funRelaId = funRelaId;
	}

}
