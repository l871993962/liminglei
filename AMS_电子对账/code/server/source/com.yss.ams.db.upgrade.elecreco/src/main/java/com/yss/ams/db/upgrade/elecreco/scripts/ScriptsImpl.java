package com.yss.ams.db.upgrade.elecreco.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;

/**
 * 
 * @ClassName: ScriptsImpl20_4_7_20170317 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author wulongxing
 * @date 2017年6月21日 下午4:33:09 
 *
 */
public class ScriptsImpl  implements ScriptDesc {

	@Override
	public ScriptBuilder getScriptBuilder() {
		ScriptBuilder builder = new ScriptBuilder();
		this.buildReveOrgDict(builder);
//		this.buildUpdateReveInfoMenu(builder);
		this.buildSequSn(builder);
		this.buildStory42660(builder);
		this.buildStory35703(builder);
		this.buildBug167771(builder);
		
		this.buildStory44562(builder);
		this.buildStory45854(builder);
		this.buildStory45858(builder);
		this.buildStory45862(builder);
		this.buildStory46220(builder);
		this.buildStory46241(builder);
		this.buildStory46253(builder);
		this.buildStory45857(builder);
		this.buildStory48443(builder);
		this.buildStory48815(builder);
		this.buildStory46715(builder);
		this.buildStory46827(builder);
		this.buildBUG183027(builder);
		this.buildStory47143(builder);
		this.buildStory50919(builder);
		this.buildStory51238(builder);
		this.buildStory52002(builder);
		this.buildStory52785(builder);
		this.buildStory55404(builder);
		this.buildStory55472(builder);
		this.buildStory57746(builder);
		this.buildStory50374(builder);
//		this.buildStory49489(builder);
		this.buildStory41535(builder);
		this.buildStory57239(builder);
		this.buildStory54447(builder);
		this.buildStory57828(builder);
		this.buildStory59971(builder);
		this.buildBUG214534(builder);
		this.buildStory60297(builder);
		this.buildStory58653(builder);
		this.buildBUG217588(builder);
		this.buildSTORY62407(builder);
		this.buildSTORY59739(builder);
		this.buildStory61514(builder);
		this.buildStory48560(builder);
		this.buildStory47037(builder);
		this.buildBug225877(builder);
		this.buildStory74798(builder);
		this.buildStory80041(builder);
		this.buildBUG272834(builder);
		this.buildSTORY63787(builder);
		this.buildStory61216(builder);
		this.buildStory69394(builder);
		this.buildStory72464(builder);
		this.buildJob2405(builder);
		this.buildJob2438(builder);
		this.buildStory82788(builder);
		this.buildBug290161(builder);
		this.buildBug291034(builder);
		this.buildBug297231(builder);
		this.buildSTORY85589(builder);
		this.buildStory73476(builder);
		this.buildStory81879(builder);
		this.buildStory89837(builder);
		this.buildStory88316(builder);
		this.buildStory86786(builder);
		this.buildStory90279(builder);
		this.buildStory91033(builder);
		this.buildStory91826(builder);
		this.buildBut338178(builder);
		this.buildStory94514(builder);
		this.buildStory94746(builder);
		this.buildStory94872(builder);
		this.buildTask1051310(builder);
		this.buildBUG341428(builder);
		this.buildStory91828(builder);
		this.buildBug353940(builder);
		this.buildStory103621(builder);
		this.buildStory101696(builder);
		this.buildStory103375(builder);
		this.buildStory103840(builder);
		this.buildStory103067(builder);
		this.buildStory108210(builder);
		return builder;
	}
	
	/**
	 * STORY #108210 净值确认的提示不要因为有不一致的条数提示什么净值确认失败，我们实际业务就是所有产品都有不一致的条数的，由于科目差异没办法全部一致，天天弹窗烦死了
	 * @param builder
	 */
	private void buildStory108210 (ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE, C_PARA_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_CONFIRMTIP', '净值确认是否提示电子对账不一致', 'VOC', 'BOOL_TYPE', '1', '【电子对账管理】中出现对账不一致，净值确认时是否要弹框提示.参数值=是（原逻辑），弹框提示；参数值=否，不提示', 'pubvocabulary', 'PUBLIC')");
		
		StringBuffer buffer1 = new StringBuffer();
		buffer1.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer1.append(" values ('COMM_PARAM_CUSTOM', '公共自定义参数', 'PARAM_TYPE', '参数类别', 6, 'ENAB', '[root]')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("108210")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_CONFIRMTIP' ")
		.addSql(buffer)
		
		.addSql("delete from t_s_dv_voc where C_DV_CODE = 'COMM_PARAM_CUSTOM' AND C_DV_TYPE = 'PARAM_TYPE' ")
		.addSql(buffer1)
		
		// 本段SQL语句结束符
		.endScript();	
	
	}
	
	/**
	 * STORY #103067 【长江养老】增加参数控制区分深港通和沪港通股票发送电子对账
	 * @param builder
	 */
	private void buildStory103067(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_SENDSCJM', '电子对账发送深港通和沪港通数据时FKMBM带市场简码', 'VOC', 'BOOL_TYPE', '0', '是：发送数据时，深港通和沪港通数据报文中FKMBM带市场简码发送,否：报文中FKMBM不包含市场简码（系统现有规则）', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("103067")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_SENDSCJM' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #103840 电子对账功能优化
	 * @param builder
	 */
	private void buildStory103840(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("103840")
		// 脚本，可添加多条SQL
		
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'dzBbInfo' and C_DV_OPER_TYPE = 'DBCHK' ")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzBbInfo', 'DBCHK')")
		
		// 本段SQL语句结束符
		.endScript();
	}
	
	/**
	 * STORY #103375 【太平养老】发送电子对账估值表，发现有重复科目
	 * @param builder
	 */
	private void buildStory103375(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_MERGEDATA', '电子对账科目代码相同时合并生成数据', 'VOC', 'BOOL_TYPE', '0', '生成电子对账数据并且当本参数选择“是”时，针对科目代码相同的数据合并求和金额和数量均不等于0时生成数据。否则按系统现有方式逐条生成对应数据', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("103375")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_MERGEDATA' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #103621 【公共需求】电子对账企业年金1711和1811新报表名称调整 
	 * @param builder
	 */
	private void buildStory103621(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("103621")
		.addSql(" update T_D_ER_DZ_TYPE set C_DZ_NAME = '资产负债表(年金工行)' where C_DZ_CODE = '1711' ")
		.addSql(" update T_D_ER_DZ_TYPE set C_DZ_NAME = '经营业绩表(年金工行)' where C_DZ_CODE = '1811' ")
		.addSql(" update T_S_DZ_TYPE set C_DZ_NAME = '资产负债表(年金工行)' where C_DZ_CODE = '1711' ")
		.addSql(" update T_S_DZ_TYPE set C_DZ_NAME = '经营业绩表(年金工行)' where C_DZ_CODE = '1811' ")
		.endScript();
	}
	
	/**
	 * BUG #353940 【鹏华基金】电子对账发送的成本列未包含减值准备
	 * @param builder
	 */
	private void buildBug353940(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_CBJSJZZB', '电子对账数据成本列加上减值准备', 'VOC', 'BOOL_TYPE', '0', '电子对账发送的数据，持有到期和可供出售类的成本明细科目成本列加上减值准备，科目名称后面加上 “（总价）”', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("353940")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_CBJSJZZB' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	private void buildBUG341428(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("341428")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ELEC_JZCBDB_COL'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('JZCBDB_COL1_BQ', '本期数', 'ELEC_JZCBDB_COL', '净资产变动表列信息', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('JZCBDB_COL2_BN', '本年数', 'ELEC_JZCBDB_COL', '净资产变动表列信息', 2, 'ENAB')")
		// 本段SQL语句结束符
		.endScript();		
		
	}
	
	/**
	 * TASK #1051310 电子对账资产代码转换字典设置
	 * @param builder
	 */
	private void buildTask1051310(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_V_D_GROUP (C_IDEN, C_GROUP_CODE, C_GROUP_NAME, C_GROUP_CODE_P, N_ORDER, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_DV_SCENE)");
		buffer.append(" values (SEQU_V_D_GROUP.Nextval, 'DZDZ_ZCDM', '电子对账资产代码转换', '[root]', 0, '', '1', 'sys', TO_CHAR(sysdate,'yyyyMMdd HH24:mi:ss') , '', '', 'SCENE_CUSTOM')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("1051310")
		.addSql("delete from T_V_D_GROUP where C_GROUP_CODE = 'DZDZ_ZCDM' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	
	}
	
	/**
	 * STORY #94872 产品估值参数“证券代码转换规则、 交易渠道转换规则”，电子对账系统单独拆分需求 
	 * @param builder
	 */
	private void buildStory94872(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_ZQDMZH', '电子对账证券代码转换规则', 'VOC', 'ZHGZ_TYPE', 'TRAN_BZH', '证券代码转换规则-默认值：TRAN_BZH(TRAN_BZH:不转换;TRAN_SBLSH:社保理事会证券代码转换规则,TRAN_ZZSPLPZ：增值税披露品种转换规则)', 'pubvocabulary')");
		
		StringBuffer buffer1 = new StringBuffer();
		buffer1.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer1.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_JYQDZH', '电子对账交易渠道转换规则', 'VOC', 'ZHGZ_TYPE', 'TRAN_BZH', '交易渠道转换规则-默认值：TRAN_BZH(TRAN_BZH:不转换;TRAN_SBLSH:社保理事会证券代码转换规则,TRAN_ZZSPLPZ：增值税披露品种转换规则)', 'pubvocabulary')");
		
		//历史数据迁移
		StringBuffer buffer3 = new StringBuffer();
		buffer3.append(" insert into T_D_ER_DSP_VALUE ");
		buffer3.append(" (C_IDEN, C_PORT_CODE, C_DV_PARAM_TYPE, C_DSP_CODE, C_DV_PARAMS_VALUE, C_PORT_CLS_CODE, D_BEGIN, D_END, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) ");
		buffer3.append(" SELECT SEQU_D_ER_DSP_VALUE.NEXTVAL,C_PORT_CODE, ");
		buffer3.append(" case when exists (select 1 from T_P_AB_PORT port where port.C_PORT_CODE = A.c_Port_Code) then ");
		buffer3.append(" 'PORT_PARAM_CUSTOM' ");
		buffer3.append(" else ");
		buffer3.append(" 'GROUP_PARAM_CUSTOM' ");
		buffer3.append(" end as C_DV_PARAM_TYPE, ");
		buffer3.append(" CASE WHEN C_DSP_CODE = 'SV_BB_ZQDMZH' THEN 'SV_BB_DZDZ_ZQDMZH' WHEN C_DSP_CODE = 'SV_BB_JYQDZH' THEN 'SV_BB_DZDZ_JYQDZH' ELSE '' END C_DSP_CODE,");
		buffer3.append(" C_DV_PARAMS_VALUE,C_PORT_CLS_CODE,D_BEGIN,D_END,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME ");
		buffer3.append(" FROM T_P_AO_PARAMS A ");
		buffer3.append(" where A.C_DSP_CODE IN ('SV_BB_ZQDMZH', 'SV_BB_JYQDZH') ");
		buffer3.append(" AND NOT EXISTS (SELECT 1 FROM T_D_ER_DSP_VALUE T WHERE T.C_PORT_CODE = a.c_port_code and t.c_dsp_code = decode(a.c_dsp_code,'SV_BB_ZQDMZH','SV_BB_DZDZ_ZQDMZH','SV_BB_JYQDZH','SV_BB_DZDZ_JYQDZH','') and t.c_port_cls_code = a.c_port_cls_code) ");
		
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("94872")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE IN ('SV_BB_DZDZ_ZQDMZH','SV_BB_DZDZ_JYQDZH') ")
		.addSql(buffer)
		.addSql(buffer1)
		.addSql(buffer3)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #94746 【政策业务】中央数据交换平台开放式基金业务数据交换协议V2.2 
	 * @param builder
	 */
	private void buildStory94746(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'DZ_BB_DZDZ_CWJJHQBLWS', '电子对账估值表场外基金行情列保留位数', 'NUMBER', '', '2', '电子对账估值表场外基金行情列保留位数,默认值为 2', '')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("94746")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_CWJJHQBLWS' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**T_D_ER_DSP_VALUE
	 * STORY #94514 【招商财富】无法与托管行核对协议回购明细科目
	 * @param builder
	 */
	private void buildStory94514(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'DZ_BB_DZDZ_BFSHGMX', '电子对账不发送债券协议回购明细数据', 'VOC', 'BOOL_TYPE', '0', '电子对账不发送债券协议回购明细数据-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("94514")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_BFSHGMX' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * BUG #338178 【富国基金】【0630.0723版本】产生电子对账月报报文中的begin_date和end_date逻辑问题
	 * @param builder
	 */
	private void buildBut338178(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_SENDDATE', '电子对账发送期初日期和期末日期是否为生成日期', 'VOC', 'BOOL_TYPE', '0', '电子对账发送期初日期和期末日期是否为生成日期-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.300.7.0.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("338178")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'SV_BB_DZDZ_SENDDATE' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #91826 富国基金-电子对账发送衍生工具相关科目的改造需求 
	 * @param builder
	 */
	private void buildStory91826(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_YSGJMFFS', '电子对账估值表衍生工具卖方数量是否为负数非明细数量是否生成', 'VOC', 'BOOL_TYPE', '0', '电子对账估值表衍生工具卖方数量是否为负数非明细数量是否生成-默认值：0(1:是;0：否)', 'pubvocabulary')");
		
		StringBuffer buffer1 = new StringBuffer();
		buffer1.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer1.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_YSGJGYJZCB', '电子对账衍生工具公允价值科目成本金额是否填0', 'VOC', 'BOOL_TYPE', '0', '电子对账衍生工具公允价值科目成本金额是否填0-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("91826")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE IN ('SV_BB_DZDZ_YSGJMFFS','SV_BB_DZDZ_YSGJGYJZCB') ")
		.addSql(buffer)
		.addSql(buffer1)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #91033 【鹏华基金】境外交易证券发送电子对账时证券代码问题优化 
	 * @param builder
	 */
	private void buildStory91033(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_KMISIN', '电子对账估值表境外交易证券的科目代码使用ISIN码匹配', 'VOC', 'BOOL_TYPE', '0', '电子对账估值表境外债券科目代码使用ISIN码匹配-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("91033")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'SV_BB_DZDZ_KMISIN' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #90279 【华宝信托】电子对账功能优化 
	 * @param builder
	 */
	private void buildStory90279(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_XQHDJGXS', '电子对账详细信息核对结果项只显示不一致信息', 'VOC', 'BOOL_TYPE', '0', '电子对账详细信息核对结果项只显示不一致信息-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("90279")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'SV_BB_DZDZ_XQHDJGXS' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY #86786 华夏基金--可以通过参数控制发送的电子对账指标的保留位数 
	 * @param builder
	 */
	private void buildStory86786(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'DZ_BB_DZDZ_SSZBBLXSWS', '电子对账实收资本保留小数位数', 'NUMBER', '', '2', '电子对账实收资本(0065)保留小数位数', '')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("86786")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_SSZBBLXSWS' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	private void buildStory88316(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("88316")
		// 脚本，可添加多条SQL
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'dzTransRepCfg'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('dzTransRepCfg', '电子对账交易数据配置', 'dzDz', 7, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzRepColCfg', null, 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'dzTransRepCfg'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzTransRepCfg', 'UPD')")
		
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'TEMP_BUS_TYPE' AND C_DV_CODE = 'TEMP_DZ_SZT_JYSJ' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('TEMP_DZ_SZT_JYSJ', '深圳通交易数据接口', 'TEMP_BUS_TYPE', '模板业务类型', 13, 'ENAB')")
		
		.addSql(" delete from T_D_ER_DZ_TYPE where C_DZ_CODE  IN ('A01','A001') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, 'A01', '交易数据', '[root]', '') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, 'A001', '银行间交易拆分', 'A01', '') ")
		
		// 本段SQL语句结束符
		.endScript();
	}
	
	/**
	 * STORY #89837 人保资产-电子对账生成的估值表持有到期数据生成错误 
	 * @param builder
	 */
	private void buildStory89837(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (sequ_d_er_dsp_para.nextval, 'SV_BB_DZDZ_TZYZJSZQZ', '电子对账数据持有到期的投资科目和溢折价科目市值取成本值', 'VOC', 'BOOL_TYPE', '0', '电子对账数据持有到期的投资科目和溢折价科目市值取成本值-默认值：0(1:是;0：否)', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("89837")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'SV_BB_DZDZ_TZYZJSZQZ' ")
		.addSql(buffer)
		
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY81879【华宝基金】电子对账检查重要估值指标
	 * @throws Exception
	 */
	private void buildStory81879(ScriptBuilder builder)
	{
		StringBuffer buffer = new StringBuffer();
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("81879");
		// 脚本，可添加多条SQL
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'BRSY', '本日收益', '843', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'MWFSY', '每万份收益', '951', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'QRNHSYL', '七日年化收益率', '956', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'TZZHPJSYCXQ', '投资组合平均剩余存续期', '9991', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'ZJCB', '净值（成本）', '845', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'ZJSZ', '净值（市值）', '846', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'PLJE', '偏离金额', '848', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'PLD', '偏离度', '849', 'CLS_HB', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'QCDWJZ', '期初单位净值', '900', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'DWJZ', '今日单位净值', '901', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'ZRDWJZ', '昨日单位净值', '902', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'FNJZZZL', '成长至今净值增长率', '910', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'LJDWJZ', '累计单位净值', '904', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'SXSY', '实现收益', '920', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'KFPSY', '可分配收益', '921', 'CLS_PT', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', '非货币基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'QCDWJZ', '期初单位净值', '900', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'DWJZ', '今日单位净值', '901', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'ZRDWJZ', '昨日单位净值', '902', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'FNJZZZL', '成长至今净值增长率', '910', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'LJDWJZ', '累计单位净值', '904', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'SXSY', '实现收益', '920', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'KFPSY', '可分配收益', '921', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'XJCE', '单位现金差额', '906', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'LZGZ', '篮子估值', '927', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		
		buffer.append("insert into T_D_ER_SPECIAL_ZB (C_IDEN, C_KEY_CODE, C_KEY_NAME, C_KM_CODE, C_DAT_CLS, C_UPDATE_BY, C_CHECK_TIME, C_UPDATE_TIME, N_CHECK_STATE, C_CHECK_BY, C_DAT_CLS_NAME)");
		buffer.append("values (SEQU_D_ER_SPECIAL_ZB.NEXTVAL, 'CSDWJZ', '创设单位净值', '926', 'CLS_ETF', 'Admin', to_char(sysdate,'yyyymmdd hh24:mi:ss'), to_char(sysdate,'yyyymmdd hh24:mi:ss'), 1, 'Admin', 'ETF基金')");
		builder.addSql(buffer)
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY73476【鹏华基金】并行组合电子对账需求
	 * @param builder
	 */
	private void buildStory73476(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_BXZHFSCPDMLX', '并行组合发送产品代码类型', 'VOC', 'ERSEND_PORT_TYPE', 'ERSEND_ASS_CODE', '内部组合代码：电子对账发送并行组合的系统组合代码     外部资产代码：电子对账发送并行组合产品信息中的资产代码（并行组合与主组合一致）', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("73476")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ERSEND_PORT_TYPE' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERSEND_ASS_CODE', '外部资产代码', 'ERSEND_PORT_TYPE', '电子对账发送资产代码', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERSEND_PORT_CODE', '内部组合代码', 'ERSEND_PORT_TYPE', '电子对账发送组合代码', 2, 'ENAB')")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_BXZHFSCPDMLX' ")
		.addSql(buffer)
		
		//历史数据迁移
		.addSql(new StringBuffer(" update T_D_ER_INFO A set C_PORT_CODE = (SELECT R.C_PORT_CODE FROM T_P_AB_PORT R where R.C_ASS_CODE = A.C_ASS_CODE) ")
				.append(" where 1 = (SELECT count(R.C_PORT_CODE) FROM T_P_AB_PORT R where R.C_ASS_CODE = A.C_ASS_CODE) and trim(C_PORT_CODE) is null "))
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * BUG #297231 电子对账报文序号，C_IDEN非顺序递增，导致查询电子对账管理界面，最后一次生成查询条件
	 * @param builder
	 */
	private void buildBug297231(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("300.7")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("297231")
		.addSql("alter sequence SEQU_D_ER_INFO order;")
		.addSql("alter sequence SEQU_D_ER_SN order;")
		.endScript();
	}
	
	/**
	 * STORY #85589 交通银行划款指令需要使用多个appid 
	 * @param builder
	 */
	private void buildSTORY85589(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("85589")
		.addSql("DELETE FROM T_S_DV_VOC T WHERE T.C_DV_TYPE = 'BUSI_TYPE' AND T.C_DV_CODE IN ('BUSI_ZHCX','BUSI_HSSJ') ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('BUSI_ZHCX', '账户查询', 'BUSI_TYPE', '业务类型', 5, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('BUSI_HSSJ', '核算数据', 'BUSI_TYPE', '业务类型', 6, 'ENAB')")
		.endScript();
	}

	/**
	 * STORY80041双估值电子对账双估值表剔除持有至到期估值增值科目
	 * @param builder
	 */
	private void buildStory80041(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append("values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_SGZBFSCYDDGZ', '双估值表是否发送持有到期估增科目', 'VOC', 'BOOL_TYPE', '1', '是：发送持有到期估增科目否：不发送持有到期估增科目', 'pubvocabulary')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("80041")
		// 脚本，可添加多条SQL
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE = 'DZ_BB_DZDZ_SGZBFSCYDDGZ' ")
		.addSql(buffer)
		// 本段SQL语句结束符
		.endScript();	
	}
	
	/**
	 * STORY74798双估值报表电子对账功能
	 * @param builder
	 */
	private void buildStory74798(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("74798")
		.addSql(" delete from T_D_ER_DZ_TYPE where C_DZ_CODE  = '1013' ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1013', '双估值表', '01', '15') ");
		//初始化指标
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_Z_BI_RELA (C_IDEN, C_ZB_CODE, C_ZB_NAME, C_DZ_CODE, C_PORT_CODE_CLS, C_DV_ZB_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_ORG_CODE, C_DV_PORT_CLS_TYPE, C_DV_PORT_CLS_LEVEL, C_DV_PORT_CLS, C_REPORT_CODE) ")
		.append(" SELECT SEQU_Z_BI_RELA.NEXTVAL, C_ZB_CODE, C_ZB_NAME, '1013', C_PORT_CODE_CLS, C_DV_ZB_CODE, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_ORG_CODE, C_DV_PORT_CLS_TYPE, C_DV_PORT_CLS_LEVEL, C_DV_PORT_CLS, C_REPORT_CODE ")
		.append(" from T_Z_BI_RELA where C_DZ_CODE = '1011' ");
		
		builder.addSql(sb)
		.endScript();
	}
	
	/**
	 * BUG291034系统默认的菜单中电子对账综合参数菜单没有挂在电子对账模块下面
	 * @param builder
	 */
	private void buildBug291034(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("290161")
		.addSql("DELETE FROM t_s_fun_custom where C_FUN_CODE_P = 'electronicCheck' and C_FUN_CODE in ('busType','dzDspPara')")
		.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE,C_PLAN_CODE) values (sequ_s_fun_custom.nextval, 'dzDspPara', '电子对账综合参数', 'electronicCheck', 'FUN_BIZ', 1, 2, 4, null, 'DefaultBiz') ")
		.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE,C_PLAN_CODE) values (sequ_s_fun_custom.nextval, 'dzDspPara', '电子对账综合参数', 'electronicCheck', 'FUN_APP', 1, 2, 4, null, 'DefaultApp') ")
		.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE,C_PLAN_CODE) values (sequ_s_fun_custom.nextval, 'dzDspPara', '电子对账综合参数', 'electronicCheck', 'FUN_APP', 1, 1, 4, null, 'SystemApp') ")
		.addSql(" insert into t_s_fun_custom (C_IDEN, C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, C_DV_APP_CLS, N_FUN_TYPE, N_DEFAULT, N_ORDER, C_ICO_FILE,C_PLAN_CODE) values (sequ_s_fun_custom.nextval, 'dzDspPara', '电子对账综合参数', 'electronicCheck', 'FUN_BIZ', 1, 1, 4, null, 'SystemBiz') ")
		.endScript();
	}
	
	/**
	 * BUG290161【华富基金】【300.7_1031版本】电子对账参数设置混乱
	 * 估值升级组件将电子对账删除的参数又插入了一遍，这里再删除一次
	 * @param builder
	 */
	private void buildBug290161(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("290161")
		.addSql("delete from T_D_ER_DSP_PARA where C_DSP_CODE IN ('DZ_BB_ELEC_YE_6407','DZ_BB_ELEC_YE_6605')");
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_ELEC_YE_6407', '电子对账余额表社保基金是否发送6407.02下的4级科目', 'VOC', 'BOOL_TYPE', '1', '电子对账余额表社保基金是否发送6407.02下的4级科目,默认为是', 'pubvocabulary')");
		builder.addSql(buffer);

		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_ELEC_YE_6605', '电子对账余额表社保基金是否发送6605.03下的3级科目', 'VOC', 'BOOL_TYPE', '1', '电子对账余额表社保基金是否发送6605.03下的3级科目,默认为是', 'pubvocabulary')");
		builder.addSql(buffer);
		
		builder.addSql(" DELETE FROM T_S_DSP_PARA where C_DSP_CODE in (SELECT C_DSP_CODE FROM T_D_ER_DSP_PARA) ")
		.endScript();
	}
	
	/**
	 * STORY82788【招商财富300升级】增加参数优化电子对账单位净值的发送位数
	 * @param builder
	 */
	private void buildStory82788(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("82788")
		.addSql("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE) values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_GZBFSXSWS', '电子对账估值表保留小数位数', 'NUMBER', null, '0', '0：根据产品估值参数单位净值保留位数设置的参数值保留估值表发送小数位数；其他整数：根据此参数设置的参数值保留估值表发送小数位数；', null)")
		.endScript();
	}
	
	/**
	 * 派工单 #2438 估值_V1.300.7.0_UI自动化测试_自动化测试(274)【指标个性设置】
	 * @param builder
	 */
	private void buildJob2438(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("2438")
		.addSql(" update T_S_FUN set C_DV_FUN_TYPE = 'MEU_AID' where C_FUN_CODE = 'dzPerRela' ")
		.endScript();
	}
	
	/**
	 * 派工单 #2405 估值_V1.300.7.0_UI自动化测试_自动化测试(274)【对账报表个性列配置】
	 * @param builder
	 */
	private void buildJob2405(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("2405")
		.addSql(" update T_S_FUN set C_DV_FUN_TYPE = 'MEU_AID' where C_FUN_CODE = 'dzRepColCfg' ")
		.endScript();
	}

	/**
	 * 修改反向电子对账管理为本方对账管理
	 * @param builder
	 */
//	private void buildUpdateReveInfoMenu(ScriptBuilder builder) {
//		builder.createScript(ScriptType.DATA)
//		.addVersion("1.300.7.0.0")
//		.addUpdateType(UpdateType.BUG)
//		.addID("000001")
//		.addSql(" update T_S_FUN set C_FUN_NAME = '本方对账管理' where C_FUN_CODE = 'reveDzInfo' ")
//		.endScript();
//	}

	/**
	 * 反向电子对账词汇管理
	 * @param builder
	 */
	private void buildReveOrgDict(ScriptBuilder builder) {
		StringBuffer sb = new StringBuffer();
		sb.append(" insert into T_V_D_GROUP (C_IDEN, C_GROUP_CODE, C_GROUP_NAME, C_GROUP_CODE_P, N_ORDER, C_DESC, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME, C_DV_SCENE) ");
		sb.append(" SELECT  SEQU_V_D_GROUP.Nextval, 'ELEC_TGH', '电子对账机构转换', '[root]', 0, '', 1, 'yss', '20190916 13:10:56', '', '', 'SCENE_CUSTOM' from dual ");
		sb.append(" where not exists (select 1 from T_V_D_GROUP where C_GROUP_CODE = 'ELEC_TGH') ");
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("000001")
		.addSql(sb.toString())
		.endScript();
	}

	/**
	 * STORY #72464 电子对账关于许可控制的功能模块改造 
	 * @param builder
	 */
	private void buildStory72464(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("72464")
//		.addSql(" delete from T_D_ER_DZ_TYPE where C_DZ_CODE in('01','1001','1011','1031','03','1701','1801','1711','1811','1901','1903')")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '01', '01类型', '[root]', '') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1001', '余额表', '01', '0') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1011', '估值表', '01', '1') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1031', '科目表', '01', '') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '03', '03类型', '[root]', '') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1701', '资产负债表', '03', '3') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1801', '利润表', '03', '4') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1901', '所有者权益（基金净值）变动表', '03', '5') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1903', '净资产变动表', '03', '8') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1711', '资产负债表（新准则）', '03', '16') ")
		.addSql(" insert into T_D_ER_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P, C_CHECK_FLAG) values (SEQU_D_ER_DZ_TYPE.NEXTVAL, '1811', '经营业绩表（新准则）', '03', '17') ")
		.addSql(" update T_D_ER_RELA set C_DV_LICORG = 'OLD_LIC' where trim(C_DV_LICORG) is null and C_BUS_TYPE = 'BUSI_DZ' ")
		//电子对账词汇表
		.addSql("delete from T_D_ER_DV_VOC where C_DV_TYPE = 'ER_LIC_ORG'")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('平安银行电子对账', '平安银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 1, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('兴业银行电子对账', '兴业银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 2, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('交通银行电子对账', '交通银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 3, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('浦发银行电子对账', '浦发银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 4, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('广发银行电子对账', '广发银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 5, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('工商银行电子对账', '工商银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 6, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('农业银行电子对账', '农业银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 7, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中国银行电子对账', '中国银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 8, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('建设银行电子对账', '建设银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 9, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('招商证券电子对账', '招商证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 10, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('招商银行电子对账', '招商银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 11, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('光大银行电子对账', '光大银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 12, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('北京银行电子对账', '北京银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 13, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('广发证券电子对账', '广发证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 14, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('民生银行电子对账', '民生银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 15, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('上海银行电子对账', '上海银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 16, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('杭州银行电子对账', '杭州银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 17, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('宁波银行电子对账', '宁波银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 18, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('邮储银行电子对账', '邮储银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 19, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('广州农商行电子对账', '广州农商行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 20, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('江苏银行电子对账', '江苏银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 21, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('徽商银行电子对账', '徽商银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 22, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('南京银行电子对账', '南京银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 23, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('恒丰银行电子对账', '恒丰银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 24, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('浙商银行电子对账', '浙商银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 25, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中信银行电子对账', '中信银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 26, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('华夏银行电子对账', '华夏银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 27, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('渤海银行电子对账', '渤海银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 28, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('包商银行电子对账', '包商银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 29, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('国泰君安电子对账', '国泰君安电子对账', 'ER_LIC_ORG', '对接机构许可信息', 30, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中信证券电子对账', '中信证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 31, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('银河证券电子对账', '银河证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 32, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('国信证券电子对账', '国信证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 33, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中信建投证券电子对账', '中信建投证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 34, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('海通证券电子对账', '海通证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 35, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中金公司电子对账', '中金公司电子对账', 'ER_LIC_ORG', '对接机构许可信息', 36, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('中登公司电子对账', '中登公司电子对账', 'ER_LIC_ORG', '对接机构许可信息', 37, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('渣打银行电子对账', '渣打银行电子对账', 'ER_LIC_ORG', '对接机构许可信息', 38, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('银行业理财登记托管中心', '银行业理财登记托管中心', 'ER_LIC_ORG', '对接机构许可信息', 39, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('安信证券电子对账', '安信证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 40, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('东方证券电子对账', '东方证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 41, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('华泰证券电子对账', '华泰证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 42, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('申万宏源电子对账', '申万宏源电子对账', 'ER_LIC_ORG', '对接机构许可信息', 43, 'ENAB')")
		.addSql("INSERT INTO T_D_ER_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('光大证券电子对账', '光大证券电子对账', 'ER_LIC_ORG', '对接机构许可信息', 44, 'ENAB')")
		.endScript();
	}
	
	/**
	 * STORY #48560整合电子对账业务类参数到便于用户设置和组件解耦
	 * @param builder
	 */
	private void buildStory48560(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("48560")
		// 脚本，可添加多条SQL
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'dzDspPara'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('dzDspPara', '电子对账综合参数', 'dzCs', 22, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzRepColCfg', null, 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'dzDspPara'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzDspPara', 'UPD')");
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_DWJZIV', '电子对账估值指标0016基金资产单位净值是否需要发送估值增值列数据', 'VOC', 'BOOL_TYPE', '0', '电子对账估值指标0016基金资产单位净值是否需要发送估值增值列数据，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_FXZHISIN', '反向电子对账证券内码是否转换为ISIN码', 'VOC', 'BOOL_TYPE', '0', '是：反向电子对账中点击人工对账时，将证券内码转换为ISIN码。否：反向电子对账中点击人工对账时，不用将证券内码转换为ISIN码。', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_GZBCFFS', '电子对账估值表按托管行拆分生成发送', 'VOC', 'BOOL_TYPE', '0', '是：组合在产生估值表的对账报文时，根据拆分规则拆分为多个子报文。否：组合直接产生估值表的电子对账报文。', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_MVCBLEVEL3', '电子对账估值持有到期类债券投资1111开头的第三级科目的市值取成本', 'VOC', 'BOOL_TYPE', '0', '电子对账估值持有到期类债券投资1111开头的第三级科目的市值取成本，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_TRANS1111', '电子对账科目代码1111.是否转换为1103.', 'VOC', 'BOOL_TYPE', '0', '电子对账科目代码1111.是否转换为1103.，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_XJZBFS', '电子对账是否发送现金类占净值比指标数据', 'VOC', 'BOOL_TYPE', '1', '电子对账是否发送现金类占净值比指标数据', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_DZDZ_YEBSSZBZSSL', '电子对账余额表实收资本是否需要数量', 'VOC', 'BOOL_TYPE', '1', '电子对账余额表实收资本4001及其子科目是否需要数量', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'DZ_BB_ELEC_YE_AMOUNT', '电子对账余额表非明细科目是否发送数量', 'VOC', 'BOOL_TYPE', '1', '电子对账余额表非明细科目是否发送数量,默认为是', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_BBFS', '电子对账数据使用本币发送', 'VOC', 'BOOL_TYPE', '0', '电子对账余额表数据原币金额使用本币金额发送，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_CBJSYZJ', '电子对账数据成本列加上溢折价', 'VOC', 'BOOL_TYPE', '0', '电子对账发送的数据，持有到期和可供出售类的成本明细科目成本列加上溢折价，科目名称后面加上 “（总价）”', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_CYDALHQ', '电子对账数据持有到期类成本科目行情取面值', 'VOC', 'BOOL_TYPE', '0', '电子对账数据持有到期类成本科目行情取面值', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_FJHJXJSFJZH', '电子对账分级产品合计项指标代码是否加上分级组合', 'VOC', 'BOOL_TYPE', '0', '电子对账分级产品合计项指标代码是否加上分级组合，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_GZETFSZ', '电子对账数据1102.99,1103.99科目市值列为0', 'VOC', 'BOOL_TYPE', '1', '电子对账数据1102.99,1103.99科目市值列为0', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_GZKMSZ', '电子对账数据估增4级明细科目市值列为0', 'VOC', 'BOOL_TYPE', '0', '电子对账数据估增4级明细科目市值列为0', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_GZSFZMXKM', '估增最明细科目发送时,是否明细数据字段取值情况默认为0', 'VOC', 'BOOL_TYPE', '0', '估增最明细科目发送时， 是否是最明细科目该字段取值情况，默认为0，否则为1(设为是的时候，明细是1，上级是0，设为否的时候，明细是0，上级是1)', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_GZSJKMSZ', '电子对账数据估增3级汇总科目市值列为0', 'VOC', 'BOOL_TYPE', '0', '电子对账数据估增3级汇总科目市值列为0', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_HJXJ', '电子对账合计小计是否需要数量', 'VOC', 'BOOL_TYPE', '1', '电子对账合计小计是否需要数量', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_JZZZL', '电子对账数据净值增长率是否取本期净值增值率', 'VOC', 'BOOL_TYPE', '0', '电子对账数据净值增长率是否取本期净值增值率', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_KFPSY', '可分配收益发送时是否发送净值占市值比例', 'VOC', 'BOOL_TYPE', '1', '可分配收益发送时是否发送净值占市值比例，默认为是', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_KMBW', '电子对账是否将第四级科目补齐为六位', 'VOC', 'BOOL_TYPE', '1', '电子对账是否将第四级科目补齐为六位，默认为是', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_SSZBZSSL', '电子对账实收资本是否需要数量', 'VOC', 'BOOL_TYPE', '1', '电子对账实收资本是否需要数量', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_WHQQDWCB', '电子对账数据无行情时取单位成本', 'VOC', 'BOOL_TYPE', '0', '电子对账数据无行情时取单位成本', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_YZJMXKM', '电子对账数据溢折价明细科目是否发送', 'VOC', 'BOOL_TYPE', '1', '电子对账数据溢折价明细科目是否发送(此参数只针对最明细科目)', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_YZJSZQZ', '电子对账数据溢折价市值列取估值表成本值', 'VOC', 'BOOL_TYPE', '0', '电子对账数据溢折价市值列取估值表成本值，是取成本列，否取市值列，默认为否', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_DZDZ_ZBXSSZB', '电子对账数据指标项市值占比列为0', 'VOC', 'BOOL_TYPE', '0', '电子对账数据指标项市值占比列为0，此参数只处理0018,0019以及0021', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE)");
		buffer.append(" values (SEQU_D_ER_DSP_PARA.nextval, 'SV_BB_ELCE_GZ_AMOUNT', '估值表非明细科目是否生成数量', 'VOC', 'BOOL_TYPE', '1', '电子对账估值表非明细科目是否生成数量', 'pubvocabulary')");
		builder.addSql(buffer);
		//历史数据迁移
		buffer = new StringBuffer();
		buffer.append(" insert into T_D_ER_DSP_VALUE ");
		buffer.append(" (C_IDEN, C_PORT_CODE, C_DV_PARAM_TYPE, C_DSP_CODE, C_DV_PARAMS_VALUE, C_PORT_CLS_CODE, D_BEGIN, D_END, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) ");
		buffer.append(" SELECT SEQU_D_ER_DSP_VALUE.NEXTVAL,C_PORT_CODE, ");
		buffer.append(" case when exists (select 1 from T_P_AB_PORT port where port.C_PORT_CODE = A.c_Port_Code) then ");
		buffer.append(" 'PORT_PARAM_CUSTOM' ");
		buffer.append(" else ");
		buffer.append(" 'GROUP_PARAM_CUSTOM' ");
		buffer.append(" end as C_DV_PARAM_TYPE, ");
		buffer.append(" C_DSP_CODE,C_DV_PARAMS_VALUE,C_PORT_CLS_CODE,D_BEGIN,D_END,N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME ");
		buffer.append(" FROM T_P_AO_PARAMS A ");
		buffer.append(" where A.C_DSP_CODE in (SELECT C_DSP_CODE FROM T_D_ER_DSP_PARA) ");
		builder.addSql(buffer);
		//删除产品参数里已经迁移过的数据
		buffer = new StringBuffer();
		buffer.append(" DELETE FROM T_S_DSP_PARA where C_DSP_CODE in (SELECT C_DSP_CODE FROM T_D_ER_DSP_PARA) ");
		builder.addSql(buffer)
		// 本段SQL语句结束符
		.endScript();
	}
	/**
	 * STORY69394电子对账报表配置模块新增电子对账报表类型：净资产变动表
	 * @param builder
	 */
	private void buildStory69394(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("69394")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DZ_TYPE where C_DZ_CODE = '1903' ")
		.addSql("insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1903', '净资产变动表', '03') ")
		// 本段SQL语句结束符
		.endScript();
	}
	/**
	 * STORY61216【景顺长城基金】发送电子对账报文明文和密文都要存数据库
	 * @param builder
	 */
	private void buildStory61216(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("61216")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ER_LOG_TYPE' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ER_SEND_LOG', '深证通链路检测', 'ER_LOG_TYPE', '发送报文', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ER_RECEIVE_LOG', '深证通链路检测', 'ER_LOG_TYPE', '接收报文', 2, 'ENAB')")
		// 本段SQL语句结束符
		.endScript();	
	}
	/**
	 * STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码
	 * @param builder
	 */
	private void buildStory47037(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("47037")
		// 脚本，可添加多条SQL
		.addSql("update T_Z_BI_RELA set C_DV_PORT_CLS = C_DV_PORT_CLS_LEVEL where C_DV_PORT_CLS_LEVEL in ( select C_DV_CODE from T_S_DV_VOC where C_DV_TYPE = 'PORT_CLS')")
		.addSql("update T_Z_BI_RELA set C_DV_PORT_CLS_LEVEL = '' where C_DV_PORT_CLS_LEVEL in ( select C_DV_CODE from T_S_DV_VOC where C_DV_TYPE = 'PORT_CLS');")
		// 本段SQL语句结束符
		.endScript();
	}
	/**
	 * STORY63787优化需求57549深证通链接检测中的任务配置功能
	 * @param builder
	 */
	private void buildSTORY63787(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("63787")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'elecCheckMr'")
		.addSql("insert into T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK, C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG) "
				+ " values ('elecCheckMr', '深证通连接检测', 'dzDz', 35, 'ENAB', 'PUB', 'MEU_AID', 'D_RZ', '', 'S', '', 'dzBbInfo', '连接检测设置', 0, 0, 0, 0, '103308', '', '', 'MENU_INNER', 'POPIN', 1.000000000000000, '[root]', -1)")
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ER_TASK_CODE' and C_DV_CODE = 'ER_MR_CHECK' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ER_MR_CHECK', '深证通链路检测', 'ER_TASK_CODE', '电子对账定时调度任务', 1, 'ENAB')")
		// 本段SQL语句结束符
		.endScript();	
	}
	/**
	 * STORY #61514 【江苏银行】提出与托管航对账不走深证通模式，走行内的模式。需要4.5系统改造，也需要3.5系统配合改造
	 * @param builder
	 */
	private void buildStory61514(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("61514")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_CODE = 'http'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('http', 'Http', 'COMM_TYPE', '通讯类型', 3, 'ENAB')")
		
		// 本段SQL语句结束符
		.endScript();
		
	}
	
	private void buildSTORY59739(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.6.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("59739")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ELEC_LRB_COL'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERL_COL1_BQ', '本期数', 'ELEC_LRB_COL', '利润表列信息', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERL_COL2_BN', '本年数', 'ELEC_LRB_COL', '利润表列信息', 2, 'ENAB')")
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ELEC_ZCFZB_COL'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERZ_COL1_QC', '期初值', 'ELEC_ZCFZB_COL', '资产负债表列信息', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERZ_COL2_QM', '期末值', 'ELEC_ZCFZB_COL', '资产负债表列信息', 2, 'ENAB')")
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ELEC_SYZQYB_COL'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL1_BQSSJJ', '本期实收基金', 'ELEC_SYZQYB_COL', '所有者权益变动表', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL2_BQWFPLR', '本期未分配利润', 'ELEC_SYZQYB_COL', '所有者权益变动表', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL3_BQSYZQY', '本期所有者权益合计', 'ELEC_SYZQYB_COL', '所有者权益变动表', 3, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL4_SQSSJJ', '上期实收基金', 'ELEC_SYZQYB_COL', '所有者权益变动表', 4, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL5_SQFPLR', '上期未分配利润', 'ELEC_SYZQYB_COL', '所有者权益变动表', 5, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ERS_COL6_SQSYZQY', '上期所有者权益', 'ELEC_SYZQYB_COL', '所有者权益变动表', 6, 'ENAB')");
		builder.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'dzRepColCfg'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('dzRepColCfg', '对账报表个性列配置', 'dzDz', 21, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzRepColCfg', null, 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'dzRepColCfg'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'CHK')")
		//屏蔽复制功能,没用.
		//.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('dzRepColCfg', 'UPD')")
		// 本段SQL语句结束符
		.endScript();		
		
	}

	private void buildBug225877(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.BUG)
		.addID("225877")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE = 'SV_BB_DZDZ_GZETFSZ' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_GZETFSZ', '电子对账数据1102.99,1103.99科目市值列为0', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账数据1102.99,1103.99科目市值列为0', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}
	/**
	 * STORY #58653 阳光资产--反向对账业务
	 * @param builder
	 */
	private void buildStory58653(ScriptBuilder builder) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE,C_REF_CODE,N_ORDER)");
		buffer.append("values ('DZ_BB_DZDZ_FXZHISIN', '反向电子对账证券内码是否转换为ISIN码', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '是：反向电子对账中点击人工对账时，将证券内码转换为ISIN码。否：反向电子对账中点击人工对账时，不用将证券内码转换为ISIN码。', '报表类', 'PUB', 'pubvocabulary','','210085')");
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0.9_2")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("58653")
		// 脚本，可添加多条SQL
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_FXZHISIN') ")
		.addSql(buffer)
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_YE_HDX'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QCYB', '期初余额（原币）', 'REVE_YE_HDX', '余额表核对项', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QCBB', '期初余额（本位币）', 'REVE_YE_HDX', '余额表核对项', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QCSL', '期初余额（数量）', 'REVE_YE_HDX', '余额表核对项', 3, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_DEYB', '本期借方发生额（原币）', 'REVE_YE_HDX', '余额表核对项', 4, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_DEBB', '本期借方发生额（本位币）', 'REVE_YE_HDX', '余额表核对项', 5, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_DESL', '本期借方发生额（数量）', 'REVE_YE_HDX', '余额表核对项', 6, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_CRYB', '本期贷方发生额（原币）', 'REVE_YE_HDX', '余额表核对项', 7, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_CRBB', '本期贷方发生额（本位币）', 'REVE_YE_HDX', '余额表核对项', 8, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_CRSL', '本期贷方发生额（数量）', 'REVE_YE_HDX', '余额表核对项', 9, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QMYB', '期末余额（原币）', 'REVE_YE_HDX', '余额表核对项', 10, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QMBB', '期末余额（本位币）', 'REVE_YE_HDX', '余额表核对项', 11, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_QMSL', '期末余额（数量）', 'REVE_YE_HDX', '余额表核对项', 12, 'ENAB')")
		//余额表累计发生额没有生成，不核对
//		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_DETOLBB', '借方累计发生额（本位币）', 'REVE_YE_HDX', '余额表核对项', 13, 'ENAB')")
//		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YE_CRTOLBB', '贷方累计发生额（本位币）', 'REVE_YE_HDX', '余额表核对项', 14, 'ENAB')")
		// 本段SQL语句结束符
		.endScript();	
	}
	/**
	 * STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
	 * @param builder
	 */
	private void buildStory60297(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0.9_2")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("60297")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'ELEC_SECRETTYPE' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ELEC_ST_BASE64', '压缩BASE64', 'ELEC_SECRETTYPE', '加密方式', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('EL_AES_ECB_CS5P', 'AES加密-ECB填充模式', 'ELEC_SECRETTYPE', '加密方式', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('ELEC_ST_NO', '不加密', 'ELEC_SECRETTYPE', '加密方式', 3, 'ENAB')")
		// 本段SQL语句结束符
		.endScript();	
	}
	/**
	 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本是否需要数量
	 * 默认为是
	 * 针对4001及其下面的子科目
	 */
	private void buildStory57828(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("57828")
		
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_GZBCFFS') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE,C_REF_CODE,N_ORDER)");
		buffer.append("values ('DZ_BB_DZDZ_GZBCFFS', '电子对账估值表按托管行拆分生成发送', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '是：组合在产生估值表的对账报文时，根据拆分规则拆分为多个子报文。否：组合直接产生估值表的电子对账报文。', '报表类', 'PUB', 'pubvocabulary','','210080')");
		builder.addSql(buffer)
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'erSplitRela'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('erSplitRela', '多托管行拆分设置', 'dzDz', 30, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '多托管行拆分设置', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'erSplitRela'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRela', 'UPD')")
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'erSplitRule'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('erSplitRule', '托管科目', 'dzDz', 31, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '托管科目', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'erSplitRule'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erSplitRule', 'UPD')")
		
		.endScript();
		
	}
	private void buildStory54447(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0.1")
		.addUpdateType(UpdateType.REQUEST)
		.addID("54447")
		.addSql("delete from t_s_dv_voc where c_dv_type='DZ_SGYY' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('DZDZMS_YSCW', '对账一致，映射有问题', 'DZ_SGYY', '电子对账手工原因', 1, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('DZDZMS_YTGHWZTZ', '我方按托管行结果调整', 'DZ_SGYY', '电子对账手工原因', 2, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('DZDZMS_YWFWZTZ', '托管行按我方结果调整', 'DZ_SGYY', '电子对账手工原因', 3, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('QTDZMS_DHDZ', '电话对账', 'DZ_SGYY', '电子对账手工原因', 4, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('QTDZMS_CZDZ', '传真对账', 'DZ_SGYY', '电子对账手工原因', 5, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('QTDZMS_QTDZ', '其他对账', 'DZ_SGYY', '电子对账手工原因', 6, 'ENAB', '[root]')");
		builder.addSql(buffer).endScript();
	}
	/*
	 * STORY59971【景顺长城基金】【道富QD】【紧急】估值表核对增加原币和指标的核对
	 * @param builder
	 */
	private void buildStory59971(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0.9_2")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("59971")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_GZ_HDX' and C_DV_CODE = 'REVE_GZ_YBSZ' ")
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_GZ_HDX' and C_DV_CODE = 'REVE_GZ_YBCB' ")
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_GZ_HDX' and C_DV_CODE = 'REVE_GZ_YBGZ' ")
		.addSql("update T_S_DV_VOC set C_DV_NAME = '本币估值增值' WHERE C_DV_TYPE = 'REVE_GZ_HDX' and C_DV_CODE = 'REVE_GZ_BBGZ' ")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_YBSZ', '原币市值', 'REVE_GZ_HDX', '估值核对项', 6, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_YBCB', '原币成本', 'REVE_GZ_HDX', '估值核对项', 7, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_YBGZ', '原币估值增值', 'REVE_GZ_HDX', '估值核对项', 8, 'ENAB')")
		
		// 本段SQL语句结束符
		.endScript();		
	}
	/**
	 * STORY52002嘉实基金-电子对账管理界面展示优化
	 * @param builder
	 */
	private void buildStory52002(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0_JSJJ")
		.addUpdateType(UpdateType.REQUEST)
		.addID("51238")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_XJZBFS') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE,C_REF_CODE,N_ORDER)");
		buffer.append("values ('DZ_BB_DZDZ_XJZBFS', '电子对账是否发送现金类占净值比指标数据', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账是否发送现金类占净值比指标数据', '报表类', 'PUB', 'pubvocabulary','','210065')");
		builder.addSql(buffer).endScript();
		
	}
	/**
	 * @Description: STORY # 55472 日常运营可以配置电子对账发送
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月21日 下午4:32:28
	 */
	private void buildStory55472(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("55472")
		// 脚本，可添加多条SQL
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'elecSend'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('elecSend', '发送电子对账', 'dzYw', 20, 'ENAB', 'PUB', 'MEU_AID', 'D_DZ', null, 'S', null, 'elecGene', '发送电子对账数据', 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
			
		builder.addSql(buffer)
		// 本段SQL语句结束符
		.endScript();
	}
	/**
	 * STORY51238（紧急）【嘉实基金】社保估值表指标问题导致电子对账不一致
	 * @param builder
	 */
	private void buildStory51238(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0_JSJJ")
		.addUpdateType(UpdateType.REQUEST)
		.addID("51238")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_XJZBFS') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE,C_REF_CODE,N_ORDER)");
		buffer.append("values ('DZ_BB_DZDZ_XJZBFS', '电子对账是否发送现金类占净值比指标数据', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账是否发送现金类占净值比指标数据', '报表类', 'PUB', 'pubvocabulary','','210065')");
		builder.addSql(buffer).endScript();
		
	}
	/**
	 * STORY55404【汇添富社保升级】电子对账发送余额表的时候实收资本是否需要数量
	 * 默认为是
	 * 针对4001及其下面的子科目
	 */
	private void buildStory55404(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("55404")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_YEBSSZBZSSL') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE,C_REF_CODE,N_ORDER)");
		buffer.append("values ('DZ_BB_DZDZ_YEBSSZBZSSL', '电子对账余额表实收资本是否需要数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账余额表实收资本4001及其子科目是否需要数量', '报表类', 'PUB', 'pubvocabulary','','210075')");
		builder.addSql(buffer).endScript();
		
	}
	/**
	 * STORY50919QD分级产品电子对账合计项指标根据级别、分级组合对账
	 * @param builder
	 */
	private void buildStory50919(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("50919")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_FJHJXJSFJZH') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_FJHJXJSFJZH', '电子对账分级产品合计项指标代码是否加上分级组合', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账分级产品合计项指标代码是否加上分级组合，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer).endScript();
		
	}
	/**
	 * STORY47143电子对账在发送港股证券时代码不需要补全6位（通过参数控制）
	 * @param builder
	 */
	private void buildStory47143(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.6")
		.addUpdateType(UpdateType.REQUEST)
		.addID("47143")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_KMBW') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_KMBW', '电子对账是否将第四级科目补齐为六位', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账是否将第四级科目补齐为六位，默认为是', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer).endScript();
		
	}
	/**
	 * BUG183027【嘉实基金】社保组合可分配收益发送时不发送净值占市值比例
	 * @param builder
	 */
	private void buildBUG183027(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0_JSJJ")
		.addUpdateType(UpdateType.BUG)
		.addID("183027")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_KFPSY') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_KFPSY', '可分配收益发送时是否发送净值占市值比例', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '可分配收益发送时是否发送净值占市值比例，默认为是', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer).endScript();
		
	}
	private void buildStory50374(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("50374")
		.addSql("DELETE FROM t_s_dv_voc WHERE  C_DV_TYPE = 'RESULT_DATA' ")
		.addSql("DELETE FROM t_s_fun WHERE  C_FUN_CODE = 'erDetail' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('ALL_DATA', '所有数据', 'RESULT_DATA', '结果类型', 1, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('CY_DATA', '差异数据', 'RESULT_DATA', '结果类型', 2, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append("C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE");
		buffer.append(") values ('erDetail', '对账明细结果', 'dzDz', 1, 'ENAB', 'PUB', 'MEU_AID', 'D_DZ', '', 'S', '', 'dzPara', '', 0, ");
		buffer.append("0, 0, 0, sequ_s_fun.nextval, '', '', 'MENU_INNER', 'POPIN')");
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'erDetail'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erDetail', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erDetail', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erDetail', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erDetail', 'SealRelaInfo')")
		.endScript();
	}
	
//	private void buildStory49489(ScriptBuilder builder) {
//		builder.createScript(ScriptType.DATA)
//		.addVersion("1.21.6.0")
//		.addUpdateType(UpdateType.REQUEST)
//		.addID("49489")
//		.addSql("delete from t_s_fun where C_FUN_CODE = 'DzOrgan' ");
//		StringBuffer buffer = new StringBuffer();
//		buffer.append("insert into t_s_fun (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
//		buffer.append("C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, C_IS_LIC_CONTROL,"); 
//		buffer.append("N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG) values ('DzOrgan', '关联组合设置', 'dzPara', 1, 'ENAB', 'PORT', 'MEU_FUN', 'P_YW', '', 'S', '', '', '', 0, 0, ");
//		buffer.append("0, 0, '6944', '', '', 'MENU_INNER', 'POPIN', '', 1.000000000000000, '[root]', 3)");
//		builder.addSql(buffer).endScript();
//	}

	private void buildStory41535(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("41535")
		.addSql("delete from T_S_FUN where C_FUN_CODE = 'dzPerRela' ")
		.addSql("delete from T_S_FUN where C_FUN_CODE = 'dzPubRela' ")
		.addSql("delete from T_S_FUN_RIGHTS where C_FUN_CODE = 'dzPerRela' ")
		.addSql("delete from T_S_FUN_RIGHTS where C_FUN_CODE = 'dzPubRela' ")
		.addSql("DELETE FROM t_s_dv_voc WHERE C_DV_TYPE = 'SEND_WAY' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append("C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE ");
		buffer.append(") values ('dzPerRela', '指标个性设置', 'dzDz', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', '', 'S', '', 'dzPerRela', ");  
		buffer.append("'设置电子对账中的指标代码与4.5系统中的指标对应关系', 1, 0, 1, 1, sequ_s_fun.nextval, '', '', 'MENU_INNER', 'POPIN')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,"); 
		buffer.append("C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE"); 
		buffer.append(") values ('dzPubRela', '指标关联设置', 'dzDz', 2, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', '', 'S', '', 'dzPubRela', ");
		buffer.append("'设置电子对账中的指标代码与4.5系统中的指标对应关系', 1, 0, 1, 1, sequ_s_fun.nextval, 'YSSELECRECO', '', 'MENU_INNER', 'POPIN')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'ADD', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'CHK', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'CPY', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'CommSet', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'DEL', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'EPT', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'SAVE', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'PNT', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'RFH', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'SAVE', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'SealRelaInfo', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'UCHK', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPubRela', 'UPD', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'ADD', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'CHK', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'CPY', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'CommSet', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'DEL', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'EPT', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'FaxPolicy', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'PNT', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'RFH', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'SAVE', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'SealRelaInfo', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'UCHK', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE, C_SIDE_TOOL) values ('dzPerRela', 'UPD', '')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('SEND_ORGIN_VAL', '发送', 'SEND_WAY', '发送方式', 0, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('NOSEND', '不发送', 'SEND_WAY', '发送方式', 0, 'ENAB', '[root]')");
		builder.addSql(buffer);
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dv_voc (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE, C_AUTH_ORG_CODE)");
		buffer.append("values ('SEND0', '0值发送', 'SEND_WAY', '发送方式', 0, 'ENAB', '[root]')");
		builder.addSql(buffer).endScript();
	}
	private void buildStory46715(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("46715")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_SSZBZSSL') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_SSZBZSSL', '电子对账实收资本是否需要数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账实收资本是否需要数量', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer).endScript();
		
	}
	private void buildStory46827(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("46827")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_HJXJ') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_HJXJ', '电子对账合计小计是否需要数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账合计小计是否需要数量', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
		
	}
	
	private void buildStory48815(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("48815")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_BBFS' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_BBFS', '电子对账数据使用本币发送', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账余额表数据原币金额使用本币金额发送，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	private void buildStory48443(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("48443")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_YZJSZQZ' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_YZJSZQZ', '电子对账数据溢折价市值列取估值表成本值', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据溢折价市值列取估值表成本值，是取成本列，否取市值列，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	private void buildStory45857(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("45857")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('SV_BB_DZDZ_GZSJKMSZ','SV_BB_DZDZ_GZKMSZ') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_GZSJKMSZ', '电子对账数据估增3级汇总科目市值列为0', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据估增3级汇总科目市值列为0', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		builder.addSql("update t_s_dsp_para set C_DV_PLAT_VALUE = '1' where C_DSP_CODE = 'SV_BB_DZDZ_GZSJKMSZ'");
		
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_GZKMSZ', '电子对账数据估增4级明细科目市值列为0', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据估增4级明细科目市值列为0', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		builder.addSql("update t_s_dsp_para set C_DV_PLAT_VALUE = '1' where C_DSP_CODE = 'SV_BB_DZDZ_GZKMSZ'")
		.endScript();
		
	}

	private void buildStory46253(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("46253")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_GZSFZMXKM' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_GZSFZMXKM', '估增最明细科目发送时,是否明细数据字段取值情况默认为0', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '估增最明细科目发送时， 是否是最明细科目该字段取值情况，默认为0，否则为1(设为是的时候，明细是1，上级是0，设为否的时候，明细是0，上级是1)', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
		
	}

	private void buildStory46241(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("46241")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN('SV_BB_DZDZ_YZJMXKM','SV_BB_ELCE_GZ_AMOUNT') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_YZJMXKM', '电子对账数据溢折价明细科目是否发送', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账数据溢折价明细科目是否发送(此参数只针对最明细科目)', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_ELCE_GZ_AMOUNT', '估值表非明细科目是否生成数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账估值表非明细科目是否生成数量', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		builder.addSql("update t_s_dsp_para set C_DV_PLAT_VALUE = '0',C_DSP_NAME='电子对账估值表非明细科目是否生成数量',C_DESC ='电子对账估值表非明细科目是否生成数量,默认为否' where C_DSP_CODE = 'SV_BB_ELCE_GZ_AMOUNT'")
		.endScript();
		
	}

	private void buildStory46220(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("46220")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_JZZZL' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_JZZZL', '电子对账数据净值增长率是否取本期净值增值率', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据净值增长率是否取本期净值增值率', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
		
	}

	private void buildStory45862(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("45862")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_ZBXSSZB' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_ZBXSSZB', '电子对账数据指标项市值占比列为0', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据指标项市值占比列为0，此参数只处理0018,0019以及0021', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	private void buildStory45858(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("45858")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_WHQQDWCB' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_WHQQDWCB', '电子对账数据无行情时取单位成本', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据无行情时取单位成本', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	private void buildStory45854(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("45854")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_CYDALHQ' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('SV_BB_DZDZ_CYDALHQ', '电子对账数据持有到期类成本科目行情取面值', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账数据持有到期类成本科目行情取面值', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	private void buildStory44562(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.8")
		.addUpdateType(UpdateType.REQUEST)
		.addID("44562")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE= 'SV_BB_DZDZ_CBJSYZJ' ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into t_s_dsp_para (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, ");
		buffer.append(" C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append(" values ('SV_BB_DZDZ_CBJSYZJ', '电子对账数据成本列加上溢折价', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', ");
		buffer.append(" '电子对账发送的数据，持有到期和可供出售类的成本明细科目成本列加上溢折价，科目名称后面加上 “（总价）”', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
	}

	/**
	 * @Description: STORY35703估值表自检以及自动生成发送电子对账
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月21日 下午4:32:28
	 */
	private void buildStory35703(ScriptBuilder builder) {
		builder.createScript(ScriptType.STRUCTURE)
		.addVersion("1.20.4.6")
		.addUpdateType(UpdateType.REQUEST)
		.addID("35703")
		.addSql("alter table T_D_ER_RELA add c_high_time VARCHAR2(50) default 0 ")
		.addSql("alter table T_D_ER_RELA add c_interval VARCHAR2(50) default 10 ")
		.addSql("comment on column T_D_ER_RELA.c_high_time is '重发次数'")
		.addSql("comment on column T_D_ER_RELA.c_interval is '间隔时间'")
		.endScript();
	}

	/**
	 * @Description: STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月21日 下午4:32:28
	 */
	private void buildStory42660(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.20.4.7.20170317")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("42660")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'SWITCH_MARK'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('MAIN', '主机', 'SWITCH_MARK', '主备类型', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('STANDBY', '备机', 'SWITCH_MARK', '主备类型', 2, 'ENAB')")
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'erPara'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('erPara', '深证通伺服器设置', 'dzDz', 1, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzPara', null, 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
			
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'erPara'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('erPara', 'UPD')")
		.addSql("")
		
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'mrInfo'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY)");
		buffer.append(" VALUES ('mrInfo', '主备信息设置', 'dzDz', 1, 'ENAB', 'PUB', 'MEU_AID', 'D_DZ', null, 'S', null, 'dzPara', null, 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', null)");
			
		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'mrInfo'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('mrInfo', 'UPD')")
		.addSql("")
		// 本段SQL语句结束符
		.endScript();
		
		builder.createScript(ScriptType.STRUCTURE)
		.addVersion("1.20.4.7.20170317")
		.addUpdateType(UpdateType.REQUEST)
		.addID("42660")
		.addSql("alter table T_D_ER_RELA add c_erpara_code VARCHAR2(50) default 'defalut' not null")
		.addSql("alter table T_D_ER_RELA modify c_src_app_logo null")
		.addSql("alter table T_D_ER_RELA modify c_pkg_password null")
		.endScript();
		
		
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.7.20170317")
		.addUpdateType(UpdateType.REQUEST)
		.addID("42660");
		buffer = new StringBuffer();

		buffer.append("insert into T_D_ER_PARA(C_IDEN,C_PARA_CODE,C_PARA_NAME,C_SRC_USERID,C_SRC_APPID,C_PKG_PASSWORD,C_DEPT_CODE,C_CERT_ID," );
		buffer.append(" N_CHECK_STATE,C_UPDATE_BY,C_UPDATE_TIME,C_CHECK_BY,C_CHECK_TIME)" ); 
		buffer.append(" select SEQU_D_ER_PARA.NEXTVAL,B.C_SRC_APP_LOGO,B.C_SRC_APP_LOGO,B.C_SRC_USER,B.C_SRC_APP_LOGO," ); 
		buffer.append(" B.C_PKG_PASSWORD,B.C_DEPT_CODE,B.C_CERT_ID,1,'Admin',to_char(sysdate,'yyyymmdd hh24:mi:ss')," ); 
		buffer.append(" 'Admin',to_char(sysdate,'yyyymmdd hh24:mi:ss') FROM (" ); 
		buffer.append(" SELECT TT.C_SRC_APP_LOGO,TT.C_SRC_USER,TT.C_PKG_PASSWORD,TT.C_DEPT_CODE,TT.C_CERT_ID FROM (");
		buffer.append(" SELECT B.C_SRC_APP_LOGO,NVL(B.C_TARGET_USER_LOGO, ' ') AS C_SRC_USER," ); 
		buffer.append(" B.C_PKG_PASSWORD,B.C_DEPT_CODE,B.C_CERT_ID, " );
		buffer.append(" ROW_NUMBER() OVER(PARTITION BY B.C_SRC_APP_LOGO ORDER BY C_TARGET_USER_LOGO DESC NULLS LAST) RANK ");
		buffer.append(" FROM T_D_ER_RELA B WHERE B.C_SRC_APP_LOGO IS NOT NULL) TT WHERE TT.RANK =1)B");

		builder.addSql(buffer);
		
		buffer = new StringBuffer();

		buffer.append("INSERT INTO T_D_ER_MRINFO(C_IDEN,C_PARA_CODE,C_DV_SWITCH_MARK,C_MR_IP,C_MR_PORT,N_ORDER)");
		buffer.append(" SELECT SEQU_D_ER_MRINFO.NEXTVAL,B.C_PARA_CODE,'MAIN','127.0.0.1','12912',1 FROM T_D_ER_PARA B");

		builder.addSql(buffer)
		
		/**
		 * TASK #1261797 【大成基金】20210331电子对账参数设置中关联的伺服器错乱
		 * 大成基金升级:如果设置的源appid不为空，就用appid作为伺服器关联编码。这个编码后面如果有改动，导致升级的时候有错乱关联伺服器的现象
		 */
//		buffer = new StringBuffer();
//		buffer.append("UPDATE T_D_ER_RELA A SET A.C_ERPARA_CODE = A.C_SRC_APP_LOGO where TRIM(A.C_SRC_APP_LOGO) IS NOT NULL");
//		builder.addSql(buffer)
		.endScript();
		
	}
	
	/**
	 * @Description: BUG #167771对账指标关联界面少词汇 
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author shiliang 
	 * @date 2017年8月1日
	 */
	private void buildBug167771(ScriptBuilder builder){
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.BUG)
		// 需求或者bug号
		.addID("167771")
//		.addSql(" delete from T_S_DZ_TYPE where C_DZ_CODE in('01','1001','1011','1031','03','1701','1801','1711','1811','1901')", true)
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '01', '01类型', '[root]') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1001', '余额表', '01') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1011', '估值表', '01') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1031', '科目表', '01') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '03', '03类型', '[root]') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1701', '资产负债表', '03') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1801', '利润表', '03') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1711', '资产负债表（新准则）', '03') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1811', '经营业绩表（新准则）', '03') ")
		.addSql(" insert into T_S_DZ_TYPE (C_IDEN, C_DZ_CODE, C_DZ_NAME, C_DZ_CODE_P) values (SEQU_S_DZ_TYPE.NEXTVAL, '1901', '所有者权益（基金净值）变动表', '03') ")
		.endScript();
		
	}
	/**
	 * @Description:STORY52785上海银行---银行电子对账特殊处理
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月21日 下午4:32:28
	 */
	private void buildStory52785(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("52785")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_CODE = 'socket'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('socket', 'Socket', 'COMM_TYPE', '通讯类型', 2, 'ENAB')")
		
		// 本段SQL语句结束符
		.endScript();
		
	}
	/**
	 * @Description: STORY #57746 增加民生银行深证通发送附件的功能
	 * @param @param builder    设定文件 
	 * @return void    返回类型 
	 * @author wulongxing 
	 * @date 2018年7月21日 下午4:32:28
	 */
	private void buildStory57746(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("57746")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE='BUSI_TYPE' AND C_DV_CODE = 'BUSI_DZFJ'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('BUSI_DZFJ', '电子附件', 'BUSI_TYPE', '业务类型', 4, 'ENAB')")
		//删除对账类型中的“银企直连”
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE='BUSI_TYPE' AND C_DV_CODE = 'BUSI_YQZL' ")
		//删除通讯类型中的“互联网”
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE='COMM_TYPE' AND C_DV_CODE = 'INTERNET' ")
		//修改估值表发送模式词汇名称
		.addSql("UPDATE T_S_DV_VOC SET C_DV_NAME='有估增科目' WHERE C_DV_TYPE='GZBFSMS' AND C_DV_CODE='GZBFSMS_YGZ'")
		.addSql("UPDATE T_S_DV_VOC SET C_DV_NAME='无估增科目' WHERE C_DV_TYPE='GZBFSMS' AND C_DV_CODE='GZBFSMS_WGZ'")
		.endScript();
	}
	
	private void buildStory57239(ScriptBuilder builder) {
		// 修改表数据
		builder.createScript(ScriptType.DATA)
		// 版本号
		.addVersion("1.21.5.0")
		// 需求或者bug标识
		.addUpdateType(UpdateType.REQUEST)
		// 需求或者bug号
		.addID("57239")
		// 脚本，可添加多条SQL
		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'DZ_MODE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('DZMO_SZT', '深证通模式', 'DZ_MODE', '对账模式', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('DZMO_QT', '其他模式', 'DZ_MODE', '对账模式', 2, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'SUB_SUIT'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('SUB_SUIT_YES', '是', 'SUB_SUIT', '应用下级科目', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('SUB_SUIT_NO', '否', 'SUB_SUIT', '应用下级科目', 2, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'IGNORE_TYPE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('HL_ROW', '行忽略', 'IGNORE_TYPE', '忽略类型', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('HL_COL', '列忽略', 'IGNORE_TYPE', '忽略类型', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('HL_CELL', '单元格忽略', 'IGNORE_TYPE', '忽略类型', 3, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'IGNORE_SCOPE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('IGNORE_SCOPE_INNER', '本方', 'IGNORE_SCOPE', '忽略方向', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('IGNORE_SCOPE_OUT', '对方', 'IGNORE_SCOPE', '忽略方向', 2, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'KM_MAP_SCOPE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YSFW_GGYS', '公共映射', 'KM_MAP_SCOPE', '映射范围', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YSFW_TGFYS', '托管方映射', 'KM_MAP_SCOPE', '映射范围', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_YSFW_CPYS', '产品映射', 'KM_MAP_SCOPE', '映射范围', 3, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_DZ_RESULT'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('RDZ_RESULT_DIFF', '不一致', 'REVE_DZ_RESULT', '对账结果', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('RDZ_RESULT_NO', '未对账', 'REVE_DZ_RESULT', '对账结果', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('RDZ_RESULT_SAME', '一致', 'REVE_DZ_RESULT', '对账结果', 3, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_HANDLE_STATE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_HS_SUCCESS', '对账成功', 'REVE_HANDLE_STATE', '处理状态', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_HS_FAIL', '对账失败', 'REVE_HANDLE_STATE', '处理状态', 2, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_LOCK_STATE'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('RLS_SDZT_BSD', '未锁定', 'REVE_LOCK_STATE', '锁定状态', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('RLS_SDZT_SD', '锁定', 'REVE_LOCK_STATE', '锁定状态', 2, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_GZ_HDX'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_SZ', '本币市值', 'REVE_GZ_HDX', '估值核对项', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_BBCB', '本币成本', 'REVE_GZ_HDX', '估值核对项', 2, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_BBGZ', '本币估增', 'REVE_GZ_HDX', '估值核对项', 3, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_HQ', '行情', 'REVE_GZ_HDX', '估值核对项', 4, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_GZ_SL', '数量', 'REVE_GZ_HDX', '估值核对项', 5, 'ENAB')")

		.addSql("DELETE FROM T_S_DV_VOC WHERE C_DV_TYPE = 'REVE_KMFW'")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_KMFW_INNER', '本方', 'REVE_KMFW', '科目范围', 1, 'ENAB')")
		.addSql("INSERT INTO T_S_DV_VOC (C_DV_CODE, C_DV_NAME, C_DV_TYPE, C_DESC, N_ORDER, C_DV_STATE) VALUES ('REVE_KMFW_OUT', '对方', 'REVE_KMFW', '科目范围', 2, 'ENAB')")
		
		//菜单，按钮
		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzAssMap'");
		StringBuffer buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzAssMap', '资托资产映射', 'dzDz', 23, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '资托资产映射', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzAssMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzAssMap', 'UPD')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzIgnoreItem'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzIgnoreItem', '忽略设置管理', 'dzDz', 24, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '忽略设置管理', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzIgnoreItem'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzIgnoreItem', 'UPD')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzInfo'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzInfo', '反向电子对账管理', 'dzDz', 22, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '反向电子对账管理', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzInfo'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzInfo', 'EPT')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzKmMap'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzKmMap', '科目映射管理', 'dzDz', 20, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '科目映射管理', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzKmMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'RFH')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzKmMap', 'UCHK')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzMessageDetail'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzMessageDetail', '对方报文明细', 'dzDz', 29, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '反向电子对方报文明细', 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '1', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzMessageDetail'")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzPortKmMap'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzPortKmMap', '产品映射设置', 'dzDz', 27, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '产品映射设置', 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '1', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzPortKmMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'RFH')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPortKmMap', 'UCHK')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzPublicKmMap'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzPublicKmMap', '公共映射设置', 'dzDz', 25, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '公共映射设置', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzPublicKmMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'RFH')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzPublicKmMap', 'UCHK')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzResultDetail'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzResultDetail', '对账结果明细', 'dzDz', 28, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '反向电子对账结果明细', 0, 0, 0, 0, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '1', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzResultDetail'")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzTghKmMap'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzTghKmMap', '托管方映射设置', 'dzDz', 26, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '公共映射设置', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzTghKmMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'RFH')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzTghKmMap', 'UCHK')")
		.addSql("")


		.addSql("DELETE FROM T_S_FUN WHERE C_FUN_CODE = 'reveDzZbMap'");
		buffer = new StringBuffer();
		buffer.append("INSERT INTO T_S_FUN (C_FUN_CODE, C_FUN_NAME, C_FUN_CODE_P, N_ORDER, C_DV_STATE, C_DV_FUN_LEVEL, C_DV_FUN_TYPE, C_DV_FUN_CLS, C_COMP_FILE, C_SRC_MARK,");
		buffer.append(" C_FUN_PARAMS, C_ICO_FILE, C_DESC, N_CHECK, N_LOCK, N_RECYCLE, N_USER, C_IDEN, C_APP_CODE, N_DELETED, C_DV_MENU_CLS, C_DV_SHOW_TYPE, N_VERIFY, C_AUTH_ORG_CODE, N_AUTHORG)");
		buffer.append(" VALUES ('reveDzZbMap', '指标映射管理', 'dzDz', 21, 'ENAB', 'PUB', 'MEU_FUN', 'D_DZ', null, 'S', null, 'dzBbInfo', '指标映射管理', 1, 0, 1, 1, SEQU_S_FUN.NEXTVAL, null, null, 'MENU_INNER', 'POPIN', '0', '[root]', -1)");

		builder.addSql(buffer)
		.addSql("DELETE FROM T_S_FUN_RIGHTS WHERE C_FUN_CODE = 'reveDzZbMap'")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'ADD')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'CHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'CPY')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'CommSet')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'DEL')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'EPT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'FaxPolicy')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'PNT')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'SAVE')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'SealRelaInfo')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'UCHK')")
		.addSql("INSERT INTO T_S_FUN_RIGHTS (C_FUN_CODE, C_DV_OPER_TYPE) VALUES ('reveDzZbMap', 'UPD')")
		.addSql("")
		
		// 本段SQL语句结束符
		.endScript();
		
	}
	/**
	 * BUG214534【招商基金】工行社保产品电子对账发送0016基金资产单位净值需要发送估值增值列数据
	 * @param builder
	 */
	private void buildBUG214534(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0.2")
		.addUpdateType(UpdateType.BUG)
		.addID("214534")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_DWJZIV') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_DZDZ_DWJZIV', '电子对账估值指标0016基金资产单位净值是否需要发送估值增值列数据', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账估值指标0016基金资产单位净值是否需要发送估值增值列数据，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		
		builder.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_MVCBLEVEL3') ");
		buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_DZDZ_MVCBLEVEL3', '电子对账估值持有到期类债券投资1111开头的第三级科目的市值取成本', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账估值持有到期类债券投资1111开头的第三级科目的市值取成本，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		
		.endScript();
		
	}
	/**
	 * BUG217588科目代码1111.开头的科目类别与托管行的1103.开头的科目类别匹配，在生成对账数据时增加参数控制是否将1111.科目代码替换为1103.
	 * @param builder
	 */
	private void buildBUG217588(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0.2")
		.addUpdateType(UpdateType.BUG)
		.addID("217588")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_DZDZ_TRANS1111') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_DZDZ_TRANS1111', '电子对账科目代码1111.是否转换为1103.', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '0', '电子对账科目代码1111.是否转换为1103.，默认为否', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		
		.endScript();
		
	}
		/**
	 * STORY62407【大成基金】【紧急】余额表发送电子对账非明细科目是否发送数量
	 * @param builder
	 */
	private void buildSTORY62407(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.5.0.14")
		.addUpdateType(UpdateType.REQUEST)
		.addID("62407")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_ELEC_YE_AMOUNT') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_ELEC_YE_AMOUNT', '电子对账余额表非明细科目是否发送数量', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账余额表非明细科目是否发送数量,默认为是', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		builder.addSql("update t_s_dsp_para set C_DV_PLAT_VALUE = '0', C_DESC='电子对账余额表非明细科目是否发送数量,默认为否' where C_DSP_CODE = 'DZ_BB_ELEC_YE_AMOUNT'")
		.endScript();
		
	}
	/**
	 *BUG272834太平养老-电子对账生成的余额表6407.02四级科目不显示。
	 * @param builder
	 */
	private void buildBUG272834(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.21.6.0.4")
		.addUpdateType(UpdateType.BUG)
		.addID("272834")
		.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_ELEC_YE_6407') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_ELEC_YE_6407', '电子对账余额表社保基金是否发送6407.02下的4级科目', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账余额表社保基金是否发送6407.02下的4级科目,默认为是', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer);
		
		builder.addSql("delete from t_s_dsp_para where C_DSP_CODE IN ('DZ_BB_ELEC_YE_6605') ");
		buffer = new StringBuffer();
		buffer.append("insert into T_S_DSP_PARA (C_DSP_CODE, C_DSP_NAME, C_DSP_CLASS, C_DSP_GROUP_CODE, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DSG_CODE, C_DSG_NAME, C_DV_PLAT_VALUE, C_DESC, C_DSP_GROUP_NAME, C_DAT_CODE, C_DS_TPYE)");
		buffer.append("values ('DZ_BB_ELEC_YE_6605', '电子对账余额表社保基金是否发送6605.03下的3级科目', 'SV', 'BB', 'VOC', 'BOOL_TYPE', 'BB', '报表类', '1', '电子对账余额表社保基金是否发送6605.03下的3级科目,默认为是', '报表类', 'PUB', 'pubvocabulary')");
		builder.addSql(buffer)
		.endScript();
		
	}
	/**
	 * sequ_d_er_sn序列调整为可循环
	 * @param builder
	 */
	private void buildSequSn(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.BUG)
		.addID("272834")
		.addSql("alter sequence sequ_d_er_sn cycle")
		.endScript();
	}
	
	/**
	 * STORY #91828 富国基金-产品参数复制功能，增加“电子对账综合参数”接口
	 * @param builder
	 */
	private void buildStory91828(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.300.7.0.0")
		.addUpdateType(UpdateType.REQUEST)
		.addID("91828")
		.addSql(" DELETE t_s_data_copy where C_DATA_CODE IN ('dzDspPara','dzdzClsCopy') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append(" insert into t_s_data_copy (C_IDEN, C_DATA_NAME, C_DATA_CODE, C_DATA_CODE_P, C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA) ");
		buffer.append(" values (sequ_s_data_copy.nextval, '电子对账综合参数', 'dzDspPara', 'dzDz', '1', 'IDzdzDSPValueCopyService', 45, null) ");
		builder.addSql(buffer);
		
		buffer = new StringBuffer();
		buffer.append(" insert into t_s_data_copy (C_IDEN, C_DATA_NAME, C_DATA_CODE, C_DATA_CODE_P, C_DV_STATE, C_SERVICE_CODE, N_ORDER, C_DATA_PARA) ");
		buffer.append(" values (sequ_s_data_copy.nextval, '电子对账分级设置', 'dzdzClsCopy', 'dzDz', '1', 'IDzdzClsCopyService', 44, null) ");
		builder.addSql(buffer)
		.endScript();
		
	}
	
	/**
	 * STORY #101696 【内部优化】电子对账综合参数支持分级设置，生成发送逻辑不受估值参数影响
	 * @param builder
	 */
	private void buildStory101696(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
				.addVersion("1.300.7.0.0")
				.addUpdateType(UpdateType.REQUEST)
				.addID("101696")
				.addSql(" DELETE T_D_ER_DSP_PARA where C_DSP_CODE IN('SV_ZB_HQBZ','SV_JD_DWJZ_001','SV_JD_DWJZ_002','SV_JD_SSZB','SV_JD_SSZB_JWFS','DZ_BB_DZDZ_GZBFSXSWS','DZ_BB_DZDZ_HQBZ','DZ_BB_DZDZ_DWJZ_001','DZ_BB_DZDZ_DWJZ_002','DZ_JD_SSZB','DZ_JD_SSZB_JWFS') ");
		StringBuffer buffer = new StringBuffer();
		buffer.append("insert into T_D_ER_DSP_PARA (C_IDEN, C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE) ");
		buffer.append(" SELECT SEQU_D_ER_DSP_PARA.NEXTVAL,C_DSP_CODE, C_DSP_NAME, C_DSP_VALUE_TYPE, C_DV_TYPE, C_DV_PLAT_VALUE, C_DESC, C_DS_TPYE FROM T_S_DSP_PARA ");
		buffer.append(" WHERE C_DSP_CODE IN('SV_ZB_HQBZ','SV_JD_DWJZ_001','SV_JD_DWJZ_002','SV_JD_SSZB','SV_JD_SSZB_JWFS') ");
		builder.addSql(buffer)
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_HQBZ' where  c_dsp_code = 'SV_ZB_HQBZ' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_DWJZ_001' where  c_dsp_code = 'SV_JD_DWJZ_001' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_DWJZ_002' where  c_dsp_code = 'SV_JD_DWJZ_002' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_JD_SSZB' where  c_dsp_code = 'SV_JD_SSZB' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_JD_SSZB_JWFS' where  c_dsp_code = 'SV_JD_SSZB_JWFS' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_ELCE_GZ_AMOUNT' where  c_dsp_code = 'SV_BB_ELCE_GZ_AMOUNT' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_ZQDMZH' where  c_dsp_code = 'SV_BB_DZDZ_ZQDMZH' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_JYQDZH' where  c_dsp_code = 'SV_BB_DZDZ_JYQDZH' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_CBJSYZJ' where  c_dsp_code = 'SV_BB_DZDZ_CBJSYZJ' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_TZYZJSZQZ' where  c_dsp_code = 'SV_BB_DZDZ_TZYZJSZQZ' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_YZJSZQZ' where  c_dsp_code = 'SV_BB_DZDZ_YZJSZQZ' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_SSZBZSSL' where  c_dsp_code = 'SV_BB_DZDZ_SSZBZSSL' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_HJXJ' where  c_dsp_code = 'SV_BB_DZDZ_HJXJ' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_KFPSY' where  c_dsp_code = 'SV_BB_DZDZ_KFPSY' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_FJHJXJSFJZH' where  c_dsp_code = 'SV_BB_DZDZ_FJHJXJSFJZH' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_YSGJMFFS' where  c_dsp_code = 'SV_BB_DZDZ_YSGJMFFS' ")
				.addSql("  UPDATE T_D_ER_DSP_PARA SET c_dsp_code = 'DZ_BB_DZDZ_YSGJGYJZCB' where  c_dsp_code = 'SV_BB_DZDZ_YSGJGYJZCB' ")
				.addSql(" DELETE T_D_ER_DSP_VALUE where C_DSP_CODE IN('SV_ZB_HQBZ','SV_JD_DWJZ_001','SV_JD_DWJZ_002','SV_JD_SSZB','SV_JD_SSZB_JWFS','DZ_BB_DZDZ_GZBFSXSWS','DZ_BB_DZDZ_HQBZ','DZ_BB_DZDZ_DWJZ_001','DZ_BB_DZDZ_DWJZ_002','DZ_JD_SSZB','DZ_JD_SSZB_JWFS') ");
		buffer = new StringBuffer();
		buffer.append("  insert into T_D_ER_DSP_VALUE (C_IDEN, C_PORT_CODE, C_DV_PARAM_TYPE, C_DSP_CODE, C_DV_PARAMS_VALUE,C_PORT_CLS_CODE,D_BEGIN, D_END, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME) ");
		buffer.append("  SELECT SEQU_D_ER_DSP_VALUE.NEXTVAL, C_PORT_CODE,'PORT_PARAM_CUSTOM',C_DSP_CODE, C_DV_PARAMS_VALUE,C_PORT_CLS_CODE,D_BEGIN, D_END, N_CHECK_STATE, C_UPDATE_BY, C_UPDATE_TIME, C_CHECK_BY, C_CHECK_TIME ");
		buffer.append("  FROM T_P_AO_PARAMS where C_DSP_CODE  IN('SV_ZB_HQBZ','SV_JD_DWJZ_001','SV_JD_DWJZ_002','SV_JD_SSZB','SV_JD_SSZB_JWFS') AND D_END > SYSDATE AND C_UPDATE_TIME IS NOT NULL ");
		builder.addSql(buffer)
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_HQBZ' where  c_dsp_code = 'SV_ZB_HQBZ' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_DWJZ_001' where  c_dsp_code = 'SV_JD_DWJZ_001' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_DWJZ_002' where  c_dsp_code = 'SV_JD_DWJZ_002' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_JD_SSZB' where  c_dsp_code = 'SV_JD_SSZB' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_JD_SSZB_JWFS' where  c_dsp_code = 'SV_JD_SSZB_JWFS' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_ELCE_GZ_AMOUNT' where  c_dsp_code = 'SV_BB_ELCE_GZ_AMOUNT' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_ZQDMZH' where  c_dsp_code = 'SV_BB_DZDZ_ZQDMZH' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_JYQDZH' where  c_dsp_code = 'SV_BB_DZDZ_JYQDZH' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_CBJSYZJ' where  c_dsp_code = 'SV_BB_DZDZ_CBJSYZJ' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_TZYZJSZQZ' where  c_dsp_code = 'SV_BB_DZDZ_TZYZJSZQZ' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_YZJSZQZ' where  c_dsp_code = 'SV_BB_DZDZ_YZJSZQZ' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_SSZBZSSL' where  c_dsp_code = 'SV_BB_DZDZ_SSZBZSSL' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_HJXJ' where  c_dsp_code = 'SV_BB_DZDZ_HJXJ' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_KFPSY' where  c_dsp_code = 'SV_BB_DZDZ_KFPSY' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_FJHJXJSFJZH' where  c_dsp_code = 'SV_BB_DZDZ_FJHJXJSFJZH' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_YSGJMFFS' where  c_dsp_code = 'SV_BB_DZDZ_YSGJMFFS' ")
				.addSql("  UPDATE T_D_ER_DSP_VALUE SET c_dsp_code = 'DZ_BB_DZDZ_YSGJGYJZCB' where  c_dsp_code = 'SV_BB_DZDZ_YSGJGYJZCB' ")
				.endScript();
	}
}
