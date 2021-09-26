package com.yss.uco.elecreco.er.erresult.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.uco.elecreco.er.erresult.pojo.ErResult;

public class ErResultDao extends GeneralDao {

	public ErResultDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/***
	 * 先删除在保存
	 * 
	 * @param pojoList
	 * @throws SQLException
	 */
	public void saveResult(ArrayList<BasePojo> pojoList) {
		Connection conn = null;
		boolean bTrans = false;
		conn = this.loadNewConnection();
		try {
			conn.setAutoCommit(bTrans);
			bTrans = true;
			delelteBySN(pojoList, conn);
			insert(pojoList, conn);
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(),e);
		}finally{
			DbFun.endTransFinal(conn, bTrans);
			releaseConnection(conn);
		}
	}

	private void delelteBySN(ArrayList<BasePojo> pojoList, Connection conn) {
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(((ErResultSqlBuilder) sqlbuilder)
					.getDeleteBySNCode());
			for (BasePojo basePojo : pojoList) {
					ErResult er = (ErResult) basePojo;
					pstmt.setString(1, er.getC_SN());
					//wlx 20160831 BUG139336【招商财富】电子对账反馈出现新问题 
					//原因分析：多次反馈的数据C_REF_NO值逐渐变小，上一次的就删除不了
//					/////有的托管行返回的 C_REF_NO 字段存放我方报文序号，有的托管行返回的是序号 
//					////为了防止错删和少删 第2个参数放我方报文序号
//					if(er.getC_REF_NO() !=null && er.getC_REF_NO().startsWith("DZ")){
//						pstmt.setString(2, er.getC_REF_NO()); 
//					}else{
//						pstmt.setString(2, er.getC_SN()); 
//					}
					pstmt.setString(2, er.getC_ASS_CODE());
					pstmt.setString(3, er.getC_CHECK_FLAG());
					pstmt.addBatch();
			}
		    pstmt.executeBatch();
		    pstmt.clearBatch();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}

	/**
	 * STORY34022伺服器改造需支持多应用系统
	 * 电子对账反馈的报文的资产代码是否存在系统中
	 * @param assetCode 资产代码
	 * @return 存在true；否则false
	 */
	public boolean isExistAssetCode(String assetCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean isExist = false;
		try {
			conn = this.loadNewConnection();
			String sql = "select 1 from t_p_ab_port where c_ass_code = ?"; 
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, assetCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				isExist = true;
			}
		    pstmt.executeBatch();
		} catch (Exception ex) {
			logger.error("查询资产代码失败：" + ex.getMessage(), ex);
			isExist = false;
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	
		return isExist;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 获取子机构，拆分发送时，没有返回报文序号调用
	 * @param orgCode
	 * @return
	 */
	public List<String> getSubOrg(String orgCode)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(((ErResultSqlBuilder) sqlbuilder)
					.getSubOrgSql());
			int index = 1;
			pstmt.setString(index++, orgCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("C_ORG_CODE"));
			}
		    pstmt.executeBatch();
		} catch (Exception ex) {
			logger.error("查询报文序号失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * key:托管行代码value:报文序号
	 * 根据资产代码（拆分代码）获取拆分组合的序号
	 * @param code
	 * @param fileType 
	 * @param startDate 
	 * @return
	 */
	public Map<String,String> getSplitSnMapByAssCodeOrSplitCode(String code, String fileType,String rptType, String startDate)
	{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String,String> map = new HashMap<String,String>();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(((ErResultSqlBuilder) sqlbuilder)
					.getSplitSnMapByAssCodeOrSplitCodeSql());
			int index = 1;
			pstmt.setString(index++, code);
			pstmt.setString(index++, code);
			pstmt.setString(index++, fileType);
			pstmt.setString(index++, rptType);
			pstmt.setString(index++, startDate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				//托管行为空的不是拆分的组合
				String orgCode = rs.getString("C_TGH_CODE");
				if(!StringUtil.IsNullOrEmptyT(orgCode))
				{
					if(!map.containsKey(orgCode))
					{
						map.put(orgCode,rs.getString("C_SN"));
					}
				}
			}
		    pstmt.executeBatch();
		} catch (Exception ex) {
			logger.error("查询报文序号失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}

	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * @param splitCode 既有可能是拆分代码，也有可能是资产代码
	 * @param fileType
	 * @param startDate 
	 * @param transPojo
	 * @return
	 */
	public String getSnByPortWithSplitRela(String splitCode,
			String fileType,String rptType, String startDate, TransPojo transPojo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			Map<String, String> map = getSplitSnMapByAssCodeOrSplitCode(splitCode,fileType,rptType,startDate);
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(((ErResultSqlBuilder) sqlbuilder)
					.getSnByPortWithSplitRelaSql());
			int index = 1;
			pstmt.setString(index++, transPojo.getFromUser());
			pstmt.setString(index++, transPojo.getFromApp());
			rs = pstmt.executeQuery();
			if(rs.next()){
				String org = rs.getString("C_TGH_CODE");
				if(map.containsKey(org))
				{
					return map.get(org);
				}else//取子机构
				{
					List<String> list = getSubOrg(org);
					if(list != null && list.size() > 0)
					{
						for(String s : list)
						{
							if(map.containsKey(s))
							{
								return map.get(s);
							}
						}
					}
				}
			}
		    pstmt.executeBatch();
		} catch (Exception ex) {
			logger.error("查询报文序号失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return null;
	}
	/**
	 * STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	 * 通过拆分代码获取资产代码
	 * @param splitCode
	 * @return
	 */
	public String getAssCodeBySplitCode(String splitCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String assCode = null;
		try {
			conn = this.loadNewConnection();
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT C_ASS_CODE FROM T_D_ER_INFO R ");
			sb.append(" where R.C_SPLIT_CODE = ?  ) ");
			pstmt = conn.prepareStatement(sb.toString());
			int index = 1;
			pstmt.setString(index++, splitCode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				assCode = rs.getString("C_ASS_CODE");
			}
		    pstmt.executeBatch();
		} catch (Exception ex) {
			logger.error("查询报文序号失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return assCode;
	}

	/**
     * 查询差异行数数据
     * @param csn
     * @return
     */
	public int queryUnAcceptCount(String csn){
		Connection conn = null;
		PreparedStatement pstmt = null;
		List<String> list=new ArrayList<String>();
		ResultSet rs = null;
		String resutl=null;
		String sql=null;
		int count=0;
		try {
			conn = this.loadNewConnection();
			sql=" select C_RESULT from t_d_er_result where C_SN=? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,csn);
			 rs = pstmt.executeQuery();
			while (rs.next()) {
				resutl = rs.getString("C_RESULT");
				list.add(resutl);
			}
			for (String res : list) {
				if (StringUtil.IsNullOrEmptyT(res) || (!res.trim().equalsIgnoreCase("0") && !(res.contains("一致") && !res.contains("不一致")))) {
					count++;
				}
			}
		} catch (Exception ex) {
			logger.error("查询对账不一致条数失败：" + ex.getMessage(), ex);
			throw new DataAccessException("查询对账不一致条数失败：" + ex.getMessage(), ex);
		}finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return count;
	}
	
	public List<String> getParaByAssCodeAndDeptCode(String assCode, String deptCode) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<String> list = new ArrayList<String>();
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" SELECT C_TGH_CODE FROM T_D_ER_RELA where c_bus_type='BUSI_DZ' AND C_DEPT_CODE = ? AND C_TGH_CODE IN ( ");
			buf.append(" SELECT C_RELA_CODE FROM T_P_AB_PORT_RELA  where  C_PORT_CODE IN (SELECT C_PORT_CODE FROM T_P_AB_PORT where C_ASS_CODE = ? ) ) ");
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(buf.toString());
			stat.setString(1, deptCode);
			stat.setString(2, assCode);
			rs = stat.executeQuery();
			while (rs.next()) {
				list.add(rs.getString(1));
			}
		} catch (Exception e) {
			logger.error("获取电子对账参数信息出错", e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
			
		}
		return list;
	}

	/**
	 * 根据机构代码获取机构内码
	 * @param orgCode
	 * @return
	 */
	public String getInnerOrgCodeByOrgCode(String orgCode) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		String slq = " select (nvl(trim(t.c_org_inner_code),t.c_org_code)) as c_org_code from t_p_bi_org t  where t.c_org_code = ? ";
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(slq.toString());
			stat.setString(1, orgCode);
			rs = stat.executeQuery();
			while (rs.next()) {
				orgCode = rs.getString("c_org_code");
			}
		} catch (SQLException e) {
			logger.error("获取机构内码错误1", e);
		} catch (Exception e) {
			logger.error("获取机构内码错误", e);

		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return orgCode;
	}
}
