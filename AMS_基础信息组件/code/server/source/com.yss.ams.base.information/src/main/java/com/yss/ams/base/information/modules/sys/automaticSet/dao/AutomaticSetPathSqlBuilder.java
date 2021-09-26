package com.yss.ams.base.information.modules.sys.automaticSet.dao;

import java.util.List;

import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;

/**
 * 自动化业务设置sqlbuilder
 * 
 * @ClassName: AutomaticSetPathSqlBuilder
 * @date 2021年06月01日
 * @Stroy106083
 * @author zhuziqing
 */
public class AutomaticSetPathSqlBuilder implements SQLBuilder {

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
		return dbNameResolver.getTableName(AutomaticSetPathTableName.userInfo);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver, String name) {
		return dbNameResolver.getColumnName(AutomaticSetPathColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getTableName(AutomaticSetPathTableName.recycle);
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		return dbNameResolver.getLogSequenceName(AutomaticSetPathTableName.userInfo);
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" SELECT A.*,B.C_INDEX_NAME,B.N_ORDER AS C_INDEX_ORDER,B.C_KEY_NAME AS C_VA_ALIAS");
		sqlBuff.append(" FROM ");
		sqlBuff.append(AutomaticSetPathTableName.userInfo + " A ");
		sqlBuff.append("LEFT JOIN T_S_INDEX B ON A.C_INDEX_CODE = B.C_INDEX_CODE");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" WHERE ").append(valueFieldbuf);
		}
		sqlBuff.append(" ORDER BY A.C_INDEX_CODE DESC,A.C_PORT_CODE ");
		return sqlBuff.toString();
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 条件查询 
	 * 
	 * @param valueFieldbuf
	 * @param paraNameList
	 */
	private void setWhereSql(StringBuffer valueFieldbuf, List<String> paraNameList) {
		for (String fieldedName : paraNameList) {
			if (fieldedName.equals("ARRAY_C_CHANEL_CODE")) {
				valueFieldbuf.append(" A.C_CHANEL_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_PRODUCT_CODE")) {
				valueFieldbuf.append(" A.C_PRODUCT_NAME IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_PORT_CODE")) {
				valueFieldbuf.append(" A.C_PORT_CODE IN (SELECT * FROM TABLE(?)) AND ");
			} else if (fieldedName.equals("ARRAY_C_INDEX_CODE")) {
				valueFieldbuf.append(" A.C_INDEX_CODE IN (SELECT * FROM TABLE(?)) AND ");
			}else if (fieldedName.equals("CHECK_STATE")) {
				valueFieldbuf.append(" A.N_CHECK_STATE = ? AND "); 
			}else if (fieldedName.equals("C_BUSINESS_TYPE_CODE")) {
				valueFieldbuf.append(" A.C_BUSINESS_TYPE_CODE = ? AND "); 
			}
		}

		if (valueFieldbuf.length() > 0) {
			StringUtil.delLastSplitMark(valueFieldbuf, " AND ");
		}
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList) throws Exception {
		StringBuffer sqlBuff = new StringBuffer();
		StringBuffer valueFieldbuf = new StringBuffer();
		this.setWhereSql(valueFieldbuf, paraNameList);
		sqlBuff.append(" select count(*)  ");
		sqlBuff.append(" from ");
		sqlBuff.append(AutomaticSetPathTableName.userInfo + " A ");
		if (valueFieldbuf.length() > 0) {
			sqlBuff.append(" where ").append(valueFieldbuf);
		}
		return sqlBuff.toString();
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 
	 * @return
	 */
	public String getAllProductTypeSql() {
		return "SELECT DISTINCT  A.C_PRODUCT_NAME FROM T_P_AUTOMATIC_SET_INTER_TYPE A ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 查询所有的产品业务分类、接口代码
	 * 
	 * @return
	 */
	public String getInterfaceClass() {
		return " SELECT DISTINCT A.C_INTERFACE_P_ID,A.C_INTERFACE_CODE,A.C_INTERFACE_NAME, A.C_PRODUCT_NAME, A.N_ORDER FROM T_P_AUTOMATIC_SET_INTER_TYPE A ORDER BY A.N_ORDER ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 删除表数据
	 * 
	 * @return
	 */
	public String getDeleteDataByTypeSql() {
		return " DELETE FROM T_P_AUTOMATIC_SET_INTER_TYPE A ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 新增 业务分类接口关系数据
	 * 
	 * @return
	 */
	public String getInsertDataListSql() {
		return " INSERT INTO T_P_AUTOMATIC_SET_INTER_TYPE (N_ORDER,C_PRODUCT_NAME,C_INTERFACE_P_ID,C_INTERFACE_CODE,C_INTERFACE_NAME,C_UPDATE_BY,C_UPDATE_TIME) VALUES (?,?,?,?,?,?,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS')) ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 查询业务分类接口关系数据
	 * 
	 * @return
	 */
	public String getProductType() {
		return "SELECT DISTINCT A.C_INTERFACE_P_ID, A.C_INTERFACE_CODE,A.C_PRODUCT_NAME,A.N_ORDER FROM T_P_AUTOMATIC_SET_INTER_TYPE A ORDER BY A.N_ORDER";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 通过分类查询对应的接口信息
	 * 
	 * @return
	 */
	public String getInterfaceByName(List<String> productName) {
		return "SELECT A.C_PRODUCT_NAME,A.C_INTERFACE_P_ID, A.C_INTERFACE_CODE FROM T_P_AUTOMATIC_SET_INTER_TYPE A WHERE A.C_PRODUCT_NAME IN (SELECT * FROM TABLE(?)) ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * 
	 * @return
	 */
	public String getDeleteSetDataByTypeSql() {
		return " DELETE FROM T_P_AUTOMATIC_SET_PATH A WHERE A.C_PRODUCT_NAME = ? AND A.C_CHANEL_CODE = ? AND A.C_PORT_CODE = ? AND A.C_INTERFACE_CODE = ? AND A.C_INTERFACE_PATH = ?  AND A.N_CHECK_STATE = 0 AND A.C_BUSINESS_TYPE_CODE != 'GZZB' ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 主界面新增 
	 * 
	 * @return
	 */
	public String getSaveDataListSql() {
		StringBuffer buf = new StringBuffer();
		buf.append(" INSERT INTO T_P_AUTOMATIC_SET_PATH (C_IDEN,C_PORT_CODE,C_PRODUCT_CODE,C_PRODUCT_NAME,C_CHANEL_CODE,C_CHANEL_TYPE, ");
		buf.append(" C_INTERFACE_GROUP,C_INTERFACE_CODE,C_INTERFACE_NAME,C_INTERFACE_PATH,C_INTERFACE_P_ID,C_UPDATE_BY,C_UPDATE_TIME) ");
		buf.append(" SELECT TO_CHAR(SEQU_P_AUTOMATIC_SET_PATH.NEXTVAL) AS C_IDEN, ");
		buf.append(" T.* FROM (SELECT ? AS C_PORT_CODE,? AS C_PRODUCT_CODE,? AS C_PRODUCT_NAME, ");
		buf.append(" ? AS C_CHANEL_CODE,? AS C_CHANEL_TYPE,? AS C_INTERFACE_GROUP, ");
		buf.append(" ? AS C_INTERFACE_CODE,? AS C_INTERFACE_NAME,? AS C_INTERFACE_PATH,? AS C_INTERFACE_P_ID, ");
		buf.append(" ? AS C_UPDATE_BY,TO_CHAR(SYSDATE, 'YYYYMMDD HH24:MI:SS') AS C_UPDATE_TIME FROM DUAL) T  ");
		buf.append(" WHERE NOT EXISTS ");
		buf.append(" (SELECT 1 FROM T_P_AUTOMATIC_SET_PATH TT ");
		buf.append("  WHERE TT.C_PORT_CODE = T.C_PORT_CODE AND TT.C_PRODUCT_NAME = T.C_PRODUCT_NAME ");
		buf.append(" AND TT.C_CHANEL_CODE = T.C_CHANEL_CODE  AND TT.C_INTERFACE_PATH = T.C_INTERFACE_PATH ");
		buf.append(" AND TT.N_CHECK_STATE = 1) ");
		return buf.toString();
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 通过分组ID、接口ID查询外部渠道组合路径设置返回给框架
	 * 
	 * @return
	 */
	public String queryInterfaceChannelSql() {
		return "SELECT DISTINCT A.C_PRODUCT_CODE,A.C_PRODUCT_NAME,A.C_CHANEL_CODE,A.C_CHANEL_TYPE,A.C_INTERFACE_P_ID,A.C_INTERFACE_GROUP, A.C_INTERFACE_PATH, A.C_PORT_CODE, A.C_INTERFACE_CODE,A.C_INTERFACE_NAME FROM T_P_AUTOMATIC_SET_PATH A"
				+ " WHERE A.C_INTERFACE_P_ID = ? AND A.C_INTERFACE_CODE = ? AND A.N_CHECK_STATE = 1 AND TRIM(A.C_INTERFACE_PATH) IS NOT NULL ";
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值） 通过组合代码查询外部渠道组合路径设置返回给框架
	 * 
	 * @return
	 */
	public String queryInterfaceChannelByPortCodeSql() {
		return "SELECT DISTINCT A.C_PRODUCT_CODE,A.C_PRODUCT_NAME,A.C_CHANEL_CODE,A.C_CHANEL_TYPE,A.C_INTERFACE_P_ID,A.C_INTERFACE_GROUP, A.C_INTERFACE_PATH, A.C_INTERFACE_CODE,A.C_INTERFACE_NAME FROM T_P_AUTOMATIC_SET_PATH A"
				+ " WHERE A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 AND TRIM(A.C_INTERFACE_PATH) IS NOT NULL ";
	}

	/**
	 * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
	 * 获取所有的估值指标
	 * @return
	 */
	public String getAllIndex() {
		return "SELECT A.N_ORDER AS C_INDEX_ORDER,A.C_INDEX_CODE,A.C_INDEX_NAME,A.C_KEY_NAME FROM T_S_INDEX A WHERE A.C_INDEX_SHOW = 'INDEX_ENAB_SHOW' AND A.N_STATE = 1 ";
	}

	/**
	 * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
	 * 新增前先删除数据
	 * @return
	 */
	public String getDeleteSetData() {
		return " DELETE FROM T_P_AUTOMATIC_SET_PATH A WHERE A.C_INDEX_CODE = ? AND A.C_VA_TIME = ? AND A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 0 ";
	}

	/**
	 * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
	 * 新增
	 * @return
	 */
	public String getSaveListSql() {
		return " INSERT INTO T_P_AUTOMATIC_SET_PATH (C_IDEN,C_PORT_CODE,C_INDEX_CODE,"
				+ "C_VA_TIME,C_UPDATE_BY,C_BUSINESS_TYPE_CODE,C_UPDATE_TIME) VALUES ("
				+ "TO_CHAR(SEQU_P_AUTOMATIC_SET_PATH.NEXTVAL),?,?,?,?,?,TO_CHAR(SYSDATE,'YYYYMMDD HH24:MI:SS')) ";
	}

	/**
	 * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
	 * 查询是否数据库中已存在某已审核的数据
	 * @return
	 */
	public String queryParamCheckSql() {
		return "SELECT * FROM T_P_AUTOMATIC_SET_PATH A WHERE A.C_INDEX_CODE = ? AND A.C_VA_TIME = ? AND A.C_PORT_CODE = ? AND A.N_CHECK_STATE = 1 ";
	}

	/**
	 * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 查询所有可参照的组合
	 * @return
	 */
	public String getRePortCodeListSql() {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT DISTINCT B.C_PORT_CODE,B.C_PORT_NAME,C.C_DAT_CODE, C.C_DAT_NAME ");
		sqlBuff.append(" FROM T_P_AUTOMATIC_SET_PATH A ");
		sqlBuff.append(" LEFT JOIN T_P_AB_PORT B ON A.C_PORT_CODE = B.C_PORT_CODE ");
		sqlBuff.append(" LEFT JOIN T_S_DAT_ASS_TYPE C ON C.C_DAT_CODE = B.C_DAT_CODE ");
		sqlBuff.append(" WHERE A.N_CHECK_STATE = '1' AND A.C_BUSINESS_TYPE_CODE != 'GZZB' ");
		return sqlBuff.toString();
	}

	/**
	 * STORY #107189 【东证资管】自动化业务设置-外部渠道组合路径设置，组合维护的路径能同步或复制给其他组合。
     * 通过参照组合代码和产品业务分类查询对应存的接口信息
	 * @param productName 
	 * @param portCode 
	 * @return
	 */
	public String queryByCodeAndNameSql(List<String> productName) {
		StringBuffer sqlBuff = new StringBuffer();
		sqlBuff.append(" SELECT DISTINCT A.C_PORT_CODE,A.C_PRODUCT_NAME,A.C_INTERFACE_CODE,A.C_INTERFACE_NAME,A.C_INTERFACE_P_ID,A.C_INTERFACE_PATH,B.C_GROUP_NAME ");
		sqlBuff.append(" FROM ");
		sqlBuff.append(AutomaticSetPathTableName.userInfo + " A ");
		sqlBuff.append(" LEFT JOIN T_V_IMP_GROUP B ON A.C_INTERFACE_P_ID = B.C_GROUP_CODE ");
		sqlBuff.append(" WHERE ");
		sqlBuff.append(" A.C_PORT_CODE = ? AND ");
		if (productName != null && productName.size() > 0) {
			sqlBuff.append(" A.C_PRODUCT_NAME IN (SELECT * FROM TABLE(?)) AND ");
		}
		sqlBuff.append(" A.N_CHECK_STATE = 1 AND A.C_BUSINESS_TYPE_CODE != 'GZZB' ORDER BY A.C_INTERFACE_CODE ");
		return sqlBuff.toString();
	}
	
	
}
