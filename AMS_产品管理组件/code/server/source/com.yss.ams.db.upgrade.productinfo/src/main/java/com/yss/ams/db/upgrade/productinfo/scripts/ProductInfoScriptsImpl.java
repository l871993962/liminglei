package com.yss.ams.db.upgrade.productinfo.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;

public class ProductInfoScriptsImpl implements ScriptDesc{

	@Override
	public ScriptBuilder getScriptBuilder() {		
		ScriptBuilder builder = new ScriptBuilder();
		// added by HeLiang 2017-07-02 STORY #42921 产品信息组件拆分开发
		/*产品信息组件相关功能代码和权限*/
		buildStory42921(builder);
		
		//STORY #85993 产品参数复制从FAST迁移到产品管理组件 
		buildStory63818(builder);
		buildStory72829(builder);
		return builder;
	}
	
	/**
	 * STORY #72829
	 * @param builder
	 */
	private void buildStory72829(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.20210430")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("72829")
		.addSql("insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, N_AUTHORG, C_AUTH_ORG_CODE, N_COPY_CHECK) values ('pd_assetsTree_A_rule', '产品树形规则', 'productType', 9, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'assetsTree', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000, -1, '[root]', -1) ")
		.endScript();
	}
	
	/**
	 * STORY #63818 新增账套在复制创建时，可在方案中选择自动化方案，自动绑定到源组合所在的流程中
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件 
	 * @param builder
	 */
	private void buildStory63818(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.20200331")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("63818")
		.addSql("insert into T_S_DATA_COPY (C_IDEN, C_DATA_NAME, C_DATA_CODE, C_DATA_CODE_P, C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA) "
				+ "values (sequ_s_data_copy.nextval, '流程管理', 'processMgr', '[root]', '1', null, 43, 'processManager')")
		.addSql("insert into T_S_DATA_COPY (C_IDEN, C_DATA_NAME, C_DATA_CODE, C_DATA_CODE_P, C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA) "
				+ "values (sequ_s_data_copy.nextval, '自动化流程', 'processManager', 'processMgr', '1', 'IAutomaticProcessDimensionCopyService', 44, 'automaticProcess')")
		.endScript();
	}

	/**
	 * added by HeLiang 2017-07-02 STORY #42921 产品信息组件拆分开发
	 * 
	 * @param builder
	 */
	private void buildStory42921(ScriptBuilder builder) {
		
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("V1.21.5.1")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("42921")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_assetsTree', '产品树形结构', 'productType', 2, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'assetsTree', '设置产品树下属的相关组合，在系统的A区按照树形结构显示组合', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_assetsTree_A', '产品树形设置', 'productType', 2, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'assetsTree', null, 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_fundAccInfo', '产品账户信息', 'cmdPara', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, 'fundAccInfo', '维护产品的账户信息，主要用于划款指令的收款人、付款人', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_portGroup', '群组管理', 'productType', 3, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'portGroup', null, 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_portGroupRela', '产品群组管理', 'productType', 3, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portGroup', '把多个有关组合做为一个群组，方便操作，在系统的A区可以选择群组', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_portPdAttribute', '产品属性分类', 'productType', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portPdAttribute', '维护组合的基本信息外的其他信息，其他信息主要用于报表统计', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_portfolio', '产品基本信息', 'productInfo', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portfolio', '维护产品的基本信息', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_productgrade', '分级产品参数', 'otherProduct', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'productgrade', '对分级产品设置特有的相关信息，用于分级产品的计算', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE,N_VERIFY) "
//			+ " values ('pd_pubAccInfo', '公用账户信息', 'cmdPara', 0, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, 'pubAccInfo', '维护公用账户信息，主要用于划款指令的收款人、付款人', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//		.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//			+ " values ('pd_accprdrela', '账户关联产品', 'baseInfo', 4, 'ENAB', 'PUB', 'MEU_FUN', 'P_XT', null, 'S', null, null, null, 1, 0, 1, 1, SEQU_s_fun.Nextval, null, null, 'MENU_INNER', 'POPIN', 0.000000000000000)")
//
//	 
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('CS', '产品管理', '[root]', 2, 'ENAB', 'PUB', 'MOD_FUN', 'P_YW', null, 'S', null, 'CS', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('SERVICE', '服务中心', '[root]', 9, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'SERVICE', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('capitalAssist', '产品参数管理', 'CS', 3, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'capitalAssist', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('cmdPara', '指令参数', 'transferData', 0, 'ENAB', 'PUB', 'CLS_FUN', 'D_YW', null, 'S', null, 'cmdPara', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSTRANSFER', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('otherProduct', '其他特殊产品', 'capitalAssist', 2, 'ENAB', 'PUB', 'CLS_FUN', 'P_YW', null, 'S', null, null, null, 1, 0, 1, 1,SEQU_s_fun.Nextval, null, null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('productInfo', '产品信息管理', 'CS', 1, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'productInfo', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('productType', '产品类别管理', 'CS', 2, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'productType', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, null, null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('transferData', '划款指令数据', 'SERVICE', 2, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'transferData', null, 1, 0, 1, 1,SEQU_s_fun.Nextval, 'YSSTRANSFER', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" delete from t_s_fun a where a.c_fun_code in "
+"('PD_CS','PD_capitalAssist','PD_otherProduct','PD_productInfo','PD_productType','pd_accprdrela','pd_assetsTree','pd_assetsTree_A','pd_fundAccInfo','pd_portGroup','pd_portGroupRela','pd_portPdAttribute','pd_portfolio','pd_productgrade','pd_pubAccInfo')")

		
.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_assetsTree', '产品树形结构', 'PD_productType', 2, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'assetsTree', '设置产品树下属的相关组合，在系统的A区按照树形结构显示组合', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_assetsTree_A', '产品树形设置', 'PD_productType', 2, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'assetsTree', null, 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_fundAccInfo', '产品账户信息', 'PD_otherProduct', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, 'fundAccInfo', '维护产品的账户信息，主要用于划款指令的收款人、付款人', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_portGroup', '群组管理', 'PD_productType', 3, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'portGroup', null, 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_portGroupRela', '产品群组管理', 'PD_productType', 3, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portGroup', '把多个有关组合做为一个群组，方便操作，在系统的A区可以选择群组', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_portPdAttribute', '产品属性分类', 'PD_productType', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portPdAttribute', '维护组合的基本信息外的其他信息，其他信息主要用于报表统计', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_portfolio', '产品基本信息', 'PD_productInfo', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portfolio', '维护产品的基本信息', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_productgrade', '分级产品参数', 'PD_otherProduct', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'productgrade', '对分级产品设置特有的相关信息，用于分级产品的计算', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_pubAccInfo', '公用账户信息', 'PD_otherProduct', 0, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, 'pubAccInfo', '维护公用账户信息，主要用于划款指令的收款人、付款人', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('pd_accprdrela', '账户关联产品', 'BASE_baseInfo', 4, 'ENAB', 'PUB', 'MEU_FUN', 'P_XT', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', 0.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('PD_productInfo', '产品信息管理', 'PD_CS', 1, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'PD_productInfo', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('PD_CS', '产品组件管理', '[root]', 2, 'ENAB', 'PUB', 'MOD_FUN', 'P_YW', null, 'S', null, 'PD_CS', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('PD_capitalAssist', '产品参数管理', 'PD_CS', 3, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'PD_capitalAssist', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('PD_otherProduct', '其他特殊产品', 'PD_capitalAssist', 2, 'ENAB', 'PUB', 'CLS_FUN', 'P_YW', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('PD_productType', '产品类别管理', 'PD_CS', 2, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'PD_productType', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSPRODUCTINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY ) "
	+ " values ('pd_portct', '组合信息', 'PM', 20, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, null, null, 0, 0, 0, 0, '102813', null, null, 'MENU_INNER', 'POPIN', 1.000000000000000)")

		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_assetsTree_A', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_fundAccInfo', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroup', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portGroupRela', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portPdAttribute', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'CNL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'OK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_portfolio', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_productgrade', 'UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS (c_fun_code, c_dv_oper_type) values ('pd_pubAccInfo', 'UPD') ")		
		.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('pd_accprdrela', 'CHOOSE')")
		.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('pd_accprdrela', 'EXP')")
		.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('pd_accprdrela', 'PNT')")
		.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('pd_accprdrela', 'SAVE')")
		.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('pd_accprdrela', 'UNBIND')")
				
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_assetsTree', '产品树形结构', 'pdInfor', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_assetsTree', '产品树形结构', 'pdInfor', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_fundAccInfo', '产品账户信息', 'baseP', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_fundAccInfo', '产品账户信息', 'baseP', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portGroupRela', '产品群组管理', 'pdInfor', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portGroupRela', '产品群组管理', 'pdInfor', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portPdAttribute', '产品属性分类', 'pdInfor', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portPdAttribute', '产品属性分类', 'pdInfor', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portfolio', '产品基本信息', 'pdInfor', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_portfolio', '产品基本信息', 'pdInfor', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_productgrade', '分级产品参数', 'pdDoc', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_productgrade', '分级产品参数', 'pdDoc', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_pubAccInfo', '公用账户信息', 'transfer', 'FUN_APP', 1, 1, 5, null, 'SystemApp')")
//
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_pubAccInfo', '公用账户信息', 'transfer', 'FUN_BIZ', 1, 1, 5, null, 'SystemBiz')")
//				
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'pd_accprdrela', '账户关联产品', 'AM', 'FUN_BIZ', 1, 2, 4, null, 'SystemBiz')")
				
		.endScript();
	}

}
