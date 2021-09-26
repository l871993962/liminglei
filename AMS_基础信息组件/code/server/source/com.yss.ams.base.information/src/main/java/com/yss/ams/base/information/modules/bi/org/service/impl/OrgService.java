package com.yss.ams.base.information.modules.bi.org.service.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import sun.misc.BASE64Encoder;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.org.dao.OrgDao;
import com.yss.ams.base.information.modules.bi.org.dao.OrgSqlBuilder;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgLinkRelaService;
import com.yss.ams.base.information.support.bi.org.service.IOrgService;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.resource.mgr.pojo.FileTrans;
//import com.yss.uco.transfer.unifypay.support.service.IFaxService;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.service.ServiceAssistance;

//import dataservice.IElecParaService;

/**
 * 机构服务类
 * @author 马向峰 拆分
 *@Date 20170531
 */
//liuxiang 2016-2-26 STORY #28246 【自动化款指令】及【划款指令管理】模块界面问题优化
@DefaultCacheRefresh(group = CacheGroup.ORG)
public class OrgService extends ServiceBus<OrgService> implements IOrgService {
	private String FILE_PATH = "";
	private OrgDao serviceDao = null;

	public OrgService() throws Exception {
		serviceDao = new OrgDao(DbPoolFactory.getInstance().getPool(),
				new OrgSqlBuilder());
		dao = serviceDao;
		
		serviceBindingClazz = Org.class;
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		String orgCodePara = "";
		if (paraMap.containsKey("C_ORG_CODE")) {
			orgCodePara = (String) paraMap.get("C_ORG_CODE");
		}
		IOrgLinkRelaService relaService = YssServiceFactory.getInstance().createService(IOrgLinkRelaService.class);
		Map<String, Org> orgMap = relaService.getOrgLinkManData(orgCodePara);
		// 关联岗位信息
		QueryRes queryRes = super.queryByCondition(paraMap, page);
		List<BasePojo> dataList = queryRes.getDataList();
		for (BasePojo pojo : dataList) {
			Org org = (Org) pojo;
			if (orgMap.containsKey(org.getC_ORG_CODE())) {
				org.setC_POST_NAME(orgMap.get(org.getC_ORG_CODE()).getC_POST_NAME());
			}
		}
		queryRes.setDataList(dataList);
		return queryRes;
	}
	
	@Override
	public String insert(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			retInfo = super.insert(pojoList);
			// 插入联系人信息
			updateOrgLinkMan(pojoList);
		} catch (Exception e) {
			logger.log("插入机构信息失败", e);
			throw new ErrorMessageException(e);
		}
		return retInfo;
	}
	
	@Override
	public String updateById(List<BasePojo> pojoList) {
		String retInfo = "";
		try {
			retInfo = super.updateById(pojoList);
			// 更新联系人信息
			updateOrgLinkMan(pojoList);
		} catch (Exception e) {
			logger.log("更新机构信息失败", e);
			throw new ErrorMessageException(e);
		}
		return retInfo;
	}
	
	@Override
	public List<Org> getOrgLinkManList(String orgCode) {
		IOrgLinkRelaService relaService = YssServiceFactory.getInstance().createService(IOrgLinkRelaService.class);
		List<Org> orgList = relaService.getOrgLinkManList(orgCode);
		return orgList;
	}
	
	/**
	 * 更新联系人信息
	 * @param pojoList
	 */
	private void updateOrgLinkMan(List<BasePojo> pojoList) {
		IOrgLinkRelaService relaService = YssServiceFactory.getInstance().createService(IOrgLinkRelaService.class);
		for (BasePojo pojo : pojoList) {
			Org orgPojo = (Org) pojo;
			String orgCode = orgPojo.getC_ORG_CODE();
			String linkManList = orgPojo.getC_LINKMAN_LIST();
			List<Org> orgList = new ArrayList<Org>();
			String[] linkManInfo = linkManList.split("β");
			for (int i = 0; i < linkManInfo.length; i++) {
				if ("".equals(linkManInfo[i].trim()) || " α α α α ".equals(linkManInfo[i])) {
					continue;
				}
				String[] linkManDetail = linkManInfo[i].split("α");
				Org org = new Org();
				org.setC_LINK_MAN(linkManDetail[0].trim());
				org.setC_POST_NAME(linkManDetail[1].trim());
				org.setC_LINK_TEL(linkManDetail[2].trim());
				org.setC_MO_TEL(linkManDetail[3].trim());
				org.setC_EMAIL(linkManDetail[4].trim());
				orgList.add(org);
			}
			relaService.updateOrgLinkMan(orgCode, orgList);
		}
	}

	public QueryRes getPortRelaOrg(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader
					.getPojoClassById(classString, this.bundleContext);

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.getPortRelaOrgDao(paraMap, clazz);
			queryRes.setDataList(dataList);

			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					InformationActivator.class));

			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId(menuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
//			logger.log(msg, ex);
			logger.log("关联机构设置：查询组合关联机构信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * 组织字典信息
	 * 
	 * @return
	 */
	public List<BasePojo> getOrgVoc() {
		List<BasePojo> orgVocList = serviceDao.getOrgVoc();
		return orgVocList;
	}

	@Override
	public QueryRes queryPortRelaOrg(HashMap<String, Object> paraMap,
			PageInation page) {
		QueryRes queryRes = new QueryRes();
		try {

			List<BasePojo> dataList = (List<BasePojo>) serviceDao
					.queryPortRelaOrg(paraMap, page);
			if (page == null) {
				page = getDefaultPageInation();
			}

			int recCount = 0;
			recCount = queryPortRelaOrgCount(paraMap);

			fillResultObject(queryRes, dataList, recCount, page, menuId);
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("关联机构设置：查询组合关联机构信息失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	private int queryPortRelaOrgCount(HashMap<String, Object> paraMap) {
		return serviceDao.queryPortRelaOrgCount(paraMap);
	}

	/**
	 * 上传成功，返回文件名
	 */
	@Override
	public String uploadLogo(FileTrans file) {
		String bRes = "Success";
		FileOutputStream fos = null;
		Boolean btrue = true;
		try {
			String path = new FileStorePathUtil("").getFilePath();
			String fullFileName = path + FILE_PATH + file.getC_FileName();
			File f = new File(fullFileName);
			if (!f.exists()) {
				if (!f.getParentFile().exists()) {
					f.getParentFile().mkdirs();
				}
				btrue = f.createNewFile();
			} else {
				btrue = f.delete();
				f = new File(fullFileName);
			}

			fos = new FileOutputStream(f, true);
			fos.write(file.getB_FileBytes());
			fos.flush();
			bRes = fullFileName;
		} catch (Exception ex) {
			bRes = "Fail";
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Exception e) {
//					e.printStackTrace();
					logger.log("关联机构设置：文件上传失败", e);
				}

			}
		}
		if(!btrue){
			bRes = "Fail";
		}
		return bRes;
	}

	/**
	 * 删除服务器上的旧logo
	 * 
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public String delLogo(String fileName) throws IOException {
		String bRes = "Success";
		Boolean btrue = true;
		File file = new File(fileName);
		if (file.exists()) {
			btrue = file.delete();
		}
		if(!btrue){
			bRes = "Fail";
		}
		return bRes;
	}

	/**
	 * 更新服务器上的旧logo
	 * 
	 * @param oldFileName
	 * @param newFile
	 * @return
	 * @throws IOException
	 */
	public String updateLogo(String oldFileName, FileTrans newFile)
			throws IOException {
		String bRes = "Success";
		bRes = uploadLogo(newFile);
		if (!bRes.equals("Fail")) {
			delLogo(oldFileName);
		}
		return bRes;
	}

	// / <summary>
	// / 图片路径
	// / </summary>
	// / <param name="imagePath">图片路径</param>
	// / <returns>1</returns>
	public String queryImage(String imagePath) {
		String returnValue = "";
		FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
		String realPath = imagePath.replace("%YSS_APP_PATH%/",
				fileStorePathUtil.getFilePath());
		File file = new File(realPath);
		if (file.exists()) {
			ByteArrayOutputStream bos = new ByteArrayOutputStream(
					(int) file.length());
			BufferedInputStream in = null;
			InputStream inputStream  = null;
			try {
				inputStream=new FileInputStream(file);
				in = new BufferedInputStream(inputStream);
				int buf_size = 1024;
				byte[] buffer = new byte[buf_size];
				int len = 0;
				while (-1 != (len = in.read(buffer, 0, buf_size))) {
					bos.write(buffer, 0, len);
				}
				BASE64Encoder encoder = new BASE64Encoder();
				returnValue = encoder.encode(bos.toByteArray());
				//inputStream.close();
				//in.close();
			} catch (IOException e) {
//				e.printStackTrace();
				logger.log("关联机构设置：查询图片路径出错", e);
			} finally {
				try {
					if(in!=null){
						in.close();
					}
					if( inputStream != null ){
						inputStream.close();
					}
					bos.close();
				} catch (IOException e) {
//					e.printStackTrace();
					logger.log("关联机构设置：查询图片路径出错", e);
				}
			}
		}
		return returnValue;
	}
	
	@Override
	public String deleteById(List<BasePojo> pojoList) {
		String info = "";
//		List<BasePojo> listPojos  = new ArrayList<BasePojo>();
		try {
			//删除传真号码信息
//			for(BasePojo pojo : pojoList){
//				String orgCode = ((Org)pojo).getC_ORG_CODE();
//				/*支付平台代码调整，删除机构信息同步删除传真号码管理信息*/
//				serviceDao.clearFaxNo(orgCode);
//			}
			
			//删除电子对账参数设置信息
//			IElecParaService elecParaService =YssServiceFactory.getInstance().createService(IElecParaService.class);
//			for(BasePojo pojo : pojoList){
//				String orgCode = ((Org)pojo).getC_ORG_CODE();				
////				List<BasePojo> pojos = elecParaService.getElecParaPojo(orgCode);
////				if(pojos.size()!=0){
////					deleteElecPara(pojos);
////				}							
//			}
			//删除机构信息
			info = super.deleteById(pojoList);
			//删除关联表信息
//			for(BasePojo pojo : pojoList){
//				String orgCode = ((Org)pojo).getC_ORG_CODE();
//				listPojos = serviceDao.searchRela(orgCode);
//				if(listPojos.size()>0){
//					deleteRela( listPojos);	
//				}					
//			}
			//删除关联表信息
			IOrgLinkRelaService relaService = YssServiceFactory.getInstance().createService(IOrgLinkRelaService.class);
			for (BasePojo pojo : pojoList) {
				String orgCode = ((Org)pojo).getC_ORG_CODE();
				relaService.deleteOrgLinkMan(orgCode);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("关联机构设置：删除机构信息出错", e);
		}
		return info;
	}
	//删除传真号码信息
	//// 删除信息只删除自己所属对象数据，不准跨应用删除其他组件数据 
	//// By Jinghehe 2017-8-4 框架组调整 详情见需求 STORY #45042 解决优化当前基本信息组件、产品组件出现的对接问题（主子数据或者被引用数据删除提醒与原则
//	 public String deleteFax(List<BasePojo> pojos){
//		  String retValue = "";
//		 IFaxService ss =YssServiceFactory.getInstance().createService(IFaxService.class);
//		 ss.deleteById(pojos);
//		 return retValue;
//	 }
	 
	//删除电子参数设置信息
	//// 删除信息只删除自己所属对象数据，不准跨应用删除其他组件数据 
	//// By Jinghehe 2017-8-4 框架组调整 详情见需求 STORY #45042 解决优化当前基本信息组件、产品组件出现的对接问题（主子数据或者被引用数据删除提醒与原则
//	 public String deleteElecPara(List<BasePojo> pojos){
//		 String retValue = "";
//		 IElecParaService ss =YssServiceFactory.getInstance().createService(IElecParaService.class);
//		 ss.deletePojos(pojos);
//		 ss.deleteByPojos(pojos); //还新增的方法-xhb
//		 return retValue;
//	 }
	 
	//删除关联信息
//	 public String deleteRela(List<BasePojo> pojos){
//		 String retValue = "";
//		 IOrgRelaService ss =YssServiceFactory.getInstance().createService(IOrgRelaService.class);
//		 ss.deleteById(pojos);
//		 return retValue;
//	 }
	 
 	/**
	 * 获取委托人组织机构
	 * @return
	 */
	 @Override
	public List<Org> getOrgByConsignerType(){
		return serviceDao.getOrgByConsignerType();
	}

	/**
	 * 产品生命周期主体信息功能   提供控制事务的插入方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public String connInsert(BasePojo pojo,Connection conn) throws ServiceException{
		String returnId = "";
		try {
			returnId = serviceDao.insert(pojo, conn);
		} catch (Exception e) {
			logger.log("插入主体信息出错", e);
			if(e instanceof ServiceException){
				throw (ServiceException)e;
			}else{
				throw new ServiceException("插入主体信息出错" + e.getMessage(), e);
			}
		}
		return returnId;
	}
	
	/**
	 * 产品生命周期主体信息功能   提供控制事务的删除方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public void connDelete(List<BasePojo> list,Connection conn) throws ServiceException{
		try {
			for (BasePojo basePojo : list) {
				serviceDao.deleteById(basePojo, conn);
			}
		} catch (Exception e) {
			logger.log("删除主体信息出错", e);
			if(e instanceof ServiceException){
				throw (ServiceException)e;
			}else{
				throw new ServiceException("删除主体信息出错" + e.getMessage(), e);
			}
		}
	}
	
	/**
	 * 产品生命周期主体信息功能   提供控制事务的修改方法
	 * @author jiangzhichao
	 * @Date 20171229
	 * @param pojo
	 * @param conn
	 * @return
	 */
	public void connUpdate(BasePojo pojo,Connection conn) throws ServiceException{
		try {
			serviceDao.updateById(pojo, conn);
		} catch (Exception e) {
			logger.log("修改主体信息出错", e);
			if(e instanceof ServiceException){
				throw (ServiceException)e;
			}else{
				throw new ServiceException("修改主体信息出错" + e.getMessage(), e);
			}
		}
	}
	
	@Override
	public CacheData updateByIds(String ids){
		List<Org> dataList = serviceDao.queryByIds(ids, Org.class);
		CacheData data = new CacheData();
		data.setDataList(JsonUtil.toString(dataList));
		return data;
	}

	@Override
	public void deleteThenInsert(Org org) {
		serviceDao.deleteThenInsert(org);
	}
	
	
	@Override
	public List<Org> getDataListByTypes(String[] types) throws Exception {
		return serviceDao.getDataListByTypes(types);
	}
	
	/**
	 * 获取组合的管理人名称
	 * STORY #70280 add by xiadeqi 2019-3-20 
	 * @param portCode
	 * @return
	 */
	public String getManagerNameByPortCode(String portCode)  throws ServiceException {
		return serviceDao.getManagerNameByPortCode(portCode);
	}
	/**
	 * Author : zuomingke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment:  根据机构代码查询机构信息，若为空或null则查询所有的机构信息
	 * @return 
	 */
	public List<Org> getOrgByOrgCodes(String orgCodes) throws Exception {
		return serviceDao.getOrgListByOrgCodes(orgCodes);
	}
	
	/**
	 * Author : zuomingke
	 * Date   : 2019-07-04
	 * Status : Add
	 * Comment:  根据机构代码查询关联组合代码
	 * @return 
	 */
	public List<String> getPortCodeListByRelaOrgCode(String orgCode) {
		return serviceDao.queryPortCodeListByRelaOrgCode(orgCode);
	}
}
