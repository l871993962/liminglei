package com.yss.ams.db.upgrade.algorithm.structs.tables;

import com.yss.fast.db.upgrade.support.api.BaseStructDesc;
import com.yss.fast.db.upgrade.support.api.TableBuilder;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNeedPrecision;
import com.yss.fast.db.upgrade.support.struct.enums.OraColumnTypeNoLength;
import com.yss.fast.db.upgrade.support.struct.enums.OraColunmnTypeOnlyLength;

/**
 * 
 * @ClassName: AlgorithmTableDescImpl 
 * @Description: STORY #55342 算法公式管理组件独立
 * @author: mazhongyuan
 * @date: 2018年5月2日 下午7:54:58
 */
public class AlgorithmTableDescImpl extends BaseStructDesc {

	private TableBuilder tableBuilder = null;
	
	@Override
	public void execute() throws Exception {
		tableBuilder = getTableBuilder();
		// STORY #55342 算法公式管理组件独立
		builderT_V_AA_ADV_ALGO_DESC();
		// STORY #55342 算法公式管理组件独立
		builderT_V_AA_ADV_ALGO_ZH();
		// 分布式改造，数据库表解耦  算法公式表从FAST迁移至估值中
		buildT_V_AA_ADV_ALGO();
		
	}

	
	private void builderT_V_AA_ADV_ALGO_ZH() throws Exception {
		tableBuilder.createTable("T_V_AA_ADV_ALGO_ZH")
		.addColumn("c_algo_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "算法code")
		.addColumn("c_formula", OraColumnTypeNoLength.CLOB, false, "' '", "算法中文形式")
		.build(UpdateType.REQUEST, "31713", "算法公式配置优化", "马向峰", "20170908");
	}

	private void builderT_V_AA_ADV_ALGO_DESC() throws Exception {
		tableBuilder.createTable("T_V_AA_ADV_ALGO_DESC")
		.addColumn("c_algo_code", OraColunmnTypeOnlyLength.VARCHAR2, 50, false, "", "算法code")
		.addColumn("c_desc", OraColumnTypeNoLength.CLOB, false, "' '", "算法描述")
		.build(UpdateType.REQUEST, "31713", "算法公式配置优化", "马向峰", "20170908");
	}
	
	/**
	 * 高级算法设置 #comment#
	 * 
	 * @author caobin
	 * @throws Exception
	 */
	private void buildT_V_AA_ADV_ALGO() throws Exception {
		tableBuilder
				.createTable("T_V_AA_ADV_ALGO", "高级算法设置")
				.addColumn("C_ALGO_CODE", OraColunmnTypeOnlyLength.VARCHAR2,
						50, false, "' '", "算法代码")
				.addColumn("C_ALGO_NAME", OraColunmnTypeOnlyLength.VARCHAR2,
						100, false, "' '", "算法名称")
				.addColumn("C_DESC", OraColunmnTypeOnlyLength.VARCHAR2, 2000,
						true, "", "描述")
				.addColumn("N_CHECK_STATE", OraColumnTypeNeedPrecision.NUMBER,
						3, 0, false, "0 ", "审核状态")
				.addColumn("C_UPDATE_BY", OraColunmnTypeOnlyLength.VARCHAR2,
						20, false, "' '", "更新人")
				.addColumn("C_UPDATE_TIME", OraColunmnTypeOnlyLength.VARCHAR2,
						20, false, "' '", "更新时间")
				.addColumn("C_CHECK_BY", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						true, "", "审核人")
				.addColumn("C_CHECK_TIME", OraColunmnTypeOnlyLength.VARCHAR2,
						20, true, "", "审核时间")
				.addColumn("C_ALGO_FORMULA_TRANSFORM",
						OraColunmnTypeOnlyLength.VARCHAR2, 4000, false, "' '",
						"算法公式转换")
				.addColumn("C_DV_ALGO_TYPE", OraColunmnTypeOnlyLength.VARCHAR2,
						20, false, "' '", "算法类型")
				.addColumn("C_IDEN", OraColunmnTypeOnlyLength.VARCHAR2, 20,
						false, "", "自动ID")
				.addColumn("C_ALGO_FORMULA", OraColumnTypeNoLength.CLOB, true,
						"", "算法公式")
				.addPrimaryConstraint("PK_V_AA_ADV_ALGO", "C_IDEN")
				.createUniqueIndex("IDX_V_AA_ADV_ALGO", "C_ALGO_CODE")
				.build(UpdateType.REQUEST, "000001", "新建",
						"caobin@ysstech.com", "2017-05-03");
	}

}
