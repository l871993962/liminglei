package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
public class KmRelaSingleRecordSqlBuilder implements SQLBuilder  {

	public String buildDeleteSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildInsertSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildSelectSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String buildUpdateSql(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getColumnNameByProperty(DBNameResolver dbnameresolver,String s) {
		return dbnameresolver.getColumnName(KmRelaSingleReocrdColumnName.valueOf(s));
	}

	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getTableName(DBNameResolver dbnameresolver) {
		return null;
	}

	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return null;
	}
	
	public String getKmMapTable()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(" ( select C_IDEN,C_IDEN_RELA,C_PORT_CODE,C_KM_CODE,C_KM_NAME,C_TGH_CODE,C_DV_KM_CLS,C_DV_KM_SCOPE from T_D_ER_REVE_KM_MAP ) ");
		return buf.toString();
	}
	
	public String getSelectItem()
	{
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.*, ");
		buf.append(" B.C_IDEN AS C_IDEN_KMMAP, ");
		buf.append(" B.C_IDEN_RELA AS C_IDEN_RELA_KMMAP, ");
		buf.append(" B.C_PORT_CODE AS C_PORT_CODE_KMMAP, ");
		buf.append(" B.C_KM_CODE AS C_KM_CODE_KMMAP, ");
		buf.append(" B.C_KM_NAME AS C_KM_NAME_KMMAP, ");
		buf.append(" B.C_TGH_CODE AS C_TGH_CODE_KMMAP, ");
		buf.append(" B.C_DV_KM_CLS AS C_DV_KM_CLS_KMMAP, ");
		buf.append(" B.C_DV_KM_SCOPE AS C_DV_KM_SCOPE_KMMAP");
		return buf.toString();
	}
	
	public String getCommSql(String item,String Atable,String Btable)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(item);
		buf.append(" from  ");
		buf.append(Atable);
		buf.append(" A ");
		buf.append(" left join ");
		buf.append(Btable);
		buf.append("  B on A.C_IDEN = B.C_IDEN_RELA  ");
		return buf.toString();
	}
	/**
	 * 查找特定组合的和组合，托管行都为空的
	 * @param item
	 * @return
	 */
	public String getCommAndPortSql(String item)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(item);
		buf.append(" from  ");
		buf.append(" T_D_ER_REVE_KM_RELA ");
		buf.append(" A ");
		buf.append(" left join ");
		buf.append(" T_D_ER_REVE_KM_MAP ");
		buf.append("  B on A.C_IDEN = B.C_IDEN_RELA  ");
		buf.append(" where ( A.C_PORT_CODE = ?  ");
		buf.append(" or ( trim(A.C_PORT_CODE) is null and trim(A.C_TGH_CODE) is null )) ");
		buf.append(" and A.N_CHECK_STATE > 0  ");
		return buf.toString();
	}
	
	public String getCommCompareSql(String item)
	{
		StringBuffer buf = new StringBuffer();
		buf.append(item);
		buf.append(" from  ");
		buf.append(" T_D_ER_REVE_KM_RELA ");
		buf.append(" A ");
		buf.append(" left join ");
		buf.append(" T_D_ER_REVE_KM_MAP ");
		buf.append("  B on A.C_IDEN = B.C_IDEN_RELA  ");
		buf.append(" where ( A.C_PORT_CODE = ? or trim(A.C_PORT_CODE) is null ) ");
		buf.append(" and ( A.C_TGH_CODE = ? or trim(A.C_TGH_CODE) is null ) ");
		buf.append(" and A.N_CHECK_STATE > 0  ");
		return buf.toString();
	}
	
	public String getCommSql(List<String> paraNameList)
	{
		return getCommSql(getSelectItem(), getKmRelaTable(paraNameList), getKmMapTable());
	}
	/**
	 * 科目映射主表，搜素条件限制在主表
	 * @return
	 */
	public String getKmRelaTable(List<String> paraNameList)
	{
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		StringBuffer buf = new StringBuffer();
		buf.append(" ( ");
		buf.append(" select A.* from T_D_ER_REVE_KM_RELA A  ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ) ");
		return buf.toString();
	}

	/**
	 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时以_RELA结尾,或者不加任何后缀
	 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时区分内外科目，内部以_INNER结尾,外部以_OUT结尾
	 * @author lwz
	 *
	 */
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		buf.append(" select COUNT(A.C_IDEN) AS CNT from ( ");
		//以映射关系作为条数，多对多为一条记录
		buf.append(getKmRelaTable(paraNameList));
		buf.append(" ) A ");
		buf.append(" group by A.C_IDEN ");
		return buf.toString();
	}

	/**
	 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时以_RELA结尾,或者不加任何后缀
	 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时区分内外科目，内部以_INNER结尾,外部以_OUT结尾
	 * @author lwz
	 *
	 */
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		buf.append(" select A.* from ( ");
		//buf.append(" SELECT A.*,B.C_KM_CODE,B.C_KM_NAME,C.C_KM_CODE as C_KM_CODE_OUT,C.C_KM_NAME as C_KM_NAME_OUT,C.C_TGH_CODE as C.C_TGH_CODE_OUT FROM T_D_ER_REVE_KM_RELA A ");
		buf.append(getCommSql(paraNameList));
		buf.append(" ) A ");
		if (valueFieldbuf.length() > 0) {
			buf.append(" WHERE ").append(valueFieldbuf);
		}
		buf.append(" ORDER BY A.N_CHECK_STATE DESC,A.C_IDEN,A.C_KM_CODE_KMMAP ASC  ");
		return buf.toString();
	}
	
	/**
	 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时不加任何后缀
	 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时以_KMMAP结尾
	 * @author Lenovo
	 *
	 */
	public String getQueryConditionSql(List<String> paraNameList,String item,String ATable,String BTable) throws Exception {
		//搜素条件限制在主表T_D_ER_REVE_KM_RELA
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.* from ( ");
		buf.append(getCommSql(item,ATable,BTable));
		buf.append(" ) A ");
		buf.append(" ORDER BY A.N_CHECK_STATE DESC,A.C_IDEN,A.C_KM_CODE_KMMAP ASC  ");
		return buf.toString();
	}
	
	/**
	 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时不加任何后缀
	 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时以_KMMAP结尾
	 * @author Lenovo
	 *
	 */
	public String getPortAndCommKmMapSql(String item) {
		//搜素条件限制在主表T_D_ER_REVE_KM_RELA
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.*, ");
		buf.append(" case when C_DV_MAP_SCOPE = 'REVE_YSFW_CPYS' then 3 ");
		buf.append(" when C_DV_MAP_SCOPE = 'REVE_YSFW_TGFYS' then 2 ");
		buf.append(" else 1 end as KMMAP_LEVEL ");
		buf.append(" from ( ");
		buf.append(getCommAndPortSql(item));
		buf.append(" ) A ");
		buf.append(" ORDER BY KMMAP_LEVEL ASC  ");
		return buf.toString();
	}
	
	/**
	 * 属于T_D_ER_REVE_KM_RELA的字段属性命名时不加任何后缀
	 * 属于T_D_ER_REVE_KM_MAP的字段属性命名时以_KMMAP结尾
	 * @author Lenovo
	 *
	 */
	public String getCompareKmMapSql(String item) {
		//搜素条件限制在主表T_D_ER_REVE_KM_RELA
		StringBuffer buf = new StringBuffer();
		buf.append(" select A.*, ");
		buf.append(" case when C_DV_MAP_SCOPE = 'REVE_YSFW_CPYS' then 3 ");
		buf.append(" when C_DV_MAP_SCOPE = 'REVE_YSFW_TGFYS' then 2 ");
		buf.append(" else 1 end as KMMAP_LEVEL ");
		buf.append(" from ( ");
		buf.append(getCommCompareSql(item));
		buf.append(" ) A ");
		buf.append(" ORDER BY KMMAP_LEVEL ASC  ");
		return buf.toString();
	}

	/**
	 * 搜素条件限制在主表T_D_ER_REVE_KM_RELA
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf,List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equalsIgnoreCase("C_KM_CODE")) {//内部科目代码A.C_KM_CODE LIKE ?
				valueFieldbuf.append(" A.C_IDEN in ( SELECT C_IDEN_RELA from T_D_ER_REVE_KM_MAP where C_KM_CODE LIKE ? and C_DV_KM_SCOPE = 'REVE_KMFW_INNER' ) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_KM_NAME")) {//内部科目名称
				valueFieldbuf.append(" A.C_IDEN in ( SELECT C_IDEN_RELA from T_D_ER_REVE_KM_MAP where C_KM_NAME LIKE ? and C_DV_KM_SCOPE = 'REVE_KMFW_INNER' ) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_KM_CODE_OUT")) {//外部科目代码
				valueFieldbuf.append(" A.C_IDEN in ( SELECT C_IDEN_RELA from T_D_ER_REVE_KM_MAP where C_KM_CODE LIKE ? and C_DV_KM_SCOPE = 'REVE_KMFW_OUT' ) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_KM_NAME_OUT")) {//外部科目名称
				valueFieldbuf.append(" A.C_IDEN in ( SELECT C_IDEN_RELA from T_D_ER_REVE_KM_MAP where C_KM_NAME LIKE ? and C_DV_KM_SCOPE = 'REVE_KMFW_OUT' ) AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DV_KM_CLS")) {//科目类型
				valueFieldbuf.append(" A.C_DV_KM_CLS = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("C_DV_MAP_SCOPE")) {//映射范围
				valueFieldbuf.append(" A.C_DV_MAP_SCOPE = ? AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE")) {//托管行
				valueFieldbuf.append(" A.C_TGH_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_IDEN")) {//ID
				valueFieldbuf.append(" A.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_DV_KM_CLS")) {//ID
				valueFieldbuf.append(" A.C_DV_KM_CLS IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_TGH_CODE_OR_NULL")) {//托管机构
				valueFieldbuf.append(" ( A.C_TGH_CODE IN (SELECT * FROM TABLE(?)) or trim(A.C_TGH_CODE) is null ) AND ");
			}else if (fieldedName.equalsIgnoreCase("ARRAY_C_PORT_CODE_OR_NULL")) {//查找特定的组合代码和组合为空的
				valueFieldbuf.append(" ( A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) or trim(A.C_PORT_CODE) is null ) AND ");
			}
		}
		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
}