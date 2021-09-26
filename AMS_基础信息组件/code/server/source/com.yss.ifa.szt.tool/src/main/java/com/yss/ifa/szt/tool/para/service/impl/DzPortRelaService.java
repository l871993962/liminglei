package com.yss.ifa.szt.tool.para.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.port.service.IPortService;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRela;
import com.yss.ams.product.information.support.modules.ab.portrela.pojo.PortRelaOrgan;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaOrganServcie;
import com.yss.ams.product.information.support.modules.ab.portrela.service.IPortRelaService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.PojoUtils;
import com.yss.ifa.szt.tool.para.dao.DzPortRelaDao;
import com.yss.ifa.szt.tool.para.dao.DzPortRelaSqlBuilder;
import com.yss.ifa.szt.tool.para.service.IDzPortRelaService;
import com.yss.ifa.szt.tool.pojo.DzRelaOrgan;


public class DzPortRelaService extends ServiceBus<DzPortRelaService> implements
		IDzPortRelaService {

	private DzPortRelaDao serviceDao = null;
	
	private IPortRelaOrganServcie portRelaOrganServcie = null;
	
	private IPortRelaService portRelaService = null;
	
	private static List<String> portCodeList = null;

	public DzPortRelaService() {
		serviceDao = new DzPortRelaDao(DbPoolFactory.getInstance().getPool(),
				new DzPortRelaSqlBuilder());
		dao = serviceDao;
	}
	
	@Override
	public QueryRes queryByCondition(HashMap<String, Object> paraMap, PageInation page) {
		try {
			// 获取当前登录用户有权限的组合代码
			IPortService portService = YssServiceFactory.getInstance().createService(IPortService.class);
			portCodeList = portService.getDataRightListForReportCenter();
			paraMap.put("ARRAY_C_PORT_CODE", StringUtil.join(portCodeList, ","));
		} catch (Exception e) {
		}
		
		return super.queryByCondition(paraMap, page);
	}
	
	@Override
	public String queryDataTotal(HashMap<String, Object> paraMap) {
		try {
			// 获取当前登录用户有权限的组合代码
			IPortService portService = YssServiceFactory.getInstance().createService(IPortService.class);
			if (portCodeList == null) {
				portCodeList = portService.getDataRightListForReportCenter();
			}
			paraMap.put("ARRAY_C_PORT_CODE", StringUtil.join(portCodeList, ","));
		} catch (Exception e) {
		}
		
		return super.queryDataTotal(paraMap);
	}
	
	@Override
	public String delByYwId(List<BasePojo> pojoList) {
		
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.delByYwId(transToPortRelas(pojoList));
	}

	@Override
	public String insert(List<BasePojo> pojoList) {
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.insert(transToPortRelas(pojoList));
	}

	@Override
	public String deleteById(List<BasePojo> pojoList) {
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.deleteById(transToPortRelas(pojoList));
	}

	@Override
	public String auditById(List<BasePojo> pojoList) {
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.auditById(transToPortRelas(pojoList));
	}

	@Override
	public String unAuditById(List<BasePojo> pojoList) {
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.unAuditById(transToPortRelas(pojoList));
	}

	/**
	 * ����ҵ��������ɾ���� 
	 * @param pojoList
	 * @return
	 */
	public String delInsert(List<BasePojo> pojoList)
	{
		if(portRelaService == null)
		{
			portRelaService = YssServiceFactory.getInstance().createService(IPortRelaService.class);
		}
		return portRelaService.delInsert(transToPortRelas(pojoList));
	}

	@Override
	public List<String> queryPortCodesRelaOrgan(HashMap<String, Object> paraMap) {
		List<String> list = new ArrayList<String>();
		if(portRelaOrganServcie == null)
		{
			portRelaOrganServcie = YssServiceFactory.getInstance().createService(IPortRelaOrganServcie.class);
		}
		QueryRes res = null;
		try {
			PageInation page = new PageInation();
            page.setCurrPage(1);
            page.setPageSize(99999);
            page.setUsePage(true);
			res = portRelaOrganServcie.queryPortRelaOrganPage(paraMap,page);
			if(res != null && res.getDataList() != null)
			{
				for(BasePojo pojo : res.getDataList())
				{
					PortRelaOrgan rela = (PortRelaOrgan) pojo;
					list.add(rela.getC_PORT_CODE());
				}
			}
		} catch (Exception e) {
			throw new ErrorMessageException(e,"��ѯ�����ṹ����");
		}
		return list;
	}
	
	private List<BasePojo> transToPortRelas(List<BasePojo> pojoList)
	{
		List<BasePojo> list = new ArrayList<BasePojo>();
		if(pojoList != null)
		{
			for (BasePojo basePojo : pojoList) {
				DzRelaOrgan dzRela = (DzRelaOrgan) basePojo;
				PortRela rela = new PortRela();
				try {
					PojoUtils.Bean2Bean(rela, dzRela);
					list.add(rela);
				} catch (Exception e) {
					this.logger.error("ת���������", e);
					throw new ErrorMessageException(e,"ת���������");
				}
			}
		}
		return list;
	}
	
}
