package com.yss.ams.base.information.modules.bi.account.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.cxf.helpers.IOUtils;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.bi.account.dao.FundAccDao;
import com.yss.ams.base.information.modules.bi.account.dao.FundAccSqlBuilder;
import com.yss.ams.base.information.support.bi.account.cache.BaseFundAccCache;
import com.yss.ams.base.information.support.bi.account.pojo.CashAcc;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.ams.base.information.support.bi.account.service.IFundAccService;
import com.yss.fast.mq.support.constants.TopicConstants;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.cache.assist.DefaultCacheRefresh;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.pi.IApplication;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.msgcenter.IFrameworkPublisher;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.copyfile.util.CopyFileUtil;
import com.yss.framework.msg.mq.MessageQueueException;
import com.yss.framework.resource.mgr.pojo.FileTrans;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.framework.resource.mgr.service.IResourceMgrService;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.platform.support.enclosure.pojo.DataEnclosure;
import com.yss.platform.support.enclosure.service.IDataEnclosureService;

/**
 * @ClassName FundAccUnifyPayService
 * @Description 产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
@DefaultCacheRefresh(group = CacheGroup.FUNDACC)
public class FundAccService extends ServiceBus<FundAccService> implements IFundAccService {

	private FundAccDao serviceDao = null;

	public FundAccService() throws Exception {
		serviceDao = new FundAccDao(YssDbPoolFactory.getInstance()
				.getDbPool(InformationActivator.class), new FundAccSqlBuilder());
		dao = serviceDao;
		
		serviceBindingClazz = FundAcc.class;
	}
	
	/**
	 * 
	 * @param id
	 * @param fundId
	 */
	@Override
	public void updatePortCode(String id,String fundId){
		serviceDao.updatePortCode(id, fundId);
	}

	/**
	 * 
	 * @param id
	 * @param accountType
	 */
	@Override
	public void updateFundAccType(String id,String accountType){
		serviceDao.updateFundAccType(id, accountType);
	}
	
	/**
	 * 
	 * @param paraMap2
	 * @return
	 */
	@Override
	public FundAcc getFundAccByInfo(HashMap<String,String> paraMap){
		return serviceDao.getFundAccByInfo(paraMap);
	}
	
	/**
	 * 
	 * @param paraMap2
	 * @return
	 */
	@Override
	public FundAcc getFundAccByInfo(Map<String,Object> paraMap){
		return serviceDao.getFundAccByInfo(paraMap);
	}
	
	@Override
	public String getFundAccOrgcodeByAccNo(String accNo) {
		return serviceDao.getFundAccOrgcodeByAccNo(accNo);
	}
	
	@Override
	public String updateFundAccUnifyPay(String accountNo) {
		return serviceDao.updateFundAccUnifyPay(accountNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataListByAccTypes(String[] types)
			throws ServiceException {
		try {
			return (List<K>) serviceDao.getDataListByAccType(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public <K extends BasePojo> List<K> getDataListByAccTypes2(String[] types)throws ServiceException {
		try {
			return (List<K>) serviceDao.getDataListByAccType2(types);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public String isAccOfAccTypeExit(String type) {
		return serviceDao.isAccOfAccTypeExit(type);
	}

	@Override
	public List<FundAcc> getFundAccByType(String[] types, String accAddr) {
		List<FundAcc> dataList = null;
		dataList = serviceDao.queryFundAccByType(types, accAddr);
		return dataList;
	}

	@Override
	public <K extends BasePojo> String updateById(K pojo) {
		String result =  super.updateById(pojo);
		FundAcc acc = (FundAcc)pojo;
		if(acc != null && acc.getId() != null){
			String accountType = acc.getC_ACCOUNT_TYPE();
			if(accountType == null){
				accountType = "";
			}
			serviceDao.updateFundAccRela(acc.getId(),accountType);
		}
		
		return result;
	}

	/**
	 * 20160616 add by zhouning_cs STORY #31467 支付自动划款指令中的开户名称需取账户设置
	 */
	@Override
	public String updateById(List<BasePojo> pojoList) {
		//FundAcc fundAccAfter = null;
		FundAcc fundAccBefore = null;
		String result =  "";
		if ( pojoList != null && pojoList.get(0) != null) {
			FundAcc fundAccAfter = (FundAcc) pojoList.get(0);
			
			if (null != fundAccAfter 
					&& !StringUtil.IsNullOrEmpty(fundAccAfter.getId())) {
				fundAccBefore = (FundAcc) this.serviceDao.queryByIds(
						fundAccAfter.getId(), FundAcc.class).get(0);
			
			//STORY #69729 基础组件中引用了支付的T_C_CP_AUTO表 需通过交互解除表依赖
//			Map<String,Boolean> resultMap = serviceDao.checkAutoGenHkzlTableExists(fundAccBefore);
//			if (null != fundAccBefore && null != fundAccAfter) {
//				serviceDao.updateAutoGenHkzl(fundAccBefore, fundAccAfter, resultMap);
//			}
			
			//给支付平台发送账户更新消息
			IFrameworkPublisher ifc=(IFrameworkPublisher)YssServiceFactory.getInstance().createService(IFrameworkPublisher.class);
			Map<String, Object> sendDetail = new HashMap<String, Object>();
			sendDetail.put("FUN_CODE", "base_info");
			sendDetail.put("SUB_FUN_CODE", "FUNDACC_CHANGE");
			sendDetail.put("fundaccBefore", JsonUtil.toString(fundAccBefore));
			sendDetail.put("fundaccAfter", JsonUtil.toString(fundAccAfter));
			try {
			    String detailMsg = JsonUtil.toString(sendDetail);
			    logger.info("向支付UnfiypayMsgSubscriber发送消息:"+detailMsg);
                ifc.send(detailMsg, TopicConstants.BASE_FUNDACC.toString());
			} catch (MessageQueueException e) {
                logger.error("给支付平台发送账户更新消息失败", e);
            }
			result = super.updateById(pojoList);
			serviceDao.updateFundAccRela(fundAccAfter.getId(),fundAccAfter.getC_ACCOUNT_TYPE());
			}
		}
		return result;
	}

	@Override
	public String updateOrgInfo(String cPAYCODE, String cBANKCODE, String id) {

		return serviceDao.updateOrgInfo(cPAYCODE, cBANKCODE, id);
	}

	public List<FundAcc> getDataListByPort(String portCode){
		return serviceDao.getDataListByPort(portCode);
	}
	
	@Override
	public HashMap<String ,List<FundAcc>> getDataListByPortlist(String[] portCodeList){
		return serviceDao.getDataListByPortlist(portCodeList);
	}
	
	@Override
	public FundAcc getFundAccByAccNo(String accNo) {
		return serviceDao.getFundAccByAccNo(accNo);
	}
	
	@Override
	public FundAcc getFundAccByAcc(FundAcc fundAcc) {
		return serviceDao.getFundAccByAcc(fundAcc);
	}

	@Override
	public FundAcc getFundAccById(String id) {
		return serviceDao.getFundAccById(id);
	}
	
	@Override
	public String getIdAfterSave(FundAcc pojo) {
		return serviceDao.getIdAfterSave(pojo);
	}
	

	@Override
	public String unAuditById(BasePojo pojo) {
		String result = super.unAuditById(pojo);
		BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
		CacheRefreshInfo info = new CacheRefreshInfo();
		info.setOper(CacheOper.AUDIT);
		List<String> idsList = new ArrayList<String>();
		idsList.add(pojo.getId());
		info.setIdList(idsList);
		fundAccCache.update(info);
		
		return result;
	}

	@Override
	public String unAuditById(List<BasePojo> pojoList) {
		String result = super.unAuditById(pojoList);
		
		BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
		CacheRefreshInfo info = new CacheRefreshInfo();
		info.setOper(CacheOper.AUDIT);
		List<String> idsList = new ArrayList<String>();
		for(int i= 0;i<pojoList.size();i++){
			idsList.add(pojoList.get(i).getId());
		}
		info.setIdList(idsList);
		fundAccCache.update(info);
		
		return result;
	}

	@Override
	public Boolean savePortFundRela(String portCodes, String fundAccID, String accountType) {
		Boolean bol = serviceDao.savePortFundRela( portCodes, fundAccID, accountType);
		if(bol){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			idsList.add(fundAccID);
			info.setIdList(idsList);
			fundAccCache.update(info);
			
			////BUG #361947 【南方基金】【0831】存在账户删除或修改后 ，新增手工或生成自动指令后，加载的账户还是旧的账户信息
			try {
				IFrameworkPublisher ifc=(IFrameworkPublisher)YssServiceFactory.getInstance().createService(IFrameworkPublisher.class);
				Map<String, Object> sendDetail = new HashMap<String, Object>();
				sendDetail.put("FUN_CODE", "base_info");
				sendDetail.put("saveFundAccById", fundAccID);
				sendDetail.put("PORT_CODE", portCodes.replace(",", "#"));
			    String detailMsg = JsonUtil.toString(sendDetail);
	            ifc.send(detailMsg, TopicConstants.UFP_NOTICE.toString());
	            logger.info("向支付发送账户关联更新消息:"+detailMsg);
			} catch (Exception e) {
	            logger.error("给综合指令界面发送更新账户关联消息失败", e);
	        }
		}
		return bol;
	}

	@Override
	public Boolean deletePortFundRela(String relaCode,String port) {
		Boolean bol = serviceDao.deletePortFundRela(relaCode,port);
		if(bol){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			for(String id : relaCode.split(",")){
				if(!StringUtil.IsNullOrEmptyT(id)){
					idsList.add(id);
				}
			}
			info.setIdList(idsList);
			fundAccCache.update(info);
		}
		return bol;
	}
	
	@Override
	public Boolean deletePortsFundRela(String relaCode,String port) {
		Boolean bol = serviceDao.deletePortsFundRela(relaCode,port);
		
		if(bol){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			idsList.add(relaCode);
			info.setIdList(idsList);
			fundAccCache.update(info);
		}
		
		return bol;
	}
	
	@Override
	public String getPortsByRelaCode(String id, String portCode){
		return serviceDao.getPortsByRelaCode(id, portCode);
	}
	
	@Override
	public String getPortsByRelaCode(String id){
		return serviceDao.getPortsByRelaCode(id, "");
	}

	@Override
	public Boolean deleteUnusePortRela() {
		return serviceDao.deleteUnusePortRela();
	}
	
	@Override
	public String insert(String orgCode, List<FundAcc> pojoList) {
		String retInfo = "";
		try {
			////插入账户信息
			List<String> cidenList = serviceDao.insert(pojoList);
			////插入机构账户关联信息
			serviceDao.insertOrgRelaFundAcc(pojoList,orgCode);
			cidenList.add("fax");
			menuId = cidenList.get(cidenList.size()-1);
			cidenList.remove(cidenList.size()-1);
			retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);
		} catch (Exception ex) {
			String errorMess = "";
			if (ex.getMessage().contains(MvcConstant._DB_ORA_UNIQUE_ERR_CODE)) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
						ex.getMessage(), dao, pojoList.get(0));
			} else if (ex.getMessage().contains(
					"Invalid Date Data While oper UpdateById ! ")) {
				errorMess = ReturnInfoGenerator.getDateErrStr();
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}

			logger.log(ex.getMessage());
			throw new ErrorMessageException(ex, errorMess);
		}

		return retInfo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> getDataListByAssCode(String portCode)
			throws ServiceException {
		return (List<K>) serviceDao.getDataListByAssCode(portCode);
	}
	
	/**
	 * 根据存款账户类型和已经关联到该组合或者没有关联到任一组合的现金账户 从估值迁移 器去除依赖    20171026 add by zhouning
	 * @param portCode 组合代码
	 * @param dvAccType 现金账户类型
	 * @return 现金账户集合
	 */
	public List<CashAcc> getCADataListByPortCode(String portCode, String dvAccType) throws ServiceException
	{
		try {
			return serviceDao.getDataListByPortCode(portCode, dvAccType);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
	
	@Override
	public String queryZfbyPort(String c_open_addr, String c_open_acc_no,String c_open_acc_name) {
		return serviceDao.queryZfbyPort( c_open_addr, c_open_acc_no,c_open_acc_name);
	}
	
	
	/** 
	 * @Title saveRelaInfo 
	 * @Description 保存关联信息
	 * @author liminghong@ysstech.com
	 * @date 2017年8月25日
	 * @param fa
	 * @param portCode
	 */
	@Override
	public String saveRelaInfo(FundAcc fa,String portCode){
		try {
			if(!StringUtil.IsNullOrEmpty(fa.getC_PORT_CODE())){
//				PortRela portRela = new PortRela();
//				portRela.setC_PORT_CODE(fa.getC_PORT_CODE());//组合代码
//				portRela.setC_RELA_CODE(fa.getId());//账户c_iden
//				portRela.setC_DV_TYPE_CODE(fa.getC_ACCOUNT_TYPE());//账户类型
				savePortFundRela(fa.getC_PORT_CODE(), fa.getId(), fa.getC_ACCOUNT_TYPE());
			}
		} catch (Exception e) {
			logger.log("保存账户关联信息失败", e);
		}
		return "";
	}
	
	/**
	 * BUG #361947 【南方基金】【0831】存在账户删除或修改后 ，新增手工或生成自动指令后，加载的账户还是旧的账户信息
	 */
	 public Boolean deleteByRealId(String[] realIds){
         Boolean flag = serviceDao.deleteByRealId(realIds);
         if(flag){
                      try {
                              IFrameworkPublisher ifc=(IFrameworkPublisher)YssServiceFactory.getInstance().createService(IFrameworkPublisher.class);
                              Map<String, Object> sendDetail = new HashMap<String, Object>();
                              sendDetail.put("FUN_CODE", "base_info");
                              sendDetail.put("deleteFundAccById", StringUtil.join(realIds, ","));
                          String detailMsg = JsonUtil.toString(sendDetail);
                  ifc.send(detailMsg, TopicConstants.UFP_NOTICE.toString());
                  logger.info("向支付发送账户更新消息:"+detailMsg);
                      } catch (Exception e) {
                  logger.error("给综合指令界面发送更新账户消息失败", e);
              }
         }
              return flag;
      }
	
	/**
	 * 产品生命周期查询
	 */
	public List<PortRela> getFundAccPort(String portCode){
		return serviceDao.getFundAccPort(portCode);
	}
	
	/**
	 * 产品生命周期查询
	 */
	public List<PortRela> getFundAcc(String[] recordIds){
		return serviceDao.getFundAcc(recordIds);
	}
	/**
	 * BUG #187667 支付参数“账户是否二次录入”参数值的维护需优化
	 * 可选值："0":否;"1":是;
	 * 读取配置文件
	 * zhangfengjun
	 */
	public String readProperty() {
		//当不存在配置文件时，默认值为"0"
		String checkAccInfo = "0";
		//读取properties配置文件
		FileStorePathUtil fileUtil = new FileStorePathUtil(
				"transfer/checkAccInfo.properties");
		String file = fileUtil.getFilePath();
		Properties pro = new Properties();
		//Fortify 规范代码改造避免空指针异常
		InputStream ips = null;
		try {
			ips =new FileInputStream(file);
			pro.load(ips);
			Iterator<String> it=pro.stringPropertyNames().iterator();
			while(it.hasNext()){
				String key=it.next();
				checkAccInfo = pro.getProperty(key);
			}
		} catch (java.io.FileNotFoundException e0) {
			logger.log("二次录入的配置文件：checkAccInfo.properties不存在，取默认值！");
			return checkAccInfo;
		} catch (IOException e) {
			logger.log(e.getMessage());
			throw new DataAccessException("读取checkAccInfo.properties参数文件出错",e);
		} finally{
			if (ips !=null){
				try {
					ips.close();
				} catch (IOException e) {
					//e.printStackTrace();
					logger.debug("读取checkAccInfo.properties参数文件出错:" + e.getMessage());
				}
			}
		}
	
		return checkAccInfo ;
	}
	
	/**
	 * param :修改
	 */
	public String updateProperty(String checkInfo) {
		FileStorePathUtil fileUtil = new FileStorePathUtil(
        		"transfer/checkAccInfo.properties");
		String file = fileUtil.getFilePath();
		
		Properties pro = new Properties();
		//Fortify 规范代码改造避免空指针异常
		InputStream ips = null;
		try {
			ips = new FileInputStream(file);          
			pro.load(ips);
			pro.setProperty("checkAccInfo", checkInfo);
			ips.close();
			this.storeProperty(pro, file, checkInfo);
		}
		 catch (Exception e) {
			logger.log(e.getMessage());
			throw new DataAccessException("写入checkAccInfo.properties参数文件出错",e);
		}finally{
			try {
				if(ips!=null){
					ips.close();
				}
			} catch (IOException e) {
				logger.debug("写入checkAccInfo.properties参数文件出错:" + e.getMessage());
			}
		}
	
		return checkInfo ;
	}
	
	/**
	 * //Fortify 规范代码改造避免空指针异常
	 * @param pro
	 * @param file
	 * @param checkInfo
	 */
	private void storeProperty(Properties pro,String file,String checkInfo){
		
		FileOutputStream fos = null ;
		
		try {
			fos = new FileOutputStream(file);     
			pro.store(fos,checkInfo);
			fos.close();
		}
		 catch (Exception e) {
			logger.log(e.getMessage());
			throw new DataAccessException("写入checkAccInfo.properties参数文件出错",e);
		}finally{
			try {
				if(fos!=null){
					fos.close();
				}
			} catch (IOException e) {
				logger.debug("写入checkAccInfo.properties参数文件出错:" + e.getMessage());
			}
		}
	
	}
	
	

	@Override
	public String showAreaA() {
		// 仅返回"false"时才不显示A区，其他情况都显示
		String showAreaA = "true";
		try {
			String fileName = "taqsConfig/taqs.properties";
			FileStorePathUtil fileStorePathUtil = new FileStorePathUtil(
					fileName);
			PropertiesUtil propertiesUtil = new PropertiesUtil();
			propertiesUtil.Properties(fileStorePathUtil.getFilePath());
			String value = propertiesUtil.getValue("fundacc.showAreaA");
			if (!StringUtil.IsNullOrEmpty(value)) {
				showAreaA = value;
			}
		} catch (Exception e) {
			logger.debug("获取配置文件失败（非TA产品无需关注此问题）:" + e.getMessage());
		}

		return showAreaA;
	}

	@Override
	public <K extends BasePojo> String insert(K pojo) {
		
		return super.insert(pojo);
	}

	/**
	 * 缓存获取账户集合
	 * @return
	 */
	public List<FundAcc> getFundAccListByCache(){
		BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
		// BUG #333384 【性能测试】支付平台性能测试，指令发送待优化sql gwz58h2c3maac
		// 影响性能，经测试没有下面bug的问题
		/*
		 * BUG267037【30.7UI测试】账户渠道设置，新增的账户无法设置账户渠道，保存不了数据
		 * 读取缓存前先加载一次数据,防止新增的数据没有更新到缓存中导致无法获取到 modified by lijinpeng 2019年7月11日
		 */
		//fundAccCache.loadDataPublic();
		return  fundAccCache.getCacheList();
	}
	
	
	/**
	 * 账户字符串中，去除中间的款项类型
	 * @param search
	 * @return
	 */
	@Override
    public String getAccByRmHkType(String search){
    	return serviceDao.getAccByRmHkType(search);
    }

	@Override
	public List<BasePojo> getFundAccNoAndAddrList() {
		return serviceDao.getFundAccNoAndAddrList();
	}
	
	@Override
	public HashMap<String, FundAcc> getUniqueAccountTypeByPorts(String portCodes, String accountType) {
		return serviceDao.getUniqueAccountTypeByPorts(portCodes,accountType);
	}
	
	@Override
	 public ArrayList<FundAcc> getAllFundAccByType(HashMap<String,String> paraMap) throws Exception {
	    //首先获取当前用户有权限的组合
		List<String> portList = null;
		StringBuffer sb = new StringBuffer();
		String portStr = "";
		IFASTDataAuthorityService dataService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
		try {
			portList = dataService.queryByUser("1", paraMap.get("userCode"));
		} catch (Exception e) {
			logger.error("获取当前用户的组合失败：："+ e.getMessage());
			throw e;
		}
		if(portList != null && portList.size() != 0){
			for(String a : portList){
				sb.append(a+",");
			}
		}
		if(sb.toString().endsWith(",")){
			portStr = sb.toString().substring(0, sb.toString().length()-2);
		}
		return serviceDao.getAllFundAccByType(paraMap,portStr);
	 }

	@Override
	public <K extends BasePojo> String update(K pojo) {
		// TODO Auto-generated method stub
		String result = super.update(pojo);

		FundAcc acc = (FundAcc) pojo;
		if (acc != null && acc.getId() != null) {
			String accountType = acc.getC_ACCOUNT_TYPE();
			if (accountType == null) {
				accountType = "";
			}
			serviceDao.updateFundAccRela(acc.getId(), accountType);
		}

		return result;
	}
	
	@Override
	public void deleteThenSaveFundRela(String relaCode, String port, String accountType) {
		Boolean bol = serviceDao.deleteThenSaveFundRela(relaCode, port, accountType);
		
		if(bol){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			idsList.add(relaCode);
			info.setIdList(idsList);
			fundAccCache.update(info);
		}
		
	}

	@Override
	public HashMap<String,String> getFundAccByNoAddrName(List<FundAcc> list){
		return serviceDao.getFundAccByNoAddrName(list);
	}

	@Override
	public QueryRes getAccInAccountTreeView(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = (List<BasePojo>) serviceDao.queryByCondition(paraMap, FundAcc.class);

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuId,
					InformationActivator.class));
		
			setShowConvertAssemble(queryRes);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("fundAccInfo");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("账户树形结构A区：查询账户树形结构账户数据出错", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}

	/**
	 * [合并代码]STORY71636【中银基金】【高】运营类指数许可费支付指令生成。 add by lijinpeng 2019年5月6日
	 * 根据组合和账户类型使用f_get216_acc_no获得对应的账户
	 */
	@Override
	public ArrayList<FundAcc> queryAccNoByfun(HashMap<String, String> paraMap) {
		return serviceDao.queryAccNoByfun(paraMap);
	}
	
	@Override
	public String updatePaycodeByAcc(HashMap<String, String> map) {
		return serviceDao.updatePaycodeByAcc(map);
	}
	
	/**
	 * 附件下载
	 * @param filePath
	 * @return
	 */
	@Override
	public String fileDown(String filePath){
		final String SAVE_PATH = "/file/updowntemp/";
		File file = null;
		try{
			FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
			String realPath = fileStorePathUtil.getFilePath() + filePath;
			Context context = ContextFactory.getContext();
			IApplication app = context.getPlatformInfo();
			file = new File(realPath);
			if(!file.exists()){
				throw new ServiceException("文件["+file.getName()+"]被移动,请联系管理员！");
			}
			CopyFileUtil.copyFileToDiffAppServer(realPath, SAVE_PATH, app);
		}catch(Exception e){
			
		}
		// findbugs问题修改
		return SAVE_PATH + (file == null? "" : file.getName());
	}
	
	@Override
	public HashMap<String, String> filesDownLoad(List<String> fileList) {
		final String savePath = "/file/updowntemp/";
		HashMap<String, String> fileMap = new HashMap<String, String>();
		try {
			for (String filePath : fileList) {
				FileStorePathUtil fileStorePathUtil = new FileStorePathUtil("");
				String realPath = fileStorePathUtil.getFilePath() + filePath;
				Context context = ContextFactory.getContext();
				IApplication app = context.getPlatformInfo();
				File file = new File(realPath);
				if(!file.exists()){
					fileMap.put(filePath,filePath + "目标文件不存在！");
				}
				CopyFileUtil.copyFileToDiffAppServer(realPath, savePath, app);
				fileMap.put(filePath, savePath + file.getName());
			}
		} catch (Exception e) {
			logger.log("下载文件出错!",e.getMessage());
		}
		return fileMap;
	}
	
	@Override
	public String delFile(List<String> filePathList) {
		Boolean btrue = true;
		for (String path : filePathList) {
			File file = new File(path);
			if (file.exists() && file.isFile()) {
				 btrue = file.delete();
			}
		}
		return "Success";
		
	}
	
	@Override
	public String updateFileMsg(List<DataEnclosure> delFileList, List<DataEnclosure> addFileList) {
		String result = serviceDao.updateFileMsg(delFileList, addFileList);
		FileStorePathUtil fileUtil = new FileStorePathUtil("transfer/attachment");
		for (DataEnclosure data : addFileList) {
			String fileName = data.getC_FileName_S();
		    String fullPath = fileUtil.getFilePath() + fileName.replace("transfer/attachment", "");;
	        File uploadFile = new File(fullPath);
			try {
				UpLoadByNewPath("", uploadFile, "sys");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				logger.error("下载文件出错!",e);
			}
		}
		return result;
	}
	
	/**
	 * 上传文件的方法，如果远程服务的虚拟路径已存在该文件，则直接返回
	 * @param remoteFilePath
	 * @param cjdFile
	 * @param userCode
	 * @return
	 * @throws Exception 
	 */
    public ResourceMgr UpLoadByNewPath(String remoteFilePath, File cjdFile, String userCode) throws Exception
    {
        IResourceMgrService resMgrService = YssServiceFactory.getInstance().
        	       createService(IResourceMgrService.class);        
        ResourceMgr sResult = new ResourceMgr();
        sResult.setModifier(userCode);
        File file = new File(cjdFile.getPath());
    	InputStream fs = new FileInputStream(file);
        try
        {
        	byte[] bytes =  IOUtils.readBytesFromStream(fs);
            int length = 5 * 1024 * 1024;
            int count = bytes.length / length;
            if (bytes.length % length > 0)
            {
                count++;
            }
            byte[] buf = new byte[length];
            for(int i = 0; i < count; i++){
				if((bytes.length - i*length) >= length){
					System.arraycopy(bytes,i*length,buf,0,length - 1);
				}else{
					System.arraycopy(bytes,i*length,buf,0,(bytes.length - i*length) - 1);
				}
				FileTrans fileTrans = new FileTrans();
				fileTrans.setB_FileBytes(buf);
				fileTrans.setC_FileName(cjdFile.getName());
				fileTrans.setN_Index(i);
				fileTrans.setN_Count(count);
				sResult = resMgrService.uploadWithOwnerPath(sResult, remoteFilePath, fileTrans);
			}
            return sResult;
        }
        catch (Exception e)
        {
            return new ResourceMgr();
        }
        finally
        {
        	if(fs != null){
                try {
                    fs.close();
                } catch (IOException e) {
                }
            }
        }
    }
    
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		IDataEnclosureService dataEnService = YssServiceFactory.getInstance()
				.createService(IDataEnclosureService.class);
		HashMap<String, Object> paraRelaMap = null;
		QueryRes queryRes = super.queryByCondition(paraMap, page);
		List<BasePojo> dataList = queryRes.getDataList();
		for (BasePojo bp : dataList) {
			FundAcc acc = (FundAcc) bp;
			paraRelaMap = new HashMap<String, Object>();
			paraRelaMap.put("dataClass", "DataEnclosure");
			paraRelaMap.put("ARRAY_C_DATAID", bp.getId());
			QueryRes queryResRela = dataEnService.queryByCondition(paraRelaMap);
			acc.setN_FILE_COUNT(queryResRela.getDataList().size());
			BasePojo pojo = (BasePojo)acc;
			pojoList.add(pojo);
		}
		queryRes.setDataList(pojoList);
		return queryRes;
	}
	
	/**
	  * 根据组合代码获取资产代码
	  * @param codes
	  * @return
	  */
	@Override
	 public HashMap<String,String> getAssCodeByPortcode(String codes,String type){
		 return serviceDao.getAssCodeByPortcode(codes,type);
	 }
	
	/**
     * 更新账户缓存
     * @param idList
     * @param type
     */
   @Override
   public void updateFundAccCache(List<String> idList,String type){
       try{
               BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
               if("0".equals(type)){
                       CacheRefreshInfo info = new CacheRefreshInfo();
                       info.setOper(CacheOper.AUDIT);
                       info.setIdList(idList);
                       fundAccCache.update(info);
               }else if("1".equals(type)){
                       SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                       String timestemp = df.format(new Date());
                       fundAccCache.setTimestamp(timestemp);
                       fundAccCache.loadDataPublic();
               }else if("2".equals(type)){
                       CacheRefreshInfo info = new CacheRefreshInfo();
                       info.setOper(CacheOper.EDIT);
                       fundAccCache.update(info);
               }
           }catch(Exception ex){
                   logger.error("更新账户缓存失败" ,ex);
           }
   }
   
   /**
	  * STORY #91838 【汇添富基金】账户插入restful接口
	  * @author zmk
	  * @date 2020-09-24
    * @param runningAccs 交易编号
    * @return
    */
    @Override
    public List<FundAcc> getAccByRunningAccs(String openAccNo,String openAddr, String openAccName, String dcCode){
       	return serviceDao.getCashAccByRunningAccs(openAccNo,openAddr,openAccName,dcCode);
	}
    public void updateFundAccById(FundAcc funAcc) {
		serviceDao.updateFundAccById(funAcc);
	}
    
    @Override
	public String getTimeByRelaPort(String portCode, String fundAccId){
		return serviceDao.getTimeByRelaPort(portCode, fundAccId);
	}
    
    @Override
	public String updateTimeByRelaPort(HashMap<String, String> paraMap) {
		String successed = String.valueOf(false);
		successed = serviceDao.updateTimeByRelaPort(paraMap);
		return successed;
  	}
    
    @Override
	public Boolean savePortFundRelaWithDate(HashMap<String, String> paraMap) {
        String fundAccID =	paraMap.get("id");
		Boolean bol = serviceDao.savePortFundRelaWithDate(paraMap);
		if(bol){
			BaseFundAccCache fundAccCache = (BaseFundAccCache)CacheManager.getInstance().getCache(CacheGroup.FUNDACC);
			CacheRefreshInfo info = new CacheRefreshInfo();
			info.setOper(CacheOper.AUDIT);
			List<String> idsList = new ArrayList<String>();
			idsList.add(fundAccID);
			info.setIdList(idsList);
			fundAccCache.update(info);
		}
		return bol;
	}   	
	@Override
	public String getAccListByOpenNoAndOpenAddr(String openNo, String openAddr, String iden) {
		return serviceDao.getAccListByOpenNoAndOpenAddr(openNo, openAddr, iden);
	}

	@Override
	public String insertFundAcc(FundAcc fa) {
		return serviceDao.insertFundAcc(fa);
	}

	@Override
	public String addPortCodeRela(String id, String fundId) {
		return serviceDao.addPortCodeRela(id,fundId);
	}

	@Override
	public FundAcc getFundAccByGF(Map<String, String> paraMap) {
		return serviceDao.getFundAccByGF(paraMap);
	}   	
}
