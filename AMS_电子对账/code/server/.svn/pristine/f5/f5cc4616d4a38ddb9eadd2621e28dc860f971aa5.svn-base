package com.yss.uco.elecreco.er.generate.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.bi.elecrela.service.IElecPerRelaService;
import com.yss.uco.elecreco.er.erinfostate.service.ErStepStateService;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErStepState;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;

public abstract class GeneElecDataService {

	/**
	 * 工行总行模式
	 */
	protected static final String DZMS_GHMS_Z = "DZMS_GHMS_Z";
	/**
	 * 工行分行模式
	 */
	protected static final String DZMS_GHMS = "DZMS_GHMS";
	/**
	 * 招商证券模式
	 */
	protected static final String DZMS_ZSZQ = "DZMS_ZSZQ";
	/**
	 * 
	 */
	protected static final String FORWORD = "FORWARD";
	/**
	 * 中行模式
	 */
	protected static final String DZMS_ZHMS = "DZMS_ZHMS";
	protected DecimalFormat decimalFormat = new DecimalFormat("#.0000");  
	protected Logger logger = LogManager.getLogger(GeneElecDataService.class);
	/**
	 * 数据库连接
	 */
	protected Connection conn = null;
	/**
	 * 组合代码
	 */
	protected String portCode = null;
	/**
	 * 资产类型 wulongxing 20171130 STORY #49703 【嘉实基金】社保组合6407.02科目发送电子对账时需要特殊处理
	 */
	protected String assType = "";
	
	/**
	 * 组合级别
	 */
	protected String dvPortCode = "";
	
	/**
	 * 生成的业务日期
	 */
	protected String geneDate = null;
	/**
	 * 当前用户
	 */
	protected String userCode = null;
	/**
	 * 报文序号
	 */
	protected String fsn = null;
	/**
	 * 资产代码
	 */
	protected String assCode = null;
	/**
	 * 核算方案代码
	 */
	protected String planCode = null;
	/**
	 * 科目级别
	 */
	protected int kmLevel = 4;
	/**
	 * 成本
	 */
	protected double N_PORT_COST = 0;
	/**
	 * 市值
	 */
	protected double N_PORT_MV = 0;
	/**
	 * 估值增值
	 */
	protected double N_PORT_IV = 0;
	/**
	 * 实收资本
	 */
	protected double N_AMOUNT = 0;
	/**
	 * 实收资金的成本列
	 */
	protected double N_SSZB_COST = 0;
	/**
	 * 估值表发送模式
	 */
	protected String C_GZB_MODE = "";
	/**
	 * 对账模式
	 */
	protected String C_DZ_MODE = "";
	/**
	 * 批处理最大批次
	 */
	protected final static int batchSize = 1000;
	/**
	 * 报表类型
	 */
	protected String c_rpt_type = "";
	/**
	 * 开始日期
	 */
	protected String startDate = "";
	/**
	 * 结束日期
	 */
	protected String endDate = "";
	/**
	 * 报表时间
	 */
	protected String reportTime = "";
	
	/**
	 * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 数据insert之后
	 * 通过组合代码取到科目长度限制，若不为空对则对科目名称长度大于科目长度限制的科目名称进行截取
	 * @author cls
	 * @date 2018年8月13日
	 */
	protected String C_KM_NAME_LENGTH ="";
	/**
	 *财务报表代码
	 */
	protected String reportCode = "";
	
	/**
	 * 日志记录
	 */
	protected BEN_RECORD ben_Record = null;
	
	public void init(Connection conn, String portCode, String geneDate, String userCode) {
		this.conn = conn;
		this.portCode = portCode;
		this.geneDate = geneDate;
		this.userCode = userCode;
	}
	public void init2(String c_rpt_type, String startDate, String endDate, String reportTime, String reportCode) {
		this.c_rpt_type = c_rpt_type;
		this.startDate = startDate;
		this.endDate = endDate;
		this.reportTime = reportTime;
		this.reportCode = reportCode;
	}
	public void init(Connection conn, String portCode, String geneDate, String userCode, BEN_RECORD ben_Record) {
		this.conn = conn;
		this.portCode = portCode;
		this.geneDate = geneDate;
		this.userCode = userCode;
		this.ben_Record = ben_Record;
	}
	/**
	 * 生成电子对账数据
	 * @return 返回生成结果
	 * @throws Exception
	 */
	public abstract Map<String, Object> geneElecData() throws Exception;
	/**
	 * 生成电子对账数据
	 * @return 返回生成结果
	 * @throws Exception
	 */
	protected abstract boolean setPojo(PreparedStatement pst, ResultSet rs, int dataType,HashMap<String, ElecPerRela> elecPerRelaMap) throws Exception;
	/**
	 * 生成电子对账数据设置头字段
	 * BUG197123减少不必要的SQL硬解析比率，优化数据库执行效率
	 * @return 返回生成结果
	 * @throws Exception
	 */
	protected int setPojoHead(PreparedStatement pst, ResultSet rs, int dataType) throws Exception{
		return 0;
	}
	
	/**
	 * 追加记录日志
	 */
	protected void appendLogDetail(String detail)
	{
		if(this.ben_Record != null)
		{
			this.ben_Record.appendDetailMes(detail);
		}
	}
	
	/**
	 * 获取fsn报文序号
	 * @return
	 * @throws Exception
	 */
	public String createFsn() throws Exception{
		String fsn = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sqlString = "";
			sqlString = " select SEQU_D_ER_SN.NEXTVAL AS SN from dual";
			pst = conn.prepareStatement(sqlString);
			rs = pst.executeQuery();
			if(rs.next()){
				fsn = "DZ" + DateUtil.getCurrDate("yyyyMMdd") + String.format("%05d", Integer.valueOf(rs.getString(1)));
			}
			this.fsn = fsn;
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return fsn;
	}
	/**
	 * 根据组合代码或者群组代码获取核算方案
	 * @param code
	 * @param geneDate
	 * @return
	 * @throws Exception 
	 */
	protected Map<String, String> getPlanByPortCodeOrGroupCode(String code,String geneDate) throws Exception
	{
		Map<String, String> planMap = new HashMap<String, String>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" select a.C_PLAN_CODE, nvl(length(b.c_km_spec), 0) as c_km_spec ");
		
		//BUG #251623 电子对账管理生成电子对账，如果方案关联组合设置了群组但是没有单独设置产品进行关联，则无法生成估值表数据
		//由T_E_EXEC_PLAN_RELA改成V_E_EXEC_PLAN_RELA试图sql
		sb.append(" FROM ( SELECT e.* FROM ( SELECT D.C_PORT_CODE,A.C_PLAN_TYPE,A.C_PLAN_CODE,A.D_BEGIN,A.D_END,A.N_CHECK_STATE from T_E_EXEC_PLAN_RELA a ");
		sb.append(" left join (select b.c_group_code, c.c_port_code ");
		sb.append(" from T_P_AB_GROUP b ");
		sb.append(" left join T_P_AB_GROUP_RELA c ");
		sb.append(" on b.c_group_code = c.c_group_code ");
		sb.append(" where b.n_check_state = 1 ");
		sb.append(" and c.n_check_state = 1) d ");
		sb.append(" on d.c_group_code = a.c_port_code) E ");
		sb.append(" where E.C_PORT_CODE is not null ");
		sb.append(" and NOT EXISTS (select 1 from T_E_EXEC_PLAN_RELA rela ");
		sb.append(" where rela.C_PORT_CODE = E.C_PORT_CODE ");
		sb.append(" and rela.C_PLAN_TYPE = E.C_PLAN_TYPE ");
		sb.append(" and rela.N_CHECK_STATE = E.N_CHECK_STATE) ");
		sb.append(" UNION ALL ");
		sb.append(" SELECT A.C_PORT_CODE,A.C_PLAN_TYPE,A.C_PLAN_CODE,A.D_BEGIN,A.D_END,A.N_CHECK_STATE ");
		sb.append(" from T_E_EXEC_PLAN_RELA a where NOT EXISTS (SELECT 1 from T_P_AB_GROUP b where a.c_port_code = b.c_group_code and b.n_check_state = 1) ");
		sb.append(" ) a ");
				
		sb.append(" left join (select c_km_spec, c_plan_code ");
		sb.append(" from T_F_SC_KM_RELA ");
		sb.append(" where N_CHECK_STATE = 1) b on a.C_PLAN_CODE = ");
		sb.append(" b.c_plan_code ");
		sb.append(" where a.C_PLAN_Type = 'AO_LEVEL' ");
		sb.append(" and a.n_check_state = 1 ");
		sb.append(" and a.C_PORT_CODE = ? ");
		sb.append(" and a.d_begin <= to_date(?, 'yyyy-MM-dd') ");
		sb.append(" and a.d_end >= to_date(?, 'yyyy-MM-dd') ");
		try {
			pst = conn.prepareStatement(sb.toString());
			pst.setString(1, code);
			pst.setString(2, geneDate);
			pst.setString(3, geneDate);
			rs = pst.executeQuery();
			if(rs.next()){
				planMap.put("planCode", rs.getString("C_PLAN_CODE"));
				planMap.put("kmLevel", rs.getString("c_km_spec"));
			}
		} catch (Exception e) {
			this.logger.error("获取科目级别失败！");
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return planMap;
	}
	/**
	 * 根据日期，组合查询核算级别方案代码及科目体系长度
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> Pro_GetPlan() throws Exception{
		Map<String, Object> planMap = new HashMap<String, Object>();
		//BUG251623电子对账管理生成电子对账，如果方案关联组合设置了群组但是没有单独设置产品进行关联，则无法生成估值表数据
//		CallableStatement proc = null;
		try {
//			String sqlString = " {call Pro_GetPlan(?,?,?,?)} ";
//			proc = conn.prepareCall(sqlString);
//			proc.setString(1, geneDate);
//			proc.setString(2, portCode);
//			proc.registerOutParameter(3, Types.VARCHAR);
//			proc.registerOutParameter(4, Types.INTEGER);
//			proc.execute();
//			this.planCode = proc.getString(3);
//			this.kmLevel = proc.getInt(4);
//			planMap.put("planCode", planCode);
//			planMap.put("kmLevel", kmLevel);
			Map<String, String> temp = getPlanByPortCodeOrGroupCode(portCode, geneDate);
			if(temp.size() == 0)
			{
				String groupCode = getGroupCode(portCode);
				if(!StringUtil.IsNullOrEmptyT(groupCode))
				{
					temp = getPlanByPortCodeOrGroupCode(groupCode, geneDate);
				}
			}
			this.planCode =  StringUtil.IsNullOrEmptyT(temp.get("planCode")) ? "" : temp.get("planCode");
			this.kmLevel = StringUtil.IsNullOrEmptyT(temp.get("kmLevel")) ? 0 : Integer.parseInt(temp.get("kmLevel"));
			planMap.put("planCode", planCode);
			planMap.put("kmLevel", kmLevel);
		} catch (Exception e) {
			throw e;
		} finally {
//			DbFun.closeStatementFinal(proc);
		}
		return planMap;
	}
	/**
	 * 获取组合的群组代码
	 * @param portCode
	 * @return
	 * @throws Exception
	 */
	protected String getGroupCode(String portCode) throws Exception {
		String groupCode = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		StringBuffer sb = new StringBuffer();
		sb.append(" SELECT C_GROUP_CODE ");
		sb.append(" FROM T_P_AB_GROUP ");
		sb.append(" WHERE c_group_code in (SELECT c_group_code ");
		sb.append("  FROM T_P_AB_GROUP_RELA ");
		sb.append("  where n_check_state = 1 ");
		sb.append("  and C_PORT_CODE = ?) ");
		sb.append(" and n_check_state = 1 ");
		try {
			pst = conn.prepareStatement(sb.toString());
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			if(rs.next()){
				groupCode = rs.getString("C_GROUP_CODE");
			}
		} catch (Exception e) {
			this.logger.error("获取群组代码出错！");
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return groupCode;
	}
	/**
	 * 查询产品信息的资产代码
	 * @return
	 * @throws Exception
	 */
	public String getAssCode()throws Exception{
		String assCode = "";
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sqlString = "select A.C_ASS_CODE,A.C_DAT_CODE,A.C_DV_PORT_CODE from T_P_AB_PORT A where A.c_port_code = ? ";
			pst = conn.prepareStatement(sqlString);
			pst.setString(1, this.portCode);
			rs = pst.executeQuery();
			if(rs.next()){
				assCode = rs.getString(1);
				this.assCode = assCode;
				this.assType = rs.getString(2);
				this.dvPortCode = rs.getString(3);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		if(isParallelPort() && usePortCode())
		{
			assCode = this.portCode;
			this.assCode = this.portCode;
		}
		return assCode;
	}
	

	/**
	 * 判断是否是并行组合
	 * @return
	 * @throws Exception 
	 */
	protected boolean isParallelPort() throws Exception
	{
		boolean res = false;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String sqlString = "select A.C_DV_ZHBZ from T_P_AB_PORT_PD A where A.c_port_code = ? and N_CHECK_STATE = 1 ";
			pst = conn.prepareStatement(sqlString);
			ResultSetMetaData metaData = pst.getMetaData();
			int columnCount = metaData.getColumnCount();//兼容低版本，没有该字段
			for (int i = 1; i <= columnCount; i++) {
				if("C_DV_ZHBZ".equalsIgnoreCase(metaData.getColumnName(i)))
				{
					pst.setString(1, this.portCode);
					rs = pst.executeQuery();
					if(rs.next()){
						if("ZHBZ_PARA".equalsIgnoreCase(rs.getString(i)))
						{
							res = true;
						}
					}
					break;
				}
			}
			
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return res;
	}
	
	/**
	 * 是否取组合代码
	 * @param portCode2 
	 * @return
	 * @throws Exception 
	 */
	protected boolean usePortCode() throws Exception
	{
		AdmPortActParams paras = new AdmPortActParams(this.portCode, new Date());
		paras.setDbConn(conn);
		paras.initActParams();
		String value = paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_BXZHFSCPDMLX);
		if("ERSEND_PORT_CODE".equalsIgnoreCase(value))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * 查询资产净值成本，资产净值，估值增值；用于计算成本占净值比例，市值占净值比例
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getZCJZ()throws Exception{
		Map<String, Object> ZCJZ = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuilder sqlString = new StringBuilder();
			sqlString.append(" select A.N_PORT_COST, A.N_PORT_MV, A.N_PORT_IV from T_R_FR_ASTSTAT A ");
			sqlString.append(" where A.c_port_code = ? and A.D_ASTSTAT = to_date(?, 'yyyy-MM-dd')");
			sqlString.append(" and A.C_KEY_CODE = 'ZCJZ' and A.C_NAV_TYPE = 'TOTAL' AND INSTR(C_KM_CODE, '_') = 0 ");//wlx 20171127 BUG181621嘉实基金-电子对账核对后-比例不一致,分级产品的资产净值KEYCODE也是ZCJZ 所以加上KMCODE筛选母级产品资产净值
			pst = conn.prepareStatement(sqlString.toString());
			pst.setString(1, this.portCode);
			pst.setString(2, geneDate);
			rs = pst.executeQuery();
			if(rs.next()){
				this.N_PORT_COST = rs.getDouble(1);
				this.N_PORT_MV = rs.getDouble(2);
				this.N_PORT_IV = rs.getDouble(3);
				ZCJZ.put("N_PORT_COST", this.N_PORT_COST);
				ZCJZ.put("N_PORT_MV", this.N_PORT_MV);
				ZCJZ.put("N_PORT_IV", this.N_PORT_IV);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return ZCJZ;
	}
	/**
	 * 查询实收资本数量
	 * @return
	 * @throws Exception
	 */
	public Map<String, Object> getSSZB()throws Exception{
		Map<String, Object> SSZB = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuilder sqlString = new StringBuilder();
			sqlString.append(" select A.N_AMOUNT,A.N_PORT_COST from T_R_FR_ASTSTAT A ");
			sqlString.append(" where A.c_port_code = ? and A.D_ASTSTAT = to_date(?, 'yyyy-MM-dd')");
			sqlString.append(" and A.C_KEY_CODE = 'SSZB' and A.C_NAV_TYPE = 'TOTAL' AND INSTR(C_KM_CODE, '_') = 0 ");//wlx 20171127 BUG181621嘉实基金-电子对账核对后-比例不一致，分级产品的实收资本KEYCODE也是SSZB 所以加上KMCODE筛选母级产品实收资本
			pst = conn.prepareStatement(sqlString.toString());
			pst.setString(1, this.portCode);
			pst.setString(2, geneDate);
			rs = pst.executeQuery();
			if(rs.next()){
				this.N_AMOUNT = rs.getDouble(1);
				this.N_SSZB_COST = rs.getDouble(2);
				SSZB.put("N_AMOUNT", this.N_AMOUNT);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return SSZB;
	}
	/**
	 * 查询组合对账模式，估值表发送模式
	 * @return
	 * @throws Exception
	 */
	 public Map<String, Object> getDZPara()throws Exception{
         Map<String, Object> DZPara = new HashMap<String, Object>();
         PreparedStatement pst = null;
         ResultSet rs = null;
         try {
                 //BUG213734【中银国际】_电子对账_估值表对账数据取不到估增
                 //当组合的关联机构取不到参数时，取上级机构的设置
//	        	 StringBuilder sqlString = new StringBuilder();
//	 			 sqlString.append(" select A.C_GZB_MODE, A.C_DZ_MODE ,A.C_KM_NAME_LENGTH  from T_D_ER_RELA A ");
//	 			 sqlString.append(" where A.c_ass_code = ? or c_tgh_code in ");
//	 			 sqlString.append(" (select distinct c_rela_code from T_P_AB_PORT_RELA where c_port_code = ? and C_RELA_TYPE = 'RELA_ORG')");
//	 			 pst = conn.prepareStatement(sqlString.toString());
                 pst = conn.prepareStatement(getDZParaSql());
                 pst.setString(1, portCode);
                 pst.setString(2, portCode);
                 rs = pst.executeQuery();
                 if(rs.next()){
                         this.C_GZB_MODE = rs.getString(1);
                         this.C_DZ_MODE = rs.getString(2);
                         this.C_KM_NAME_LENGTH=rs.getString(3);
                         DZPara.put("C_GZB_MODE", this.C_GZB_MODE);
                         DZPara.put("C_DZ_MODE", this.C_DZ_MODE);
                         DZPara.put("C_KM_NAME_LENGTH", this.C_KM_NAME_LENGTH);
                 }
         } catch (Exception e) {
                 throw e;
         } finally {
                 DbFun.closeResultSetFinal(rs);
                 DbFun.closeStatementFinal(pst);
         }
         return DZPara;
	 }
	
	 /**
     * BUG213734【中银国际】_电子对账_估值表对账数据取不到估增
     * 当组合的关联机构取不到参数时，取上级机构的设置
     * @return
     */
    public String getDZParaSql()
    {
            StringBuffer sb = new StringBuffer();
            sb.append(" select A.C_GZB_MODE, A.C_DZ_MODE ,A.C_KM_NAME_LENGTH ");
            sb.append(" from T_D_ER_RELA A, ");
            sb.append(" ( ");
            sb.append(" SELECT C_ORG_CODE,level AS ORG_LEVEL  FROM T_P_BI_ORG ");
            sb.append(" start with C_ORG_CODE in(select distinct c_rela_code  from T_P_AB_PORT_RELA where c_port_code = ? and C_RELA_TYPE = 'RELA_ORG') ");
            sb.append(" connect by prior C_ORG_CODE_P =  C_ORG_CODE order by ORG_LEVEL asc ");
            sb.append(" ) B ");
            sb.append(" where A.c_ass_code = ? or ");
            sb.append(" ( c_tgh_code = B.C_ORG_CODE and (B.ORG_LEVEL = 1 or A.C_HAS_BRANCH = '1') ) ");
            sb.append(" order by B.ORG_LEVEL asc ");
            return sb.toString();
    }
	
	/**
	 * 插入生成记录
	 * @param fileType
	 * @param rptType
	 * @throws Exception
	 */
	protected void geneErInfo(String fileType, String rptType) throws Exception{
		PreparedStatement pst = null;
		try {
			//STORY73476【鹏华基金】并行组合电子对账需求 添加组合代码
			StringBuilder builder = new StringBuilder();
			builder.append("insert into T_D_ER_INFO (C_IDEN, C_SN, C_DV_ER_WAY, D_DATE, ");
			builder.append(" C_UPDATE_BY, C_CHECK_BY, N_CHECK_STATE, C_STATE, C_RPT_TYPE, "); 
			builder.append(" C_FILE_TYPE, C_ASS_CODE,C_PORT_CODE, C_CHECK_TIME, C_UPDATE_TIME ) "); 
			builder.append(" select SEQU_D_ER_INFO.Nextval, ?, 'FORWARD', to_date(?, 'yyyy-MM-dd'), ?, ?, "); 
			builder.append(" to_number(case when a.n_check = 0 and a.n_user = 0 then 1 else 0 end) as N_CHECK_STATE, "); 
			builder.append(" 'ER_SEND', ?, ?, ?,?, "); 
			builder.append(" TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS'), "); 
			builder.append(" TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS') "); 
			builder.append(" from (select n_check, n_user from V_S_FUN where c_fun_code = 'dzBbInfo') a ");

			pst = conn.prepareStatement(builder.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.geneDate);
			pst.setString(3, userCode);
			pst.setString(4, userCode);
			pst.setString(5, rptType);
			pst.setString(6, fileType);
			pst.setString(7, this.assCode);
			pst.setString(8, this.portCode);
			
			pst.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	protected int getKMLevel(String c_KM_Code){
		int level = 0;
		if(c_KM_Code != null && c_KM_Code.contains(".")){
			level = c_KM_Code.split("\\.").length;
		}
		return level;
	}
	
	protected boolean executeSpecial(PreparedStatement pst, ResultSet rs, int dataType, int headIndex) throws Exception{
		return true;
	}
	
	/**
	 * 科目代码转换
	 * @param kmCode
	 * @return
	 */
	protected String KMCodeTransfer(String kmCode) {
		String transferKmCode = kmCode;
		if(kmCode != null){
			if(DZMS_GHMS_Z.equalsIgnoreCase(this.C_DZ_MODE)){
				String kmAry[] = kmCode.split("\\.", -1);
				if(kmAry.length == 3){
					if(kmAry[2].length() == 6){
						String spilt = kmAry[2].substring(kmAry[2].length() - 2, kmAry[2].length());
						transferKmCode = kmAry[0] + "." + spilt;
					}else if(kmAry[2].length() != 2){
						String spilt = "";
						if(kmAry[2].length() > 2){
							spilt = kmAry[2].substring(kmAry[2].length() - 2, kmAry[2].length());
						}else{
							for(int i = 0; i < 2 - kmAry[2].length(); i++){
								spilt += "0";
							}
							spilt += kmAry[2];
						}
						transferKmCode = kmAry[0] + "." + kmAry[1] + "." + spilt;
					}
				}
			}
		}
		return transferKmCode;
	}
	
	/**
	 * 科目级别计算
	 * @param kmCode
	 * @param kmLevel
	 * @return
	 */
	protected String KMLevelCount(String kmCode, String kmLevel) {
		String kmLevelCount = kmLevel;
		if(kmCode != null){
			if(DZMS_GHMS_Z.equalsIgnoreCase(this.C_DZ_MODE)){
				String kmAry[] = kmCode.split("\\.", -1);
				if(kmAry.length == 3){
					if(kmAry[2].length() == 6){
						kmLevelCount = "2";
					}
				}
			}
		}
		return kmLevelCount;
	}
	
	/**
	 * 科目上级科目计算
	 * @param kmCode
	 * @return
	 */
	protected String KMCodeParent(String kmCode) {
		String kmCode_P = kmCode;
		if(kmCode != null){
			if(DZMS_GHMS_Z.equalsIgnoreCase(this.C_DZ_MODE)){
				String kmAry[] = kmCode.split("\\.", -1);
				if(kmAry.length == 3){
					if(kmAry[2].length() == 6){
						kmCode_P = kmAry[0] ;
					}
				}
			}
		}
		return kmCode_P;
	}
	
	protected String transferReportType(String reportCode) {
		String reportType = "";
		if("03".equalsIgnoreCase(reportCode)){
			 reportType = "REPORT_MONTH";
		}else if("04".equalsIgnoreCase(reportCode)){
			 reportType = "REPORT_SEASON";
		}else if("05".equalsIgnoreCase(reportCode)){
			 reportType = "REPORT_HALF_YEAR";
		}else if("06".equalsIgnoreCase(reportCode)){
			 reportType = "REPORT_YEAR";
		}else{
			reportType = reportCode;
		}
		return reportType;
	}
	
	protected void geneErInfoHisAndInst(String C_FILE_TYPE,String C_RPT_TYPE){
		//记录更新历史记录
		if (StringUtil.IsNullOrEmptyT(fsn)) {
			return ;
		}
		if (null == C_FILE_TYPE) {
			C_FILE_TYPE = "";
		}
		if (null == C_RPT_TYPE) {
			C_RPT_TYPE = "";
		}
		ErStepState erStepState = new ErStepState();
		erStepState.setC_SN(this.fsn);
		erStepState.setC_FILE_TYPE(C_FILE_TYPE);
		erStepState.setC_RPT_TYPE(C_RPT_TYPE);
		erStepState.setC_STATE("ER_SEND");
		erStepState.setC_STEP_DATE(YssFun.formatDatetime(new Date()));
		erStepState.setD_DATE(this.geneDate);
		erStepState.setC_ASS_CODE(this.assCode);
		erStepState.setErrInfo("生成电子对账报文");
		ErStepStateService erStepService = new ErStepStateService();
		erStepService.insertPojo(erStepState);		
	}
	
	protected ElecPerRela getPerRelaByCode(String c_ZB_CODE){
		return this.getPerRelaByCode(c_ZB_CODE, null, null);
	}
	
	protected ElecPerRela getPerRelaByCode(String c_ZB_CODE,String c_ZB_Name,String c_DZ_CODE){
		IElecPerRelaService perRelaService = YssServiceFactory.getInstance().createService(IElecPerRelaService.class);
		BasePojo pojo = null;
		if (StringUtil.IsNullOrEmptyT(c_ZB_Name)||StringUtil.IsNullOrEmptyT(C_DZ_MODE)) {
			pojo = perRelaService.getPerRelaByCode(c_ZB_CODE);
		}else {
			pojo = perRelaService.getPerRelaByCodeAndName(c_ZB_CODE, c_ZB_Name, c_DZ_CODE);
		}
		if (pojo != null && pojo instanceof ElecPerRela) {
			return (ElecPerRela)pojo;
		}
		return null;
	}
	
	protected HashMap<String, ElecPerRela> getPerRelaByPortAndDZCode(String c_PORT_CODE,String c_DZ_CODE){
		IElecPerRelaService perRelaService = YssServiceFactory.getInstance().createService(IElecPerRelaService.class);
		//BUG211964电子对账个性指标设置取值逻辑不对
//		HashMap<String, Object> paraMap = new HashMap<String, Object>();
//		paraMap.put("ARRAY_C_PORT_CODE", c_PORT_CODE);
//		paraMap.put("ARRAY_C_DZ_CODE", c_DZ_CODE);
//		paraMap.put("dataClass", "ElecPerRela");
//		paraMap.put("N_CHECK_STATE", "SearchAudit");
//		QueryRes res = perRelaService.queryByCondition(paraMap);
//		List<BasePojo> dataList = res.getDataList();
//		HashMap<String, ElecPerRela> elecPerRelaMap = new HashMap<String, ElecPerRela>();
//		for (BasePojo pojo : dataList) {
//			if (pojo instanceof ElecPerRela) {
//				ElecPerRela elePerRela = (ElecPerRela)pojo;
//				elecPerRelaMap.put(elePerRela.getC_ZB_CODE(), elePerRela);
//			}
//		}
//		return elecPerRelaMap;
		return perRelaService.getPerRelaByPortAndDZCode(c_PORT_CODE, c_DZ_CODE);
	}
}
