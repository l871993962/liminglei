package com.yss.ams.sec.information.modules.mp.secTransferPara.service.impl;

import java.util.List;

import com.yss.ams.sec.information.activator.SecInfoActivator;
import com.yss.ams.sec.information.modules.mp.secTransferPara.dao.SecTransferParaDao;
import com.yss.ams.sec.information.modules.mp.secTransferPara.dao.SecTransferParaSqlBuilder;
import com.yss.ams.sec.information.support.modules.mp.secTransferPara.pojo.SecTransferPara;
import com.yss.ams.sec.information.support.modules.mp.secTransferPara.service.ISecTransferParaService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.returninfo.ReturnInfoGenerator;
import com.yss.mvc.service.ServiceAssistance;



/**
 * #42948 资讯信息管理组件化拆分
 * @author chenbo
 *2017-07-06
 */
public class SecTransferParaService extends ServiceBus<SecTransferParaService>
		implements ISecTransferParaService {

	private SecTransferParaDao serviceDao = null;

	public SecTransferParaService() throws Exception {
		serviceDao = new SecTransferParaDao(YssDbPoolFactory.getInstance()
				.getDbPool(SecInfoActivator.class), new SecTransferParaSqlBuilder());
		dao = serviceDao;
	}

	@Override
	protected void fillResultObject(QueryRes queryRes, List<BasePojo> dataList,
			int countAll, PageInation page, String menuId) throws Exception {
		String menuIdd = "sv_secTransfer_para";
		for(BasePojo basePojo : dataList){
			SecTransferPara secTransferPara = null;
			secTransferPara = (com.yss.ams.sec.information.support.modules.mp.secTransferPara.pojo.SecTransferPara) basePojo;
			 if("SBLSHGZ_SEC|SBLSHGZ_SEAT".equalsIgnoreCase(secTransferPara.getC_ITEM_VALUE())){
				 secTransferPara.setC_ITEM_VALUE("交易证券|交易渠道");
			 }else if("SBLSHGZ_SEAT|SBLSHGZ_SEC".equalsIgnoreCase(secTransferPara.getC_ITEM_VALUE())){
				 secTransferPara.setC_ITEM_VALUE("交易渠道|交易证券");
			 }
			 
			 basePojo = secTransferPara;
		}

		queryRes.setDataList(dataList);
		queryRes.setHeadKeyList(ServiceAssistance.getListHead(menuIdd,
				SecInfoActivator.class));

		if (page == null) {
			page = new PageInation();
		} else {
			page.setTotalNum(countAll);
		}
		queryRes.setPage(page);

		setShowConvertAssemble(queryRes);
		queryRes.setOperRes(MvcConstant._Success);
		queryRes.setMenuId(menuIdd);
	}

	/**
	 * 修改功能选项参数
	 * 
	 * @param pojoList
	 * @return
	 */
	public String updateConds(List<BasePojo> pojoList) {
		String retInfo = "";
		BasePojo currPojo = null;
		try {
			for (BasePojo pojo : pojoList) {
				SecTransferPara secPara = (SecTransferPara) pojo;
				currPojo = pojo;
				serviceDao.updateById(secPara);
			}
			retInfo = ReturnInfoGenerator.getUpdateOKStr(menuId);
		} catch (Exception ex) {
			String errorMess = "";
			if (ex.getMessage().contains("ORA-00001")) {
				errorMess = ReturnInfoGenerator.getChkUniqueErrStrs(
						ex.getMessage(), serviceDao, currPojo);
			} else {
				errorMess = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeSaveErr, menuId);
			}
			throw new ErrorMessageException(ex, errorMess);
		}
		return retInfo;
	}
}
