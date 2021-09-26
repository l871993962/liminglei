package com.yss.uco.elecreco.automic.task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.task.api.IAutomaticTaskDynamicService;
import com.yss.framework.api.task.xmlPojo.TaskDynamicData;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.file.XmlProcessor;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ElecGroupRela;

/**
 * @ClassName ElecGeneTaskDynamicService
 * @Description 统计分析处理自动化任务数据提供类
 * @author wulongxing@ysstech.com
 * @CreateDate 2018年3月16日
 * @Version V4.5.0.1
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 * @Task STORY53584估值自动化-产生电子对账的功能需要注册到自动化平台
 */
public class ElecGeneTaskDynamicService implements
		IAutomaticTaskDynamicService {
	/**
	 * 增加日志
	 */
	protected static final Logger logger = LogManager.getLogger(ElecGeneTaskDynamicService.class);
	@SuppressWarnings("unchecked")
	@Override
	public List<TaskDynamicData> getParamDynamicData() {

		/* 构建需要的参数集合 */
		List<TaskDynamicData> taskDynamicDatas = new ArrayList<TaskDynamicData>();

		FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.YSSPERIPHERAL);
		String fileName = fileUtil.getFilePath() + YssConstant.YSSPERIPHERAL_ERCONFIG;
		XmlProcessor processor = new XmlProcessor(fileName);
		try {
			Document doc = processor.getDocument();
			List<Element> eleList = doc.getRootElement().elements();
			for (Iterator<Element> iterator = eleList.iterator(); iterator.hasNext();) {
				Element ele = iterator.next();
				// 获取每个group节点
				ElecGroupRela parentElecGroupRela = new ElecGroupRela();
				parseElement(parentElecGroupRela, ele);
				TaskDynamicData dynamicData = new TaskDynamicData();
				dynamicData.setParentCode("[root]");
				dynamicData.setCode(parentElecGroupRela.getC_ELEC_CODE());
				dynamicData.setName(parentElecGroupRela.getC_ELEC_NAME());
				taskDynamicDatas.add(dynamicData);
				// 获取group下的子节点
				for (Iterator<Element> childEleIter = ele.elements().iterator(); childEleIter.hasNext();) {
					Element childEle = childEleIter.next();
					ElecGroupRela childElecGroupRela = new ElecGroupRela();
					parseElement(childElecGroupRela, childEle);
					dynamicData = new TaskDynamicData();
					dynamicData.setParentCode(parentElecGroupRela.getC_ELEC_CODE());
					dynamicData.setCode(childElecGroupRela.getC_ELEC_CODE());
					dynamicData.setName(childElecGroupRela.getC_ELEC_NAME());
					taskDynamicDatas.add(dynamicData);
					
				}
			}
		} catch (Exception e) {
			logger.error("获取对账类型出错",e);
		}

		return taskDynamicDatas;
	}
	
	@SuppressWarnings("unchecked")
	private void parseElement(ElecGroupRela elecGroupRela, Element element) {
		for (Iterator<Attribute> attIter = element.attributeIterator(); attIter
				.hasNext();) {
			Attribute attr = attIter.next();
			if ("id".equalsIgnoreCase(attr.getName())) {
//				if(attr.getValue() != null && attr.getValue().indexOf("_") > 0){
//				elecGroupRela.setC_ELEC_CODE(attr.getValue().split("_", -1)[1]);
//			}else{
//				elecGroupRela.setC_ELEC_CODE(attr.getValue());
//			}
			elecGroupRela.setC_ELEC_CODE(attr.getValue());
			} else if ("name".equalsIgnoreCase(attr.getName())) {
				elecGroupRela.setC_ELEC_NAME(attr.getValue());
			} else if ("resultId".equalsIgnoreCase(attr.getName())) {
				elecGroupRela.setC_RESULT_CODE(attr.getValue());
			}
		}
	}

}
