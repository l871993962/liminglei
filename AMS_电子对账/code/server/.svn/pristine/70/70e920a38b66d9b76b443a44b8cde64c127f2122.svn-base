package com.yss.ams.db.upgrade.elecreco.structs.tables;

import com.yss.ams.db.upgrade.elecreco.structs.cons.ElecModuleInfo;
import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ColumnBuilder;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraPartitionType;

public class TableDescImpl extends BaseStructDesc {
	private TableBuilder tableBuilder = null;
	
	@Override
	public void execute() throws Exception {
		tableBuilder = getTableBuilder();
		
		buildT_C_BW_MRFORWARD();
		
		buildT_D_ER_INFO();
		buildT_D_ER_STEP_STATE();
		buildT_D_ER_KM();
		buildT_D_ER_GZ();
		buildT_D_ER_DBLGZ();
		buildT_D_ER_YE();
		buildT_D_ER_ZCFZ();
		buildT_D_ER_SYZQYBD();
		buildT_D_ER_LR();
		buildT_D_ER_JZCBD();
		buildT_D_ER_RESULT();
		
		buildT_P_ER_REPCFG();
		buildT_P_ER_REPCOLCFG();
		buildT_D_ER_RELA();
		buildT_Z_BI_RELA();
		buildT_D_ER_TMPL();
		buildT_D_ER_TMPL_RELA();
		buildT_D_ER_PARA();
		buildT_D_ER_MRINFO();
		buildR_D_ER_TEMP_PORT();
		buildT_Z_BI_PER_RELA();
		buildT_D_ER_UN_PORT();
		buildT_D_ER_RESVIEW();
		buildT_D_ER_AUTOSTATE();
		buildT_D_ER_REVE_RESRELA();
		buildT_D_ER_REVE_KM_MAP();
		buildT_D_ER_REVE_KM_RELA();
		buildT_D_ER_REVE_ASS_MAP();
		buildT_D_ER_REVE_ZB_MAP();
		buildT_D_ER_REVE_INFO();
		buildT_D_ER_REVE_RESULT();
		buildT_D_ER_REVE_IGNORE();
		buildT_D_OD_GZ_OUT();
		buildT_D_OD_KM_OUT();
		buildT_D_OD_YE_OUT();
		buildT_D_ER_SPLIT_RELA();
		buildT_D_ER_SPLIT_RULE();
		buildT_D_ER_TASK();
		buildT_D_ER_RPT_LOG();
		buildT_D_ER_DSP_PARA();
		buildT_D_ER_DSP_VALUE();
		buildT_D_ER_DZ_TYPE();
		buildT_D_ER_DV_VOC();
		buildT_D_ER_SPECIAL_ZB();
		buildT_P_ER_TRANSREPCFG();
		buildT_D_ER_XML_GZ();
	}
	
	/**
	 * STORY #103644 华夏基金-新增托管行电子对账结果数据查询视图 
	 * @throws Exception
	 */
	private void buildT_D_ER_XML_GZ() throws Exception{
		tableBuilder.createTable("T_D_ER_XML_GZ","估值表发送数据明细表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
			.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
			.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目代码")
			.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目名称")
			.addColumn("N_VA_PRICE", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "行情价格")
			.addColumn("N_QUOT_LOGO", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "行情标志")
			.addColumn("N_AMOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "证券数量")
			.addColumn("N_ORIG_COST", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "原币证券成本")
			.addColumn("N_PORT_COST", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "本币证券成本")
			.addColumn("N_ORIG_MV", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "原币证券市值")
			.addColumn("N_PORT_MV", OraColunmnTypeOnlyLength.VARCHAR2, 45, true, "", "本币证券市值")
			.addColumn("N_ORIG_IV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "原币估值增值")
			.addColumn("N_PORT_IV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "本币估值增值")
			.addColumn("N_CB_JZ_BL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "成本占净值比")
			.addColumn("N_SZ_JZ_BL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "市值占净值比")
			.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "是否明细科目")
			.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
			.addColumn("C_NAV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "数据类型")
			.addColumn("C_PORT_CLS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "分级组合代码")
			.addPrimaryConstraint("PK_D_ER_XML_GZ", "C_IDEN")
			.createIndex("IDX_D_ER_XML_GZ", "C_SN,C_KM_CODE")
			.build(UpdateType.REQUEST, "103644", "华夏基金-新增托管行电子对账结果数据查询视图", "tongdengke@ysstech.com", "2021-04-12");
	}
	
	private void buildT_P_ER_TRANSREPCFG() throws Exception{
		tableBuilder.createTable("T_P_ER_TRANSREPCFG","对账对账交易数据配置").setModuleCode(ElecModuleInfo.MODULE_PARAM)
			.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
			.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "机构代码")
			.addColumn("C_TRANS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "电子对账报表")
			.addColumn("C_TPL_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "财务报表")
			.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
			.addColumn("C_DAT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "资产类型")
			.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
			.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
			.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
			.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
			.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
			.addPrimaryConstraint("PK_P_ER_TRANSREPCFG", "C_IDEN")
			.createUniqueIndex("IDX_P_ER_TRANSREPCFG", "C_ORG_CODE, C_TRANS_CODE, C_PORT_CODE,C_DAT_CODE")
			.build(UpdateType.REQUEST, "88316", "华夏基金-MOM产品个性化需求——场外和银行间交易数据发送托管行 ", "tongdengke@ysstech.com", "2020-06-08");

	}
	
	/**
	 * STORY81879【华宝基金】电子对账检查重要估值指标
	 * @throws Exception
	 */
	private void buildT_D_ER_SPECIAL_ZB() throws Exception {
		tableBuilder.createTable("T_D_ER_SPECIAL_ZB","特定指标信息表").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_KEY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "指标代码")
				.addColumn("C_KEY_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "指标名称")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "科目代码")
				.addColumn("C_DAT_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产类别")
				.addColumn("C_DAT_CLS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "资产类别名称")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查时间")
				.addPrimaryConstraint("PK_D_ER_SPECIAL_ZB", "C_IDEN")
				.createIndex("IDX_D_ER_SPECIAL_ZB", "C_DAT_CLS,C_KEY_CODE")
				.build(UpdateType.REQUEST, "81879", "新建", "liwenzhou@ysstech.com", "2019-11-12");
	}
	
	/**
	 * 反向对账-外部余额表
	 * @throws Exception 
	 */
	private void buildT_D_OD_YE_OUT() throws Exception {
		tableBuilder.createTable("T_D_OD_YE_OUT","反向导入接口-余额表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_TGH_NAME_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "外部托管行")
		.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "内部托管行代码")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合代码")
		.addColumn("C_ASS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合名称")
		.addColumn("D_GZ_DATE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
		.addColumn("C_ROW_INDEX", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "行下标")
		.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目代码")
		.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目名称")
		.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "币种代码")
		.addColumn("C_JD_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "借贷方向")
		.addColumn("C_DETAIL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "是否明细科目")
		.addColumn("N_ORIG_STARTBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期初余额（原币）")
		.addColumn("N_PORT_STARTBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期初余额（本币）")
		.addColumn("N_AMOUNT_STARTBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期初余额（数量）")
		.addColumn("N_ORIG_DEBIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期借方发生额（原币）")
		.addColumn("N_PORT_DEBIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期借方发生额（本位币）")
		.addColumn("N_AMOUNT_DEBIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期借方发生额（数量）")
		.addColumn("N_ORIG_CREDIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期贷方发生额（原币）")
		.addColumn("N_PORT_CREDIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期贷方发生额（本位币）")
		.addColumn("N_AMOUNT_CREDIT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "本期贷方发生额（数量）")
		.addColumn("N_ORIG_ENDBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期末余额（原币）")
		.addColumn("N_PORT_ENDBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期末余额（本位币）")
		.addColumn("N_AMOUNT_ENDBAL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "期末余额（数量）")
		.addColumn("N_J_TOLTAL_ORIG", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "累计借方发生额（原币）")
		.addColumn("N_J_TOLTAL_PORT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "累计借方发生额（本币）")
		.addColumn("N_J_TOLTAL_AMOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "借方累计数量")
		.addColumn("N_D_TOLTAL_ORIG", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "累计贷发生额（原币）")
		.addColumn("N_D_TOLTAL_PORT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "累计贷发生额（本币）")
		.addColumn("N_D_TOLTAL_AMOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "贷方累计数量")
		.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口使用字段")
		.addColumn("N_ADJUST", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "导入接口使用字段")
		.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口")
		.addColumn("C_USER", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "操作用户")
		.addColumn("C_PATH_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "路径类型")
		.addColumn("C_PATH", OraColunmnTypeOnlyLength.VARCHAR2, 400, true, "''", "文件路径")
		.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
		.createIndex("IDX_DEL_D_OD_YE_OUT", "C_TGH_NAME_OUT, C_ASS_CODE, D_GZ_DATE")
		.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	/**
	 * 反向对账-外部科目表
	 * @throws Exception 
	 */
	private void buildT_D_OD_KM_OUT() throws Exception {
		tableBuilder.createTable("T_D_OD_KM_OUT","反向导入接口-科目表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_TGH_NAME_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "外部托管行")
		.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "内部托管行代码")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合代码")
		.addColumn("C_ASS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合名称")
		.addColumn("D_GZ_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "业务日期")
		.addColumn("C_ROW_INDEX", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "行下标")
		.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目代码")
		.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "科目名称")
		.addColumn("C_KM_LEVEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "科目级别")
		.addColumn("C_KM_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "上级代码")
		.addColumn("C_DETAIL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "是否明细科目")
		.addColumn("C_DV_KM_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "科目类别")
		.addColumn("C_DV_JD_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "借贷方向")
		.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口使用字段")
		.addColumn("N_ADJUST", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "导入接口使用字段")
		.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口")
		.addColumn("C_USER", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "操作用户")
		.addColumn("C_PATH_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "路径类型")
		.addColumn("C_PATH", OraColunmnTypeOnlyLength.VARCHAR2, 400, true, "''", "文件路径")
		.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
		.createIndex("IDX_DEL_D_OD_KM_OUT", "C_TGH_NAME_OUT, C_ASS_CODE")
		.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	/**
	 * 反向对账-外部估值表
	 * @throws Exception 
	 */
	private void buildT_D_OD_GZ_OUT() throws Exception {
		tableBuilder.createTable("T_D_OD_GZ_OUT","反向导入接口-估值表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_TGH_NAME_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "外部托管行")
		.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "内部托管行代码")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合代码")
		.addColumn("C_ASS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "产品组合名称")
		.addColumn("D_GZ_DATE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
		.addColumn("C_ROW_INDEX", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "行下标")
		.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目代码")
		.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目名称")
		.addColumn("C_CURR_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "币种代码")
		.addColumn("C_EXRATE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "汇率")
		.addColumn("N_AMOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "数量")
		.addColumn("N_ORGI_UNIT_COST", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "单位成本（原币）")
		.addColumn("N_PORT_UNIT_COST", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "单位成本（本位币）")
		.addColumn("N_ORGI_COST", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "成本（原币）")
		.addColumn("N_PORT_COST", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "成本（本位币）")
		.addColumn("N_CB_JZ_BL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "成本占净值比例")
		.addColumn("N_ORGI_VA_PRICE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "行情（原币）")
		.addColumn("N_PORT_VA_PRICE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "行情（本位币）")
		.addColumn("N_ORGI_MV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "市值（原币）")
		.addColumn("N_PORT_MV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "市值（本币）")
		.addColumn("N_SZ_JZ_BL", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "市值占净值比例")
		.addColumn("N_ORGI_IV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "估增（原币）")
		.addColumn("N_PORT_IV", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "估增（本币）")
		.addColumn("C_TPXX", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "停牌信息")
		.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口使用字段")
		.addColumn("N_ADJUST", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "导入接口使用字段")
		.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "导入接口")
		.addColumn("C_USER", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "操作用户")
		.addColumn("C_PATH_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "路径类型")
		.addColumn("C_PATH", OraColunmnTypeOnlyLength.VARCHAR2, 400, true, "''", "文件路径")
		.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
		.createIndex("IDX_DEL_D_OD_GZ_OUT", "C_TGH_NAME_OUT, C_ASS_CODE, D_GZ_DATE")
		.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	/**
	 * STORY #72464 电子对账关于许可控制的功能模块改造 
	 * @throws Exception
	 */
	private void buildT_D_ER_DV_VOC() throws Exception {
		tableBuilder.createTable("T_D_ER_DV_VOC","电子对账词汇字典")
			.addColumn("C_DV_CODE",OraColunmnTypeOnlyLength.VARCHAR2,200,false,"' ' ", "词汇代码")
			.addColumn("C_DV_NAME",OraColunmnTypeOnlyLength.VARCHAR2,200,false,"' ' ", "词汇名称")
			.addColumn("C_DV_TYPE",OraColunmnTypeOnlyLength.VARCHAR2,50,false,"' ' ", "词汇类型")
			.addColumn("C_DESC",OraColunmnTypeOnlyLength.VARCHAR2,200,true,"", "备注，格式如公司行为—证券发行—发行地点")
			.addColumn("N_ORDER",OraColumnTypeNeedPrecision.NUMBER,3,0,false,"0 ", "词汇编号")
			.addColumn("C_DV_STATE",OraColunmnTypeOnlyLength.VARCHAR2,20,true,"'ENAB'", "是否启用")
			.addColumn("C_AUTH_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'[root]'", "权限组织机构")
			.createUniqueIndex("IDX_D_ER_DV_VOC", "C_DV_CODE")
			.createIndex("IDX_D_ER_DV_VOC001", "C_DV_TYPE")
			.build(UpdateType.REQUEST, "72464", "新建","liwenzhou@ysstech.com","2019-09-06");
	}
	
	/**
	 * STORY #72464 电子对账关于许可控制的功能模块改造 
	 * @throws Exception
	 */
	private void buildT_D_ER_DZ_TYPE() throws Exception {
		tableBuilder.createTable("T_D_ER_DZ_TYPE","电子对账对账类型字典").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "对账类型代码")
				.addColumn("C_DZ_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "对账类型名称")
				.addColumn("C_CHECK_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "对账结果映射代码")
				.addColumn("C_DZ_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上级代码")
				.addPrimaryConstraint("PK_D_ER_DZ_TYPE", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_DZ_TYPE", "C_DZ_CODE")
				.build(UpdateType.REQUEST, "72464", "新建","liwenzhou@ysstech.com","2019-09-06");
	}
	
	/**
	 * STORY #48560整合电子对账业务类参数到便于用户设置和组件解耦
	 * @throws Exception
	 */
	private void buildT_D_ER_DSP_PARA() throws Exception {
		tableBuilder.createTable("T_D_ER_DSP_PARA","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_DSP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "参数代码")
				.addColumn("C_DSP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "''", "参数名称")
				.addColumn("C_DSP_VALUE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "参数类型(字符串:VARCHAR；日期:DATE；词汇:VOC；数字：NUMBER)")
				.addColumn("C_DV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "词汇类型")
				.addColumn("C_DV_PLAT_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 500, false, "''", "默认值")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "描述")
				.addColumn("C_DS_TPYE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "词汇缓存")
				.addColumn("C_PARA_TPYE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'PRIVATE'", "参数类型")
				.addPrimaryConstraint("PK_D_ER_DSP_PARA", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_DSP_PARA", "C_DSP_CODE")
				.build(UpdateType.REQUEST, "48560", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	/**
	 * STORY #48560整合电子对账业务类参数到便于用户设置和组件解耦
	 * @throws Exception
	 */
	private void buildT_D_ER_DSP_VALUE() throws Exception {
		tableBuilder.createTable("T_D_ER_DSP_VALUE","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "组合代码或者群组代码")
				.addColumn("C_DV_PARAM_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "应用范围")
				.addColumn("C_DSP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "参数代码")
				.addColumn("C_DV_PARAMS_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 500, false, "''", "参数值")
				.addColumn("C_PORT_CLS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "分级组合代码")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY-MM-DD')", "开始时间")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY-MM-DD')", "结束时间")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_DSP_VALUE", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_DSP_VALUE", "C_PORT_CODE,C_DSP_CODE,C_PORT_CLS_CODE")
				.build(UpdateType.REQUEST, "48560", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	/**
	 * STORY69394电子对账报表配置模块新增电子对账报表类型：净资产变动表
	 */
	private void buildT_D_ER_JZCBD() throws Exception {
		tableBuilder.createTable("T_D_ER_JZCBD","净资产变动表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
		.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "'1701' ", "文件类型")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
		.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
		.addColumn("D_START_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "开始日期")
		.addColumn("D_END_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "结束日期")
		.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
		.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标代码")
		.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "指标名称")
		.addColumn("N_CUR_VALUE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本期数")
		.addColumn("N_TOL_VALUE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本年数")
		.addPrimaryConstraint("PK_D_ER_JZCBD", "C_IDEN")
		.createIndex("IDX_D_ER_JZCBD", "C_SN,C_ASS_CODE,C_FILE_TYPE,D_START_DATE")
		.build(UpdateType.REQUEST, "69394", "新建", "liwenzhou@ysstech.com", "2019-04-22");
	}
	private void buildT_D_ER_DBLGZ() throws Exception {
		tableBuilder.createTable("T_D_ER_DBLGZ","估值表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, true, "", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目代码")
				.addColumn("C_KM_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "上级科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目名称")
				.addColumn("N_VA_PRICE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "行情价格")
				.addColumn("N_QUOT_LOGO", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "行情标志")
				.addColumn("N_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "证券数量")
				.addColumn("N_ORIG_COST", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "原币证券成本")
				.addColumn("N_PORT_COST", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本币证券成本")
				.addColumn("N_ORIG_MV", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "原币证券市值")
				.addColumn("N_PORT_MV", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本币证券市值")
				.addColumn("N_ORIG_IV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "原币估值增值")
				.addColumn("N_PORT_IV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本币估值增值")
				.addColumn("N_CB_JZ_BL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "成本占净值比")
				.addColumn("N_SZ_JZ_BL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "市值占净值比")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "是否明细科目")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'FORWARD' ", "对账方向")
				.addColumn("C_NAV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "数据类型")
				.addColumn("C_PORT_CLS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "分级组合代码")
				.addColumn("C_ZB_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "汇总项指标名称")
				.addColumn("N_WAY", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "科目方向")
				.createIndex("IDX_D_ER_DBLGZ", "C_SN,C_ASS_CODE,D_END_DATE,C_KM_CODE")
				.build(UpdateType.REQUEST, "74798", "新建", "liwenzhou@ysstech.com", "2019-06-23");
	}
	/**
	 * STORY61216【景顺长城基金】发送电子对账报文明文和密文都要存数据库
	 * 新增电子对账定时调度任务
	 * @throws Exception 
	 */
	private void buildT_D_ER_RPT_LOG() throws Exception {
		tableBuilder.createTable("T_D_ER_RPT_LOG","报文日志表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "id")
		.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "报文序号")
		.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 6, true, "''", "文件类型")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "资产代码")
		.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, true, "''", "报表类型")
		.addColumn("C_BEGIN_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "开始日期yyyyMMdd")
		.addColumn("C_SRC_USERID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "源用户")
		.addColumn("C_SRC_APPID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "源应用")
		.addColumn("C_PKG_PSD", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "发送密码")
		.addColumn("C_DEPT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "公司代码")
		.addColumn("C_CERT_ID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "证书ID")
		.addColumn("C_SECRETKEY", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "密钥")
		.addColumn("C_DV_SECRETTYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "加密方式ELEC_SECRETTYPE")
		.addColumn("C_DV_CHARSET", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "编码CHR_CODE")
		.addColumn("C_TARGET_USERID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "目标用户")
		.addColumn("C_TARGET_APPID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "目标应用")
		.addColumn("C_DV_LOG_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "日志类型ER_LOG_TYPE")
		.addColumn("C_ENCRYPT_LOG", OraColumnTypeNoLength.CLOB, true, "''", "加密日志")
		.addColumn("C_DECRYPT_LOG", OraColumnTypeNoLength.CLOB, true, "''", "解密日志")
		.addColumn("D_HANDLE_TIME", OraColumnTypeNoLength.DATE, true, "", "更新时间")
		.addColumn("C_ERR_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "''", "错误信息")
		.addColumn("C_PKG_ID", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "深证通数据包ID")
		.addPrimaryConstraint("PK_D_ER_RPT_LOG", "C_IDEN")
		.createIndex("IDX_D_ER_RPT_LOG", "C_SN,C_ASS_CODE,C_SRC_USERID,C_SRC_APPID,C_TARGET_USERID,C_TARGET_APPID")
		.build(UpdateType.REQUEST, "61216", "新建", "liwenzhou@ysstech.com", "2019-01-10");
	}
	/**
	 * STORY #63787优化需求57549深证通链接检测中的任务配置功能
	 * 新增电子对账定时调度任务
	 * @throws Exception 
	 */
	private void buildT_D_ER_TASK() throws Exception {
		tableBuilder.createTable("T_D_ER_TASK","电子对账定时任务").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "id")
		.addColumn("C_DV_TASK_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "任务代码")
		.addColumn("N_RUN_INTERVAL", OraColumnTypeNeedPrecision.NUMBER, 20, 0, false, "1", "运行间隔(分钟)")
		.addColumn("C_CALL_USER", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "''", "提醒用户")
		.addColumn("N_RUN_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "当前执行状态(1-正常，0-异常)")
		.addColumn("C_RUN_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "''", "当前执行详细信息")
		.addColumn("N_RUN_RESULT", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "当前执行结果（0-失败，1-成功）")
		.addColumn("C_RESULT_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "''", "执行结果详细信息")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改时间")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改人")
		.addPrimaryConstraint("PK_D_ER_TASK", "C_IDEN")
		.createUniqueIndex("IDX_D_ER_TASK", "C_DV_TASK_CODE")
		.build(UpdateType.REQUEST, "63787", "新建", "liwenzhou@ysstech.com", "2018-12-22");
	}
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	private void buildT_D_ER_SPLIT_RELA() throws Exception {
		tableBuilder.createTable("T_D_ER_SPLIT_RELA","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "id")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "投资组合")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "''", "托管银行")
				.addColumn("C_SPLIT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "拆分代码")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')","结束日期")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查时间")
				.addPrimaryConstraint("PK_D_ER_SPLIT_RELA", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_SPLIT_RELA", "C_PORT_CODE,C_TGH_CODE,C_SPLIT_CODE")
				.build(UpdateType.REQUEST, "57828", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
	private void buildT_D_ER_SPLIT_RULE() throws Exception {
		tableBuilder.createTable("T_D_ER_SPLIT_RULE","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "id")
				.addColumn("C_IDEN_RELA", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "关联的拆分映射关系")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "''", "科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "''", "科目名称")
				//默认已审核
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "修改时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "检查时间")
				.addPrimaryConstraint("PK_D_ER_SPLIT_RULE", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_SPLIT_RULE", "C_IDEN_RELA,C_KM_CODE")
				.build(UpdateType.REQUEST, "57828", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	private void buildT_D_ER_AUTOSTATE() throws Exception {
		tableBuilder.createTable("T_D_ER_AUTOSTATE","自动流程发送状态表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
		.addColumn("c_process_id", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "流程ID")
		.addColumn("c_task_id", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "任务ID")
		.addColumn("c_port_code", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "组合代码")
		.addColumn("c_file_type", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "文件类型")
		.addColumn("c_sn", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "报文序号")
		.addColumn("c_state", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "发送状态")
		.addPrimaryConstraint("PK_D_ER_AUTOSTATE", "C_IDEN")
		.build(UpdateType.REQUEST, "54581", "新建", "wulongxing@ysstech.com", "2018-04-17");

	}
	
	private void buildT_D_ER_RESVIEW() throws Exception {
		tableBuilder.createTable("T_D_ER_RESVIEW","主要指标方案表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
		.addColumn("C_PLAN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "方案代码")
		.addColumn("C_PLAN_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "方案名称")
		.addColumn("C_ITEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "主要指标代码")
		.addColumn("C_PLAN_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "方案类别")
		.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "说明")
		.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "修改人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "修改时间")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
		.addPrimaryConstraint("PK_T_D_ER_RESVIEW", "C_IDEN")
		.build(UpdateType.REQUEST, "41248", "新建", "liwenzhou@ysstech.com", "2018-04-17");

	}
	private void buildT_D_ER_REVE_RESRELA() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_RESRELA","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_RESULT_RELA", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "关联对账结果主键")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目名称")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "托管行")
				.addColumn("C_DV_KM_SCOPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "科目范围")
				.addColumn("N_JE14", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额14")
				.addColumn("N_JE13", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额13")
				.addColumn("N_JE12", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额12")
				.addColumn("N_JE11", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额11")
				.addColumn("N_JE10", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额10")
				.addColumn("N_JE9", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额9")
				.addColumn("N_JE8", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额8")
				.addColumn("N_JE7", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额7")
				.addColumn("N_JE6", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额6")
				.addColumn("N_SL1", OraColumnTypeNeedPrecision.NUMBER, 20, 2, true, "", "数量1")
				.addColumn("N_JE3", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额3")
				.addColumn("N_JE4", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额4")
				.addColumn("N_JE5", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额5")
				.addColumn("C_BY1", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "备用1")
				.addColumn("N_JE2", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额2")
				.addColumn("N_JE1", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额1")
				.addColumn("C_IGNORE_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "''", "忽略标记")
				.addPrimaryConstraint("PK_D_ER_REVE_RESRELA", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_RESRELA", "C_RESULT_RELA")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_KM_MAP() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_KM_MAP","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_IDEN_RELA", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "映射关系ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "产品组合")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "''", "科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "科目名称")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "托管机构")
				.addColumn("C_DV_KM_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)")
				.addColumn("C_DV_KM_SCOPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "科目范围（REVE_KMFW_INNER:内部科目；REVE_KMFW_OUT：外部科目）")
				.addPrimaryConstraint("PK_D_ER_REVE_KM_MAP", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_KM_MAP", "C_IDEN_RELA,C_KM_CODE,C_KM_NAME")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_KM_RELA() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_KM_RELA","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "产品组合")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "托管机构")
				.addColumn("C_DV_MAP_SCOPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "映射范围（REVE_YSFW_GGYS:公共映射，REVE_YSFW_TGFYS:托管方映射,REVE_YSFW_CPYS:产品映射）")
				.addColumn("C_DV_KM_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "科目类型(KC_ZC:资产类;KC_FZ:负债类;KC_GT:共同类;KC_QY:权益类;KC_SY:损益类)")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_REVE_KM_RELA", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_KM_RELA", "C_PORT_CODE,C_TGH_CODE,C_DV_KM_CLS,C_DV_MAP_SCOPE")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_ASS_MAP() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_ASS_MAP","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "投资组合代码")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "对账类型")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "''", "托管银行")
				.addColumn("C_PORT_CODE_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "''", "托管行资产代码")
				.addColumn("C_DV_DZ_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账模式（DZMODE_SZT：深圳通模式；DZMODE_QT：其他模式）")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_REVE_ASS_MAP", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_REVE_ASS_MAP", "C_PORT_CODE,C_PORT_CODE_OUT,C_TGH_CODE,C_FILE_TYPE,C_DV_DZ_MODE")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_ZB_MAP() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_ZB_MAP","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账类型")
				.addColumn("C_ZB_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "''", "内部指标代码")
				.addColumn("C_ZB_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "内部指标名称")
				.addColumn("C_ZB_CODE_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "''", "外部指标代码")
				.addColumn("C_ZB_NAME_OUT", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "外部指标名称")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "产品组合")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "托管机构")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_REVE_ZB_MAP", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_REVE_ZB_MAP", "C_PORT_CODE,C_TGH_CODE,C_FILE_TYPE,C_ZB_CODE,C_ZB_CODE_OUT")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_INFO() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_INFO","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "对账编号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账方向")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "投资组合")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账类型")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "报表类型")
				.addColumn("N_FK_STATE", OraColumnTypeNeedPrecision.NUMBER, 4, 0, false, "", "反馈给托管行的次数")
				.addColumn("C_DV_HANDLE_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "处理状态(REVE_HS_SUCCESS：对账成功;REVE_HS_FAIL：对账失败)")
				.addColumn("C_HANDLE_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "''", "处理详情")
				.addColumn("C_DV_LOCK_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "锁定状态（SDZT_NO不锁定，SDZT_YES锁定）")
				.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "对账日期")
				.addColumn("C_DV_DZ_RESULT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "对账结果(DZ_RESULT_SAME：对账一致;DZ_RESULT_DIFF：对账不一致)")
				.addColumn("C_REMARK", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "''", "备注")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_REVE_INFO", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_INFO", "N_FK_STATE,C_DV_LOCK_STATE,C_DV_DZ_RESULT")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildT_D_ER_REVE_RESULT() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_RESULT","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "''", "对账类型")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 12, false, "''", "组合代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "''", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, true, "", "报表开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, true, "", "报表结束日期")
				.addColumn("C_DV_DZ_RESULT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账结果")
				.addColumn("C_RESULT_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 4000, false, "''", "对账详情")
				.addColumn("C_NOTE", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "''", "备注")
				.addColumn("C_DEALER", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "''", "处理人")
				.addColumn("D_TIME", OraColumnTypeNoLength.DATE, true, "", "处理时间")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "报文序号")
				//STORY #58653 阳光资产--反向对账业务
				.addColumn("N_CE_JE14", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额14")
				.addColumn("N_CE_JE13", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额13")
				.addColumn("N_CE_JE12", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额12")
				.addColumn("N_CE_JE11", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额11")
				.addColumn("N_CE_JE10", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额10")
				.addColumn("N_CE_JE9", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额9")
				.addColumn("N_CE_JE8", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额8")
				.addColumn("N_CE_JE7", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额7")
				.addColumn("N_CE_JE6", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额6")
				.addColumn("N_CE_SL1", OraColumnTypeNeedPrecision.NUMBER, 20, 2, true, "", "数量1")
				.addColumn("N_CE_JE3", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额3")
				.addColumn("N_CE_JE4", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额4")
				.addColumn("N_CE_JE5", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额5")
				.addColumn("C_CE_BY1", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "备用1")
				.addColumn("N_CE_JE2", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额2")
				.addColumn("N_CE_JE1", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "金额1")
				.addPrimaryConstraint("PK_D_ER_REVE_RESULT", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_RESULT", "C_SN")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}

	private void buildT_D_ER_REVE_IGNORE() throws Exception {
		tableBuilder.createTable("T_D_ER_REVE_IGNORE","").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "''", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "对账类型")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "产品组合")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "托管机构")
				.addColumn("C_DV_IGNORE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "忽略类型(HL_ROW:行忽略;HL_COL:列忽略;HL_CELL:单元格忽略)")
				.addColumn("C_DV_IGNORE_SCOPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "忽略方向(IGNORE_SCOPE_INNER：本方;IGNORE_SCOPE_OUT:对方)")
				.addColumn("C_DV_SUB_SUIT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "''", "应用下级科目(SUB_SUIT_YES:是；SUB_SUIT_NO:否)")
				.addColumn("C_COL_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "列标识")
				.addColumn("C_ROW_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "''", "行标识")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "审核时间")
				.addPrimaryConstraint("PK_D_ER_REVE_IGNORE", "C_IDEN")
				.createIndex("IDX_D_ER_REVE_IGNORE", "C_PORT_CODE,C_TGH_CODE,C_FILE_TYPE")
				.build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	
	private void buildR_D_ER_TEMP_PORT() throws Exception {
		tableBuilder.createTempTable("R_D_ER_TEMP_PORT","电子对账管理临时表").onCommitDeleteRows().setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("c_port_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "组合代码")
		.addColumn("n_order", OraColumnTypeNeedPrecision.NUMBER, 5, 0, true, "", "顺序")
		.build(UpdateType.REQUEST, "52002", "电子对账管理界面展示优化", "wulongxing@ysstech.com", "2018-02-09");
	}
	private void buildT_Z_BI_PER_RELA() throws Exception{
		tableBuilder.createTable("T_Z_BI_PER_RELA","对账指标个性化关联表").setModuleCode(ElecModuleInfo.MODULE_PARAM)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
		.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30,true, "", "组合代码")
		.addColumn("C_ZB_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
		.addColumn("C_ZB_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "指标名称")
		.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "对账类型")
		.addColumn("C_SEND_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "发送模式")
		.addColumn("C_ZB_ELEM1", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素1")
		.addColumn("C_ZB_VALUE1", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值1")
		.addColumn("C_ZB_ELEM2", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素2")
		.addColumn("C_ZB_VALUE2", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值2")
		.addColumn("C_ZB_ELEM3", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素3")
		.addColumn("C_ZB_VALUE3", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值3")
		.addColumn("C_ZB_ELEM4", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素4")
		.addColumn("C_ZB_VALUE4", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值4")
		.addColumn("C_ZB_ELEM5", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素5")
		.addColumn("C_ZB_VALUE5", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值5")
		.addColumn("C_ZB_ELEM6", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素6")
		.addColumn("C_ZB_VALUE6", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值6")
		.addColumn("C_ZB_ELEM7", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素7")
		.addColumn("C_ZB_VALUE7", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值7")
		.addColumn("C_ZB_ELEM8", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素8")
		.addColumn("C_ZB_VALUE8", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值8")
		.addColumn("C_ZB_ELEM9", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素9")
		.addColumn("C_ZB_VALUE9", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标元素值9")
		.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
		.addPrimaryConstraint("PK_Z_BI_PER_RELA", "C_IDEN")
		.createUniqueIndex("IDX_Z_BI_PER_RELA", "C_PORT_CODE, C_ZB_CODE, C_DZ_CODE")
		.build(UpdateType.REQUEST, "41535", "新建", "songdabang@ysstech.com", "2017-02-12");
	}

	private void buildT_C_BW_MRFORWARD() throws Exception {
		tableBuilder.createTable("T_C_BW_MRFORWARD","2.5报文中间表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
		.addColumn("FID", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "自动id")
		.addColumn("FDATE", OraColumnTypeNoLength.DATE, true, "", "报文接收日期")
		.addColumn("FTIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "报文接收时间")
		.addColumn("FMBYH", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "目标银行")
		.addColumn("FMBYHBZ", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "目标银行标识")
		.addColumn("FSRCAPPID", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "源应用标识")
		.addColumn("FYSBW", OraColumnTypeNoLength.CLOB, true, "", "报文")
		.addColumn("FINBZ", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "写入标识")
		.addColumn("FOUTBZ", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "读取标识")
		.addColumn("SAVE_FLAG", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "保存标识")
		.addPrimaryConstraint("PK_C_BW_MRFORWARD", "fid")
		.createIndex("IDX_C_BW_MRFORWARD0", "FMBYH,FMBYHBZ,FSRCAPPID,FOUTBZ")
		.build(UpdateType.REQUEST, "32891", "支付平台接收MR返回报文，反写给2.5", "wulongxing@ysstech.com", "2017-05-05");

	}

	private void buildT_D_ER_INFO() throws Exception {
		tableBuilder.createTable("T_D_ER_INFO","对账生成表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "对账方向")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'' ", "资产代码")
				//STORY73476【鹏华基金】并行组合电子对账需求
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'' ", "组合代码")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "文件类型")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "报表类型")
				.addColumn("C_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "发送状态")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "对账日期")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_ERR_INFO", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "", "提示信息")
				//STORY57828需求北京-【新华资产】ams估值核算【高】（关于新华资产系统需支持资管拆分估值表发送实现电子对账的需求）
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "托管行代码")
				.addColumn("C_SPLIT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "拆分代码")
				.addColumn("C_DV_RESULT", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "原因")
				.addColumn("C_SUMMARY", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "", "说明")
				.addColumn("C_CONFIRM_EXECUTE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "锁定状态")
				.addColumn("C_DEALER", OraColunmnTypeOnlyLength.VARCHAR2, 60, true, "", "处理人")
				.addPrimaryConstraint("PK_D_ER_INFO", "C_IDEN")
				.createIndex("IDX_D_ER_INFO", "C_SN")//不能建唯一性索引，历史数据中存在一样的值创建不了
				.createIndex("IDX_D_ER_INFO1", "D_DATE,C_ASS_CODE,C_FILE_TYPE")//BUG234867【汇添富基金】电子对账管理后台查询到频繁执行的sql
				.createIndex("IDX_D_ER_INFO2", "D_DATE,C_PORT_CODE,C_FILE_TYPE")//STORY73476【鹏华基金】并行组合电子对账需求
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	private void buildT_D_ER_STEP_STATE() throws Exception {
		tableBuilder.createTable("T_D_ER_STEP_STATE","对账生成历史状态表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "文件类型")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "报表类型")
				.addColumn("C_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "发送状态")
				.addColumn("C_STEP_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "历史状态日期")
				.addColumn("C_ERR", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "错误信息")
				.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "对账日期")
				.addPrimaryConstraint("PK_D_ER_STEP_STATE", "C_IDEN")
				.build(UpdateType.REQUEST, "50374 ", "新建", "songdabang@ysstech.com", "2018-01-17");
	}
	
	private void buildT_D_ER_UN_PORT() throws Exception {
		tableBuilder.createTable("T_D_ER_UN_PORT","不对账组合表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "组合代码")
				.addColumn("C_OPER_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "操作人")
				.addColumn("C_OPER_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "操作时间")
				.addPrimaryConstraint("PK_D_ER_UN_PORT", "C_IDEN")
				//BUG244963电子对账管理界面总览分页勾选数据点击其他对账可以保存多条其他对账数据
				.createUniqueIndex("IDX_D_ER_UN_PORT", "C_PORT_CODE")
				.build(UpdateType.REQUEST, "50374 ", "新建", "ouyangkang@ysstech.com", "2018-03-26");
	}
	
	private void buildT_D_ER_KM() throws Exception {
		tableBuilder.createPartitionTable("T_D_ER_KM","科目表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "对账方向")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'' ", "资产代码")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "文件类型")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","结束日期")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "科目名称")
				.addColumn("C_KM_LEVEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "科目级别")
				.addColumn("C_KM_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "上级科目代码")
				.addColumn("C_DV_JD_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "余额方向")
				.addColumn("C_DV_KM_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "科目类别")
				.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "日期")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "是否明细科目")
				//STORY #91504 【富国基金】巡检时发现几张大数据量的表未进行表分区需要优化
				.partitionBy(OraPartitionType.RANGE, "D_START_DATE")
				.addPartintion("PART_2010", "TO_DATE(' 2011-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2011", "TO_DATE(' 2012-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2012", "TO_DATE(' 2013-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2013", "TO_DATE(' 2014-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2014", "TO_DATE(' 2015-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2015", "TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2016", "TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2017", "TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2018", "TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2019", "TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2020", "TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2021", "TO_DATE(' 2022-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2022", "TO_DATE(' 2023-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2023", "TO_DATE(' 2024-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2024", "TO_DATE(' 2025-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2025", "TO_DATE(' 2026-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2026", "TO_DATE(' 2027-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2027", "TO_DATE(' 2028-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2028", "TO_DATE(' 2029-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2029", "TO_DATE(' 2030-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2030", "TO_DATE(' 2031-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2031", "TO_DATE(' 2032-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2032", "TO_DATE(' 2033-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2033", "TO_DATE(' 2034-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2034", "TO_DATE(' 2035-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2035", "TO_DATE(' 2036-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2036", "TO_DATE(' 2037-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2037", "TO_DATE(' 2038-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2038", "TO_DATE(' 2039-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2039", "TO_DATE(' 2040-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2040", "TO_DATE(' 2041-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2041", "TO_DATE(' 2042-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
                .addPartintion("PART_MAX", "MAXVALUE")
				.addPrimaryConstraint("PK_D_ER_KM", "C_IDEN")
				.createIndex("IDX_D_ER_KM", "C_SN,C_ASS_CODE,C_FILE_TYPE").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	//STORY59971【景顺长城基金】【道富QD】【紧急】估值表核对增加原币和指标的核对
	//估值表增加：原币成本、原币市值、原币估值增值
	private void buildT_D_ER_GZ() throws Exception {
		tableBuilder.createPartitionTable("T_D_ER_GZ","估值表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, true, "", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目代码")
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "科目名称")
				.addColumn("N_VA_PRICE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "行情价格")
				.addColumn("N_QUOT_LOGO", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "行情标志")
				.addColumn("N_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "证券数量")
				.addColumn("N_ORIG_COST", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "原币证券成本")
				.addColumn("N_PORT_COST", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本币证券成本")
				.addColumn("N_ORIG_MV", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "原币证券市值")
				.addColumn("N_PORT_MV", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "本币证券市值")
				.addColumn("N_ORIG_IV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "原币估值增值")
				.addColumn("N_PORT_IV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本币估值增值")
				.addColumn("N_CB_JZ_BL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "成本占净值比")
				.addColumn("N_SZ_JZ_BL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "市值占净值比")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 2, 0, true, "", "是否明细科目")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'FORWARD' ", "对账方向")
				.addColumn("C_NAV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "数据类型")
				.addColumn("C_PORT_CLS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合代码")
				//BUG289235【鹏华基金】产生电子对账的分级基金，A组合的电子对账产生的电子对账指标是其他组合的
				.addColumn("C_ZB_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "指标名称")
				//STORY #91504 【富国基金】巡检时发现几张大数据量的表未进行表分区需要优化
				.partitionBy(OraPartitionType.RANGE, "D_START_DATE")
				.addPartintion("PART_2010", "TO_DATE(' 2011-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2011", "TO_DATE(' 2012-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2012", "TO_DATE(' 2013-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2013", "TO_DATE(' 2014-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2014", "TO_DATE(' 2015-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2015", "TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2016", "TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2017", "TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2018", "TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2019", "TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2020", "TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2021", "TO_DATE(' 2022-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2022", "TO_DATE(' 2023-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2023", "TO_DATE(' 2024-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2024", "TO_DATE(' 2025-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2025", "TO_DATE(' 2026-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2026", "TO_DATE(' 2027-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2027", "TO_DATE(' 2028-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2028", "TO_DATE(' 2029-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2029", "TO_DATE(' 2030-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2030", "TO_DATE(' 2031-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2031", "TO_DATE(' 2032-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2032", "TO_DATE(' 2033-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2033", "TO_DATE(' 2034-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2034", "TO_DATE(' 2035-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2035", "TO_DATE(' 2036-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2036", "TO_DATE(' 2037-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2037", "TO_DATE(' 2038-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2038", "TO_DATE(' 2039-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2039", "TO_DATE(' 2040-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2040", "TO_DATE(' 2041-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2041", "TO_DATE(' 2042-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
                .addPartintion("PART_MAX", "MAXVALUE")
				.addPrimaryConstraint("PK_D_ER_GZ", "C_IDEN")
//				.createIndex("IDX_D_ER_GZ", "C_SN")
				.createIndex("IDX_D_ER_GZ0", "C_SN,C_ASS_CODE,D_END_DATE,C_KM_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_YE() throws Exception {
		tableBuilder.createPartitionTable("T_D_ER_YE","余额表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "' '", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, false, "' '", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "科目代码")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 3, false, "' '", "币种代码")
				.addColumn("N_ORIG_STARTBAL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "原币期初余额")
				.addColumn("N_ORIG_DEBIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "原币借方发生额")
				.addColumn("N_ORIG_CREDIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "原币贷方发生额")
				.addColumn("N_ORIG_ENDBAL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "原币期末余额")
				.addColumn("N_PORT_STARTBAL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "本币期初余额")
				.addColumn("N_PORT_DEBIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "本币借方发生额")
				.addColumn("N_PORT_CREDIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "本币贷方发生额")
				.addColumn("N_PORT_ENDBAL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "本币期末余额")
				.addColumn("N_AMOUNT_STARTBAL", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "期初余额数量")
				.addColumn("N_AMOUNT_DEBIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "借方发生额数量")
				.addColumn("N_AMOUNT_CREDIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0", "贷方发生额数量")
				.addColumn("N_AMOUNT_ENDBAL", OraColumnTypeNeedPrecision.NUMBER, 21, 7, false, "0", "期末余额数量")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 2, 0, false, "0", "是否明细科目")
				.addColumn("N_J_TOLTAL_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0 ", "借方累计发生额")
				.addColumn("N_D_TOLTAL_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, false, "0 ", "贷方累计发生额")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "报文序号")
				.addColumn("C_DV_ER_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'FORWARD' ", "对账方向")
				//STORY55542汇添富项目，电子对账余额表（银行间代码转换规则）明细科目，科目名称没有显示出来。
				.addColumn("C_KM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "科目名称")
				.partitionBy(OraPartitionType.RANGE, "D_START_DATE")
				.addPartintion("PART_2010", "TO_DATE(' 2011-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2011", "TO_DATE(' 2012-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2012", "TO_DATE(' 2013-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2013", "TO_DATE(' 2014-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2014", "TO_DATE(' 2015-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2015", "TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2016", "TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2017", "TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2018", "TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2019", "TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2020", "TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2021", "TO_DATE(' 2022-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2022", "TO_DATE(' 2023-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2023", "TO_DATE(' 2024-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2024", "TO_DATE(' 2025-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2025", "TO_DATE(' 2026-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2026", "TO_DATE(' 2027-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2027", "TO_DATE(' 2028-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2028", "TO_DATE(' 2029-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2029", "TO_DATE(' 2030-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2030", "TO_DATE(' 2031-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2031", "TO_DATE(' 2032-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2032", "TO_DATE(' 2033-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2033", "TO_DATE(' 2034-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2034", "TO_DATE(' 2035-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2035", "TO_DATE(' 2036-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2036", "TO_DATE(' 2037-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2037", "TO_DATE(' 2038-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2038", "TO_DATE(' 2039-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2039", "TO_DATE(' 2040-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2040", "TO_DATE(' 2041-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2041", "TO_DATE(' 2042-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
                .addPartintion("PART_MAX", "MAXVALUE")
				.addPrimaryConstraint("PK_D_ER_YE", "C_IDEN")
				.createIndex("IDX_D_ER_YE", "C_SN,C_ASS_CODE,C_FILE_TYPE")
				.createIndex("IDX_D_ER_YE1", "C_SN,C_KM_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_ZCFZ() throws Exception {
		tableBuilder.createTable("T_D_ER_ZCFZ","资产负债表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "'1701' ", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "结束日期")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "报文序号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标代码")
				//BUG262135对账指标关联界面同一资本代码设置多个指标名称时生成电子对账数据指标名称取值错误
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "指标名称")
				.addColumn("N_BEGIN_VALUE", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "期初值")
				.addColumn("N_END_VALUE", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "期末值")
				.addPrimaryConstraint("PK_D_ER_ZCFZ", "C_IDEN")
				.createIndex("IDX_D_ER_ZCFZ", "C_SN")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_SYZQYBD() throws Exception {
		tableBuilder.createTable("T_D_ER_SYZQYBD","所有者权益变动表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "'1901' ", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "结束日期")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "报文序号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
				//BUG262135对账指标关联界面同一资本代码设置多个指标名称时生成电子对账数据指标名称取值错误
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "指标名称")
				.addColumn("N_THIS_NAV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本期实收基金")
				.addColumn("N_THIS_UNPROFIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本期未分配利润")
				.addColumn("N_THIS_INTERESTS", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本期所有者权益合计")
				.addColumn("N_LAST_NAV", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "上期实收基金")
				.addColumn("N_LAST_UNPROFIT", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "上期未分配利润")
				.addColumn("N_LAST_INTERESTS", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "上期所有者权益")
				.addPrimaryConstraint("PK_D_ER_SYZQYBD", "C_IDEN")
				.createIndex("IDX_D_ER_SYZQYBD", "C_SN")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_LR() throws Exception {
		tableBuilder.createTable("T_D_ER_LR","利润表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "'1801' ", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 8, true, "", "结束日期")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "报文序号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
				//BUG262135对账指标关联界面同一资本代码设置多个指标名称时生成电子对账数据指标名称取值错误
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "指标名称")
				.addColumn("N_CUR_VALUE", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本期数")
				.addColumn("N_TOL_VALUE", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "", "本年数")
				.addPrimaryConstraint("PK_D_ER_LR", "C_IDEN")
				.createIndex("IDX_D_ER_LR", "C_SN")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	private void buildT_D_ER_RESULT() throws Exception {
		tableBuilder.createPartitionTable("T_D_ER_RESULT","对账结果表").setModuleCode(ElecModuleInfo.MODULE_MANAGE)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_FILE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 4, false, "' '", "文件类型")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "报表类型")
				.addColumn("D_START_DATE", OraColumnTypeNoLength.DATE, true, "", "开始日期")
				.addColumn("D_END_DATE", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_RESULT", OraColunmnTypeOnlyLength.VARCHAR2, 4000, false, "' ' ", "对账结果")
				.addColumn("C_REF_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "关联流水号")
				.addColumn("C_NOTE", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "", "附言")
				.addColumn("C_DEALER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "处理人")
				.addColumn("D_TIME", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "处理时间")
				.addColumn("C_SN", OraColunmnTypeOnlyLength.NVARCHAR2, 60, false, "' '", "报文序号")
				//BUG #278650 【海通回归测试】数据库自动升级时后台日志报错
				.addColumn("C_B_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "", "本方科目代码")
				.addColumn("C_D_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "", "对方科目代码")
				.addColumn("N_B_JE", OraColumnTypeNeedPrecision.NUMBER, 20, 8, true, "0", "本方金额")
				.addColumn("N_D_JE", OraColumnTypeNeedPrecision.NUMBER, 20, 8, true, "0", "对方金额")
				.addColumn("N_B_SL", OraColumnTypeNeedPrecision.NUMBER, 20, 2, true, "0", "本方数量")
				.addColumn("N_D_SL", OraColumnTypeNeedPrecision.NUMBER, 20, 2, true, "0", "对方数量")
				.addColumn("N_B_JE3", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "本方金额3")
				.addColumn("N_D_JE3", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "对方金额3")
				.addColumn("N_B_JE4", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "本方金额4")
				.addColumn("N_D_JE4", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "对方金额4")
				.addColumn("N_B_JE5", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "本方金额5")
				.addColumn("N_D_JE5", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "对方金额5")
				.addColumn("N_JS_TIME", OraColunmnTypeOnlyLength.NVARCHAR2, 100, true, "", "接收时间")
				.addColumn("C_B_BY1", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '", "本方备用1")
				.addColumn("C_D_BY1", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '", "对方备用1")
				.addColumn("N_B_JET", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "本方金额2")
				.addColumn("N_D_JET", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "对方金额2")
				.addColumn("N_B_JEO", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "本方金额1")
				.addColumn("N_D_JEO", OraColumnTypeNeedPrecision.NUMBER, 20, 6, true, "0", "对方金额1")
				.addColumn("C_CHECK_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "对账类型")
				.partitionBy(OraPartitionType.RANGE, "D_START_DATE")
				.addPartintion("PART_2010", "TO_DATE(' 2011-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2011", "TO_DATE(' 2012-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2012", "TO_DATE(' 2013-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2013", "TO_DATE(' 2014-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2014", "TO_DATE(' 2015-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2015", "TO_DATE(' 2016-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2016", "TO_DATE(' 2017-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2017", "TO_DATE(' 2018-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2018", "TO_DATE(' 2019-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2019", "TO_DATE(' 2020-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2020", "TO_DATE(' 2021-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2021", "TO_DATE(' 2022-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2022", "TO_DATE(' 2023-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2023", "TO_DATE(' 2024-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2024", "TO_DATE(' 2025-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2025", "TO_DATE(' 2026-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2026", "TO_DATE(' 2027-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2027", "TO_DATE(' 2028-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2028", "TO_DATE(' 2029-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2029", "TO_DATE(' 2030-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2030", "TO_DATE(' 2031-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2031", "TO_DATE(' 2032-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2032", "TO_DATE(' 2033-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2033", "TO_DATE(' 2034-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2034", "TO_DATE(' 2035-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2035", "TO_DATE(' 2036-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2036", "TO_DATE(' 2037-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2037", "TO_DATE(' 2038-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2038", "TO_DATE(' 2039-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2039", "TO_DATE(' 2040-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2040", "TO_DATE(' 2041-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
				.addPartintion("PART_2041", "TO_DATE(' 2042-01-01 00:00:00', 'SYYYY-MM-DD HH24:MI:SS', 'NLS_CALENDAR=GREGORIAN')")
                .addPartintion("PART_MAX", "MAXVALUE")
				.addPrimaryConstraint("PK_D_ER_RESULT", "C_IDEN")
				.createIndex("IDX_D_ER_RESULT", "C_SN, C_ASS_CODE, C_CHECK_FLAG")
				.createIndex("IDX_D_ER_RESULT1", "C_REF_NO, C_ASS_CODE, C_CHECK_FLAG")
				.createIndex("IDX_D_ER_RESULT2", "D_START_DATE, C_ASS_CODE")
				.createIndex("IDX_D_ER_RESULT3", "D_TIME, C_ASS_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_TMPL() throws Exception {
		tableBuilder.createTable("T_D_ER_TMPL","电子对账部署模板").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_TMPL_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "模板代码")
				.addColumn("C_TMPL_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "模板名称")
				.addColumn("C_TMPL_PATH", OraColunmnTypeOnlyLength.VARCHAR2, 500, false, "' '", "模板路径")
				.addColumn("C_VERSION", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "版本号")
				.addColumn("C_DV_TMPL_STATUS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "模板状态")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建时间")
				.addColumn("C_TMPL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "模板类型")
				.addPrimaryConstraint("PK_D_ER_TMPL", "C_IDEN").createUniqueIndex("IDX_D_ER_TMPL", "C_TMPL_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	private void buildT_D_ER_TMPL_RELA() throws Exception {
		tableBuilder.createTable("T_D_ER_TMPL_RELA","电子对账模板关联").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_TMPL_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "模板代码")
				.addColumn("C_TMPL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "模板类型")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_D_ER_TMPL_RELA", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_TMPL_RELA", "C_PORT_CODE,C_TMPL_TYPE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_D_ER_RELA() throws Exception {
		tableBuilder.createTable("T_D_ER_RELA","电子对账参数").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "资产代码")
				.addColumn("C_DEPT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "公司ID")
				.addColumn("C_CERT_ID", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "证书ID")
				.addColumn("C_TGH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "机构代码")
				.addColumn("C_TGH_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "机构名称")
				.addColumn("C_BUS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "业务类型")
				.addColumn("C_COMM_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "通讯类型")
				.addColumn("C_TARGET_USER", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "目标用户")
				.addColumn("C_TARGET_USER_LOGO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "源用户")
				.addColumn("C_TARGET_APP_LOGO", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "目标应用标识")
				.addColumn("C_SRC_APP_LOGO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "源应用标识")
				.addColumn("C_PKG_PASSWORD", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '", "发送密码")
				.addColumn("C_DZ_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'DZMS_TYMS' ", "对账模式")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_GZB_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'GZBFSMS_WGZ'", "估值表发送模式")
				.addColumn("C_MR_IP", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'127.0.0.1' ", "伺服器IP")
				.addColumn("C_MR_PORT", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'11911' ", "伺服器端口")
				.addColumn("C_HIGH_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'1' ", "重发次数")
				.addColumn("C_INTERVAL", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'60' ", "间隔时间")
				.addColumn("C_ERPARA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'defalut'", "深证通伺服器")
				.addColumn("c_Has_Branch", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "0", "是否应用分机构")//STORY49489产品关联电子对账设置优化
				//STORY55269【富国基金】支持电子对账参数设置支持多管理人
				//修改【电子对账参数设置】设置界面的保存逻辑，通过“托管机构”“业务类型”“管理人”3个字段确定唯一约束条件。
				.addColumn("C_MANAGE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "管理人机构")
				 // 科目名称长度  STORY #58495 需求单-【海通证券】电子对账的科目名称需要优化 author:cls date:2018-08-14
				.addColumn("C_KM_NAME_LENGTH", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "科目名称长度")
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				.addColumn("C_SECRETKEY", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "管理人秘钥")
				.addColumn("C_DV_SECRETTYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'ELEC_ST_BASE64'", "加密类型")
				.addColumn("C_DV_CHARSET", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'GBK'", "报文编码")
				//STORY #72464 电子对账关于许可控制的功能模块改造 
				.addColumn("C_DV_LICORG", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "机构许可信息")
				//.createUniqueIndex("IDX_D_ER_RELA", "C_TGH_CODE,C_BUS_TYPE").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
				.addPrimaryConstraint("PK_D_ER_RELA", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_RELA", "C_TGH_CODE,C_BUS_TYPE,C_MANAGE_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildT_Z_BI_RELA() throws Exception {
		tableBuilder.createTable("T_Z_BI_RELA","对账指标关联表").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_ZB_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
				.addColumn("C_ZB_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "指标名称")
				.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "对账类型")
				.addColumn("C_PORT_CODE_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "分级组合")
				.addColumn("C_DV_ZB_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标项名称")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "托管行")
				.addColumn("C_DV_PORT_CLS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "分级类型")
				.addColumn("C_DV_PORT_CLS_LEVEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "分级级别")
				//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码   增加字段级别类型
				.addColumn("C_DV_PORT_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "级别类型")
				//STORY53570嘉实基金-电子对账-月报 产品分类配置   新增财务报表字段
				.addColumn("C_REPORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "财务报表")
				.addColumn("C_ROW", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指定行")
				.addPrimaryConstraint("PK_Z_BI_RELA", "C_IDEN").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	private void buildT_P_ER_REPCFG() throws Exception{
		tableBuilder.createTable("T_P_ER_REPCFG","对账报表配置").setModuleCode(ElecModuleInfo.MODULE_PARAM)
			.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
			.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "机构代码")
			.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "电子对账报表")
			.addColumn("C_REPORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "财务报表")
			.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
			//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
			.addColumn("C_DAT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "资产类型")
			//STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
			.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "报表类型")
			.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
			.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
			.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
			.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
			.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
			.addPrimaryConstraint("PK_P_ER_REPCFG", "C_IDEN")
			//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
			.createUniqueIndex("IDX_P_ER_REPCFG", "C_ORG_CODE, C_DZ_CODE, C_PORT_CODE,C_DAT_CODE,C_RPT_TYPE")
			.build(UpdateType.REQUEST, "36615", "南方基金-系统需要支持存在多个财务报表的电子对账 ", "wulongxing@ysstech.com", "2017-05-05");
		
		//STORY #85122 【鹏华基金】社保和养老基金的利润表（年报）电子对账发送 
//		colBuilder.alterTable("T_P_ER_REPCFG")
//		.addColumn("C_RPT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "报表类型")
//		.build(UpdateType.REQUEST, "85122", "【鹏华基金】社保和养老基金的利润表（年报）电子对账发送","tongdengke@ysstech.com","2020-06-11");

	}
	
	private void buildT_P_ER_REPCOLCFG() throws Exception{
		tableBuilder.createTable("T_P_ER_REPCOLCFG","对账报表列个性配置").setModuleCode(ElecModuleInfo.MODULE_PARAM)
			.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
			.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "电子对账报表")
			.addColumn("C_REPORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "财务报表代码")
			.addColumn("C_ELEC_COL", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "电子对账列")
			.addColumn("N_REPORT_COL", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "", "财务报表列")
			.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
			.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
			.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
			.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
			.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
			.addPrimaryConstraint("PK_P_ER_REPCOLCFG", "C_IDEN")
			//STORY60117【鹏华基金】电子对账报表配置增加资产类型控制
			.createUniqueIndex("IDX_P_ER_REPCOLCFG", "C_REPORT_CODE,C_DZ_CODE, C_ELEC_COL")
			.build(UpdateType.REQUEST, "59739", "【易方达基金】【紧急加急】财务报表电子对账生成应该同财务报表配置报错一致 ", "lwz@ysstech.com", "2018-10-18");

	}

	/**
	 * @Description: STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @author wulongxing 
	 * @date 2017年6月21日 下午5:04:10
	 */
	private void buildT_D_ER_PARA() throws Exception {
		tableBuilder.createTable("T_D_ER_PARA","深圳通参数").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_PARA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "参数代码")
				.addColumn("C_PARA_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "参数名称")
				.addColumn("C_SRC_USERID", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "源用户")
				.addColumn("C_SRC_APPID", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "源应用")
				.addColumn("C_PKG_PASSWORD", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "发送密码")
				.addColumn("C_DEPT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "公司代码")
				.addColumn("C_CERT_ID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "证书ID")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "创建时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_D_ER_PARA", "C_IDEN")
				.createUniqueIndex("IDX_D_ER_PARA", "C_PARA_CODE").build(UpdateType.REQUEST, "42660", "【中国银行】深证通伺服器要求采用热备模式", "wulongxing@ysstech.com", "2017-06-12");
	}
	/**
	 * @Description: STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @author wulongxing 
	 * @date 2017年6月21日 下午5:03:57
	 */
	private void buildT_D_ER_MRINFO() throws Exception {
		tableBuilder.createTable("T_D_ER_MRINFO","伺服器参数").setModuleCode(ElecModuleInfo.MODULE_PARAM)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键ID")
				.addColumn("C_PARA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "参数代码")
				.addColumn("C_DV_SWITCH_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "主备标识")
				.addColumn("C_MR_IP", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "伺服器IP")
				.addColumn("C_MR_PORT", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "伺服器端口")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "序号")
				//STORY #63787优化需求57549深证通链接检测中的任务配置功能
				.addColumn("N_LINK_STATUS", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "", "连接状态(0：到深证通不通；1：到伺服器不通 ，-1：链路通畅)")
				.addPrimaryConstraint("PK_D_ER_MRINFO", "C_IDEN")
				.build(UpdateType.REQUEST, "42660", "【中国银行】深证通伺服器要求采用热备模式", "wulongxing@ysstech.com", "2017-06-12");
	}
}
