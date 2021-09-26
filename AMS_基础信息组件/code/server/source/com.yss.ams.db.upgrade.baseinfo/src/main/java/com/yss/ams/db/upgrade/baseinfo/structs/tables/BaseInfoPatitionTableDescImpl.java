/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2017年7月8日杨海茗
**********************************************************************/

package com.yss.ams.db.upgrade.baseinfo.structs.tables;

import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision.NUMBER;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength.DATE;
import static com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength.VARCHAR2;

import com.yss.ams.db.upgrade.baseinfo.structs.Baseinfo;
import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.ColumnBuilder;
import com.yss.fast.db.upgrade.support.api.IndexBuilder;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.api.ViewBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;

/**
 * @ClassName BaseInfoPatitionTableDescImpl
 * @Description
 * @author yhm
 * @CreateDate 2017-5-8
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class BaseInfoPatitionTableDescImpl extends BaseStructDesc {

	private TableBuilder tableBuilder = null;
	private ColumnBuilder columnBuilder  = null;
	private IndexBuilder indexBuilder = null;
	private ViewBuilder viewBuilder = null;

	@Override
	public void execute() throws Exception {
		tableBuilder = getTableBuilder();
		columnBuilder = getColumnBuilder();
		indexBuilder = getIndexBuilder();
		viewBuilder = getViewBuilder();
		buildT_P_BI_ORG();
		buildT_P_BI_PLATE();
		buildT_P_BI_AREA();
		buildT_P_BI_MKT();
		buildT_P_BI_HDAY();
		buildT_P_BI_HDAY_SUB();
		buildT_P_BI_IE();
		buildT_P_BI_IE_RELA();
		buildT_P_BI_SRC_SIGN();
		buildT_P_BI_SALES_NET();
		buildT_P_BI_CURY_PAIR();
		buildT_P_BI_ORG_MBR();
		buildT_S_ITEM_PARA_RELA();
		buildT_S_DC_CURY();
		buildT_S_DA_SEC_VAR();
		buildT_S_DAT_ASS_TYPE();
		buildT_S_DT_TD_MODE();
		buildT_S_DTA_TD_ATTR();
		buildT_S_DAI_ITEM();
		buildT_S_DAE_ELEM();
		buildT_S_DAE_ELEM_DETAIL();
		buildT_S_DF_FEE_RELA();
		buildT_S_DVA_ITEM();
		buildT_S_DSP_PARA();
		buildT_S_DE_EXP();
		buildT_S_FIN_ITEM();
		buildT_S_IE_ITEM();
		buildT_S_MKT_VAR();
		buildT_S_INDEX();
		buildT_S_INDEX_PARA_RELA();
		buildT_S_CL_INDEX();
		buildT_S_QH_TRANS();
		buildT_S_SEC_TRANS();
		buildT_S_DV_TD_ITEM();
		buildT_S_MKT_TRANS();
		buildT_S_CASH_FLOW();
		buildT_S_CO_TD_SWITCH();
		buildT_S_DZ_TYPE();
		buildT_S_INF_VAR();
		buildT_P_BI_FEE();
		buildT_P_AB_BUSINESS_RANGE();
		
		//账户模块
		buildT_C_CP_FUND_ACC();
		//账户表  兼容新增
		buildT_P_BI_FUND_ACC();
		buildT_P_AB_PORT_ACC_RELA();
		buildT_S_DAC_TYPE();
		buildT_CP_FUNDTYPE_RELA();
		/**
		 * BUG #183314 【空库首次启动系统】执行升级组件报错  by liuyi
		 * 因为此表在baseinfo升级时有用到，但此表还未创建，所以移至baseinfo升级组件中创建
		 */
		buildT_P_AB_PORT_RELA();
		
		//区域信息表
		buildT_S_DC_ADDRESS();
		//chenbo 20170822 TASK #332232
		buildR_D_FEE_ID();
		/// By Jinghehe 2017-10-12 挪到基础组件里面，因为基础组合结构和产品组件都有关联这张表进行查询操作
		buildT_V_D_GROUP_DETAIL();
		buildT_V_D_GROUP();
  	    //add by zhoushuhang 2017-12-12 STORY #44767 2017-5-19-关于就《临时停市债券质押式回购业务结算暂行办法（征求意见稿）》公开征求意见的通知
  	    buildT_S_SUSPEND_RELA();
  	    //账户树形结构及关联表   STORY #62009 【歌斐资产】新增支付账户信息群组界面的树形结构管理
  	    buildT_P_BI_ACC_TREE();
  	    buildT_P_AB_ACC_TREE_RELA();
  	    buildT_S_PARA();
  	    //STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
		buildT_P_BI_ORG_LINK_RELA();
		//STORY #82160 【华宝基金】产品业务范围增加维护界面
		buildT_P_AB_BUSINESS_TYPE();
		//BUG #343629 职业年金只用到基础组件和产品基本信息组件启动时报错
        buildT_S_DV_VOC_UCO();
        buildT_P_AB_TD_CHAN();
        //STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
  		buildT_P_AUTOMATIC_SET_TYPE();
  		//STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
  		buildT_P_AUTOMATIC_SET();
  		//STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
  		buildT_P_AUTOMATIC_SET_PATH();
  	    //STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
  		buildT_P_AUTOMATIC_SET_INTER_TYPE();
	}	
	
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * @throws Exception   
	 */
	private void buildT_P_AUTOMATIC_SET_INTER_TYPE() throws Exception {
		tableBuilder.createTable("T_P_AUTOMATIC_SET_INTER_TYPE", "自动化业务分类接口关系表")
		.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 6, 0, false, "0", "序号")
		.addColumn("C_PRODUCT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "产品业务分类")
		.addColumn("C_INTERFACE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "接口代码")
		.addColumn("C_INTERFACE_P_ID", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "接口父级Id")
		.addColumn("C_INTERFACE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "接口名称")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新时间")
		.noRecycle()
		.build(UpdateType.REQUEST, "106083", "新增", "zhuziqing", "2021-06-03");
	}

	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
     * STORY #106974 【海富通】【自动化业务设置】新增【估值指标】分页 （关联需求STORY #106396 ）
	 * @throws Exception 
	 */
	private void buildT_P_AUTOMATIC_SET_PATH() throws Exception {
		tableBuilder.createTable("T_P_AUTOMATIC_SET_PATH", "自动化业务组合路径设置表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "序号")
		.addColumn("C_CHANEL_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "渠道明细代码")
		.addColumn("C_CHANEL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "渠道明细类型")
		.addColumn("C_PRODUCT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "产品业务代码")
		.addColumn("C_PRODUCT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "产品业务分类")
		.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "对应的组合代码")
		.addColumn("C_INTERFACE_GROUP", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "接口分组")
		.addColumn("C_INTERFACE_P_ID", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "分组id")
		.addColumn("C_INTERFACE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "接口代码")
		.addColumn("C_INTERFACE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "接口名称")
		.addColumn("C_INTERFACE_PATH", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "接口对应的路径")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "制作时间")
		.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "审核时间")
		.noRecycle()
		.addPrimaryConstraint("PK_P_AUTOMATIC_SET_PATH", "C_IDEN")
		.build(UpdateType.REQUEST, "106083", "新增", "zhuziqing", "2021-05-28");
		
		this.getColumnBuilder().alterTable("T_P_AUTOMATIC_SET_PATH")
		.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "指标代码")
		.addColumn("C_VA_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "所属估值表日期")
		.addColumn("C_BUSINESS_TYPE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "业务类型代码")
		.build(UpdateType.REQUEST, "106974", "STORY106974新增【估值指标】分页", "zhuziqing", "2021-07-01");
	}

	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @throws Exception
	 */
	private void buildT_P_AUTOMATIC_SET_TYPE() throws Exception {
		tableBuilder.createTable("T_P_AUTOMATIC_SET_TYPE", "自动化业务设置类型表")
		.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "序号")
		.addColumn("C_BUSINESS_TYPE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "业务类型代码")
		.addColumn("C_BUSINESS_TYPE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "业务类型名称")
		.addColumn("C_BUSINESS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "明细类型代码")
		.addColumn("C_BUSINESS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "明细类型名称")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新时间")
		.build(UpdateType.REQUEST, "90952", "新增", "yangze", "2020-12-24");
	}
	
	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @throws Exception
	 */
	private void buildT_P_AUTOMATIC_SET() throws Exception {
		tableBuilder.createTable("T_P_AUTOMATIC_SET", "自动化业务设置表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "序号")
		.addColumn("C_BUSINESS_TYPE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "业务类型代码")
		.addColumn("C_BUSINESS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "明细类型代码")
		.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
		.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "审核时间")
		.addPrimaryConstraint("PK_P_AUTOMATIC_SET", "C_IDEN")
		.createUniqueIndex("IDX_P_AUTOMATIC_SET", "C_BUSINESS_TYPE_CODE, C_BUSINESS_CODE, C_PORT_CODE")
		.build(UpdateType.REQUEST, "90952", "新增", "yangze", "2020-12-24");
	}
	
	/**
	 * 证券交易渠道设置
	 * @Title buildT_P_AB_TD_CHAN 
	 * @Description 
	 * @author zhanghualin@ysstech.com
	 * @date 2017年05月12日 14:48:27
	 * @throws Exception
	 */
	private void buildT_P_AB_TD_CHAN() throws Exception {
		tableBuilder.createTable("T_P_AB_TD_CHAN", "证券交易渠道设置")
				.setModuleCode("PARAMS")
				.addColumn("C_IDEN", VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_TD_CHAN_NAME", VARCHAR2, 100, false, "' '", "交易渠道名称")
				.addColumn("C_DV_CHAN_TYPE", VARCHAR2, 20, false, "' '", "渠道类型")
				.addColumn("C_TD_CHAN_CODE", VARCHAR2, 20, false, "' '", "交易渠道代码")
				.addColumn("C_ORG_CODE", VARCHAR2, 20, false, "' '", "所属机构")
				.addColumn("C_MKT_CODE", VARCHAR2, 20, false, "' '", "交易市场")
				.addColumn("C_DESC", VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_CA_CODE", VARCHAR2, 20, true, "", "资金账号")
				.addPrimaryConstraint("PK_P_AB_TD_CHAN", "C_IDEN")
				.createUniqueIndex("IDX_P_AB_TD_CHAN0", "C_TD_CHAN_CODE, C_MKT_CODE , C_ORG_CODE")
				.createIndex("IDX_P_AB_TD_CHAN", "C_ORG_CODE, N_CHECK_STATE")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
		
		this.getColumnBuilder().alterTable("T_P_AB_TD_CHAN")
		.addColumn("C_DV_SEAT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易单元类型")
		.addColumn("C_DV_WHE_APPOINT", OraColunmnTypeOnlyLength.VARCHAR2, 2, true, "", "是否指定")
		.build(UpdateType.REQUEST, "49767", "STORY49767迁移交易渠道服务", "zhaijiajia", "20171206");
		//this.getColumnBuilder().alterTable("T_P_AB_TD_CHAN")
		//.addColumn("D_OPEN_DATE", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "租用日期")
		//.addColumn("D_OUT_DATE", DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "退租日期")
		//.build(UpdateType.REQUEST, "53810", "STORY #53810 【鹏华基金】产品关联信息证券交易席位，增加两个时间日期，租用日期、退租日期，用于XBRL取数", "wulei", "20190108");
		this.getColumnBuilder().alterTable("T_P_AB_TD_CHAN")
		.addColumn("D_START", DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "租用日期")
		.addColumn("D_END", DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "退租日期")
		.build(UpdateType.REQUEST, "53810", "STORY #53810 【鹏华基金】产品关联信息证券交易席位，增加两个时间日期，租用日期、退租日期，用于XBRL取数", "zuomingke", "20190712");
		//STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求 edit by liliang 20191205
		this.getColumnBuilder().alterTable("T_P_AB_TD_CHAN")
			.addColumn("C_BIC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "BIC CODE")
			.build(UpdateType.REQUEST, "82297", "STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求", "liliang", "20191205");			
		//STORY #82427【政策需求】-银行间单市场债券ETF（产品端）：组要维护机构21位代码,现调整30位
		this.getColumnBuilder().alterTable("T_P_AB_TD_CHAN")
		.modifyColumn("C_TD_CHAN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '")	
		.build(UpdateType.REQUEST, "82427", "STORY #82427【政策需求】-银行间单市场债券ETF（产品端）：组要维护机构21位代码 ", "guoyuhao", "2020-03-10");
	}

	
	/**
     * BUG #290197 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志
     * STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志 
     * add  by  sunyanlin  20191128
     * @throws Exception
     */
    private void buildT_S_DV_VOC_UCO() throws Exception {
        tableBuilder.createTable("T_S_DV_VOC_UCO","估值数据字典")
            .addColumn("C_DV_CODE",OraColunmnTypeOnlyLength.VARCHAR2,50,false,"' ' ", "词汇代码")
            .addColumn("C_DV_NAME",OraColunmnTypeOnlyLength.VARCHAR2,200,false,"' ' ", "词汇名称")
            .addColumn("C_DV_TYPE",OraColunmnTypeOnlyLength.VARCHAR2,30,false,"' ' ", "词汇类型")
            .addColumn("C_DESC",OraColunmnTypeOnlyLength.VARCHAR2,200,true,"", "备注，格式如公司行为—证券发行—发行地点")
            .addColumn("N_ORDER",OraColumnTypeNeedPrecision.NUMBER,3,0,false,"0 ", "词汇编号")
            .addColumn("C_DV_STATE",OraColunmnTypeOnlyLength.VARCHAR2,20,true,"'ENAB'", "是否启用")
            .addColumn("c_auth_org_code", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'[root]'", "权限组织机构")
            .createUniqueIndex("IDX_S_DV_VOC_UCO", "C_DV_CODE")
            .createIndex("IDX_S_DV_VOC_UCO_001", "C_DV_TYPE")
            .build(UpdateType.REQUEST, "83002", "词汇表数据同步","sunyanlin","2019-11-28");
        
    }
	
	/**
	 * STORY #82160 【华宝基金】产品业务范围增加维护界面
	 * @throws Exception
	 */
	private void buildT_P_AB_BUSINESS_TYPE() throws Exception {
		tableBuilder.createTable("T_P_AB_BUSINESS_TYPE", "业务类型设置表")
		.addColumn("C_BUSINESS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "业务类型代码")
		.addColumn("C_BUSINESS_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "业务类型名称")
		.addColumn("C_BUSINESS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "业务类型")
		.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "序号")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "")
		.build(UpdateType.REQUEST, "82160", "新增", "yangze", "2019-11-22");
	}
	
	private void buildT_CP_FUNDTYPE_RELA() throws Exception{
		tableBuilder.createTable("T_CP_FUNDTYPE_RELA","账户类型表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "主键")
		.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "组合代码")
		.addColumn("C_RELA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "关联编号")
		.addColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "账户类型")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "修改时间")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "修改人")
		.addPrimaryConstraint("PK_CP_FUNDTYPE_RELA", "C_IDEN")
		.createIndex("IDX_CP_FUNDTYPE_RELA" , "C_RELA_CODE")
		.build(UpdateType.REQUEST, "75453", "STORY #75453 【前海开源基金】-【基本参数】-【支付产品账户信息】--一个账户需要能设置多个账户类型信息 ","zhangfengjun@ysstech.com", "2019-09-02");
	}
	
	/**
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @throws Exception
	 */
	private void buildT_P_BI_ORG_LINK_RELA() throws Exception {
		tableBuilder.createTable("T_P_BI_ORG_LINK_RELA", "主体信息联系人信息表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "ID")
		.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "关联机构代码")
		.addColumn("C_LINK_MAN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "联系人")
		.addColumn("C_POST_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "岗位")
		.addColumn("C_LINK_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 18, true, "' '", "联系电话")
		.addColumn("C_MO_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 11, true, "' '", "手机号码")
		.addColumn("C_EMAIL", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "电子邮箱")
		.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "", "序号")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "")
		.build(UpdateType.REQUEST, "81326", "新增", "yangze", "2019-10-30");
	}

	private void buildT_V_D_GROUP() throws Exception {
		tableBuilder.createTable("T_V_D_GROUP", "转换字典表")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理唯一主键")
				.addColumn("C_GROUP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "接口组代码")
				.addColumn("C_GROUP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "接口组名称")
				.addColumn("C_GROUP_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "父级接口组代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "序号")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_SCENE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'SCENE_CUSTOM'", "应用场景词汇(自定义，银行间外汇)")
				.addPrimaryConstraint("PK_V_D_GROUP", "C_GROUP_CODE,C_GROUP_CODE_P")
				.createIndex("IDX_V_D_GROUP1", "C_GROUP_CODE,C_DV_SCENE").build(UpdateType.REQUEST, "000001", "");
	}
	private void buildR_D_FEE_ID() throws Exception {
		tableBuilder.createTempTable("R_D_FEE_ID", "费用ID临时表")
		
				.setModuleCode("VALUATION")
				
				.addColumn("ID_D_AC_TD_IVT", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "费用ID")
				
				.createIndex("IDX_R_D_FEE_ID", "ID_D_AC_TD_IVT")
				.build(UpdateType.REQUEST, "45059", "TASK #332232 将每百元利息拆分到资讯组件", "", "2017-08-22");
	}
	
	private void buildT_P_BI_ORG() throws Exception {
		tableBuilder.createTable("T_P_BI_ORG","机构信息设置").setModuleCode(Baseinfo.MODULE_PUBLIC).addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "机构代码")
				//STORY #43111 【海通证券】 关联机构设置新增机构内码字段，区分现有的机构代码字段   edit by sunyanlin 2017-07-05
				.addColumn("C_ORG_INNER_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "机构内码")
				.addColumn("C_ORG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "机构名称")
				.addColumn("C_DV_ORG_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "机构类型")
				//.addColumn("C_ORG_NAME_ST", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "机构简称")
				//  BUG #236970 【华宝基金】t_p_bi_org表中C_ORG_NAME_ST 字段长度不够
				.addColumn("C_ORG_NAME_ST", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "机构简称")
				//.addColumn("C_ORG_NAME_CN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "中文名称")
				//海通21.6升级报错  根据海通数据库扩大精度
				.addColumn("C_ORG_NAME_CN", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "中文名称")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易市场")
				.addColumn("C_ORG_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上市公司代码")
				.addColumn("N_REG_CAP", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "注册资本")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "资本币种")
				.addColumn("C_CORP_REP", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "法人代表")
				.addColumn("C_CORP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "公司代码")
				.addColumn("C_LINK_MAN", OraColunmnTypeOnlyLength.VARCHAR2, 500, true, "", "联系人")
				.addColumn("C_LINK_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "联系电话")
				.addColumn("C_MO_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "手机号码")
				.addColumn("C_EMAIL", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "电子邮箱")
				.addColumn("C_REG_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "注册地址")
				.addColumn("C_OFFIC_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "办公地址")
				.addColumn("C_REG_POST", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "注册邮编")
				.addColumn("C_OFFIC_POST", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "办公邮编")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_DV_MANAGER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "管理人")
				.addColumn("C_DV_TRUSTEE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "托管人")
				.addColumn("C_DV_TRUSTEE_SEC", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "次托管人")
				.addColumn("C_DV_WARRANTOR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "担保人")
				.addColumn("C_DV_INVEST_ADVISER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投资顾问")
				.addColumn("C_DV_TRUSTEE_XT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "信托人")
				.addColumn("C_DV_SALES_CHANNELS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "销售渠道")
				.addColumn("C_DV_CLEARING_MEMBER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "结算会员")
				.addColumn("C_PLACE_SETTLEMENT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "结算地点")
				.addColumn("C_CLEAR_ACCOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "清算账号")
				.addColumn("C_BROKER_ID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "经纪人ID")
				.addColumn("C_BROKER_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "经纪人名称")
				.addColumn("C_BROKER_ID_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "经纪人种类")
				.addColumn("C_CLEARER_ID", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "清算人ID")
				.addColumn("C_CLEARER_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "清算人名称")
				.addColumn("C_CLEARER_ID_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "清算人种类")
				.addColumn("C_DV_TRD_CLIENT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "第三方委托")
				.addColumn("C_DV_BX_CLIENT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "保险委托")
				.addColumn("C_LOGO_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "机构LOGO")
				.addColumn("C_PAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "大额支付号")
				.addColumn("C_BANK_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "联行号")
				.addColumn("C_DV_DEPOSITARY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "受托人")
				.addColumn("D_FOUND_TIME", OraColumnTypeNoLength.DATE, true, "", "成立时间")
				.addColumn("C_DV_ORG_ATTR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "机构属性")
				.addColumn("C_DV_CONSIGNER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "委托人")
				.addColumn("C_WWW_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "国际互联网地址")
				.addColumn("C_FAX_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "传真号码")
				.addColumn("C_INDUSTRY_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "行业类别")
				.addColumn("C_BROKER_ACCOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "经纪账户")
				.addColumn("C_DV_COUNTERPARTY", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "对手方")
				.addColumn("C_DV_ISSUER", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "发行人")
				.addColumn("C_DV_WBFWJG", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "外包服务机构")
				.addColumn("C_TG_ACCOUNT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "托管账户标号")
				.addColumn("C_DV_TRUSTEE_MA", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "外包服务机构类型")
				//chenbo 20170805 STORY #44886 【基础信息组件】 机构信息设置主体资质属性系统存储机制优化
				.addColumn("C_DV_SUM", OraColunmnTypeOnlyLength.VARCHAR2, 4000, true, "", "主体资质字段汇总")
				.addPrimaryConstraint("PK_P_BI_ORG", "C_IDEN").createUniqueIndex("IDX_P_BI_ORG1", "C_ORG_CODE")
				.createIndex("IDX_P_BI_ORG", "N_CHECK_STATE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		// added by HeLiang 2017-02-15 STORY #37662 光大银行新增自定义资产情况报表
		columnBuilder.alterTable("T_P_BI_ORG")
				.addColumn("C_DV_MARKETING", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "销售机构")
				.build(UpdateType.REQUEST, "37662", "STORY #37662 光大银行新增自定义资产情况报表","HeLiang","2017-02-15");
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("c_elec_reconciliation", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "电子对账")
		.build(UpdateType.REQUEST, "37662", "");
		
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("D_CARD_VAL_DUR", OraColumnTypeNoLength.DATE, true, "", "法人证件有效期")
		.addColumn("D_CARD_VAL_DUR_END", OraColumnTypeNoLength.DATE, true, "", "法人证件有效期结束日期")
		.addColumn("D_IVT_CARD_VALDUR", OraColumnTypeNoLength.DATE, true, "", "投资人证件有效期")
		.addColumn("D_IVT_CARD_VALDUR_END", OraColumnTypeNoLength.DATE, true, "", "投资人证件有效期结束日期")
		.addColumn("C_DV_REPCARD_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "法人代表证件类型")
		.addColumn("C_REP_CARD_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "法人代表证件代码")
		.addColumn("C_IVT_CARD_NO", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投资人证件号码")
		.addColumn("C_IVT_CARD_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "投资人证件类型")
		.addColumn("C_ADMIN_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "投管人名称")
		.addColumn("C_ADMIN_NATURE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "投管人性质")
		.addColumn("C_ADMIN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "投管人代码")
		.build(UpdateType.REQUEST, "37444", "");
		
		// added by zhoushuhang 2017-05-19 BUG #160288 点击资本币种下拉框报错
		columnBuilder.alterTable("T_P_BI_ORG")
				.addColumn("C_ORG_ENCODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "主体编码")
				.build(UpdateType.BUG, "160288", "BUG #160288 点击资本币种下拉框报错","zhoushuhang","2017-05-19");
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_REMARK", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "备注")
		.build(UpdateType.REQUEST, "46387", "STORY #46387 【安信基金场外交易】主体信息界面增加备注字段","guoguangyi","2017-10-25");
		//STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("c_taxpayer_no", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "纳税人识别号")
		.addColumn("c_dv_taxpayer_type", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "纳税人类型")
		.addColumn("c_deposit_bank", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "开户银行")
		.addColumn("c_bank_acc", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "银行账户")
		.addColumn("c_tax_authorities", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "主管税务机关")
		.build(UpdateType.REQUEST, "43971", "STORY #43971 需求上海-[长江养老]金融资产管理平台V4.5[高]20170705（关联机构设置界面增加“纳税人识别号”文本框）","xuyuanhao","2017-11-02");
		
		//added by jiangzhichao 2017-12-05 STORY #49890 主体机构信息新增组织形式、存续期间
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_ORG_FORM", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组织形式")
		.addColumn("C_DURATION", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "存续期间")
		.addColumn("C_AGENT_PHONE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "客服电话")
		.build(UpdateType.REQUEST, "49890", "STORY #49890 产品生命周期 主体机构信息新增组织形式、存续期间、客服电话","jiangzhichao","2017-12-05");
		
		//add by sunyanlin 2017-12-13 STORY #49170 【招商证券】【营改增】银行间交易需要区分对手方 
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("c_dv_fin_org", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'JRJG'", "主体性质")
		.build(UpdateType.REQUEST, "49170 ", "STORY #49170 【招商证券】【营改增】银行间交易需要区分对手方 ", "sunyanlin", "2017-12-13");
		
		//STORY #45603 【易方达基金】【QDII】证券信息数据导入清算
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "' '", "' '")
		.build(UpdateType.REQUEST, "45603 ", "合并 STORY #45603 【易方达基金】【QDII】证券信息数据导入清算 ", "zhengjiebin", "2018-05-25");
		
		//BUG #194084 【海通证券】 关联机构机构名称长度限制太小，导致银行名称都无法输入完整
		columnBuilder.alterTable("T_P_BI_ORG")
		.modifyColumn("C_ORG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 600, true, "' '")
		.build(UpdateType.BUG, "194084", "BUG #194084 【海通证券】 关联机构机构名称长度限制太小，导致银行名称都无法输入完整","yuanyafeng@ysstech.com","2018-11-13");
		
		//STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段 
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_CH_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '","财汇机构代码")
		.addColumn("C_CH_ORG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 600, true, "' '","财汇机构简称")
		.build(UpdateType.REQUEST, "63833", "STORY #63833 关联机构设置中新增财汇机构代码和财汇机构简称字段","wangwu@ysstech.com","2019-10-21");
		//STORY #48731 长江养老电子发票需求-委托人信息接口导入
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_CORP_TEL", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "","单位电话")
		.addColumn("C_DV_INOVIC_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "","开票类型")
		.build(UpdateType.REQUEST, "48731", "STORY #48731 长江养老电子发票需求-委托人信息接口导入","lujianhao@ysstech.com","2020-03-13");
		//STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_DV_MANAGER_SEC", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "次管理人")
		.build(UpdateType.REQUEST, "32976", "STORY #32976 嘉实基金-管理人信息区分实际管理人和名义管理人","shijian","2016-09-26");
		columnBuilder.alterTable("T_P_BI_ORG")
		.addColumn("C_ISRElATED", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "是否关联方")
		.build(UpdateType.REQUEST, "51721", "STORY #51721 光大证券-监管类信息完善","lujianhao", "2018-07-12");
		//STORY #103374 【中欧基金】【版本V1.300.7.0.20210228.0323】导给彭博的净值f5888inav.inc、现金余额f5888icsh.inc接口文件及TA申赎f5888icsh.inc文件
		columnBuilder.alterTable("T_P_BI_ORG")
        .addColumn("C_PB_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "''", "彭博编码")
        .build(UpdateType.REQUEST, "103374", "STORY #103374", "liuyanxu", "2021-05-31");
		//STORY #108912 【大家资产】估值核算20210723---证券基本信息中新增融资人，担保人字段
		columnBuilder.alterTable("T_P_BI_ORG")
        .addColumn("C_DV_RZR", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "融资人")
        .build(UpdateType.REQUEST, "108912", "STORY #108912", "liuyanxu", "2021-08-26");
	}
	
	private void buildT_P_BI_PLATE() throws Exception {
		tableBuilder.createTable("T_P_BI_PLATE","板块信息").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_PLATE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "板块代码")
				.addColumn("C_PLATE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "板块名称")
				//.addColumn("C_PLATE_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "上级板块")
				//edit by sunyanlin 20180302 21.6-FAST2.0功能测试   板块分类设置界面保存报错  拓宽字段长度
				.addColumn("C_PLATE_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "上级板块")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, true, "", "启用日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "结束日期")
				.addColumn("C_PLATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "板块分类标准")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "行业指数代码")
				.addPrimaryConstraint("PK_P_BI_PLATE", "C_IDEN")
				//.createUniqueIndex("IDX_P_BI_PLATE", "C_PLATE_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
				//STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口 
				columnBuilder.alterTable("T_P_BI_PLATE")
				.addColumn("C_PLATE_FLBZ", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "' '", "板块分类标准，根据板块代码带出")
				.build(UpdateType.REQUEST, "58957", "STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口  ", "wulei", "2018-11-12");
				indexBuilder.createUniqueIndex("IDX_P_BI_PLATE")
				.setColumns("C_PLATE_CODE,C_PLATE_FLBZ")
				.onTable("T_P_BI_PLATE")
				.build(UpdateType.REQUEST, "58957", "STORY #58957 【招商证券】新增港股GICS行业分类导入及清算接口 ", "wulei", "2018-11-12");
				columnBuilder.alterTable("T_P_BI_PLATE")
				.modifyColumn("C_PLATE_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '")
				.build(UpdateType.BUG, "253805", "BUG #253805 【富国基金】【运维】清算彭博证券信息接口时产生股票品种和板块及维护板块分类代码长度限制问题", "liutao", "2019-04-17");	
	}
	
	private void buildT_P_BI_AREA() throws Exception {
		tableBuilder.createTable("T_P_BI_AREA","地区信息设置").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_AREA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "地区代码")
				.addColumn("C_AREA_CODE_EN", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "英文简称")
				.addColumn("C_AREA_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "地区名称")
				.addColumn("C_AREA_NAME_EN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "英文名称")
				.addColumn("C_AREA_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "所属地区")
				.addColumn("C_AREA_SEQ", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "地区编号")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_BI_AREA", "C_IDEN").createUniqueIndex("IDX_P_BI_AREA", "C_AREA_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_MKT() throws Exception {
		tableBuilder.createTable("T_P_BI_MKT","交易市场").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "市场内码")
				.addColumn("C_MKT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "市场名称")
				.addColumn("C_MKT_NAME_EN", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "英文名称")
				.addColumn("C_MKT_NAME_ST", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "简称")
				.addColumn("C_DV_MKT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "市场类型")
				.addColumn("C_DE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所属交易所")
				.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "节假日群")
				.addColumn("C_AREA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所属地区")
				.addColumn("N_MKT_ATTR", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "市场属性（0：表示国内市场；1：表示国际市场）")
				.addColumn("N_SETT_DAYS", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1 ", "结转天数")
				.addColumn("C_SWIFT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "", "swift代码")
				.addColumn("C_FIX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "FIX CODE")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_MKT_NO", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "市场代码")
				.addPrimaryConstraint("PK_P_BI_MKT", "C_IDEN").createIndex("IDX_P_BI_MKT1", "N_CHECK_STATE,C_MKT_CODE")
				.createUniqueIndex("IDX_P_BI_MKT", "C_MKT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_HDAY() throws Exception {
		tableBuilder.createTable("T_P_BI_HDAY","节假日群设置").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "节假日代码")
				.addColumn("C_HDAY_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "节假日名称")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_BI_HDAY", "C_IDEN").createUniqueIndex("IDX_P_BI_HDAY", "C_HDAY_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_HDAY_SUB() throws Exception {
		tableBuilder.createTable("T_P_BI_HDAY_SUB","节假日群设置_子表").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_HDAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "节假日代码")
				.addColumn("D_HDAY", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "日期")
				.addColumn("C_DATE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "日期类型(W-工作日  H-节假日)")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("N_YEAR", OraColumnTypeNoLength.NUMBER, true, "", "年份")
				.addPrimaryConstraint("PK_P_BI_HDAY_SUB", "C_IDEN")
				.createUniqueIndex("IDX_P_BI_HDAY_SUB", "C_HDAY_CODE,D_HDAY").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_IE() throws Exception {
		tableBuilder.createTable("T_P_BI_IE","收支项目").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "自增长流水号")
				.addColumn("C_FEE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "收支代码")
				.addColumn("C_FEE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "收支名称")
				.addColumn("C_SRC_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "来源标识（S-系统；E-扩展）")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addUniqueConstraint("IDX_P_BI_IE", "C_FEE_CODE").addPrimaryConstraint("PK_P_BI_IE", "C_IDEN")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_IE_RELA() throws Exception {
		tableBuilder.createTable("T_P_BI_IE_RELA","收支分类").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "自增长流水号")
				.addColumn("C_FEE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "收支代码")
				.addColumn("C_IE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "收支项目")
				.addColumn("C_SRC_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "来源标识（S-系统；E-扩展）")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_FEE_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上级收支代码")
				.createUniqueIndex("IDX_P_BI_IE_RELA", "C_FEE_CODE,C_IE_CODE")
				.addPrimaryConstraint("PK_P_BI_IE_RELA", "C_IDEN")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_SRC_SIGN() throws Exception {
		tableBuilder.createTable("T_P_BI_SRC_SIGN","来源标识").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_SRC_SIGN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "来源标识代码")
				.addColumn("C_SRC_SIGN_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "来源标识名称")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_BI_SRC_SIGN", "C_IDEN")
				.createUniqueIndex("IDX_P_BI_SRC_SIGN", "C_SRC_SIGN_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_SALES_NET() throws Exception {
		tableBuilder.createTable("T_P_BI_SALES_NET","销售网点设置").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_NET_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "网点代码")
				.addColumn("C_NET_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "网点名称")
				.addColumn("C_DV_NET_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "网点类型")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_CHANNEL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "渠道类型")
				.addColumn("C_DV_NET_SRC",  OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "' '")
				.addColumn("C_NET_SOURCE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "网点资源")
				//addby mazhongyuan  20180427  #55358 销售网点设置——表增加字段销售商性质（C_DV_NET_NATURE）及修改展示字段
				.addColumn("C_DV_NET_NATURE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true,"", "销售商性质")
				.addPrimaryConstraint("PK_P_BI_SALES_NET", "C_IDEN")
				.createUniqueIndex("IDX_P_BI_SALES_NET", "C_NET_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		columnBuilder.alterTable("T_P_BI_SALES_NET")
			    .addColumn("C_NET_SOURCE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "' '")
			    .addColumn("C_VENDOR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "","' '")
				.build(UpdateType.REQUEST, "000001", "", "", "");
	}
	
	private void buildR_P_BI_SALES_NET() throws Exception {
		tableBuilder.createTable("R_P_BI_SALES_NET","销售网点设置").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "自增长流水号")
				.addColumn("C_NET_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "网点代码")
				.addColumn("C_NET_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "' '", "网点名称")
				.addColumn("C_DV_NET_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "网点类型")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_CHANNEL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "渠道类型")
			    .addColumn("C_NET_SOURCE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "")
				.build(UpdateType.REQUEST, "000001", "", "", "");
	}
	
	private void buildT_P_BI_CURY_PAIR() throws Exception {
		tableBuilder.createTable("T_P_BI_CURY_PAIR","货币对设置").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_CURY_PAIR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "货币对代码")
				.addColumn("C_CURY_PAIR_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "货币对名称")
				.addColumn("C_DC_CODE_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "基准货币")
				.addColumn("C_DC_CODE_PRICE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "计价货币")
				.addColumn("N_QTE_FACTO", OraColumnTypeNeedPrecision.NUMBER, 18, 4, false, "0", "报价因子")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_BI_CURY_PAIR", "C_IDEN")
				.createUniqueIndex("IDX_P_BI_CURY_PAIR", "C_CURY_PAIR_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_ORG_MBR() throws Exception {
		tableBuilder.createTable("T_P_BI_ORG_MBR","机构结算会员").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_MBR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "会员号")
				.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "机构代码")
				.addColumn("C_ACC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "资金账号")
				.addColumn("C_ORG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "开户行名称")
				.addColumn("C_CA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "账户代码")
				.addColumn("C_CA_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "账户名称")
				.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "开始日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "结束日期")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_BROKER_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "券商代码")
				.addPrimaryConstraint("PK_P_BI_ORG_MBR", "C_IDEN")
				.createUniqueIndex("IDX_P_BI_ORG_MBR", "C_MBR_CODE,C_BROKER_CODE,D_BEGIN,D_END")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		//STORY #74322 华安基金：导出退补款文件支持生成特殊路径 华安退补款接口独立出来
		columnBuilder.alterTable("T_P_BI_ORG_MBR")
			    .addColumn("C_BROKER_CODE2", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "券商代码2")
				.build(UpdateType.REQUEST, "74322", "STORY #74322 华安基金：导出退补款文件支持生成特殊路径 华安退补款接口独立出来", "suixin", "20190619");
		//BUG #303401 【交银】300(1130.0215)版本结算会员设置表中扩展字段c_ca_name的长度
		columnBuilder.alterTable("T_P_BI_ORG_MBR")
				.modifyColumn("C_CA_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "' '")
				.build(UpdateType.BUG, "303401", "BUG #303401 【交银】300(1130.0215)版本结算会员设置表中扩展字段c_ca_name的长度", "yangze", "20200305");
		//STORY #91716 【招商基金】【0331】上交所ETF退补款导出文件夹，对于同一个券商不需要区分自营和非自营
		columnBuilder.alterTable("T_P_BI_ORG_MBR")
			    .addColumn("C_ORG_CODE_TAG", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "机构代码2")
				.build(UpdateType.REQUEST, "91716", "STORY #91716 【招商基金】【0331】上交所ETF退补款导出文件夹，对于同一个券商不需要区分自营和非自营", "guoyuhao", "20200812");
		//STORY #89891 【华泰柏瑞】上海跨市场ETF支持RQFII投资的券商与普通券商分别导出退补款
		columnBuilder.alterTable("T_P_BI_ORG_MBR")
			    .addColumn("C_BROKER_CATEGORY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "券商类别")
				.build(UpdateType.REQUEST, "89891", "STORY #89891 【华泰柏瑞】上海跨市场ETF支持RQFII投资的券商与普通券商分别导出退补款", "liliang", "20200917");
	}
	
	private void buildT_S_ITEM_PARA_RELA() throws Exception {
		tableBuilder.createTable("T_S_ITEM_PARA_RELA","业务参数关联表").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_ITEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "业务代码")
				.addColumn("C_DSP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "参数代码")
				.createUniqueIndex("IDX_S_ITEM_PARA_RELA", "C_ITEM_CODE,C_DSP_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DC_CURY() throws Exception {
		tableBuilder.createTable("T_S_DC_CURY","币种字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "1", "自增长流水号")
				.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "币种代码")
				.addColumn("C_DC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "币种名称")
				.addColumn("C_DC_SIGN", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "币种副号")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "编号")
				.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'ENAB'", "启用状态")
				.addPrimaryConstraint("PK_S_DC_CURY", "C_IDEN").createUniqueIndex("IDX_S_DC_CURY", "C_DC_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DA_SEC_VAR() throws Exception {
		tableBuilder.createTable("T_S_DA_SEC_VAR","证券品种字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长")
				.addColumn("C_SEC_VAR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "证券品种代码")
				.addColumn("C_SEC_VAR_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "挣钱品种名称")
				.addColumn("C_DA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "证券品种属性")
				.addColumn("C_DA_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "证券品种属性上级代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 5, 0, true, "", "编号")
				.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'ENAB'", "启用状态")
				.addPrimaryConstraint("PK_S_DA_SEC_VAR", "C_IDEN")
				.createUniqueIndex("IDX_S_DA_SEC_VAR1", "C_SEC_VAR_CODE")
				.createIndex("IDX_S_DA_SEC_VAR2", "C_DA_CODE_P").createIndex("IDX_S_DA_SEC_VAR", "C_DA_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DAT_ASS_TYPE() throws Exception {
		tableBuilder.createTable("T_S_DAT_ASS_TYPE","资产类型字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DAT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "资产类型代码")
				.addColumn("C_DAT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "资产类型名称")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "编号")
				.addColumn("C_DAT_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'[root]' ", "资产类型代码父节点")
				.addColumn("C_DAT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "资产类型分类")
				.createUniqueIndex("IDX_S_DAT_ASS_TYPE", "C_DAT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DT_TD_MODE() throws Exception {
		tableBuilder.createTable("T_S_DT_TD_MODE","交易方式字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易方式代码")
				.addColumn("C_DT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "交易方式名称")
				.addColumn("C_BUSI_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'[root] '", "业务类型")
				.addColumn("N_FUND_WAY", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "资金方向（1－流入, -1-流程，0－无）")
				.addColumn("N_CAPI_WAY", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "数量方向（1－流入, -1-流程，0－无）")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "编号")
				.createIndex("IDX_S_DT_TD_MODE1", "C_DT_CODE,N_FUND_WAY,N_CAPI_WAY")
				.createUniqueIndex("IDX_S_DT_TD_MODE", "C_DT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
//		indexBuilder.createUniqueIndex("IDX_S_DT_TD_MODE_001")
//		.onTable("T_S_DT_TD_MODE")
//		.setColumns("c_busi_type")
//		.build(UpdateType.BUG, "217272", "BUG #217272 【紧急】【招商基金】DVP指令管理撤单问题","lifudan@ysstech.com","2018-08-31");

	}
	
	private void buildT_S_DTA_TD_ATTR() throws Exception {
		tableBuilder.createTable("T_S_DTA_TD_ATTR","交易属性字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DTA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易属性代码")
				.addColumn("C_DTA_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "交易属性名称")
				.addColumn("C_BUSI_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'GPYW '", "业务类型")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "编号")
				.createUniqueIndex("IDX_S_DTA_TD_ATTR", "C_DTA_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DAI_ITEM() throws Exception {
		tableBuilder.createTable("T_S_DAI_ITEM","核算项目字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DAI_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "核算项目代码")
				.addColumn("C_DAI_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "核算项目名称")
				.addColumn("C_DV_KM_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "科目类别")
				.addColumn("N_FUND_WAY", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1 ", "核算方向（1－流入，-1－流出）")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 4, 0, false, "1 ", "编号")
				.addColumn("C_DV_BOOL_TYPE_AM", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "核算数量(1－核算，0－不核算)")
				.addColumn("C_STOCK_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'OT' ", "库存类别(SEC:证券类 CACH:现金类 FEE:运营类 OT:其他类)")
				.addColumn("C_DAI_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "核算项目类型（父子节点）")
				.createUniqueIndex("IDX_S_DAI_ITEM", "C_DAI_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DAE_ELEM() throws Exception {
		tableBuilder.createTable("T_S_DAE_ELEM","核算元素字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DAE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "核算元素代码")
				.addColumn("C_DAE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "核算元素名称")
				.addColumn("C_DS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "数据来源字符串从词汇获取字符串为：VOC=词汇类型从数据表获取字符串为：FUN=功能代码")
				.addColumn("C_DAI_FIELD", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素字段")
				.createUniqueIndex("IDX_S_DAE_ELEM", "C_DAE_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DAE_ELEM_DETAIL() throws Exception {
		tableBuilder.createTable("T_S_DAE_ELEM_DETAIL","核算元素明细字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DAI_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "核算项目代码")
				.addColumn("C_DAE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "核算元素代码")
				.addColumn("C_DAE_CODE_SUB", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "大类型")
				.addColumn("C_DAE_CODE1", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE2", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE3", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE4", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE5", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE6", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE7", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_DAE_CODE8", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算元素代码")
				.addColumn("C_LOAD_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "使用场景")
				.addPrimaryConstraint("PK_S_DAE_ELEM_DETAIL",
						"C_DAI_CODE,C_DAE_CODE,C_DAE_CODE_SUB,C_DAE_CODE1,C_DAE_CODE2,C_DAE_CODE3,C_DAE_CODE4,C_DAE_CODE5,C_DAE_CODE6,C_DAE_CODE7,C_DAE_CODE8,C_LOAD_MODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DF_FEE_RELA() throws Exception {
		tableBuilder.createTable("T_S_DF_FEE_RELA","费用关联字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "1", "自增长流水号")
				.addColumn("C_FEE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "费用代码")
				.addColumn("C_TD_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易类型")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "1", "编号")
				.addColumn("C_IE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收支项目")
				.addPrimaryConstraint("PK_S_DF_FEE_RELA", "C_IDEN")
				.createUniqueIndex("IDX_S_DF_FEE_RELA", "C_FEE_CODE,C_TD_TYPE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DVA_ITEM() throws Exception {
		tableBuilder.createTable("T_S_DVA_ITEM","核算业务项字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DVA_ITEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "核算项目代码")
				.addColumn("C_DVA_ITEM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "核算项目名称")
				.addColumn("C_DVA_ITEM_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'[root] '", "父级核算项目代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 5, 0, false, "0 ", "编号")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0 ", "是否明细项（1－明细项；0－非明细项）")
				.addColumn("C_PARA", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "关联参数")
				.createUniqueIndex("IDX_S_DVA_ITEM", "C_DVA_ITEM_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_DSP_PARA() throws Exception {
		tableBuilder.createTable("T_S_DSP_PARA","综合参数字典").setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_DSP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "1", "参数代码")
				.addColumn("C_DSP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "参数名称")
				.addColumn("C_DSP_CLASS", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "分类(核算：AO,清算:CO)")
				.addColumn("C_DSP_GROUP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "1", "参数类型代码")
				.addColumn("C_DSP_VALUE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "1", "参数类型(普通:空格；日期:DATE；词汇:VOC)")
				.addColumn("C_DV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "词汇类型")
				.addColumn("C_DSG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "1", "分组类型代码")
				.addColumn("C_DSG_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "分组类型名称")
				.addColumn("C_DV_PLAT_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 500, false, "' ' ", "默认值")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 300, true, "", "描述")
				.addColumn("C_DSP_GROUP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "参数类型名称")
				.addColumn("C_DAT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "资产类型")
				.addColumn("C_DS_TPYE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "词汇缓存")
				.addColumn("N_ORDER",OraColunmnTypeOnlyLength.NUMBER,6,false,"0", "")
				.addColumn("c_ref_code",OraColunmnTypeOnlyLength.VARCHAR2,20,true,"", "")
				.addColumn("c_ref_value",OraColunmnTypeOnlyLength.VARCHAR2,100,true,"", "")
				.addColumn("c_dsp_cls_first_name",OraColunmnTypeOnlyLength.VARCHAR2,100,true,"", "")
				.createUniqueIndex("IDX_S_DSP_PARA", "C_DSP_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		
		this.getColumnBuilder().alterTable("T_S_DSP_PARA")
		.modifyColumn("C_DSP_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "1")
		.build(UpdateType.REQUEST, "38699", "【光大证券】【V2.5需求】【优先】管理费及业绩报酬新算法", "zhaijiajia", "20170810");
        //add by zhoushuhang 2017-08-22 STORY38174【优化】优化调整清算综合参数、核算综合设置、估值产品参数 的参数设置方案  		
        columnBuilder.alterTable("T_S_DSP_PARA")
        .addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 6, 0, false, "0", "执行顺序")
        .addColumn("C_REF_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "依赖参数代码")
        .addColumn("C_REF_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "依赖参数值")
        .addColumn("C_DSP_CLS_FIRST_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "参数分级一级名称")
        .build(UpdateType.REQUEST, "38174", "STORY38174【优化】优化调整清算综合参数、核算综合设置、估值产品参数 的参数设置方案 ","周树航","20170822");
	}
	
	private void buildT_S_DE_EXP() throws Exception {
		tableBuilder.createTable("T_S_DE_EXP","表达式字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_EXP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "表达式代码")
				.addColumn("C_EXP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' '", "表达式名称")
				.addColumn("C_DV_EXP_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "表达式类型")
				.addColumn("C_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 2000, false, "' '", "表达式内容")
				.createUniqueIndex("IDX_S_DE_EXP", "C_EXP_CODE,C_DV_EXP_TYPE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_FIN_ITEM() throws Exception {
		tableBuilder.createTable("T_S_FIN_ITEM","财务项目字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_FIN_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "财务项目代码")
				.addColumn("C_FIN_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "财务项目名称")
				.addColumn("C_FIN_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "父级财务项目代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 4, 0, false, "0", "编号")
				.createUniqueIndex("IDX_S_FIN_ITEM", "C_FIN_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_IE_ITEM() throws Exception {
		tableBuilder.createTable("T_S_IE_ITEM","收支项目字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "收支项目代码")
				.addColumn("C_IE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "收支项目名称")
				.addColumn("C_DAI_CODE_REC", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "应收项目")
				.addColumn("C_DAI_CODE_COP", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "应付项目")
				.addColumn("C_DAI_CODE_INC", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收入项目")
				.addColumn("C_DAI_CODE_FEE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "费用项目")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "编号")
				.addColumn("N_STATE", OraColumnTypeNoLength.INTEGER, false, "", "状态")
				.addColumn("C_IE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收支项目分类")
				.addColumn("C_SZ_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "收支类型")
				.createUniqueIndex("IDX_S_IE_ITEM", "C_IE_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_MKT_VAR() throws Exception {
		tableBuilder.createTable("T_S_MKT_VAR","交易市场字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易市场代码")
				.addColumn("C_MKT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "交易市场名称")
				.addColumn("C_DV_MKT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "交易市场分类")
				.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'ENAB'", "是否启用")
				.createUniqueIndex("IDX_S_MKT_VAR", "C_MKT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_INDEX() throws Exception {
		tableBuilder.createTable("T_S_INDEX","指标项目字典表").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "指标名称")
				.addColumn("C_DATA_SOURCE", OraColumnTypeNoLength.CLOB, true, "", "数据源")
				.addColumn("C_DATA_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "数据类型")
				.addColumn("N_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "状态")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 5, 0, true, "", "编号")
				.addColumn("C_NAV_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "记录分类代码")
				.addColumn("N_DETAIL", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "0", "明细类型")
				.addColumn("C_KEY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "项目代码")
				.addColumn("C_KEY_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "项目名称")
				.addColumn("C_IS_SYS", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'N'", "所属资产类别")
				.addColumn("C_TRU", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'JW_ROUND'", "截位方式")
				.addColumn("C_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'CFG'", "指标计算方式")
				.addColumn("C_RET", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'2'", "保留位数")
				.addPrimaryConstraint("PK_S_INDEX", "C_IDEN").createUniqueIndex("IDX_S_INDEX", "C_INDEX_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		columnBuilder.alterTable("T_S_INDEX")
				.modifyColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 6, 0, true, "")
				.build(UpdateType.REQUEST, "41856", "STROY#41856 数据中心需要使用到的指标项和合计项", "cth", "20170828");
		columnBuilder.alterTable("T_S_INDEX")
				.addColumn("N_SHOW_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "999", "显示顺序")
				.build(UpdateType.REQUEST, "70175", "STORY #70175 【工银瑞信】估值表指标项需简化处理，按照客户需求进行排序", "zimingliang", "20190401");
		indexBuilder.createIndex("IDX_S_INDEX1")
		.setColumns("C_IS_SYS")
		.onTable("T_S_INDEX")
		.build(UpdateType.BUG, "285504", "BUG #285504 300.7版本性能测试，统计分析 SQLID 967quatyrufhc io 比较高", "chenyoucai", "2019-11-02");
		columnBuilder.alterTable("T_S_INDEX")
		.addColumn("C_INDEX_SHOW", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'INDEX_ENAB_SHOW'", "显示状态")
		.build(UpdateType.REQUEST, "84422", "STORY #84422 华夏基金-净值增长率指标只生成并显示分级部分", "tianpeng", "20200226");

	}
	
	private void buildT_S_INDEX_PARA_RELA() throws Exception {
		tableBuilder.createTable("T_S_INDEX_PARA_RELA","指标项目参数表").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "' '", "指标代码")
				.addColumn("C_INDEX_PARA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "参数代码")
				.createUniqueIndex("IDX_S_INDEX_PARA_RELA", "C_INDEX_CODE,C_INDEX_PARA_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_CL_INDEX() throws Exception {
		tableBuilder.createTable("T_S_CL_INDEX","指标项目字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_INDEX_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标代码")
				.addColumn("C_INDEX_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "指标名称")
				.addColumn("C_INDEX_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "指标类型")
				.addColumn("C_INDEX_CLS", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "指标分类")
				.addColumn("C_INDEX_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "指标上级代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "编号")
				.addColumn("C_VALUE_DEF", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "参考违规阀值")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "1", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_S_CL_INDEX", "C_IDEN").createUniqueIndex("IDX_S_CL_INDEX", "C_INDEX_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_QH_TRANS() throws Exception {
		tableBuilder
				.createTable("T_S_QH_TRANS","商品期货品种市场列表字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_MKT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50,true, "", "市场名称")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50,true, "", "市场代码")
				.addColumn("C_SEC_TAG", OraColunmnTypeOnlyLength.VARCHAR2, 50,true, "", "标的品种")
				.addColumn("C_SEC_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 50,true, "", "期货代码开头")
				.addColumn("N_RATE", OraColumnTypeNeedPrecision.NUMBER, 30, 15,true, "", "保证金比例")
				.addColumn("N_FV_ISSUE", OraColumnTypeNeedPrecision.NUMBER, 15,8, true, "", "合约乘数")
				.addColumn("C_LAST_TRADEDATE",OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "最后交易日")
				.addColumn("C_DELIVER_DATE", OraColunmnTypeOnlyLength.VARCHAR2,50, true, "", "交割日")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200,true, "", "描述").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		indexBuilder.createUniqueIndex("IDX_S_QH_TRANS").onTable("T_S_QH_TRANS")
				.setColumns("C_SEC_MARK, C_MKT_CODE").build(UpdateType.BUG, "185394", "添加唯一约束索引","王增辉","2017-12-22");
	}
	
	private void buildT_S_SEC_TRANS() throws Exception {
		tableBuilder.createTable("T_S_SEC_TRANS","证券代码转换字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_ZQDM", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "9999999", "原始代码")
				.addColumn("C_SEC", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'999999'", "转换代码")
				.addColumn("C_COND", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "'999999'", "匹配条件")
				.addColumn("C_SEC_VAR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "证券品种")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'CS'", "接口代码")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'XSHE'", "市场代码")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "备注信息")
				.addColumn("C_TD_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易类型")
				.addColumn("N_AMOUNT_HD", OraColumnTypeNeedPrecision.NUMBER, 18, 4, true, "0", "每手数量")
				.addColumn("C_TRANS_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "转换类型")
				.addPrimaryConstraint("PK_S_SEC_TRANS", "C_ZQDM,C_MKT_CODE,C_CFG_CODE,C_COND")
				.createIndex("IDX_S_SEC_TRANS2", "C_MKT_CODE")
				.createIndex("IDX_S_SEC_TRANS1", "C_COND,C_CFG_CODE,C_TD_TYPE")
				.createIndex("IDX_S_SEC_TRANS0", "C_COND,C_MKT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
//				columnBuilder.alterTable("T_S_SEC_TRANS")
//				.addColumn("C_ISTEMP_SUSPEND", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'0'","是否适用临时停市日设置")
//				.build(UpdateType.REQUEST, "44767", "2017-5-19-关于就《临时停市债券质押式回购业务结算暂行办法（征求意见稿）》公开征求意见的通知", "zhoushuhang@ysstech.com", "2017-12-12");
		//STORY #65167需求|一般 配债的发行代码段，新增代码段不需要编写程序，支持前台配置	
		columnBuilder.alterTable("T_S_SEC_TRANS")
			    .addColumn("c_buy_mode", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "买单交易模式")
			    .addColumn("c_sell_mode", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "卖单交易模式")
			    .addColumn("c_dv_plat", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "交易平台")
			    .addColumn("c_iden", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "物理主键标识")
			    .addColumn("c_update_by", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "操作人")
			    .addColumn("c_update_time", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "操作时间")
			    .addColumn("c_check_by", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "审核人")
			    .addColumn("c_check_time", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "审核时间")
			    .addColumn("n_check_state", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.build(UpdateType.REQUEST, "65167", "新增", "xdl", "2019-08-17");
		indexBuilder.createUniqueIndex("IDX_S_SEC_TRANS").onTable("T_S_SEC_TRANS").setColumns("c_iden")
				.build(UpdateType.REQUEST, "65167", "新增", "xdl", "2019-08-17");
	}
	
	private void buildT_S_DV_TD_ITEM() throws Exception {
		tableBuilder.createTable("T_S_DV_TD_ITEM","恒生交易数据业务分类表").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "市场代码")
				.addColumn("C_TD_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "业务类型代码")
				.addColumn("C_TD_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "业务类型名称")
				.addColumn("C_DT_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "业务名称")
				.addColumn("C_DT_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "业务类别")
				.addColumn("C_MKT_NO", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易市场")
				.addColumn("C_SB_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "申报代码类型")
				.addColumn("C_TD_NO", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易方式")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "描述")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "接口代码")
				.addColumn("C_ITEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "项目代码")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_MKT_TRANS() throws Exception {
		tableBuilder.createTable("T_S_MKT_TRANS","市场转换字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_MKT_NAME_CN", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "境外交易所中文名")
				.addColumn("C_MKT_NAME_EN", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "境外交易所英文名")
				.addColumn("C_BM", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "上传文件中的统一编码")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "转换后系统中的交易市场字典市场编码")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "备注信息")
				.addPrimaryConstraint("PK_S_MKT_TRANS", "C_BM,C_MKT_CODE").build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_CASH_FLOW() throws Exception {
		tableBuilder.createTable("T_S_CASH_FLOW","现金流标记字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_CASH_FLOW_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "现金流代码")
				.addColumn("C_CASH_FLOW_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' ' ", "现金流名称")
				.addColumn("C_DT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "交易方式代码")
				.addColumn("C_DVA_ITEM_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "' ' ", "核算项目名称")
				.addColumn("C_SEC_VAR_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "证券品种")
				.addColumn("C_DVA_ITEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "核算项目代码")
				.addColumn("C_FEE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' ' ", "费用代码")
				.createIndex("IDX_S_CASH_FLOW", "C_CASH_FLOW_CODE")
//				.createUniqueIndex("IDX_S_CASH_FLOW0",
//						"C_CASH_FLOW_CODE,C_DT_CODE,C_SEC_VAR_CODE,C_DVA_ITEM_CODE,C_FEE_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		columnBuilder.alterTable("T_S_CASH_FLOW")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200,
						true, "' '", "描述")
				.addColumn("C_DV_ACC_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,
						20, true, "' '", "账户")
				.addColumn("C_KM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 200,
						true, "' '", "科目代码")
				.addColumn("C_DAI_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						true, "' '", "核算项目")
				.addColumn("N_ORIG_MONEY", OraColunmnTypeOnlyLength.VARCHAR2,
						20, true, "' '", "原币金额")
				.addColumn("N_WAY", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						true, "' '", "借贷方向")
		.build(UpdateType.REQUEST, "41220", "STORY #41220 【紧急】太平保险-自动生成的现金流标记完善","huangshui","2017-07-15");
		columnBuilder.alterTable("T_S_CASH_FLOW")
	    .addColumn("c_cash_flow_out", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "' '")
	    .addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "操作人")
	    .addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "操作时间")
		.build(UpdateType.REQUEST, "30868", "新增", "zhd", "2017-07-10");
}
	
	private void buildT_S_CO_TD_SWITCH() throws Exception {
		tableBuilder.createTable("T_S_CO_TD_SWITCH","转换字典表").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_DSP_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "参数代码")
				.addColumn("C_DSP_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "' '", "参数名称")
				.addColumn("C_DV_VALUE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "参数值")
				.addColumn("C_CFG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "导入接口代码")
				.addColumn("C_DT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "交易方式")
				.createUniqueIndex("IDX_S_CO_TD_SWITCH", "C_DSP_CODE,C_DV_VALUE,C_CFG_CODE,C_DT_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
		/*BUG #150001 清算框架中打标识处理时，低版本无法兼容高版本数据库
		 * 清算接口优化代码合并*/
		indexBuilder.createIndex("IDX_S_CO_TD_SWITHC1").onTable("T_S_CO_TD_SWITCH")
		.setColumns("C_CFG_CODE, C_DT_CODE").build(UpdateType.BUG, "150001", "清算接口优化,增加索引","李明宏","2017-01-25");
		
		// STORY #69129 上交所科创板股票上市业务系统相关功能分析改造 
		columnBuilder.alterTable("T_S_CO_TD_SWITCH")
		.addColumn("C_DV_PLAT", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "交易平台")
		.build(UpdateType.REQUEST, "69129", "STORY #69129 上交所科创板股票上市业务系统相关功能分析改造 ", "dingshalu", "2019-03-21");
	}
	
	private void buildT_S_DZ_TYPE() throws Exception {
		tableBuilder.createTable("T_S_DZ_TYPE","划款类型字典").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_DZ_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "划款类型代码")
				.addColumn("C_DZ_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "划款类型名称")
				.addColumn("C_DZ_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上级代码")
				.addPrimaryConstraint("PK_S_DZ_TYPE", "C_IDEN").createUniqueIndex("IDX_S_DZ_TYPE", "C_DZ_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_S_INF_VAR() throws Exception {
		tableBuilder.createTable("T_S_INF_VAR","接口种类").setModuleCode(Baseinfo.MODULE_DICT)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自增长流水号")
				.addColumn("C_INF_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "接口种类代码")
				.addColumn("C_INF_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口种类名称")
				.addColumn("C_INF_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "上级接口种类代码")
				.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 3, 0, true, "", "编号")
				.addPrimaryConstraint("PK_S_INF_VAR", "C_IDEN").createUniqueIndex("IDX_S_INF_VAR", "C_INF_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","杨海茗","20170508");
	}
	
	private void buildT_P_BI_FEE() throws Exception {
		tableBuilder.createTable("T_P_BI_FEE","费用品种设置").addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "唯一主键")
				.addColumn("C_FEE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "费用代码")
				.addColumn("C_SRC_MARK", OraColunmnTypeOnlyLength.VARCHAR2, 10, false, "' '", "来源标记")
				.addColumn("C_FEE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "费用名称")
				.addColumn("C_DV_FEE_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "费用类型")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "操作人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "操作时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addPrimaryConstraint("PK_P_BI_FEE", "C_IDEN").createUniqueIndex("IDX_P_BI_FEE", "C_FEE_CODE")
				.build(UpdateType.REQUEST, "000001", "新建","龚岳","20170706");
	}

	private void buildT_C_CP_FUND_ACC() throws Exception{
		tableBuilder.createTable("T_C_CP_FUND_ACC", "账户主体卡信息表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "id")
		.addColumn("C_OPEN_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "开户账号")
		.addColumn("C_OPEN_ACC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "开户名称")
		.addColumn("C_OPEN_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "开户行")
		.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "机构代码/所有人")
		.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种代码")
		.addColumn("C_USAGE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "划款用途")
		.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "划款备注")
		.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, true, "", "启用日期/开户日期（支付平台特有）")
		.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "截止日期/销户日期（支付平台特有）")
		.addColumn("C_HOLDER", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "所有人")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "资产代码（支付平台特有）")
		.addColumn("C_HAVEUSED", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'0'", "是否使用过（支付平台特有）")
		.addColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "账户类型")
		.addColumn("C_INTER_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "中间行（支付平台特有）")
		.addColumn("C_PAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "","大额支付号")
		.addColumn("C_BC_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "中行机构号，当所属银行时中行时候维护本字段")
		.addColumn("C_OPEN_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "开户方式")
		.addColumn("C_RUNNING_ACC", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "流水账号，中国银行划款使用")
		.addColumn("C_BC_LINK_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "中行联行号，当所属银行时中行时候维护本字段")
		.addColumn("C_PROVINCE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "所属省份")
		.addColumn("C_CITY", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "所属城市")
		.addColumn("C_CNX", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "虚拟号")
		.addColumn("C_SWIFT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "银行国际统一码")
		.addColumn("C_BANK_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "开户行地址")
		.addColumn("C_CREATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建人")			
		.addColumn("C_CREATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建时间")			
		.addColumn("N_CHECK_STATE", OraColunmnTypeOnlyLength.NUMBER, 3, true, "'0'", "审批状态，0-未审核，1-已审核，资金划拨单未开启审核模式时默认为已审核")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审批人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审批时间")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "操作人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "操作时间")
		.addColumn("N_NUMBER1", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段1")
		.addColumn("N_NUMBER2", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段2")
		.addColumn("N_NUMBER3", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段3")
		.addColumn("C_STR1", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段1")
		.addColumn("C_STR2", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段2")
		.addColumn("C_STR3", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段3")
		.addColumn("C_CA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "现金账户代码")

		.addPrimaryConstraint("PK_C_CP_FUND_ACC", "C_IDEN")
		//.createUniqueIndex("IDX_C_CP_FUND_ACC", "C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, C_DC_CODE")
		.createIndex("IDX_C_CP_FUND_ACC_01", "C_OPEN_ACC_NO, C_ORG_CODE, C_DC_CODE, C_CA_CODE")
		//.createUniqueIndex("IDX_C_CP_FUND_ACC_02", "C_OPEN_ACC_NO,C_OPEN_ADDR,C_OPEN_ACC_NAME,C_DC_CODE")
		.build(UpdateType.REQUEST, "000001", "新建", "zhouning", "2017-11-16");
		
		
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
	    .addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合代码（废弃，后续新需求不允许使用该字段）")
		.addColumn("C_SYS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "支付系统号")
		.addColumn("C_OPEN_BANK", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "开户行名称")
		.addColumn("C_BANK_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "开户行机构号")
		.addColumn("C_BELONG_BANK", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "所属银行")
		.build(UpdateType.REQUEST, "48529", "新增", "zhouning", "2017-11-20");
		
		// STORY #42186 歌斐支付平台-账户模板导入
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
		.addColumn("d_yearly_check_date", OraColumnTypeNoLength.DATE, true, "to_timestamp('9998-12-31','YYYY/MM/DD')", "年间日期")
		.build(UpdateType.REQUEST, "45028", "STORY #42186 歌斐支付平台-账户模板导入", "zhouzitao@ysstech.com", "2017-07-04");
	
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
	    .addColumn("C_ASSO_NUM", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "联行号")
		.addColumn("C_CASH_ACCOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "现金账户")
		.addColumn("C_DC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种名称")
		.addColumn("C_ENABLE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "是否可用")
		.addColumn("C_INTERFACE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口代码")
		.addColumn("C_INTERFACE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "接口名称")
		.addColumn("C_OPEN_CITY", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "支付系统号")
		.addColumn("C_OPEN_PROVINCE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "所属省份")
		.addColumn("C_OWNER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所有人")
		.addColumn("C_PAY_CHANNEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "支付通道")
		.build(UpdateType.REQUEST, "48529", "新增", "xiaoqirui", "2018-01-20");
		
		//BUG #195539 【招商海通合并】webservice接口推送银行账户时，报文并没有该字段的值，中行机构号和中行联行号却给0值
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
		.modifyColumn("C_BC_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, null)
		.modifyColumn("C_BC_LINK_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, null)
		.build(UpdateType.BUG, "195539", "BUG #195539 【招商海通合并】webservice接口推送银行账户时，报文并没有该字段的值，中行机构号和中行联行号却给0值","zhouwang@ysstech.com","2018-03-24");
		//  STORY #56733 实现OA数据与指令数据、指令数据与银行明细流水的报表汇总统计
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
		.addColumn("C_FLOW_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, null, "流水账号")
		.build(UpdateType.REQUEST, "56733", "STORY #56733 实现OA数据与指令数据、指令数据与银行明细流水的报表汇总统计","xiaoqirui@ysstech.com","2018-08-01");
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
	    .addColumn("C_RELA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "关联号")
	    .build(UpdateType.BUG, "252441", "新增", "liangchong", "2019-04-11");
		
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
		.modifyColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,400, true, "")
		.build(UpdateType.REQUEST, "75453", "STORY #75453 【前海开源基金】-【基本参数】-【支付产品账户信息】--一个账户需要能设置多个账户类型信息 ","zhangfengjun@ysstech.com", "2019-09-02");
	
		columnBuilder.alterTable("T_C_CP_FUND_ACC")
		.addColumn("C_OPEN_JC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "开户行简称")
		.build(UpdateType.BUG, "328697", "BUG #328697 产品账户接口报错", "yangze@ysstech.com", "2020-08-07");
		
		//索引增加组合代码做唯一约束。 BUG #252441 【易方达基金】【运维】产品账户信息维护后清算进来的资金换汇业务没有获取到资金账户
		indexBuilder.createUniqueIndex("IDX_C_CP_FUND_ACC").onTable("T_C_CP_FUND_ACC")
		.setColumns("C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, C_DC_CODE, C_PORT_CODE, C_CA_CODE") // 现金账户应该也区分
		.build(UpdateType.BUG, "252441", "修改", "liangchong", "2019-04-11");
		
	}
	
	private void buildT_P_BI_FUND_ACC() throws Exception{
		tableBuilder.createTable("T_P_BI_FUND_ACC", "账户主体卡信息表(新)").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "id")
		.addColumn("C_OPEN_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 100, false, "", "开户账号")
		.addColumn("C_OPEN_ACC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "开户名称")
		.addColumn("C_OPEN_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "开户行")
		.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "机构代码/所有人")
		.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种代码")
		.addColumn("C_USAGE", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "划款用途")
		.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "划款备注")
		.addColumn("D_BEGIN", OraColumnTypeNoLength.DATE, true, "", "启用日期/开户日期（支付平台特有）")
		.addColumn("D_END", OraColumnTypeNoLength.DATE, true, "", "截止日期/销户日期（支付平台特有）")
		.addColumn("C_HOLDER", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "所有人")
		.addColumn("C_ASS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "资产代码（支付平台特有）")
		.addColumn("C_HAVEUSED", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'0'", "是否使用过（支付平台特有）")
		.addColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "账户类型")
		.addColumn("C_INTER_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "中间行（支付平台特有）")
		.addColumn("C_PAY_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "","大额支付号")
		.addColumn("C_BC_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "中行机构号，当所属银行时中行时候维护本字段")
		.addColumn("C_OPEN_MODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "开户方式")
		.addColumn("C_RUNNING_ACC", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "流水账号，中国银行划款使用")
		.addColumn("C_BC_LINK_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "中行联行号，当所属银行时中行时候维护本字段")
		.addColumn("C_PROVINCE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "所属省份")
		.addColumn("C_CITY", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "所属城市")
		.addColumn("C_CNX", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "虚拟号")
		.addColumn("C_SWIFT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "银行国际统一码")
		.addColumn("C_BANK_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "开户行地址")
		.addColumn("C_CREATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建人")			
		.addColumn("C_CREATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "创建时间")			
		.addColumn("N_CHECK_STATE", OraColunmnTypeOnlyLength.NUMBER, 3, true, "'0'", "审批状态，0-未审核，1-已审核，资金划拨单未开启审核模式时默认为已审核")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审批人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审批时间")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "操作人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "操作时间")
		.addColumn("N_NUMBER1", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段1")
		.addColumn("N_NUMBER2", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段2")
		.addColumn("N_NUMBER3", OraColumnTypeNeedPrecision.NUMBER, 20,4, true, "", "数值备用字段3")
		.addColumn("C_STR1", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段1")
		.addColumn("C_STR2", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段2")
		.addColumn("C_STR3", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符串备用字段3")
		.addColumn("C_CA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "现金账户代码")

		.addPrimaryConstraint("PK_P_BI_FUND_ACC", "C_IDEN")
		.createUniqueIndex("IDX_P_BI_FUND_ACC", "C_OPEN_ACC_NO, C_OPEN_ACC_NAME, C_OPEN_ADDR, C_DC_CODE")
		.createIndex("IDX_P_BI_FUND_ACC_01", "C_OPEN_ACC_NO, C_ORG_CODE, C_DC_CODE, C_CA_CODE")
		.build(UpdateType.REQUEST, "000001", "新建", "zhouning", "2017-11-16");
		
		
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
	    .addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "组合代码（废弃，后续新需求不允许使用该字段）")
		.addColumn("C_SYS_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "支付系统号")
		.addColumn("C_OPEN_BANK", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, "", "开户行名称")
		.addColumn("C_BANK_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "开户行机构号")
		.addColumn("C_BELONG_BANK", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "'0'", "所属银行")
		.build(UpdateType.REQUEST, "48529", "新增", "zhouning", "2017-11-20");
		
		// STORY #42186 歌斐支付平台-账户模板导入
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("d_yearly_check_date", OraColumnTypeNoLength.DATE, true, "to_timestamp('9998-12-31','YYYY/MM/DD')", "年间日期")
		.build(UpdateType.REQUEST, "45028", "STORY #42186 歌斐支付平台-账户模板导入", "zhouzitao@ysstech.com", "2017-07-04");
	
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
	    .addColumn("C_ASSO_NUM", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "联行号")
		.addColumn("C_CASH_ACCOUNT", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "现金账号")
		.addColumn("C_DC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种名称")
		.addColumn("C_ENABLE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "是否可用")
		.addColumn("C_INTERFACE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "接口代码")
		.addColumn("C_INTERFACE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "接口名")
		.addColumn("C_OPEN_CITY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "支付系统号")
		.addColumn("C_OPEN_PROVINCE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所属省份")
		.addColumn("C_OWNER", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "所有人")
		.addColumn("C_PAY_CHANNEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "支付通道")
		.addColumn("C_IDEN_LINK", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "关联ID")
		.build(UpdateType.REQUEST, "48529", "新增", "xiaoqirui", "2018-01-20");
		
		//BUG #195539 【招商海通合并】webservice接口推送银行账户时，报文并没有该字段的值，中行机构号和中行联行号却给0值
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.modifyColumn("C_BC_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, null)
		.modifyColumn("C_BC_LINK_NO", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, null)
		.build(UpdateType.BUG, "195539", "BUG #195539 【招商海通合并】webservice接口推送银行账户时，报文并没有该字段的值，中行机构号和中行联行号却给0值","zhouwang@ysstech.com","2018-03-24");
		
		// STORY #42186 歌斐支付平台-账户模板导入
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("C_IDEN_LINK", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "关联ID")
		.build(UpdateType.BUG, "205734", "BUG #205734 非标webservice接口推送指令报错", "zhouwang@ysstech.com", "2018-06-06");
	
		//STORY #56407 【歌斐资产】对于系统中的久悬户和已销户希望进行特殊处理；
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("C_DISCARD_STATUS", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "'NORMAL_ACC_SIG'", "账户久悬状态")
		.build(UpdateType.REQUEST, "56407", "STORY #56407 【歌斐资产】对于系统中的久悬户和已销户希望进行特殊处理；", "zhouwang@ysstech.com", "2018-06-27");
		
		  //  STORY #56733 实现OA数据与指令数据、指令数据与银行明细流水的报表汇总统计
		  columnBuilder.alterTable("T_P_BI_FUND_ACC")
		  .addColumn("C_FLOW_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 100, true, null, "流水账号")
		  .build(UpdateType.REQUEST, "56733", "STORY #56733 实现OA数据与指令数据、指令数据与银行明细流水的报表汇总统计","xiaoqirui@ysstech.com","2018-08-01");
		
		//STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("C_DV_DATA_SRC", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "数据源")
		.build(UpdateType.REQUEST, "58468", "STORY #58468 生命周期系统中的账户信息数据同步至基础组件的银行账户信息", "yangze@ysstech.com", "2019-05-30");
		
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.modifyColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,400, true, "")
		.build(UpdateType.REQUEST, "75453", "STORY #75453 【前海开源基金】-【基本参数】-【支付产品账户信息】--一个账户需要能设置多个账户类型信息 ","zhangfengjun@ysstech.com", "2019-09-02");
		
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("C_PAYMENT_KEY", OraColunmnTypeOnlyLength.VARCHAR2, 50 , true, "", "划款密钥")
		.build(UpdateType.REQUEST, "78064 ", "STORY #78064 【汇添富prop直连】对接支付网关优化","yexianfa@ysstech.com", "2020-03-20");
		
		columnBuilder.alterTable("T_P_BI_FUND_ACC")
		.addColumn("C_OPEN_JC", OraColunmnTypeOnlyLength.VARCHAR2, 200 , true, "", "开户行简称")
		.build(UpdateType.BUG, "327138 ", "BUG #327138 【太平资产】【300.7-0531版本】产品账户信息新增时没有开户行简称，账户计息设置无开户名称","wangwu@ysstech.com", "2020-08-06");
		
	}
	
	
	/**
	 * 区域信息表 T_S_DC_ADDRESS
	 */
	private void buildT_S_DC_ADDRESS() throws Exception{
		tableBuilder.createTable("T_S_DC_ADDRESS", "区域信息表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理主键")
		.addColumn("C_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "区域代码：区域代码，如11北京市")
		.addColumn("C_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "区域名：区域代码对应的区域名")
		.addColumn("C_LEVEL", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "区域级别： 1 国家 2 省/直辖市 3 地级市")
		.addColumn("C_PARENT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, true, "", "上级区域代码：存储上一级区域的代码，如北京市辖区1101的上级区域代码为11北京市")
		.addColumn("N_NUMBER1", OraColumnTypeNeedPrecision.NUMBER, 20,2, true, "", "数值备用字段1，精度（20,4）")
		.addColumn("C_STR1", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "字符备用字段1")
		.addPrimaryConstraint("PK_S_DC_ADDRESS_01", "C_IDEN")
		.createUniqueIndex("IDX_S_P_ADDRESS_01", "C_CODE")
		.build(UpdateType.REQUEST, "000001", "新建", "liangyilin", "2017-08-16");
	}
	
	private void buildT_V_D_GROUP_DETAIL() throws Exception {
		tableBuilder.createTable("T_V_D_GROUP_DETAIL","转换字典数据表")
				.addColumn("C_IDEN",OraColunmnTypeOnlyLength.VARCHAR2,30,false,"","自动id")
				.addColumn("C_GROUP_CODE",OraColunmnTypeOnlyLength.VARCHAR2,25,false,"","转换字典组代码")
				.addColumn("C_S_CODE",OraColunmnTypeOnlyLength.VARCHAR2,200,false,"","源代码")
				.addColumn("C_T_CODE",OraColunmnTypeOnlyLength.VARCHAR2,200,false,"","转换后代码")
				.addColumn("N_CHECK_STATE",OraColumnTypeNeedPrecision.NUMBER,3,0,false,"1","审核状态")
				.addColumn("C_UPDATE_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新人")
				.addColumn("C_UPDATE_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新时间")
				.addColumn("C_CHECK_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核人")
				.addColumn("C_CHECK_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核时间")	
				.createUniqueIndex("IDX_V_D_GROUP_DETAIL","C_GROUP_CODE, C_S_CODE, C_T_CODE")
				.build(UpdateType.REQUEST, "000001", "");
	}
	
	private void buildT_P_AB_PORT_ACC_RELA() throws Exception{
		tableBuilder.createTable("T_P_AB_PORT_ACC_RELA", "组合银行账户关联表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理主键")
		.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "产品代码")
		.addColumn("C_RELA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "银行账户物理主键")
		.addColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "账户类型")
		.addPrimaryConstraint("PK_P_AB_PORT_ACC_RELA", "C_IDEN")
		.createUniqueIndex("IDX_P_AB_PORT_ACC_RELA_01", "C_PORT_CODE, C_RELA_CODE")
		.build(UpdateType.REQUEST, "000001", "新建", "zhouning", "2017-11-08");
		
		columnBuilder.alterTable("T_P_AB_PORT_ACC_RELA")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "''", "更新时间")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "''", "更新人")
		.build(UpdateType.BUG, "205462", "账户关联组合信息表T_P_AB_PORT_ACC_RELA中增加时间字段  ", "dingyan@ysstech.com", "2018-06-05");
		
		columnBuilder.alterTable("T_P_AB_PORT_ACC_RELA")
		.modifyColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,400, true, "")
		.build(UpdateType.REQUEST, "75453", "STORY #75453 【前海开源基金】-【基本参数】-【支付产品账户信息】--一个账户需要能设置多个账户类型信息 ","zhangfengjun@ysstech.com", "2019-09-02");
		
		columnBuilder.alterTable("T_P_AB_PORT_ACC_RELA")
		.addColumn("D_START", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "开始日期")
		.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "结束日期")
		.build(UpdateType.REQUEST, "89090", "STORY #89090 变更托管人及交易席位，可以维护旧流水的截止日期以及新流水的启用日期", "liuwenjian@ysstech.com", "2020-11-06");
		
	}
	
	/**
	 * 组合关联设置
	 * @Title buildT_P_AB_PORT_RELA 
	 * @Description 
	 * @author zhanghualin@ysstech.com
	 * @date 2017年05月12日 15:07:20
	 * @throws Exception
	 */
	private void buildT_P_AB_PORT_RELA() throws Exception {
		tableBuilder.createTable("T_P_AB_PORT_RELA", "组合关联设置")
				.setModuleCode(Baseinfo.MODULE_PUBLIC)
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "自动ID")
				.addColumn("C_PORT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合代码")
				.addColumn("C_RELA_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "组合关联类型")
				.addColumn("C_RELA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "' '", "组合关联代码")
				.addColumn("C_DV_TYPE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "类型代码")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER, 3, 0, false, "0", "审核状态（1-审核；0-未审核；-1-删除）")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
				.addColumn("C_CA_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "账户代码")
				.addColumn("C_MKT_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "市场代码")
				.addPrimaryConstraint("PK_P_AB_PORT_RELA", "C_IDEN")
				//edit by wzh 2017-7-18 BUG #166220 【紧急】银河证券：期货公司同一渠道对应不同市场清算出来的流水缺失（经纪公司）字段
				//.createUniqueIndex("IDX_P_AB_PORT_RELA0", "C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE")
				//.createUniqueIndex("IDX_P_AB_PORT_RELA0", "C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE")
				//BUG #208084	【平安资产】产品关联信息中 只显示一个交易渠道的代码
				.createUniqueIndex("IDX_P_AB_PORT_RELA0", "C_PORT_CODE, C_RELA_TYPE, C_RELA_CODE, C_DV_TYPE_CODE, C_MKT_CODE")
				.createIndex("IDX_P_AB_PORT_RELA1", "C_PORT_CODE, C_RELA_TYPE, N_CHECK_STATE")
				//modify by caoxingyun 2017-10-16 估值系统标准表索引定义
				//.createIndex("IDX_P_AB_PORT_RELA", "C_RELA_CODE")
				.build(UpdateType.REQUEST, "000001", "新建", "zhanghualin@ysstech.com", "2017-05-12");
		
				//安信关联产品所需字段
		columnBuilder.alterTable("T_P_AB_PORT_RELA")
				.addColumn("C_DV_MATCH_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "匹配类型")
				.addColumn("C_MATCH_WORD", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "匹配关键词")
				.addColumn("C_DV_USE_RANGE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "使用范围")
				.build(UpdateType.REQUEST, "", "添加字段","qiantao@ysstech.com","2017-06-15");
		//汇添富基金增加席位退租功能
		columnBuilder.alterTable("T_P_AB_PORT_RELA")
				.addColumn("D_START", OraColumnTypeNoLength.DATE, false, "to_timestamp('1900-01-01','YYYY/MM/DD')", "开始日期")
				.addColumn("D_END", OraColumnTypeNoLength.DATE, false, "to_timestamp('9998-12-31','YYYY/MM/DD')", "结束日期")
				.build(UpdateType.REQUEST, "77779", "STORY #77779 【兴全基金】交易渠道可以设置起止日期合并需求STORY #68186", "dudexu", "2019-08-12");
		//STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求
		columnBuilder.alterTable("T_P_AB_PORT_RELA")
				.addColumn("C_BIC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "BIC CODE")
				.build(UpdateType.REQUEST, "82297", "STORY #82297 嘉实基金-RQFII ETF组合识别为沪股、深股渠道的交易数据报表和台账需求", "liliang", "2019-12-11");
		
	}
	
	private void buildT_S_DAC_TYPE()  throws Exception{
		tableBuilder.createTable("T_S_DAC_TYPE", "账户类型字典表").setModuleCode(Baseinfo.MODULE_DICT)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理主键")
		.addColumn("C_DAC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "账户类型代码")
		.addColumn("C_DAC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "账户类型名称")
		.addColumn("C_DAC_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "账户类型父级节点代码")
		.addColumn("N_ORDER", OraColumnTypeNeedPrecision.NUMBER, 5,0, true, "", "排序")
		.addColumn("C_DAC_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "账户分类（定位为资金户还是证券户等）")
		.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "'ENAB'", "启用状态")
		.addColumn("C_ACC_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 10, true, "", "分类")
		.addPrimaryConstraint("PK_S_DAC_TYPE", "C_IDEN")
		.createUniqueIndex("IDX_S_DAC_TYPE_01", "C_DAC_CODE")
		.build(UpdateType.REQUEST, "000001", "新建", "zhouning", "2017-11-08");
	}
	
	/**
	 * add by zhoushuhang 2017-12-20
	 * STORY #44767 2017-5-19-关于就《临时停市债券质押式回购业务结算暂行办法（征求意见稿）》公开征求意见的通知
	 * @throws Exception
	 */
	private void buildT_S_SUSPEND_RELA() throws Exception{
		tableBuilder.createTable("T_S_SUSPEND_RELA", "临时停市日设置关联信息").setModuleCode(Baseinfo.MODULE_DICT)
		.addColumn("C_SEC", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "'999999'", "满足临时停市代码段")
		.addPrimaryConstraint("PK_S_SUSPEND_RELA", "C_SEC")
		.build(UpdateType.REQUEST, "44767", "新建", "zhoushuhang@ysstech.com", "2017-12-20");
	}
	
	
	private void buildT_P_BI_ACC_TREE()  throws Exception{
		tableBuilder.createTable("T_P_BI_ACC_TREE", "账户树形结构表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理主键")
		.addColumn("C_NODE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "账户树形节点代码")
		.addColumn("C_NODE_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "账户树形节点名称")
		.addColumn("C_NODE_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "账户树形节点父级节点代码")
		.addColumn("C_POST_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "", "权限岗位")
		.addColumn("N_CHECK_STATE",OraColumnTypeNeedPrecision.NUMBER,3,0,false,"1","审核状态")
		.addColumn("C_UPDATE_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新人")
		.addColumn("C_UPDATE_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新时间")
		.addColumn("C_CHECK_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核人")
		.addColumn("C_CHECK_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核时间")	
		.addPrimaryConstraint("PK_P_BI_ACC_TREE", "C_IDEN")
		.createUniqueIndex("IDX_P_BI_ACC_TREE01", "C_NODE_CODE")
		.createUniqueIndex("IDX_P_BI_ACC_TREE02", "C_NODE_NAME")
		.build(UpdateType.REQUEST, "62009", "【歌斐资产】新增支付账户信息群组界面的树形结构管理", "zhouning", "2017-11-12");
	}
	
	private void buildT_P_AB_ACC_TREE_RELA()  throws Exception{
		tableBuilder.createTable("T_P_AB_ACC_TREE_RELA", "账户树形结构关联信息表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "物理主键")
		.addColumn("C_NODE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "当前节点代码")
		.addColumn("C_NODE_CODE_P", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "父级节点代码")
		.addColumn("C_IDEN_RELA", OraColunmnTypeOnlyLength.VARCHAR2, 200, false, "", "关联账户ID")
		.addColumn("C_OPEN_ACC_NAME", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "", "账户名称")
		.addColumn("C_OPEN_ACC_NO", OraColunmnTypeOnlyLength.VARCHAR2, 200, true, "", "账户代码")
		.addColumn("C_OPEN_ADDR", OraColunmnTypeOnlyLength.VARCHAR2, 2000, true, "", "账户开户行")
		.addColumn("C_ACCOUNT_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "账户类型")
		.addColumn("C_DC_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "币种")
		.addColumn("C_ORG_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 30, true, "", "开户机构")
		.addColumn("N_CHECK_STATE",OraColumnTypeNeedPrecision.NUMBER,3,0,false,"1","审核状态")
		.addColumn("C_UPDATE_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新人")
		.addColumn("C_UPDATE_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","更新时间")
		.addColumn("C_CHECK_BY",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核人")
		.addColumn("C_CHECK_TIME",OraColunmnTypeOnlyLength.VARCHAR2,25,true,"","审核时间")	
		.addPrimaryConstraint("PK_P_AB_ACC_TREE_RELA", "C_IDEN")
		.createUniqueIndex("IDX_P_AB_ACC_TREE_RELA", "C_NODE_CODE_P,C_IDEN_RELA")
		.build(UpdateType.REQUEST, "62009", "【歌斐资产】新增支付账户信息群组界面的树形结构管理", "zhouning", "2017-11-12");
	}
	
	private void buildT_S_PARA() throws Exception {
		tableBuilder.createTable("T_S_PARA","算法参数表").setModuleCode(Baseinfo.MODULE_DICT)
		.addColumn("C_PARA_CODE", OraColunmnTypeOnlyLength.VARCHAR2,50, false, "' '", "参数代码")
		.addColumn("C_PARA_NAME", OraColunmnTypeOnlyLength.VARCHAR2,100, false, "' '", "参数名称")
		.addColumn("C_DV_PARA_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,20, false, "' '", "参数值类型")
		.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 200,false, "' '", "描述")
		.addColumn("C_DV_CTL_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,20, true, "", "参数控件")
		.addColumn("C_DV_VALUE_TYPE",OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "参数控件值类型")
		.addColumn("C_CTL_ATTR", OraColunmnTypeOnlyLength.VARCHAR2,2000, true, "", "参数控件条件")
		.addColumn("C_DS_ATTR", OraColunmnTypeOnlyLength.VARCHAR2, 500,true, "", "参数控件属性")
		.addColumn("C_EXPAND_COND", OraColunmnTypeOnlyLength.VARCHAR2,500, true, "", "扩展条件")
		.createUniqueIndex("IDX_S_PARA", "C_PARA_CODE")
		.build(UpdateType.REQUEST, "000001", "参", "xuhanbing", "2019-1-22");
	}
	
	private void buildT_P_AB_BUSINESS_RANGE() throws Exception {
		tableBuilder.createTable("T_P_AB_BUSINESS_RANGE","产品业务范围表").setModuleCode(Baseinfo.MODULE_PUBLIC)
		.addColumn("C_IDEN",  OraColunmnTypeOnlyLength.VARCHAR2, 30,  false,  "",  "序号")
		.addColumn("C_PORT_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 20,  false,  "",  "组合代码")
		.addColumn("C_BUSINESS_CODE",  OraColunmnTypeOnlyLength.VARCHAR2, 20,  false,  "",  "业务类型代码")
		.addColumn("N_CHECK_STATE",  OraColumnTypeNeedPrecision.NUMBER,  3,  0,  false,  "1",  "审核状态")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, false, "", "更新时间")
		.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核人")
		.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20, true, "", "审核时间")
		.addPrimaryConstraint("PK_P_AB_BUSINESS_RANGE", "C_IDEN")
		.createUniqueIndex("IDX_P_AB_BUSINESS_RANGE", "C_PORT_CODE,C_BUSINESS_CODE")
		.build(UpdateType.REQUEST, "72335", "华宝兴业：新增界面用于设置组合所属的投资范围的标签，将标签与流程绑定，以此判断组合所需要走的流程 ", "xiadeqi", "2019-05-17");
	}
}
