package com.yss.ams.base.information.modules.sys.feeRelation.service.impl;

import java.util.HashMap;
import java.util.List;

import com.yss.ams.base.information.activator.InformationActivator;
import com.yss.ams.base.information.modules.sys.feeRelation.dao.FeeRelationDao;
import com.yss.ams.base.information.modules.sys.feeRelation.dao.FeeRelationSqlBuilder;
import com.yss.ams.base.information.support.sys.feeRelation.service.IFeeRelationService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.context.Context;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.mvc.pojo.PojoLoader;
import com.yss.mvc.pojo.sysinit.HeadKey;

/**
 * @classDesc 费用关联service实现
 * @version 1.0 2012-9-22
 * @author yh
 */
/**
 * @author yuankai 公共信息拆分 2017.5.31
 *
 */
public class FeeRelationService extends ServiceBus<FeeRelationService> implements IFeeRelationService{
	
	
	private FeeRelationDao feeRelationDao = null;
	
	public FeeRelationService(){
		feeRelationDao = new FeeRelationDao(YssDbPoolFactory.getInstance().getDbPool(InformationActivator.class), new FeeRelationSqlBuilder());
		super.dao = feeRelationDao;
	}
	
	/**
	 * 查询关联信息，用于查询资产交易界面费用关联数据
	 * @param paraMap
	 * @return
	 */
	public QueryRes queryRealDataByCondition(HashMap<String, Object> paraMap) {
		QueryRes queryRes = new QueryRes();
		//获取列头信息
		Context context = YssContextFactory.getInstance().getAppContext(InformationActivator.class);
		List<HeadKey> headKeyList = context.getListHeadMap("feeRela").getHeadKeyList();
		//获取数据信息
		List<BasePojo> dataList = feeRelationDao.queryRealDateByCondition(paraMap);
		
		
		queryRes.setHeadKeyList(headKeyList);
		queryRes.setDataList(dataList);
		try {
			setShowConvertAssemble(queryRes);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("查询关联信息时失败!", e);
		}
		queryRes.setMenuId("feeRela");
		return queryRes;
	}
	
	/**
	 * 条件查询
	 * 
	 * @param paraMap 参数集合
	 * 
	 * @return 查询结果对象
	 */
	public List<BasePojo> queryRealDateByCondition(HashMap<String, Object> paraMap){
		return feeRelationDao.queryRealDateByCondition(paraMap);
	}
	
	/**
	 * 条件查询，用于查询费用关联界面
	 * 
	 * @param paraMap 参数集合
	 * 
	 * @return 查询结果对象
	 */
	@Override
	public QueryRes queryByConditionFee(HashMap<String, Object> paraMap){
		QueryRes queryRes = new QueryRes();
		String classString = "";
		Class<?> clazz;
		int recCount = 0;
		List<BasePojo> dataList = null;
		try {
			classString = String.valueOf(paraMap.get("dataClass"));
			clazz = PojoLoader.getPojoClassById(classString,this.bundleContext);

			dataList = query(paraMap, clazz);
			fillResultObject(queryRes, dataList, recCount, null, "feeRelaDict");
		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (ServiceException)ex;
			}else{
				logger.log(ex.getMessage());
				throw new ServiceException(ex);
			}
		}
		return queryRes;
	}

}
