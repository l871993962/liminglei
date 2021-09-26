/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2017年5月8日杨海茗
**********************************************************************/

package com.yss.ams.db.upgrade.baseinfo.structs.sequences;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.SequenceBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * @ClassName SequenceDescImpl
 * @Description
 * @author yhm
 * @CreateDate 2017-5-8
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SequenceDescImpl extends BaseStructDesc {

	private SequenceBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getSeqBuilder();
		buildSEQU_S_INDEX();
		buildSEQU_S_INF_VAR();
		buildSEQU_CP_FUNDTYPE_RELA();
		buildSEQU_S_DF_FEE_RELA();
		buildSEQU_P_BI_ORG();
		buildSEQU_P_BI_PLATE();
		buildSEQU_P_BI_AREA();
		buildSEQU_P_BI_MKT();
		buildSEQU_P_BI_HDAY();
		buildSEQU_P_BI_HDAY_SUB();
		buildSEQU_P_BI_IE();
		buildSEQU_P_BI_IE_RELA();
		buildSEQU_P_BI_SRC_SIGN();
		buildSEQU_P_BI_SALES_NET();
		buildSEQU_P_BI_CURY_PAIR();
		buildSEQU_P_BI_ORG_MBR();
		buildSEQU_S_DC_CURY();
		buildSEQU_S_DA_SEC_VAR();
		buildSEQU_S_DAT_ASS_TYPE();
		buildSEQU_S_DT_TD_MODE();
		buildSEQU_S_DTA_TD_ATTR();
		buildSEQU_S_DAI_ITEM();
		buildSEQU_S_DAE_ELEM();
		buildSEQU_S_DVA_ITEM();
		buildSEQU_S_DSP_PARA();
		buildSEQU_S_DE_EXP();
		buildSEQU_S_FIN_ITEM();
		buildSEQU_S_IE_ITEM();
		buildSEQU_S_MKT_VAR();
		buildSEQU_S_INDEX_PARA_RELA();
		buildSEQU_S_CL_INDEX();
		buildSEQU_S_DZ_TYPE();
		buildSEQU_P_BI_FEE();
		buildSEQU_P_AB_BUSINESS_RANGE();
		//账户模块
		/**
		 * BUG #186138 【产品升级组件】SEQU_C_CP_FUND_ACC序列产品升级组件未创建    
		 * @author maxiangfeng
		 * @date 2018-01-02
		 */
		buildSEQU_C_CP_FUND_ACC();

		buildSEQU_P_BI_FUND_ACC();
		
		buildSEQU_P_BI_ORG_LINKMAN();
		
		buildSEQU_S_DC_ADDRESS();//区域信息表
		buildSEQU_P_BI_ORG_ABROAD_INFO();
		buildSEQU_V_D_GROUP_DETAIL();
		buildSEQU_V_D_GROUP();
		buildSEQU_P_AB_PORT_ACC_RELA();
		buildSEQU_S_DAC_TYPE();
		buildSEQU_P_SV_SEC_BASE_PAYDAY();
		/**
		 * BUG #183314 【空库首次启动系统】执行升级组件报错  by liuyi
		 * 因为此表在baseinfo升级时有用到，但此表还未创建，所以移至baseinfo升级组件中创建
		 * 同时移动序列
		 */
		buildSEQU_P_AB_PORT_RELA();

		buildSEQU_P_BI_ACC_TREE();
		buildSEQU_P_AB_ACC_TREE_RELA();
		
		//STORY #65167需求|一般 配债的发行代码段，新增代码段不需要编写程序，支持前台配置
  		buildSEQU_S_SEC_TRANS();
  		
		//STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
  		buildSEQU_P_BI_ORG_LINK_RELA();
  		
  		//STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
		buildSEQU_P_AUTOMATIC_SET();
		
		//STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
		buildSEQU_P_AUTOMATIC_SET_PATH();
	}
	
	/**
	 * STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
	 * @throws Exception
	 */
	private void buildSEQU_P_AUTOMATIC_SET_PATH() throws Exception{
		builder.createSequence("SEQU_P_AUTOMATIC_SET_PATH").minvalue("1").maxvalue("999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "106083", "创建", "zhuziqing", "2021-06-01");
	}

	/**
	 * STORY #90952 【鹏华基金】券商结算模式下数据文件扫描节点判断规则
	 * @throws Exception
	 */
	private void buildSEQU_P_AUTOMATIC_SET() throws Exception {
		builder.createSequence("SEQU_P_AUTOMATIC_SET").minvalue("1").maxvalue("999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "90952", "创建", "yangze", "2020-12-24");
	}
	
	/**
	 * STORY #81326 【华宝兴业】关联结构设置 中联系信息维护调整
	 * @throws Exception
	 */
	private void buildSEQU_P_BI_ORG_LINK_RELA() throws Exception  {
		builder.createSequence("SEQU_P_BI_ORG_LINK_RELA").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "81326 ", "创建", "yangze", "2019-10-30");	
	}

	private void buildSEQU_V_D_GROUP() throws Exception {
		builder.createSequence("SEQU_V_D_GROUP").minvalue("1").maxvalue("9999999999999999999999999999").startWith("165")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_BI_FEE() throws Exception {
		builder.createSequence("SEQU_P_BI_FEE").minvalue("0").maxvalue("999999999999999999999999999").startWith("21")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DZ_TYPE() throws Exception {
		builder.createSequence("SEQU_S_DZ_TYPE").minvalue("1").maxvalue("999999999999999999999999").startWith("41")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_CL_INDEX() throws Exception {
		builder.createSequence("SEQU_S_CL_INDEX").minvalue("1").maxvalue("999999999999999999999999").startWith("41")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_INDEX_PARA_RELA() throws Exception {
		builder.createSequence("SEQU_S_INDEX_PARA_RELA").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_MKT_VAR() throws Exception {
		builder.createSequence("SEQU_S_MKT_VAR").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_IE_ITEM() throws Exception {
		builder.createSequence("SEQU_S_IE_ITEM").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_FIN_ITEM() throws Exception {
		builder.createSequence("SEQU_S_FIN_ITEM").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DE_EXP() throws Exception {
		builder.createSequence("SEQU_S_DE_EXP").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DSP_PARA() throws Exception {
		builder.createSequence("SEQU_S_DSP_PARA").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DVA_ITEM() throws Exception {
		builder.createSequence("SEQU_S_DVA_ITEM").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DAE_ELEM() throws Exception {
		builder.createSequence("SEQU_S_DAE_ELEM").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DAI_ITEM() throws Exception {
		builder.createSequence("SEQU_S_DAI_ITEM").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DTA_TD_ATTR() throws Exception {
		builder.createSequence("SEQU_S_DTA_TD_ATTR").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DT_TD_MODE() throws Exception {
		builder.createSequence("SEQU_S_DT_TD_MODE").minvalue("1").maxvalue("999999999999999999999999").startWith("21")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DAT_ASS_TYPE() throws Exception {
		builder.createSequence("SEQU_S_DAT_ASS_TYPE").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DA_SEC_VAR() throws Exception {
		builder.createSequence("SEQU_S_DA_SEC_VAR").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DC_CURY() throws Exception {
		builder.createSequence("SEQU_S_DC_CURY").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_ORG_MBR() throws Exception {
		builder.createSequence("SEQU_P_BI_ORG_MBR").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_CURY_PAIR() throws Exception {
		builder.createSequence("SEQU_P_BI_CURY_PAIR").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_SALES_NET() throws Exception {
		builder.createSequence("SEQU_P_BI_SALES_NET").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_SRC_SIGN() throws Exception {
		builder.createSequence("SEQU_P_BI_SRC_SIGN").minvalue("0").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_IE_RELA() throws Exception {
		builder.createSequence("SEQU_P_BI_IE_RELA").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_IE() throws Exception {
		builder.createSequence("SEQU_P_BI_IE").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_HDAY_SUB() throws Exception {
		builder.createSequence("SEQU_P_BI_HDAY_SUB").minvalue("0").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_HDAY() throws Exception {
		builder.createSequence("SEQU_P_BI_HDAY").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_MKT() throws Exception {
		builder.createSequence("SEQU_P_BI_MKT").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_AREA() throws Exception {
		builder.createSequence("SEQU_P_BI_AREA").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_PLATE() throws Exception {
		builder.createSequence("SEQU_P_BI_PLATE").minvalue("0").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_ORG() throws Exception {
		builder.createSequence("SEQU_P_BI_ORG").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_INDEX() throws Exception {
		builder.createSequence("SEQU_S_INDEX").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_INF_VAR() throws Exception {
		builder.createSequence("SEQU_S_INF_VAR").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_S_DF_FEE_RELA() throws Exception {
		builder.createSequence("SEQU_S_DF_FEE_RELA").minvalue("1").maxvalue("999999999999999999999999").startWith("881")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_CP_FUNDTYPE_RELA() throws Exception {
		builder.createSequence("SEQU_CP_FUNDTYPE_RELA")
		.minvalue("1")
		.maxvalue("9999999999999999999999999999")
		.startWith("1")
		.incrementBy("1")
		.cache("20")
		.build(UpdateType.REQUEST, "75453 ", "STORY #75453 【前海开源基金】-【基本参数】-【支付产品账户信息】--一个账户需要能设置多个账户类型信息", "zhangfengjun@ysstech.com", "2019-09-02");
	}
	
	private void buildSEQU_C_CP_FUND_ACC() throws Exception{
		builder.createSequence("SEQU_C_CP_FUND_ACC").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "账户主体卡信息表", "liangyilin", "2017-07-14");
	}
	
	private void buildSEQU_P_BI_FUND_ACC() throws Exception{
		builder.createSequence("SEQU_P_BI_FUND_ACC").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "账户主体卡信息表", "zhouning", "2017-07-14");
	}
	
	private void buildSEQU_P_BI_ORG_LINKMAN() throws Exception{
		builder.createSequence("SEQU_P_BI_ORG_LINKMAN").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "机构联系人表", "liangyilin", "2017-07-14");
	}

	private void buildSEQU_S_DC_ADDRESS() throws Exception{
		builder.createSequence("SEQU_S_DC_ADDRESS").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "区域信息表", "liangyilin", "2017-08-16");
	}
	private void buildSEQU_P_BI_ORG_ABROAD_INFO() throws Exception{
		builder.createSequence("SEQU_P_BI_ORG_ABROAD_INFO").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	private void buildSEQU_V_D_GROUP_DETAIL() throws Exception {
		builder.createSequence("SEQU_V_D_GROUP_DETAIL").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("608").incrementBy("1").cache("10").build(UpdateType.REQUEST, "00001", "");
	}
	private void buildSEQU_P_AB_PORT_ACC_RELA() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT_ACC_RELA").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "产品银行账户关联表", "zhouning", "2017-11-08");
	}
	
	private void buildSEQU_S_DAC_TYPE() throws Exception {
		builder.createSequence("SEQU_S_DAC_TYPE").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "产品银行账户关联表", "zhouning", "2017-11-09");
	}
	
	private void buildSEQU_P_AB_PORT_RELA() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT_RELA").minvalue("0").maxvalue("999999999999999999999999")
				.startWith("4037").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_ACC_TREE() throws Exception {
		builder.createSequence("SEQU_P_BI_ACC_TREE").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "62009", "【歌斐资产】新增支付账户信息群组界面的树形结构管理", "zhouning", "2017-11-12");
	}

	private void buildSEQU_P_AB_ACC_TREE_RELA() throws Exception {
		builder.createSequence("SEQU_P_AB_ACC_TREE_RELA").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "62009", "【歌斐资产】新增支付账户信息群组界面的树形结构管理", "zhouning", "2017-11-12");
	}
	
	private void buildSEQU_P_AB_BUSINESS_RANGE() throws Exception {
		builder.createSequence("SEQU_P_AB_BUSINESS_RANGE").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "72335", "华宝兴业：新增界面用于设置组合所属的投资范围的标签，将标签与流程绑定，以此判断组合所需要走的流程 ", "xiadeqi", "2019-05-17");
	}
	private void buildSEQU_P_SV_SEC_BASE_PAYDAY() throws Exception {
		builder.createSequence("SEQU_P_SV_SEC_BASE_PAYDAY").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "68666", "");
	}
	
	/**
	 * STORY #65167需求|一般 配债的发行代码段，新增代码段不需要编写程序，支持前台配置
	 * @throws Exception 
	 * @Desc
	 */
	private void buildSEQU_S_SEC_TRANS() throws Exception {
		builder.createSequence("SEQU_S_SEC_TRANS").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "65167 ", "创建", "xdl", "2019-08-17");
		
	}
}
