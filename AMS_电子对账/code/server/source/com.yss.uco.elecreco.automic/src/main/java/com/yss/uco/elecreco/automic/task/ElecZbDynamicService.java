package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.uco.elecreco.support.bean.ErSpecialZb;
import com.yss.uco.elecreco.support.service.IErSpecialZbService;

public class ElecZbDynamicService implements IAutomaticTaskDynamicService{
	
	/**
	 * 增加日志
	 */
	protected static final Logger logger = LogManager.getLogger(ElecZbDynamicService.class);

	@Override
	public List<TaskDynamicData> getParamDynamicData() {
		/* 构建需要的参数集合 */
		List<TaskDynamicData> taskDynamicDatas = new ArrayList<TaskDynamicData>();
		//创建顶级父节点
		taskDynamicDatas.add(createDynamicData("", "PARENT_GZB", "估值表", "[root]"));
		try {
			IErSpecialZbService erSpecialZbService = YssServiceFactory.getInstance().createService(IErSpecialZbService.class);
			List<ErSpecialZb> allData = erSpecialZbService.getAllData();
			if(allData != null)
			{
				Set<String> parent = new HashSet<String>();//二级节点，产品类型
				for (ErSpecialZb zb : allData) {
					if(!parent.contains(zb.getC_DAT_CLS()))
					{
						parent.add(zb.getC_DAT_CLS());
						//创建二级节点
						taskDynamicDatas.add(createDynamicData("", "PARENT_" + zb.getC_DAT_CLS(), zb.getC_DAT_CLS_NAME(), "PARENT_GZB"));
					}
					taskDynamicDatas.add(createDynamicData(zb.getC_KM_CODE(), zb.getC_KEY_CODE(), zb.getC_KEY_NAME(), "PARENT_" + zb.getC_DAT_CLS()));
				}
			}
		} catch (Exception e) {
			logger.error("获取特殊指标出错",e);
		}
		return taskDynamicDatas;
	}

	
	private TaskDynamicData createDynamicData(String disCode, String code, String name, String parentCode) {
		TaskDynamicData data = new TaskDynamicData();
		data.setDisCode(disCode);
		data.setCode(code);
		data.setName(name);
		data.setParentCode(parentCode);
		return data;
	}

}
