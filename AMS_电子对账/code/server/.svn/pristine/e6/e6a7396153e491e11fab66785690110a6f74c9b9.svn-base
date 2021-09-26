package com.yss.uco.elecreco.er.reverse.map.kmmap.service.impl;
import com.yss.uco.elecreco.er.reverse.map.kmmap.dao.KmMapDao;
import com.yss.uco.elecreco.er.reverse.map.kmmap.dao.KmMapSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.kmmap.service.IKmMapService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

public class KmMapService extends ServiceBus<KmMapService> implements IKmMapService {

	private KmMapDao serviceDao = null;
	public KmMapService() throws Exception {
		serviceDao = new KmMapDao(DbPoolFactory.getInstance().getPool(),new KmMapSqlBuilder());
		dao = serviceDao;
	}
//	public QueryRes queryInnerKm(HashMap<String, Object> paraMap) {
//		QueryRes queryRes = new QueryRes();
//		String classString = "";
//		Class<?> clazz;
//		List<BasePojo> dataList = null;
//		try {
//			classString = String.valueOf(paraMap.get("dataClass"));
//			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);
//
//			
//			dataList = serviceDao.queryInnerKm(paraMap, clazz);
//			
//			fillResultObject(queryRes, dataList, 0, null, "reveInnerKmb");
//			
//		} catch (Exception ex) {
//			if(ex instanceof YssRuntimeException){
//				throw (ServiceException)ex;
//			}else{
//				logger.error(ex.getMessage());
//				throw new ServiceException(ex);
//			}
//		}
//		return queryRes;
//	}
	
//	public QueryRes queryOutKm(HashMap<String, Object> paraMap) {
//		QueryRes queryRes = new QueryRes();
//		String classString = "";
//		Class<?> clazz;
//		List<BasePojo> dataList = null;
//		try {
//			classString = String.valueOf(paraMap.get("dataClass"));
//			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);
//
//			
//			dataList = serviceDao.queryInnerKm(paraMap, clazz);
//			
//			fillResultObject(queryRes, dataList, 0, null, "reveOutKmb");
//		} catch (Exception ex) {
//			if(ex instanceof YssRuntimeException){
//				throw (ServiceException)ex;
//			}else{
//				logger.error(ex.getMessage());
//				throw new ServiceException(ex);
//			}
//		}
//		return queryRes;
//	}

	
}