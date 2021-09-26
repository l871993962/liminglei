package com.yss.ams.base.information.fast.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.modules.bi.org.dao.OrgDao;
import com.yss.ams.base.information.modules.bi.org.dao.OrgSqlBuilder;
import com.yss.ams.base.information.support.bi.org.pojo.Org;
import com.yss.ams.base.information.support.bi.org.service.IOrgDataService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.commonInfo.pojo.FastOrg;
import com.yss.framework.api.commonInfo.service.IFastOrgService;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.db.DbPoolFactory;

/**
 * @ClassName 
 * @Description 
 * @author houjiaqi
 * @CreateDate 2019年1月2日 上午10:14:47
 * @Copyright (c) 2016, 深圳赢时胜 All Rights Reserved.
 */
public class FastOrgService implements IFastOrgService {

	private OrgDao orgDao = null;
	
	public FastOrgService () {
		orgDao = new OrgDao(DbPoolFactory.getInstance().getPool(), new OrgSqlBuilder());
	}
	
	private List<FastOrg> convert2FastOrg(List<Org> org) {
		List<FastOrg> fastOrgList = new ArrayList<FastOrg>();
		
		if (org != null && org.size() > 0) {
			for (Org infoOrg : org) {
				FastOrg fastOrg = new FastOrg();
				fastOrg.setD_CARD_VAL_DUR_END(infoOrg.getD_CARD_VAL_DUR_END());
				fastOrg.setD_IVT_CARD_VALDUR_END(infoOrg.getD_IVT_CARD_VALDUR_END());
				fastOrg.setC_REP_CARD_CODE(infoOrg.getC_REP_CARD_CODE());
				fastOrg.setC_DV_REPCARD_TYPE(infoOrg.getC_DV_REPCARD_TYPE());
				fastOrg.setD_CARD_VAL_DUR(infoOrg.getD_CARD_VAL_DUR());
				fastOrg.setC_ADMIN_CODE(infoOrg.getC_ADMIN_CODE());
				fastOrg.setC_ADMIN_NAME(infoOrg.getC_ADMIN_NAME());
				fastOrg.setC_ADMIN_NATURE(infoOrg.getC_ADMIN_NATURE());
				fastOrg.setC_IVT_CARD_TYPE(infoOrg.getC_IVT_CARD_TYPE());
				fastOrg.setC_IVT_CARD_NO(infoOrg.getC_IVT_CARD_NO());
				fastOrg.setD_IVT_CARD_VALDUR(infoOrg.getD_IVT_CARD_VALDUR());
				fastOrg.setC_PLATE_CODE(infoOrg.getC_PLATE_CODE());
				fastOrg.setC_INDUSTRY_TYPE(infoOrg.getC_INDUSTRY_TYPE());
				fastOrg.setC_DV_COUNTERPARTY(infoOrg.getC_DV_COUNTERPARTY());
				fastOrg.setC_ORG_CODE(infoOrg.getC_ORG_CODE());
				fastOrg.setC_ORG_NAME(infoOrg.getC_ORG_NAME());
				fastOrg.setC_ORG_NAME_CN(infoOrg.getC_ORG_NAME_CN());
				fastOrg.setC_ORG_NAME_ST(infoOrg.getC_ORG_NAME_ST());
				fastOrg.setC_ORG_CODE_P(infoOrg.getC_ORG_CODE_P());
				fastOrg.setC_CORP_REP(infoOrg.getC_CORP_REP());
				fastOrg.setC_DC_CODE(infoOrg.getC_DC_CODE());
				fastOrg.setN_REG_CAP(infoOrg.getN_REG_CAP());
				fastOrg.setC_REG_ADDR(infoOrg.getC_REG_ADDR());
				fastOrg.setC_OFFIC_ADDR(infoOrg.getC_OFFIC_ADDR());
				fastOrg.setC_DV_ORG_TYPE(infoOrg.getC_DV_ORG_TYPE());
				fastOrg.setC_CORP_CODE(infoOrg.getC_CORP_CODE());
				fastOrg.setC_LINK_MAN(infoOrg.getC_LINK_MAN());
				fastOrg.setC_LINK_TEL(infoOrg.getC_LINK_TEL());
				fastOrg.setC_MO_TEL(infoOrg.getC_MO_TEL());
				fastOrg.setC_EMAIL(infoOrg.getC_EMAIL());
				fastOrg.setC_REG_POST(infoOrg.getC_REG_POST());
				fastOrg.setC_DESC(infoOrg.getC_DESC());
				fastOrg.setC_OFFIC_POST(infoOrg.getC_OFFIC_POST());
				fastOrg.setC_WWW_ADDR(infoOrg.getC_WWW_ADDR());
				fastOrg.setC_FAX_TEL(infoOrg.getC_FAX_TEL());
				fastOrg.setC_MKT_CODE(infoOrg.getC_MKT_CODE());
				fastOrg.setC_DV_MANAGER(infoOrg.getC_DV_MANAGER());
				fastOrg.setC_DV_TRUSTEE(infoOrg.getC_DV_TRUSTEE());
				fastOrg.setC_DV_TRUSTEE_SEC(infoOrg.getC_DV_TRUSTEE_SEC());
				fastOrg.setC_DV_WARRANTOR(infoOrg.getC_DV_WARRANTOR());
				fastOrg.setC_DV_INVEST_ADVISER(infoOrg.getC_DV_INVEST_ADVISER());
				fastOrg.setC_DV_TRUSTEE_XT(infoOrg.getC_DV_TRUSTEE_XT());
				fastOrg.setC_DV_SALES_CHANNELS(infoOrg.getC_DV_SALES_CHANNELS());
				fastOrg.setC_DV_CLEARING_MEMBER(infoOrg.getC_DV_CLEARING_MEMBER());
				fastOrg.setC_QUALIFICATION(infoOrg.getC_QUALIFICATION());
				fastOrg.setC_PLACE_SETTLEMENT(infoOrg.getC_PLACE_SETTLEMENT());
				fastOrg.setC_CLEAR_ACCOUNT(infoOrg.getC_CLEAR_ACCOUNT());
				fastOrg.setC_BROKER_ID(infoOrg.getC_BROKER_ID());
				fastOrg.setC_BROKER_NAME(infoOrg.getC_BROKER_NAME());
				fastOrg.setC_BROKER_ID_TYPE(infoOrg.getC_BROKER_ID_TYPE());
				fastOrg.setC_CLEARER_ID(infoOrg.getC_CLEARER_ID());
				fastOrg.setC_CLEARER_NAME(infoOrg.getC_CLEARER_NAME());
				fastOrg.setC_CLEARER_ID_TYPE(infoOrg.getC_CLEARER_ID_TYPE());
				fastOrg.setC_LOGO_NAME(infoOrg.getC_LOGO_NAME());
				fastOrg.setC_PAY_CODE(infoOrg.getC_PAY_CODE());
				fastOrg.setC_BANK_CODE(infoOrg.getC_BANK_CODE());
				fastOrg.setC_DV_TRD_CLIENT(infoOrg.getC_DV_TRD_CLIENT());
				fastOrg.setC_DV_CONSIGNER(infoOrg.getC_DV_CONSIGNER());
				fastOrg.setC_DV_BX_CLIENT(infoOrg.getC_DV_BX_CLIENT());
				fastOrg.setC_DV_DEPOSITARY(infoOrg.getC_DV_DEPOSITARY());
				fastOrg.setC_DV_ORG_ATTR(infoOrg.getC_DV_ORG_ATTR());
				fastOrg.setD_FOUND_TIME(infoOrg.getD_FOUND_TIME());
				fastOrg.setC_DV_ISSUER(infoOrg.getC_DV_ISSUER());
				fastOrg.setC_DV_WBFWJG(infoOrg.getC_DV_WBFWJG());
				fastOrg.setC_DV_TRUSTEE_MA(infoOrg.getC_DV_TRUSTEE_MA());
				fastOrg.setC_DV_MARKETING(infoOrg.getC_DV_MARKETING());
				fastOrg.setC_TG_ACCOUNT_CODE(infoOrg.getC_TG_ACCOUNT_CODE());
				fastOrg.setC_ELEC_RECONCILIATION(infoOrg.getC_ELEC_RECONCILIATION());
				fastOrg.setC_DV_SUM(infoOrg.getC_DV_SUM());
				fastOrg.setC_ORG_ENCODE(infoOrg.getC_ORG_ENCODE());
				fastOrgList.add(fastOrg);
			}
		}
		
		return fastOrgList;
	}
	
	/**
	 * @Desc  
	 * @author houjiaqi
	 * @date 2019年1月2日 上午10:14:48
	 * @param @return
	 * @param @throws ServiceException
	 */
	@Override
	public List<FastOrg> getDataList() throws ServiceException {
		List<BasePojo> basePojoList = YssServiceFactory.getInstance().createService(IOrgDataService.class).getDataList();
		//增加空判断
		if(basePojoList == null || basePojoList.size() == 0){
			return new ArrayList<FastOrg>();
		}
		
		List<Org> orgList = new ArrayList<Org>();
		for (BasePojo pojo : basePojoList) {
			orgList.add((Org)pojo);
		}
		return convert2FastOrg(orgList);
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年3月8日 上午10:41:03
	 * @param @param portCodes
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public String getOneManagerFrom(String portCodes) throws Exception {
		return orgDao.getOneManagerFrom(portCodes);
	}

	/**
	 * @Desc  STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @author houjiaqi
	 * @date 2019年3月5日 上午10:56:20
	 * @param @return
	 * @param @throws Exception
	 */
	@Override
	public List<Map<String, String>> getCacodes() throws Exception {
		return orgDao.getCacodes();
	}

}
