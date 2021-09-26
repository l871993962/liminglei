package com.yss.ams.base.information.modules.sys.indexdi.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.sys.indexdi.pojo.Indexdi;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
//import com.yss.para.bi.indexdi.pojo.Indexdi;

/**
 * 合规指标类型字典T_S_INDEX dao
 *
 */
public class IndexdiDao extends GeneralDao{
	
	public IndexdiDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	/**
	 * 覆写对象转换方法
	 */
	@Override
	protected BasePojo setResultSet(ResultSetTools rsTools,ResultSet rs,Class<?> clazz){
		Indexdi indexdi = new Indexdi();
		try {
			indexdi.setC_INDEX_CODE(rs.getString("C_INDEX_CODE"));
			indexdi.setC_INDEX_NAME(rs.getString("C_INDEX_NAME"));
			indexdi.setC_DATA_TYPE(rs.getString("C_DATA_TYPE"));
			indexdi.setN_STATE(rs.getInt("N_STATE"));
			indexdi.setN_ORDER(rs.getInt("N_ORDER"));
			indexdi.setc_NAV_TYPE(rs.getString("C_NAV_TYPE"));
			indexdi.setN_DETAIL(rs.getInt("N_DETAIL"));
			indexdi.setC_KEY_CODE(rs.getString("C_KEY_CODE"));
			indexdi.setC_KEY_NAME(rs.getString("C_KEY_NAME"));
			indexdi.setC_IS_SYS(rs.getString("C_IS_SYS"));
			indexdi.setC_TRU(rs.getString("C_TRU"));
			indexdi.setC_MODE(rs.getString("C_MODE"));
			indexdi.setC_RET(rs.getString("C_RET"));
			/* CLOB类型字段转换成字符串类型 */
			Clob clob = rs.getClob("C_DATA_SOURCE");
			indexdi.setC_DATA_SOURCE(getDataSource(clob));
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("合规监控指标设置：重新对象转换方法出错", e);
		}
		return indexdi;
	}
	
	/**
	 * 合规指标类型 插入数据
	 */
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list, Connection conn){
		PreparedStatement pstmt = null;
		IndexdiSqlBuilder indexdiSqlBuilder = new IndexdiSqlBuilder();
		String sql = indexdiSqlBuilder.getInsertSql(getSequanceName(((IndexdiSqlBuilder)sqlbuilder)
				.getTableName(dbNameResolver)));
		/*
		 * Author : ChenLong
		 * Date   : 2013-11-18
		 * Status : Add
		 * Comment: 插入数据的CIDEN返回值集合
		 * */
		List<String> cidenList = new ArrayList<String>();
		try {
			pstmt = conn.prepareStatement(sql);
			for(T t : list){
				Indexdi indexdi = (Indexdi)t;
				
				/*
				 * Author : ChenLong
				 * Date   : 2013-11-18
				 * Status : Add
				 * Comment: 插入数据的CIDEN返回值
				 * */
				String ciden = getSequenceNextNumber(conn, getSequanceName(((IndexdiSqlBuilder)sqlbuilder)
						.getTableName(dbNameResolver)));
				cidenList.add(ciden);
				
				pstmt.setString(1, ciden);
				pstmt.setString(2, indexdi.getC_INDEX_CODE());
				pstmt.setString(3, indexdi.getC_INDEX_NAME());
				
				/* CLOB类型字段 字符串转换成字符流再存储 */
				String dataSource = indexdi.getC_DATA_SOURCE();
				StringReader dataSourceSR = new StringReader(indexdi.getC_DATA_SOURCE());
				pstmt.setCharacterStream(4, dataSourceSR, dataSource == null? 0 : dataSource.length());
				dataSourceSR.close();
				
				pstmt.setString(5, indexdi.getC_DATA_TYPE());
				pstmt.setInt(6, indexdi.getN_STATE());
				pstmt.setInt(7, indexdi.getN_ORDER());
				pstmt.setString(8, indexdi.getc_NAV_TYPE());
				pstmt.setInt(9, indexdi.getN_DETAIL());
				pstmt.setString(10, indexdi.getC_KEY_CODE());
				pstmt.setString(11, indexdi.getC_KEY_NAME());
				pstmt.setString(12, indexdi.getC_IS_SYS());
				pstmt.setString(13, indexdi.getC_TRU());
				pstmt.setString(14, indexdi.getC_MODE());
				pstmt.setString(15, indexdi.getC_RET());
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			pstmt.clearBatch();//addbyleeyu20151015
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("合规监控指标设置：插入合规指标类型出错", e);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("合规监控指标设置：插入合规指标类型出错", e);
		}finally{
			closeStatementFinal(pstmt);
		}
		return cidenList;
	}
	
	/**
	 * 获取DataSource CLOB对象
	 * @param clob
	 * @return
	 */
	private String getDataSource(Clob clob){
		StringBuffer strBuff = new StringBuffer();
		BufferedReader br = null;
		Reader reader = null;
		try {
			if(clob != null){
				reader = clob.getCharacterStream();
				br = new BufferedReader(reader);
				String line = br.readLine();
				while(line != null){
					strBuff.append(line);
					line = br.readLine();
					if(line != null){
						strBuff.append("\n");
					}
				}
				reader.close();
				br.close();
			}
			
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("合规监控指标设置：获取DataSource CLOB对象出错", e);
		} catch (IOException e) {
//			e.printStackTrace();
			logger.log("合规监控指标设置：获取DataSource CLOB对象出错", e);
		}finally{
			try {
				if(reader!=null){
					reader.close();
				}
				if(br!=null){
						br.close();
				}
			} catch (IOException e) {
				logger.log("合规监控指标设置：获取DataSource CLOB对象出错", e);
			}
		}
		return strBuff.toString();
	}
}
