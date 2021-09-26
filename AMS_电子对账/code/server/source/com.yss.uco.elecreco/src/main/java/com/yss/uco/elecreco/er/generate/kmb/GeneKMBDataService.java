package com.yss.uco.elecreco.er.generate.kmb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.generate.AdmPortActParams;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.service.LicCompanyName;
import com.yss.uco.elecreco.er.generate.util.KMTransUtil;
import com.yss.uco.elecreco.support.util.ErDspParamCodeEnum;
import com.yss.uco.elecreco.support.util.UcoDspParamCodeEnum;

public class GeneKMBDataService extends GeneElecDataService{

	protected static final String C_FILE_TYPE = "1031";
	protected static final String C_RPT_TYPE = "01";
	/**
	 * 科目类别
	 */
	protected Map<String, String> KMClsMap = new HashMap<String, String>();
	/**
	 * 借贷方向
	 */
	protected Map<String, String> JDWayMap = new HashMap<String, String>();
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
	@Override
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	//派生了一个子类，修改逻辑时，注意拆分子类
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		int count = 0;
		try {
			//获取参数值
			AdmPortActParams paras = new AdmPortActParams(this.portCode, new Date());
			paras.setDbConn(conn);
			paras.initActParams();
			int transGZ = KMTransUtil.getGzCode(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_ZQDMZH), paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_JYQDZH));
			if(transGZ!=KMTransUtil.ZHGZ_BZH)
			{
				isTransKM = true;
				kmTransMap = KMTransUtil.getTransMap(conn, transGZ);
			}else
			{
				isTransKM = false;
			}
			this.getPortPara();
			this.createFsn();
			this.getAssCode();
			this.Pro_GetPlan();//BUG189287【加急】【招商基金】宁波银行电子对账的科目表通过深证通发送后，托管行反馈没收到
			this.getDZPara();//获取对账参数
			this.addKmClsMap();
			this.addJDWayMap();
			count = 0;
//			if(DZMS_GHMS_Z.equals(this.C_DZ_MODE)){
//				count += this.geneKMBData_ICBC_Z();
//			}else{
				count += this.geneKMBData();
//			}
			/**
			* STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。 添加转换规则Map
			* */
			transKMCode();
			
			/**
			 * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 数据insert之后
			 * 通过组合代码取到科目长度限制，若不为空对则对科目名称长度大于科目长度限制的科目名称进行截取
			 * @author cls
			 * @date 2018年8月13日
			 * */
			KMNameSub();
			trans1111KMCode();
			if(count > 0){
				this.geneErInfo(C_FILE_TYPE, C_RPT_TYPE);
				this.geneErInfoHisAndInst(C_FILE_TYPE, C_RPT_TYPE);//生成历史进行记录
				result = this.fsn;
				resultDetail = "电子对账科目表数据生成成功！";
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
			logger.error("生成科目表数据出错：" + e.getMessage(), e);
			throw e;
		}
		
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	/**
	 * 插入科目表T_D_ER_KM预处理sql语句
	 * @return
	 */
	protected String getInsertSql(){
		StringBuilder builder = new StringBuilder();
		builder.append("insert into T_D_ER_KM ");
		builder.append("(C_IDEN, C_SN, C_FILE_TYPE, C_RPT_TYPE, C_ASS_CODE, D_START_DATE, "); 
		builder.append("  D_END_DATE, D_DATE, C_DV_ER_WAY, C_KM_CODE, C_KM_NAME,C_KM_CODE_P, C_KM_LEVEL, "); 
		builder.append("  N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY) "); 
//		builder.append(" values(SEQU_D_ER_KM.NEXTVAL,'").append(this.fsn).append("','1031','01','");
//		builder.append(this.assCode).append("',");
//		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),");
//		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),");
//		builder.append("to_date('").append(this.geneDate).append("','yyyy-MM-dd'),'FORWARD',");
		builder.append(" values(SEQU_D_ER_KM.NEXTVAL,?,?,?,?,?,?,?,?,");
		builder.append("?,?,?,?,?,?,?)");
		return builder.toString();
		
	}
	/**
	 * BUG197123减少不必要的SQL硬解析比率，优化数据库执行效率
	 */
	@Override
	protected int setPojoHead(PreparedStatement pst, ResultSet rs, int dataType)
			throws Exception {
		int index = 0;
		pst.setString(++index, this.fsn);//报文序号
		pst.setString(++index, C_FILE_TYPE);//文件类型
		pst.setString(++index, C_RPT_TYPE);//报表类型
		pst.setString(++index, this.assCode);//资产代码
		pst.setDate(++index, YssFun.toSqlDate(this.geneDate));//开始日期
		pst.setDate(++index, YssFun.toSqlDate(this.geneDate));//结束日期
		pst.setDate(++index, YssFun.toSqlDate(this.geneDate));//结束日期
		pst.setString(++index, FORWORD);//对账方向
		return index;
	}
	/**
	 * 明细项生成
	 * @return
	 * @throws Exception
	 */
	protected int geneKMBData() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(getKMBSql());
			int index = 1;
			//BUG189287【加急】【招商基金】宁波银行电子对账的科目表通过深证通发送后，托管行反馈没收到
			
			pst.setString(index++, this.planCode);
			
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			
			pst.setString(index++, this.planCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				boolean isAdd = setPojo(insertPst, rs, 0,null);
				if(isAdd){
					insertPst.addBatch();
					if(++count % batchSize == 0) {
						insertPst.executeBatch();
					}
				}
			}
			insertPst.executeBatch();
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;
	}
	/**
	 * 科目表数据的数据源sql
	 * @return
	 */
	protected String getKMBSql(){
		StringBuilder builder = new StringBuilder();
		//BUG189287【加急】【招商基金】宁波银行电子对账的科目表通过深证通发送后，托管行反馈没收到
		builder.append("select  C_KM_CODE, C_KM_NAME, C_KM_CODE_P, \n");
		builder.append("        to_number(PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE)) as C_KM_LEVEL, \n"); 
		builder.append("        N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
		builder.append("       from (select A.C_KM_CODE, A.C_KM_NAME, A.C_KM_CODE_P, \n"); 
		builder.append("                     A.N_DETAIL, A.C_DV_KM_CLS, A.C_DV_JD_WAY \n"); 
		builder.append("               from T_F_SC_KM A \n"); 
		builder.append("              where a.c_plan_code = ? and a.n_check_state = 1 and a.c_km_code not like '%>' \n"); 
		builder.append("             union all \n"); 
		builder.append("             select distinct A.C_KM_CODE, A.C_KM_NAME, B.C_KM_CODE_P, \n"); 
		builder.append("                             B. N_DETAIL, B.C_DV_KM_CLS, B.C_DV_JD_WAY \n"); 
		builder.append(" from ( select a.C_KM_CODE, a.C_KM_NAME, a.C_KM_CODE_T,a.RANK from (SELECT a.C_KM_CODE, a.C_KM_NAME, a.C_KM_CODE_T, ROW_NUMBER() OVER(PARTITION BY a.C_KM_CODE ORDER BY C_KM_NAME DESC) RANK\n");
		builder.append("  from T_D_Ai_Stock a\n" );
		builder.append(" where a.C_Port_Code = ?\n"); 
		builder.append("   and a.D_Stock = To_DATE(?, 'yyyy-MM-dd')\n");
		builder.append("   ) a where rank =1 \n" ); 
		builder.append(" union\n"); 
		builder.append(" select a.C_KM_CODE, a.C_KM_NAME, a.C_KM_CODE_T,a.RANK from( ");
		builder.append(" select a.C_KM_CODE, a.C_KM_NAME, a.C_KM_CODE_T, ROW_NUMBER() OVER(PARTITION BY a.C_KM_CODE ORDER BY C_KM_NAME DESC) RANK\n"); 
		builder.append("  from T_D_AI_ACT_VAL a\n" ); 
		builder.append(" where a.C_Port_Code = ?\n" );
		builder.append("   and a.D_CHK_ACC = To_DATE(?, 'yyyy-MM-dd')\n" );
		builder.append("   and not exists (SELECT b.C_KM_CODE\n" );
		builder.append("          from T_D_Ai_Stock b\n" );
		builder.append("         where b.C_Port_Code = ?\n" );
		builder.append("           and b.D_Stock = To_DATE(?, 'yyyy-MM-dd')\n"); 
		builder.append("           and b.c_km_code = a.c_km_code)\n" );
		builder.append("   ) a where rank =1");
		builder.append(" ) a \n");
		builder.append(" join T_F_SC_KM b ");
		builder.append(" ON a.C_KM_CODE_T = b.C_KM_CODE");
		builder.append(" AND b.c_plan_code = ?");
		builder.append(" AND a.C_KM_CODE_T like '%>')");

		return builder.toString();
	}
	/**
	 * 明细项生成
	 * @return
	 * @throws Exception
	 */
//	protected int geneKMBData_ICBC_Z() throws Exception {
//		PreparedStatement pst = null;
//		PreparedStatement insertPst = null;
//		ResultSet rs = null;
//		int count = 0;
//		try {
//			insertPst = conn.prepareStatement(getInsertSql());
//			pst = conn.prepareStatement(getKMBSql_ICBC_Z());
//			int index = 1;
//			pst.setString(index++, portCode);
//			pst.setString(index++, geneDate);
//			pst.setString(index++, geneDate);
//			pst.setString(index++, portCode);
//			pst.setString(index++, geneDate);
//			pst.setString(index++, portCode);
//			pst.setString(index++, geneDate);
//			rs = pst.executeQuery();
//			while (rs.next()) {
//				boolean isAdd = setPojo(insertPst, rs, 0);
//				if(isAdd){
//					insertPst.addBatch();
//					if(++count % batchSize == 0) {
//						insertPst.executeBatch();
//					}
//				}
//			}
//			insertPst.executeBatch();
//			insertPst.close();
//		} catch (Exception e) {
//			throw e;
//		}finally{
//			DbFun.closeResultSetFinal(rs);
//			DbFun.closeStatementFinal(pst, insertPst);
//		}
//		return count;
//	}
	/**
	 * 科目表数据的数据源sql
	 * @return
	 */
//	protected String getKMBSql_ICBC_Z(){
//		StringBuilder builder = new StringBuilder();
//
//		builder.append("select  C_KM_CODE, C_KM_NAME, C_KM_CODE_P, \n");
//		builder.append("        C_KM_LEVEL, N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
//		builder.append("       from (select A.C_KM_CODE, A.C_KM_NAME, A.C_KM_CODE_P, \n"); 
//		builder.append("                    to_number(PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE)) as C_KM_LEVEL, \n"); 
//		builder.append("                     A.N_DETAIL, A.C_DV_KM_CLS, A.C_DV_JD_WAY \n"); 
//		builder.append("               from T_F_SC_KM A \n"); 
//		builder.append("               join (select C_PLAN_CODE \n"); 
//		builder.append("                      from T_E_EXEC_PLAN_RELA \n"); 
//		builder.append("                     where C_PLAN_Type = 'AO_LEVEL' \n"); 
//		builder.append("                       and n_check_state = 1 and C_PORT_CODE = ? \n"); 
//		builder.append("                       and d_begin <= to_date(?, 'yyyy-MM-dd') \n"); 
//		builder.append("                       and d_end >= to_date(?, 'yyyy-MM-dd')) b \n"); 
//		builder.append("                 on a.c_plan_code = b.c_plan_code \n"); 
//		builder.append("              where a.n_check_state = 1 and a.c_km_code not like '%>' \n"); 
//		builder.append("             union all \n"); 
//		builder.append("             select distinct  \n"); 
//		builder.append("					case when PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE) = 3 and length(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.')) = 6\n");
//		builder.append(" 					then\n" ); 
//		builder.append("   					PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE,'1') || '.' || GETLEFTNUM(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.'),1)||GETLEFTNUM(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.'),0)\n");
//		builder.append(" 					when PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE) = 3 and length(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.')) != 2\n");
//		builder.append(" 					then\n" );
//		builder.append("   					PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE,'2') || '.' || GETLEFTNUM(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.'),1)||GETLEFTNUM(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.'),0)\n"); 
//		builder.append(" 					else C_KM_CODE\n");
//		builder.append(" 				end as C_KM_CODE,\n");
//		builder.append(" 				C_KM_NAME,");
//		builder.append("					case when  PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE) = 3 and length(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.')) = 6\n");
//		builder.append(" 					then PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE,'1')\n" );
//		builder.append(" 					else C_KM_CODE_P\n" ); 
//		builder.append(" 				end as C_KM_CODE_P,");
//		builder.append("					case when  PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE) = 3 and length(STRSPLIT(STRSPLIT(C_KM_CODE,'1','.'),'1','.')) = 6\n" );
//		builder.append(" 				    then to_number(PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE)) - 1\n" ); 
//		builder.append(" 					else to_number(PKG_FUN_GETKM_LEVEL.GETKM_LEVEL(C_KM_CODE))\n" );
//		builder.append(" 				end as C_KM_LEVEL,\n");
//		builder.append("                N_DETAIL, C_DV_KM_CLS, C_DV_JD_WAY \n"); 
//		builder.append("               from (select a.C_KM_CODE, a.C_KM_NAME,a.C_KM_CODE_T, \n"); 
//		builder.append("                             b.C_KM_CODE_P, b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
//		builder.append("                        from T_D_Ai_Stock a \n"); 
//		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
//		builder.append("                       where a.C_Port_Code = ? \n"); 
//		builder.append("                         and a.D_Stock = To_DATE(?, 'yyyy-MM-dd') \n"); 
//		builder.append("                         and b.C_KM_CODE like '%>' \n"); 
//		builder.append("                      union \n"); 
//		builder.append("                      select a.C_KM_CODE,a.C_KM_NAME, a.C_KM_CODE_T, \n"); 
//		builder.append("                             b.C_KM_CODE_P,b.N_DETAIL, b.C_DV_KM_CLS, b.C_DV_JD_WAY \n"); 
//		builder.append("                        from T_D_AI_ACT_VAL a \n"); 
//		builder.append("                        join T_F_SC_KM b ON a.C_KM_CODE_T = b.C_KM_CODE \n"); 
//		builder.append("                       where a.C_Port_Code = ? \n"); 
//		builder.append("                         and a.D_CHK_ACC = To_DATE(?, 'yyyy-MM-dd') \n"); 
//		builder.append("                         and b.C_KM_CODE like '%>') \n"); 
//		builder.append("           )\n");
//
//		return builder.toString();
//	}
	/**
	 * 
	 * C_KM_CODE,C_KM_NAME,C_KM_CODE_P,C_KM_LEVEL, N_DETAIL,C_DV_KM_CLS,C_DV_JD_WAY
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 拆分生成时重写此方法
	 * @param pst
	 * @param rs
	 * @throws SQLException
	 */
	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs, int dataType,HashMap<String, ElecPerRela> elecPerRelaMap) throws Exception {
		boolean isAdd = true;
		int headIndex = setPojoHead(pst, rs, dataType);
		int index = headIndex;
		ElecPerRela perRelaPojo = null;
		if(elecPerRelaMap != null){
			perRelaPojo = elecPerRelaMap.get(rs.getString("C_KM_CODE"));
			if (perRelaPojo != null && "0".equalsIgnoreCase(perRelaPojo.getC_SEND_MODE())) {
				return false;
			}
		}
		switch (dataType) {
		default:
			pst.setString(++index, KMCodeTransfer(rs.getString(1)));//科目代码
			pst.setString(++index, rs.getString(2));//科目名称
			pst.setString(++index, KMCodeParent(rs.getString(3)));//上级科目代码
			pst.setString(++index, KMLevelCount(rs.getString(1), rs.getString(4)));//科目级别
			//// wlx 20161228 BUG148608南方基金-工行电子对账科目表FACCTDETAIL字段值需调整
			if(DZMS_GHMS.equalsIgnoreCase(this.C_DZ_MODE)){//工行模式
				//BUG233524【易方达基金】【21.6】电子对账余额表损益类科目发送过去的明细科目托管行收不到  
				//将所有科目的是否明细科目字段都调整为非明细
//				String kmCode = rs.getString(1);
//				String kmAry[]= kmCode.split("\\.", -1);
//				if(kmAry.length == 4){//第四级科目为明细，否则都为非明细
//					pst.setInt(++index, 1);//是否明细科目 0非明细；1明细
//				}else{
					pst.setInt(++index, 0);//是否明细科目 0非明细；1明细
//				}
			}else{
				pst.setInt(++index, rs.getInt(5));//是否明细科目 0非明细；1明细
			}
			pst.setString(++index, KMClsMap.get(rs.getString(6))==null ? "6" : KMClsMap.get(rs.getString(6)));//科目类别
			pst.setString(++index, JDWayMap.get(rs.getString(7))==null ? "0" : JDWayMap.get(rs.getString(7)));//借贷方向
			isAdd = executeSpecial(pst, rs, dataType, headIndex);
			break;
		}
		return isAdd;
	}
	/**
	 * 添加科目类别映射
	 */
	protected void addKmClsMap(){
		KMClsMap.put("KC_ZC", "1");
		KMClsMap.put("KC_FZ", "2");
		KMClsMap.put("KC_GT", "3");
		KMClsMap.put("KC_QY", "4");
		KMClsMap.put("KC_SY", "5");
		KMClsMap.put("", "6");
	}
	/**
	 * 添加借贷方向映射
	 */
	protected void addJDWayMap() {
		JDWayMap.put("JD_J", "1");
		JDWayMap.put("JD_D", "-1");
		JDWayMap.put("", "0");
	}
	
	@Override
	protected boolean executeSpecial(PreparedStatement pst, ResultSet rs,
			int dataType, int headIndex) throws Exception {
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
				int level = this.getKMLevel(kmCode);
				if(level == 2 || level == 3){
					pst.setInt(headIndex + 5, 0);
				}
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
				pst.setString(headIndex + 1, c_km_code);
			}
		}
		return isAdd;
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
				selectBuilder.append("SELECT C_KM_CODE FROM T_D_ER_KM WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? ");
				StringBuilder updateBuilder = new StringBuilder();
				updateBuilder.append("UPDATE T_D_ER_KM SET C_KM_CODE = ? WHERE C_ASS_CODE = ? AND C_SN = ? AND D_START_DATE = ? AND C_KM_CODE = ? ");

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
	 * STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 数据insert之后
	 * 通过组合代码取到科目长度限制，若不为空对则对科目名称长度大于科目长度限制的科目名称进行截取
	 * 
	 * @author cls
	 * @date 2018年8月13日
	 */
	private void KMNameSub() {

		PreparedStatement pst = null;
		PreparedStatement updatePst = null;
		ResultSet rs = null;
		try {
			StringBuilder updateBuilder = new StringBuilder();
			if (!StringUtil.IsNullOrEmpty(C_KM_NAME_LENGTH)) {
				
				int kmLength = Integer.parseInt(C_KM_NAME_LENGTH);
				//获得负数
				int neKmLength = -kmLength;
				updateBuilder
						.append("  UPDATE T_D_ER_KM SET C_KM_NAME = SUBSTR(C_KM_NAME,"+ neKmLength + ") ")
						.append("  WHERE     C_SN = ?  AND  C_ASS_CODE = ? AND D_START_DATE = ? ")
						.append("  AND  LENGTH(C_KM_NAME) > " + kmLength);
				updatePst = conn.prepareStatement(updateBuilder.toString());
				int index = 1;
				updatePst.setString(index++, this.fsn);
				updatePst.setString(index++, this.assCode);
				updatePst.setDate(index++, YssFun.toSqlDate(geneDate));
				updatePst.execute();
			}


		} catch (Exception e) {
			logger.error("截取科目名称长度出错 ：" + e.getMessage(), e);
		} finally {
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
				String updateSql = " UPDATE T_D_ER_KM A SET A.C_KM_CODE = REGEXP_REPLACE(A.C_KM_CODE,'1111','1103',1,1) WHERE A.C_KM_CODE LIKE '1111%' AND A.C_ASS_CODE = ? AND A.C_SN = ? AND A.D_START_DATE = ? ";
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
		if(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111) != null && "1".equalsIgnoreCase(paras.getElecParamValue(ErDspParamCodeEnum.DZ_BB_DZDZ_TRANS1111))){
			trans1111KmCode = true;
		}else {
			trans1111KmCode = false;
		}
	}
}
