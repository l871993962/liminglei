package com.yss.uco.elecreco.er.reverse.map.kmrela.dao;
import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.uco.elecreco.er.erkmb.pojo.ErKmb;
import com.yss.uco.elecreco.er.reverse.map.kmmap.dao.KmMapDao;
import com.yss.uco.elecreco.er.reverse.map.kmmap.dao.KmMapSqlBuilder;
import com.yss.uco.elecreco.er.reverse.map.kmmap.pojo.KmMap;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRela;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaRecord;
import com.yss.uco.elecreco.er.reverse.map.kmrela.pojo.KmRelaSingleReocrd;
import com.yss.uco.elecreco.er.reverse.out.erkmb.dao.ErKmbOutSqlBuilder;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.context.LoggingInfo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.logger.HandleSaveDataThread;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.ReflectionUtil;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.util.DateUtil;
import com.yss.framework.util.PojoUtils;

public class KmRelaRecordDao extends GeneralDao  {

	private KmRelaRecordSqlBuilder sqlBuilder = null;
	public KmRelaRecordDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (KmRelaRecordSqlBuilder) sqlBuilder;
	}
	
	public List<BasePojo> queryInnerKm(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		//ResultSetTools rsTools = null;
		try {
			//rsTools = new ResultSetTools(dbNameResolver, new ErKmbSqlBuilder());
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = this.sqlBuilder.getInnerKMBSql(paraMap);

			pstmt = conn.prepareStatement(sql);
			int index = 1;
//			String geneDate = DateUtil.dateToString(new java.util.Date(), "yyyy-MM-dd");
			String geneDate = (String) paraMap.get("D_BUS_DATE");;
			String portCode = (String) paraMap.get("C_PORT_CODE");
			String kmCls = (String) paraMap.get("C_DV_KM_CLS");
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, geneDate);
			pstmt.setString(index++, geneDate);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, geneDate);
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, geneDate);
			pstmt.setString(index++, kmCls);
			if(Boolean.parseBoolean((String) paraMap.get("IS_FLITER")))
			{
				pstmt.setString(index++, (String) paraMap.get("C_DV_MAP_SCOPE"));
			}
			rs = pstmt.executeQuery();
			//BasePojo pojo = (BasePojo) clazz.newInstance();
			//PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			ErKmb kmb = null;
			while (rs.next()) {
				//BasePojo t = setResultSet(rsTools,rs, clazz, props); // 提供可以重写的方法byleeyu20130420 
				kmb = getErKMb(rs);
//				getConvertKey(props,kmb);
				pojoList.add(kmb);
			}
			
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return pojoList;
	}
	
	private ErKmb getErKMb(ResultSet rs) throws SQLException
	{
		ErKmb kmb = new ErKmb();
		kmb.setC_KM_CODE(rs.getString("C_KM_CODE"));
		kmb.setC_KM_CODE_P(rs.getString("C_KM_CODE_P"));
		kmb.setC_KM_NAME(rs.getString("C_KM_NAME"));
		return kmb;
	}
	
	public List<BasePojo> queryOutKm(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, new ErKmbOutSqlBuilder());
			conn = this.loadNewConnection();
			// conn.setAutoCommit(false);
			sql = sqlBuilder.getOutKMBSql(paraMap);

			pstmt = conn.prepareStatement(sql);
			int index = 1;
			//String geneDate = DateUtil.dateToString(new java.util.Date(), "yyyy-MM-dd");
			String portCode = (String) paraMap.get("C_PORT_CODE");
			String kmCls = (String) paraMap.get("C_DV_KM_CLS");
			pstmt.setString(index++, portCode);
			pstmt.setString(index++, kmCls);
			if(Boolean.parseBoolean((String) paraMap.get("IS_FLITER")))
			{
				pstmt.setString(index++, (String) paraMap.get("C_DV_MAP_SCOPE"));
			}
			rs = pstmt.executeQuery();

			
			BasePojo pojo = (BasePojo) clazz.newInstance();
			PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
			while (rs.next()) {
				BasePojo t = setResultSet(rsTools,rs, clazz, props); // 提供可以重写的方法byleeyu20130420 
				getConvertKey(props,t);
				pojoList.add(t);
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;
	}
	
	
	public KmRelaRecord transToKmRelaRecord(KmRelaSingleReocrd single) throws Exception
	{
		KmRelaRecord krr = new KmRelaRecord();
		KmMap km = new KmMap();
		KmRela kr = new KmRela();
		PropertyDescriptor prop = null;
		String name = "";
		Object resValue = null;
		PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(single);
		PropertyDescriptor[] krRrops = PojoUtils.getPropertyDescriptors(kr);
		for (int i = 0; i < props.length; i++) {
			prop = props[i];
			name = prop.getName();
			
			if ("startUseDate".equalsIgnoreCase(name)
					|| "endUseDate".equalsIgnoreCase(name)) {
				continue;
			}
			if(name.endsWith("_KMMAP"))//内部科目代码
			{
				resValue =  ReflectionUtil.getFieldValue(single, name);
				if (resValue != null) {
					//ReflectionUtil.setFieldValue(innerKm, name.substring(0, name.lastIndexOf("_INNER")), resValue);
					ReflectionUtil.setProperty(km, name.substring(0, name.lastIndexOf("_KMMAP")), resValue);
				}
			}else//KmRela
			{
				resValue =  ReflectionUtil.getFieldValue(single, name);
				if (resValue != null) {
					for(PropertyDescriptor pd : krRrops)
					{
						if(pd.getName().equalsIgnoreCase(name))
						{
						
							ReflectionUtil.setProperty(kr, name, resValue);
							break;
						}
					}
					
				}
			}
			
	}
		List<KmMap> ins = new ArrayList<KmMap>();
		List<KmMap> outs = new ArrayList<KmMap>();
		if("REVE_KMFW_OUT".equalsIgnoreCase(km.getC_DV_KM_SCOPE()))
		{
			outs.add(km);
		}else if("REVE_KMFW_INNER".equalsIgnoreCase(km.getC_DV_KM_SCOPE()))
		{
			ins.add(km);
		}
		krr.setKmRela(kr);
		krr.setList_KM_INNER(ins);
		krr.setList_KM_OUT(outs);
		krr.setKmRelaRecordWithKmRela(kr);
		return krr;
		
	}

	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz)
	{
		List<BasePojo> result = new ArrayList<BasePojo>();
		List<BasePojo> list = new KmRelaSingleRecordDao(pool, new KmRelaSingleRecordSqlBuilder()).queryByCondition(paraMap, KmRelaSingleReocrd.class);
		//按插入顺序排序
		Map<String,BasePojo> map = new HashMap<String,BasePojo>();
		KmRelaSingleReocrd single = null;
		KmRelaRecord krr = null;
		KmRelaRecord temp = null;
		for(BasePojo pojo : list)
		{
			single = (KmRelaSingleReocrd) pojo;
			if(map.containsKey(single.getId()))
			{
				krr = (KmRelaRecord) map.get(single.getId());
				try {
					temp = transToKmRelaRecord(single);
					krr.getList_KM_INNER().addAll(temp.getList_KM_INNER());
					krr.getList_KM_OUT().addAll(temp.getList_KM_OUT());
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}else
			{
				try {
					map.put(single.getId(), transToKmRelaRecord(single));
					result.add(map.get(single.getId()));
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}
		}
		return result;
		
	}
	
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> result = new ArrayList<BasePojo>();
		List<BasePojo> list = new KmRelaSingleRecordDao(pool, new KmRelaSingleRecordSqlBuilder()).queryByConditionPage(paraMap, page, KmRelaSingleReocrd.class);
		//按插入顺序排序
		Map<String,BasePojo> map = new HashMap<String,BasePojo>();
		KmRelaSingleReocrd single = null;
		KmRelaRecord krr = null;
		KmRelaRecord temp = null;
		for(BasePojo pojo : list)
		{
			single = (KmRelaSingleReocrd) pojo;
			if(map.containsKey(single.getId()))
			{
				krr = (KmRelaRecord) map.get(single.getId());
				try {
					temp = transToKmRelaRecord(single);
					krr.getList_KM_INNER().addAll(temp.getList_KM_INNER());
					krr.getList_KM_OUT().addAll(temp.getList_KM_OUT());
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}else
			{
				try {
					map.put(single.getId(), transToKmRelaRecord(single));
					result.add(map.get(single.getId()));
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}
		}
		return result;
	}
	
	@Override
	public int queryByConditionCount(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return new KmRelaSingleRecordDao(pool, new KmRelaSingleRecordSqlBuilder()).queryByConditionCount(paraMap);
	}

	
	
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list)
			throws DataAccessException {
		
		List<String> cidenList = new ArrayList<String>();
		Connection conn = null;
		try {
			KmRelaRecord krr = null;
			if (list == null) {
				throw new InvalidParametersException("list数据不能为空");
			}
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			KmMapDao kmMapDao = new KmMapDao(pool, new KmMapSqlBuilder());
			KmRelaDao kmRelaDao = new KmRelaDao(pool, new KmRelaSqlBuilder());
			for (T pojo : list) {
				if (pojo instanceof ParamPojo) {
					((ParamPojo) pojo).setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
				
				krr = (KmRelaRecord) pojo;
				String ciden = kmRelaDao.insert(krr.getKmRelaWithKmRelaRecord(), conn);
				cidenList.add(ciden);
				//kmMapDao.insert(krr.getLIST_KM_INNER(), conn);
				//kmMapDao.insert(krr.getLIST_KM_OUT(), conn);
				for(KmMap km : krr.getList_KM_INNER())
				{
					km.setC_IDEN_RELA(ciden);
					kmMapDao.insert(km, conn);
				}
				for(KmMap km : krr.getList_KM_OUT())
				{
					km.setC_IDEN_RELA(ciden);
					kmMapDao.insert(km, conn);
				}
			}			
			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (DataAccessException)ex;
			}else{
				logger.error("保存失败：" + ex.getMessage());
				throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
			}
			
		} finally {
			this.releaseConnection(conn);
		}
		return cidenList;
	}



	
	private <T extends BaseBean> List<BasePojo> transBasePojo(List<T> list)
	{
		List<BasePojo> pojos = new ArrayList<BasePojo>();
		for(T pojo : list)
		{
			pojos.add((BasePojo) pojo);
		}
		return pojos;
	}

	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		//this.delete(pojoList);
		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			//deleteById(pojoList, conn);
			
			KmRelaRecord krr = null;
			KmRelaDao krDao = new KmRelaDao(pool, new KmRelaSqlBuilder());
			KmMapDao kmDao = new KmMapDao(pool, new KmMapSqlBuilder());
			for(T pojo : pojoList)
			{
				krr = (KmRelaRecord) pojo;
				krDao.deleteById(krr.getKmRela(), conn);
				kmDao.deleteById(krr.getList_KM_INNER(), conn);
				kmDao.deleteById(krr.getList_KM_OUT(), conn);
				
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}


	@Override
	public void saveDelRecord(List<BasePojo> pojoList) {
		try {
			if (pojoList == null) {
				throw new InvalidParametersException("list数据不能为空");
			}
			
			LoggingInfo logginInfo =  ContextFactory.getContext().getLogInfo();
			HandleSaveDataThread handleThread = new HandleSaveDataThread(this, pojoList, logginInfo);
			Thread thread = new Thread(handleThread);
			thread.start();
			
		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		}
	}


	@Override
	public <T extends BaseBean> void saveDelRecords(List<T> beanList,
			String userCode) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		KmRelaDao krDao = new KmRelaDao(pool, new KmRelaSqlBuilder());
		KmMapDao kmDao = new KmMapDao(pool, new KmMapSqlBuilder());
		KmRelaRecord krr = null;
		try{
			for(T baseBean : beanList){
				krr = (KmRelaRecord) baseBean;
				kmDao.saveDelRecord(transBasePojo(krr.getList_KM_INNER()));
				kmDao.saveDelRecord(transBasePojo(krr.getList_KM_OUT()));
				pojoList.add(krr.getKmRela());
			}
			krDao.saveDelRecord(pojoList);
		}catch (Exception ex) {
			throw new DataAccessException("插入失败：" + ex.getMessage(), ex);
		} finally {
		}
	}
	
	public List<KmRelaRecord> getCompareKmMap(String portCode,String tgh) {
		List<KmRelaRecord> pojoList = new ArrayList<KmRelaRecord>();
		List<KmRelaSingleReocrd> list = new KmRelaSingleRecordDao(pool, new KmRelaSingleRecordSqlBuilder()).getCompareKmMap(portCode, tgh);
		Map<String,KmRelaRecord> map = new HashMap<String,KmRelaRecord>();
		KmRelaRecord krr = null;
		KmRelaRecord temp = null;
		for(KmRelaSingleReocrd single : list)
		{
			if(map.containsKey(single.getId()))
			{
				krr = (KmRelaRecord) map.get(single.getId());
				try {
					temp = transToKmRelaRecord(single);
					krr.getList_KM_INNER().addAll(temp.getList_KM_INNER());
					krr.getList_KM_OUT().addAll(temp.getList_KM_OUT());
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}else
			{
				try {
					map.put(single.getId(), transToKmRelaRecord(single));
					pojoList.add(map.get(single.getId()));
				} catch (Exception e) {
					//e.printStackTrace();
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}
		}
		return pojoList;
	}

	public List<BasePojo> queryIsMappingKm(HashMap<String, Object> paraMap) {
		// TODO Auto-generated method stub
		return this.queryByCondition(paraMap, KmRelaRecord.class);
	}

	/**
	 * 获取组合和公共级别的映射关系
	 * @param portCode
	 * @return
	 */
	public List<KmRelaRecord> getPortAndCommKmMap(String portCode) {
		List<KmRelaRecord> pojoList = new ArrayList<KmRelaRecord>();
		List<KmRelaSingleReocrd> list = new KmRelaSingleRecordDao(pool, new KmRelaSingleRecordSqlBuilder()).getPortAndCommKmMap(portCode);
		Map<String,KmRelaRecord> map = new HashMap<String,KmRelaRecord>();
		KmRelaRecord krr = null;
		KmRelaRecord temp = null;
		for(KmRelaSingleReocrd single : list)
		{
			if(map.containsKey(single.getId()))
			{
				krr = (KmRelaRecord) map.get(single.getId());
				try {
					temp = transToKmRelaRecord(single);
					krr.getList_KM_INNER().addAll(temp.getList_KM_INNER());
					krr.getList_KM_OUT().addAll(temp.getList_KM_OUT());
				} catch (Exception e) {
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}else
			{
				try {
					map.put(single.getId(), transToKmRelaRecord(single));
					pojoList.add(map.get(single.getId()));
				} catch (Exception e) {
					logger.error("从KmRelaSingleReocrd转换到KmRelaRecord出错：", e);
				}
			}
		}
		return pojoList;
	}
	

	
	
	
	
	
}