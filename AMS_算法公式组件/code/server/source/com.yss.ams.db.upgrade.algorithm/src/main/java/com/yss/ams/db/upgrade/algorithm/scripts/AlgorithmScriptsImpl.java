package com.yss.ams.db.upgrade.algorithm.scripts;

import com.yss.fast.db.upgrade.support.script.builder.ScriptBuilder;
import com.yss.fast.db.upgrade.support.script.enums.ScriptType;
import com.yss.fast.db.upgrade.support.script.enums.UpdateType;
import com.yss.fast.db.upgrade.support.service.ScriptDesc;

/**
 * 
 * @ClassName: AlgorithmScriptsImpl 
 * @Description: STORY #55342 算法公式管理组件独立
 * @author: mazhongyuan
 * @date: 2018年5月2日 下午7:53:44
 */
public class AlgorithmScriptsImpl implements ScriptDesc {

	@Override
	public ScriptBuilder getScriptBuilder() {
		// TODO Auto-generated method stub
		ScriptBuilder builder = new ScriptBuilder();
		this.buildStory55342(builder);
		
		return builder;
	}

	/**
	 * 
	* @Title: buildStory55342 
	* @Description: STORY #55342 算法公式管理组件独立 
	* @param builder void
	* @author mazhongyuan
	* @date 2018年5月3日下午1:19:53
	 */
	private void buildStory55342(ScriptBuilder builder) {
		builder.createScript(ScriptType.DATA)
		.addVersion("1.20.4.7.20180503")
		.addUpdateType(UpdateType.REQUEST)
		.addID("55342")
		.addSql("UPDATE T_S_FUN SET C_APP_CODE = 'ALGORITHM' WHERE C_FUN_CODE = 'AdvancedAlgorithm'")
		.endScript();
	}

}
