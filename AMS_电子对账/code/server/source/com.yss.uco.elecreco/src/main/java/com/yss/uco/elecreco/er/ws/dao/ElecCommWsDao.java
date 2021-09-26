package com.yss.uco.elecreco.er.ws.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.er.ws.pojo.dzdzstate.DzdzCommon;
import com.yss.uco.elecreco.er.ws.pojo.dzdzstate.RequestDzStateXml;


/**
 * STORY #96897 【300.7 0831】南方基金电子对账结果需要支持外部查询
 * dzDZ相关数据库操作
 * @author XQR
 *
 */
public class ElecCommWsDao extends GeneralDao {

	private Logger logger = LogManager.getLogger(ElecCommWsDao.class);
	
	public ElecCommWsDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	
	/**
	 * 查询电子对账数据
	 * @param RequestDzStateXml
	 * @return List 
	 */
	public List<DzdzCommon> queryDzdz(RequestDzStateXml xml) {
		List<DzdzCommon> list = new ArrayList<DzdzCommon>();
		Connection conn = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		String portCodes = xml.getDzdzCommon().getPORT_CODE();
		String reportTypes = xml.getDzdzCommon().getREPORT_TYPE();
		//带_
		String report1 = "";
		//不带_
		String report2 = "";
		String[] arr = reportTypes.split(",");
		for (String str : arr) {
			if (str.contains("_")) {
				report1 += str + ",";
			}else {
				report2 += str + ",";
			}
		}
		
		String date = xml.getDzdzCommon().getDATE();
		HashMap<String, String> dzMap = new HashMap<String, String>();
		dzMap.put("1001", "余额表"); 
		dzMap.put("1011", "估值表"); 
		dzMap.put("1031", "科目表"); 
		dzMap.put("1701", "资产负债表");
		dzMap.put("1801","利润表");
		dzMap.put("1711", "资产负债表（年金工行）");
		dzMap.put("1811","经营业绩表（年金工行）");
		dzMap.put("1013","双估值表");
		dzMap.put("1903","净资产变动表");
		dzMap.put("1901","所有者权益表");
		dzMap.put("A001","银行间交易拆分");
		
		HashMap<String, String> staMap = new HashMap<String, String>();
		staMap.put("ER_SENDED", "已发送");
		staMap.put("ER_SENDED_SECCUSS", "发送成功");
		staMap.put("ER_SENDED_FAIL", "发送失败");
		staMap.put("ER_ACCECPED", "对账一致");
		staMap.put("ER_SEND", "已生成");
		staMap.put("ER_IDENTICAL", "对账不一致");
		staMap.put("ER_UNGENE", "未生成");
		
		try{
			conn = this.loadNewConnection();
			String sql1 = " SELECT T.* FROM T_D_ER_INFO T where T.C_ASS_CODE IN (SELECT * FROM TABLE(?)) AND"
					+ " T.C_RPT_TYPE || '_' || T.C_FILE_TYPE IN (SELECT * FROM TABLE(?)) "
					+ " AND T.D_DATE = TO_DATE(?,'yyyy/MM/dd') ";
			
			String sql2 = " SELECT T.* FROM T_D_ER_INFO T where T.C_ASS_CODE IN (SELECT * FROM TABLE(?)) AND"
					+ " T.C_FILE_TYPE IN (SELECT * FROM TABLE(?)) "
					+ " AND T.D_DATE = TO_DATE(?,'yyyy/MM/dd') ";
			
			if (!StringUtil.IsNullOrEmptyT(report1)) {
				pst = conn.prepareStatement(sql1);
				pst.setArray(1, DbFun.sqlOverLongCondition(portCodes, conn));
				pst.setArray(2, DbFun.sqlOverLongCondition(report1, conn));
				pst.setString(3, date);
				rs = pst.executeQuery();
				while(rs.next()){
					DzdzCommon p = new DzdzCommon();
					p.setPORT_CODE(rs.getString("C_ASS_CODE"));
					p.setDATE(rs.getString("D_DATE"));
					String dzCode = rs.getString("C_FILE_TYPE");
					p.setDZCODE(dzCode);
					p.setDZNAME(dzMap.get(dzCode));
					p.setSTATE("Success");
					String state = rs.getString("C_STATE");
					p.setRESULT(state);
					p.setMES(staMap.get(state));
					p.setMES_DETAIL(rs.getString("C_STATE"));
					list.add(p);
				}
			}
			
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			
			if (!StringUtil.IsNullOrEmptyT(report2)) {
				pst = conn.prepareStatement(sql2);
				pst.setArray(1, DbFun.sqlOverLongCondition(portCodes, conn));
				pst.setArray(2, DbFun.sqlOverLongCondition(report2, conn));
				pst.setString(3, date);
				rs = pst.executeQuery();
				while(rs.next()){
					DzdzCommon p = new DzdzCommon();
					p.setPORT_CODE(rs.getString("C_ASS_CODE"));
					p.setDATE(rs.getString("D_DATE"));
					String dzCode = rs.getString("C_FILE_TYPE");
					p.setDZCODE(dzCode);
					p.setDZNAME(dzMap.get(dzCode));
					p.setSTATE("Success");
					String state = rs.getString("C_STATE");
					p.setRESULT(state);
					p.setMES(staMap.get(state));
					p.setMES_DETAIL(rs.getString("C_STATE"));
					list.add(p);
				}
			}
			
		}catch(Exception ex){
			logger.error("查询电子对账数据出错", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return list;
	}
	
}