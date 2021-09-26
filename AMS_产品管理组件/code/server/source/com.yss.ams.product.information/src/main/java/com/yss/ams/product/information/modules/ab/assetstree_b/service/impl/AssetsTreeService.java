package com.yss.ams.product.information.modules.ab.assetstree_b.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.assetstree_b.dao.AssetsTree_BDao;
import com.yss.ams.product.information.modules.ab.assetstree_b.dao.AssetsTree_BSqlBuilder;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.pojo.AssetsTree_B;
import com.yss.ams.product.information.support.modules.ab.assetstree_b.service.IAssetsTree_BService;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.framework.api.busoperservice.ILogger;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.DataProcServiceFactroy;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.util.DateUtil;


/**
 * 功能简介：新增'产品树形设置'复制
 * @author gh 2016-9-27
 * STORY33240【南方基金】复制建仓继承内容增加群组功能的勾选选项
 */
/**
 * <产品树型结构>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTreeService extends ServiceBus<AssetsTreeService> implements IAssetsTree_BService{

	private AssetsTree_BDao serviceDao = null;

	public AssetsTreeService() throws Exception {
		serviceDao = new AssetsTree_BDao(YssDbPoolFactory.getInstance()
				.getDbPool(ProductInfoActivator.class),
				new AssetsTree_BSqlBuilder());
		dao = serviceDao;
	}
	
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品树形设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, AssetsTree_B.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("assetsTree");
		String dataName = "产品树形设置";
		for (int i = 0; i < portCodeList.length; i++) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			for (int j = 0; j < basepojos.size(); j++) {
				AssetsTree_B pojo = ((AssetsTree_B) basepojos.get(j));
				if (pojo.getAuditState() == 1) {
					//add by liyanjun 2017-2-18 STORY35927新产品成立参数模板需求
					//当开启了审核机制，并且“生成审核数据”未勾选，则产生未审核数据
//					新增审核机制参数‘复制创建默认审核’，该参数基于审核机制开启的情况下，控制产品参数复制的模块继承数据的审核状态，并且只有当前产品复制参数中有的模块支持设置。
//					当【数据安全】模块中‘复制创建默认审核’勾选，则复制继承的模块数据均为已审核。
//					若某一模块未开启审核机制，则生成的该模块数据不受‘复制创建默认审核’控制。  STORY64774【招商基金】【紧急】产品参数复制优化 addby wsm 2018-12-15
					if (!StringUtil.IsNullOrEmptyT(checkState)) {
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& ( "0"
										.equals(checkState))) {
							pojo.setAuditState(0);
						}
					}else{
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& (safeData.getN_COPY_CHECK() != 1)) {
							pojo.setAuditState(0);
						}
					}
					pojo.setC_PORT_CODE(portCodeList[i]);
					pojo.setId("");
					pojo.setOperator(userCode);
					pojo.setModifier(userCode);
					pojo.setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					pojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					saveList.add(pojo);
				}
			}
			IPortDataService portDataService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			HashMap<String, String> map = portDataService.getKeyConvertMap();
			String pre = "组合" + map.get(portCodeList[i]) + dataName;
			try {
				ben_Record.init(portCodeList[i], dataName, null);
				ben_Record.BeginLog();
				log.write(execProcCode,
						ben_Record);
				ben_Record.appendDetailMes("组合代码：" + portCodeList[i]
						+ "，数据项代码：" + dataName);
				dao.insert(saveList);
				String end = "复制成功" + saveList.size() + "条数据，参照组合共"+saveList.size()+"条数据！";
				ben_Record.appendDetailMes(pre + end);
				ben_Record.EndLog_Success(end);
			} catch (Exception e) {
				ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
				ben_Record.EndLog_Fail("复制数据错误！");
			} finally {
				saveList.clear();
				ben_Record.setC_User_Code(userCode);
				log.write(execProcCode,
						ben_Record);
			}
			logger.debug("复制组合  " + portCodeList[i]);
		}
		logger.debug("产品树形设置复制结束");
		return null;
	}
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState, String isClear) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品树形设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, AssetsTree_B.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("assetsTree");
		String dataName = "产品树形设置";
		for (int i = 0; i < portCodeList.length; i++) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			for (int j = 0; j < basepojos.size(); j++) {
				AssetsTree_B pojo = ((AssetsTree_B) basepojos.get(j));
				if (pojo.getAuditState() == 1) {
					//add by liyanjun 2017-2-18 STORY35927新产品成立参数模板需求
					//当开启了审核机制，并且“生成审核数据”未勾选，则产生未审核数据
//					新增审核机制参数‘复制创建默认审核’，该参数基于审核机制开启的情况下，控制产品参数复制的模块继承数据的审核状态，并且只有当前产品复制参数中有的模块支持设置。
//					当【数据安全】模块中‘复制创建默认审核’勾选，则复制继承的模块数据均为已审核。
//					若某一模块未开启审核机制，则生成的该模块数据不受‘复制创建默认审核’控制。  STORY64774【招商基金】【紧急】产品参数复制优化 addby wsm 2018-12-15
					if (!StringUtil.IsNullOrEmptyT(checkState)) {
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& ( "0"
										.equals(checkState))) {
							pojo.setAuditState(0);
						}
					}else{
						if (safeData != null
								&& safeData.getN_CHECK() == 1
								&& (safeData.getN_COPY_CHECK() != 1)) {
							pojo.setAuditState(0);
						}
					}
					pojo.setC_PORT_CODE(portCodeList[i]);
					pojo.setId("");
					pojo.setOperator(userCode);
					pojo.setModifier(userCode);
					pojo.setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					pojo.setAuditDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					saveList.add(pojo);
				}
			}
			IPortDataService portDataService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			HashMap<String, String> map = portDataService.getKeyConvertMap();
			String pre = "组合" + map.get(portCodeList[i]) + dataName;
			if("NO".equals(isClear)){
				try {
					ben_Record.init(portCodeList[i], dataName, null);
					ben_Record.BeginLog();
					log.write(execProcCode,
							ben_Record);
					ben_Record.appendDetailMes("组合代码：" + portCodeList[i]
							+ "，数据项代码：" + dataName);
					dao.insert(saveList);
					String end = "复制成功" + saveList.size() + "条数据，参照组合共"+saveList.size()+"条数据！";
					ben_Record.appendDetailMes(pre + end);
					ben_Record.EndLog_Success(end);
				} catch (Exception e) {
					ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
					ben_Record.EndLog_Fail("复制数据错误！");
				} finally {
					saveList.clear();
					ben_Record.setC_User_Code(userCode);
					log.write(execProcCode,
							ben_Record);
				}
				logger.debug("复制组合  " + portCodeList[i]);
			}else{ // 如果是从清算调用的直接处理复制操作
				try{
					dao.insert(saveList);
				}catch(Exception e){
					ben_Record.BeginLog();
					log.write(execProcCode,ben_Record);
					ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
					ben_Record.EndLog_Warn("复制数据失败！");
				}finally{
					saveList.clear();
					ben_Record.setC_User_Code(userCode);
					log.write(execProcCode,ben_Record);
				}
			}
		}
		logger.debug("产品树形设置复制结束");
		return null;
	}

	@Override
	public String isSameAssetType(String portCode, String dragPortCode) {
		return null;
	}

	@Override
	public int updateOrdelete(String id, String trCode, String isParent,
			String type) {
		return 0;
	}
	
	@Override
	public String getUserId(String quyCon) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateOrdelete(String id, String trCode, String isParent,
			String type, String trCodeR) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public List<String> copy(String portCode, HashMap<String, String> portCodeList,
			String dataCode, String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品树形设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, AssetsTree_B.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		/* STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况 存放复制失败组合 */
		List<String> failPorts = new ArrayList<String>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("assetsTree");
		String dataName = "产品树形设置";
		for (String portTemp : portCodeList.keySet()) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			IPortDataService portDataService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			HashMap<String, String> map = portDataService.getKeyConvertMap();
			String pre = "组合" + map.get(portTemp) + dataName;
			try {
				ben_Record.init(portTemp, dataName, null);
				ben_Record.BeginLog();
				log.write(execProcCode,
						ben_Record);
				ben_Record.appendDetailMes("组合代码：" + portTemp
						+ "，数据项代码：" + dataName);
				String[] params = portCodeList.get(portTemp).split("=");
				if ("1".equals(params[0])) {
					for (int j = 0; j < basepojos.size(); j++) {
						AssetsTree_B pojo = ((AssetsTree_B) basepojos.get(j));
						if (pojo.getAuditState() == 1) {
							//add by liyanjun 2017-2-18 STORY35927新产品成立参数模板需求
							//当开启了审核机制，并且“生成审核数据”未勾选，则产生未审核数据
//							新增审核机制参数‘复制创建默认审核’，该参数基于审核机制开启的情况下，控制产品参数复制的模块继承数据的审核状态，并且只有当前产品复制参数中有的模块支持设置。
//							当【数据安全】模块中‘复制创建默认审核’勾选，则复制继承的模块数据均为已审核。
//							若某一模块未开启审核机制，则生成的该模块数据不受‘复制创建默认审核’控制。  STORY64774【招商基金】【紧急】产品参数复制优化 addby wsm 2018-12-15
							if (!StringUtil.IsNullOrEmptyT(checkState)) {
								if (safeData != null
										&& safeData.getN_CHECK() == 1
										&& ( "0"
												.equals(checkState))) {
									pojo.setAuditState(0);
								}
							}else{
								if (safeData != null
										&& safeData.getN_CHECK() == 1
										&& (safeData.getN_COPY_CHECK() != 1)) {
									pojo.setAuditState(0);
								}
							}
							pojo.setC_PORT_CODE(portTemp);
							pojo.setId("");
							pojo.setOperator(userCode);
							pojo.setModifier(userCode);
							pojo.setModifyDate(DateUtil
									.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
							pojo.setAuditDate(DateUtil
									.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
							saveList.add(pojo);
						}
					}
					
					try {
						dao.insert(saveList);
						String end = "复制成功" + saveList.size() + "条数据，参照组合共"+saveList.size()+"条数据！";
						ben_Record.appendDetailMes(pre + end);
						ben_Record.EndLog_Success(end); 
					} catch (DataAccessException e) {
						String end = "已有参数，不允许进行复制！";
						ben_Record.appendDetailMes(pre + end);
						ben_Record.EndLog_Warn(end);	
					}
				} else {
					String end = params[1];
					ben_Record.appendDetailMes(pre + end);
					ben_Record.EndLog_Warn(end);
				}
			} catch (Exception e) {
				logger.log(e.getMessage(), e);
				ben_Record.appendDetailMes(pre + "已有参数，不允许进行复制！");
				ben_Record.EndLog_Fail("复制数据错误！");
			} finally {
				saveList.clear();
				ben_Record.setC_User_Code(userCode);
				log.write(execProcCode,
						ben_Record);
			}
			logger.debug("复制组合  " + portTemp);
		}
		logger.debug("产品树形设置复制结束");
		return failPorts;
	}
	
	public String getCodeByCId(String id) {
		return serviceDao.getCodeByCId(id);
	}
}
