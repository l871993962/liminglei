package com.yss.ams.base.information.modules.sys.feeRelation.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.support.sys.feeRelation.pojo.FeeRelation;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;

/**
 * @classDesc 费用关联dao类
 * @version 1.0 2012-9-22
 * @author yh
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 */
public class FeeRelationDao extends GeneralDao {
	private FeeRelationSqlBuilder sqlbuilder = null;
	/**
	 * @param pool
	 * @param sqlBuilder
	 */
	public FeeRelationDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		sqlbuilder=(FeeRelationSqlBuilder)sqlBuilder;
	}
	/**
	 * 条件查询
	 * 
	 * @param paraMap 参数集合
	 * 
	 * @return 查询结果对象
	 */
	
	public List<BasePojo> queryRealDateByCondition(HashMap<String, Object> paraMap) {
			Connection conn = null;
			PreparedStatement psm = null;
			ResultSet rs = null;
			List<BasePojo> pojos = null;
			try {
				conn = loadNewConnection();
				conn.setAutoCommit(false);
				String sql = sqlbuilder.queryRealtionSql(paraMap);
				psm = conn.prepareStatement(sql);
			//	psm.setString(1, c_td_no);
				rs = psm.executeQuery();
				ResultSetTools tools = new ResultSetTools(dbNameResolver,
						sqlbuilder);
				pojos = new ArrayList<BasePojo>();
				while (rs.next()) {
					BasePojo pojo = setResultSet(tools, rs, FeeRelation.class);
					getConvertKey(PojoUtils.getPropertyDescriptors(pojo), pojo);
					pojos.add(pojo);
				}
				conn.setAutoCommit(true);
			} catch (Exception ex) {

			} finally {
				closeResultSetFinal(rs);
				closeStatementFinal(psm);
				releaseConnection(conn);
			}
			return pojos;
	}


}
