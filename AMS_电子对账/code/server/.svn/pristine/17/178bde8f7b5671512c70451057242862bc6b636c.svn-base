package com.yss.uco.elecreco.er.generate.trans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbFun;
import com.yss.uco.elecreco.bi.elecrela.pojo.ElecPerRela;
import com.yss.uco.elecreco.er.generate.service.GeneElecDataService;
import com.yss.uco.elecreco.er.generate.trans.pojo.TransPojo;

public class GeneTransDataService extends GeneElecDataService{
	
	protected static final String C_FILE_TYPE = "A001";
	protected static final String C_RPT_TYPE = "A01";

	@Override
	public Map<String, Object> geneElecData() throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String result = "";
		String resultDetail = "";
		try{
			this.getAssCode();//获取资产代码
			/**
			 * STORY #100969 华夏基金【0831.0122】需求变更mom产品需要通过电子对账发送银行间数据 
			 * 排除母基金组合代码，只根据子基金组合去生成报文，母基金不需要发送报文
			 */
			if("UNIT_LAYER".equalsIgnoreCase(this.dvPortCode)){
				//交易数据
				List<TransPojo> datas = tradeData();
				if(datas.size() > 0){
					this.geneErTransInfo(datas,C_FILE_TYPE, C_RPT_TYPE);//插入生成记录
					resultDetail = "生成交易数据成功！";
					result = "DZ生成交易数据成功！";
				}else{
					result = "Warn";
					resultDetail = "没有数据生成！";
				}
			}else{
				result = "Warn";
				resultDetail = "母基金不需要生成数据！";
			}
		}catch(Exception e){
			this.logger.error("GeneTransDataService geneElecData error", e);
		}
		
		resultMap.put("result", result);
		resultMap.put("resultDetail", resultDetail);
		return resultMap;
	}
	
	private void geneErTransInfo(List<TransPojo> datas, String fileType, String rptType) throws Exception{
		PreparedStatement pst = null;
		try {
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
			for (TransPojo transPojo : datas) {
				pst.setString(1, transPojo.getC_TD_NO());
				pst.setString(2, this.geneDate);
				pst.setString(3, userCode);
				pst.setString(4, userCode);
				pst.setString(5, rptType);
				pst.setString(6, fileType);
				pst.setString(7, this.assCode);
				pst.setString(8, this.portCode);
				pst.addBatch();
			}
			pst.executeBatch();
		} catch (Exception e) {
			this.logger.error("GeneTransDataService geneErTransInfo error", e);
			throw e;
		} finally {
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 获取交易数据
	 * @return
	 */
	private List<TransPojo> tradeData(){
		List<TransPojo> transData = new ArrayList<TransPojo>();
		PreparedStatement pst = null;
		ResultSet rs = null;
		try {
			String tranSql = "SELECT T.D_TRADE,T.C_PORT_CODE,T.C_MKT_CODE,T.C_TD_NO FROM T_D_AC_TRADE_IVT T WHERE T.C_MKT_CODE = 'XCFE' AND T.C_PORT_CODE in (select * from table(?)) AND T.D_TRADE = TO_DATE(?,'yyyy-MM-dd') ";
			pst = conn.prepareStatement(tranSql);
			pst.setArray(1, DbFun.sqlOverLongCondition(this.portCode, conn));
			pst.setString(2, this.geneDate);
			rs = pst.executeQuery();
			TransPojo pojo = null;
			while (rs.next()) {
				pojo = new TransPojo();
				pojo.setC_MKT_CODE(rs.getString("C_MKT_CODE"));
				pojo.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				pojo.setC_TD_NO(rs.getString("C_TD_NO"));
				pojo.setD_TRADE(rs.getString("D_TRADE"));
				transData.add(pojo);
			}
		} catch (Exception e) {
			this.logger.error("GeneTransDataService tradeData error", e);
		}finally{
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
		return transData;
	}

	@Override
	protected boolean setPojo(PreparedStatement pst, ResultSet rs,
			int dataType, HashMap<String, ElecPerRela> elecPerRelaMap)
			throws Exception {
		return false;
	}

}
