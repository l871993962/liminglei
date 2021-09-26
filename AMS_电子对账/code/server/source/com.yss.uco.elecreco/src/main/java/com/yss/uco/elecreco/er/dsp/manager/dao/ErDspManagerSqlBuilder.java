package com.yss.uco.elecreco.er.dsp.manager.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

public class ErDspManagerSqlBuilder implements SQLBuilder {

	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildDeleteSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(ErDspManagerTableName.info);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(ErDspManagerColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(ErDspManagerTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getSequenceName(ErDspManagerTableName.info);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.*  from ( ");
		sb.append(" select A.*,P.c_port_name ");
		sb.append(" from ( ");
		sb.append(getSelectItem()).append(getFrom());
		sb.append(" union ").append(getSelectItem()).append(getFromGroup());
		sb.append(" ) A ");
		sb.append(" left join ");
		sb.append(" ( ");
		sb.append(" SELECT a.c_port_code as c_port_code, ");
		sb.append(" a.c_port_name as c_port_name ");
		sb.append(" FROM T_P_AB_PORT a ");
		sb.append(" union all ");
		sb.append(" select b.c_group_code as c_port_code, ");
		sb.append(" b.c_group_name as c_port_name ");
		sb.append(" from t_p_ab_group b ");
		sb.append(" ) p ");
		sb.append(" on p.c_port_code = a.c_port_code ");
		//STORY #108210 净值确认的提示不要因为有不一致的条数提示什么净值确认失败，我们实际业务就是所有产品都有不一致的条数的，由于科目差异没办法全部一致，天天弹窗烦死了
		sb.append("  union ");
		sb.append(getCommItem());
		sb.append(getCommNameCol());
		sb.append(getFromComm());
		sb.append(" ) A ");
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		if (valueFieldbuf.length() > 0) {
			sb.append(" WHERE ").append(valueFieldbuf);
		}
		sb.append(" order by N_CHECK_STATE,PARAM_TYPE_LEVEL,C_DSP_NAME ");
		return sb.toString();
	}

	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));
		
		for (String fieldedName : paraNameList) {
			if (fieldedName.equalsIgnoreCase("C_DSP_NAME")) {
				valueFieldbuf.append(" A.C_DSP_NAME = ? AND ");
			} else if (fieldedName.equalsIgnoreCase("C_DV_PARAM_TYPE")) {
				valueFieldbuf.append(" A.C_DV_PARAM_TYPE in ('TEMPLATE_PARAME',?) AND ");
			}else if(fieldedName.equalsIgnoreCase("C_PORT_CLS_CODE")){
				valueFieldbuf.append(" A.C_PORT_CLS_CODE = ? AND ");
			}else if(fieldedName.equalsIgnoreCase("ARRAY_C_DSP_CODE")){
				valueFieldbuf.append(" A.C_DSP_CODE IN(SELECT * FROM TABLE(?)) AND ");
			}else if(fieldedName.equalsIgnoreCase("ARRAY_PORT_CODE")){
				valueFieldbuf.append(" A.c_port_code IN(SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		StringBuffer sb = new StringBuffer();
		sb.append(" select count(*) ");
		sb.append(" from ( ");
		sb.append(getSelectItem()).append(getFrom());
		sb.append(" union ").append(getSelectItem()).append(getFromGroup());
		sb.append(" union ").append(getCommItem()).append(getFromComm());
		sb.append(" ) A ");
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		if (valueFieldbuf.length() > 0) {
			sb.append(" WHERE ").append(valueFieldbuf);
		}
		return sb.toString();
	}
	
	private String getSelectItem()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then A.C_DSP_CODE else B.C_DSP_CODE end as C_DSP_CODE , ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then A.C_DV_PLAT_VALUE else B.C_DV_PARAMS_VALUE end as C_DV_PARAMS_VALUE , ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then 1 else B.N_CHECK_STATE end as N_CHECK_STATE, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then to_date('1900-01-01','yyyy-MM-dd') else B.D_BEGIN end as D_BEGIN, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then to_date('9998-12-31','yyyy-MM-dd') else B.D_END end as D_END, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then 'TEMPLATE_PARAME' else B.C_DV_PARAM_TYPE end as C_DV_PARAM_TYPE, ");
		sb.append(" case when trim(B.C_PORT_CODE) is null then A.C_PORT_CODE else B.C_PORT_CODE end as C_PORT_CODE, ");
		sb.append(" case when B.C_DV_PARAM_TYPE = 'PORT_PARAM_CUSTOM'  then '1' when B.C_DV_PARAM_TYPE = 'GROUP_PARAM_CUSTOM'  then '2' else '3' end as PARAM_TYPE_LEVEL, ");
		//模板表
		sb.append(" A.C_DSP_NAME, ");
		sb.append(" A.C_DSP_VALUE_TYPE, ");
		sb.append(" A.C_DV_TYPE, ");
		sb.append(" A.C_DV_PLAT_VALUE, ");
		sb.append(" A.C_DESC, ");
		sb.append(" A.C_DS_TPYE, ");
		//自定义参数表
		sb.append(" B.C_IDEN, ");
		sb.append(" B.C_PORT_CLS_CODE, ");
		sb.append(" B.C_UPDATE_BY, ");
		sb.append(" B.C_UPDATE_TIME, ");
		sb.append(" B.C_CHECK_BY, ");
		sb.append(" B.C_CHECK_TIME ");
		return sb.toString();
	}
	
	private String getFrom()
	{
		StringBuffer sb = new StringBuffer();
		//sb.append(" select * ");
		sb.append(" from (SELECT A.*, B.COLUMN_VALUE as C_PORT_CODE FROM T_D_ER_DSP_PARA A join table(?) B on trim(B.COLUMN_VALUE) is not null WHERE A.C_PARA_TPYE = 'PRIVATE') A ");
		sb.append(" left join T_D_ER_DSP_VALUE B on (B.C_DSP_CODE = A.C_DSP_CODE and ");
		sb.append(" 	B.C_PORT_CODE = A.C_PORT_CODE ) ");
//		sb.append(" 	(B.C_PORT_CODE = A.C_PORT_CODE or ");
//		sb.append("  	B.C_PORT_CODE in ");
//		sb.append(" 	(SELECT C_GROUP_CODE FROM T_P_AB_GROUP_RELA where C_PORT_CODE = A.C_PORT_CODE))) ");
		return sb.toString();
	}
	
	private String getFromGroup()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" from (SELECT A.*, B.COLUMN_VALUE as C_PORT_CODE FROM T_D_ER_DSP_PARA A join table(?) B on trim(B.COLUMN_VALUE) is not null WHERE A.C_PARA_TPYE = 'PRIVATE') A ");
		sb.append(" join T_D_ER_DSP_VALUE B on (B.C_DSP_CODE = A.C_DSP_CODE and  ");
		sb.append("  	B.C_PORT_CODE in ");
		sb.append(" 	(SELECT C_GROUP_CODE FROM T_P_AB_GROUP_RELA where C_PORT_CODE = A.C_PORT_CODE)) ");
		return sb.toString();
	}
	
	private String getCommItem()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" select ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then A.C_DSP_CODE else B.C_DSP_CODE end as C_DSP_CODE , ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then A.C_DV_PLAT_VALUE else B.C_DV_PARAMS_VALUE end as C_DV_PARAMS_VALUE , ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then 1 else B.N_CHECK_STATE end as N_CHECK_STATE, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then to_date('1900-01-01','yyyy-MM-dd') else B.D_BEGIN end as D_BEGIN, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then to_date('9998-12-31','yyyy-MM-dd') else B.D_END end as D_END, ");
		sb.append(" case when trim(B.C_DSP_CODE) is null then 'TEMPLATE_PARAME' else B.C_DV_PARAM_TYPE end as C_DV_PARAM_TYPE, ");
		sb.append(" ' ' as C_PORT_CODE, ");
		sb.append(" case when B.C_DV_PARAM_TYPE = 'COMM_PARAM_CUSTOM'  then '1' else '2' end as PARAM_TYPE_LEVEL, ");
		//模板表
		sb.append(" A.C_DSP_NAME, ");
		sb.append(" A.C_DSP_VALUE_TYPE, ");
		sb.append(" A.C_DV_TYPE, ");
		sb.append(" A.C_DV_PLAT_VALUE, ");
		sb.append(" A.C_DESC, ");
		sb.append(" A.C_DS_TPYE, ");
		//自定义参数表
		sb.append(" B.C_IDEN, ");
		sb.append(" B.C_PORT_CLS_CODE, ");
		sb.append(" B.C_UPDATE_BY, ");
		sb.append(" B.C_UPDATE_TIME, ");
		sb.append(" B.C_CHECK_BY, ");
		sb.append(" B.C_CHECK_TIME ");
		return sb.toString();
	}
	
	private String getCommNameCol(){
		return " ,' ' AS c_port_name ";
	}
	
	private String getFromComm()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(" FROM (SELECT A.*,'' as C_PORT_CODE FROM T_D_ER_DSP_PARA A WHERE A.C_PARA_TPYE = 'PUBLIC')A left join T_D_ER_DSP_VALUE B on (B.C_DSP_CODE = A.C_DSP_CODE AND TRIM(B.C_PORT_CODE) IS NULL) ");
		return sb.toString();
	}
	
}
