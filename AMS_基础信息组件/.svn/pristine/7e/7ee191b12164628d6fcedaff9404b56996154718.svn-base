package com.yss.ams.base.information.modules.sys.automaticSet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.StringUtils;
import com.yss.ams.base.information.modules.sys.automaticSet.dao.AutomaticSetPathDao;
import com.yss.ams.base.information.modules.sys.automaticSet.dao.AutomaticSetPathSqlBuilder;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPathPojo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetPathService;
import com.yss.fast.task.support.automatic.pojo.FileScanChannel;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.uco.dataintegration.support.dataservice.pojo.ImpCfgGroup;
import com.yss.uco.dataintegration.support.dataservice.service.imp.IImpCfgGroupDataService;

/**
 * 自动化业务设置实现类 STORY #106083 【东证资管】券商结算模式增加动态判断的接口（105821拆出给估值）
 * 
 * @ClassName: AutomaticSetServiceImpl
 * @date 2021年06月01日
 * @Stroy105821
 * @author zhuziqing
 */
public class AutomaticSetPathServiceImpl extends ServiceBus<AutomaticSetPathServiceImpl>
		implements IAutomaticSetPathService {

	private AutomaticSetPathDao automaticSetPathDao = null;

	public AutomaticSetPathServiceImpl() {
		automaticSetPathDao = new AutomaticSetPathDao(DbPoolFactory.getInstance().getPool(),
				new AutomaticSetPathSqlBuilder());
		dao = automaticSetPathDao;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		return null;
	}

	@Override
	public List<Vocabulary> getAllProductType() throws ServiceException {
		return automaticSetPathDao.getAllProductType();
	}

	@Override
	public boolean updateDataList(List<HashMap<String, String>> paraMap) {
		return automaticSetPathDao.updateDataList(paraMap);
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode) throws ServiceException {
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws ServiceException {
		return null;
	}

	@Override
	public QueryRes queryDataList(HashMap<String, Object> paraMap) throws ServiceException {
		PageInation page = new PageInation();
		QueryRes queryRes = null;
		// 转换审核数据类型
		try {
			if (paraMap.get("CHECK_STATE") != null) {
				String status = paraMap.get("CHECK_STATE").toString();
				if (!StringUtils.isNullOrEmpty(status)) {

					paraMap.put("CHECK_STATE", YssFun.toNumber(status));
				}
			}

			queryRes = super.queryByCondition(paraMap, page);

			ListHeadDtl listHeadInfo = null;
			boolean isOSGI = YssContextFactory.getInstance().getOSGI();
			if (isOSGI) {
				Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
				listHeadInfo = context.getListHeadMap("automaticSet_wbqdzhljsz");
			}
			if (listHeadInfo != null) {
				List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
				queryRes.setHeadKeyList(headKeyList);
			}
		} catch (YssException e) {
			logger.error("条件查询数据失败：" + e.getMessage(), e);
			throw new ServiceException(e);
		}
		return queryRes;
	}

	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap, PageInation page) {
		QueryRes queryRes = null;
		// 转换审核数据类型
		try {
			if (paraMap.get("CHECK_STATE") != null) {
				String status = paraMap.get("CHECK_STATE").toString();
				if (!StringUtils.isNullOrEmpty(status)) {

					paraMap.put("CHECK_STATE", YssFun.toNumber(status));
				}
			}
			//获取界面类型
			String tabType =" ";
			if(null != paraMap.get("C_BUSINESS_TYPE_CODE")) {
				 tabType = paraMap.get("C_BUSINESS_TYPE_CODE").toString();
			}

			queryRes = super.queryByCondition(paraMap, page);
			
			//维护 "所属估值表日期" 字段
			if ("GZZB".equals(tabType) && null != queryRes.getDataList()) {
				List<BasePojo> list = queryRes.getDataList();
				for (BasePojo basePojo : list) {
					AutomaticSetPathPojo pojo =(AutomaticSetPathPojo)basePojo;
					String vaTime = pojo.getC_VA_TIME();
					if("JDJZRQ".equals(vaTime)) {
						pojo.setC_VA_TIME("节点基准日期");
					}else if("JDJZRQHSYGZR".equals(vaTime)) {
						pojo.setC_VA_TIME("节点基准日期和上一工作日");
					}
				}
			}

			String totalNum = super.queryDataTotal(paraMap);
			page.setTotalNum(Integer.parseInt(totalNum));

			ListHeadDtl listHeadInfo = null;
			boolean isOSGI = YssContextFactory.getInstance().getOSGI();
			if (isOSGI) {
				Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
				if ("GZZB".equals(tabType)) {
					listHeadInfo = context.getListHeadMap("automaticSet_gzzb");
				} else {
					listHeadInfo = context.getListHeadMap("automaticSet_wbqdzhljsz");
				}
			}
			if (listHeadInfo != null) {
				List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
				queryRes.setHeadKeyList(headKeyList);
			}
		} catch (YssException e) {
			logger.error("分页条件查询数据失败：" + e.getMessage(), e);
			throw new ServiceException(e);
		}
		return queryRes;
	}
	
	@Override
	public List<Vocabulary> getInterfaceClass() throws ServiceException {
		return automaticSetPathDao.getInterfaceClass();
	}

	@Override
	public List<AutomaticSetPathPojo> getProductType() {
		return automaticSetPathDao.getProductType();
	}

	@Override
	public List<ImpCfgGroup> getInterfaceData(List<String> productName) {
		List<ImpCfgGroup> list = new ArrayList<ImpCfgGroup>();

		// 获取产品分类对应的接口代码和接口父级Id
		List<AutomaticSetPathPojo> interfaceList = automaticSetPathDao.getInterfaceByName(productName);

		// 遍历结果集，调用服务获取对应的导入接口信息
		for (AutomaticSetPathPojo automaticSetPathPojo : interfaceList) {
			ImpCfgGroup impCfgGroup = new ImpCfgGroup();
			String parentId = automaticSetPathPojo.getC_INTERFACE_P_ID();
			String interfaceCode = automaticSetPathPojo.getC_INTERFACE_CODE();
			String prodName = automaticSetPathPojo.getC_PRODUCT_NAME();

			// 调用框架导入接口服务，获取对应的接口信息
			IImpCfgGroupDataService impService = YssServiceFactory.getInstance()
					.createService(IImpCfgGroupDataService.class);
			impCfgGroup = impService.getCfgGroupInfo(parentId, interfaceCode);
			impCfgGroup.setC_HDay_Code(prodName);// 此处是借用fast的pojo节假日字段存取每个接口数据对应的产品业务分类
			list.add(impCfgGroup);
		}
		return list;
	}

	@Override
	public boolean saveDataList(List<String> proList, List<HashMap<String, String>> dataList) {
		return automaticSetPathDao.saveDataList(proList, dataList);
	}

	@Override
	public boolean copy(HashMap<String, String> data) {
		return automaticSetPathDao.copy(data);
	}

	@Override
	public List<FileScanChannel> queryInterfaceChannel(FileScanChannel fileScanChannel) {
		return automaticSetPathDao.queryInterfaceChannel(fileScanChannel);
	}

	@Override
	public List<FileScanChannel> queryInterfaceChannelByPortCode(String portCode) {
		return automaticSetPathDao.queryInterfaceChannelByPortCode(portCode);
	}

	@Override
	public List<AutomaticSetPathPojo> getAllIndex() {	
		return automaticSetPathDao.getAllIndex();
	}

	@Override
	public boolean saveList(List<HashMap<String, String>> dataList) {
		return automaticSetPathDao.saveList(dataList);
	}

	@Override
	public List<AutomaticSetPathPojo> getRePortCodeList() {
		return automaticSetPathDao.getRePortCodeList();
	}

	@Override
	public List<BasePojo> queryByCodeAndName(String portCode, List<String> productName) {
		return automaticSetPathDao.queryByCodeAndName(portCode,productName);
	}
}
