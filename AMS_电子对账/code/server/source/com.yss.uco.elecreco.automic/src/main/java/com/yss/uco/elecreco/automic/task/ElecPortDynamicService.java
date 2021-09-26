package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.framework.api.util.VocabularyConsts;
import com.yss.right.pojo.DataRight;
import com.yss.right.service.IFastDataRightService;

/**
 * 
 * @ClassName AutomaticTaskPortDynamicService
 * @Description 自动导入机器人消息监听任务组合参数动态数据源实现类
 * @author zhoushuhang@ysstech.com
 * @CreateDate 2018年7月23日
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 * @Task STORY57962新增检查任务节点：净值确认，对帐结果，具体文件到达
 */
public class ElecPortDynamicService implements
		IAutomaticTaskDynamicService {

	/**
	 * 组合权限数据类型
	 */
	private static final String DATA_TYPE = "1";

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		List<TaskDynamicData> dataList = new ArrayList<TaskDynamicData>();
		// 定义权限数据源接口
		IFastDataRightService service = YssServiceFactory.getInstance()
				.createService(IFastDataRightService.class);
		List<DataRight> rightList = service.query(DATA_TYPE);
		Map<String, String> map = new HashMap<String, String>();
		if (null != rightList && rightList.size() > 0) {
			for (DataRight dataRight : rightList) {
				if (map.containsKey(dataRight.getC_DATA_CODE())) {
					continue;
				}
				dataList.add(dataRightTransDynamicData(dataRight));
				map.put(dataRight.getC_DATA_CODE(), VocabularyConsts.Blank);
			}
		}

		map.clear();
		return dataList;
	}

	/**
	 * @Title dataRightTransDynamicData
	 * @Description 将组合数据权限信息装换成参数动态数据
	 * @author zhoushuhang@ysstech.com
	 * @date 2018年5月28日
	 * @param dataRight
	 * @return TaskDynamicData
	 */
	private TaskDynamicData dataRightTransDynamicData(DataRight dataRight) {
		TaskDynamicData data = new TaskDynamicData();
		data.setCode(dataRight.getC_DATA_CODE());
		data.setName(dataRight.getC_DATA_NAME());
		data.setParentCode(dataRight.getC_DATA_CODE_P());
		return data;
	}
}
