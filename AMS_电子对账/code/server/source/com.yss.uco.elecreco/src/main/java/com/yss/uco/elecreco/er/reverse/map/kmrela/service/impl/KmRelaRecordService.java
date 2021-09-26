package com.yss.uco.elecreco.er.reverse.map.kmrela.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.map.kmrela.dao.KmRelaRecordDao;
import com.yss.uco.elecreco.er.reverse.map.kmrela.dao.KmRelaRecordSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRela;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaRecordService;
import com.yss.uco.elecreco.er.reverse.map.kmrela.service.IKmRelaService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.db.DbPoolFactory;
import com.yss.framework.util.DateUtil;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

public class KmRelaRecordService extends ServiceBus<KmRelaRecordService> implements IKmRelaRecordService {

	private KmRelaRecordDao serviceDao = null;
	public KmRelaRecordService() throws Exception {
		serviceDao = new KmRelaRecordDao(DbPoolFactory.getInstance().getPool(),new KmRelaRecordSqlBuilder());
		dao = serviceDao;
	}
	
	public QueryRes queryInnerKm(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			
			dataList = serviceDao.queryInnerKm(paraMap, clazz);
			
			fillResultObject(queryRes, dataList, 0, null, "reveInnerKmb");
			
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}
	
	public QueryRes queryOutKm(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			
			dataList = serviceDao.queryOutKm(paraMap, clazz);
			
			fillResultObject(queryRes, dataList, 0, null, "reveOutKmb");
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.error(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}
	
	@Override
	public String recoverDelRecord(List<BasePojo> pojoList) {
		
		//return super.recoverDelRecord(pojoList);
		
		String retInfo = "";
		List<String> ids = new ArrayList<String>();
		try {
			/* 添加自定义日志功能 记录执行时间 */
			// StringBuffer buf = new StringBuffer();
			// buf.append("serviceName:").append(this.getClass().getName())
			// .append("  ");
			// buf.append("methodName:").append("recoverDelRecord insert").append(
			// "  ");
			// buf.append("menuId:").append(getMenuId());
			//
			// WriteLog.newInstance().startLog();
			
			for(BasePojo p : pojoList){
				ids.add(p.getId());
			}
			
			dao.insert(pojoList);
			// WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
			// StringUtil.clearStringBuffer(buf);
			//
			// buf.append("serviceName:").append(this.getClass().getName())
			// .append("  ");
			// buf.append("methodName:").append("recoverDelRecord clearDelRecord")
			// .append("  ");
			// buf.append("menuId:").append(getMenuId());
			//
			// WriteLog.newInstance().startLog();
			//删除之前恢复ID，防止恢复的时候ID已经被改变
			int i = 0;
			for(BasePojo p : pojoList){
				p.setId(ids.get(i++));
			}
			
			dao.clearDelRecord(pojoList);
			// WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
			// StringUtil.clearStringBuffer(buf);
			// buf = null;
			retInfo = ReturnInfoGenerator.getOperOKStr(
					MvcConstant._CodeRecoverOK, "restore", menuId);
		} catch (Exception e) {
			if(e instanceof YssRuntimeException){
				throw (ServiceException)e;
			}else{
				String errorMessage = ReturnInfoGenerator.getOperErrMsg(
						MvcConstant._CodeRecoverErr, menuId);
				throw new ErrorMessageException(e,errorMessage);
			}
		}

		return retInfo;
		
	}

	@Override
	public String auditById(BasePojo pojo) {
		String retInfo = "";
		IKmRelaService service =null;
		try {
			service = new KmRelaService();
			service.setMenuId(menuId);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeCheckErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}
		return service.auditById(((KmRelaRecord) pojo).getKmRela());
	}

	@Override
	public String auditById(List<BasePojo> pojoList) {
		String retInfo = "";
		List<BasePojo> list = new ArrayList<BasePojo>();
		KmRelaRecord krr = null;
		
		IKmRelaService service =null;
		//return super.auditById(pojoList);
		try {
			service = new KmRelaService();
			service.setMenuId(menuId);
			for(BasePojo pojo : pojoList )
			{
				krr = (KmRelaRecord) pojo;
				KmRela rela = krr.getKmRela();
				rela.setAuditDate(krr.getAuditDate());
				rela.setAuditState(krr.getAuditState());
				rela.setOperator(krr.getOperator());
				list.add(rela);
				
			}
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeCheckErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}
		return service.auditById(list);
	}

	@Override
	public String unAuditById(BasePojo pojo) {
		// TODO Auto-generated method stub
		//return super.unAuditById(pojo);
		String retInfo = "";
		IKmRelaService service =null;
		try {
			service = new KmRelaService();
			service.setMenuId(menuId);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeCheckErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}
		return service.unAuditById(((KmRelaRecord) pojo).getKmRela());
	}

	@Override
	public String unAuditById(List<BasePojo> pojoList) {
		// TODO Auto-generated method stub
		//return super.unAuditById(pojoList);
		String retInfo = "";
		List<BasePojo> list = new ArrayList<BasePojo>();
		KmRelaRecord krr = null;
		
		IKmRelaService service =null;
		//return super.auditById(pojoList);
		try {
			service = new KmRelaService();
			service.setMenuId(menuId);
			for(BasePojo pojo : pojoList )
			{
				krr = (KmRelaRecord) pojo;
				KmRela rela = krr.getKmRela();
				rela.setAuditDate(krr.getAuditDate());
				rela.setAuditState(krr.getAuditState());
				rela.setOperator(krr.getOperator());
				list.add(rela);
				
			}
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeCheckErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}
		return service.unAuditById(list);
	}

	@Override
	public String deleteById(List<BasePojo> pojoList) {
		String retInfo = "";

		try {

			/* 添加自定义日志功能 记录执行时间 */
			// StringBuffer buf = new StringBuffer();
			// buf.append("serviceName:").append(this.getClass().getName())
			// .append("  ");
			// buf.append("methodName:").append("deleteById").append("  ");
			// buf.append("menuId:").append(getMenuId());
			//
			// WriteLog.newInstance().startLog();
			// 循环删除数据
			// for (BasePojo pojo : pojoList) {
			// dao.deleteById(pojo);
			// }

			// // 直接调用DAO层的批量删除方法byleeyu20130703
			dao.deleteById(pojoList);
			// WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
			// StringUtil.clearStringBuffer(buf);
			//
			// buf.append("serviceName:").append(this.getClass().getName())
			// .append("  ");
			// buf.append("methodName:").append("deleteById saveDelRecord")
			// .append("  ");
			// buf.append("menuId:").append(getMenuId());
			//
			// WriteLog.newInstance().startLog();
			// 保存回收站数据
			// 在调用的地方与saveDelRecore方法中添加条件防止子类重写时没有判断byleeyu20130719
			if (safeData != null && safeData.getN_RECYCLE() > 0) {
				retInfo = saveDelRecord(pojoList);
			}
			// WriteLog.newInstance(this.getClass().getSimpleName()).write(buf);
			// StringUtil.clearStringBuffer(buf);
			// buf = null;
			retInfo = getDelInfo(retInfo);
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				retInfo = ReturnInfoGenerator.getOperErrMsg(MvcConstant._CodeDelErr, menuId);
				logger.error(ex.getMessage());
				throw new ErrorMessageException(ex,retInfo);
			}
		}

		return retInfo;
	}

	@Override
	public String deleteById(BasePojo pojo) {
		// TODO Auto-generated method stub
		return super.deleteById(pojo);
	}

	@Override
	public String saveDelRecord(List<BasePojo> pojoList) {
		String retInfo = "";

		try {
			if (safeData != null && safeData.getN_RECYCLE() > 0) {
				for (BasePojo pojo : pojoList) {
					if (pojo instanceof KmRelaRecord) {
						((KmRelaRecord) pojo).getKmRela().setModifyDate(DateUtil
								.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
					}
				}
				/* 添加自定义日志功能 记录执行时间 */
				// StringBuffer buf = new StringBuffer();
				// buf.append("serviceName:").append(this.getClass().getName())
				// .append("  ");
				// buf.append("methodName:").append("saveDelRecord").append("  ");
				// buf.append("menuId:").append(getMenuId());
				//
				// WriteLog.newInstance().startLog();
				dao.saveDelRecord(pojoList);
				// WriteLog.newInstance(this.getClass().getSimpleName())
				// .write(buf);
				// StringUtil.clearStringBuffer(buf);
				// buf = null;
				retInfo = MvcConstant._Success;
			}

		} catch (Exception e) {
			retInfo = MvcConstant._Fault;
			// //edit by weijj 20130724 回收站保存不抛异常 在前台提示
			// throw new ServiceException(e, menuId, retInfo);
		}

		return retInfo;
	}

	@Override
	public String insert(List<BasePojo> pojoList) {
		return super.insert(pojoList);
	}

	@Override
	public List<KmRelaRecord> getCompareKmMap(String portCode, String tgh) {
		return this.serviceDao.getCompareKmMap(portCode, tgh);
	}

	@Override
	public List<KmRelaRecord> queryIsMappingKm(HashMap<String, Object> paraMap) {
		List<KmRelaRecord> kms = new ArrayList<KmRelaRecord>();
		List<BasePojo> list = this.serviceDao.queryIsMappingKm(paraMap);
		if(list != null)
		{
			for(BasePojo pojo : list)
			{
				kms.add((KmRelaRecord) pojo);
			}
		}
		return kms;
	}

	@Override
	public List<KmRelaRecord> getPortAndCommKmMap(String portCode) {
		return this.serviceDao.getPortAndCommKmMap(portCode);
	}




	
	

}