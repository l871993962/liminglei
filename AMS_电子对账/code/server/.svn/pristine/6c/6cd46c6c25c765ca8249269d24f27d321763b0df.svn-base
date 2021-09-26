package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;
/**
 * 
 * @author wlx
 * STORY72797华宝兴业：检查对账结果节点新增参数判断是否检查最新的对账结果以及是否需要等待对账结果
 */
public class ElecResultParamService implements IAutomaticTaskDynamicService{

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		/* 构建需要的参数集合 */
		List<TaskDynamicData> taskDynamicDatas = new ArrayList<TaskDynamicData>();
		// BUG #297532 【对账结果检查（特殊指标）】节点中参数“最新对账结果依据” 需改成默认值，且置灰不可编辑
		TaskDynamicData dynamicData = new TaskDynamicData();
		dynamicData.setCode(ParamDZCons.DZ_TIME_NEW);
		dynamicData.setName("对账时间最新（最后一次接收）");
		taskDynamicDatas.add(dynamicData);

		TaskDynamicData dynamicData1 = new TaskDynamicData();
		dynamicData1.setCode(ParamDZCons.PORT_MV_NEW);
		dynamicData1.setName("资产净值市值最新且最后一次接收");
		taskDynamicDatas.add(dynamicData1);
		
		TaskDynamicData dynamicData2 = new TaskDynamicData();
		dynamicData2.setCode(ParamDZCons.PORT_MV_NEW_SC);
		dynamicData2.setName("资产净值市值最新且最后一次生成");
		taskDynamicDatas.add(dynamicData2);
		return taskDynamicDatas;
	}

}
