/*********************************************************************
Title：
Description：
Copyright：Copyright @ 2001-2017 Ysstech,All Rights Reserved
2016年11月16日雷建华
**********************************************************************/

package com.yss.ams.db.upgrade.syncdata.structs.sequences;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.SequenceBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;

/**
 * @ClassName SyncDataSequenceDescImpl
 * @Description
 * @author chenyoucai@ysstech.com
 * @CreateDate 20180626
 */
public class SyncDataSequenceDescImpl extends BaseStructDesc {

	private SequenceBuilder builder = null;

	@Override
	public void execute() throws Exception {
		builder = getSeqBuilder();
  		//STORY57889【数据管理】数据同步、同步日志
  		buildSEQU_D_OD_SYNC_MQ(); 
  		buildSEQU_D_OD_SYNC_MQ_CFG(); 
	}
	
	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 * @throws Exception
	 */
	private void buildSEQU_D_OD_SYNC_MQ() throws Exception{
		builder.createSequence("SEQU_D_OD_SYNC_MQ").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "57889", "创建", "chenyoucai", "2018-06-26");	
	}
	
	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 * @throws Exception
	 */
	private void buildSEQU_D_OD_SYNC_MQ_CFG() throws Exception{
		builder.createSequence("SEQU_D_OD_SYNC_MQ_CFG").minvalue("1").maxvalue("9999999999999999999999999999")
		.startWith("1").incrementBy("1").cache("20").build(UpdateType.REQUEST, "57889", "创建", "chenyoucai", "2018-06-26");	
	}

}
