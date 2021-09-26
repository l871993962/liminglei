package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.automic.cons.ParamDZCons;

public class ElecEndWhereDynamicService implements IAutomaticTaskDynamicService {

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		 /* 构建需要的参数集合 */
        List<TaskDynamicData> taskDynamicDatas = new ArrayList<TaskDynamicData>();
        TaskDynamicData dynamicData = new TaskDynamicData();
        dynamicData.setCode(ParamDZCons.RESULT_SAME);
        dynamicData.setName("对账一致");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ParamDZCons.RESULT_DIFF);
        dynamicData.setName("对账不一致");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ParamDZCons.RESULT_NO);
        dynamicData.setName("无结果");
        taskDynamicDatas.add(dynamicData);
        return taskDynamicDatas;
	}

}
