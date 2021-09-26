package com.yss.ams.db.upgrade.algorithm.structs.sequences;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.SequenceBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

public class SequenceDescImpl extends BaseStructDesc {

	private SequenceBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getSeqBuilder();
		
		//builderT_V_AA_ADV_ALGO_DESC();
		//builderT_V_AA_ADV_ALGO_ZH();
	}

	/**
	 * 
	* @Title: builderT_V_AA_ADV_ALGO_ZH 
	* @Description: 
	* @throws Exception void
	* @author mazhongyuan
	* @date 2018年5月2日下午2:13:05
	 */
	private void builderT_V_AA_ADV_ALGO_ZH() throws Exception {
		builder.createSequence("SEQUE_V_AA_ADV_ALGO_ZH").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "55342", "新建", "mazhongyuan", "2018-05-02");
}

	/**
	 * 
	* @Title: builderT_V_AA_ADV_ALGO_DESC 
	* @Description: 
	* @throws Exception void
	* @author mazhongyuan
	* @date 2018年5月2日下午2:13:14
	 */ 
	private void builderT_V_AA_ADV_ALGO_DESC() throws Exception {
		builder.createSequence("SEQUE_V_AA_ADV_ALGO_DESC").minvalue("1").maxvalue("999999999999999999").startWith("1").incrementBy("1")
		.cache("20").build(UpdateType.REQUEST, "553421", "新建", "mazhongyuan", "2018-05-02");
}

}
