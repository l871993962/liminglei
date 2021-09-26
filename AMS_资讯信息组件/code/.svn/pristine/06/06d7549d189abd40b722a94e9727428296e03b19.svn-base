/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2017年5月8日杨海茗
**********************************************************************/

package com.yss.ams.db.upgrade.secinfo.structs.sequences;

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
		buildSEQU_D_MP_SEC_FXF();
		buildSEQU_P_SV_SEC_BASE();
		buildSEQU_P_AO_VAL_SUPP();
		buildSEQU_D_MP_PRE_STOCK();
		buildSEQU_D_SV_FI_PAY();
		buildSEQU_D_SV_FI_INCOME();
		buildSEQU_P_AB_PORT_SEC();
		buildSEQU_P_SV_SEC_FEEINFO();
		buildSEQU_D_MP_SEC_EQU();
		buildSEQU_D_MP_SEC_TRANSFER();
		buildSEQU_P_SV_SEC_SOLDBACK();
		buildSEQU_P_BI_PLATE_SUB();
		buildSEQU_P_SV_INDEX();
		buildSEQU_P_SV_INDEX_STOCK();
		buildSEQU_D_MP_SEC_MKT();
		buildSEQU_D_MP_SEC_FW();
		buildSEQU_D_MP_INDEX();
		buildSEQU_D_MP_ETF_SKEP_DETAIL();
		buildSEQU_D_MP_HG_MKT();
		buildSEQU_P_SV_SEC_BASE_PAYDAY();
	}
	
	private void buildSEQU_P_SV_SEC_BASE_PAYDAY() throws Exception {
		builder.createSequence("SEQU_P_SV_SEC_BASE_PAYDAY").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "68666", "");
	}
	
	private void buildSEQU_D_MP_SEC_FXF() throws Exception {
		builder.createSequence("SEQU_D_MP_SEC_FXF").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "36146", "");
	}
	
	private void buildSEQU_P_SV_SEC_BASE() throws Exception {
		builder.createSequence("SEQU_P_SV_SEC_BASE").minvalue("1").maxvalue("999999999999999999999999999")
				.startWith("429778").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_AO_VAL_SUPP() throws Exception {
		builder.createSequence("SEQU_P_AO_VAL_SUPP").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_PRE_STOCK() throws Exception {
		builder.createSequence("SEQU_D_MP_PRE_STOCK").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("66").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_SV_FI_PAY() throws Exception {
		builder.createSequence("SEQU_D_SV_FI_PAY").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("1000").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_SV_FI_INCOME() throws Exception {
		builder.createSequence("SEQU_D_SV_FI_INCOME").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("33917319").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_AB_PORT_SEC() throws Exception {
		builder.createSequence("SEQU_P_AB_PORT_SEC").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_SV_SEC_FEEINFO() throws Exception {
		builder.createSequence("SEQU_P_SV_SEC_FEEINFO").minvalue("1").maxvalue("999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_SEC_EQU() throws Exception {
		builder.createSequence("SEQU_D_MP_SEC_EQU").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("216439").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_SEC_TRANSFER() throws Exception{
		builder.createSequence("SEQU_D_MP_SEC_TRANSFER").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_SV_SEC_SOLDBACK() throws Exception {
		builder.createSequence("SEQU_P_SV_SEC_SOLDBACK").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("11").incrementBy("1").cache("10").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_BI_PLATE_SUB() throws Exception {
		builder.createSequence("SEQU_P_BI_PLATE_SUB").minvalue("0").maxvalue("999999999999999999999999999")
				.startWith("2381").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_SV_INDEX() throws Exception {
		builder.createSequence("SEQU_P_SV_INDEX").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("761").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_P_SV_INDEX_STOCK() throws Exception {
		builder.createSequence("SEQU_P_SV_INDEX_STOCK").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_SEC_MKT() throws Exception {
		builder.createSequence("SEQU_D_MP_SEC_MKT").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("13647097").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_SEC_FW() throws Exception {
		builder.createSequence("SEQU_D_MP_SEC_FW").minvalue("1").maxvalue("999999999999999999999999").startWith("21")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_INDEX() throws Exception {
		builder.createSequence("SEQU_D_MP_INDEX").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("149480").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_ETF_SKEP_DETAIL() throws Exception {
		builder.createSequence("SEQU_D_MP_ETF_SKEP_DETAIL").minvalue("1").maxvalue("999999999999999999999999")
				.startWith("18125961").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
	
	private void buildSEQU_D_MP_HG_MKT() throws Exception {
		builder.createSequence("SEQU_D_MP_HG_MKT").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("11048").incrementBy("1").cache("20").build(UpdateType.REQUEST, "00001", "");
	}
}
