package com.yss.ams.db.upgrade.baseinfo.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;

public class BaseInfoScriptsImpl implements ScriptDesc{

	@Override
	public ScriptBuilder getScriptBuilder() {
		ScriptBuilder builder = new ScriptBuilder();
		/*功能界面funcode*/
		buildFun(builder);
		/*板块信息*/
		buildBKXX(builder);
		/*地区信息设置*/
		buildDQXXSZ(builder);
		/*交易市场设置*/
		buildJYSCSZ(builder);
		/*收支项目分类*/
		buildSZXMFL(builder);
		/*收支项目设置*/
		buildSZXMSZ(builder);
		/*货币对设置*/
		buildHBDSZ(builder);
		/*业务参数设置表*/
		buildYWCSZZB(builder);
		/*国际货币设置*/
		buildGJHBSZ(builder);
		/*证券品种属性*/
		buildZQPZSX(builder);
		/*资产类型*/
		buildZCLX(builder);
		/*交易方式*/
		buildJYFS(builder);
		/*交易属性*/
		buildJYSX(builder);
		/*核算项目*/
		buildHSXM(builder);
		/*核算元素*/
		buildHSYS(builder);
		/*核算元素字典表*/
		buildHSYSZD(builder);
		/*费用关联字典*/
		buildFYGLZD(builder);
		/*核算业务*/
		buildHSYW(builder);
		/*产品估值参数*/
		buildCPGZCS(builder);
		/*表达式*/
		buildBDS(builder);
		/*财务项目字典*/
		buildCWXMZD(builder);
		/*收支结转字典*/
		buildeSZJZZD(builder);
		/*交易市场字典*/
		buildJYSCZD(builder);
		/*估值指标方案*/
		//buildGZZBFA(builder);
		/*更新索引算法*/
		//buildUpdateT_D_INDEX(builder);
		/*指标模版参数关联表*/
		buildZBMBCSGLB(builder);
		/*证券代码转换字典*/
		buildZQDMZHZD(builder);
		/*恒生交易数据业务分类表*/
		buildHSJYSJYWFLB(builder);
		/*现金流标记字典*/
		buildXJLBJZD(builder);
		/*转换字典表*/
		buildZHZDB(builder);
		/*接口种类*/
		buildJKZL(builder);
		/*区域信息*/
		buildADDRESS(builder);
		//// 2017-8-17 By Jinghehe 提供字典数据 
		buildT_S_DV_VOC(builder);
		//// 2017-11-08 zhouning 提供迁移旧产品账户关联数据到新关联表
		////buildUpdateT_P_AB_PORT_ACC_RELA(builder);
		//// 2017-11-09 zhouning 账户类型数据表插入数据
		buildUpdateT_S_DAC_TYPE(builder);
		////BUG #252441 【易方达基金】【运维】产品账户信息维护后清算进来的资金换汇业务没有获取到资金账户
		buildUpdateT_C_CP_FUND_ACC(builder);
		//story72335
		buildStory72335(builder);
		//BUG #287704 【华宝基金】产品业务范围无法选择转融通
		buildBug287704(builder);
		//STORY #73960 【电子对账】新增分级产品成立时同步所复制帐套的分级指标功能 
		buildStory73960(builder);
		//STORY #80614 账户信息设置中的“账户类型”需要支持多选
		buildInsertT_CP_FUNDTYPE_RELA(builder);
		//STORY #90197 银行账户信息界面新增账户时自动绑定组合
		buildStory90197(builder);
		//STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
        buildStory90952(builder);
        //STORY #69003 【中国银行】-证券基本信息新增品种基金品种_私募基金，区分开放式和封闭式
        buildStory69003(builder);
		return builder;
	}
	
	/**
	 * STORY #69003 【中国银行】-证券基本信息新增品种基金品种_私募基金，区分开放式和封闭式
	 * 删除调菜单'fundAccInfo' 使用base_fundAccInfo
	 * @param builder
	 */
	private void buildStory69003(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
			// 版本号
			.addVersion("300.7")
			// 需求或者bug标识
			.addUpdateType(UpdateType.REQUEST)
			// 需求或者bug号
			.addID("69003")
			//加功能表数据
			.addSql(" delete from T_S_DA_SEC_VAR t where t.C_SEC_VAR_CODE in ('JJ_KFS_SM','JJ_FBS_SM') ")
			.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, N_ORDER, C_DV_STATE) "
					+ "values (sequ_s_da_sec_var.nextval, 'JJ_KFS_SM', '基金品种_开放式_私募', 'JJ_KFS_SM', 'JJ', 13017, 'ENAB') ")
			.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, N_ORDER, C_DV_STATE) "
					+ "values (sequ_s_da_sec_var.nextval, 'JJ_FBS_SM', '基金品种_封闭式_私募', 'JJ_FBS_SM', 'JJ', 13018, 'ENAB') ")
			.endScript();
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @param builder
	 */
	public void buildStory90952(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("300.7")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("90952")
		.addSql(" insert into T_S_FUN(c_fun_code,c_fun_name,c_fun_code_p,n_order,c_dv_state,c_dv_fun_level,c_dv_fun_type,c_dv_fun_cls,c_src_mark,c_ico_file,n_check,n_lock,n_recycle,n_user,c_iden,c_app_code,c_dv_menu_cls,c_dv_show_type) "
				+ "values('automaticSet','自动化业务设置','baseInfo',5,'ENAB','PUB','MEU_FUN','D_YW','E','automaticSet',1,0,0,1,sequ_s_fun.nextval,'YSSINFO','MENU_INNER','POPIN') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','ADD') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','BRW') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','CHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','DEL') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','SAVE') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','UCHK') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','UPD') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','CPY') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','EPT') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','PNT') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','RFH') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','CommSet') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','FaxPolicy') ")
		.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('automaticSet','SealRelaInfo') ")
		.endScript();// 本段SQL语句结束符
	}
	
	/**
	 * 区域信息，省、市
	 * @param builder
	 */
	private void buildADDRESS(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("00000")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '11', '北京市', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '12', '天津市', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '13', '河北省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '14', '山西省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '15', '内蒙古自治区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '21', '辽宁省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '22', '吉林省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '23', '黑龙江省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '31', '上海市', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '32', '江苏省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '33', '浙江省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '34', '安徽省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '35', '福建省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '36', '江西省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '37', '山东省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '41', '河南省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '42', '湖北省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '43', '湖南省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '44', '广东省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '45', '广西壮族自治区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '46', '海南省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '50', '重庆市', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '51', '四川省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '52', '贵州省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '53', '云南省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '54', '西藏自治区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '61', '陕西省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '62', '甘肃省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '63', '青海省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '64', '宁夏回族自治区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '65', '新疆维吾尔族自治区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '71', '台湾省', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '81', '香港特别行政区', '2', null, null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6401', '银川市', '3', '64', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6402', '石嘴山市', '3', '64', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6403', '吴忠市', '3', '64', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6404', '固原市', '3', '64', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6405', '中卫市', '3', '64', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6501', '乌鲁木齐市', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6502', '克拉玛依市', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6521', '吐鲁番地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6522', '哈密地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6523', '昌吉回族自治州', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6527', '博尔塔拉蒙古自治州', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6528', '巴音郭楞蒙古自治州', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6529', '阿克苏地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6530', '克孜勒苏柯尔克孜自治州', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6531', '喀什地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6532', '和田地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6540', '伊犁哈萨克自治州', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6542', '塔城地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6543', '阿勒泰地区', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6590', '省直辖行政单位', '3', '65', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '7100', '台湾省', '3', '71', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '8100', '香港特别行政区', '3', '81', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '8200', '澳门特别行政区', '3', '82', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6328', '海西蒙古族藏族自治州', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1101', '北京市辖区', '3', '11', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1102', '北京市辖县', '3', '11', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1201', '天津市辖区', '3', '12', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1202', '天津市辖县', '3', '12', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1301', '石家庄市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1302', '唐山市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1303', '秦皇岛市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1304', '邯郸市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1305', '邢台市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1306', '保定市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1307', '张家口市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1308', '承德市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1309', '沧州市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1310', '廊坊市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1311', '衡水市', '3', '13', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1401', '太原市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1402', '大同市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1403', '阳泉市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1404', '长治市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1405', '晋城市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1406', '朔州市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1407', '晋中市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1408', '运城市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1409', '忻州市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1410', '临汾市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1411', '吕梁市', '3', '14', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1501', '呼和浩特市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1502', '包头市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1503', '乌海市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1504', '赤峰市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1505', '通辽市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1506', '鄂尔多斯市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1507', '呼伦贝尔市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1508', '巴彦淖尔市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1509', '乌兰察布市', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1522', '兴安盟', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1525', '锡林郭勒盟', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1526', '乌兰察布盟', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '1529', '阿拉善盟', '3', '15', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2101', '沈阳市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2102', '大连市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2103', '鞍山市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2104', '抚顺市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2105', '本溪市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2106', '丹东市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2107', '锦州市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2108', '营口市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2109', '阜新市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2110', '辽阳市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2111', '盘锦市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2112', '铁岭市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2113', '朝阳市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2114', '葫芦岛市', '3', '21', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2201', '长春市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2202', '吉林市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2203', '四平市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2204', '辽源市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2205', '通化市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2206', '白山市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2207', '松原市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2208', '白城市', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2224', '延边朝鲜族自治州　', '3', '22', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2301', '哈尔滨市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2302', '齐齐哈尔市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2303', '鸡西市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2304', '鹤岗市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2305', '双鸭山市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2306', '大庆市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2307', '伊春市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2308', '佳木斯市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2309', '七台河市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2310', '牡丹江市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2311', '黑河市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2312', '绥化市', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '2327', '大兴安岭地区', '3', '23', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3101', '上海市辖区', '3', '31', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3102', '上海市辖县', '3', '31', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3201', '南京市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3202', '无锡市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3203', '徐州市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3204', '常州市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3205', '苏州市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3206', '南通市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3207', '连云港市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3208', '淮安市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3209', '盐城市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3210', '扬州市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3211', '镇江市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3212', '泰州市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3213', '宿迁市', '3', '32', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3301', '杭州市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3302', '宁波市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3303', '温州市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3304', '嘉兴市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3305', '湖州市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3306', '绍兴市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3307', '金华市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3308', '衢州市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3309', '舟山市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3310', '台州市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3311', '丽水市', '3', '33', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3401', '合肥市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3402', '芜湖市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3403', '蚌埠市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3404', '淮南市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3405', '马鞍山市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3406', '淮北市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3407', '铜陵市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3408', '安庆市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3410', '黄山市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3411', '滁州市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3412', '阜阳市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3413', '宿州市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3414', '巢湖市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3415', '六安市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3416', '亳州市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3417', '池州市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3418', '宣城市', '3', '34', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3501', '福州市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3502', '厦门市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3503', '莆田市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3504', '三明市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3505', '泉州市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3506', '漳州市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3507', '南平市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3508', '龙岩市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3509', '宁德市', '3', '35', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3601', '南昌市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3602', '景德镇市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3603', '萍乡市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3604', '九江市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3605', '新余市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3606', '鹰潭市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3607', '赣州市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3608', '吉安市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3609', '宜春市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3610', '抚州市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3611', '上饶市', '3', '36', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3701', '济南市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3702', '青岛市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3703', '淄博市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3704', '枣庄市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3705', '东营市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3706', '烟台市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3707', '潍坊市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3708', '济宁市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3709', '泰安市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3710', '威海市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3711', '日照市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3712', '莱芜市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3713', '临沂市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3714', '德州市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3715', '聊城市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3716', '滨州市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '3717', '荷泽市', '3', '37', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4101', '郑州市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4102', '开封市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4103', '洛阳市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4104', '平顶山市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4105', '安阳市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4106', '鹤壁市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4107', '新乡市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4108', '焦作市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4109', '濮阳市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4110', '许昌市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4111', '漯河市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4112', '三门峡市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4113', '南阳市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4114', '商丘市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4115', '信阳市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4116', '周口市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4117', '驻马店市', '3', '41', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4201', '武汉市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4202', '黄石市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4203', '十堰市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4205', '宜昌市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4206', '襄樊市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4207', '鄂州市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4208', '荆门市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4209', '孝感市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4210', '荆州市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4211', '黄冈市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4212', '咸宁市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4213', '随州市', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4228', '恩施土家族苗族自治州', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4290', '省直辖行政单位', '3', '42', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4301', '长沙市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4302', '株洲市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4303', '湘潭市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4304', '衡阳市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4305', '邵阳市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4306', '岳阳市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4307', '常德市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4308', '张家界市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4309', '益阳市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4310', '郴州市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4311', '永州市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4312', '怀化市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4313', '娄底市', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4331', '湘西土家族苗族自治州', '3', '43', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4401', '广州市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4402', '韶关市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4403', '深圳市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4404', '珠海市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4405', '汕头市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4406', '佛山市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4407', '江门市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4408', '湛江市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4409', '茂名市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4412', '肇庆市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4413', '惠州市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4414', '梅州市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4415', '汕尾市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4416', '河源市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4417', '阳江市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4418', '清远市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4419', '东莞市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4420', '中山市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4451', '潮州市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4452', '揭阳市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4453', '云浮市', '3', '44', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4501', '南宁市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4502', '柳州市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4503', '桂林市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4504', '梧州市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4505', '北海市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4506', '防城港市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4507', '钦州市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4508', '贵港市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4509', '玉林市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4510', '百色市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4511', '贺州市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4512', '河池市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4513', '来宾市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4514', '崇左市', '3', '45', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4601', '海口市', '3', '46', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4602', '三亚市', '3', '46', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '4690', '省直辖县级行政单位', '3', '46', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5001', '重庆市辖区', '3', '50', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5002', '重庆市辖县', '3', '50', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5003', '重庆市辖其他市', '3', '50', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5101', '成都市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5103', '自贡市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5104', '攀枝花市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5105', '泸州市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5106', '德阳市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5107', '绵阳市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5108', '广元市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5109', '遂宁市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5110', '内江市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5111', '乐山市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5113', '南充市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5114', '眉山市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5115', '宜宾市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5116', '广安市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5117', '达州市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5118', '雅安市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5119', '巴中市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5120', '资阳市', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5132', '阿坝藏族羌族自治州', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5133', '甘孜藏族自治州', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5134', '凉山彝族自治州', '3', '51', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5201', '贵阳市', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5202', '六盘水市', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5203', '遵义市', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5204', '安顺市', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5222', '铜仁地区', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5223', '黔西南布依族苗族自治州', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5224', '毕节地区', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5226', '黔东南苗族侗族自治州', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5227', '黔南布依族苗族自治州', '3', '52', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5301', '昆明市　', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5303', '曲靖市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5304', '玉溪市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5305', '保山市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5306', '昭通市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5307', '丽江市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5308', '思茅市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5309', '临沧市', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5323', '楚雄彝族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5325', '红河哈尼族彝族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5326', '文山壮族苗族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5328', '西双版纳傣族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5329', '大理白族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5331', '德宏傣族景颇族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5333', '怒江傈僳族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5334', '迪庆藏族自治州', '3', '53', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5401', '拉萨市', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5421', '昌都地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5422', '山南地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5423', '日喀则地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5424', '那曲地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5425', '阿里地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '5426', '林芝地区', '3', '54', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6101', '西安市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6102', '铜川市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6103', '宝鸡市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6104', '咸阳市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6105', '渭南市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6106', '延安市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6107', '汉中市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6108', '榆林市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6109', '安康市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6110', '商洛市', '3', '61', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6201', '兰州市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6202', '嘉峪关市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6203', '金昌市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6204', '白银市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6205', '天水市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6206', '武威市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6207', '张掖市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6208', '平凉市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6209', '酒泉市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6210', '庆阳市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6211', '定西市', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6226', '陇南地区', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6229', '临夏回族自治州', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6230', '甘南藏族自治州', '3', '62', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6301', '西宁市', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6321', '海东地区', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6322', '海北藏族自治州', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6323', '黄南藏族自治州', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6325', '海南藏族自治州', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6326', '果洛藏族自治州', '3', '63', null, null)")
		
		.addSql(" insert into T_S_DC_ADDRESS (C_IDEN, C_CODE, C_NAME, C_LEVEL, C_PARENT_CODE, N_NUMBER1, C_STR1)"
		+ " values (SEQU_S_DC_ADDRESS.NEXTVAL, '6327', '玉树藏族自治州', '3', '63', null, null)")

		.endScript();
	}

	/**
	 * 接口种类
	 * @param builder
	 */
	private void buildJKZL(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("delete from T_S_INF_VAR where C_INF_CODE = 'QT_RQ'")
				.addSql("insert into T_S_INF_VAR (C_IDEN, C_INF_CODE, C_INF_NAME, C_INF_CODE_P, N_ORDER) values (sequ_s_inf_var.nextval, 'QT_RQ', '运作期区间', 'OTHER', 101)")
				.addSql("delete from T_S_INF_VAR where c_inf_code in ('Corp_Action','PUB_MP','TRADE_TA','OTHER','PUBJN_HQ','PUBJN_ZX',"
						+ "'PUBJN_QY','JYJN_SHJY','JYJN_SHQY','JYJN_SHDZ','JYJN_SHGGQY','JYJN_SHXSB','JYJN_SHHGT','JYJN_SZSGT','JYJN_RZRQJSD',"
						+ "'JYJN_RZRQJZ','JYJN_RZRQHS','JYJN_RZRQZXXQ','JYJN_SZJY','JYJN_SZQY','JYJN_SZDZ','JYJN_QHZJS','JYJN_QHJYJKZX',"
						+ "'JYJN_ZRTZJGS','JYJN_GJSJYSHJJS','JYJN_XTJY','JYJN_WGCZ','JYIN_DCCK','SYHH_MRYK','TAJN_HSTA','TAJN_JZTA',"
						+ "'TAJN_TPZCTA','TAJN_SHETF','TAJN_SZETF','QT_JCSJ','QT_ZJLS','QT_JYLS','QT_FZGZ','QT_DZSJ','QT_ZQLL',"
						+ "'QT_TCSJ','QT_SGTQY','QT_QT','QT_GTSJ','PUBJW_HQ','PUBJW_ZX','PUBJW_QY','JYJW_JYJW','TAJW_TAJW','TRADE') ")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'Corp_Action', '公司行为信息', '[root]', 2)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUB_MP', '公共行情信息', '[root]', 3)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TRADE_TA', 'TA交易数据', '[root]', 4)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'OTHER', '其他', '[root]', 5)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJN_HQ', '行情数据（境内）', 'PUB_MP', 6)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJN_ZX', '资讯数据（境内）', 'PUB_MP', 7)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJN_QY', '权益数据（境内）', 'PUB_MP', 8)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHJY', '上交所交易', 'TRADE', 9)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHQY', '上交所权益', 'TRADE', 10)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHDZ', '上交所对账', 'TRADE', 11)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHGGQY', '上交所个股期权', 'TRADE', 12)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHXSB', '新三板', 'TRADE', 13)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SHHGT', '沪港通', 'TRADE', 14)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SZSGT', '深港通', 'TRADE', 15)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_RZRQJSD', '融资融券（金仕达）', 'TRADE', 16)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_RZRQJZ', '融资融券（金证）', 'TRADE', 17)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_RZRQHS', '融资融券（恒生）', 'TRADE', 18)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_RZRQZXXQ', '融资融券（中信证券）', 'TRADE', 19)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SZJY', '深圳交所交易', 'TRADE', 20)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SZQY', '深交所权益', 'TRADE', 21)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_SZDZ', '深交所对账', 'TRADE', 22)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_QHZJS', '期货交易（中金所）', 'TRADE', 23)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_QHJYJKZX', '期货交易（监控中心）', 'TRADE', 24)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_ZRTZJGS', '转融通（证金公司）', 'TRADE', 25)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_GJSJYSHJJS', '贵金属交易（上海金交所）', 'TRADE', 26)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_XTJY', '信托交易', 'TRADE', 27)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJN_WGCZ', '违规处置数据（股票质押回购）', 'TRADE', 28)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYIN_DCCK', '定存存款交易', 'TRADE', 29)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'SYHH_MRYK', '收益互换每日盈亏', 'TRADE', 30)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJN_HSTA', '恒生TA', 'TRADE_TA', 31)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJN_JZTA', '金证TA', 'TRADE_TA', 32)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJN_TPZCTA', '太平资产TA', 'TRADE_TA', 33)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJN_SHETF', '上海ETF', 'TRADE_TA', 34)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJN_SZETF', '深圳ETF', 'TRADE_TA', 35)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_JCSJ', '基础数据', 'OTHER', 36)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_ZJLS', '资金流水', 'OTHER', 37)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_JYLS', '交易流水', 'OTHER', 38)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_FZGZ', '分组估值', 'OTHER', 39)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_DZSJ', '对账数据', 'OTHER', 40)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_ZQLL', '证券利率', 'OTHER', 41)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_TCSJ', 'O32头寸数据', 'OTHER', 42)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_SGTQY', '深国投期初数据迁移', 'OTHER', 43)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_QT', '其他数据', 'OTHER', 44)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'QT_GTSJ', '柜台数据', 'OTHER', 45)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJW_HQ', '行情数据（境外）', 'PUB_MP', 46)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJW_ZX', '资讯数据（境外）', 'PUB_MP', 47)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'PUBJW_QY', '权益数据（境外）', 'PUB_MP', 48)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'JYJW_JYJW', '交易数据（境外）', 'TRADE', 49)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TAJW_TAJW', 'TA数据（境外）', 'TRADE_TA', 50)")
				.addSql("insert into T_S_INF_VAR (c_iden, c_inf_code, c_inf_name, c_inf_code_p, n_order) values (sequ_s_inf_var.nextval, 'TRADE', '投资交易数据', '[root]', 1)")
				.endScript();
	}
	
	/**
	 * 转换字典表
	 * @param builder
	 */
	private void buildZHZDB(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				 .addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_GT', 'INFI_JY_HB_SZ', 'RZRQ_MC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_GT', 'INFI_JY_HB_SZ', 'RZRQ_MR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_JY_HB_SZ', 'RZRQ_MC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_JY_HB_SZ', 'RZRQ_MR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_GH_SH', 'RZRQ_RQHQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_GH_SH', 'RZRQ_RQHQ_MQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_GH_SH', 'RZRQ_RQMC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_GH_SH', 'RZRQ_RZMC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_GH_SH', 'RZRQ_RZMR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_MC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_MR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_RQHQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_RQHQ_MQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_RQMC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_RZMC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_QSMXNEW', 'RZRQ_RZMR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_YWHB_SH', 'RZRQ_DBHC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_YWHB_SH', 'RZRQ_DBHR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_JY_YWHB_SH', 'RZRQ_RQHQ_YQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JG_SZ', 'RZRQ_DBHC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JG_SZ', 'RZRQ_DBHR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JG_SZ', 'RZRQ_RQHQ_YQ') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_ZG') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_JG_SZ', 'ZQSP_ZG') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ "values ('CO_M2_CG_GZPTYW', '固定收益平台业务数据读取方式', 'RT_GZPTYW_JSMX', 'INFI_JY_JSMX', 'ZQJY_BUY')")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ "values ('CO_M2_CG_GZPTYW', '固定收益平台业务数据读取方式', 'RT_GZPTYW_JSMX', 'INFI_JY_JSMX', 'ZQJY_SELL')")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ "values ('CO_M2_CG_GZPTYW', '固定收益平台业务数据读取方式', 'RT_GZPTYW_ZGH', 'INFI_JY_ZGH_SH', 'ZQJY_BUY')")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ "values ('CO_M2_CG_GZPTYW', '固定收益平台业务数据读取方式', 'RT_GZPTYW_ZGH', 'INFI_JY_ZGH_SH', 'ZQJY_SELL')")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_RQHQ_YQ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHR') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+" values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYSV5', 'INFI_QY_JGNEW_SZ', 'RZRQ_RQHQ_YQ') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'GPJY_BUY')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'GPJY_SELL')   ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'ZQJY_BUY')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'ZQJY_SELL')   ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'JJJY_BUY')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'JJJY_SELL')   ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'QZJY_BUY')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'QZJY_SELL')   ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'HGJY_ZHG')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'HGJY_NHG')    ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'XZSG_SQ')     ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SJSHB', 'INFI_JY_HB_SZ', 'XGSG_SQ')     ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'GPJY_BUY')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'GPJY_SELL') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'ZQJY_BUY')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'ZQJY_SELL') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'JJJY_BUY')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'JJJY_SELL') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'QZJY_BUY')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'QZJY_SELL') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'HGJY_ZHG')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_JY_QSMXNEW', 'HGJY_NHG')  ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_QY_FX_SZ', 'XGSG_SQ')     ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE)  values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'RT_SJSV5_SZDJS', 'INFI_QY_FX_SZ', 'XZSG_SQ')     ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_SG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'DJPX_FHPX') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'DJPX_XJDJ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQJY_DF') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQZH_ZZG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'GPJY_YYSG_BUY') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQJY_HS') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_QZSP')")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQJY_SH') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_PG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_CF') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_JJFC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_JJHB')")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_GFDJ') ")
				
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_SG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'DJPX_FHPX') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'DJPX_XJDJ') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQJY_DF') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQZH_ZZG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'GPJY_YYSG_BUY') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQJY_HS') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_QZSP')")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQJY_SH') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_PG') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_CF') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_JJFC') ")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_JG', 'INFI_QY_GF_SZ', 'ZQSP_JJHB')")
				.addSql(" insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) "
						+ " values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'RT_QYSJ_GF', 'INFI_QY_GF_SZ', 'ZQSP_GFDJ') ")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHC')")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_DBHR')")
				.addSql("insert into T_S_CO_TD_SWITCH (C_DSP_CODE, C_DSP_NAME, C_DV_VALUE, C_CFG_CODE, C_DT_CODE) values ('CO_RZRQ_SJDQFS', '交易所与柜台都提供的融资融券业务数据读取方式', 'RT_RZRQ_JYS', 'INFI_QY_JGNEW_SZ', 'RZRQ_RQHQ_YQ')")
				.endScript();
	}
	
	/**
	 * 现金流标记字典
	 * @param builder
	 */
	private void buildXJLBJZD(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
			
				.addSql("insert into T_S_CASH_FLOW (C_CASH_FLOW_CODE, C_CASH_FLOW_NAME, C_DT_CODE, C_DVA_ITEM_NAME, C_SEC_VAR_CODE, C_DVA_ITEM_CODE, C_FEE_CODE) values ('HB_YHZZ', '银行转账', 'ZJHH_HB', '资金划拨', ' ', ' ', ' ')")
			
				.addSql("insert into T_S_CASH_FLOW (C_CASH_FLOW_CODE, C_CASH_FLOW_NAME, C_DT_CODE, C_DVA_ITEM_NAME, C_SEC_VAR_CODE, C_DVA_ITEM_CODE, C_FEE_CODE) values ('I_TZZH', '投资组合划入现金', ' ', ' ', ' ', ' ', ' ')")
			
				.addSql("insert into T_S_CASH_FLOW (C_CASH_FLOW_CODE, C_CASH_FLOW_NAME, C_DT_CODE, C_DVA_ITEM_NAME, C_SEC_VAR_CODE, C_DVA_ITEM_CODE, C_FEE_CODE) values ('O_TZZH', '投资组合划出现金', ' ', ' ', ' ', ' ', ' ')")
				.endScript();
	}
	
	/**
	 * 恒生交易数据业务分类表
	 * @param builder
	 */
	private void buildHSJYSJYWFLB(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付管理费', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYZF01') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付托管费', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYZF02') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付销售服务费', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYZF04') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付行政服务费', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYZF29') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付业绩报酬', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYZF30') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '支付投资顾问费', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'FYXT01') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '募集期结息', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZWH03') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '证转银', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ01') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银转证', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ02') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '证转银（信用）', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ03') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银转证（信用）', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ04') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '期转银', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ05') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银转期', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ06') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银转金', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ07') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '金转银', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ08') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银衍转账（银转衍）', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ09') ")
				.addSql(" INSERT INTO T_S_DV_TD_ITEM (C_MKT_CODE, C_TD_CODE, C_TD_NAME, C_DT_NAME, C_DT_NO, C_MKT_NO, C_SB_TYPE, C_TD_NO, C_DESC, C_CFG_CODE, C_ITEM_CODE) "
						+ " VALUES (' ', ' ', '银衍转账（衍转银）', ' ', ' ', ' ', ' ', ' ', ' ', 'INFI_QT_QSXTZL', 'ZZBAJ10') ")
				.endScript();
	}
	
	/**
	 * 证券代码转换字典
	 * @param builder
	 */
	private void buildZQDMZHZD(ScriptBuilder builder){
		//T_S_SEC_TRANS
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1058%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1057%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('128***', '128***', '128%', 'ZQ_GSZ', 'SG', 'XSHG', '可交换公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('190***', '600***', '190%', 'GP_GP', 'SG', 'XSHG', '可转债转股（600***）', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('181***', '600***', '181%', 'GP_GP', 'SG', 'XSHG', '可转债转股（600***）', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('191***', '601***', '191%', 'GP_GP', 'SG', 'XSHG', '可转债转股（601***）', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('192***', '128***', '192%', 'ZQ_GSZ', 'SG', 'XSHG', '可交换公司债转股（128***）', 'ZQJY', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('203***', '203***', '203%', 'HG_MDS_GZXQ', 'SG', 'XSHG', '国债买断式回购', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('310***', '310***', '310%', 'QH_ZQ', 'SG', 'XSHG', '国债期货', 'QHJY', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('523***', '523***', '523%', 'JJ_KFS', 'SG', 'XSHG', '开放式基金分红', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('522***', '522***', '522%', 'JJ_KFS', 'SG', 'XSHG', '开放式基金跨市场转托管', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('521***', '521***', '521%', 'JJ_KFS', 'SG', 'XSHG', '开放式基金认购', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('524***', '524***', '524%', 'JJ_KFS', 'SG', 'XSHG', '开放式基金基金转换', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('5198**', '5198**', '5198%', 'JJ_KFS_HBX_SH', 'SG', 'XSHG', '基金品种_开放式_实时货币', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('582***', '582***', '582%', 'QZ_QZ', 'SG', 'XSHG', '权证行权', 'QZXQ', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('702***', '600***', '702%', 'GP_GP', 'SG', 'XSHG', '职工股 配股( 对应600***)', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('700***', '600***', '700%', 'GP_GP', 'SG', 'XSHG', '配股 ( 对应600***)', 'ZQSP', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('738***', '600***', '738%', 'GP_GP', 'SG', 'XSHG', '网上投票( 对应600***)', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('741***', '600***', '741%', 'GP_GP', 'SG', 'XSHG', '申购，增发配号( 对应600***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('744***', '600***', '744%', 'GP_GP', 'SG', 'XSHG', '可转债配号( 对应600***)', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('762***', '601***', '762%', 'GP_GP', 'SG', 'XSHG', '配股 ( 对应601***)', 'ZQSP', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('788***', '601***', '788%', 'GP_GP', 'SG', 'XSHG', '网络投票( 对应601***)', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('791***', '601***', '791%', 'GP_GP', 'SG', 'XSHG', '申购或增发配号( 对应601***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('794***', '601***', '794%', 'GP_GP', 'SG', 'XSHG', '可转债配号( 对应601***)', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('000***', '000***', '000%', 'GP_GP', 'CS', 'XSHE', 'A股主板证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('9*****', '9*****', '9%', 'GP_GP', 'SG', 'XSHG', 'B股', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('001***', '001***', '001%', 'GP_GP', 'CS', 'XSHE', 'A股主板证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('002***', '002***', '002%', 'GP_GP', 'CS', 'XSHE', 'A股中小板证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('03****', '03****', '03%', 'QZ_QZ', 'CS', 'XSHE', 'A股认购认沽权证', 'QZJY', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('124***', '124***', '124%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('30***', '00***', '30%', 'GP_GP', 'GGT', 'HKCG', '港股通临时代码转换', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('512**2', '512**2', '512%2', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF资金', 'JJJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('512**1', '512**1', '512%1', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('512**0', '512**0', '512%0', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('513***', '513***', '513%', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF资金', 'JJJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('018***', '018***', '018%', 'ZQ_JRZ_ZC', 'SG', 'XSHG', '政策性金融债', 'ZQJY', 10.0000, 'SEC')")
//				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('5188**', '5188**', '5188%', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
//				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('5058**', '5058**', '5058%', 'JJ_FBS', 'SG', 'XSHG', '封闭式基金', 'JJJY', 100.0000, ' ')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('132***', '132***', '132%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135**', '135***', '1350%', 'ZQ_DQRZZ', 'SG', 'XSHG', '短期债券（132000-132999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135**', '135***', '1351%', 'ZQ_DQRZZ', 'SG', 'XSHG', '短期债券（132000-132999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135**', '135***', '1352%', 'ZQ_DQRZZ', 'SG', 'XSHG', '短期债券（132000-132999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135**', '135***', '1353%', 'ZQ_DQRZZ', 'SG', 'XSHG', '短期债券（132000-132999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135**', '135***', '1354%', 'ZQ_DQRZZ', 'SG', 'XSHG', '短期债券（132000-132999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('118***', '118***', '118%', 'ZQ_SMZQ', 'SG', 'XSHE', '私募债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('119***', '119***', '119%', 'ZQ_ZCZQH', 'SG', 'XSHE', '资产证券化', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('125***', '125***', '125%', 'ZQ_SMZQ', 'SG', 'XSHG', '私募债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('139**', '139***', '139%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债（139000-139999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135***', '135***', '1356%', 'ZQ_SMZQ_BGCZ', 'SG', 'XSHG', '并购重组私募债（135500-135999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135***', '135***', '1357%', 'ZQ_SMZQ_BGCZ', 'SG', 'XSHG', '并购重组私募债（135500-135999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135***', '135***', '1359%', 'ZQ_SMZQ_BGCZ', 'SG', 'XSHG', '并购重组私募债（135500-135999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('5118*0', '5118*0', '5118*0', 'JJ_KFS_HBX_JY', 'SG', 'XSHG', '基金品种_开放式_交易货币', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('519***', '519***', '519%', 'JJ_KFS', 'SG', 'XSHG', '开放式基金申购', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1220**', '1220**', '1220%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1222**', '1222**', '1222%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1224**', '1224**', '1224%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1226**', '1226**', '1226%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1227**', '1227**', '1227%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1228**', '1228**', '1228%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1229**', '1229**', '1229%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('132001', '132001', '132001', 'ZQ_KZZ', 'SG', 'XSHG', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('512***', '512***', '512%', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1380**', '1380**', '1380%', 'ZQ_SMZQ_KJH', 'CS', 'XSHG', '可交换私募债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1370**', '1370**', '1370%', 'ZQ_SMZQ_KJH', 'CS', 'XSHG', '可交换私募债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('501***', '501***', '501%', 'JJ_FBS', 'SG', 'XSHG', '基金品种_封闭式', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('114***', '114***', '114%', 'ZQ_SMZQ', 'SG', 'XSHE', '私募债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('140***', '140***', '140%', 'GP_GP', 'CS', 'XSHE', '优先股 (140001-140999) ', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('03****', '03****', '03%', 'ZQ_ZQJH', 'CY', 'XCFE', '债券品种_债权计划', 'ZQJY', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('123***', '123***', '123%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('505***', '505***', '505%', 'JJ_FBS', 'SG', 'XSHG', '封闭式基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('518**0', '518**0', '518%0', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('136***', '136***', '136%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('502***', '502***', '502%', 'JJ_FBS', 'SG', 'XSHG', '基金品种_封闭式', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('500***', '500***', '500%', 'JJ_FBS', 'SG', 'XSHG', '封闭式基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135***', '135***', '1355%', 'ZQ_SMZQ_BGCZ', 'SG', 'XSHG', '并购重组私募债（135500-135999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('135***', '135***', '1358%', 'ZQ_SMZQ_BGCZ', 'SG', 'XSHG', '并购重组私募债（135500-135999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('513**0', '513**0', '513%0', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1221**', '1221**', '1221%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1223**', '1223**', '1223%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('1225**', '1225**', '1225%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('07****', '07****', '07%', 'GP_GP', 'CS', 'XSHE', 'A股增发', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('08****', '08****', '08%', 'QZ_QZ', 'CS', 'XSHE', 'A股配股权证', 'QZJY', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('100***', '100***', '100%', 'ZQ_GZXQ', 'CS', 'XSHE', '国债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('101***', '101***', '101%', 'ZQ_GZXQ', 'CS', 'XSHE', '国债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('108***', '108***', '108%', 'ZQ_QT', 'CS', 'XSHE', '贴债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('109***', '109***', '109%', 'ZQ_DFZFZ', 'CS', 'XSHE', '地方政府债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('111***', '111***', '111%', 'ZQ_QYZ', 'CS', 'XSHE', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('112***', '112***', '112%', 'ZQ_GSZ', 'CS', 'XSHE', '公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('115***', '115***', '115%', 'ZQ_KZZ', 'CS', 'XSHE', '认股权和债券分离交易的可转换公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('12****', '12****', '125%', 'ZQ_KZZ', 'CS', 'XSHE', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('13****', '13****', '13%', 'HG_ZYS_GZXQ', 'CS', 'XSHE', '债券回购', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('15****', '15****', '150%', 'JJ_KFS_LOF', 'CS', 'XSHE', '开放式基金_LOF', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('16****', '16****', '16%', 'JJ_KFS_LOF', 'CS', 'XSHE', '开放式基金_LOF', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('734***', '603***', '734%', 'GP_GP', 'SG', 'XSHG', '申购款或增发款( 对应603***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('732***', '603***', '732%', 'GP_GP', 'SG', 'XSHG', '申购，增发 ( 对应603***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('511**0', '511**0', '511%0', 'JJ_KFS_HBX_JY', 'SG', 'XSHG', '基金品种_开放式_交易货币', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('127***', '127***', '127%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（127000-127999）', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('18****', '18****', '18%', 'JJ_FBS', 'CS', 'XSHE', '证券投资基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('20****', '20****', '20%', 'GP_GP', 'CS', 'XSHE', 'B股证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('28****', '28****', '28%', 'GP_GP', 'CS', 'XSHE', 'B股证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('30****', '30****', '30%', 'GP_GP_CYB', 'CS', 'XSHE', '创业板证券', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('36****', '36****', '36%', 'GP_GP_CYB', 'CS', 'XSHE', '网络投票证券', 'GPJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('37****', '37****', '37%', 'GP_GP_CYB', 'CS', 'XSHE', '创业板增发', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('38****', '38****', '38%', 'GP_GQ', 'CS', 'XSHE', '创业板权证', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('39****', '39****', '39%', ' ', 'CS', 'XSHE', '综合或成份指数/成交量统计指标', null, 0.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('15****', '15****', '1599%', 'JJ_KFS_ETF', 'CS', 'XSHE', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('15****', '15****', '158%', 'JJ_KFS_LOF', 'CS', 'XSHE', '开放式基金_LOF', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('090***', '010***', '090%', 'HG_ZYS_GZXQ', 'SG', 'XSHG', '新国债质押式回购质押券出入库(对应010国债)', 'HGJY', 1000.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '122***', '7510%', 'ZQ_GZXQ', 'SG', 'XSHG', '国债现券', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('609***', '002***', '609%', 'GP_GP', 'SG', 'XSHG', '上海卖出市值配售的深圳股票', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('740***', '600***', '740%', 'GP_GP', 'SG', 'XSHG', '申购款，增发款 ( 对应600***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('790***', '601***', '790%', 'GP_GP', 'SG', 'XSHG', '申购款或增发款 ( 对应601***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('731***', '600***', '731%', 'GP_GP', 'SG', 'XSHG', '持股增发( 对应600***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('781***', '601***', '781%', 'GP_GP', 'SG', 'XSHG', '持股增发( 对应601***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('730***', '600***', '730%', 'GP_GP', 'SG', 'XSHG', '申购，增发 ( 对应600***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('780***', '601***', '780%', 'GP_GP', 'SG', 'XSHG', '申购，增发( 对应601***)', 'XGSG', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('737***', '600***', '737%', 'GP_GP', 'SG', 'XSHG', null, 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('739***', '002***', '739%', 'GP_GP', 'SG', 'XSHE', null, 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('70****', '600***', '70%', 'GP_GP', 'SG', 'XSHG', null, 'ZQSP', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('760***', '601***', '760%', 'GP_GP', 'SG', 'XSHG', '配股 ( 对应601***)', 'ZQSP', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('743***', '110***', '743%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'XZSG', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('793***', '113***', '793%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'XZSG', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75190%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '122***', '75197%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('733***', '110***', '733%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'XZSG', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('783***', '113***', '783%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'XZSG', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('764***', '113***', '764%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'ZQSP', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('704***', '110***', '704%', 'ZQ_KZZ', 'SG', 'XSHG', '提供界面用户可以手工修改上市代码', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '122***', '7511%', 'ZQ_GZXQ', 'SG', 'XSHG', '国债现券', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75191%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75192%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75193%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75194%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75195%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '130***', '75196%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '122***', '75198%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('751***', '122***', '75199%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债', 'XZSG', 10.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('019***', '019***', '019%', 'ZQ_GZXQ', 'SG', 'XSHG', '固定收益电子交易平台交易国债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('60****', '60****', '60%', 'GP_GP', 'SG', 'XSHG', '股票', 'GPJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('100***', '100***', '100%', 'ZQ_KZZ', 'SG', 'XSHG', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('113***', '113***', '113%', 'ZQ_KZZ', 'SG', 'XSHG', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('112***', '112***', '112%', 'ZQ_KZZ', 'SG', 'XSHG', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('110***', '110***', '110%', 'ZQ_KZZ', 'SG', 'XSHG', '可转债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('010***', '010***', '010%', 'ZQ_GZXQ', 'SG', 'XSHG', '国债(2001-2009)年发行', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('009***', '009***', '009%', 'ZQ_GZXQ', 'SG', 'XSHG', '国债(2000年前发行)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('510**2', '510**2', '510%2', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF资金', 'JJJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('510**1', '510**1', '510%1', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, null)")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('205***', '205***', '205%', 'HG_ZYS', 'SG', 'XSHG', '债券质押式报价回购', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('020***', '020***', '020%', 'ZQ_GZXQ', 'SG', 'XSHG', '记账式贴现国债', 'ZQJY', 10.0000, 'SEC')")
				// edit by wzh 2017-12-14 BUG #183799 银河证券 - 清算上交所大宗交易库接口产生的大宗交易债券流水中数量翻倍，而且没有所含利息。
				//.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('122***', '122***', '122%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债（1220-1224） 登记债(1225-1229)', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('126***', '126***', '126%', 'ZQ_KFLZ', 'SG', 'XSHG', '分离交易的可转换公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('130***', '130***', '130%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('121***', '121***', '121%', 'ZQ_ZCZQH', 'SG', 'XSHG', '资产证券化', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('204***', '204***', '204%', 'HG_ZYS_GZXQ', 'SG', 'XSHG', '债券质押式回购', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('202***', '202***', '202%', 'HG_ZYS_QYZ', 'SG', 'XSHG', '企业债回购', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('201***', '201***', '201%', 'HG_ZYS_GZXQ', 'SG', 'XSHG', '国债回购（席位托管方式）', 'HGJY', 1000.0000, 'SEC')")
//				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('50****', '50****', '50%', 'JJ_FBS', 'SG', 'XSHG', '封闭式基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('510**0', '510**0', '510%0', 'JJ_KFS_ETF', 'SG', 'XSHG', 'ETF基金', 'JJJY', 100.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('120***', '120***', '120%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('129***', '129***', '129%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('099***', '009***', '099%', 'HG_ZYS_GZXQ', 'SG', 'XSHG', '新国债质押式回购质押券出入库(对应009国债)', 'HGJY', 1000.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('580***', '580***', '580%', 'QZ_QZ', 'SG', 'XSHG', '权证（公司权证，股改权证）', 'QZJY', 0.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('107***', '107***', '107%', 'ZQ_GZXQ', 'SG', 'XSHG', '记账式贴现国债出入库', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('106***', '106***', '106%', 'ZQ_DFZFZ', 'SG', 'XSHG', '地方政府债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1050%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('104***', '104***', '104%', 'ZQ_GSZ', 'SG', 'XSHG', '公司债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1059%', 'ZQ_QYZ', 'SG', 'XSHG', '企业债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1056%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1055%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1054%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1053%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1052%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
				.addSql("insert into T_S_SEC_TRANS (C_ZQDM, C_SEC, C_COND, C_SEC_VAR_CODE, C_CFG_CODE, C_MKT_CODE, C_DESC, C_TD_TYPE, N_AMOUNT_HD, C_TRANS_TYPE)values ('105***', '105***', '1051%', 'ZQ_KFLZ', 'SG', 'XSHG', '(1050**-1058**)分离债', 'ZQJY', 10.0000, 'SEC')")
		    	.endScript();
	}
	
	/**
	 * 指标模版参数关联表
	 * @param builder
	 */
	private void buildZBMBCSGLB(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("delete from T_S_INDEX_PARA_RELA where c_Index_Para_Code in ('SV_ZB_QSK','SV_ZB_WFZJZC','SV_ZB_QSK','SV_ZB_WFZJZC',"
					+ "'SV_JD_DWJZ_001','SV_JD_DWJZ_002','SV_JD_JZRZZ_001','SV_JD_JZRZZ_002','SV_JD_JZZZ_001','SV_JD_JZZZ_002','SV_JD_JZZZ_001',"
					+ "'SV_JD_JZZZ_002','SV_JD_DWSY_001','SV_JD_DWSY_002','SV_JD_WFSY_001','SV_JD_WFSY_002','SV_JD_WFSY_001','SV_JD_WFSY_002',"
					+ "'SV_JD_QRSY_001','SV_JD_QRSY_002','SV_JD_DWJZ_001','SV_JD_DWJZ_002') ")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('ZCHJ','SV_ZB_QSK')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('ZCHJ','SV_ZB_WFZJZC')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('FZHJ','SV_ZB_QSK')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('FZHJ','SV_ZB_WFZJZC')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('DWJZ','SV_JD_DWJZ_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('DWJZ','SV_JD_DWJZ_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('RJZZZL','SV_JD_JZRZZ_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('RJZZZL','SV_JD_JZRZZ_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('BQJZZZL','SV_JD_JZZZ_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('BQJZZZL','SV_JD_JZZZ_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('LJJZZZL','SV_JD_JZZZ_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('LJJZZZL','SV_JD_JZZZ_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('DWKFPSY','SV_JD_DWSY_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('DWKFPSY','SV_JD_DWSY_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('MWFSY','SV_JD_WFSY_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('MWFSY','SV_JD_WFSY_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('MWFBNLJSY','SV_JD_WFSY_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('MWFBNLJSY','SV_JD_WFSY_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('QRNHSYL','SV_JD_QRSY_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('QRNHSYL','SV_JD_QRSY_002')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('HYHLJJZ','SV_JD_DWJZ_001')")
				.addSql("insert into T_S_INDEX_PARA_RELA (c_index_code, c_Index_Para_Code) values ('HYHLJJZ','SV_JD_DWJZ_002')")
		.endScript();
	}
	
	/**
	 *更新索引算法
	 * @param builder
	 */
	private void buildUpdateT_D_INDEX(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("000012")
				
.addSql("update t_s_index a set a.c_data_source ='SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,"
+"     NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN 0 ELSE A.N_PORT_MONEY * B.N_FUND_WAY END), 0) N_PORT_IV"
+"  FROM T_D_AI_STOCK A"
+"  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE"
+" WHERE A.C_PORT_CODE = <PORT>"
+"   AND A.D_STOCK = <DATE>"
+"   AND A.C_DAI_CODE in ('||'''ZQTZ_CB'''||','||'''ZQTZ_GYBD'''||')"
+"   and A.c_sec_var_code = '||'''GP_GP_CYB'''||'"
+"   AND A.c_mkt_code ='||'''XSHE'''||'"
+"   and a.c_dv_issue_mode = '||'''SS'''"
+"where a.c_index_code ='LTGP_SZ_CYB_TZHJ'")
 
.addSql("update t_s_index a set a.c_data_source =  'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,"
+"     NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,"
+"     NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN 0 ELSE A.N_PORT_MONEY * B.N_FUND_WAY END), 0) N_PORT_IV"
+"  FROM T_D_AI_STOCK A"
+"  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE"
+" WHERE SUBSTR(A.c_Year_Month,-2,2) <> '||'''00'''||'"
+"   AND A.C_PORT_CODE = <PORT>"
+"   AND A.D_STOCK = <DATE>"
+"   AND A.C_DAI_CODE in ('||'''ZQTZ_CB'''||','||'''ZQTZ_GYBD'''||')"
+"   and a.c_sec_var_code like '||'''HG%'''"
+"where a.c_index_code ='QZHGTZ'")
 
.addSql("update t_s_index a set a.c_data_source = 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,"
+"     NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,"
+"     NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' THEN 0 ELSE A.N_PORT_MONEY * B.N_FUND_WAY END), 0) N_PORT_IV"
+"  FROM T_D_AI_STOCK A"
+"  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE"
+" WHERE SUBSTR(A.c_Year_Month,-2,2) <> '||'''00'''||'"
+"   AND A.C_PORT_CODE = <PORT>"
+"   AND A.D_STOCK = <DATE>"
+"   AND A.C_DAI_CODE in ('||'''ZQTZ_CB'''||','||'''ZQTZ_GYBD'''||' )"
+"   and a.c_sec_var_code like '||'''GP%'''||'"
+"   and a.c_sec_var_code not like '||'''GP_GQ%'''"
+"where a.c_index_code ='QZGPTZ'")
 
.addSql("update t_s_index a set a.c_data_source =  'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,"
+"     NVL(SUM(CASE WHEN A.C_DAI_CODE in ( '||'''ZQTZ_CB'''||','||'''ZQTZ_YZJ'''||') THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,"
+"     NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE in ( '||'''ZQTZ_CB'''||','||'''ZQTZ_YZJ'''||') THEN 0 ELSE A.N_PORT_MONEY * B.N_FUND_WAY END), 0) N_PORT_IV"
+"  FROM T_D_AI_STOCK A"
+"  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE"
+" WHERE SUBSTR(A.c_Year_Month,-2,2) <> '||'''00'''||'"
+"   AND A.C_PORT_CODE = <PORT>"
+"   AND A.D_STOCK = <DATE>"
+"   AND A.C_DAI_CODE in ('||'''ZQTZ_CB'''||','||'''ZQTZ_GYBD'''||','||'''ZQTZ_YZJ'''||')"
+"   and a.c_sec_var_code like '||'''ZQ%'''"
+"where a.c_index_code ='QZZQTZ'")

.addSql("update t_s_index a set a.c_data_source =  'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,"
+"     NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||' or A.C_DAI_CODE = '||'''YSGJ_CB'''||'  or A.C_DAI_CODE = '||'''ZQTZ_YZJ'''||' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,"
+"     NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV,"
+"       NVL(SUM(CASE WHEN A.C_DAI_CODE = '||'''ZQTZ_CB'''||'  or A.C_DAI_CODE = '||'''YSGJ_CB'''||' or A.C_DAI_CODE = '||'''ZQTZ_YZJ'''||' THEN 0 ELSE A.N_PORT_MONEY * B.N_FUND_WAY END), 0) N_PORT_IV"
+"  FROM T_D_AI_STOCK A"
+"  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE"
+" WHERE SUBSTR(A.c_Year_Month,-2,2) <> '||'''00'''||'"
+"   AND A.C_PORT_CODE = <PORT>"
+"   AND A.D_STOCK = <DATE>"
+"   AND A.C_DAI_CODE in ('||'''ZQTZ_CB'''||','||'''ZQTZ_GYBD'''||','||'''ZQTZ_YZJ'''||','||'''YSGJ_CB'''||','||'''YSGJ_GYBD'''||')' "
+"where a.c_index_code ='ZQTZHJ'")
.addSql("update t_s_index a set a.c_key_name = a.c_index_name where a.c_index_code in ('JRKYTC_FBZ_BHQSK','JRKYTC_FBZ_HQSK')")
				.endScript();
				
	}
	
	/**
	 * 估值指标方案
	 * @param builder
	 */
	private void buildGZZBFA(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TVKZBLCLS', 'TV看涨/TV看跌', null, 'ALGO', 1, 1112, 'TOTAL_ALL', 0, 'TVKZBLCLS', 'TV看涨/TV看跌', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SSZB', '实收资本', 'sszbOper', 'SYS', 1, 811, 'TOTAL', 3, 'SSZB', '实收资本', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SSZB_FETZ', '实收资本-份额调整', 'sszbFezsOper', 'SYS', 1, 812, 'TOTAL', 0, 'SSZB_FETZ', '实收资本-份额调整', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RJZZZL', '日净值增长率', 'rjzzzlOper', 'SYS', 1, 910, 'TOTAL_ALL', 0, 'RJZZZL', '日净值增长率', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'BQJZZZL', '本期净值增长率', 'bqjzzzlOper', 'SYS', 1, 911, 'TOTAL_ALL', 0, 'BQJZZZL', '本期净值增长率', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJJZZZL', '累计净值增长率', 'ljjzzzlOper', 'SYS', 1, 912, 'TOTAL_ALL', 0, 'LJJZZZL', '累计净值增长率', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SXSY', '实现收益', 'sxsyOper', 'SYS', 1, 920, 'TOTAL_ALL', 0, 'SXSY', '实现收益', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'KFPSY', '可分配收益', 'kfpsyOper', 'SYS', 1, 921, 'TOTAL_ALL', 0, 'KFPSY', '可分配收益', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWKFPSY', '单位可分配收益', 'dwKfpsyOper', 'SYS', 1, 922, 'TOTAL_ALL', 0, 'DWKFPSY', '单位可分配收益', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'XJLZJZB', '现金类占净值比', 'xjlZjzbOper', 'SYS', 1, 999, 'TOTAL_ALL', 0, 'XJLZJZB', '现金类占净值比', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'BRSY', '本日收益', 'brsyOper', 'SYS', 1, 950, 'TOTAL_ALL', 2, 'BRSY', '本日收益', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'MWFSY', '每万份收益', 'mwfsyOper', 'SYS', 1, 951, 'TOTAL_ALL', 2, 'MWFSY', '每万份收益', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJBRSY', '期间累计本日收益', 'qjljbrsyOper', 'SYS', 1, 952, 'TOTAL_ALL', 2, 'LJBRSY', '期间累计本日收益', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QJLJWFSY', '期间累计万份收益', 'qjljmwfsyOper', 'SYS', 1, 953, 'TOTAL_ALL', 2, 'QJLJWFSY', '期间累计万份收益', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZCJZ', '资产净值', 'zcjzOper', 'SYS', 1, 841, 'TOTAL', 3, 'ZCJZ', '资产净值', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWJZ', '单位净值', 'jrdwjzOper', 'SYS', 1, 902, 'TOTAL_ALL', 0, 'DWJZ', '单位净值', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZCJZ_TZZLR', '稠州银行资产净值', 'select sum(a.n_port_mv * case when a.n_way=0 then 1 else a.n_way end) N_PORT_MV ,sum(a.n_port_cost * case when a.n_way=0 then 1 else a.n_way end) N_PORT_COST from t_r_fr_aststat a where a.c_port_code = <PORT> and a.d_aststat=<DATE> and a.c_key_code in (''ZCJZ'',''YYL_TZZLR'',''YYL_BHLR'',''YYL_TGF'')', 'CFG', 1, 842, 'TOTAL', 0, 'ZCJZ_TZZLR', '稠州银行资产净值', 'N', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'PLD', '偏离度', 'pldOper', 'SYS', 1, 849, 'TOTAL_ALL', 2, 'PLD', '偏离度', 'HBJJJBZB', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'PLJE', '偏离金额', 'pljeOper', 'SYS', 1, 848, 'TOTAL_ALL', 2, 'PLJE', '偏离金额', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SYQX', '投资组合剩余期限', 'portSyqxOper', 'SYS', 1, 957, 'TOTAL_ALL', 2, 'SYQX', '投资组合剩余期限', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'BRSYCLS', '分级本日收益', 'brsyClsOper', 'SYS', 1, 950, 'TOTAL_ALL', 2, 'BRSY', '本日收益', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'MWFSYCLS', '分级每万份收益', 'mwfsyClsOper', 'SYS', 1, 951, 'TOTAL_ALL', 2, 'MWFSY', '每万份收益', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJBRSYCLS', '分级期间累计本日收益', 'qjljbrsyClsOper', 'SYS', 1, 952, 'TOTAL_ALL', 2, 'LJBRSY', '期间累计本日收益', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QJLJWFSYCLS', '分级期间累计万份收益', 'qjljmwfsyClsOper', 'SYS', 1, 953, 'TOTAL_ALL', 2, 'QJLJWFSY', '期间累计万份收益', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'MWFBNLJSYCLS', '分级每万份本年累计收益', 'mwfbnljsyClsOper', 'SYS', 1, 955, 'TOTAL_ALL', 2, 'MWFBNLJSY', '每万份本年累计收益', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QRNHSYLCLS', '分级七日年化收益率', 'qrnhsylClsOper', 'SYS', 1, 956, 'TOTAL_ALL', 2, 'QRNHSYL', '七日年化收益率', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SSZBCLS', '分级实收资本', 'qyItemOper', 'SYS', 1, 811, 'TOTAL', 1, 'SSZB', '实收资本', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZCJZCLS', '分级资产净值', 'zcjzOper', 'SYS', 1, 841, 'TOTAL', 1, 'ZCJZ', '资产净值', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FQZCJZCLS', '分级费前资产净值', 'fqfjzcjzOper', 'SYS', 1, 840, 'TOTAL', 1, 'FQZCJZ', '费前资产净值', 'RQFIIZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWJZCLS', '分级单位净值', 'jrdwjzOper', 'SYS', 1, 901, 'TOTAL_ALL', 1, 'DWJZ', '单位净值', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QCDWJZCLS', '分级期初单位净值', 'qcdwjzOper', 'SYS', 1, 900, 'TOTAL_ALL', 1, 'QCDWJZ', '期初单位净值', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZRDWJZCLS', '分级昨日单位净值', 'zrdwjzOper', 'SYS', 1, 902, 'TOTAL_ALL', 1, 'ZRDWJZ', '昨日单位净值', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RJZZZLCLS', '分级日净值增长率', 'rjzzzlOper', 'SYS', 1, 910, 'TOTAL_ALL', 1, 'RJZZZL', '日净值增长率', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'BQJZZZLCLS', '分级本期净值增长率', 'bqjzzzl', 'SYS', 1, 911, 'TOTAL_ALL', 1, 'BQJZZZL', '本期净值增长率', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJPXJECLS', '分级累计派现金额', 'ljpxjeOper', 'SYS', 1, 903, 'TOTAL_ALL', 1, 'LJPXJE', '累计派现金额', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJDWJZCLS', '分级累计单位净值', 'ljdwjzOper', 'SYS', 1, 904, 'TOTAL_ALL', 1, 'LJDWJZ', '累计单位净值', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SXSYCLS', '分级实现收益', 'sxsyOper', 'SYS', 1, 920, 'TOTAL_ALL', 1, 'SXSY', '实现收益', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'KFPSYCLS', '分级可分配收益', 'kfpsyOper', 'SYS', 1, 921, 'TOTAL_ALL', 1, 'KFPSY', '可分配收益', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'GGQQTZHJ', '个股期权投资合计', null, 'ALGO', 1, 1113, 'TOTAL', 0, 'GGQQTZHJ', '个股期权投资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'GGQQBZJHJ', '个股期权保证金合计', null, 'ALGO', 1, 1114, 'TOTAL', 0, 'GGQQBZJHJ', '个股期权保证金合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RZYWTZHJ', '融资业务投资合计', null, 'ALGO', 1, 1115, 'TOTAL', 0, 'RZYWTZHJ', '融资业务投资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RQYWTZHJ', '融券业务投资合计', null, 'ALGO', 1, 1116, 'TOTAL', 0, 'RQYWTZHJ', '融券业务投资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TYSCRZHJ', '同业市场融资合计', null, 'ALGO', 1, 1117, 'TOTAL', 0, 'TYSCRZHJ', '同业市场融资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TYSCRQHJ', '同业市场融券合计', null, 'ALGO', 1, 1118, 'TOTAL', 0, 'TYSCRQHJ', '同业市场融券合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RZYW_MDSHGTZHJ', '买断式回购融资业务投资合计', null, 'ALGO', 1, 1119, 'TOTAL', 0, 'RZYW_MDSHGTZHJ', '买断式回购融资业务投资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'RQYW_MDSHGTZHJ', '买断式回购融券业务投资合计', null, 'ALGO', 1, 1120, 'TOTAL', 0, 'RQYW_MDSHGTZHJ', '买断式回购融券业务投资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TYSC_MDSHGRZHJ', '同业市场买断式回购融资合计', null, 'ALGO', 1, 1121, 'TOTAL', 0, 'TYSC_MDSHGRZHJ', '同业市场买断式回购融资合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TYSC_MDSHGRQHJ', '同业市场买断式回购融券合计', null, 'ALGO', 1, 1122, 'TOTAL', 0, 'TYSC_MDSHGRQHJ', '同业市场买断式回购融券合计', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SBJJ_SYQX', '投资组合剩余期限_社保', '<CLOB>', 'ALGO', 1, 1122, 'TOTAL_ALL', 0, 'SBJJ_SYQX', '投资组合剩余期限_社保', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWJZ_KFQ', '单位净值_扣费前', '<CLOB>', 'ALGO', 1, 1125, 'TOTAL_ALL', 0, 'DWJZ_KFQ', '单位净值_扣费前', ' ', 'SV_JD_DWJZ_002', 'CFG', 'SV_JD_DWJZ_001')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FCKJSY', '非存款净收益', '<CLOB>', 'ALGO', 1, 1100, 'TOTAL_ALL', 0, 'FCKJSY', '非存款净收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'CKJSY', '存款净收益', '<CLOB>', 'ALGO', 1, 1100, 'TOTAL_ALL', 0, 'CKJSY', '存款净收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'AZSF', 'A值', null, 'SYS', 1, 901, 'TOTAL_ALL', 0, 'AZSF', 'A值', null, 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWKFPSYCLS', '分级单位可分配收益', 'dwKfpsyOper', 'SYS', 1, 922, 'TOTAL_ALL', 1, 'DWKFPSY', '单位可分配收益', 'PTFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SYPZJ_YSX', '损益平准-已实现', 'sypzjYsxOper', 'SYS', 1, 821, 'TOTAL', 0, 'SYPZJ_YSX', '损益平准-已实现', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SYPZJ_WSX', '损益平准-未实现', 'sypzjWsxOper', 'SYS', 1, 822, 'TOTAL', 0, 'SYPZJ_WSX', '损益平准-未实现', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SYPZJ', '损益平准金', 'sypzjOper', 'SYS', 1, 820, 'TOTAL', 0, 'SYPZJ', '损益平准金', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZCHJ', '资产合计', 'zchjOper', 'SYS', 1, 831, 'TOTAL', 3, 'ZCHJ', '资产合计', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FZHJ', '负债合计', 'fzhjOper', 'SYS', 1, 832, 'TOTAL', 3, 'FZHJ', '负债合计', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FQZCJZ', '费前资产净值', 'fqzzcjzOper', 'SYS', 1, 840, 'TOTAL', 0, 'FQZCJZ', '费前资产净值', 'RQFIIZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QCDWJZ', '期初单位净值', 'qcdwjzOper', 'SYS', 1, 900, 'TOTAL_ALL', 0, 'QCDWJZ', '期初单位净值', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'MWFBNLJSY', '每万份本年累计收益', 'mwfbnljsyOper', 'SYS', 1, 955, 'TOTAL_ALL', 2, 'MWFBNLJSY', '每万份本年累计收益', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QRNHSYL', '七日年化收益率', 'qrnhsylOper', 'SYS', 1, 956, 'TOTAL_ALL', 2, 'QRNHSYL', '七日年化收益率', 'HBJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'ZRDWJZ', '昨日单位净值', 'zrdwjzOper', 'SYS', 1, 902, 'TOTAL_ALL', 0, 'ZRDWJZ', '昨日单位净值', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJPXJE', '累计派现金额', 'ljpxjeOper', 'SYS', 1, 903, 'TOTAL_ALL', 0, 'LJPXJE', '累计派现金额', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJDWJZ', '累计单位净值', 'ljdwjzOper', 'SYS', 1, 904, 'TOTAL_ALL', 0, 'LJDWJZ', '累计单位净值', 'PTJJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'DWXJCE', '单位现金差额', 'dwxjceOper', 'SYS', 1, 971, 'TOTAL_ALL', 0, 'DWXJCE', '单位现金差额', 'ETFZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'HYHLJJZ', '还原后份额累计净值', 'hyhljjzOper', 'SYS', 1, 972, 'TOTAL_ALL', 0, 'HYHLJJZ', '还原后份额累计净值', 'ETFZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'SSZBHBCLS', '分级实收资本', 'qyItemOper', 'SYS', 1, 811, 'TOTAL', 2, 'SSZB', '实收资本', 'HBFJZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FDSY', '基金浮动收益', null, 'ALGO', 1, 1100, 'TOTAL_ALL', 0, 'FDSY', '基金浮动收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FDSYL', '基金浮动收益率', null, 'ALGO', 1, 1101, 'TOTAL_ALL', 0, 'FDSYL', '基金浮动收益率', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'GDSY', '基金固定收益', null, 'ALGO', 1, 1102, 'TOTAL_ALL', 0, 'GDSY', '基金固定收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'GDSYL', '基金固定收益率', null, 'ALGO', 1, 1103, 'TOTAL_ALL', 0, 'GDSYL', '基金固定收益率', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJFDSY', '累积浮动收益', null, 'ALGO', 1, 1104, 'TOTAL_ALL', 0, 'LJFDSY', '累积浮动收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJGDSY', '累积固定收益', null, 'ALGO', 1, 1105, 'TOTAL_ALL', 0, 'LJGDSY', '累积固定收益', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'NHSYL', '年化收益率', null, 'ALGO', 1, 1106, 'TOTAL_ALL', 0, 'NHSYL', '年化收益率', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QRFDNHSYL', '七日浮动年化收益率', null, 'ALGO', 1, 1107, 'TOTAL_ALL', 0, 'QRFDNHSYL', '七日浮动年化收益率', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QRGDNHSYL', '七日固定年化收益率', null, 'ALGO', 1, 1108, 'TOTAL_ALL', 0, 'QRGDNHSYL', '七日固定年化收益率', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'YGNHSYL', '预估年化收益率', null, 'ALGO', 1, 1109, 'TOTAL_ALL', 0, 'YGNHSYL', '预估年化收益率', ' ', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'GGBLCLS', '杠杆比例', null, 'ALGO', 1, 1110, 'TOTAL_ALL', 0, 'GGBLCLS', '杠杆比例', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'FEPBCLS', '份额配比', null, 'ALGO', 1, 1111, 'TOTAL_ALL', 0, 'FEPBCLS', '份额配比', ' ', 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'TZZHPJSYCXQ', '投资组合平均剩余存续期', 'portPjsyqxOper', 'SYS', 1, 9991, 'TOTAL_ALL', 2, 'TZZHPJSYCXQ', '投资组合平均剩余存续期', 'JJJBZB', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'XZAZ', '修正A值', null, 'SYS', 1, 901, 'TOTAL_ALL', 0, 'XZAZ', '修正A值', null, 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'XZDWJZ', '修正单位净值', null, 'SYS', 1, 902, 'TOTAL_ALL', 0, 'XZDWJZ', '修正单位净值', null, 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'QHKHQY', '期货客户权益', null, 'SYS', 1, 6000, 'TOTAL', 0, 'QHKHQY', '期货客户权益', null, 'JW_ROUND', 'CFG', '4')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'MRKYTC', '明日可用头寸', '<CLOB>', 'ALGO', 1, 1130, 'TOTAL_ALL', 0, 'MRKYTC', '明日可用头寸', 'N', 'JW_ROUND', 'CFG', '2')")
				.addSql("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET) values ( sequ_S_INDEX.Nextval, 'LJDWJZ_KFQ', '累计单位净值_扣费前', '<CLOB>', 'ALGO', 1, 1126, 'TOTAL_ALL', 0, 'LJDWJZ_KFQ', '累计单位净值_扣费前', ' ', 'SV_JD_DWJZ_002', 'CFG', 'SV_JD_DWJZ_001')");
				
				 
		StringBuffer buf = new StringBuffer();

		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'GPTZ', '股票投资', '  SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE IN (''ZQTZ_CB'',''ZQTZ_GYBD'')");
		buf.append("   AND A.C_SEC_VAR_CODE like ''GP%''', 'CFG', 1, 102, 'TOTAL', 0, 'GPTZ', '股票投资', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZCJZ_USD', '资产净值_美元', 'SELECT NVL(A.N_AMOUNT, 0) N_AMOUNT,C_PORT_CLS_CODE,");
//		buf.append("       ROUND(NVL(A.N_PORT_COST, 0) /");
//		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),");
//		buf.append("             2) N_PORT_COST,");
//		buf.append("       ROUND(NVL(A.N_PORT_MV, 0) /");
//		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),");
//		buf.append("             2) N_PORT_MV,");
//		buf.append("       ROUND(NVL(A.N_PORT_IV, 0) /");
//		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),");
//		buf.append("             2) N_PORT_IV");
//		buf.append("  FROM (SELECT 0 AS N_AMOUNT,C_PORT_CLS_CODE,");
//		buf.append("               SUM(ZC_PORT_COST - FZ_PORT_COST) AS N_PORT_COST,");
//		buf.append("               SUM(ZC_PORT_MV - FZ_PORT_MV) AS N_PORT_MV,");
//		buf.append("               SUM(ZC_PORT_IV - FZ_PORT_IV) AS N_PORT_IV");
//		buf.append("          FROM (SELECT N_PORT_COST AS ZC_PORT_COST,");
//		buf.append("                       N_PORT_MV   AS ZC_PORT_MV,");
//		buf.append("                       N_PORT_IV   AS ZC_PORT_IV,");
//		buf.append("                       0           AS FZ_PORT_COST,");
//		buf.append("                       0           AS FZ_PORT_MV,");
//		buf.append("                       0           AS FZ_PORT_IV,");
//		buf.append("                       ''159988'' AS C_PORT_CLS_CODE");
//		buf.append("                  FROM T_R_FR_ASTSTAT A");
//		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("                   AND A.D_ASTSTAT = <DATE>");
//		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("                   AND A.C_KEY_CODE = ''ZCHJ''");
//		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0");
//		buf.append("                UNION ALL");
//		buf.append("                SELECT 0           AS ZC_PORT_COST,");
//		buf.append("                       0           AS ZC_PORT_MV,");
//		buf.append("                       0           AS ZC_PORT_IV,");
//		buf.append("                       N_PORT_COST AS FZ_PORT_COST,");
//		buf.append("                       N_PORT_MV   AS FZ_PORT_MV,");
//		buf.append("                       N_PORT_IV   AS FZ_PORT_IV,");
//		buf.append("                       ''159988'' AS C_PORT_CLS_CODE");
//		buf.append("                  FROM T_R_FR_ASTSTAT A");
//		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("                   AND A.D_ASTSTAT = <DATE>");
//		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("                   AND A.C_KEY_CODE = ''FZHJ''");
//		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0)GROUP BY C_PORT_CLS_CODE) A");
//		buf.append("  LEFT JOIN (select c_rate_expr");
//		buf.append("               from T_D_AI_VAL_PR");
//		buf.append("              where c_pr_type = ''R''");
//		buf.append("                and c_val_pr_code = ''USD''");
//		buf.append("                and c_port_code = <PORT>");
//		buf.append("                and d_val_acct = <DATE>) B");
//		buf.append("    ON 1 = 1', 'CFG', 1, 841, 'TOTAL', 1, 'ZCJZ_USD', '资产净值_美元', 'N', 'JW_ROUND', 'CFG', '2')");
//		builder.addSql(buf);
//		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQTZ', '债券投资', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE IN (''ZQTZ_CB'',''ZQTZ_GYBD'')");
		buf.append("   AND A.C_SEC_VAR_CODE like ''ZQ%''', 'CFG', 1, 103, 'TOTAL', 0, 'ZQTZ', '债券投资', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZCJZ_RMB', '资产净值_人民币', 'SELECT NVL(A.N_AMOUNT, 0) N_AMOUNT,");
		buf.append("       NVL(A.N_PORT_COST, 0) N_PORT_COST,");
		buf.append("       NVL(A.N_PORT_MV, 0) N_PORT_MV,");
		buf.append("       NVL(A.N_PORT_IV, 0) N_PORT_IV,");
		buf.append("       C_PORT_CLS_CODE");
		buf.append("  FROM (SELECT 0 AS N_AMOUNT,C_PORT_CLS_CODE,");
		buf.append("               SUM(ZC_PORT_COST - FZ_PORT_COST) AS N_PORT_COST,");
		buf.append("               SUM(ZC_PORT_MV - FZ_PORT_MV) AS N_PORT_MV,");
		buf.append("               SUM(ZC_PORT_IV - FZ_PORT_IV) AS N_PORT_IV");
		buf.append("          FROM (SELECT N_PORT_COST AS ZC_PORT_COST,");
		buf.append("                       N_PORT_MV   AS ZC_PORT_MV,");
		buf.append("                       N_PORT_IV   AS ZC_PORT_IV,");
		buf.append("                       0           AS FZ_PORT_COST,");
		buf.append("                       0           AS FZ_PORT_MV,");
		buf.append("                       0           AS FZ_PORT_IV,");
		buf.append("                       ''888888'' AS C_PORT_CLS_CODE");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''ZCHJ''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0");
		buf.append("                UNION ALL");
		buf.append("                SELECT 0           AS ZC_PORT_COST,");
		buf.append("                       0           AS ZC_PORT_MV,");
		buf.append("                       0           AS ZC_PORT_IV,");
		buf.append("                       N_PORT_COST AS FZ_PORT_COST,");
		buf.append("                       N_PORT_MV   AS FZ_PORT_MV,");
		buf.append("                       N_PORT_IV   AS FZ_PORT_IV,");
		buf.append("                       ''888888'' AS C_PORT_CLS_CODE");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''FZHJ''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0)GROUP BY C_PORT_CLS_CODE) A', 'CFG', 1, 841, 'TOTAL', 1, 'ZCJZ_RMB', '资产净值_人民币', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZZYWSR', '中间业务收入', 'select nvl(sum(a.n_port_money * -1), 0) as C_KM_NAME,");
		buf.append("       nvl(sum(a.n_port_money * -1), 0) as N_VA_RATE");
		buf.append("  from t_d_ai_stock a");
		buf.append(" where a.c_dai_code in (''LRFP_WFP_YSX'', ''LRFP_WFP_WSX'')");
		buf.append("   and a.c_port_code = < PORT >");
		buf.append("   and a.d_stock = < DATE >', 'CFG', 1, 924, 'TOTAL_ALL', 0, 'ZZYWSR', '中间业务收入', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'YFTZZLR', '应付投资者利润', 'select nvl(sum(a.n_port_money * -1), 0) as C_KM_NAME,");
		buf.append("       nvl(sum(a.n_port_money * -1), 0) as N_VA_RATE");
		buf.append("  from t_d_ai_stock a");
		buf.append(" where a.c_dai_code = ''LRFP_YFLR''");
		buf.append("   and a.c_port_code = < PORT >");
		buf.append("   and a.d_stock = < DATE >', 'CFG', 1, 923, 'TOTAL_ALL', 0, 'YFTZZLR', '应付投资者利润', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'YFTZZLRCLS', '分级应付投资者利润', 'select nvl(sum(n_port_money * -1), 0) as C_KM_NAME,");
		buf.append("       nvl(sum(n_port_money * -1), 0) as N_VA_RATE,");
		buf.append("       c_port_cls_code as C_PORT_CLS_CODE");
		buf.append("  from t_d_ai_stock a");
		buf.append(" where a.c_dai_code = ''LRFP_YFLR''");
		buf.append("   and a.c_port_code = < PORT >");
		buf.append("   and a.d_stock = < DATE >");
		buf.append(" group by c_port_cls_code', 'CFG', 1, 923, 'TOTAL_ALL', 1, 'YFTZZLR', '应付投资者利润', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'QTYSGJ_TZHJ', '其中其他衍生工具投资', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''YSGJ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''YSGJ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE IN (''YSGJ_CB'',''YSGJ_GYBD'')");
		buf.append("   AND A.C_SEC_VAR_CODE in (''QH_GZ'',''QH_SP'',''QH_ZQ'')', 'CFG', 1, 1123, 'TOTAL', 0, 'QTYSGJ_TZHJ', '其中其他衍生工具投资', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'LTGP_TZHJ', '流通股票投资合计', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("   and A.c_sec_var_code like ''GP%''");
		//BUG241005【华宝基金】组合表上指标项顺序混乱
//		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 102, 'TOTAL', 0, 'LTGP_TZHJ', '流通股票投资合计', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 201, 'TOTAL', 0, 'LTGP_TZHJ', '流通股票投资合计', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'LTGP_SH_TZHJ', '其中上海流通股票', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("   and A.c_sec_var_code like ''GP%''");
		buf.append("   AND A.c_mkt_code =''XSHG''");
		//BUG241005【华宝基金】组合表上指标项顺序混乱
//		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 102, 'TOTAL', 0, 'LTGP_SH_TZHJ', '其中上海流通股票', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 202, 'TOTAL', 0, 'LTGP_SH_TZHJ', '其中上海流通股票', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'LTGP_SZ_TZHJ', '其中深圳流通股票', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("   and A.c_sec_var_code like ''GP%''");
		buf.append("   AND A.c_mkt_code =''XSHE''");
		//BUG241005【华宝基金】组合表上指标项顺序混乱
//		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 102, 'TOTAL', 0, 'LTGP_SZ_TZHJ', '其中深圳流通股票', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		buf.append("   and a.c_dv_issue_mode = ''SS''', 'CFG', 1, 203, 'TOTAL', 0, 'LTGP_SZ_TZHJ', '其中深圳流通股票', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'KFS_PTJJ_TZHJ', '其中开放式_普通基金', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("   and A.c_sec_var_code =''JJ_KFS''', 'CFG', 1, 16130, 'TOTAL', 0, 'KFS_PTJJ_TZHJ', '其中开放式_普通基金', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'QZJJTZ', '其中基金投资', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("   and A.c_sec_var_code like ''JJ%''', 'CFG', 1, 1300, 'TOTAL', 0, 'QZJJTZ', '其中基金投资', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'JJJY_TZHJ', '基金投资合计', 'SELECT NVL(SUM(A.N_AMOUNT * B.N_FUND_WAY), 0) N_AMOUNT,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_ORIG_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_ORIG_COST,");
		buf.append("       NVL(SUM(A.N_ORIG_MONEY * B.N_FUND_WAY), 0) N_ORIG_MV,");
		buf.append("       0 N_ORIG_IV,");
		buf.append("       NVL(SUM(CASE WHEN A.C_DAI_CODE = ''ZQTZ_CB'' THEN A.N_PORT_MONEY * B.N_FUND_WAY ELSE 0 END), 0) N_PORT_COST,");
		buf.append("       NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
		buf.append("  FROM T_D_AI_STOCK A");
		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B ON A.C_DAI_CODE = B.C_DAI_CODE");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_STOCK = <DATE>");
		buf.append("   AND A.C_DAI_CODE = ''ZQTZ_CB''");
		buf.append("    and A.c_sec_var_code like ''JJ%''', 'CFG', 1, 105, 'TOTAL', 0, 'JJJY_TZHJ', '基金投资合计', 'JJJBZB', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'DWJZ_RMB', '单位净值_人民币', 'SELECT cast (round(NVL(A.N_PORT_COST, 0.0),4) as number(8,4))  N_VA_RATE,");
		buf.append("       cast (round(NVL(A.N_PORT_COST, 0.0),4) as number(8,4))  C_KM_NAME,");
		buf.append("       ''888888'' AS C_PORT_CLS_CODE");
		buf.append("  FROM (SELECT SUM(ZCJZ) / SUM(SSZB) AS N_PORT_COST");
		buf.append("          FROM (SELECT N_PORT_MV AS ZCJZ, 0 AS SSZB");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''ZCJZ''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0");
		buf.append("                UNION ALL");
		buf.append("                SELECT 0 AS ZCJZ, DECODE(N_AMOUNT, 0, 1, N_AMOUNT) AS SSZB");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''SSZB''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0)) A', 'CFG', 1, 901, 'TOTAL_ALL', 1, 'DWJZ_RMB', '单位净值_人民币', 'N', 'SV_JD_DWJZ_002', 'SYS', 'SV_JD_DWJZ_001')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'DWJZ_USD', '单位净值_美元', 'SELECT ROUND(NVL(A.N_PORT_COST, 0)/");
		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),4) N_VA_RATE,");
		buf.append("       ROUND(NVL(A.N_PORT_COST, 0)/");
		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),4) C_KM_NAME,");
		buf.append("                              ''159988'' AS C_PORT_CLS_CODE");
		buf.append("  FROM (SELECT SUM(ZCJZ) / SUM(SSZB) AS N_PORT_COST");
		buf.append("          FROM (SELECT N_PORT_MV AS ZCJZ, 0 AS SSZB");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''ZCJZ''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0");
		buf.append("                UNION ALL");
		buf.append("                SELECT 0 AS ZCJZ, DECODE(N_AMOUNT, 0, 1, N_AMOUNT) AS SSZB");
		buf.append("                  FROM T_R_FR_ASTSTAT A");
		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
		buf.append("                   AND A.D_ASTSTAT = <DATE>");
		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
		buf.append("                   AND A.C_KEY_CODE = ''SSZB''");
		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') = 0)) A");
		buf.append(" LEFT JOIN (select c_rate_expr");
		buf.append("               from T_D_AI_VAL_PR");
		buf.append("              where c_pr_type = ''R''");
		buf.append("                and c_val_pr_code = ''USD''");
		buf.append("                and c_port_code = <PORT>");
		buf.append("                and d_val_acct = <DATE>) B");
		buf.append("    ON 1 = 1', 'CFG', 1, 901, 'TOTAL_ALL', 1, 'DWJZ_USD', '单位净值_美元', 'N', 'JW_ROUND', 'CFG', '4')");
		builder.addSql(buf);
		buf.setLength(0);
		
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZCJZ_RMB_CLS', '分级单位净值_人民币', 'SELECT ROUND(NVL(A.N_PORT_COST, 0) /");
//		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) - instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),");
//		buf.append("             4) N_VA_RATE,");
//		buf.append("       ROUND(NVL(A.N_PORT_COST, 0) /");
//		buf.append("             to_number(substr(NVL(c_rate_expr, 1),");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) + 1,");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''/'', 1) -");
//		buf.append("                              instr(NVL(c_rate_expr, 1), ''*'', 1) - 1)),");
//		buf.append("             4) C_KM_NAME,");
//		buf.append("       C_KM_CODE AS C_PORT_CLS_CODE");
//		buf.append("  FROM (SELECT ZCJZ/SSZB AS N_PORT_COST,C_KM_CODE");
//		buf.append("          FROM (");
//		buf.append("         select sum(ZCJZ) as ZCJZ,sum(SSZB) AS SSZB,c_km_code");
//		buf.append(" from (SELECT N_PORT_MV AS ZCJZ, 0 AS SSZB,substr(c_km_code,5) as c_km_code");
//		buf.append("                  FROM T_R_FR_ASTSTAT A");
//		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("                   AND A.D_ASTSTAT =<DATE>");
//		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("                   AND A.C_KEY_CODE = ''ZCJZ''");
//		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'') > 0");
//		buf.append("                UNION ALL");
//		buf.append("                SELECT 0 AS ZCJZ, DECODE(N_AMOUNT, 0, 1, N_AMOUNT) AS SSZB,substr(c_km_code,5) as c_km_code");
//		buf.append("                  FROM T_R_FR_ASTSTAT A");
//		buf.append("                 WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("                   AND A.D_ASTSTAT = <DATE>");
//		buf.append("                   AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("                   AND A.C_KEY_CODE = ''SSZB''");
//		buf.append("                   AND INSTR(A.C_KM_CODE, ''_'')> 0 )");
//		buf.append("                       group by c_km_code");
//		buf.append("                   ) ) A");
//		buf.append("   join t_p_aa_port_cls cls on cls.c_port_cls_code = a.c_km_code");
//		buf.append("   JOIN (select c_rate_expr,c_val_pr_code");
//		buf.append("               from T_D_AI_VAL_PR");
//		buf.append("              where c_pr_type = ''R''");
//		buf.append("                and c_val_pr_code = ''CNY''");
//		buf.append("                and c_port_code =<PORT>");
//		buf.append("");
//		buf.append("                and d_val_acct = <DATE>) B");
//		buf.append("    ON c_val_pr_code = b.c_val_pr_code");
//		buf.append("    and cls.c_dc_code = c_val_pr_code', 'CFG', 1, 901, 'TOTAL_ALL', 1, 'DWJZ_RMB', '单位净值_人民币', 'N', 'JW_TRUNC', 'CFG', '3')");
//		builder.addSql(buf);
//		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_AJSYL', 'A级收益率', 'SELECT trim(to_char(round(CASE");
		buf.append("                        WHEN nvl(SUM(N_LJJZSZ_MONEY), 0) = 0 THEN");
		buf.append("                         0");
		buf.append("                        ELSE");
		buf.append("                         (nvl(SUM(N_LJCESY_MONEY), 0) + nvl(SUM(N_GSCESY_MONEY), 0) +");
		buf.append("                         nvl(SUM(N_LJGD_MONEY), 0)) * 365 * 100 /");
		buf.append("                         nvl(SUM(N_LJJZSZ_MONEY), 0)");
		buf.append("                      END,5),''9999999999999999999999990D999'') || ''%'') as C_KM_NAME");
		buf.append("  FROM T_D_AC_SELL_TA_ZQT A");
		buf.append("  JOIN (select *");
		buf.append("          from T_P_AA_PORT_CLS");
		buf.append("         where N_CHECK_STATE >= 0");
		buf.append("           AND c_dv_port_cls = ''PORT_A'') B");
		buf.append("    ON A.C_PORT_CODE = B.C_PORT_CODE");
		buf.append("   AND A.C_PORT_CLS_CODE = B.C_PORT_CLS_CODE");
		buf.append(" WHERE A.C_PORT_CODE = < PORT >");
		buf.append("   AND A.D_TRADE = < DATE >', 'CFG', 1, 1204, 'TOTAL_ALL', 0, 'ZQT_AJSYL', 'A级收益率', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_AKZQSZ', 'A款证券市值 ', 'SELECT nvl(sum(A.N_SEC_MONEY*B.N_PRICE_CLOSE),0) as C_KM_NAME");
//		buf.append("  FROM T_D_AC_SELL_TA_ZQT A");
//		buf.append("  JOIN (SELECT C_SEC_CODE,MAX(N_PRICE_CLOSE) as N_PRICE_CLOSE from T_D_MP_SEC_MKT");
//		buf.append("   where D_MKT IN (SELECT MAX(D_MKT) from T_D_MP_SEC_MKT A");
//		buf.append("   where D_MKT <= <DATE> and N_CHECK_STATE = 1 ) GROUP BY C_SEC_CODE) B");
//		buf.append("  ON A.C_SEC_CODE = B.C_SEC_CODE");
//		buf.append(" where A.C_PORT_CODE = <PORT>");
//		buf.append("   AND A.d_trade = <DATE>");
//		buf.append("   AND A.N_CHECK_STATE = 1', 'CFG', 1, 1206, 'TOTAL_ALL', 0, 'ZQT_AKZQSZ', 'A款证券市值 ', 'N', 'JW_ROUND', 'CFG', '2')");
//		buf.append("");
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_BJDWJZ', 'B级单位净值', '");
//		buf.append(" select round(case when x.N_PORT_MV < nvl(y.N_PORT_MV,0) or nvl(z.N_PORT_MV,0) = 0 then 0");
//		buf.append("    else (x.N_PORT_MV-y.N_PORT_MV)/z.N_PORT_MV end ,5) as C_KM_NAME from");
//		buf.append(" (SELECT C_PORT_CODE,D_ASTSTAT,max(CASE");
//		buf.append("          WHEN A.C_NAV_TYPE = ''TOTAL_ALL'' THEN");
//		buf.append("           A.N_VA_RATE");
//		buf.append("          ELSE");
//		buf.append("           A.N_PORT_MV");
//		buf.append("        END) AS N_PORT_MV");
//		buf.append("   FROM T_R_FR_ASTSTAT A");
//		buf.append("  WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("    AND A.D_ASTSTAT =  <DATE>");
//		buf.append("    AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("    AND A.C_KEY_CODE = ''ZCJZ''");
//		buf.append("    AND INSTR(A.C_KM_CODE, ''_'') = 0");
//		buf.append("    group by C_PORT_CODE,D_ASTSTAT) x");
//		buf.append(" left join");
//		buf.append("(SELECT a.C_PORT_CODE,a.D_STOCK, NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
//		buf.append("  FROM T_D_AI_STOCK A");
//		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B");
//		buf.append("    ON A.C_DAI_CODE = B.C_DAI_CODE");
//		buf.append("  JOIN (select * from T_P_AA_PORT_CLS where N_CHECK_STATE >= 0 AND c_dv_port_cls = ''PORT_A'') C");
//		buf.append("  ON A.C_PORT_CODE = C.C_PORT_CODE AND A.C_PORT_CLS_CODE = C.C_PORT_CLS_CODE");
//		buf.append(" where substr(a.c_year_month, -2, 2) <> ''00''");
//		buf.append("   and A.C_PORT_CODE = <PORT>");
//		buf.append("   AND A.D_STOCK =  <DATE>");
//		buf.append("   AND A.C_PORT_CLS_CODE != '' ''");
//		buf.append("       group by a.C_PORT_CODE,a.D_STOCK) y");
//		buf.append("on x.C_PORT_CODE = y.C_PORT_CODE and x.D_ASTSTAT = y.D_STOCK");
//		builder.addSql(buf);
//		buf.setLength(0);
		
//		buf.append("(SELECT a.C_PORT_CODE,a.D_STOCK,nvl(sum(abs(n_amount)),0) as N_PORT_MV");
//		buf.append("  FROM T_D_AI_STOCK A");
//		buf.append(" JOIN (select * from T_P_AA_PORT_CLS where N_CHECK_STATE >= 0 AND c_dv_port_cls = ''PORT_B'') C");
//		buf.append("  ON A.C_PORT_CODE = C.C_PORT_CODE AND A.C_PORT_CLS_CODE = C.C_PORT_CLS_CODE");
//		buf.append(" where A.C_PORT_CODE = <PORT>");
//		buf.append("   AND A.D_STOCK =  <DATE>");
//		buf.append("     group by a.C_PORT_CODE,a.D_STOCK) z");
//		buf.append(" on x.C_PORT_CODE = z.C_PORT_CODE and x.D_ASTSTAT = z.D_STOCK', 'CFG', 1, 1205, 'TOTAL_ALL', 0, 'ZQT_BJDWJZ', 'B级单位净值', 'N', 'JW_ROUND', 'CFG', '2')");
//		builder.addSql(buf);
//		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_GSCESY', '估算超额收益', 'SELECT");
		buf.append("nvl(SUM(N_GSCESY_MONEY),0) AS  C_KM_NAME");
		buf.append("FROM T_D_AC_SELL_TA_ZQT A");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_TRADE = <DATE>', 'CFG', 1, 1202, 'TOTAL_ALL', 0, 'ZQT_GSCESY', '估算超额收益', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_LJGDSY', '累计固定收益', 'SELECT");
		buf.append("nvl(SUM(N_LJGD_MONEY),0) AS  C_KM_NAME");
		buf.append("FROM T_D_AC_SELL_TA_ZQT A");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_TRADE = <DATE>', 'CFG', 1, 1201, 'TOTAL_ALL', 0, 'ZQT_LJGDSY', '累计固定收益', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_LJJZSZ', '累积基准市值', 'SELECT");
		buf.append("nvl(SUM(N_LJJZSZ_MONEY),0) AS  C_KM_NAME");
		buf.append("FROM T_D_AC_SELL_TA_ZQT A");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_TRADE = <DATE>', 'CFG', 1, 1204, 'TOTAL_ALL', 0, 'ZQT_LJJZSZ', '累积基准市值', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_LJYSXCESY', '累计已实现超额收益', 'SELECT");
		buf.append("nvl(SUM(N_LJCESY_MONEY),0) AS  C_KM_NAME");
		buf.append("FROM T_D_AC_SELL_TA_ZQT A");
		buf.append(" WHERE A.C_PORT_CODE = <PORT>");
		buf.append("   AND A.D_TRADE = <DATE>', 'CFG', 1, 1203, 'TOTAL_ALL', 0, 'ZQT_LJYSXCESY', '累计已实现超额收益', 'N', 'JW_ROUND', 'CFG', '2')");
		builder.addSql(buf);
		buf.setLength(0);
		
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_QYBC', '权益补偿', 'SELECT NVL(SUM(N_LJQY_MONEY),0) AS C_KM_NAME");
//		buf.append("FROM T_D_AC_SELL_TA_ZQT A");
//		buf.append("where A.C_PORT_CODE = <PORT>");
//		buf.append("AND A.d_trade = <DATE>', 'CFG', 1, 1206, 'TOTAL_ALL', 0, 'ZQT_QYBC', '权益补偿', 'N', 'JW_ROUND', 'CFG', '2')");
//		buf.append("");
//		buf.append("insert into T_S_INDEX (C_IDEN, C_INDEX_CODE, C_INDEX_NAME, C_DATA_SOURCE, C_DATA_TYPE, N_STATE, N_ORDER, C_NAV_TYPE, N_DETAIL, C_KEY_CODE, C_KEY_NAME, C_IS_SYS, C_TRU, C_MODE, C_RET)");
//		buf.append("values ( sequ_S_INDEX.Nextval, 'ZQT_WCDBBL', '维持担保比例 ', '");
//		buf.append("select");
//		buf.append(" trim(to_char(ROUND(CASE WHEN nvl(y.N_PORT_MV,0) + nvl(z.N_GSCESY_MONEY,0) + nvl(z.N_LJCESY_MONEY,0) = 0 then 0");
//		buf.append("   else x.N_PORT_MV/(nvl(y.N_PORT_MV,0) + nvl(z.N_GSCESY_MONEY,0) + nvl(z.N_LJCESY_MONEY,0)) *100 end,2),''9999999999999999999999990D99''))||''%'' AS C_KM_NAME from");
//		buf.append(" (SELECT C_PORT_CODE,D_ASTSTAT,max(CASE");
//		buf.append("          WHEN A.C_NAV_TYPE = ''TOTAL_ALL'' THEN");
//		buf.append("           A.N_VA_RATE");
//		buf.append("          ELSE");
//		buf.append("           A.N_PORT_MV");
//		buf.append("        END) AS N_PORT_MV");
//		buf.append("   FROM T_R_FR_ASTSTAT A");
//		buf.append("  WHERE A.C_PORT_CODE = <PORT>");
//		buf.append("    AND A.D_ASTSTAT =  <DATE>");
//		buf.append("    AND A.C_NAV_TYPE = ''TOTAL''");
//		buf.append("    AND A.C_KEY_CODE = ''ZCJZ''");
//		buf.append("    AND INSTR(A.C_KM_CODE, ''_'') = 0");
//		buf.append("    group by C_PORT_CODE,D_ASTSTAT) x");
//		buf.append(" left join");
//		buf.append("(SELECT a.C_PORT_CODE,a.D_STOCK, NVL(SUM(A.N_PORT_MONEY * B.N_FUND_WAY), 0) N_PORT_MV");
//		buf.append("  FROM T_D_AI_STOCK A");
//		buf.append("  JOIN (SELECT C_DAI_CODE, N_FUND_WAY FROM T_S_DAI_ITEM) B");
//		buf.append("    ON A.C_DAI_CODE = B.C_DAI_CODE");
//		buf.append("  JOIN (select * from T_P_AA_PORT_CLS where N_CHECK_STATE >= 0 AND c_dv_port_cls = ''PORT_A'') C");
//		buf.append("  ON A.C_PORT_CODE = C.C_PORT_CODE AND A.C_PORT_CLS_CODE = C.C_PORT_CLS_CODE");
//		buf.append(" where substr(a.c_year_month, -2, 2) <> ''00''");
//		buf.append("   and A.C_PORT_CODE = <PORT>");
//		buf.append("   AND A.D_STOCK = <DATE>");
//		buf.append("   AND A.C_PORT_CLS_CODE != '' ''");
//		buf.append("       group by a.C_PORT_CODE,a.D_STOCK) y");
//		buf.append("on x.C_PORT_CODE = y.C_PORT_CODE and x.D_ASTSTAT = y.D_STOCK");
//		buf.append("left join");
//		buf.append("( select c_port_code,d_trade,");
//		buf.append(" SUM(N_GSCESY_MONEY) AS N_GSCESY_MONEY,SUM(N_LJCESY_MONEY) AS N_LJCESY_MONEY");
//		buf.append("  from T_D_AC_SELL_TA_ZQT t");
//		buf.append(" where t.c_port_code = <PORT>");
//		buf.append("   and t.d_trade = <DATE>");
//		buf.append("   and t.n_check_state = 1");
//		buf.append("GROUP BY  c_port_code,d_trade) z");
//		buf.append(" on x.C_PORT_CODE = z.C_PORT_CODE and x.D_ASTSTAT = z.d_trade ', 'CFG', 1, 1205, 'TOTAL_ALL', 0, 'ZQT_WCDBBL', '维持担保比例 ', 'N', 'JW_ROUND', 'CFG', '2')");
//		builder.addSql(buf);
//		buf.setLength(0);
		
		builder.endScript();
	}
	
	/**
	 * 交易市场字典
	 * @param builder
	 */
	private void buildJYSCZD(ScriptBuilder builder){
		//T_S_MKT_VAR
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('FDER', '境外衍生品交易市场', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XVES', '明讯银行', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('EUCL', '欧洲清算所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('FROT', '法国场外交易市场', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('IFCA', '加拿大温尼伯商品交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('NASDAQ', '纳斯达克', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('SHCH', '上海清算所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XAMM', '阿曼证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XBOM', '孟买证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XETR', '德国证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XIME', '台湾交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('CCFX', '中国金融期货交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('COTC', '中国柜台交易市场', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('FTM', '场内交易市场', '[root]', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('HKCG', '港股通联合交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('NEEQ', '全国中小企业股转转让市场', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('OTC', '场外交易市场', '[root]', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('SGEX', '上海黄金交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XASX', '澳大利亚证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XCFE', '中国外汇交易中心', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XDCE', '大连商品交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XHKG', '香港联合交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XNZE', '新西兰证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XSES', '新加坡证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XSGE', '上海期货交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XSHE', '深圳证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XSHG', '上海证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XZCE', '郑州商品交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XNAS', '纳斯达克交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XNYS', '纽约证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('AMEX', '美国证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XPAR', '泛欧证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('DEOT', '德国场外交易市场', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('EOTC', '香港场外交易所', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('SOTC', '新加坡场外交易所', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('SWX', '瑞士证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XKLS', '马来西亚证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XKRX', '韩国证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XSWX', '瑞士证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XTAI', '台湾证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XTSE', '多伦多证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('HKCS', '深港通联合市场', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('OOTC', '美国其它场外交易市场', 'OTC', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XLON', '英国伦敦证券交易所', 'FTM', 'ENAB')")
				.addSql("insert into T_S_MKT_VAR (C_MKT_CODE, C_MKT_NAME, C_DV_MKT_TYPE, C_DV_STATE)values ('XTKS', '东京证券交易所', 'FTM', 'ENAB')")
				.endScript();
	}
	
	/**
	 * 收支结转字典
	 * @param builder
	 */
	private void buildeSZJZZD(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('QTFY', '其他费用项', null, 'YFK_QTK', null, 'QTFY', 14, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('YTDT', '预提待摊项', 'DTFY', 'YTFY', 'QTFY', 'QTFY', 2, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('YYSF_ZQ', '证券税费项', null, 'YFK_SF_ZQ_YSX', null, 'YYSF_ZQ_YSX', 5, 1, 'SZXMFL_ZQX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('YYSF_ZJ', '资金税费项', null, 'YFK_SF_ZJ', null, 'YYSF_ZJ', 6, 1, 'SZXMFL_ZJX', 'SZLX_FK')")
//				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('ZJLX', '资金利息项', 'YSLX_ZJ', 'YFLX_ZJ', 'LXSR_ZJ', 'LXZC_ZJ', 8, 0, 'SZXMFL_ZJX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('ZQLX', '证券利息项', 'YSLX_ZQ', 'YFLX_ZQ', 'LXSR_ZQ', 'LXZC_ZQ', 9, 0, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('YYSZ', '运营收支项', null, 'YFK_YYF', null, 'YYFY', 1, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('ZQHL', '证券红利项', 'YSK_GL', null, 'TZSY_HLSR', null, 7, 0, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('XSLX', '销售利息项', 'YSLX_TA', null, 'LXSR_TA', null, 10, 0, 'SZXMFL_XSX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('HDSY', '汇兑损益项', null, null, null, 'HDSY', 13, 0, 'SZXMFL_ZJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('JZZB', '减值准备项', null, 'JZZB', null, 'ZCJZSS', 15, 0, 'SZXMFL_ZJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('SYJZ', '收益结转项', null, 'LRFP_YFLR', null, 'LRFP_WFP_YSX', 17, 0, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('RZLX', '融资利息项', ' ', 'YFLX_RZ', ' ', 'LXZC_RZ', 18, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('RZBJ', '融资本金项', ' ', 'HBFZ_RZ', ' ', 'LXZC_RZ', 19, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('RQLX', '融券利息项', ' ', 'YFLX_RQ', ' ', 'LXZC_RQ', 20, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('QSKX', '清算款收支项', 'ZQQSK', 'ZQQSK', 'QTSR', 'QTFY', 23, 1, 'SZXMFL_QSKX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('JJYF_FY', '交易费用项', null, 'YFK_FY_JY', null, 'JYFY', 3, 1, 'SZXMFL_ZQX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('JJYF_YJ', '交易佣金项', null, 'YFK_FY_YJ', null, 'QTFY', 4, 1, 'SZXMFL_YJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('QTSZ', '其他收支项', 'YSK_QTK', 'YFK_QTK', 'QTSR', 'QTFY', 16, 1, 'SZXMFL_SZX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('XSSZ_F', '销售费用项', null, 'YFK_SHF', null, 'QTFY', 11, 1, 'SZXMFL_XSX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('XSSZ_K', '销售收入项', 'YSK_QTK', null, 'QTSR', null, 12, 1, 'SZXMFL_SZX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('ZRQLX', '转融券利息项', 'YSLX_ZRQ', 'YFLX_ZRQ', 'QTFY', 'QTFY', 22, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('ZRZLX', '转融资利息项', 'YSLX_ZRZ', 'YFLX_ZRZ', 'QTFY', 'QTFY', 21, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM (C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE) values ('FHJS', '分红结算项', null, 'YFLR', null, 'QTFY', 24, 1, 'SZXMFL_XSX', 'SZLX_FK')")
				//add by gongyue 20170503
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('BXLX', '保险利息项', 'YSK_GL', NULL, 'TZSY_CJSR', NULL, 7, 0, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('GYBD', '估值增值', 'YSGJ_GYBD', NULL, 'GYBD', NULL, 25, 1, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('HDSY', '汇兑损益项', NULL, NULL, NULL, 'HDSY', 13, 0, 'SZXMFL_ZJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('JJYF_FY', '交易费用项', NULL, 'YFK_FY_JY', NULL, 'JYFY', 3, 1, 'SZXMFL_ZQX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('JJYF_YJ', '交易佣金项', NULL, 'YFK_FY_YJ', NULL, 'JYFY', 4, 1, 'SZXMFL_YJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('JZZB', '减值准备项', NULL, 'JZZB', NULL, 'ZCJZSS', 15, 0, 'SZXMFL_ZJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('QSKX', '清算款收支项', 'ZQQSK', 'ZQQSK', 'QTSR', 'QTFY', 23, 1, 'SZXMFL_QSKX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('QTFY', '其他费用项', NULL, 'YFK_QTK', NULL, 'QTFY', 14, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('QTSZ', '其他收支项', 'YSK_QTK', 'YFK_QTK', 'QTSR', 'QTFY', 16, 1, 'SZXMFL_SZX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('RQLX', '融券利息项', ' ', 'YFLX_RQ', ' ', 'LXZC_RQ', 20, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('RZBJ', '融资本金项', ' ', 'HBFZ_RZ', ' ', 'LXZC_RZ', 19, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('RZLX', '融资利息项', ' ', 'YFLX_RZ', ' ', 'LXZC_RZ', 18, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('SYJZ', '收益结转项', NULL, 'LRFP_YFLR', NULL, 'LRFP_WFP_YSX', 17, 0, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('XSLX', '销售利息项', 'YSLX_TA', NULL, 'LXSR_TA', NULL, 10, 0, 'SZXMFL_XSX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('XSSZ_F', '销售费用项', NULL, 'YFK_SHF', NULL, 'QTFY', 11, 1, 'SZXMFL_XSX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('XSSZ_K', '销售收入项', 'YSK_QTK', NULL, 'QTSR', NULL, 12, 1, 'SZXMFL_SZX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('YTDT', '预提待摊项', 'DTFY', 'YTFY', 'QTFY', 'QTFY', 2, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('YTZDT', '预提转待摊', NULL, 'YTFY', NULL, 'DTFY', 2, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('YYSF_ZJ', '资金税费项', NULL, 'YFK_SF_ZJ', NULL, 'YYSF_ZJ', 6, 1, 'SZXMFL_ZJX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('YYSF_ZQ', '证券税费项', NULL, 'YFK_SF_ZQ_YSX', NULL, 'YYSF_ZQ_YSX', 5, 1, 'SZXMFL_ZQX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('YYSZ', '运营收支项', NULL, 'YFK_YYF', NULL, 'YYFY', 1, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZFQTFY', '支付其他费用项', NULL, 'YFK_QTK', NULL, 'QTFY', 14, 1, 'SZXMFL_SZX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZJLX', '资金利息项', 'YSLX_ZJ', 'YFLX_ZJ', 'LXSR_ZJ', 'LXZC_ZJ', 8, 1, 'SZXMFL_ZJX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZQHL', '证券红利项', 'YSK_GL', NULL, 'TZSY_HLSR', NULL, 7, 0, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZQLX', '证券利息项', 'YSLX_ZQ', 'YFLX_ZQ', 'LXSR_ZQ', 'LXZC_ZQ', 9, 0, 'SZXMFL_ZQX', 'SZLX_SK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZRQLX', '转融券利息项', 'YSLX_ZRQ', 'YFLX_ZRQ', 'QTFY', 'QTFY', 22, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.addSql("insert into T_S_IE_ITEM(C_IE_CODE, C_IE_NAME, C_DAI_CODE_REC, C_DAI_CODE_COP, C_DAI_CODE_INC, C_DAI_CODE_FEE, N_ORDER, N_STATE, C_IE_TYPE, C_SZ_TYPE)VALUES('ZRZLX', '转融资利息项', 'YSLX_ZRZ', 'YFLX_ZRZ', 'QTFY', 'QTFY', 21, 1, 'SZXMFL_QTX', 'SZLX_FK')")
				.endScript();
	}
	
	/**
	 * 财务项目字典
	 * @param builder
	 */
	private void buildCWXMZD(ScriptBuilder builder){
		//T_S_FIN_ITEM
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('AMOUTM', '数量金额', 'CHKRED', 2)")
//				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('AUDIT', '审核', 'VCHAUDIT', 1)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('BDEBIT', '借贷平衡', 'CHKVCH', 1)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('CHECKKMISNULL', '检查科目代码为空', 'CHKRED', 3)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('CHKNUM', '整理凭证号', 'VCHCHECK', 1)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('CHKRED', '分录级检查', 'VCHCHECK', 3)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('CHKVCH', '凭证级检查', 'VCHCHECK', 2)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('CURRENCY', '核算币种', 'CHKRED', 3)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('DDURING', '期间日期', 'CHKVCH', 2)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('REDAMOUT', '分录数量', 'CHKVCH', 3)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('REDSUB', '分录科目', 'CHKRED', 1)")
//				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('UNAUDIT', '反审核', 'VCHAUDIT', 2)")
//				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('VCHAUDIT', '凭证审核', '[root]', 2)")
				.addSql("insert into T_S_FIN_ITEM (C_FIN_CODE, C_FIN_NAME, C_FIN_CODE_P, N_ORDER)values ('VCHCHECK', '凭证检查', '[root]', 1)")
				.endScript();
	}
	
	/**
	 * 表达式
	 * @param builder
	 */
	private void buildBDS(ScriptBuilder builder){
		//T_S_DE_EXP 表达式
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('COMM_YJ', '纯佣金', 'EXP_COMM', ' ')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('COMM_YJ_FEE', '佣金-经手费-征管费-结算费', 'EXP_COMM', '''ZQL_JSOF'',''ZQL_ZGF'',''ZQL_JSUF''')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('COMM_YJ_FEE_ALL', '佣金-一级费用', 'EXP_COMM', '''ZQL_JSOF'',''ZQL_ZGF'',''ZQL_JSUF'',''ZQL_GHF''')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_30_360', '30/360', 'EXP_BOND', '(360*($DATEYEAR($DATE(accrualDate))-$DATEYEAR($DATE(curPeriodBeginDate)))+30*($DATEMONTH($DATE(accrualDate))-$DATEMONTH($DATE(curPeriodBeginDate)))+($DATEDAY($DATE(accrualDate))-$DATEDAY($DATE(curPeriodBeginDate))+1))/360.0*couponRate/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_30_360', '30/360', 'EXP_CASH', '( $DATEDIFF_30_360($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/360.0*couponRate*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_360', 'A/360', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/360.0*couponRate/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_360', 'A/360', 'EXP_CASH', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/360.0*couponRate*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_365', 'A/365', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/365.0*couponRate/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_365', 'A/365', 'EXP_CASH', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/365.0*couponRate*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_365F', 'A/365F', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/365.0*couponRate/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_365F', 'A/365F', 'EXP_CASH', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/365.0*couponRate*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_A', 'A/A', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/(365.0 + $LEAPYEARS($DATE(curPeriodBeginDate),$DATE(curPeriodEndDate)))*couponRate/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_A', 'A/A', 'EXP_CASH', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/(365.0 + $LEAPYEARS($DATE(curPeriodBeginDate),$DATE(curPeriodEndDate)))*couponRate*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_A_BOND', 'A/A-Bond', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/($DATEDIFF($DATE(curPeriodBeginDate),$DATE(curPeriodEndDate)) + 1)*couponRate/payFrequency/100.0*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('FI_A_A_BOND', 'A/A-Bond', 'EXP_CASH', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/($DATEDIFF($DATE(curPeriodBeginDate),$DATE(curPeriodEndDate)) + 1)*couponRate/payFrequency*remainMoney')")
				.addSql("insert into T_S_DE_EXP (C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE) values ('COMM_YJ_FEE_JSOF', '佣金-经手费', 'EXP_COMM', '''ZQL_JSOF''')")
				//add by gongyue 20170503
				.addSql("insert into T_S_DE_EXP(C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE)VALUES('COMM_YJ_FEE0', '佣金-经手费-征管费', 'EXP_COMM', '''ZQL_JSOF'',''ZQL_ZGF''')")
				.addSql("insert into T_S_DE_EXP(C_EXP_CODE, C_EXP_NAME, C_DV_EXP_TYPE, C_VALUE)VALUES('FI_A_366', 'A/366', 'EXP_BOND', '( $DATEDIFF($DATE(adjustDate),$DATE(accrualDate))+ 1.0)/366.0*couponRate/100.0*remainMoney')")
				.endScript();
	}
	
	/**
	 * 产品估值参数
	 * @param builder
	 */
	private void buildCPGZCS(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZDFWF_MIN', '中登服务费最低限额', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '1000', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZDFWF_MAX', '中登服务费最高限额', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '10000', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_FXZBJ_JTBL', '风险准备金计提比例', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '0.2', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_FXZBJ_JTJS', '风险准备金计提基数', 'SV', 'CPCS', 'VOC', 'FXZBJ_MARK', 'CPCS', '产品参数', 'YJBC', null, '产品参数', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_TAJZDCRQ', '产品TA净值导出日期为T-N日', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '1', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_FXZBJ_SXBL', '风险准备金上限比例', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '0.005', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_YJJZ', '业绩比较标准', 'SV', 'CPCS', ' ', null, 'CPCS', '产品参数', '0.03', null, '产品参数', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_FXZBJ_SXZB', '风险准备金上限指标', 'SV', 'CPCS', 'VOC', 'FXZBJ_MARK', 'CPCS', '产品参数', 'FXJ_SSZB', null, '产品参数', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_EXPORT_001', 'TA净值导出资产净值是否等于实收资本', 'SV', 'DC', 'VOC', 'BOOL_TYPE', 'DC', '导出类', '0', 'TA净值导出资产净值是否等于实收资本', '导出类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_DWJZ_001', '单位净值保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_DWJZ_002', '单位净值截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_JZRZZ_001', '净值日增长率保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_JZRZZ_002', '净值日增长率截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_JZZZ_001', '净值增长率保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_JZZZ_002', '净值增长率截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_DWSY_001', '单位可分配收益保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_DWSY_002', '单位可分配收益截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_WFSY_001', '每万份收益保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'CLS_HB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_WFSY_002', '每万份收益截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'CLS_HB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_QRSY_001', '七日收益率保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'CLS_HB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_QRSY_002', '七日收益率截位方式', 'SV', 'JD', 'VOC', 'JW_TYPE', 'WS', '保留位数', 'JW_ROUND', null, '精度类', 'CLS_HB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_AZJSJGBLWS', 'A值计算结果保留位数', 'SV', 'JD', 'VOC', null, 'JD', '精度类', '0', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_GZZS', '跟踪指数', 'SV', 'JD', 'VOC', 'GP', 'WS', '指数', '000300 SZ', null, '精度类', 'PUB', 'sec')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_ZJGC_BLWS', '中间过程保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '4', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_ZSJZZ', '指数基准值', 'SV', 'JD', ' ', null, 'WS', '保留位数', '1000', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_DWJZ_MAX', '单位净值最大值', 'SV', 'JD', ' ', null, 'WS', '精度类', '2', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_DWJZ_MIN', '单位净值最小值', 'SV', 'JD', ' ', null, 'WS', '精度类', '0.35', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_GGBS', '杠杆倍数', 'SV', 'JD', ' ', null, 'WS', '精度类', '3', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_SSZB', '实收资本数量保留小数位数', 'SV', 'JD', ' ', null, 'WS', '保留小数位数', '2', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_JD_DWCB', '单位成本保留位数', 'SV', 'JD', ' ', null, 'WS', '保留位数', '2', null, '精度类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_FEPB_JK', '优先级风险级份额配比监控', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0', '当所有优先级资产净值/所有风险级资产净值大于参数值时进行提醒，参数值为0时不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_KYTC', '今日可用头寸预警线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0', '今日可用头寸小于或等于参数值时提醒，只对组合自定义参数做检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_DWJZ_CPDQ', '产品到期前N日提醒', 'SV', 'TX', ' ', null, 'TX', '提醒类', '5', '业务日期在产品到期日期至产品到期日期-N日之间时提醒。产品到期日取产品基本信息中的到期日期，如果是分级组合的子级别取分级产品参数中的到期日期', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_DWJZ_PCX', '单位净值平仓线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0.85', '单位净值小于或等于参数值时提醒，参数为0不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_DWJZ_SZX', '单位净值止损线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0.9', '单位净值小于或等于参数值时提醒，参数为0不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_YYFYE_FB', '非标品种对应应付运营费余额检查', 'SV', 'TX', 'VOC', 'BOOL_TYPE', 'TX', '提醒类', '0', '检查运营费用科目余额是否存在异常', '提醒类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_DWJZ_YJXX', '单位净值预警线下线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0.85', '单位净值小于或等于参数值时提醒，参数为0不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_JZZZL_YJX', '单位净值增长率绝对值预警线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0', '单位净值增长率的绝对值大于或等于参数值时提醒，参数为0不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_PZSL_SSZB', '按实收资本计费的凭证数量提醒', 'SV', 'TX', 'VOC', 'TX_PZSL_SSZB', 'TX', '提醒类', 'TX_PZSL_SSZB_F', '检查当日是否正常核算‘运营收支项’', '提醒类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_YEYC', '库存余额异常提醒', 'SV', 'TX', 'VOC', 'TX_YEYC', 'TX', '提醒类', 'TX_YEYC_F', '检查资产和负债类科目余额是否存在异常', '提醒类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_TX_DWJZ_YJSX', '单位净值预警线上线', 'SV', 'TX', ' ', null, 'TX', '提醒类', '0.9', '单位净值大于或等于参数值时提醒，参数为0不检查', '提醒类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_NAV3', '指标参数NAV3', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数NAV3', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_R1', '指标参数R1', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数R1', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_R2', '指标参数R2', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数R2', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_WTRGTBL', '委托人指定代表跟投比例', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '委托人指定代表跟投比例', '指标项', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_LJDWJZJZZ', '累计单位净值基准值', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '1', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE6', '超额收益率比率6', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_FQJZ_002', '费前分级资产净值公式', 'SV', 'ZB', 'VOC', 'SF_FJCS', 'ZB', '指标类', ' ', null, '指标类', 'PUB', 'AdvancedAlgorithm')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_FQJZ_001', '费前资产净值公式', 'SV', 'ZB', 'VOC', 'SF_FJCS', 'ZB', '指标类', ' ', null, '指标类', 'PUB', 'AdvancedAlgorithm')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK', '证券清算款统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_ZC/FZ', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_KFPSY', '可分配收益小于零归零', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '1', '可分配收益', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_GGBJJZ', '杠杆比例比较基准值', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '25', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JHDWMZ', '计划单位面值', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '1', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZRDWJZ', '昨日单位净值取值方式', 'SV', 'ZB', 'VOC', 'ZB_DATE_TYPE', 'ZB', '指标类', 'ZRDWJZ_NATURAL', '昨日单位净值取值方式', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_FJZBSFHQSR', '分级指标算法中T计算时含起始日', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_YXSYRBJ', '优先受益人本金', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_YXSYQJDSYHJ', '优先受益区间段收益合计', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JFQJDFYHJ', '计费区间段费用合计', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZGDFL', '修正单位净值-总固定费率', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_BGABBFBBJ', 'B级给A级保百分比本金', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_WFZJZC', '无负债结算的衍生工具项目清算款默认进资产类', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '无负债结算的衍生工具项目清算款默认进资产类', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE1', '超额收益率比率1', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE2', '超额收益率比率2', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE3', '超额收益率比率3', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE4', '超额收益率比率4', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_CESYL_RATE5', '超额收益率比率5', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZFDFL', '修正单位净值-总浮动费率', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_MJZCLTS', '募集至成立日天数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_SYPZJ_YSX', '损益平准金_已实现余额', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '1', '实现收益是或否包含损益平准金_已实现核算项目余额', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_LRFP_WFP_YSX', '利润分配_未分配利润_已实现余额', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '实现收益是或否包含利润分配_未分配利润_已实现核算项目余额', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_SJTS', '实际天数', 'SV', 'ZB', 'VOC', 'ZB_SJTS', 'ZB', '指标类', 'SJTS_NSJTS', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JZSYL', '优先类每年基准收益率', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JZZJGC', '资产净值中间过程是否保留小数位数', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JZZJWS', '资产净值中间过程保留小数位数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '4', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JZDWJZWS', '计算资产净值时单位净值保留小数位数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '4', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_GGBLWS', '杠杆比例保留位数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '4', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_GGJWFS', '杠杆比例保留位数截位方式', 'SV', 'ZB', 'VOC', 'JW_TYPE', 'ZB', '指标类', 'JW_ROUND', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZJBLWS', '中间变量保留小数位数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '4', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZSHQ', '指数行情代码', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', ' ', null, '指标类', 'PUB', 'indexinfo')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_BJJZR', '比较基准日', 'SV', 'ZB', 'VOC', 'ZB_RQLX', 'ZB', '指标类', ' ', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE1', '分级指标比率值一', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE2', '分级指标比率值二', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE3', '分级指标比率值三', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_ZJJSBLWS', '中间计算过程保留小数位数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '4', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_FJJSTSTJ', '分级计算特殊判断条件', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', null, '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_FJJSTSZ', '分级计算特殊值', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_KFPSYJF', '可分配收益减负的未实现收益', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '可分配收益是否减负的未实现收益', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE4', '分级指标比率值4', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE5', '分级指标比率值5', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE6', '分级指标比率值6', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE7', '分级指标比率值7', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_RATE8', '分级指标比率值8', 'SV', 'ZB', 'VOC', null, 'ZB', '指标类', '0', null, '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_KTTD', '证券清算款_可退替代统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_可退替代统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_KTTDGZ', '证券清算款_可退替代估增统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_可退替代估增统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_XJCE', '证券清算款_现金差额统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_现金差额统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_XJTD', '证券清算款_现金替代统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_现金替代统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_XJTD_KS', '证券清算款_现金替代_跨市统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_现金替代_跨市统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_YGXJCE', '证券清算款_预估现金差额统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_预估现金差额统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_YGXJTD', '证券清算款_预估现金替代统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_预估现金替代统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_YSTB', '证券清算款_可收退补款统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_可收退补款统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_QSK_YTTB', '证券清算款_应退退补款统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_QSK', '证券清算款_应退退补款统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_CYDQ_SZL', '持有到期类资产市值列是否等于行情*数量', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '持有到期类资产市值列是否等于行情*数量', '指标项', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_SSZBMZ', '实收资本面值', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '1', '实收资本面值，控制估值表中实收资本数量用库存余额除以该值', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_DQCKLL', '定期存款利率', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '定期存款利率', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_DWJZLXJYTS', '基金单位净值连续交易天数', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '10', '基金单位净值连续交易天数', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_JZDJJB', '净值倒减级别', 'SV', 'ZB', ' ', null, 'ZB', '指标类', ' ', '净值倒减级别', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_M', '指标参数M', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数M', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_M1', '指标参数M1', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数M1', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_M2', '指标参数M2', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数M2', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_NAV1', '指标参数NAV1', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数NAV1', '指标类', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('SV_ZB_NAV2', '指标参数NAV2', 'SV', 'ZB', ' ', null, 'ZB', '指标类', '0', '指标参数NAV2', '指标类', 'PUB', null)")
				//add by gongyue 20170503
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_JJ_LCSY_YSGL', '保险理财品种的收益进应收股利', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_HS', '核算项', '1', '保险理财品种的收益进应收股利', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_JTSF_001', '增值税是否明细计提', 'AO', 'JTSF', 'VOC', 'BOOL_TYPE', 'JTSF', '计提税费', '1', '计提增值税是否每张券/现金账户明细计提；否表示汇总计提，使用营业税费_销项税和应付税费_销项税等销项税类核算项目', '计提税费', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_JTSF_002', '增值税是否自动计提', 'AO', 'JTSF', 'VOC', 'BOOL_TYPE', 'JTSF', '计提税费', '1', '增值税计提凭证是否自动计提；否表示不会自动在明细表中插入流水，只有手工流水才会生成凭证。', '计提税费', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_QH_HEJZBLWE', '合约结转比例保留位数', 'AO', 'AO_QH', ' ', NULL, 'CB_CAL', '成本类', '0', '合约平仓时结转比例保留位数，0为无限位', '期货', 'PUB', NULL)")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_QT_CBJZ_003', '卖出证券成本计算方法', 'AO', 'AO_QT', 'VOC', 'CBJZ_JSFF', 'CB_CAL', '成本类', 'CBJZ_JSFF_DWCB', '证券类产品卖出成本是等于持仓金额/持仓数量*卖出数量还是卖出数量/持仓数量*持仓金额', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_QT_SZLT_PZKFYHZFL', '上市流通凭证是否可出现红字分录', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算项', '1', '上市流通凭证是否可出现红字分录', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_QT_WSHLSJC', '核算时是否检查存在未审核业务流水 ', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算类', '0', '参数开启时，如果存在未审核的业务流水时进行报错提醒，否则不提醒', '其他', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_QZXQ_MRCBSF', '权证行权买入标的成本算法', 'AO', 'QZ', 'VOC', 'MRCBSF', 'QZ', '权证业务', 'MRCBSF_BDWSJ', '权证行权买入的标的证券按市价还是行权价入成本', '权证', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TAJS_007', '合并核算损益平准金方式', 'AO', 'XSJS', 'VOC', 'AO_TAJS_007', 'XSJS', '销售计算', 'hzhs', '不区分模式，损益平准金按明细分笔计算还是用同类流水合计金额计算', '销售计算', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TAJS_008', '非权益类分录汇率基准日', 'AO', 'XSJS', 'VOC', 'AO_TAJS_006', 'XSJS', '销售计算', 'AO_TAJS_006_002', '控制QD模式下的非权益类科目计算过程中使用到的汇率取哪天。申购业务（应收申购款、应付申购款）赎回业务（应付赎回款、应付赎回费、应付运营费、其它收入）业绩报酬（应付运营费；分红转投中的应付利润）。', '销售计算', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TAJS_009', '权益类分录币种是否取组合本位币', 'AO', 'XSJS', 'VOC', 'BOOL_TYPE', 'XSJS', '销售计算', '0', '控制QD模式下的权益类分录币种是否取组合本位币，为否表示取销售币种。', '销售计算', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TA_XS_ETFBPPZ', '补票凭证是否在ETF销售凭证之前', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '1', '补票凭证是否在ETF销售凭证之前', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TA_XS_ETFJSYFTBK', 'ETF台账计算应付替代款过程中是否保留2位小数', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '1', 'ETF台账计算应付替代款过程中是否保留2位小数', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_TA_XS_ETFXJTDTZ', 'ETF台账现金替代金额是否根据过户库调整', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '1', 'ETF台账现金替代金额是否根据过户库调整', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_XG_SSLT_001', '上市流通业务损益类科目是否转换', 'AO', 'ZQZH', 'VOC', 'BOOL_TYPE', 'ZQZH', '证券转换', '1', '上市流通业务中转出证券的损益类科目余额是否也要转到转入证券中，为否表示只转资产类科目余额。', '证券转换', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZJCF_MXHS', '存款投资业务按流水明细核算', 'AO', 'AQ_ZJCF', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算类', '0', '资金存放业务进行核算时是否核算到明细流水', '资金存放', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZJZJ_001', '资金追加提取业务实收资本是否核算数量', 'AO', 'ZJZJ', 'VOC', 'BOOL_TYPE', 'ZJZJ', '资金追加提取', '1', '资金追加提取业务实收资本是否核算数量', '资金追加提取', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZQZH_ZTG_001', '转托管业务损益类科目是否转换', 'AO', 'ZQZH', 'VOC', 'BOOL_TYPE', 'ZQZH', '证券转换', '1', '转托管业务中转出证券的损益类科目余额是否也要转到转入证券中，为否表示只转资产类科目余额。', '证券转换', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZQ_HS_DFSJ', '债券到期兑付利息税差进', 'AO', 'ZQ', 'VOC', 'ZQ_DFLXCE', 'ZQ_HS', '核算项', 'ZQ_DFLXCE_CJSR_ZQ', '债券到期兑付利息税差进', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZQ_HS_ZFBGZ', '基金拆分当天不核算估增', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_HS', '核算项', '0', '基金拆分业务当天是否生成估值增值', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZQ_HS_ZQGZ', '多次还本付息债是否按比例冲估增', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_HS', '核算项', '1', '多次还本付息债还本付息凭证是否按比例冲估增', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('AO_ZQ_TYF_WLHB', '实际利率计算时是否考虑未来的还本', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_TYF', '摊余成本法', '0', '交行实际利率算法是否考虑未来还本', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_APIHG_JYZQ', '银行间买断式回购交易证券规则', 'CO', 'PUB', 'VOC', 'APIHG_SEC_RULE', ' ', ' ', 'APIHG_CODE', '银行间买断式回购交易证券按上市代码匹配还是按照计息天数匹配', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_GGQQ_BZJ', '开启上交所股票期权组合保证金', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', ' ', '接口', '0', ' ', '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_HS_TAHBJJFTSJ', '是否读入恒生TA货币基金分投数据', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'TA', '接口', '1', '是否通过BONUS读取恒生TA货币基金分投数据', '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_M2_CG_JYFGHMS_SGT', '深交所深港通是否交易费合计计算且成本轧差（工行模式）', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'HK', '深港通交易清算明细文件', '0', '深港通业务交易费用进行合计后再乘以汇率，并且成本项本位币由轧差所得', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_M2_CG_YJ_SGT', '深交所深港通佣金明细汇总计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '深港通', 'FEE_CALC_MX', '深交所深港通佣金明细汇总计算方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_QFGSPTHSCFPXLSJD', '区分固收平台核算时拆分派息流水占比精度位数', 'CO', 'PUB', ' ', NULL, 'GGC', '公共参数', '8', '8', '公共', 'PUB', NULL)")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_QHSBF_HSX', '期货申报费核算项', 'CO', 'PORT', 'VOC', 'QHSBF_HSX', 'GGC', '公共参数', 'JJYF_FY', '控制清算期货申报费进收支结转业务时的收支项目', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_SJSGS_QFHS', '上交所固定收益平台区分核算', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '0', '交易业务清算出交易属性，核算时科目区分出交易属性为‘固收’', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_SJSKSETFSS_SJDQFS', '深交所跨市场ETF申赎数据读取方式', 'CO', 'FACE', 'VOC', 'READ_TYPE_SJSKS_ETF', 'ETF', 'ETF申赎', 'RT_SJSKSETF_DLRHB', '深交所跨市场ETF申赎数据读取方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_ZQHB_INFO_JYS', '交易所债券还本信息处理接口', 'CO', 'PUB', 'VOC', 'CO_ZX_INFO_ZQHB', 'GGC', '公共参数', 'ZQHB_FROM_JYSSJ', '1.资讯数据 2.交易所数据', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_ZQZYHGZQDMQSGZ', '上海质押式协议回购证券代码取数规则', 'CO', 'PUB', 'VOC', 'HG_HGDMQSGZ', 'GGC', '公共参数', 'HG_HGDMQSGZ_1', '控制上海股票/债券质押协议回购证券代码取数规则', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('CO_ZTG_ZQ', '证券转托管业务类型是否清算', 'CO', 'FACE', 'VOC', 'BOOL_TYPE', 'TA', 'TA', '0', '证券转托管业务类型是否清算', '接口', 'PUB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('HB_SZ_BNLJWFSYLJSFS', '本年累计万份收益率计算方式', 'HB', 'ZB', 'VOC', 'HB_FZJZ', 'ZB', '指标', 'HB_MONTH', '本年累计万份收益率计算方式', '指标类', 'CLS_HB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('LJ_YS_002', 'ETF联接基金更新台账方式', 'AO', 'LJ', 'VOC', 'ETFLJ_GXTZ', 'LJ', '联接基金', 'ETFLJ_GXTZ_ETFTZ', 'ETF联接基金更新补票台账表方式', '联接基金', 'CLS_ETF', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('LJ_YS_003', 'ETF联接基金更新退补数据方式', 'AO', 'LJ', 'VOC', 'ETFLJ_GXTB', 'LJ', '联接基金', 'ETFLJ_GXTB_ETFTBSJ', 'ETF联接基金更新退补数据方式', '联接基金', 'CLS_ETF', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('LJ_YS_004', 'ETF联接基金更新净值方式', 'AO', 'LJ', 'VOC', 'ETFLJ_GXJZ', 'LJ', '联接基金', 'ETFLJ_GXJZ_ETFJJ', 'ETF联接基金更新净值方式', '联接基金', 'CLS_ETF', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('LJ_YS_005', 'ETF退补款退款和补款分别结转', 'AO', 'LJ', 'VOC', 'BOOL_TYPE', 'LJ', '联接基金', '0', 'ETF退补款退款和补款分别结转', '联接基金', 'CLS_ETF', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_BB_ELCE_GZ_AMOUNT', '估值表非明细科目是否生成数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'GGC', '报表类', '0', '电子对账估值表非明细科目是否生成数量', '报表类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_BB_ZQDMZH', '证券代码转换规则', 'SV', 'BB', 'VOC', 'ZHGZ_TYPE', 'GGC', '报表类', 'TRAN_BZH', '估值表、科目表、余额表中证券代码转换为披露代码的规则', '报表类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_FHZSSF', '复合指数算法配置', 'SV', 'ZB', 'VOC', 'FHZSSF', 'ZB', '指标类', 'ZSSF_NFQQFHSF', '复合指数算法配置', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_FJFESK', '分级份额赎空时估值指标取', 'SV', 'ZB', 'VOC', NULL, 'ZB', '指标类', '系统默认算法', '净值占比类分级产品，某级别赎空时单位净值、累计单位净值取设定的级别', '指标类', 'PUB', 'productgrade')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_KFPSY_SYJWSX', '可分配收益是否统计损益平准金_未实现', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '1', '可分配收益是否统计损益平准金_未实现', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_QSK_YSTBK', '证券清算款_应收退补款统计', 'SV', 'ZB', 'VOC', 'QSK_INDEX', 'ZB', '指标类', 'QSK_ZC/FZ', '证券清算款_应收退补款统计进入不同的科目', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_SBZCGZZB', '社保资产模式合计项估值指标', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '社保保障基金是否启用社保模型的合计项估值指标', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_SSZBJEZB', '是否展示实收资本金额指标项', 'SV', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标类', '0', '实收资本金额指标', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA(C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)VALUES('SV_ZB_YJBJJZLX', '业绩比较基准类型', 'SV', 'ZB', 'VOC', 'YJBJJZLX', 'ZB', '指标类', 'JZLX_ZS', '业绩比较基准类型', '指标类', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_PX_FH_JSRQCL', '核算生成的股票和债券对价派息业务的结算日期处理', 'AO', 'AO_PX', 'VOC', 'JSRQCL', 'PX_HS', '核算项', 'JSRQCL_GJDJPXXX', '核算生成的股票和债券对价派息业务的结算日期处理', '分红派息', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_PX_FH_BLWS', '自动生成股票派息业务金额保留位数方式', 'AO', 'AO_PX', 'VOC', 'JW_TYPE', 'PX_HS', '核算项', 'JW_ROUND', '自动生成股票派息业务金额保留位数方式', '分红派息', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_QT_SZHQ_002', '汇率行情保留位数', 'AO', 'AO_QT', ' ', ' ', 'SZ_CAL', '市值类', '15', '交易币种*中间币种*组合币种结果保留位数', '其它', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_CBJZ_002', '成本结转方法', 'AO', 'AO_QT', 'VOC', 'CB_CAL', 'CB_CAL', '成本类', 'JZ_DR', '结算成本公式', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_001', '结转损益至利润分配', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT_JZSY', '结转损益', '0', '结转损益至利润分配', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_FJ_001', '分基结转损益比例含当日分红金额', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT_JZSY', '结转损益', '0', '分级基金结转损益比例是否考虑当天分红金额', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_FJ_002', '分基结转损益扎差结转', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT_JZSY', '结转损益', '0', '指收入减费用扎差金额按公式比例结转', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_HS_001', '计提风险金归入核算项目', 'AO', 'AO_QT', 'VOC', 'JT_FXJ', 'QT_HS', '核算项', 'FXJ_YJ', '计提风险金由产品承担还是券商承担', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_003', '利润分配至应付利润', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT_JZSY', '结转损益', '0', '利润分配至应付利润', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JYYJ', '交易佣金从文件中取', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易佣金从文件中读取', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_HDTC_JSFS', '现金头寸预测表当日核对头寸计算方式', 'AO', 'AO_QT', 'VOC', 'CHECK_CASH_TYPE', 'AO_QT', '其它', 'BHSGK_QSK', '现金头寸预测表当日核对头寸计算方式', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_XJTC_YWLX', '现金头寸预测表按业务类型不区分流入流出', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT', '其它', '0', '头寸预测表按业务类型不区分流入流出', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZMR_DBMC', '融资买入是否在担保券卖出之前', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'TA_HS', null, '1', '融资买入是否在担保券卖出之前', '其他', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_QT_SZHQ', '本币市值计算方式', 'AO', 'AO_QT', 'VOC', 'SZ_CAL', 'SZ_CAL', '市值类', 'SZ_CAL_BF', '本币市值计算方式', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_JZZYX', '结转损益时实收资本为零时利润结转', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算项', '0', '结转损益时实收资本为零时利润结转', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_SDGZ_001', '非公开发行股票锁定成本是否除权', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算项', '1', '非公开发行股票锁定成本是否除权,默认为是', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_SZJZ_SFBT', '收支结转业务支付运营费用是否补提', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算项', '0', '收支结转业务支付运营费用支付和科目余额不等时是否补提', '其他', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_ZRT_YCTS', '转融通合约延期计提利息天数', 'AO', 'AO_QT', ' ', ' ', ' ', '其他', '30', null, '其他', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_JZSY_FJ_003', '结转损益凭证是否每日生成', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'AO_QT_JZSY', '结转损益', '0', '结转损益凭证是否每日生成', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_HBLZHTZ_MS', '货币类专户台账模式', 'AO', 'AO_QT', 'VOC', 'HBLZHTZ_MS', 'QT_HS', '核算类', 'HBLZHTZ_MS1', '生成货币类专户台账时的模式', '其他', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QT_ZHJX_AJXJZ', '账户计息按照利率品种计息时利率是否按照计息基准日取', 'AO', 'AO_QT', 'VOC', 'BOOL_TYPE', 'QT_HS', '核算项', '1', '账户计息按照利率品种计息时利率是否按照计息基准日取', '其它', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_QT_CBJZ_001', '卖出证券成本结转过程保留位数', 'AO', 'AO_QT', ' ', ' ', 'CB_CAL', '成本类', '0', '证券类产品卖出成本结转中间计算过程保留位数', '其它', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_QT_SZHQ_001', '数量*行情*汇率过程保留位数', 'AO', 'AO_QT', ' ', ' ', 'SZ_CAL', '市值类', '2', '数量*行情*汇率，中间结果保留2位', '其它', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('FZ_WC_001', '分组基金尾查调整组合', 'AO', 'FZ', 'VOC', null, 'FZ', '分组基金', '510050', '分组基金尾查调整到哪个组合', '分组基金', 'PUB', 'portfolio')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_CB_001', '一级市场费用入成本', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'CB', '成本类', '0', '费用是否入成本', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_FY_001', '结算服务费每日交收', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_FY_002', '交易手续费每日交收', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_FY_003', '银行手续费每日交收', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_JX_001', '一级市场计息方式', 'AO', 'HG', 'VOC', 'AI_MOD_HG', 'JX', '计息类', 'HG_FIR', '计头计尾', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_TXFY_001', '一级市场费用摊销方式', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'TXFY', '摊销费用', '0', '摊销', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_FY_004', '结算代理佣金每日交收', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M2_HG_CB_001', '二级市场费用入成本', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'CB', '成本类', '0', '费用是否入成本', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M2_HG_JX_001', '二级市场计息方式', 'AO', 'HG', 'VOC', 'AI_MOD_HG', 'JX', '计息类', 'HG_END', '计头计尾', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M2_HG_TXFY_001', '二级市场费用摊销方式', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'TXFY', '摊销费用', '0', '摊销', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M2_HG_JX_002', '二级市场计息方式(股票质押回购)', 'AO', 'HG', 'VOC', 'AI_MOD_HG', 'JX', '计息类', 'HG_END', '计头计尾', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M2_HG_TXSY_001', '二级市场收益摊销方式', 'AO', 'HG', 'VOC', 'HG_SYJT_METHOD', 'TXSY', '摊销收益', 'HSM_NATURAL', '摊销', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_XTJYHBJX', '相同回购交易是否合并计息', 'AO', 'HG', 'VOC', 'BOOL_TYPE', 'HG', '回购', '0', '相同回购交易是否合并计息', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_HG_TXSY_001', '一级市场收益摊销方式', 'AO', 'HG', 'VOC', 'HG_SYJT_METHOD', 'TXSY', '摊销收益', 'HSM_NATURAL', '摊销', '回购', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_ETF_002', 'RQFII ETF交易费用承担方', 'AO', 'JJ', 'VOC', 'FEE_BEARER', 'JJ_HS', '核算项', 'PD_BEAR', 'RQFII ETF交易费用承担方 产品承担/投资者承担', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_JYHBETF', '投资交易型货币ETF卖出凭证是否去除当日分红转投', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_HS', '核算项', '1', '投资交易型货币ETF卖出凭证是否去除当日分红转投', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_005', '复利型货币基金节假日是否复利计提万份收益', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'HBJJ_SY', '货币基金计提万份收益', '1', '复利型货币基金节假日是否复利计提万份收益', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_004', '货币基金计提万份收益值保留2位小数方式位', 'AO', 'JJ', 'VOC', 'JW_TYPE', 'HBJJ_SY', '货币基金计提万份收益', 'JW_ROUND', '货币基金计提万份收益值保留2位小数方式位', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_JJ_PDZH_001', 'LOF基金配对转换核算方案', 'AO', 'JJ', 'VOC', 'JJ_PDZH', 'JJ_LOF', '核算项', 'SZ', 'LOF基金配对转换核算方案,默认市值法', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_JJ_PDZH_002', 'LOF基金合并核算日期T+', 'AO', 'JJ', ' ', ' ', 'JJ_LOF', '核算项', '0', 'LOF基金合并核算日期T+', '基金', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_ETF_003', '投资ETF基金投资成本计算方法', 'AO', 'JJ', 'VOC', 'ETF_CBSF', 'JJ_CBSF', '成本算法', 'FORMULA_3', '基金投资成本计算公式', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_ETF_001', 'ETF现金差额归入核算项目', 'AO', 'JJ', 'VOC', 'ETF_XJCE', 'JJ_HS', '核算项', 'ZQTZ_ETF', 'ETF现金差额归入核算项目', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_FH_001', '基金分红转投冲减核算项目', 'AO', 'JJ', 'VOC', 'HBJJ_TZSY', 'JJ_HS', '核算项', 'ZQTZ_JJ', '基金分红转投冲减核算项目', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_001', '货币基金是否计提万份收益', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'ETF_HBJJ', '收益计提', '1', '货币基金是否计提万份收益', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_002', '复利型货币基金收益归入核算项目', 'AO', 'JJ', 'VOC', 'HBJJ_TZSY', 'JJ_HS', '核算项', 'ZQTZ_JJ', '复利型货币基金收益归入核算项目', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_003', '货币基金收益计提方式', 'AO', 'JJ', 'VOC', 'HBJJ_JT', 'HBJJ_SY', '收益计提', 'HBJJ_JT_BEFORE', '货币基金收益计提方式', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_003_001', '节假日计提模式（T日计提T-1日万份收益）', 'AO', 'JJ', 'VOC', 'HBJJ_JJR_JT-1', 'HBJJ_SY', '收益计提', 'HBJJ_JJR_JT_FK', '节假日计提模式（T日计提T-1日万份收益）', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_HB_003_002', '节假日计提模式（T日计提T-0日万份收益）', 'AO', 'JJ', 'VOC', 'HBJJ_JJR_JT', 'HBJJ_SY', '收益计提', 'HBJJ_JJR_JT_WDAY', '节假日计提模式（T日计提T-0日万份收益）', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_LOFCFYWSX', 'LOF基金拆分是否在卖出之前', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_LOF', '核算项', '1', 'LOF基金拆分是否在卖出之前，默认为是', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_RGJJLX', '认购基金利息冲成本', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_HS', '核算项', '1', '认购基金利息冲成本', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_JT_001', '理财计息归入核算项目', 'AO', 'JJ', 'VOC', 'JJ_TZSY', 'JJ_HS', '核算项', 'LCJT_LXSR_ZQ', '计提理财收益归入核算项目', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_ETF_TBK_TZFS', '投资ETF基金是否按照明细券处理', 'AO', 'JJ', 'VOC', 'BOOL_TYPE', 'JJ_HS', '核算项', '0', '投资ETF基金是否按照明细券处理', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('LJ_YS_001', '联接基金对应ETF基金组合', 'AO', 'LJ', 'VOC', null, 'LJ', '联接基金', '510050', null, '联接基金', 'CLS_ETF', 'portfolio')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_JJ_JYLFYLCB', '交易类基金交易费用入成本', 'AO', 'PORT', 'VOC', 'BOOL_TYPE', 'JJ', '基金', '0', '交易类基金交易费用入成本', '基金', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_PX_FH_GGTYSGL', '港股通应收股利是否以人民币记账', 'AO', 'PX', 'VOC', 'BOOL_TYPE', 'PX_HS', '核算项', '1', '港股通业务是否以人民币对应收股利进行记账', '分红派息', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_PX_FH_SHPX', '股票是否按税后比例派息', 'AO', 'PX', 'VOC', 'BOOL_TYPE', 'PX_HS', '核算项', '1', '股票是否按税后比例派息', '分红派息', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_HQYQBZJJS', '黄金延期保证金取持仓文件中数据', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '1', '黄金延期保证金取持仓文件中数据', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_BZJJS', '期货保证金取持仓文件中数据', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '期货保证金系统计算还是取持仓文件中金额', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_DXDB_BZJ', '按单向大边保证金制度计算保证金', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '是否启用单向大边保证金制度', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_DXDB_JZR', '单向大边保证金制度截止到最后交易日前N个工作日', 'AO', 'QH', ' ', null, 'SZ_CAL', '期货业务', '5', '维护单向大边保证金制度截止到最后交易日前N个工作日', '期货', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_DXDB_XDCE', '大商所启用单向大边保证金制度', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '大商所启用单向大边保证金制度', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_DXDB_XSGE', '上期所启用单向大边保证金制度', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '上期所启用单向大边保证金制度', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_KCMXSF', '股指期货开仓采用明细算法', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '股指期货开仓采用明细算法', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_PCCBJS', '平仓时期货成本计算方式', 'AO', 'QH', 'VOC', 'QH_PCCBJS', 'QH', '期货业务', 'QH_YDJQPJ', '移动加权平均；先进先出计算', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_DXDB_XZCE', '郑商所启用单向大边保证金制度', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '郑商所启用单向大边保证金制度', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_PCCYMXSF', '股指期货平仓采用明细算法', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '0', '股指期货平仓采用明细算法', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_GZ', '期货本币估增算法', 'AO', 'QH', 'VOC', 'QH_GZ', 'SZ_CAL', '市值类', 'QH_GZSZ', '期货本币估增算法', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QH_HEJG', '合约交割数据系统自动在交割日产生', 'AO', 'QH', 'VOC', 'BOOL_TYPE', 'QH', '期权业务', '0', '合约交割数据系统自动在交割日产生', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QQ_GGZDSCJG', '上交所个股期权放弃行权时自动生成交割数据', 'AO', 'QQ', 'VOC', 'BOOL_TYPE', 'QQ', '期权业务', '0', '上交所个股期权放弃行权时自动生成交割数据', '期权', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_QQ_GGBZJJS', '个股期权保证金从文件中取', 'AO', 'QQ', 'VOC', 'BOOL_TYPE', 'QH', '期权业务', '0', '个股期权保证金系统计算还是取中登文件中金额', '期权', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZRQ_HSSX_002', '担保划出是否在融资买入之前', 'AO', 'RZRQ', 'VOC', 'BOOL_TYPE', 'ZQ_SX', '融资融券', '1', '融资融券交易顺序，1.是 担保划出在融资买入之前 2.否 担保划出不在融资买入之前', '融资融券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZRQ_FYJT_001', '节假日融资融券费用计提日期', 'AO', 'RZRQ', 'VOC', 'RZRQ_JXL', 'RZRQ_JX', '计息类', 'RZRQ_JX_AFTER', '节假日融资融券费用计提日期', '融资融券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZRQ_JXWS_001', '融资融券计息过程保留位数', 'AO', 'RZRQ', ' ', null, 'RZRQ_WS', '保留位数', '3', '融资融券计息过程保留位数', '融资融券', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZRQ_JXQS_001', '融资融券计息起始日', 'AO', 'RZRQ', 'VOC', 'RZRQ_JXLQXR', 'RZRQ_JX', '计息类', 'RZRQ_JX_DR', '从融资融券当日开始计息还是从融资融券下一自然日开始计息', '融资融券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_RZRQ_JXHQ_001', '融券计息行情取值', 'AO', 'RZRQ', 'VOC', 'RZRQ_JXLHQ', 'RZRQ_JX', '计息类', 'RZRQ_JX_DRHQ', '融券计息行情取当日还是上一交易日', '融资融券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_SP_SG_001', '自动生成送股业务的数量保留位数方式', 'AO', 'SP', 'VOC', 'JW_TYPE', 'SP_SG', '核算项', 'JW_ROUND', '自动生成送股业务的数量保留位数方式', '送股', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_SSZB', '实收资本本币算法', 'AO', 'TA', 'VOC', 'TA_XS', 'TA_XS', '销售', 'TA_YBFE', '实收资本本币算法', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_SYPZJ', '杠杆分级是否核算损益平准金_未实现', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_SYPZJ', '损益平准金算法', '1', '杠杆分级是否核算损益平准金_未实现', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_AUTO_TAFH', '是否根据权益信息产生分红数据', 'AO', 'TA', 'VOC', 'AUTO_TAFH', 'TA_XS', '销售', 'NONE_OPER', '是否根据权益信息产生分红数据', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_SS_ZJZJTQ', '产品销售中申赎是否同步到资金追加提取', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '0', '产品销售中申赎是否同步到资金追加提取', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_XS_CFJC', '产品销售数据重复检查天数', 'AO', 'TA', ' ', ' ', 'TA_XS', '销售', '0', '产品销售数据重复检查天数', '销售', ' ', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_XS_001', '结转赎回款凭证中是否结转赎回费', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '0', '结转赎回款凭证中是否结转赎回费', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_FETZ', '产品销售中份额调整业务核算项目', 'AO', 'TA', 'VOC', 'TA_FETZ', 'TA_HS', '销售', 'TA_FETZ_FETZ', '产品销售中份额调整业务除实收资本外涉及的核算项目', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_XS_DFSY', '兑付收益是否核算分红业绩报酬凭证', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '核算项', '0', '兑付收益是否核算分红业绩报酬凭证', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_FH', '产品分红结转于利润分配_应付利润', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '核算项', '0', '产品分红结转于利润分配_应付利润', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_YJBC', '业绩报酬入赎回款处理', 'AO', 'TA', 'VOC', 'TA_HS', 'TA_HS', '核算项', 'YYL_YJBCF', '业绩报酬入赎回款处理', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_JT_001', '申购/赎回款计息每日计提', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_JT', '计提', '1', '申购/赎回款计息每日计提', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_FEZS', '产品销售的份额折算业务核算项目', 'AO', 'TA', 'VOC', 'TA_FEZS_HSXM', 'TA_XS', '销售', 'TA_FEZS_HSXM_SSZB', '产品销售的份额折算业务是否要冲减损益平准金', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_QFFJ', '分级产品利润相关科目核算是否不区分分级级别', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '核算项', '1', '分级产品利润相关科目核算是否不区分分级级别', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_SSZBFJ', '分级产品实收资本科目核算是否不区分分级级别', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '核算项', '0', '分级产品实收资本科目核算是否不区分分级级别', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_SYPZJ', '核算损益平准金', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '1', '是否核算损益平准金', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_QZQJ_QRYSX', '产品销售强增强减不核算损益平准金_未实现', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '销售', '0', '产品销售强增强减不核算损益平准金_未实现', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_CPCL', '产品成立非提前到账是否走应付申购款', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '0', '产品成立非提前到账时是否走应付申购款核算项;否表示提前到账走应付申购款,非提前到账走应收申购款', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_FXZBJ', '产品销售的风险准备金业务核算项目', 'AO', 'TA', 'VOC', 'TA_FX', 'TA_XS', '销售', 'FXZBJ', '产品销售的风险准备金业务核算项目', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_HS_YJBCCF', '业绩报酬拆分为业绩报酬、服务费的比例', 'AO', 'TA', ' ', ' ', 'TA_XS', '销售', '1', '业绩报酬拆分为业绩报酬、服务费的比例', '销售', ' ', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_XS_FHJZ_ZJYF', '产品销售分红核算是否直接从应付利润结转', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_HS', '核算项', '0', '基金分红凭证，当参数为是时：负债类-银行存款；否则权益类-负债类-银行存款', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_TA_ZJLSJS', '销售数据是否按照资金流水做结算', 'AO', 'TA', 'VOC', 'BOOL_TYPE', 'TA_XS', '销售', '0', '销售数据是否按照资金流水做结算', '销售', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001_004', '溢折价不摊销到反方向', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_TYF', '摊余成本法', '0', '溢折加摊销是否摊销到反方向', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001_005', '实际利率年天数含闰年（加权实际利率法）', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_TYF', '摊余成本法', '0', '加权实际利率算法中年天数默认为365，参数可控制闰年为366', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_HS_ZQFK', '新股新债中签确认是否做返款挂账凭证', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_HS', '核算项', '1', '新股新债中签确认是否做返款挂账凭证(返款先结转至非T+1)', '核算', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001_001', '实际利率现值倒减', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_TYF', '摊余成本法', '0', '实际利率现值倒减法', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001', '摊余成本法与实际利率法', 'AO', 'ZQ', 'VOC', 'ZQ_TYCBF', 'ZQ_TYF', '摊余成本法', 'CB_JJ', '摊余成本算法摊余成本算法', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_ZQ_FY_003', '银行手续费每日交收', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_ZQ_FY_002', '交易手续费每日交收', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_ZQ_FY_001', '结算服务费每日交收', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'FY', '费用类', '0', '交易日交收', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_ZQ_BS_001', '一级市场执行顺序', 'AO', 'ZQ', 'VOC', 'ZQ_YHJ', 'ZQ_BS', '执行顺序', 'ZQ_YHJ_BUY', '银行间债券业务凭证处理顺序', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_HS_JX', '沪深市场债券当日参与计息', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'JX', '计息类', '0', '沪深市场债券当日参与计息', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_HS_YZJ', '债券计息溢折价入核算项目', 'AO', 'ZQ', 'VOC', 'ZQJX_YZJ_HSXM', 'ZQ_HS', '核算项', 'ZQJX_YZJ_LXSR', '债券计息溢折价入核算项目', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('A0_ZQ_HS_JX', '新债利息归入核算项目', 'AO', 'ZQ', 'VOC', 'ZQ_CB', 'ZQ_HS', '核算项', 'ZQTZ_CB', '新债在起息后中签的中签金额入账方式', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001_002', '实际利率保留位数', 'AO', 'ZQ', ' ', ' ', 'ZQ_TYF', '摊余成本法', '12', '实际利率保留位数', '债券', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_TYF_001_003', '实际利率差值误差范围', 'AO', 'ZQ', ' ', ' ', 'ZQ_TYF', '摊余成本法', '8', '实际利率差值误差范围', '债券', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_HS_HBFX', '还本付息节假日处理方式', 'AO', 'ZQ', 'VOC', 'ZQ_HBFX', 'ZQ_HS', '核算项', 'ZQ_HBFX_XYGZR', '债券还本付息除权日为节假日,何时生成债券还本的业务流水', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_HS_ZQHB', '多次还本付息债是否按比例冲成本', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'ZQ_HS', '核算项', '1', '多次还本付息债是否按比例冲成本', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_M1_ZQ_CB_001', '一级市场可供出售类费用入成本', 'AO', 'ZQ', 'VOC', 'BOOL_TYPE', 'CB', '成本类', '1', '银行间可供出售类债券交易费用是否入成本', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQ_HS_SJ', '利息税差额归为利息收入', 'AO', 'ZQ', 'VOC', 'ZQ_LXCE', 'ZQ_LXCE_LXSR_ZQ', '核算项', 'ZQ_LXCE_LXSR_ZQ', '债券利息税差额归入核算项目', '债券', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQZH_JJZH_001', '基金转换业务应收股利是否计入转入基金成本', 'AO', 'ZQZH', 'VOC', 'BOOL_TYPE', 'ZQZH', '证券转换', '0', '基金转换业务转出应收股利是入转入基金成本还是应收股利', '证券转换', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('AO_ZQZH_JJZH_002', '基金转换业务应收股利是否移动加权平均计算', 'AO', 'ZQZH', 'VOC', 'BOOL_TYPE', 'ZQZH', '证券转换', '1', '基金转换业务转出应收股利是根据移动加权平均计算还是取set界面维护值', '证券转换', 'PUB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_SZ_XJLZB', '现金类占净值成本比', 'HB', 'HBZB', 'VOC', 'BOOL_TYPE', 'XJZB', '占比', '0', '现金类占净值成本比', '指标类', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_JY_GZZZ', '货币型产品是否支持估值增值', 'HB', 'JY', 'VOC', 'BOOL_TYPE', 'HBJY', '货币型产品支持估增', '0', '货币型产品是否支持估值增值', '交易', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_JZ_FEJZ_002', '是否根据产品销售分红转投数据结转', 'HB', 'JZ', 'VOC', 'BOOL_TYPE', 'JZ', '结转', '0', null, '结转', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_JZ_FEJZ_003', '节假日收益不转份额', 'HB', 'JZ', 'VOC', 'BOOL_TYPE', 'JZ', '结转', '0', null, '结转', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_JZ_FEJZ_001', '收益转份额', 'HB', 'JZ', 'VOC', 'HB_FZJZ', 'JZ', 'JZ', 'HB_MONTH', '每日收益转实收资本', '结转', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_SZ_SYTS_001', '通知存款剩余期按品种天数', 'HB', 'ZB', 'VOC', 'BOOL_TYPE', 'ZB', '指标', '0', null, '指标类', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_SZ_7RNHSYL', '七日年化收益率计算方式', 'HB', 'ZB', 'VOC', 'HB_FZJZ', 'ZB', '指标', 'HB_MONTH', '七日年化收益率计算方式', '指标类', 'CLS_HB', 'pubvocabulary')")
				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('HB_SZ_PLD', '影子定价法计偏离度', 'HB', 'ZB', ' ', null, 'SZ', '影子定价', '0.0005', '影子偏离度', '指标类', 'CLS_HB', null)")				
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_QSGHF', '深交所券商过户费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', ' ', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_YHS', '深交所印花税计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_YJ', '深交所佣金明细汇总计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_ZGF', '深交所证管费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_TA_SHF', '赎回费含归资产费用', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'TA', '销售数据', '1', '赎回费含赎回费收入', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_ZYS_HG', '上海质押式回购购回价格精度位数', 'CO', 'PUB', ' ', null, 'GGC', '公共参数', '10', '10', '公共', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_JJJY_ZQ', '深圳净价交易债券结算保留位数', 'CO', 'PUB', ' ', null, 'GGC', '公共参数', '8', null, '公共', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_ZYS_HG', '深圳质押式回购购回价格精度位数', 'CO', 'PUB', ' ', null, 'GGC', '公共参数', '8', '8', '公共', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_JYSDSFS', '读取交易所数据方式', 'CO', 'PORT', 'VOC', 'FACE_READ_TYPE', 'GGC', '公共参数', 'FRT_GD', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M1_FY_GZQH', '股指期货费用计算方式', 'CO', 'PORT', 'VOC', 'FEE_MODE_CALC', 'GZQH', '股指期货库', 'FMC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_GHF', '上交所过户费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_JSF', '上交所经手费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_JZF', '上交所结算费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_QSGHF', '上交所券商过户费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', ' ', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_YHS', '上交所印花税计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_YJ', '上交所佣金明细汇总计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_ZGF', '上交所证管费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '上交所过户库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_GHF', '深交所过户费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_JSF', '深交所经手费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_JZF', '深交所结算费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'HB', '深交所回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_ZYF', '质押费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'ZHHB', '深交所综合回报库', 'FEE_CALC_MX', '股票质押式回购', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_JSJE_ZQ', '深圳净值交易债券结算金额公式', 'CO', 'PUB', 'VOC', 'ZQ_JSJE', 'GGC', '公共参数', 'ZQ_JSJE_HZ', '深圳净值交易债券结算金额', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_GSZ_ZQ', '深交所公司债券DVP交收模式', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '深交所公司债券DVP交收模式', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_MODE_ETFXJCE', 'ETF预估现金差额获取下一天的ETF文件', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'ZDYTBK', 'ETF预估现金差额', '0', '预估现金差额计算方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CS_GHF_ETF', 'ETF申赎过户费根据交易费用设置计算', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'ETF', 'ETF申赎', '0', 'ETF申赎过户费根据交易费用设置计算', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_GGT', '上交所港股通本币成交金额计算方式', 'CO', 'PORT', 'VOC', 'FEE_MODE_CALC', 'GH', '上交所港股通', 'PMCG_CBDJWCJFY', '上交所港股通本币成交金额计算方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M1_SJSGGQQMFXQYJ', '上交所个股期权卖方行权不收佣金', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'QQ', '期权业务', '1', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M1_SJSGGQQMFKCYJ', '上交所个股期权卖方开仓不收佣金', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'QQ', '期权业务', '1', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_RZRQMQHK_LX', '金仕达融资融券卖券还款清算模式', 'CO', 'PORT', 'VOC', 'QSMS_RZRQ_MQHK', 'RZRQ', '融资融券', 'QSMS_RZRQ_MQHK_BXH', '融资融券卖券还款清算是否清算出本金和利息两条数据', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('C0_QH_ZJSJYFYJSFS', '中金所期货交易费用从文件中取', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'QH', '期货业务', '1', '中金所期货交易费用从文件中取还是系统根据费率算', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_YJ_GGT', '港股通佣金明细汇总计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '港股通', 'FEE_CALC_BH', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_FH_DZCL', '分红派息是否优先进行到账处理', 'CO', 'FH', 'VOC', 'BOOL_TYPE', 'FH', '分红', '0', '分红派息是否优先进行到账处理', '分红', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_YJ_XSB', '新三板佣金明细汇总计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_JJ_JJCFHBHZ', '基金拆分合并同一天多笔是否汇总', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '0', '基金拆分合并同一天多笔是否汇总', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_HQ_WCJ_SFDR', '当日无成交的行情是否读入系统', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '当日无成交的行情是否读入系统', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_QHHQ_SJDQFS', '期货行情数据读取方式', 'CO', 'PUB', 'VOC', 'CO_QHHQ_SJDQFS', 'GGC', '公共参数', 'RT_QHHQ_QHGS', null, '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_RZRQ_SJDQFS', '融资融券业务数据读取方式', 'CO', 'FACE', 'VOC', 'CO_RZRQ_SJDQFS', 'RZRQ', '融资融券', 'RT_RZRQ_JYS', null, '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_HQ_SH_SJDQFS', '上交所行情读取方式', 'CO', 'PUB', 'VOC', 'READ_TYPE_SHHQ', 'GGC', '公共参数', 'RT_HQ_SH_FAST', '上交所行情读取方式', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_JSF_XSB', '新三板经手费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_YHS_XSB', '新三板印花税计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_JZF_XSB', '新三板结算费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_ZGF_XSB', '新三板证管费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_GHF_XSB', '新三板过户费计算方式', 'CO', 'PORT', 'VOC', 'FEE_TYPE_CALC', 'GH', '新三板回报库', 'FEE_CALC_MX', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('C0_MODE_ETFTZKSTBKSJ', '投资ETF计算可收退补款是否取补票明细文件', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'ETF', 'ETF申赎', '0', '投资ETF时某些客户可获取补票明细文件，补券成本可直接从文件中取。该参数控制在有该文件时，可收退补款计算是取文件中数据，还是按收盘价*数量计算', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_FH_DZCL_JJ', '基金分红派息是否优先进行到账处理', 'CO', 'FH', 'VOC', 'BOOL_TYPE', 'FH', '分红', '0', '用于控制基金分红派息和债券还本付息数据中“结算日期”的处理', '分红', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_MODE_SJSMXJYFY', '深中登清算明细库交易费用从文件中取', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'SJSMX', '公共参数', '1', '深中登清算明细库交易费用是系统自动计算还是从文件中取', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_SJSV5_SJDQFS', '深交所V5交易接口数据读取方式', 'CO', 'PORT', 'VOC', 'READ_TYPE_SJSV5', 'GGC', '公共参数', 'RT_SJSV5_SZDJS', '深交所V5交易接口数据读取方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_FH_DZCL_GP', '股票分红派息是否优先进行到账处理', 'CO', 'FH', 'VOC', 'BOOL_TYPE', 'FH', '分红', '0', '用于控制股票分红派息和债券还本付息数据中“结算日期”的处理', '分红', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_FH_DZCL_ZQ', '债券分红派息是否优先进行到账处理', 'CO', 'FH', 'VOC', 'BOOL_TYPE', 'FH', '分红', '0', '用于控制债券分红派息和债券还本付息数据中“结算日期”的处理', '分红', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_FGKFXXGZD', '公开发行新股申购是否采用新制度', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '上交所和深交所新股申购是否采用无需预先缴款制度', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_SJSHQ_SJDQFS', '深交所行情读取方式', 'CO', 'PUB', 'VOC', 'READ_TYPE_SJSHQ', 'GGC', '公共参数', 'RT_SJSHQ_SJSHQ', '深交所行情、证券信息读取方式', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_MODE_GTMS', '上交所ETF申赎经营模式', 'CO', 'PORT', 'VOC', 'MANAGE_TYPE', 'ZDYTBK', 'ETF申赎经营模式', 'MANAGE_TGMS', '上交所应退补款业务数据清算日期', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_HG_ZYSQS', '股票质押式回购数据清算方式', 'CO', 'PUB', 'VOC', 'GPZYHG_QSFS', 'GGC', '公共参数', 'EXCHANGE', '股票质押式回购数据清算方式', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M1_API', '外汇交易中心投资分类标识', 'CO', 'PORT', 'VOC', 'IVT_CLS', 'API', '外汇交易中心', 'IC_TD', '默认为交易类', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M1_SJSGGQQFY', '上交所个股期权经手费从文件中取', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'QQ', '期权业务', '1', '上交所个股期权经手费从文件中取', '期权', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_GZPTYW', '固定收益平台业务数据读取方式', 'CO', 'PORT', 'VOC', 'RT_GZPTYW_TYPE', 'GH', '固定收益平台', 'RT_GZPTYW_ZGH', '固定收益平台业务数据读取方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_ZRT_DZSF', '转融通计息倒置算法', 'CO', 'PORT', 'VOC', 'INT_ALGO', 'ZRT', '转融通', 'DQRGC', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_HSFZJYLSDSFS', '读取恒生分组交易流水数据方式', 'CO', 'PORT', 'VOC', 'HS_FACE_READ_TYPE', 'GGC', '公共参数', 'HFRT_JJ', '读取恒生分组交易流水数据方式', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_JJCFCQYQTS', '基金拆分除权延期天数', 'CO', 'PORT', ' ', ' ', ' ', ' ', '0', 'GF、JG库基金拆分除权日期与FS/CJRQ相差天数 ', '组合', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_LCMBYLXBLWS', '理财品种每百元利息保留小数位数', 'CO', 'PUB', ' ', null, 'GGC', '公共参数', '12', '12', '公共', 'PUB', null)")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_M2_CG_JYFGHMS_HGT', '上交所沪港通是否交易费合计计算且成本轧差（工行模式）', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'HK', '沪港通交易明细文件', '0', '沪港通业务交易费用进行合计后再乘以汇率，并且成本项本位币由轧差所得', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_MODE_ETFHJKSTB', '申赎上交所黄金ETF可收退补计算方式', 'CO', 'PORT', 'VOC', 'TBKJS_TYPE', 'TBKJS', '黄金ETF可收退补', 'TBKJS_SLCSJ', '1.取篮子文件替代金额 2.按替代数量*收盘价计算', '黄金ETF可收退补', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_QYSJ_SJDQFS', '深交所权益数据读取方式', 'CO', 'FACE', 'VOC', 'READ_TYPE_QYSJ', 'QYSJ', '权益数据', 'RT_QYSJ_JG', '权益数据读取方式', '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_SGBS_SM', '送股是否补税', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '默认为0,增加一个“送股是否补税”参数', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_SJSJYXHB_SS', '上交所交易型货币ETF申赎读取方式', 'CO', 'FACE', 'VOC', 'CO_SJSHBETF_DQFS', 'SJSHBETF_DQFS', '读取方式', 'SJSHBETF_SJSJSMX', null, '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_MODE_BJSJGJYFY', '新三板明细结果库交易费用从文件中取', 'CO', 'PORT', 'VOC', 'BOOL_TYPE', 'HB', '新三板', '1', '新三板明细结果库交易费用从文件中取', '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_TA_BONUS', '中登TA分红数据是否处理', 'CO', 'FACE', 'VOC', 'BOOL_TYPE', 'TA', 'TA', '1', '中登TA分红数据是否处理', '接口', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_ZQPZXX_SHZT', '系统自动增加的品种信息默认已审核', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '系统自动增加的品种信息是否默认已审核', '公共', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_SJSZHXY_DQFS', '深交所综合协议平台业务读取数据方式', 'CO', 'PORT', 'VOC', 'SJSJY_DQFS', 'GGC', '公共参数', 'DQFS_SJSHB', null, '组合', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('CO_QH_IN_JKZXJYSX', '监控中心境内期货数据交易属性', 'CO', 'QH', 'VOC', 'QH_DTA_TYPE', 'QH', '期货业务', 'QH_KHBH', '监控中心境内期货数据交易属性', '期货', 'PUB', 'pubvocabulary')")
//				.addSql("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE) values ('C0_JYSZTGSHZT', '交易所转托管流水的默认审核状态为已审核', 'CO', 'PUB', 'VOC', 'BOOL_TYPE', 'GGC', '公共参数', '1', '该参数控制转托管业务清算流水的审核状态', '公共', 'PUB', 'pubvocabulary')")
				.endScript();
	}
	
	/**
	 * 核算业务
	 * @param builder
	 */
	private void buildHSYW(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZQ', '计提债券利息', 'RCJTBUS', 70200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZQLX', '计提债券利息', 'RCJT_ZQ', 70210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_HG', '计提回购收益', 'RCJTBUS', 70300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_HGSY', '计提回购收益', 'RCJT_HG', 70310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_CK', '计提存放利息', 'RCJTBUS', 70400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_BTLX', '存放补提利息', 'RCJT_CK', 70410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_CKLX', '计提存放利息', 'RCJT_CK', 70420, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_CJ', '计提同业拆借', 'RCJTBUS', 70500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_TYCJ', '计提同业拆借', 'RCJT_CJ', 70510, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_PJ', '计提票据利息', 'RCJTBUS', 70600, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_PJSY', '计提票据利息', 'RCJT_PJ', 70610, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZH', '计提账户利息', 'RCJTBUS', 80150, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_JZ', '待结转款计息', 'RCJTBUS', 70800, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_JZLX', '待结转款计息', 'RCJT_JZ', 70810, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_FXJ', '计提风险基金', 'RCJTBUS', 70900, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_FXJJ', '计提风险基金', 'RCJT_FXJ', 70910, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_HH', '计提利率互换', 'RCJTBUS', 71000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_LLHH', '计提利率互换', 'RCJT_HH', 71010, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_JJ', '计提理财收益', 'RCJTBUS', 71100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_JJSY', '计提理财收益', 'RCJT_JJ', 71110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_HBSY', '计提万份收益', 'RCJT_JJ', 71120, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YY', '运营收支业务', 'RCJTBUS', 71200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_SF', '计提营业税费', 'RCJTBUS', 71300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_JYFY', '计提固定费用', 'RCJTBUS', 71400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_GDFY', '计提固定费用', 'RCJT_JYFY', 71410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZBUS', '资产估值业务', '[root]', 80000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_ZQ', '证券库存估值', 'ZCGZBUS', 80100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_ZQKC', '证券库存估值', 'ZCGZ_ZQ', 80110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_XJSF', '账户利息税', 'RCJT_SF', 80120, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YYSF', '证券营业税', 'RCJT_SF', 80121, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZQSF', '债券利息税', 'RCJT_SF', 80122, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_XJ', '现金库存估值', 'ZCGZBUS', 80200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_XJKC', '现金库存估值', 'ZCGZ_XJ', 80210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_YY', '运营费用估值', 'ZCGZBUS', 80300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_YYFY', '运营费用估值', 'ZCGZ_YY', 80310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YYSZ', '运营收支业务', 'RCJT_YY', 80320, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YYSZ_FINAL', '运营收支日终费用', 'RCJT_YY', 80321, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZBUS', '损益结转业务', '[root]', 90000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_JZSY', '损益结转业务', 'SYJZBUS', 90100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_JZSR', '损益结转(收入)', 'SYJZ_JZSY', 90110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_JZZC', '损益结转(支出)', 'SYJZ_JZSY', 90111, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_JZQY', '权益结转业务', 'SYJZ_JZSY', 90112, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_SYJZ', '收益结转业务', 'SYJZBUS', 90200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ_SYFP', '收益结转业务', 'SYJZ_SYJZ', 90210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('FECF', '份额折算拆分', 'SYJZBUS', 90300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FECF', '份额拆分', 'FECF', 90310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SCCWBUS', '生成财务凭证', '[root]', 91000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SCCW_SCPZ', '生成财务凭证', 'SCCWBUS', 91100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SCCW_SCPZ_KMH', '生成财务凭证', 'SCCW_SCPZ', 91110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQMK', '证券借贷业务', 'TZJYBUS', 22400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQMK_BUY', '卖空_买券', 'ZQMK', 22420, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQMK_SELL', '卖空_卖券', 'ZQMK', 22410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_XQ_LC', '期权买方行权_认购', 'QQXQ', 21711, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_XQ_SP', '期权卖方行权_认沽', 'QQXQ', 21712, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_XQ_LP', '期权买方行权_认沽', 'QQXQ', 50800, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_XQ_SC', '期权卖方行权_认购', 'QQXQ', 50801, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY_DC', '期权对冲', 'QQJY', 21613, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY_TC', '期权调仓', 'QQJY', 21612, 1, ' ')")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_ZSTZ', '指数调整', 'ZQZH', 40310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_LXZFE', '利息转份额', 'TAXS', 30125, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_PZZQ', '配债中签', 'ZQJY', 20424, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_RG', '场内认购', 'JJJY', 20512, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_JJFC', '基金分拆', 'ZQSP', 40219, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_JJHB', '基金合并', 'ZQSP', 40220, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ', '资金追加业务', 'TZJYBUS', 71510, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ_ZJ', '资金追加', 'ZJZJTQ', 71511, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ_TQ', '资金提取', 'ZJZJTQ', 71512, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_ZQT', '资券通', 'TAXS', 30126, 1, ' ')")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_DBHR', '担保划入', 'RZRQ', 22210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_DBHC', '担保划出', 'RZRQ', 22220, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_TQ', '场外提取', 'CWSS', 50501, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ_ZJ_LJJZ_ZYZJ', '资金追加_累计净值_自有资金追加', 'ZJZJTQ', 71513, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RQLX', '融券利息项', 'SZJZYW', 60320, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZBJ', '融资本金项', 'SZJZYW', 60321, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZLX', '融资利息项', 'SZJZYW', 60319, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJHH_BZJTZ', '保证金调整', 'ZJHH', 60413, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_HBFX', '债券还本付息', 'ZQJY', 20414, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_XJSG_GGT', '港股通_现金收购', 'GPJY', 50303, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_FCHB_GGT', '港股通_分拆合并', 'ZQSP', 40219, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_GFSG_GGT', '港股通_股份收购', 'ZQZH', 40316, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_QZZH', '权证转换', 'ZQZH', 50701, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY_BUY', '现货买入', 'XHJY', 21813, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY_SELL', '现货卖出', 'XHJY', 21814, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_DFSY', '兑付收益', 'TAXS', 30127, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZDY', '计提自定义凭证业务', 'RCJTBUS', 71500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZDYPZ', '计提自定义凭证业务', 'RCJT_ZDY', 71501, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_SELL', '理财卖出', 'JJJY', 50100, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TZJYBUS', '投资交易业务', '[root]', 20000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XGSG', '新股申购业务', 'TZJYBUS', 20100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XGSG_SQ', '新股申请', 'XGSG', 20110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XGSG_QR', '新股确认', 'XGSG', 20111, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XZSG', '新债申购业务', 'TZJYBUS', 20200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XZSG_SQ', '新债申请', 'XZSG', 20210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XZSG_QR', '新债确认', 'XZSG', 20211, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY', '股票交易业务', 'TZJYBUS', 20300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_BUY', '股票买入', 'GPJY', 20310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_ZR', '股票注入', 'GPJY', 20311, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_ZFZQ', '增发中签', 'GPJY', 20312, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY', '债券交易业务', 'TZJYBUS', 20400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_BUY', '债券买入', 'ZQJY', 20410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_FX', '债券分销', 'ZQJY', 20411, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_SH', '债券赎回', 'ZQJY', 20412, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_ZR', '债券注入', 'ZQJY', 20413, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_YMR', '债券预买入', 'ZQJY', 20420, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_YMC', '债券预卖出', 'ZQJY', 20421, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_YMR_JS', '债券预买入_结算', 'ZQJY', 20422, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_YMR_JG', '债券预买入_交割', 'ZQJY', 20423, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY', '理财交易业务', 'TZJYBUS', 20500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_BUY', '理财买入', 'JJJY', 20510, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_SG', '货基申购', 'JJJY', 20511, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS', '开放申赎业务', 'TZJYBUS', 20600, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_SQ', '场外申请', 'CWSS', 20610, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_QR', '场外确认', 'CWSS', 20611, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CNSS', '场内申赎业务', 'TZJYBUS', 20700, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CNSS_TZ', '场内ETF调整', 'CNSS', 20710, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CNSS_SG', '场内ETF申购', 'CNSS', 20711, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZJY', '权证投资业务', 'TZJYBUS', 20800, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZJY_BUY', '权证买入', 'QZJY', 20810, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZXQ', '权证行权业务', 'TZJYBUS', 20900, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZXQ_QZXQ', '权证行权', 'QZXQ', 20910, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZXQ_FQXQ', '放弃行权', 'QZXQ', 20911, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY', '回购交易业务', 'TZJYBUS', 21100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_SQ', '融券首期', 'HGJY', 21110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_ZR', '融券转入', 'HGJY', 21111, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_ZQ', '融券支取', 'HGJY', 21112, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_DQ', '融券到期', 'HGJY', 21113, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_SQ', '融资首期', 'HGJY', 21120, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_ZR', '融资转入', 'HGJY', 21121, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_ZQ', '融资支取', 'HGJY', 21122, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_DQ', '融资到期', 'HGJY', 21123, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CKTZ', '同业存放业务', 'TZJYBUS', 21200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CKTZ_SQ', '存放首期', 'CKTZ', 21210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CKTZ_ZR', '存放转入', 'CKTZ', 21211, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CKTZ_ZQ', '存放支取', 'CKTZ', 21212, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CKTZ_DQ', '存放到期', 'CKTZ', 21213, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY', '同业拆借业务', 'TZJYBUS', 21300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CC_SQ', '拆出首期', 'CJJY', 21310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CC_ZR', '拆出转入', 'CJJY', 21311, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CC_ZQ', '拆出支取', 'CJJY', 21312, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CC_DQ', '拆出到期', 'CJJY', 21313, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CR_SQ', '拆入首期', 'CJJY', 21320, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CR_ZR', '拆入转入', 'CJJY', 21321, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CR_ZQ', '拆入支取', 'CJJY', 21322, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CJJY_CR_DQ', '拆入到期', 'CJJY', 21323, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('PJJY', '票据交易业务', 'TZJYBUS', 21400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('PJJY_BUY', '票据买入', 'PJJY', 21410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('PJJY_SELL', '票据卖出', 'PJJY', 21411, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('PJJY_DF', '票据兑付', 'PJJY', 21412, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QHJY', '期货交易业务', 'TZJYBUS', 21500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QHJY_KC', '期货开仓', 'QHJY', 21510, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QHJY_PC', '期货平仓', 'QHJY', 21511, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QHJY_JG', '期货交割', 'QHJY', 21512, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QHJY_GZJG', '国债期货交割', 'QHJY', 21520, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY', '期权交易业务', 'TZJYBUS', 21600, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY_KC', '期权开仓', 'QQJY', 21610, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY_PC', '期权平仓', 'QQJY', 21611, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQJY_JG', '期权交割', 'QQJY', 50802, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ', '期权行权业务', 'TZJYBUS', 21700, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_XQ', '期权行权', 'QQXQ', 21710, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QQXQ_FQXQ', '期权放弃行权', 'QQXQ', 21720, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY', '现货交易业务', 'TZJYBUS', 21800, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY_KC', '现货开仓', 'XHJY', 21810, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY_PC', '现货平仓', 'XHJY', 21811, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XHJY_JG', '现货交割', 'XHJY', 21812, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YQJY', '远期外汇业务', 'TZJYBUS', 21900, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YQJY_BUY', '远期外汇买入', 'YQJY', 21910, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YQJY_SELL', '远期外汇卖出', 'YQJY', 21920, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HHJY', '互换交易业务', 'TZJYBUS', 22100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HHJY_KC', '互换交易开仓', 'HHJY', 22110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HHJY_PC', '互换交易平仓', 'HHJY', 22120, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ', '融资融券业务', 'TZJYBUS', 22200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_DBHR', '担保划入', 'RZRQ', 22210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_MR', '担保买入', 'RZRQ', 22211, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_DBHC', '担保划出', 'RZRQ', 22220, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_MC', '担保卖出', 'RZRQ', 50303, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RZMR', '融资买入', 'RZRQ', 22221, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RZMC', '融资还款_卖券', 'RZRQ', 22231, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQHQ_YQ_T1', '融券还券_余券', 'RZRQ', 22240, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQMC', '融券卖出', 'RZRQ', 22241, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQHQ_MQ', '融券还券_买券', 'RZRQ', 22242, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQHQ', '融券还券', 'RZRQ', 22243, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQHQ_CS', '融券还券_产生', 'RZRQ', 22244, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RZRQ_RQHQ_YQ', '融券还券_余券', 'RZRQ', 22245, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_GP', '股票质押回购', 'TZJYBUS', 22300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_CSHG', '初始回购_正回购', 'HGJY_GP', 22310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_GHJY', '购回交易_正回购', 'HGJY_GP', 22311, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_BCZY', '补充质押', 'HGJY_GP', 22312, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_ZHG_JCZY', '解除质押', 'HGJY_GP', 22313, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_CSHG', '初始回购_逆回购', 'HGJY_GP', 22320, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HGJY_NHG_GHJY', '购回交易_逆回购', 'HGJY_GP', 22321, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CPXSBUS', '产品销售业务', '[root]', 30000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS', '产品销售业务', 'CPXSBUS', 30100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_JJCL', '产品成立', 'TAXS', 30110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_SG', '产品申购', 'TAXS', 30111, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FHTZ', '产品分投', 'TAXS', 30112, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FH', '产品分红', 'TAXS', 30113, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_SH', '产品赎回', 'TAXS', 30114, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_ZC', '产品转出', 'TAXS', 30115, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_QZQJ', '强增强减', 'TAXS', 30120, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FETZ', '份额调整', 'TAXS', 30121, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FXZBJ', '风险准备金', 'TAXS', 30122, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_FXBC', '风险补偿', 'TAXS', 30123, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_QJ_FE', '强减份额', 'TAXS', 30124, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GSXWBUS', '公司行为业务', '[root]', 40000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP', '证券送配业务', 'GSXWBUS', 40200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_SG', '送股', 'ZQSP', 40210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_QZSP', '权证送配', 'ZQSP', 40211, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_PZ', '配债', 'ZQSP', 40212, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_PG', '配股', 'ZQSP', 40213, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_PGJK', '配股缴款', 'ZQSP', 40214, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_KFLZSP', '可分离债送配', 'ZQSP', 40215, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_CF', '拆分', 'ZQSP', 40216, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_GFDJ', '股份对价', 'ZQSP', 40217, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQSP_SYJZ', '收益结转', 'ZQSP', 40218, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH', '证券转换业务', 'GSXWBUS', 40300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_ZTG', '转托管', 'ZQZH', 40311, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_ZQDH', '债券调换', 'ZQZH', 40312, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_JJZH', '基金转换', 'ZQZH', 40313, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_HG', '换股', 'ZQZH', 40314, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_CFL', '投资重分类', 'ZQZH', 40315, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQLT', '证券流通业务', 'GSXWBUS', 40400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQLT_ZQLT', '证券流通业务', 'ZQLT', 40410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_CX', '债券承销', 'ZQJY', 50402, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_HS', '债券回售', 'ZQJY', 50403, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_DF', '债券兑付', 'ZQJY', 50404, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_SHQR', '场外赎回', 'CWSS', 50500, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QZJY_SELL', '权证卖出', 'QZJY', 50600, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_ZZG', '债转股', 'ZQZH', 50700, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJSBUS', '资金结算业务', '[root]', 60000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_XS', '产品销售结算', 'JYJSBUS', 60100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_XSJS', '产品销售结算', 'JYJS_XS', 60110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_ZQ', '证券交易结算', 'JYJSBUS', 60200, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_HHJS', '互换平仓结算', 'JYJS_ZQ', 60210, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_ZQJS', '证券交易结算', 'JYJS_ZQ', 60220, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SZJZYW', '收支结转业务', 'JYJSBUS', 60300, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SZJZ', '收支结转', 'SZJZYW', 60301, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YYSZ', '运营收支项', 'SZJZYW', 60302, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YTDT', '预提待摊项', 'SZJZYW', 60303, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJYF_FY', '交易费用项', 'SZJZYW', 60304, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJYF_YJ', '交易佣金项', 'SZJZYW', 60305, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YYSF_ZQ', '证券税费项', 'SZJZYW', 60306, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('YYSF_ZJ', '资金税费项', 'SZJZYW', 60307, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQHL', '证券红利项', 'SZJZYW', 60308, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJLX', '资金利息项', 'SZJZYW', 60309, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQLX', '证券利息项', 'SZJZYW', 60310, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_SH', '货基赎回', 'JJJY', 50101, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JJJY_DF', '理财兑付', 'JJJY', 50102, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CNSS_SH', '场内ETF赎回', 'CNSS', 50200, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CNSS_JZ', '场内ETF结转', 'CNSS', 50201, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_SELL', '股票卖出', 'GPJY', 50300, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_CQ', '股票抽取', 'GPJY', 50301, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_YYSG', '要约收购', 'GPJY', 50302, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_CQ', '债券抽取', 'ZQJY', 50401, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_SELL', '债券卖出', 'ZQJY', 50400, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XSLX', '销售利息项', 'SZJZYW', 60311, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XSSZ_F', '销售费用项', 'SZJZYW', 60312, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('XSSZ_K', '销售收入项', 'SZJZYW', 60313, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('HDSY', '汇兑损益项', 'SZJZYW', 60314, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QTFY', '其他费用项', 'SZJZYW', 60315, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JZZB', '减值准备项', 'SZJZYW', 60316, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QTSZ', '其他收支项', 'SZJZYW', 60317, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYJZ', '收益结转项', 'SZJZYW', 60318, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJHH', '资金汇划业务', 'JYJSBUS', 60400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJHH_HB', '资金划拨', 'ZJHH', 60410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJHH_HH', '资金换汇', 'ZJHH', 60411, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJHH_LXJZ', '利息结转', 'ZJHH', 60412, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYHZ', '交易回转业务', 'JYJSBUS', 60500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYHZ_ZQJY', '证券交易回转', 'JYHZ', 60510, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJTBUS', '日常计提业务', '[root]', 70000, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YD', '预提待摊业务', 'RCJTBUS', 70100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YTDT', '预提待摊业务', 'RCJT_YD', 70110, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_CDQJ', '侧袋强减', 'TAXS', 30127, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_CDQZ', '侧袋强增', 'TAXS', 30127, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX', '对价派息业务', 'GSXWBUS', 40100, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_HLBS', '红利补税业务', 'DJPX', 40112, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_HLTZ', '红利投资业务', 'DJPX', 40113, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_TQSY_MC', '卖出提取收益业务', 'DJPX', 40114, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_TQSY_SH', '赎回提取收益业务', 'DJPX', 40115, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_XJDJ', '对价派息业务', 'DJPX', 40111, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ETFXS', 'ETF产品销售业务', 'CPXSBUS', 80900, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ETFXS_XS', 'ETF产品销售', 'ETFXS', 80910, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('FZJY_DQ', '非标负债交易_到期', 'FZJY', 22530, 1, ' ')")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('FZJY_FX', '非标负债交易_付息', 'FZJY', 22525, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_ETFXS', 'ETF产品销售结算', 'JYJSBUS', 60150, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('JYJS_ETFXSJS', 'ETF产品销售结算', 'JYJS_ETFXS', 60151, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('QSKX', '清算款收支项', 'SZJZYW', 60322, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZRT', '计提转融通利息', 'RCJT_ZRTLX', 70207, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZRTLX', '计提转融通利息', 'RCJTBUS', 70205, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YXG', '计提优先股利息', 'RCJTBUS', 70700, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYHH', '收益互换业务', 'TZJYBUS', 22600, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYHH_DQ', '收益互换到期', 'SYHH', 22611, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SYHH_SQ', '收益互换首期', 'SYHH', 22610, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_YXGLX', '计提优先股利息', 'RCJT_YXG', 70710, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_YJBC', '业绩报酬', 'TAXS', 30127, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('TAXS_ZR', '产品转入', 'TAXS', 30112, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_HH', '互换业务估值', 'ZCGZBUS', 80400, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCGZ_SYHH', '收益互换期间估值', 'ZCGZ_HH', 80410, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCJY_DQ', '资产到期', 'ZCJY', 22670, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZCJY_FX', '资产付息', 'ZCJY', 22680, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQZH_FBZLOF', '封闭分级合并转LOF', 'ZQZH', 40317, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRQLX', '转融券利息项', 'SZJZYW', 60322, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRTJY', '转融通业务', 'TZJYBUS', 22500, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZJDQ', '融出资金_到期', 'ZRTJY', 50302, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZJSQ', '融出资金_首期', 'ZRTJY', 50301, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQBFDQ', '融出证券_部分展期到期', 'ZRTJY', 50308, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQBFJE', '融出证券_部分展期了结', 'ZRTJY', 50307, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQDQ', '融出证券_到期', 'ZRTJY', 50304, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQQBDQ', '融出证券_全部展期到期', 'ZRTJY', 50309, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQQBJE', '融出证券_全部展期了结', 'ZRTJY', 50306, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQSH', '融出证券_索还', 'ZRTJY', 50305, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRT_ZQSQ', '融出证券_首期', 'ZRTJY', 50303, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZRZLX', '转融资利息项', 'SZJZYW', 60323, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_RZRQ', '计提融资融券利息', 'RCJTBUS', 70700, 0, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_RZRQLX', '计提融资融券利息', 'RCJT_RZRQ', 70710, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ_BC', '补仓', 'ZJZJTQ', 71514, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZJZJTQ_BCTQ', '补仓提取', 'ZJZJTQ', 71515, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_FHPX', '分红派息业务', 'DJPX', 55000, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('RCJT_ZHLX', '计提账户利息', 'RCJT_ZH', 80151, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_SGXJCE', '申购现金差额', 'CWSS', 20612, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('CWSS_SHXJCE', '赎回现金差额', 'CWSS', 20613, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('FHJS', '分红结算项', 'SZJZYW', 60325, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SGJS', '申购结算项', 'SZJZYW', 60323, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('SHJS', '赎回结算项', 'SZJZYW', 60324, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('DJPX_FHTQSY', '分红收益提取', 'DJPX', 40116, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('ZQJY_JJRHBHL', '节假日还本汇率', 'ZQJY', 20415, 1, null)")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_HS', '股票交易_回售', 'GPJY', 50310, 1, ' ')")
				.addSql("insert into T_S_DVA_ITEM (C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA) values ('GPJY_SH', '股票交易_赎回', 'GPJY', 50311, 1, ' ')")
				//add by gongyue 20170503
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ETFXS_CL', 'ETF产品成立', 'ETFXS', 80910, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ETFXS_TBKCJ', 'ETF冲减退补款', 'ETFXS', 80912, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('GPJY_JZZB', '股票减值准备', 'GPJY', 80130, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('JJJY_JZZB', '理财减值准备', 'JJJY', 80132, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('YTZDT', '预提转待摊', 'SZJZYW', 60303, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ZJZJTQ_ZJTQ_SB', '资金提取_社保', 'ZJZJTQ', 71516, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ZJZJTQ_ZQTQ_SB', '证券提取_社保', 'ZJZJTQ', 71518, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ZJZJTQ_ZQZJ_SB', '证券追加_社保', 'ZJZJTQ', 71517, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ZQJY_JZZB', '债券减值准备', 'ZQJY', 80131, 1, NULL)")
				.addSql("insert into T_S_DVA_ITEM(C_DVA_ITEM_CODE, C_DVA_ITEM_NAME, C_DVA_ITEM_CODE_P, N_ORDER, N_DETAIL, C_PARA)VALUES('ZQSP_GFTZ_GGT', '港股通_股份调整', 'ZQSP', 40220, 1, NULL)")
				.endScript();
	}
	
	/**
	 * 费用关联字典
	 * @param builder
	 */
	private void buildFYGLZD(ScriptBuilder builder){
		//T_S_DF_FEE_RELA
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'ZQZH', 9, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'ZQZH', 8, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHZCF', 'ZQZH', 5, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'HGJY_GP', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'HGJY_GP', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'ZQZH', 5, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'ZQZH', 6, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'ZQZH', 7, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSDLYJ', 'HGJY', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSDLYJ', 'ZQJY', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_QSGHF', 'HGJY', 10, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_QSGHF', 'HGJY_GP', 5, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_QSGHF', 'JJJY', 7, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_QSGHF', 'ZQJY', 13, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_QSGHF', 'GPJY', 11, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZTGF', 'GZQH', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'GZQH', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'GZQH', 0, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'ZQJY', 11, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF_SQS', 'ZQJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'ZQJY', 11, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'ZQJY', 12, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF_SQS', 'ZQJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'ZQJY', 9, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHSXF', 'ZQJY', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'ZQJY', 8, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF', 'ZQJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'ZQJY', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'ZQJY', 10, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'ZQJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'HGJY', 8, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF_SQS_HG', 'HGJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'HGJY', 8, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'HGJY', 9, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF_SQS', 'HGJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHSXF', 'HGJY', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF_HG', 'HGJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'HGJY', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF_HG', 'HGJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'QQXQ', 10, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYYJ', 'QQXQ', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGYJ', 'QQXQ', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'QQXQ', 11, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'QQXQ', 9, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'QQXQ', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'QQXQ', 8, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'QQXQ', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'QQXQ', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGSXF', 'QQXQ', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'QQXQ', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YYL_TZGWF', 'ZCJY', 3, 'YYSZ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_FTSXF', 'ZCJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YYL_GLF', 'ZCJY', 1, 'YYSZ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'QZJY', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_QTF', 'QZJY', 10, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'QZJY', 8, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'QZJY', 9, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'QZJY', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'QZJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'QZJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'QZJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'QZJY', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'QZJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'ZQMK', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'ZQMK', 8, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'ZQMK', 9, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'ZQMK', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'ZQMK', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'ZQMK', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'ZQMK', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'ZQMK', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'ZQMK', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'ZQJD', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'HGJY_GP', 3, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'HGJY_GP', 4, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZYDJF', 'HGJY_GP', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'HGJY_GP', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHSXF', 'CJJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF', 'CJJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'CJJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'GPJY', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_QTF', 'GPJY', 10, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'GPJY', 8, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'GPJY', 9, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'GPJY', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'GPJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'GPJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'GPJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'GPJY', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'GPJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHSXF', 'YQJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSFWF', 'YQJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'YQJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYYJ', 'QHJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGYJ', 'QHJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGSXF', 'QHJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'QHJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_FXJ', 'JJJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'JJJY', 5, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'JJJY', 6, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'JJJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'JJJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'JJJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YJL_JYJ', 'QQJY', 10, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYYJ', 'QQJY', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGYJ', 'QQJY', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'QQJY', 11, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'QQJY', 9, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'QQJY', 7, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'QQJY', 8, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'QQJY', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'QQJY', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGSXF', 'QQJY', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'QQJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSUF', 'QZXQ', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_ZGF', 'QZXQ', 5, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'QZXQ', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JSOF', 'QZXQ', 3, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYF', 'QZXQ', 4, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'QZXQ', 6, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_GHF', 'CNSS', 1, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_QTF', 'ZQZH', 4, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_BCF', 'ZQZH', 2, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_ZCF', 'ZQZH', 1, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_YHS', 'ZQZH', 3, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YYL_YJBCF', 'TAXS', 10, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_HDZCF', 'TAXS', 9, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_HDSGF', 'TAXS', 8, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_BCFGZC', 'TAXS', 7, 'XSSZ_K')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_WYFGZC', 'TAXS', 5, 'XSSZ_K')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_WYF', 'TAXS', 4, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_BCF', 'TAXS', 6, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_SHFGZC', 'TAXS', 2, 'XSSZ_K')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_SHF', 'TAXS', 1, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_ZCF', 'TAXS', 3, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_SGF', 'CWSS', 2, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_RGF', 'CWSS', 1, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'XSL_SHF', 'CWSS', 3, 'XSSZ_F')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'QTL_HKF', 'ZJHH', 1, 'QTSZ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'YFL_YJ', 'XHJY', 3, 'JJYF_YJ')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JGSXF', 'XHJY', 2, 'JJYF_FY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'ZQL_JYSXF', 'XHJY', 1, 'JJYF_FY')")
				//AddByLiyongjun 20180402 STORY #51626 【ETF产品】南方、国泰、鹏华、易方达、嘉实ETF产品整合，优化
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'QTL_QTF', 'ETFXS', 1, 'QTFY') ")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'QTL_ZCF', 'ETFXS', 2, 'QTFY')")
				.addSql("insert into T_S_DF_FEE_RELA (C_IDEN, C_FEE_CODE, C_TD_TYPE, N_ORDER, C_IE_CODE)values (SEQU_S_DF_FEE_RELA.Nextval, 'QTL_QTF', 'ETFXS_SUB', 1, 'QTFY')")
				.endScript();
	}
	
	/**
	 * 核算元素字典表
	 * @param builder
	 */
	private void buildHSYSZD(ScriptBuilder builder){
		//T_S_DAE_ELEM_DETAIL
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'QH', 'SEC', 'SEC_VAR', 'MKT', 'DTA', ' ', ' ', ' ', ' ', ' ')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('GYBD', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('HBZC_CK', 'SEC_VAR', 'CK', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('HBZC_CK', 'SEC_VAR', 'CK_DQ', 'ORG', 'SEC_DUR', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('HBZC_CK', 'SEC_VAR', 'CK_TZ', 'ORG', 'SEC_DUR', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('HBZJ', ' ', ' ', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'QH', 'SEC_VAR', 'MKT', 'DTA', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JYFY', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JZZB', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JZZB', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('JZZB', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZJ', ' ', ' ', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'CJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'CK', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'CK', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'CK_DQ', 'ORG', 'SEC_DUR', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'CK_TZ', 'ORG', 'SEC_DUR', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'HG', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'PJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXSR_ZQ', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXZC_ZQ', 'SEC_VAR', 'CJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXZC_ZQ', 'SEC_VAR', 'CK', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('LXZC_ZQ', 'SEC_VAR', 'HG', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'QQ', 'SEC_VAR', 'MKT', 'DTA', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_CJSR', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_HLSR', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_YZJ', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_YZJ', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('TZSY_YZJ', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFK_FY_YJ', ' ', ' ', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFK_SF_ZQ_WSX', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFK_SF_ZQ_WSX', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFK_SF_ZQ_YSX', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFK_SF_ZQ_YSX', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFLX_ZQ', 'SEC_VAR', 'CJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFLX_ZQ', 'SEC_VAR', 'CK', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YFLX_ZQ', 'SEC_VAR', 'HG', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSK_GL', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSK_GL', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSK_GL', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSK_GL', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZJ', ' ', ' ', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'CJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'CK', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'CK', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'CK_DQ', 'ORG', 'SEC_DUR', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("delete from T_S_DAE_ELEM_DETAIL where C_DAI_CODE = 'YSLX_ZQ' and C_DAE_CODE = 'SEC_VAR' and C_DAE_CODE_SUB = 'CK_TZ'")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'CK_TZ', 'ORG', 'SEC_DUR', 'PI_FQCY_FI', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'HG', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'PJ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YSLX_ZQ', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YYSF_ZQ_WSX', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YYSF_ZQ_WSX', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YYSF_ZQ_YSX', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('YYSF_ZQ_YSX', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZBGJ', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZBGJ', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZBGJ', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZBGJ', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZBGJ', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZCJZSS', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZCJZSS', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZCJZSS', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZCJZSS', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQQSK_SQK', 'MKT', 'COTC', 'ISSUE_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQQSK_SQK', 'SEC_VAR', ' ', 'DT', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_CB', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'QZ', 'WART_TYPE', 'EXER_MODE', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'XH_JQ', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'XH_SP', 'SEC', 'SEC_VAR', 'MKT', ' ', ' ', ' ', ' ', ' ', 'PUB')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_GYBD', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_YZJ', 'SEC_VAR', 'JJ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_YZJ', 'SEC_VAR', 'LC', 'INVEST_MODE', 'ORG', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				.addSql("insert into T_S_DAE_ELEM_DETAIL (C_DAI_CODE, C_DAE_CODE, C_DAE_CODE_SUB, C_DAE_CODE1, C_DAE_CODE2, C_DAE_CODE3, C_DAE_CODE4, C_DAE_CODE5, C_DAE_CODE6, C_DAE_CODE7, C_DAE_CODE8, C_LOAD_MODE)values ('ZQTZ_YZJ', 'SEC_VAR', 'ZQ', 'INVEST_MODE', ' ', ' ', ' ', ' ', ' ', ' ', ' ', 'KM')")
				// 本段SQL语句结束符
				.endScript();
	}
	
	/**
	 * 核算元素
	 * @param builder
	 */
	private void buildHSYS(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('ACC_TYPE', '账户类型', 'pubvocabulary-ACC_TYPE', 'N_VAR_ACC')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('CA', '现金账户', 'cashaccount', 'N_CODE_ACC')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('DC', '交易币种', 'currey', 'N_CURY')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('DS', '销售类型', 'dttdmode', 'N_VAR_DS')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('DT', '交易类型', 'dttdmode', 'N_VAR_DT')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('DTA', '交易属性', 'dtatdattr', 'N_CODE_DTA')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('FEE', '费用代码', 'feeBreed', 'N_CODE_FEE')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('FEE_TYPE', '费用品种', 'pubvocabulary-FEE_TYPE', 'N_VAR_FEE')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('INVEST_CLS', '投资分类', 'pubvocabulary-IVT_CLS', 'N_VAR_INV')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('ISSUE_MODE', '发行方式', 'pubvocabulary-ISSUE_MODE', 'N_ISSUE')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('MKT', '交易市场', 'exchange', 'N_MKT')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('NET', '网点代码', 'taselnet', 'N_CODE_NET')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('PORT_CLS', '分级组合', 'pubvocabulary-PORT_CLS', 'N_CODE_PTCLS')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('SEC', '证券内码', 'sec', 'N_CODE_SEC')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('SEC_VAR', '证券品种', 'seccategory', 'N_VAR_SEC')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('TD_CHAN', '交易渠道', 'tradeSeat', 'N_CODE_CHAN')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('VAR_DUR', '期限', 'pubvocabulary-VAR_DUR', 'N_CODE_DUR')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('EXER_MODE', '行权方式', 'pubvocabulary-EXE_STYLE', ' ')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('INVEST_MODE', '投资方式', 'pubvocabulary-INV', ' ')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('ORG', '机构名称', 'pubvocabulary-ORG_TYPE', ' ')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('SEC_DUR', '品种期限', 'pubvocabulary-SEC_DUR', ' ')")
				.addSql("insert into T_S_DAE_ELEM (C_DAE_CODE, C_DAE_NAME, C_DS_TYPE, C_DAI_FIELD) values ('WART_TYPE', '权证类型', 'pubvocabulary-QQ_QZ_TYPE', ' ')")
				.endScript();
	}
	
	/**
	 * 核算项目
	 * @param builder
	 */
	private void buildHSXM(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('BNLR', '本年利润', 'KC_QY', -1, 4030, 0, 'CACH', 'BNLR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('BNLR_WSX', '本年利润_未实现', 'KC_QY', -1, 4032, 0, 'CACH', 'BNLR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('BNLR_YSX', '本年利润_已实现', 'KC_QY', -1, 4031, 0, 'CACH', 'BNLR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('DTFY', '待摊费用', 'KC_ZC', 1, 1070, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('DYSY', '递延收益', 'KC_FZ', -1, 2110, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('GYBD', '公允价值变动损益', 'KC_SY', -1, 6010, 0, 'SEC', 'GYBD')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('GYBD_KTTD', '公允价值变动损益_可退替代', 'KC_SY', -1, 6011, 0, 'CACH', 'GYBD')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBFZ_CJ', '货币负债_拆借', 'KC_FZ', -1, 2010, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBFZ_CK', '货币负债_存放', 'KC_FZ', -1, 2011, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBFZ_RZ', '货币负债_融资', 'KC_FZ', -1, 2012, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBZC', '货币资产', 'KC_ZC', 1, 1000, 0, 'CACH', 'HBZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBZC_CJ', '货币资产_拆借', 'KC_ZC', 1, 1003, 0, 'CACH_SEC', 'HBZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBZC_CK', '货币资产_存放', 'KC_ZC', 1, 1002, 0, 'CACH_SEC', 'HBZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBZJ', '货币资产_资金', 'KC_ZC', 1, 1001, 0, 'CACH', 'HBZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HDSY', '汇兑损益', 'KC_SY', -1, 6030, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HZZB', '坏账准备', 'KC_ZC', 1, 1090, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('JYFY', '交易费用', 'KC_SY', 1, 6060, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('JZZB', '减值准备', 'KC_ZC', 1, 1100, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP', '利润分配', 'KC_QY', -1, 4040, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_WFP', '利润分配_未分配利润', 'KC_QY', -1, 4042, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_WFP_WSX', '利润分配_未分配利润_未实现', 'KC_QY', -1, 4044, 0, 'CACH', 'LRFP_WFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_WFP_YSX', '利润分配_未分配利润_已实现', 'KC_QY', -1, 4043, 0, 'CACH', 'LRFP_WFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_YFLR', '利润分配_应付利润', 'KC_QY', -1, 4041, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR', '利息收入', 'KC_SY', -1, 6000, 0, 'CACH', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR_TA', '利息收入_销售', 'KC_SY', -1, 6003, 0, 'CACH_TA', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR_ZJ', '利息收入_资金', 'KC_SY', -1, 6002, 0, 'CACH', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR_ZQ', '利息收入_证券', 'KC_SY', -1, 6001, 0, 'SEC', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXZC', '利息支出', 'KC_SY', 1, 6050, 0, 'CACH_FEE', 'LXZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXZC_RQ', '利息支出_融券', 'KC_SY', 1, 6053, 0, 'SEC', 'LXZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXZC_RZ', '利息支出_融资', 'KC_SY', 1, 6054, 0, 'CACH', 'LXZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXZC_ZJ', '利息支出_资金', 'KC_SY', 1, 6052, 0, 'CACH', 'LXZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXZC_ZQ', '利息支出_证券', 'KC_SY', 1, 6051, 0, 'SEC', 'LXZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('MCHGJRZC', '卖出回购金融资产款', 'KC_FZ', -1, 2000, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('MRFSJRZC', '买入返售金融资产', 'KC_ZC', 1, 1080, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('NDSYTZ', '以前年度损益调整', 'KC_SY', 1, 6110, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('QTFY', '其他费用', 'KC_SY', 1, 6080, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('QTSR', '其他收入', 'KC_SY', -1, 6040, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SSZB', '实收资本', 'KC_QY', -1, 4000, 1, 'CACH', 'SSZB')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SSZB_FETZ', '实收资本_份额调整', 'KC_QY', -1, 4001, 0, 'CACH', 'SSZB')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SSZB_ZYZJ', '实收资本_自有资金', 'KC_QY', -1, 4002, 0, 'CACH', 'SSZB')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SYPZJ', '损益平准金', 'KC_QY', -1, 4020, 0, 'CACH', 'SYPZJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SYPZJ_WSX', '损益平准金_未实现', 'KC_QY', -1, 4022, 0, 'CACH', 'SYPZJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('SYPZJ_YSX', '损益平准金_已实现', 'KC_QY', -1, 4021, 0, 'CACH', 'SYPZJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('TZSY', '投资收益', 'KC_SY', -1, 6020, 0, 'SEC', 'TZSY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('TZSY_CJSR', '投资收益_差价收入', 'KC_SY', -1, 6021, 0, 'SEC', 'TZSY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('TZSY_HLSR', '投资收益_红利收入', 'KC_SY', -1, 6022, 0, 'SEC', 'TZSY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('TZSY_TDSY', '投资收益_替代损益', 'KC_SY', -1, 6023, 0, 'SEC', 'TZSY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_FY', '应付交易费用', 'KC_FZ', -1, 2060, 0, 'CACH', 'YFK_FY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_FY_JY', '应付交易费用_费用', 'KC_FZ', -1, 2061, 0, 'CACH_FEE', 'YFK_FY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_FY_YJ', '应付交易费用_佣金', 'KC_FZ', -1, 2062, 0, 'CACH_CHAN', 'YFK_FY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_LR', '应付利润', 'KC_FZ', -1, 2080, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_QTK', '其他应付款', 'KC_FZ', -1, 2030, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SF', '应付税费', 'KC_FZ', -1, 2100, 0, 'CACH', 'YFK_SF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SF_ZJ', '应付税费_资金', 'KC_FZ', -1, 2101, 0, 'CACH', 'YFK_SF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SF_ZQ_WSX', '应付税费_证券_未实现', 'KC_FZ', -1, 2103, 0, 'CACH_SEC', 'YFK_SF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SF_ZQ_YSX', '应付税费_证券_已实现', 'KC_FZ', -1, 2102, 0, 'CACH_SEC', 'YFK_SF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SHF', '应付赎回费', 'KC_FZ', -1, 2050, 0, 'CACH_TA', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SHK', '应付赎回款', 'KC_FZ', -1, 2040, 0, 'CACH_TA', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_YYF', '应付运营费', 'KC_FZ', -1, 2070, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX', '应付利息', 'KC_FZ', -1, 2020, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_RQ', '应付利息_融券', 'KC_FZ', -1, 2024, 0, 'CACH_SEC', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_RZ', '应付利息_融资', 'KC_FZ', -1, 2023, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_ZJ', '应付利息_资金', 'KC_FZ', -1, 2022, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_ZQ', '应付利息_证券', 'KC_FZ', -1, 2021, 0, 'CACH_SEC', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSGJ', '衍生工具', 'KC_GT', 1, 3020, 0, 'SEC', 'YSGJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSGJ_CB', '衍生工具_成本', 'KC_GT', 1, 3021, 1, 'SEC', 'YSGJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSGJ_CDCB', '衍生工具_冲抵成本', 'KC_GT', 1, 3022, 1, 'SEC', 'YSGJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSGJ_GYBD', '衍生工具_公允价值', 'KC_GT', 1, 3023, 0, 'SEC', 'YSGJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSK_GL', '应收股利', 'KC_ZC', 1, 1030, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSK_QTK', '其他应收款', 'KC_ZC', 1, 1060, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSK_SGK', '应收申购款', 'KC_ZC', 1, 1040, 0, 'CACH_TA', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX', '应收利息', 'KC_ZC', 1, 1020, 0, 'CACH', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_TA', '应收利息_销售', 'KC_ZC', 1, 1023, 0, 'CACH_TA', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_ZJ', '应收利息_资金', 'KC_ZC', 1, 1021, 0, 'CACH_SEC', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_ZQ', '应收利息_证券', 'KC_ZC', 1, 1022, 0, 'CACH', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSPJ', '应收票据', 'KC_ZC', 1, 1050, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YTFY', '预提费用', 'KC_FZ', -1, 2090, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYFY', '运营费用', 'KC_SY', 1, 6070, 0, 'CACH_FEE', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYSF', '营业税费', 'KC_SY', 1, 6090, 0, 'CACH', 'YYSF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYSF_ZJ', '营业税费_资金', 'KC_SY', 1, 6091, 0, 'CACH', 'YYSF')")
				//edit by zhoushuhang 20210713 BUG #375228 增值税业务相关【核算项目】脚本，空库升级上来，核算项目名称展示不正确
				/*.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYSF_ZQ_WSX', '营业税费_证券_未实现', 'KC_SY', 1, 6093, 0, 'CACH_SEC', 'YYSF')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYSF_ZQ_YSX', '营业税费_证券_已实现', 'KC_SY', 1, 6092, 0, 'CACH_SEC', 'YYSF')")*/
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZBGJ', '资本公积', 'KC_QY', -1, 4010, 0, 'SEC', 'SYPZJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZCJZSS', '资产减值损失', 'KC_SY', 1, 6100, 0, 'CACH_SEC', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQFZ', '证券负债', 'KC_FZ', -1, 2001, 1, 'SEC', 'ZQFZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQFZ_CB', '证券负债_成本', 'KC_FZ', -1, 2002, 1, 'SEC', 'ZQFZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQFZ_GYBD', '证券负债_公允价值', 'KC_FZ', -1, 2004, 0, 'SEC', 'ZQFZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQFZ_YZJ', '证券负债_溢折价', 'KC_FZ', -1, 2003, 0, 'SEC', 'ZQFZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK', '证券清算款', 'KC_GT', 1, 3000, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_KTTD', '证券清算款_可退替代', 'KC_GT', 1, 3004, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_KTTD_GYBD', '证券清算款_可退替代估增', 'KC_GT', 1, 3005, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_SQK', '证券清算款_非T+1', 'KC_GT', 1, 3001, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_XJCE', '证券清算款_现金差额', 'KC_GT', 1, 3002, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_XJTD', '证券清算款_现金替代', 'KC_GT', 1, 3003, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_YTTD', '证券清算款_应退退补款', 'KC_GT', 1, 3006, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQTZ', '证券投资', 'KC_ZC', 1, 1010, 1, 'SEC', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQTZ_CB', '证券投资_成本', 'KC_ZC', 1, 1011, 1, 'SEC', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQTZ_GYBD', '证券投资_公允价值', 'KC_ZC', 1, 1013, 0, 'SEC', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQTZ_YZJ', '证券投资_溢折价', 'KC_ZC', 1, 1012, 0, 'SEC', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_XJCE_YG', '证券清算款_预估现金差额', 'KC_GT', 1, 3007, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_XJTD_KS', '证券清算款_现金替代_跨市', 'KC_GT', 1, 3008, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_YTTD_KS', '证券清算款_可收退补款', 'KC_GT', 1, 3009, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFK_SGK', '应付申购款', 'KC_FZ', -1, 2051, 0, 'CACH_TA', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_ZRQ', '应付利息_转融券', 'KC_FZ', -1, 2027, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_ZRT', '应付利息_转融通', 'KC_FZ', -1, 2025, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YFLX_ZRZ', '应付利息_转融资', 'KC_FZ', -1, 2026, 0, 'CACH', 'YFLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('ZQQSK_XJTD_YG', '证券清算款_预估现金替代', 'KC_GT', 1, 3010, 0, 'CACH', 'ZQQSK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('PTKM', '普通科目', ' ', 0, 1, 0, ' ', ' ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('CCZJ', '拆出资金', 'KC_ZC', 1, 1302, 0, 'CACH', 'CCZJ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('HBZC_LC', '货币资产_理财', 'KC_ZC', 1, 2111, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_TQFDYYGJP', '利润分配_提取法定盈余公积', 'KC_QY', -1, 5110, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_TQRYYYGJP', '利润分配_提取任意盈余公积', 'KC_QY', -1, 5110, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_YFTDXTJHLY', '利润分配_应付特定信托计划利益', 'KC_QY', 0, 4041, 1, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LRFP_YFXJGLLR', '利润分配_应付现金股利或利润', 'KC_QY', -1, 5110, 0, 'CACH', 'LRFP')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('QTQYBD', '其他权益变动', 'KC_QY', -1, 4110, 0, 'CACH', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YINGFZK', '应付账款', 'KC_FZ', -1, 2202, 0, 'CACH_FEE', 'YSZK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YINGSZK', '应收账款', 'KC_ZC', 1, 1122, 0, 'CACH', 'YSZK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YJFZ', '预计负债', 'KC_FZ', -1, 2801, 0, 'CACH', 'YJFZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YJLX', '应计利息', 'KC_ZC', 1, 4110, 0, 'CACH', 'ZQTZ')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YUFZK', '预付账款', 'KC_ZC', 1, 1123, 0, 'CACH_FEE', 'YFZK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YUSZK', '预收账款', 'KC_FZ', -1, 2203, 0, 'CACH', 'YSZK')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYGJ', '盈余公积', 'KC_QY', -1, 5110, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYGJ_FD', '法定盈余公积', 'KC_QY', -1, 5110, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYGJ_RY', '任意盈余公积', 'KC_QY', -1, 5110, 0, 'CACH', null)")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YYWZC', '营业外支出', 'KC_SY', 1, 6711, 0, 'CACH_FEE', 'YYWZC')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('TZSY_YZJ', '投资收益_溢折价', 'KC_SY', -1, 6024, 0, 'SEC', 'TZSY')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR_ZRQ', '利息收入_转融券', 'KC_SY', -1, 6006, 0, 'SEC', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('LXSR_ZRZ', '利息收入_转融资', 'KC_SY', -1, 6005, 0, 'SEC', 'LXSR')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_ZRQ', '应收利息_转融券', 'KC_ZC', 1, 1026, 0, 'CACH', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_ZRT', '应收利息_转融通', 'KC_ZC', 1, 1024, 0, 'CACH', 'YSLX')")
				.addSql("insert into T_S_DAI_ITEM (C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE) values ('YSLX_ZRZ', '应收利息_转融资', 'KC_ZC', 1, 1025, 0, 'CACH', 'YSLX')")
				//add by gongyue 20170503
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('GYBD_XXS', '公允价值变动损益_销项税', 'KC_SY', -1, 6012, 0, 'SEC', 'GYBD')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('LXSR_ZJ_XXS', '利息收入_资金_销项税', 'KC_SY', -1, 6005, 0, 'CACH', 'LXSR_ZJ')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('LXSR_ZQ_XXS', '利息收入_证券_销项税', 'KC_SY', -1, 6004, 0, 'SEC', 'LXSR_ZQ')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('LXSR_ZRQ_XXS', '利息收入_转融券_销项税', 'KC_SY', -1, 6007, 0, 'SEC', 'LXSR_ZRQ')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('LXSR_ZRZ_XXS', '利息收入_转融资_销项税', 'KC_SY', -1, 6006, 0, 'SEC', 'LXSR_ZRZ')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('QTSR_TDSY', '其它收入_替代损益', 'KC_SY', -1, 6041, 0, 'CACH', 'QTSR')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('TZSY_CJSR_XXS', '投资收益_差价收入_销项税', 'KC_SY', -1, 6024, 0, 'SEC', 'TZSY_CJSR')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('TZSY_HLSR_XXS', '投资收益_红利收入_销项税', 'KC_SY', -1, 6025, 0, 'SEC', 'TZSY_HLSR')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YFK_SF_ZJ_XXS', '应付税费_资金_销项税', 'KC_FZ', -1, 2104, 0, 'CACH', 'YFK_SF_ZJ')")
				//edit by zhoushuhang 20210713 BUG #375228 增值税业务相关【核算项目】脚本，空库升级上来，核算项目名称展示不正确
				/*.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YFK_SF_ZQ_WSX_XXS', '应付税费_证券_未实现_销项税', 'KC_FZ', -1, 2106, 0, 'CACH_SEC', 'YFK_SF_ZQ_WSX')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YFK_SF_ZQ_XXS', '应付税费_证券_已实现_销项税', 'KC_FZ', -1, 2105, 0, 'CACH_SEC', 'YFK_SF_ZQ_YSX')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YYSF_ZJ_XXS', '营业税费_资金_销项税', 'KC_SY', 1, 6094, 0, 'CACH', 'YYSF_ZJ')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YYSF_ZQ_WSX_XXS', '营业税费_证券_未实现_销项税', 'KC_SY', 1, 6096, 0, 'CACH_SEC', 'YYSF_ZQ_WSX')")
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('YYSF_ZQ_YSX_XXS', '营业税费_证券_已实现_销项税', 'KC_SY', 1, 6095, 0, 'CACH_SEC', 'YYSF_ZQ_YSX')")*/
				.addSql("insert into T_S_DAI_ITEM(C_DAI_CODE, C_DAI_NAME, C_DV_KM_CLS, N_FUND_WAY, N_ORDER, C_DV_BOOL_TYPE_AM, C_STOCK_CLS, C_DAI_TYPE)VALUES('ZQQSK_YSTB', '证券清算款_应收退补款', 'KC_GT', 1, 3011, 0, 'CACH', 'ZQQSK')")
				.endScript();
	}
	
	/**
	 * 交易属性
	 * @param builder
	 */
	private void buildJYSX(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
			.addVersion("1.21.5.0")
			.addUpdateType(UpdateType.REQUEST)
			.addID("00000")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('BD_SELL', '备兑_卖方', 'YSP', 7)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('FBD_BUY', '非备兑_买方', 'YSP', 5)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('FBD_SELL', '非备兑_卖方', 'YSP', 6)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('HGS', '回购式', 'PJYW', 2)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('JD', '借贷', 'ZQJD', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('MDS', '买断式', 'PJYW', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('MK', '卖空', 'ZQMK', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('PT', '普通', 'GPYW', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TB', '套保', 'YSP_MD', 4)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TB_BUY', '套保_买方', 'YSP', 3)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TB_SELL', '套保_卖方', 'YSP', 4)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TJ_BUY', '投机_买方', 'YSP', 5)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TJ_SELL', '投机_卖方', 'YSP', 6)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TL', '套利', 'YSP_MD', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TL_BUY', '套利_买方', 'YSP', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('TL_SELL', '套利_卖方', 'YSP', 2)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('WT', '委托', 'GPYW', 4)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('XY', '信用', 'GPYW', 5)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('ZB', '指标', 'GPYW', 3)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('ZS', '指数', 'GPYW', 2)")
			.addSql("insert into T_S_DTA_TD_ATTR (C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER) values ('ZRT', '转融通', 'GPYW', 6)")
			//add by gongyue 20170503
			.addSql("insert into T_S_DTA_TD_ATTR(C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER)VALUES('GS', '固收', 'GPYW', 1)")
			.addSql("insert into T_S_DTA_TD_ATTR(C_DTA_CODE, C_DTA_NAME, C_BUSI_TYPE, N_ORDER)VALUES('ZHYJ', '组合移交', 'GPYW', 7)")
			.endScript();
	}
	
	/**
	 * 交易方式
	 * @param builder
	 */
	private void buildJYFS(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_TQSY_MC', '卖出提取收益', 'DJPX', -1, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_TQSY_SH', '赎回提取收益', 'DJPX', 1, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_FHTQSY', '分红收益提取', 'DJPX', 1, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('HGJY', '回购交易', '[root]', 0, 0, 35)")
				// BUG #241892 “交易方式”字段词汇显示不正确 add by mazhongyuan 2018-01-28
//				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('HGJY_NHG_SQ_ZYS', '质押式逆回购首期', 'HGJY', 0, 0, 1)")
//				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('HGJY_ZHG_DQ_ZYS', '质押式正回购到期', 'HGJY', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_BCFH', '补差分红', 'ASSET_EQU', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_CEFH', '超额分红', 'ASSET_EQU', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_CF', '产品拆分', 'ASSET_EQU', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_CL', '单位成本', 'ASSET_EQU', 0, 0, 0)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_FH', '产品分红', 'ASSET_EQU', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_JZFH', '基准分红', 'ASSET_EQU', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_QC', '期初单位', 'ASSET_EQU', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CJJY_CC', '资金拆出', 'CJJY', -1, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CJJY_CR', '资金拆入', 'CJJY', 1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CKTZ_CFTY', '存放同业', 'CKTZ', -1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CKTZ_TYCF', '同业存放', 'CKTZ', 1, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SG', 'ETF申购', 'CNSS', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SGTBK', 'ETF申购退补款', 'CNSS', 1, 0, 16)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SGTD', '申购现金替代', 'CNSS', 1, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SGTD_KS', '申购现金替代_跨市', 'CNSS', 1, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SH', 'ETF赎回', 'CNSS', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SHTBK', 'ETF赎回退补款', 'CNSS', 1, 0, 17)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SHTD', '赎回现金替代', 'CNSS', -1, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SHTD_KS', '赎回现金替代_跨市', 'CNSS', -1, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_TBK', 'ETF退补款', 'CNSS', 1, 0, 15)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_TBK_KS', '可收退补款', 'CNSS', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SG', '申购现金差额', 'CNSS', 1, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SGFK', '申购现金差额_付款', 'CNSS', 1, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SGSK', '申购现金差额_收款', 'CNSS', 1, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SH', '赎回现金差额', 'CNSS', 1, 0, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SHFK', '赎回现金差额_付款', 'CNSS', 1, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_SHSK', '赎回现金差额_收款', 'CNSS', 1, 0, 12)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE_YG', '预估现金差额', 'CNSS', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_RGQR', '认购确认', 'CWSS', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_RGSQ', '认购申请', 'CWSS', -1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SGQR', '申购确认', 'CWSS', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SGSQ', '申购申请', 'CWSS', -1, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SHQR', '赎回确认', 'CWSS', 1, -1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SHSQ', '赎回申请', 'CWSS', 1, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_FHPX', '分红派息', 'DJPX', 1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_HLBS', '红利补税', 'DJPX', -1, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_HLTZ', '红利投资', 'DJPX', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_XJDJ', '现金对价', 'DJPX', 1, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_RG', '认购', 'ETFXS', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SG', '申购', 'ETFXS', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SGTBK', '申购退补款', 'ETFXS', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SGTD', '申购替代', 'ETFXS', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SH', '赎回', 'ETFXS', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SHTBK', '赎回退补款', 'ETFXS', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SHTD', '赎回替代', 'ETFXS', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_SHTD_BX', '赎回替代_必须', 'ETFXS', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ETFXS_XJCE', '现金差额', 'ETFXS', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_BUY', '股票买入', 'GPJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_CQ', '股票抽取', 'GPJY', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_SELL', '股票卖出', 'GPJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_YYSG', '要约收购', 'GPJY', -1, 1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_ZFZQ', '增发中签', 'GPJY', -1, 1, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_ZR', '股票注入', 'GPJY', -1, 1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('HGJY_NHG', '逆回购', 'HGJY', -1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('HGJY_ZHG', '正回购', 'HGJY', 1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('IC_FS', '可供出售类', 'IVT_CLS', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('IC_HM', '持有到期类', 'IVT_CLS', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('IC_SL', '源生贷款类', 'IVT_CLS', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('IC_TD', '交易类', 'IVT_CLS', 0, 0, 0)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_BUY', '理财买入', 'JJJY', -1, 1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_DF', '理财兑付', 'JJJY', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_RG', '场内认购', 'JJJY', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_SELL', '理财卖出', 'JJJY', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_SG', '场内申购', 'JJJY', -1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('JJJY_SH', '场内赎回', 'JJJY', 1, -1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('PJJY_BUY', '票据买入', 'PJJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('PJJY_DF', '票据兑付', 'PJJY', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('PJJY_NHG', '逆回购', 'PJJY', -1, -1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('PJJY_SELL', '票据卖出', 'PJJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('PJJY_ZHG', '正回购', 'PJJY', 1, 1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QHJY_JG', '交割', 'QHJY', 1, -1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QHJY_KC', '开仓', 'QHJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QHJY_PC', '平仓', 'QHJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQJY_DC', '对冲', 'QQJY', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQJY_JG', '交割', 'QQJY', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQJY_KC', '开仓', 'QQJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQJY_PC', '平仓', 'QQJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQJY_TC', '调仓', 'QQJY', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQXQ_FQ', '放弃行权', 'QQXQ', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QQXQ_XQ', '期权行权', 'QQXQ', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QZJY_BUY', '权证买入', 'QZJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QZJY_SELL', '权证卖出', 'QZJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QZXQ_FQXQ', '放弃行权', 'QZXQ', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('QZXQ_QZXQ', '权证行权', 'QZXQ', 1, -1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_DBHC', '担保划出', 'RZRQ', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_DBHR', '担保划入', 'RZRQ', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_MC', '担保卖出', 'RZRQ', 1, -1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_MR', '担保买入', 'RZRQ', 1, -1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RQHQ', '融券还券', 'RZRQ', -1, 1, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RQHQ_CS', '融券还券_产生', 'RZRQ', -1, 1, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RQHQ_MQ', '融券还券_买券', 'RZRQ', 1, -1, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RQHQ_YQ', '融券还券_余券', 'RZRQ', -1, 1, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RQMC', '融券卖出', 'RZRQ', -1, 1, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RZMC', '融资还款_卖券', 'RZRQ', -1, 1, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_RZMR', '融资买入', 'RZRQ', -1, 1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_DFSY', '兑付收益', 'TAXS', 0, 0, 14)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_FECF', '份额拆分', 'TAXS', 0, 1, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_FETZ', '份额调整', 'TAXS', 0, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_FH', '分红', 'TAXS', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_FHTZ', '分投', 'TAXS', 1, 1, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_FXZBJ', '风险准备金', 'TAXS', 0, 0, 12)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_JJCL', '成立', 'TAXS', 1, 1, 0)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_LXZFE', '利息转份额', 'TAXS', 1, 1, 15)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_QJ', '强减', 'TAXS', -1, -1, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_QJ_FE', '强减_份额', 'TAXS', -1, -1, 13)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_QZ', '强增', 'TAXS', 1, 1, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_SG', '申购', 'TAXS', 1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_SH', '赎回', 'TAXS', -1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_ZC', '转出', 'TAXS', -1, -1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_ZR', '转入', 'TAXS', 1, 1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_ZSY', '总收益', 'TAXS', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XGSG_QR', '新股确认', 'XGSG', -1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XGSG_SQ', '新股申请', 'XGSG', -1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY', '现货交易', '[root]', 0, 0, 28)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY_BUY', '买入', 'XHJY', -1, 1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY_JG', '交割', 'XHJY', 1, -1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY_KC', '开仓', 'XHJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY_PC', '平仓', 'XHJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XHJY_SELL', '卖出', 'XHJY', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XZSG_QR', '新债确认', 'XZSG', -1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('XZSG_SQ', '新债申请', 'XZSG', -1, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('YQJY_BUY', '远期买入', 'YQJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('YQJY_SELL', '远期卖出', 'YQJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJD_GHZQ', '归还证券_卖空', 'ZQJD', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJD_JCZQ', '借出证券_卖空', 'ZQJD', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJD_JRZQ', '借入证券_卖空', 'ZQJD', 0, 0, 0)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJD_SHZQ', '收回证券_卖空', 'ZQJD', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_BUY', '债券买入', 'ZQJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_CQ', '债券抽取', 'ZQJY', 1, -1, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_CX', '债券承销', 'ZQJY', -1, 1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_DF', '债券兑付', 'ZQJY', 1, -1, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_FX', '债券分销', 'ZQJY', 1, -1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_HBFX', '债券还本付息', 'ZQJY', 1, -1, 15)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_HS', '债券回售', 'ZQJY', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_PZZQ', '配债中签', 'ZQJY', -1, 1, 14)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_SELL', '债券卖出', 'ZQJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_SH', '债券赎回', 'ZQJY', 1, -1, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_YMC', '债券预卖出', 'ZQJY', 1, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_YMR', '债券预买入', 'ZQJY', -1, 0, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_YMR_JG', '债券预买入_交割', 'ZQJY', 1, 0, 12)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_YMR_JS', '债券预买入_结算', 'ZQJY', 1, 0, 13)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQJY_ZR', '债券注入', 'ZQJY', -1, 1, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQMK_BUY', '证券买入_卖空', 'ZQMK', 1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQMK_SELL', '证券卖出_卖空', 'ZQMK', -1, 1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_CF', '拆分', 'ZQSP', 0, 1, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_GFDJ', '股份对价', 'ZQSP', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_JJFC', '基金分拆', 'ZQSP', 0, 1, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_JJHB', '基金合并', 'ZQSP', 0, 1, 12)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_KFLZSP', '可分离债送配', 'ZQSP', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_PG', '配股', 'ZQSP', -1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_PZ', '配债', 'ZQSP', -1, 1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_QZSP', '权证送配', 'ZQSP', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_SG', '送股', 'ZQSP', 0, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_SGRQ', '送股_融券', 'ZQSP', 0, 1, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_SYJZ', '收益结转', 'ZQSP', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_CFL', '投资重分类', 'ZQZH', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_HG', '换股', 'ZQZH', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_JJZH', '基金转换', 'ZQZH', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_ZQDH', '债券调换', 'ZQZH', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_ZSTZ', '指数调整', 'ZQZH', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_ZTG', '转托管', 'ZQZH', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_ZZG', '债转股', 'ZQZH', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_SHTD_YG', '预估赎回现金替代', 'CNSS', -1, 0, 21)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_TBK_FK', 'ETF退补款_付款', 'CNSS', 1, 0, 17)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_TBK_SK', 'ETF退补款_收款', 'CNSS', 1, 0, 16)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_TQ', '提取', 'CWSS', 1, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CNSS_XJCE', '现金差额', 'CNSS', 1, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_XJSG_GGT', '港股通_现金收购', 'GPJY', 1, -1, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_GFSG_GGT', '港股通_股份收购', 'ZQZH', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_XJSG_GGT', '港股通_送配_现金收购', 'ZQSP', 0, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_GFSG_GGT', '港股通_送配_股份收购', 'ZQSP', 0, 1, 12)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_XJGFSG_GGT', '港股通_送配_现金加股份收购', 'ZQSP', 0, 1, 13)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_FCHB_GGT', '港股通_分拆合并', 'ZQSP', 0, 1, 14)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_QZZH', '权证转换', 'ZQZH', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('FZJY', '负债交易', '[root]', 0, 0, 30)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('FZJY_SG', '申购', 'FZJY', 1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('FZJY_SH', '赎回', 'FZJY', -1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_YYSG_BUY', '要约收购_买入', 'GPJY', 1, -1, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('LDXJY', '流动性资产交易', '[root]', 0, 0, 34)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('LDXJY_BUY', '流动性资产买入', 'LDXJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('LDXJY_SELL', '流动性资产卖出', 'LDXJY', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_DBHC', '担保划出', 'RZRQ', 1, -1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('RZRQ_DBHR', '担保划入', 'RZRQ', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_YJBC', '业绩报酬', 'TAXS', -1, -1, 16)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY', '资产交易', '[root]', 0, 0, 32)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_BUY', '资产购买', 'ZCJY', -1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_SELL', '资产售出', 'ZCJY', 1, -1, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_WTDK', '委托贷款', 'ZCJY', 1, 1, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_ZR', '资产转让', 'ZCJY', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_ZR_HG', '回购业务', 'ZCJY_ZR', 1, -1, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZCJY_ZR_SYQ', '收益权转让', 'ZCJY_ZR', -1, -1, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_CF_GRLX', '拆分_认购利息', 'ZQSP', 0, 1, 16)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_FBZLOF', '封闭分级合并转LOF', 'ZQSP', 0, 1, 15)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQZH_FBZLOF', '封闭分级合并转LOF', 'ZQZH', 0, 1, 15)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('AE_WFSY', '预期每万份收益', 'ASSET_EQU', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_FJYGH', '非交易过户', 'ZQSP', 0, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('DJPX_DF', '债券兑付', 'DJPX', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZQSP_GFTZ_GGT', '港股通_股份调整', 'ZQSP', 0, 0, 17)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_HS', '股票回售', 'GPJY', 1, -1, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('GPJY_SH', '股票赎回', 'GPJY', 1, -1, 10)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZJDQ', '融出资金_到期', 'ZRTJY', 0, 0, 2)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZJSQ', '融出资金_首期', 'ZRTJY', 0, 0, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQBFDQ', '融出证券_部分展期到期', 'ZRTJY', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQBFJE', '融出证券_部分展期了结', 'ZRTJY', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQDQ', '融出证券_到期', 'ZRTJY', 0, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQQBDQ', '融出证券_全部展期到期', 'ZRTJY', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQQBJE', '融出证券_全部展期了结', 'ZRTJY', 0, 0, 6)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQSH', '融出证券_索还', 'ZRTJY', 0, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('ZRT_ZQSQ', '融出证券_首期', 'ZRTJY', 0, 0, 3)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SGXJCE', '申购现金差额', 'CWSS', 1, 0, 4)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('CWSS_SHXJCE', '赎回现金差额', 'CWSS', 1, 0, 5)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_CDQZ', '侧袋强增', 'TAXS', 1, 1, 1)")
				.addSql("insert into T_S_DT_TD_MODE (C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER) values ('TAXS_CDQJ', '侧袋强减', 'TAXS', -1, -1, 2)")
				//add by gongyue 20170503
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('AE_FECF', '份额拆分', 'ASSET_EQU', 0, 0, 8)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('ETFXS_CL', '成立', 'ETFXS', 0, 0, 10)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('ETFXS_SGTD_KS', '申购替代_跨市', 'ETFXS', 0, 0, 11)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('ETFXS_SHTD_KS', '赎回替代_跨市', 'ETFXS', 0, 0, 12)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('ETFXS_TBKCJ', 'ETF冲减退补款', 'ETFXS', 0, 0, 13)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('GPJY_JZZB', '股票减值准备', 'GPJY', 0, 0, 9)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('JJJY_JZZB', '基金减值准备', 'JJJY', 0, 0, 7)")
				.addSql("insert into T_S_DT_TD_MODE(C_DT_CODE, C_DT_NAME, C_BUSI_TYPE, N_FUND_WAY, N_CAPI_WAY, N_ORDER)VALUES('ZQJY_JZZB', '债券减值准备', 'ZQJY', 0, 0, 16)")
				.endScript();
	}
	
	/**
	 * 资产类型
	 * @param builder
	 */
	private void buildZCLX(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("00000")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_ZQTZJJ', '证券投资基金', 1, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_ZCGLJH', '资产管理计划', 2, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_QDII', 'QDII证券基金', 3, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_QYNJJJ', '企业年金基金', 7, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_SBJJ', '社会保障基金', 5, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_XTZCCP', '信托计划产品', 4, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_BXZCCP', '保险资产产品', 6, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_RQFII', 'RQFII证券基金', 8, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('CLS_ETF', 'ETF型', 3, '[root]', 'CLS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('CLS_LJ', '联接型', 4, '[root]', 'CLS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('CLS_PT', '普通型', 1, '[root]', 'CLS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('CLS_HB', '货币型', 2, '[root]', 'CLS')")
		.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_YHZCCP', '银行资产产品', 9, '[root]', 'ASS')")
		//.addSql("insert into T_S_DAT_ASS_TYPE (C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE) values ('ASS_BSZB', '博时资本', 10, '[root]', 'ASS')")
		//add by gongyue 20170503
		.addSql("insert into T_S_DAT_ASS_TYPE(C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE)VALUES('ASS_QDIIZH', 'QDII专户理财', 11, '[root]', 'ASS')")
		.addSql("insert into T_S_DAT_ASS_TYPE(C_DAT_CODE, C_DAT_NAME, N_ORDER, C_DAT_CODE_P, C_DAT_TYPE)VALUES('ASS_RQDIIZH', 'RQDII专户', 12, '[root]', 'ASS')")
		.endScript();
	}
	
	/**
	 * 证券品种属性
	 */
	private void buildZQPZSX(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("00000")
		//.addSql("delete from t_s_da_sec_var a where a.c_sec_var_code in ('CK','CK_DQ','CK_HQ','CK_JG','CK_TZ','CK_XY','CK_QT','CK_ZRT','CK_XTBZJJ','DK','DK_DQ','DK_HQ','DK_JG','DK_QT','DK_TZ','DK_XY','PJ','PJ_SY','PJ_YH','CJ','CJ_TY','GP','GP_GP','GP_CT','GP_GP_CYB','GP_GQ','GP_GQ_A','GP_GQ_B','GP_GQ_YXG','GP_FDCXT','GP_YXG','ZQ','ZQ_CJZ','ZQ_CJZ_ZC','ZQ_CJZ_YH','ZQ_CJZ_YHZW','ZQ_CJZ_BXZW','ZQ_GZXQ','ZQ_GZXQ_PZS','ZQ_DFZFZ','ZQ_DFZFZ_ZFZCZQ','ZQ_QYGSZ','ZQ_QYZ','ZQ_GSZ','ZQ_SMZQ','ZQ_SMZQ_BGCZ','ZQ_SMZQ_KJH','ZQ_KZZ','ZQ_KZZ_SC','ZQ_KJHZ','ZQ_KFLZ','ZQ_DQRZZ','ZQ_DQRZZ_C','ZQ_JHQYZ','ZQ_JRZ','ZQ_JRZ_ZC','ZQ_JRZ_YH','ZQ_JRZ_FYH','ZQ_YHPJ','ZQ_JHPJ','ZQ_ZQPJ','ZQ_ZFZCJGZ','ZQ_GJJGZ','ZQ_ZCZQH','ZQ_ZQJH','ZQ_TYCD','ZQ_DXGJ','ZQ_QT','ZQ_QD','ZQ_GKZ','ZQ_PPN','ZQ_CT','HG','HG_ZYS','HG_ZYS_XY_ZQ','HG_ZYS_GZXQ','HG_ZYS_QYZ','HG_ZYS_GP','HG_ZYS_JTL','HG_MDS','HG_MDS_GZXQ','HG_MDS_QYZ','HG_YDGH','JJ','JJ_FBS','JJ_FBS_REITs','JJ_KFS','JJ_KFS_ETF','JJ_KFS_ETF_ZQ','JJ_KFS_LOF','JJ_KFS_LOF_WSS','JJ_KFS_HBX','JJ_KFS_HBX_SH','JJ_KFS_HBX_JY','JJ_KFS_QDII','JJ_KFS_REITs','JJ_KFS_LOF_A','JJ_KFS_LOF_DK','LC','LC_BX','LC_BX_ZG','LC_CF','LC_DX','LC_FFY','LC_PJ','LC_JH','LC_SM','LC_XT','LC_HBX','LC_ZG','LC_YHLC','LC_LC','LC_HP','LC_QY','LC_ZQJH','LC_TZJH','LC_ZX','LC_YLJ','LC_YH','LC_RZYW','LC_JRZC','LC_QQ','LC_PE','LC_QT','LC_JDBT','LC_YXHH','LC_XTYBZJJ','LC_JJGSZHCP','LC_ZG_QHGS','LC_YHHPMD','LC_YHHPSYQZR','LC_SYHPSYQZR','LC_WTZQJYSZKSR','LC_SYQ','LC_SYQ_GP','LC_SYQ_YS','LC_SYQ_XD','LC_SYQ_TD','LC_SYQ_QT','LC_SYQ_PZ','LC_SYHH','LC_SYPZ','LC_SYQ_GQJHHQY','LC_GQ','LC_GQ_FKZ','LC_GQ_KZ','LC_XDTZ','LC_XYZ','LC_DC','LC_DK','LC_WTDK','LC_DK_FWD','QH','QH_GP','QH_GZ','QH_SP','QH_ZQ','QQ','QQ_GP','QQ_ZQ','QQ_ZS','QQ_JJ_ETF','QQ_QH_SP','XH','XH_SP','XH_JQ','XH_YQ','QZ','QZ_QZ','QZ_NX','QZ_PG','HH','HH_LL','HH_SY','HH_SY_GP','HH_SY_QH','HH_SY_ZS','YQ','YQ_GP','YQ_LL','YQ_WH','YQ_ZQ')")
				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK', '存放品种', 'CK', '[root]', 'ENAB', 16000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_DQ', '存放品种_定期', 'CK_DQ', 'CK', 'ENAB', 16001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_HQ', '存放品种_活期', 'CK_HQ', 'CK', 'ENAB', 16002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_JG', '存放品种_结构性', 'CK_JG', 'CK', 'ENAB', 16003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_TZ', '存放品种_通知', 'CK_TZ', 'CK', 'ENAB', 16004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_XY', '存放品种_协议', 'CK_XY', 'CK', 'ENAB', 16005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_QT', '存放品种_其他', 'CK_QT', 'CK', 'ENAB', 16006)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_ZRT', '存放品种_转融通', 'CK_ZRT', 'CK', 'ENAB', 16007)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CK_XTBZJJ', '存放品种_信托保障基金', 'CK_XTBZJJ', 'CK', 'ENAB', 16008)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK', '贷款品种', 'DK', '[root]', 'ENAB', 16100)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_DQ', '贷款品种_定期', 'DK_DQ', 'DK', 'ENAB', 16101)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_HQ', '贷款品种_活期', 'DK_HQ', 'DK', 'ENAB', 16102)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_JG', '贷款品种_结构性', 'DK_JG', 'DK', 'ENAB', 16103)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_QT', '贷款品种_其他', 'DK_QT', 'DK', 'ENAB', 16104)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_TZ', '贷款品种_通知', 'DK_TZ', 'DK', 'ENAB', 16105)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'DK_XY', '贷款品种_协议', 'DK_XY', 'DK', 'ENAB', 16106)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'PJ', '票据品种', 'PJ', '[root]', 'ENAB', 16200)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'PJ_SY', '票据品种_商业', 'PJ_SY', 'PJ', 'ENAB', 16201)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'PJ_YH', '票据品种_银行', 'PJ_YH', 'PJ', 'ENAB', 16202)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CJ', '拆借品种', 'CJ', '[root]', 'ENAB', 16300)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'CJ_TY', '拆借品种_同业', 'CJ_TY', 'CJ', 'ENAB', 16301)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP', '股票品种', 'GP', '[root]', 'ENAB', 10000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GP', '股票品种_股票', 'GP_GP', 'GP', 'ENAB', 10001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_CT', '股票品种_存托', 'GP_CT', 'GP', 'ENAB', 10002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GP_CYB', '股票品种_股票_创业板', 'GP_GP_CYB', 'GP', 'ENAB', 10003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GQ', '股票品种_股权', 'GP_GQ', 'GP', 'ENAB', 10004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GQ_A', '股票品种_股权_两网及A股', 'GP_GQ_A', 'GP', 'ENAB', 10005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GQ_B', '股票品种_股权_两网及B股', 'GP_GQ_B', 'GP', 'ENAB', 10006)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_GQ_YXG', '股票品种_股权_优先股', 'GP_GQ_YXG', 'GP', 'ENAB', 10007)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_FDCXT', '股票品种_房地产信托', 'GP_FDCXT', 'GP', 'ENAB', 10008)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'GP_YXG', '股票品种_优先股', 'GP_YXG', 'GP', 'ENAB', 10009)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ', '债券品种', 'ZQ', '[root]', 'ENAB', 11000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CJZ', '债券品种_次级债', 'ZQ_CJZ', 'ZQ', 'ENAB', 11001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CJZ_ZC', '债券品种_政策性次级债', 'ZQ_CJZ_ZC', 'ZQ', 'ENAB', 11002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CJZ_YH', '债券品种_商业银行次级债', 'ZQ_CJZ_YH', 'ZQ', 'ENAB', 11003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CJZ_YHZW', '债券品种_商业银行次级债务', 'ZQ_CJZ_YHZW', 'ZQ', 'ENAB', 11004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CJZ_BXZW', '债券品种_保险公司次级债务', 'ZQ_CJZ_BXZW', 'ZQ', 'ENAB', 11005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_GZXQ', '债券品种_国债', 'ZQ_GZXQ', 'ZQ', 'ENAB', 11050)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_GZXQ_PZS', '债券品种_凭证式国债', 'ZQ_GZXQ_PZS', 'ZQ', 'ENAB', 11051)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_DFZFZ', '债券品种_地方政府债', 'ZQ_DFZFZ', 'ZQ', 'ENAB', 11052)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_DFZFZ_ZFZCZQ', '债券品种_政府支持债券', 'ZQ_DFZFZ_ZFZCZQ', 'ZQ', 'ENAB', 11053)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_QYGSZ', '债券品种_企业公司债', 'ZQ_QYGSZ', 'ZQ', 'ENAB', 11100)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_QYZ', '债券品种_企业债', 'ZQ_QYZ', 'ZQ', 'ENAB', 11101)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_GSZ', '债券品种_公司债', 'ZQ_GSZ', 'ZQ', 'ENAB', 11102)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_SMZQ', '债券品种_私募债', 'ZQ_SMZQ', 'ZQ', 'ENAB', 11110)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_SMZQ_BGCZ', '债券品种_并购重组私募债', 'ZQ_SMZQ_BGCZ', 'ZQ', 'ENAB', 11111)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_SMZQ_KJH', '债券品种_可交换私募债', 'ZQ_SMZQ_KJH', 'ZQ', 'ENAB', 11112)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_KZZ', '债券品种_可转换债', 'ZQ_KZZ', 'ZQ', 'ENAB', 11120)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_KZZ_SC', '债券品种_双创可转债', 'ZQ_KZZ_SC', 'ZQ', 'ENAB', 11121)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_KJHZ', '债券品种_可交换债', 'ZQ_KJHZ', 'ZQ', 'ENAB', 11122)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_KFLZ', '债券品种_可分离转债', 'ZQ_KFLZ', 'ZQ', 'ENAB', 11123)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_DQRZZ', '债券品种_短期融资券', 'ZQ_DQRZZ', 'ZQ', 'ENAB', 11130)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_DQRZZ_C', '债券品种_超短期融资券', 'ZQ_DQRZZ_C', 'ZQ', 'ENAB', 11131)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JHQYZ', '债券品种_集合企业债', 'ZQ_JHQYZ', 'ZQ', 'ENAB', 11140)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JRZ', '债券品种_金融债', 'ZQ_JRZ', 'ZQ', 'ENAB', 11150)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JRZ_ZC', '债券品种_政策性金融债', 'ZQ_JRZ_ZC', 'ZQ', 'ENAB', 11151)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JRZ_YH', '债券品种_商业银行金融债', 'ZQ_JRZ_YH', 'ZQ', 'ENAB', 11152)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JRZ_FYH', '债券品种_非银行金融债', 'ZQ_JRZ_FYH', 'ZQ', 'ENAB', 11153)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_YHPJ', '债券品种_央行票据', 'ZQ_YHPJ', 'ZQ', 'ENAB', 11200)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_JHPJ', '债券品种_集合票据', 'ZQ_JHPJ', 'ZQ', 'ENAB', 11201)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_ZQPJ', '债券品种_中期票据', 'ZQ_ZQPJ', 'ZQ', 'ENAB', 11202)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_ZFZCJGZ', '债券品种_政府支持机构债', 'ZQ_ZFZCJGZ', 'ZQ', 'ENAB', 11203)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_GJJGZ', '债券品种_国际机构债', 'ZQ_GJJGZ', 'ZQ', 'ENAB', 11204)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_ZCZQH', '债券品种_资产支持证券', 'ZQ_ZCZQH', 'ZQ', 'ENAB', 11205)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_ZQJH', '债券品种_债权计划', 'ZQ_ZQJH', 'ZQ', 'ENAB', 11206)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_TYCD', '债券品种_同业存单', 'ZQ_TYCD', 'ZQ', 'ENAB', 11207)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_DXGJ', '债券品种_定向工具', 'ZQ_DXGJ', 'ZQ', 'ENAB', 11208)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_QT', '债券品种_其他债', 'ZQ_QT', 'ZQ', 'ENAB', 11209)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_QD', '债券品种_QD', 'ZQ_QD', 'ZQ', 'ENAB', 11210)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_GKZ', '债券品种_国开债', 'ZQ_GKZ', 'ZQ', 'ENAB', 11211)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_PPN', '债券品种_PPN', 'ZQ_PPN', 'ZQ', 'ENAB', 11212)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'ZQ_CT', '债券品种_存托', 'ZQ_CT', 'ZQ', 'ENAB', 11213)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG', '回购品种', 'HG', '[root]', 'ENAB', 12000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS', '回购品种_质押式', 'HG_ZYS', 'HG', 'ENAB', 12001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS_XY_ZQ', '回购品种_质押式_协议_债券', 'HG_ZYS_XY_ZQ', 'HG', 'ENAB', 12002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS_GZXQ', '回购品种_质押式_国债', 'HG_ZYS_GZXQ', 'HG', 'ENAB', 12003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS_QYZ', '回购品种_质押式_企业债', 'HG_ZYS_QYZ', 'HG', 'ENAB', 12004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS_GP', '回购品种_质押式_股票', 'HG_ZYS_GP', 'HG', 'ENAB', 12005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_ZYS_JTL', '回购品种_质押式_金天利', 'HG_ZYS_JTL', 'HG', 'ENAB', 12006)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_MDS', '回购品种_买断式', 'HG_MDS', 'HG', 'ENAB', 12007)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_MDS_GZXQ', '回购品种_买断式_国债', 'HG_MDS_GZXQ', 'HG', 'ENAB', 12008)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_MDS_QYZ', '回购品种_买断式_企业债', 'HG_MDS_QYZ', 'HG', 'ENAB', 12009)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HG_YDGH', '回购品种_约定购回', 'HG_YDGH', 'HG', 'ENAB', 12010)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ', '基金品种', 'JJ', '[root]', 'ENAB', 13000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_FBS', '基金品种_封闭式', 'JJ_FBS', 'JJ', 'ENAB', 13001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_FBS_REITs', '基金品种_封闭式_REITs', 'JJ_FBS_REITs', 'JJ', 'ENAB', 13002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS', '基金品种_开放式', 'JJ_KFS', 'JJ', 'ENAB', 13003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_ETF', '基金品种_开放式_ETF', 'JJ_KFS_ETF', 'JJ', 'ENAB', 13004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_ETF_ZQ', '基金品种_开放式_ETF_债券', 'JJ_KFS_ETF_ZQ', 'JJ', 'ENAB', 13005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_LOF', '基金品种_开放式_LOF', 'JJ_KFS_LOF', 'JJ', 'ENAB', 13006)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_LOF_WSS', '基金品种_开放式_LOF_未上市', 'JJ_KFS_LOF_WSS', 'JJ', 'ENAB', 13007)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_HBX', '基金品种_开放式_货币', 'JJ_KFS_HBX', 'JJ', 'ENAB', 13008)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_HBX_SH', '基金品种_开放式_实时货币', 'JJ_KFS_HBX_SH', 'JJ', 'ENAB', 13009)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_HBX_JY', '基金品种_开放式_交易货币', 'JJ_KFS_HBX_JY', 'JJ', 'ENAB', 13010)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_QDII', '基金品种_开放式_QDII', 'JJ_KFS_QDII', 'JJ', 'ENAB', 13011)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_REITs', '基金品种_开放式_REITs', 'JJ_KFS_REITs', 'JJ', 'ENAB', 13012)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_LOF_A', '基金品种_开放式_LOF_A', 'JJ_KFS_LOF_A', 'JJ', 'ENAB', 13013)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'JJ_KFS_LOF_DK', '基金品种_开放式_LOF_定期开放', 'JJ_KFS_LOF_DK', 'JJ', 'ENAB', 13014)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC', '理财品种', 'LC', '[root]', 'ENAB', 15000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_BX', '理财品种_保险', 'LC_BX', 'LC', 'ENAB', 15001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_BX_ZG', '理财品种_保险资管', 'LC_BX_ZG', 'LC', 'ENAB', 15002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_CF', '理财品种_财富', 'LC_CF', 'LC', 'ENAB', 15003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_DX', '理财品种_定向', 'LC_DX', 'LC', 'ENAB', 15004)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_FFY', '理财品种_福费廷', 'LC_FFY', 'LC', 'ENAB', 15005)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_PJ', '理财品种_票据', 'LC_PJ', 'LC', 'ENAB', 15006)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_JH', '理财品种_集合', 'LC_JH', 'LC', 'ENAB', 15007)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SM', '理财品种_私募', 'LC_SM', 'LC', 'ENAB', 15008)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_XT', '理财品种_信托', 'LC_XT', 'LC', 'ENAB', 15009)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_HBX', '理财品种_货币', 'LC_HBX', 'LC', 'ENAB', 15010)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_ZG', '理财品种_资管', 'LC_ZG', 'LC', 'ENAB', 15011)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YHLC', '理财品种_银行理财', 'LC_YHLC', 'LC', 'ENAB', 15012)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_LC', '理财品种_理财', 'LC_LC', 'LC', 'ENAB', 15013)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_HP', '理财品种_汇票', 'LC_HP', 'LC', 'ENAB', 15014)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_QY', '理财品种_企业', 'LC_QY', 'LC', 'ENAB', 15015)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_ZQJH', '理财品种_债权计划', 'LC_ZQJH', 'LC', 'ENAB', 15016)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_TZJH', '理财品种_投资计划', 'LC_TZJH', 'LC', 'ENAB', 15017)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_ZX', '理财品种_专项', 'LC_ZX', 'LC', 'ENAB', 15018)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YLJ', '理财品种_养老金', 'LC_YLJ', 'LC', 'ENAB', 15019)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YH', '理财品种_银行', 'LC_YH', 'LC', 'ENAB', 15020)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_RZYW', '理财品种_融资业务', 'LC_RZYW', 'LC', 'ENAB', 15021)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_JRZC', '理财品种_金融资产', 'LC_JRZC', 'LC', 'ENAB', 15022)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_QQ', '理财品种_期权', 'LC_QQ', 'LC', 'ENAB', 15023)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_PE', '理财品种_PE投资', 'LC_PE', 'LC', 'ENAB', 15024)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_QT', '理财品种_其他', 'LC_QT', 'LC', 'ENAB', 15025)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_JDBT', '理财品种_京东白条', 'LC_JDBT', 'LC', 'ENAB', 15026)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YXHH', '理财品种_有限合伙', 'LC_YXHH', 'LC', 'ENAB', 15027)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_XTYBZJJ', '理财品种_信托业保障基金', 'LC_XTYBZJJ', 'LC', 'ENAB', 15028)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_JJGSZHCP', '理财品种_基金公司专户产品', 'LC_JJGSZHCP', 'LC', 'ENAB', 15029)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_ZG_QHGS', '理财品种_期货公司资管计划', 'LC_ZG_QHGS', 'LC', 'ENAB', 15030)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YHHPMD', '理财品种_银行汇票买断', 'LC_YHHPMD', 'LC', 'ENAB', 15100)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_YHHPSYQZR', '理财品种_银行汇票受益权转让', 'LC_YHHPSYQZR', 'LC', 'ENAB', 15101)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYHPSYQZR', '理财品种_商业汇票受益权转让', 'LC_SYHPSYQZR', 'LC', 'ENAB', 15102)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_WTZQJYSZKSR', '理财品种_委托债权及应收账款受让', 'LC_WTZQJYSZKSR', 'LC', 'ENAB', 15103)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ', '理财品种_收益权', 'LC_SYQ', 'LC', 'ENAB', 15200)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_GP', '理财品种_收益权_股票', 'LC_SYQ_GP', 'LC', 'ENAB', 15201)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_YS', '理财品种_收益权_应收账款', 'LC_SYQ_YS', 'LC', 'ENAB', 15202)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_XD', '理财品种_收益权_信贷资产', 'LC_SYQ_XD', 'LC', 'ENAB', 15203)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_TD', '理财品种_收益权_特定资产', 'LC_SYQ_TD', 'LC', 'ENAB', 15204)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_QT', '理财品种_收益权_其他', 'LC_SYQ_QT', 'LC', 'ENAB', 15205)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_PZ', '理财品种_收益权_凭证', 'LC_SYQ_PZ', 'LC', 'ENAB', 15206)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYHH', '理财品种_收益互换', 'LC_SYHH', 'LC', 'ENAB', 15207)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYPZ', '理财品种_收益凭证', 'LC_SYPZ', 'LC', 'ENAB', 15208)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_SYQ_GQJHHQY', '理财品种_收益权_股权及合伙企业', 'LC_SYQ_GQJHHQY', 'LC', 'ENAB', 15209)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_GQ', '理财品种_股权', 'LC_GQ', 'LC', 'ENAB', 15300)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_GQ_FKZ', '理财品种_股权_非控制', 'LC_GQ_FKZ', 'LC', 'ENAB', 15301)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_GQ_KZ', '理财品种_股权_控制', 'LC_GQ_KZ', 'LC', 'ENAB', 15302)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_XDTZ', '理财品种_信贷投资', 'LC_XDTZ', 'LC', 'ENAB', 15303)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_XYZ', '理财品种_信用证', 'LC_XYZ', 'LC', 'ENAB', 15304)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_DC', '理财品种_定存', 'LC_DC', 'LC', 'ENAB', 15400)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_DK', '理财品种_贷款', 'LC_DK', 'LC', 'ENAB', 15401)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_WTDK', '理财品种_委托贷款', 'LC_WTDK', 'LC', 'ENAB', 15402)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'LC_DK_FWD', '理财品种_贷款_非委贷', 'LC_DK_FWD', 'LC', 'ENAB', 15403)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QH', '期货品种', 'QH', '[root]', 'ENAB', 17000)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QH_GP', '期货品种_股票', 'QH_GP', 'QH', 'ENAB', 17001)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QH_GZ', '期货品种_股指', 'QH_GZ', 'QH', 'ENAB', 17002)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QH_SP', '期货品种_商品', 'QH_SP', 'QH', 'ENAB', 17003)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QH_ZQ', '期货品种_债券', 'QH_ZQ', 'QH', 'ENAB', 17004)")
				// lizhidong 20171229 华泰回归测试—期权品种大类有误
				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ', '期权品种', 'QQ', '[root]', 'ENAB', 17100)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ_GP', '期权品种_股票', 'QQ_GP', 'QQ', 'ENAB', 17101)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ_ZQ', '期权品种_债券', 'QQ_ZQ', 'QQ', 'ENAB', 17102)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ_ZS', '期权品种_指数', 'QQ_ZS', 'QQ', 'ENAB', 17103)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ_JJ_ETF', '期权品种_ETF基金', 'QQ_JJ_ETF', 'QQ', 'ENAB', 17104)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QQ_QH_SP', '期权品种_商品期货', 'QQ_QH_SP', 'QQ', 'ENAB', 17105)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'XH', '现货品种', 'XH', '[root]', 'ENAB', 17200)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'XH_SP', '现货品种_实盘', 'XH_SP', 'XH', 'ENAB', 17201)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'XH_JQ', '现货品种_即期', 'XH_JQ', 'XH', 'ENAB', 17202)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'XH_YQ', '现货品种_延期', 'XH_YQ', 'XH', 'ENAB', 17203)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QZ', '权证品种', 'QZ', '[root]', 'ENAB', 17300)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QZ_QZ', '权证品种_权证', 'QZ_QZ', 'QZ', 'ENAB', 17301)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QZ_NX', '权证品种_牛熊证', 'QZ_NX', 'QZ', 'ENAB', 17302)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'QZ_PG', '权证品种_配股', 'QZ_PG', 'QZ', 'ENAB', 17303)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH', '互换品种', 'HH', '[root]', 'ENAB', 17400)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH_LL', '互换品种_利率', 'HH_LL', 'HH', 'ENAB', 17402)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH_SY', '互换品种_收益', 'HH_SY', 'HH', 'ENAB', 17403)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH_SY_GP', '收益互换_股票', 'HH_SY_GP', 'HH', 'ENAB', 17404)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH_SY_QH', '收益互换_期货', 'HH_SY_QH', 'HH', 'ENAB', 17405)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'HH_SY_ZS', '收益互换_指数', 'HH_SY_ZS', 'HH', 'ENAB', 17406)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'YQ', '远期品种', 'YQ', '[root]', 'ENAB', 17500)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'YQ_GP', '远期品种_股票', 'YQ_GP', 'YQ', 'ENAB', 17501)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'YQ_LL', '远期品种_利率', 'YQ_LL', 'YQ', 'ENAB', 17502)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'YQ_WH', '远期品种_外汇', 'YQ_WH', 'YQ', 'ENAB', 17503)")

				.addSql(" insert into T_S_DA_SEC_VAR (C_IDEN, C_SEC_VAR_CODE, C_SEC_VAR_NAME, C_DA_CODE, C_DA_CODE_P, C_DV_STATE, N_ORDER) "
						+ " values (SEQU_S_DA_SEC_VAR.NEXTVAL, 'YQ_ZQ', '远期品种_债券', 'YQ_ZQ', 'YQ', 'ENAB', 17504)")
				.endScript();
	}
	
	/**
	 * 国际货币设置
	 * @param builder
	 */
	private void buildGJHBSZ(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'AED', '阿联酋元', 'AED', 73)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ALL', '阿尔巴尼亚元', 'ALL', 9)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'AOK', '安哥拉元', 'AOK', 11)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ARS', '阿根廷比索', 'ARS', 83)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ATS', '奥地利', 'ATS', 12)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'AUD', '澳元', 'AUD', 5)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'BAM', '波斯尼亚元', 'BAM', 17)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'BEF', '比利时元', 'BEF', 15)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'BOB', '波利维亚元', 'BOB', 16)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'BRL', '巴西元', 'BRL', 18)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'BSD', '巴哈马元', 'BSD', 14)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CAD', '加拿大元', 'CAD', 20)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CHF', '瑞郎', 'CHF', 44)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CLP', '智利元', 'CLP', 21)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CNY', '人民币', 'CNY', 1)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'COP', '哥伦比亚元', 'COP', 22)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CUP', '古巴元', 'CUP', 24)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CYP', '塞浦路兹元', 'CYP', 25)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'CZK', '捷克斯洛伐克元', 'CZK', 26)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'DEM', '德国元', 'DEM', 31)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'DKK', '丹麦克朗', 'DKK', 92)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'DLZ', '波兰元', 'DLZ', 33)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'DZD', '阿尔及利亚元', 'DZD', 10)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'EGP', '埃及元', 'EGP', 27)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ESP', '西班牙元', 'ESP', 41)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'EUR', '欧元', 'EUR', 7)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'FIM', '芬兰元', 'FIM', 29)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'FJD', '斐济元', 'FJD', 28)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'FRF', '法国元', 'FRF', 30)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'GBP', '英镑', 'GBP', 4)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'GHC', '加纳元', 'GHC', 32)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'GRD', '希腊元', 'GRD', 47)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'HKD', '港币', 'HKD', 3)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'HNL', '洪都拉斯元', 'HNL', 48)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'HRK', '克罗地亚元', 'HRK', 23)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'HUF', '匈牙利元', 'HUF', 49)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'HZM', '阿塞拜疆元', 'HZM', 13)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'IDR', '印度尼西亚元', 'IDR', 52)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ILS', '以色列谢克尔', 'ILS', 85)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'INR', '印度卢比', 'INR', 51)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'IQD', '爱尔兰元', 'IQD', 53)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'IRR', '伊朗元', 'IRR', 77)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ISK', '冰岛元', 'ISK', 50)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ITL', '意大利元', 'ITL', 54)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'JDD', '约旦元', 'JDD', 55)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'JPY', '日元', 'JPY', 6)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'KRW', '韩国元', 'KRW', 56)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'KWD', '科威特元', 'KWD', 57)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'LUF', '卢森堡元', 'LUF', 59)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MAD', '摩洛哥元', 'MAD', 64)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MNT', '蒙古元', 'MNT', 63)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MOP', '澳门元', 'MOP', 58)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MTL', '马耳他元', 'MTL', 61)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MXN', '墨西哥元', 'MXN', 62)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'MYR', '马来西亚元', 'MYR', 60)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'NLG', '荷兰元', 'NLG', 66)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'NOK', '挪威克朗', 'NOK', 82)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'NSK', '挪威元', 'NSK', 67)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'NZD', '新西兰元', 'NZD', 65)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'OMR', '阿曼里亚尔', 'OMR', 8)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'PEN', '秘鲁元', 'PEN', 69)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'PHP', '菲律宾元', 'PHP', 70)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'PKR', '巴基斯坦元', 'PKR', 68)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'PLN', '波兰兹罗提', 'PLN', 89)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'PTE', '葡萄牙埃斯库多', 'PTE', 81)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'RBL', '俄罗斯卢布', 'RBL', 88)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ROL', '罗马尼亚元', 'ROL', 34)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'RON', '罗马尼亚列伊', 'RON', 90)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'RUB', '俄罗斯元', 'RUB', 35)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'RWF', '卢旺达元', 'RWF', 36)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'SAR', '沙特阿拉伯元', 'SAR', 37)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'SDP', '苏丹元', 'SDP', 42)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'SEK', '瑞典克朗', 'SEK', 43)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'SGD', '新加坡元', 'SGD', 38)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'SIT', '斯洛文尼亚元', 'SIT', 39)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'THB', '泰国元', 'THB', 80)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TJR', '塔吉克斯坦元', 'TJR', 46)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TND', '突尼斯元', 'TND', 78)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TOP', '汤加元', 'TOP', 79)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TRL', '土耳其元', 'TRL', 71)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TRY', '土耳其里拉', 'TRY', 87)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'TWD', '台币', 'TWD', 45)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'UAH', '乌克兰格里', 'UAH', 86)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'UES', '乌兹别克斯坦元', 'UES', 74)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'UGX', '乌干达元', 'UGX', 72)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'USD', '美元', 'USD', 2)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'VND', '越南盾', 'VND', 76)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'XAF', '喀麦隆元', 'XAF', 19)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'YER', '也门元', 'YER', 75)")
				.addSql("insert into T_S_DC_CURY (C_IDEN, C_DC_CODE, C_DC_NAME, C_DC_SIGN, N_ORDER) values ( sequ_S_DC_CURY.Nextval, 'ZAR', '南非元', 'ZAR', 40)")
				.endScript();
	}
	
	/**
	 * 业务参数设置表
	 * @param builder
	 */
	private void buildYWCSZZB(ScriptBuilder builder){
		//T_S_ITEM_PARA_RELA
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ABCSJ', 'CO_SGBS_SF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('CWSS', 'AO_JJ_JYLFYLCB')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('CWSS', 'AO_JJ_RGJJLX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_JJ_FH_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_PX_FH_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_PX_FH_BLWS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_PX_FH_GGTYSGL')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_PX_FH_JSRQCL')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_PX_FH_SHPX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('DJPX', 'AO_ZQ_HS_SJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('FECF', 'AO_TA_HS_FEZS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('HGJY', 'AO_M1_HG_CB_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('HGJY', 'AO_M1_HG_FY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('HGJY', 'AO_M1_HG_FY_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('HGJY', 'AO_M1_HG_FY_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('HGJY', 'AO_M2_HG_CB_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_GPZYHG', 'C0_GPZYHGQSFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_JSMX_GGT', 'CO_M2_CG_GGT')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_JSMX_GGT', 'CO_M2_CG_JYFGHMS_HGT')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QQJSMX', 'CO_M1_SJSGGQQFY')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QQJSMX', 'CO_M1_SJSGGQQMFKCYJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QQJSMX', 'CO_M1_SJSGGQQMFXQYJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'C0_GPZYHGQSFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_GHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_JSF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_JZF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_QSGHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_YHS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_YJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_ZGF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_M2_CS_ZYS_HG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_MODE_SJSMXJYFY')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_QSMXNEW', 'CO_SJSV5_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQDZMX', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQDZMX_HT', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQFZ_ZS', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQJS', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQJS_HT', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQJY_ZS', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQJY_ZX', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQLX_ZX', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQWJLX', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('INFI_JY_RZRQWJLX_HT', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JJJY', 'AO_JJ_ETF_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JJJY', 'AO_JJ_ETF_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JJJY', 'AO_JJ_ETF_TBK_TZFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JJJY', 'AO_JJ_JYLFYLCB')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JJJY', 'AO_JJ_RGJJLX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('JYJS_XS', 'AO_TA_HS_ZJLSJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QHJY', 'AO_QH_BZJJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QHJY', 'AO_QH_HEJG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QHJY', 'AO_QH_HQYQBZJJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QHJY', 'AO_QH_JWQHGZJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QHJY', 'AO_QH_PCCBJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QH_SETTDETAIL', 'CO_M1_FY_GZQH')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QH_TD_FORM', 'CO_M1_FY_GZQH')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QQJY', 'AO_QQ_GGBZJJS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('QQJY', 'AO_QQ_GGZDSCJG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJL_ZRT', 'AO_QT_ZRT_YCTS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_CK', 'HB_SZ_SYTS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_FXJ', 'AO_QT_HS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M1_HG_JX_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M1_HG_TXFY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M1_HG_TXSY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M1_HG_XTJYHBJX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M2_HG_JX_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M2_HG_JX_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M2_HG_TXFY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_HG', 'AO_M2_HG_TXSY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_003_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_003_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_004')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_005')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JJ', 'AO_JJ_HB_YJBTSY')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_JZ', 'AO_TA_JT_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_RZRQ', 'AO_RZRQ_FYJT_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_RZRQ', 'AO_RZRQ_JXHQ_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_RZRQ', 'AO_RZRQ_JXQS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_RZRQ', 'AO_RZRQ_JXWS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_ZH', 'AO_QT_ZHJX_AJXJZ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_ZQ', 'AO_ZQ_HS_JX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_ZQ', 'AO_ZQ_HS_YZJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RCJT_ZQ', 'AO_ZQ_TYF_HS_JX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RZRQ', 'AO_RZRQ_HSSX_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('RZRQ', 'AO_RZRQ_HSSX_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGF', 'CO_FH_DZCL_GP')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGF', 'CO_FH_DZCL_ZQ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGF', 'CO_JJCFCQYQTS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGF', 'CO_QYSJ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'C0_GPZYHGQSFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_GHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_JSF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_JZF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_QSGHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_YHS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_YJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_ZGF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_M2_CG_ZYS_HG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSGH', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_GHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_JSF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_JZF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_QSGHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_YHS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_YJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_ZGF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_M2_CS_ZYS_HG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_RZRQ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSHB', 'CO_SJSV5_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSJG', 'CO_FH_DZCL_GP')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSJG', 'CO_FH_DZCL_ZQ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSJG', 'CO_JJCFCQYQTS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSJG', 'CO_QYSJ_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSZHHB', 'C0_GPZYHGQSFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SJSZHHB', 'CO_M2_CS_ZYS_HG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_FJ_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_FJ_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_FJ_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SYJZ_JZSY', 'AO_QT_JZSY_JZZYX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('SZJZYW', 'AO_QT_SZJZ_SFBT')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_AUTO_TAFH')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_FH')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_HS_FXZBJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_HS_QFFJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_HS_SSZBFJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_HS_SYPZJ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_HS_YJBC')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_QZQJ_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_QZQJ_QRYSX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_SSZB')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_SS_ZJZJTQ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_XS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'AO_TA_XS_DFSY')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'HB_JZ_FEJZ_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TAXS', 'HB_XS_FHJZ_ZJYF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('TA_REDEEM', 'CO_TA_SHF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('XGSG', 'AO_ZQ_HS_ZQQR')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('XZSG', 'AO_ZQ_HS_ZQQR')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZCGZ_ZQ', 'AO_QH_GZ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZCGZ_ZQ', 'AO_QT_SDGZ_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZDJSMX', 'C0_GPZYHGQSFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZDJSMX', 'CO_FH_DZCL_GP')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZDJSMX', 'CO_FH_DZCL_ZQ')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZDJSMX', 'CO_GZPTYW_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZDJSMX', 'CO_M2_CG_ZYS_HG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZGH', 'CO_GZPTYW_SJDQFS')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_M1_ZQ_BS_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_M1_ZQ_CB_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_M1_ZQ_FY_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_M1_ZQ_FY_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_M1_ZQ_FY_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_HS_HBFX')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_HS_ZQHB')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_SX_ZZG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001_003')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001_004')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQJY', 'AO_ZQ_TYF_001_005')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQSP', 'AO_SP_SG_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQZH', 'A0_JJ_PDZH_001')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQZH', 'A0_JJ_PDZH_002')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZQZH', 'AO_ZQ_SX_ZZG')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('ZRTJY', 'CO_ZRT_DZSF')")
				.addSql("insert into T_S_ITEM_PARA_RELA (C_ITEM_CODE, C_DSP_CODE)values ('rzrqjyls_hs', 'CO_RZRQ_SJDQFS')")
				.endScript();
	}
	
	/**
	 * 货币对设置
	 * @param builder
	 */
	private void buildHBDSZ(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'HKD/CNY', '港币/人民币', 'HKD', 'CNY', 1.0000, null, 1, 'xifeimin', '20130618 14:05:28', 'xifeimin', '20130618 14:05:51')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'USD/HKD', '美元/港币', 'USD', 'HKD', 1.0000, null, 1, 'zp', '20160116 14:54:58', 'zp', '20160116 14:55:00')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'JPY/CNY', '日元/人民币', 'JPY', 'CNY', 100.0000, null, 1, 'zp', '20160116 14:52:39', 'zp', '20160116 14:53:55')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'THB/USD', '泰铢/美元', 'THB', 'USD', 1.0000, null, 1, 'zp', '20160116 14:53:49', 'zp', '20160116 14:53:55')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'USD/CNY', '美元/人民币', 'USD', 'CNY', 1.0000, null, 1, 'wanghailong', '20160506 21:57:10', 'wanghailong', '20160506 21:57:13')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'EUR/USD', '欧元/美元', 'EUR', 'USD', 1.0000, null, 1, 'daimengru', '20160309 11:15:21', 'daimengru', '20160309 11:15:23')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'USD/EUR', '美元/欧元', 'USD', 'EUR', 1.0000, null, 1, 'wanghailong', '20160506 21:57:29', 'wanghailong', '20160506 21:57:32')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'EUR/CNY', '欧元/人民币', 'EUR', 'CNY', 1.0000, null, 1, 'wanghailong', '20160506 21:55:05', 'wanghailong', '20160506 21:55:08')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'AUD/CNY', '澳元/人民币', 'AUD', 'CNY', 1.0000, null, 1, 'wanghailong', '20160506 21:56:10', 'wanghailong', '20160506 21:56:16')")
				.addSql("insert into t_p_bi_cury_pair (C_IDEN, C_CURY_PAIR_CODE, C_CURY_PAIR_NAME, C_DC_CODE_MARK, C_DC_CODE_PRICE, N_QTE_FACTO, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_cury_pair.Nextval, 'GBP/CNY', '英镑/人民币', 'GBP', 'CNY', 1.0000, null, 1, 'wanghailong', '20160506 21:54:09', 'wanghailong', '20160506 21:54:13')")
				.endScript();
	}
	
	/**
	 * 收支项目设置
	 * @param builder
	 */
	private void buildSZXMSZ(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_ZCFWF', '资产服务费', 'E', null, 1, 'ZJ', '20140907 18:26:51', 'ZJ', '20140907 18:26:55')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_FXZBJ', '风险准备金', 'E', null, 1, 'meipeng', '20140617 14:01:11', 'meipeng', '20140617 14:01:15')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_LXS', '利息税', 'E', null, 1, 'sys', '20121226 14:47:14', 'ADMIN', '20140830 16:47:41')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_XJS', '存款利息税', 'E', null, 1, 'sys', '20121226 14:47:46', 'sys', '20121226 15:20:08')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_QT', '其他', 'S', null, 1, 'sys', '20121227 16:39:59', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_KHF', '开户费', 'E', null, 1, 'sys', '20130201 10:39:18', 'sys', '20130201 10:39:22')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YFL_BZJ', '保证金', 'E', null, 1, 'sys', '20130105 14:04:59', 'sys', '20130105 14:05:05')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_YHS', '印花税', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YJL_JYJ', '净佣金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YFL_YJ', '应付佣金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JGSXF', '交割手续费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JYYJ', '交易佣金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JGYJ', '交割佣金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_RGF', '认购费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JSOF', '经手费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_SHF', '赎回费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_SHFGZC', '赎回费归资产', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_ZCF', '转出费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_WYF', '违约费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_BCF', '补差费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_BCFGZC', '补差费归资产', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_HDSGF', '后端申购费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_HDZCF', '后端转出费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_ZGF', '征管费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_GLF', '管理费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_TGF', '托管费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_BGF', '保管费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_ZHGLF', '帐户管理费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_XSF', '销售服务费', 'E', null, 1, '020266', '20150701 18:48:33', '020266', '20150701 18:48:33')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_TZGWF', '投资顾问费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_YJBCF', '业绩报酬费（运营类）', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_LSF', '律师费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_XXPLF', '信息披露费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_GHF', '过户费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_SJF', '审计费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_JYFJF', '教育附加费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_CJS', '城建税', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_DWF', '堤围费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'SFL_YYS', '营业税', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_HKF', '汇款费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JSUF', '结算费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_WYFGZC', '违约费归资产', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YFL_FXJ', '风险金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_SXF', '手续费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_SGF', '申购费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JYF', '交易费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JYSXF', '交易手续费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_YHSXF', '银行手续费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JSFWF', '结算服务费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_ZSSYF', '指数使用费', 'E', null, 1, 'sys', '20130108 16:55:07', 'sys', '20130108 16:55:11')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_ZQLX', '利息', 'S', null, 1, 'sys', '20130110 17:41:34', 'sys', '20130110 17:41:41')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_SSF', '上市费', 'E', null, 1, 'sys', '20130115 17:40:28', 'sys', '20130115 17:40:33')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_CSF', '初始费', 'E', null, 1, 'sys', '20130106 11:14:13', 'sys', '20130106 11:14:17')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_ZYDJF', '质押登记费', 'S', null, 1, ' ', ' ', ' ', ' ')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_ZTGF', '转托管费', 'E', null, 1, 'lujiazhen', '20131120 17:10:51', 'xifeimin', '20131120 17:11:17')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_ZDFWF', '中登服务费', 'E', null, 1, 'meipeng', '20140610 14:06:26', 'meipeng', '20140610 14:06:26')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZTQHK', '融资提前还款', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZMQHK', '融资卖券还款', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZFX', '融资罚息', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZFXZC', '融资罚息支出', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQFX', '融券罚息', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQFXZC', '融券罚息支出', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQFHDZ', '融券分红偿还金额到账', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQPGDZ', '融券配股偿还金额到账', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQPSZQDZ', '融券派送债券偿还金额到账', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQFHCX', '融券分红除息', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZLXZC', '融资利息支出', 'S', ' ', 1, 'sys', '20140624 11:04:52', 'sys', '20140624 11:04:52')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQLXZC', '融券利息支出', 'S', ' ', 1, 'sys', '20140624 11:04:53', 'sys', '20140624 11:04:53')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQ', '融券计息', 'S', ' ', 1, 'sys', '20140624 11:04:53', 'sys', '20140624 11:04:53')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZ', '融资计息', 'S', ' ', 1, 'sys', '20140624 11:04:53', 'sys', '20140624 11:04:53')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QSK_ZQF', '证券组合费', 'E', null, 1, 'sys', '20140810 16:03:43', 'BZ', '20141011 11:04:03')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_YYFWF', '运营服务费', 'E', null, 1, 'ZJ', '20140920 17:37:41', 'ZJ', '20140920 17:37:45')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_WDSXF', '委贷手续费', 'E', null, 1, 'ZJ', '20141011 11:32:37', 'ZJ', '20141011 11:32:41')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_TDF', '通道费', 'E', null, 1, 'ZJ', '20141011 11:21:26', 'ZJ', '20141011 11:21:31')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_ZXFWF', '咨询服务费', 'E', null, 1, 'ZJ', '20141011 11:27:18', 'ZJ', '20141011 11:27:20')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQJX', '融券计息', 'E', null, 1, 'ZB1', '20141206 16:55:26', 'ZB1', '20141206 16:58:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_QTF', '证券其他费', 'E', null, 1, 'BZ', '20141118 01:21:40', 'BZ', '20141118 01:21:45')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_TQHK', '融资提前还款', 'E', null, 1, 'ZB1', '20141206 16:56:58', 'ZB1', '20141206 16:58:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RZJX', '融资计息', 'E', null, 1, 'ZB1', '20141206 16:54:37', 'ZB1', '20141206 16:58:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_FDXSF', '浮动销售费', 'E', null, 1, 'huangyixin', '20150113 16:27:29', 'huangyixin', '20150113 16:27:32')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_TDJTDSY', '特定级特定收益', 'E', null, 1, 'lizepu', '20150411 16:26:18', 'lizepu', '20150411 16:26:45')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_YXJFY', '优先级费用', 'E', null, 1, 'lizepu', '20141220 15:39:45', 'lizepu', '20141220 15:39:49')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSCCF', '贵金属仓储费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSYBF', '贵金属运保费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSCQF', '贵金属超期费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSWYJ', '贵金属违约金', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSDYF', '贵金属递延费', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_GJSSTS', '贵金属品种升贴水', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_TZGWYJBCF', '投资顾问业绩报酬费', 'E', null, 1, 'tanxy3', '20150428 14:18:35', 'tanxy3', '20150428 14:18:39')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_TDJFDSY', '特定级浮动收益', 'E', null, 1, 'lizepu', '20150411 16:26:40', 'lizepu', '20150411 16:26:45')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_FJYGHSXF', '非交易过户手续费', 'S', null, 1, 'sys', '30-5月 -15', 'sys', '30-5月 -15')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XSL_FJYGHYHS', '非交易过户印花税', 'S', null, 1, 'sys', '30-5月 -15', 'sys', '30-5月 -15')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZRT_ZRQGHWY', '转融券归还违约', 'E', null, 1, 'chenli', '20150211 13:51:29', 'chenli', '20150211 13:53:29')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZRT_ZRQJSWY', '转融券交收违约', 'E', null, 1, 'chenli', '20150211 13:51:16', 'chenli', '20150211 13:53:29')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZRT_ZRZGHWY', '转融资归还违约', 'E', null, 1, 'chenli', '20150211 13:50:50', 'chenli', '20150211 13:53:29')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZRT_ZRZJSWY', '转融资交收违约', 'E', null, 1, 'chenli', '20150211 13:51:05', 'chenli', '20150211 13:53:29')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZJZJTQ_ZJ', '资金追加', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZJZJTQ_TQ', '资金提取', 'S', null, 1, 'sys', '20121130 14:11:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JYSXF_SQS', '上清所交易费', 'S', null, 1, 'sys', '20131223 13:18:01', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'ZQL_JSFWF_SQS', '上清所结算费', 'S', null, 1, 'sys', '20131223 13:18:01', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQQYJX', '融券权益计息', 'E', null, 1, 'zh', '20150325 13:49:47', 'zh', '20150325 13:50:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_RQQYLXZC', '融券权益利息支付', 'E', null, 1, 'zh', '20150325 13:50:13', 'zh', '20150325 13:50:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YFL_QSGHF', '券商过户费', 'S', null, 1, 'sys', '20150318 11:06:15', 'sys', null)")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YYL_XZFW', '行政服务费', 'E', null, 1, 'liyanchen', '20150610 11:59:21', 'liyanchen', '20150610 11:59:29')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_KHWH', '客户维护费', 'E', null, 1, 'liyanchen', '20150611 10:51:22', 'liyanchen', '20150611 10:51:22')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_FKYYGW', '风控运营顾问费', 'E', null, 1, 'liyanchen', '20150611 10:54:07', 'liyanchen', '20150611 10:54:07')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_YDRQ', '约定融券', 'E', null, 1, 'sys', '20150714 14:11:15', 'sys', '20150714 14:11:15')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'RZRQ_YDRQGH', '约定融券归还', 'E', null, 1, 'sys', '20150714 14:12:15', 'sys', '20150714 14:12:15')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_QHSBF', '期货申报费', 'S', '期货申报费', 1, 'sys', '20150803 00:00:00', 'sys', '20150803 00:00:00')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'YTL_ZHWHF', '账户维护费', 'E', null, 1, 'QY', '20160313 16:14:07', 'QY', '20160313 16:14:19')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XWBZJ_DPZQ', '席位保证金_大鹏证券', 'E', null, 1, 'sys', '20160509 11:50:24', 'sys', '20160509 11:51:09')")
				.addSql("insert into t_p_Bi_Ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'XWBZJ_XXZQ', '席位保证金_厦信证券', 'E', null, 1, 'sys', '20160509 11:51:04', 'sys', '20160509 11:51:09')")
				// AddbyLiyongjun 2018-04-02 STORY #51626 【ETF产品】南方、国泰、鹏华、易方达、嘉实ETF产品整合，优化
				.addSql("insert into t_p_bi_ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_QTF', '其他费', 'E', null, 1, 'sys', '20180115 10:39:18', 'sys', '20180115 10:39:18')")
				.addSql("insert into t_p_bi_ie (C_IDEN, C_FEE_CODE, C_FEE_NAME, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_Bi_Ie.Nextval, 'QTL_ZCF', '注册费', 'E', null, 1, 'sys', '20180115 10:39:18', 'sys', '20180115 10:39:18')")
				.endScript();
	}
	
	/**
	 * 收支项目分类
	 * @param builder
	 */
	private void buildSZXMFL(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZ', 'RZLX', 'E', null, 1, 'ADMIN', '20140904 15:47:10', 'ADMIN', '20140904 15:47:10', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQFX', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 20:28:02', 'ADMIN', '20140904 20:28:02', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQFHDZ', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 20:28:27', 'ADMIN', '20140904 20:28:27', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQLXZC', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 20:28:57', 'ADMIN', '20140904 20:28:57', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZMQHK', 'RZLX', 'E', null, 1, 'ADMIN', '20140904 20:30:48', 'ADMIN', '20140904 20:30:48', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZFXZC', 'RZLX', 'E', null, 1, 'ADMIN', '20140904 20:31:44', 'ADMIN', '20140904 20:31:44', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQ', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 16:08:21', 'ADMIN', '20140904 16:08:21', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZCFWF', 'YYSZ', 'E', null, 1, 'ZJ', '20140907 18:27:17', 'ZJ', '20140907 18:27:17', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZTQHK', 'RZBJ', 'E', null, 1, 'ADMIN', '20140904 20:30:18', 'ADMIN', '20140904 20:30:18', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQFXZC', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 20:28:15', 'ADMIN', '20140904 20:28:15', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQFHCX', 'RQLX', 'E', null, 1, 'ADMIN', '20140904 20:28:44', 'ADMIN', '20140904 20:28:44', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZTQHK', 'RZLX', 'E', null, 1, 'ADMIN', '20140904 20:30:05', 'ADMIN', '20140904 20:30:05', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZMQHK', 'RZBJ', 'E', null, 1, 'ADMIN', '20140904 20:30:36', 'ADMIN', '20140904 20:30:36', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZFX', 'RZLX', 'E', null, 1, 'ADMIN', '20140904 20:31:28', 'ADMIN', '20140904 20:31:28', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZDFWF', 'YYSZ', 'E', null, 1, 'zhangyuanfeng', '20140610 14:11:17', 'zhangyuanfeng', '20140610 14:11:21', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QSK_ZQF', 'QSKX', 'E', null, 1, 'sys', '20140810 16:11:29', 'sys', '20140810 16:11:31', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZHGLF', 'QTFY', 'E', null, 1, 'sys', '20130219 15:08:04', 'sys', '20130219 15:07:37', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QTL_KHF', 'QTFY', 'E', null, 1, 'sys', '20130201 10:39:50', 'sys', '20130201 10:39:54', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QTL_HKF', 'QTFY', 'E', null, 1, 'sys', '20130129 15:50:53', 'sys', '20130129 15:50:58', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_YYS', 'YYSF_ZQ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_DWF', 'YYSF_ZQ', 'E', null, 1, 'BZ', '20141012 15:16:32', 'BZ', '20141012 15:16:32', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_CJS', 'YYSF_ZQ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_JYFJF', 'YYSF_ZQ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_BCF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_SHF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_ZCF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_HDZCF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_HDSGF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_WYF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_WYFGZC', 'XSSZ_K', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_SHFGZC', 'XSSZ_K', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_BCFGZC', 'XSSZ_K', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YFL_YJ', 'JJYF_YJ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YJL_JYJ', 'JJYF_YJ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JGSXF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JGYJ', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JYYJ', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_SJF', 'YTDT', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_XXPLF', 'YTDT', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_LSF', 'YTDT', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_GLF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TGF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_BGF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_XSF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_YJBCF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TZGWF', 'YYSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_GHF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JYF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JYSXF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JSFWF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JSUF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YFL_FXJ', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_ZGF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JSOF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_YHS', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_SXF', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_RGF', 'XSSZ_F', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XSL_SGF', 'XSSZ_F', 'S', null, 1, 'sys', '20121220 16:40:06', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QTL_ZQLX', 'QTSZ', 'S', null, 1, 'sys', '20130110 17:41:59', 'sys', '20130110 17:42:05', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_LXS', 'YYSF_ZQ', 'E', null, 1, 'sys', '20121226 15:21:12', 'sys', '20121226 15:21:33', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'SFL_XJS', 'YYSF_ZJ', 'E', null, 1, 'sys', '20121226 15:21:29', 'sys', '20121226 15:21:33', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YFL_BZJ', 'QTSZ', 'E', null, 1, 'sys', '20130105 10:29:09', 'guanzhibin', '20130618 16:57:46', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_SSF', 'YTDT', 'E', null, 1, 'sys', '20130115 17:40:47', 'sys', '20130115 17:40:51', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_ZTGF', 'JJYF_FY', 'E', null, 1, 'xifeimin', '20131120 17:12:00', 'xifeimin', '20131120 17:12:02', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_FXZBJ', 'YYSZ', 'E', null, 1, 'meipeng', '20140617 14:06:38', 'meipeng', '20140617 14:06:41', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RZLXZC', 'RZLX', 'E', null, 1, 'KSW', '20140919 16:03:43', 'KSW', '20140919 16:03:43', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_YYFWF', 'YYSZ', 'E', null, 1, 'ZJ', '20140920 17:38:02', 'ZJ', '20140920 17:38:02', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_TDF', 'QTFY', 'E', null, 1, 'ZJ', '20141011 11:21:48', 'ZJ', '20141011 11:21:48', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QTL_QT', 'QTFY', 'E', null, 1, 'ZJ', '20141011 11:43:35', 'ZJ', '20141011 11:43:35', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_ZXFWF', 'QTFY', 'E', null, 1, 'ZJ', '20141011 11:27:58', 'ZJ', '20141011 11:27:58', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_WDSXF', 'QTFY', 'E', null, 1, 'ZJ', '20141011 11:32:53', 'ZJ', '20141011 11:32:53', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_YHSXF', 'QTFY', 'E', null, 1, 'LH', '20141009 20:13:50', 'LH', '20141009 20:13:50', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'DTFY_STCX', 'HDSY', 'E', null, 1, 'lizepu', '20141214 19:06:46', 'lizepu', '20141214 19:06:46', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_QTF', 'JJYF_FY', 'E', null, 1, 'BZ', '20141118 01:26:27', 'BZ', '20141118 01:26:27', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'DTFY_HXSH', 'HDSY', 'E', null, 1, 'ZB2', '20141226 14:39:39', 'ZB2', '20141226 14:39:39', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'DTFY_LHXH', 'HDSY', 'E', null, 1, 'ZB2', '20141226 18:25:11', 'ZB2', '20141226 18:25:11', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TDJTDSY', 'YYSZ', 'E', null, 1, 'lizepu', '20150411 16:27:12', 'lizepu', '20150411 16:27:12', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TDJFDSY', 'YYSZ', 'E', null, 1, 'lizepu', '20150411 16:27:24', 'lizepu', '20150411 16:27:24', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_FDXSF', 'YYSZ', 'E', null, 1, 'huangyixin', '20150113 16:28:03', 'huangyixin', '20150113 16:28:03', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSCCF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSYBF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSCQF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSWYJ', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSDYF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSSTS', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TZGWYJBCF', 'YYSZ', 'E', null, 1, 'tanxy3', '20150428 14:22:05', 'tanxy3', '20150428 14:22:05', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_ZXFWF', 'YYSZ', 'E', null, 1, 'lizepu', '20141214 14:28:05', 'lizepu', '20141214 14:28:05', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_YXJFY', 'YYSZ', 'E', null, 1, 'lizepu', '20141220 15:40:06', 'lizepu', '20141220 15:40:06', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JYSXF_SQS', 'JJYF_FY', 'S', ' ', 1, 'sys', '20141125 18:00:00', 'sys', '20141125 18:00:00', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZQL_JSFWF_SQS', 'JJYF_FY', 'S', ' ', 1, 'sys', '20141125 18:00:00', 'sys', '20141125 18:00:00', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZRT_ZRZGHWY', 'ZRZLX', 'E', null, 1, 'chenli', '20150215 11:43:35', 'chenli', '20150215 11:44:38', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZRT_ZRZJSWY', 'ZRZLX', 'E', null, 1, 'chenli', '20150215 11:43:46', 'chenli', '20150215 11:44:38', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZRT_ZRQJSWY', 'ZRQLX', 'E', null, 1, 'chenli', '20150215 11:44:03', 'chenli', '20150215 11:44:38', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZRT_ZRQGHWY', 'ZRQLX', 'E', null, 1, 'chenli', '20150215 11:44:25', 'chenli', '20150215 11:44:38', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZJZJTQ_ZJ', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'ZJZJTQ_TQ', 'JJYF_FY', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_DBZJLXSR', 'RZLX', 'E', null, 1, 'zh', '20150325 13:51:19', 'zh', '20150325 13:51:19', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQQYJX', 'RQLX', 'E', null, 1, 'zh', '20150325 13:51:37', 'zh', '20150325 13:51:37', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_RQQYLXZC', 'RQLX', 'E', null, 1, 'zh', '20150325 13:51:52', 'zh', '20150325 13:51:52', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YFL_QSGHF', 'JJYF_YJ', 'S', null, 1, 'sys', '20150318 11:06:16', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSCCF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSYBF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSCQF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSWYJ', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSDYF', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_GJSSTS', 'QTSZ', 'S', null, 1, 'sys', '20121203 15:12:08', 'sys', null, '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_XZFW', 'YYSZ', 'E', null, 1, 'liyanchen', '20150610 11:59:49', 'liyanchen', '20150610 11:59:49', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_KHWH', 'YYSZ', 'E', null, 1, 'liyanchen', '20150611 10:51:38', 'liyanchen', '20150611 10:51:38', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_FKYYGW', 'YYSZ', 'E', null, 1, 'liyanchen', '20150611 10:54:22', 'liyanchen', '20150611 10:54:22', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_YDRQGH', 'RZLX', 'E', null, 1, 'QY', '20151117 16:14:51', 'QY', '20151117 16:14:51', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_YDRQGH', 'RQLX', 'E', null, 1, 'QY', '20151117 16:15:20', 'QY', '20151117 16:15:20', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_XSF', 'YTDT', 'E', null, 1, '020266', '20151123 13:07:16', '020266', '20151123 13:07:16', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'RZRQ_YDRQ', 'RQLX', 'E', null, 1, 'QY', '20151117 16:16:26', 'QY', '20151117 16:16:26', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_XZFW', 'YTDT', 'E', null, 1, '020266', '20151123 13:06:57', '020266', '20151123 13:06:57', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TZGWF', 'YTDT', 'E', null, 1, '020266', '20151123 13:07:20', '020266', '20151123 13:07:20', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'QTL_QHSBF', 'QTSZ', 'S', '期货申报费', 1, 'sys', '20150803 00:00:00', 'sys', '20150803 00:00:00', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_SJF', 'YYSZ', 'E', null, 1, 'QY', '20150929 17:56:48', 'QY', '20150929 17:56:48', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_TGF', 'YTDT', 'E', null, 1, '020266', '20151123 13:06:17', '020266', '20151123 13:06:17', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_GLF', 'YTDT', 'E', null, 1, '020266', '20151123 20:58:33', '020266', '20151123 20:58:33', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZSSYF', 'YYSZ', 'E', null, 1, 'yanghaiming', '20160310 14:01:55', 'yanghaiming', '20160310 14:01:59', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZSSYF', 'YTDT', 'E', null, 1, 'liuyuntao', '20160311 19:41:11', 'liuyuntao', '20160311 19:41:17', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YTL_ZHWHF', 'YTDT', 'E', null, 1, 'QY', '20160313 16:14:41', 'QY', '20160313 16:14:48', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'YYL_ZHGLF', 'YTDT', 'E', null, 1, 'sys', '20160512 18:11:48', 'sys', '20160512 18:11:53', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XWBZJ_DPZQ', 'QTFY', 'E', null, 1, 'sys', '20160509 11:52:18', 'sys', '20160509 11:52:43', '[root]')")
				.addSql("insert into t_p_Bi_Ie_Rela (C_IDEN, C_FEE_CODE, C_IE_CODE, C_SRC_MARK, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_FEE_CODE_P) values ( sequ_p_Bi_Ie_Rela.Nextval, 'XWBZJ_XXZQ', 'QTFY', 'E', null, 1, 'sys', '20160509 11:52:39', 'sys', '20160509 11:52:43', '[root]')")
				.endScript();
	}
	
	/**
	 * 交易市场设置
	 * @param builder
	 */
	private void buildJYSCSZ(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("00000")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XCFE', '中国银行间交易市场', 'null', '银行间', 'OTC', 'XCFE', 'CY', 'CHN', 0, 0, 'null', ' ', 'null', 1, 'yssautotest', '20191126 14:45:38', 'yssautotest', '20191126 14:45:38', 'CY') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'HKCG', '港股通联合市场', 'null', '港股通联合市场', 'FTM', 'HKCG', 'GGT', 'CHN', 0, 2, 'null', ' ', 'null', 1, 'sys', '20170113 11:26:06', 'yss', '20191118 20:10:04', 'HG') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'BATS', '巴兹全球市场', null, '巴兹全球', 'FTM', 'BATS', 'US', 'USA', 0, 0, null, ' ', null, 1, 'admin', '20181213 17:25:29', 'admin', '20181213 17:35:27', 'UF') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'GOTC', '海外柜台交易市场', ' ', '海外柜台交易', '[root]', 'GOTC', 'CN', '0US', 0, 0, ' ', ' ', ' ', 1, 'yssautotest', '20191126 14:20:02', 'yssautotest', '20191126 14:20:02', 'GOTC1') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'COTC', '中国柜台交易市场', 'null', '柜台交易', 'OTC', 'COTC', 'CN', 'CHN', 0, 0, 'null', ' ', 'null', 1, 'sys', '20170222 17:39:01', 'sys', '20170222 17:39:04', 'OTC') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XSHG', '上海证券交易所', 'null', '上交所', 'FTM', 'XSHG', 'CN', 'CHN', 0, 1, 'null', ' ', 'null', 1, 'sys', '20130419 12:02:17', 'sys', '20130423 15:48:01', 'SH') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XSHE', '深圳证券交易所', 'null', '深交所', 'FTM', 'XSHE', 'CN', 'CHN', 0, 1, 'null', ' ', 'null', 1, 'sys', '20130423 17:03:58', 'sys', '20130423 17:04:02', 'SZ') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XSES', '新加坡证券交易所', 'null', '新加坡证券交易所', 'FTM', 'XSES', 'SP', 'SGP', 0, 3, 'null', ' ', 'null', 1, 'sys', '20130618 15:21:05', 'sys', '20130618 15:24:59', 'SGP') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XHKG', '香港联合交易所', null, '港交所', 'FTM', 'XHKG', 'HK', 'CHN', 0, 2, null, ' ', null, 1, 'yss', '20191118 11:30:28', 'yss', '20191118 20:00:46', 'HK') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XNZE', '新西兰证券交易所', 'null', '新西兰交易所', 'FTM', 'XNZE', 'NZ', 'NZL', 0, 3, 'null', 'null', 'null', 1, 'sys', '20130618 15:23:43', 'sys', '20130618 15:24:59', 'NZ') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XASX', '澳大利亚证券交易所', 'null', '澳交所', 'FTM', 'XASX', 'AU', 'AUS', 0, 3, 'null', 'null', 'null', 1, 'sys', '20130618 15:17:57', 'sys', '20130618 15:24:59', 'AU') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XSGE', '上海期货交易所', 'null', '上期所', 'FTM', 'XSGE', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20140914 10:59:34', 'sys', '20140914 10:59:38', 'SQ') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'NEEQ', '全国中小企业股转转让市场', 'null', '新三板市场', 'FTM', 'NEEQ', 'CN', 'CHN', 0, 0, 'null', ' ', 'null', 1, 'sys', '20141205 19:33:57', 'sys', '20141205 19:34:04', 'OC') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'AMEX', '美国证券交易所', 'null', 'US', 'FTM', 'AMEX', 'US', 'USA', 0, 2, 'null', ' ', 'null', 1, 'sys', '20180827 11:02:05', 'sys', '20180827 11:02:10', 'US') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XPAR', '巴黎交易所', 'null', 'FP', 'FTM', 'XPAR', 'CN', 'FRA', 0, 0, 'null', 'null', 'null', 1, 'sys', '20160509 17:51:52', 'sys', '20160509 17:51:54', 'FP') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XAMM', '芝加哥商品交易所', 'null', '芝加哥商品交易所', 'FTM', 'XAMM', 'CN', 'USA', 0, 0, 'null', ' ', 'null', 1, 'sys', '20171123 15:01:39', 'sys', '20171123 15:01:53', 'CBT') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XETR', '德国证券交易所', 'null', '德国交易所', 'FTM', 'XETR', 'JW', '0DE', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160614 17:06:27', 'sys', '20160614 17:08:33', 'GY') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XTAI', '台湾交易所', 'null', '台湾交易所', 'FTM', 'XTAI', 'CN', 'CTN', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160729 15:57:34', 'sys', '20160729 15:57:36', 'TT') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'NASDAQ', '纳斯达克证券交易所', 'null', '纳斯达克', 'FTM', 'NASDAQ', 'US', '0US', 0, 3, 'null', ' ', 'null', 1, 'liujunli', '20190523 15:41:09', 'liujunli', '20190523 15:41:16', 'US') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'SHCH', '上海清算所', 'null', '上海清算所', 'FTM', 'SHCH', 'CN', 'CHN', 0, 1, 'null', ' ', 'null', 1, 'sys', '20140611 10:49:41', 'sys', '20160614 17:08:33', 'SQ') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'OOTC', '美国其它场外交易市场', 'null', '美国场外', 'FTM', 'OOTC', 'US', '0US', 0, 0, 'null', ' ', 'null', 1, 'sys', '20161208 14:00:03', 'sys', '20161214 22:01:07', 'NA') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'IFCA', '加拿大证券交易所', 'null', '加拿大交易所', 'FTM', 'IFCA', 'JW', '0CA', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160614 17:06:22', 'sys', '20160614 17:08:33', 'CAD') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XAMS', '泛欧阿姆斯特丹交易所', 'null', '阿姆斯特丹交易所', 'FTM', 'XAMS', 'JW', '0NL', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160729 15:25:16', 'sys', '20160729 15:25:19', 'NA') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XSWX', '瑞士证券交易所', 'null', '瑞士交易所', 'FTM', 'XSWX', 'US', 'CHE', 0, 0, 'null', 'null', 'null', 1, 'sys', '20161208 19:48:33', 'sys', '20161208 19:48:38', 'SW') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XIME', '台湾交易所', 'null', '台交所', 'FTM', 'XIME', 'CN', 'CTN', 0, 2, 'null', ' ', 'null', 1, 'sys', '20140521 16:12:37', 'sys', '20140521 16:12:48', 'TW') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'SWX', '瑞士证券交易所', 'null', '瑞士交易所', 'FTM', 'SWX', 'SW', 'CHE', 0, 3, 'null', 'null', 'null', 1, 'sys', '20131026 15:32:58', 'sys', '20131026 15:32:58', 'SW') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'ZZSJYSC', '增值税交易市场', 'null', '增值税市场', 'ZZS', 'ZZSJYSC', 'CN', 'CHN', 0, 1, 'null', ' ', 'null', 1, 'sys', '20130419 12:02:17', 'sys', '20180101 16:41:07', 'ZZSSC') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'HKCS', '深港通联合市场', 'null', '深港通联合市场', 'FTM', 'HKCS', 'GGT', 'CHN', 0, 2, 'null', ' ', 'null', 1, 'yss', '20190722 19:29:04', 'yss', '20191118 20:00:55', 'HS') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'FDER', '境外衍生品交易市场', 'null', '境外衍生品市场', 'FTM', 'FDER', 'JW', '0US', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160923 10:44:56', 'sys', '20160923 10:45:24', 'FDER') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XVES', '明讯银行', 'null', '明讯银行', 'FTM', 'XVES', 'JW', '0US', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160923 10:44:56', 'sys', '20160923 10:45:24', 'CEDE') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'EUCL', '欧洲清算所', 'null', '欧洲清算所', 'FTM', 'EUCL', 'JW', '0US', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160923 10:44:56', 'sys', '20160923 10:45:24', 'ECLR') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'SINE', '上海能源交易所', 'null', '上海能源交易所', 'FTM', 'SINE', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20170729 15:06:33', 'sys', '20170729 15:06:37', 'SC') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XDCE', '大连商品交易所', 'null', '大连商品交易所', 'FTM', 'XDCE', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20140901 15:27:42', 'sys', '20140901 15:27:45', 'DCE') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'CCFX', '中国金融期货交易所', 'null', '中金所', 'FTM', 'CCFX', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20140829 13:52:59', 'sys', '20140829 13:53:02', 'CFX') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XZCE', '郑州商品交易所', 'null', '郑州商品交易所', 'FTM', 'XZCE', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20140829 18:59:47', 'sys', '20140829 18:59:55', 'CZC') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XLON', '英国伦敦证券交易所', 'null', '英国交易所', 'FTM', 'XLON', 'JW', '0GB', 0, 0, 'null', ' ', 'null', 1, 'sys', '20160614 17:06:32', 'sys', '20161208 19:14:17', 'LN') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XNYS', '美国纽约证券交易所', 'null', '纽约证券交易所', 'FTM', 'XNYS', 'US', '0US', 0, 0, 'null', ' ', 'null', 1, 'sys', '20161219 16:34:42', 'sys', '20161219 16:34:47', 'UN') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XTKS', 'Tokyo Stock Exchange', 'null', 'Tokyo Exchange', 'FTM', 'XTKS', 'JP', '0JP', 0, 0, 'null', ' ', 'null', 1, 'liujunli', '20190430 14:42:06', 'liujunli', '20190430 14:42:08', 'JP') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'SGEX', '上海黄金交易所', 'null', '上海黄金交易所', 'FTM', 'SGEX', 'CN', 'CHN', 0, 1, 'null', ' ', 'null', 1, 'sys', '20141024 20:50:36', 'sys', '20160614 17:08:33', 'SGEX') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'SHPJ', '上海票据交易所', 'null', '上海票据交易所', 'FTM', 'SHPJ', 'CN', 'CHN', 0, 0, 'null', 'null', 'null', 1, 'sys', '20190917 08:09:53', 'sys', '20190917 08:09:53', 'SP') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XMIL', '意大利证券交易所', null, '意大利证券交易所', 'FTM', 'XMIL', 'CN', 'ITA', 0, 0, null, ' ', null, 1, 'sys', '20180716 22:54:00', 'sys', '20180912 11:37:47', 'IM') ")
		.addSql(" insert into t_p_bi_mkt (C_IDEN, C_MKT_CODE, C_MKT_NAME, C_MKT_NAME_EN, C_MKT_NAME_ST, C_DV_MKT_TYPE, C_DE_CODE, C_HDAY_CODE, C_AREA_CODE, N_MKT_ATTR, N_SETT_DAYS, C_SWIFT_CODE, C_FIX_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_MKT_NO)values (sequ_p_Bi_Mkt.Nextval, 'XLUX', '卢森堡证券交易所', null, '卢森堡证券交易所', 'FTM', 'XLUX', 'CN', 'LUX', 0, 0, null, ' ', null, 1, 'sys', '20180728 03:45:39', 'sys', '20180912 11:37:47', 'LX') ")
		.endScript();
	}
	
	/**
	 * 地区信息设置
	 * @param builder
	 */
	private void buildDQXXSZ(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'EU', 'Europe', '欧洲', 'Europe', '[root]', ' ', null, 1, 'sysdbo', '20120526 18:06:27', 'sysdbo', '20120526 18:06:27')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SA', 'South America', '南美洲', 'South America', '[root]', ' ', null, 1, 'sysdbo', '20120526 18:06:27', 'sysdbo', '20120526 18:06:27')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NA', 'North America', '北美洲', 'North America', '[root]', ' ', null, 1, 'sysdbo', '20120526 18:06:27', 'sysdbo', '20120526 18:06:27')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'OA', 'Oceania', '大洋洲', 'Oceania', '[root]', ' ', null, 1, 'sysdbo', '20120526 18:06:27', 'sysdbo', '20120526 18:06:27')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CHN', 'CN', '中国大陆', 'Mainland,China', 'AS', '156', null, 1, 'yanpengtao', '20130228 11:24:05', 'yanpengtao', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AIA', 'AI', '安圭拉', 'Anguilla', 'NA', '660', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ATG', 'AG', '安提瓜和巴布达', 'Antigua and Barbuda', 'NA', '28', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BRB', 'BB', '巴巴多斯', 'Barbados', 'NA', '52', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BMU', 'BM', '百慕大', 'Bermuda', 'NA', '60', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BHS', 'BS', '巴哈马', 'Bahamas', 'NA', '44', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BLZ', 'BZ', '伯利兹', 'Belize', 'NA', '84', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CAN', 'CA', '加拿大', 'Canada', 'NA', '124', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CR', 'CR', '哥斯达黎加', 'Costa Rica ', 'NA', '188', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CUB', 'CU', '古巴', 'Cuba', 'NA', '192', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GRD', 'GD', '格林纳达', 'Grenada', 'NA', '308', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GRL', 'GL', '格陵兰', 'Greenland', 'NA', '304', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GTM', 'GT', '危地马拉', 'Guatemala ', 'NA', '320', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'HND', 'HN', '洪都拉斯', 'Honduras', 'NA', '340', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'HTI', 'HT', '海地', 'Haiti', 'NA', '332', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KNA', 'KN', '圣基茨和尼维斯', 'Saint Kitts and nevis', 'NA', '659', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CYM', 'KY', '开曼群岛', 'Cayman Islands', 'NA', '136', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LCA', 'LC', '圣卢西亚', 'Saint lucia', 'NA', '662', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MSR', 'MS ', '蒙特塞拉特', 'Montserrat', 'NA', '500', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MEX', 'MX', '墨西哥', 'Mexico', 'NA', '484', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NIC', 'NI', '尼加拉瓜', 'Nicaragua', 'NA', '558', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SPM', 'PM', '圣皮埃尔和密克隆', 'Saint Pierre and Miquelon', 'NA', '666', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PRI', 'PR', '波多黎各', 'Puerto Rico', 'NA', '630', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SLV', 'SV', '萨尔瓦多', 'El Salvador', 'NA', '222', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TCA ', 'TC', '特克斯科斯群岛', 'Turks and Caicos Islands', 'NA', '796', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TTO', 'TT', '特立尼达和多巴哥', 'Trinidad and Tobago', 'NA', '780', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'USA', 'US', '美国', 'United States', 'NA', '840', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'VCT', 'VC', '圣文森特和格林纳丁斯', 'Saint Vincent and the Grenadines', 'NA', '670', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ASM', 'AS', '美属萨摩亚', 'American Samoa', 'OA', '16', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AUS', 'AU', '澳大利亚', 'Australia', 'OA', '36', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CCK', 'CC', '科科斯(基林)群岛', 'Cocos(Keeling) Islands', 'OA', '166', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'COK', 'CK', '库克群岛', 'Cook Islands', 'OA', '184', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CSR', 'CS', '圣诞岛', 'Christmas Island', 'OA', '162', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FJI', 'FJ', '斐济', 'Fiji', 'OA', '242', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FSM', 'FM', '密克罗尼西亚', 'Micronesia', 'OA', '583', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GUM', 'GU', '关岛', 'Guam ', 'OA', '316', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KIR ', 'KI', '基里巴斯', 'Kiribati', 'OA', '296', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MHL', 'MH', '马绍尔群岛', 'Marshall Islands', 'OA', '584', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MNP ', 'MP', '北马里亚纳', 'Northern Marianas', 'OA', '580', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NCL', 'NC', '新喀里多尼亚', 'New Caledonia', 'OA', '540', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NFK', 'NF', '诺福克岛', 'Norfolk Island', 'OA', '574', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NRU', 'NR', '瑙鲁', 'Nauru', 'OA', '520', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NIU', 'NU', '纽埃', 'Niue', 'OA', '570', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NZL', 'NZ', '新西兰', 'New Zealand ', 'OA', '554', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PNG', 'PG', '巴布亚新几内亚', 'Papua New Guinea ', 'OA', '598', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PCN', 'PN', '皮特凯恩群岛', 'Pitcairn Islands Group', 'OA', '612', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PLW', 'PW', '贝劳', 'Palau', 'OA', '585', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SLB', 'SB', '所罗门群岛', 'Solomon Islands', 'OA', '90', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TKL', 'TK', '托克劳', 'Tokelau', 'OA', '772', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'VUT', 'VU ', '瓦努阿图', 'Vanuatu', 'OA', '548', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'WSM', 'WS', '西萨摩亚', 'Western Samoa', 'OA', '882', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AGO', 'AO', '安哥拉', 'Angola', 'AF', '24', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BFA ', 'BF', '布基纳法索', 'Burkina Faso', 'AF', '854', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BDI', 'BI', '布隆迪', 'Burundi', 'AF', '108', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BEN ', 'BJ', '贝宁', 'Benin', 'AF', '204', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BWA', 'BW', '博茨瓦纳', 'Botswana', 'AF', '72', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CAF ', 'CF', '中非', 'Central Africa', 'AF', '140', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'COG', 'CG', '刚果', 'Congo', 'AF', '178', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CIV', 'CI', '科特迪瓦', 'Cote d''Ivoire', 'AF', '384', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CMR', 'CM', '喀麦隆', 'Cameroon', 'AF', '120', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CPV', 'CV', '佛得角', 'Cape Verde ', 'AF', '132', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DJI', 'DJ', '吉布提', 'Djibouti', 'AF', '262', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DZA', 'DZ', '阿尔及利亚', 'Algeria', 'AF', '12', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'EGY', 'EG', '埃及', 'Egypt', 'AF', '818', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ESH', 'EH', '西撒哈拉', 'Western Sahara', 'AF', '732', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ERI', 'ER', '厄立特里亚', 'Eritrea', 'AF', '232', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ETH', 'ET', '埃塞俄比亚', 'Ethiopia', 'AF', '231', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GAB', 'GA', '加蓬', 'Gabon', 'AF', '266', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GHA', 'GH', '加纳', 'Ghana', 'AF', '288', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GMB', 'GM', '冈比亚', 'Gambia ', 'AF', '270', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GIN', 'GN', '几内亚', 'Guinea', 'AF', '324', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GNQ', 'GQ', '赤道几内亚', 'Equatorial Guinea ', 'AF', '226', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GNB', 'GW', '几内亚比绍', 'Guine-bissau ', 'AF', '624', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'JAM', 'JM', '牙买加', 'Jamaica', 'AF', '388', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KEN', 'KE', '肯尼亚', 'Kenya', 'AF', '404', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'COM', 'KM', '科摩罗', 'Comoros', 'AF', '174', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LBR', 'LR', '利比里亚', 'Liberia', 'AF', '430', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LSO', 'LS', '莱索托', 'Lesotho', 'AF', '426', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LBY', 'LY', '利比亚', 'Libya', 'AF', '434', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MAR', 'MA', '摩洛哥', 'Morocco', 'AF', '504', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MDG', 'MG', '马达加斯加', 'Madagascar', 'AF', '450', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MLI', 'ML', '马里', 'Mali', 'AF', '466', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MRT', 'MR', '毛里塔尼亚', 'Mauritania', 'AF', '478', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MUS', 'MU', '毛里求斯', 'Mauritius', 'AF', '480', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MWI', 'MW', '马拉维', 'Malawi', 'AF', '454', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MOZ', 'MZ', '莫桑比克', 'Mozambique', 'AF', '508', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NAM', 'NA', '纳米比亚', 'Namibia', 'AF', '516', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NER', 'NE', '尼日尔', 'Niger', 'AF', '562', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NGA', 'NG', '尼日利亚', 'Nigeria', 'AF', '566', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'REU', 'RE', '留尼汪', 'Reunion', 'AF', '638', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'RWA', 'RW', '卢旺达', 'Rwanda', 'AF', '646', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SYC', 'SC', '塞舌尔', 'Seychells ', 'AF', '690', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SDN', 'SD', '苏丹', 'Sudan', 'AF', '736', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SHN', 'Sh', '圣赫勒拿', 'Saint helena', 'AF', '654', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SLE', 'SL', '塞拉利昂', 'Sierra leone ', 'AF', '694', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SEN', 'SN', '塞内加尔', 'Senegal', 'AF', '686', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SOM', 'SO', '索马里', 'Somalia', 'AF', '706', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'STp', 'St', '圣多美和普林西比', 'Sao Tome and Principe    ', 'AF', '678', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SWZ', 'SZ', '斯威士兰', 'Swaziland', 'AF', '748', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TCD', 'TD', '乍得', 'Chad ', 'AF', '148', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TGO', 'TG', '多哥', 'Togo', 'AF', '768', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TUN', 'TN', '突尼斯', 'Tunisia', 'AF', '788', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TZA', 'TZ', '坦桑尼亚', 'Tanzania', 'AF', '834', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'UGA', 'UG', '乌干达', 'Uganda ', 'AF', '800', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MYT', 'YT', '马约特', 'Mayotte', 'AF', '175', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ZAF', 'ZA', '南非', 'South Africa ', 'AF', '710', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ZMB', 'ZM', '赞比亚', 'Zambia', 'AF', '894', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ZAR', 'ZR', '扎伊尔', 'Zaire', 'AF', '180', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ZWE', 'ZW', '津巴布韦', 'Zimbabwe', 'AF', '716', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ANT', 'AN', '荷属安的列斯', 'Netherlands Antilles ', 'SA', '530', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ARG', 'AR', '阿根廷', 'Argentina', 'SA', '32', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ABW', 'AW', '阿鲁巴', 'Aruba', 'SA', '533', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BOL', 'BO ', '玻利维亚', 'Bolivia', 'SA', '68', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BRA', 'BR', '巴西', 'Brazil', 'SA', '76', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CHL', 'CL', '智利', 'Chile ', 'SA', '152', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'COL', 'Co', '哥伦比亚', 'Colombia', 'SA', '170', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DMA', 'DM', '多米尼克', 'Dominica ', 'SA', '212', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DOM ', 'DO', '多米尼加共和国', 'Dominican Republic', 'SA', '214', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ECU', 'EC', '厄瓜多尔', 'Ecuador', 'SA', '218', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FLK', 'FK', '马尔维纳斯群岛(福克兰群岛)', 'Malvinas Islands (Falkland Islands)', 'SA', '238', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GUF', 'GF', '法属圭亚那', 'French Guiana', 'SA', '254', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GLP', 'GP', '瓜德罗普', 'Guadeloupe', 'SA', '312', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SGS', 'GS', '南乔治亚岛和南桑德韦奇岛', 'South Georgia and South Sandwich Islands     ', 'SA', '239', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GUY ', 'GY', '圭亚那', 'Guyana', 'SA', '328', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MTQ', 'MQ', '马提尼克', 'Martinique', 'SA', '474', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PAN', 'PA', '巴拿马', 'Panama', 'SA', '591', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PER', 'PE', '秘鲁', 'Peru', 'SA', '604', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PRY', 'PY', '巴拉圭', 'Paraguay', 'SA', '600', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SUR', 'SR', '苏里南', 'Suriname', 'SA', '740', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'URY', 'UY', '乌拉圭', 'Uruguay', 'SA', '858', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'VEN', 'VE', '委内瑞拉', 'Venezuela', 'SA', '862', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AND', 'AD ', '安道尔', 'Andorra', 'EU', '20', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ALB', 'AL', '阿尔巴尼亚', 'Albania', 'EU', '8', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AUT', 'AT', '奥地利', 'Austria', 'EU', '40', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BIH', 'BA', '波斯尼亚和黑塞哥维那', 'Bosnia and Herzegovina', 'EU', '70', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BEL ', 'BE ', '比利时', 'Belgium', 'EU', '56', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BGR', 'BG', '保加利亚', 'Bulgaria', 'EU', '100', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BLR', 'BY', '白俄罗斯', 'Belarus', 'EU', '112', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CHE', 'CH', '瑞士', 'Switzerland ', 'EU', '756', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CYP', 'CY', '塞浦路斯', 'Cyprus', 'EU', '196', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CZE', 'CZ', '捷克', 'Czech Repoublic', 'EU', '203', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DEU', 'DE ', '德国', 'Germany', 'EU', '276', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'DNK', 'DK', '丹麦', 'Denmark', 'EU', '208', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'EST', 'EE', '爱沙尼亚', 'Estonia', 'EU', '233', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ESP', 'ES', '西班牙', 'Spain ', 'EU', '724', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FIN', 'FI', '芬兰', 'Finland', 'EU', '246', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FRO', 'FO', '法罗群岛', 'Faroe Islands ', 'EU', '234', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'FRA', 'FR', '法国', 'France', 'EU', '250', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GBR', 'GB ', '英国', 'United Kingdom', 'EU', '826', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GIB ', 'GI', '直布罗陀', 'Gibraltar ', 'EU', '292', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GRC', 'GR', '希腊', 'Greece', 'EU', '300', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'HRV', 'HR', '克罗地亚', 'Croatia', 'EU', '191', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'HUN', 'HU', '匈牙利', 'Hungary', 'EU', '348', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'IRL', 'IE', '爱尔兰', 'Ireland', 'EU', '372', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ISL', 'IS', '冰岛', 'Iceland', 'EU', '352', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ITA', 'IT', '意大利', 'Italy ', 'EU', '380', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LIE', 'LI', '列支敦士登', 'Liechtenstein ', 'EU', '438', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LTU', 'LT', '立陶宛', 'Lithuania', 'EU', '440', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LUX', 'LU', '卢森堡', 'Luxembourg', 'EU', '442', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LVA', 'LV', '拉脱维亚', 'Latvia', 'EU', '428', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MCO', 'MC', '摩纳哥', 'Monaco', 'EU', '492', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MDA ', 'MD', '摩尔多瓦', 'Moldova', 'EU', '498', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MKD', 'MK', '马斯顿', 'Macedonia', 'EU', '807', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MLT', 'MT', '马耳他', 'Malta', 'EU', '470', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NLD', 'NL', '荷兰', 'Netherlands', 'EU', '528', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NOR', 'NO', '挪威', 'Norway', 'EU', '578', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AF', 'Africa', '非洲', 'Africa', '[root]', null, null, 1, 'zp', '20120526 18:06:27', 'zp', '20120526 18:06:27')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'POL', 'PL', '波兰', 'Poland', 'EU', '616', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PRT', 'PT', '葡萄牙', 'Portugal', 'EU', '620', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ROM', 'RO', '罗马尼亚', 'Romania', 'EU', '642', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'RUS', 'RU', '俄罗斯', 'Russia', 'EU', '643', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SWE', 'SE', '瑞典', 'Sweden', 'EU', '752', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SVN', 'SI', '斯洛文尼亚', 'Slovenia', 'EU', '705', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SJM', 'SJ', '斯瓦尔巴群岛', 'Svalbard and jan Mayen Islands', 'EU', '744', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SVK', 'SK', '斯洛伐克', 'Slovakia ', 'EU', '703', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SMR', 'SM', '圣马力诺', 'San Marion', 'EU', '674', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'UKR', 'UA', '乌克兰', 'Ukraine ', 'EU', '804', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'VAT', 'VA', '梵蒂冈', 'Vatican', 'EU', '336', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'YUG', 'YU', '南斯拉夫', 'Yugoslavia', 'EU', '891', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ARE', 'AE', '阿闻酋', 'United Arab Emirates', 'AS', '784', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AFG ', 'AF', '阿富汗', 'Afghanistan', 'AS', '4', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ARM', 'AM', '亚美尼亚', 'Armenia', 'AS', '51', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AZE', 'AZ', '阿塞拜疆', 'Azerbaijan', 'AS', '31', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BGD ', 'BD', '孟加拉国', 'Bangladesh', 'AS', '50', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BHR', 'BH', '巴林', 'Bahrain', 'AS', '48', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BRN', 'BN', '文莱', 'Brunei Darussalam', 'AS', '96', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'BTN', 'BT', '不丹', 'Bhutan', 'AS', '64', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'CTN', 'CT', '中国台湾', 'Taiwan,China', 'AS', '158', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'GEO', 'GE', '格鲁吉亚', 'Georgia', 'AS', '268', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'HKG', 'HK', '中国香港', 'Hong Kong,China', 'AS', '344', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'IDN', 'ID', '印度尼西亚', 'Indonesia', 'AS', '360', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'ISR', 'IL', '以色列', 'Israel', 'AS', '376', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'IND', 'IN', '印度', 'India', 'AS', '356', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'IRQ', 'IQ', '伊拉克', 'Iraq', 'AS', '368', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'IRN', 'IR', '伊朗', 'Iran ', 'AS', '364', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'JOR', 'JO', '约旦', 'Jordan', 'AS', '400', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'JPN ', 'JP', '日本', 'Japan', 'AS', '392', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KGZ', 'KG', '吉尔吉斯斯坦', 'Kyrgyzstan', 'AS', '417', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KHM', 'KH', '柬埔寨', 'Cambodia', 'AS', '116', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PRK ', 'KP', '朝鲜', 'Korea,Democratic People''s Republic of', 'AS', '408', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KOR', 'KR', '韩国', 'Korea,Republic of', 'AS', '410', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KWT', 'KW', '科威特', 'Kuwait', 'AS', '414', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'KAZ', 'KZ', '哈萨克斯坦', 'Kazakhstan', 'AS', '398', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LAO', 'LA', '老挝', 'Lao', 'AS', '418', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LBN', 'LB', '黎巴嫩', 'Lebanon', 'AS', '422', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'LKA', 'LK', '斯里兰卡', 'Sri Lanka', 'AS', '144', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MMR', 'MM', '缅甸', 'Myanmar', 'AS', '104', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MNG', 'MN', '蒙古', 'Mongolia', 'AS', '496', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MAC', 'MO', '中国澳门', 'Macau,China', 'AS', '446', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MDV', 'MV', '马尔代夫', 'Maldives', 'AS', '462', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'MYS ', 'MY', '马来西亚', 'Malaysia', 'AS', '458', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'NPL', 'NP', '尼泊尔', 'Nepal', 'AS', '524', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'OMN', 'OM', '阿曼', 'Oman', 'AS', '512', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PHL', 'PH', '菲律宾', 'Philippines', 'AS', '608', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PAK', 'PK', '巴基斯坦', 'Pakistan ', 'AS', '586', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'PST', 'PS', '巴勒斯坦', 'Palestine', 'AS', '374', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'QAT', 'QA', '卡塔尔', 'Qatar', 'AS', '634', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SAU', 'SA', '沙特阿拉伯', 'Saudi Arabia', 'AS', '682', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SGP', 'SG', '新加坡', 'Singapore', 'AS', '702', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'SYR', 'SY', '叙利亚', 'Syria', 'AS', '760', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'THA', 'TH', '泰国', 'Thailand ', 'AS', '764', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TJK', 'TJ', '塔吉克斯坦', 'Tajikistan', 'AS', '762', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TKM', 'TM', '土库曼斯坦', 'Turkmenistan ', 'AS', '795', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TMP', 'TP', '东帝汶', 'East Timor ', 'AS', '626', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'TUR', 'TR', '土耳其', 'Turkey', 'AS', '792', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'UZB', 'UZ', '乌兹别克斯坦', 'Uzbekistan ', 'AS', '860', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'VNM', 'VN', '越南', 'Viet Nam ', 'AS', '704', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'YEM', 'YE', '也门', 'Yemen', 'AS', '887', null, 1, 'zp', '20130228 11:24:05', 'zp', '20130228 11:24:09')")
				.addSql("insert into t_p_bi_area (C_IDEN, C_AREA_CODE, C_AREA_CODE_EN, C_AREA_NAME, C_AREA_NAME_EN, C_AREA_CODE_P, C_AREA_SEQ, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) values ( sequ_p_bi_area.Nextval, 'AS', 'Asia', '亚洲', 'Asia', '[root]', ' ', null, 1, 'sysdbo', '20120526 18:06:27', 'sysdbo', '20120526 18:06:27')")
				.endScript();
	}
	
	/**
	 * 板块信息
	 * @param builder
	 */
	private void buildBKXX(ScriptBuilder builder){
		//t_p_Bi_Plate 板块信息
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")
				.addSql("insert into t_p_bi_plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values (sequ_p_bi_plate.nextval, 'SAC', '证监协会', '[root]', null, 1, 'SYSTEM', to_char(sysdate,'yyyyMMdd hh24:mm:ss'), null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '20', '木材加工及木、竹、藤、棕、草制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30047')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '21', '家具制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30048')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '22', '造纸及纸制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '23', '印刷和记录媒介复制业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30050')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '24', '文教、工美、体育和娱乐用品制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30051')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '25', '石油加工、炼焦及核燃料加工业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30052')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '26', '化学原料及化学制品制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30053')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '27', '医药制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30054')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '28', '化学纤维制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30055')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '29', '橡胶和塑料制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30056')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '30', '非金属矿物制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30057')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '31', '黑色金属冶炼及压延加工业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30058')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '32', '有色金属冶炼及压延加工业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30059')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '33', '金属制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30060')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '34', '通用设备制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30061')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '35', '专用设备制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30062')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '36', '汽车制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30063')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '37', '铁路、船舶、航空航天和其它运输设备制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30064')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '38', '电气机械及器材制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30065')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '58', '装卸搬运和其他运输代理业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '59', '仓储业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '60', '邮政业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'H', '住宿和餐饮业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30036')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '61', '住宿业', 'H', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30036')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '62', '餐饮业', 'H', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30036')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'I', '信息传输、软件和信息技术服务业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11044')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '63', '电信、广播电视和卫星传输服务', 'I', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11044')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '64', '互联网和相关服务', 'I', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11044')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '65', '软件和信息技术服务业', 'I', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11044')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'J', '金融业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '66', '货币金融服务', 'J', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '67', '资本市场服务', 'J', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '68', '保险业', 'J', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '69', '其他金融业', 'J', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'K', '房地产业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11047')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '70', '房地产业', 'K', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11047')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'L', '租赁和商务服务业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30037')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '71', '租赁业', 'L', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30037')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '72', '商务服务业', 'L', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30037')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'M', '科学研究和技术服务业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30038')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '39', '计算机、通信和其他电子设备制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30066')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '40', '仪器仪表制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30067')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '41', '其他制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '42', '废弃资源综合利用业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '43', '金属制品、机械和设备修理业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'D', '电力、热力、燃气及水的生产和供应业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11041')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '44', '电力、热力生产和供应业', 'D', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11041')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '45', '燃气生产和供应业', 'D', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11041')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '46', '水的生产和供应业', 'D', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11041')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'E', '建筑业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '47', '房屋建筑业', 'E', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '48', '土木工程建筑业', 'E', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '49', '建筑安装业', 'E', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '50', '建筑装饰和其他建筑业', 'E', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'F', '批发和零售业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11045')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '51', '批发业', 'F', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11045')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '52', '零售业', 'F', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11045')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'G', '交通运输、仓储和邮政业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '53', '铁路运输业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '54', '道路运输业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '55', '水上运输业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '56', '航空运输业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '57', '管道运输业', 'G', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '75', '科技推广和应用服务业', 'M', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30038')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'N', '水利、环境和公共设施管理业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30039')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '76', '水利管理业', 'N', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30039')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '77', '生态保护和环境治理业', 'N', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30039')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '78', '公共设施管理业', 'N', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30039')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'O', '居民服务、修理和其他服务业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '79', '居民服务业', 'O', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '80', '机动车、电子产品和日用产品修理业', 'O', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '81', '其他服务业', 'O', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'P', '教育', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '82', '教育', 'P', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Q', '卫生和社会工作', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '83', '卫生', 'Q', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '84', '社会工作', 'Q', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'R', '文化、体育和娱乐业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '85', '新闻和出版业', 'R', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '86', '广播、电视、电影和影视录音制作业', 'R', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '87', '文化艺术业', 'R', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '88', '体育', 'R', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '89', '娱乐业', 'R', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11049')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'S', '综合', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11050')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '90', '综合', 'S', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11050')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'A', '农、林、牧、渔业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '01', '农业', 'A', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '02', '林业', 'A', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '03', '牧业', 'A', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '04', '渔业', 'A', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '05', '农、林、牧、渔服务业', 'A', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11030')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'B', '采矿业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '06', '煤炭开采和洗选业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '07', '石油和天然气开采业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '08', '黑色金属矿采选业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '09', '有色金属矿采选业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '10', '非金属矿采选业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '11', '开采辅助活动', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '12', '其他采矿业', 'B', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H11031')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'C', '制造业', 'SAC', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '13', '农副食品加工业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30041')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '14', '食品制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30042')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '15', '酒、饮料和精制茶制造业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30043')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '16', '烟草制品业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '17', '纺织业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30044')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '18', '纺织服装、服饰业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30045')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, '19', '皮革、毛皮、羽毛及其制品和制鞋业', 'C', null, 1, 'ADMIN', '20140126 13:00:00', null, null, null, null, 'SEC_ASSOCIATION', 'H30046')")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'GICS', 'GICS', '[root]', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Energy', 'Energy', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Basic Materials', 'Basic Materials', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Industrials', 'Industrials', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Consumer Cyclicals', 'Consumer Cyclicals', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Consumer Non-Cyclicals', 'Consumer Non-Cyclicals', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Financials', 'Financials', 'GICS', null, 1, 'YSS', '20160711 07:07:48', 'YSS', '20160711 07:07:48', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Healthcare', 'Healthcare', 'GICS', null, 1, 'YSS', '20160711 07:07:49', 'YSS', '20160711 07:07:49', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Technology', 'Technology', 'GICS', null, 1, 'YSS', '20160711 07:07:49', 'YSS', '20160711 07:07:49', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Telecommunications Services', 'Telecommunications Services', 'GICS', null, 1, 'YSS', '20160711 07:07:49', 'YSS', '20160711 07:07:49', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.addSql("insert into t_p_Bi_Plate (C_IDEN, C_PLATE_CODE, C_PLATE_NAME, C_PLATE_CODE_P, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, D_BEGIN, D_END, C_PLATE_TYPE, C_INDEX_CODE) values ( sequ_p_Bi_Plate.Nextval, 'Utilities', 'Utilities', 'GICS', null, 1, 'YSS', '20160711 07:07:49', 'YSS', '20160711 07:07:49', to_date('01-01-1990', 'dd-mm-yyyy'), to_date('31-12-9998', 'dd-mm-yyyy'), 'GICS', null)")
				.endScript();
	}

	
	/**
	 * 功能界面funcode 
	 * @param builder
	 */
	private void buildFun(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("00000")

//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_area', '地区信息设置', 'baseInfo', 4, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'area', '维护地区字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_baseSourceRecord', '信息来源设置', 'baseInfo', 0, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'baseSourceRecord', '维护信息来源字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_currency', '国际货币设置', 'baseInfo', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'currency', '维护币种字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_currencyPair', '货币对设置', 'baseInfo', 10, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'currencyPair', '维护币种之间的对应关系，用于计算汇率', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_exchange', '交易市场设置', 'baseInfo', 5, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'exchange', '维护交易市场字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_holidays', '节假日群设置', 'baseInfo', 3, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'holidays', '维护节假日信息，用于计算工作日', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_holidays_A', '节假日群类型', 'baseInfo', 3, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'holidays', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_ie', '收支项目设置', 'baseInfo', 7, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'ie', '维护收支代码字典信息，用于收支项目的清算或核算', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_ieLink', '收支分类设置', 'baseInfo', 8, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'ieLink', '维护收支项目下的各类收支代码，用于收支结转设置中的收支代码选择', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_organ', '主体信息设置', 'baseInfo', 2, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'organ', '上半区：维护机构基本信息 下半区：维护机构的理财产品、产品账户、电子对账参数等信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_orgmgr', '结算会员设置', 'baseInfo', 11, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'orgmgr', '对机构设置结算会员信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_taselnet', '产品销售网点', 'baseInfo', 9, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'taselnet', '维护产品销售的销售网点信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_zdorg', '转换字典组织架构', 'sourceManager', 3, 'ENAB', 'PUB', 'MEU_AID', 'D_DZ', null, 'S', null, 'zdorg', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
//						+ " values ('base_zhzd', '转换字典', 'sourceManager', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'zhzd', '外部系统中的代码数据与估值系统做代码转换，主要用于数据导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//				 
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('CONFIG', '配置管理', '[root]', 12, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'CONFIG', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('PARA', '参数管理', '[root]', 3, 'ENAB', 'PUB', 'MOD_FUN', 'P_YW', null, 'S', null, 'CS', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('baseInfo', '基本信息设置', 'PARA', 1, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'baseInfo', null, 1, 0, 1, 1, SEQU_s_fun.Nextval, 'YSSUCO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
//
//				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
//						+ " values ('sourceManager', '资源管理', 'CONFIG', 3, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, null, null, 1, 0, 1, 1, SEQU_s_fun.Nextval, null, null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
// 
				
				.addSql(" delete from t_s_fun a where a.c_fun_code in "
				+"('base_area','base_baseSourceRecord','base_currency','base_currencyPair','base_exchange','base_holidays','base_holidays_A','base_ie','base_ieLink','base_organ','base_orgmgr','base_taselnet','base_zdorg','base_zhzd','BASE_CONFIG','BASE_baseInfo','BASE_sourceManager')")
						 
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_exchange', '交易市场设置', 'BASE_baseInfo', 5, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'exchange', '维护交易市场字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_holidays', '节假日群设置', 'BASE_baseInfo', 3, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'holidays', '维护节假日信息，用于计算工作日', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_holidays_A', '节假日群类型', 'BASE_baseInfo', 3, 'ENAB', 'PUB', 'MEU_AID', 'P_YW', null, 'S', null, 'holidays', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_ie', '收支项目设置', 'BASE_baseInfo', 7, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'ie', '维护收支代码字典信息，用于收支项目的清算或核算', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_ieLink', '收支分类设置', 'BASE_baseInfo', 8, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'ieLink', '维护收支项目下的各类收支代码，用于收支结转设置中的收支代码选择', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_area', '地区信息设置', 'BASE_baseInfo', 4, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'area', '维护地区字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_baseSourceRecord', '信息来源设置', 'BASE_baseInfo', 0, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'baseSourceRecord', '维护信息来源字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_currency', '国际货币设置', 'BASE_baseInfo', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'currency', '维护币种字典信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_currencyPair', '货币对设置', 'BASE_baseInfo', 10, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'currencyPair', '维护币种之间的对应关系，用于计算汇率', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_organ', '主体信息设置', 'BASE_baseInfo', 2, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'organ', '上半区：维护机构基本信息 下半区：维护机构的理财产品、产品账户、电子对账参数等信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_orgmgr', '结算会员设置', 'BASE_baseInfo', 11, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'orgmgr', '对机构设置结算会员信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_zdorg', '转换字典组织架构', 'BASE_sourceManager', 3, 'ENAB', 'PUB', 'MEU_AID', 'D_DZ', null, 'S', null, 'zdorg', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_zhzd', '转换字典', 'BASE_sourceManager', 3, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'zhzd', '外部系统中的代码数据与估值系统做代码转换，主要用于数据导入', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('BASE_CONFIG', '基础组件管理', '[root]', 12, 'ENAB', 'PUB', 'MOD_FUN', 'D_YW', null, 'S', null, 'BASE_CONFIG', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('BASE_baseInfo', '基本信息设置', 'BASE_CONFIG', 1, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, 'BASE_baseInfo', null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('BASE_sourceManager', '资源管理', 'BASE_CONFIG', 3, 'ENAB', 'PUB', 'CLS_MOD', 'P_YW', null, 'S', null, null, null, 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_taselnet', '产品销售网点', 'BASE_baseInfo', 9, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'taselnet', '维护产品销售的销售网点信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN', -1.000000000000000)")
				
				/*.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('base_fundAccInfo', '银行账户信息', 'unifyPay', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, 'BASE_baseInfo', '银行账户信息', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', 'POPIN',-1.000000000000000)")
				*/
				.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)"
				+ " values ('pd_relaPort', '账户关联产品', 'PD_productInfo', 1, 'ENAB', 'PUB', 'MEU_FUN', 'P_YW', null, 'S', null, 'portfolio', null, 0, 0, 0, 0, sequ_s_fun.nextval, null, null, 'MENU_INNER', 'POPIN', 1.000000000000000)")
				
				.addSql(" delete from t_s_fun where c_fun_code = 'base_accountTreeA'")
				.addSql(" insert into t_s_fun (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG) "
						+ " values (SEQU_S_FUN.Nextval, 'base_accountTreeA', '账户树形结构A区', 'unifyPay', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, null, null, 0, 0, 0, 0, null, null, 'MENU_INNER', 'POPIN', null, null, null)")
				.addSql(" delete from t_s_fun where c_fun_code = 'base_accountTreeB'")
				.addSql(" insert into t_s_fun (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG) "
						+ " values (SEQU_S_FUN.Nextval, 'base_accountTreeB', '账户树形结构', 'unifyPay', 6, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, 'S', null, null, null, 0, 0, 0, 0, null, null, 'MENU_INNER', 'POPIN', null, null, null)")
				//.addSql(" update t_s_fun set N_RECYCLE = 1 where c_fun_code = 'base_fundAccInfo' and N_RECYCLE = 0 ")		
						
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'ADD') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'CPY') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'DEL') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'EPT') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'SAVE') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'UPD') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'CHK') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeA', 'UCHK') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'ADD') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'CPY') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'DEL') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'EPT') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'SAVE') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'UPD') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'CHK') ")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)values ('base_accountTreeB', 'UCHK') ")
				
				
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_area', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_baseSourceRecord', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currency', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currency', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currency', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_currencyPair', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_exchange', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'GRE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_holidays_A', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ie', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_ieLink', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_organ', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_orgmgr', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_sectorChild_A', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_taselnet', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zdorg', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE)"
						+ " values ('base_zhzd', 'UPD')")
				/*.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'ADD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'CHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'CPY')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'EPT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'RFH')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'UCHK')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('base_fundAccInfo', 'UPD')")*/
//				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
//						+ " values ('pd_relaPort', 'UPD')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('pd_relaPort', 'DEL')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('pd_relaPort', 'SAVE')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('pd_relaPort', 'PNT')")
				.addSql(" insert into t_s_fun_rights (C_FUN_CODE, C_DV_OPER_TYPE) "
						+ " values ('pd_relaPort', 'RFH')")
				.addSql(" delete from  t_s_fun_rights where C_FUN_CODE = 'pd_relaPort' "
						+ " and C_DV_OPER_TYPE = 'UPD' ")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_organ', '主体信息设置', 'JBXX', 'FUN_APP', 1, 1, 6, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_organ', '主体信息设置', 'baseP', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_ie', '收支项目设置', 'baseInfo', 'FUN_APP', 1, 1, 6, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_ieLink', '收支分类设置', 'baseInfo', 'FUN_APP', 1, 1, 7, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_taselnet', '销售网点设置', 'pdSell', 'FUN_APP', 1, 1, 6, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_zhzd', '转换字典', 'liquidateBusiness', 'FUN_APP', 1, 1, 7, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_orgmgr', '结算会员设置', 'baseInfo', 'FUN_APP', 1, 1, 3, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_taselnet', '销售网点设置', 'JBXX', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_holidays', '节假日群设置', 'baseInfo', 'FUN_APP', 1, 1, 2, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_exchange', '交易市场设置', 'baseInfo', 'FUN_APP', 1, 1, 1, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_currency', '国际货币设置', 'baseInfo', 'FUN_APP', 1, 1, 4, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_currencyPair', '货币对设置', 'baseInfo', 'FUN_APP', 1, 1, 5, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_area', '地区信息设置', 'baseInfo', 'FUN_APP', 1, 1, 0, null, 'SystemApp')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_orgmgr', '结算会员设置', 'baseInfo', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_taselnet', '销售网点设置', 'JBXX', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_zhzd', '转换字典', 'liquidateBusiness', 'FUN_BIZ', 1, 1, 7, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_taselnet', '销售网点设置', 'pdSell', 'FUN_BIZ', 1, 1, 6, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_area', '地区信息设置', 'baseInfo', 'FUN_BIZ', 1, 1, 0, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_currency', '国际货币设置', 'baseInfo', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_ie', '收支项目设置', 'baseInfo', 'FUN_BIZ', 1, 1, 6, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_exchange', '交易市场设置', 'baseInfo', 'FUN_BIZ', 1, 1, 1, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_holidays', '节假日群设置', 'baseInfo', 'FUN_BIZ', 1, 1, 2, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_ieLink', '收支分类设置', 'baseInfo', 'FUN_BIZ', 1, 1, 7, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_organ', '主体信息设置', 'baseP', 'FUN_BIZ', 1, 1, 3, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_organ', '主体信息设置', 'JBXX', 'FUN_BIZ', 1, 1, 6, null, 'SystemBiz')")
//				.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE, C_PLAN_CODE) "
//						+ " values (sequ_s_fun_custom.nextval, 'base_currencyPair', '货币对设置', 'baseInfo', 'FUN_BIZ', 1, 1, 5, null, 'SystemBiz')")
						
				.endScript();
	}
	
		/**
	 * 2017-8-17 By Jinghehe 提供字典数据 
	 * @param builder
	 */
	private void buildT_S_DV_VOC(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
				// 版本号
				.addVersion("V1.21.5.0")
				// 需求或者bug标识
				.addUpdateType(UpdateType.REQUEST)
				// 需求或者bug号
				.addID("37444")
				// /-----投资人证件类型-个人
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_SFZ', '身份证', 'TZRGR', '投资人证件类型-个人', 0, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_HZ', '护照', 'TZRGR', '投资人证件类型-个人', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_JUGZ', '军官证', 'TZRGR', '投资人证件类型-个人', 2, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_SBZ', '士兵证', 'TZRGR', '投资人证件类型-个人', 3, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_GATXZ', '港澳居民来往内地通行证', 'TZRGR', '投资人证件类型-个人', 4, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_HKB', '户口本', 'TZRGR', '投资人证件类型-个人', 5, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_WGHZ', '外国护照', 'TZRGR', '投资人证件类型-个人', 6, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_QT', '其它', 'TZRGR', '投资人证件类型-个人', 7, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_WZZ', '文职证', 'TZRGR', '投资人证件类型-个人', 8, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_WGRYJJLZ', '外国人永久居留证', 'TZRGR', '投资人证件类型-个人', 11, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_TBZ', '台胞证', 'TZRGR', '投资人证件类型-个人', 10, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRGR_JGZ', '警官证', 'TZRGR', '投资人证件类型-个人', 9, 'ENAB')")
				// /---------投管人性质
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('ZTSX_JG', '机构', 'ZTSX', '投管人性质', 0, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('ZTSX_GR', '个人', 'ZTSX', '投管人性质', 1, 'ENAB')")
				// /--------投资人证件类型-机构
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_ZZJG', '组织机构代码证', 'TZRJG', '投资人证件类型-机构', 0, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_YYZZ', '营业执照', 'TZRJG', '投资人证件类型-机构', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_XZJG', '行政机关', 'TZRJG', '投资人证件类型-机构', 2, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_SHTT', '社会团体', 'TZRJG', '投资人证件类型-机构', 3, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_JD', '军队', 'TZRJG', '投资人证件类型-机构', 4, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_WJ', '武警', 'TZRJG', '投资人证件类型-机构', 5, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_XSJG', '下属机构(具有主管单位批文号)', 'TZRJG', '投资人证件类型-机构', 6, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_JJH', '基金会', 'TZRJG', '投资人证件类型-机构', 7, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_QT', '其它', 'TZRJG', '投资人证件类型-机构', 8, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_DJZS', '登记证书', 'TZRJG', '投资人证件类型-机构', 9, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TZRJG_PW', '批文', 'TZRJG', '投资人证件类型-机构', 10, 'ENAB')")
				// /--------法人代表证件类型
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_SFZ', '身份证', 'FRDB', '法人代表证件类型', 0, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_HZ', '护照', 'FRDB', '法人代表证件类型', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_JUGZ', '军官证', 'FRDB', '法人代表证件类型', 2, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_SBZ', '士兵证', 'FRDB', '法人代表证件类型', 3, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_GATXZ', '港澳居民来往内地通行证', 'FRDB', '法人代表证件类型', 4, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_HKB', '户口本', 'FRDB', '法人代表证件类型', 5, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_WGHZ', '外国护照', 'FRDB', '法人代表证件类型', 6, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_QT', '其它', 'FRDB', '法人代表证件类型', 7, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_WZZ', '文职证', 'FRDB', '法人代表证件类型', 8, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_JGZ', '警官证', 'FRDB', '法人代表证件类型', 9, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_TBZ', '台胞证', 'FRDB', '法人代表证件类型', 10, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('FRDB_WGRYJJLZ', '外国人永久居留证', 'FRDB', '法人代表证件类型', 11, 'ENAB')")
				
				//开户方式
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_OPEN_MODE_01', '管理人自开', 'TAQS_OPEN_MODE', '开户方式', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_OPEN_MODE_02', '委托人代开', 'TAQS_OPEN_MODE', '开户方式', 2, 'ENAB')")
				//账户状态
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ACC_STATE_01', '正常', 'TAQS_ACC_STATE', '账户状态', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ACC_STATE_02', '预开户', 'TAQS_ACC_STATE', '账户状态', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ACC_STATE_03', '冻结', 'TAQS_ACC_STATE', '账户状态', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ACC_STATE_04', '销户', 'TAQS_ACC_STATE', '账户状态', 1, 'ENAB')")
				//公司账户类型
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_01', '基金托管账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 3, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_02', '上海中登托管户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 33, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_03', '清算总账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 2, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_04', '深圳中登托管户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 34, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_05', '代销总清算账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 35, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_06', '境外清算户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 46, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_07', '募集户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 1, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_09', '上海备付金账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 5, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_10', '深圳备付金账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 6, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_11', '直销备付金账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 10, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_12', '直销中心账户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 4, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_13', '基金税费户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 7, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_18', '上海备付金税费户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 8, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_19', '深圳备付金税费户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 9, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_COMP_ACCOUNT_22', '公司税费户', 'TAQS_COMP_ACCOUNT', '公司账户类型', 22, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_OTH_ACCOUNT_CY', '常用账户', 'TAQS_OTH_ACCOUNT', '其他账户类型', 99, 'ENAB')")
				//机构账户类型
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ORG_ACCOUNT_02', '销售机构资金账户', 'TAQS_ORG_ACCOUNT', '机构账户类型', 12, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ORG_ACCOUNT_03', '销售机构费用账户', 'TAQS_ORG_ACCOUNT', '机构账户类型', 16, 'ENAB')")
				//.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_ORG_ACCOUNT_04', '席位结算编号账户', 'TAQS_ORG_ACCOUNT', '机构账户类型', 26, 'ENAB')")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) values ('TAQS_OTH_ACCOUNT_DZ', '对帐账户', 'TAQS_OTH_ACCOUNT', '其他账户类型', 98, 'ENAB')")
				//add zhangyongzhao 同步数据操作数据类型
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_ADD', '新增', 'SYNC_TYPE', '同步类型', 0, 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_DEL', '删除', 'SYNC_TYPE', '同步类型', 1, 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('SYNC_UPD', '修改', 'SYNC_TYPE', '同步类型', 2, 'ENAB', '[root]') ")
				//add 账户上次使用词汇
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_1M', '一个月', 'DATE_RANGE', '日期区间', '1', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_3M', '三个月', 'DATE_RANGE', '日期区间', '2', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_HALF_Y', '半年', 'DATE_RANGE', '日期区间', '3', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_1Y', '一年', 'DATE_RANGE', '日期区间', '4', 'ENAB', '[root]') ")
				
			    ///STORY #62778 直销客银行账户文件（HSBANKACCO.YYYYMMDD）调整为1.2的接口格式 增加投资人证件类型-产品
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('TZRCP_ZZJGDM', '组织机构代码证', 'TZRCP', '投资人证件类型-产品', 0, 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('TZRCP_QT', '其它', 'TZRCP', '投资人证件类型-产品', 1, 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('TZRCP_PW', '批文', 'TZRCP', '投资人证件类型-产品', 2, 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('TZRCP_DJZS', '登记证书', 'TZRCP', '投资人证件类型-产品', 3, 'ENAB', '[root]') ")
				//add 账户上次使用词汇
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_1M', '一个月', 'DATE_RANGE', '日期区间', '1', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_3M', '三个月', 'DATE_RANGE', '日期区间', '2', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_HALF_Y', '半年', 'DATE_RANGE', '日期区间', '3', 'ENAB', '[root]') ")
				.addSql(" insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE) values ('FUNDACC_1Y', '一年', 'DATE_RANGE', '日期区间', '4', 'ENAB', '[root]') ")
				.endScript();
	}
	
	
	
	
	
	
	/**
	 *迁移旧产品账户关联关系到新产品银行账户关联关系表
	 * @param builder
	 */
	private void buildUpdateT_S_DAC_TYPE(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
				.addVersion("1.21.5.2")
				.addUpdateType(UpdateType.REQUEST)
				.addID("000000")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZCCGZH', '资产存管账户', '[root]', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZCCGZH_BASE', '基本账户', 'CPZH_ZCCGZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TGH', '托管户', 'CPZH_ZCCGZH_BASE', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_DVP_SQS', '上清所银行间账户', 'CPZH_ZCCGZH_BASE', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_DVP_ZZ', '中债银行间账户', 'CPZH_ZCCGZH_BASE', 3, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZQZJZH', '证券资金账户', 'CPZH_ZCCGZH_BASE', 4, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZCCGZH_OTHER', '其他交易存管账户', 'CPZH_ZCCGZH', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZQBZJZH', '证券保证金账户', 'CPZH_ZCCGZH_OTHER', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QHBFJZH', '期货户', 'CPZH_ZCCGZH_OTHER', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_CKH', '存款账户', 'CPZH_ZCCGZH_OTHER', 3, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QHGSBZJZH', '期货公司保证金账户', 'CPZH_ZCCGZH_OTHER', 4, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QHZH', '期货账户', 'CPZH_ZCCGZH_OTHER', 5, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_FYLZH', '费用类账户', '[root]', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TGFH', '托管费户', 'CPZH_FYLZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_GLFH', '管理费户', 'CPZH_FYLZH', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_XSF', '销售服务费账户', 'CPZH_FYLZH', 3, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_BBFXZBJ', '保本风险准备金账户', 'CPZH_FYLZH', 5, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TGFFXZBJ', '托管费风险准备金账户', 'CPZH_FYLZH', 6, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZSSYF', '指数使用费账户', 'CPZH_FYLZH', 7, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TZGWF', '投资顾问费账户', 'CPZH_FYLZH', 8, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_YJBCF', '业绩报酬费账户', 'CPZH_FYLZH', 9, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_SJF', '审计费账户', 'CPZH_FYLZH', 10, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZBXXPLF', '中证报信息披露费账户', 'CPZH_FYLZH', 11, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZQXXPLF', '证券时报信息披露费账户', 'CPZH_FYLZH', 12, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_SBXXPLF', '上证报信息披露费账户', 'CPZH_FYLZH', 13, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZQRBPLF', '证券日报信息披露费账户', 'CPZH_FYLZH', 14, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_SSF', '上市费账户', 'CPZH_FYLZH', 15, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JYSXF', '外汇交易中心手续费账户', 'CPZH_FYLZH', 16, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JSFWF', '中债结算服务费', 'CPZH_FYLZH', 17, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JSFWF_SQS', '上清所结算服务费', 'CPZH_FYLZH', 18, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_XMGLF', '项目管理费账户', 'CPZH_FYLZH', 19, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TZGWYJBCF', '浮动投资顾问费账户', 'CPZH_FYLZH', 20, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_FDXMGLF', '浮动项目管理费账户', 'CPZH_FYLZH', 21, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_FDXSF', '浮动销售服务费账户', 'CPZH_FYLZH', 22, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_KHF', '开户费账户', 'CPZH_FYLZH', 23, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QSYJZH', '券商佣金账户', 'CPZH_FYLZH', 24, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_STF', '受托费账户', 'CPZH_FYLZH', 25, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JYSXF_SQS', '上清所交易手续费账户', 'CPZH_FYLZH', 26, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZHJZXJH', '专户及专项计划', '[root]', 3, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_WTRZH', '委托人账户', 'CPZH_ZHJZXJH', 1, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_TZBDZH', '投资标的账户', 'CPZH_ZHJZXJH', 2, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QSLZH', '清算类账户', '[root]', 4, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZQSH', '总清算户', 'CPZH_QSLZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QSH', '清算户', 'CPZH_QSLZH', 2, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_MJZH', '募集账户', 'CPZH_QSLZH', 3, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZXH', '直销账户', 'CPZH_QSLZH', 4, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QSGSZH', '公司账户', 'CPZH_QSLZH', 6, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_FEEQSZH', '费用清算账户', 'CPZH_QSLZH', 5, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_QSLZH_ZDTA', '中登TA账户', 'CPZH_QSLZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZD_SH', '中登上海备付金账户', 'CPZH_QSLZH_ZDTA', 1, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZD_SZ', '中登深圳备付金账户', 'CPZH_QSLZH_ZDTA', 2, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZDBZJ_SH', '中登上海保证金账户', 'CPZH_QSLZH_ZDTA', 3, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZDBZJ_SZ', '中登深圳保证金账户', 'CPZH_QSLZH_ZDTA', 4, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZDFEE_SH', '中登上海费用账户', 'CPZH_QSLZH_ZDTA', 5, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZDFEE_SZ', '中登深圳费用账户', 'CPZH_QSLZH_ZDTA', 6, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_WTLZH', '委贷类账户', '[root]', 5, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_WDZH', '委贷类账户', 'CPZH_WTLZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JGXSLZH', '机构销售类账户', '[root]', 6, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_DXJGZJZH', '销售资金往来账户', 'CPZH_JGXSLZH', 1, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_DXJGFEEZH', '代销机构手续费账户', 'CPZH_JGXSLZH', 2, 'CHZH_ZHMXFL', 'ENAB', 'TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_GSCWZH', '公司财务账户', '[root]', 7, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_FXZBJ', '管理风险准备金账户', 'CPZH_GSCWZH', 1, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_YBH', '一般户', 'CPZH_GSCWZH', 2, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_JBH', '基本户', 'CPZH_GSCWZH', 3, 'CHZH_ZHMXFL', 'ENAB', null) ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_JYLZH', '交易类账户', '[root]', 8, 'CHZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_JYLZH_BASE', '基本交易账户', 'JYZH_JYLZH', 1, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_BOND_SQS', '上清所债券账户', 'JYZH_JYLZH_BASE', 1, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_BOND_ZZ', '中债债券账户', 'JYZH_JYLZH_BASE', 2, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_SECURITYZH', '证券账户', 'CPZH_ZCCGZH_BASE', 3, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_JYLZH_OTHER', '其他交易账户', 'JYZH_JYLZH', 4, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_IPO_SH', '新股上海网下发行专户', 'JYZH_JYLZH', 5, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_IPO_SZ', '新股深圳网下发行专户', 'JYZH_JYLZH', 6, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_FUNDZH', '基金账户', 'CPZH_ZCCGZH_OTHER', 1, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN, C_DAC_CODE, C_DAC_NAME, C_DAC_CODE_P, N_ORDER, C_DAC_TYPE, C_DV_STATE, C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'JYZH_FUTUREZH', '期货账户', 'JYZH_JYLZH_OTHER', 2, 'JYZH_ZHMXFL', 'ENAB', 'FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_JGXSZH','开放式基金收款账户','CPZH_TZBDZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				
				/* 初始化库失败，报唯一约束，重复
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_TZBDZH','非标类账户','CPZH_TZBDZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				*/
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZDSHKZDZH','中登赎回款指定账户','CPZH_ZHJZXJH','2','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZJTA','自建TA总清算户','CPZH_QSLZH_ZDTA','1','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZQSH_ZDSH','中登上海总清算户','CPZH_QSLZH_ZDTA','1','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZQSH_ZDSZ','中登深圳总清算户','CPZH_QSLZH_ZDTA','1','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZXH_GT','柜台','CPZH_ZXH','4','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZXH_DS','电商','CPZH_ZXH','4','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZXH_ZD','中登','CPZH_ZXH','4','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZXH_DZ','垫资','CPZH_ZXH','4','CHZH_ZHMXFL','ENAB','TA') ")
				
				/* 初始化库失败，报唯一约束，重复
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_MJZH','募集期','CPZH_QSLZH','3','CHZH_ZHMXFL','ENAB','TA') ")
				*/
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZSGL_TGH','托管行','CPZH_ZSGL','2','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_WBJG','外部机构','CPZH_ZSGL','2','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZSGL','证书管理','[root]','1','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZSGL_TGH_TA','托管行','CPZH_ZSGL_TA','2','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_WBJG_TA','外部机构','CPZH_ZSGL_TA','2','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZSGL_TA','证书管理','[root]','1','CHZH_ZHMXFL','ENAB','TA') ")
				
				.addSql("update T_S_DAC_TYPE set C_DAC_NAME = '一对一委托人账户' where C_DAC_CODE='CPZH_WTRZH' ")
				
				
				.addSql("update T_S_DAC_TYPE set C_DAC_NAME = '其他投资标的收款账户' where C_DAC_CODE='CPZH_TZBDZH' ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZYCKZYZH','协议存款自有账户','CPZH_ZCCGZH_OTHER','2','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_XPF','信批费','CPZH_FYLZH','2','CHZH_ZHMXFL','ENAB','FA') ")
				
				.addSql("update T_S_DAC_TYPE set C_DAC_CODE_P = 'CPZH_XPF' where C_DAC_CODE in ('CPZH_ZBXXPLF','CPZH_ZQXXPLF','CPZH_SBXXPLF','CPZH_ZQRBPLF') ")
				
				
				//.addSql("update T_S_DAC_TYPE set C_DAC_CODE_P = 'CPZH_ZQSH' where C_DAC_CODE in ('CPZH_ZDFEE_SZ','CPZH_ZD_SZ','CPZH_ZQSH_ZDSH','CPZH_ZJTA','CPZH_ZD_SH','CPZH_ZDBZJ_SZ','CPZH_ZQSH_ZDSZ','CPZH_ZDBZJ_SH','CPZH_ZDFEE_SH') ")
				
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE in ('CPZH_ZQSH_ZDSH','CPZH_ZJTA','CPZH_ZQSH_ZDSZ') ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE in ('CPZH_ZDFEE_SZ','CPZH_ZD_SZ','CPZH_ZD_SH','CPZH_ZDBZJ_SZ','CPZH_ZDBZJ_SH','CPZH_ZDFEE_SH') ")
				
				.addSql(" update T_S_DAC_TYPE set C_ACC_TYPE = '' where C_DAC_CODE = 'CPZH_QSLZH_ZDTA' ")
				
				/*update zhangyongzhao  账户管理*/
				/*update zhangyongzhao 2018-04-24 BUG #200191 产品账户信息B区界面‘账号类型’显示错误*/
//				.addSql(" delete from T_S_DAC_TYPE t where t.C_DAC_CODE = 'CPZH_CKH' ")
				 
				/*update zhangyongzhao 2018.3.20*/
				.addSql(" update T_S_DAC_TYPE set C_DAC_CODE_P = 'CPZH_QSLZH_ZDTA' where C_DAC_CODE in ('CPZH_ZQSH_ZDSZ','CPZH_ZQSH_ZDSH','CPZH_ZJTA','CPZH_ZD_SH','CPZH_ZDFEE_SZ','CPZH_ZDFEE_SH','CPZH_ZDBZJ_SZ','CPZH_ZDBZJ_SH','CPZH_ZD_SZ') ")
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '总清算账户', C_ACC_TYPE='TA' where C_DAC_CODE = 'CPZH_QSLZH_ZDTA' ")
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '募集户' where C_DAC_CODE = 'CPZH_MJZH' ")
				
				/*update zhangyongzhao 2018-04-24 BUG #200191 产品账户信息B区界面‘账号类型’显示错误*/
//				.addSql(" delete from T_S_DAC_TYPE where C_DAC_CODE = 'CPZH_ZQSH' ")
				//add zhangyongzhao 2018-03-30 增加非标类账户 
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_FBLZH','非标类账户','CPZH_TZBDZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				
				//add by zhangyongzhao 20180507 STORY55185【账户管理】上线需求汇总04-02
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_TA','TA总清算户','CPZH_QSLZH_ZDTA','4','CHZH_ZHMXFL','ENAB','TA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
						"values (sequ_S_DAC_TYPE.nextval,'CPZH_FYLZH_TGH','托管费账户','CPZH_FYLZH','28','CHZH_ZHMXFL','ENAB','FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZZS','增值税账户','CPZH_FYLZH','26','CHZH_ZHMXFL','ENAB','FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_GSLZH','固收类账户','[root]','3','CHZH_ZHMXFL','ENAB','FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_GSZH_SH','固收账户（上海）','CPZH_GSLZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_GSZH_SZ','固收账户（深圳）','CPZH_GSLZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				
				//zhangyongzhao 2018-05-31 费用类账户改为费用类收款账户；
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '费用类收款账户' where C_DAC_CODE = 'CPZH_FYLZH' ")
				
				//zhangyongzhao 2018-05-31 信披费账户改为信披费收款账户；
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '信披费收款账户' where C_DAC_CODE = 'CPZH_XPF' ")
				
				//zhangyongzhao 2018-05-31 STORY57366【账户管理】账户名称及排序修改 
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_TGH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '4' where C_DAC_CODE = 'CPZH_DVP_SQS' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_DVP_ZZ' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE = 'JYZH_SECURITYZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '4' where C_DAC_CODE = 'CPZH_ZSGL' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_ZCCGZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE = 'CPZH_FYLZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_ZHJZXJH' ")
				
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '费用类收款账户' where C_DAC_CODE = 'CPZH_FYLZH' ")
				.addSql(" update T_S_DAC_TYPE set C_DAC_NAME = '信披费收款账户' where C_DAC_CODE = 'CPZH_XPF' ")
				
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE = 'CPZH_ZQBZJZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_QHBFJZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '4' where C_DAC_CODE = 'JYZH_FUNDZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_ZYCKZYZH' ")
				
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_FYLZH_TGH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE = 'CPZH_QSYJZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_SJF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '4' where C_DAC_CODE = 'CPZH_SSF'")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '5' where C_DAC_CODE = 'CPZH_JSFWF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '6' where C_DAC_CODE = 'CPZH_JSFWF_SQS' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '7' where C_DAC_CODE = 'CPZH_JYSXF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '8' where C_DAC_CODE = 'CPZH_TZGWF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '9' where C_DAC_CODE = 'CPZH_XPF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_ZBXXPLF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '2' where C_DAC_CODE = 'CPZH_SBXXPLF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_ZQXXPLF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '4' where C_DAC_CODE = 'CPZH_ZQRBPLF' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '3' where C_DAC_CODE = 'CPZH_TZBDZH' ")
				.addSql(" update T_S_DAC_TYPE set N_ORDER = '1' where C_DAC_CODE = 'CPZH_ZSGL_TGH' ")
				
				//xhm 20180627 STORY #57977 【账户管理】账户需求汇总06-13
				//增加账户“指数使用费”、“增值税及附加税费”、“托管行非担保交收备付金账户”
				/* 初始化库失败，报唯一约束，重复
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZSSYF','指数使用费','CPZH_FYLZH','2','CHZH_ZHMXFL','ENAB','FA') ")
				*/
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_ZZSJFJSF','增值税及附加税费','CPZH_FYLZH','3','CHZH_ZHMXFL','ENAB','FA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval,'CPZH_TGHFDBJSBFJ','托管行非担保交收备付金账户','CPZH_ZCCGZH_OTHER','2','CHZH_ZHMXFL','ENAB','FA') ")
				//xhm 20180627 STORY #57977 【账户管理】账户需求汇总06-13
				//TA账户，树形结构中的“总清算账户”去掉；（原本总清算账户项下的账户类型从3级升为2级）
				.addSql(" update T_S_DAC_TYPE set c_dac_code_p = 'CPZH_QSLZH' where c_dac_code_p = 'CPZH_QSLZH_ZDTA' ")
				
				//STORY #58361【账户管理】树形结构中，同类账户合并_AddByXHM
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZDZH', '中登账户', 'CPZH_QSLZH', '2', 'CHZH_ZHMXFL', 'ENAB','TA') ")
				.addSql("insert into T_S_DAC_TYPE (C_IDEN,C_DAC_CODE,C_DAC_NAME,C_DAC_CODE_P,N_ORDER,C_DAC_TYPE,C_DV_STATE,C_ACC_TYPE) "+
				"values (sequ_S_DAC_TYPE.nextval, 'CPZH_ZXH_GS', '公司直销账户', 'CPZH_ZXH', '1', 'CHZH_ZHMXFL', 'ENAB','TA') ")
				
				.addSql(" update T_S_DAC_TYPE set c_dac_code_p = 'CPZH_ZDZH' where c_dac_code IN ('CPZH_ZD_SH', 'CPZH_ZD_SZ', 'CPZH_ZDBZJ_SH', 'CPZH_ZDBZJ_SZ', 'CPZH_ZDFEE_SH', 'CPZH_ZDFEE_SZ') ")
				.addSql(" update T_S_DAC_TYPE set c_dac_code_p = 'CPZH_ZXH_GS' where c_dac_code IN ('CPZH_ZXH_DS', 'CPZH_ZXH_DZ', 'CPZH_ZXH_GT') ")
				.addSql(" update T_S_DAC_TYPE set c_dac_name = '中登直销账户' where c_dac_code = 'CPZH_ZXH_ZD' ")
				.addSql(" update T_S_DAC_TYPE set c_dac_name = '投资标的收款账户' where c_dac_code = 'CPZH_TZBDZH' ")
				
				.endScript();
	}
	
	/**
	 * 账户类型数据表
	 * @param builder
	 */
	private void buildUpdateT_P_AB_PORT_ACC_RELA(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.2")
		.addUpdateType(UpdateType.REQUEST)
		.addID("000000")
		.addSql(" insert into t_p_bi_fund_acc ( "
		+" C_IDEN "
		+" ,C_PORT_CODE "
		+" ,C_CA_CODE "
		+" ,C_DC_CODE "
		+" ,C_OPEN_ADDR "
		+" ,C_OPEN_ACC_NO "
		+" ,C_SYS_CODE "
		+" ,C_USAGE "
		+" ,C_DESC "
		+" ,N_CHECK_STATE "
		+" ,C_UPDATE_BY "
		+" ,C_UPDATE_TIME "
		+" ,C_CHECK_BY "
		+" ,C_CHECK_TIME "
		+" ,C_OPEN_ACC_NAME "
		+" ,D_BEGIN "
		+" ,D_END "
		+" ,C_ORG_CODE "
		+" ,C_HOLDER "
		+" ,C_ASS_CODE "
		+" ,C_HAVEUSED "
		+" ,C_ACCOUNT_TYPE "
		+" ,C_PAY_CODE "
		+" ,C_BANK_CODE "
		+" ,C_BC_LINK_NO "
		+" ,C_PROVINCE "
		+" ,C_STR1 "
		+" ,C_OPEN_CITY "
		+" ,C_INTERFACE_CODE "
		+" ,C_INTER_ORG_CODE "
		+" ,C_CNX "
		+" ,C_DC_NAME "
		+" ,N_NUMBER2 "
		+" ,C_PAY_CHANNEL "
		+" ,C_SWIFT_CODE "
		+" ,C_OWNER "
		+" ,C_STR3 "
		+" ,C_OPEN_MODE "
		+" ,C_CREATE_BY "
		+" ,C_INTERFACE_NAME "
		+" ,C_STR2 "
		+" ,C_OPEN_BANK "
		+" ,C_BANK_ADDR "
		+" ,C_CASH_ACCOUNT "
		+" ,C_CITY "
		+" ,N_NUMBER3 "
		+" ,C_BC_ORG_CODE "
		+" ,C_RUNNING_ACC "
		+" ,C_CREATE_TIME "
		+" ,N_NUMBER1 "
		+" ,C_OPEN_PROVINCE "
		+" ,D_YEARLY_CHECK_DATE "
		+" ,C_BELONG_BANK "
		+" ,C_ASSO_NUM "
		+" ,C_ENABLE "
		+" ,C_IDEN_LINK "
		+" ) "
		+" select  "
		+" SEQU_P_BI_FUND_ACC.NEXTVAL "
		+" ,C_PORT_CODE "
		+" ,C_CA_CODE "
		+" ,C_DC_CODE "
		+" ,C_OPEN_ADDR "
		+" ,C_OPEN_ACC_NO "
		+" ,C_SYS_CODE "
		+" ,C_USAGE "
		+" ,C_DESC "
		+" ,N_CHECK_STATE "
		+" ,C_UPDATE_BY "
		+" ,C_UPDATE_TIME "
		+" ,C_CHECK_BY "
		+" ,C_CHECK_TIME "
		+" ,C_OPEN_ACC_NAME "
		+" ,D_BEGIN "
		+" ,D_END "
		+" ,C_ORG_CODE "
		+" ,C_HOLDER "
		+" ,C_ASS_CODE "
		+" ,C_HAVEUSED "
		+" ,C_ACCOUNT_TYPE "
		+" ,C_PAY_CODE "
		+" ,C_BANK_CODE "
		+" ,C_BC_LINK_NO "
		+" ,C_PROVINCE "
		+" ,C_STR1 "
		+" ,C_OPEN_CITY "
		+" ,C_INTERFACE_CODE "
		+" ,C_INTER_ORG_CODE "
		+" ,C_CNX "
		+" ,C_DC_NAME "
		+" ,N_NUMBER2 "
		+" ,C_PAY_CHANNEL "
		+" ,C_SWIFT_CODE "
		+" ,C_OWNER "
		+" ,C_STR3 "
		+" ,C_OPEN_MODE "
		+" ,C_CREATE_BY "
		+" ,C_INTERFACE_NAME "
		+" ,C_STR2 "
		+" ,C_OPEN_BANK "
		+" ,C_BANK_ADDR "
		+" ,C_CASH_ACCOUNT "
		+" ,C_CITY "
		+" ,N_NUMBER3 "
		+" ,C_BC_ORG_CODE "
		+" ,C_RUNNING_ACC "
		+" ,C_CREATE_TIME "
		+" ,N_NUMBER1 "
		+" ,C_OPEN_PROVINCE "
		+" ,D_YEARLY_CHECK_DATE "
		+" ,C_BELONG_BANK "
		+" ,C_ASSO_NUM "
		+" ,C_ENABLE "
		+" ,'' as C_IDEN_LINK "
		+" from  "
		+" ( "
		+" select * from ( "
		+"    select ROW_NUMBER() OVER(PARTITION BY t.c_Open_Addr,t.c_Open_Acc_No,t.c_Open_Acc_Name,t.c_Dc_Code   "
		+"    order by t.C_ACCOUNT_TYPE,t.C_HAVEUSED,t.C_PAY_CODE,t.C_HOLDER,t.C_USAGE,t.C_DESC desc) as rn, "
		+"    t.* "
		+"    from t_c_cp_fund_acc t)  "
		+"    where rn=1 "
		+" ) where c_iden not in (select c_iden  from t_p_bi_fund_acc)")
		.addSql("insert into T_P_AB_PORT_ACC_RELA (c_iden,c_port_code,C_RELA_CODE,c_account_type) "
		+" select SEQU_P_AB_PORT_ACC_RELA.nextval,a.c_port_code,a.C_RELA_CODE,a.c_account_type "
		+" from ( "
		+"  select ROW_NUMBER() OVER(PARTITION BY t.c_port_code,t.C_RELA_CODE "
		+"  ORDER BY c_account_type desc nulls last) as rn,t.c_port_code,t.C_RELA_CODE,t.c_account_type "
		+"  from (   "
		+"   select distinct c_port_code,C_RELA_CODE,c_dv_type_code as c_account_type "
		+"   from t_p_ab_port_rela where C_RELA_TYPE='RELA_FUND_ACC' "
		+ "  union "
		+ "  select a.c_port_code,b.c_iden as C_RELA_CODE,b.c_account_type "
		+ "  from t_c_cp_fund_acc a "
		+ "  left join t_p_Bi_Fund_Acc b on a.C_OPEN_ACC_NAME=b.C_OPEN_ACC_NAME "
		+ "  and a.C_DC_CODE =b.C_DC_CODE and a.C_OPEN_ADDR =b.C_OPEN_ADDR and a.C_OPEN_ACC_NO=b.C_OPEN_ACC_NO "
		+ "  where trim(a.c_port_code) is not null and b.c_iden is not null "
		+ "  ) t  )a  "
		+" where rn=1 and not exists (select 1 from T_P_AB_PORT_ACC_RELA b "
		+" where a.c_port_code=b.c_port_code and a.C_RELA_CODE=b.C_RELA_CODE) ")
		.endScript();
	}
	
	/**
	 * BUG #252441 【易方达基金】【运维】产品账户信息维护后清算进来的资金换汇业务没有获取到资金账户
	 * 根据账户信息表 和 关联表 插入数据到原始估值使用的账户信息表。
	 * @param builder
	 */
	private void buildUpdateT_C_CP_FUND_ACC(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.3")
		.addUpdateType(UpdateType.BUG)
		.addID("252441")
		.addSql(" insert into  t_c_cp_fund_acc  (  "				
			+ "	C_IDEN  ,C_PORT_CODE ,C_CA_CODE  ,C_DC_CODE    ,C_OPEN_ADDR                                                      "
            + "  ,C_OPEN_ACC_NO ,C_SYS_CODE    ,C_USAGE ,C_DESC ,N_CHECK_STATE                                                   "
            + "  ,C_UPDATE_BY  ,C_UPDATE_TIME  ,C_CHECK_BY ,C_CHECK_TIME  ,C_OPEN_ACC_NAME                                       "
            + "  ,D_BEGIN,D_END,C_ORG_CODE ,C_HOLDER  ,C_ASS_CODE  ,C_HAVEUSED                                                   "
            + "  ,C_ACCOUNT_TYPE  ,C_PAY_CODE  ,C_BANK_CODE ,C_BC_LINK_NO ,C_PROVINCE                                            "
            + "  ,C_STR1  ,C_OPEN_CITY  ,C_INTERFACE_CODE ,C_INTER_ORG_CODE ,C_CNX                                               "
            + "  ,C_DC_NAME ,N_NUMBER2 ,C_PAY_CHANNEL  ,C_SWIFT_CODE ,C_OWNER ,C_STR3 ,C_OPEN_MODE                               "
            + "  ,C_CREATE_BY ,C_INTERFACE_NAME  ,C_STR2 ,C_OPEN_BANK  ,C_BANK_ADDR ,C_CASH_ACCOUNT                              "
            + "  ,C_CITY  ,N_NUMBER3 ,C_BC_ORG_CODE ,C_RUNNING_ACC ,C_CREATE_TIME ,N_NUMBER1                                     "
            + "  ,C_OPEN_PROVINCE ,D_YEARLY_CHECK_DATE ,C_BELONG_BANK ,C_ASSO_NUM  ,C_ENABLE,C_RELA_CODE  )                                  "
            + "  select sequ_c_cp_fund_acc.nextval  ,x.* from                                                                    "
            + " ( select    x.c_port_code ,C_CA_CODE  ,C_DC_CODE    ,C_OPEN_ADDR                                                 "
            + "  ,C_OPEN_ACC_NO ,C_SYS_CODE    ,C_USAGE ,C_DESC ,N_CHECK_STATE                                                   "
            + "  ,T.C_UPDATE_BY  ,T.C_UPDATE_TIME  ,C_CHECK_BY ,C_CHECK_TIME  ,C_OPEN_ACC_NAME                                       "
            + "  ,D_BEGIN,T.D_END,C_ORG_CODE ,C_HOLDER  ,C_ASS_CODE  ,C_HAVEUSED                                                   "
            + "  ,x.c_account_type  ,C_PAY_CODE  ,C_BANK_CODE ,C_BC_LINK_NO ,C_PROVINCE                                          "
            + "  ,C_STR1  ,C_OPEN_CITY  ,C_INTERFACE_CODE ,C_INTER_ORG_CODE ,C_CNX                                               "
            + "  ,C_DC_NAME ,N_NUMBER2 ,C_PAY_CHANNEL  ,C_SWIFT_CODE ,C_OWNER ,C_STR3 ,C_OPEN_MODE                               "
            + "  ,C_CREATE_BY ,C_INTERFACE_NAME  ,C_STR2 ,C_OPEN_BANK  ,C_BANK_ADDR ,C_CASH_ACCOUNT                              "
            + "  ,C_CITY  ,N_NUMBER3 ,C_BC_ORG_CODE ,C_RUNNING_ACC ,C_CREATE_TIME ,N_NUMBER1                                     "
            + "  ,C_OPEN_PROVINCE ,D_YEARLY_CHECK_DATE ,C_BELONG_BANK ,C_ASSO_NUM  ,C_ENABLE ,x.c_rela_code                                    "
            + "  from t_p_bi_fund_acc t join T_P_AB_PORT_ACC_RELA x on t.c_iden = x.c_rela_code  )   x                           "
            + "  where  (C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, nvl(C_DC_CODE,' '),c_port_code) not in                     "
            + "   (select C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, nvl(C_DC_CODE,' '),c_port_code from t_c_cp_fund_acc )     ")
        .addSql("  update t_c_cp_fund_acc x set c_rela_code = ( select c_iden from t_p_bi_fund_acc t where t.C_OPEN_ACC_NO= x.C_OPEN_ACC_NO "
        		+ " and t.C_OPEN_ACC_NAME = x.C_OPEN_ACC_NAME and t.C_OPEN_ADDR = x.C_OPEN_ADDR and nvl(t.C_DC_CODE,' ') =  nvl(x.C_DC_CODE,' ') )"
        		+ " where trim(x.c_rela_code) is null ")
		.endScript();
	}
	
	/**
	 * STORY #80614 账户信息设置中的“账户类型”需要支持多选
	 * @param builder
	 */
	private void buildInsertT_CP_FUNDTYPE_RELA(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("FAST2.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("80614")
		.addSql("  INSERT INTO  "
		+ "    t_cp_fundtype_rela   "
		+ "	     (C_IDEN,C_PORT_CODE, C_RELA_CODE,C_ACCOUNT_TYPE,C_UPDATE_TIME,C_UPDATE_BY )  "
		+ "	       SELECT SEQU_CP_fundtype_rela.Nextval, ' ',acc.c_iden,ACC.C_ACCOUNT_TYPE,nvl(acc.c_update_time,to_char(SYSDATE,'yyyyMMdd hh24:mi:ss')) AS C_UPDATE_TIME,NVL(acc.C_UPDATE_BY,' ') as C_UPDATE_BY  "
		+ "	          FROM T_P_BI_FUND_ACC  ACC WHERE NOT EXISTS (SELECT 1 FROM  t_cp_fundtype_rela  tprela  "
		+ "	            WHERE tprela.c_rela_code = ACC.c_Iden AND nvl(acc.c_account_type,' ') = nvl(tprela.c_account_type,' ') )  "
		+ "	         AND trim(ACC.c_Account_Type) IS NOT NULL AND acc.c_Account_Type NOT LIKE '%|%' ")
		.endScript();
	}
	
	private void buildStory72335(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
			// 版本号
			.addVersion("FAST2.0")
			// 需求或者bug标识
			.addUpdateType(UpdateType.REQUEST)
			// 需求或者bug号
			.addID("72335")
			//加功能表数据
			.addSql(" insert into t_s_fun(C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, "
					+ " C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, "
					+ " N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY) "
					+ " values ('base_portBusinessRange', '产品业务范围', 'baseInfo', 4, 'ENAB', 'PUB', 'MEU_FUN', 'D_YW', null, "
					+ " 'E', null, 'area', '维护产品关联业务信息', 1, 0, 0, 1, sequ_s_fun.nextval, 'YSSINFO', null, 'MENU_INNER', "
					+ " 'POPIN', -1.000000000000000) ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','ADD')  ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','BRW')  ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','CHK')  ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','DEL')  ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','SAVE') ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','UCHK') ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','UPD')  ")
			.addSql(" insert into T_S_FUN_RIGHTS(C_FUN_CODE,C_DV_OPER_TYPE)values ('base_portBusinessRange','CPY')  ")
			//加词汇表数据
			.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_GGT','沪港通业务','AO_AUTO_BUSINESS','自动化业务范围',0,'ENAB','[root]') ")
			.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_SGT','深港通业务','AO_AUTO_BUSINESS','自动化业务范围',1,'ENAB','[root]') ")
			.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_XSB','新三板业务','AO_AUTO_BUSINESS','自动化业务范围',2,'ENAB','[root]') ")
			.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_LTSX','流通受限业务','AO_AUTO_BUSINESS','自动化业务范围',3,'ENAB','[root]') ")
			.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_CW','场外业务','AO_AUTO_BUSINESS','自动化业务范围',4,'ENAB','[root]') ")
			.endScript();
	}
	
	/**
	 * BUG #287704 【华宝基金】产品业务范围无法选择转融通
	 * add by yangze 2019-11-03
	 * @param builder
	 */
	private void buildBug287704(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("FAST2.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("287704")
		// 脚本，可添加多条SQL		
		.addSql(" insert into t_s_dv_voc(c_dv_code,c_dv_name,c_dv_type,c_desc,n_order,c_dv_state,c_auth_org_code)values('AO_BUSINESS_ZRT','转融通业务','AO_AUTO_BUSINESS','自动化业务范围',5,'ENAB','[root]') ")
		// 本段SQL语句结束符		
		.endScript();
	}
	
	private void buildStory73960(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
			// 版本号
			.addVersion("FAST2.0")
			// 需求或者bug标识
			.addUpdateType(UpdateType.REQUEST)
			// 需求或者bug号
			.addID("73960")
			//加功能表数据
			.addSql(" insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE) "
					+ " values ('dzdzClsCopy', '电子对账分级复制', 'dzDz', 8, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, null, null, 0, 0, 0, 0, sequ_s_fun.nextval,null, null, 'MENU_INNER', 'POPIN') ")
			.endScript();
	}
	
	/**
	 * STORY #90197 银行账户信息界面新增账户时自动绑定组合
	 * 删除调菜单'fundAccInfo' 使用base_fundAccInfo
	 * @param builder
	 */
	private void buildStory90197(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
			// 版本号
			.addVersion("300.7")
			// 需求或者bug标识
			.addUpdateType(UpdateType.REQUEST)
			// 需求或者bug号
			.addID("90197")
			//加功能表数据
			.addSql(" delete  from t_s_fun t where t.c_fun_code='base_fundAccInfo' ")
			.addSql(" delete from t_s_fun_rights t where t.c_fun_code='base_fundAccInfo' ")
			.endScript();
	}
	
	
}
