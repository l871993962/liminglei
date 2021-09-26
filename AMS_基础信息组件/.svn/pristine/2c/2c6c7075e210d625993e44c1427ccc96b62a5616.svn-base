package com.yss.ifa.szt.tool.para.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;
import com.yss.ifa.szt.tool.pojo.ErPara;
import com.yss.ifa.szt.tool.pojo.MrInfo;

/**
 *  /// STORY42784中国银行_深证通伺服器要求采用热备模式
 *  /// STORY42660【中国银行】深证通伺服器要求采用热备模式
 * @ClassName: ErParaDao 
 * @Description: 深圳通参数设置
 * @author wulongxing
 * @date 2017年6月13日 下午2:51:31 
 *
 */
public class ErParaDao extends GeneralDao{
	private MrInfoDao mrInfoDao = null;
	public ErParaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		mrInfoDao = new MrInfoDao(pool, new MrInfoSqlBuilder());
	}
	
	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = super.queryByCondition(paraMap, clazz);
		for (int i = 0; i < pojoList.size(); i++) {
			ErPara erPara = (ErPara) pojoList.get(i);
			List<MrInfo> mrInfoList = mrInfoDao.queryMrInfos(erPara.getC_Para_Code());
			erPara.setMrInfoList(mrInfoList);
		}
		
		return pojoList;
	}
	/**
	 * @Description: 根据参数代码获取伺服器配置信息
	 * @param c_Para_Code 参数代码
	 * @return  返回主备机伺服器信息
	 * @author wulongxing 
	 * @date 2017年6月20日 下午4:12:59
	 */
	public List<MrInfo> queryMrInfos(String c_Para_Code) {
		return mrInfoDao.queryMrInfos(c_Para_Code);
	}

	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {

		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			for (int i = 0; i < pojoList.size(); i++) {
				ErPara erPara = (ErPara) pojoList.get(i);
				deleteById(erPara, conn);
				for (int j = 0; j < erPara.getMrInfoList().size(); j++) {
					mrInfoDao.deleteById(erPara.getMrInfoList().get(j), conn);
				}
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
	public <T extends BasePojo> void updateById(List<T> pojoList)
			throws DataAccessException {

		Connection conn = null;
		boolean bTrans = false;
		try {
			if (pojoList == null || pojoList.size() == 0) {
				throw new InvalidParametersException("baseBean数据实例不能为空");
			}

			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			for (int i = 0; i < pojoList.size(); i++) {
				ErPara erPara = (ErPara) pojoList.get(i);
				List<BasePojo> list = this.queryByIds(erPara.getId(), ErPara.class);
				if(list != null && !list.isEmpty()){
					ErPara unEditPara = (ErPara) list.get(0);
					mrInfoDao.deleteByParaCode(unEditPara.getC_Para_Code(), conn);
				}
				updateById(pojoList.get(i), conn);
				mrInfoDao.insert(erPara.getMrInfoList(), conn);
			}
			
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	
		super.updateById(pojoList);
	}
	@Override
	public <T extends BaseBean> List<String> insert(List<T> list)
			throws DataAccessException {

		List<String> cidenList = new ArrayList<String>();
		
		Connection conn = null;
		try {
			if (list == null) {
				throw new InvalidParametersException("list数据不能为空");
			}
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			for (T pojo : list) {
				if (pojo instanceof ParamPojo) {
					((ParamPojo) pojo).setModifyDate(DateUtil
							.getNow(MvcConstant._DATA_STD_DATE_FORMAT));
				}
				
				String ciden = insert(pojo, conn);
				ErPara erPara = (ErPara) pojo;
				mrInfoDao.insert(erPara.getMrInfoList(), conn);
				cidenList.add(ciden);
			}			

			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			if(ex instanceof YssRuntimeException){
				throw (DataAccessException)ex;
			}else{
				logger.log("保存失败：" + ex.getMessage());
				throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
			}
		} finally {
			this.releaseConnection(conn);
		}
		return cidenList;
	
	}




	/**
	 * @Description: 下拉框初始化加载参数调用
	 * @return 返回数据对象
	 * @author wulongxing 
	 * @date 2017年6月17日 下午2:44:09
	 */
	public List<BasePojo> getDataList() {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		BasePojo basePojo = null;
		try{
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			String sql = sqlbuilder.getQueryConditionSql(null);
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				basePojo = (BasePojo) rsTools.ResultToPojoObject(rs, ErPara.class);
				pojoList.add(basePojo);
			}
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return pojoList;

	}
	/**
	 * @Description: listhead加载列头时，key转成name
	 * @param listKey 需要筛选key的值
	 * @return 返回key,value 
	 * @author wulongxing 
	 * @date 2017年6月17日 下午2:45:08
	 */
	public HashMap<String, String> getKeyConvertMap(List<String> listKey) throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			String[] arrList = new String[listKey.size()];

			conn = this.loadNewConnection();

			List<String> paraNameList = new ArrayList<String>();
			paraNameList.add("ARRAY_C_PARA_CODE");
			String sql = sqlbuilder.getQueryConditionSql(paraNameList);
			listKey.toArray(arrList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(arrList, conn));

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String key = rs.getString("c_Para_Code");
				String val = rs.getString("c_Para_Name");
				keyValueMap.put(key, val);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return keyValueMap;
	}
	/**
	 * @Description: listhead加载列头时，key转成name
	 * @return 返回key,value 
	 * @author wulongxing 
	 * @date 2017年6月17日 下午2:45:19
	 */
	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {

			conn = this.loadNewConnection();

			String sql = sqlbuilder.getQueryConditionSql(null);;

			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String key = rs.getString("c_Para_Code");
				String val = rs.getString("c_Para_Name");
				keyValueMap.put(key, val);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return keyValueMap;
	}
	/**
	 * @Description: 浏览，修改下拉框值时，根据key值获取POJO
	 * @param pojoCode
	 * @return BasePojo    返回类型 
	 * @author wulongxing 
	 * @date 2017年6月17日 下午2:45:28
	 */
	public BasePojo getPojoByCode(String pojoCode) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetTools rsTools = null;
		BasePojo basePojo = null;
		try{
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			conn = this.loadNewConnection();
			List<String> paraNameList = new ArrayList<String>();
			paraNameList.add("ARRAY_C_PARA_CODE");
			String sql = sqlbuilder.getQueryConditionSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(pojoCode, conn));
			rs = pstmt.executeQuery();
			if(rs.next()){
				basePojo = (BasePojo) rsTools.ResultToPojoObject(rs, ErPara.class);
			}
		}catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}

		return basePojo;
	}

}
