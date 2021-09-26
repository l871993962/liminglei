package com.yss.uco.elecreco.er.generate.gzb.split;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.generate.gzb.GeneGZBDataService;
import com.yss.uco.elecreco.er.generate.split.dao.gz.GzGenerateSplitDao;
import com.yss.uco.elecreco.er.generate.split.dao.gz.GzGenerateSplitSqlBuilder;
import com.yss.uco.elecreco.er.generate.split.service.IGenerateSplitService;
import com.yss.uco.elecreco.er.generate.split.service.impl.GzGenerateSplitService;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.util.StringUtil;

public class SplitGeneGZBDataService extends GeneGZBDataService{
	protected IGenerateSplitService splitService;
	protected ErSplitRela rela = null;
	protected String splitCode = "";
	protected String tghCode = "";
	protected String relaId = "";

	public SplitGeneGZBDataService() {
		super();
		this.splitService = new GzGenerateSplitService(new GzGenerateSplitDao(new GzGenerateSplitSqlBuilder()));
	}


	/**
	 * 需要单独生成一个子账时，需要调用该方法，传入托管行代码和拆分代码，托管行代码不能为空
	 * @param splitCode
	 * @param tghCode
	 */
	public SplitGeneGZBDataService(String splitCode, String tghCode) {
		super();
		init(tghCode, splitCode);
	}


	/**
	 * 需要单独生成一个子账时，需要调用该方法，传入托管行代码和拆分代码，托管行代码不能为空
	 * @param tghCode
	 * @param splitCode
	 */
	public void init(String tghCode,String splitCode)
	{
		this.tghCode = tghCode;
		this.splitCode = splitCode;
	}
	
	public IGenerateSplitService getSplitService() {
		return splitService;
	}

	public void setSplitService(IGenerateSplitService splitService) {
		this.splitService = splitService;
	}


	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		//BUG224969北京-【新华资产】电子对账【高】（多托管行拆分完成后，生成估值表时显示没数据产生）
		int allTotal = 0;
		map.put("result", "");
		map.put("size", "0");
		List<ErKmb> list = splitService.getUnSplitRuleDetailKm(portCode, geneDate, conn);
		if(list != null && list.size() > 0)
		{
			this.Pro_GetPlan();//获取核算方案代码及科目体系
			
			Map<String,String> gzDetailKm = gzDetailKmData();
			
			StringBuffer detail  = new StringBuffer("以下科目没有设置拆分规则：<br>");
			StringBuffer detailBody  = new StringBuffer("");
			for(ErKmb km : list)
			{
				if(gzDetailKm.containsKey(km.getC_KM_CODE())){
					detailBody.append(km.getC_KM_CODE()).append("(").append(km.getC_KM_NAME()).append(")").append("<br>");
				}
			}
			
			if(!StringUtil.IsNullOrEmptyT(detailBody.toString())){
				detail.append(detailBody);
				map.put("result", "SplitRuleFail");
				map.put("resultDetail", detail.toString());
				return map;
			}
		}
		
		if(!StringUtil.IsNullOrEmptyT(tghCode))//只生成一个子账,需要传进来托管行代码和拆分代码
		{
			ErSplitRela rela = getErSplitRela(portCode,geneDate,tghCode,splitCode,conn);
			if(rela == null)
			{
				map.put("result", "SplitRelaFail");
				map.put("resultDetail", "没有设置多托管行拆分设置！");
				return map;
			}
			allTotal = 1;
			initParams(rela);
			//BUG224969北京-【新华资产】电子对账【高】（多托管行拆分完成后，生成估值表时显示没数据产生）
			Map<String, Object> sigleResult = super.geneElecData();
			addResultInfo(map, sigleResult);
		}else//生成所有子账
		{
			List<ErSplitRela> relas = getErSplitRelas(portCode,geneDate,conn);
			allTotal = relas.size();
			if(relas == null || relas.size() == 0)
			{
				map.put("result", "SplitRelaFail");
				map.put("resultDetail", "没有设置多托管行拆分设置！");
				return map;
			}
			for(ErSplitRela rela : relas)
			{
				initParams(rela);
				Map<String, Object> sigleResult = super.geneElecData();
				addResultInfo(map, sigleResult);
			}
		}
		//BUG224969北京-【新华资产】电子对账【高】（多托管行拆分完成后，生成估值表时显示没数据产生）
		StringBuffer sb = new StringBuffer();
		String s = map.get("size").toString();
		int size = Integer.parseInt(s);
		sb.append("需要生成").append(allTotal).append("份数据，")
		.append("实际生成").append(size).append("份数据。");
		map.put("resultDetail", sb.toString());
		return map;
	}
	/**
	 * BUG224969北京-【新华资产】电子对账【高】（多托管行拆分完成后，生成估值表时显示没数据产生）
	 * @param map
	 * @param sigleResult
	 */
	public void addResultInfo(Map<String, Object> map,Map<String, Object> sigleResult)
	{
		String allResult = map.get("result").toString();
		String sizeStr = map.get("size").toString();
		int size = Integer.parseInt(sizeStr);
		String result = "";
		if(sigleResult != null){
			result = sigleResult.get("result").toString();
		}
		if (result.startsWith("DZ")) {
			if(!StringUtil.IsNullOrEmptyT(allResult))
			{
				allResult += ",";
			}
			map.put("result",allResult+result);
			size++;
		} 
		map.put("size",size+"");
	}
	
	public ErSplitRela getErSplitRela(String portCode, String geneDate,
			String tghCode, String splitCode, Connection conn) throws Exception {
		return splitService.getErSplitRela(portCode, geneDate, tghCode, splitCode, conn);
	}

	public void initParams(ErSplitRela rela)
	{
		this.rela = rela;
		this.splitCode = rela.getC_SPLIT_CODE();
		this.tghCode = rela.getC_TGH_CODE();
		this.relaId = rela.getId();
	}
	
	public List<ErSplitRela> getErSplitRelas(String portCode,String date,Connection conn) throws Exception
	{
		List<ErSplitRela> list = this.splitService.getErSplitRelas(portCode, date, conn);
		if(list == null)
		{
			return new ArrayList<ErSplitRela>();
		}
		return list;
	}
	
	protected Map<String,String> gzDetailKmData() throws Exception {
		Map<String,String> detailKm = new HashMap<String, String>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			pst = conn.prepareStatement(gzDetailSql());
			int n_kmLevel = this.kmLevel;
			while(n_kmLevel > 0){
				int index = 1;
				pst.setInt(index++, n_kmLevel);
				pst.setInt(index++, n_kmLevel);
				pst.setString(index++, portCode);
				pst.setString(index++, geneDate);
				pst.setString(index++, planCode);
				pst.setInt(index++, n_kmLevel);
//				pst.setString(index++, this.relaId);
				pst.setString(index++, portCode);
				pst.setString(index++, geneDate);
//				pst.setString(index++, this.relaId);
				pst.setString(index++, planCode);
				pst.setString(index++, portCode);
				pst.setString(index++, geneDate);
				rs = pst.executeQuery();
				while (rs.next()) {
					String key = rs.getString("C_KM_CODE");
					if(!detailKm.containsKey(key)){
						detailKm.put(key, "");
					}
				}
				n_kmLevel--;
				DbFun.closeResultSetFinal(rs);
				pst.clearBatch();
			}
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return detailKm;
	}

	@Override
	protected int geneGZBDetailData() throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(getGZBDetailSql());
			int n_kmLevel = this.kmLevel;
			while(n_kmLevel > 0){
				int index = 1;
				pst.setInt(index++, n_kmLevel);
				pst.setInt(index++, n_kmLevel);
				pst.setString(index++, this.relaId);
				pst.setString(index++, portCode);
				pst.setString(index++, geneDate);
				pst.setString(index++, planCode);
				pst.setInt(index++, n_kmLevel);
				rs = pst.executeQuery();
				while (rs.next()) {
					boolean isAdd = setPojo(insertPst, rs, DETAIL_TYPE,elecPerRelaMap);
					if(isAdd){
						insertPst.addBatch();
						if(++count % batchSize == 0) {
							insertPst.executeBatch();
						}
					}
				}
				insertPst.executeBatch();
				n_kmLevel--;
				DbFun.closeResultSetFinal(rs);
				pst.clearBatch();
			}
			insertPst.close();
		} catch (Exception e) {
			throw e;
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst, insertPst);
		}
		return count;
	}
	
	protected String gzDetailSql() {
		StringBuilder builder = new StringBuilder();
		builder.append("  select ");
		builder.append("  C_KM_CODE ");
		builder.append("  from ( ");
		builder.append("  select ");
		builder.append("  A.C_KM_CODE as C_KM_CODE ");
		builder.append("  FROM (select ");
		builder.append("  PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE, ?) as C_KM_CODE, ");
		builder.append("  PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE_T, ?) as C_KM_CODE_T ");
		builder.append("  from T_R_FR_ASTSTAT AST ");
		builder.append("  where ");
		builder.append("  C_PORT_CODE = ? and D_ASTSTAT = to_date(?, 'yyyy-MM-dd') ");
		builder.append("  AND C_NAV_TYPE not in ('TOTAL', 'TOTAL_ALL') ");
		builder.append("  AND C_KM_CODE IS NOT NULL AND C_KM_CODE <> ' ') A ");
		builder.append("  JOIN (SELECT C_KM_CODE, C_KM_NAME, N_DETAIL FROM T_F_SC_KM C WHERE C.C_PLAN_CODE = ? AND C.N_CHECK_STATE = 1 and c.n_order = 0 ) B ");
		builder.append("  ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE_T, ?) = B.C_KM_CODE ");
		builder.append("  union ");
		builder.append("  SELECT ");
		builder.append("  A.C_KM_CODE ");
		builder.append("  FROM T_D_AI_STOCK A ");
		builder.append("  WHERE substr(a.c_year_month, -2, 2) <> '00' ");
		builder.append("  AND A.C_PORT_CODE = ? ");
		builder.append("  AND A.D_STOCK = to_date(?, 'yyyy-MM-dd') ");
		builder.append("  AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY' ");
		builder.append("  union ");
		builder.append("  SELECT   C.C_KM_CODE ");
		builder.append("  FROM T_D_AI_STOCK A ");
		builder.append("  JOIN T_F_SC_KM C ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3)  = C.C_KM_CODE AND C.C_PLAN_CODE = ? and c.n_order = 0 ");
		builder.append("  WHERE substr(a.c_year_month, -2, 2) <> '00' ");
		builder.append("  AND A.C_PORT_CODE = ? ");
		builder.append("  AND A.D_STOCK = to_date(?, 'yyyy-MM-dd') ");
		builder.append("  AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY' group by C.C_KM_CODE, C.C_KM_NAME ");
		builder.append("  ) ");

		return builder.toString();
	}

	@Override
	protected String getGZBDetailSql() {
		StringBuilder builder = new StringBuilder();

		builder.append(" select \n"); 
		builder.append("    		      A.C_KM_CODE as C_KM_CODE,");
		builder.append("                  MAX(CASE WHEN (INSTR(B.C_KM_NAME, '>') = 0 AND INSTR(B.C_KM_NAME, '<') = 0)\n");
		builder.append("                           THEN B.C_KM_NAME else A.C_KM_NAME end) AS C_KM_NAME, \n");
		builder.append("                  SUM(A.N_VA_PRICE) AS N_VA_PRICE, \n"); 
		builder.append("                  SUM(A.N_AMOUNT) AS N_AMOUNT, \n"); 
		builder.append("  				  SUM(A.N_ORIG_COST * N_WAY) as N_ORIG_COST, ");
		builder.append("                  SUM(A.N_PORT_COST * N_WAY) AS N_PORT_COST, \n"); 
		builder.append("  				  SUM(A.N_ORIG_MV * N_WAY) as N_ORIG_MV, ");
		builder.append("                  SUM(A.N_PORT_MV * N_WAY) AS N_PORT_MV, \n"); 
		builder.append("  				  SUM(A.N_ORIG_IV * N_WAY) as N_ORIG_IV, ");
		builder.append("                  SUM(A.N_PORT_IV * N_WAY) AS N_PORT_IV, \n"); 
		builder.append("                  MAX(NVL(B.N_DETAIL, 1)) AS N_DETAIL, MAX(C_NAV_TYPE) AS C_NAV_TYPE, \n");
		builder.append(" C_PORT_CODE_CLS ");
		builder.append("             	FROM (select N_AMOUNT,N_ORIG_COST, N_PORT_COST, N_WAY, \n"); 
		//STORY #48435 嘉实基金电子对账生产沪港深组合的估值表中的行情与估值表展示不一至 
		//参数“估值表中行情列是否按原币显示”值为是，行情按照原来的显示，否则行情=round(行情*汇率,2)
		if (isYbhq){ 
			builder.append("                          N_VA_PRICE,");
		}else{
			//BUG210434【建信基金】电子对账行情与估值表展示不一致延伸, 港股通科目时并且不按原币行情显示时，行情=round(行情*汇率,2)
			builder.append("              CASE WHEN SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, ' ') + 1, LENGTH(C_KM_CODE)) = 'HG' THEN ");
			builder.append("                  ROUND(N_VA_PRICE * N_VA_RATE, 2) ");
			builder.append("               WHEN SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, ' ') + 1, LENGTH(C_KM_CODE)) = 'HS' THEN ");
			builder.append("                  ROUND(N_VA_PRICE * N_VA_RATE, 2) ");
			builder.append("               ELSE "); 
			builder.append("                  N_VA_PRICE END AS N_VA_PRICE,");

//			builder.append("                          ROUND(N_VA_PRICE * N_VA_RATE,2) as N_VA_PRICE,");
		}
		builder.append("                          N_ORIG_MV,N_PORT_MV, N_ORIG_IV,N_PORT_IV, \n"); 
		builder.append("                          PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE, ?) as C_KM_CODE, C_KM_NAME, \n"); 
		builder.append("                          PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(C_KM_CODE_T, ?) as C_KM_CODE_T,C_NAV_TYPE, \n"); 
		builder.append(" C_PORT_CLS_CODE AS C_PORT_CODE_CLS ");
		builder.append("                      from T_R_FR_ASTSTAT AST \n"); 
		builder.append("                       where \n"); 
		builder.append("                       exists (SELECT 1 FROM T_D_ER_SPLIT_RULE SR where SR.C_KM_CODE = AST.C_KM_CODE and SR.C_IDEN_RELA = ?) \n"); 
		builder.append("                       and C_PORT_CODE = ? and D_ASTSTAT = to_date(?, 'yyyy-MM-dd') \n"); 
		builder.append("                          AND C_NAV_TYPE not in ('TOTAL', 'TOTAL_ALL') \n"); 
		builder.append("                          AND C_KM_CODE IS NOT NULL AND C_KM_CODE <> ' ') A \n"); 
		builder.append("             		  JOIN (SELECT C_KM_CODE, C_KM_NAME, N_DETAIL FROM T_F_SC_KM C WHERE C.C_PLAN_CODE = ? AND C.N_CHECK_STATE = 1 and c.n_order = 0 ) B \n"); 
		builder.append("              		  ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE_T, ?) = B.C_KM_CODE \n"); 
		builder.append("            	GROUP BY A.C_KM_CODE,C_PORT_CODE_CLS order by A.C_KM_CODE \n");

		return builder.toString();
	}

	/**
	 * 屏蔽掉汇总项和指标项
	 */
	@Override
	protected int geneGZBNavTypeData() throws Exception {
		return 0;
	}

	@Override
	protected int geneGZBYGZData(String gzDetail) throws Exception {
		PreparedStatement pst = null;
		PreparedStatement insertPst = null;
		ResultSet rs = null;
		int count = 0;
		try {
			insertPst = conn.prepareStatement(getInsertSql());
			pst = conn.prepareStatement(this.getGZBYGZSql2(gzDetail));
			int index = 1;
			pst.setString(index++, this.relaId);
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			pst.setString(index++, this.relaId);
			pst.setString(index++, planCode);
			pst.setString(index++, portCode);
			pst.setString(index++, geneDate);
			rs = pst.executeQuery();
			//BUG211964电子对账个性指标设置取值逻辑不对
			//减少查询次数
			//HashMap<String, ElecPerRela> elecPerRelaMap = getPerRelaByPortAndDZCode(portCode, "1011");
			while (rs.next()) {
				boolean isAdd = setPojo(insertPst, rs, YGZ_TYPE, elecPerRelaMap);
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
	 * 只保留明细科目
	 */
	@Override
	protected String getGZBYGZSql2(String gzDetail) throws Exception {
		StringBuilder builder = new StringBuilder();

		builder.append("select\n" );
		builder.append("  C_KM_CODE, C_KM_NAME, 0 as N_VA_PRICE,\n" ); 
		builder.append("  0 as N_AMOUNT,N_ORIG_MONEY as N_ORIG_COST, N_PORT_MONEY as N_PORT_COST,\n" ); 
		builder.append("  N_ORIG_MONEY as N_ORIG_MV,N_PORT_MONEY as N_PORT_MV, 0 as N_ORIG_IV,0 as N_PORT_IV, N_DETAIL,C_PORT_CLS_CODE AS C_PORT_CODE_CLS \n" ); 
		builder.append("from (SELECT A.N_ORIG_MONEY * B.N_FUND_WAY AS N_ORIG_MONEY,A.N_PORT_MONEY * B.N_FUND_WAY AS N_PORT_MONEY,\n" ); 
		builder.append("             A.C_KM_CODE, A.C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 1 as N_DETAIL, ");
		} else {
			builder.append(" 0 as N_DETAIL, ");
		}
		builder.append("      A.C_PORT_CLS_CODE " );
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" ); 
		builder.append("			and exists (SELECT 1 FROM T_D_ER_SPLIT_RULE SR where SR.C_KM_CODE = A.C_KM_CODE and SR.C_IDEN_RELA = ? )");
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY'\n" ); 
		builder.append("      union all\n" ); 
		builder.append("      SELECT sum(A.N_ORIG_MONEY * B.N_FUND_WAY) AS N_ORIG_MONEY,sum(A.N_PORT_MONEY * B.N_FUND_WAY) AS N_PORT_MONEY,\n" ); 
		builder.append("             C.C_KM_CODE, C.C_KM_NAME, " ); 
		if (gzDetail != null && gzDetail.equalsIgnoreCase("1")) {
			builder.append(" 0 as N_DETAIL, ");
		} else {
			builder.append(" 1 as N_DETAIL, ");
		}
		builder.append("      A.C_PORT_CLS_CODE " );
		builder.append("      FROM T_D_AI_STOCK A\n" ); 
		builder.append("      LEFT JOIN (SELECT N_FUND_WAY,C_DAI_CODE FROM T_S_DAI_ITEM B\n" ); 
		builder.append("                  WHERE B.C_DAI_CODE LIKE'%GYBD%') B ON B.C_DAI_CODE = A.C_DAI_CODE\n" ); 
		builder.append("      JOIN T_F_SC_KM C ON PKG_FUN_GETLEVEL_KM.GETLEVEL_KM(A.C_KM_CODE, 3)  = C.C_KM_CODE AND C.C_PLAN_CODE = ? and c.n_order = 0 \n" ); 
		builder.append("      WHERE substr(a.c_year_month, -2, 2) <> '00'\n" ); 
		builder.append("			and exists (SELECT 1 FROM T_D_ER_SPLIT_RULE SR where SR.C_KM_CODE = A.C_KM_CODE and SR.C_IDEN_RELA = ? )");
		builder.append("            AND A.C_PORT_CODE = ?\n" ); 
		builder.append("            AND A.D_STOCK = to_date(?, 'yyyy-MM-dd')\n" ); 
		builder.append("            AND A.C_DAI_CODE LIKE'%GYBD%' and A.C_DV_KM_CLS <> 'KC_SY' group by C.C_KM_CODE, C.C_KM_NAME ,A.C_PORT_CLS_CODE  \n" ); 
		builder.append(" )");

		return builder.toString();
	}

	/**
	 * 添加托管行和拆分代码两个字段
	 */
	@Override
	protected void geneErInfo(String fileType, String rptType) throws Exception {
		PreparedStatement pst = null;
		try {
			//STORY73476【鹏华基金】并行组合电子对账需求 添加组合代码
			StringBuilder builder = new StringBuilder();
			builder.append("insert into T_D_ER_INFO (C_IDEN, C_SN, C_DV_ER_WAY, D_DATE, ");
			builder.append(" C_UPDATE_BY, C_CHECK_BY, N_CHECK_STATE, C_STATE, C_RPT_TYPE, "); 
			builder.append(" C_FILE_TYPE, C_ASS_CODE, C_CHECK_TIME, C_UPDATE_TIME,C_TGH_CODE,C_SPLIT_CODE, C_PORT_CODE ) "); 
			builder.append(" select SEQU_D_ER_INFO.Nextval, ?, 'FORWARD', to_date(?, 'yyyy-MM-dd'), ?, ?, "); 
			builder.append(" to_number(case when a.n_check = 0 and a.n_user = 0 then 1 else 0 end) as N_CHECK_STATE, "); 
			builder.append(" 'ER_SEND', ?, ?, ?, "); 
			builder.append(" TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS'), "); 
			builder.append(" TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS'), "); 
			builder.append(" ?,?,? "); 
			builder.append(" from (select n_check, n_user from V_S_FUN where c_fun_code = 'dzBbInfo') a ");

			pst = conn.prepareStatement(builder.toString());
			pst.setString(1, this.fsn);
			pst.setString(2, this.geneDate);
			pst.setString(3, userCode);
			pst.setString(4, userCode);
			pst.setString(5, rptType);
			pst.setString(6, fileType);
			pst.setString(7, this.assCode);
			pst.setString(8, this.tghCode);
			pst.setString(9, this.splitCode);
			pst.setString(10, this.portCode);
			pst.executeUpdate();
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 查询组合对账模式，估值表发送模式
	 * @return
	 * @throws Exception
	 */
	@Override
	public Map<String, Object> getDZPara()throws Exception{
		Map<String, Object> DZPara = new HashMap<String, Object>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			StringBuilder sqlString = new StringBuilder();
			sqlString.append(" SELECT A.C_GZB_MODE, A.C_DZ_MODE FROM T_D_ER_RELA A join ( ");
			sqlString.append(" SELECT C_ORG_CODE,level as org_level FROM T_P_BI_ORG R ");
			sqlString.append(" start with R.C_ORG_CODE = ? ");
			sqlString.append(" connect by prior R.C_ORG_CODE_P = R.C_ORG_CODE ");
			sqlString.append(" ) R on A.C_TGH_CODE = R.C_ORG_CODE ");
			sqlString.append(" where (A.C_HAS_BRANCH = '1' or R.org_level = 1) ");
			sqlString.append(" order by org_level asc ");
			pst = conn.prepareStatement(sqlString.toString());
			pst.setString(1, this.tghCode);
			rs = pst.executeQuery();
			if(rs.next()){
				this.C_GZB_MODE = rs.getString(1);
				this.C_DZ_MODE = rs.getString(2);
				DZPara.put("C_GZB_MODE", this.C_GZB_MODE);
				DZPara.put("C_DZ_MODE", this.C_DZ_MODE);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return DZPara;
	}
	
}
