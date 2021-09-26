package com.yss.uco.elecreco.er.ergzb.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.DbPoolFactory;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaDao;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaSqlBuilder;
import com.yss.uco.elecreco.support.bean.ErGzb;

public class ErGzbDao extends GeneralDao {

	private ElecRelaDao elecRelaDao = null;
	
	public ErGzbDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		elecRelaDao = new ElecRelaDao(DbPoolFactory.getInstance().getPool(), new ElecRelaSqlBuilder());
	}

	/**
	 * 重写基类查询方法
	 * 背景：【电子对账管理】模块：
	 * 现进行后台的科目代码转换，科目代码列=指标代码（‘指标名称_分级名称’）
	 * wlx 2016-12-2 BUG #146679::嘉实基金电子对账估值指标显示前台修改
	 */
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = super.queryByCondition(paraMap, clazz);
		HashMap<String, String> elecRelaMap = queryElecRelaMap(paraMap);
		for (BasePojo basePojo : pojoList){
			ErGzb erGzb = (ErGzb) basePojo;
			//20180929 BUG221290【大成基金】估值表指标项电子对账反馈时无科目名称时，本方科目名称要根据对账指标关联进行匹配展示
			String kmCode = erGzb.getC_KM_CODE();
//			if(erGzb.getC_KM_CODE().indexOf("_") > 0){
//				String kmAry[] = erGzb.getC_KM_CODE().split("_");
//				kmCode = kmAry[0];
//			}
			
			//兼容老版本，老版本没有C_ZB_NAME字段
			String zbName = StringUtil.IsNullOrEmptyT(erGzb.getC_ZB_NAME()) ? elecRelaMap.get(kmCode) : erGzb.getC_ZB_NAME();
			erGzb.setC_KM_CODE_TEMP(erGzb.getC_KM_CODE());
			if(!StringUtil.IsNullOrEmptyT(zbName)){
				if(StringUtil.IsNullOrEmptyT(erGzb.getC_PORT_CLS_CODE())){
					erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + ")");
				}else {
					erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + "-" + erGzb.getC_PORT_CLS_CODE() + ")");
				}
			}
//			String zbName = elecRelaMap.get(erGzb.getC_KM_CODE());
//			if (null != zbName) {
//				erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + ")");
//			}else if (erGzb.getC_KM_CODE().indexOf("_") > 0) { //如果科目代码包含“_”,则指标代码_分级产品代码
//				String kmAry[] = erGzb.getC_KM_CODE().split("_");
//				zbName = elecRelaMap.get(kmAry[0]);
//				if(zbName != null){
//					//STORY47037嘉实基金电子对账管理查看托管人反馈的信息时，分级指标名称需要展示至具体的分级代码
//					//erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + ")");
//					String c_PORT_CLS_CODE = erGzb.getC_PORT_CLS_CODE();
//					if(c_PORT_CLS_CODE == null || c_PORT_CLS_CODE.trim().equals(""))
//					{
//						erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + ")");
//					}else
//					{
//						erGzb.setC_KM_CODE(erGzb.getC_KM_CODE() + "(" + zbName + "-" + c_PORT_CLS_CODE + ")");
//					}
//				}
//			}
		}
		
		return pojoList;
	}
	
	/**
	 * 20171121 wlx BUG180889【中国银行】电子对账生成后已生成记录中指标项重复
	 * @param paraMap
	 * @param clazz
	 * @return
	 */
	public List<BasePojo> queryGzbData(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		return this.queryByCondition(paraMap, clazz);
	}
	/**
	 * 获取 HashMap<指标代码, 指标名称> 
	 * wlx 2016-12-2 BUG #146679::嘉实基金电子对账估值指标显示前台修改
	 * @param paraMap2 条件
	 * @param 
	 * @return
	 */
	public HashMap<String, String> queryElecRelaMap(HashMap<String, Object> paraMap2){
		HashMap<String, String> paraMap = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement psm = null;
		ResultSet rs = null;
		try {
			conn = loadNewConnection();
			StringBuffer buf = new StringBuffer();
			buf.append("  SELECT DISTINCT A.C_ZB_CODE, A.C_ZB_NAME FROM T_Z_BI_RELA A WHERE A.C_DZ_CODE ='1011' ");
			psm = conn.prepareStatement(buf.toString());
			rs = psm.executeQuery();
			while (rs.next()) {
				if(!paraMap.containsKey(rs.getString("C_ZB_CODE"))){
					paraMap.put(rs.getString("C_ZB_CODE"), rs.getString("C_ZB_NAME"));
				}
			}
		} catch (Exception ex) {
			logger.error("查询对账指标出错:" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(psm);
			releaseConnection(conn);
		}
		return paraMap;
	}
	
	public List<String> getRealIndexCode(String dzCode, String zbCode) {
		List<String> indexCodeList = new ArrayList<String>();
		indexCodeList = elecRelaDao.getRealIndexCode(dzCode,zbCode);
		return indexCodeList;
	}

	public String formatSSZBValue(String port, String d_trade, BigDecimal value) {
		return elecRelaDao.formatSSZBValue(port,d_trade,value);
	}

	public HashMap<String, String> formatedData(String ports,
			HashMap<String, String> formatData) {
		return elecRelaDao.formatedData(ports,formatData);
	}
}
