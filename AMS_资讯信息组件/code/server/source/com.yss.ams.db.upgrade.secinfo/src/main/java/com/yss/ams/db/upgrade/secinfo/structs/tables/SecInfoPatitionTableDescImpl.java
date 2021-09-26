/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2017年7月8日杨海茗
**********************************************************************/

package com.yss.ams.db.upgrade.secinfo.structs.tables;

import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision.NUMBER;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength.DATE;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength.VARCHAR2;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ColumnBuilder;
import com.yss.fast.db.upgrade.support.api.IndexBuilder;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraPartitionType;

/**
 * @ClassName BaseInfoPatitionTableDescImpl
 * @Description
 * @author yhm
 * @CreateDate 2017-5-8
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SecInfoPatitionTableDescImpl extends BaseStructDesc {

	private TableBuilder tableBuilder = null;
	private ColumnBuilder columnBuilder  = null;
	private IndexBuilder indexBuilder = null;
	
	@Override
	public void execute() throws Exception {
		tableBuilder = getTableBuilder();
		columnBuilder = getColumnBuilder();
		indexBuilder = getIndexBuilder();
		buildT_P_BI_PLATE_SUB();
		buildT_P_SV_SEC_BASE();
		buildT_D_SV_FI_PAY();
		buildT_D_SV_FI_INCOME();
		buildT_D_SV_FI_FACTRATE();
		buildT_P_SV_INDEX_STOCK();
		buildT_P_SV_INDEX();
		buildT_P_NS_BASE();
		buildT_D_MP_HG_MKT();
		buildT_D_MP_PRE_STOCK();
		buildT_D_MP_INDEX();
		buildT_D_MP_SEC_MKT();
		buildT_D_MP_SEC_FW();
		buildT_P_AB_PORT_SEC();
		buildT_D_MP_SEC_EQU();
		buildT_D_MP_SEC_FXF();
		buildT_D_MP_SEC_TRANSFERPARA();
		buildT_D_MP_SEC_TRANSFER();
		buildT_P_AO_VAL_SUPP();
		buildT_P_SV_SEC_FEEINFO();
		buildT_P_SV_SEC_SOLDBACK();
		buildT_D_MP_ETF_SKEP_DETAIL();
		//// 债券每百元 拆分到资讯组件，这里补充sql 
		buildR_D_CLR_PARAM();
		//STORY65821富国基金--功能优化--基金基本信息中增加字段
		buildT_P_SV_SEC_PARA();
		//STORY #68666 年金非标资产标准信息维护需新增自定义付息日期 
		buildT_P_SV_SEC_BASE_PAYDAY();
	}	
	
	/**
	 * STORY #68666 年金非标资产标准信息维护需新增自定义付息日期 
	 * @throws Exception
	 */
	private void buildT_P_SV_SEC_BASE_PAYDAY() throws Exception {
		tableBuilder.createTable("T_P_SV_SEC_BASE_PAYDAY", "自定义付息日期")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "ID")
		.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
		.addColumn("C_PAY_DAY", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "' '", "自定义付息日期")
		.addPrimaryConstraint("PK_P_SV_SEC_BASE_PAYDAY", "C_IDEN")
		.createUniqueIndex("IDX_P_SV_SEC_BASE_PAYDAY", "C_SEC_CODE")
		.build(UpdateType.REQUEST, "68666", "新增", "liuwenjian", "2019-09-23");
	}
	
	private void buildT_P_BI_PLATE_SUB() throws Exception {
		tableBuilder.createTable("T_P_BI_PLATE_SUB","板块信息设置_子表").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_PLATE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "板块代码")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券内码")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易市场代码")
				.addColumn("C_CAPITAL", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "总股本")
				.addColumn("C_CIR_CAPITAL", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "流通股本")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "起始日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "终止日期")
				.addPrimaryConstraint("PK_P_BI_PLATE_SUB", "C_IDEN")
				//.createUniqueIndex("IDX_P_BI_PLATE_SUB1", "C_PLATE_CODE,C_SEC_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
		//21.6海通测试BUG,超长字段保存报错,扩大字段精度
		columnBuilder.alterTable("T_P_BI_PLATE_SUB")
		.modifyColumn("C_PLATE_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '")
		.build(UpdateType.BUG, "000001", "21.6海通测试BUG,超长字段保存报错,扩大字段精度", "sunyanlin", "2018-01-22");
		//STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口 
		columnBuilder.alterTable("T_P_BI_PLATE_SUB")
		.addColumn("C_PLATE_FLBZ", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "板块分类标准，根据板块代码带出")
		.build(UpdateType.REQUEST, "58957", "STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口  ", "wulei", "2018-11-12");
		indexBuilder.createUniqueIndex("IDX_P_BI_PLATE_SUB1")
		.setColumns("C_PLATE_CODE,C_SEC_CODE,D_BEGIN,C_PLATE_FLBZ")
		.onTable("T_P_BI_PLATE_SUB")
		.build(UpdateType.REQUEST, "58957", "STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口 ", "wulei", "2018-11-12");
	}
	
	private void buildT_D_MP_ETF_SKEP_DETAIL() throws Exception {
		tableBuilder.createTable("T_D_MP_ETF_SKEP_DETAIL","股票篮成分股信息")
				.addColumn("D_TRADE", OraColumnTypeNoLength.DATE, true, "", "业务日期")
				.addColumn("C_TRADE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "ETF基金代码（二级代码）")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "证券代码")
				.addColumn("C_SEC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "证券简称")
				.addColumn("N_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "证券数量")
				.addColumn("C_REP_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "", "替代标志")
				.addColumn("N_RATIO", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "溢价比例")
				.addColumn("N_SUB_MONEY", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "申购替代金额")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种代码")
				.addColumn("N_REDEM_MONEY", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "赎回替代金额")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "市场代码")
				.addColumn("C_DC_CODE_REPL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "现金替代币种")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "自动ID")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口代码")
				.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "净值日期")
				.addColumn("N_NAV", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "单位净值")
				//modify by caoxingyun 2017-10-16 估值系统标准表索引定义
				.createIndex("IDX_D_MP_ETF_SKEP_DETAIL2", "C_SEC_CODE,C_TRADE_CODE, D_TRADE")
				.createIndex("IDX_D_MP_ETF_SKEP_DETAIL", "D_TRADE")
				.createIndex("IDX_D_MP_ETF_SKEP_DETAIL1", "C_TRADE_CODE,D_TRADE,C_REP_MARK")
				.createIndex("IDX_D_MP_ETF_SKEP_DETAIL4", "C_SEC_CODE")
				.build(UpdateType.REQUEST, "000001", "");
	}
	
	private void buildT_P_SV_SEC_SOLDBACK() throws Exception {
		tableBuilder.createTable("T_P_SV_SEC_SOLDBACK", "证券回售信息表")
				.setModuleCode("SECINFO")
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_SEC_CODE", VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_SEC_MKT_CODE", VARCHAR2, 50, false, "' '", "上市代码")
				.addColumn("C_MKT_CODE", VARCHAR2, 20, false, "' '", "交易市场")
				.addColumn("N_SOLDBACK_PRICE", NUMBER, 18, 4, false, "1", "回售价格")
				.addColumn("D_SOLDBACK_BEGIN", DATE, true, "", "回售开始日期")
				.addColumn("D_SOLDBACK_END", DATE, true, "", "回售结束日期")
				.addColumn("D_FINAL", DATE, true, "", "回售资金到账日期")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_SV_SEC_SOLDBACK", "C_IDEN")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
		columnBuilder.alterTable("T_P_SV_SEC_SOLDBACK")
		.addColumn("C_PORT_CODE_LIST", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "投资组合")
		.build(UpdateType.REQUEST, "57678", "新建", "wangtao_sh@ysstech.com", "2018-08-21");
		columnBuilder.alterTable("T_P_SV_SEC_SOLDBACK")
		.addColumn("C_DV_QUT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "报价方式")
		.addColumn("D_AI_END", OraColumnTypeNoLength.DATE, true, "", "计息截止日")
		.addColumn("C_DECLARE_CODE", OraColunmnTypeOnlyLength.VARCHAR2,20, true, "' '", "申报代码")
		.build(UpdateType.REQUEST, "74915", "STORY74915【兴全基金】债券回售整体方案（一期+计息调尾）", "zhoushuhang", "2019-07-22");
		
		columnBuilder.alterTable("T_P_SV_SEC_SOLDBACK")
        .addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "' '", "数据来源")
        .addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2,100, true, "' '", "接口代码")
        .build(UpdateType.REQUEST, "97387", "STORY #97387 T_P_SV_SEC_SOLDBACK增加数据来源C_CFG_CODE和手工标识C_DATA_IDF标识", "liuwenjian", "2020-11-30");
	}
	
	private void buildT_D_MP_SEC_TRANSFER() throws Exception{
		tableBuilder.createTable("T_D_MP_SEC_TRANSFER","证券代码转换信息")
		.addColumn("c_iden ", OraColunmnTypeOnlyLength.VARCHAR2, 30, false,"", "自动ID")
		.addColumn("c_sec_code",  OraColunmnTypeOnlyLength.VARCHAR2, 50, false,"", "证券内码")
		.addColumn("c_transfer_code",  OraColunmnTypeOnlyLength.VARCHAR2, 50, false,"", "转换规则代码")
		.addColumn("c_pub_code ",  OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "披露代码")
		.addColumn("c_data_idf", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'Z'", "数据来源")
		.addColumn("c_desc ",  OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
		.addColumn("c_update_by ",  OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新人")
		.addColumn("c_update_time ",  OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新时间")
		.addColumn("c_check_by ",  OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("c_check_time ",  OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
		.addColumn("n_check_state ",  OraColunmnTypeOnlyLength.NUMBER, 3, false, "0", "审核状态")
		//STORY #90999 境外证券信息缺少实际交易场所信息 添加披露交易所字段
        .addColumn("C_PUB_MKT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "披露交易所")
		.addPrimaryConstraint("PK_D_MP_SEC_TRANSFER", "C_IDEN")
		.addUniqueConstraint("IDX_D_MP_SEC_TRANSFER", "C_SEC_CODE")
		.build(UpdateType.REQUEST, " 32744 " , "");
		columnBuilder.alterTable("T_D_MP_SEC_TRANSFER")
	    .addColumn("c_type", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "转换类型")
		.build(UpdateType.REQUEST, "000001", "", "", "");
		
		columnBuilder.alterTable("T_D_MP_SEC_TRANSFER")
			.addColumn("C_TRANSFER_VAR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "披露品种")
			.modifyColumn("C_PUB_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "")
			.build(UpdateType.REQUEST, "48618", "品种信息通过证券代码转换功能，实现按照实际的品种在增值税计税券中进行计税", "chenyoucai", "2017-12-23");
		indexBuilder.createUniqueIndex("IDX_D_MP_SEC_TRANSFER1")
		 .setColumns("C_SEC_CODE,C_TRANSFER_CODE")
		 .onTable("T_D_MP_SEC_TRANSFER")
		 .build(UpdateType.REQUEST, "48618", "品种信息通过证券代码转换功能，实现按照实际的品种在增值税计税券中进行计税", "chenyoucai", "2017-12-23");
	}
	
	private void buildT_P_SV_SEC_FEEINFO() throws Exception {
		tableBuilder.createTable("T_P_SV_SEC_FEEINFO", "计费证券信息表")
				.setModuleCode("SECINFO")
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_SEC_CODE", VARCHAR2, 200, false, "", "证券内码")
				.addColumn("C_SEC_NAME", VARCHAR2, 50, true, "", "证券内码")
				.addColumn("C_SEC_MKT_CODE", VARCHAR2, 50, true, "", "上市代码")
				.addColumn("C_SEC_VAR_CODE", VARCHAR2, 20, true, "", "证券品种")
				.addColumn("C_SFJT", VARCHAR2, 2, false, "'1'", "是否计提")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, true, "", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, true, "", "审核状态")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, true, "", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, true, "' '", "组合代码")
				.createUniqueIndex("IDX_P_SV_SEC_FEEINFO", "C_SEC_CODE, C_PORT_CODE, C_SFJT")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
	}	
	
	private void buildT_P_AO_VAL_SUPP() throws Exception {
		tableBuilder.createTable("T_P_AO_VAL_SUPP", "证券转让类型")
				.setModuleCode("SECINFO")
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_SEC_CODE", VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_SEC_MKT_CODE", VARCHAR2, 50, false, "' '", "上市代码")
				.addColumn("C_MKT_CODE", VARCHAR2, 20, false, "' '", "市场代码")
				.addColumn("C_DV_TYPE", VARCHAR2, 20, false, "' '",  "转让类型")
				.addColumn("C_SUPP_TYPE", VARCHAR2, 20, false, "' '", "参数类型")
				.addColumn("D_BEGIN", DATE, true, "", "开始日期")
				.addColumn("D_END", DATE, true, "", "终止日期")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_AO_VAL_SUPP", "C_IDEN")
				//modify by caoxiingyun 估值系统标准表索引定义
				.createIndex("IDX_P_AO_VAL_SUPP0", "C_SEC_CODE,D_BEGIN")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
	}
	
	private void buildT_P_SV_SEC_BASE() throws Exception {
		tableBuilder.createTable("T_P_SV_SEC_BASE","证券基本信息")
				.setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "证券代码")
				.addColumn("C_SEC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "证券名称")
				.addColumn("C_SEC_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "上市代码")
				.addColumn("C_SEC_ISIN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "ISIN代码")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易市场")
				.addColumn("C_SEC_VAR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "证券品种")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "交易币种")
				.addColumn("N_PRICE_FCR", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "1 ", "报价因子")
				.addColumn("C_SEC_CODE_TRG", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "标的证券")
				.addColumn("N_AMOUNT_HD", OraColumnTypeNeedPrecision.NUMBER, 18, 4, false, "1 ", "每手数量")
				.addColumn("N_FV_ISSUE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "1 ", "发行面值")
				.addColumn("D_TO_LIST", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')",
						"上市日期")
				.addColumn("D_OFF_LIST", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')",
						"退市日期")
				.addColumn("C_DV_VAR_DUR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "品种期限")
				.addColumn("C_DV_QUT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "报价方式")
				.addColumn("N_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "税率")
				.addColumn("N_FV_IR", OraColumnTypeNeedPrecision.NUMBER, 30, 16, true, "", "票面利率")
				.addColumn("N_PRICE_ISSUE", OraColumnTypeNeedPrecision.NUMBER, 18, 8, true, "", "发行价格")
				.addColumn("C_DV_AI_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "计息方式")
				.addColumn("C_DV_PI_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "付息公式")
				.addColumn("D_AI_BEGIN", OraColumnTypeNoLength.DATE, true, "", "计息起始日")
				.addColumn("D_AI_END", OraColumnTypeNoLength.DATE, true, "", "计息截止日")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 300, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_AI_EXPR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "计息公式")
				.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所属机构")
				.addColumn("C_ORG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "出票人")
				.addColumn("C_OPEN_ACC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "承兑人行名")
				.addColumn("C_OPEN_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "承兑人行号")
				.addColumn("C_SYS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "大额支付号")
				.addColumn("C_CREDIT_RATING", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "信用评级")
				.addColumn("C_DV_ASSURE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "担保方式")
				.addColumn("C_DV_ISSUE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "发行类型")
				.addColumn("C_SEC_NAME_CN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "证券中文名称")
				.addColumn("N_RATIO", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "0", "保证金比例1")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "期权到期日期")
				.addColumn("C_ETF_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "ETF类型")
				.addColumn("C_SETT_ORG", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "结算机构")
				.addColumn("C_CONTAINRIGHT_FLAG", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'NULLRIGHT'", "含权标记")
				.addColumn("C_DV_RIGHT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'NULLRIGHT'", "含权标志")
				.addColumn("C_DV_RTA", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "利率类型")
				.addColumn("N_SPREAD", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "", "利差")
				.addColumn("C_EXPR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "利率代码")
				.addColumn("N_BLXS", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "", "比例系数")
				.addColumn("C_JCJG", OraColunmnTypeOnlyLength.VARCHAR2, 60, true, "", "检查间隔")
				.addColumn("N_UPPER_LIMIT", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "", "利率上限")
				.addColumn("N_LOWER_LIMIT", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "", "利率下限")
				.addColumn("C_INTERVAL_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "", "周期")
				.addColumn("C_INTERVAL_DAY", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "", "天数")
				.addColumn("C_RZRQ_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "融资融券标示")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "投资组合")
				.addColumn("C_SJSSZQ", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "实际所属证券")
				.addColumn("C_JTJZ4STD_SETT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "标准资产计提基准")
				.addColumn("C_JTJZ4NOTSTD_SETT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "非标资产计提基准")
				.addColumn("c_pcyk_cury", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "平仓盈亏币种代码")
				.addColumn("d_sqai_begin",  OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","首期起息日期")
				.addColumn("c_bank_code", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "联行号")
				.addColumn("c_branch_bank_code", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "银行支行代码")
				.addColumn("C_DJPX_OPEN", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'1'", "对价派息启用")
				// STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求 edit by sunyanlin 20190910
				.addColumn("C_INDEX_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "指数品种")
				// STORY #92285 华夏基金-彭博接口的证券代码取值逻辑变更-证券基本信息 
				.addColumn("C_PBSEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "彭博内码")
				// STORY #98154 AMS国寿资产V4.5-新增永续债提醒功能，若买入时该券是永续债，则给与提醒
				.addColumn("C_PAY_OFF_ORDER", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "清偿顺序")
				// STORY #98178 【估值核算】【国寿资产】【V1.300.7.0.20200831.1103】-O32红利投资接口增加控制是否包含节假日收益参数
				.addColumn("C_DV_IS_JJRSY", OraColunmnTypeOnlyLength.VARCHAR2, 1, true, "'0'", "含节假日收益")
				//modify by caoxingyun 2017-10-16 估值系统标准表索引定义
				.addPrimaryConstraint("PK_P_SV_SEC_BASE", "C_IDEN")
				.createUniqueIndex("IDX_P_SV_SEC_BASE4", "C_SEC_CODE")
				.createIndex("IDX_P_SV_SEC_BASE2","D_TO_LIST, D_OFF_LIST,C_MKT_CODE, C_SEC_VAR_CODE")
				//.createIndex("IDX_P_SV_SEC_BASE3", "C_MKT_CODE,N_CHECK_STATE,D_TO_LIST")
				//.createIndex("IDX_P_SV_SEC_BASE4", "N_CHECK_STATE,C_MKT_CODE,C_SEC_CODE")
				//BUG #344526 华夏基金QD-彭博债券基本信息接口清算报错-彭博内码重复
				.createUniqueIndex("IDX_P_SV_SEC_BASE3", "C_SEC_MKT_CODE,C_SEC_ISIN_CODE, D_TO_LIST, D_OFF_LIST, C_MKT_CODE, C_PORT_CODE")
				//.createIndex("IDX_P_SV_SEC_BASE", "C_SEC_MKT_CODE")
				.createIndex("IDX_P_SV_SEC_BASE1", "C_SEC_VAR_CODE,C_SEC_CODE_TRG")
				.createIndex("IDX_P_SV_SEC_BASE5", "C_CHECK_TIME,N_CHECK_STATE")
				// 增加以C_SEC_ISIN_CODE的索引byleeyu20191023 BUG #282353
				.createIndex("IDX_P_SV_SEC_BASE6", "C_SEC_ISIN_CODE,C_PBSEC_CODE")
				.build(UpdateType.REQUEST, "000001", "");
		
		 columnBuilder.alterTable("T_P_SV_SEC_BASE")
				 	.addColumn("c_pcyk_cury", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "")
				 	.addColumn("d_sqai_begin", OraColumnTypeNoLength.DATE, true, "", "")
				    .addColumn("d_tra_begin", OraColumnTypeNoLength.DATE, true, "", "转换起始日")
				    .addColumn("d_tra_end", OraColumnTypeNoLength.DATE, true, "", "转换截止日")
				    .addColumn("c_fx_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "证券分销代码")
					.addColumn("C_SEC_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "")
					.build(UpdateType.REQUEST, "1376620", "");
		 
		   columnBuilder.alterTable("T_P_SV_SEC_BASE")
				      .addColumn("C_DEBT_RATING", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "债项评级")
				      .addColumn("C_RATING_OUTLOOK", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "评级展望")
				      .addColumn("C_ORG_ATTRIBUTE",OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "主体属性")
				      .addColumn("C_CITY_INVEST_BOND", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "是否城投")
				      .addColumn("C_INDUSTRY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "行业")
				      .addColumn("N_REMAIN_PERIOD", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "剩余期限")
				      .build(UpdateType.REQUEST, "41899", "");
		   
		   columnBuilder.alterTable("T_P_SV_SEC_BASE")
					   .addColumn("C_GUAR_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "保本类型")
					   .addColumn("C_FINA_COMM", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "金融商品")
					   .build(UpdateType.REQUEST, "37961", "STORY37961财税140号文件，针对资管增值税系统改造需求", "xiaozhilong", "2017-6-20");
		
			columnBuilder.alterTable("T_P_SV_SEC_BASE")
					   .addColumn("N_XSFWFL", OraColumnTypeNeedPrecision.NUMBER, 18, 8, true, "", "销售服务费率")
					   .addColumn("c_tgr_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "托管人代码")
					   .addColumn("c_tgr_name", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "托管人名称")
					   .build(UpdateType.REQUEST, "39539 ", "【南方基金】FOF基金系统改造", "liutao_sh", "2017-07-18");
		   
			   columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("n_today_interest", OraColunmnTypeOnlyLength.NUMBER, 3, true, "0", "含付息日利息")
			   .addColumn("n_xsfwfl", OraColumnTypeNeedPrecision.NUMBER, 18,8, true, "0", "销售服务费率")
			   .addColumn("c_tgr_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
			   .addColumn("c_tgr_name", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
			   .build(UpdateType.REQUEST, "41687", "【易方达】NJ0002 XT0023", "zhouxudong", "2017-8-4");
//			   
//			   columnBuilder.alterTable("T_P_SV_SEC_BASE")
//			   .addColumn("C_BASE_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
//			   .addColumn("C_PAY_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
//			   .addColumn("N_PAY_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
//			   .addColumn("N_BASE_DATE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "")
//			   .addColumn("N_INDUSTRY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "")
//			   .build(UpdateType.REQUEST, "00001 ", "", "SDB", "2017-11-06");
			   
			   columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("C_FXJG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "发行机构")
			   .build(UpdateType.REQUEST, "49921", "针对债券品种支持维护发行机构", "lujianhao", "2018-01-16");
			   //STORY #38100 【招商证券】万份收益计提方式支持计尾不计头 
			   columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("C_SYJT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收益计提方式")
			   .build(UpdateType.REQUEST, "38100", "万份收益计提方式支持计尾不计头", "weijingjing", "2019-01-12");
			   
			   //add by shijian 2017-11-11 STORY #47805 嘉实基金QD—回购业务需求
			   columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("C_REPO_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "' '", "回购类别")
			   .addColumn("C_REPO_INCOME_FROM", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "' '", "回购收益计算起始日期")
			   .addColumn("C_REPO_INCOME_TO", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "' '", "回购收益计算结束日期")
			   .build(UpdateType.REQUEST, "47805", "嘉实基金QD—回购业务需求", "shijian", "2017-11-11");
		
				// added by HeLiang 2017-11-15 STORY #43829 新企业会计准则（Ifrs9）解决方案
				columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_FINANCE_TOOL", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "金融工具")
				.build(UpdateType.REQUEST, "43829", "新企业会计准则（Ifrs9）解决方案", "HeLiang", "2017-11-15");
		
				// added by xyh 2017-12-06 
				//STORY #49171 【招商证券】【营改增】标准证券需要记录发行方,并且历史标的需要支持excel导入<br>
				//合并 STORY #47088 债券基本信息，增加发行机构类型字段，区分金融机构和非金融机构<br>
				columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_MAIN_PROP", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "主体性质")
				.build(UpdateType.REQUEST, "49171", "【招商证券】【营改增】标准证券需要记录发行方,并且历史标的需要支持excel导入", "xyh", "2017-12-06");
				
				//同步华夏STORY #46638 公共处理债券信息时，需根据财汇提供的PAR_BOND_INFO数据更新上市日期
				columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "'Z'", "数据来源")
				.build(UpdateType.REQUEST, "46638", "公共处理债券信息时，需根据财汇提供的PAR_BOND_INFO数据更新上市日期", "ljh", "2018-1-16");
			    
				//合并代码需求44526
			columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("D_TO_LIST_ZQ", OraColumnTypeNoLength.DATE, true ,"TO_TIMESTAMP('1900-01-01','YYYY/MM/DD')", "上市日期")
				.build(UpdateType.REQUEST, "44526", "【招商证券】【营改增】标准证券需要记录发行方,并且历史标的需要支持excel导入", "xyh", "2017-12-06");			
			   
			//add by chenchangyou 20180122  STORY #42102 【鹏华基金】境外债券生成历史付息期间逻辑变更
			     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.modifyColumn("d_sqai_begin",  OraColumnTypeNoLength.DATE, true, "to_timestamp('1900-01-01','YYYY/MM/DD')")
				.build(UpdateType.REQUEST, "42102", "【鹏华基金】境外债券生成历史付息期间逻辑变更", "ccy", "2018-03-27");
			     
			// STORY #47986 嘉实基金-理财区分公募养老金等产品类型，以及特殊管理费计提方式需求 (#2 #1 )
			columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_LC_SEC_TAG", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "募集方式")
			.build(UpdateType.REQUEST, "47986", "嘉实基金-理财区分公募养老金等产品类型，以及特殊管理费计提方式需求", "csz", "2018-2-26");
			
			// STORY #53617 【广发基金】新增理财产品计息方式  add by lijinpeng  20180418
			columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("C_ACCURACY_RATE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "0", "利率精度") // STORY33701
			   .addColumn("N_CSLL", OraColumnTypeNeedPrecision.NUMBER, 15, 8 , true, "0", "初始利率") // STORY 33739
			   .addColumn("N_FDBL", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "0", "浮动比例") // STORY 33739
			   .build(UpdateType.REQUEST, "53617", "【广发基金】新增理财产品计息方式", "lijinpeng", "2018-04-18");
			
			columnBuilder.alterTable("T_P_SV_SEC_BASE")
			   .addColumn("C_BASE_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "基准日期类型")
			   .addColumn("C_PAY_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "付息日期类型")
			   .addColumn("N_PAY_DATE", OraColunmnTypeOnlyLength.NUMBER, 3, true, "", "付息天数")
			   .addColumn("N_BASE_DATE", OraColunmnTypeOnlyLength.NUMBER, 3, true, "", "基准天数")
			   .build(UpdateType.REQUEST, "54227", "STORY54227- 关于协议存款自定义结息日的需求合并：STORY32772【南方基金】【V2.5需求】南方基金：协定存款付息方式增加", "zjj", "2018-3-28");
			
			
			// STORY #42965 【紧急】买卖货币基金强增强减业务
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("n_fesx",  OraColumnTypeNeedPrecision.NUMBER,18,4, true, "", "份额上限")
				.addColumn("n_fexx",  OraColumnTypeNeedPrecision.NUMBER,18,4, true, "", "份额下限")
				.build(UpdateType.REQUEST, "42965", "【紧急】买卖货币基金强增强减业务", "zhouxudong", "2017-07-05");
		     
		     //STORY 56789
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		        .modifyColumn("c_org_type", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '")
				.build(UpdateType.REQUEST, "56789", "' '", "ljp", "2018-7-11");
				
			 //BUG #198015 【富国基金】财汇债券基本信息导入接口FXJG字段精度不够
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		        .modifyColumn("C_FXJG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "")
				.build(UpdateType.BUG, "198015", "【富国基金】财汇债券基本信息导入接口FXJGOU字段精度不够", "liutao", "2018-4-17");
		        
		     //STORY #45603 【易方达基金】【QDII】证券信息数据导入清算
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("c_issuers_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "发行人ID")
				.addColumn("c_issuers_name", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "发行人名称")
				.build(UpdateType.REQUEST, "45603", "合并【易方达基金】【QDII】证券信息数据导入清算", "zhengjiebin", "2018-05-25");
				
			 //STORY #50934 【易方达基金】【QDII】新增接口更新A股的ISIN和英文信息
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("c_sedolcode", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '", "SEDOL码")
				.build(UpdateType.REQUEST, "50934", "合并【易方达基金】【QDII】新增接口更新A股的ISIN和英文信息", "xiongdaolin", "2018-04-27");
		     
			 //STORY #40222 【2.5升级】【专户】易方达基金：支持分级基金按成本估值
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_SEC_VAR_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "证券品种分级")
				.build(UpdateType.REQUEST, "40222", "合并【2.5升级】【专户】易方达基金：支持分级基金按成本估值", "xiongdaolin", "2018-05-31");
			 
			 //STORY52063仅在大宗协议平台上市的债券，收盘价不作为估值行情处理
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("c_dv_plat", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "交易平台")
				.build(UpdateType.REQUEST, "52063", "STORY52063仅在大宗协议平台上市的债券，收盘价不作为估值行情处理", "wangtao", "2018-06-07");
		     
		     // STORY#53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_JZFS_CODE", VARCHAR2, 20 , true, "", "集资方式")
				.build(UpdateType.REQUEST, "53605", "【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案", "HeMing", "20180702");
		     
		     //BUG #210529 【富国基金】债券基本信息界面债券关联多个组合报错
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		     	.modifyColumn("C_PORT_CODE", VARCHAR2, 200,true, "' '")
		     	.build(UpdateType.BUG, "210529", "BUG #210529 【富国基金】债券基本信息界面债券关联多个组合报错", "liutao", "20180713");
		     		
		     //STORY #62079 人保资产-支持人保资产新科目体系核算
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		     	.addColumn("C_KZCD_GQ", VARCHAR2, 20, true, "", "控制程度")
		     	.build(UpdateType.REQUEST, "62079", "STORY #62079 人保资产-支持人保资产新科目体系核算", "xiongdaolin", "20181013");

		     /*//STORY #64216 股衍主数据信息交易编号长度调整
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		        .modifyColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "")
				.build(UpdateType.REQUEST, "64216", "STORY #64216 股衍主数据信息交易编号长度调整", "lzs", "2018-11-2");*/
		     
		     //STORY #66077 人保资产-支持银行间债券A/A特殊计息
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		     	.addColumn("N_JXTS", OraColumnTypeNeedPrecision.NUMBER, 6, 0, true, "0", "计息天数")
		     	.build(UpdateType.REQUEST, "66077", "STORY #66077 人保资产-支持银行间债券A/A特殊计息", "fengyun", "20181216");
		     
		     //STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_DV_JXFS", VARCHAR2, 20 , true, "'JXFS_MRTX'", "计息方式")
				.build(UpdateType.REQUEST, "64492", " STORY64492【易方达香港】【一期】境外回购支持按年利率计算日收益进行计提 ", "hezhigang", "20181212");
				
			 //STORY #64863 【政策需求】银行间信用风险缓释凭证（一期） (#2 #1 ) 
			 columnBuilder.alterTable("T_P_SV_SEC_BASE")
			 	.modifyColumn("C_DV_AI_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '")
			 	.build(UpdateType.REQUEST, "64863", "STORY #64863 【政策需求】银行间信用风险缓释凭证（一期） (#2 #1 ) ", "liuxiawei", "20190124");
				
				  // STORY #59902 改造存放品种信息界面，新增存放品种
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		     	.addColumn("C_SNZC", VARCHAR2, 10, true, "", "省内资产")
		     	.build(UpdateType.REQUEST, "59902", "STORY #59902 改造存放品种信息界面，新增存放品种,增加字段省内资产", "wenxiyang", "20180713");
		   //STORY #59838 改造债券基本信息界面，满足青岛银行中债披露需求
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
				.addColumn("C_INV_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "投资方式")
				.build(UpdateType.REQUEST, "59838", "STORY #59838 改造债券基本信息界面，满足青岛银行中债披露需求", "zhengjianfei", "20180903");

		   //STORY #60331 场外期权业务数据接口
		     columnBuilder.alterTable("T_P_SV_SEC_BASE")
		     	.addColumn("C_OPTION_TYPE", VARCHAR2, 50,true, "","期权结构")
		     	.build(UpdateType.REQUEST, "60331", "STORY #60331场外期权业务数据接口", "liaobo", "20180906");
		     
	     //STORY #60331场外期权业务数据接口
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_vanillaExotic", VARCHAR2, 6,true, "","期权属性")
	     	.build(UpdateType.REQUEST, "60331", "STORY #60331场外期权业务数据接口", "liaobo", "20180906");
	     
	     //STORY #60331场外期权业务数据接口
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("N_ISP", OraColumnTypeNeedPrecision.NUMBER,18,4,true, "","期初价格")
	     	.build(UpdateType.REQUEST, "60331", "STORY #60331场外期权业务数据接口", "liaobo", "20180906");
	     
	     //STORY #64802 【招商基金】增加fxzt字段的读入及增加债券基本信息该字段的展示
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_MAIN_NAME", VARCHAR2, 200, true, "", "主体名称")
	     	.build(UpdateType.REQUEST, "64802", "STORY #64802 【招商基金】增加fxzt字段的读入及增加债券基本信息该字段的展示", "zhangchuan", "20190222");
			
		 //STORY #68750 【政策需求】银行间信用风险缓释凭证（二期）  
		 columnBuilder.alterTable("T_P_SV_SEC_BASE")
		 	.modifyColumn("C_DV_VAR_DUR", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '")
		 	.build(UpdateType.REQUEST, "68750", "STORY #68750 【政策需求】银行间信用风险缓释凭证（二期）   ", "liuxiawei", "20190213");
				
		 //BUG #243052 证券基本信息标的字段增加长度
		 columnBuilder.alterTable("T_P_SV_SEC_BASE")
		 	.modifyColumn("C_SEC_CODE_TRG", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "")
			.build(UpdateType.BUG, "243052", "BUG #243052 证券基本信息标的字段增加长度", "xdl", "2019-02-12");
	
		 //BUG243168【易方达（香港）】产品基本信息英文名称及证券基本信息名称长度不足
		 columnBuilder.alterTable("T_P_SV_SEC_BASE")
		 	.modifyColumn("C_SEC_NAME_CN", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "")
		    .modifyColumn("C_ISSUERS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '")
		    .modifyColumn("C_ISSUERS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '")
		    .build(UpdateType.BUG, "243168", "BUG243168【易方达（香港）】产品基本信息英文名称及证券基本信息名称长度不足", "hezhigang", "2019-02-12");
	
		//BUG243168【易方达（香港）】产品基本信息英文名称及证券基本信息名称长度不足
		columnBuilder.alterTable("T_P_SV_SEC_BASE")
			     .modifyColumn("C_PAY_CALCULATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "")
			     .build(UpdateType.REQUEST, "63605", "STORY #73909 【紧急】人保资产-支持头部残段存款业务核算逻辑 ", "tanmeishan", "2019-06-25");
	     //STORY #68109 【21.6】【易方达基金】彭博证券信息文件导入接口和清算接口优化 
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	        .addColumn("C_SEC_NAME_EN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "英文名称")
	        .addColumn("C_DV_RIGHTMX", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "含权明细")
			.build(UpdateType.REQUEST, "68109", "STORY #68109 【21.6】【易方达基金】彭博证券信息文件导入接口和清算接口优化", "zhaohai", "20190315");
		    
	     //BUG252716【易方达香港】证券基本信息加大证券代码、ISIN、上市代码长度到200
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	        .modifyColumn("C_SEC_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '")
	        .modifyColumn("C_SEC_ISIN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "")
	        .build(UpdateType.BUG, "252716", "BUG252716【易方达香港】证券基本信息加大证券代码、ISIN、上市代码长度到200", "hezhigang", "2019-04-15");
	   //STORY #63605 富国基金-【公募】存放业务无法生成正确得付息信息和支取流水 
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_PAY_CALCULATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "付息日计算")
			.build(UpdateType.REQUEST, "63605", "STORY #63605 富国基金-【公募】存放业务无法生成正确得付息信息和支取流水 ", "liutao", "2019-01-17");
	     //STORY #64653 建信基金-FOF产品-标准证券-理财产品信息中管理费率、托管费率、销售服务费率字段可以通过读入par_base_fund自动载入 
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	        .addColumn("N_GLF_RATE", OraColumnTypeNeedPrecision.NUMBER, 18, 8, true, "0", "管理费费率")
		    .addColumn("N_TGF_RATE", OraColumnTypeNeedPrecision.NUMBER, 18, 8, true, "0", "托管费费率")
		    .build(UpdateType.REQUEST, "64653", "STORY #64653 建信基金-FOF产品-标准证券-理财产品信息中管理费率、托管费率、销售服务费率字段可以通过读入par_base_fund自动载入 ", "xuhanbing", "2019-05-22");
	     
	   //BUG #264981 平安资产-核算股票质押回购报错
		columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_DV_JXFS", VARCHAR2, 50, true, "'JXFS_MRTX'","计息方式")
			.build(UpdateType.BUG, "264981", "BUG #264981 平安资产-核算股票质押回购报错 ", "zengwangdong", "20190628");
	     
		 //STORY #78340 【高】人保资产-非标产品信息增加税前税后利率处理逻辑  
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("N_RATE_AT", OraColumnTypeNeedPrecision.NUMBER,30,15,true,"0","税后利率")
			.build(UpdateType.REQUEST, "78340", "STORY #78340 【高】人保资产-非标产品信息增加税前税后利率处理逻辑 ", "tms", "2019-09-01");
	     
		 //STORY #77931 【中金公司】优化票交所兑付业务
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_TXH_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "贴现行行名")
			.addColumn("C_TXH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "贴现行行号")
			.build(UpdateType.REQUEST, "77931", "STORY #77931 【中金公司】优化票交所兑付业务 ", "liupeng_cs", "2019-09-04");
	     //// wulongxing 20191023 STORY81024证券基本信息界面新增字段TisCode
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_SM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "SM代码")
			.build(UpdateType.REQUEST, "81024", "STORY81024证券基本信息界面新增字段TisCode", "wulongxing", "2019-10-23");
	     //// weijingjing 20200623 STORY #90922 华夏基金-估值表要展示彭博ticker码
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_TICKER_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "ticker码")
			.build(UpdateType.REQUEST, "90922", "STORY #90922 华夏基金-估值表要展示彭博ticker码", "weijingjing", "2020-06-23");
	     //STORY78461【上海银行】与私募产品运行检测表相关的项目字段新增
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_JWTZ", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "是否境外投资")
			.addColumn("C_QYGPDM", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "项目企业股票代码")
			.addColumn("D_QY_DATE", OraColumnTypeNoLength.DATE, true, "to_timestamp('1900-01-01','YYYY/MM/DD')", "成立日期")
			.addColumn("C_SHXYBM", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "项目企业统一社会信用编码")
			.addColumn("C_QYZCD", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "项目企业注册地")
			.addColumn("C_TZHY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投资行业")
			.addColumn("C_TZJD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投资阶段")
			.build(UpdateType.REQUEST, "77931", "STORY78461【上海银行】与私募产品运行检测表相关的项目字段新增 ", "jiaxinxiao@ysstech.com", "2019-10-21");
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("C_MKT_DETAIL", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "明细市场")
			.build(UpdateType.REQUEST, "68358", "STORY #68358 【南方资本】非标产品需要区分明细市场字段新增", "yangru", "2019-11-07");
	     
	     // add by ZhangJM  STORY #76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
			.addColumn("N_FV_DAY_IR", OraColumnTypeNeedPrecision.NUMBER, 30, 16, true, "", "票面日利率")
			.build(UpdateType.REQUEST, "76306", "STORY76306 银华基金：投资分级基金A类产品，每日计提收益，卖出时进行冲减", "ZhangJM", "2019-11-20");
	     
	     //STORY #84448 华夏基金-QD税收矩阵设置
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
         .addColumn("C_ZCDGJ", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "注册地国家")
            .addColumn("C_JYSSZG", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "交易所所在国")
            .build(UpdateType.REQUEST, "84448", "STORY #84448 华夏基金-QD税收矩阵设置 ", "dingshalu", "2020-03-24");
	   //STORY #90124 社保基金新增通知存款核算需求
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_ZCFS", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "转存方式")
            .addColumn("C_ZCPL", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "转存频率")
//            .addColumn("C_ZCRQ", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "转存日期")
//            .addColumn("C_ZCRQLX", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "转存日期类型")
            .build(UpdateType.REQUEST, "90124", "STORY #90124 社保基金新增通知存款核算需求 ", "hzg", "2020-08-24");
	     //STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_FXSZG", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "发行所在国")
	     	.addColumn("C_FXRSZG", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "发行人所在国")
	     	.addColumn("C_GICS", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "GICS分类")
         .build(UpdateType.REQUEST, "97105", "STORY #97105 新华资产-彭博证券基本信息文件，增加四列信息，估值报表系统需要使用，协助更新此文件接口", "fuzhendong", "2021-07-07");
	     
	     //STORY #95445 华夏基金QD-综合证券信息界面增加BBGID查询功能
	     indexBuilder.createIndex("IDX_P_SV_SEC_BASE7")
			.setColumns("C_PBSEC_CODE")
			.onTable("T_P_SV_SEC_BASE")
			.build(UpdateType.REQUEST, "95445", "STORY #95445 华夏基金QD-综合证券信息界面增加BBGID查询功能", "huangpeng", "2020-11-04");
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
     		.addColumn("C_MANAGER_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "基金管理人")
     		.addColumn("C_FUND_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "基金系列名称")
     		.build(UpdateType.REQUEST, "96254", "STORY96254华夏基金QD-彭博证券基本信息接口，增加直存“基金管理人”和“基金系列名称”的逻辑", "zhoushuhang", "2021-01-15");
	     //STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_INVEST_ACCOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投资者收益账户")
			.build(UpdateType.REQUEST, "76847", "STORY #76847 【华宝基金】511990 SH华宝添益红利投资特殊处理 ", "zimingliang", "2019-11-1");
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_ZGZH_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "正股转换代码")
			.build(UpdateType.REQUEST, "87911", "STORY #87911 新增财汇资讯可转债对应股票信息文件导入接口", "郑耀杰", "2020-05-27 ");
	     // STORY #105860 【招银理财】非标资产逾期业务
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("N_GRACE_PERIOD", OraColumnTypeNeedPrecision.NUMBER, 6, 0, true, "", "宽限期")
			.build(UpdateType.REQUEST, "105860", "STORY #105860 【招银理财】非标资产逾期业务", "mxlee", "2021-07-13");
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_GRACE_PERIOD_RULE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "宽限期计息规则")
			.build(UpdateType.REQUEST, "105860", "STORY #105860 【招银理财】非标资产逾期业务", "mxlee", "2021-07-13");
	     //STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_HDAY_CODE_CK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "存放节假日群")
			.build(UpdateType.REQUEST, "109057", "STORY109057博时基金-公募2.5已实现-存放业务品种信息节假日群的选择", "huangkaixuan", "2021-07-29");
	     
	     //STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_DV_DBR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "担保人")
			.build(UpdateType.REQUEST, "108912", "STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段", "liuyanxu", "2021-08-26");
	     columnBuilder.alterTable("T_P_SV_SEC_BASE")
	     	.addColumn("C_DV_RZR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "融资人")
			.build(UpdateType.REQUEST, "108912", "STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段", "liuyanxu", "2021-08-26");
	
	}
	
	private void buildT_D_SV_FI_PAY() throws Exception {
		tableBuilder.createTable("T_D_SV_FI_PAY","债券历史付息").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "债券代码")
				.addColumn("D_ADJ", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "调息日")
				.addColumn("N_COUP_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "票面利率")
				.addColumn("N_REM_COR", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "剩余本金")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "本期起息日")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "本期截息日")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_BOOL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "周期型利率")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "市场代码")
				.addColumn("N_REPAY_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 18, 4, false, "0 ", "偿还数量")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "'Z'", "数据来源")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "","组合代码")
				.addPrimaryConstraint("PK_D_SV_FI_PAY", "C_IDEN")
				//modify by caoxingyun 2017-10-16 估值系统标准表索引定义 
				.createUniqueIndex("IDX_D_SV_FI_PAY", "C_SEC_CODE,D_ADJ")
				.createIndex("IDX_D_SV_FI_PAY1", "C_SEC_CODE,D_END")
				.createIndex("IDX_D_SV_FI_PAY2", "D_BEGIN,D_END")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
		       
		        //20161116_刘驰_STORY35337
		columnBuilder.alterTable("T_D_SV_FI_PAY")
	            .addColumn("N_QNHB", OraColunmnTypeOnlyLength.NUMBER, 3, true, "", "是否期内还本")
	            .addColumn("D_QNHB", OraColumnTypeNoLength.DATE, true, "", "期内还本日期")
	            .build(UpdateType.REQUEST,"35337" , "");
		
		columnBuilder.alterTable("T_D_SV_FI_PAY")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "","")
				.build(UpdateType.REQUEST, "40535", "部分债券在社保组合的品种不一样，需要增加转换规则","liutao","2017-7-4");
		columnBuilder.alterTable("T_D_SV_FI_PAY")
			    .addColumn("N_QNFX", OraColunmnTypeOnlyLength.NUMBER, 3, true, "", "是否期内付息1-是,0-否")
		        .build(UpdateType.REQUEST, "67977", "STORY #67977 平安资产---非标不规则还本新增业务场景 ","tianpeng","2019-3-14");		                                              
		indexBuilder.createIndex("IDX_D_SV_FI_PAY3").onTable("T_D_SV_FI_PAY")
				.setColumns("C_SEC_CODE, D_QNHB")
				.build(UpdateType.REQUEST, "67977", "STORY #67977 平安资产---非标不规则还本新增业务场景", "tianpeng", "2019-3-14");
		columnBuilder.alterTable("T_D_SV_FI_PAY")
			.modifyColumn("N_REPAY_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0")
			.build(UpdateType.BUG, "333418", "BUG #333418 【太平金控4.5 （300.7.0731.812）】债券基本信息 历史付息信息 ，偿还数量比例  小数位 限制放开到15位 ","xiaomenglei","2020-9-11");
		columnBuilder.alterTable("T_D_SV_FI_PAY")
		.modifyColumn("N_COUP_RATE", OraColumnTypeNeedPrecision.NUMBER, 38, 16, false, "0")
		.build(UpdateType.REQUEST, "42350", "STORY #42350 同业存单利率最多可保留8位小数，期望不限制 ","liangchong","2021-1-27");
		indexBuilder.createIndex("IDX_D_SV_FI_PAY4").onTable("T_D_SV_FI_PAY")
			.setColumns("D_ADJ")
			.build(UpdateType.BUG, "361015", "BUG #361015 【宁波银行资金外包】【1031.1228】计提理财收益速度慢，占用时间长", "xiongdaolin", "2021-3-30");
	}
	
	private void buildT_D_SV_FI_INCOME() throws Exception {
		tableBuilder.createPartitionTable("T_D_SV_FI_INCOME","债券每百元利息").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "债券代码")
				.addColumn("D_INCOME", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "计息日期")
				.addColumn("C_DV_QUT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'NET' ", "报价方式")
				.addColumn("N_INCOME_DAYS", OraColumnTypeNeedPrecision.NUMBER, 10, 0, false, "0", "利息天数")
				//21.6海通升级报错
				.addColumn("N_COUP_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "票面利率")
				.addColumn("N_REM_COR", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "剩余本金")
				.addColumn("N_INCOME_PT", OraColumnTypeNeedPrecision.NUMBER, 35, 20, false, "0", "税前每百元利息(买卖使用）")
				.addColumn("N_INCOME_AT", OraColumnTypeNeedPrecision.NUMBER, 35, 20, false, "0", "税后每百元利息(买卖使用）")
				.addColumn("N_INCOME_PT_DUE", OraColumnTypeNeedPrecision.NUMBER, 35, 20, true, "", "税前每百元利息（计息使用）")
				.addColumn("N_INCOME_AT_DUE", OraColumnTypeNeedPrecision.NUMBER, 35, 20, true, "", "税后每百元利息（计息使用）")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "到期日期")
				// STORY #75904 第一批：QDII-【博时基金】抗通胀债券需求  edit by sunyanlin 20190910  这个是原来的剩余本金的含义，原来的N_REM_COR 在此需求后表达含义是调整后本金
				.addColumn("N_LEFT_MONEY", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "剩余本金")
				//STORY #91504 【富国基金】巡检时发现几张大数据量的表未进行表分区需要优化
				.partitionBy(OraPartitionType.RANGE, "D_INCOME")
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
                .addPartintion("PART_MAX", "MAXVALUE")
				.addPrimaryConstraint("PK_D_SV_FI_INCOME", "C_IDEN")
				.createIndex("IDX_D_SV_FI_INCOME0", "D_INCOME,C_DV_QUT_MOD")
				.createUniqueIndex("IDX_D_SV_FI_INCOME", "C_SEC_CODE,D_INCOME").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
	}
	
	private void buildT_D_SV_FI_FACTRATE() throws Exception {
		tableBuilder.createTable("T_D_SV_FI_FACTRATE","债券实际利率值").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "债券代码")
				.addColumn("D_TRADE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "交易日期")
				.addColumn("D_CHANGE", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "变动日期")
				.addColumn("C_DV_INVEST_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "投资分类")
				.addColumn("C_DV_EXPR_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "算法类型")
				.addColumn("C_DV_AI_EXPR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "计息公式")
				.addColumn("N_FACT_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "实际利率")
				.addColumn("N_OD_BAL", OraColumnTypeNeedPrecision.NUMBER, 18, 4, false, "0", "溢折价余额")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_ISSUE_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "发行方式")
				.addColumn("C_DTA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易属性")
				.addColumn("C_DAI_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'ZQTZ_CB' ", "核算项目")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "'Z' ", "数据来源")
				.addPrimaryConstraint("PK_D_SV_FI_FACTRATE", "C_IDEN")
//				.createUniqueIndex("IDX_D_SV_FI_FACTRATE",
//						"C_PORT_CODE,C_SEC_CODE,D_TRADE,D_CHANGE,C_DV_INVEST_CLS,C_DV_ISSUE_MODE,C_DTA_CODE,C_DAI_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
		
				columnBuilder.alterTable("T_D_SV_FI_FACTRATE").addColumn("C_DV_RATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '","利率类型")
					.build(UpdateType.REQUEST, "67307", "修改", "xiahongqi@ysstech.com", "2019-06-06");
				
				indexBuilder.createUniqueIndex("IDX_D_SV_FI_FACTRATE")
							.setColumns("C_PORT_CODE,C_SEC_CODE,D_TRADE,D_CHANGE,C_DV_INVEST_CLS,C_DV_ISSUE_MODE,C_DTA_CODE,C_DAI_CODE,C_DV_RATE_TYPE")
							.onTable("T_D_SV_FI_FACTRATE")
							.build(UpdateType.REQUEST, "67307", "STORY #67307 人保资产-新系统下支持债券由可供出售重分类至持有至到期时的自动处理", "xiahongqi", "2019-06-06");
	}
	
	private void buildT_P_SV_INDEX_STOCK() throws Exception {
		tableBuilder.createTable("T_P_SV_INDEX_STOCK","指数成分券信息").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指数代码")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "成分券内码")
				.addColumn("C_SEC_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "成分券上市代码")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "启用日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_SV_INDEX_STOCK", "C_IDEN")
				.createIndex("IDX_P_SV_INDEX_STOCK4", "C_INDEX_CODE,D_BEGIN,N_CHECK_STATE,C_SEC_MKT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
		   //STORY #50737 信息披露系统对资讯数据的使用要求
	     columnBuilder.alterTable("T_P_SV_INDEX_STOCK")
	     	.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "结束日期")
	     	.build(UpdateType.REQUEST, "53605 ", "STORY #50737 信息披露系统对资讯数据的使用要求", "zmb", "2019-08-19");
	     // //STORY #50737 信息披露系统对资讯数据的使用要求
	     indexBuilder.createUniqueIndex("IDX_P_SV_INDEX_STOCK")
			.setColumns("C_INDEX_CODE, C_SEC_CODE, D_BEGIN, D_END")
			.onTable("T_P_SV_INDEX_STOCK")
			.build(UpdateType.REQUEST, "53605", "STORY #50737 信息披露系统对资讯数据的使用要求 ", "zmb", "2019-08-19");
	}
	
	private void buildT_P_SV_INDEX() throws Exception {
		tableBuilder.createTable("T_P_SV_INDEX","指数基本信息").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_SEC_ISIN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "ISIN代码")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指数代码")
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "指数名称")
				.addColumn("C_INDEX_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指数类型（行业，综合，股票，债券，基金，利率）")
				.addColumn("C_INDEX_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指数编制机构")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易币种")
				.addColumn("N_INDEX_BASE", OraColumnTypeNeedPrecision.NUMBER, 8, 0, false, "0", "指数基准点数")
				.addColumn("D_BASE", OraColumnTypeNoLength.DATE, true, "", "基准日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "'H' ", "数据标识")
				.addColumn("C_DV_PLAT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "来源")
				.addPrimaryConstraint("PK_P_SV_INDEX", "C_IDEN")
				.createUniqueIndex("IDX_P_SV_INDEX", "C_SEC_CODE,C_DC_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170511");
	}
	
	private void buildT_P_NS_BASE() throws Exception {
		tableBuilder.createTable("T_P_NS_BASE", "非标品种基本信息")
				.setModuleCode("SECINFO")
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_SEC_CODE", VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_SEC_NAME", VARCHAR2, 200, false, "' '", "证券名称")
				.addColumn("C_PORT_CODE", VARCHAR2, 50, true, "", "组合代码")
				.addColumn("C_SEC_MKT_CODE", VARCHAR2, 20, true, "", "上市代码")
				.addColumn("C_SEC_ISIN_CODE", VARCHAR2, 20, true, "", "ISIN代码")
				.addColumn("C_MKT_CODE", VARCHAR2, 20, true, "", "交易市场")
				.addColumn("C_SEC_VAR_CODE", VARCHAR2, 20, false, "' '", "证券品种")
				.addColumn("C_DC_CODE", VARCHAR2, 20, true, "", "交易币种，默认CNY")
				.addColumn("N_PRICE_FCR", NUMBER, 18, 4, false, "1", "报价因子")
				.addColumn("C_SEC_CODE_TRG", VARCHAR2, 200, true, "", "标的证券，利率标的代码")
				.addColumn("N_AMOUNT_HD", NUMBER, 18, 4, false, "1", "每手数量")
				.addColumn("N_FV_ISSUE", NUMBER, 18, 4, false, "1", "发行面值")
				.addColumn("D_TO_LIST", DATE, true, "", "上市日期")
				.addColumn("D_OFF_LIST", DATE, true, "", "退市日期")
				.addColumn("C_DV_VAR_DUR", VARCHAR2, 20, true, "", "持有期限")
				.addColumn("C_DV_PAY_FREY", VARCHAR2, 20, true, "", "付息频率")
				.addColumn("C_DV_QUT_MOD", VARCHAR2, 20, true, "", "报价方式")
				.addColumn("N_RATE", NUMBER, 30, 15, true, "", "税率")
				.addColumn("N_FV_IR", NUMBER, 15, 8, true, "", "票面利率")
				.addColumn("N_PRICE_ISSUE", NUMBER, 15, 8, true, "", "发行价格")
				.addColumn("C_DV_AI_MOD", VARCHAR2, 20, true, "", "计息方式")
				.addColumn("C_DV_AI_EXPR", VARCHAR2, 20, true, "", "计息公式")
				.addColumn("C_DV_PI_MOD", VARCHAR2, 20, true, "", "付息公式")
				.addColumn("D_AI_BEGIN", DATE, true, "", "计息起始日")
				.addColumn("D_AI_END", DATE, true, "", "计息截止日")
				.addColumn("C_ORG_CODE", VARCHAR2, 20, true, "", "机构代码")
				.addColumn("C_ORG_NAME", VARCHAR2, 50, true, "", "出票人")
				.addColumn("C_OPEN_ACC_NAME", VARCHAR2, 50, true, "", "承兑人行名")
				.addColumn("C_OPEN_ACC_NO", VARCHAR2, 50, true, "", "承兑人行号")
				.addColumn("C_SYS_CODE", VARCHAR2, 50, true, "", "大额支付号")
				.addColumn("C_CREDIT_RATING", VARCHAR2, 20, true, "", "信用评级")
				.addColumn("N_RULE_APPLY", NUMBER, 3, 0, true, "1", "申购确认规则")
				.addColumn("N_RULE_RANS", NUMBER, 3, 0, true, "1", "赎回确认规则")
				.addColumn("C_DV_LOCK_RANS", VARCHAR2, 20, true, "", "赎回锁定期")
				.addColumn("N_RAN_DELAY", NUMBER, 3, 0, true, "", "赎回款最大可延迟天数")
				.addColumn("C_DV_DUR_TYPE", VARCHAR2, 20, true, "", "期限分类/期限类型")
				.addColumn("C_DV_INV", VARCHAR2, 20, true, "", "计息方式：累进计息法、余额计息法")
				.addColumn("C_DV_INV_HT", VARCHAR2, 20, true, "", "计息启止方式:记头不计尾、头尾均记")
				.addColumn("C_DV_DA_TYPE", VARCHAR2, 20, true, "", "计息天数调整方式")
				.addColumn("C_DV_RATE_TYPE", VARCHAR2, 20, true, "", "利率品种类型：利率品种、货币基金、理财产品")
				.addColumn("C_ALGO_CODE", VARCHAR2, 20, true, "", "利率确定公式：算数平均、直接引用")
				.addColumn("N_MULTI_PLIER", NUMBER, 18, 4, true, "", "利率乘数")
				.addColumn("N_SPREAD", NUMBER, 30, 15, true, "0", "利差")
				.addColumn("N_RULE_PAY", NUMBER, 3, 0, true, "1", "付息月")
				.addColumn("N_PAY_PLAN", NUMBER, 3, 0, true, "0", "截息日规则")
				.addColumn("N_PAY_DAY", NUMBER, 3, 0, true, "0", "付息计划生成日")
				.addColumn("C_DV_WD_RULE", VARCHAR2, 20, true, "", "付息日")
				.addColumn("C_DESC", VARCHAR2, 20, true, "", "描述")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "审核状态")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addColumn("N_HIDE", NUMBER, 3, 0, true, "0", "是否隐藏")
				.addColumn("C_DV_ISSUE", VARCHAR2, 50, true, "", "发行类型")
				.addColumn("C_DV_ASSURE", VARCHAR2, 50, true, "", "担保方式")
				.addColumn("C_SEC_NAME_CN", VARCHAR2, 50, true, "", "证券中文名称")
				.addColumn("N_RATIO", NUMBER, 30, 15, true, "0", "保证金比例1")
				.addColumn("D_END", DATE, true, "", "期权到期日期")
				.addColumn("C_DV_PAY_UN", VARCHAR2, 20, true, "", "付息月")
				.addColumn("D_SQAI_BEGIN", DATE, true, "", "首期起息日期")
				.addColumn("C_FX_CODE", VARCHAR2, 50, false, "' '", "证券分销代码")
				.addColumn("D_TRA_BEGIN", DATE, true, "", "转换起始日")
				.addColumn("D_TRA_END", DATE, true, "", "转换截止日")
				.addPrimaryConstraint("PK_P_NS_BASE", "C_IDEN")
				.createUniqueIndex("IDX_P_NS_BASE1", "C_SEC_CODE, C_PORT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
			
		columnBuilder.alterTable("T_P_NS_BASE")
					   .addColumn("N_XSFWFL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "销售服务费率")
					   .addColumn("c_tgr_code", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "托管人代码")
					   .addColumn("c_tgr_name", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "托管人名称")
					   .build(UpdateType.REQUEST, "39539 ", "【南方基金】FOF基金系统改造", "liutao_sh", "2017-07-18");
		
		columnBuilder.alterTable("T_P_NS_BASE")
		    .addColumn("n_today_interest", OraColunmnTypeOnlyLength.NUMBER, 3, true, "0", "含付息日利息")
			   .addColumn("n_xsfwfl", OraColumnTypeNeedPrecision.NUMBER, 18,8, true, "0", "销售服务费率")
			   .addColumn("c_tgr_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "托管人代码")
			   .addColumn("c_tgr_name", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "托管人名称")
		   .build(UpdateType.REQUEST, "41687", "【易方达】NJ0002 XT0023", "zhouxudong", "2017-8-4");
		  //add by chenchangyou 20180122  STORY #42102 【鹏华基金】境外债券生成历史付息期间逻辑变更
	     columnBuilder.alterTable("T_P_NS_BASE")
		.modifyColumn("d_sqai_begin",  OraColumnTypeNoLength.DATE, true, "to_timestamp('1900-01-01','YYYY/MM/DD')")
		.build(UpdateType.REQUEST, "42102", "【鹏华基金】境外债券生成历史付息期间逻辑变更", "ccy", "2018-03-27");
	     
	     // STORY #42965 【紧急】买卖货币基金强增强减业务
	     columnBuilder.alterTable("T_P_NS_BASE")
			.addColumn("n_fesx",  OraColumnTypeNeedPrecision.NUMBER,18,4, true, "", "份额上限")
			.addColumn("n_fexx",  OraColumnTypeNeedPrecision.NUMBER,18,4, true, "", "份额下限")
			.build(UpdateType.REQUEST, "42965", "【紧急】买卖货币基金强增强减业务", "zhouxudong", "2017-07-05");
	     
		   //STORY #38100 【招商证券】万份收益计提方式支持计尾不计头 
		   columnBuilder.alterTable("T_P_NS_BASE")
		   .addColumn("C_SYJT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收益计提方式")
		   .build(UpdateType.REQUEST, "38100", "万份收益计提方式支持计尾不计头", "weijingjing", "2019-01-12");
	     
	     //STORY #40222 【2.5升级】【专户】易方达基金：支持分级基金按成本估值
	     columnBuilder.alterTable("T_P_NS_BASE")
	     	.addColumn("C_SEC_VAR_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "证券品种分级")
	     	.build(UpdateType.REQUEST, "40222 ", "【2.5升级】【专户】易方达基金：支持分级基金按成本估值", "xiongdaolin", "2018-05-31");
	     
	   //STORY#53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案
	     columnBuilder.alterTable("T_P_NS_BASE")
	     	.addColumn("C_JZFS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "集资方式")
	     	.build(UpdateType.REQUEST, "53605 ", "STORY#53605 【鹏华基金】债券品种信息投资分类增加“公募”“私募”用以区分不同的估值方案", "HeMing", "2018-07-02");
	     
	     //STORY #62079 人保资产-支持人保资产新科目体系核算
	     columnBuilder.alterTable("T_P_NS_BASE")
	     	.addColumn("C_KZCD_GQ", VARCHAR2, 20, true, "", "控制程度")
	     	.build(UpdateType.REQUEST, "62079", "STORY #62079 人保资产-支持人保资产新科目体系核算", "xiongdaolin", "20181013");

	}
	
	private void buildT_D_MP_HG_MKT() throws Exception {
		tableBuilder.createTable("T_D_MP_HG_MKT","证券回购行情").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("D_MKT", OraColumnTypeNoLength.DATE, true, "to_timestamp('1990-01-01','YYYY/MM/DD')", "行情日期")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, true, "to_timestamp('1990-01-01','YYYY/MM/DD')", "启用日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "to_timestamp('1990-01-01','YYYY/MM/DD')", "停用日期")
				.addColumn("N_DURATION", OraColumnTypeNeedPrecision.NUMBER, 10, 0, true, "", "回购期限")
				.addColumn("N_RATE", OraColumnTypeNeedPrecision.NUMBER, 20, 8, true, "", "回购利率")
				.addColumn("C_BIZ_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "", "业务类型")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "证券代码")
				.addColumn("C_IS_PUBLIC", OraColunmnTypeOnlyLength.VARCHAR2, 1, true, "", "公共 标志")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核人")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'HGJY_NHG'", "交易方式")
				.addPrimaryConstraint("PK_D_MP_HG_MKT", "C_IDEN")
				.createIndex("IDX_D_MP_HG_MKT1", "D_MKT, C_DT_CODE, C_SEC_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","李勇军","20170511");
		
			columnBuilder.alterTable("T_D_MP_HG_MKT")
			   .addColumn("C_DT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'HGJY_NHG'", "交易方式")
			   .build(UpdateType.REQUEST, "35720 ", "股票质押回购融资（卖出回购）业务需求", "huangjin", "2017-08-11");
	}
	
	private void buildT_D_MP_PRE_STOCK() throws Exception {
		tableBuilder.createTable("T_D_MP_PRE_STOCK","优先股信息").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "证券代码")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "交易市场")
				.addColumn("C_SEC_CODE_TRG", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "标的证券")
				.addColumn("C_DV_VAR_DUR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "付息频率")
				.addColumn("C_DV_QUT_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "报价方式")
				.addColumn("N_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "税率")
				.addColumn("N_FV_IR", OraColumnTypeNeedPrecision.NUMBER, 15, 8, true, "", "票面利率")
				.addColumn("C_DV_AI_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "计息方式")
				.addColumn("C_DV_PI_MOD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "付息公式")
				.addColumn("C_DV_AI_EXPR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "计息公式")
				.addColumn("D_AI_BEGIN", OraColumnTypeNoLength.DATE, true, "", "计息起始日")
				.addColumn("D_AI_END", OraColumnTypeNoLength.DATE, true, "", "计息截止日")
				.addColumn("C_DV_ACCOUNT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算类型")
				.addColumn("C_DV_TOTAL_INCOME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'0' ", "累计股息")
				.addColumn("C_DV_ATTEND_PROFIT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'0' ", "参与分红")
				.addColumn("C_DV_EXCHGE_STOCK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'0' ", "转换成普通股")
				.addColumn("C_DV_DELAY_PAY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'0'", "延期支付")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.createIndex("IDX_D_MP_PRE_STOCK0", "C_SEC_CODE")
				.addPrimaryConstraint("PK_D_MP_PRE_STOCK", "C_IDEN")
				.build(UpdateType.REQUEST, "000001", "新建","乔科伟","20170511");
	}
	
	private void buildT_D_MP_INDEX() throws Exception {
		tableBuilder.createTable("T_D_MP_INDEX","指数行情资料").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自动增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券内码")
				.addColumn("D_MKT", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "行情日期")
				.addColumn("C_MKT_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "行情分类")
				.addColumn("C_MKT_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "行情时间")
				.addColumn("N_PRICE_NEW", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "最新价")
				.addColumn("N_PRICE_BUY", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "买入价")
				.addColumn("N_PRICE_SELL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "卖出价")
				.addColumn("N_PRICE_HIGH", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "最高价")
				.addColumn("N_PRICE_LOW", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "最低价")
				.addColumn("N_PRICE_CLOSE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "收盘价")
				.addColumn("N_PRICE_OPEN", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "开盘价")
				.addColumn("N_PRICE_AVG", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "平均价")
				.addColumn("D_PUB", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "公告日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "'H' ", "数据来源")
				.addColumn("C_DV_PLAT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "行情来源")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口代码")
				.addColumn("N_PRICE_SETT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "0", "结算价")
				.addColumn("N_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'HQZT_ZCJY'", "行情状态")
				.addPrimaryConstraint("PK_D_MP_INDEX", "C_SEC_CODE,D_MKT,C_DV_PLAT")
				.addColumn("N_TD_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "成交数量")
        		.addColumn("N_TD_MONEY", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "成交金额")
        		.addColumn("N_PRICE_ZRCLOSE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "0", "昨日收盘价")
				.build(UpdateType.REQUEST, "000001", "新建","马向峰","20170511");
              //20171108_韩某强_STORY#46284
                columnBuilder.alterTable("T_D_MP_INDEX")
                     .addColumn("C_HQZT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'HQZT_ZCJY'", "行情状态")
                     .build(UpdateType.REQUEST, "46284", "新增行情状态字段", "hanmouqiang", "2017-11-08");
	}
	
	private void buildT_D_MP_SEC_MKT() throws Exception {
		tableBuilder.createPartitionTable("T_D_MP_SEC_MKT","证券行情").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "证券内码")
				.addColumn("D_MKT", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "行情日期")
				.addColumn("C_MKT_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "行情分类")
				.addColumn("C_MKT_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "行情时间")
				.addColumn("N_PRICE_NEW", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "最新价")
				.addColumn("N_PRICE_BUY", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "买入价")
				.addColumn("N_PRICE_SELL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "卖出价")
				.addColumn("N_PRICE_HIGH", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "最高价")
				.addColumn("N_PRICE_LOW", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "最低价")
				.addColumn("N_PRICE_CLOSE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "收盘价")
				.addColumn("N_PRICE_OPEN", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "开盘价")
				.addColumn("N_PRICE_AVG", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "平均价")
				.addColumn("D_PUB", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "公告日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "'H'", "数据来源")
				.addColumn("C_DV_PLAT", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "行情来源")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "接口代码")
				.addColumn("N_PRICE_SETT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "结算价")
				.addColumn("C_HQZT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "'HQZT_ZCJY'", "行情状态 ")
				.addColumn("N_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'正常交易'", "交易状态")
				.addColumn("N_TD_AMOUNT", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "成交数量")
				.addColumn("N_TD_MONEY", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "成交金额 ")
				.addColumn("C_ZQ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '", "证券内码2")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合")
                .addColumn("C_MKT_STATUS", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "除权股票的行情状态")
                .addColumn("N_AMOUNT_TOTAL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "0", "总数量")
                .addColumn("N_PRICE_TOTAL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "0", "总金额")
                .addColumn("N_PRICE_ZRCLOSE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "昨日收盘价")
                .addColumn("C_KXD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "可信度")
                .addColumn("N_INCOME_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15, true, "", "收益率")
                .addColumn("C_MKT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'PRICE_ADJ_NONE'", "行情类型")
                .addColumn("D_BASE", OraColumnTypeNoLength.DATE, true, "to_timestamp('1900-01-01','YYYY/MM/DD')", "基准日期")
                //STORY #91504 【富国基金】巡检时发现几张大数据量的表未进行表分区需要优化
				.partitionBy(OraPartitionType.RANGE, "D_MKT")
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
                .addPartintion("PART_MAX", "MAXVALUE")
                .addPrimaryConstraint("PK_D_MP_SEC_MKT", "C_IDEN")
				.createIndex("IDX_D_MP_SEC_MKT","C_MKT_CLS, D_MKT, C_PORT_CODE")
				.createIndex("IDX_D_MP_SEC_MKT1", "D_MKT, C_SEC_CODE, C_CFG_CODE, C_DV_PLAT")
				//BUG #350713 银华基金-估值核算-【300.7-1031-1223】估值性能问题-核算卡
				.createIndex("IDX_D_MP_SEC_MKT2", "C_SEC_CODE, D_MKT")
				.createIndex("IDX_D_MP_SEC_MKT3", "N_CHECK_STATE,C_MKT_CLS")
				//BUG #371457 【友邦保险】【300.7.0.20210430.0601】公共信息处理，万得证券行情无数据，处理前一天才能清算成功
				.createIndex("IDX_D_MP_SEC_MKT4", "D_PUB,C_CFG_CODE")
				.build(UpdateType.REQUEST, "000001", "创建证券行情表","ChenLong","20170511");
		columnBuilder.alterTable("T_D_MP_SEC_MKT")
        .addColumn("C_DV_DCQ", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "待偿期")
        .build(UpdateType.REQUEST, "101294 ", "新增", "章胡彬", "20210531");
	}
	
	private void buildT_D_MP_SEC_FW() throws Exception {
		tableBuilder.createTable("T_D_MP_SEC_FW","远期外汇行情").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("D_MKT", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "行情日期")
				.addColumn("C_MKT_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "行情分类")
				.addColumn("C_DV_VAR_DUR", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "品种期限")
				.addColumn("C_MKT_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "行情时间")
				.addColumn("D_SPOT", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "即期日期")
				.addColumn("D_FW", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "远期日期")
				.addColumn("N_PRICE_BUY", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "买入价")
				.addColumn("N_PRICE_SELL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "卖出价")
				.addColumn("N_POINT_BUY", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "买入点数")
				.addColumn("N_POINT_SELL", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0", "卖出点数")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "数据来源（H-手工 Z-自动）")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("N_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'HQZT_ZCJY'", "行情状态")
				.addPrimaryConstraint("PK_D_MP_SEC_FW", "C_IDEN")
				.createUniqueIndex("IDX_D_MP_SEC_FW", "C_SEC_CODE,D_MKT,C_MKT_CLS,C_DV_VAR_DUR,D_FW")
				.build(UpdateType.REQUEST, "000001", "新建", "王佩旭","20170511");
				//BUG #362799 【海富通基金 版本300.7 20201031.0330】---远期外汇行情界面维护同样的货币，同样的期限，不同的远期日期提示违反唯一约束性条件
	}
	
	private void buildT_P_AB_PORT_SEC() throws Exception {
		tableBuilder.createTable("T_P_AB_PORT_SEC", "证券行情映射").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "流水号")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "组合代码")
				.addColumn("C_PORT_CLS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' ' ", "分级代码")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "指标代码")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_PORT_CODE_MAP", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "映射组合")
				.addColumn("C_DV_MAP_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'MZRR'", "映射频率")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, true, "DATE'1900-1-1'", "启用日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "DATE'9998-12-31'", "结束日期")
				.addColumn("C_PARA_VALUE", OraColumnTypeNoLength.CLOB, true, "", "映射频率参数值")
				.addColumn("C_DV_HDAY_PROCESS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "节假日处理")
				.addColumn("C_DV_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "日期类型")
				.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "节假日群代码")
				.createUniqueIndex("IDX_P_AB_PORT_SEC", "C_PORT_CODE,C_PORT_CODE_MAP,C_SEC_CODE,C_INDEX_CODE,D_BEGIN,D_END")
				.build(UpdateType.REQUEST, "000001", "新建", "肖志龙", "20170511");
		
		columnBuilder.alterTable("T_P_AB_PORT_SEC")
        .addColumn("C_ISENABLE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "1", "是否可用")
        .build(UpdateType.REQUEST, "000011", "新建", "肖志龙", "20170511");
	}
	
	private void buildT_D_MP_SEC_EQU() throws Exception {
		tableBuilder.createTable("T_D_MP_SEC_EQU","证券权益信息表").setModuleCode("SECINFO")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自动增长ID")
				.addColumn("C_DATA_IDF", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "数据来源（H-手动，Z-自动）")
				.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
				.addColumn("C_SEC_CODE_TAG", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "标的证券")
				.addColumn("C_DS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "权益类型")
				.addColumn("N_EQU_RATIO_PT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "税前权益比例")
				.addColumn("N_EQU_RATIO_AT", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "税后权益比例")
				.addColumn("N_PRICE_PLAC", OraColumnTypeNeedPrecision.NUMBER, 30, 15, false, "0 ", "配售价格")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "分红币种")
				.addColumn("C_DV_VAR_DUR", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "锁定期限")
				.addColumn("C_DV_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "发行方式/分红类型")
				.addColumn("C_DTA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "交易属性")
				.addColumn("D_REG", OraColumnTypeNoLength.DATE, true, "", "登记日期")
				.addColumn("D_FINAL", OraColumnTypeNoLength.DATE, true, "", "缴款截止日")
				.addColumn("D_EXR", OraColumnTypeNoLength.DATE, true, "", "除权日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "交易市场")
				.addColumn("C_EQU_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "数据标识(DJ-对价派息；SP-证券送配；LT-证券流通；FX-证券发行)")
				.addColumn("C_SEC_CODE_TMP", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "临时证券代码(保存港股通业务的临时证券代码)")
				.addColumn("C_ZS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "折算类型")
				.addPrimaryConstraint("PK_D_MP_SEC_EQU", "C_IDEN").createIndex("IDX_D_MP_SEC_EQU0", "D_REG,C_EQU_CLS")
				.createIndex("IDX_D_MP_SEC_EQU", "C_SEC_CODE")  
				.createIndex("IDX_D_MP_SEC_EQU2", "C_SEC_CODE_TAG").build(UpdateType.REQUEST, "183824", "新建", "井呵呵", "20171212");
		// added lwx 2017-12-24 STORY #49635 【国泰基金】送股业务优化，支持输入原币本币，数量为0
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("C_JW_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "截位方式")
		.build(UpdateType.REQUEST, "49635", "新建", "lwx", "2017-12-24");
		// added xhq 2018-01-03 STORY #50979 【国泰基金】新增派息和配股支持截尾保留方式
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("N_BLWS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "保留位数小数")
		.build(UpdateType.REQUEST, "50979", "新建", "xhq", "2018-01-03");
				
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("C_DV_SRC", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "来源")
		.build(UpdateType.REQUEST, "48579", "新建", "ldq", "2018-01-09");
		
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("D_ANNC", OraColumnTypeNoLength.DATE,true, "", "公告日期")
		.build(UpdateType.REQUEST, "44930", "新建", "ljp", "2018-01-09");
		
		//合并代码 edit by zouyuan STORY #42890 【QDII】【易方达基金】境外分红接口支持设置税率 支持按ISIN前缀(发行人注册地)设置红利税税率
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("c_sec_isin_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "ISIN前缀")
		.build(UpdateType.REQUEST, "42890", "新建", "zouyuan", "2018-06-06");
		//STORY #67071 彭博分红派息接口默认只提供税前权益比例，与增加私有化对价派息信息   edit by xuhanbing 20190125
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合代码")
		.build(UpdateType.REQUEST, "67071", "新建", "xuhanbing", "2019-01-25");
		indexBuilder.createIndex("IDX_D_MP_SEC_EQU1").onTable("T_D_MP_SEC_EQU")
		.setColumns("C_SEC_CODE, D_EXR, C_DS_CODE")
		.build(UpdateType.BUG, "286350", "BUG286350公司性能测试环境，调度方案执行，SQLID 0bzz1qh3744v8 执行时间很长", "chenyoucai", "20191113");
		//STORY #84666 t_d_mp_sec_equ增加c_cfg_code能区分数据来源是哪个接口 
		columnBuilder.alterTable("T_D_MP_SEC_EQU")
	    .addColumn("C_CFG_CODE_EQU", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口代码")
		.build(UpdateType.REQUEST, "84666", "新建", "xiaomenglei", "2020-02-18");
		
		//STORY81417境外证券送股、配股、换股公司行为处理需求
        columnBuilder.alterTable("T_D_MP_SEC_EQU")
        .addColumn("C_DS_CODE_I", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "送配子类型")
        .build(UpdateType.REQUEST, "81417", "新建", "liangchong", "2020-05-08");
        
    	//STORY #86143 华夏基金-彭博权益信息接口-权益接口相关补充需求
        columnBuilder.alterTable("T_D_MP_SEC_EQU")
        .addColumn("N_RATIO",OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "配正股比")
        .build(UpdateType.REQUEST, "86143", "新建", "weijingjing", "2020-06-08");
        
        columnBuilder.alterTable("T_D_MP_SEC_EQU")
        .addColumn("C_QY_QYSTATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "权益状态")
        .build(UpdateType.REQUEST, "57468", "新建", "wangwentao", "2018-08-15");
	}
	
	private void buildT_D_MP_SEC_TRANSFERPARA() throws Exception{
		tableBuilder.createTable("T_D_MP_SEC_TRANSFERPARA","证券代码转换参数信息").setModuleCode("SECINFO")
				.addColumn("c_iden ", OraColunmnTypeOnlyLength.VARCHAR2, 30, false,"", "自动增长流水号")
				.addColumn("c_item_code",  OraColunmnTypeOnlyLength.VARCHAR2, 30, false,"' '", "规则代码")
				.addColumn("c_item_name",  OraColunmnTypeOnlyLength.VARCHAR2, 200, false,"' '", "规则名称")
				.addColumn("c_item_value ",  OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "应用条件")
				.addColumn("c_logical_judgment", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "逻辑判断")	
				.build(UpdateType.REQUEST, " 38131 " , "新建","袁凯","20170511");
	}
	
	/**
	 * 证券发行方信息
	 * @Title buildT_D_MP_SEC_FXF 
	 * @Description 
	 * @author guohui@ysstech.com
	 * @date 2017年05月26日
	 * @throws Exception
	 */
	private void buildT_D_MP_SEC_FXF() throws Exception {
		tableBuilder
				.createTable("T_D_MP_SEC_FXF", "证券发行方信息")
				.setModuleCode("SECINFO")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "流水号")
		.addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "证券代码")
		.addColumn("D_DATE", OraColumnTypeNoLength.DATE, true, "", "净值日期")
		.addColumn("N_STOCK_COST", OraColumnTypeNeedPrecision.NUMBER, 18, 2, true, "0", "总股本")
		.addColumn("N_VALUE", OraColumnTypeNeedPrecision.NUMBER, 18, 2, true, "0", "总净值")
		.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")	
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
		.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态")
		.addPrimaryConstraint("PK_D_MP_SEC_FXF", "C_IDEN")
		.createUniqueIndex("IDX_D_MP_SEC_FXF", "C_SEC_CODE,D_DATE")
		.createIndex("IDX_D_MP_SEC_FXF1", "C_SEC_CODE")
		.build(UpdateType.REQUEST, "36146", "新建", "guohui@ysstech.com", "2017-05-26");
	}
	
	private void buildR_D_CLR_PARAM() throws Exception {
		tableBuilder.createTempTable("R_D_CLR_PARAM", "组合临时表")
		
				.setModuleCode("CLEAR")
				
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "组合代码")
				
				.createIndex("IDX_D_CLR_PARAM", "C_PORT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "", "2017-05-08");
	}
	
	/**
     * STORY65821富国基金--功能优化--基金基本信息中增加字段
     * @throws Exception
     */
    public void buildT_P_SV_SEC_PARA() throws Exception{
        tableBuilder.createTable("T_P_SV_SEC_PARA","证券关联参数信息表")
        .addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "自动ID")
        .addColumn("C_SEC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "交易证券")
        .addColumn("C_RELA_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "关联类型")
        .addColumn("C_CLS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "分级类型")
        .addColumn("C_LEVEL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "级别类型")
        .addColumn("C_TRANS_CONDITION", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "转出条件")
        .addColumn("N_THRESHOLD", OraColumnTypeNeedPrecision.NUMBER,18,4, true, "0", "阀值")
        .addColumn("C_SEC_CODE_TARGET", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "目标证券")

        .addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER,3,0, false, "0", "审核状态")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "制作人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "制作时间")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
        .addPrimaryConstraint("PK_P_SV_SEC_PARA", "C_IDEN")
        .build(UpdateType.REQUEST, "65821", "STORY65821富国基金--功能优化--基金基本信息中增加字段", "zxp", "20190113");
    }
    
}
