package com.yss.ams.db.upgrade.secinfo.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;

public class SecInfoScriptsImpl implements ScriptDesc{

	@Override
	public ScriptBuilder getScriptBuilder() {
		// TODO Auto-generated method stub
		ScriptBuilder builder = new ScriptBuilder();
		/*资讯信息组件相关功能代码和权限*/
		buildStory42948(builder);
		return builder;
	}
	

	/**
	 * STORY #42948 资讯信息管理组件化拆分
	 * 
	 * @param builder
	 */
	private void buildStory42948(ScriptBuilder builder) {

		builder.createScript(ScriptType.DATA)
				// 版本号
				.addVersion("V1.21.5.1")
				// 需求或者bug标识
				.addUpdateType(UpdateType.REQUEST)
				// 需求或者bug号
				.addID("42948")

//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_option', '期权品种信息', 'Derivatives', 10, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'option', '维护期权品种信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_counterrate', '回购收益行情', 'marketPrice', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '特殊的股票质押式回购，维护总的回购收益，可以通过第三方接口导入', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_counterratesec', '证券回购收益行情', 'marketPrice', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '根据回购收益行情，系统计算生成每个证券的每日回购收益', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_dividend', '对价派息信息', 'companyBehaviour', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'dividend', '维护对价派息的的权益信息，可以通过第三方接口导入，用于生成对价派息的交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_etfgpbakt', 'ETF股票篮子', 'cpxssj', 27, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', 'null', 'S', 'null', 'etfgpbakt', '维护ETF产品的股票篮子数据', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_forextradecat', '外汇交易品种', 'money_capital', 14, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'forextradecat', '维护外汇交易品种的基本信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_forward', '远期品种信息', 'Derivatives', 11, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'forward', '维护远期品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_future', '期货品种信息', 'Derivatives', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'future', '维护期货品种信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_future_cfactor', '交割标的清单', 'Derivatives', 12, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', ' ', 'S', ' ', 'future_cfactor', '维护交割标的清单', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_fwmktvalue', '远期外汇行情', 'marketPrice', 5, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'fwmktvalue', '维护远期外汇行情', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_indexStock', '指数成份股信息', 'BaseType', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexStock', '维护指数成份股信息，可以通过第三方接口导入', 0, 0, 1, 0, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_indexinfo', '指数基本信息', 'BaseType', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexinfo', '维护指数基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_indexmp', '指数行情资料', 'marketPrice', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '维护指数行情资料，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_interestRate', '利率品种信息', 'money_capital', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'interestRate', '维护互换品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_secdist', '证券送配信息', 'companyBehaviour', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secdist', '维护证券送配的权益信息，可以通过第三方接口导入，用于生成证券送配的交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_billinfo', '票据基本信息', 'money_capital', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'billinfo', '维护票据基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_secswap', '利率互换品种', 'Derivatives', 16, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'ExchangeSec', '维护利率互换品种的基本信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_certificate', '权证基本信息', 'BaseType', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'certificate', '维护权证基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_depositInfo', '存放品种信息', 'money_capital', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'depositInfo', '维护存放品种信息sv', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_securityPrePublish', '证券预发行信息', 'companyBehaviour', 5, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securityPrePublish', '维护证券预发行信息，用于国债的预买卖交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_securityPublish', '证券发行信息', 'companyBehaviour', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securityPublish', '维护证券的发行代码与上市代码的对应关系，用于债券分销等交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_securitycirculate', '证券流通信息', 'companyBehaviour', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securitycirculate', '维护证券流通的权益信息，可以通过第三方接口导入，用于生成证券流通交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_securitymatches', '证券配对信息', 'companyBehaviour', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securitycirculate', '维护证券配对信息，用于基金的分拆合并', 0, 0, 0, 0, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_spot', '现货品种信息', 'Derivatives', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'spot', '维护现货品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_financial', '理财产品信息', 'BaseType', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'financial', '维护理财产品信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_fixinterest', '债券基本信息', 'BaseType', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'fixinterest', '维护债券基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_lending', '同业拆借信息', 'money_capital', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'lending', '维护同业拆借信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_purchase', '回购基本信息', 'money_capital', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'purchase', '维护回购基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_sectorChild', '板块信息设置', 'baseInfo', 6, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'sectorChild', '维护交易证券所属板块', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_qhbzj', '保证金调整', 'Derivatives', 10, 'ENAB', 'PUB', 'MEU_AID', 'D_ZX', null, 'S', null, 'future', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_sec', '综合证券信息', 'BaseType', 13, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'sec', '维护股票、债券、基金等各种证券品种的基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_secSoldBack', '证券回售基本信息', 'BaseType', 14, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secSoldBack', '维护证券回售基本信息', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_sectorChild_A', '板块分类设置', 'baseInfo', 6, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'sectorChild', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_stockinfo', '股票基本信息', 'BaseType', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'stockinfo', '维护股票基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_bondpayinterest', '债券历史付息', 'BaseType', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'bondpayinterest', '维护债券历史付息，系统会自动生成', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_chargingSec', '计费证券信息', 'BaseType', 15, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_preStockInterest', '优先股计息信息', 'gsxw', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_secmbylx', '债券每日利息', 'secLx', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secmbylx', '维护债券每日利息，系统自动生成，用于债券的计息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_depositinterest', '利率行情资料', 'marketPrice', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'depositinterest', '维护利率行情，利率变化的时候维护，用于计算时取利率数据', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_exratepriceadmin', '汇率行情资料', 'marketPrice', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'exratepriceadmin', '维护汇率行情资料，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_marketvalue', '证券行情资料', 'marketPrice', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'marketvalue', '维护证券行情，可以通过第三方接口导入，用于证券的估值', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('sv_outmarketvalue', '场外净值行情', 'marketPrice', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'outmarketvalue', '维护场外证券的净值行情，可以通过第三方接口导入，用于证券的估值', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//                .addSql(" insert into t_s_fun (C_FUN_CODE,C_FUN_NAME, C_FUN_CODE_P,N_ORDER,C_DV_STATE,C_DV_FUN_LEVEL, C_DV_FUN_TYPE,C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,C_FUN_PARAMS,C_ICO_FILE,C_DESC, N_CHECK, N_LOCK,N_RECYCLE, N_USER, C_IDEN,C_APP_CODE,N_DELETED,C_DV_MENU_CLS,C_DV_SHOW_TYPE,N_VERIFY) "
//				        + " values('sv_sysInit','公共信息处理','VALMANAGE',2,'ENAB','PUB','MEU_FUN','D_RZ', null,'S', null,'sysInit','对公用的信息进行清算，比如证券、行情等和具体的组合没有关系的数据',1,0,1,1,'1014','YSSSECINFO', null,'MENU_INNER','POPIN', -1.000000000000000)")
//				
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('VALMANAGE', '估值处理', 'VALUE', 1, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'VALMANAGE', null, 1, 0, 1, 1, 'SEQU_s_fun.Nextval', 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('VALUE', '估值处理', '[root]', 7, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'VALUE', null, 1, 0, 1, 1, 'SEQU_s_fun.Nextval', 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//                 
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('companyBehaviour', '公司行为信息', 'marketPubInfo', 3, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'companyBehaviour', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('cpxssj', '产品销售数据', 'salesData', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_YW', null, 'S', null, 'cpxssj', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('gsxw', '公司行为数据', 'assetData', 4, 'ENAB', 'PUB', 'CLS_FUN', 'D_YW', null, 'S', null, 'gsxw', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('marketPrice', '公共行情信息', 'marketPubInfo', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'marketPrice', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('marketPubInfo', '市场公共信息', 'ZX', 2, 'ENAB', 'PUB', 'CLS_MOD', 'D_ZX', null, 'S', null, 'marketPubInfo', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('baseInfo', '基本信息设置', 'PARA', 1, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'baseInfo', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('money_capital', '货币资本', 'securityInfo', 3, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'money_capital', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('SJ', '业务管理', '[root]', 6, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'SJ', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('ZX', '资讯管理', '[root]', 3, 'ENAB', 'PUB', 'MOD_FUN', 'D_ZX', null, 'S', null, 'ZX', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('assetData', '投资交易业务', 'SJ', 1, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'assetData', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('salesData', '产品销售业务', 'SJ', 2, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'salesData', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('secLx', '债券利息信息', 'marketPubInfo', 4, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'companyBehaviour', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('securityInfo', '证券品种信息', 'ZX', 1, 'ENAB', 'PUB', 'CLS_MOD', 'D_ZX', null, 'S', null, 'securityInfo', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('BaseType', '基础类', 'securityInfo', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'BaseType', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('Derivatives', '金融衍生工具', 'securityInfo', 2, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'Derivatives', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//      			

.addSql(" delete from t_s_fun a where a.c_fun_code in "
+"('SV_BaseType','SV_Derivatives','SV_SJ','SV_ZX','SV_assetData','SV_companyBehaviour','SV_cpxssj','SV_gsxw','SV_marketPrice','SV_marketPubInfo','SV_money_capital','SV_salesData','SV_secLx','SV_securityInfo','sv_billinfo','sv_bondpayinterest','sv_certificate','sv_chargingSec','sv_counterrate','sv_counterratesec','sv_depositInfo','sv_depositinterest','sv_dividend','sv_etfgpbakt','sv_exratepriceadmin','sv_financial','sv_fixinterest','sv_forextradecat','sv_forward','sv_future','sv_future_cfactor','sv_fwmktvalue','sv_indexStock','sv_indexinfo','sv_indexmp','sv_interestRate','sv_lending','sv_marketvalue','sv_option','sv_outmarketvalue','sv_preStockInterest','sv_purchase','sv_qhbzj','sv_sec','sv_secSoldBack','sv_secdist','sv_secmbylx','sv_secswap','sv_sectorChild','sv_sectorChild_A','sv_securityPrePublish','sv_securityPublish','sv_securitycirculate','sv_securitymatches','sv_spot','sv_stockinfo','sv_sysInit')")

				
.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_gsxw', '公司行为数据', 'SV_assetData', 4, 'ENAB', 'PUB', 'CLS_FUN', 'D_YW', null, 'S', null, 'SV_gsxw', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_sectorChild', '板块信息设置', 'SV_baseInfo', 6, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'sectorChild', '维护交易证券所属板块', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_sectorChild_A', '板块分类设置', 'SV_baseInfo', 6, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'sectorChild', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_sec', '综合证券信息', 'SV_BaseType', 13, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'sec', '维护股票、债券、基金等各种证券品种的基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_indexinfo', '指数基本信息', 'SV_BaseType', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexinfo', '维护指数基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_indexStock', '指数成份股信息', 'SV_BaseType', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexStock', '维护指数成份股信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_certificate', '权证基本信息', 'SV_BaseType', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'certificate', '维护权证基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_financial', '理财产品信息', 'SV_BaseType', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'financial', '维护理财产品信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_fixinterest', '债券基本信息', 'SV_BaseType', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'fixinterest', '维护债券基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_secSoldBack', '证券回售基本信息', 'SV_BaseType', 14, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secSoldBack', '维护证券回售基本信息', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_chargingSec', '计费证券信息', 'SV_BaseType', 15, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_bondpayinterest', '债券历史付息', 'SV_BaseType', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'bondpayinterest', '维护债券历史付息，系统会自动生成', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_stockinfo', '股票基本信息', 'SV_BaseType', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'stockinfo', '维护股票基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', 1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_securityPublish', '证券发行信息', 'SV_companyBehaviour', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securityPublish', '维护证券的发行代码与上市代码的对应关系，用于债券分销等交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_dividend', '对价派息信息', 'SV_companyBehaviour', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'dividend', '维护对价派息的的权益信息，可以通过第三方接口导入，用于生成对价派息的交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_securityPrePublish', '证券预发行信息', 'SV_companyBehaviour', 5, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securityPrePublish', '维护证券预发行信息，用于国债的预买卖交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_securitymatches', '证券配对信息', 'SV_companyBehaviour', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securitycirculate', '维护证券配对信息，用于基金的分拆合并', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_secdist', '证券送配信息', 'SV_companyBehaviour', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secdist', '维护证券送配的权益信息，可以通过第三方接口导入，用于生成证券送配的交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_securitycirculate', '证券流通信息', 'SV_companyBehaviour', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'securitycirculate', '维护证券流通的权益信息，可以通过第三方接口导入，用于生成证券流通交易流水', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_etfgpbakt', 'ETF股票篮子', 'SV_cpxssj', 27, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', 'null', 'S', 'null', 'etfgpbakt', '维护ETF产品的股票篮子数据', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_qhbzj', '保证金调整', 'SV_Derivatives', 10, 'ENAB', 'PUB', 'MEU_AID', 'D_ZX', null, 'S', null, 'future', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_option', '期权品种信息', 'SV_Derivatives', 10, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'option', '维护期权品种信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_spot', '现货品种信息', 'SV_Derivatives', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'spot', '维护现货品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_future', '期货品种信息', 'SV_Derivatives', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'future', '维护期货品种信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_forward', '远期品种信息', 'SV_Derivatives', 11, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'forward', '维护远期品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_future_cfactor', '交割标的清单', 'SV_Derivatives', 12, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', ' ', 'S', ' ', 'future_cfactor', '维护交割标的清单', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_secswap', '利率互换品种', 'SV_Derivatives', 16, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'ExchangeSec', '维护利率互换品种的基本信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_preStockInterest', '优先股计息信息', 'SV_gsxw', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_marketvalue', '证券行情资料', 'SV_marketPrice', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'marketvalue', '维护证券行情，可以通过第三方接口导入，用于证券的估值', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_outmarketvalue', '场外净值行情', 'SV_marketPrice', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'outmarketvalue', '维护场外证券的净值行情，可以通过第三方接口导入，用于证券的估值', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_depositinterest', '利率行情资料', 'SV_marketPrice', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'depositinterest', '维护利率行情，利率变化的时候维护，用于计算时取利率数据', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_exratepriceadmin', '汇率行情资料', 'SV_marketPrice', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'exratepriceadmin', '维护汇率行情资料，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_counterrate', '回购收益行情', 'SV_marketPrice', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '特殊的股票质押式回购，维护总的回购收益，可以通过第三方接口导入', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_counterratesec', '证券回购收益行情', 'SV_marketPrice', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '根据回购收益行情，系统计算生成每个证券的每日回购收益', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_indexmp', '指数行情资料', 'SV_marketPrice', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'indexmp', '维护指数行情资料，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_fwmktvalue', '远期外汇行情', 'SV_marketPrice', 5, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'fwmktvalue', '维护远期外汇行情', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_companyBehaviour', '公司行为信息', 'SV_marketPubInfo', 3, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_companyBehaviour', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_marketPrice', '公共行情信息', 'SV_marketPubInfo', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_marketPrice', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_secLx', '债券利息信息', 'SV_marketPubInfo', 4, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_companyBehaviour', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_interestRate', '利率品种信息', 'SV_money_capital', 9, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'interestRate', '维护互换品种信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_purchase', '回购基本信息', 'SV_money_capital', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'purchase', '维护回购基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_forextradecat', '外汇交易品种', 'SV_money_capital', 14, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'forextradecat', '维护外汇交易品种的基本信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_billinfo', '票据基本信息', 'SV_money_capital', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'billinfo', '维护票据基本信息，可以通过第三方接口导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_lending', '同业拆借信息', 'SV_money_capital', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'lending', '维护同业拆借信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_depositInfo', '存放品种信息', 'SV_money_capital', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'depositInfo', '维护存放品种信息sv', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_cpxssj', '产品销售数据', 'SV_salesData', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_YW', null, 'S', null, 'SV_cpxssj', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_secmbylx', '债券每日利息', 'SV_secLx', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_ZX', null, 'S', null, 'secmbylx', '维护债券每日利息，系统自动生成，用于债券的计息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_money_capital', '货币资本', 'SV_securityInfo', 3, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_money_capital', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_BaseType', '基础类', 'SV_securityInfo', 1, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_BaseType', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_Derivatives', '金融衍生工具', 'SV_securityInfo', 2, 'ENAB', 'PUB', 'CLS_FUN', 'D_ZX', null, 'S', null, 'SV_Derivatives', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_salesData', '产品销售业务', 'SV_SJ', 2, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'SV_salesData', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_assetData', '投资交易业务', 'SV_SJ', 1, 'ENAB', 'PUB', 'CLS_MOD', 'D_YW', null, 'S', null, 'SV_assetData', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('sv_sysInit', '公共信息处理', 'SV_VALMANAGE', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_RZ', null, 'S', null, 'sysInit', '对公用的信息进行清算，比如证券、行情等和具体的组合没有关系的数据', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_marketPubInfo', '市场公共信息', 'SV_ZX', 2, 'ENAB', 'PUB', 'CLS_MOD', 'D_ZX', null, 'S', null, 'SV_marketPubInfo', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_SJ', '业务管理', 'SV_ZX', 6, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'SV_SJ', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_securityInfo', '证券品种信息', 'SV_ZX', 1, 'ENAB', 'PUB', 'CLS_MOD', 'D_ZX', null, 'S', null, 'SV_securityInfo', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

.addSql(" insert  into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
+ " values ('SV_ZX', '资讯组件管理', '[root]', 3, 'ENAB', 'PUB', 'MOD_FUN', 'D_ZX', null, 'S', null, 'SV_ZX', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSSECINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")

 
				
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sysInit', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sysInit', 'EXE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_billinfo', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_bondpayinterest', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_certificate', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_chargingSec', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterrate', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_counterratesec', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositInfo', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_depositinterest', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_dividend', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_etfgpbakt', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_etfgpbakt', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_exratepriceadmin', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_financial', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'GRE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fixinterest', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forextradecat', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_forward', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_future_cfactor', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_fwmktvalue', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexStock', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexinfo', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_indexmp', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_interestRate', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_lending', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_marketvalue', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_option', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_outmarketvalue', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_preStockInterest', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_purchase', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_qhbzj', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sec', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secSoldBack', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTrans', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secTransfer_para', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secdist', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secmbylx', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_secswap', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_sectorChild_A', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPrePublish', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securityPublish', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitycirculate', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_securitymatches', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_spot', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_stockinfo', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) values ('sv_suspended_para', 'UPD')")

//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_depositinterest', '利率行情资料', 'marketPrice', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_depositinterest', '利率行情资料', 'marketPrice', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_dividend', '对价派息信息', 'companyBehaviour', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_dividend', '对价派息信息', 'companyBehaviour', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_etfgpbakt', 'ETF股票篮子', 'ETFTZ', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_etfgpbakt', 'ETF股票篮子', 'ETFTZ', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_exratepriceadmin', '汇率行情资料', 'marketPrice', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_exratepriceadmin', '汇率行情资料', 'marketPrice', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_financial', '理财产品信息', 'BaseSecurity', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_financial', '理财产品信息', 'BaseSecurity', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_fixinterest', '债券基本信息', 'BaseSecurity', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_fixinterest', '债券基本信息', 'BaseSecurity', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_forextradecat', '外汇交易品种', 'money_capital', 'FUN_BIZ', 1, 1, 5, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_forextradecat', '外汇交易品种', 'money_capital', 'FUN_APP', 1, 1, 5, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_forward', '远期品种信息', 'Derivatives', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_forward', '远期品种信息', 'Derivatives', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_future', '期货品种信息', 'Derivatives', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_future', '期货品种信息', 'Derivatives', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_fwmktvalue', '远期外汇行情', 'marketPrice', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_fwmktvalue', '远期外汇行情', 'marketPrice', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexinfo', '指数基本信息', 'plateSetting', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexinfo', '指数基本信息', 'plateSetting', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexmp', '指数行情资料', 'marketPrice', 'FUN_APP', 1, 1, 5, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexmp', '指数行情资料', 'marketPrice', 'FUN_BIZ', 1, 1, 5, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexStock', '指数成份股信息', 'plateSetting', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_indexStock', '指数成份股信息', 'plateSetting', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_interestRate', '利率品种信息', 'money_capital', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_interestRate', '利率品种信息', 'money_capital', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_lending', '同业拆借信息', 'money_capital', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_lending', '同业拆借信息', 'money_capital', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_marketvalue', '证券行情资料', 'marketPrice', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_marketvalue', '证券行情资料', 'marketPrice', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_option', '期权品种信息', 'Derivatives', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_option', '期权品种信息', 'Derivatives', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_outmarketvalue', '场外净值行情', 'marketPrice', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_outmarketvalue', '场外净值行情', 'marketPrice', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_purchase', '回购基本信息', 'money_capital', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_purchase', '回购基本信息', 'money_capital', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sec', '综合证券信息', 'BaseSecurity', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sec', '综合证券信息', 'BaseSecurity', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secdist', '证券送配信息', 'companyBehaviour', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secdist', '证券送配信息', 'companyBehaviour', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secswap', '利率互换品种', 'Derivatives', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secswap', '利率互换品种', 'Derivatives', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sectorChild', '板块信息设置', 'plateSetting', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sectorChild', '板块信息设置', 'plateSetting', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secTrans', '证券转让类型', 'checkBusiness', 'FUN_APP', 1, 1, 12, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_secTrans', '证券转让类型', 'checkBusiness', 'FUN_BIZ', 1, 1, 12, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securitycirculate', '证券流通信息', 'companyBehaviour', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securitycirculate', '证券流通信息', 'companyBehaviour', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securityPrePublish', '证券预发行信息', 'companyBehaviour', 'FUN_BIZ', 1, 1, 5, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securityPrePublish', '证券预发行信息', 'companyBehaviour', 'FUN_APP', 1, 1, 5, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securityPublish', '证券发行信息', 'companyBehaviour', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_securityPublish', '证券发行信息', 'companyBehaviour', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_spot', '现货品种信息', 'Derivatives', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_spot', '现货品种信息', 'Derivatives', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_stockinfo', '股票基本信息', 'BaseSecurity', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_stockinfo', '股票基本信息', 'BaseSecurity', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_billinfo', '票据基本信息', 'money_capital', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_billinfo', '票据基本信息', 'money_capital', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_certificate', '权证基本信息', 'BaseSecurity', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_certificate', '权证基本信息', 'BaseSecurity', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_depositInfo', '存放品种信息', 'money_capital', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_depositInfo', '存放品种信息', 'money_capital', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sysInit', '公共信息处理', 'VALMANAGE', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")							
//                .addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'sv_sysInit', '公共信息处理', 'VALMANAGE', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")

				 .endScript();
	}
	
}
