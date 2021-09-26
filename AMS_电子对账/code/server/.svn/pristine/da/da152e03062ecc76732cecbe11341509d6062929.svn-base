package com.yss.uco.elecreco.er.template.service.impl;

import java.io.File;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.log.annotations.OperLog;
import com.yss.uco.elecreco.er.template.dao.DzTemplateDao;
import com.yss.uco.elecreco.er.template.dao.DzTemplateSqlBuilder;
import com.yss.uco.elecreco.er.template.pojo.Deploy;
import com.yss.uco.elecreco.er.template.pojo.DzTemplate;
import com.yss.uco.elecreco.er.template.pojo.FileStreamParam;
import com.yss.uco.elecreco.er.template.service.IDzTemplateService;
import com.yss.uco.elecreco.er.template.util.ReadMeTool;
import com.yss.uco.elecreco.er.template.util.TemplateManager;

/**
 * @author liuxiang 2015年2月13日
 */
public class DzTemplateService extends ServiceBus<DzTemplateService> implements
		IDzTemplateService {

	private TemplateManager templateMgr = null;
	
	private DzTemplateDao serviceDao = null;

	public DzTemplateService() {
		serviceDao = new DzTemplateDao(DbPoolFactory.getInstance().getPool(),
				new DzTemplateSqlBuilder());
		dao = serviceDao;
		templateMgr = TemplateManager.NewInstance();
	}

	@Override
	public DzTemplate getTemplateByTypeCodeAndPortCode(String typeCode,
			String portCode) {
		return serviceDao.getTemplateByTypeCodeAndPortCode(typeCode, portCode);
	}
	
	/**
	 * 报表文档部署
	 */
	@OperLog(Ignore = true)
	@Override
	public String deploy(String zipFiles) throws Exception {
		boolean bResult = false;
		try{
			String[] zipFileArry = zipFiles.split(",");
			for (String zipFile : zipFileArry) {
				String fileDir = templateMgr.Deploy(zipFile);//将zip文件解压至新的文件夹下
				String virtualPath = TemplateManager.YSS_APP_PATH + TemplateManager.TEMPLATE_PATH + zipFile.substring(0, zipFile.indexOf(".")) + "/";
				Deploy deploy = ReadMeTool.readDeploy(fileDir + ReadMeTool.DEPLOY_FILE);//解析deploy。xml文件
				serviceDao.deploy(virtualPath, deploy);//将解析好的xml对象保存至对应表中
				if (fileDir == null || fileDir.length() == 0) {
					return String.valueOf(false);
				}
				bResult = true;
			}
		}catch(Exception ex){
			if(ex instanceof YssRuntimeException){
				throw ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return String.valueOf(bResult);
	}
	
	/**
	 * 报表文档卸载
	 * @param zipFiles 报表代码
	 */
	@Override
	public String unDeploy(List<BasePojo> templateList) throws Exception {
		boolean bResult = false;
		File DirFile = null;
		String[] lstFile = null;
		try {
			for (BasePojo basePojo : templateList) {
				DzTemplate dztemplate = (DzTemplate)basePojo;
				String fileDir = templateMgr.convertYSSAPPPath(dztemplate.getC_TMPL_PATH());
				this.deleteById(basePojo);
				// 删除这个模板文档下的文件
				DirFile = new File(fileDir);
				lstFile = DirFile.list();
				if(lstFile != null){
					for (String sFile : lstFile) {
						File file = new File(fileDir + sFile);
						file.delete();
					}
				}
				DirFile.delete();
				
				fileDir = fileDir.replace(TemplateManager.TEMPLATE_PATH, TemplateManager.ZIP_SAVE_PATH);
				while(fileDir.endsWith("/")){
					fileDir = fileDir.substring(0,fileDir.length() - 1);
				}
				DirFile = new File(fileDir + ".zip");
				if(DirFile.exists()){
					DirFile.delete();
				}
				bResult = true;
			}
		} catch (Exception ex) {
			bResult = false;
		}
		return String.valueOf(bResult);
	}

	/**
	 * 报表文件下载
	 * @param fileName 要下载文件名
	 * @return 返回要下载文件路径
	 * @throws Exception
	 */
	@Override
	public String downLoad(BasePojo basePojo) throws Exception {
		DzTemplate dzTemplate = (DzTemplate)basePojo;
		String filePath = dzTemplate.getC_TMPL_PATH().replace(TemplateManager.TEMPLATE_PATH, TemplateManager.ZIP_SAVE_PATH);
		filePath = templateMgr.convertYSSAPPPath(filePath);
		while(filePath.endsWith("/")){
			filePath = filePath.substring(0,filePath.length() - 1);
		}
		String fileName = filePath + ".zip";
		return templateMgr.copyFileToPlatForm(fileName);//将yss_app路径下的文件写一份至平台的路径下
	}

	@Override
	public String updateStatus(List<BasePojo> basePojoList) throws Exception {
		serviceDao.updateById(basePojoList);
		return "";
	}
	
	/**
	 *  查询已部署且已启用的模板
	 * @return 导出接口代码
	 */
	@Override
	public List<String> getDeployTemplate() throws Exception {
		return serviceDao.getDeployTemplate();
	}

	/**
	 * 报表文件上传
	 * @param file
	 * @return
	 * @throws Exception
	 */
	@Override
	public String upload(FileStreamParam fileStreamParam) throws Exception {
		String filePath = templateMgr.getUpLoadFilePath(TemplateManager.ZIP_SAVE_PATH);
		boolean bRes = templateMgr.upload(fileStreamParam, filePath);
		return Boolean.toString(bRes);
	}
}
