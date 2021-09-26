
package com.yss.ams.base.information.modules.sys.dttdmode.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 交易方式字典表T_S_DT_TD_MODE SQLBuilder
 *
 */
public class DttdModeDaoSqlBuilder implements SQLBuilder {

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

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(DttdModeColumnName.valueOf(s));
	}

	/**
	 * 根据条件 获取符合条件的交易方式字典视图V_S_DT_TD_MODE的数据总数
	 * @param paraNameList查询条件
	 */
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select count(*) as CNT from V_S_DT_TD_MODE a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
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

	/**
	 * 根据条件 获取符合条件的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @param paraNameList查询条件
	 */
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			this.setWhereSql(valueFieldbuf, paraNameList);
			buf.append(" select a.* from V_S_DT_TD_MODE a  ");
			if (valueFieldbuf.length() > 0) {
				buf.append(" where ").append(valueFieldbuf);
			}
			buf.append("  order by a.n_order  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldbuf);
		}
		return retSql;
	}

	/**
	 * 根据条件合并为where条件
	 * @param valueFieldbuf 合并后的where条件
	 * @param paraNameList 条件参数
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_DT_CODE")) {
				valueFieldbuf.append(" a.C_DT_CODE like ?  AND ");
			} else if (fieldedName.equals(("C_DT_NAME"))) {
				valueFieldbuf.append("  a.C_DT_NAME like ? AND ");
			} else if (fieldedName.equals("C_BUSI_TYPE")) {
				valueFieldbuf.append(" a.C_BUSI_TYPE like ? AND ");
			} else if (fieldedName.equals("N_FUND_WAY")) {
				valueFieldbuf.append(" a.N_FUND_WAY like ? AND ");
			} else if (fieldedName.equals("N_CAPI_WAY")) {
				valueFieldbuf.append(" a.N_CAPI_WAY like ? AND ");
			}else if (fieldedName.equals("N_ORDER")) {
				valueFieldbuf.append(" a.N_ORDER like ? AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
//		return dbnameresolver.getTableName(DttdModeTableName.recycle);
		return dbnameresolver.getLogTableName(DttdModeTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(DttdModeTableName.userInfo);
	}
	
	/**
	 *  联合查询业务类型，交易方式数据
	 */
	public String getTdModeBusiSql(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT * FROM ( ");
		buf.append("  SELECT A.C_BUSI_TYPE AS C_DT_CODE, ");
		buf.append(" A.C_DVA_ITEM_NAME AS C_DT_NAME, ");
		buf.append(" '[root]' AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM (SELECT DISTINCT A.C_BUSI_TYPE, C.C_DVA_ITEM_NAME ");
		buf.append(" FROM V_S_DT_TD_MODE A ");
		buf.append(" JOIN (SELECT B.* FROM V_S_DVA_ITEM B) C ");
		buf.append(" ON A.C_BUSI_TYPE = C.C_DVA_ITEM_CODE ");
		// buf.append(" AND A.C_BUSI_TYPE not in ('DJPX','TAXS','ETFXS','PJJY','CJJY','XHJY','ZQSP') ");
		buf.append(" ) A UNION ");
		buf.append(" SELECT  A.c_dt_code,a.c_dt_name,a.c_busi_type,a.n_fund_way,a.n_capi_way,a.n_order ");
		buf.append(" FROM V_S_DT_TD_MODE A JOIN (SELECT B.* FROM V_S_DVA_ITEM B) C ON A.C_BUSI_TYPE = C.C_DVA_ITEM_CODE ");
		buf.append(" ) "); 
		// buf.append(" ) D WHERE D.C_BUSI_TYPE = '[root]' "); 
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/* START 数据源服务方法 */
	
	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public String getAllDataSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		setCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 *  联合查询业务类型，交易方式所有数据
	 */
	public String getKeyConvertSql() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		buf.append(" UNION ALL ");
		buf.append(" SELECT B.C_DVA_ITEM_CODE AS C_DT_CODE, B.C_DVA_ITEM_NAME AS C_DT_NAME, ");
		buf.append(" ' ' AS C_BUSI_TYPE, 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, 0 AS N_ORDER FROM V_S_DVA_ITEM B ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 *  根据 交易方式代码C_DT_CODE 获取交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public String getDataByCode(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append("  where ");
		buf.append(" C_DT_CODE = ? ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 *  根据 交易方式代码C_DT_CODE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public String getDataByKeys(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append("  where C_DT_CODE");
		buf.append("  in (SELECT * FROM TABLE(?)) ");
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 *  根据 业务类型C_BUSI_TYPE数组 获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	public String getDataListByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getCommonQuerySqlBuf(buf);
		buf.append(" where ");
		buf.append(" C_BUSI_TYPE ");
		buf.append(" IN (SELECT * FROM TABLE(?)) ");
		setCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 获取所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return 所有的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 */
	private void getCommonQuerySqlBuf(StringBuffer buf){
		buf.append(" select a.*  ");
		buf.append(" from V_S_DT_TD_MODE a ");
		
	}
	
	private void setCommonOrderSqlBuf(StringBuffer buf){
		buf.append(" order by a.n_order  ");
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(DttdModeTableName.userInfo);
	}

	/**
	 *  联合查询业务类型，交易方式数据,每个业务类型下的交易方式数据
	 */
	private void getTreeDatasql(StringBuffer buf){
		buf.append("  SELECT A.C_BUSI_TYPE AS C_DT_CODE, ");
		buf.append(" A.C_DVA_ITEM_NAME AS C_DT_NAME, ");
		buf.append(" '[root]' AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM (SELECT DISTINCT A.C_BUSI_TYPE, C.C_DVA_ITEM_NAME ");
		buf.append(" FROM V_S_DT_TD_MODE A ");
		buf.append(" JOIN (SELECT B.* FROM V_S_DVA_ITEM B) C ");
		buf.append(" ON A.C_BUSI_TYPE = C.C_DVA_ITEM_CODE) A ");
		buf.append(" UNION ");
		buf.append(" SELECT  A.c_dt_code,a.c_dt_name,a.c_busi_type,a.n_fund_way,a.n_capi_way,a.n_order ");
		buf.append(" FROM V_S_DT_TD_MODE A JOIN (SELECT B.* FROM V_S_DVA_ITEM B) C ON A.C_BUSI_TYPE = C.C_DVA_ITEM_CODE ");
	}
	
	/**
	 *  联合查询业务类型，交易方式数据,每个业务类型下的交易方式数据
	 */
	public String getTreeData(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getTreeDatasql(buf);
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据业务类型C_BUSI_TYPE数组 或者 交易方式代码C_DT_CODE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	public String getTreeDataByTypes(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT TREE.* FROM ( ");
		getTreeDatasql(buf);
		buf.append(" ) TREE WHERE C_BUSI_TYPE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" OR C_DT_CODE IN (SELECT * FROM TABLE(?))");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据业务类型C_BUSI_TYPE数组，获取业务类型，交易方式联合树形结构
	 * @return
	 */
	public String getTreeDataByType(){
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getTreeDatasql(buf);
		buf.append(" where c_busi_type IN (SELECT * FROM TABLE(?)) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 根据时间戳获取所有交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return
	 */
	public String getDataListByTimestamp() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		getCommonQuerySqlBuf(buf);
		setCommonOrderSqlBuf(buf);
		
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * 获取核算项目代码c_dva_item_code等于SZJZYW或者 （父级代码等于SZJZYW并且核算项目代码c_dva_item_code不等于SZJZ）的交易方式字典视图V_S_DT_TD_MODE的数据信息
	 * @return
	 */
	public String getTreeDataForRule() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		getTreeDatasql(buf);
		buf.append("  union all ");
		buf.append(" select a.C_DVA_ITEM_CODE as c_dt_code,a.C_DVA_ITEM_NAME as c_dt_name, ");
		buf.append(" case when a.C_DVA_ITEM_CODE = 'SZJZYW' then '[root]' else ");
		buf.append(" a.C_DVA_ITEM_CODE_P end as c_busi_type, 0 as n_fund_way, 0 as n_capi_way, ");
		buf.append(" a.n_order from V_S_DVA_ITEM a where a.c_dva_item_code in ('SZJZYW') ");
		buf.append(" or (a.c_dva_item_code_p in ('SZJZYW') and a.c_dva_item_code <> 'SZJZ') ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 根据业务类型C_BUSI_TYPE，获取相应的业务类型，交易方式联合数据
	 * @return
	 */
	public String getDataByCodeForRule() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT A.C_BUSI_TYPE AS C_DT_CODE, ");
		buf.append(" A.C_DVA_ITEM_NAME AS C_DT_NAME, ");
		buf.append(" '[root]' AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM (SELECT DISTINCT A.C_BUSI_TYPE, C.C_DVA_ITEM_NAME ");
		buf.append(" FROM V_S_DT_TD_MODE A ");
		buf.append(" JOIN (SELECT B.* FROM V_S_DVA_ITEM B) C ");
		buf.append(" ON A.C_BUSI_TYPE = C.C_DVA_ITEM_CODE) A ");
		buf.append(" WHERE A.C_BUSI_TYPE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}

	/**
	 * add by liyanjun 2016-2-20 STORY #28608 【广发证券】在分组恒生交易数据文件接口添加控制
	 */
	public String getTreeDataByCfgCode() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT A.C_ITEM_CODE AS C_DT_CODE, ");
		buf.append(" A.C_TD_NAME AS C_DT_NAME, ");
		buf.append(" C_MKT_CODE AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM T_S_DV_TD_ITEM A WHERE C_CFG_CODE IN (SELECT * FROM TABLE(?)) ");
		buf.append(" UNION ALL ");
		buf.append(" SELECT A.C_MKT_CODE AS C_DT_CODE,  ");
		buf.append(" A.C_MKT_NAME AS C_DT_NAME, ");
		buf.append(" '[root]' AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM T_P_BI_MKT A ");
		buf.append(" WHERE C_MKT_CODE IN ");
		buf.append(" (SELECT C_MKT_CODE FROM T_S_DV_TD_ITEM A WHERE C_CFG_CODE IN (SELECT * FROM TABLE(?))) ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * add by wangtangyao 20160815 STORY #28887 保险资产证券清算款、其他应收款科目处理
	 * 根据词汇分类，获取非明细业务类型（针对证券清算款非T+1），不从销售方式表中获取
	 * 证券清算款非T+1核算项下拉列表要加载开放申赎业务类型
	 */
	public String getSQKDataListByType() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT A.C_DV_CODE AS C_DT_CODE, ");
		buf.append(" A.C_DV_NAME AS C_DT_NAME, ");
		buf.append(" A.C_DV_TYPE AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM v_s_dv_voc A ");
		buf.append(" WHERE A.C_DV_TYPE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * 支持多参数以数组形式传入
	 * @return
	 */
	public String getSQKDataListByTypes() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		buf.append("  SELECT replace(C_DV_CODE,'ZQQSK_SQK_','') AS C_DT_CODE, ");
		buf.append(" A.C_DV_NAME AS C_DT_NAME, ");
		buf.append(" A.C_DV_TYPE AS C_BUSI_TYPE, ");
		buf.append(" 0 AS N_FUND_WAY, ");
		buf.append(" 0 AS N_CAPI_WAY, ");
		buf.append(" 0 AS N_ORDER ");
		buf.append(" FROM v_s_dv_voc A ");
		buf.append(" WHERE A.C_DV_TYPE in (SELECT * FROM TABLE(?)) "); // 合并需求代码差异引起的BUG
		buf.append(" union all select a.* from t_s_dt_td_mode a ");
		//STORY #55345 【鹏华基金】证券清算款非T+1 对于核算元素“销售类型”要可以区分产品销售和场内、场外申购赎回
		buf.append(" where (C_BUSI_TYPE IN ('TAXS') or C_DT_CODE IN ('JJJY_RG','JJJY_SG','JJJY_SH') )");
		buf.append(" order by n_order");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	
	/**
	 * add by yuanyafeng 20180911 STORY #61545 【紧急】太平保险-附件管理优化（二期）
	 * @return 根据功能代码获取交易方式的脚本
	 */
	public String getDataListByFun() {
		String sql = "";
		StringBuffer buf = new StringBuffer();
		
		buf.append(" SELECT A.* ");
		buf.append(" FROM T_S_DT_TD_MODE A ");
		buf.append(" JOIN (SELECT C_S_CODE, C_T_CODE ");
		buf.append(" FROM T_V_D_GROUP_DETAIL ");
		buf.append(" WHERE C_GROUP_CODE = 'JYFSZH') B ");
		buf.append(" ON A.C_BUSI_TYPE = B.C_T_CODE ");
		buf.append(" WHERE B.C_S_CODE = ? ");
		sql = buf.toString();
		StringUtil.clearStringBuffer(buf);
		return sql;
	}
	/* END 数据源服务方法 */
}
