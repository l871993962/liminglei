package com.yss.uco.elecreco.service.automatic.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.db.OraDbTool;

public class AutomaticParamDao extends GeneralDao {
	
	/**
	 * 增加日志记录类
	 */
	private static final Logger logger = LogManager.getLogger(AutomaticParamDao.class);
	
	private AutomaticParamSqlBuilder sqlBuilder;

	public AutomaticParamDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (AutomaticParamSqlBuilder) sqlBuilder;
	}
	
	/**
	 * STORY #90284 【富国基金】ETF重新跑账后对应的联接基金自动重新跑
	 * 通过ETF基金获取联接基金
	 * @param portList
	 * @param date
	 * @return
	 */
	public Map<String, List<String>> getLinkPortbyEtfPort(List<String> portList, Date date) {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		try {
			conn = DbPoolFactory.getInstance().getPool().getConnection();
			String sql = this.sqlBuilder.getLinkPortbyEtfPortSql();
			pst = conn.prepareStatement(sql);
			pst.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(portList.toArray(new String[0]), conn));
			pst.setDate(2, YssFun.toSqlDate(date));
			pst.setDate(3, YssFun.toSqlDate(date));
			rs = pst.executeQuery();
			while (rs.next()) {
				String etfPort = rs.getString("C_DV_PARAMS_VALUE");
				String linkPort = rs.getString("C_PORT_CODE");
				if (!map.containsKey(etfPort)) {
					map.put(etfPort, new ArrayList<String>());
				}
				map.get(etfPort).add(linkPort);
			}
			return map;
		} catch (Exception e) {
			logger.error("获取ETF基金失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return map;
	}

}
