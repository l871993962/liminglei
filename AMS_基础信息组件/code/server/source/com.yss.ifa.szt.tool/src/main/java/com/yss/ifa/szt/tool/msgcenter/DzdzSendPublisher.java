package com.yss.ifa.szt.tool.msgcenter;

import java.io.File;

import org.dom4j.Document;
import org.dom4j.Element;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.util.LocalFilePathUtil;
import com.yss.framework.api.util.file.XmlProcessor;
import com.yss.framework.msg.TopicPublisher;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.msg.mq.TopicNames;

public class DzdzSendPublisher extends TopicPublisher{
	
	public DzdzSendPublisher(TopicNames topicName) throws MessageQueueException {
		super(topicName);
	}

	@Override
	public String loadConfig() throws MessageQueueException {
		LocalFilePathUtil fileUtil = new LocalFilePathUtil(YssConstant.GLOABL_PATH);
		String path = fileUtil.getFilePath() + YssConstant.GLOBAL_MQ_XML;
		Document doc = null;
		String url = "";
		try {
			File f = new File(path);
			if(f.exists()){
				doc = new XmlProcessor(path).getDocument();
				Element element = doc.getRootElement();
				Element server = element.element("yssmr-server");
				if(server == null){//mq.xml没有配置伺服器 BUG #136431 南方基金-估值系统端口非11911时深证通伺服器无法初始化 
					server = element.element("mq-server");
					if(server == null){
						throw new MessageQueueException("mq.xml配置文件尚未配置");
					}
				}
				url = server.attributeValue("address").split(",")[0];
			}else{
				throw new MessageQueueException("找不到【" + path + "】配置文件,请检查");
			}
		} catch (MessageQueueException ex) {
			throw ex;
		}
		
		return url;
	
	}

	
}
