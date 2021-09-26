package com.yss.ams.product.information.modules.aa.portcls.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 分级产品SQL建造类
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
public class PortClsSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select count(*) as CNT from T_P_AA_PORT_CLS  a ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
				buf.append(" ORDER BY a.N_CHECK_STATE, a.C_IDEN ");
			}
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {

			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append("select a.* from T_P_AA_PORT_CLS  a ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}

			buf.append(" ORDER BY a.N_CHECK_STATE, a.C_IDEN ");

			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));
		for (String fieldName : paraNameList) {
			if (fieldName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf
						.append(" a.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldName.equals("C_PORT_CLS_CODE")) {
				valueFieldbuf.append(" a.C_PORT_CLS_CODE = ? AND ");
			} else if (fieldName.equals("C_DV_PORT_CLS_TYPE")) {
				valueFieldbuf.append(" a.C_DV_PORT_CLS_TYPE =? AND ");
			} else if (fieldName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			}else if (fieldName.equals("C_PORT_CODE")) {
				valueFieldbuf.append(" a.C_PORT_CODE = ? AND ");
				////分级级别 
			} else if (fieldName.equals("C_DV_PORT_CLS_LEVEL")) {
				valueFieldbuf.append(" a.C_DV_PORT_CLS_LEVEL = ? AND ");
			} else if(fieldName.equals("ExpiryDateBegin")){  //到期日期   add by xhb 20160105 TORY #25082
				valueFieldbuf.append(" a.D_OFF_LIST >= to_date(?,'yyyy-MM-dd') AND ");
			} else if(fieldName.equals("ExpiryDateEnd")){
				valueFieldbuf.append(" a.D_OFF_LIST <= to_date(?,'yyyy-MM-dd') AND ");
			} else if(fieldName.equals("TradeDateBegin")){  //成立日期   add by xhb 20160105 
				valueFieldbuf.append(" a.D_TO_LIST >= to_date(?,'yyyy-MM-dd') AND ");
			} else if(fieldName.equals("TradeDateEnd")){
				valueFieldbuf.append(" a.D_TO_LIST <= to_date(?,'yyyy-MM-dd') AND ");
			} else if(fieldName.equals("ARRAY_C_IDEN")){ //modified by jiangzhichao 2017-11-30  产品生命周期系统4.5开发_分级产品信息 优化查询功能
				valueFieldbuf.append(" a.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldName.contains("%")) { // modified by HeLiang 2017-10-10 STORY #46802 产品生命周期系统4.5开发_分级产品信息 优化模糊查询功能
				valueFieldbuf.append(fieldName.replace("%", "")
						+ " LIKE ? AND ");
			}else if (fieldName.equals("C_DV_PORT_CLS")) {//级别类型
				valueFieldbuf.append(" a.C_DV_PORT_CLS = ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getPortClsByUser() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT A.C_PORT_CODE, ");
		buf.append(" A.C_PORT_CLS_CODE,A.C_PORT_CLS_NAME, ");
		buf.append(" A.C_DV_PORT_CLS_TYPE,A.C_DV_PORT_CLS_LEVEL, ");
		buf.append(" A.C_DV_PORT_CLS, A.C_DC_CODE,");
		buf.append(" A.D_TO_LIST ,A.D_OFF_LIST, ");
		buf.append(" A.C_ALGO_CODE,A.C_DESC,A.N_CHECK_STATE, ");
		buf.append(" A.C_UPDATE_BY,A.C_CHECK_BY,A.C_UPDATE_TIME , ");
		buf.append(" A.C_CHECK_TIME,A.C_PORT_CLS_CODE_P, ");
		buf.append(" A.C_DV_NETTING,A.C_DV_INC_DIS,A.C_IDEN, ");
		buf.append(" A.C_ALGO_CODE_I,A.N_YEAR_INCOME,A.C_INCOME_TYPE, ");
		buf.append(" A.C_FORMULA_CODE, A.D_LIQUID_DATE");
		// STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
	    buf.append(", A.C_XYPJ, A.C_DV_QHGS, C_FJBM,C_XSDX,C_TYKHLX ");
	    buf.append(" , C_DV_LEVELNETTING ");
		buf.append(" FROM T_P_AA_PORT_CLS A ");
//		buf.append(" JOIN (SELECT * FROM T_S_USER_RELA WHERE C_USER_CODE = ? AND N_CHECK_STATE >= 0) B ");
//		buf.append(" ON A.C_PORT_CODE = B.C_PORT_CODE ");
		buf.append(" WHERE A.N_CHECK_STATE = 1 AND A.C_PORT_CODE IN (select * from TABLE(?))");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/* START 数据服务方法 */

	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" SELECT C_PORT_CODE, ");
	    buf.append(" C_PORT_CLS_CODE,C_PORT_CLS_NAME, ");
	    buf.append(" C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL, ");
	    buf.append(" C_DV_PORT_CLS, C_DC_CODE,");
	    buf.append(" D_TO_LIST ,D_OFF_LIST, ");
	    buf.append(" C_ALGO_CODE,C_DESC,N_CHECK_STATE, ");
	    buf.append(" C_UPDATE_BY,C_CHECK_BY,C_UPDATE_TIME , ");
	    buf.append(" C_CHECK_TIME,C_PORT_CLS_CODE_P, ");
	    buf.append(" C_DV_NETTING,C_DV_INC_DIS,C_IDEN, ");
	    /**Start 20150516.modified by liubo.BUG #112520 产品销售数据，新增申购保存报错
	     * D_LIQUID_DATE增加为空时的判断*/
	    buf.append(" C_ALGO_CODE_I,N_YEAR_INCOME,C_INCOME_TYPE,C_FORMULA_CODE,Nvl(D_LIQUID_DATE,date'9998-12-31') as D_LIQUID_DATE ");
	    /**End 20150516.modified by liubo.BUG #112520 产品销售数据，新增申购保存报错*/
	    // STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
	    buf.append(", C_XYPJ, C_DV_QHGS, C_FJBM,C_XSDX,C_TYKHLX ");
	    buf.append(" , C_DV_LEVELNETTING ");
	    buf.append(" FROM T_P_AA_PORT_CLS ");
	    buf.append(" WHERE N_CHECK_STATE = 1 ");
	    buf.append(" and c_iden in (select max(a.c_iden) ");
	    buf.append(" from T_P_AA_PORT_CLS a ");
	    buf.append(" where a.N_CHECK_STATE = 1 ");//BUG #170665 【分级产品】查询条件分级组合展示数据重复且数据缺少，收益率查询提示缺少服务
	    buf.append(" group by a.c_port_cls_code, a.c_port_code) ");
//		buf.append(" CONNECT BY PRIOR C_PORT_CLS_CODE = C_PORT_CLS_CODE_P ");
//		buf.append(" START WITH C_PORT_CLS_CODE_P = '[root]' ");
//		buf.append(" ORDER SIBLINGS BY C_PORT_CLS_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);

		buf.append(" AND C_PORT_CODE = ? ");
		buf.append(" AND C_PORT_CLS_CODE = ? ");
//		buf.append(" CONNECT BY PRIOR C_PORT_CLS_CODE = C_PORT_CLS_CODE_P ");
//		buf.append(" START WITH C_PORT_CLS_CODE_P = '[root]' ");
//		buf.append(" ORDER SIBLINGS BY C_PORT_CLS_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String getPojoByCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" AND C_PORT_CLS_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	public String getDataListByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);

		buf.append(" AND ");
		buf.append(" C_PORT_CODE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
//		buf.append(" CONNECT BY PRIOR C_PORT_CLS_CODE = C_PORT_CLS_CODE_P ");
//		buf.append(" START WITH C_PORT_CLS_CODE_P = '[root]' ");
//		buf.append(" ORDER SIBLINGS BY C_PORT_CLS_CODE ");

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	private void getCommonQuerySqlBuf(StringBuffer buf) {
		buf.append(" SELECT C_PORT_CODE, ");
		buf.append(" C_PORT_CLS_CODE,C_PORT_CLS_NAME, ");
		buf.append(" C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL, ");
		buf.append(" C_DV_PORT_CLS, C_DC_CODE,");
		buf.append(" D_TO_LIST ,D_OFF_LIST, ");
		buf.append(" C_ALGO_CODE,C_DESC,N_CHECK_STATE, ");
		buf.append(" C_UPDATE_BY,C_CHECK_BY,C_UPDATE_TIME , ");
		buf.append(" C_CHECK_TIME,C_PORT_CLS_CODE_P, ");
		buf.append(" C_DV_NETTING,C_DV_INC_DIS,C_IDEN, ");
		//BUG #361549 申万宏源外包分级产品估值表爆红，显示分级与汇总净值差1分
		buf.append(" C_DV_LEVELNETTING, ");
		/**Start 20150516.modified by liubo.BUG #112520 产品销售数据，新增申购保存报错
		 * D_LIQUID_DATE增加为空时的判断*/
		buf.append(" C_ALGO_CODE_I,N_YEAR_INCOME,C_INCOME_TYPE,C_FORMULA_CODE,Nvl(D_LIQUID_DATE,date'9998-12-31') as D_LIQUID_DATE ");
		/**End 20150516.modified by liubo.BUG #112520 产品销售数据，新增申购保存报错*/
		// STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
	    buf.append(", C_XYPJ, C_DV_QHGS, C_FJBM,C_XSDX,C_TYKHLX ");
		buf.append(" FROM T_P_AA_PORT_CLS ");
		buf.append(" WHERE N_CHECK_STATE = 1 ");

	}
	
	/*
	 * 查询分级组合信息
	 */
	public String getPortClsByDate()
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" SELECT DISTINCT C_PORT_CODE, ");
			buf.append(" C_PORT_CLS_CODE,C_PORT_CLS_NAME, ");
			buf.append(" C_DV_PORT_CLS_TYPE,C_DV_PORT_CLS_LEVEL, ");
			buf.append(" C_DV_PORT_CLS, C_DC_CODE,");
			buf.append(" D_TO_LIST ,D_OFF_LIST, ");
			buf.append(" C_ALGO_CODE,C_DESC, ");
			buf.append(" N_CHECK_STATE, C_UPDATE_BY, C_CHECK_BY, C_UPDATE_TIME, C_CHECK_TIME, C_IDEN, ");
			buf.append(" C_PORT_CLS_CODE_P, ");
			buf.append(" C_DV_NETTING,C_DV_INC_DIS, ");
			buf.append(" C_ALGO_CODE_I,N_YEAR_INCOME,C_INCOME_TYPE,C_FORMULA_CODE,D_LIQUID_DATE ");
			// STORY #51721 光大证券-监管类信息完善 add by lujianhao 20180705
		    buf.append(", C_XYPJ, C_DV_QHGS, C_FJBM ");
			buf.append(" FROM T_P_AA_PORT_CLS ");
			buf.append(" where C_PORT_CODE = ? and D_OFF_LIST >= TO_DATE(?,'yyyy-MM-dd') and D_TO_LIST <= TO_DATE(?,'yyyy-MM-dd') and N_CHECK_STATE = 1 order by D_TO_LIST  ASC ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}
	
	public String getportClsRecords() throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		try {
			buf.append(" SELECT A.* FROM T_P_AA_PORT_CLS A WHERE A.N_CHECK_STATE = 1 ");
			buf.append(" AND A.D_LIQUID_DATE = ? AND A.C_PORT_CODE = ? ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	/* END 数据服务方法 */
	
	public String getUpdateDateSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" UPDATE T_P_AA_PORT_CLS SET D_OFF_LIST = TO_DATE(?,'yyyy-MM-dd') ");
		buf.append(" WHERE C_PORT_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		
		return sql;
	}

	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String s) {
		// TODO Auto-generated method stub
		return dbNameResolver.getColumnName(PortClsColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return arg0.getTableName(PortClsTableName.recycle);
	}

	public String getTableName(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return arg0.getTableName(PortClsTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(PortClsTableName.userInfo);
	}

	public String getPortClsCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		buf.append(" select * from T_P_AA_PORT_CLS t where t.c_port_code=? and t.n_check_state=1 order by t.n_check_state asc");

		sql = buf.toString();
		
		return sql;
	}
	
	
	public String getPortClsCodeByCond() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select t.c_port_cls_code from T_P_AA_PORT_CLS t where t.c_port_code=?  and t.C_DV_PORT_CLS = ? and t.n_check_state=1 ");
		sql = buf.toString();
		return sql;
	}

	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();

		getCommonQuerySqlBuf(buf);
		
		sql = " TO_DATE(C_CHECK_TIME,'yyyy-mm-dd hh24:mi:ss') >= to_date(?,'yyyy-mm-dd hh24:mi:ss')";
		if (sql.trim().length() > 0) {
			buf.append(" AND (");
			buf.append(sql);
			buf.append(")");
		}

		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 
	 * @author liuxiang
	 * @date 2016年9月1日 STORY28429【广发证券】TA净值表导出设置中导出级别的优化
	 * @return
	 */
	public String getClsCodesByPortCodeAndType() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		//buf.append(" SELECT TO_CHAR(WM_CONCAT(DISTINCT C_PORT_CLS_CODE)) C_PORT_CLS_CODE ");
		buf.append(" SELECT TO_CHAR(PKG_FUN_CONCAT_ARRAY.F_CONCAT_ARRAY( CAST (collect(distinct C_PORT_CLS_CODE) AS VARTABLETYPE),',')) C_PORT_CLS_CODE ");
		buf.append(" FROM T_P_AA_PORT_CLS A WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ");
		buf.append(" AND A.C_DV_PORT_CLS IN (SELECT * FROM TABLE(?)) GROUP BY C_PORT_CODE ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String checkDate(String id) {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT 1 ");
		buf.append(" FROM T_P_AA_PORT_CLS cls ");
		buf.append(" WHERE cls.C_PORT_CODE = ? ");
		buf.append(" AND cls.C_PORT_CLS_CODE = ? ");
		buf.append(" AND ((cls.D_TO_LIST <= ? AND cls.D_OFF_LIST >= ?) ");
		buf.append(" OR　(cls.D_TO_LIST <= ? AND cls.D_OFF_LIST >= ?)) ");
		if(!StringUtil.IsNullOrEmpty(id))
		{
			buf.append(" AND cls.C_IDEN != '" + id + "' ");
		}
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}	
	
	/**
	 * add by xuhanbing 2016-12-6 
	 * STORY #35787 海通资管 赢财升鑫产品的 每年基准收益率参数优化
	 * 判断分级成立日期与清算日期时间段是否有重叠的情况
	 * @param id
	 * @return
	 */
	public String checkDateQSRQ(String id) {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT 1 ");
		buf.append(" FROM T_P_AA_PORT_CLS cls ");
		buf.append(" WHERE cls.C_PORT_CODE = ? ");
		buf.append(" AND cls.C_PORT_CLS_CODE = ? ");
		buf.append(" AND ((cls.D_TO_LIST <= ? AND cls.D_LIQUID_DATE >= ?) ");
		buf.append(" OR　(cls.D_TO_LIST <= ? AND cls.D_LIQUID_DATE >= ?) ");
		buf.append(" OR (cls.D_TO_LIST >= ? AND cls.D_LIQUID_DATE <= ?)");
		buf.append(" )");
		if(!StringUtil.IsNullOrEmpty(id))
		{
			buf.append(" AND cls.C_IDEN != '" + id + "' ");
		}
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortCls(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A");
		buf.append(" WHERE A.C_PORT_CODE = ? AND  A.D_TO_LIST = ? ");
		buf.append(" AND A.C_PORT_CLS_CODE = ? AND A.N_CHECK_STATE = 1");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsNotDate(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT *  FROM T_P_AA_PORT_CLS A");
		buf.append(" WHERE A.C_PORT_CODE = ?  AND A.C_PORT_CLS_CODE = ? AND A.N_CHECK_STATE = 1 ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortCls_Date(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT *  FROM T_P_AA_PORT_CLS A");
		buf.append(" WHERE A.C_PORT_CODE = ?  AND A.C_PORT_CLS_CODE = ? AND A.N_CHECK_STATE = 1 ");
		buf.append(" AND ? BETWEEN D_TO_LIST AND D_OFF_LIST ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortCls_Port(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ");
		buf.append(" ORDER BY DECODE(A.C_DV_PORT_CLS_LEVEL, 'PCCT_JZFE', 1, 2) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsByTypeAndClass(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE C_DV_PORT_CLS_TYPE = ? AND C_DV_PORT_CLS = ? ");
		buf.append(" AND N_CHECK_STATE = 1 AND C_PORT_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsByClass(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE  ? BETWEEN A.D_TO_LIST AND A.D_OFF_LIST AND C_DV_PORT_CLS = ? ");
		buf.append(" AND N_CHECK_STATE = 1 AND C_PORT_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsByLevel(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE C_DV_PORT_CLS_TYPE = ? AND C_DV_PORT_CLS = ? ");
		buf.append(" AND N_CHECK_STATE = 1 AND C_PORT_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsMx(){
		String sql = "";
		StringBuffer bufSql = new StringBuffer();
		bufSql.append(" SELECT * ");
		bufSql.append(" FROM T_P_AA_PORT_CLS ");
		bufSql.append(" WHERE C_PORT_CODE IN ");
		bufSql.append(" (SELECT C_PORT_CODE ");
		bufSql.append(" FROM (SELECT * ");
		bufSql.append(" FROM T_P_AB_PORT ");
		bufSql.append(" START WITH C_PORT_CODE = ");
		bufSql.append(" (SELECT C_PORT_CODE ");
		bufSql.append(" FROM T_P_AB_PORT ");
		bufSql.append(" WHERE C_PORT_CODE = ? ");
		bufSql.append(" AND C_DV_PORT_CODE = 'PORT_LAYER') ");
		bufSql.append(" CONNECT BY PRIOR C_PORT_CODE = C_PORT_CODE_P) ");
		bufSql.append(" WHERE C_PORT_CODE NOT IN ");
		bufSql.append(" (SELECT C_PORT_CODE_P ");
		bufSql.append(" FROM T_P_AB_PORT ");
		bufSql.append(" START WITH C_PORT_CODE = ");
		bufSql.append(" (SELECT C_PORT_CODE ");
		bufSql.append(" FROM T_P_AB_PORT ");
		bufSql.append(" WHERE C_PORT_CODE = ? ");
		bufSql.append(" AND C_DV_PORT_CODE = 'PORT_LAYER') ");
		bufSql.append(" CONNECT BY PRIOR C_PORT_CODE = C_PORT_CODE_P)) ");
		sql = bufSql.toString();
		StringUtil.clearStringBuffer(bufSql);
		return sql;
	}
	
	public String queryPortClsList(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsYxq(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM T_P_AA_PORT_CLS A ");
		buf.append(" WHERE A.C_PORT_CODE = ? AND ? between A.D_TO_LIST and  A.D_OFF_LIST ");
		buf.append(" AND A.C_PORT_CLS_CODE = ? AND A.N_CHECK_STATE = 1 ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortCls_Port_Date(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from t_p_aa_port_cls a where a.c_port_code = ? ");
		buf.append(" and ? between a.d_to_list and a.d_off_list and A.N_CHECK_STATE = 1 order by a.c_dv_netting, a.d_to_list ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPortClsByLiquid(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" select * from t_p_aa_port_cls a where a.c_port_code = ? ");
		buf.append(" and ? between a.d_to_list and a.D_LIQUID_DATE and A.N_CHECK_STATE = 1 order by a.c_dv_netting, a.d_to_list ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	public String queryPreviousPortCls(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT A.* FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND  A.D_OFF_LIST = (select max(b.D_OFF_LIST) ");
		sqlBuffer.append(" from T_P_AA_PORT_CLS B  WHERE B.C_PORT_CODE = ? ");
		sqlBuffer.append(" AND B.C_PORT_CLS_CODE = ? AND B.D_OFF_LIST < ? ");
		sqlBuffer.append(" AND B.N_CHECK_STATE = 1) ");
		sqlBuffer.append(" AND A.C_PORT_CLS_CODE = ? AND A.N_CHECK_STATE = 1 ");
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryPortClsByDvCls(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1");
		sqlBuffer.append(" and c_dv_port_cls = ? and d_to_list <= ? order by d_to_list "); 
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryPortClsByDvCls_Port_Date(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1");
		sqlBuffer.append(" and d_to_list <= ? order by d_to_list "); 
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryPortClsByDvClsAndDate(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 AND  a.c_DV_PORT_CLS_LEVEL <> ? ");
		sqlBuffer.append(" AND ? BETWEEN D_TO_LIST AND D_OFF_LIST ORDER BY D_TO_LIST "); 
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryPortClsByClsLevel(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 AND  a.c_DV_PORT_CLS_LEVEL = ? ");
		sqlBuffer.append(" AND ? BETWEEN D_TO_LIST AND D_OFF_LIST  "); 
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryPortClsSort(boolean sort){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM ( SELECT * FROM T_P_AA_PORT_CLS A");
		sqlBuffer.append(" WHERE A.C_PORT_CODE = ? AND ? BETWEEN D_TO_LIST AND D_OFF_LIST ");
		sqlBuffer.append(" AND A.N_CHECK_STATE = 1 order by A.D_TO_LIST");
		if(!sort){
			sqlBuffer.append(" desc");
		}
		sqlBuffer.append(") where rownum = 1");
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
	public String queryFjSyfpInfo(){
		String sql = "";
		StringBuffer sqlBuffer = new StringBuffer();
		sqlBuffer.append(" SELECT * FROM T_P_AA_PORT_CLS ");
		sqlBuffer.append(" WHERE C_PORT_CODE = ? AND D_TO_LIST <= ? AND D_OFF_LIST > ? AND N_CHECK_STATE = 1 ");
		sqlBuffer.append(" and N_Year_Income > 0 and length(C_Algo_Code_I) > 0 ");
		sql = sqlBuffer.toString();
		StringUtil.clearStringBuffer(sqlBuffer);
		return sql;
	}
	
}


