package com.yss.uco.elecreco.er.erunport.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.uco.elecreco.er.erunport.dao.ErUnPortDao;
import com.yss.uco.elecreco.er.erunport.dao.ErUnPortSqlBuilder;
import com.yss.uco.elecreco.er.erunport.pojo.ErUnPort;
import com.yss.uco.elecreco.er.erunport.service.IErUnPortService;

public class ErUnPortService extends ServiceBus<ErUnPortService> implements
		IErUnPortService {
	private ErUnPortDao serviceDao = null;
	
	public ErUnPortService() throws Exception {
		serviceDao = new ErUnPortDao(DbPoolFactory.getInstance().getPool(),
				new ErUnPortSqlBuilder());
		dao = serviceDao;
	}

	@Override
	public String insertListByPortCodes(List<String> portCodeList) {

		List<String> cidenList = new ArrayList<String>();

		String retInfo = "";
		ErUnPort pojo = null;
		try {
			logger.debug("================================= " + "\r\n"
					+ "  Start Insert Data... ");
			//BUG244963���Ӷ��˹������������ҳ��ѡ��ݵ��������˿��Ա����������������
			Set<String> codes = filterUnPortCodes(portCodeList);
			for (String portCode : codes) {
				pojo = new ErUnPort();
				pojo.setC_PORT_CODE(portCode);
				pojo.setOperator(ContextFactory.getContext()
						.getUserCode());
				pojo.setOperTime(DateUtil
						.getNow(MvcConstant._DATA_STD_DATE_FORMAT));

				String ciden = dao.insert(pojo);
				cidenList.add(ciden);
			}
			retInfo = ReturnInfoGenerator.getSaveOKStr(menuId, cidenList);

			logger.debug("  Complete " + "\r\n"
					+ "================================= ");
		} catch (Exception ex) {
			String errorMess = "";
			if(ex.getMessage().contains("ORA-00001")){
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(ex.getMessage(),dao,pojo);
			}else{
				errorMess = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeSaveErr, menuId);
			}
			
			throw new ErrorMessageException(ex, errorMess);
		}

		return retInfo;
	
	}
	
	@Override
	public String deletByPortCodes(List<String> portCodeList) {
		return serviceDao.deletByPortCodes(portCodeList);
	}

	@Override
	public Set<String> filterUnPortCodes(List<String> portCodeList) {
		return serviceDao.filterUnPortCodes(portCodeList);
	}
	
	@Override
	public boolean queryByCode(String portCode) {
		// TODO Auto-generated method stub
		return serviceDao.queryByCode(portCode);
		
	}
}
