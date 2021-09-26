package com.yss.ams.product.information.modules.pg.portgrouprela.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaDao;
import com.yss.ams.product.information.modules.pg.portgrouprela.dao.PortGroupRelaSqlBuilder;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.cache.PortCache;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.pojo.PortGroupRela;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.service.IPortGroupRelaService;
import com.yss.ams.product.information.util.port.PortUtil;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.busoperservice.ILogger;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BEN_RECORD;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.SafeData;
import com.yss.framework.api.common.pi.ISafeDataDataService;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.DataProcServiceFactroy;
import com.yss.framework.api.service.HttpServiceFactory;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.right.constants.RightConstants;
import com.yss.framework.context.ContextFactory;

/**
 * <产品群组管理>服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupRelaService extends ServiceBus<PortGroupRelaService> implements IPortGroupRelaService{
	
	private PortGroupRelaDao serviceDao = null;
	
	// modified by HeLiang 2017-06-22 STORY #42921 产品信息组件拆分开发
    // FunCode使用新增的，避免获取映射关系错误
	String portGroupMemuId = "pd_portGroup";
	
	public PortGroupRelaService() throws Exception{
		serviceDao = new PortGroupRelaDao(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortGroupRelaSqlBuilder());
		dao = serviceDao;
	}
	
	/**
	 * 获取备选项组合列表
	 * @return
	 */
	public List<Port> querySelectablePortList(String c_group_code){
		//BUG #173916 A区产品群组未实现新老权限兼容 begin
		List<String> selectPortCode = null;
//		if (RightConstants.isFastRight) {
//			selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
//			return convertAndFilterPortNewRight(serviceDao.queryAllPortList(),selectPortCode);
//		} else {
//			selectPortCode = serviceDao.querySelectedPortCodeList(c_group_code);
//			return convertAndFilterPort(serviceDao.queryPortList(),selectPortCode);
//		}
		//STORY #69753 业务基础组件对FAST平台表结构的解耦 
		//分布式不再支持旧权限体系，使用FAST新权限体系
		selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
		return convertAndFilterPortNewRight(serviceDao.queryAllPortList(),selectPortCode);
		
		//BUG #173916 A区产品群组未实现新老权限兼容 end
	}
	
	/**
	 * BUG #173916 A区产品群组未实现新老权限兼容
	 * 新权限体系 1、查询时过滤掉已有的组合，2.如果父级目录为空则赋值为[root]
	 * @param list
	 * @param selectPortCode
	 * @return
	 */
	public List<Port> convertAndFilterPortNewRight(List<Port> list, List<String> selectPortCode){
		//当前用户
		String userCode =  YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getUserCode();
		//根据当前用户获取用户下的组合（已根据权限过滤）
		IFASTDataAuthorityService authorService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
		List<String> userPortCodes = null;
		try {
			userPortCodes = authorService.queryByUser(RightConstants.portType, userCode);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log("convertAndFilterPortNewRight->", e);
		}
		
		for(int i=0;i<list.size();i++){
			Port port = list.get(i);
			if(null == port.getC_PORT_CODE_P() || port.getC_PORT_CODE_P().trim().length() == 0){
				port.setC_PORT_CODE_P("[root]");
			}
			for(String portCode : selectPortCode){
				if(null != userPortCodes && !userPortCodes.contains(port.getC_PORT_CODE()) || portCode.equals(port.getC_PORT_CODE())){
					list.remove(i);
					i--;
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 根据群组代码查询所有组合
	 * @return
	 */
	@Override
	public List<Port> querySelectedPortList(String c_group_code){
		//BUG #173916 A区产品群组未实现新老权限兼容 begin
		List<String> selectPortCode = null;
//		if (RightConstants.isFastRight) {
//			selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
//		}else{
//			selectPortCode = serviceDao.querySelectedPortCodeList(c_group_code);
//		}
		
	    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
        //分布式不再支持旧权限体系，使用FAST新权限体系
		selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
		//BUG #173916 A区产品群组未实现新老权限兼容 end
		List<Port> selectPort = new ArrayList<Port>();
		
		PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		
		for(String c_port_code : selectPortCode){
			if(null != portCache.getCacheByKey(c_port_code)){
				selectPort.add(portCache.getCacheByKey(c_port_code));	
			}
		}
		return convertPort(selectPort);
	}
	
	/**
	 * 根据群组代码查询群组下的所有组合
	 * @return
	 */
	public List<Port> querySelectPortList(String c_group_code){
		List<String> selectPortCode = serviceDao.querySelectPortCodeList(c_group_code);
		List<Port> selectPort = new ArrayList<Port>();
		
		PortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
		
		for(String c_port_code : selectPortCode){
			if(null != portCache.getCacheByKey(c_port_code)){
				selectPort.add(portCache.getCacheByKey(c_port_code));	
			}
		}
		return selectPort;
	}
	
	/**
	 * 根据群组代码查询所有组合代码
	 * @return
	 */
	public List<String> querySelectedPortCode(String c_group_code){
		//BUG #173916 A区产品群组未实现新老权限兼容 begin
		List<String> selectPortCode = null;
//		if (RightConstants.isFastRight) {
//			selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
//		}else{
//			selectPortCode = serviceDao.querySelectedPortCodeList(c_group_code);
//		}
	    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
        //分布式不再支持旧权限体系，使用FAST新权限体系
		selectPortCode = serviceDao.querySelectedPortCodeListNewRihgt(c_group_code);
		//BUG #173916 A区产品群组未实现新老权限兼容 end
		return selectPortCode;
	}
	
	/**
	 * 根据群组代码查询所有组合代码
	 * @return
	 */
	public List<Port> querySelectPortCode(String c_group_code){
		List<String> selectPortCode = serviceDao.querySelectPortCodeList(c_group_code);
		List<Port> selectPort = new ArrayList<Port>();
        IPortDataService portDataService =  YssServiceFactory.getInstance().createService("IPortDataService");
        List<BasePojo> pojoList = portDataService.getDataList();
		for(BasePojo pojo : pojoList){
			Port port = (Port)pojo;
			if(!selectPortCode.contains(port.getC_PORT_CODE())){
				selectPort.add(port);
			}
		}
		return convertPort(selectPort);
	}
	
	/**
	 * 根据群组代码查询所有组合代码
	 * @return
	 */
	public QueryRes querySelectedPort(String c_group_code){
		
		QueryRes queryRes = new QueryRes();
		try {
			//BUG #173916 A区产品群组未实现新老权限兼容 begin
			List<BasePojo> dataList = null;
//			if (RightConstants.isFastRight) {
//				dataList = serviceDao.querySelectedPortListNewRihgt(c_group_code);
//			}else{
//				dataList = serviceDao.querySelectedPortList(c_group_code);
//			}
			//BUG #173916 A区产品群组未实现新老权限兼容 end
	        //STORY #69753 业务基础组件对FAST平台表结构的解耦 
            //分布式不再支持旧权限体系，使用FAST新权限体系
			dataList = serviceDao.querySelectedPortListNewRihgt(c_group_code);
			
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap("pd_portfolio_A");
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();

			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_portfolio_A");
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：根据群组代码查询组合失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
		
		
	}
	/**
	 * 根据群组代码查询所有组合代码，无用户和岗位权限
	 * BUG #286325 【华宝基金】定时执行自动化流程，部分群组没有启动流程
	 * @return
	 */
	public QueryRes querySelectedPortWithoutRight(String c_group_code){
		QueryRes queryRes = new QueryRes();
		try {
			List<BasePojo> dataList = serviceDao.querySelectedPortListWithoutRight(c_group_code);
			ListHeadDtl listHeadInfo = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getListHeadMap("pd_portfolio_A");
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
			queryRes.setDataList(dataList);
			queryRes.setHeadKeyList(headKeyList);
			queryRes.setOperRes(MvcConstant._Success);
			queryRes.setMenuId("pd_portfolio_A");
		} catch (Exception ex) {
			logger.log("产品群组模块：根据群组代码查询组合失败", ex);
			queryRes.setOperRes(MvcConstant._Fault);
		}
		return queryRes;
	}
	
	/**
	 * 如果Port的父级代码为空，则赋值为[root]
	 * @param list
	 * @return
	 */
	public List<Port> convertPort(List<Port> list){
		for(Port port : list){
			if(null == port.getC_PORT_CODE_P() || port.getC_PORT_CODE_P().trim().length() == 0){
				port.setC_PORT_CODE_P("[root]");
			}
		}
		return list;
	}
	
	/**
	 * 1、查询时过滤掉已有的组合，2.如果父级目录为空则赋值为[root]
	 * @param list
	 * @param selectPortCode
	 * @return
	 */
	public List<Port> convertAndFilterPort(List<Port> list, List<String> selectPortCode){
		for(int i=0;i<list.size();i++){
			Port port = list.get(i);
			if(null == port.getC_PORT_CODE_P() || port.getC_PORT_CODE_P().trim().length() == 0){
				port.setC_PORT_CODE_P("[root]");
			}
			for(String portCode : selectPortCode){
				if(portCode.equals(port.getC_PORT_CODE())){
					list.remove(i);
					i--;
					break;
				}
			}
		}
		return list;
	}
	
	/**
	 * 保存时过滤掉已存在的组合
	 * @param list
	 * @param selectPortCode
	 * @return
	 */
	public List<BasePojo> filterExistPort(List<BasePojo> list, List<String> selectPortCode){
		List<BasePojo> needSaveList = new ArrayList<BasePojo>();
		for(int i=0;i<list.size();i++){
			PortGroupRela portGroup = (PortGroupRela) list.get(i);
			for(String portCode : selectPortCode){
				if(portCode.equals(portGroup.getC_PORT_CODE())){
					list.remove(i);
					i--;
					break;
				}
			}
		}
		return needSaveList;
	}
	
	/**
	 * 保存时先获取需要删除的数据
	 * @param list
	 * @param selectPortCode
	 * @return
	 */
	public List<String> getNeedDeleteData(List<BasePojo> list, List<String> selectPortCode){
		for(int i=0;i<selectPortCode.size();i++){
			for(BasePojo pojo : list){
				PortGroupRela portGroup = (PortGroupRela) pojo;
				if(portGroup.getC_PORT_CODE().equals(selectPortCode.get(i))){
					selectPortCode.remove(i);
					i--;
					break;
				}
			}
		}
		return selectPortCode;
	}
	
//	/**
//	 * 重写新增保存
//	 */
//	@Override
//	public String insert(List<BasePojo> pojoList) {
//		//先删除，后增加
//		List<BasePojo> needSaveList = new ArrayList<BasePojo>();
//		for(BasePojo basePojo : pojoList){
//			needSaveList.add(basePojo);
//		}
//		
//		if(pojoList.size() > 0){
//			for(BasePojo pojo : pojoList){
//				PortGroupRela portGroup = (PortGroupRela) pojo;
//				String c_group_code = portGroup.getC_GROUP_CODE(); 
//				if(null != c_group_code && c_group_code.trim().length() >0){
//					List<String> selectPortCode = serviceDao.querySelectedPortCodeList(c_group_code);
//					filterExistPort(needSaveList,selectPortCode);
//					List<String> needDeleteList = this.getNeedDeleteData(pojoList, selectPortCode);
//					if(needDeleteList.size() > 0){
//						serviceDao.deleteByGroupPortCode(c_group_code,needDeleteList);
//					}
//					break;
//				}
//			}
//		}
//		
//		return super.insert(needSaveList);
//	}
	
	@Override
	public String updateById(List<BasePojo> pojoList) {
		//// By Jinghehe 先删除数据 在添加数据 bug101026 产品群组管理，保存报错 
		if(null != pojoList && !pojoList.isEmpty()
				&& null != pojoList.get(0) )
		{
			String groupCode = ((PortGroupRela)pojoList.get(0)).getC_GROUP_CODE();
			serviceDao.deleteByGroupCode(groupCode);
		}
		
		insert(pojoList);
		return ReturnInfoGenerator.getUpdateOKStr(menuId);
	}

	/**
	 * 
	 * @param portCodes
	 * @return
	 */
	public String deleteByPortCodes(String[] portCodes) {
		String retInfo = "";
		try {
			serviceDao.deleteByPortCodes(portCodes);
			retInfo = ReturnInfoGenerator.getDeleteOKStr(menuId);
		} catch (Exception ex) {
			throw new ErrorMessageException(ex, ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeSaveErr, menuId));
//			retInfo = ReturnInfoGenerator.getOperErrMsg(
//					MvcConstant._CodeSaveErr, menuId);
//			throw new ServiceException(ex, menuId, retInfo);
		}
		return retInfo;
	}
	
	/**
	 * 功能简介：新增'产品群组设置'复制
	 * @author huangjin 2016-9-22
	 * STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	 */
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品群组设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, PortGroupRela.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("portGroupRela");
		String dataName = "产品群组设置";
		for (int i = 0; i < portCodeList.length; i++) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			for (int j = 0; j < basepojos.size(); j++) {
				PortGroupRela pojo = ((PortGroupRela) basepojos.get(j));
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
		logger.debug("产品群组设置复制结束");
		return null;
	}
	/**
	 * 功能简介：新增'产品群组设置'复制
	 * @author huangjin 2016-9-22
	 * STORY #28950 参数复制功能优化-复制权限和群组以及产品数
	 */
	public List<String> copy(String portCode, String[] portCodeList,
			String dataCode, String execProcCode, String checkState, String isClear) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品群组设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, PortGroupRela.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("portGroupRela");
		String dataName = "产品群组设置";
		for (int i = 0; i < portCodeList.length; i++) {
			BEN_RECORD ben_Record = new BEN_RECORD(userCode);
			for (int j = 0; j < basepojos.size(); j++) {
				PortGroupRela pojo = ((PortGroupRela) basepojos.get(j));
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
		logger.debug("产品群组设置复制结束");
		return null;
	}
	
	public List<String> copy(String portCode, HashMap<String, String> portCodeList,
			String dataCode, String execProcCode, String checkState) {
		ILogger log = DataProcServiceFactroy.getInstance().createService(
				ILogger.class);
		logger.debug("产品群组设置复制开始");
		HashMap<String, Object> paraMap = new HashMap<String, Object>();
		paraMap.put("C_PORT_CODE", portCode);
		List<BasePojo> basepojos = super.query(paraMap, PortGroupRela.class);
		List<BasePojo> saveList = new ArrayList<BasePojo>();
		/* STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况 存放复制失败组合 */
		List<String> failPorts = new ArrayList<String>();
		String userCode = ContextFactory.getContext().getUserCode();
		ISafeDataDataService safeDataService = HttpServiceFactory.getInstance().createService(ISafeDataDataService.class);
		SafeData safeData = safeDataService.getByFunCode("portGroupRela");
		String dataName = "产品群组设置";
		
		Map<String ,List<String>> selfGroupCodeList=serviceDao.queryGroupCodeByPortCodeList(portCodeList);
		for (String portTemp : portCodeList.keySet()) {
			List<String> groupList = null;
			//查询本组合存在的群组
			if(selfGroupCodeList.size()>0){
				groupList=selfGroupCodeList.get(portTemp);
			}
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
						PortGroupRela pojo = ((PortGroupRela) basepojos.get(j));
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
							/* BUG #357979 【估值核算】【富国基金】【300.7-1031.0131_FGJJ】上交所过户库清算流水成交数量错误  */
							//复制群组前进行判断，如果目标组合的群组与在本组合已存在，则不再进行重复添加。		
							//判断组合是不是已经包含这个群组
							if (null == groupList || groupList.size() ==0) {
								saveList.add(pojo);
							}else if (!groupList.contains(pojo.getC_GROUP_CODE())) {
								saveList.add(pojo);	
							}	
					}
					}					
					try {
						if (saveList.size()==0) {
							throw new DataAccessException("与目标组合的群组一致，无需复制");						
						}
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
				/* STORY64614富国基金-【运维】产品参数复制功能优化，需考虑重复复制的情况 存放复制失败组合 */
				failPorts.add(portTemp);
				ben_Record.appendDetailMes(pre + "复制失败！" + e.getMessage());
				ben_Record.EndLog_Fail("复制数据错误！");
			} finally {
				saveList.clear();
				ben_Record.setC_User_Code(userCode);
				log.write(execProcCode,
						ben_Record);
			}
			logger.debug("复制组合  " + portTemp);
		}
		logger.debug("产品群组设置复制结束");
		return failPorts;
	}
	
	/**
	 * add method 根据群组代码获取组合
	 * 返回组件framework组合对象
	 * @param groupCode
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, com.yss.framework.api.common.co.Port> getFWPortByGroupCode(
			String groupCode) throws ServiceException {
		Map<String, Port> commPortMap =serviceDao.getPortByGroupCode(groupCode);
		HashMap<String, com.yss.framework.api.common.co.Port> fastPortMap = new HashMap<String, com.yss.framework.api.common.co.Port>();
		for (String key : commPortMap.keySet()) {
			fastPortMap.put(key, commPortMap.get(key));
		}
		return fastPortMap;
	}
	
	/**
	 * add method 根据群组代码获取组合
	 * 返回组件组合对象
	 * @param groupCode
	 * @return
	 * @throws ServiceException
	 */
	public Map<String, Port> getZJPortByGroupCode(String groupCode) throws ServiceException {
		return serviceDao.getPortByGroupCode(groupCode);
	}
	
	/**
	 * STORY #103420 产品组件提供批量查找群组的方法
	 * @param portCodeList
	 * @return
	 */
	public Map<String, List<String>> queryGroupCodeByPortCodeList(List<String> portCodeList) {
		return serviceDao.queryGroupCodeByPortCodeList(portCodeList);
	}
	/**
	 *  STORY #103420 产品组件提供批量查找群组的方法
	 * @return
	 */
	public Map<String, List<String>> queryAllGroupCode() {
		return serviceDao.queryAllGroupCode();
	}
	
}
