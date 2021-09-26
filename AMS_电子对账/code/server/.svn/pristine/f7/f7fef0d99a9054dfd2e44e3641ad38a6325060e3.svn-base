package com.yss.uco.elecreco.er.autostate.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BEN_RECORD.DoingType;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.uco.elecreco.er.erbbinfo.dao.ErBbInfoSqlBuilder;
import com.yss.uco.elecreco.er.erresult.pojo.ErResult;
import com.yss.uco.elecreco.er.erresult.pojo.ErResultQuery;
import com.yss.uco.elecreco.er.erresult.service.IErResultService;
import com.yss.uco.elecreco.support.bean.AutoState;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.service.IElecRelaService;
import com.yss.uco.elecreco.support.util.ElecrecoCondtionCons;
import com.yss.uco.elecreco.support.util.FileTypeEnum;

public class AutoStateDao extends GeneralDao {
	AutoStateSqlBuilder autoStateSqlBuilder = new AutoStateSqlBuilder();
	public AutoStateDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public void sendAutoMessage(String status, String fsn, String fileType,
			String cAssCode) {
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = autoStateSqlBuilder.updateState();
			pst = conn.prepareStatement(sql);
			pst.setString(1, status);
			pst.setString(2, fsn);
			pst.setString(3, fileType);
			//STORY73476【鹏华基金】并行组合电子对账需求
//			pst.setString(4, cAssCode);
			pst.executeUpdate();
			this.closeStatementFinal(pst);
			
//			if(!"ER_ACCECPED".equals(status) && !"ER_IDENTICAL".equals(status)){//若该反馈结果不是最终状态，则不必处理之后的流程
//				return;
//			}
//			
//			//查询数据的自动化流程发送的流程ID，任务ID
//			sql = autoStateSqlBuilder.getSameProcess();
//			pst = conn.prepareStatement(sql);
//			pst.setString(1, fsn);
//			pst.setString(2, fileType);
//			pst.setString(3, cAssCode);
//			rs = pst.executeQuery();
//			String state = "0";//状态0对账一致；1 对账不一致；2有未反馈结果
//			String processId = "";
//			String taskId = "";
//			boolean isExist = false;
//			while (rs.next()) {
//				if("ER_ACCECPED".equals(rs.getString("C_STATE")) && !"1".equals(state) && !"2".equals(state)){
//					state = "0";
//				}else if("ER_IDENTICAL".equals(rs.getString("C_STATE")) && !"2".equals(state)){
//					state = "1";
//				}else {
//					state = "2";
//				}
//				isExist = true;
//				processId = rs.getString("c_process_id");
//				taskId = rs.getString("c_task_id");
//			}
//			//发送消息通知流程引擎
//			if(isExist){// 该数据是通过自动化流程发送的
//				if("0".equals(state)|| "1".equals(state)){//状态是对账一致或对账不一致时通知
//					IAutomaticTaskHandleService handleService = YssServiceFactory.getInstance().createService(IAutomaticTaskHandleService.class);
//					AutoTaskHandleVo handleVo = new AutoTaskHandleVo();
//					handleVo.setProcInstId(processId);
//					handleVo.setTaskInstId(taskId);
//					if("0".equals(state)){
//						handleVo.setBusiExeResult("000000");
//						handleVo.setWait(false);
//					}else{
//						handleVo.setBusiExeResult("100000");
//						handleVo.setWait(true);
//					}
//					handleService.handTask(handleVo);
//				}
//			}
		} catch (Exception e) {
			logger.error("电子对账结果反馈流程引擎出错", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	}
	/**
	 * 
	 * @param conditionMap
	 * @return
	 */
	public List<BEN_RECORD> getSendResult(Map<String, String> conditionMap) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BEN_RECORD> recordList = new ArrayList<BEN_RECORD>();
		try {
			conn = this.loadNewConnection();
			
			String ARRAY_C_PORT_CODE = conditionMap.get("ARRAY_C_PORT_CODE");
			String ARRAY_C_DZ_CODE = conditionMap.get("ARRAY_C_DZ_CODE");
			String C_START_DATE = conditionMap.get("C_START_DATE");
//			String C_END_DATE = conditionMap.get("C_END_DATE");
			String execOperCode = conditionMap.get("C_OPER_CODE");
			String processId = conditionMap.get("C_PROCESS_ID");
			String taskId = conditionMap.get("C_TASK_ID");
			
			StringBuilder builder = new StringBuilder();
			builder.append("SELECT A.* FROM T_D_ER_AUTOSTATE A ");
			builder.append(" WHERE C_PROCESS_ID = ? AND C_PORT_CODE IN(SELECT * FROM TABLE(?)) AND C_FILE_TYPE IN(SELECT * FROM TABLE(?))");
			
			pst = conn.prepareStatement(builder.toString());
			pst.setString(1, processId);
			pst.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(ARRAY_C_PORT_CODE, conn));
			pst.setArray(3, OraDbTool.newInstance().sqlOverLongCondition(ARRAY_C_DZ_CODE, conn));
			rs = pst.executeQuery();
			
			BEN_RECORD record = null;
			ResultSetTools rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			while (rs.next()) {
				AutoState autoState = (AutoState) setResultSet(rsTools, rs, AutoState.class);
				record = new BEN_RECORD();
				record.init(autoState.getC_port_code(), autoState.getC_file_type(), YssFun.toDate(C_START_DATE));
				record.BeginLog();
				record.setC_Fun_Code("");
				record.setC_Dispatch_ID(execOperCode);
				record.setProcessInstanceId(processId);
				record.setTaskInstanceId(taskId);
				
				if("ER_ACCECPED".equalsIgnoreCase(autoState.getC_state())){//对账一致
					record.EndLog_Success("对账一致");
				}else if("ER_IDENTICAL".equalsIgnoreCase(autoState.getC_state())){//对账不一致
					record.EndLog_Warn("对账不一致");
				}else if("ER_SENDED_FAIL".equalsIgnoreCase(autoState.getC_state())){//发送失败
					record.EndLog_Fail("发送失败");
				}else if("ER_SENDED_SECCUSS".equalsIgnoreCase(autoState.getC_state()) && "1031".equalsIgnoreCase(autoState.getC_file_type())){
					record.EndLog_Success("发送成功");
				}else{
					record.setC_Mes_Text("对账处理中");
					record.setC_Doing_Type(DoingType.Doing);
				}
				recordList.add(record);
			}
			
		} catch (Exception e) {
			logger.error("电子对账结果反馈流程引擎出错", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	
		return recordList;
	}
	/**
	 * 获取对账结果
	 * @param conditionMap
	 * @return
	 */
	public List<BEN_RECORD> getDZResult(Map<String, String> conditionMap) {

		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<BEN_RECORD> recordList = new ArrayList<BEN_RECORD>();
		try {
			conn = this.loadNewConnection();
			
			String ARRAY_C_PORT_CODE = conditionMap.get("ARRAY_C_PORT_CODE");
			String ARRAY_C_DZ_CODE = conditionMap.get("ARRAY_C_DZ_CODE");
			String C_START_DATE = conditionMap.get("C_START_DATE");
//			String C_END_DATE = conditionMap.get("C_END_DATE");
			String execOperCode = conditionMap.get("C_OPER_CODE");
			String processId = conditionMap.get("C_PROCESS_ID");
			String taskId = conditionMap.get("C_TASK_ID");
			String dzNewResult = conditionMap.get("dzNewResult");//对账最新依据
			StringBuilder builder = new StringBuilder();
			if("PORT_MV_NEW".equalsIgnoreCase(dzNewResult) && "1011".equalsIgnoreCase(ARRAY_C_DZ_CODE)){//资产净值市值最新
				builder.append("SELECT A.C_STATE1 AS C_STATE, A.* FROM (");
				builder.append(" SELECT O.C_IDEN,O.C_SN,O.C_DV_ER_WAY,O.C_ASS_CODE,");
				builder.append("        O.C_FILE_TYPE,O.C_RPT_TYPE," ); 
				builder.append("        O.N_CHECK_STATE,O.C_UPDATE_BY,O.C_UPDATE_TIME,");
				builder.append("        O.C_CHECK_BY,O.D_DATE,O.C_CHECK_TIME,O.C_ERR_INFO," );
				builder.append("        O.C_DV_RESULT,O.C_SUMMARY,");
				builder.append("  DECODE(C.N_PORT_MV,D.N_PORT_MV,O.C_STATE,'ER_SENDED_SECCUSS') AS C_STATE1,DECODE(C.N_PORT_MV, D.N_PORT_MV, 1, 2) AS lev,");
				builder.append("  O.C_PORT_CODE,DECODE(DECODE(C.N_PORT_MV,D.N_PORT_MV,O.C_STATE,'ER_SENDED_SECCUSS'),'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4)AS N_ORDER FROM T_D_ER_INFO O ");
				////STORY73476【鹏华基金】并行组合电子对账需求
//				builder.append(" JOIN (SELECT C_ASS_CODE,C_PORT_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) B ON O.C_ASS_CODE = B.C_ASS_CODE ");
				builder.append(" JOIN (SELECT Z.N_PORT_MV,Z.C_ASS_CODE,Z.C_SN,Z.D_START_DATE FROM T_D_ER_GZ Z WHERE Z.C_KM_CODE = '0015') C ON O.C_ASS_CODE = C.C_ASS_CODE AND O.C_SN=C.C_SN AND O.D_DATE = C.D_START_DATE");
				builder.append(" JOIN (SELECT T.N_PORT_MV FROM T_R_FR_ASTSTAT T WHERE T.D_ASTSTAT =? AND T.C_PORT_CODE = ? AND T.C_KEY_CODE='ZCJZ') D ON 1 = 1");
				builder.append(" WHERE O.C_PORT_CODE = ? AND O.D_DATE = ? ");
				builder.append(" AND O.C_FILE_TYPE = ?)A ORDER BY lev ASC, c_update_time desc , N_ORDER ASC");
				pst = conn.prepareStatement(builder.toString());
				//STORY73476【鹏华基金】并行组合电子对账需求
				int index = 1;
				//pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setDate(index++, YssFun.toSqlDate(C_START_DATE));
				pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setDate(index++, YssFun.toSqlDate(C_START_DATE));
				pst.setString(index++,ARRAY_C_DZ_CODE);
			}else if ("PORT_MV_NEW_SC".equalsIgnoreCase(dzNewResult)) {
				builder.append(" SELECT C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,C_UPDATE_BY,  ");
				builder.append(" C_UPDATE_TIME,C_CHECK_BY,D_DATE,C_CHECK_TIME,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_SPLIT_CODE,C_TGH_CODE,");
				builder.append("C_PORT_CODE,C_CONFIRM_EXECUTE,N_ORDER FROM ( ");
				builder.append(" SELECT O.*,DECODE(O.C_STATE,'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4)AS N_ORDER FROM T_D_ER_INFO O ");
				builder.append(" LEFT  JOIN (SELECT T.N_PORT_MV ");
				builder.append(" FROM T_R_FR_ASTSTAT T ");
				builder.append(" WHERE T.D_ASTSTAT = ? ");
				builder.append(" AND T.C_PORT_CODE = ? ");
				builder.append(" AND T.C_KEY_CODE = 'ZCJZ') D ");
				builder.append(" ON 1 = 1 ");
				builder.append(" LEFT JOIN (SELECT Z.N_PORT_MV, Z.C_ASS_CODE, Z.C_SN, Z.D_START_DATE ");
				builder.append(" FROM T_D_ER_GZ Z ");
				builder.append(" WHERE Z.C_KM_CODE = '0015') C ");
				builder.append(" ON O.C_ASS_CODE = C.C_ASS_CODE ");
				builder.append(" AND O.C_SN = C.C_SN ");
				builder.append(" AND O.D_DATE = C.D_START_DATE ");
				builder.append(" WHERE O.C_PORT_CODE = ? and O.D_DATE = ? AND C.N_PORT_MV = D.N_PORT_MV");
				builder.append(" AND O.C_FILE_TYPE = ?) ORDER BY TO_NUMBER(C_IDEN) desc , N_ORDER ASC ");
				
				pst = conn.prepareStatement(builder.toString());
				pst.setDate(1, YssFun.toSqlDate(C_START_DATE));
				pst.setString(2, ARRAY_C_PORT_CODE);
				pst.setString(3, ARRAY_C_PORT_CODE);
				pst.setDate(4, YssFun.toSqlDate(C_START_DATE));
				pst.setString(5,ARRAY_C_DZ_CODE);
			} else{
				builder.append(" SELECT C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,C_UPDATE_BY,  ");
				builder.append(" C_UPDATE_TIME,C_CHECK_BY,D_DATE,C_CHECK_TIME,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_SPLIT_CODE,C_TGH_CODE,");
				builder.append("C_PORT_CODE,C_CONFIRM_EXECUTE,N_ORDER FROM ( ");
				//STORY73476【鹏华基金】并行组合电子对账需求
				builder.append(" SELECT O.*,DECODE(O.C_STATE,'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4)AS N_ORDER FROM T_D_ER_INFO O ");
				builder.append(" WHERE O.C_PORT_CODE = ? and O.D_DATE = ? ");
				builder.append(" AND O.C_FILE_TYPE = ?) ORDER BY c_update_time desc , N_ORDER ASC");
				pst = conn.prepareStatement(builder.toString());
				pst.setString(1, ARRAY_C_PORT_CODE);
				pst.setDate(2, YssFun.toSqlDate(C_START_DATE));
				pst.setString(3,ARRAY_C_DZ_CODE);
			}
			
			
			rs = pst.executeQuery();
			Map<String, String> dataMap = new HashMap<String, String>();
			BEN_RECORD record = null;
			ResultSetTools rsTools = new ResultSetTools(new DefaultDBNameResolver(), new ErBbInfoSqlBuilder());
			while (rs.next()) {
				
				ErBbInfo erBbInfo = (ErBbInfo) setResultSet(rsTools, rs, ErBbInfo.class);
				String key = erBbInfo.getC_PORT_CODE()+"_"+erBbInfo.getC_FILE_TYPE()+"_" +erBbInfo.getC_RPT_TYPE()+"_"+C_START_DATE;
				if(dataMap.containsKey(key)){//有可能同一个产品同一个对账类型发送过多次
					continue;
				}else{
					dataMap.put(key, key);
				}
				record = new BEN_RECORD();
				record.init(erBbInfo.getC_PORT_CODE(), erBbInfo.getC_FILE_TYPE(), YssFun.toDate(C_START_DATE));
				record.BeginLog();
				record.setC_Fun_Code("");
				record.setC_Dispatch_ID(execOperCode);
				record.setProcessInstanceId(processId);
				record.setTaskInstanceId(taskId);
				record.setC_Item_Code(erBbInfo.getC_FILE_TYPE());
				record.setC_Item_Name(FileTypeEnum.getValueByKey(erBbInfo.getC_FILE_TYPE()));
				record.setC_Port_Code(erBbInfo.getC_PORT_CODE());
				
				if("ER_ACCECPED".equalsIgnoreCase(erBbInfo.getC_STATE())){//对账一致
					record.EndLog_Success("对账一致");
					record.setC_Extra_Info(erBbInfo.getC_STATE());
				}else if("ER_IDENTICAL".equalsIgnoreCase(erBbInfo.getC_STATE())){//对账不一致
					record.EndLog_Warn("对账不一致");
					record.setC_Extra_Info(erBbInfo.getC_STATE());
				}else if("ER_SENDED_FAIL".equalsIgnoreCase(erBbInfo.getC_STATE())){//发送失败
					record.EndLog_Fail("发送失败");
				}else if("ER_SENDED_SECCUSS".equalsIgnoreCase(erBbInfo.getC_STATE()) && "1031".equalsIgnoreCase(erBbInfo.getC_FILE_TYPE())){
					record.EndLog_Success("发送成功");
				}else{
					record.setC_Mes_Text("对账处理中");
					record.setC_Doing_Type(DoingType.Doing);
				}
				recordList.add(record);
			}
			dataMap.clear();
			
		} catch (Exception e) {
			logger.error("电子对账结果反馈流程引擎出错", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
	
		return recordList;
	}

	private HashMap<String, Object> createParam(ErBbInfo erBbInfo,String bfCodes) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("dataClass", "ErResultQuery");
		map.put("SHOW_TYPE", "CY_DATA");
		map.put("C_SN", erBbInfo.getC_SN());
		map.put("C_ASS_CODE", erBbInfo.getC_ASS_CODE());
		map.put("C_CHECK_FLAG", erBbInfo.getC_FILE_TYPE());
		map.put("D_DATE", erBbInfo.getD_DATE());
		map.put("C_RPT_TYPE", erBbInfo.getC_RPT_TYPE());
		map.put("ARRAY_C_B_CODE", bfCodes);
		return map;
	}
	

	/**
	 * 获取不一致的数据
	 * @param erBbInfo
	 * @param bfCodes
	 * @return Map{key:电子对账指标代码，value:估值指标项代码}
	 * @throws Exception
	 */
	public Map<String, String> getDiffData(ErBbInfo erBbInfo,String bfCodes, String checkCondition) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();
		if(!StringUtil.IsNullOrEmptyT(bfCodes))
		{
			//通过指标项查询对账指标代码
			IElecRelaService relaService = YssServiceFactory.getInstance().createService(IElecRelaService.class);
			Map<String, String> codes = relaService.getZbCodeByKeyCode(bfCodes);
			if(codes != null && codes.size() > 0)
			{
				Set<String> zbCodes = codes.keySet();
				IErResultService erResultService = YssServiceFactory.getInstance().createService(IErResultService.class);
				HashMap<String, Object> paraMap = createParam(erBbInfo,StringUtil.join(zbCodes, ","));
				QueryRes queryRes = erResultService.queryByCondition(paraMap);
				if(queryRes != null && queryRes.getDataList() != null && queryRes.getDataList().size() == 0)//托管行不返回，默认对账一致
				{
					return map;
				}else//
				{
					for (BasePojo pojo : queryRes.getDataList()) {
						ErResult result = (ErResult) pojo;
						/**
						 * 判断对账结果
						 */
						String res = result.getC_RESULT();
						/// by weijj 20151231 有些银行返回汉字  bug120751 
						if(StringUtil.IsNullOrEmptyT(checkCondition)){
							if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
								map.put(result.getC_B_KM_CODE(), codes.get(result.getC_B_KM_CODE()));
							}
						}else{
							ErResultQuery erResultQuery = (ErResultQuery) pojo;
							List<String> conditionList = StringUtil.split(checkCondition, ",");
							StringBuffer sb = new StringBuffer();
							for (String condition : conditionList) {
								//科目名称
								if(condition.equalsIgnoreCase(ElecrecoCondtionCons.KEY_MC)){
									String b_mc = erResultQuery.getC_B_KM_NAME();
									String d_mc = erResultQuery.getC_D_KM_NAME();
									if(StringUtil.IsNullOrEmpty(b_mc) || !b_mc.equalsIgnoreCase(d_mc)){
										sb.append("N");
									}
								}
								//数量
								else if(condition.equalsIgnoreCase(ElecrecoCondtionCons.KEY_SL)){
									BigDecimal b_sl = erResultQuery.getN_B_SL();
									BigDecimal d_sl = erResultQuery.getN_D_SL();
									if(null == b_sl || b_sl.compareTo(d_sl) != 0){
										sb.append("N");
									}
								}
								//成本
								else if(condition.equalsIgnoreCase(ElecrecoCondtionCons.KEY_CB)){
									BigDecimal b_cb = erResultQuery.getN_B_JEO();
									BigDecimal d_cb = erResultQuery.getN_D_JEO();
									if(null == b_cb || b_cb.compareTo(d_cb) != 0){
										sb.append("N");
									}
								}
								//市值
								else if(condition.equalsIgnoreCase(ElecrecoCondtionCons.KEY_SZ)){
									BigDecimal b_je = erResultQuery.getN_B_JE();
									BigDecimal d_je = erResultQuery.getN_D_JE();
									if(null == b_je || b_je.compareTo(d_je) != 0){
										sb.append("N");
									}
								}
								//估值增值
								else if(condition.equalsIgnoreCase(ElecrecoCondtionCons.KEY_GZ)){
									BigDecimal b_jet = erResultQuery.getN_B_JET();
									BigDecimal d_jet = erResultQuery.getN_D_JET();
									if(null == b_jet || b_jet.compareTo(d_jet) != 0){
										sb.append("N");
									}
								}
							}
							
							if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
								if(sb != null && sb.toString().contains("N")){
									map.put(result.getC_B_KM_CODE(), codes.get(result.getC_B_KM_CODE()));
								}
							}
						}
					}
					return map;
				}
			}else{//没有生成到对应指标
				return map;
			}
		}else
		{
			return map;
		}
	}
	
	/**
	 * 获取对账结果,通过特殊指标判断对账结果
	 * @param conditionMap
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public List<ErBbInfo> getDZResultInfo(Map<String, String> conditionMap) {
		
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		List<ErBbInfo> recordList = new ArrayList<ErBbInfo>();
		StringBuilder builder = new StringBuilder();
		try {
			conn = this.loadNewConnection();
			String ARRAY_C_PORT_CODE = conditionMap.get("ARRAY_C_PORT_CODE");
			String ARRAY_C_DZ_CODE = conditionMap.get("ARRAY_C_DZ_CODE");
			String C_START_DATE = conditionMap.get("C_START_DATE");
//			String C_END_DATE = conditionMap.get("C_END_DATE");
			String dzNewResult = conditionMap.get("dzNewResult");//对账最新依据
			if("PORT_MV_NEW".equalsIgnoreCase(dzNewResult) && "1011".equalsIgnoreCase(ARRAY_C_DZ_CODE)){//资产净值市值最新
				builder.append("SELECT A.C_STATE1 AS C_STATE, A.* FROM (");
				builder.append(" SELECT O.C_IDEN,O.C_SN,O.C_DV_ER_WAY,O.C_ASS_CODE,");
				builder.append("        O.C_FILE_TYPE,O.C_RPT_TYPE," ); 
				builder.append("        O.N_CHECK_STATE,O.C_UPDATE_BY,O.C_UPDATE_TIME,");
				builder.append("        O.C_CHECK_BY,O.D_DATE,O.C_CHECK_TIME,O.C_ERR_INFO," );
				builder.append("        O.C_DV_RESULT,O.C_SUMMARY,");
				builder.append("  DECODE(C.N_PORT_MV,D.N_PORT_MV,O.C_STATE,'ER_SENDED_SECCUSS') AS C_STATE1,DECODE(C.N_PORT_MV, D.N_PORT_MV, 1, 2) AS lev,");
				builder.append("  O.C_PORT_CODE,DECODE(DECODE(C.N_PORT_MV,D.N_PORT_MV,O.C_STATE,'ER_SENDED_SECCUSS'),'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4)AS N_ORDER FROM T_D_ER_INFO O ");
				////STORY73476【鹏华基金】并行组合电子对账需求
				//builder.append(" JOIN (SELECT C_ASS_CODE,C_PORT_CODE FROM T_P_AB_PORT WHERE C_PORT_CODE = ?) B ON O.C_ASS_CODE = B.C_ASS_CODE ");
				builder.append(" JOIN (SELECT Z.N_PORT_MV,Z.C_ASS_CODE,Z.C_SN,Z.D_START_DATE FROM T_D_ER_GZ Z WHERE Z.C_KM_CODE = '0015') C ON O.C_ASS_CODE = C.C_ASS_CODE AND O.C_SN=C.C_SN AND O.D_DATE = C.D_START_DATE");
				builder.append(" JOIN (SELECT T.N_PORT_MV FROM T_R_FR_ASTSTAT T WHERE T.D_ASTSTAT =? AND T.C_PORT_CODE = ? AND T.C_KEY_CODE='ZCJZ') D ON 1 = 1");
				builder.append(" WHERE O.D_DATE = ? ");
				builder.append(" AND O.C_PORT_CODE = ? ");
				builder.append(" AND O.C_FILE_TYPE = ?)A ORDER BY lev asc,c_update_time desc , N_ORDER ASC");
				pst = conn.prepareStatement(builder.toString());
				//STORY73476【鹏华基金】并行组合电子对账需求
				int index = 1;
				//pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setDate(index++, YssFun.toSqlDate(C_START_DATE));
				pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setDate(index++, YssFun.toSqlDate(C_START_DATE));
				pst.setString(index++, ARRAY_C_PORT_CODE);
				pst.setString(index++,ARRAY_C_DZ_CODE);
			}else if ("PORT_MV_NEW_SC".equalsIgnoreCase(dzNewResult)) {
				builder.append(" SELECT C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,C_UPDATE_BY,  ");
				builder.append(" C_UPDATE_TIME,C_CHECK_BY,D_DATE,C_CHECK_TIME,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_SPLIT_CODE,C_TGH_CODE,");
				builder.append("C_PORT_CODE,C_CONFIRM_EXECUTE,N_ORDER FROM ( ");
				builder.append(" SELECT O.*,DECODE(O.C_STATE,'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4)AS N_ORDER FROM T_D_ER_INFO O ");
				builder.append(" LEFT  JOIN (SELECT T.N_PORT_MV ");
				builder.append(" FROM T_R_FR_ASTSTAT T ");
				builder.append(" WHERE T.D_ASTSTAT = ? ");
				builder.append(" AND T.C_PORT_CODE = ? ");
				builder.append(" AND T.C_KEY_CODE = 'ZCJZ') D ");
				builder.append(" ON 1 = 1 ");
				builder.append(" LEFT JOIN (SELECT Z.N_PORT_MV, Z.C_ASS_CODE, Z.C_SN, Z.D_START_DATE ");
				builder.append(" FROM T_D_ER_GZ Z ");
				builder.append(" WHERE Z.C_KM_CODE = '0015') C ");
				builder.append(" ON O.C_ASS_CODE = C.C_ASS_CODE ");
				builder.append(" AND O.C_SN = C.C_SN ");
				builder.append(" AND O.D_DATE = C.D_START_DATE ");
				builder.append(" WHERE O.C_PORT_CODE = ? and O.D_DATE = ? AND C.N_PORT_MV = D.N_PORT_MV");
				builder.append(" AND O.C_FILE_TYPE = ?) ORDER BY TO_NUMBER(C_IDEN) desc , N_ORDER ASC ");
				
				pst = conn.prepareStatement(builder.toString());
				pst.setDate(1, YssFun.toSqlDate(C_START_DATE));
				pst.setString(2, ARRAY_C_PORT_CODE);
				pst.setString(3, ARRAY_C_PORT_CODE);
				pst.setDate(4, YssFun.toSqlDate(C_START_DATE));
				pst.setString(5,ARRAY_C_DZ_CODE);
			}else{
				builder.append(" SELECT C_IDEN,C_SN,C_DV_ER_WAY,C_ASS_CODE,C_FILE_TYPE,C_RPT_TYPE,C_STATE,N_CHECK_STATE,C_UPDATE_BY,  ");
				builder.append(" C_UPDATE_TIME,C_CHECK_BY,D_DATE,C_CHECK_TIME,C_ERR_INFO,C_DV_RESULT,C_SUMMARY,C_SPLIT_CODE,C_TGH_CODE,");
				builder.append("C_PORT_CODE,C_CONFIRM_EXECUTE,N_ORDER FROM ( ");
				//STORY73476【鹏华基金】并行组合电子对账需求
				builder.append(" SELECT O.*,DECODE(O.C_STATE,'ER_ACCECPED',1,'ER_IDENTICAL',2,'ER_SENDED_SECCUSS',3,4) AS N_ORDER FROM T_D_ER_INFO O ");
				builder.append(" WHERE O.C_PORT_CODE = ? and O.D_DATE = ? ");
				builder.append(" AND O.C_FILE_TYPE = ?) ORDER BY c_update_time desc , N_ORDER ASC");
				pst = conn.prepareStatement(builder.toString());
				pst.setString(1, ARRAY_C_PORT_CODE);
				pst.setDate(2, YssFun.toSqlDate(C_START_DATE));
				pst.setString(3,ARRAY_C_DZ_CODE);
			}
			
			
			rs = pst.executeQuery();
			Map<String, String> dataMap = new HashMap<String, String>();
			ResultSetTools rsTools = new ResultSetTools(new DefaultDBNameResolver(), new ErBbInfoSqlBuilder());
			while (rs.next()) {
				ErBbInfo erBbInfo = (ErBbInfo) setResultSet(rsTools, rs, ErBbInfo.class);
				String key = erBbInfo.getC_PORT_CODE()+"_"+erBbInfo.getC_FILE_TYPE()+"_" +erBbInfo.getC_RPT_TYPE()+"_"+C_START_DATE;
				if(dataMap.containsKey(key)){//有可能同一个产品同一个对账类型发送过多次
					continue;
				}else{
					dataMap.put(key, key);
				}
				recordList.add(erBbInfo);
			}
			dataMap.clear();
			
		} catch (Exception e) {
			logger.error("查询电子对账结果失败:" + builder.toString() + ":" + conditionMap.toString(), e);
			throw new DataAccessException(e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return recordList;
	}

}
