package com.yss.uco.elecreco.er.generate.kmb.split;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.generate.kmb.GeneKMBDataService;
import com.yss.uco.elecreco.er.generate.split.dao.km.KmGenerateSplitDao;
import com.yss.uco.elecreco.er.generate.split.dao.km.KmGenerateSplitSqlBuilder;
import com.yss.uco.elecreco.er.generate.split.service.IGenerateSplitService;
import com.yss.uco.elecreco.er.generate.split.service.impl.KmGenerateSplitService;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.util.StringUtil;

public class SplitGeneKMBDataService extends GeneKMBDataService {

	protected IGenerateSplitService splitService;
	protected ErSplitRela rela = null;
	protected String splitCode = "";
	protected String tghCode = "";
	protected String relaId = "";
	protected Map<String,String> rules = null;

	public SplitGeneKMBDataService() {
		super();
		this.splitService = new KmGenerateSplitService(new KmGenerateSplitDao(new KmGenerateSplitSqlBuilder()));
	}


	/**
	 * 需要单独生成一个子账时，需要调用该方法，传入托管行代码和拆分代码，托管行代码不能为空
	 * @param splitCode
	 * @param tghCode
	 */
	public SplitGeneKMBDataService(String splitCode, String tghCode) {
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
	
	public ErSplitRela getErSplitRela(String portCode, String geneDate,
			String tghCode, String splitCode, Connection conn) throws Exception {
		return splitService.getErSplitRela(portCode, geneDate, tghCode, splitCode, conn);
	}

	public void initParams(ErSplitRela rela) throws Exception
	{
		this.rela = rela;
		this.splitCode = rela.getC_SPLIT_CODE();
		this.tghCode = rela.getC_TGH_CODE();
		this.relaId = rela.getId();
		this.rules = getRules();
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
	
	
	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		List<ErKmb> list = splitService.getUnSplitRuleDetailKm(portCode, geneDate, conn);
		if(list != null && list.size() > 0)
		{
			StringBuffer detail  = new StringBuffer("以下科目没有设置拆分规则：<br>");
			map.put("result", "SplitRuleFail");
			for(ErKmb km : list)
			{
				detail.append(km.getC_KM_CODE()).append("(").append(km.getC_KM_NAME()).append(")").append("<br>");
			}
			map.put("resultDetail", detail.toString());
			return map;
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
			initParams(rela);
			map = super.geneElecData();
		}else//生成所有子账
		{
			List<ErSplitRela> relas = getErSplitRelas(portCode,geneDate,conn);
			if(relas == null || relas.size() == 0)
			{
				map.put("result", "SplitRelaFail");
				map.put("resultDetail", "没有设置多托管行拆分设置！");
				return map;
			}
			for(ErSplitRela rela : relas)
			{
				initParams(rela);
				map = super.geneElecData();
			}
		}
		return map;
	}
	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs, int dataType,HashMap<String, ElecPerRela> elecPerRelaMap) throws Exception {
		if(1 == rs.getInt("N_DETAIL")&&!isAddDetailKm(rs.getString("C_KM_CODE")))
		{
			return false;
		}
		return super.setPojo(pst, rs, dataType, elecPerRelaMap);
	}
	
	protected Map<String,String> getRules() throws Exception
	{
		Map<String, String> map = this.splitService.getSplitRuleDetailKmBySplitRela(relaId, conn);
		if(map == null)
		{
			map = new HashMap<String, String>();
		}
		return map;
	}
	
	protected boolean isAddDetailKm(String kmCode) throws Exception
	{
		if(this.rules == null)
		{
			this.rules = new HashMap<String, String>();
		}
		if(this.rules.containsKey(kmCode))
		{
			return true;
		}
		return false;
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
	
}
