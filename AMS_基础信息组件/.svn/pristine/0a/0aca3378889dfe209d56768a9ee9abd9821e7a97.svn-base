package com.yss.ams.base.information.modules.sys.convertdict.zhzd.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;

/**
 * 转换字典  操作数据库SQL生成工具类
 * @author 马向峰  拆分   2017.0527
 *
 */
public class ZhzdSqlBuilder implements SQLBuilder {

	@Override
	public String buildDeleteSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildInsertSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildSelectSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbnameresolver,
			String s) {
		return dbnameresolver.getColumnName(ZhzdColumnName.valueOf(s));
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getLogSequenceName(ZhzdTableName.corporg);
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		try {
			for (String fieldName : paraNameList) {
				if ("C_GROUP_CODE_P".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE_P = ? AND ");
				} else if ("C_GROUP_CODE".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE = ? AND ");
				}
			}

			if (valueFieldbuf.length() > 0) {
				StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
			}

			buf.append(" select count(*) AS CNT ");
			buf
					.append(" from T_V_D_GROUP_DETAIL b join (SELECT C_GROUP_CODE_P,C_GROUP_CODE FROM T_V_D_GROUP A ");

			if (valueFieldbuf.length() > 0) {
				buf.append(" START WITH ").append(valueFieldbuf);
				buf
						.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P ");
			}

			buf.append(") C ON B.C_GROUP_CODE = C.C_GROUP_CODE  ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		StringBuffer whereStringbuf = new StringBuffer();
		try {
			for (String fieldName : paraNameList) {
				if ("C_GROUP_CODE_P".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE_P = ? AND ");
				} else if ("C_GROUP_CODE".equals(fieldName)) {
					valueFieldbuf.append(" A.C_GROUP_CODE = ? AND ");
				}
			}
			whereStringbuf.append(SqlUtil.getCheckStateClause(paraNameList, "B"));
			for (String fieldName : paraNameList) {
				 if ("ARRAY_C_IDEN".equals(fieldName))
				{
					 whereStringbuf.append(" B.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
				}
			}

			if (valueFieldbuf.length() > 0) {
				StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
			}
			if (whereStringbuf.length() > 0) {
				StringUtil.delLastSplitMark(whereStringbuf, " AND ");
			}
//			this.setWhereSql(valueFieldbuf, paraNameList);

			buf.append(" SELECT B.*,c.c_dv_scene ");
			buf.append(" FROM T_V_D_GROUP_DETAIL B JOIN (SELECT C_GROUP_CODE_P , C_GROUP_CODE,A.c_dv_scene FROM T_V_D_GROUP A ");
			
			if (valueFieldbuf.length() > 0) {
				buf.append(" START WITH ").append(valueFieldbuf);
				buf.append(" connect by prior a.C_GROUP_CODE = a.C_GROUP_CODE_P ");
			}

			buf.append(" ) C ON B.C_GROUP_CODE = C.C_GROUP_CODE ");
			if(whereStringbuf.length()>0){
				buf.append(" where " + whereStringbuf);
			}
			buf.append("   ORDER BY N_CHECK_STATE, B.C_S_CODE ");
			retSql = buf.toString();
		} catch (Exception ex) {
			throw ex;
		} finally {
			StringUtil.clearStringBuffer(buf);
		}
		return retSql;
	}

	public String getAllDataSql() {
		StringBuffer buf = new StringBuffer();
		buf.append("SELECT * FROM T_V_D_GROUP_DETAIL");
		return buf.toString();
	}

	/**
	 * 根据场景类型生成查找转换字典的SQL
	 * @return SQL
	 */
	public String getSqlByType() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_V_D_GROUP_DETAIL A JOIN T_V_D_GROUP B");
		buf.append(" ON A.C_GROUP_CODE = B.C_GROUP_CODE WHERE B.C_DV_SCENE IN(SELECT * FROM TABLE(?))");
		return buf.toString();
	}
	
	/**
	 * 根据场景类型生成查找转换字典的SQL
	 * @return SQL
	 */
	public String getZHZDDataConv() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_V_D_GROUP_DETAIL A  where c_group_code in (SELECT * FROM TABLE(?))");
		return buf.toString();
	}
	
	/**
	 * 根据场景类型生成查找转换字典的SQL
	 * @return SQL
	 */
	public String getZHZDDataByCode() {
		StringBuffer buf = new StringBuffer();
		buf.append(" SELECT A.* FROM T_V_D_GROUP_DETAIL A  where A.C_S_CODE in (SELECT * FROM TABLE(?))");
		return buf.toString();
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ZhzdTableName.recycle);
	}

	@Override
	public String getTableName(DBNameResolver dbnameresolver) {
		return dbnameresolver.getTableName(ZhzdTableName.corporg);
	}
	
	private void setWhereSql(StringBuffer valueFieldbuf,
			List<String> paraNameList) {
		valueFieldbuf.append(SqlUtil.getCheckStateClause(paraNameList, "A"));

//		for (String fieldedName : paraNameList) {
//			if (fieldedName.equals("ARRAY_C_PD_CODE")) {
//				valueFieldbuf.append(" A.C_PD_CODE IN (SELECT * FROM TABLE(?)) AND ");
//			}
//		}
//		
		for (String fieldName : paraNameList) {
			if ("C_GROUP_CODE_P".equals(fieldName)) {
				valueFieldbuf.append(" A.C_GROUP_CODE_P = ? AND ");
			} else if ("C_GROUP_CODE".equals(fieldName)) {
				valueFieldbuf.append(" A.C_GROUP_CODE = ? AND ");
			} else if ("SearchUnAudit".equals(fieldName)){
				valueFieldbuf.append(" A.N_CHECK_STATE = ? AND ");
			} else if (fieldName.equals("ARRAY_C_IDEN"))
			{
				valueFieldbuf.append(" A.C_IDEN IN (SELECT * FROM TABLE(?)) AND ");
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}
	
	/**
	 * 根据场景和源值生成查找转换值的SQL
	 * @return SQL
	 */
	public String specificSceneConvert() {
		StringBuffer buf = new StringBuffer();
		buf.append(" select dict.c_group_code, dict.c_s_code, dict.c_t_code "); 
		buf.append(" from T_V_D_GROUP_DETAIL dict ");
		buf.append(" join (select c_group_code, c_dv_scene ");
		buf.append(" from t_v_d_group where c_dv_scene = ?) dg ");
		buf.append(" on dict.c_group_code = dg.c_group_code ");
		buf.append(" where c_s_code = ? ");
		return buf.toString();
	}
	/**
	 * comment：根据转换字典代码批量获取转换字典<br>
	 * src：STORY #60135 嘉实基金-系统间升级交互机制 <br>
	 * author：shijian@ysstech.com<br>
	 * date：2018年12月21日<br>
	 */
	public String getConvertMapByGroup() {
		StringBuffer sb = new StringBuffer();
		sb.append(" select A.C_GROUP_CODE, A.C_S_CODE, A.C_T_CODE ");
		sb.append("   from T_V_D_GROUP_DETAIL A ");
		sb.append("   join T_V_D_GROUP B ");
		sb.append("     on A.C_GROUP_CODE = B.C_GROUP_CODE ");
		sb.append("  where A.C_GROUP_CODE in (select ID_D_AC_TD_IVT from R_D_FEE_ID) ");
		sb.append("    and A.N_CHECK_STATE = 1 ");
		sb.append("    and B.N_CHECK_STATE = 1 ");
		return sb.toString();
	}
}
