package com.yss.ams.db.upgrade.productinfo.structs.tables;

//import static com.yss.ams.db.upgrade.yssuco.cons.YssucoProductInfo.MODULE_PARAMS;
import static com.yss.ams.db.upgrade.productinfo.structs.ProductInfo.MODULE_PARAMS;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision.NUMBER;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength.DATE;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength.VARCHAR2;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ColumnBuilder;
import com.yss.fast.db.upgrade.support.api.IndexBuilder;
//import com.yss.fast.db.upgrade.support.api.ConstraintBuilder;
//import com.yss.fast.db.upgrade.support.api.IndexBuilder;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;

/**
 * 业务核算系统(参数管理)表结构
 * 处理参数管理相关表
 * By Jinghehe 
 * 2017-5-4 
 * @ClassName UcoParamManageTableDescImpl
 * 
 */
public class ProductInfoManageTableDescImpl extends BaseStructDesc {

	private TableBuilder tableBuilder = null;

	private ColumnBuilder colBuilder = null;
//	private ConstraintBuilder constraintBuilder = null;
	private IndexBuilder indexBuilder = null;

	//private ViewBuilder viewBuilder = null;

	//private ProcedureBuilder procedureBuilder = null;

	@Override
	public void execute() throws Exception { 
		tableBuilder = getTableBuilder();
		colBuilder = getColumnBuilder();
//		constraintBuilder = getConstraintBuilder();
		indexBuilder = getIndexBuilder();
		//viewBuilder = getViewBuilder();
		//procedureBuilder = getProBuilder();
		
		buildT_P_AB_PORT();
		buildT_P_AB_PORT_PD();
		buildT_P_AA_PORT_CLS();
		buildT_P_AB_ASS_TR();
		buildT_P_AB_ASS_TR_SUB();
		//STORY #72829 资产结构新增仅包含“存续期+待发行”的组合  add by yangru 20200529
		buildT_P_AB_ASS_TR_RULE();
		buildT_P_AB_GROUP_RELA();
		buildT_P_AB_GROUP();
		buildT_C_CP_PUB_ACC();
		
		//STORY #85993 产品参数复制从FAST迁移到产品管理组件
		buildT_S_DATA_COPY();
		buildT_S_DATA_COPY_CUSTOM();
		buildT_S_DATA_COPY_CHECK();

		////组合自定义设置
		buildT_P_AB_PORT_CUSTOM();
		//BUG #369477 【主干升级专项】FMS自由凭证查询界面 点击查询报错
		buildT_S_DV_PD_ATTR();
		
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @throws Exception
	 */
	private void buildT_S_DATA_COPY() throws Exception {
		tableBuilder
				.createTable("T_S_DATA_COPY", "数据复制表")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30,
						false, "", "主键ID")
				.addColumn("C_DATA_NAME", OraColunmnTypeOnlyLength.VARCHAR2,
						255, true, "", "数据名称")
				.addColumn("C_DATA_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, true, "", "数据代码")
				.addColumn("C_DATA_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' ' ", "数据上级代码")
				.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 10,
						true, "0", "数据词汇状态")
				.addColumn("C_SERVICE_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						100, true, "", "服务代码")
				.addColumn("N_ORDER", OraColumnTypeNoLength.NUMBER, true, "0",
						"优先级")
				.addColumn("C_DATA_PARA", OraColunmnTypeOnlyLength.VARCHAR2,
						50, true, "", "数据参数")
				.build(UpdateType.REQUEST, "000001", "", "lipeng_cs@yss.com",
						"2017-05-03");
	}
	
	/**
	 * STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @throws Exception
	 */
	private void buildT_S_DATA_COPY_CHECK() throws Exception {
		tableBuilder
				.createTable("T_S_DATA_COPY_CHECK", "数据复制检查表")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 
						30, false, "", "主键ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' '", "组合代码")
				.addColumn("C_DATA_NAME", OraColunmnTypeOnlyLength.VARCHAR2,
						255, true, "", "数据名称")
				.addColumn("C_DATA_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' '", "数据代码")
				.addColumn("C_SOURCE_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' '", "源组合代码")
				.addColumn("C_COPY_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 
						10, true, "'0'", "复制状态，是否复制")
				.addPrimaryConstraint("PK_S_DATA_COPY_CHECK", "C_IDEN")
				.createUniqueIndex("IDX_S_DATA_COPY_CHECK", "C_PORT_CODE,C_SOURCE_CODE,C_DATA_CODE")
				.build(UpdateType.REQUEST, "64614", "富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况", "zhaijiajia@yss.com",
						"2019-02-23");
	}
	
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @throws Exception
	 */
	private void buildT_S_DATA_COPY_CUSTOM() throws Exception {
		tableBuilder
				.createTable("T_S_DATA_COPY_CUSTOM", "自定义数据复制表")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_USER_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						20, false, "' ' ", "用户代码")
				.addColumn("C_DATA_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' ' ", "数据代码")
				.addColumn("C_FUN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						false, "' ' ", "功能代码")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30,
						false, "", "主键ID")
				.addPrimaryConstraint("PK_S_DATA_COPY_CUSTOM", "C_IDEN")
				.createUniqueIndex("IDX_S_DATA_COPY_CUSTOM",
						"C_USER_CODE,C_DATA_CODE,C_FUN_CODE")
				.build(UpdateType.REQUEST, "000001", "", "lipeng_cs@yss.com",
						"2017-05-03");
	}


	/**
	 * 组合基本信息
	 * @Title buildT_P_AB_PORT 
	 * @Description 
	 * @author zhanghualin@ysstech.com
	 * @date 2017年05月12日 15:18:33
	 * @throws Exception
	 */
	private void buildT_P_AB_PORT() throws Exception {
		tableBuilder.createTable("T_P_AB_PORT", "组合基本信息")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_PORT_NAME", VARCHAR2, 200, false, "' '", "组合名称")
				.addColumn("C_DAT_CODE", VARCHAR2, 20, false, "' '", "资产类型")
				.addColumn("C_PORT_NAME_ST", VARCHAR2, 100, true, "", "组合简称")
				.addColumn("C_PORT_NAME_EN", VARCHAR2, 50, true, "", "英文名称")
				.addColumn("C_ASS_CODE", VARCHAR2, 20, false, "' '", "资产代码")
				.addColumn("C_DC_CODE", VARCHAR2, 20, false, "' '", "组合币种")
				.addColumn("D_BUILD", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "成立日期")
				.addColumn("D_CLOSE", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "终止日期")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_PORT_CODE_P", VARCHAR2, 20, true, "", "上级组合")
				.addColumn("C_DV_PORT_CODE", VARCHAR2, 20, false, "' '", "组合级别")
				.addColumn("C_HDAY_CODE", VARCHAR2, 20, false, "' '", "节假日群")
				.addColumn("C_DAT_CLS", VARCHAR2, 20, false, "' '", "资产类别")
				.addColumn("C_DV_PROD_STATE", VARCHAR2, 20, true, "'PS4'", "PS1-计划期、PS2-待发行、PS3-募集期、PS4-存续期、PS5-已到期、PS6-已清算")
				.addColumn("D_CLEAR", DATE, false, "to_date('9998-12-31','yyyy-MM-dd')", "清算日期")
				.addColumn("C_PORT_UNIT", VARCHAR2, 20, true, "", "定向产品单元层组合标识")
				.addColumn("C_BK_RELA_ID", VARCHAR2, 30, true, "' '", "' '")
				.addColumn("C_PROD_RELA_ID", VARCHAR2, 30, true, "' '", "' '")
				.addColumn("C_PATH_TYPE", VARCHAR2, 30, true, "", "路径类型")
				//重调索引定义并增加数据中心按资产代码查询场景byleeyu20191115
				.addPrimaryConstraint("PK_P_AB_PORT", "C_IDEN")
				.createUniqueIndex("IDX_P_AB_PORT", "C_PORT_CODE")
				.createIndex("IDX_P_AB_PORT1", "C_DAT_CODE, C_PORT_NAME, N_CHECK_STATE")
				.createIndex("IDX_T_P_AB_PORT2", "C_PORT_CODE_P")
				.createIndex("IDX_T_P_AB_PORT3", "C_ASS_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
			
			colBuilder.alterTable("T_P_AB_PORT")
			.addColumn("C_ASSETS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "资产种类")
			.build(UpdateType.REQUEST, "41439", "41439,”功能","liyanjun", "2017-8-11");
			
			colBuilder.alterTable("T_P_AB_PORT")
			.addColumn("D_COLSE_ACC", DATE, false, "to_date('9998-12-31','yyyy-MM-dd')", "关账日期")
			.build(UpdateType.REQUEST, "D_COLSE_ACC", "41658,”功能","liyanjun", "2017-8-11");
			//STORY #62897 组合信息中增加境内境外选项
			colBuilder.alterTable("T_P_AB_PORT")
			.addColumn("C_KH_NATURE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "客户性质")
			.build(UpdateType.REQUEST, "62897", "62897,”新建","lzs", "2018-10-12");
			//STORY #59882 支持青岛银行产品到期自动清算
			colBuilder.alterTable("T_P_AB_PORT")
			.addColumn("C_LASTDQDATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上次到期时间")
			.build(UpdateType.REQUEST, "59882", "STORY #59882 支持青岛银行产品到期自动清算","huzhi", "2018-10-09");
			//BUG243168【易方达（香港）】产品基本信息英文名称及证券基本信息名称长度不足
			colBuilder.alterTable("T_P_AB_PORT").modifyColumn("C_PORT_NAME_EN", VARCHAR2, 200, true, "")
			.build(UpdateType.BUG, "243168", "BUG243168【易方达（香港）】产品基本信息英文名称及证券基本信息名称长度不足", "hezhigang", "2019-02-12");
			//STORY #68355 【南方资本】产品基本信息新增字段备案日期
			colBuilder.alterTable("T_P_AB_PORT")
			.addColumn("D_RECORD", DATE, true, "", "备案日期")
			.build(UpdateType.REQUEST, "D_RECORD", "68355,”功能","yangru", "2019-11-07");

	}

	/**
	 * 组合产品属性表
	 * @Title buildT_P_AB_PORT_PD 
	 * @Description 
	 * @author zhanghualin@ysstech.com
	 * @date 2017年05月12日 15:09:07
	 * @throws Exception
	 */
	private void buildT_P_AB_PORT_PD() throws Exception {
		tableBuilder.createTable("T_P_AB_PORT_PD", "组合产品属性表")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "序列号")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_DAT_CODE", VARCHAR2, 30, false, "' '", "资产类型")
				.addColumn("C_OPER_TYPE", VARCHAR2, 20, true, "", "运作类型")
				.addColumn("C_COLLECT_CODE", VARCHAR2, 50, true, "", "募集对象")
				.addColumn("C_CLIENT_TYPE", VARCHAR2, 30, true, "", "客户类型")
				.addColumn("C_INVEST_CODE", VARCHAR2, 50, true, "", "投资对象")
				.addColumn("C_ASSETS_CODE", VARCHAR2, 200, true, "", "资产种类")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_PRODUCT_NUM", VARCHAR2, 100, true, "", "产品编号")
				.addColumn("C_CONTRACT_NAME", VARCHAR2, 100, true, "", "合同名称")
				.addColumn("C_PORT_TYPE", VARCHAR2, 50, true, "", "组合类别")
				.addColumn("C_CTTG_ACCOUNT", VARCHAR2, 80, true, "' '", "次托托管账户")
				.addColumn("C_SHORT_NUM", VARCHAR2, 100, true, "", "产品编号")
				.addColumn("C_ZJHJC", VARCHAR2, 50, true, "", "证监会简称")
				.addColumn("D_RECORD_REVIEW_DATE", DATE, true, "TO_DATE(TO_CHAR(SYSDATE,'YYYY-MM-DD'),'YYYY-MM-DD')", "' '")
				.addColumn("N_ENTRUST", NUMBER, 22, 0, true, "", "' '")
				//STORY #100864 【东证资管】产品属性分类需要加一个披露开始日供报表系统取数 add by syl 20210209
				.addColumn("D_PUBLISH_START", DATE, true, "to_timestamp('1900-01-01','YYYY/MM/DD')", "披露开始日期")
				/**
				 * BUG #186808 产品属性分类新增保存报错
				 * @author maxiangfeng
				 * @date 2018-01-02
				 */
				.addColumn("C_TA_TYPE", VARCHAR2, 20, true, "", "TA类型")
				
				.addPrimaryConstraint("PK_P_AB_PORT_PD", "C_IDEN")
//				.createUniqueIndex("IDX_P_AB_PORT_PD", "C_PORT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
		
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_DAT_MXCODE", VARCHAR2, 30, true, "", "明细资产类型")
				.build(UpdateType.REQUEST, "032566", "新增明细资产类型字段","liutao", "2017-7-20");
		
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_PLAN_TYPE", VARCHAR2, 30, true, "", "计划类别")
				.build(UpdateType.REQUEST, "38001", "南方基金 新建组合：复制创建新组合时，增加“复制产品属性分类信息”功能","liutao", "2017-7-20");
		
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("N_T_DAYS", NUMBER, 3, 0, true, "1", "T+N估值属性")
				.build(UpdateType.REQUEST, "42394", "【易方达】在【产品属性设置】中增加T+N估值 ","zxd", "2017-8-26");
		
		// edit by sunyanlin 2017-09-19 STORY #46343 中信证券-产品信息的产品属性分类中新增组织形式字段并取值
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_ORGANIZATION_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "组织形式")
				.build(UpdateType.REQUEST, "46343", "中信证券-产品信息的产品属性分类中新增组织形式字段并取值","sunyanlin", "2017-09-19");

		indexBuilder.createUniqueIndex("IDX_P_AB_PORT_PD")
				.onTable("T_P_AB_PORT_PD")
				.setColumns("C_PORT_CODE,C_PLAN_TYPE")
				.build(UpdateType.REQUEST, "38001", "南方基金 新建组合：复制创建新组合时，增加“复制产品属性分类信息”功能", "liutao", "2017-7-20");
	

		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_TRUSTOR_TYPE", VARCHAR2, 50, true, "", "计划类别")
				.addColumn("C_FUND_SOURCE", VARCHAR2, 50, true, "", "计划类别")
				.build(UpdateType.REQUEST, "38001", "南方基金 新建组合：复制创建新组合时，增加“复制产品属性分类信息”功能","liutao", "2017-7-20");
		
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_SSPSDXBM", VARCHAR2, 20, true, "", "深市配售对象编码")
				.build(UpdateType.REQUEST, "47258", "新增 ","zhd", "2017-10-23");
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_TA_TYPE", VARCHAR2, 20, true, "", "TA类型")
		.build(UpdateType.REQUEST, "186808", "新增 ","maxiangfeng", "2018-01-04");
		
		colBuilder.alterTable("T_P_AB_PORT_PD")
				.addColumn("C_DV_INVEST_CLS", VARCHAR2, 20, true, "' '", "投资分类")
				.build(UpdateType.REQUEST, "44026", "新增 ","wzh", "2018-1-25");
		//STORY #62897 组合信息中增加境内境外选项
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_KH_NATURE", VARCHAR2, 30, true, "' '", "客户性质")
		.build(UpdateType.REQUEST, "62897", "新增 ","lzs", "2018-10-12");
		//STORY #62312 人保资产-交易所的交易涉及到的共用股东代码数据需要与恒生系统进行关联，有系统进行自动拆分【接口12】
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_COVER_ACCOUNTS", VARCHAR2, 100, true, "' '", "O32套账号")
		.build(UpdateType.REQUEST, "62312", "新增 ","lixingen", "2018-11-20");
		//STORY #63055 人保资产-4.5测试系统中产品建账环节：产品属性分类可以增加辅助信息【核算需求258】
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_KHXZ", VARCHAR2, 20, true, "' '", "客户性质")
			.build(UpdateType.REQUEST, "63055", "新增 ","xiadeqi", "2018-12-03");
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_CPLB", VARCHAR2, 20, true, "' '", "产品类别")
			.build(UpdateType.REQUEST, "63055", "新增 ","xiadeqi", "2018-12-03");
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_CPSX_ZGXG", VARCHAR2, 20, true, "' '", "产品属性_资管新规")
			.build(UpdateType.REQUEST, "63055", "新增 ","xiadeqi", "2018-12-03");
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_CPSX_BJH", VARCHAR2, 20, true, "' '", "产品属性_保监会")
			.build(UpdateType.REQUEST, "63055", "新增 ","xiadeqi", "2018-12-03");
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_ASSOCIATION_CODE", VARCHAR2, 20, true, "' '", "协会代码 ")
			.build(UpdateType.REQUEST, "47356", "新增 "," ", "2018-12-03");
		//STORY #67912 （QDII）收益分配统计明细及汇总报表新增相关参数(估值分支V1.21.6.5) add by 2019-1-25 
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("N_FHBL", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "", "分红比例 ")
			.addColumn("C_SYFPMS", OraColunmnTypeOnlyLength.VARCHAR2, 1000, true, "", "收益分配描述 ")
			.build(UpdateType.REQUEST, "67912", "新增 ","liuqiang", "2019-1-28");
		
		//BUG #249701 银华基金-估值核算-维护产品属性时报错。C_DV_ZHBZ 标识符无效，表中没有该字段 add by zengguowei 2019-3-26
		colBuilder.alterTable("T_P_AB_PORT_PD")
			.addColumn("C_DV_ZHBZ", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合标识")
			.build(UpdateType.BUG, "249701", "新增 ","zengguowei", "2019-3-26");
			
		//add by liuyanxu 2019-6-1 STORY #71829 【歌斐资产】产品基金信息界面资产类型字段新增字典查询；
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_CP_TYPE", VARCHAR2, 20, true, "' '", "产品类别 ")
		.build(UpdateType.REQUEST, "71829", "新增 ","liuyanxu", "2019-6-1");	
		
		//计算方法 edit by baoqiaolin 2020-02-19 STORY #80028 华夏基金年金组合“资金追加提取”业务根据金额法份额法区分核算需求
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_JSFF", VARCHAR2, 50, true, "' '", "计算方法 ")
		.build(UpdateType.REQUEST, "80028", "新增 ","baoqiaolin", "2020-02-19");
		
		//STORY #88593 申万宏源证券-针对托管模式下的过户/大宗过户文件的处理模式
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_SETT_MODE", VARCHAR2, 100, true, "' '", "证券结算模式")
		.build(UpdateType.REQUEST, "88593", "新增 ","yangze", "2020-05-11");
		
		//STORY #95794 AMS国寿资产V4.5-产品属性分类设置模块新增[委托方式]字段
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_ENTRUST_WAY", VARCHAR2, 30, true, "' '", "委托方式")
		.build(UpdateType.REQUEST, "95794", "新增 ","sunqiangwen", "2020-10-19");
		
		//BUG #343436 【南方基金】【0831.1103】产品属性分类，前台缺少做账顺延天数控件
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("N_ACCOUNTING_DAYS", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "做账顺延天数 ")
		.build(UpdateType.BUG, "343436", "新增 ","xiaomenglei", "2020-11-21");
		
		//STORY #96136 【宁波银行】---服务业务情况表中需要估值系统提供部分字段
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_DJBM", VARCHAR2, 50, true, "' '", "登记编码")
		.build(UpdateType.REQUEST, "96136", "新增 ","yanghe", "2020-12-10");
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_FWNR", VARCHAR2, 200, true, "' '", "服务内容")
		.build(UpdateType.REQUEST, "96136", "新增 ","yanghe", "2020-12-10");
		//STORY #50726 委托人类型字段需要支持同时维护多个类型
		colBuilder.alterTable("T_P_AB_PORT_PD").modifyColumn("C_TRUSTOR_TYPE", VARCHAR2, 300, true, "")
		.build(UpdateType.REQUEST, "50726", "STORY #50726 委托人类型字段需要支持同时维护多个类型", "yangguojie", "2021-01-29");
		//STORY #50031 资管产品投资债券市场情况表资金来源字段需支持多选，其取值于4.5V产品属性分类 客户类型字段
		colBuilder.alterTable("T_P_AB_PORT_PD").modifyColumn("C_CLIENT_TYPE", VARCHAR2, 300, true, "")
		.build(UpdateType.REQUEST, "50031", "STORY #50031 资管产品投资债券市场情况表资金来源字段需支持多选，其取值于4.5V产品属性分类 客户类型字段", "yangguojie", "2021-01-29");
		colBuilder.alterTable("T_P_AB_PORT_PD").modifyColumn("N_T_DAYS", OraColumnTypeNeedPrecision.NUMBER,3,0, true, "0")
		.build(UpdateType.BUG, "353627 ", "BUG #353627 【0831_1224】【华夏基金】产品属性分类中默认T+1估值", "neil", "2021-03-02");
		
		//BUG #369477 【主干升级专项】FMS自由凭证查询界面 点击查询报错
		colBuilder.alterTable("T_P_AB_PORT_PD").addColumn("C_BUSPART_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "")
		.build(UpdateType.BUG, "369477 ", "BUG #369477 【主干升级专项】FMS自由凭证查询界面 点击查询报错", "shijian", "2021-06-03");
		
		colBuilder.alterTable("T_P_AB_PORT_PD").addColumn("C_PDLINE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "产品线")
		.build(UpdateType.REQUEST, "33693 ", "STORY #33693 【深国投】产品基本信息缺少组合类型等信息", "liangchong", "2021-06-22");
		
		colBuilder.alterTable("T_P_AB_PORT_PD").addColumn("C_PANDA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "Panda代码")
		.build(UpdateType.REQUEST, "103013 ", "STORY103013【上投摩根】港股通导出DOI文件", "huangkaixuan", "2021-07-06");
		
		colBuilder.alterTable("T_P_AB_PORT_PD").addColumn("C_BDZQ", VARCHAR2, 20, true, "", "标准券代号")
		.build(UpdateType.REQUEST, "95096", "STORY #95096 报价式回购新增导出行情及折扣系数文件接口", "zhuhaihang", "2021-08-10");
		
		//STORY #62385 需求北京-【新华资产】ams估值核算【中】（关于新华资产系统支持将增值税台账表中的相关数据导出至excel给普华部分变动接口）
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_INNER_PRO_CLS", VARCHAR2, 30, true, "' '", "内部产品分类")
		.build(UpdateType.REQUEST, "62385", "新增 ","tms", "2018-10-12");
		
		//STORY #104753 【鹏华基金】视图(vb_port_property)需要与估值系统中产品属性分类模块字段一致 :
		//视图需要用到T_P_AB_PORT_PD表的C_DEPT_NUM、C_ORG_NUM字段，只合并鹏华版本的STORY #71728 新增建账接口需求中的加的字段
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_DEPT_NUM", VARCHAR2, 50, true, "' '", "部门编码")
		.build(UpdateType.REQUEST, "104753", "新增 ","yanghe", "2021-07-30");
		colBuilder.alterTable("T_P_AB_PORT_PD")
		.addColumn("C_ORG_NUM", VARCHAR2, 50, true, "' '", "机构编码")
		.build(UpdateType.REQUEST, "104753", "新增 ","yanghe", "2021-07-30");
	}

	/**
	 * 分级产品设置
	 * @Title buildT_P_AA_PORT_CLS 
	 * @Description 
	 * @author zhanghualin@ysstech.com
	 * @date 2017年05月12日 15:53:25
	 * @throws Exception
	 */
	private void buildT_P_AA_PORT_CLS() throws Exception {
		tableBuilder.createTable("T_P_AA_PORT_CLS", "分级产品设置")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_PORT_CLS_CODE", VARCHAR2, 20, false, "' '", "分级组合")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, false, "' '", "投资组合")
				.addColumn("C_PORT_CLS_NAME", VARCHAR2, 100, false, "' '", "分级名称")
				.addColumn("C_DV_PORT_CLS_TYPE", VARCHAR2, 20, false, "' '", "分级类型")
				.addColumn("C_DV_PORT_CLS_LEVEL", VARCHAR2, 20, false, "' '", "分级级别")
				.addColumn("D_TO_LIST", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "成立日期")
				.addColumn("D_OFF_LIST", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "退市日期")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_PORT_CLS", VARCHAR2, 20, false, "' '", "级别类型")
				.addColumn("C_DC_CODE", VARCHAR2, 20, false, "' '", "分级币种")
				.addColumn("C_ALGO_CODE", VARCHAR2, 20, true, "", "计算公式")
				.addColumn("C_PORT_CLS_CODE_P", VARCHAR2, 20, true, "", "上级分级组合")
				.addColumn("C_DV_NETTING", VARCHAR2, 20, true, "", "轧差")
				.addColumn("C_INC_DIS", VARCHAR2, 20, true, "", "收益分配")
				.addColumn("C_DV_INC_DIS", VARCHAR2, 20, true, "", "收益分配")
				.addColumn("N_YEAR_INCOME", NUMBER, 30, 15, true, "", "年化收益率")
				.addColumn("C_ALGO_CODE_I", VARCHAR2, 20, true, "", "收益率公式")
				.addColumn("C_INCOME_TYPE", VARCHAR2, 20, true, "", "收益率类型")
				.addColumn("C_FORMULA_CODE", VARCHAR2, 20, true, "", "公式代码")
				.addColumn("D_LIQUID_DATE", DATE, true, "", "清算日期")
				.addColumn("C_PATY_TYPE", VARCHAR2, 20, true, "", "' '")
				.addPrimaryConstraint("PK_P_AA_PORT_CLS", "C_IDEN")
				.createIndex("IDX_P_AA_PORT_CLS", "C_PORT_CODE,D_TO_LIST,D_OFF_LIST")
				.createUniqueIndex("IDX_P_AA_PORT_CLS0", "C_PORT_CLS_CODE, C_PORT_CODE, D_TO_LIST, D_OFF_LIST")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
		
		colBuilder.alterTable("T_P_AA_PORT_CLS")
				.addColumn("C_CFG_CODE", VARCHAR2, 20, true, "", "接口代码")
				.addColumn("C_DATA_IDF", VARCHAR2, 20, true, "", "数据来源")
				.build(UpdateType.REQUEST, "39626", "新增接口代码、数据来源字段","qinxinglin@ysstech.com", "2017-06-20");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		        .addColumn("C_DV_LEVELNETTING", VARCHAR2, 20, true, "", "级别轧差")
		        .build(UpdateType.REQUEST, "57289", "【汇添富公募升级】货币多级产品收益结转算法（费率结构相同的级别合并计算）","xiahongqi@ysstech.com","2018-06-12");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("c_fejz_param", VARCHAR2, 20, true, "", "收益转份额")
		.addColumn("c_fejz_month", VARCHAR2, 20, true, "", "收益转份额月份")
		.addColumn("n_fejz_day", NUMBER, 3, 0, true, "", "收益转份额天数")
		.build(UpdateType.REQUEST, "60354", "STORY60354富国基金-【公募】货币产品参数收益转份额支持设置到分级产品","wangtao_sh@ysstech.com", "2018-08-22");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("C_XYPJ", VARCHAR2, 20, true, "' '", "信用评级")
		.build(UpdateType.REQUEST, "51721", "STORY #51721 光大证券-监管类信息完善","lujianhao", "2018-07-12");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("C_DV_QHGS", VARCHAR2, 20, true, "", "期货公司")
		.build(UpdateType.REQUEST, "101435", "STORY #101435 博时基金-分级基金期货损益结转至不同class并准确计算其单位净值的需求 (#2 #1 )","hanmouqiang@ysstech.com", "2021-04-27");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("C_FJBM", VARCHAR2, 20, true, "", "分级编码")
		.build(UpdateType.REQUEST, "101435", "STORY #104365 【招银理财】分级产品损益结转尾差分配设置","lizhenyu@ysstech.com", "2021-07-01");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("C_XSDX", VARCHAR2, 20, true, "", "销售对象")
		.build(UpdateType.REQUEST, "109352", "STORY #109352 【东莞银行理财】分级产品参数新增字段销售对象","pengyaao@ysstech.com", "2021-08-17");
		colBuilder.alterTable("T_P_AA_PORT_CLS")
		.addColumn("C_TYKHLX", VARCHAR2, 20, true, "", "同业客户类型")
		.build(UpdateType.REQUEST, "109352", "STORY #109352 【东莞银行理财】分级产品参数新增字段销售对象","pengyaao@ysstech.com", "2021-08-17");
	}

	/**
	 * 
	 * @Title buildT_P_AB_ASS_TR 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:00:22
	 * @throws Exception
	 */
	private void buildT_P_AB_ASS_TR() throws Exception {
		tableBuilder.createTable("T_P_AB_ASS_TR", "资产树形结构")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_TR_CODE", VARCHAR2, 20, false, "' '", "结构代码")
				.addColumn("C_TR_NAME", VARCHAR2, 50, false, "' '", "结构名称")
				.addColumn("C_TR_CODE_P", VARCHAR2, 20, false, "' '", "上级结构")
				.addColumn("C_DV_TR", VARCHAR2, 20, false, "' '", "分类规则")
				.addColumn("C_TR_CODE_R", VARCHAR2, 20, false, "' '", "根节点代码")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_AB_ASS_TR", "C_IDEN")
				.createUniqueIndex("IDX_P_AB_ASS_TR", "C_TR_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
		
		colBuilder.alterTable("T_P_AB_ASS_TR")
		.addColumn("C_USER",OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "用户")
		.build(UpdateType.REQUEST, "39490", "【南方基金】产品树形结构实现私有层面设置，并且针对私有层面的不用审核,”功能","liulei", "2017-9-21");
		colBuilder.alterTable("T_P_AB_ASS_TR")
		.addColumn("C_DV_UN_DIS",OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "'0'", "显示未分配")
		.addColumn("N_ORDER", OraColunmnTypeOnlyLength.NUMBER, 30, false, "0", "执行顺序")
		.build(UpdateType.REQUEST, "49928", "STORY49928产品树形结构界面优化,”功能","zhoushuhang@ysstech.com", "2018-03-05");
		colBuilder.alterTable("T_P_AB_ASS_TR")
		.addColumn("C_AUTO_ZR_TYPE",OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "自动转入类型")
		.build(UpdateType.REQUEST, "316612", "BUG #316612 【长江养老】V1.300.7.0.20200430产品树形结构中产品类型跟老版本不一致，需求STORY #72829也未合并","yangru", "2020-5-29");
		
	}

	/**
	 * 资产树形结构
	 * @Title buildT_P_AB_ASS_TR_SUB 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:06:27
	 * @throws Exception
	 */
	private void buildT_P_AB_ASS_TR_SUB() throws Exception {
		tableBuilder.createTable("T_P_AB_ASS_TR_SUB", "资产树形结构")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_TR_CODE", VARCHAR2, 20, false, "' '", "结构代码")
				.addColumn("C_TR_CODE_R", VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, false, "' '", "根节点代码")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_AB_ASS_TR_SUB", "C_IDEN")
				.createUniqueIndex("IDX_P_AB_ASS_TR_SUB", "C_PORT_CODE, C_TR_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
		colBuilder.alterTable("T_P_AB_ASS_TR_SUB")
		.addColumn("C_AUTO_TYPE",OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "自动转入类型")
		.build(UpdateType.REQUEST, "316612", "BUG #316612 【长江养老】V1.300.7.0.20200430产品树形结构中产品类型跟老版本不一致，需求STORY #72829也未合并","yangru", "2020-5-29");
	}
	
	/**
	 * 产品树形设置分类规则
	 * @Title buildT_P_AB_ASS_TR_SUB 
	 * @Description 
	 * @author yangru
	 * @date20200529
	 * @throws Exception
	 */
	private void buildT_P_AB_ASS_TR_RULE() throws Exception {
		tableBuilder.createTable("T_P_AB_ASS_TR_RULE", "资产树形规则")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_IDEN_RELA", VARCHAR2, 20, true, "' '", "关联id")
				.addColumn("C_CPSJWD", VARCHAR2, 200, true, "' '", "产品时间维度")
				.addColumn("C_CPSJWD_FLCJ", VARCHAR2,1, true, "' '", "产品时间维度层级")
				.addColumn("C_ZCSXWD", VARCHAR2, 1000, true, "' '", "产品属性维度")
				.addColumn("C_ZCSXWD_FLCJ", VARCHAR2, 1, true, "' '", "产品属性维度层级")
				.addPrimaryConstraint("PK_P_AB_ASS_TR_RULE", "C_IDEN")
				.build(UpdateType.REQUEST, "316612", "新建", "yangru@ysstech.com", "2020-05-29");
	}

	/**
	 * 
	 * @Title buildT_P_AB_GROUP_RELA 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:18:23
	 * @throws Exception
	 */
	private void buildT_P_AB_GROUP_RELA() throws Exception {
		tableBuilder.createTable("T_P_AB_GROUP_RELA", "群组关联信息")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动主键索引")
				.addColumn("C_GROUP_CODE", VARCHAR2, 20, false, "' '", "群组代码")
				.addColumn("C_PORT_CODE", VARCHAR2, 20, true, "", "组合代码")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, true, "", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, true, "", "更新时间")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_AB_GROUP_RELA", "C_IDEN")
//				.createUniqueIndex("IDX_P_AB_GROUP_RELA", "C_GROUP_CODE, C_PORT_CODE")
				//STORY #85223 【南方基金】【0123】需要支持产品关联多个群组的场景-去掉唯一性，通过前台控制
//				.createIndex("IDX_P_AB_GROUP_RELA", "C_GROUP_CODE, C_PORT_CODE")
				//BUG #285493 byleeyu20191102添加索引,V_P_AO_PARAMS视图以C_PORT_CODE查询
				.createIndex("IDX_P_AB_GROUP_RELA1", "C_PORT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
		//BUG #357979 【估值核算】【富国基金】【300.7-1031.0131_FGJJ】上交所过户库清算流水成交数量错误				
		indexBuilder.createUniqueIndex("IDX_P_AB_GROUP_RELA")
		.onTable("T_P_AB_GROUP_RELA")
		.setColumns("C_GROUP_CODE, C_PORT_CODE")
		.build(UpdateType.BUG, "357979 ", "富国基金 上交所过户库清算流水成交数量错误", "wangchao", "2021-4-12");				
	}

	/**
	 * 
	 * @Title buildT_P_AB_GROUP 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:16:47
	 * @throws Exception
	 */
	private void buildT_P_AB_GROUP() throws Exception {
		tableBuilder.createTable("T_P_AB_GROUP", "群组基本信息")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动主键索引")
				.addColumn("C_GROUP_CODE", VARCHAR2, 20, false, "' '", "群组代码")
				.addColumn("C_GROUP_NAME", VARCHAR2, 50, false, "' '", "群组名称")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, true, "", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, true, "", "更新时间")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_AB_GROUP", "C_IDEN")
				.createUniqueIndex("IDX_P_AB_GROUP", "C_GROUP_CODE, N_CHECK_STATE")
				.build(UpdateType.REQUEST, "000001", "新建", "wangguoliang@ysstech.com", "2017-05-12");
	}

	/**
	 * 
	 * @Title buildT_C_CP_FUND_ACC 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:16:47
	 * @throws Exception
	 */
	/*private void buildT_C_CP_FUND_ACC() throws Exception {
		tableBuilder.createTable("T_C_CP_FUND_ACC","产品账户信息")
			   .addColumn("c_iden", VARCHAR2, 30, false, "","自动主键索引")
		       .addColumn("c_port_code", VARCHAR2, 20, true, "","组合代码")
		       .addColumn("c_ca_code", VARCHAR2, 50, true, "","账户代码")
		       .addColumn("c_dc_code", VARCHAR2, 20, true, "","货币代码")
		       .addColumn("c_open_addr", VARCHAR2, 100, false, "' '","开户地址")
		       .addColumn("c_open_acc_no", VARCHAR2, 100, false, "' '","开户账号")
		       .addColumn("c_sys_code", VARCHAR2, 50, true, "","支付系统号")
		       .addColumn("c_usage", VARCHAR2, 200, true, "","划款用途")
		       .addColumn("c_desc", VARCHAR2, 200, true, "","划款备注")
		       .addColumn("n_check_state", NUMBER, 3,0, false, "0","审核状态")
		       .addColumn("c_update_by", VARCHAR2, 20, false, "' '","更新人")
		       .addColumn("c_update_time", VARCHAR2, 20, false, "' '","更新时间")
		       .addColumn("c_check_by", VARCHAR2, 20, true, "","审核人")
		       .addColumn("c_check_time", VARCHAR2, 20, true, "","审核时间")
		       .addColumn("c_open_acc_name", VARCHAR2, 100, false, "' '","开户名称")
		       .addColumn("d_begin", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')","开户日期")
		       .addColumn("d_end", DATE, false , "to_timestamp('1900-01-01','YYYY/MM/DD')","关户日期")
		       .addColumn("c_org_code", VARCHAR2, 20, true, "","机构代码/所有人")
		       .addColumn("c_holder", VARCHAR2, 200, true, "","所有人 ")
		       .addColumn("c_ass_code", VARCHAR2, 20, true, "","支付系统号")
		       .addColumn("c_haveused", VARCHAR2, 20, true, "'0'","账号是否是已使用")
		       .addColumn("c_account_type", VARCHAR2, 20, true, "","账户类型")
		       .addColumn("c_inter_org_code", VARCHAR2, 20, true, "","")
		       .addColumn("c_pay_code", VARCHAR2, 50, true, "","")
				.addPrimaryConstraint("PK_C_CP_FUND_ACC", "C_IDEN")
				.createUniqueIndex("IDX_C_CP_FUND_ACC", "C_PORT_CODE, C_DC_CODE, C_OPEN_ACC_NO, C_CA_CODE")
				.createUniqueIndex("IDX_C_CP_FUND_ACC_01", "C_OPEN_ACC_NO, C_OPEN_ADDR, C_OPEN_ACC_NAME")
				.build(UpdateType.REQUEST, "000001", "创建", "", "");
	}*/

	/**
	 * 
	 * @Title buildT_C_CP_PUB_ACC 
	 * @Description 
	 * @author wangguoliang@ysstech.com
	 * @date 2017年05月12日 16:16:47
	 * @throws Exception
	 */
	private void buildT_C_CP_PUB_ACC() throws Exception {
		tableBuilder.createTable("T_C_CP_PUB_ACC","公共账户信息")
				.addColumn("c_iden", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自动ID")
				.addColumn("c_dv_oppo_rela", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "' '")
				.addColumn("c_dc_code", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "币种代码")
				.addColumn("c_open_addr", OraColunmnTypeOnlyLength.VARCHAR2,100, false, "' '", "开户地址")
				.addColumn("c_open_acc_no", OraColunmnTypeOnlyLength.VARCHAR2,100, false, "' '", "开户账号")
				.addColumn("c_sys_code", OraColunmnTypeOnlyLength.VARCHAR2,50, true, "", "支付系统号")
				.addColumn("c_usage", OraColunmnTypeOnlyLength.VARCHAR2,200, true, "", "划款用途")
				.addColumn("c_desc", OraColunmnTypeOnlyLength.VARCHAR2,200, true, "", "划款备注")
				.addColumn("n_check_state", OraColumnTypeNeedPrecision.NUMBER, 3,0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("c_update_by", OraColunmnTypeOnlyLength.VARCHAR2,20, false, "' '", "更新人")
				.addColumn("c_update_time", OraColunmnTypeOnlyLength.VARCHAR2,20, false, "' '", "更新时间")
				.addColumn("c_check_by", OraColunmnTypeOnlyLength.VARCHAR2,20, true, "", "审核人")
				.addColumn("c_check_time", OraColunmnTypeOnlyLength.VARCHAR2,20, true, "", "审核时间")
				.addColumn("c_open_acc_name", OraColunmnTypeOnlyLength.VARCHAR2,100, false, "' '", "开户名称")
				.addPrimaryConstraint("PK_C_CP_PUB_ACC", "C_IDEN")
				.createUniqueIndex("IDX_C_CP_PUB_ACC", "C_DC_CODE, C_OPEN_ACC_NO, C_OPEN_ACC_NAME")
				.build(UpdateType.REQUEST, "000001", "创建", "", "");
	}


	/**
	 * By Jinghehe 2017-8-16 
	 * @throws Exception
	 */
	private void buildT_P_AB_PORT_CUSTOM()  throws Exception {
		tableBuilder.createTable("T_P_AB_PORT_CUSTOM", "组合自定义设置")
		.setModuleCode(MODULE_PARAMS)
		.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
		.addColumn("C_PORT_CODE", VARCHAR2, 20, false, "' '", "组合代码")
		.addColumn("c_user_code", VARCHAR2, 20, false, "' '", "用户代码")
		.addColumn("c_post_code", VARCHAR2, 20, false, "' '", "岗位代码")
		.addColumn("c_fun_code", VARCHAR2, 50, false, "' '", "功能代码")
		.addColumn("c_tr_code_r", VARCHAR2, 20, false, "'ASS'", "根节点代码")
		 
		.addPrimaryConstraint("PK_P_AB_PORT_CUSTOM", "C_IDEN")
		.createUniqueIndex("IDX_P_AB_PORT_CUSTOM", "C_USER_CODE, C_PORT_CODE, C_POST_CODE, C_FUN_CODE, C_TR_CODE_R")
		.build(UpdateType.REQUEST, "000001", "新建", "jinghehe@ysstech.com", "2017-08-16");

	}
	
	/**
	 * BUG #369477 【主干升级专项】FMS自由凭证查询界面 点击查询报错
	 * @throws Exception
	 */
	private void buildT_S_DV_PD_ATTR() throws Exception {
		tableBuilder
				.createTable("T_S_DV_PD_ATTR", "产品附加属性表")
				.setModuleCode(MODULE_PARAMS)
				.addColumn("C_PD_ATTR_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						20, false, "", "")
				.addColumn("C_PD_ATTR_NAME", OraColunmnTypeOnlyLength.VARCHAR2,
						200, false, "", "")
				.addColumn("C_PD_ATTR_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						false, "", "")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200,
						true, "", "")
				.addColumn("N_ORDER", OraColunmnTypeOnlyLength.NUMBER, 3,
						false, "", "")
				.build(UpdateType.BUG, "369477", "", "shijian",
						"2021-06-03");
	}
}
