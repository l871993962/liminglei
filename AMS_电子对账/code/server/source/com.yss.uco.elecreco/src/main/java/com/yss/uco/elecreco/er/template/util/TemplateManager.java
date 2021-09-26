package com.yss.uco.elecreco.er.template.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.httpclient.HttpException;

import com.yss.framework.api.common.pi.IApplication;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.copyfile.util.CopyFileUtil;
import com.yss.uco.elecreco.er.template.pojo.FileStreamParam;

/**
 * 业务文档的相关操作
 * 
 */
public class TemplateManager {

	/**
	 * zip文件解压后保存的文档路径
	 */
	public static final String TEMPLATE_PATH = "/ysselecreco/generate/template/";
	
	/**
	 * ZIP文件保存的路径
	 */
	public static final String ZIP_SAVE_PATH = "/ysselecreco/generate/updownzip/";
	
	/**
	 * 导出文件的存放路径
	 */
	public static final String EXP_FILE_PATH = "/ysselecreco/generate/expfile/";
	
	/**
	 * YSS_APP路径代号
	 */
	public static final String YSS_APP_PATH = "%YSS_APP_PATH%";

	/**
	 * 定义静态的实例对象
	 */
	private static TemplateManager simpleIns = null;

	/**
	 * 构造方法
	 */
	private TemplateManager() {
	}

	/**
	 * 定义静态的构造方法
	 * 
	 * @return 本实例
	 */
	public static TemplateManager NewInstance() {
		if (simpleIns == null) {
			simpleIns = new TemplateManager();
		}
		return simpleIns;
	}

	/**
	 * 部署 将zip文件解压至新的文件夹下
	 * @return 返回解压后的文件夹
	 */
	public String Deploy(String zipFileName) throws Exception {
		String fileRealDir = "";
		try {
			File file = new File(this.getUpLoadFilePath(ZIP_SAVE_PATH) + zipFileName);
			String fileNewDir =  this.getUpLoadFilePath(TEMPLATE_PATH + file.getName().substring(0, file.getName().indexOf(".")) + "/");
			
			ZipTool.UnZip(this.getUpLoadFilePath(ZIP_SAVE_PATH) + zipFileName, fileNewDir);

			fileRealDir = fileNewDir;
		} catch (Exception ex) {
			throw ex;
		}
		return fileRealDir;
	}
	
	/**
	 * 获取应用绝对路径
	 * @param fileName 文件名称
	 * @return
	 */
	public String getUpLoadFilePath(String filePath){
		FileStorePathUtil fileStorePathUtil = new FileStorePathUtil(filePath);
		
		String fullFilePath = fileStorePathUtil.getFilePath();
		return fullFilePath;
	}
	
	/**
	 * 将文件复制到platform下
	 * @param fileName 文件名
	 * @return 返回复制后platform文件夹
	 */
	public String copyFileToPlatForm(String fileName) throws HttpException, IOException, YssException{
		Context context = ContextFactory.getContext();
		IApplication app = context.getPlatformInfo();
		CopyFileUtil.copyFileToDiffAppServer(fileName, ZIP_SAVE_PATH, app);
		File file = new File(fileName);
		return ZIP_SAVE_PATH + file.getName();
	}
	
	/**
	 * 将虚拟路径替换成真实路径
	 * @param virtualPath 虚拟路径
	 * @return
	 */
	public String convertYSSAPPPath(String virtualPath){
		FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
		
		String realPath = virtualPath.replace(YSS_APP_PATH + "/", fileStorePathUtil.getFilePath());
		return realPath;
	}
	
	/**
	 * 将文件流上传到后台，并用追加的方式保存成文件
	 * 
	 * @param fileStreamParam 文件传输的POJO对象
	 * @param filePath 文件保存路径
	 * @return 状态，成功或失败
	 * @throws Exception
	 */
	public boolean upload(FileStreamParam fileStreamParam, String filePath) throws Exception {
		boolean bRes = false;
		FileOutputStream fos = null;
		try {
			String fullFileName = filePath + fileStreamParam.getFileName();
			File f = new File(fullFileName);
			if (!f.exists()) {
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				f.createNewFile();
			} else {
				if(fileStreamParam.getPartIndex() == 1){
					f.delete();
					f = new File(fullFileName);
				}
			}

			fos = new FileOutputStream(f, true);
			fos.write(fileStreamParam.getFileData());
			fos.flush();
			bRes = true;
		} catch (Exception ex) {
			bRes = false;
		} finally {
			if (fos != null) {
				fos.close();
			}
		}

		return bRes;
	}
}
