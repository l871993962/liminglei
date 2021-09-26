package com.yss.ams.sec.information.modules.mp.secTransferPara.dao;

import java.util.List;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.mvc.dao.sql.DBNameResolver;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;

/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecTransferParaSqlBuilder implements SQLBuilder{

	private Logger logger = LogManager.getLogger(this.getClass());
	
	@Override
	public String buildInsertSql(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String buildUpdateSql(DBNameResolver dbNameResolver) {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldBuf = new StringBuffer();
		try {

			buf.append("UPDATE T_P_SV_SUSPENDED_COND SET C_ITEM_CODE=?,C_ITEM_NAME=?,C_ITEM_VALUE = ?,C_LOGICAL_JUDGMENT =?,C_VALUE_TYPE=? WHERE C_IDEN = ? ");

			retSql = buf.toString();

		} catch (Exception e) {
			logger.log("证券代码转换：证券代码转换信息出错", e);
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldBuf);
		}
		return retSql;
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
		return dbNameResolver.getTableName(SecTransferParaTableName.secTransferPara);
	}

	@Override
	public String getColumnNameByProperty(DBNameResolver dbNameResolver,
			String name) {
		return dbNameResolver.getColumnName(SecTransferParaColumnName.valueOf(name));
	}

	@Override
	public String getRecycleTableName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getLogSequenceName(DBNameResolver dbNameResolver) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getQueryConditionSql(List<String> paraNameList)
			throws Exception {
		String retSql = "";
		StringBuffer buf = new StringBuffer();
		StringBuffer valueFieldBuf = new StringBuffer();
		//STORY #69753 业务基础组件对FAST平台表结构的解耦  
		IVocCacheDataService vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService"); 
        List<Vocabulary> vocList = vocService.getDataListByTypes(new String [] {"SBLSHGZ"});
        String sqltmp = "";
		
        for (int i = 0; i < vocList.size(); i++) {
        	 sqltmp += "select '" + vocList.get(i).getC_DV_CODE() + "' as c_dv_code ,'" + vocList.get(i).getC_DV_NAME() + "' as c_dv_name from dual" + "  UNION ALL ";
		}
		int i = sqltmp.lastIndexOf("UNION ALL");
		String sql= sqltmp.substring(i, sqltmp.length()-1);
		try {
			buf.append(" select b.c_iden, ");
			buf.append(" a.c_dv_code          as c_item_code, ");
			buf.append(" a.c_dv_name          as c_item_name, ");
			buf.append(" b.c_item_value, ");
			buf.append(" b.c_logical_judgment ");
			buf.append(" from ( ");
			buf.append(sql);
			buf.append(" ) a ");
//			buf.append(" from (select * from t_s_Dv_Voc where c_DV_code = 'SBLSHGZ') a ");
			buf.append(" left join T_D_MP_SEC_TRANSFERPARA b ");
			buf.append(" on a.c_dv_code = b.c_item_code order by b.c_iden ");
			retSql = buf.toString();
		} catch (Exception e) {
			throw e;
		} finally {
			StringUtil.clearStringBuffer(buf);
			StringUtil.clearStringBuffer(valueFieldBuf);
		}
		return retSql;
	}

	@Override
	public String getQueryConditionCountSql(List<String> paraNameList)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
