package com.yss.ams.base.information.modules.bi.hday.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.support.bi.hday.pojo.Hday;
import com.yss.ams.base.information.support.cache.MktCache;
import com.yss.ams.base.information.support.util.holidays.HolidaysAide;
import com.yss.framework.api.cache.CacheManager;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.Mkt;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.ErrorMessageException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.api.util.YssFun;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;
import com.yss.mvc.returninfo.ReturnInfoGenerator;

/**
 * 节假日群设置dao层
 * @author yuankai 公共信息拆分  2017.5.31
 *
 */
public class HdayDao extends GeneralDao {
	private HdaySqlBuilder builder = null;
	public HdayDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		builder = (HdaySqlBuilder)sqlBuilder;
		// TODO Auto-generated constructor stub
	}

	public <T extends BaseBean> String insert(T baseBean)
			throws DataAccessException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		/*
		 * Author : ChenLong
		 * Date   : 2013-11-18
		 * Status : Add
		 * Comment: 插入数据的CIDEN返回值
		 * */
		String ciden = "";
		try {
			if (baseBean == null) {
				throw new InvalidParametersException("数据实例不能为空");
			}
//			Hday hday=(Hday)baseBean;
//			String sql="delete from t_p_bi_hday_sub where d_hday=? and c_hday_code=?";
			
			conn = this.loadNewConnection();
//			pstmt=conn.prepareStatement(sql);
//			pstmt.setDate(1,YssFun.toSqlDate(hday.getD_HDAY()));
//			pstmt.setString(2, hday.getC_HDAY_CODE());
//			pstmt.execute();
			conn.setAutoCommit(false);
			ciden = insert(baseBean, conn);

			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
//			try {
//				if (pstmt != null) {
//					pstmt.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			try {
//				if (conn != null) {
//					conn.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
		}
		return ciden;
	}
	
	/**
	 * 重写删除方法  added by ll 2012-11-19
	 * @param pojoList
	 * @return
	 */
	public String deleteByList(List<BasePojo> pojoList){
		String retInfo = "";
		boolean bTrans = false;
		String delSql = " delete from T_P_BI_HDAY_SUB WHERE C_IDEN = ? ";
		PreparedStatement pstmt = null;
		Connection conn = null;
		
		try {
			conn= this.loadNewConnection();
			pstmt = conn.prepareStatement(delSql);
			for(BasePojo basePojo : pojoList){
				pstmt.setString(1,basePojo.getId() );
				pstmt.addBatch();
			}
			bTrans = true;
			conn.setAutoCommit(bTrans);
			pstmt.executeBatch();
			conn.commit();
			retInfo = ReturnInfoGenerator.getDeleteOKStr("base_holidays");
		} catch (Exception ex) {
			retInfo = ReturnInfoGenerator.getOperErrMsg(
					MvcConstant._CodeDelErr, "base_holidays");
//			ex.printStackTrace();
			logger.log("节假日群设置：删除节假日群数据失败", ex);
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
//			if(pstmt != null){
//				try {
//					if(null != pstmt){
//						pstmt.close();
//					}
//					if(null != conn){
//						conn.close();
//					}
//				} catch (SQLException e) {
//					e.printStackTrace();
//				}
//			}	
		}
		
		return retInfo;
	
	}
	
	/**
	 * 根据节假日群代码查询所有年份
	 * @param code
	 * @return
	 */
	public List<String> getAllYear(String code){
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn= this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getAllYear());
			pstmt.setString(1, code);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("N_YEAR"));
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("节假日群设置：根据节假日群代码查询所有年份出错", ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return list;
	}
 /**
  * 根据条件查询符合条件的所有节假日信息
  * @param hashMap
  * @return
  */
	public List<String> getAllHoiday(HashMap<String,Object> hashMap) {
		List<String> paraNameList = getParaName(hashMap);
		List<String> list = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn= this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getAllHoiday(paraNameList));
			setPara(pstmt,paraNameList,hashMap);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				list.add(YssFun.formatDate(rs.getDate("D_HDAY"), "yyyy-MM-dd"));
			}
		} catch (Exception ex) {
			//   ex.printStackTrace();
			logger.log("节假日群设置：根据条件查询符合条件的所有节假日信息失败", ex);
		}finally{
			closeResultSetFinal(rs);
		    closeStatementFinal(pstmt);
		    releaseConnection(conn);
		}
	  
		return list;
	}
	
	/**
	 * 根据节假日代码查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getHoidayByCode(String C_HDAY_CODE) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("C_HDAY_CODE", C_HDAY_CODE);
		List<String> paraNameList = getParaName(hashMap);
		List<Date> list = new ArrayList<Date>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getAllHoiday(paraNameList));
			setPara(pstmt, paraNameList, hashMap);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// BUG #170284 从功能菜单搜索‘调度方案处理’，双击打开报错 
				list.add(new java.util.Date(rs.getDate("D_HDAY").getTime()));
			}
		} catch (Exception ex) {
			throw new YssRuntimeException(ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return list;
	}
	
	/**
	 * 根据节假日代码查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getAuditHoidayByCode(String C_HDAY_CODE) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("C_HDAY_CODE", C_HDAY_CODE);
		hashMap.put("SearchAudit", "");
		List<String> paraNameList = getParaName(hashMap);
		List<Date> list = new ArrayList<Date>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getAllHoiday(paraNameList));
			setPara(pstmt, paraNameList, hashMap);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// BUG #170284 从功能菜单搜索‘调度方案处理’，双击打开报错 
				list.add(new java.util.Date(rs.getDate("D_HDAY").getTime()));
			}
		} catch (Exception ex) {
			throw new YssRuntimeException(ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return list;
	}
	
	/**
	 * 根据节假日代码和审核状态查询符合条件的所有节假日日期信息
	 * @param C_HDAY_CODE
	 * @return
	 */
	public List<Date> getHoidayByCodeAndCheck(String C_HDAY_CODE, String N_CHECK_STATE) {
		List<Date> list = new ArrayList<Date>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql ="select a.D_HDAY  from t_p_bi_hday_sub a where c_date_type = 'H' and c_hday_code = ? and n_check_state = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, C_HDAY_CODE);
			pstmt.setString(2, N_CHECK_STATE);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				// BUG #170284 从功能菜单搜索‘调度方案处理’，双击打开报错 
				list.add(new java.util.Date(rs.getDate("D_HDAY").getTime()));
			}
		} catch (Exception ex) {
			throw new YssRuntimeException(ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return list;
	}

	public void setPara(PreparedStatement pstmt,List<String> paraNameList,HashMap<String,Object> hashMap) throws SQLException{
		for (int i = 0; i < paraNameList.size(); i++) {
			pstmt.setString(i+1, hashMap.get(paraNameList.get(i)).toString());
		}
	}
	
	/**
	 * 根据条件查询节假日信息记录数
	 * @param hashMap
	 * @return
	 */
	public String getSameHoiday(HashMap<String, Object> hashMap) {
		List<String> paraNameList = getParaName(hashMap);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String reVal = "false";
		try {
			conn= this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getHoidayCount(paraNameList));
			setPara(pstmt,paraNameList,hashMap);
			rs = pstmt.executeQuery();
			rs.next();
			int num = rs.getInt(1);
			if (num > 0) {
				reVal = "yes";
			}
		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("节假日群设置：根据条件查询节假日信息记录数失败", ex);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		
		return reVal;
	}

	/**
	 * 根据节假日群和日期获取其对应的节假日是否存在MAP
	 * @param pojoList
	 * @return
	 * @throws Exception 
	 */
	public Map<String, Boolean> getExistMaps(List<BasePojo> pojoList) throws Exception {

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Map<String, Boolean> isExistMaps = new HashMap<String, Boolean>();
		
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(builder.getHdayByPrimaryKey());
			
			if(pojoList != null && pojoList.size() > 0) {
				StringBuffer hdaysBuf = new StringBuffer();
				pstmt.setString(1, ((Hday) pojoList.get(0)).getC_HDAY_CODE());
				for(BasePojo pojo : pojoList) {
					hdaysBuf.append(((Hday) pojo).getD_HDAY()).append(",");
				}
				pstmt.setArray(2, OraDbTool.newInstance().sqlOverLongCondition(hdaysBuf.substring(0, hdaysBuf.lastIndexOf(",")),conn));
				rs = pstmt.executeQuery();
				while(rs.next()) {
					isExistMaps.put(rs.getString("C_HDAY_CODE") + "/" + 
							YssFun.formatDate(rs.getDate("D_HDAY"), "yyyy-MM-dd"), true);
				}
			}
			
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("节假日群设置：根据节假日群和日期查询是否存在对应的节假日信息失败", e);
			throw new YssException("操作节假日群失败：" + e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return isExistMaps;
	}
	
	/**
     * 如果date是工作日则返回date,如果date是节假日则返回下一个工作日。
     * add by chenwenhai 20140514 
     * @param specifiedDate：指定日期
     * @param offset:期限
     * @param mktCode：交易市场代码
     * @return 指定日期+期限 （节假日顺延到下个工作日）
     * 前台调用代码，后期改成交易市场代码，需要修改前台代码  by yuankai 2017.6.23
     */
    public String getWorkday(String specifiedDate,String offset, String mktCode){
    	Date curDate = null;
    	try{
    		curDate = YssFun.parseDate(specifiedDate);
    		int offset2 = Integer.parseInt(offset);
//    		PdPortCache portCache = CacheManager.getInstance().getCache(CacheGroup.PORT);
//    		Port port = portCache.getCacheByKey(portCode);
//    		String holidaysCode = port.getC_HDAY_CODE();
    		//// By Jinghehe 2017-8-3 获取交易市场缓存对象
    		MktCache mktCache = CacheManager.getInstance().getCache(CacheGroup.MKT);
    		//// By Jinghehe 2017-8-3 获取交易市场缓存对象如果是null 直接返回传入日期 
    		if(null == mktCache)
    		{
    			return YssFun.formatDate(curDate);
    		}
    		//// By Jinghehe 2017-8-3 根据交易市场内码 从交易市场缓存获取对应交易市场对象 
    		Mkt mkt = mktCache.getCacheByKey(mktCode);
    		//// By Jinghehe 2017-8-3 获取交易市场缓存对象如果是null 直接返回传入日期 
    		if(null == mkt)
    		{
    			return YssFun.formatDate(curDate);
    		}
    		curDate = YssFun.addDay(curDate, offset2); 
    		//// By Jinghehe 2017-8-3 获取交易市场的节假日群代码 推算 偏移日期
    		String holidaysCode = mkt.getC_HDAY_CODE();
    		while (true) {
    			Boolean flag = HolidaysAide.isWorkDay(curDate, holidaysCode);
    			
    			if(flag){
        			break;
        		}else{
        			curDate = YssFun.addDay(curDate, 1);
        		}
    		}
    	}catch(Exception e){
//    		e.printStackTrace();
    		logger.log("节假日群设置：根据指定日期获取下一个工作日出错", e);
    	}
    	
    	return YssFun.formatDate(curDate);
    }
    
	/**
     * 根据条件获取日期
     * @param date
     * @param offset
     * @param hdayCode
     * @param hdayType
     * @return
     */
    public String getDay(String date, String offset, String hdayCode,
			String hdayType) {
    	Date curDate = null;
    	Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
    	try{
    		conn = this.loadNewConnection();
    		String sql ="select PKG_FUN_GETDAY_HDAY.GETDAY_HDAY(?,?,?,?) as D_DATE from dual";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hdayCode);
			pstmt.setDate(2, YssFun.toSqlDate(date));
			pstmt.setInt(3, YssFun.toInt(offset));
			pstmt.setString(4, hdayType);
			rs = pstmt.executeQuery();
			if(rs.next()){
				curDate = rs.getDate("D_DATE");
			}
    	}catch(Exception ex){
//    		ex.printStackTrace();
    		logger.log("节假日群设置：根据条件获取日期出错", ex);
    	}finally{
    		this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
    	}
		return YssFun.formatDate(curDate);
	}
    
    /**
     * 
     * @Title getWorkDayInMonth 
     * @Description 获取目标月份的第N个工作日日期
     * @author lixiang@ysstech.com
     * @date 2017年7月17日上午11:08:14
     * @param date
     * @param indexDay
     * @param c_HDAY_CODE
     * @return
     * @return String
     */
	public String getWorkDayInMonth(String date, int indexDay,
			String c_HDAY_CODE) {
		SimpleDateFormat spd = new SimpleDateFormat("yyyy-MM-dd");
        String resultDate = "";
        HashMap<String, Object> map= new HashMap<String, Object>();
        map.put("C_HDAY_CODE", c_HDAY_CODE);
        //list为以排序好的节假日集合，减少后续遍历次数
        List<BasePojo> list = this.querySelectList(Hday.class, new HdaySqlBuilder().getHoliDayByMouths(date), map);
        
        try {
            Date dd = spd.parse(date+"-01");
            //计算工作日
            for(int i=0;i<indexDay;i++){
                
                Calendar calendar = new GregorianCalendar();
                calendar.setTime(dd);
                //工作日遍历增加，则日期加1
                if( i != 0 ){
                    calendar.add(calendar.DATE, 1);
                    dd= calendar.getTime();
                }
                
                Iterator<BasePojo> it = list.iterator();
                //日期如果在节假日范围里就加大一天，继续遍历
                while(it.hasNext()){
                	Hday hday = (Hday)it.next();
                    
                    //如果与节假日相同，就日期加1继续比较，不同则跳出当前循环计算下个工作日
                    if(spd.parse(hday.getD_HDAY()).equals(dd)){
                        calendar.add(calendar.DATE, 1);
                        dd= calendar.getTime();
                        
                        it.remove();
                    }else{
                        break;
                    }
                }
            }
            
            resultDate = spd.format(dd);
            
        }
        catch (ParseException e) {
            throw new ErrorMessageException(e,"");
        }
        return resultDate;
	}
	
	/**
	 * 
	 * @Title querySelectList 
	 * @Description 通过类型查询select数据
	 * @author lixiang@ysstech.com
	 * @date 2017年7月17日上午11:11:31
	 * @param clazz
	 * @param sql
	 * @param paraMap
	 * @return
	 * @return List<BasePojo>
	 */
    public List<BasePojo> querySelectList(Class<?> clazz,String sql,HashMap<String, Object> paraMap) {
        List<BasePojo> pojoList = new ArrayList<BasePojo>();
        Connection conn = null;
        
        List<String> paraNameList = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        ResultSetTools rsTools = null;
        try {
            rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);//holiDaySqlBuilder
            conn = this.loadNewConnection();
            if(null != paraMap){
                paraNameList = getParaName(paraMap);
            }
//          sql = transTypeSqlBuilder.queryTradeType();

            pstmt = conn.prepareStatement(sql);

       //// 如果SQL中有问号时才进行赋值
            if (sql.indexOf("?") > -1) {
                int index = 1;
                Object paraValue = null;
              //Fortify 规范代码改造避免空指针异常
                if(null != paraNameList && paraNameList.size()>0){
	                for (String valueFieldName : paraNameList) {
	                    if ("N_CHECK_STATE".equals(valueFieldName)) {
	                        continue;
	                    }
	
	                    if (valueFieldName.startsWith("ARRAY_")) {
	                        pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
	                                .valueOf(paraMap.get(valueFieldName)),conn));
	                    } else {
	                        paraValue = paraMap.get(valueFieldName);
	                        if (java.util.Date.class.equals(paraValue)) {
	                            java.sql.Date dateValue = new java.sql.Date(
	                                    ((java.util.Date) paraValue).getTime());
	                            pstmt.setDate(index, dateValue);
	                        } else {
	                            pstmt.setObject(index, paraValue);
	                        }
	                    }
	
	                    index++;
	                }
                }
            }
            
            rs = pstmt.executeQuery();

            BasePojo pojo = (BasePojo) clazz.newInstance();
            PropertyDescriptor[] props = PojoUtils.getPropertyDescriptors(pojo);
            while (rs.next()) {
                BasePojo t = setResultSet(rsTools, rs, clazz, props);
                getConvertKey(props, t);
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
    

	/***
	 * hwh
	 * 获取已审核节假日代码/名称
	 * STORY #68965 FAST分布式分库情况下的sql解耦 
	 * @return
	 */
	public HashMap<String,String> getHdayInfo() {
		HashMap<String,String> hdayMap=new HashMap<String,String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql ="select * from t_p_bi_hday where n_check_state=1";
			pstmt = conn.prepareStatement(sql);			
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String key=rs.getString("c_hday_code");
				String value=rs.getString("c_hday_name");
				hdayMap.put(key, value);
			}
		} catch (Exception ex) {
			throw new YssRuntimeException(ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return hdayMap;
	}
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return Hday
	 */
	public Hday queryHdayByHday(String hdayCode,String hday){
		Hday pojo = null;
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
	
		String sql = "";
		ResultSetTools rsTools = null;
	
		HdaySqlBuilder dsServiceBuilder = null;
		try {
			dsServiceBuilder = new HdaySqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);
	
			conn = this.loadNewConnection();
	
			sql = " SELECT * FROM T_P_BI_HDAY_SUB A WHERE A.C_HDAY_CODE = ? AND A.D_HDAY = ?  ";
			Date date = YssFun.parseDate(hday,"yyyyMMdd");
			String sDate = YssFun.formatDate(date,YssCons.YSS_DATEFORMAT);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, hdayCode);
			pstmt.setDate(2,YssFun.toSqlDate(sDate));
			rs = pstmt.executeQuery();
	
			if (rs.next()) {
				pojo = (Hday)rsTools.ResultToBean(rs, Hday.class);
			}
	
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojo;
	}
	
	/**
	 * STORY #73552 节假日信息设置restful接口 
	 * add by zuomingke
	 * date 20190713
	 * @return List<Hday>
	 */
	public List<Hday> queryHdayByYearOrMonth(String hdayCode ,String yearOrMonth){
		List<Hday> pojoList = new ArrayList<Hday>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		String sql = "";

		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			if(yearOrMonth.length() == 4){
				sql = " SELECT * FROM T_P_BI_HDAY_SUB A WHERE A.C_HDAY_CODE = ? AND A.N_YEAR = ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hdayCode);
				pstmt.setInt(2, Integer.parseInt(yearOrMonth));
			}else{
				sql = "SELECT * FROM T_P_BI_HDAY_SUB A WHERE A.C_HDAY_CODE = ? AND  A.D_HDAY BETWEEN ? AND ? ";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, hdayCode);
				String sYear = yearOrMonth.substring(0,4);
				String sMonth = yearOrMonth.substring(4,6);
				int nYear = Integer.parseInt(sYear);
				int nMonth = Integer.parseInt(sMonth);
				if(nMonth == 12){
					String sDate = sYear+"-"+sMonth+"-"+"01";
					pstmt.setDate(2,YssFun.toSqlDate(sDate));
					
					nMonth =1;
					nYear++;
					sDate = String.valueOf(nYear)+"-"+String.valueOf(nMonth)+"-"+"01";
					pstmt.setDate(3,YssFun.toSqlDate(sDate));
					
				}else{
					String sDate = sYear+"-"+sMonth+"-"+"01";
					pstmt.setDate(2,YssFun.toSqlDate(sDate));
					
					nMonth++;
					sDate = sYear +"-"+ String.valueOf(nMonth)+"-"+"01";					
					pstmt.setDate(3,YssFun.toSqlDate(sDate));
					
				}
			}
			
			rs = pstmt.executeQuery();
			Hday t = null;
			while (rs.next()) {
				t = (Hday) rsTools.ResultToBean(rs, Hday.class);
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
		
}
