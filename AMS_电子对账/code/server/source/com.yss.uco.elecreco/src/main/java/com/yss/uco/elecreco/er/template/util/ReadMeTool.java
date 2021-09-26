package com.yss.uco.elecreco.er.template.util;

import java.io.File;
import java.net.URL;

import org.eclipse.core.runtime.FileLocator;

import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.uco.elecreco.activator.ErActivator;
import com.yss.uco.elecreco.er.template.pojo.Deploy;

/**
 * 模板基础信息
 * 
 * @author liuxiang
 * @date 2015年2月12日
 */
public class ReadMeTool {
	
	public static final String DEPLOY_FILE = "deploy.xml";
	
	public static final String DEPLOY_SCHEMA_FILE = "META-INF/config/schema/deploy.xsd";
	
	/**
	 * 读说明文档
	 * 
	 * @param fileName
	 *            文档名称
	 * @return 内容字典
	 */
	public static Deploy readDeploy(String fileName) throws Exception {
		JAXBProcessor jaxbPro = new JAXBProcessor();
		Deploy xmlBean = new Deploy();
		File fileReadme = new File(fileName);
		URL runtimeURL = null;
		try{
		URL url = YssContextFactory.getInstance().getBundleContext(ErActivator.class).getBundle().getEntry(DEPLOY_SCHEMA_FILE);
		
		if (url != null) {
			runtimeURL = FileLocator.resolve(url);
		}else{
			throw new Exception(DEPLOY_SCHEMA_FILE + "文件不存在！");
		}
		
		if (fileReadme.exists())
			return (Deploy) jaxbPro.unMarshal(xmlBean, fileReadme, runtimeURL);
		}catch(Exception ex){
			if(ex instanceof YssException){
				throw ex;
			}else{
				throw new YssException(ex);
			}
		}
		return null;
	}
	
	/**
	 * 读说明文档
	 * 
	 * @param fileName
	 *            文档名称
	 * @return 内容字典
	 */
	public static Deploy readDeploy2(String fileName) throws Exception {
		JAXBProcessor jaxbPro = new JAXBProcessor();
		Deploy xmlBean = new Deploy();
		File fileReadme = new File(fileName);
		try{
		if (fileReadme.exists())
			return (Deploy) jaxbPro.unMarshal(xmlBean, fileReadme);
		}catch(Exception ex){
			if(ex instanceof YssException){
				throw ex;
			}else{
				throw new YssException(ex);
			}
		}
		return null;
	}
	
}
