package com.yss.uco.elecreco.er.erdata.dao;

import java.io.File;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yss.framework.api.busoperservice.ILogger;
import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.DataProcServiceFactroy;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.framework.util.DateUtil;
import com.yss.uco.elecreco.er.erdata.assist.ParaEnum;
import com.yss.uco.elecreco.er.erdata.assist.SqlAssistant;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.dblgzb.GeneDBLGZBDataService;
import com.yss.uco.elecreco.er.generate.gzb.GeneGZBDataService;
import com.yss.uco.elecreco.er.generate.gzb.split.SplitGeneGZBDataService;
import com.yss.uco.elecreco.er.generate.jzcbdb.GeneJZCBDBDataService;
import com.yss.uco.elecreco.er.generate.kmb.GeneKMBDataService;
import com.yss.uco.elecreco.er.generate.kmb.split.SplitGeneKMBDataService;
import com.yss.uco.elecreco.er.generate.lrb.GeneLRBDataService;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.syzqyb.GeneSYZQYBDataService;
import com.yss.uco.elecreco.er.generate.trans.GeneTransDataService;
import com.yss.uco.elecreco.er.generate.yeb.GeneYEBDataService;
import com.yss.uco.elecreco.er.generate.zcfzb.GeneZCFZBDataService;
import com.yss.uco.elecreco.er.template.pojo.DataSource;
import com.yss.uco.elecreco.er.template.pojo.Deploy;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.er.template.util.ReadMeTool;
import com.yss.uco.elecreco.er.template.util.TemplateManager;
import com.yss.uco.elecreco.support.dzdz.common.impl.BaoWenBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

/**生成电子对账数据类
 * @author Orlando
 *
 */
public class ErDataDao extends GeneralDao {

	private ErDataDaoSqlBuilder erSqlBuilder;

	/**
	 * 日志记录
	 */
	private BEN_RECORD ben_Record;
	
	/**
	 * 功能代码
	 */
	private String funCode = " ";
	
	/**
	 * 执行编号
	 */
	private String c_Dispatch_ID = " ";
	

	//方案代码
	private String c_PLAN_CODE = " ";

	/**
	 * 组合
	 */
	private String port = "";

	/**
	 * 对账类型代码
	 */
	private String dzcode = "";

	/**
	 * 报文序号
	 */
	private String  sn = "";
	
	/**
	 * 日期
	 */
	private Date date = null;
	/**
	 * 日期字符串 yyyy-MM-dd
	 */
	private String c_date = "";

	private BaoWenBuilder baoWenBulider = null;
//	/**
//	 * 数据库链接
//	 */
	//private Connection conn = null;
	
	private String userCode = null;
	
	/**
	 * 对账模板
	 */
	private DzTemplate template = null;

	public ILogger getSender() {
		return sender;
	}

	public void setSender(ILogger sender) {
		this.sender = sender;
	}
	
	public String getSn() {
		return sn;
	}


	private ILogger sender = null;

	public String getExecProcCode() {
		return execProcCode;
	}

	public void setExecProcCode(String execProcCode) {
		this.execProcCode = execProcCode;
	}

	private String execProcCode = "";

	int n_SumExecuteRow = 0; // 共执行的记录数

	public ErDataDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.erSqlBuilder = (ErDataDaoSqlBuilder) sqlBuilder;
		this.sender = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		baoWenBulider = new BaoWenBuilder();
	}

	/**
	 * 初始化划款指令参数
	 * 
	 * @param port
	 * @param hkcode
	 * @param date
	 */
	public void init(String port, String dzcode, Date date, DzTemplate template, String userCode) {
		this.port = port;
		this.dzcode = dzcode;
		this.date = date;
		this.c_date = DateUtil.dateToString(date, "yyyy-MM-dd");
		this.template= template;
		this.userCode = userCode;
		//this.conn = conn;
		// this.ben_Record = ben_Record;
	}
	
	public void initExeLog(String funCode, String c_Dispatch_ID, String c_PLAN_CODE)
	{
		this.funCode = funCode;
		this.c_Dispatch_ID = c_Dispatch_ID;
		this.c_PLAN_CODE = c_PLAN_CODE;
	}
//	/**
//	 * ADD 2015-02-07 LY 数据检查
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	private boolean check(Connection conn) throws Exception {
//		// 判断如果净值表中无数据则代表未生成过估值报表
//		if (dzcode.equals("1011") && !this.getAssetStatsPojos(port, date,conn)) {
//			String msg = "[" + port + "]在[" + DateUtil.dateToString(date, DateUtil.LONG_DATE_FORMAT) + "]日期尚未产生估值报表！";
//			ben_Record.setC_Doing_Type(DoingType.Warn);
//			ben_Record.setC_Mes_Text("尚未产生估值报表！");
//			ben_Record.appendDetailMes(msg);
//			return false;
//		}

//		if (checkstatus(conn)) {
//			ben_Record.EndLog_Warn("已存在已生成数据，不能重新产生！");
//			return false;
//		}
//		return true;
//
//	}

	/**
	 * 执行生成划款指令操作
	 * 
	 * @throws Exception
	 */
	public BEN_RECORD doOper() throws Exception {
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			// 声明日志
			n_SumExecuteRow = 0;
			ben_Record = new BEN_RECORD();
			String fileType = dzcode;
			if(dzcode.indexOf("_")>=0){
				fileType = dzcode.split("_",-1)[1];
			}
			logger.info("dzcode：" + dzcode);
			ben_Record.init(port, dzcode, date);
			ben_Record.setC_Item_Name(FileTypeEnum.getValueByKey(fileType));
			ben_Record.setC_User_Code(this.userCode);
			ben_Record.setC_Dispatch_ID(c_Dispatch_ID);
			ben_Record.setC_Idf_Code(c_PLAN_CODE);
			ben_Record.setC_Fun_Code(funCode);
			ben_Record.BeginLog();
			// cache.put(InfoType._BUSNESSLOG_DZ.name(), ben_Record);
			sender.write(execProcCode, ben_Record);
			ben_Record.setC_User_Code(this.userCode);

			ben_Record.appendDetailMes("***********产生电子对账数据***************");
			ben_Record.appendDetailMes("-----开始时间："
					+ YssFun.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
					+ "----");

			// 如果当前电子对账信息表中不存在待发送的数据，则开始产生，否则不再产生相应的数据
			/**Start 20151225 modified by liubo.BUG #123807 中信电子对账走MQ通道服务端解析报错
			 * 中信现场测试的时候发现，同时勾选多个对账项目的时候，对账信息序号会重复。
			 * 调试后发现是因为从对账信息表取某个日期最大的序号时，因为线程递进速度太快，
			 * 前一个线程还未执行完，新的数据还未插入对账信息表，后一个线程就已经在取最大序号了，这样就会导致多个线程取到一个序号
			 * 这种情况就需要做一个全局锁，强制要求一个线程执行完，对账信息表更新过后，下一个线程才能继续执行*/
			//synchronized(YssCons.SYS_LOCK_TIME)
//			{
			//	BUG162306【鹏华基金】【紧急】电子对账 已生成存在记录，再次点击生成，要覆盖之前生成的流水。
				//if (check(conn)) {
				// Start edit by sunhe 20170613
				// 已生成的状态数据可以被重新生成覆盖，已发送的数据不可被覆盖 
				//BUG210176产品组合的组合代码和资产代码不同时新生成的电子对账数据不会覆盖原来数据
				deleteGeneData(conn);
				executeProcedure(conn);
//			}
			/**End 20151225 modified by liubo.BUG #123807 中信电子对账走MQ通道服务端解析报错*/

			// if (!checkstatus()) {
			// // 执行存储过程
			// executeProcedure();
			// } else {
			// // ben_Record.appendDetailMes("电子对账信息表中已存在待发送的数据，不能重新产生！");
			// // ben_Record.EndLog_Success("执行成功");
			// // ben_Record.EndLog_Warn("已存在审核数据，不能重新产生！");
			// ben_Record.EndLog_Warn("已存在已生成数据，不能重新产生！");
			// }

			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			ben_Record.appendDetailMes(ex.getMessage());
			ben_Record.EndLog_Fail("执行失败，其中执行成功了" + n_SumExecuteRow + "条数据。");
			logger.error("生成电子对账数据出错：" + ex.getMessage(), ex);
		} finally {
			ben_Record.appendDetailMes("-----结束时间："
					+ YssFun.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss")
					+ "----");
			sender.write(execProcCode, ben_Record);
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
		return ben_Record;
	}
	
	/**
	 * 判断如果净值表中无数据则代表未生成过估值报表
	 * 未生成过估值报表则不生成电子对账——估值表
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private boolean check1011(Connection conn) throws Exception {
		if (dzcode.equalsIgnoreCase("1011") && !this.getAssetStatsPojos(port, date, conn)) {
			String msg = "[" + port + "]在[" + this.c_date + "]日期尚未产生估值报表！";
			ben_Record.setC_Doing_Type(DoingType.Warn);
			ben_Record.setC_Mes_Text("尚未产生估值报表！");
			ben_Record.appendDetailMes(msg);
			return false;
		}
		return true;
	}
	/**
	 * Author : wulongxing
	 * Date   : 2016-12-1
	 * Status : Add
	 * Comment: 查询生成电子对账数据模式 true则使用存储过程模式，否则使用代码逻辑生成
	 * @throws Exception
	 */
	private boolean getElecMode() throws Exception{
		boolean mark = false;
		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
		PropertiesUtil propertiesUtil = new PropertiesUtil();
		String fileName = fileUtil.getFilePath() + "runtime.properties";
		File file = new File(fileName);
		if(file.exists()){
			Properties properties = propertiesUtil.Properties(fileName);
			mark = Boolean.valueOf(properties.getProperty("elec_old_mode","false"));//BUG24121121.5、21.6版本生成电子对账数据默认走代码模式
		}
		return mark;
	}
	/**
	 * 执行存储过程
	 * 
	 * @return
	 * @throws Exception
	 */
	private void executeProcedure(Connection conn) throws Exception {
		String result = "";
		String result_detail = "";

		CallableStatement proc = null;
		Map<String, String> map = new HashMap<String, String>();
		try {
			if(this.template != null){
				executeTemplate(conn);
			}else{	
				if(getElecMode() == true){
					//// true 就是产生估值表，false 就是没有产生估值表 
					boolean isHaveData = check1011(conn);
					if (dzcode.equalsIgnoreCase("1031")) {// 科目表
						proc = conn.prepareCall("{call PRC_ER_KM(?,?,?,?,?)}");
						proc.setString(1, port);
						proc.setString(2, YssFun.formatDate(date, "yyyy-MM-dd"));
						proc.setString(3, userCode);
						proc.registerOutParameter(4, Types.VARCHAR);
						proc.registerOutParameter(5, Types.VARCHAR);
					} else if (dzcode.equalsIgnoreCase("1011") && isHaveData) {// 估值表
						proc = conn.prepareCall("{call PRC_ER_GZ(?,?,?,?,?)}");
						proc.setString(1, port);
						proc.setString(2, YssFun.formatDate(date, "yyyy-MM-dd"));
						proc.setString(3, userCode);
						proc.registerOutParameter(4, Types.VARCHAR);
						proc.registerOutParameter(5, Types.VARCHAR);
					} else if (dzcode.equalsIgnoreCase("1001")) {// 余额表
						proc = conn.prepareCall("{call PRC_ER_YE(?,?,?,?,?)}");
						proc.setString(1, port);
						proc.setString(2, YssFun.formatDate(date, "yyyy-MM-dd"));
						proc.setString(3, userCode);
						proc.registerOutParameter(4, Types.VARCHAR);
						proc.registerOutParameter(5, Types.VARCHAR);
					}else if (dzcode.indexOf("_") > 0) {
						// 如果是xx_xxxx格式，
						map = getElecReport(conn);
						// 财务报表数据表有对应组合代码、报表代码的数据
						if (map != null && map.get("D_START_DATE") != null) {
							Date startDate = YssFun.parseDate(map.get("D_START_DATE"));
							Date endDate = YssFun.parseDate(map.get("D_END_DATE"));
							String C_RPT_TYPE = dzcode.substring(0, 2);
							String c_start_date = formatStartDateByReport(startDate, C_RPT_TYPE);
							String c_end_date = formatEndDateByReport(endDate, C_RPT_TYPE);
							String reportTime = map.get("C_REPORT_TIME");
							if (dzcode.indexOf("1701")>=0 || dzcode.indexOf("1711")>=0) {//资产负债表  
								proc = conn.prepareCall("{call PRC_ER_ZCFZ(?,?,?,?,?,?,?,?,?)}");
								proc.setString(1, port);
								proc.setString(2, c_date);
								proc.setString(3, c_start_date);
								proc.setString(4, c_end_date);
								proc.setString(5, reportTime);
								proc.setString(6, C_RPT_TYPE);
								proc.setString(7, userCode);
								proc.registerOutParameter(8, Types.VARCHAR);
								proc.registerOutParameter(9, Types.VARCHAR);
							} else if (dzcode.indexOf("1801")>=0 || dzcode.indexOf("1811")>=0) {//利润表
								proc = conn.prepareCall("{call PRC_ER_LR(?,?,?,?,?,?,?,?,?)}");
								proc.setString(1, port);
								proc.setString(2, c_date);
								proc.setString(3, c_start_date);
								proc.setString(4, c_end_date);
								proc.setString(5, reportTime);
								proc.setString(6, C_RPT_TYPE);
								proc.setString(7, userCode);
								proc.registerOutParameter(8, Types.VARCHAR);
								proc.registerOutParameter(9, Types.VARCHAR);
							} else if (dzcode.indexOf("1901")>=0) {// 所有者权益（基金净值）变动表
								proc = conn.prepareCall("{call PRC_ER_SYZQYBD(?,?,?,?,?,?,?,?,?)}");
								proc.setString(1, port);
								proc.setString(2, c_date);
								proc.setString(3, c_start_date);
								proc.setString(4, c_end_date);
								proc.setString(5, reportTime);
								proc.setString(6, C_RPT_TYPE);
								proc.setString(7, userCode);
								proc.registerOutParameter(8, Types.VARCHAR);
								proc.registerOutParameter(9, Types.VARCHAR);
							}
						}
					}

					if (proc != null) {
						// 执行存储过程
						proc.execute();
						if(dzcode.indexOf("_")>=0) {
							result = proc.getString(8);
							result_detail = proc.getString(9);
						} else {
							result = proc.getString(4);
							result_detail = proc.getString(5);
						}
						ben_Record.appendDetailMes(result_detail);

						if (result.startsWith("DZ")) {
							this.sn = result;
							ben_Record.EndLog_Success("执行成功");
						} else if (result.equalsIgnoreCase("Warn")) {
							ben_Record.EndLog_Warn("没有数据产生");
						} else {
							ben_Record.EndLog_Fail("执行失败");
						}
					}else if(map==null) {
						ben_Record.EndLog_Warn("请检查是否配置财务报表或已生成财务报表数据");
					}else if(!isHaveData)
					{
						ben_Record.EndLog_Warn("尚未产生估值报表！");
					}
					else {
						ben_Record.EndLog_Warn("接口没有实现");
					}
				}else{
					GeneElecDataService geneService = null;
					Map<String, Object> resultMap = null;
					//// true 就是产生估值表，false 就是没有产生估值表 
					boolean isHaveData = check1011(conn);
					if (dzcode.equalsIgnoreCase("1031")) {
//						geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneKMBDataService.class);
						//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
						if(isSplitGenerate(port,conn))
						{
							geneService = new SplitGeneKMBDataService();
						}else
						{
							geneService = new GeneKMBDataService();
						}
						geneService.init(conn, port, c_date, userCode);
						resultMap = geneService.geneElecData();
					} else if (dzcode.equalsIgnoreCase("1011") && isHaveData) {
//						geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneGZBDataService.class);
						//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
						if(isSplitGenerate(port,conn))
						{
							geneService = new SplitGeneGZBDataService();
						}else
						{
							geneService = new GeneGZBDataService();
						}
						geneService.init(conn, port, c_date, userCode);
						resultMap = geneService.geneElecData();
					} else if (dzcode.equalsIgnoreCase("1013")) {//双估值表
						geneService = new GeneDBLGZBDataService();
						geneService.init(conn, port, c_date, userCode, ben_Record);
						resultMap = geneService.geneElecData();
					} else if (dzcode.equalsIgnoreCase("1001")) {
//						geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneYEBDataService.class);
						geneService = new GeneYEBDataService();
						geneService.init(conn, port, c_date, userCode);
						resultMap = geneService.geneElecData();
					} else if (dzcode.indexOf("_") > 0) {
						// 如果是xx_xxxx格式，
						boolean transFlag = false;
						if(dzcode.indexOf("A01_")>=0){
							transFlag = true;
						}else{
							map = getElecReport(conn);
							transFlag = false;
						}
						
						// 财务报表数据表有对应组合代码、报表代码的数据
						if (map!=null && map.get("D_START_DATE")!=null) {
							Date startDate = YssFun.parseDate(map.get("D_START_DATE"));
							Date endDate = YssFun.parseDate(map.get("D_END_DATE"));
							String C_RPT_TYPE = dzcode.substring(0, 2);
							String c_start_date = formatStartDateByReport(startDate, C_RPT_TYPE);
							String c_end_date = formatEndDateByReport(endDate, C_RPT_TYPE);
							String reportTime = map.get("C_REPORT_TIME");
							String reportCode = map.get("C_REPORT_CODE");
							if (dzcode.indexOf("1701")>=0 || dzcode.indexOf("1711")>=0) {
								String c_FILE_TYPE = "";
								if (dzcode.indexOf("1701")>=0 ) {
									c_FILE_TYPE = "1701";
								}else {
									c_FILE_TYPE = "1711";
								}
//								geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneZCFZBDataService.class);
								geneService = new GeneZCFZBDataService(c_FILE_TYPE);
								geneService.init(conn, port, c_date, userCode);
								geneService.init2(C_RPT_TYPE, c_start_date, c_end_date, reportTime, reportCode);
								resultMap = geneService.geneElecData();
							} else if (dzcode.indexOf("1801")>=0 || dzcode.indexOf("1811")>=0) {
//								geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneLRBDataService.class);
								String c_FILE_TYPE = "";
								if (dzcode.indexOf("1801")>=0 ) {
									c_FILE_TYPE = "1801";
								}else {
									c_FILE_TYPE = "1811";
								}
								geneService = new GeneLRBDataService(c_FILE_TYPE);
								geneService.init(conn, port, c_date, userCode);
								geneService.init2(C_RPT_TYPE, c_start_date, c_end_date, reportTime, reportCode);
								resultMap = geneService.geneElecData();
							} else if (dzcode.indexOf("1901")>=0) {
//								geneService = YssClassFactory.getInstance().createInstance("YSSELECRECO", GeneSYZQYBDataService.class);
								geneService = new GeneSYZQYBDataService();
								geneService.init(conn, port, c_date, userCode);
								geneService.init2(C_RPT_TYPE, c_start_date, c_end_date, reportTime, reportCode);
								resultMap = geneService.geneElecData();
							}else if (dzcode.indexOf("1903")>=0) {
								geneService = new GeneJZCBDBDataService();
								geneService.init(conn, port, c_date, userCode);
								geneService.init2(C_RPT_TYPE, c_start_date, c_end_date, reportTime, reportCode);
								resultMap = geneService.geneElecData();
							}
						}
						
						//STORY #88316 华夏基金-MOM产品个性化需求——场外和银行间交易数据发送托管行 
						if(transFlag){
							if (dzcode.indexOf("A001")>=0) {      
								geneService = new GeneTransDataService();
								geneService.init(conn, port, c_date, userCode, ben_Record);
								resultMap = geneService.geneElecData();
							}
						}
					} 
					if(resultMap != null){
						result = resultMap.get("result").toString();
						result_detail =  resultMap.get("resultDetail").toString();
					}
					if (result.startsWith("DZ")) {
						this.sn = result;
						ben_Record.EndLog_Success("执行成功");
					} else if (result.equalsIgnoreCase("Warn")) {
						ben_Record.EndLog_Warn("没有数据产生");
						//重新初始化不提示
						if(!StringUtil.IsNullOrEmptyT(result_detail) && result_detail.contains("不需要生成数据")){
							ben_Record = new BEN_RECORD();
							ben_Record.EndLog_Warn("");
						}
					} else if (result.equalsIgnoreCase("Fail")) {
						ben_Record.EndLog_Fail("执行失败");
					} else if(map == null) {
						ben_Record.EndLog_Warn("请检查是否配置财务报表或已生成财务报表数据");
					}else if(!isHaveData)
					{
						ben_Record.EndLog_Warn("尚未产生估值报表！");
					}
					//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
					//添加拆分生成的错误信息
					else if(result.equalsIgnoreCase("SplitRuleFail"))
					{
						ben_Record.EndLog_Warn("存在没有拆分的明细科目！");
					}else if(result.equalsIgnoreCase("SplitRelaFail"))
					{
						ben_Record.EndLog_Warn("没有设置多托管行拆分设置！");
					}
					else {
						ben_Record.EndLog_Warn("接口没有实现");
					}
					ben_Record.appendDetailMes(result_detail);
				}
			}
			
			// STORY #84622 【鹏华基金】产生电子对账功能日志优化 
			String logMesText = ben_Record.getC_Mes_Text();
			String fileType_P = "日报";
			String fileType = dzcode;
			if(dzcode.indexOf("_")>=0){
				fileType_P = dzcode.split("_",-1)[0];
				fileType = dzcode.split("_",-1)[1];
				fileType_P = FileTypeEnum.getValueByKey(fileType_P);
			}
			
			fileType = FileTypeEnum.getValueByKey(fileType);
			ben_Record.setC_Mes_Text("生成"+fileType_P+"_" + fileType+"," + logMesText);
		} catch (Exception ex) {
			throw ex;
		} finally {
			this.closeStatementFinal(proc);
		}
	}
	
//	public BigDecimal getErValue(String portName, String reportType, String rowNum, String colNum) throws SQLException {
//		ResultSet rs = null;
//		PreparedStatement pst = null;
//		StringBuffer buf = new StringBuffer();
//		try {
//			buf.append(" select c_data from t_f_rc_rep_tbdata where N_ROW_NUM in ");
//			buf.append(" (select N_ROW_NUM+"+rowNum+" from t_f_rc_rep_tbcell where c_cell_content like '%"+portName+"%' and c_report_code='"+reportType+"') ");
//			buf.append(" and N_COL_NUM in ");
//			buf.append(" (select N_COL_NUM+"+colNum+" as N_COL_NUM from t_f_rc_rep_tbcell where c_cell_content like '%"+portName+"%' and c_report_code='"+reportType+"')");
//			String sql = buf.toString();
//			pst = conn.prepareStatement(sql);
//			rs = pst.executeQuery();
//			while(rs.next()) {
//				return rs.getBigDecimal("C_DATA");
//			}
//		} catch(Exception e) {
//		} finally {
//			StringUtil.clearStringBuffer(buf);
//			DbFun.closeStatementFinal(pst);
//			DbFun.closeResultSetFinal(rs);
//		}
//		return new BigDecimal("0");
//	}
	
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 是否拆分生成科目表，估值表
	 * @param port
	 * @param conn
	 * @return
	 * @throws Exception
	 */
	private boolean isSplitGenerate(String port,Connection conn) throws Exception {
		AdmPortActParams paras = new AdmPortActParams(port, new Date());
		paras.setDbConn(conn);
		paras.initActParams();
		String status = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_GZBCFFS);
		if("1".equalsIgnoreCase(status))
		{
			return true;
		}
		return false;
	}

	/**
	 * 获取财务报表数据表数据起始/结束日期
	 * @param reportType
	 * @return
	 * @throws Exception
	 */
	private Map<String, String> getElecReport(Connection conn) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		// 查询财务报表数据表是否有对应数据
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		PreparedStatement pst = null;
		// 报表类型
		String reportType = "";
		if(dzcode.indexOf("03_")>=0) reportType = "REPORT_MONTH";
		else if(dzcode.indexOf("04_")>=0) reportType = "REPORT_SEASON";
		else if(dzcode.indexOf("05_")>=0) reportType = "REPORT_HALF_YEAR";
		else if(dzcode.indexOf("06_")>=0) reportType = "REPORT_YEAR";
		// 电子对账类型
		String dzCode = dzcode.split("_")[1];
		//wlx 20161212 STORY36615【紧急】南方基金-系统需要支持存在多个财务报表的电子对账
		sqlBuffer.append(" SELECT A.* FROM T_F_RC_REP_TBDATA A ");
		sqlBuffer.append(" JOIN ");
		sqlBuffer.append(" (SELECT C_REPORT_CODE FROM (");
		sqlBuffer.append(" SELECT * FROM ( ");
		sqlBuffer.append("  SELECT A.C_REPORT_CODE,A.C_RPT_TYPE, (case when A.C_RPT_TYPE = '"+reportType+"' then 1 when trim(A.C_RPT_TYPE) IS NULL then 2 else 3 end) AS CNT FROM T_P_ER_REPCFG A"); 
		sqlBuffer.append("   WHERE A.N_CHECK_STATE = 1 AND A.C_PORT_CODE = ? AND A.C_DZ_CODE = ?"); // 首先取配置了组合代码的报表
		sqlBuffer.append("  UNION ALL" );
		sqlBuffer.append("  SELECT A.C_REPORT_CODE,A.C_RPT_TYPE, (case when A.C_RPT_TYPE = '"+reportType+"' then 4 when trim(A.C_RPT_TYPE) IS NULL then 5 else 6 end) AS CNT FROM T_P_ER_REPCFG A"); 
		sqlBuffer.append(" left join (select b.c_group_code, c.c_port_code ");
		sqlBuffer.append(" from T_P_AB_GROUP b ");
		sqlBuffer.append(" left join T_P_AB_GROUP_RELA c ");
		sqlBuffer.append(" on b.c_group_code = c.c_group_code ");
		sqlBuffer.append(" where b.n_check_state = 1 ");
		sqlBuffer.append(" and c.n_check_state = 1) d ON d.c_group_code = a.c_port_code ");
		sqlBuffer.append("   WHERE A.N_CHECK_STATE = 1 AND D.C_PORT_CODE = ? AND A.C_DZ_CODE = ?"); // 首先取配置了组合代码的报表
		sqlBuffer.append("  UNION ALL" );
		//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
		//优先匹配资产类型相同的，然后是为空的
		sqlBuffer.append("  SELECT A.C_REPORT_CODE,A.C_RPT_TYPE, "); 
		//STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
		sqlBuffer.append(" (case when A.C_RPT_TYPE = '"+reportType+"' then 7 ");
        sqlBuffer.append("  when trim(A.C_RPT_TYPE) IS NULL AND trim(A.C_DAT_CODE) is not null then 8 ");
        sqlBuffer.append(" else 9 end) "); 
        sqlBuffer.append(" AS CNT "); 
		sqlBuffer.append(" FROM T_P_ER_REPCFG A "); 
		sqlBuffer.append("   WHERE  A.N_CHECK_STATE = 1 AND A.C_ORG_CODE IN (SELECT C_RELA_CODE");
		sqlBuffer.append("                            FROM T_P_AB_PORT_RELA B"); 
		sqlBuffer.append("                            JOIN T_D_ER_RELA C");
		sqlBuffer.append("                              ON B.C_RELA_CODE = C.C_TGH_CODE");
		sqlBuffer.append("                           WHERE B.C_RELA_TYPE = 'RELA_ORG'");
		sqlBuffer.append("                             AND B.C_PORT_CODE = ?)");// 其次取配置了托管行的报表
		//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
		//优先匹配资产类型相同的，然后是为空的
		sqlBuffer.append("     AND (A.C_DAT_CODE in ( SELECT C_DAT_CODE FROM T_P_AB_PORT where C_PORT_CODE = ?) or TRIM(A.C_DAT_CODE) is NULL) ");
		sqlBuffer.append("     AND A.C_DZ_CODE = ? AND TRIM(A.C_PORT_CODE) IS NULL "); //BUG194060嘉实基金-电子对账-电子对账报表配置界面维护数据优化
		sqlBuffer.append("  UNION ALL" );
		sqlBuffer.append("  SELECT C_REPORT_CODE,'' AS C_RPT_TYPE, 10 AS CNT FROM T_F_RC_REP_TPL WHERE N_CHECK_STATE = 1 AND C_DZ_CODE = ? ) TT  ");//最后取财务报表配置的报表
		sqlBuffer.append(" WHERE (TT.C_RPT_TYPE = ? or trim(TT.C_RPT_TYPE)is null) ORDER BY TT.CNT ASC ) ");
		//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
		//排序要放到过滤前面
		sqlBuffer.append(" WHERE ROWNUM = 1 ) B ");
		sqlBuffer.append("	 ON A.C_REPORT_CODE = B.C_REPORT_CODE");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND A.C_REPORT_TYPE = ? "); 
	    
		try {
			pst = conn.prepareStatement(sqlBuffer.toString());
			int index = 1;
			
			pst.setString(index++, port);
			pst.setString(index++, dzCode);
			
			pst.setString(index++, port);
			pst.setString(index++, dzCode);
			
			pst.setString(index++, port);
			//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
			pst.setString(index++, port);
			pst.setString(index++, dzCode);
			
			pst.setString(index++, dzCode);
			
			pst.setString(index++, reportType);
			
			pst.setString(index++, port);
			pst.setString(index++, reportType);
			rs = pst.executeQuery();
			while(rs.next()) {
				String datePeriod = rs.getString("C_REPORT_TIME");
				map.put("C_REPORT_TIME", datePeriod);
				String startDate = datePeriod.trim().split("\\|")[0];
				String endDate = datePeriod.trim().split("\\|")[1];
				if(date.compareTo(YssFun.parseDate(startDate))>=0 && date.compareTo(YssFun.parseDate(endDate))<=0) {
					map.put("D_START_DATE", startDate);
					map.put("D_END_DATE", endDate);
					map.put("C_REPORT_CODE", rs.getString("C_REPORT_CODE"));
					return map;
				}
			}
		} catch (SQLException e) {
			throw e;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
		}
		return null;		
	}
	
	/**
	 * 根据报表类型输出开始日期格式
	 * 03：月报；04：季报；05半年报；06：年报
	 * 日报：YYYYMMDD 如20060804代表 2006年8月4日
	 * 周报：YYYYMMWW（WW:01-05代表一个月中的五周）如20060801 代表 2006年8月第一周
	 * 月报：YYYYMM01如 20060801代表2006年8月
	 * 季报：YYYYJJ01（JJ:01－第一季度，04－第二季度，07－第三季度，10－第四季度）
	 * 如 20060701代表2006年第三季度
	 * 半年报：YYYYBB01（BB：01－上半年，07－下半年）如 20060707代表2006年下半年
	 * 年报：YYYY0101如 20060101代表2006年
	 * 统计报表：YYYYMMDD,YYYYMMDD 如20060804,20060831代表2006年08月04日到2006年08月31日
	 * @param 
	 * @return
	 * @throws Exception
	 */
	public String formatStartDateByReport(Date date, String rptType) throws Exception {
		String sDate = YssFun.formatDate(date, "yyyyMMdd").toString();
		int num = (Integer.parseInt(sDate.subSequence(4, 6).toString())-1) / 3;
		String quarter = "";
		if(rptType.equalsIgnoreCase("03")) { // 月报
			sDate = sDate.substring(0, 6) + "01";
		} else if(rptType.equalsIgnoreCase("04")) { // 季报
			if(num==0) quarter="01";
			else if(num==1) quarter="04";
			else if(num==2) quarter="07";
			else quarter="10";
			sDate = sDate.substring(0, 4) + quarter + "01";
		} else if(rptType.equalsIgnoreCase("05")) { // 半年报
			if(num==0) quarter="01";
			else quarter="07";
			sDate = sDate.substring(0, 4) + quarter + "01";
		} else if(rptType.equalsIgnoreCase("06")) { // 年报
			sDate = sDate.substring(0, 4) + "0101";
		}
		
		return sDate;
	}
	
	/**
	 * 根据报表类型输出开始日期格式
	 * @param date
	 * @param rptType
	 * @return
	 * @throws Exception
	 */
	public String formatEndDateByReport(Date date, String rptType) throws Exception {
		String sDate = YssFun.formatDate(date, "yyyyMMdd").toString();
		int num = (Integer.parseInt(sDate.substring(4, 6))-1) / 3;
		String quarter = "";
		if(rptType.equalsIgnoreCase("03")) { // 月报
			if(sDate.substring(4, 6).equalsIgnoreCase("02")) {
				int year = Integer.parseInt(sDate.subSequence(0, 4).toString());
				if ((year%100 == 0 && year%400 == 0) || (year%100 != 0 && year%4 == 0))
                {
					sDate = sDate.substring(0, 6) + "29";
                } else {
                	sDate = sDate.substring(0, 6) + "28";
                }
			} else if(sDate.substring(4, 6).equalsIgnoreCase("04") || sDate.substring(4, 6).equalsIgnoreCase("06") ||
					sDate.substring(4, 6).equalsIgnoreCase("09") || sDate.substring(4, 6).equalsIgnoreCase("11")) {
				sDate = sDate.substring(0, 6) + "30";
			} else {
				sDate = sDate.substring(0, 6) + "31";
			}
		} else if(rptType.equalsIgnoreCase("04")) { // 季报
			if(num==0) quarter="03";
			else if(num==1) quarter="06";
			else if(num==2) quarter="09";
			else quarter="12";
			if(num==1 || num==2) {
				sDate = sDate.substring(0, 4) + quarter + "30";
			} else {
				sDate = sDate.substring(0, 4) + quarter + "31";
			}
		} else if(rptType.equalsIgnoreCase("05")) { // 半年报
			if(num==0 || num==1) {
				sDate = sDate.substring(0, 4) + "0630";
			}
			else {
				sDate = sDate.substring(0, 4) + "1231";
			}
		} else if(rptType.equalsIgnoreCase("06")) { // 年报
			sDate = sDate.substring(0, 4) + "1231";
		}
		
		return sDate;
	}

	/**
	 * liuxiang 2015/2/16 STORY #20392 华泰证券：电子对账可自定义发送估值表中的列
	 * 
	 * 执行模板配置信息
	 */
	private void executeTemplate(Connection conn)  throws Exception {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CallableStatement proc = null;
		boolean autoCommit = true;
		try{
			String filePath = TemplateManager.NewInstance().convertYSSAPPPath(template.getC_TMPL_PATH());
			File deployFile = new File(filePath +  ReadMeTool.DEPLOY_FILE);
			if(!deployFile.exists()){
				ben_Record.EndLog_Warn("对账模板丢失");
			}else{
				autoCommit = conn.getAutoCommit();
				conn.setAutoCommit(false);
				int kmLevel = 1; // 科目级别
				int count = 0; // 生成数据条数
				String c_SN = ""; // 报文序号
				String c_ASS_CODE = "";// 基金代码（资产代码）
				SqlAssistant sqlAssistant = new SqlAssistant();
				String sql = "";
				String targetSql = "";
				Deploy deploy = ReadMeTool.readDeploy(filePath +  ReadMeTool.DEPLOY_FILE);
				String pattStr = ParaEnum.C_PORT_CODE.toString()+ "|"  + ParaEnum.D_DATE.toString() +
						"|" + ParaEnum.C_SN.toString() + "|" + ParaEnum.N_KM_LEVEL.toString();
				Pattern pattern = Pattern.compile(pattStr);
				
				// 取出基金代码
				pstmt = conn.prepareStatement("select C_ASS_CODE　FROM T_P_AB_PORT WHERE C_PORT_CODE = ?");
				pstmt.setString(1, port);
				rs = pstmt.executeQuery();
				if(rs.next()){
					c_ASS_CODE = rs.getString(1);
				}
				
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pstmt);
				
				// 取出科目级别
				proc = conn.prepareCall("{call pro_getplan(?,?,?,?)}");
				proc.setString(1, c_date);
				proc.setString(2, port);
				proc.registerOutParameter(3, Types.VARCHAR);
				proc.registerOutParameter(4, Types.INTEGER);
				// 执行存储过程
				proc.execute();
				kmLevel = proc.getInt(4);
				proc.close();
				
				// 取出报文序号
				proc = conn.prepareCall("{? = call PKG_FUN_CREATEFSN.CREATEFSN(?)}");
				proc.registerOutParameter(1, Types.VARCHAR);
				proc.setString(2, c_date);
				// 执行存储过程
				proc.execute();
				c_SN = proc.getString(1);
				proc.close();
				
				// 在产生电子对账估值表数据前，先根据组合、日期删除掉电子对账估值表中重复数据，避免出现重复数据
				sql = " delete from T_D_ER_GZ a where a.C_ASS_CODE = ?  and a.C_SN = ? and "
						+ " a.D_START_DATE = to_date(?, 'yyyy-MM-dd') AND A.C_DV_ER_WAY = 'FORWARD' ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, c_ASS_CODE);
				pstmt.setString(2, c_SN);
				pstmt.setString(3, c_date);
				pstmt.executeUpdate();
				this.closeStatementFinal(pstmt);
				
				DataSource sqlSource = null;
				HashMap<String,Object> paraMap = new HashMap<String,Object>();
				List<Object> sqlParam = new ArrayList<Object>();
				// 取出数据源
				String[] dataSource = deploy.getBasicTemplate().getTable().getDataSet().getDataSource().split(",");
				// 循环数据源产生数据
				for (String string : dataSource) {
					if(StringUtil.IsNullOrEmpty(string))
						continue;
					sqlParam.clear();
					// 要执行的sql
					sqlSource = null;
					for (int i = 0; i < deploy.getBasicTemplate().getListDataSource().size(); i++) {
						if(string.trim().equalsIgnoreCase(deploy.getBasicTemplate().getListDataSource().get(i).getCode())){
							sqlSource = deploy.getBasicTemplate().getListDataSource().get(i);
							break;
						}
					}
					
					if(sqlSource != null){
						sql = sqlAssistant.buildInsertTabSql(deploy.getBasicTemplate().getTable(), deploy.getBasicTemplate().getTable().getDataSet().getListField(), sqlSource.getValue());
						Matcher m = pattern.matcher(sql);
						paraMap.put("C_PORT_CODE", port);
						paraMap.put("D_DATE", c_date);
						paraMap.put("C_SN", c_SN);
						paraMap.put("N_KM_LEVEL", Integer.valueOf(kmLevel));
						targetSql = sql.replaceAll(pattStr, "?");
						pstmt = conn.prepareStatement(targetSql);
						
						if(sqlSource.getIsLoop().equalsIgnoreCase("true")){
							if(sqlSource.getLoopBy().equalsIgnoreCase(ParaEnum.N_KM_LEVEL.name())){
								// 循环科目级别生成数据
								for(int i = kmLevel; i > 0; i--){
									paraMap.put("N_KM_LEVEL", Integer.valueOf(i));
									while(m.find()){
										String condtionCode = m.group().substring(2);
										sqlParam.add(paraMap.get(condtionCode));
									}
									
									for(int j = 0; j < sqlParam.size(); j++){
										pstmt.setObject(j + 1, sqlParam.get(j));
									}
									
									m = pattern.matcher(sql);
									sqlParam.clear();
									count += pstmt.executeUpdate();
								}
							}
						} else {
							while(m.find()){
								String condtionCode = m.group().substring(2);
								sqlParam.add(paraMap.get(condtionCode));
							}
							
							for(int j = 0; j < sqlParam.size(); j++){
								pstmt.setObject(j + 1, sqlParam.get(j));
							}
							
							count += pstmt.executeUpdate();
						}
						
						this.closeStatementFinal(pstmt);
					}
				}
				
				//如果已经产生了电子对账数据，则需要向电子对账信息表中添加一条待发送电子对账信息
				if(count > 0){
					proc = conn.prepareCall("{call prc_er_info(?,?,?,?,?,?,?)}");
					proc.setString(1, c_ASS_CODE);
					proc.setDate(2, YssFun.toSqlDate(date));
					proc.setString(3, c_SN);
					proc.setString(4, userCode);
					proc.setString(5, "ER_SEND");
					proc.setString(6, "1011");
					proc.setString(7, "01");
					
					proc.executeUpdate();
					this.sn = c_SN;
					ben_Record.appendDetailMes("产生电子对账估值表数据成功");
					ben_Record.EndLog_Success("执行成功");
				} else {
					ben_Record.EndLog_Warn("没有库存，请执行统计分析");
				}
				
				conn.commit();
				conn.setAutoCommit(true);
				conn.setAutoCommit(autoCommit);
			}
		}
		catch (Exception e) {
			ben_Record.EndLog_Fail(e.getMessage());
			throw e;
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(proc);
			this.closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 根据fsn保存xml文件
	 * 
	 * @param fsn
	 * @throws YssException
	 */
	public XmlFile getXmlFileRoot(String fsn, String fileType, String cAssCode) {
		XmlFile root = null;
		Connection conn = null;
		try {
			conn = this.loadNewConnection();
			root = baoWenBulider.builderFile(conn, fsn, fileType, cAssCode);
		} catch (Exception e) {
			if (root != null) {
				root.setC_ERR_INFO(e.getMessage());
			}
		} finally {
			this.releaseConnection(conn);
		}
		return root;
	}

	// /**
	// * 执行数据库函数
	// * @return
	// */
	// private String executeFunction(){
	// String result = "";
	// CallableStatement func = null;
	// try{
	// if(dzcode.equalsIgnoreCase("1031")){
	// // 科目表
	// func = conn.prepareCall("{? = call Prc_Dz_TDzAccount(?,?,?)}");
	// func.registerOutParameter(1, Types.VARCHAR);
	// func.setString(2, port);
	// func.setString(3, YssFun.formatDate(date, "yyyy-MM-dd"));
	// func.registerOutParameter(4, Types.VARCHAR);
	// }else if(dzcode.equalsIgnoreCase("1011")){
	// // 估值表
	// func = conn.prepareCall("{? = call Prc_Dz_TDzJJGZB(?,?,?)}");
	// func.registerOutParameter(1, Types.VARCHAR);
	// func.setString(2, port);
	// func.setString(3, YssFun.formatDate(date, "yyyy-MM-dd"));
	// func.registerOutParameter(4, Types.VARCHAR);
	// }else if(dzcode.equalsIgnoreCase("1001")){
	// // 余额表
	// func = conn.prepareCall("{? = call Prc_Dz_TDZBALANCE(?,?,?)}");
	// func.registerOutParameter(1, Types.VARCHAR);
	// func.setString(2, port);
	// func.setString(3, YssFun.formatDate(date, "yyyy-MM-dd"));
	// func.registerOutParameter(4, Types.VARCHAR);
	// }
	//
	// // 执行存储过程
	// func.execute();
	// result = func.getString(1);
	// }catch(Exception ex){
	//
	// }finally{
	//
	// }
	//
	// return result;
	// }

	/**
	 * 校验电子对账生成数据状态，若当前日期、对账类型下，电子对账信息表已经存在待发送的电子对账数据，则不需要重新产生对账数据
	 * 删除已生成数据（包括生成记录表，明细表数据）
	 * BUG210176产品组合的组合代码和资产代码不同时新生成的电子对账数据不会覆盖原来数据
	 * BUG278098非功能测试 产生电子对账 发现SQLID 6x9j9a1tkkau9 执行时间比较长
	 * BUG297716月报数据同一月内换一个日期生成  明细数据删除了  但生成报文记录没有删除
	 * @return true/false
	 * @throws Exception
	 */
	private boolean deleteGeneData(Connection conn) throws Exception {
		boolean status = false;
		String sql = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String reportType = dzcode.indexOf("_") > 0 ? dzcode.split("_")[0] : "01";
			if ("01".equals(reportType)) {
				sql = erSqlBuilder.checkStatusSql();
				pst = conn.prepareStatement(sql);
				pst.setString(1, port);
				pst.setString(2, dzcode.indexOf("_") > 0 ? dzcode.split("_")[1] : dzcode);
				pst.setDate(3, YssFun.toSqlDate(date));
				pst.setString(4, "ER_SEND");
				pst.setString(5, dzcode.indexOf("_") > 0 ? dzcode.split("_")[0] : "01");
			}else if("A01".equals(reportType)){//交易数据
				sql = erSqlBuilder.checkStatusSql();
				pst = conn.prepareStatement(sql);
				pst.setString(1, port);
				pst.setString(2, dzcode.indexOf("_") > 0 ? dzcode.split("_")[1] : dzcode);
				pst.setDate(3, YssFun.toSqlDate(date));
				pst.setString(4, "ER_SEND");
				pst.setString(5, "A01");
			}else{
				Calendar cale = Calendar.getInstance();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				cale.setTime(date);
				String firstDay = "";
				String lastDay = "";
				if("03".equals(reportType)){
					//月报
					cale.add(Calendar.MONTH, 0);
					cale.set(Calendar.DAY_OF_MONTH, 1);
					firstDay = format.format(cale.getTime());
					
					cale.add(Calendar.MONTH, 1);
					cale.set(Calendar.DAY_OF_MONTH, 0);
					lastDay = format.format(cale.getTime());
				}else if("04".equals(reportType)){
					//季报
					int year = cale.get(Calendar.YEAR);
					int month = cale.get(Calendar.MONTH) + 1;
					if(month <= 3){
						//第一季度
						firstDay = year + "-01-01";
						lastDay = year + "-03-31";
					}else if(month <= 6){
						//第二季度
						firstDay = year + "-04-01";
						lastDay = year + "-06-30";
					}else if(month <= 9){
						firstDay = year + "-07-01";
						lastDay = year + "-09-30";
					}else if(month <= 12){
						firstDay = year + "-10-01";
						lastDay = year + "-12-31";
					}
				}else if("05".equals(reportType)){
					//半年报
					int year = cale.get(Calendar.YEAR);
					int month = cale.get(Calendar.MONTH) + 1;
					firstDay = month <= 6 ? year + "-01-01" : year + "-07-01";
					lastDay = month <= 6 ? year + "-06-30" : year + "-12-31";
				}else if("06".equals(reportType)){
					//年报
					int year = cale.get(Calendar.YEAR);
					firstDay = year + "-01-01";
					lastDay = year + "-12-31";
				}
				sql = erSqlBuilder.checkDuplicate();
				pst = conn.prepareStatement(sql);
				pst.setString(1, port);
				pst.setString(2, dzcode.indexOf("_") > 0 ? dzcode.split("_")[1] : dzcode);
				pst.setDate(3, YssFun.toSqlDate(firstDay));
				pst.setDate(4, YssFun.toSqlDate(lastDay));
				pst.setString(5, "ER_SEND");
				pst.setString(6, dzcode.indexOf("_") > 0 ? dzcode.split("_")[0] : "01");
			}
			rs = pst.executeQuery();
			while (rs.next()) {
				deleteByStatus(conn, rs.getString("C_SN"));
				deleteByIden(conn,rs.getString("C_IDEN"));
			}
//			deleteGeneDataFromBbInfo(conn);
			status = true;
		} catch (Exception ex) {
			this.logger.error("删除电子对账已生成历史数据失败！");
			throw ex;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
		}
		return status;
	}
	
//	/**
//	 * 从电子对账管理信息表中清楚状态为已生成的数据
//	 * @param conn
//	 * @throws Exception
//	 */
//	private void deleteGeneDataFromBbInfo(Connection conn) throws Exception {
//		String sql = "";
//		PreparedStatement pst = null;
//		try {
//			sql = erSqlBuilder.deleteStatusSql();
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, port);
//			pst.setString(2, dzcode.indexOf("_")>0? dzcode.split("_")[1]:dzcode);
//			pst.setDate(3, YssFun.toSqlDate(date));
//			pst.setString(4,"ER_SEND");
//			pst.setString(5, dzcode.indexOf("_")>0? dzcode.split("_")[0]:"01");
//			pst.executeUpdate();
//		}catch (Exception ex) {
//			this.logger.error("删除电子对账生成历史数据失败：" + sql);
//			throw ex;
//		} finally {
//			this.closeStatementFinal(pst);
//		}
//	}

	/**
	 * BUG297716月报数据同一月内换一个日期生成  明细数据删除了  但生成报文记录没有删除
	 * 根据iden值删除T_D_ER_INFO中的数据
	 * @param conn
	 * @param string
	 * @throws Exception
	 */
	private void deleteByIden(Connection conn, String string) throws Exception {
		String sql = " DELETE FROM T_D_ER_INFO A WHERE A.C_IDEN = ? AND A.C_DV_ER_WAY = 'FORWARD' ";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, string);
			pst.executeUpdate();
		} catch (SQLException e) {
			this.logger.error("删除电子对账已生成历史数据失败:" + sql);
			throw e;
		} finally{
			this.closeStatementFinal(pst);
		}
	}

	/**
	 * 校验电子对账生成数据状态，若当前日期、对账类型下，电子对账信息表已经存在待发送的电子对账数据，则不需要重新产生对账数据
	 * BUG210176产品组合的组合代码和资产代码不同时新生成的电子对账数据不会覆盖原来数据
	 * @return true/false
	 * @throws Exception
	 */
	private void deleteByStatus(Connection conn, String c_sn) throws Exception {
		String sql = "";
		PreparedStatement pst = null;
		try {
			String fileType = dzcode.indexOf("_") > 0 ? dzcode.split("_")[1] : dzcode;
			if("1011".equalsIgnoreCase(fileType)){//估值表
				sql = erSqlBuilder.deleteSql("T_D_ER_GZ","FORWARD");
			}
			else if("1001".equalsIgnoreCase(fileType)){//余额表
				sql = erSqlBuilder.deleteSql("T_D_ER_YE","FORWARD");
			}
			else if("1013".equalsIgnoreCase(fileType)){//双估值表
				sql = erSqlBuilder.deleteSql("T_D_ER_DBLGZ","");
			}
			else if("1031".equalsIgnoreCase(fileType)){//科目表
				sql = erSqlBuilder.deleteSql("T_D_ER_KM","");
			}
			else if("1701".equalsIgnoreCase(fileType)|| "1711".equalsIgnoreCase(fileType)){//资产负债表
				sql = erSqlBuilder.deleteSql("T_D_ER_ZCFZ","");
			}
			else if("1801".equalsIgnoreCase(fileType) || "1811".equalsIgnoreCase(fileType)){//利润表
				sql = erSqlBuilder.deleteSql("T_D_ER_LR","");
			}
			else if("1901".equalsIgnoreCase(fileType)){//所有者权益表
				sql = erSqlBuilder.deleteSql("T_D_ER_SYZQYBD","");
			}else if("1903".equalsIgnoreCase(fileType)){//净资产变动表
				sql = erSqlBuilder.deleteSql("T_D_ER_JZCBD","");
			}else if("A001".equalsIgnoreCase(fileType)){
				return ;
			}
			pst = conn.prepareStatement(sql);
			pst.setString(1, fileType);
			pst.setString(2, port);
			//BUG278098非功能测试 产生电子对账 发现SQLID 6x9j9a1tkkau9 执行时间比较长
			pst.setString(3, c_sn);
			pst.executeUpdate();

		} catch (Exception ex) {
			throw ex;
		} finally {
			this.closeStatementFinal(pst);
		}
	}
	
	/**
	 * ADD 2015-02-07 LY 根据组合编号与业务日期查找净值表中是否有记录，判断是否净值表中有已生成的数据
	 * 
	 * @param portCode
	 *            组合
	 * @param actDate
	 *            业务日期
	 * @return
	 */
	private boolean getAssetStatsPojos(String portCode, Date actDate,Connection conn)
			throws SQLException {
		ResultSet rs = null;
		String sql = "";
		PreparedStatement pst = null;
		try {
			sql = " select 1 from t_r_fr_aststat ast where ast.c_port_code = ? and ast.d_aststat = ? ";
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode); // 组合代码
			pst.setDate(2, YssFun.toSqlDate(actDate)); // 业务日期
			rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (SQLException ex) {
			throw ex;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
		}
		return false;
	}
}
