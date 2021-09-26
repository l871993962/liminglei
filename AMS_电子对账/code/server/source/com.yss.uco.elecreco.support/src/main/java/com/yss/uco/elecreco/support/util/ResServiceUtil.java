package com.yss.uco.elecreco.support.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.cxf.helpers.IOUtils;

import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.webservice.pojo.WebService;
import com.yss.framework.api.webservice.service.IWebserviceService;
import com.yss.framework.resource.mgr.pojo.FileTrans;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.framework.resource.mgr.service.IResourceMgrService;

/**
 * 服务管理界面配置信息工具类
 * @ClassName ServiceUtil
 * @Description 
 * @author duwei@ysstech.com
 * @CreateDate 2016年12月22日
 * @Version V4.5.0.1 
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class ResServiceUtil {

	private static final Logger logger = LogManager.getLogger(ResServiceUtil.class);
	private static final ResServiceUtil serviceUtil = new ResServiceUtil();
	
	/**
	 * 获取实例
	 * @Title getInstance 
	 * @Description 
	 * @author duwei@ysstech.com
	 * @date 2016年12月22日
	 * @return
	 * @return ServiceUtil
	 */
	public static ResServiceUtil getInstance() {
		return serviceUtil;
	}
	
	/**
	 * 根据服务ID获取服务信息
	 * @Title getAllServiceInfo 
	 * @Description 
	 * @author duwei@ysstech.com
	 * @date 2016年12月22日
	 * @return
	 * @return List<Map<String,String>>
	 */
	public static String[] getIWService(String wId) {
		IWebserviceService webService = YssServiceFactory.getInstance().createService(IWebserviceService.class);
		String[] service = null;
		try {
			WebService pojo = webService.getIWServiceById(wId);
			if(pojo != null){
				service = new String[2];
				service[0] = pojo.getC_IP();
				service[1] = pojo.getC_PORT();
			}
			
			if(service == null){
				throw new DataAccessException("请确认是否配置服务管理：" + wId );
			}
		} catch (Exception e) {
			throw new DataAccessException("获取服务IP和端口失败：" + e.getMessage(), e);
		}	
		return service;
	}
	
	public static String validFilePath(String filepath) throws Exception{  
	    List<String> allowedExtensions = new ArrayList<String>();  
	    boolean result = false;  
	    allowedExtensions.add(".xml");  
	    allowedExtensions.add(".jar");  
	    allowedExtensions.add(".api");  
	    allowedExtensions.add(".properties");  
	    allowedExtensions.add(".xsd");  
	    allowedExtensions.add(".flw");  
	    allowedExtensions.add(".schema");  
	    allowedExtensions.add(".MF");  
	    allowedExtensions.add(".log");  
	    for(String suf:allowedExtensions){  
	        if(filepath.endsWith(suf)){  
	            result = true;  
	            break;  
	        }  
	    }  
	    if(!result){  
	          
	           // 按指定模式在字符串查找  
	          String pattern1 = "(.*)(\\.log\\.{0,1}\\d{0,})$";  
	          // 创建 Pattern 对象  
	          Pattern r1 = Pattern.compile(pattern1);  
	          // 现在创建 matcher 对象  
	          Matcher m1 = r1.matcher(filepath);  
	          if(!m1.matches()){  
	                Exception Exception = new Exception("file name is Illage") ;  
	                throw Exception   ;  
	          }  
	    }  
	    filepath = filepath.replaceAll("../", "");  
	    filepath = filepath.replaceAll("..\\\\", "");  
	    return filepath;   
	}  

	/**
	 * YSS_APP目录下的附件转移到资源管理器
	 * 
	 * @param fileName
	 * @return
	 */
	public static String fileNameConvert(String fileName) {
		FileInputStream fis = null;
		List<File> fileList = new ArrayList<File>();
		try {
			//STORY #50314 支付平台附件上传需要适配资源管理器
			FileStorePathUtil fileUtil = new FileStorePathUtil(fileName);
			File tempFile = new File(fileUtil.getDeployPath(false));
			fis = new FileInputStream(tempFile);
			IResourceMgrService rmgrService = HttpServiceFactory.getInstance()
					.createService(IResourceMgrService.class);

			FileTrans file2upload = new FileTrans();
			file2upload.setC_FileName(tempFile.getName());
			file2upload.setB_FileBytes(IOUtils.readBytesFromStream(fis));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String date = sdf.format(new Date());
			String sztPath = "/export/expfile/" + date + "/";
			ResourceMgr rm = rmgrService.uploadWithOwnerPath(sztPath, file2upload);
			// 删除本地临时文件
			if (tempFile.exists()&& tempFile.isFile()
					&& rmgrService.isExists(sztPath 
							+ file2upload.getC_FileName())) {
				fileList.add(tempFile);
			}
			fileName = rm.getC_VIRTUAL_PATH() + rm.getC_NAME();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally{
			if(fis != null){
				try {
					fis.close();
				} catch (IOException e) {
				}
			}
		}
		for(File file : fileList){
			file.delete();
		}
		return fileName;
	}
}

