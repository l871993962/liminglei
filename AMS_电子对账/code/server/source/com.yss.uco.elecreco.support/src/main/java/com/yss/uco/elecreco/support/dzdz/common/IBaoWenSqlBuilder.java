package com.yss.uco.elecreco.support.dzdz.common;

import com.yss.framework.api.mvc.dao.sql.SQLBuilder;

/**
 * SqlBuilder 
 * @author weijj
 *
 */
public interface IBaoWenSqlBuilder extends SQLBuilder {
	String getRootSql();
	/*没被调用到，废弃  wlx 20160907*/
//	String getInitSql();
	String getRootSqlByReportType(String tableName);
	/**
	 * STORY #49489 光大证券-产品关联电子对账设置优化
	 * @return
	 */
	String getDzParaSql();
}
