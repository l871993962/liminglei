package com.yss.uco.elecreco.er.spilt.rela.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.util.PojoUtils;
import com.yss.uco.elecreco.er.spilt.rela.pojo.ErSplitRela;
import com.yss.uco.elecreco.er.spilt.rule.dao.ErSplitRuleDao;
import com.yss.uco.elecreco.er.spilt.rule.dao.ErSplitRuleSqlBuilder;
import com.yss.uco.elecreco.er.spilt.rule.pojo.ErSplitRule;

public class ErSplitRelaDao extends GeneralDao  {

	private ErSplitRelaSqlBuilder sqlBuilder = null;
	public ErSplitRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (ErSplitRelaSqlBuilder) sqlBuilder;
	}
	public List<ErSplitRela> getErSplitRelasByPortCode(String port) {
		List<ErSplitRela> pojoList = new ArrayList<ErSplitRela>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			sql = this.sqlBuilder.getErSplitRelasByPortCodeSql();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, port);
			rs = pstmt.executeQuery();
			ErSplitRela pojo = new ErSplitRela();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				pojo = (ErSplitRela) setResultSet(rsTools, rs, pojo.getClass(), props);
				getConvertKey(props, pojo);
				pojoList.add(pojo);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		if(pojoList != null && pojoList.size() > 0 )
		{
			HashMap<String, Object> paraMap = new HashMap<String, Object>();
			ErSplitRuleDao dao = new ErSplitRuleDao(pool, new ErSplitRuleSqlBuilder());
			for(T rela : pojoList)
			{
				paraMap.clear();
				paraMap.put("dataClass", "ErSplitRule");
				paraMap.put("C_IDEN_RELA", rela.getId());
				List<BasePojo> list = dao.queryByCondition(paraMap , ErSplitRule.class);
				if(list != null && list.size() > 0)
				{
					dao.deleteById(list);
				}
			}
			super.deleteById(pojoList);
		}
	}
}