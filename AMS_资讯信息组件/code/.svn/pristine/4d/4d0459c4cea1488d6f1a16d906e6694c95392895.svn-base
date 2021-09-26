package com.yss.ams.sec.information.modules.sv.fipay.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.yss.ams.sec.information.support.modules.sv.fipay.pojo.FiPay;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.util.DateUtil;

/**
 * 债券历史付息信息dao层
 * @author yuankai
 * 资讯信息拆分	2017.7.3 STORY #42948 资讯信息管理组件化拆分
 */
public class FiPayDao extends GeneralDao {

	public FiPayDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 校验当前选中的需要删除的历史付息数据是否符合删除要求
	 * @param pojoList
	 * @return
	 */
	public String checkDeleteData(List<BasePojo> pojoList){
		HashMap<String, ArrayList<FiPay>> fiPayHashMap = new HashMap<String, ArrayList<FiPay>>();
		ArrayList<FiPay> fiPayList = null;
		FiPay fiPay = new FiPay();
		String adjDateS = null;
		ResultSet rs = null;
		PreparedStatement pst = null;
		Connection conn = null;
		String sql = "";
		String secCode = "";
		for (BasePojo basePojo : pojoList) {
			fiPay = (FiPay)basePojo;
			fiPayList = fiPayHashMap.get(fiPay.getC_SEC_CODE());
			if(fiPayList!=null) {
				fiPayList.add(fiPay);
			} else {
				fiPayList = new ArrayList<FiPay>();
				fiPayList.add(fiPay);
				fiPayHashMap.put(fiPay.getC_SEC_CODE(), fiPayList);
			}
		}
		Iterator iterator = fiPayHashMap.keySet().iterator();
		try {
			conn = this.loadNewConnection();
			while(iterator.hasNext()) {
			
				secCode = (String)iterator.next();
				fiPayList = fiPayHashMap.get(secCode);
				for(FiPay fiPay1 : fiPayList) {
					
					if(adjDateS!=null) {
						Date adjDateD = DateUtil.toDate(fiPay1.getD_ADJ());
						Date currentDate = DateUtil.toDate(adjDateS);
						if(adjDateD.after(currentDate)) adjDateS = fiPay1.getD_ADJ();
					} else {
						adjDateS = fiPay1.getD_ADJ();
					}
				}
				sql = "select count(*) as rount from T_D_SV_FI_PAY t where T.C_SEC_CODE = ? and  T.D_ADJ > ? ";
				pst = conn.prepareStatement(sql);
				pst.setString(1, secCode);
				pst.setDate(2, YssFun.toSqlDate(adjDateS));
				rs = pst.executeQuery();
				if (rs.next()) {
					if(rs.getInt("rount")>0) {
						return YssCons._faultMark;
					}
				}
				closeResultSetFinal(rs);
				closeStatementFinal(pst);
		    }
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}
		return YssCons._successMark;
		
	}
}
