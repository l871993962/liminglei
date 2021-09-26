package com.yss.ams.base.information.modules.sys.automaticSet.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.sys.automaticSet.dao.AutomaticSetDao;
import com.yss.ams.base.information.modules.sys.automaticSet.dao.AutomaticSetSqlBuilder;
import com.yss.ams.base.information.support.sys.automaticSet.pojo.AutomaticSetPojo;
import com.yss.ams.base.information.support.sys.automaticSet.service.IAutomaticSetService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.User;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.pojo.sysinit.HeadKey;
import com.yss.mvc.pojo.sysinit.ListHeadDtl;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.system.user.service.IUserService;

/** 
 * 自动化业务设置实现类
 * @ClassName: AutomaticSetServiceImpl
 * @date 2020年12月24日
 * @Stroy90952
 * @author yangze
 */
public class AutomaticSetServiceImpl  extends ServiceBus<AutomaticSetServiceImpl> implements IAutomaticSetService {
	
	private AutomaticSetDao automaticSetDao= null;
	
	public AutomaticSetServiceImpl() {
		automaticSetDao = new AutomaticSetDao(DbPoolFactory.getInstance().getPool(), new AutomaticSetSqlBuilder());
		dao = automaticSetDao;
	}
	
	@Override
	public List<Vocabulary> getDataListByType(String type) throws ServiceException {
		return automaticSetDao.getDataListByType(type);
	}
	
	@Override
	public boolean updateDataList(String type, HashMap<String, String> paraMap) throws ServiceException {
		return automaticSetDao.updateDataList(type, paraMap);
	}
	
	@Override
	public List<String> getPortListByBusiCode(String type, String busiCode) throws ServiceException {
		return automaticSetDao.getPortListByBusiCode(type, busiCode);
	}
	
	@Override
	public List<Map<String, String>> getBusiInfoByCode(String type, String portCode) throws ServiceException {
		return automaticSetDao.getBusiInfoByCode(type, portCode);
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
			PageInation page) {
		String type = (String) paraMap.get("ARRAY_C_BUSINESS_TYPE_CODE");
		if ("ZRKJ_TYPE".equals(type)) {
			automaticSetDao.updateSetDataList(type, paraMap);
		}
		if (paraMap.containsKey("ALL_C_PORT_CODE")) {
			paraMap.remove("ALL_C_PORT_CODE");
		}
		
		QueryRes queryRes = super.queryByCondition(paraMap, page);
		
		if ("ZRKJ_TYPE".equals(type)) {
			ListHeadDtl listHeadInfo = null;
			boolean isOSGI = YssContextFactory.getInstance().getOSGI();
			if(isOSGI){
				Context context = YssContextFactory.getInstance().getAppContext(bundleContext.getAppCode());
				listHeadInfo = context.getListHeadMap("automaticSet_zrkj");
			}else{
				listHeadInfo = AppContext.getInstance().getListHeadMap("automaticSet_zrkj");
			}
			List<HeadKey> headKeyList = listHeadInfo.getHeadKeyList();
			queryRes.setHeadKeyList(headKeyList);
			
			Map<String, String> userMap = new HashMap<String, String>();
			IUserService userService = YssServiceFactory.getInstance().createService(IUserService.class);
            List<User> userList = userService.queryAllUsers();
            for (User user : userList) {
            	userMap.put(user.getC_USER_CODE(), user.getC_USER_NAME());
            }
            for (BasePojo pojo : queryRes.getDataList()) {
            	AutomaticSetPojo automaticSetPojo = (AutomaticSetPojo) pojo;
            	String businessCode = automaticSetPojo.getC_BUSINESS_CODE();
            	if (null != businessCode && !"".equals(businessCode.trim())) {
            		automaticSetPojo.setC_BUSINESS_CODE(businessCode + "_" + userMap.get(businessCode));
            	}
            }
		}
		
		return queryRes;
	}
	
	@Override
	public String queryDataTotal(HashMap<String, Object> paraMap) {
		if (paraMap.containsKey("ALL_C_PORT_CODE")) {
			paraMap.remove("ALL_C_PORT_CODE");
		}
		return super.queryDataTotal(paraMap);
	}
	
	@Override
	public List<String> getAllBusiType() throws ServiceException {
		List<String> list = new ArrayList<String>();
		List<Vocabulary> typeList = automaticSetDao.getDataTypeList();
		for (Vocabulary type : typeList) {
			String typeCode = type.getC_DV_CODE();
			StringBuffer typeBuf = new StringBuffer();
			typeBuf.append(typeCode).append("###").append(type.getC_DV_NAME()).append("###[root]###0");
			list.add(typeBuf.toString());
			List<Vocabulary> busiList = automaticSetDao.getDataListByType(typeCode);
			for (Vocabulary busi : busiList) {
				StringBuffer busiBuf = new StringBuffer();
				busiBuf.append(busi.getC_DV_CODE()).append("###").append(busi.getC_DV_NAME()).append("###").append(typeCode).append("###1");
				list.add(busiBuf.toString());
			}
		}
		return list;
	}
	
	@Override
	public List<String> getBusiTypeByCode(String portCode) throws ServiceException {
		List<String> list = new ArrayList<String>();
		Map<String, List<String>> map = automaticSetDao.queryBusiCodeByPortCode(portCode);
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			List<String> values = entry.getValue();
			for (String value : values) {
				list.add(value + "###" + key);
			}
		}
		return list;
	}
	
	@Override
	public List<String> getBusiTypeByCodes(String type, List<String> portCodes) throws ServiceException {
		List<String> list = new ArrayList<String>();
		Map<String, List<String>> map = automaticSetDao.queryBusiCodeByPortCodes(type, portCodes);
		for (Map.Entry<String, List<String>> entry : map.entrySet()) {
			String key = entry.getKey();
			StringBuffer buf = new StringBuffer();
			for (String value : entry.getValue()) {
				buf.append(value).append("#");
			}
			if (buf.length() > 0) {
				buf.deleteCharAt(buf.length() - 1);
			}
			list.add(key + "###" + buf.toString());
		}
		return list;
	}
	
	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		HashMap<String, String> convertMap = new HashMap<String, String>();
		List<Vocabulary> typeList = automaticSetDao.getDataTypeList();
		for (Vocabulary type : typeList) {
			convertMap.put(type.getC_DV_CODE(), type.getC_DV_NAME());
			List<Vocabulary> list = automaticSetDao.getDataListByType(type.getC_DV_CODE());
			for (Vocabulary voc : list) {
				convertMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
			}
		}
		return convertMap;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws ServiceException {
		HashMap<String, String> convertMap = new HashMap<String, String>();
		List<Vocabulary> typeList = automaticSetDao.getDataTypeList();
		for (Vocabulary type : typeList) {
			convertMap.put(type.getC_DV_CODE(), type.getC_DV_NAME());
			List<Vocabulary> list = automaticSetDao.getDataListByType(type.getC_DV_CODE());
			for (Vocabulary voc : list) {
				if (listKey.contains(voc.getC_DV_CODE())) {
					convertMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
				}
			}
		}
		return convertMap;
	}

	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
}
