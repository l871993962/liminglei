package com.yss.uco.elecreco.support.dzdz.common.impl;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.framework.resource.mgr.service.IResourceMgrService;
import com.yss.framework.util.DateUtil;
import com.yss.ifa.szt.tool.para.service.IErParaService;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.ifa.szt.tool.zip.BaoWenTool;
import com.yss.uco.dataintegration.support.dataservice.service.IFileDownLoadService;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.dzdz.bus.dblgz.DblgzbRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.erjzcbdb.ErJzcbdbRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.gz.GzbRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.jyyjxzz.JyyjxzzRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.km.KmRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.lr.LrRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.syzqy.SyzqyRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.yue.YuebRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.zcfz.ZcfzRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.bus.zcfzxzz.ZcfzxzzRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IBaoWenBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IBaoWenSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.IRecordBuilder;
import com.yss.uco.elecreco.support.dzdz.common.builder.BaoWenSqlBuilder;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlInRoot;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;
import com.yss.uco.elecreco.support.util.ResServiceUtil;

public class BaoWenBuilder implements IBaoWenBuilder {
	private Logger logger = LogManager.getLogger(BaoWenBuilder.class);
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 托管行代码，需要拆分发送时，需要传入该值
	 */
	private String tghCode;
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 拆分代码，在拆分发送时需要替换掉资产代码时，传入该值
	 */
	private String splitCode;
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 是否拆分生成发送
	 */
	private boolean isSplit = false;
	private IBaoWenSqlBuilder sqlBuilder = new BaoWenSqlBuilder();
	private TransPojo transPojo = null;
	/**
	 * wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
	 */
	public Map<String, String> paraMap = new HashMap<String, String>();
	public String getTghCode() {
		return tghCode;
	}
	public void setTghCode(String tghCode) {
		this.tghCode = tghCode;
	}
	public String getSplitCode() {
		return splitCode;
	}
	public void setSplitCode(String splitCode) {
		this.splitCode = splitCode;
	}
	public boolean isSplit() {
		return isSplit;
	}
	public void setSplit(boolean isSplit) {
		this.isSplit = isSplit;
	}
	public void initSplitParams(String tghCode,String splitCode,boolean isSplit)
	{
		this.splitCode = splitCode;
		this.tghCode = tghCode;
		this.isSplit = isSplit;
	}
	
	public TransPojo builderTransPojo(Connection conn, String fsn,
			String fileType, String c_ass_code,String portCode,String iden) throws Exception {
		//1.获取模板
		String tpl = getTransReport(conn, portCode);
		
		if(StringUtil.IsNullOrEmptyT(tpl)){
			logger.error("交易数据模板不存在");
			throw new DataAccessException("交易数据模板不存在");
		}
		
		logger.info("交易数据模板:" + tpl);
		
		//2.获取电子对账参数设置
		XmlInRoot root = new XmlInRoot();
		if(this.isSplit && !StringUtil.IsNullOrEmptyT(tghCode))
		{
			root = (XmlInRoot) builderSplitBaoWen(conn, fsn, fileType, portCode, root);
		}else
		{
			root = (XmlInRoot) builderBaoWen(conn, fsn, fileType, portCode, root);
		}
		
		//3.生成报文
		// 调用生成报文方法，并发送请求；
		if (transPojo != null) {
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			paraMap.put("id", iden);
			paraMap.put("DEPT_CODE", root.getC_DEPT_CODE());
			paraMap.put("code", tpl);
			paraMap.put("D_START_DATE", DateUtil.getNow("yyyyMMdd"));
			paraMap.put("D_END_DATE", DateUtil.getNow("yyyyMMdd"));
			IFileDownLoadService service = YssServiceFactory.getInstance()
					.createService(IFileDownLoadService.class);
			String fileName = service.getXmlFromTemplate(paraMap);		
			fileName = ResServiceUtil.fileNameConvert(fileName);
			IResourceMgrService resMgrService = YssServiceFactory.getInstance().createService(IResourceMgrService.class);
			String physicalPath = "";
			try {
				ResourceMgr rm = resMgrService.getResourceByVirtualPath(fileName);
				if (null != rm) {
					physicalPath = rm.getC_PHYSICAL_PATH();
				}
			} catch (Exception e1) {
				logger.error("查询文件物理路径失败！", e1);
				throw new DataAccessException("查询文件物理路径失败！", e1);
			}
			
			File file = new File(physicalPath);
			if(file.exists()){
				String fileContent = BaoWenTool.readFileByLines(physicalPath);
				logger.debug("BaoWenBuilder,send(),fileContent="+fileContent);
				fileContent = BaoWenTool.zip(transPojo, fileContent);
				transPojo.setSendStr(fileContent);
			}
		}
		return transPojo;
	}
	
	/**
	 * 获取财务报表数据表数据起始/结束日期
	 * @param reportType
	 * @return
	 * @throws Exception
	 */
	private String getTransReport(Connection conn, String portCode) throws Exception {
		ResultSet rs = null;
		StringBuffer sqlBuffer = new StringBuffer();
		PreparedStatement pst = null;
		sqlBuffer.append(" SELECT C_TPL_CODE FROM (");
		sqlBuffer.append("  SELECT A.C_TPL_CODE, 1 AS CNT FROM T_P_ER_TRANSREPCFG A"); 
		sqlBuffer.append("   WHERE A.N_CHECK_STATE = 1 AND A.C_PORT_CODE = ? AND A.C_TRANS_CODE = 'TEMP_DZ_SZT_JYSJ' "); // 首先取配置了组合代码的报表
		sqlBuffer.append("  UNION ALL" ); 
		sqlBuffer.append("  SELECT A.C_TPL_CODE, (case when trim(A.C_DAT_CODE) is not null then 2 else 3 end) AS CNT FROM T_P_ER_TRANSREPCFG A"); 
		sqlBuffer.append("   WHERE  A.N_CHECK_STATE = 1 AND (A.C_ORG_CODE IN (SELECT C_RELA_CODE");
		sqlBuffer.append("                            FROM T_P_AB_PORT_RELA B"); 
		sqlBuffer.append("                            JOIN T_D_ER_RELA C");
		sqlBuffer.append("                              ON B.C_RELA_CODE = C.C_TGH_CODE");
		sqlBuffer.append("                           WHERE B.C_RELA_TYPE = 'RELA_ORG'");
		sqlBuffer.append("                             AND B.C_PORT_CODE = ?)");// 其次取配置了托管行的报表
		sqlBuffer.append("     OR (A.C_DAT_CODE in ( SELECT C_DAT_CODE FROM T_P_AB_PORT where C_PORT_CODE = ?) or TRIM(A.C_DAT_CODE) is NULL)) ");
		sqlBuffer.append("     AND A.C_TRANS_CODE = 'TEMP_DZ_SZT_JYSJ' AND TRIM(A.C_PORT_CODE) IS NULL "); 
		sqlBuffer.append(" ) B WHERE ROWNUM = 1  ");
	    
		try {
			pst = conn.prepareStatement(sqlBuffer.toString());
			int index = 1;
			
			pst.setString(index++, portCode);
			pst.setString(index++, portCode);
			pst.setString(index++, portCode);
			rs = pst.executeQuery();
			if(rs.next()) {
				String datePeriod = rs.getString("C_TPL_CODE");
				return datePeriod;
			}
		} catch (SQLException e) {
			logger.error("BaoWenBuilder getTransReport error", e);
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return null;		
	}
	
	public TransPojo builderTransPojo(Connection conn, String fsn,
			String fileType, String c_ass_code,String portCode, ErBbInfo bbInfo) throws Exception {
		XmlInRoot root = null;
		//wlx 20171228 STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
		paraMap.put("SV_BB_DZDZ_KMBW", paraMap.get("SV_BB_DZDZ_KMBW_" + c_ass_code));
		paraMap.put("DZ_BB_DZDZ_DWJZ_001", paraMap.get("DZ_BB_DZDZ_DWJZ_001_" + c_ass_code));
		paraMap.put("SV_BB_DZDZ_KMISIN", paraMap.get("SV_BB_DZDZ_KMISIN_" + c_ass_code));
		paraMap.put("DZ_BB_DZDZ_BFSHGMX", paraMap.get("DZ_BB_DZDZ_BFSHGMX_" + c_ass_code));
		paraMap.put("DZ_BB_DZDZ_SENDSCJM", paraMap.get("DZ_BB_DZDZ_SENDSCJM_" + c_ass_code));
		root = builderBaoWen(conn, fsn, fileType, portCode,paraMap);
		if (root == null) {
			return null;
		}
		
		/**
		 * BUG #338178 【富国基金】【0630.0723版本】产生电子对账月报报文中的begin_date和end_date逻辑问题
		 */
		if(paraMap.containsKey("SV_BB_DZDZ_SENDDATE_" + c_ass_code)){
			String sendDatePara = paraMap.get("SV_BB_DZDZ_SENDDATE_" + c_ass_code);
			if("1".equalsIgnoreCase(sendDatePara) && !StringUtil.IsNullOrEmptyT(bbInfo.getD_DATE())){
				root.setD_START_DATE(bbInfo.getD_DATE().replace("-", ""));
				root.setD_END_DATE(bbInfo.getD_DATE().replace("-", ""));
			}
		}

		// STORY #35077 【南方基金】【紧急】电子对账资产代码转换
		String transferedCode = new BaoWenTool().getTransferC_Ass_CodeMap(conn, c_ass_code, true);
		root.setC_ASS_CODE("".equalsIgnoreCase(transferedCode) ? c_ass_code : transferedCode);
		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
		if(this.isSplit && !StringUtil.IsNullOrEmptyT(this.tghCode)&&!StringUtil.IsNullOrEmptyT(splitCode))
		{
			root.setC_ASS_CODE(splitCode);
		}
		//STORY32744【南方基金】【v2.5需求】南方基金：社保资产证券代码新规则 add by liuyanni 20161103
		//根据参数选择的证券代码转换规则所生成的披露代码替代科目中的证券内码，只对估值表、科目表、余额表做此处理
/*		if (!StringUtil.IsNullOrEmpty(gzCode) && !gzCode.equalsIgnoreCase("TRAN_BZH")) {
			setKmCodeToPlCode(conn,fileType, root,gzCode,kmMap);
			//交易渠道的转换在之前方法中以实现，现注释吊该方法  modify by guohui 20170411
//			tran2209KmCode(fileType, root, kmMap);     
		}*/
		
		String result = JAXBProcessor.marshalWithReturnString2(root.getClass(), root, "GBK");
		
		int beginIndex = result.indexOf("<IN>") + 4;
		int endIndex = result.indexOf("</IN>");
		String data =  result.substring(beginIndex, endIndex);
		StringBuffer buffer = new StringBuffer();
		buffer.append("<?xml version=\"1.0\" encoding = \"GBK\"?>");
		buffer.append("<IN>");
		buffer.append(data);
		buffer.append("</IN>");
		result = buffer.toString();
		//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
		//result = BaoWenTool.zip(result, "GBK");
		result = BaoWenTool.zip(transPojo, result);
		if (transPojo != null) {
			transPojo.setSendStr(result);
		}
		return transPojo;
	}

	public XmlFile builderFile(Connection conn, String fsn, String fileType,
			String c_ass_code) {
		XmlFile root = new XmlFile();
		try {
			root = (XmlFile) builderBaoWen(conn, fsn, fileType, c_ass_code,
					root);
			if (root != null) {
				IRecordBuilder recordBuilder = getRecordBuilder(root
						.getC_FILE_TYPE());
				if (recordBuilder == null) {
					root.setC_ERR_INFO(root.getC_FILE_TYPE() + " : 没有配置对账类型");
				} else {
					root = (XmlFile) recordBuilder.setRecords(conn, root);
				}
			}
		} catch (Exception e) {
			root.setC_ERR_INFO(e.getMessage());
		}

		return root;
	}

	
	@Override
	public XmlInRoot builderBaoWen(Connection conn, String fsn,
			String fileType, String portCode, Map<String, String> kmMap) throws Exception {
		XmlInRoot root = new XmlInRoot();
		//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
		if(this.isSplit && !StringUtil.IsNullOrEmptyT(tghCode))
		{
			root = (XmlInRoot) builderSplitBaoWen(conn, fsn, fileType, portCode, root);
		}else
		{
			root = (XmlInRoot) builderBaoWen(conn, fsn, fileType, portCode, root);
		}
		if (root != null) {
			root = setRecord(conn,root,kmMap);
		}
		return root;
	}

	/**
	 * 拼装明细数据
	 * @param conn
	 * @param root
	 * @return
	 */
	private XmlInRoot setRecord(Connection conn, XmlInRoot root, Map<String, String> kmMap) {
		IRecordBuilder recordBuilder = null;
		if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1011")) {
			recordBuilder = new GzbRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			//wlx 20171107 BUG #179033 建信基金：电子对账，日报，科目表，发送不成功 
			if(root.getErGzbList() == null || root.getErGzbList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		} else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1001")) {
			recordBuilder = new YuebRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErYuebList() == null || root.getErYuebList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据,可能是期末余额都为零或数据格式有问题");
			}
		} else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1013")) {
			recordBuilder = new DblgzbRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErDblgzbList() == null || root.getErDblgzbList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1031")) {
			recordBuilder = new KmRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErKmList() == null || root.getErKmList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}
		else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1701")) {
			recordBuilder = new ZcfzRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErZcfzList() == null || root.getErZcfzList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}
		else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1711")) {
			recordBuilder = new ZcfzxzzRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErZcfzList() == null || root.getErZcfzList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		} else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1801")) {
			recordBuilder = new LrRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErLrList() == null || root.getErLrList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		} else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1811")) {
			recordBuilder = new JyyjxzzRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErLrList() == null || root.getErLrList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}
		else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1901")) {
			recordBuilder = new SyzqyRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErSyzqyList() == null || root.getErSyzqyList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}else if (root.getC_FILE_TYPE().trim().equalsIgnoreCase("1903")) {
			recordBuilder = new ErJzcbdbRecordBuilder();
			root = (XmlInRoot) recordBuilder.setRecords(conn, root,kmMap);
			if(root.getErJzcbdbList() == null || root.getErJzcbdbList().size() == 0){
				transPojo.setErrInfo("没有生成明细数据或数据格式有问题");
			}
		}
		else{
			transPojo.setErrInfo(root.getC_FILE_TYPE() + " : 没有配置对账类型");
		}
		return root;
	}

	private IRecordBuilder getRecordBuilder(String id) {
		IRecordBuilder recordBuilder = null;
		if (id.trim().equalsIgnoreCase("1011")) {
			recordBuilder = new GzbRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1001")) {
			recordBuilder = new YuebRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1013")) {
			recordBuilder = new DblgzbRecordBuilder();
		}else if (id.trim().equalsIgnoreCase("1031")) {
			recordBuilder = new KmRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1701")) {
			recordBuilder = new ZcfzRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1711")) {
			recordBuilder = new ZcfzxzzRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1801")) {
			recordBuilder = new LrRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1811")) {
			recordBuilder = new JyyjxzzRecordBuilder();
		} else if (id.trim().equalsIgnoreCase("1901")) {
			recordBuilder = new SyzqyRecordBuilder();
		}else if (id.trim().equalsIgnoreCase("1903")) {
			recordBuilder = new ErJzcbdbRecordBuilder();
		}
		return recordBuilder;
	}

	private XmlFile builderBaoWen(Connection conn, String fsn, String fileType,
			String c_ass_code, XmlFile clazz) throws Exception {
		XmlFile root = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			String rptType = fileType.split("_")[0];
			fileType = fileType.split("_")[1];
			if(rptType.equalsIgnoreCase("01")) {
				stat = conn.prepareStatement(sqlBuilder.getRootSql());
			} else {
				String tableName = "";
				if(fileType.equalsIgnoreCase("1701") || fileType.equalsIgnoreCase("1711")) {
					tableName = "T_D_ER_ZCFZ";
				} else if (fileType.equalsIgnoreCase("1801") || fileType.equalsIgnoreCase("1811")) {
					tableName = "T_D_ER_LR";
				} else {
					tableName = "T_D_ER_SYZQYBD";
				}
				stat = conn.prepareStatement(sqlBuilder.getRootSqlByReportType(tableName));
				stat.setString(5, rptType);
			}
			stat.setString(1, c_ass_code);
			stat.setString(2, fsn);
			stat.setString(3, fileType);
			stat.setString(4, c_ass_code);
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			if (rs.next()) {
				transPojo = new TransPojo();
				transPojo.setFromApp(rs.getString("C_SRC_APP_LOGO"));
				transPojo.setFromUser(rs.getString("C_TARGET_USER_LOGO"));//源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
				//STORY42660【中国银行】深证通伺服器要求采用热备模式
				IErParaService erParaService = YssServiceFactory.getInstance().createService(IErParaService.class);
				transPojo.setMrInfoList(erParaService.queryMrInfos(rs.getString("C_PARA_CODE")));
				transPojo.setCommType(rs.getString("C_COMM_TYPE"));
				transPojo.setPkgPassword(rs.getString("C_PKG_PASSWORD"));
				transPojo.setToApp(rs.getString("C_TARGET_APP_LOGO"));
				transPojo.setToUser(rs.getString("C_TARGET_USER"));
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				transPojo.setSecretKey(rs.getString("C_SECRETKEY"));
				transPojo.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				transPojo.setCharSet(rs.getString("C_DV_CHARSET"));
				root = (XmlFile) rsTools.ResultToPojoObject(rs,
						clazz.getClass());
				root.setC_ERR_INFO(rs.getString("C_ERR_INFO"));
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 *获取拆分报文头
	 * @param conn
	 * @param fsn
	 * @param fileType
	 * @param portCode
	 * @param managerCode
	 * @param orgCode
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private XmlRoot getSplitXmlRoot(Connection conn, String fsn, String fileType,
			String portCode,String managerCode, String orgCode, XmlRoot clazz) throws Exception {
		XmlRoot root = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		//STORY42660【中国银行】深证通伺服器要求采用热备模式
		IErParaService erParaService = YssServiceFactory.getInstance().createService(IErParaService.class);
		boolean isFiterManager = false;
		try {
			if(managerCode!=null&&!StringUtil.IsNullOrEmptyT(managerCode))
			{
				isFiterManager = true;
			}
			BaoWenSqlBuilder builder = (BaoWenSqlBuilder) sqlBuilder;
			stat = conn.prepareStatement(builder.getSplitRootSql(isFiterManager));
			int index = 1;
			stat.setString(index++, portCode);
			stat.setString(index++, orgCode);
			if(isFiterManager)
			{
				stat.setString(index++, managerCode);
			}
			
			stat.setString(index++, fsn);
			stat.setString(index++, fileType);
			stat.setString(index++, portCode);
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			if (rs.next()) {
				transPojo = new TransPojo();
				transPojo.setFromApp(rs.getString("C_SRC_APP_LOGO"));
				transPojo.setFromUser(rs.getString("C_TARGET_USER_LOGO")); ////源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
				transPojo.setMrInfoList(erParaService.queryMrInfos(rs.getString("C_PARA_CODE")));
				transPojo.setPkgPassword(rs.getString("C_PKG_PASSWORD"));
				transPojo.setToApp(rs.getString("C_TARGET_APP_LOGO"));
				transPojo.setToUser(rs.getString("C_TARGET_USER"));
				transPojo.setCommType(rs.getString("C_COMM_TYPE"));
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				transPojo.setSecretKey(rs.getString("C_SECRETKEY"));
				transPojo.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				transPojo.setCharSet(rs.getString("C_DV_CHARSET"));
				root = (XmlRoot) rsTools.ResultToPojoObject(rs,
						clazz.getClass());
				return root;
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return root;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取拆分报文头
	 * STORY73476【鹏华基金】并行组合电子对账需求 资产代码换成组合代码
	 * @param conn
	 * @param fsn
	 * @param fileType
	 * @param portCode
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private XmlRoot builderSplitBaoWen(Connection conn, String fsn, String fileType,
			String portCode, XmlRoot clazz) throws Exception {
		XmlRoot root = null;
		try {
			
			String managerCode = getManagerByPortCode(conn, portCode);
			root = getSplitXmlRoot(conn, fsn, fileType, portCode, managerCode,this.tghCode, clazz);
			if(root != null)
			{
				return root;
			}
			List<String> orgCodes = getParentOrgs(conn, this.tghCode);
			for(String orgCode : orgCodes)
			{
				root = getSplitXmlRoot(conn, fsn, fileType, portCode, managerCode,orgCode, clazz);
				if(root != null)
				{
					return root;
				}
			}
		} finally {
		}
		return root;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取上级机构
	 * @param conn
	 * @param tghCode
	 * @return
	 * @throws SQLException
	 */
	private List<String> getParentOrgs(Connection conn,String tghCode) throws SQLException {
		List<String> parentOrgs = new ArrayList<String>();
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			BaoWenSqlBuilder builder = (BaoWenSqlBuilder) sqlBuilder;
			stat = conn.prepareStatement(builder.getParentOrgsSql());
			int index = 1;
			stat.setString(index++, tghCode);
			stat.setString(index++, tghCode);
			rs = stat.executeQuery();
			while (rs.next()) {
				parentOrgs.add(rs.getString("C_TGH_CODE"));
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return parentOrgs;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取管理人
	 * @param conn
	 * @param portCode
	 * @return
	 * @throws SQLException
	 */
	private String getManagerByPortCode(Connection conn,String portCode) throws SQLException {
		String managerCode = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			BaoWenSqlBuilder builder = (BaoWenSqlBuilder) sqlBuilder;
			stat = conn.prepareStatement(builder.getManagerByPortCodeSql());
			int index = 1;
			stat.setString(index++, portCode);
			rs = stat.executeQuery();
			if (rs.next()) {
				managerCode = rs.getString("C_MANAGE_CODE");
			}
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
		}
		return managerCode;
	}
	/**
	 *   STORY #49489 光大证券-产品关联电子对账设置优化
	 * 	对账参数匹配逻辑：是否应用分机构为否的话 按照原逻辑 ，是的时候按照分机构到总机构进行轮寻
	 *             匹配到就停止轮寻，如果匹配不到root返回null
	 * 
	 * @param conn
	 * @param fsn
	 * @param fileType
	 * @param portCode
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	private XmlRoot builderBaoWen(Connection conn, String fsn, String fileType,
			String portCode, XmlRoot clazz) throws Exception {
		XmlRoot root = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		PreparedStatement paraStat = null;
		ResultSet paraRs = null;
		//STORY42660【中国银行】深证通伺服器要求采用热备模式
		IErParaService erParaService = YssServiceFactory.getInstance().createService(IErParaService.class);
		
		try {
			stat = conn.prepareStatement(sqlBuilder.getRootSql());
			stat.setString(1, portCode);
			stat.setString(2, fsn);
			stat.setString(3, fileType);
			stat.setString(4, portCode);
			rs = stat.executeQuery();
			DefaultDBNameResolver dbNameResolver = new DefaultDBNameResolver();
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver,
					sqlBuilder);
			if (rs.next()) {
				transPojo = new TransPojo();
				transPojo.setFromApp(rs.getString("C_SRC_APP_LOGO"));
				transPojo.setFromUser(rs.getString("C_TARGET_USER_LOGO")); ////源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
				transPojo.setMrInfoList(erParaService.queryMrInfos(rs.getString("C_PARA_CODE")));
				transPojo.setPkgPassword(rs.getString("C_PKG_PASSWORD"));
				transPojo.setToApp(rs.getString("C_TARGET_APP_LOGO"));
				transPojo.setToUser(rs.getString("C_TARGET_USER"));
				transPojo.setCommType(rs.getString("C_COMM_TYPE"));
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				transPojo.setSecretKey(rs.getString("C_SECRETKEY"));
				transPojo.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				transPojo.setCharSet(rs.getString("C_DV_CHARSET"));
				root = (XmlRoot) rsTools.ResultToPojoObject(rs,
						clazz.getClass());
				return root;
			}
			
			paraStat = conn.prepareStatement(sqlBuilder.getDzParaSql());
			paraStat.setString(1, portCode);
			paraStat.setString(2, portCode);
			paraStat.setString(3, portCode);
			paraStat.setString(4, fsn);
			paraStat.setString(5, fileType);
			paraStat.setString(6, portCode);
			paraRs = paraStat.executeQuery();
			while (paraRs.next()) {
				transPojo = new TransPojo();
				transPojo.setFromApp(paraRs.getString("C_SRC_APP_LOGO"));
				transPojo.setFromUser(paraRs.getString("C_TARGET_USER_LOGO")); ////源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
				transPojo.setMrInfoList(erParaService.queryMrInfos(paraRs.getString("C_PARA_CODE")));
				transPojo.setPkgPassword(paraRs.getString("C_PKG_PASSWORD"));
				transPojo.setToApp(paraRs.getString("C_TARGET_APP_LOGO"));
				transPojo.setToUser(paraRs.getString("C_TARGET_USER"));
				transPojo.setCommType(paraRs.getString("C_COMM_TYPE"));
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				//BUG240077【国泰基金】电子对账管理点击发送电子对账，无反应
				transPojo.setSecretKey(paraRs.getString("C_SECRETKEY"));
				transPojo.setSecretType(paraRs.getString("C_DV_SECRETTYPE"));
				transPojo.setCharSet(paraRs.getString("C_DV_CHARSET"));
				if (this.check(paraRs)) {
					continue;
				}else {
					root = (XmlRoot) rsTools.ResultToPojoObject(paraRs,
							clazz.getClass());
					break;
				}
			}
		}catch(Exception e)
		{
			this.logger.error("获取电子对账参数出错",e);
		}finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeResultSetFinal(paraRs);
			DbFun.closeStatementFinal(stat);
			DbFun.closeStatementFinal(paraStat);
		}
		return root;
	}
	
	private boolean check(ResultSet paraRs) throws SQLException{
		if (StringUtil.IsNullOrEmptyT(paraRs.getString("C_SRC_APP_LOGO"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_TARGET_USER_LOGO"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_PARA_CODE"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_PKG_PASSWORD"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_TARGET_APP_LOGO"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_TARGET_USER"))||
				StringUtil.IsNullOrEmptyT(paraRs.getString("C_COMM_TYPE"))) {
			return true;
		}
		return false;
	}

}
