package com.yss.uco.elecreco.er.generate.yeb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.service.LicCompanyName;
import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;
import com.yss.uco.elecreco.support.util.UcoDspParamCodeEnum;

public class GeneYEBDataService extends GeneElecDataService{

	protected static final String C_FILE_TYPE = "1001";
	protected static final String C_RPT_TYPE = "01";
	/**
	 * 库存表年月
	 */
	//protected String c_year_month = "";
	/**
	 * 库存表期初年月
	 */
	protected String c_year_month_qc = "";
	/**
	 * 业务日期的上个月份最后一天
	 */
	protected String c_end_date = "";
	/**
	 * 业务日期的当月第一天
	 */
	protected String c_first_date = "";
	/**
	 * 
	 */
	protected String c_year = "";
	//STORY62407【大成基金】【紧急】余额表发送电子对账非明细科目是否发送数量
	//余额表非明细科目是否生成数量 默认为是
	private boolean isGeneAmount = true;
	/**
	 *  STORY #48815 嘉实基金电子对账-中行-港股通组合需要用本位币（人民币）发送电子对账 
	 *  新增产品估值参数“电子对账数据使用本币发送”（SV_BB_DZDZ_BBFS） 
 	 *	参数值：是、否        默认值：否 
     *	是：使用本币发送电子对账数据。 
     *	否：使用原币发送电子对账数据。 
	 */
	private boolean isUseBB = false;
	/**
	 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本是否需要数量
	 * 默认为是
	 * 针对4001及其下面的子科目
	 */
	private boolean DZ_BB_DZDZ_YEBSSZBZSSL = true;
	/**
	 * STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
	 * */
	private Map<String, String> kmTransMap = new HashMap<String, String>();
	private boolean isTransKM = false;
	/**
	 * 是否将1111.科目代码转换为1103.科目代码
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 */
	private boolean trans1111KmCode = false;
	/**
	 * BUG #272834 太平养老-电子对账生成的余额表6407.02四级科目不显示。
	 * 是否发送6407.02的4级科目
	 */
	private boolean isSend6407FourKmCode = true;
	/**
	 * BUG #272834 太平养老-电子对账生成的余额表6407.02四级科目不显示。
	 * 是否发送6605.03的3级科目
	 */
	private boolean isSend6605ThreeKmCode = true;
	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		int count = 0;
		try {
			Date currDate = DateUtil.stringtoDate(this.geneDate, "yyyy-MM-dd");
			//业务日期的上个月份最后一天
			Date preMonthLastDate = DateUtil.getLastNatureDayOnMonth(DateUtil.nextMonth(currDate, -1));
			c_end_date = DateUtil.dateToString(preMonthLastDate, "yyyy-MM-dd");
			c_first_date = DateUtil.dateToString(currDate, "yyyy-MM") + "-01";
			c_year = DateUtil.dateToString(currDate, "yyyy");
			if("01".equalsIgnoreCase(DateUtil.dateToString(currDate,"MM"))){
		  	//BUG #149400 南方基金-生成电子对账余额表期初余均为0 modify by weijingjing 20170104
				//c_year_month = c_year + "00";
				//c_year_month_qc = DateUtil.dateToString(preMonthLastDate, "yyyyMM");
				//BUG #339052 【IFRS9】关联两个核算级别方案，在关联新科目期间【电子对账管理 】查询余额报表。还会显示旧科目
				c_year_month_qc =  c_year + "00";
			}else{
				//c_year_month = DateUtil.dateToString(currDate,"yyyyMM");
				c_year_month_qc = DateUtil.dateToString(preMonthLastDate, "yyyyMM");
			}
			this.Pro_GetPlan();
			this.createFsn();
			this.getAssCode();
			this.getDZPara();//获取对账参数
			this.getPortPara();//获取估值产品参数
			this.geneDataIntoTempTable();
			this.geneDataLoopByKmLevel();
			count = 0;
			count += this.geneYEBData();
			//wulongxing 20171130 STORY #49703 【嘉实基金】社保组合6407.02科目发送电子对账时需要特殊处理
			//BUG #272834 太平养老-电子对账生成的余额表6407.02四级科目不显示。
			if(!isSend6407FourKmCode && ("ASS_SBJJ".equalsIgnoreCase(this.assType) || "ASS_JBYLBXJJ".equalsIgnoreCase(this.assType))){//资产类型为社会保障基金或基本养老保险基金
				this.delete6407FourKmCode();
			}
			if(!isSend6605ThreeKmCode && ("ASS_SBJJ".equalsIgnoreCase(this.assType) || "ASS_JBYLBXJJ".equalsIgnoreCase(this.assType))){
				this.delete6605ThreeKmCode();//STORY51829(紧急)【嘉实基金】社保和基本养老组合发送电子对账时6605.03科目只发送二级
			}
			/**
			 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本不需要数量
			 * 默认为是
			 * 针对4001及其下面的子科目
			 */
			if(!this.DZ_BB_DZDZ_YEBSSZBZSSL){//不生成实收资本数量
				update4001AmountZero();
			}
			
			/**
			 * BUG #315311 【招商基金】工商银行电子对账，发过去的科目表看不到621101的三级科目
			 * 针对6211及其下面的子科目
			 */
			if(DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE)){
				update6211DetailZero();
			}
			
			/**
			* STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
			* */
			transKMCode();
			trans1111KMCode();
			if(count > 0){
				this.geneErInfo(C_FILE_TYPE, C_RPT_TYPE);
				this.geneErInfoHisAndInst(C_FILE_TYPE, C_RPT_TYPE);//生成历史进行记录
				result = this.fsn;
				resultDetail = "电子对账余额表数据生成成功！";
			}else{
				result = "Warn";
				resultDetail = "没有数据生成！";
				//BUG251623电子对账管理生成电子对账，如果方案关联组合设置了群组但是没有单独设置产品进行关联，则无法生成估值表数据
				if(this.kmLevel == 0)
				{
					resultDetail += "请检查是否设置了方案关联组合！";
				}
			}
			
		} catch (Exception e) {
			result = "Fail";
			resultDetail = e.getMessage();
			logger.error("生成余额表数据出错： " + e.getMessage(), e);
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	
	/**
	 * BUG #315311 【招商基金】工商银行电子对账，发过去的科目表看不到621101的三级科目
	 * 针对6211及其下面的子科目
	 */
	private void update6211DetailZero() throws Exception {
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append(" UPDATE T_D_ER_YE SET N_DETAIL = 0 WHERE  ");
			sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND N_DETAIL = 1 AND C_KM_CODE LIKE '6211%' ");
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.assCode);
			pst.setDate(3, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("余额表更新6211及其下面的子科目是否为明细：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本不需要数量
	 * 默认为是
	 * 针对4001及其下面的子科目
	 */
	private void update4001AmountZero() throws Exception {
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append(" UPDATE T_D_ER_YE SET N_AMOUNT_StartBal = 0,N_AMOUNT_Debit = 0,N_AMOUNT_CREDIT = 0,N_AMOUNT_EndBal = 0 WHERE  ");
			sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE LIKE '4001%' ");
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.assCode);
			pst.setDate(3, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("余额表的时候实收资本4001科目不需要数量：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * wulongxing 20180120 STORY51829(紧急)【嘉实基金】社保和基本养老组合发送电子对账时6605.03科目只发送二级
	 * 删除6605.03开头的三级科目，科目只到3级没有四级
	 * @throws Exception 
	 */
	private void delete6605ThreeKmCode() throws Exception {
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
		try {
			sql.append(" DELETE FROM T_D_ER_YE WHERE  ");
			sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE LIKE '6605.03.%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_Code) = 3 ");
			pst = conn.prepareStatement(sql.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.assCode);
			pst.setDate(3, YssFun.toSqlDate(geneDate));
			pst.executeUpdate();
		} catch (Exception e) {
			logger.error("删除6605.03三级明细科目出错：" + e.getMessage(), e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * wulongxing 20171130 STORY #49703 【嘉实基金】社保组合6407.02科目发送电子对账时需要特殊处理
	 * 删除6407.02开头的四级科目
	 * @throws Exception 
	 */
	private void delete6407FourKmCode() throws Exception {
		PreparedStatement pst = null;
		StringBuffer sql = new StringBuffer();
	    try {
	      sql.append(" DELETE FROM T_D_ER_YE WHERE  ");
	      sql.append(" C_SN = ? AND C_ASS_CODE = ? AND D_START_DATE = ? AND C_KM_CODE LIKE '6407.02.%' AND PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_Code) = 4 ");
	      pst = conn.prepareStatement(sql.toString());
	      pst.setString(1, this.fsn);
	      pst.setString(2, this.assCode);
	      pst.setDate(3, YssFun.toSqlDate(geneDate));
	      pst.executeUpdate();
	    } catch (Exception e) {
	      logger.error("删除6407.02四级明细科目出错：" + e.getMessage(), e);
	      throw e;
	    }finally{
	      DbFun.closeStatementFinal(pst);
	    }
	}

	/**
	 * 将明细数据统计插入临时表R_F_BM_BAL
	 * @throws Exception 
	 */
	protected void geneDataIntoTempTable() throws Exception {
		PreparedStatement pst = null;
//		PreparedStatement insertPst = null;
		ResultSet rs = null;
//		int count = 0;
		try {
			String sqlStr = getTempTableSql()+getDataIntoTempTableSql();
//			insertPst = conn.prepareStatement(getTempTableSql());
			pst = conn.prepareStatement(sqlStr);
			int index = 1;
			pst.setString(index++, this.geneDate);
			pst.setString(index++, this.geneDate);
			pst.setString(index++, this.geneDate);
			//取年初余额
//			pst.setString(index++, this.planCode);
//			pst.setString(index++, this.portCode);
//			pst.setString(index++, this.c_year);
			//取期初余额
//			pst.setString(index++, this.planCode);
			pst.setString(index++, this.portCode);
			pst.setString(index++, this.c_year_month_qc);
			//除了每年一月份其他取上月末
			if ((c_year + "00").equals(c_year_month_qc)) {
				pst.setString(index++, this.c_first_date);
			}else {
				pst.setString(index++, this.c_end_date);
			}

			//取年累计发生额
//			pst.setString(index++, this.planCode);
//			pst.setString(index++, this.portCode);
//			pst.setString(index++, this.c_year);
//			pst.setString(index++, this.geneDate);
			//取本期余额
//			pst.setString(index++, this.planCode);
			pst.setString(index++, this.portCode);
			pst.setString(index++, this.geneDate);
			pst.setString(index++, this.geneDate);
			//取本期发生额 
//			pst.setString(index++, this.planCode);
			pst.setString(index++, this.portCode);
			pst.setString(index++, this.c_first_date);
			pst.setString(index++, this.geneDate);
			
			pst.setString(index++, this.planCode);
			pst.setString(index++, this.planCode);
			pst.executeUpdate();
//			while (rs.next()) {
//				setTempPojo(insertPst, rs);
//				insertPst.addBatch();
//				if(++count % batchSize == 0) {
//					insertPst.executeBatch();
//				}
//			}
//			insertPst.executeBatch();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst, insertPst);
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * R_F_BM_BAL
	 * C_Port_Code, C_KM_Code, C_KM_Name, C_KM_Code_P, C_KM_CODE_T
	 * C_KM_CODE_P_T, N_DETAIL, N_Way, C_Year, D_Bal, N_Period,
	 * N_A_INI_Y, N_A_INI, N_A_DEB, N_A_CRE, N_A_DEB_Y, N_A_CRE_Y, N_A_BAL,
	 * N_M_INI_Y, N_M_INI, N_M_DEB, N_M_CRE, N_M_DEB_Y, N_M_CRE_Y, N_M_BAL,
	 * N_PM_INI_Y, N_PM_INI, N_PM_DEB, N_PM_CRE, N_PM_DEB_Y, N_PM_CRE_Y, N_PM_BAL, C_DC_Code, C_DV_KM_Cls
	 * @param pst
	 * @param rs
	 * @throws SQLException
	 */
	protected void setTempPojo(PreparedStatement pst, ResultSet rs) throws SQLException {
		int index = 1;
		pst.setString(index++, rs.getString("C_Port_Code"));
		pst.setString(index++, rs.getString("C_KM_CODE"));
		pst.setString(index++, rs.getString("C_KM_NAME"));
		pst.setString(index++, rs.getString("C_KM_Code_P"));
		pst.setString(index++, rs.getString("C_KM_CODE_T"));
		pst.setString(index++, rs.getString("C_KM_CODE_P_T"));
		pst.setInt(index++, rs.getInt("N_DETAIL"));
		pst.setInt(index++, rs.getInt("N_Way"));
		pst.setString(index++, rs.getString("C_Year"));
		pst.setDate(index++, rs.getDate("D_Bal"));
		pst.setDouble(index++, rs.getDouble("N_Period"));
		pst.setDouble(index++, rs.getDouble("N_A_INI_Y"));
		pst.setDouble(index++, rs.getDouble("N_A_INI"));
		pst.setDouble(index++, rs.getDouble("N_A_DEB"));
		pst.setDouble(index++, rs.getDouble("N_A_CRE"));
		pst.setDouble(index++, rs.getDouble("N_A_DEB_Y"));
		pst.setDouble(index++, rs.getDouble("N_A_CRE_Y"));
		pst.setDouble(index++, rs.getDouble("N_A_BAL"));
		pst.setDouble(index++, rs.getDouble("N_M_INI_Y"));
		pst.setDouble(index++, rs.getDouble("N_M_INI"));
		pst.setDouble(index++, rs.getDouble("N_M_DEB"));
		pst.setDouble(index++, rs.getDouble("N_M_CRE"));
		pst.setDouble(index++, rs.getDouble("N_M_DEB_Y"));
		pst.setDouble(index++, rs.getDouble("N_M_CRE_Y"));
		pst.setDouble(index++, rs.getDouble("N_M_BAL"));
		pst.setDouble(index++, rs.getDouble("N_PM_INI_Y"));
		pst.setDouble(index++, rs.getDouble("N_PM_INI"));
		pst.setDouble(index++, rs.getDouble("N_PM_DEB"));
		pst.setDouble(index++, rs.getDouble("N_PM_CRE"));
		pst.setDouble(index++, rs.getDouble("N_PM_DEB_Y"));
		pst.setDouble(index++, rs.getDouble("N_PM_CRE_Y"));
		pst.setDouble(index++, rs.getDouble("N_PM_BAL"));
		pst.setString(index++, rs.getString("C_DC_Code"));
		pst.setString(index++, rs.getString("C_DV_KM_Cls"));
	}
	/**
	 * 插入中间表R_F_BM_BAL sql语句
	 * @return
	 */
	private String getTempTableSql() {
		StringBuilder builder = new StringBuilder();

		builder.append("insert into R_F_BM_BAL\n ");
		builder.append("      (C_Port_Code, C_KM_Code, C_KM_Name, C_KM_Code_P, C_KM_CODE_T,\n "); 
		builder.append("       C_KM_CODE_P_T, N_DETAIL, N_Way, C_Year, D_Bal, N_Period,\n "); 
		builder.append("       N_A_INI_Y, N_A_INI, N_A_DEB, N_A_CRE, N_A_DEB_Y, N_A_CRE_Y, N_A_BAL,\n "); 
		builder.append("       N_M_INI_Y, N_M_INI, N_M_DEB, N_M_CRE, N_M_DEB_Y, N_M_CRE_Y, N_M_BAL,\n "); 
		builder.append("       N_PM_INI_Y, N_PM_INI, N_PM_DEB, N_PM_CRE, N_PM_DEB_Y, N_PM_CRE_Y, N_PM_BAL,\n "); 
		builder.append("       C_DC_Code, C_DV_KM_Cls)\n "); 
//		builder.append(" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return builder.toString();
	}
	protected String getDataIntoTempTableSql() {
		StringBuilder builder = new StringBuilder();

		builder.append("      select bal.C_Port_Code,\n "); 
		builder.append("              bal.C_KM_Code,\n "); 
		builder.append("              NVL(max(bal.C_KM_Name), bal.C_KM_CODE) as C_KM_Name,\n "); 
		builder.append("              max(bal.C_KM_Code_P) as C_KM_Code_P,\n "); 
		builder.append("              max(bal.C_KM_CODE_T) as C_KM_CODE_T,\n "); 
		builder.append("              max(NVL(KM.C_KM_CODE_P, NVL(substr(BAL.C_KM_CODE, 1,instr(bal.C_KM_CODE, '.', -1) - 1),'[root]'))) as C_KM_CODE_P_T,\n "); 
		builder.append("              bal.N_DETAIL,\n "); 
		builder.append("              nvl(ITEM.N_WAY, KM.N_WAY) as N_Way,\n "); 
		builder.append("              to_char(to_date(?, 'yyyy-MM-dd'), 'yyyy') as C_Year,\n "); 
		builder.append("              to_date(?, 'yyyy-MM-dd') as D_Bal,\n "); 
		builder.append("              to_char(to_date(?, 'yyyy-MM-dd'), 'MM') as N_Period,\n "); 
		builder.append("              sum(bal.N_A_INI_Y) as N_A_INI_Y,\n "); 
		builder.append("              sum(bal.N_A_INI) as N_A_INI,\n "); 
		builder.append("              sum(bal.N_A_DEB) as N_A_DEB,\n "); 
		builder.append("              sum(bal.N_A_CRE) as N_A_CRE,\n "); 
		builder.append("              sum(bal.N_A_DEB_Y) as N_A_DEB_Y,\n "); 
		builder.append("              sum(bal.N_A_CRE_Y) as N_A_CRE_Y,\n "); 
		builder.append("              sum(bal.N_A_BAL) as N_A_BAL,\n "); 
		builder.append("              sum(bal.N_M_INI_Y) as N_M_INI_Y,\n "); 
		builder.append("              sum(bal.N_M_INI) as N_M_INI,\n "); 
		builder.append("              sum(bal.N_M_DEB) as N_M_DEB,\n "); 
		builder.append("              sum(bal.N_M_CRE) as N_M_CRE,\n "); 
		builder.append("              sum(bal.N_M_DEB_Y) as N_M_DEB_Y,\n "); 
		builder.append("              sum(bal.N_M_CRE_Y) as N_M_CRE_Y,\n "); 
		builder.append("              sum(bal.N_M_BAL) as N_M_BAL,\n "); 
		builder.append("              sum(bal.N_PM_INI_Y) as N_PM_INI_Y,\n "); 
		builder.append("              sum(bal.N_PM_INI) as N_PM_INI,\n "); 
		builder.append("              sum(bal.N_PM_DEB) as N_PM_DEB,\n "); 
		builder.append("              sum(bal.N_PM_CRE) as N_PM_CRE,\n "); 
		builder.append("              sum(bal.N_PM_DEB_Y) as N_PM_DEB_Y,\n "); 
		builder.append("              sum(bal.N_PM_CRE_Y) as N_PM_CRE_Y,\n "); 
		builder.append("              sum(bal.N_PM_BAL) as N_PM_BAL,\n "); 
		builder.append("              max(CASE WHEN bal.C_DC_CODE = 'CNY' THEN '001'\n "); 
		builder.append("                       WHEN bal.C_DC_CODE = 'USD' THEN '002'\n "); 
		builder.append("                       ELSE ' ' END) AS C_DC_CODE,\n "); 
		builder.append("              NVL(km.C_DV_KM_CLS, bal.C_DV_KM_Cls) as C_DV_KM_Cls\n "); 
		builder.append("         from (\n "); 
//		builder.append("               --- 取出年初数\n "); 
//		builder.append("               select a.C_Port_Code,\n "); 
//		builder.append("                       a.C_Year_Month,\n "); 
//		builder.append("                       a.D_Stock as D_BAL,\n "); 
//		builder.append("                       a.C_KM_Code,\n "); 
//		builder.append("                       a.C_KM_Name,\n "); 
//		builder.append("                       a.C_DC_CODE,\n "); 
//		builder.append("                       a.C_DV_KM_Cls,\n "); 
//		builder.append("                       c.N_WAY as N_WAY,\n "); 
//		builder.append("                       NVL(substr(a.C_KM_CODE,1,Instr(a.C_KM_CODE, '.', -1) -1), '[root]') as C_KM_CODE_P,\n "); 
//		builder.append("                       NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,\n "); 
//		builder.append("                       NVL(b.C_KM_CODE_P, NVL(Substr(a.C_KM_CODE, 1, Instr(a.C_KM_CODE, '.', -1) - 1), '[root]')) as C_KM_CODE_P_T,\n "); 
//		builder.append("                       '年初数' as C_Data_Type,\n "); 
//		builder.append("                       1 as N_DETAIL,\n "); 
//		builder.append("                       N_Amount as N_A_INI_Y,\n "); 
//		builder.append("                       0 as N_A_INI,\n "); 
//		builder.append("                       0 as N_A_DEB,\n "); 
//		builder.append("                       0 as N_A_CRE,\n "); 
//		builder.append("                       0 as N_A_DEB_Y,\n "); 
//		builder.append("                       0 as N_A_CRE_Y,\n "); 
//		builder.append("                       0 as N_A_BAL,\n "); 
//		builder.append("                       N_Orig_Money as N_M_INI_Y,\n "); 
//		builder.append("                       0 as N_M_INI,\n "); 
//		builder.append("                       0 as N_M_DEB,\n "); 
//		builder.append("                       0 as N_M_CRE,\n "); 
//		builder.append("                       0 as N_M_DEB_Y,\n "); 
//		builder.append("                       0 as N_M_CRE_Y,\n "); 
//		builder.append("                       0 as N_M_BAL,\n "); 
//		builder.append("                       N_Port_Money as N_PM_INI_Y,\n "); 
//		builder.append("                       0 as N_PM_INI,\n "); 
//		builder.append("                       0 as N_PM_DEB,\n "); 
//		builder.append("                       0 as N_PM_CRE,\n "); 
//		builder.append("                       0 as N_PM_DEB_Y,\n "); 
//		builder.append("                       0 as N_PM_CRE_Y,\n "); 
//		builder.append("                       0 as N_PM_BAL\n "); 
//		builder.append("                 from T_D_Ai_Stock a\n "); 
//		builder.append("                 left join (select C_KM_CODE, C_KM_CODE_P, C_DV_KM_Cls, C_DC_CODE\n "); 
//		builder.append("                              from T_F_SC_KM where N_Detail = 1 and C_PLAN_CODE = ?) b\n "); 
//		builder.append("                   on a.C_KM_CODE_T = b.C_KM_CODE\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = b.C_DV_KM_Cls\n "); 
//		builder.append("                  and a.C_DC_CODE = b.C_DC_CODE\n "); 
//		builder.append("                 join (select C_DAI_CODE, N_FUND_WAY as N_WAY, C_DV_KM_Cls From T_S_Dai_Item) c\n "); 
//		builder.append("                   on a.C_Dai_Code = c.c_Dai_Code\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = c.C_DV_KM_Cls\n "); 
//		builder.append("                where a.C_Port_Code = ?\n "); 
//		builder.append("                  and Length(LTrim(a.C_KM_CODE)) > 0\n "); 
//		builder.append("                  and a.C_Year_Month = ? || '00'\n "); 
//		//builder.append("                  and a.D_Stock = to_date(? || '-01-01', 'yyyy-MM-dd')\n "); 
//		builder.append("               union all\n "); 
		builder.append("				----取上期期初余额 \n");
		builder.append("               select a.C_Port_Code,\n "); 
		builder.append("                      a.C_Year_Month,\n "); 
		builder.append("                      a.D_Stock as D_BAL,\n "); 
		builder.append("                      a.C_KM_Code,\n "); 
		builder.append("                      a.C_KM_Name,\n "); 
		builder.append("                      a.C_DC_CODE,\n "); 
		builder.append("                      a.C_DV_KM_Cls,\n ");
		builder.append("                      a.C_Dai_Code,\n ");
//		builder.append("                      nvl(b.N_WAY, c.N_WAY) as N_WAY,\n "); //wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
		builder.append("                      NVL(substr(a.C_KM_CODE, 1,Instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P,\n "); 
		builder.append("                      a.C_KM_CODE_T,\n "); 
//		builder.append("                      NVL(Substr(a.C_KM_CODE, 1,Instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P_T,\n "); 
		builder.append("                      '期初余额' as C_Data_Type,\n "); 
		builder.append("                      1 as N_DETAIL,\n "); 
		builder.append("                      0 as N_A_INI_Y,\n "); 
		builder.append("                      N_Amount as N_A_INI,\n "); 
		builder.append("                      0 as N_A_DEB,\n "); 
		builder.append("                      0 as N_A_CRE,\n "); 
		builder.append("                      0 as N_A_DEB_Y,\n "); 
		builder.append("                      0 as N_A_CRE_Y,\n "); 
		builder.append("                      0 as N_A_BAL,\n "); 
		builder.append("                      0 as N_M_INI_Y,\n "); 
		builder.append("                      N_Orig_Money as N_M_INI,\n "); 
		builder.append("                      0 as N_M_DEB,\n "); 
		builder.append("                      0 as N_M_CRE,\n "); 
		builder.append("                      0 as N_M_DEB_Y,\n "); 
		builder.append("                      0 as N_M_CRE_Y,\n "); 
		builder.append("                      0 as N_M_BAL,\n "); 
		builder.append("                      0 as N_PM_INI_Y,\n "); 
		builder.append("                      N_Port_Money as N_PM_INI,\n "); 
		builder.append("                      0 as N_PM_DEB,\n "); 
		builder.append("                      0 as N_PM_CRE,\n "); 
		builder.append("                      0 as N_PM_DEB_Y,\n "); 
		builder.append("                      0 as N_PM_CRE_Y,\n "); 
		builder.append("                      0 as N_PM_BAL\n "); 
		builder.append("                 from T_D_Ai_Stock a\n "); 
//		builder.append("                 left join (select C_KM_CODE, C_KM_CODE_P, C_DV_KM_Cls, C_DC_CODE, DECODE(C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_way\n ");//wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
//		builder.append("                              from T_F_SC_KM where /*N_Detail = 1 and */ C_PLAN_CODE = ?) b\n "); 
//		builder.append("                   on a.C_KM_CODE_T = b.C_KM_CODE\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = b.C_DV_KM_Cls\n "); 
//		builder.append("                  and a.C_DC_CODE = b.C_DC_CODE\n "); 
//		builder.append("                 join (select C_DAI_CODE, N_FUND_WAY as N_WAY, C_DV_KM_Cls From T_S_Dai_Item) c\n "); 
//		builder.append("                   on a.C_Dai_Code = c.c_Dai_Code\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = c.C_DV_KM_Cls\n "); 
		builder.append("                where a.C_Port_Code = ?\n "); 
		builder.append("                  and Length(LTrim(a.C_KM_CODE)) > 0\n "); 
		builder.append("                  and C_Year_Month = ?\n "); 
		builder.append("                  and D_Stock = to_date(?, 'yyyy-MM-dd')\n "); 
//		builder.append("               --- 取出年累计发生额\n "); 
//		builder.append("               union all\n "); 
//		builder.append("               select a.C_Port_Code,\n "); 
//		builder.append("                      a.C_Year_Month,\n "); 
//		builder.append("                      a.D_Chk_Acc as D_BAL,\n "); 
//		builder.append("                      a.C_KM_Code,\n "); 
//		builder.append("                      a.C_KM_Name,\n "); 
//		builder.append("                      a.C_DC_CODE,\n "); 
//		builder.append("                      a.C_DV_KM_Cls,\n "); 
//		builder.append("                      nvl(b.N_WAY, c.N_WAY) as N_WAY,\n "); //wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
//		builder.append("                      NVL(substr(a.C_KM_CODE,1, instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P,\n "); 
//		builder.append("                      NVL(b.C_KM_CODE, a.C_KM_CODE) as C_KM_CODE_T,\n "); 
//		builder.append("                      NVL(b.C_KM_CODE_P, NVL(substr(a.C_KM_CODE, 1, instr(a.C_KM_CODE, '.', -1) - 1), '[root]')) as C_KM_CODE_P_T,\n "); 
//		builder.append("                      '年累计发生额' as C_Data_Type,\n "); 
//		builder.append("                      1 as N_DETAIL,\n "); 
//		builder.append("                      0 as N_A_INI_Y,\n "); 
//		builder.append("                      0 as N_A_INI,\n "); 
//		builder.append("                      0 as N_A_DEB,\n "); 
//		builder.append("                      0 as N_A_CRE,\n "); 
//		builder.append("                      (case when a.N_Way = 1 then a.N_Amount else 0 end) as N_A_DEB_Y,\n "); 
//		builder.append("                      (case when a.N_Way = -1 then a.N_Amount else 0 end) as N_A_CRE_Y,\n "); 
//		builder.append("                      0 as N_A_BAL,\n "); 
//		builder.append("                      0 as N_M_INI_Y,\n "); 
//		builder.append("                      0 as N_M_INI,\n "); 
//		builder.append("                      0 as N_M_DEB,\n "); 
//		builder.append("                      0 as N_M_CRE,\n "); 
//		builder.append("                      (case when a.N_Way = 1 then a.N_Orig_Money else 0 end) as N_M_DEB_Y,\n "); 
//		builder.append("                      (case when a.N_Way = -1 then a.N_Orig_Money else 0 end) as N_M_CRE_Y,\n "); 
//		builder.append("                      0 as N_M_BAL,\n "); 
//		builder.append("                      0 as N_PM_INI_Y,\n "); 
//		builder.append("                      0 as N_PM_INI,\n "); 
//		builder.append("                      0 as N_PM_DEB,\n "); 
//		builder.append("                      0 as N_PM_CRE,\n "); 
//		builder.append("                      (case when a.N_Way = 1 then a.N_Port_Money else 0 end) as N_PM_DEB_Y,\n "); 
//		builder.append("                      (case when a.N_Way = -1 then a.N_Port_Money else 0 end) as N_PM_CRE_Y,\n "); 
//		builder.append("                      0 as N_PM_BAL\n "); 
//		builder.append("                 from T_D_AI_ACT_VAL a\n "); 
//		builder.append("                 left join (select C_KM_CODE, C_KM_CODE_P, C_DV_KM_Cls, C_KM_NAME,C_DC_CODE, DECODE(C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_way \n "); //wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
//		builder.append("                              from T_F_SC_KM where /*N_Detail = 1 and */ C_Plan_Code = ?) b\n "); 
//		builder.append("                  on a.C_KM_CODE_T = b.C_KM_CODE\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = b.C_DV_KM_Cls\n "); 
//		builder.append("                  and a.C_DC_CODE = b.C_DC_CODE\n "); 
//		builder.append("                 join (select C_DAI_CODE, C_DV_KM_Cls, N_FUND_WAY as N_WAY from T_S_Dai_Item) c\n "); 
//		builder.append("                   on a.C_Dai_Code = c.c_Dai_Code\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = c.C_DV_KM_Cls\n "); 
//		builder.append("                where a.C_Port_Code = ?\n "); 
//		builder.append("                  and Length(LTrim(a.C_KM_CODE)) > 0\n "); 
//		builder.append("				  and a.N_CHECK_STATE = 1 \n");
//		builder.append("                  and (a.D_Chk_Acc between to_date(? || '-01-01', 'yyyy-MM-dd') and to_date(?, 'yyyy-MM-dd'))\n "); 
		builder.append("               --- 取出本期余额\n "); 
		builder.append("               union all\n "); 
		builder.append("				select a.C_Port_Code, a.C_Year_Month,\n" );
		builder.append("                    a.D_Stock as D_BAL,\n" ); 
		builder.append("                    a.C_KM_Code,\n" ); 
		builder.append("                    a.C_KM_Name,\n" ); 
		builder.append("                    a.C_DC_CODE,\n" ); 
		builder.append("                    a.C_DV_KM_Cls,\n" ); 
		builder.append("                    a.C_Dai_Code,\n" ); 
//		builder.append("                    nvl(b.N_WAY, c.N_WAY) as N_WAY,\n" ); 
		builder.append("                    NVL(substr(a.C_KM_CODE,1, Instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P,\n" ); 
		builder.append("                    a.C_KM_CODE_T,\n" ); 
//		builder.append("                    NVL(Substr(a.C_KM_CODE, 1, Instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P_T,\n" ); 
		builder.append("                    '本期余额' as C_Data_Type,\n" ); 
		builder.append("                    1 as N_DETAIL,\n" ); 
		builder.append("                    0 as N_A_INI_Y,\n" ); 
		builder.append("                    0 as N_A_INI,\n" ); 
		builder.append("                    0 as N_A_DEB,\n" ); 
		builder.append("                    0 as N_A_CRE,\n" ); 
		builder.append("                    0 as N_A_DEB_Y,\n" ); 
		builder.append("                    0 as N_A_CRE_Y,\n" ); 
		builder.append("                    N_Amount as N_A_BAL,\n" ); 
		builder.append("                    0 as N_M_INI_Y,\n" ); 
		builder.append("                    0 as N_M_INI,\n" ); 
		builder.append("                    0 as N_M_DEB,\n" ); 
		builder.append("                    0 as N_M_CRE,\n" ); 
		builder.append("                    0 as N_M_DEB_Y,\n" ); 
		builder.append("                    0 as N_M_CRE_Y,\n" ); 
		builder.append("                    N_Orig_Money as N_M_BAL,\n" ); 
		builder.append("                    0 as N_PM_INI_Y,\n" ); 
		builder.append("                    0 as N_PM_INI,\n" ); 
		builder.append("                    0 as N_PM_DEB,\n" ); 
		builder.append("                    0 as N_PM_CRE,\n" ); 
		builder.append("                    0 as N_PM_DEB_Y,\n" ); 
		builder.append("                    0 as N_PM_CRE_Y,\n" ); 
		builder.append("                    N_Port_Money as N_PM_BAL\n" ); 
		builder.append("               from T_D_Ai_Stock a\n" ); 
//		builder.append("               left join (select C_KM_CODE, C_KM_CODE_P, C_DV_KM_Cls, C_DC_CODE, DECODE(C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_way \n" ); 
//		builder.append("                            from T_F_SC_KM where /*N_Detail = 1 and */ C_PLAN_CODE = ?) b\n" ); 
//		builder.append("                on a.C_KM_CODE_T = b.C_KM_CODE\n" ); 
//		builder.append("                and a.C_DV_KM_Cls = b.C_DV_KM_Cls\n" ); 
//		builder.append("                and a.C_DC_CODE = b.C_DC_CODE\n" ); 
//		builder.append("              join (select C_DAI_CODE, N_FUND_WAY as N_WAY, C_DV_KM_Cls From T_S_Dai_Item) c\n" ); 
//		builder.append("                on a.C_Dai_Code = c.c_Dai_Code\n" ); 
//		builder.append("                and a.C_DV_KM_Cls = c.C_DV_KM_Cls\n" ); 
		builder.append("              where a.C_Port_Code = ?\n" ); 
		builder.append("                and Length(LTrim(a.C_KM_CODE)) > 0\n" ); 
		builder.append("                and C_Year_Month = to_char(to_date(?,'yyyy-MM-dd'),'yyyyMM')\n" ); 
		builder.append("                and D_Stock = to_date(?, 'yyyy-MM-dd')\n");
		builder.append("               --- 取本期发生额\n "); 
		builder.append("               union all\n "); 
		builder.append("               select a.C_Port_Code,\n "); 
		builder.append("                      a.C_Year_Month,\n "); 
		builder.append("                      a.D_Chk_Acc as D_BAL,\n "); 
		builder.append("                      a.C_KM_Code,\n "); 
		builder.append("                      a.C_KM_Name,\n "); 
		builder.append("                      a.C_DC_CODE,\n "); 
		builder.append("                      a.C_DV_KM_Cls,\n "); 
		builder.append("                    a.C_Dai_Code,\n" ); 
//		builder.append("                      nvl(b.N_WAY, c.N_WAY) as N_WAY,\n "); //wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
		builder.append("                      NVL(substr(a.C_KM_CODE, 1, instr(a.C_KM_CODE, '.', -1) - 1), '[root]') as C_KM_CODE_P,\n "); 
		builder.append("                      a.C_KM_CODE_T,\n "); 
//		builder.append("                      NVL(b.C_KM_CODE_P, NVL(substr(a.C_KM_CODE, 1, instr(a.C_KM_CODE, '.', -1) - 1), '[root]')) as C_KM_CODE_P_T,\n "); 
		builder.append("                      '本期发生额' as C_Data_Type,\n "); 
		builder.append("                      1 as N_DETAIL,\n "); 
		builder.append("                      0 as N_A_INI_Y,\n "); 
		builder.append("                      0 as N_A_INI,\n "); 
		builder.append("                      (case when a.N_Way = 1 then a.N_Amount else 0 end) as N_A_DEB,\n "); 
		builder.append("                      (case when a.N_Way = -1 then a.N_Amount else 0 end) as N_A_CRE,\n "); 
		builder.append("                      0 as N_A_DEB_Y,\n "); 
		builder.append("                      0 as N_A_CRE_Y,\n "); 
		builder.append("                      0 as N_A_BAL,\n "); 
		builder.append("                      0 as N_M_INI_Y,\n "); 
		builder.append("                      0 as N_M_INI,\n "); 
		builder.append("                      (case when a.N_Way = 1 then a.N_Orig_Money else 0 end) as N_M_DEB,\n "); 
		builder.append("                      (case when a.N_Way = -1 then a.N_Orig_Money else 0 end) as N_M_CRE,\n "); 
		builder.append("                      0 as N_M_DEB_Y,\n "); 
		builder.append("                      0 as N_M_CRE_Y,\n "); 
		builder.append("                      0 as N_M_BAL,\n "); 
		builder.append("                      0 as N_PM_INI_Y,\n "); 
		builder.append("                      0 as N_PM_INI,\n "); 
		builder.append("                      (case when a.N_Way = 1 then a.N_Port_Money else 0 end) as N_PM_DEB,\n "); 
		builder.append("                      (case when a.N_Way = -1 then a.N_Port_Money else 0 end) as N_PM_CRE,\n "); 
		builder.append("                      0 as N_PM_DEB_Y,\n "); 
		builder.append("                      0 as N_PM_CRE_Y,\n "); 
		builder.append("                      0 as N_PM_BAL\n "); 
		builder.append("                 from T_D_AI_ACT_VAL a\n "); 
//		builder.append("                 left join (select C_KM_CODE, C_KM_CODE_P, C_DV_KM_Cls, C_KM_NAME, C_DC_CODE, DECODE(C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_way \n "); //wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
//		builder.append("                              from T_F_SC_KM where /*N_Detail = 1 and */C_Plan_Code = ?) b\n "); 
//		builder.append("                  on a.C_KM_CODE_T = b.C_KM_CODE\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = b.C_DV_KM_Cls\n "); 
//		builder.append("                  and a.C_DC_CODE = b.C_DC_CODE\n "); 
//		builder.append("                 join (select C_DAI_CODE, C_DV_KM_Cls, N_FUND_WAY as N_WAY from T_S_Dai_Item) c\n "); 
//		builder.append("                   on a.C_Dai_Code = c.c_Dai_Code\n "); 
//		builder.append("                  and a.C_DV_KM_Cls = c.C_DV_KM_Cls\n "); 
		builder.append("                where a.C_Port_Code = ?\n "); 
		builder.append("                  and Length(LTrim(a.C_KM_CODE)) > 0\n "); 
		builder.append("				  and a.N_CHECK_STATE = 1 \n");
		builder.append("                  and (a.D_Chk_Acc between to_date(?, 'yyyy-MM-dd') and to_date(?, 'yyyy-MM-dd'))) bal\n "); 
		builder.append(" LEFT JOIN (SELECT A.C_KM_CODE,");
		builder.append("                    A.C_KM_CODE_P,");
		builder.append("                    A.C_KM_NAME,");
		builder.append("                    A.C_DC_CODE,");
		builder.append("                    DECODE(A.C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_way,");
		builder.append("                    AA.C_DV_KM_CLS");
		builder.append("             FROM T_F_SC_KM A, T_F_SC_KM AA");
		builder.append("             WHERE A.C_PLAN_CODE = ? ");
		builder.append("             AND AA.C_PLAN_CODE = ? ");
		builder.append("             AND PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 1) = AA.C_KM_CODE");
		builder.append("             AND A.C_PLAN_CODE = AA.C_PLAN_CODE ");
		builder.append(" group by A.C_KM_CODE,A.C_KM_CODE_P, A.C_KM_NAME,A.C_DC_CODE,A.C_DV_JD_WAY,AA.C_DV_KM_CLS");
		builder.append("   ) km");
		builder.append("   ON bal.C_KM_CODE_T = km.C_KM_CODE");
		builder.append("      AND bal.C_DV_KM_Cls = km.C_DV_KM_Cls ");//20180925 BUG221380（紧急）【嘉实基金】电子对账余额表生成报错 
		builder.append("      AND bal.C_DC_CODE= km.C_DC_CODE ");
		builder.append("  join (select C_DAI_CODE, C_DV_KM_Cls, N_FUND_WAY as N_WAY");
		builder.append("          from T_S_Dai_Item) item ");
		builder.append("    on bal.C_Dai_Code = item.c_Dai_Code");
		builder.append("    and bal.C_DV_KM_Cls = item.C_DV_KM_Cls  ");
		builder.append("        group by bal.C_Port_Code, bal.C_KM_Code, NVL(km.C_DV_KM_CLS, bal.C_DV_KM_Cls),\n "); 
		builder.append("                 bal.N_DETAIL, nvl(ITEM.N_WAY, KM.N_WAY), bal.C_DC_Code");

		return builder.toString();
	}
	/**
	 * 从临时表R_F_BM_BAL逐级统计上级科目余额
	 * @throws Exception 
	 */
	protected void geneDataLoopByKmLevel() throws Exception {
		PreparedStatement pst = null;
//		PreparedStatement insertPst = null;
//		ResultSet rs = null;
//		int count = 0;
		try {
			String sqlStr = getTempTableSql()+getDataLoopByKmLevelSql();
//			insertPst = conn.prepareStatement(getTempTableSql());
			pst = conn.prepareStatement(sqlStr);
			while (kmLevel > 0) {
				int index = 1;
				pst.setInt(index++, this.kmLevel);
				pst.setInt(index++, this.kmLevel);
				pst.setInt(index++, this.kmLevel);
				pst.setInt(index++, this.kmLevel);
				pst.setString(index++, this.planCode);
				pst.setInt(index++, this.kmLevel);
				pst.setString(index++, this.portCode);
				pst.setInt(index++, this.kmLevel);
				pst.setInt(index++, this.kmLevel);
				pst.executeUpdate();
//				rs = pst.executeQuery();
//				while (rs.next()) {
//					setTempPojo(insertPst, rs);
//					insertPst.addBatch();
//					if(++count % batchSize == 0) {
//						insertPst.executeBatch();
//					}
//				}
//				insertPst.executeBatch();
//				DbFun.closeResultSetFinal(rs);
//				pst.clearBatch();
				kmLevel--;
			}
		} catch (Exception e) {
			throw e;
		}finally{
//			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}
	protected String getDataLoopByKmLevelSql() {
		StringBuilder builder = new StringBuilder();
		
		builder.append("          select y.* from (select bal.C_Port_Code,\n ");
		builder.append("                          max(PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(bal.C_KM_Code, ?)) as C_KM_CODE,\n ");
		builder.append("                         max(NVL(km_c_km_name, ' ')) as C_KM_NAME,\n ");
		builder.append("                         max(NVL(PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(bal.C_KM_Code_P, ? - 1), '[root]')) as C_KM_CODE_P,\n ");
		builder.append("                         max(PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(bal.C_KM_CODE_T, ?)) as C_KM_CODE_T,\n ");
		builder.append("                         max(NVL(PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(bal.C_KM_CODE_T, ? - 1), '[root]')) as C_KM_CODE_P_T,\n ");
		builder.append("                         0 as N_DETAIL, bal.N_KM_Way AS N_Way, bal.C_Year, bal.D_Bal, bal.N_Period,\n ");
		builder.append("                         sum(N_A_INI_Y) as N_A_INI_Y,\n");
		builder.append("						 sum(N_A_INI) as N_A_INI,\n");
		builder.append("						 sum(N_A_DEB) as N_A_DEB,\n");
		builder.append("						 sum(N_A_CRE) as N_A_CRE,\n ");
		builder.append("                         sum(N_A_DEB_Y) as N_A_DEB_Y, \n");
		builder.append("						 sum(N_A_CRE_Y) as N_A_CRE_Y, \n");
		builder.append("						 sum(N_A_BAL) as N_A_BAL,\n \n");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_INI_Y end) as N_M_INI_Y,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_INI end) as N_M_INI,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_DEB end) as N_M_DEB,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_CRE end) as N_M_CRE,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_DEB_Y end) as N_M_DEB_Y,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_CRE_Y end) as N_M_CRE_Y,\n ");
//		builder.append("                         sum(case when km_C_DC_Code = '***' then 0 else N_M_BAL end) as N_M_BAL,\n ");
		builder.append("                         sum(N_M_INI_Y) as N_M_INI_Y,\n ");
		builder.append("                         sum(N_M_INI) as N_M_INI,\n ");
		builder.append("                         sum(N_M_DEB) as N_M_DEB,\n ");
		builder.append("                         sum(N_M_CRE) as N_M_CRE,\n ");
		builder.append("                         sum(N_M_DEB_Y) as N_M_DEB_Y,\n ");
		builder.append("                         sum(N_M_CRE_Y) as N_M_CRE_Y,\n ");
		builder.append("                         sum(N_M_BAL) as N_M_BAL,\n ");
		builder.append("                         sum(N_PM_INI_Y) as N_PM_INI_Y,\n ");
		builder.append("                         sum(N_PM_INI) as N_PM_INI,\n ");
		builder.append("                         sum(N_PM_DEB) as N_PM_DEB,\n ");
		builder.append("                         sum(N_PM_CRE) as N_PM_CRE,\n ");
		builder.append("                         sum(N_PM_DEB_Y) as N_PM_DEB_Y,\n ");
		builder.append("                         sum(N_PM_CRE_Y) as N_PM_CRE_Y,\n ");
		builder.append("                         sum(N_PM_BAL) as N_PM_BAL,\n ");
		builder.append("						 MAX(CASE WHEN km_C_DC_Code = 'CNY' THEN '001'\n");
		builder.append("     					 WHEN bal.C_DC_CODE = 'USD' THEN '002'\n" ); 
		builder.append("     					 ELSE' ' END) AS C_DC_CODE,");
		//EditByliyongjun 20161117 资产类设置共同类核算项目后，查询余额表报错，因为原逻辑会根据资产类别汇总，相同科目汇总了两条
		// 取明细科目资产类别时，直接取上级科目资产类别，避免上级科目重复
		// BUG #145331 【南方基金】【电子对账】（紧急）资产类设置共同类核算项目后，电子对账余额表发送指令报错
//		builder.append("                         bal.C_DV_KM_Cls\n ");
//		builder.append("                    from R_F_BM_BAL bal\n ");
		builder.append("  						 bal.C_DV_KM_Cls1 AS C_DV_KM_Cls\n  ");
		builder.append(" 						 FROM ( ");
		builder.append(" 						 SELECT bal.*,\n");
		builder.append(" 						 km.c_km_code AS km_c_km_code,\n");
		builder.append(" 						 km.c_km_name AS km_c_km_name,\n");
		builder.append("                         DECODE(km.C_DV_JD_WAY, 'JD_J', 1, 'JD_D', -1, 0) as n_km_way,");//wlx 20171108 BUG #179024 建信基金，电子对账，生成余额表报违反唯一约束条件 
		builder.append("						 km.c_dc_code AS km_C_DC_Code,\n") ;
		builder.append("						 km.C_DV_KM_CLS AS C_DV_KM_CLS1 \n");
		builder.append("						 FROM R_F_Bm_Bal bal join (select a.C_KM_Code, a.C_KM_NAME, a.C_DC_Code, aa.C_DV_KM_CLS, a.C_DV_JD_WAY ");
		builder.append("						 from T_F_Sc_Km a , T_F_Sc_Km aa where a.c_Plan_Code = ? ");
		builder.append("						 and aa.c_plan_code = a.c_plan_code and PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 1) = aa.C_KM_CODE ");
//		builder.append("                    left join (select C_KM_Code, C_KM_Name, C_DC_Code\n ");
//		builder.append("                                from T_F_Sc_Km a where c_Plan_Code = ?\n ");
		builder.append("							   and PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(A.C_KM_Code) = ?");
		builder.append("                               and not exists(select '1' from R_F_BM_BAL c where c.c_km_code_T = a.c_km_code) ");
		builder.append(" group by A.C_KM_CODE,A.C_KM_NAME, A.C_DC_Code,AA.C_DV_KM_CLS,A.C_DV_JD_WAY ");
		builder.append(" ) km ");
		builder.append("                    on bal.C_KM_Code_P_T = km.C_KM_Code\n ");
		builder.append("                   where bal.C_Port_Code = ?\n ");
		builder.append("                     and PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(bal.C_KM_Code_P) = ?\n ");
		builder.append("                    ) bal\n");
		builder.append("                   group by bal.C_Port_Code,\n ");
		builder.append("                            PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(bal.C_KM_Code, ?),\n ");
		builder.append("                            bal.N_KM_Way,\n ");
		builder.append("                            bal.C_Year,\n ");
		builder.append("                            bal.C_DV_KM_Cls1,\n ");
		builder.append("                            bal.D_Bal,\n ");
		builder.append("                            bal.N_Period,\n ");
		builder.append("                            km_C_DC_Code) y\n ");
		
		
		return builder.toString();
	}
	/**
	 * 插入余额表T_D_ER_KM预处理sql语句
	 * @return
	 */
	protected String getInsertSql(){
		StringBuilder builder = new StringBuilder();

		builder.append("insert into T_D_ER_YE ");
		builder.append("   (C_IDEN, C_SN, C_FILE_TYPE, C_RPT_TYPE, C_ASS_CODE, D_START_DATE, D_END_DATE, C_DV_ER_WAY, ");
		//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，新增科目名称
		//builder.append("    C_KM_CODE, C_DC_CODE, N_ORIG_StartBal, N_ORIG_Debit, N_ORIG_Credit, ");
		builder.append("    C_KM_CODE,C_KM_NAME, C_DC_CODE, N_ORIG_StartBal, N_ORIG_Debit, N_ORIG_Credit, ");
		builder.append("    N_ORIG_EndBal, N_PORT_StartBal, N_PORT_Debit, N_PORT_Credit, "); 
		builder.append("    N_PORT_EndBal, N_AMOUNT_StartBal, N_AMOUNT_Debit, ");
		builder.append("    N_AMOUNT_CREDIT,  N_AMOUNT_EndBal, N_DETAIL, N_J_TOLTAL_AMOUNT, N_D_TOLTAL_AMOUNT)");
		builder.append(" select SEQU_D_ER_YE.NEXTVAL,'").append(this.fsn).append("','1001','01','");
		builder.append(this.assCode).append("',");
		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),");
		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),'FORWARD',");
//		builder.append("?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		return builder.toString();
		
	}
	/**
	 * 明细项生成
	 * @return
	 * @throws Exception
	 */
	protected int geneYEBData() throws Exception {
		PreparedStatement pst = null;
//		PreparedStatement insertPst = null;
//		ResultSet rs = null;
		int count = 0;
		try {
			String str = getInsertSql()+getYEBSql();
			pst = conn.prepareStatement(str);
			count = pst.executeUpdate();
//			while(rs.next()){
//				boolean isAdd = setPojo(insertPst, rs, 0);
//				if(isAdd){
//					insertPst.addBatch();
//					if(++count % batchSize == 0) {
//						insertPst.executeBatch();
//					}
//				}
//			}
//			insertPst.executeBatch();
		} catch (Exception e) {
			throw e;
		}finally{
//			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return count;
	}
	/**
	 * 
	 *  C_KM_CODE, C_DC_CODE, N_ORIG_StartBal, N_ORIG_Debit, N_ORIG_Credit,
	 *   N_ORIG_EndBal, N_PORT_StartBal, N_PORT_Debit, N_PORT_Credit,
	 *    N_PORT_EndBal, N_AMOUNT_StartBal, N_AMOUNT_Debit,
	 *      N_AMOUNT_CREDIT,  N_AMOUNT_EndBal, N_DETAIL, N_J_TOLTAL_AMOUNT, N_D_TOLTAL_AMOUNT
	 * @param pst
	 * @param rs
	 * @throws SQLException
	 */
	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs, int type,HashMap<String, ElecPerRela> elecPerRelaMap) throws Exception {
		boolean isAdd = true;
		ElecPerRela perRelaPojo = null;
		if(elecPerRelaMap != null){
			perRelaPojo = elecPerRelaMap.get(rs.getString("C_KM_CODE"));
			if (perRelaPojo != null && "0".equalsIgnoreCase(perRelaPojo.getC_SEND_MODE())) {
				return false;
			}
		}
		int indexHead = setPojoHead(pst, rs, type);
		pst.setString(indexHead + 1, KMCodeTransfer(rs.getString("C_KM_CODE")));
		pst.setString(indexHead + 2, rs.getString("C_DC_CODE"));
		pst.setDouble(indexHead + 3, rs.getDouble("N_ORIG_StartBal"));
		pst.setDouble(indexHead + 4, rs.getDouble("N_ORIG_Debit"));
		pst.setDouble(indexHead + 5, rs.getDouble("N_ORIG_Credit"));
		pst.setDouble(indexHead + 6, rs.getDouble("N_ORIG_EndBal"));
		pst.setDouble(indexHead + 7, rs.getDouble("N_PORT_StartBal"));
		pst.setDouble(indexHead + 8, rs.getDouble("N_PORT_Debit"));
		pst.setDouble(indexHead + 9, rs.getDouble("N_PORT_Credit"));
		pst.setDouble(indexHead + 10, rs.getDouble("N_PORT_EndBal"));
		pst.setDouble(indexHead + 11, rs.getDouble("N_AMOUNT_StartBal"));
		pst.setDouble(indexHead + 12, rs.getDouble("N_AMOUNT_Debit"));
		pst.setDouble(indexHead + 13, rs.getDouble("N_AMOUNT_CREDIT"));
		pst.setDouble(indexHead + 14, rs.getDouble("N_AMOUNT_EndBal"));
		pst.setInt(indexHead + 15, rs.getInt("N_DETAIL"));
		pst.setDouble(indexHead + 16, rs.getDouble("N_J_TOLTAL_AMOUNT"));
		pst.setDouble(indexHead + 17, rs.getDouble("N_D_TOLTAL_AMOUNT"));
		isAdd = executeSpecial(pst, rs, type, indexHead);
		return isAdd;
	}
	/**
	 * 余额表数据的数据源sql
	 * @return
	 */
	protected String getYEBSql(){
		StringBuilder builder = new StringBuilder();

//		builder.append("select ");
		//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，新增科目名称
		builder.append("            C_KM_CODE,");
		builder.append("             C_KM_NAME,\n");
		builder.append("             C_DC_CODE,\n ");
		/**
		 *  STORY #48815 嘉实基金电子对账-中行-港股通组合需要用本位币（人民币）发送电子对账 
		 *  新增产品估值参数“电子对账数据使用本币发送”（SV_BB_DZDZ_BBFS） 
	 	 *	参数值：是、否        默认值：否 
	     *	是：使用本币发送电子对账数据。 
	     *	否：使用原币发送电子对账数据。 
		 */
		if(isUseBB){
			builder.append("             N_PM_INI * N_Way as N_ORIG_StartBal,\n ");//-- 期初余额(原币)
			builder.append("             N_PM_DEB as N_ORIG_Debit,\n ");//-- 本期借方发生额(原币)
			builder.append("             N_PM_CRE as N_ORIG_Credit, \n ");//-- 本期贷方发生额(原币)
			builder.append("             N_PM_BAL * N_Way as N_ORIG_EndBal,\n ");// --期末余额(原币)
		}else{
			builder.append("             N_M_INI * N_Way as N_ORIG_StartBal,\n ");//-- 期初余额(原币)
			builder.append("             N_M_DEB as N_ORIG_Debit,\n ");//-- 本期借方发生额(原币)
			builder.append("             N_M_CRE as N_ORIG_Credit, \n ");//-- 本期贷方发生额(原币)
			builder.append("             N_M_BAL * N_Way as N_ORIG_EndBal,\n ");// --期末余额(原币)
		}
		
		builder.append("             N_PM_INI * N_Way as N_PORT_StartBal, \n ");//-- 期初余额(本币)
		builder.append("             N_PM_DEB as N_PORT_Debit,\n ");// -- 本期借方发生额(本币)
		builder.append("             N_PM_CRE as N_PORT_Credit, \n ");//-- 本期贷方发生额(本币)
		builder.append("             N_PM_BAL * N_Way as N_PORT_EndBal, \n ");//--期末余额(本币)
		if(this.isGeneAmount){	
			builder.append("             N_A_INI * N_Way as N_AMOUNT_StartBal,\n ");// -- 期初余额(数量)
			builder.append("             N_A_DEB as N_AMOUNT_Debit, \n ");//-- 本期借方发生额(数量)
			builder.append("             N_A_CRE as N_AMOUNT_CREDIT,\n ");// -- 本期贷方发生额(数量)
			builder.append("             N_A_BAL * N_Way as N_AMOUNT_EndBal,\n ");// -- 期末余额(数量)
		}else{
			builder.append(" CASE WHEN N_DETAIL=1 THEN N_A_INI * N_Way ELSE 0 END AS N_AMOUNT_StartBal,\n ");// -- 期初余额(数量)
			builder.append(" CASE WHEN N_DETAIL=1 THEN N_A_DEB ELSE 0 END AS N_AMOUNT_Debit, \n ");//-- 本期借方发生额(数量)
			builder.append(" CASE WHEN N_DETAIL=1 THEN N_A_CRE ELSE 0 END AS N_AMOUNT_CREDIT,\n ");// -- 本期贷方发生额(数量)
			builder.append(" CASE WHEN N_DETAIL=1 THEN N_A_BAL * N_Way ELSE 0 END AS N_AMOUNT_EndBal,\n ");// -- 期末余额(数量)
		}
		builder.append("             N_DETAIL,\n ");
		builder.append("             N_A_DEB_Y as N_J_TOLTAL_AMOUNT,\n ");//--借方累计发生额
		builder.append("             N_A_CRE_Y as N_D_TOLTAL_AMOUNT \n ");//--贷方累计发生额
		builder.append("        from R_F_BM_BAL ");

		return builder.toString();
	}
	@Override
	protected boolean executeSpecial(PreparedStatement pst, ResultSet rs,
			int dataType, int indexHead) throws Exception {
		boolean isAdd = true;
		//BUG199876汇添富项目，估值表产生电子对账失败，报空指针
		if(YssContextFactory.getInstance() == null || YssContextFactory.getInstance().getLicense() == null){
			logger.info("获取YssContextFactory.getInstance().getLicense()为NULL");
			return true;
		}
		if(LicCompanyName.COMPANY_GFSC.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())){
			if(DZMS_GHMS_Z.equalsIgnoreCase(this.C_DZ_MODE)){
				String kmCode = KMCodeTransfer(rs.getString("C_KM_CODE"));
				if(0 == rs.getInt("N_DETAIL") && 
						("1031.01".equalsIgnoreCase(kmCode) || "1031.02".equalsIgnoreCase(kmCode)
								||"1031.03".equalsIgnoreCase(kmCode)||"1021.01".equalsIgnoreCase(kmCode)
								||"1021.02".equalsIgnoreCase(kmCode)||"1021.03".equalsIgnoreCase(kmCode))){
					isAdd = false;
					return isAdd;
				}
				if("1105.13.99".equalsIgnoreCase(kmCode)){
					pst.setDouble(indexHead + 14, 0.0);
				}
			}
			
			if(DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE) && rs.getString("C_KM_CODE") != null && rs.getString("C_KM_CODE").length() == 4){
				pst.setDouble(indexHead + 11, 0.0);
				pst.setDouble(indexHead + 12, 0.0);
				pst.setDouble(indexHead + 13, 0.0);
				pst.setDouble(indexHead + 14, 0.0);
			}
		}else if(LicCompanyName.COMPANY_HTSC_ZG.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())
				|| LicCompanyName.COMPANY_HTSC_WB.equalsIgnoreCase(YssContextFactory.getInstance().getLicense().getCompany())){
			if(!DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE)){
				String c_km_code = rs.getString("C_KM_CODE");
				if(c_km_code.contains("1111.")){
					c_km_code = c_km_code.replaceAll("1111.", "1103.");
				}else if(c_km_code.contains("1111") && !c_km_code.contains(".")){
					c_km_code = c_km_code.replaceAll("1111", "1103");
				}
				pst.setString(indexHead + 1, c_km_code);
			}
		}
		return isAdd;
	}
	/**
	 * 获取估值产品参数
	 */
	private void getPortPara() {
		//获取参数值
		AdmPortActParams paras = new AdmPortActParams(this.portCode, new Date());
		paras.setDbConn(conn);
		try {
			paras.initActParams();
		} catch (Exception e) {
			logger.error("获取估值产品参数出错：" + e.getMessage(), e);
		}
		/**
		 *  STORY #48815 嘉实基金电子对账-中行-港股通组合需要用本位币（人民币）发送电子对账 
		 *  新增产品估值参数“电子对账数据使用本币发送”（SV_BB_DZDZ_BBFS） 
	 	 *	参数值：是、否        默认值：否 
	     *	是：使用本币发送电子对账数据。 
	     *	否：使用原币发送电子对账数据。 
		 */
		if(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_BBFS) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.SV_BB_DZDZ_BBFS))){
			isUseBB = true;
		}
		/**
		 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本不需要数量
		 * 默认为是
		 * 针对4001及其下面的子科目
		 */
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YEBSSZBZSSL) != null && "0".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_YEBSSZBZSSL))){
			DZ_BB_DZDZ_YEBSSZBZSSL = false;
		}else{
			DZ_BB_DZDZ_YEBSSZBZSSL = true;
		}
		int transGZ = KMTransUtil.getGzCode(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH), paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH));
		if(transGZ!=KMTransUtil.ZHGZ_BZH)
		{
			isTransKM = true;
			try {
				kmTransMap = KMTransUtil.getTransMap(conn, transGZ);
			} catch (Exception e) {
				logger.error("获取披露代码出错：" + e.getMessage(), e);
			}
		}else
		{
			isTransKM = false;
		}
		//STORY62407【大成基金】【紧急】余额表发送电子对账非明细科目是否发送数量
		String geneAmount = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_ELEC_YE_AMOUNT);
		if(geneAmount != null && "0".equalsIgnoreCase(geneAmount)){
			this.isGeneAmount = false;
		}
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111))){
			trans1111KmCode = true;
		}else{
			trans1111KmCode = false;
		}
		//BUG #272834 太平养老-电子对账生成的余额表6407.02四级科目不显示。
		String value = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_ELEC_YE_6407);
		if(value != null && "0".equalsIgnoreCase(value)){
			this.isSend6407FourKmCode = false;
		}
		String value2 =paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_ELEC_YE_6605);
		if(value2 != null && "0".equalsIgnoreCase(value2)){
			this.isSend6605ThreeKmCode = false;
		}
	}
	/**
	 * STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
	 * 转换证券代码
	 * */
	private void transKMCode(){
		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		try {
			if(isTransKM)
			{
				StringBuilder selectBuilder = new StringBuilder();
				selectBuilder.append("SELECT C_KM_CODE FROM T_D_ER_YE WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? ");
				StringBuilder updateBuilder = new StringBuilder();
				updateBuilder.append("UPDATE T_D_ER_YE SET C_KM_CODE = ? WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? AND C_KM_CODE = ? ");

				pst = conn.prepareStatement(selectBuilder.toString());
				int index = 1;
				pst.setString(index++, this.assCode);
				pst.setString(index++, this.fsn);
				pst.setDate(index++, YssFun.toSqlDate(geneDate));

				updatePst = conn.prepareStatement(updateBuilder.toString());
				rs = pst.executeQuery();
				String newKmCode = "";
				while(rs.next()){
					newKmCode = KMTransUtil.transKMCode(rs.getString("C_KM_CODE"), kmTransMap);
					index = 1;
					updatePst.setString(index++, newKmCode);

					updatePst.setString(index++, this.assCode);
					updatePst.setString(index++, this.fsn);
					updatePst.setDate(index++, YssFun.toSqlDate(geneDate));

					updatePst.setString(index++, rs.getString("C_KM_CODE"));
					updatePst.addBatch();
				}
				updatePst.executeBatch();
			}
		} catch (Exception e) {
			 logger.error("转换披露代码出错：" + e.getMessage(), e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(updatePst);
			DbFun.closeStatementFinal(pst);
		}
	}
	/**
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 * 
	 * @throws Exception
	 */
	private void trans1111KMCode() throws Exception {
		if(this.trans1111KmCode){
			PreparedStatement pst = null;
			try {
				String updateSql = " UPDATE T_D_ER_YE A SET A.C_KM_CODE = REGEXP_REPLACE(A.C_KM_CODE,'1111','1103',1,1) WHERE A.C_KM_CODE LIKE '1111%' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
				pst = conn.prepareStatement(updateSql);
				int index = 1;
				pst.setString(index++, this.assCode);
				pst.setString(index++, this.fsn);
				pst.setDate(index++, YssFun.toSqlDate(geneDate));
				pst.executeUpdate();
			} catch (Exception e) {
				logger.error("科目代码1111.更新为1103.！", e);
				throw e;
			}finally{
				DbFun.closeStatementFinal(pst);
			}
		}
	}
}
