package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.support.util.ElecrecoCondtionCons;

public class ElecRstCheckConditionDynamicService implements IAutomaticTaskDynamicService {

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		 /* 构建需要的参数集合 */
        List<TaskDynamicData> taskDynamicDatas = new ArrayList<TaskDynamicData>();
        TaskDynamicData dynamicData = new TaskDynamicData();
        dynamicData.setCode(ElecrecoCondtionCons.KEY_MC);
        dynamicData.setName("科目名称");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ElecrecoCondtionCons.KEY_SL);
        dynamicData.setName("数量");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ElecrecoCondtionCons.KEY_CB);
        dynamicData.setName("成本");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ElecrecoCondtionCons.KEY_SZ);
        dynamicData.setName("市值");
        taskDynamicDatas.add(dynamicData);
        
        dynamicData = new TaskDynamicData();
        dynamicData.setCode(ElecrecoCondtionCons.KEY_GZ);
        dynamicData.setName("估值增值");
        taskDynamicDatas.add(dynamicData);
        
        return taskDynamicDatas;
	}

}
