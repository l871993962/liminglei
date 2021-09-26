package com.yss.ifa.szt.tool.para.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.ifa.szt.tool.pojo.MrInfo;

public class MrInfoDao extends GeneralDao{

	public MrInfoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * @Description: 根据参数代码获取伺服器配置信息
	 * @param c_Para_Code 参数代码
	 * @return  返回主备机伺服器信息
	 * @author wulongxing 
	 * @date 2017年6月20日 下午4:12:59
	 */
	public List<MrInfo> queryMrInfos(String c_Para_Code) {
		List<MrInfo> pojoList = new ArrayList<MrInfo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, new MrInfoSqlBuilder());
			conn = this.loadNewConnection();
			sql = "SELECT * FROM T_D_ER_MRINFO WHERE C_PARA_CODE = ? ORDER BY C_DV_SWITCH_MARK, N_ORDER";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_Para_Code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MrInfo t = (MrInfo) setResultSet(rsTools,rs, MrInfo.class);
				pojoList.add(t);
			}
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	/**
	 * 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @param @param c_Para_Code
	 * @param @param conn    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月21日 上午10:33:53
	 */
	public void deleteByParaCode(String c_Para_Code, Connection conn) {
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			sql = "DELETE FROM T_D_ER_MRINFO WHERE C_PARA_CODE = ? ";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_Para_Code);
			pstmt.executeUpdate();
			
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * @Description: 根据伺服器配置信息,供深圳通链路检测调用
	 * @param c_Para_Code 参数代码
	 * @return  返回主备机伺服器信息
	 * @author lwz 
	 * @date 2017年6月20日 下午4:12:59
	 */
	public List<MrInfo> queryAllMrInfos() {
		List<MrInfo> pojoList = new ArrayList<MrInfo>();
		Set<String> set = new HashSet<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, new MrInfoSqlBuilder());
			conn = this.loadNewConnection();
			sql = " SELECT A.* FROM T_D_ER_MRINFO A  order by A.C_IDEN ";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				MrInfo t = (MrInfo) setResultSet(rsTools,rs, MrInfo.class);
				if(!set.contains(t.getC_Mr_Ip()+":"+t.getC_Mr_Port()))
				{
					set.add(t.getC_Mr_Ip()+":"+t.getC_Mr_Port());
					pojoList.add(t);
				}
				
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	/**
	 * 根据ip和端口更新检测状态
	 * @param state
	 * @param ip
	 * @param port
	 */
	public void updateCheckState(MrInfo info) {
		Connection conn = null;
		PreparedStatement pstmt = null;

		String sql = "";
		try {
			conn = this.loadNewConnection();
			sql = " update T_D_ER_MRINFO A set A.N_LINK_STATUS = ? where A.C_MR_IP = ? and A.C_MR_PORT = ? ";
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			pstmt.setInt(index++, info.getN_Link_Status());
			pstmt.setString(index++, info.getC_Mr_Ip());
			pstmt.setString(index++, info.getC_Mr_Port());
			pstmt.executeUpdate();
			conn.commit();
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}

}
