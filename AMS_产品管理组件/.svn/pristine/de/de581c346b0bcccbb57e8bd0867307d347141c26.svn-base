/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2016年11月16日雷建华
**********************************************************************/

package com.yss.ams.db.upgrade.productinfo.structs.sequences;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.SequenceBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * @ClassName YssUCOSequenceDescImpl
 * @Description
 * @author leijianhua@ysstech.com
 * @CreateDate 2016年11月16日下午5:17:08
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class ProductInfoSequenceDescImpl extends BaseStructDesc {

	private SequenceBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getSeqBuilder();
		
		buildSEQU_P_AB_PORT();
		/**
		 * BUG #183314 【空库首次启动系统】执行升级组件报错  by liuyi
		 * 因为此表在baseinfo升级时有用到，但此表还未创建，所以移至baseinfo升级组件中创建
		 * 同时移动序列
		 */
		//buildSEQU_P_AB_PORT_RELA();
		buildSEQU_P_AB_PORT_PD();
		buildSEQU_P_AA_PORT_CLS();
		buildSEQU_P_AB_ASS_TR();
		buildSEQU_P_AB_ASS_TR_SUB();
		//STORY #72829 资产结构新增仅包含“存续期+待发行”的组合  add by yangru 20200529
		buildSEQU_P_AB_ASS_TR_RULE();
		buildSEQU_P_AB_GROUP_RELA();
		buildSEQU_P_AB_GROUP();
		buildSEQU_C_CP_PUB_ACC();
		
		//STORY #85993 产品参数复制从FAST迁移到产品管理组件
		buildSEQU_S_DATA_COPY();
		buildSEQU_S_DATA_COPY_CUSTOM();
		
		////组合自定义设置
		buildSEQU_P_AB_PORT_CUSTOM();
		
	}
	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @throws Exception
	 */
	private void buildSEQU_S_DATA_COPY() throws Exception {
		builder.createSequence("SEQU_S_DATA_COPY").minvalue("1").maxvalue("999999999999999999999999").startWith("81")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	/**
	 * STORY #85993 产品参数复制从FAST迁移到产品管理组件
	 * @throws Exception
	 */
	private void buildSEQU_S_DATA_COPY_CUSTOM() throws Exception {
		builder.createSequence("SEQU_S_DATA_COPY_CUSTOM").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("181").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_PORT() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT").minvalue("0").maxvalue("999999999999999999999999").startWith("3403")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_PORT_RELA() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT_RELA").minvalue("0").maxvalue("999999999999999999999999")
				.startWith("4037").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_PORT_PD() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT_PD").minvalue("1").maxvalue("999999999999999999999999999")
				.startWith("321").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AA_PORT_CLS() throws Exception {
		builder.createSequence("SEQU_P_AA_PORT_CLS").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("1870").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	private void buildSEQU_P_AB_ASS_TR() throws Exception {
		builder.createSequence("SEQU_P_AB_ASS_TR").minvalue("0").maxvalue("999999999999999999999999999")
				.startWith("1027").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_ASS_TR_SUB() throws Exception {
		builder.createSequence("SEQU_P_AB_ASS_TR_SUB").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("4163").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_AB_ASS_TR_RULE() throws Exception {
		builder.createSequence("SEQU_P_AB_ASS_TR_RULE").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("4163").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_GROUP_RELA() throws Exception {
		builder.createSequence("SEQU_P_AB_GROUP_RELA").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("2517").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_GROUP() throws Exception {
		builder.createSequence("SEQU_P_AB_GROUP").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("221").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_C_CP_PUB_ACC() throws Exception {
		builder.createSequence("SEQU_C_CP_PUB_ACC").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}

	private void buildSEQU_P_AB_PORT_CUSTOM() throws Exception{
		builder.createSequence("SEQU_P_AB_PORT_CUSTOM").minvalue("1").maxvalue("1E24").startWith("1").incrementBy("1").cache("10")
		.build(UpdateType.REQUEST, "000001", "组合自定义设置", "jinghehe", "2017-8-16");
	}
	
	
}
