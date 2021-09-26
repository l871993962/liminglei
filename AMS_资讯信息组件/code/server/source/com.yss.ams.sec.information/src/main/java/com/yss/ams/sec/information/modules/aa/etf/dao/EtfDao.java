package com.yss.ams.sec.information.modules.aa.etf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
/**
 * ETF基本信息 dao
 * @author gongyue
 * 资讯信息拆分	2017.7.5 STORY #42948 资讯信息管理组件化拆分
 */
public class EtfDao extends GeneralDao {

	public EtfDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-05-04
	 * Status : Add
	 * Task   : TASK #203469 ETF非担保交收指令生成表
	 * Comment: 获取组合相应的交易代码
	 */
	public String getTradeCodeByPortCode(String portCode){
		String tradeCode = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			String sql = "select distinct c_trade_code from t_p_aa_etf where c_port_code = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				tradeCode = rs.getString("c_trade_code");
			}
		}catch(Exception ex){
			throw new BusinessException("根据组合代码获取交易代码出错!" + ex.getMessage(),ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return tradeCode;
	}
}
