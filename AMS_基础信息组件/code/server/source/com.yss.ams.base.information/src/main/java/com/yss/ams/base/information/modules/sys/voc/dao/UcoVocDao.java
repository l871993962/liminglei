package com.yss.ams.base.information.modules.sys.voc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;

/**
 * //STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志  edit by sunyanlin 20191202
 * @author lenovo
 *
 */
public class UcoVocDao  extends GeneralDao{
	
	private UcoVocSqlbuilder ucoVocSqlbuilder = null;

	public UcoVocDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.ucoVocSqlbuilder = (UcoVocSqlbuilder) sqlBuilder;
	}
	
	public void addAndUpdUcoVoc(boolean isFistLoad, List<Vocabulary> addList,List<Vocabulary> updList)  throws Exception{
		Connection conn = null;
		boolean bTrans = false;
		try
		{
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			
			if(isFistLoad){
				//初次同步，数据全部删除
				this.deleteAllUcoVoc(conn);
			}
			
			if(addList.size() > 0){
				this.insertUcoVoc(addList, conn);
			}
			
			if(updList.size() > 0){
				this.updateUcoVoc(updList, conn);
			}
			
			conn.commit();
			bTrans = true;
			conn.setAutoCommit(bTrans);
			
		}catch(Exception e){
			logger.error("增量同步词汇数据失败", e);
			throw e;
		}finally{
			DbFun.endTransFinal(conn, bTrans);
			DbFun.releaseConnection(conn);
		}
	}
	
	/**
	 * 批量更新估值词汇数据
	 *  C_DV_STATE词汇对象没有对应属性，不予更新和插入
	 * @param updList
	 * @param conn
	 */
	public void insertUcoVoc(List<Vocabulary> addList ,Connection conn) throws Exception{
		PreparedStatement pst = null;
		String sql = "";
		try{
			sql = ucoVocSqlbuilder.getInsertUcoVocSql();
			pst = conn.prepareStatement(sql);
			
			for(Vocabulary voc : addList){
				pst.setString(1, voc.getC_DV_CODE());
				pst.setString(2, voc.getC_DV_NAME());
				pst.setString(3, voc.getC_DV_TYPE());
				pst.setString(4, voc.getC_DESC());
				pst.setString(5, voc.getN_ORDER());
				pst.setString(6, voc.getC_AUTH_ORG_CODE());				
				pst.addBatch();
			}
			
			pst.executeBatch();
			pst.clearBatch();
		}catch(Exception e){
			logger.error("批量更新估值词汇表数据失败！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 全量删除估值表数据
	 * @param conn
	 */
	public void deleteAllUcoVoc(Connection conn)  throws Exception{
		PreparedStatement pst = null;
		String sql = "";
		try{
			sql = ucoVocSqlbuilder.getDeleteAllUcoVocSql();
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
		}catch(Exception e){
			logger.error("全量删除估值词汇表数据失败！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}		
	}
	
	/**
	 * 批量更新估值词汇数据
	 * C_DV_STATE词汇对象没有对应属性，不予更新和插入
	 * @param updList
	 * @param conn
	 */
	public void updateUcoVoc(List<Vocabulary> updList ,Connection conn) throws Exception{
		PreparedStatement pst = null;
		String sql = "";
		try{
			sql = ucoVocSqlbuilder.getUpdateUcoVocSql();
			pst = conn.prepareStatement(sql);
			
			for(Vocabulary voc : updList){
				pst.setString(1, voc.getC_DV_NAME());
				pst.setString(2, voc.getC_DV_TYPE());
				pst.setString(3, voc.getC_DESC());
				pst.setString(4, voc.getN_ORDER());
				pst.setString(5, voc.getC_AUTH_ORG_CODE());
				pst.setString(6, voc.getC_DV_CODE());
				pst.addBatch();
			}
			
			pst.executeBatch();
			pst.clearBatch();
		}catch(Exception e){
			logger.error("批量更新估值词汇表数据失败！", e);
			throw e;
		}finally{
			DbFun.closeStatementFinal(pst);
		}
	}

}
