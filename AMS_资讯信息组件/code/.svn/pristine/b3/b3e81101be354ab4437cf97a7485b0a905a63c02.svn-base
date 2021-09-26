package com.yss.ams.sec.information.modules.mp.secmkt.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class SecMktSqlBuilder implements SQLBuilder {

	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select count(*) as CNT from T_D_MP_SEC_MKT a ");
		buf
				.append(" left join (select b.C_MKT_CODE, b.C_SEC_CODE, c.c_dv_mkt_type as c_dv_mkt_type ");
		buf.append(" from T_P_SV_SEC_BASE b");
		buf.append(" left join (select c_mkt_code, C_DV_MKT_TYPE ");
		buf.append(" from T_P_BI_MKT");
		buf
				.append(" where N_CHECK_STATE = 1) c on b.c_mkt_code = c.c_mkt_code");
		buf
				.append(" where b.N_CHECK_STATE = 1) b on a.C_SEC_CODE = b.C_SEC_CODE");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		//Orlnaod 20131116 排序太慢，先注掉
		//buf.append(" order by a.N_CHECK_STATE , a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}

	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();

		// Cause: BUG7025
		// 使用sql.Array作为入口参数、使用select table的形式
		// 作为in条件时，sql嵌套需要注意。
		// 本次修改因为查询市场类型的子句干扰了整个查询。
		// Update By Huxingtao 2013-2-25
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select a.* from T_D_MP_SEC_MKT a ");
		buf.append(" left join (select bb.C_MKT_CODE, bb.C_SEC_CODE");
		// buf.append(", c.c_dv_mkt_type as c_dv_mkt_type ");
		buf.append(" from T_P_SV_SEC_BASE bb");
		// buf.append(" left join (select c_mkt_code, C_DV_MKT_TYPE ");
		// buf.append(" from T_P_BI_MKT");
		// buf.append(" where N_CHECK_STATE = 1) c on bb.c_mkt_code = c.c_mkt_code");
		buf
				.append(" where bb.N_CHECK_STATE = 1) b on a.C_SEC_CODE = b.C_SEC_CODE");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		//Orlando 20131116 排序太慢了，先注掉
	    buf.append(" order by a.N_CHECK_STATE , a.C_IDEN ");
		retSql = buf.toString();

		return retSql;
	}
	
	/**
	 * BUG #318348 多行情同时存在  add by zmk 2020-06-15
	 * 检查数据库中是否已有证券内码、行情日期、行情分类、行情来源相同的数据
	 * @return
	 */
	public String queryDuplicate(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select C_SEC_CODE FROM t_d_mp_sec_mkt WHERE C_SEC_CODE = ? AND D_MKT= ?  AND C_MKT_CLS = ? AND C_DV_PLAT = ? ");
        return buf.toString();
	}

	/**
	 * 整合前台条件:包括证券行情,场外行情,汇率行情,存款利率
	 * 
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "a"));

		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("C_MKT_CLS")) { // 行情分类 =
				valueFieldbuf.append(" a.C_MKT_CLS = ?  AND ");
			} else if (fieldedName.equals("ARRAY_C_MKT_CLS")) { // 行情分类 in
				valueFieldbuf
						.append(" a.C_MKT_CLS IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_MKT_CODE")) { // 交易市场 in
				valueFieldbuf
						.append(" b.C_MKT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_MKT_CODE")) { // 交易市场 =
				valueFieldbuf.append(" b.C_MKT_CODE  = ?  AND ");
			} else if (fieldedName.equals("D_BEGIN")) { // >=开始日期
				valueFieldbuf.append("a.D_MKT >= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("D_END")) { // <= 结束日期
				valueFieldbuf.append("a.D_MKT <= TO_DATE(?,'yyyy-MM-dd') AND ");
			} else if (fieldedName.equals("C_SEC_CODE")) { // 交易证券=
				valueFieldbuf.append(" a.C_SEC_CODE = ? AND ");
			} else if (fieldedName.equals("ARRAY_C_SEC_CODE")) { // 交易证券  in
						valueFieldbuf.append(" a.C_SEC_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("C_DV_MKT_TYPE")) { // 市场类型
				valueFieldbuf.append(" a.C_DV_MKT_TYPE = ?  AND ");
			} else if (fieldedName.equals("C_DEL_TIME_START")) {
				valueFieldbuf.append("a.C_DEL_TIME >= ? AND ");
			} else if (fieldedName.equals("C_DEL_TIME_END")) {
				valueFieldbuf.append("a.C_DEL_TIME <= ? AND ");
			} else if (fieldedName.equals("ARRAY_C_HQZT_CODE")) { // 行情状态 in
				valueFieldbuf.append(" a.C_HQZT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}
			//STORY #35336 （嘉实QD需求）原4.0需求：26150 需求北京-[嘉实基金]QDII资产管理系统[高]2015928001（QDII汇率导入需求)
			else if(fieldedName.equals("C_PORT_CODE")){
				valueFieldbuf.append("a.C_PORT_CODE = ?  AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * modified by liyanjun 2016-6-28 STORY #31739 【招商证券】证券行情映射频率，每周和每月增加最后一个工作日
	 * 如果行情映射中选择了映射组合，则只产生该组合的私有行情，不再产生公共行情
	 * @return
	 */
	/*分布式部署拆分，此部分代码没有被使用，注释掉
	public String getSecMpMapSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT DISTINCT PS.C_PORT_CODE,PS.C_PORT_CLS_CODE,PS.C_SEC_CODE,PS.C_INDEX_CODE,PS.C_DV_HDAY_PROCESS,");//增加节假日处理 liuxiang 2016-7-9 STORY #30775 证券行情映射节假日万分收益
		buf.append(" FA.C_KM_NAME AS N_PRICE_CLOSE,fa.D_ASTSTAT FROM T_P_AB_PORT_SEC PS ");
		buf.append(" JOIN (SELECT A.C_PORT_CODE, A.D_ASTSTAT, A.C_KM_NAME,");
		buf.append(" CASE WHEN INSTR(c_km_code,'_',1) = 0 THEN ");
		buf.append(" ' ' ELSE SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, '_', 1)+1) END AS c_km_code,a.c_key_code");
		buf.append(" FROM T_R_FR_ASTSTAT A JOIN T_E_CONFIRM B");
		buf.append("  ON A.C_PORT_CODE = B.C_PORT_CODE AND A.D_ASTSTAT = B.D_BIZ_DATE");
		buf.append(" AND B.C_EXECUTE = 'LOCK' AND B.C_BIZ_CLS = 'eConfirm' ");
		buf.append(" AND A.C_KEY_CODE IN ('DWJZ', 'MWFSY')");
		buf.append("  AND A.C_PORT_CODE = ?");
		buf.append(" AND A.D_ASTSTAT = ? ) FA");
		buf.append(" ON PS.C_PORT_CODE = FA.C_PORT_CODE ");
		buf.append(" AND NVL(PS.C_PORT_CLS_CODE,' ') = fa.c_km_code "); ///by weijj bug130794 
		buf.append(" AND ps.c_index_code = fa.c_key_code ");
		buf.append(" AND PS.n_check_state = 1 ");
		buf.append(" AND TRIM(PS.C_PORT_CODE_MAP) IS NULL ");// 如果行情映射中选择了映射组合，则只产生该组合的私有行情，不再产生公共行情
		return buf.toString();
	}*/
	
	public String getDeleteSql(){
		StringBuffer buf = new StringBuffer();
		//BUG #133229 【紧急】【钜盛华】净值确认管理重复多次锁定和解锁报错
		buf.append(" DELETE FROM t_d_mp_sec_mkt WHERE C_SEC_CODE = ? AND D_MKT= ? AND C_MKT_CLS = ? ");
		buf.append(" AND C_DATA_IDF = 'Z'");
        return buf.toString();		
	}

	//add by zzk 20150714 查询非自动数据 BUG #115191 【紧急】批量日期锁定估值表锁定不了 
	public String getQuerySGSql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" select DISTINCT PS.C_SEC_CODE FROM t_d_mp_sec_mkt MP,T_P_AB_PORT_SEC PS WHERE MP.C_SEC_CODE=PS.C_SEC_CODE and  PS.C_PORT_CODE = ? AND MP.D_MKT= ? AND MP.C_MKT_CLS = 'OU' ");
		buf.append(" AND MP.C_DATA_IDF != 'Z'");
        return buf.toString();		
	}
	
	/**
	 * add by liyanjun 2016-3-31 STORY #28721 中信证券-【行政外包4.5系统】增加母基金反确认提示信息
	 * @return sql
	 */
	//STORY #69753 业务基础组件对FAST平台表结构的解耦  
	//【分布式改造】方法没有使用，注释该方法 
//	public String getQueryUserSql(){
//		StringBuffer buf = new StringBuffer();
//		buf.append(" SELECT DISTINCT C_USER_CODE FROM ( ");
//		buf.append(" SELECT C_PORT_CODE,C_USER_CODE FROM T_S_USER_RELA WHERE N_CHECK_STATE = 1 ");//用户权限表
//		buf.append(" UNION ALL ");
//		buf.append(" SELECT C_PORT_CODE,C_USER_CODE FROM T_S_BAIL WHERE N_CHECK_STATE = 1 ) A ");//顶岗管理数据表
//		buf.append(" WHERE C_USER_CODE IN (SELECT C_USER_CODE FROM T_S_USER) ");//用户表
//		buf.append(" AND EXISTS (SELECT '1' FROM T_R_FR_ASTSTAT B ");//估值表中有持仓
//		buf.append(" WHERE C_SEC_CODE = ? AND D_ASTSTAT = ? AND C_DAI_CODE = 'ZQTZ_CB' AND A.C_PORT_CODE = B.C_PORT_CODE) ");
//        return buf.toString();		
//	}
	
	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		// TODO Auto-generated method stub
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		// TODO Auto-generated method stub
		return dbnameresolver.getColumnName(SecMktColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogTableName(SecMktTableName.userInfo);
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(SecMktTableName.userInfo);
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(SecMktTableName.userInfo);
	}

	/**
	 * By Jinghehe 2015-12-07
	 * @return
	 */
	public String getdeleteSecMpSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" DELETE FROM t_d_mp_sec_mkt WHERE C_SEC_CODE = ? AND D_MKT= ? AND C_DV_PLAT = ? AND C_MKT_CLS = 'OU' ");
		buf.append(" AND C_DATA_IDF = 'H' ");
        return buf.toString();	
	}
	
	/**
	 * 获取节假日最后一天期间累计万分收益sql
	 * @author liuxiang
	 * @date 2016-7-9 STORY #30775 证券行情映射节假日万分收益
	 * @return
	 */
	public String getLastHdayQjljwfsySql(){
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.C_KM_NAME AS C_QJLJWFSY FROM T_R_FR_ASTSTAT A");
		buf.append(" WHERE A.C_PORT_CODE = ? AND A.D_ASTSTAT = ? ");
		buf.append(" AND A.C_KEY_CODE = 'QJLJWFSY' AND (CASE WHEN ");
		buf.append(" INSTR(C_KM_CODE, '_', 1) = 0 THEN ' ' ELSE ");
		buf.append(" SUBSTR(C_KM_CODE, INSTR(C_KM_CODE, '_', 1) + 1) END) = ? ");
        return buf.toString();	
	}
	
	/**
	 * 根据库存数据表的seccode list查询 证券行情映射表 的  标的组合代码
	 * add baoql 20190827 
	 * STORY #75718 自动化优化：增加智能映射方式 
	 * @return
	 * @throws Exception
	 */
	public String getPortCodeBySecCodeListSql() throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT DISTINCT P.C_PORT_CODE FROM T_P_AB_PORT_SEC P                 ");
		sqlBuff.append(" WHERE P.D_BEGIN<=? AND P.D_END>=?                                    ");
		sqlBuff.append(" AND EXISTS(SELECT * FROM TABLE(?) WHERE TRIM(COLUMN_VALUE) = P.C_SEC_CODE)  ");
        return sqlBuff.toString();		
	}
}
