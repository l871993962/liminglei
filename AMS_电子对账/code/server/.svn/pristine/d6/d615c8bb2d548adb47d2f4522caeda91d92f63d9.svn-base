/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2016年12月11日雷建华
**********************************************************************/

package com.yss.ams.db.upgrade.elecreco.structs.sequences;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.SequenceBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * @ClassName OtherSequenceDescImpl
 * @Description
 * @author wulongxing@ysstech.com
 * @CreateDate 2017年05月04日下午4:26:51
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class SequenceDescImpl extends BaseStructDesc {

	private SequenceBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getSeqBuilder();
		buildSEQU_C_BW_MRFORWARD();
		
		buildSEQU_D_ER_SN();
		buildSEQU_D_ER_INFO();
		buildSEQU_D_ER_KM();
		buildSEQU_D_ER_GZ();
		buildSEQU_D_ER_DBLGZ();
		buildSEQU_D_ER_YE();
		buildSEQU_D_ER_LR();
		buildSEQU_D_ER_SYZQYBD();
		buildSEQU_D_ER_ZCFZ();
		buildSEQU_D_ER_JZCBD();
		buildSEQU_D_ER_RESULT();
		
		buildSEQU_D_ER_RELA();
		buildSEQU_S_DZ_TYPE();
		buildSEQU_Z_BI_RELA();
		buildSEQU_P_ER_REPCFG();
		buildSEQU_P_ER_REPCOLCFG();
		buildSEQU_D_ER_TMPL();
		buildSEQU_D_ER_TMPL_RELA();
		buildSEQU_D_ER_PARA();
		buildSEQU_D_ER_MRINFO();
		buildSEQU_D_ER_STEP_STATE();
		buildSEQU_Z_BI_PER_RELA();
		buildSEQU_D_ER_UN_PORT();
		buildSEQU_D_ER_RESVIEW();
		buildSEQU_D_ER_AUTOSTATE();
		buildSEQU_D_ER_REVE_RESRELA();
		buildSEQU_D_ER_REVE_KM_MAP();
		buildSEQU_D_ER_REVE_KM_RELA();
		buildSEQU_D_ER_REVE_ASS_MAP();
		buildSEQU_D_ER_REVE_ZB_MAP();
		buildSEQU_D_ER_REVE_INFO();
		buildSEQU_D_ER_REVE_RESULT();
		buildSEQU_D_ER_REVE_IGNORE();
		buildSEQU_D_ER_SPLIT_RELA();
		buildSEQU_D_ER_SPLIT_RULE();
		buildSEQU_D_ER_TASK();
		buildSEQU_D_ER_RPT_LOG();
		buildSEQU_D_ER_DSP_PARA();
		buildSEQU_D_ER_DSP_VALUE();
		buildSEQU_D_ER_DZ_TYPE();
		buildSEQU_D_ER_SPECIAL_ZB();
		buildSEQU_P_ER_TRANSREPCFG();
		buildSEQU_D_ER_XML_GZ();
	}
	
	/**
	 * STORY #103644 华夏基金-新增托管行电子对账结果数据查询视图 
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_XML_GZ() throws Exception {
		builder.createSequence("SEQU_D_ER_XML_GZ").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "103644", "华夏基金-新增托管行电子对账结果数据查询视图 ", "tongdengke@ysstech.com", "2021-04-12");
	}
	
	private void buildSEQU_P_ER_TRANSREPCFG() throws Exception {
		builder.createSequence("SEQU_P_ER_TRANSREPCFG").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "88316", "华夏基金-MOM产品个性化需求——场外和银行间交易数据发送托管行", "tongdengke@ysstech.com", "2020-06-08");
	}
	
	/**
	 * STORY81879【华宝基金】电子对账检查重要估值指标
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_SPECIAL_ZB() throws Exception {
		builder.createSequence("SEQU_D_ER_SPECIAL_ZB").minvalue("1").maxvalue("9999999999999999999999999999").startWith("1")
		.incrementBy("1").cache("10").build(UpdateType.REQUEST, "81879", "新建", "liwenzhou@ysstech.com", "2019-11-12");
	}
	
	/**
	 * STORY #72464 电子对账关于许可控制的功能模块改造 
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_DZ_TYPE() throws Exception {
		builder.createSequence("SEQU_D_ER_DZ_TYPE").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "72464", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	
	private void buildSEQU_D_ER_DSP_PARA() throws Exception {
		builder.createSequence("SEQU_D_ER_DSP_PARA").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "48560", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	private void buildSEQU_D_ER_DSP_VALUE() throws Exception {
		builder.createSequence("SEQU_D_ER_DSP_VALUE").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "48560", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	/**
	 * STORY69394电子对账报表配置模块新增电子对账报表类型：净资产变动表
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_JZCBD() throws Exception {
		builder.createSequence("SEQU_D_ER_JZCBD").minvalue("1").maxvalue("9999999999999999999999999999").startWith("1")
		.incrementBy("1").cache("10").build(UpdateType.REQUEST, "69394", "新建", "liwenzhou@ysstech.com", "2019-04-23");
	}

	private void buildSEQU_D_ER_RPT_LOG() throws Exception {
		builder.createSequence("SEQU_D_ER_RPT_LOG").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "61216", "新建", "liwenzhou@ysstech.com", "2018-12-24");
	}
	
	private void buildSEQU_D_ER_TASK() throws Exception {
		builder.createSequence("SEQU_D_ER_TASK").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "63787", "新建", "liwenzhou@ysstech.com", "2018-12-24");
	}
	private void buildSEQU_D_ER_SPLIT_RELA() throws Exception {
		builder.createSequence("SEQU_D_ER_SPLIT_RELA").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57828", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	
	private void buildSEQU_D_ER_SPLIT_RULE() throws Exception {
		builder.createSequence("SEQU_D_ER_SPLIT_RULE").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57828", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	
	private void buildSEQU_D_ER_AUTOSTATE() throws Exception{
		builder.createSequence("SEQU_D_ER_AUTOSTATE").minvalue("1").maxvalue("999999999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "54581", "新建", "wulongxing@ysstech.com", "2018-04-17");
	}
	private void buildSEQU_D_ER_RESVIEW() throws Exception{
		builder.createSequence("SEQU_D_ER_RESVIEW").minvalue("1").maxvalue("999999999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "41248", "新建", "liwenzhou@ysstech.com", "2018-04-17");
	}
	
	private void buildSEQU_D_ER_REVE_RESRELA() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_RESRELA").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_KM_MAP() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_KM_MAP").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_KM_RELA() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_KM_RELA").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_ASS_MAP() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_ASS_MAP").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_ZB_MAP() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_ZB_MAP").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_INFO() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_INFO").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_RESULT() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_RESULT").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}


	private void buildSEQU_D_ER_REVE_IGNORE() throws Exception {
		builder.createSequence("SEQU_D_ER_REVE_IGNORE").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
			.cache("20").build(UpdateType.REQUEST, "57239", "新建", "liwenzhou@ysstech.com", "2018-07-17");
	}
	private void buildSEQU_Z_BI_PER_RELA() throws Exception{
		builder.createSequence("SEQU_Z_BI_PER_RELA").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "41535", "新建", "songdabang@ysstech.com", "2018-02-12");
	}
	
	private void buildSEQU_D_ER_STEP_STATE() throws Exception {
		builder.createSequence("SEQU_D_ER_STEP_STATE").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
				.cache("20").build(UpdateType.REQUEST, "50374", "新建", "songdabang@ysstech.com", "2018-01-24");
	}
	
	private void buildSEQU_C_BW_MRFORWARD() throws Exception {
		builder.createSequence("SEQU_C_BW_MRFORWARD").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
				.cache("20").build(UpdateType.REQUEST, "32891", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	/**
	 * BUG #297231 电子对账报文序号，C_IDEN非顺序递增，导致查询电子对账管理界面，最后一次生成查询条件
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_SN() throws Exception {
		builder.createSequence("SEQU_D_ER_SN").minvalue("1").maxvalue("99999").startWith("1").incrementBy("1")
				//增加循环
				.setCycle(true)
				//增加递增
				.setOrder(true)
				.cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_LR() throws Exception {
		builder.createSequence("SEQU_D_ER_LR").minvalue("1").maxvalue("9999999999999999999999999999").startWith("1")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_SYZQYBD() throws Exception {
		builder.createSequence("SEQU_D_ER_SYZQYBD").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("10").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_TMPL() throws Exception {
		builder.createSequence("SEQU_D_ER_TMPL").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_TMPL_RELA() throws Exception {
		builder.createSequence("SEQU_D_ER_TMPL_RELA").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_ZCFZ() throws Exception {
		builder.createSequence("SEQU_D_ER_ZCFZ").minvalue("1").maxvalue("9999999999999999999999999999").startWith("1")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_YE() throws Exception {
		builder.createSequence("SEQU_D_ER_YE").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("2039063").incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_RESULT() throws Exception {
		builder.createSequence("SEQU_D_ER_RESULT").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("330071").incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_KM() throws Exception {
		builder.createSequence("SEQU_D_ER_KM").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("2155788").incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_RELA() throws Exception {
		builder.createSequence("SEQU_D_ER_RELA").minvalue("1").maxvalue("9999999999999999999999999999").startWith("403")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	/**
	 * BUG #297231 电子对账报文序号，C_IDEN非顺序递增，导致查询电子对账管理界面，最后一次生成查询条件
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_INFO() throws Exception {
		builder.createSequence("SEQU_D_ER_INFO").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("7964").incrementBy("1")
				//增加递增
				.setOrder(true).cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_S_DZ_TYPE() throws Exception {
		builder.createSequence("SEQU_S_DZ_TYPE").minvalue("1").maxvalue("999999999999999999999999").startWith("41")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}

	private void buildSEQU_D_ER_GZ() throws Exception {
		builder.createSequence("SEQU_D_ER_GZ").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("897996").incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	/**
	 * STORY74798双估值报表电子对账功能
	 * @throws Exception
	 */
	private void buildSEQU_D_ER_DBLGZ() throws Exception {
		builder.createSequence("SEQU_D_ER_DBLGZ").minvalue("1").maxvalue("9999999999999999999999999999")
				.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "74798", "新建", "liwenzhou@ysstech.com", "2019-06-23");
	}
	private void buildSEQU_Z_BI_RELA() throws Exception {
		builder.createSequence("SEQU_Z_BI_RELA").minvalue("1").maxvalue("999999999999999999999999").startWith("1206")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "000001", "新建", "wulongxing@ysstech.com", "2017-05-05");
	}
	
	private void buildSEQU_P_ER_REPCFG() throws Exception {
		builder.createSequence("SEQU_P_ER_REPCFG").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "36615", "南方基金-系统需要支持存在多个财务报表的电子对账 ", "wulongxing@ysstech.com", "2017-05-05");
	}
	private void buildSEQU_P_ER_REPCOLCFG() throws Exception {
		builder.createSequence("SEQU_P_ER_REPCOLCFG").minvalue("1").maxvalue("999999999999999999999999").startWith("1")
				.incrementBy("1").cache("20").build(UpdateType.REQUEST, "59739", "【易方达基金】【紧急加急】财务报表电子对账生成应该同财务报表配置报错一致 ", "liwenzhoug@ysstech.com", "2018-10-19");
	}
	/**
	 * @Description: STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @author wulongxing 
	 * @date 2017年6月21日 下午5:02:53
	 */
	private void buildSEQU_D_ER_PARA() throws Exception {
		builder.createSequence("SEQU_D_ER_PARA").minvalue("1").maxvalue("99999999999999999999999").startWith("1")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "42660", "【中国银行】深证通伺服器要求采用热备模式", "wulongxing@ysstech.com", "2017-06-12");
	}
	/**
	 * @Description: STORY42660【中国银行】深证通伺服器要求采用热备模式
	 * @author wulongxing 
	 * @date 2017年6月21日 下午5:03:05
	 */
	private void buildSEQU_D_ER_MRINFO() throws Exception {
		builder.createSequence("SEQU_D_ER_MRINFO").minvalue("1").maxvalue("99999999999999999999999").startWith("1")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "42660", "【中国银行】深证通伺服器要求采用热备模式", "wulongxing@ysstech.com", "2017-06-12");
	}
	/**
	 * @Description: STORY #50374 电子对账功能优化 不对账组合表
	 * @author ouyangkang 
	 * @date 2018年3月7日 09:15:42
	 */
	private void buildSEQU_D_ER_UN_PORT() throws Exception {
		builder.createSequence("SEQU_D_ER_UN_PORT").minvalue("1").maxvalue("99999999999999999999999").startWith("1")
				.incrementBy("1").cache("10").build(UpdateType.REQUEST, "50374", "新建", "ouyangkang@ysstech.com", "2018-03-07");
	}
}
