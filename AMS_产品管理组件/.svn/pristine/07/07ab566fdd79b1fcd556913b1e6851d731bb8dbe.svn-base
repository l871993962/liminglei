package com.yss.ams.product.information.modules.ab.assetsTree_a_rule.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * 树形结构分类规则
 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
 * add by yangru 20190717
 * @author lenovo
 *
 */
public class AssetsTree_A_RuleDao extends GeneralDao {
	private AssetsTree_A_RuleSqlBuilder assetTreeARuleSqlBuilder;

	public AssetsTree_A_RuleDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		assetTreeARuleSqlBuilder = (AssetsTree_A_RuleSqlBuilder) sqlBuilder;
	}
	
	/**
	 * 根据关联id删除数据
	 * @param relaId
	 */
	public void deleteByRelaId(String relaId){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql.append(" delete from ") ;
			sql.append(this.assetTreeARuleSqlBuilder.getTableName(dbNameResolver));
			sql.append(" where C_IDEN_RELA = ? ");
			
			pstmt = conn.prepareStatement(sql.toString());
			logger.debug(sql.toString());
			pstmt.setString(1, relaId);
			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
	}
	

}
