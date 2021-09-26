package com.yss.ams.db.upgrade.syncdata.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.InitScriptDesc;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;


/**
 * 数据同步基础数据
 * @ClassName SyncDataBaseScripImpl
 * @Description
 * @author chenyoucai@ysstech.com
 * @CreateDate 20180626
 */
public class SyncDataBaseScripImpl implements ScriptDesc{

	@Override
	public ScriptBuilder getScriptBuilder() {
		ScriptBuilder builder = new ScriptBuilder();
		//STORY57889【数据管理】数据同步、同步日志
		buildStory57889(builder);
		return builder;
	}
	
	/**
	 * add by chenyoucai 20180626 STORY57889【数据管理】数据同步、同步日志
	 * @param builder
	 */
	private void buildStory57889(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("V1.21.6")
		.addUpdateType(UpdateType.REQUEST)
		.addID("57889")	
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('productsInfo', '产品基本信息', 'SYNCFUNCODE', '数据同步模块', 0, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('tradeUnitManage', '交易单元管理', 'SYNCFUNCODE', '数据同步模块', 1, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('tradeUnitBind', '交易单元绑定', 'SYNCFUNCODE', '数据同步模块', 13, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('saleOrg', '销售机构管理', 'SYNCFUNCODE', '数据同步模块',2, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('subjectInfo', '主体信息设置', 'SYNCFUNCODE', '数据同步模块', 3, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('feeManagement', '费用信息管理', 'SYNCFUNCODE', '数据同步模块', 4, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('portRelaMan', '产品干系人', 'SYNCFUNCODE', '数据同步模块', 5, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('fundManager', '投研人员管理', 'SYNCFUNCODE', '数据同步模块', 6, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('externalPersonnel', '外部人员管理', 'SYNCFUNCODE', '数据同步模块', 7, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('importantIssues', '重大事项管理', 'SYNCFUNCODE', '数据同步模块', 8, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('contractInfo', '合同信息', 'SYNCFUNCODE', '数据同步模块', 9, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('bankAccount', '银行账户', 'SYNCFUNCODE', '数据同步模块', 10, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('tradeAccount', '交易账户', 'SYNCFUNCODE', '数据同步模块', 11, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('futureAccount', '期货账户', 'SYNCFUNCODE', '数据同步模块', 12, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('RECEIVED', '已接收', 'SYNC_STATE', '同步状态', 0, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('UPDATED', '已同步', 'SYNC_STATE', '同步状态', 1, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('IGNORED', '忽略', 'SYNC_STATE', '同步状态', 2, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_ADD', '新增', 'SYNC_TYPE', '同步类型', 0, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_DEL', '删除', 'SYNC_TYPE', '同步类型', 1, 'ENAB', '[root]')")
		.addSql("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_UPD', '修改', 'SYNC_TYPE', '同步类型', 2, 'ENAB', '[root]')")
		.addSql("insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG) values ('syncdata', '数据同步', 'orgdatamanage', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'bankApi', null, 0, 0, 0, 0, sequ_s_fun.nextval, null, null, 'MENU_INNER', 'POPIN', 1.000000000000000, '[root]', -1)")
		.addSql("insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('syncdata', 'GRE')")
		.addSql("insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('syncdata', 'SAVE')")
		.addSql("insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('syncdata', 'SyncFuncode')")
		.addSql("insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('syncdata', 'UPD')")
		.endScript();
	}
	

}

