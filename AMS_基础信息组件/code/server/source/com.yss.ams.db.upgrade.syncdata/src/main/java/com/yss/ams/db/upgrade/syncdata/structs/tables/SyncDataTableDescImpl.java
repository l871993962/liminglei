package com.yss.ams.db.upgrade.syncdata.structs.tables;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;

/**
 * @ClassName SyncDataTableDescImpl
 * @Description
 * @author chenyoucai@ysstech.com
 * @CreateDate 20180626
 */
public class SyncDataTableDescImpl extends BaseStructDesc {
	private TableBuilder tableBuilder = null;

	@Override
	public void execute() throws Exception {
		tableBuilder = getTableBuilder();

		//STORY57889【数据管理】数据同步、同步日志
		buildT_D_OD_SYNC_MQ();
		buildT_D_OD_SYNC_MQ_CFG();
	}
	
	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 */
	private void buildT_D_OD_SYNC_MQ() throws Exception{
		tableBuilder.createTable("T_D_OD_SYNC_MQ","消息同步消息表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "")
		.addColumn("C_DV_STATE", OraColunmnTypeOnlyLength.VARCHAR2, 50,false, "' '", "消息状态")
		.addColumn("C_RECEIVE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20,false, "'1900-01-01 00:00:00'", "接受时间")
		.addColumn("C_SENDER", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "发送人")
		.addColumn("C_SEND_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20,false, "'1900-01-01 00:00:00'", "发送时间")
		.addColumn("C_SYSTEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50,false, "' '", "业务系统")
		.addColumn("C_DV_MODULE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "功能模块")
		.addColumn("C_DV_OPER_TYPE", OraColunmnTypeOnlyLength.VARCHAR2, 50,false, "' '", "操作类型")
		.addColumn("C_DATA_ID", OraColunmnTypeOnlyLength.VARCHAR2, 30,false, "' '", "数据ID")
		.addColumn("C_MESSAGE", OraColunmnTypeOnlyLength.VARCHAR2, 4000, false, "' '", "主题消息")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 30,true, "", "操作人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20,true, "", "操作时间")
		.addPrimaryConstraint("PK_D_OD_SYNC_MQ", "C_IDEN")
		.build(UpdateType.REQUEST, "57889", "新增","chenyoucai","2018-06-26");
	}	
	
	/**
	 * STORY57889【数据管理】数据同步、同步日志
	 */
	private void buildT_D_OD_SYNC_MQ_CFG() throws Exception{
		tableBuilder.createTable("T_D_OD_SYNC_MQ_CFG","消息同步模块配置表")
		.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 30, false, "", "")
		.addColumn("C_SYSTEM_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50,false, "' '", "业务系统")
		.addColumn("C_MODULE_CODE", OraColunmnTypeOnlyLength.VARCHAR2, 50,false, "' '", "功能模块")
		.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "' '", "操作人")
		.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2, 20,false, "'1900-01-01 00:00:00'", "操作时间")
		.addPrimaryConstraint("PK_D_OD_SYNC_MQ_CFG", "C_IDEN")
		.createUniqueIndex("IDX_D_OD_SYNC_MQ_CFG", "C_SYSTEM_CODE, C_MODULE_CODE")
		.build(UpdateType.REQUEST, "57889", "新增","chenyoucai","2018-06-26");
	}	
	
}
