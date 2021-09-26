package com.yss.ams.visaval.dao;

import java.io.StringReader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.api.pojo.ParamFromSql;
import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.pojo.AdvAlgoPara;
import com.yss.ams.visaval.support.service.IVisFunctionTransformService;
import com.yss.ams.visaval.support.service.IVisJythonFactoryWrap;
import com.yss.ams.visaval.support.util.pojo.FunctionBean;
import com.yss.ams.visaval.support.util.pojo.SysWordBean;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.IEffectivable;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.AutoDateProc;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.api.util.YssCons;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.PojoUtils;

/**
 * 更新记录
 * =====================
 * 更新内容 修改抛出异常类型
 * 由YssException 改成  DataAccessException
 * 更新人 李海智
 * 更新时间  20130620
 */
public class AdvAlgoDao extends GeneralDao {
	
	public AdvAlgoDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
	}

	/***
	 * 获取对的核算项
	 * 
	 * @param customize
	 *            当前数据
	 * @return 返回查询的数据
	 * @throws YssException
	 */
	public String getAllDate() throws YssException {
		String result = "";// 定义返回结果
		ResultSet rs = null;// 定义结果集
		String strSql = "";// 定义拼接查询的sql语句
		KeyWordsFuncBen keyWFunc = null;
		Connection conn = null;
		try {
			conn = loadNewConnection();
			strSql = "select C_DV_CODE as C_CODE,C_DV_NAME as C_NAME,C_DV_TYPE as C_TYPE from  "
					+ " v_s_dv_voc a where a.c_dv_type = 'FUNC_TYPE'";
//				    " or a.c_dv_type = 'KEY_TYPE' ";//delete  by zhaoxianlin 20130506 STORY #3351 算法公式的优化   不再使用关键字
			rs = openResultSet(strSql, conn);
			while (rs.next()) {
				keyWFunc = new KeyWordsFuncBen();
				keyWFunc.parseRsToAttr(rs);
				result += keyWFunc.buildAttrToStr()
						+ YssCons.YSS_PASSAGESPLITMARK;
			}
			if (result.length() > 0) {
				result = result.substring(0, result.length() - 2);
			}
		} catch (Exception e) {
			//将 YssException 换成  DataAccessException by lihaizhi 20130620
			throw new DataAccessException("转换公式运算符错误！", e);
		} finally {
			closeResultSetFinal(rs);
			releaseConnection(conn);
		}
		return result;
	}

	/***
	 * 获取对的核算项
	 * 
	 * @param customize
	 *            当前数据
	 * @return 返回查询的数据
	 * @throws YssException
	 */
	public String getKeyWords(String keyType) throws YssException {
		String result = "";// 定义返回结果
		ResultSet rs = null;// 定义结果集
		String strSql = "";// 定义拼接查询的sql语句
		SysWordBean sysWordsBen = null;
		Connection conn = null;
		try {
			conn = loadNewConnection();
			strSql = "select * from t_s_keywords a ";
			// commonSql.whereClause(getQuyConJObj());
			if (keyType != null && keyType.trim().length() > 0) {
				strSql = strSql + " where C_DV_KEY_TYPE='" + keyType + "'";
			}
			rs = openResultSet(strSql, conn);
			while (rs.next()) {
				sysWordsBen = new SysWordBean();
				sysWordsBen.parseRsToAttr(rs);
				result += sysWordsBen.buildAttrToStr()
						+ YssCons.YSS_PASSAGESPLITMARK;
			}
			// //去掉对于的\r\f
			if (0 != result.length()) {
				result = result.substring(0, result.length() - 2);
			}
		} catch (Exception e) {
			//将 YssException 换成  DataAccessException by lihaizhi 20130620
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			closeResultSetFinal(rs);
			releaseConnection(conn);
		}
		return result;
	}

	/***
	 * 获取对的核算项
	 * 
	 * @param customize
	 *            当前数据
	 * @return 返回查询的数据
	 * @throws YssException
	 */
	public String getFunc(String funType) throws YssException {
		String result = "";// 定义返回结果
		ResultSet rs = null;// 定义结果集
		String strSql = "";// 定义拼接查询的sql语句
		FunctionBean funcBen = null;
		Connection conn = null;
		try {
			conn = loadNewConnection();
			strSql = "select a.*,to_char(b.PARAINFO) as PARAINFO from T_S_FUNCTION a   "
					+ "left join(select b.c_func_code,wmSys.Wm_Concat(b.C_FUNC_CODE ||'\t'||b.c_para_code ||'\t'||b.c_para_name||'\t'||b.c_dv_para_type||'\t'||b.c_desc||'\t'||b.n_order)"
					+ "as PARAINFO from  (select b.*, c.c_para_name, c.c_dv_para_type, c.c_desc from t_s_func_para b left join (select * from t_s_para) c on b.c_para_code = c.c_para_code ) b group by b.c_func_code) b on a.c_func_code = b.c_func_code ";
			// + commonSql.whereClause(getQuyConJObj());
			if (funType != null && funType.trim().length() > 0) {
				strSql = strSql + " where C_DV_FUNC_TYPE='" + funType + "'";
			}
			rs = openResultSet(strSql, conn);
			while (rs.next()) {
				funcBen = new FunctionBean();
				funcBen.parseRsToAttr(rs);
				result += funcBen.buildAttrToStr()
						+ YssCons.YSS_PASSAGESPLITMARK;
			}
			// //去掉对于的\r\f
			if (0 != result.length()) {
				result = result.substring(0, result.length() - 2);
			}
		} catch (Exception e) {
			//将 YssException 换成  DataAccessException by lihaizhi 20130620
			throw new DataAccessException("查询失败：" + e.getMessage(), e);
		} finally {
			closeResultSetFinal(rs);
			releaseConnection(conn);
		}
		return result;
	}

	/***
	 * 获取对的核算项
	 * 
	 * @param customize
	 *            当前数据
	 * @return 返回查询的数据
	 * @throws YssException
	 */
	public String getRealExpression(String targetStr) throws YssException {
		try {
			// 获取对应的转换参数
			//ArrayList<Variable> list = FunctionTransform.getAllList(targetStr);
			// 把字符串中的函数，关键字转换成系统能够识别的字符串
			// 把字符串中的函数，关键字转换成系统能够识别的字符串
			IVisFunctionTransformService functionTransformService = YssServiceFactory.getInstance().createService(IVisFunctionTransformService.class);
			targetStr = functionTransformService.functionTran(targetStr); // 转换函数和关键字
			// 转换用户定义的默认组合
			targetStr = functionTransformService.getAllUserDefine(targetStr);
			// 去掉字符串中多余的空格和回车
			targetStr = functionTransformService.removeSpecChar(targetStr);// 去掉空格和回车
			// 把字符串转换成标准的三元运算符
			targetStr = functionTransformService.logicTran(targetStr); // 转换三元运算符
			// 去掉多余的空格和回车
			targetStr = functionTransformService.removeSpecChar(targetStr);// 去掉空格和回车
			// 执行函数获取结果
//			PreparedExpression pe = ExpressionEvaluator.preparedCompile(
//					targetStr, list);
			// result = ExpressionEvaluator.evaluate(targetStr,list).toString();
		} catch (Exception ex) {
			throw new YssException(ex);
		}

		return targetStr;
	}

	public class KeyWordsFuncBen {
		/***
		 * 代码
		 */
		private String C_CODE = null;

		/***
		 * 名字
		 */
		private String C_NAME = null;

		/***
		 * 类型
		 */
		private String C_TYPE = null;

		/***
		 * 父节点代码
		 */
		//注释无用属性 by lihaizhi 20130620
		//private String C_CODE_P = null;
		
		/**
		 * 设置实体Bean值
		 * 
		 * @param rs
		 * @throws SQLException
		 * @throws YssException
		 */
		public void parseRsToAttr(ResultSet rs) throws SQLException,
				YssException {
			C_CODE = rs.getString("C_CODE");
			C_NAME = rs.getString("C_NAME");
			C_TYPE = rs.getString("C_TYPE");
		}

		/**
		 * buildRowStr 按一定格式拼接数据
		 * 
		 * @return String
		 */
		public String buildAttrToStr() {
			StringBuffer buf = new StringBuffer();
			buf.append(C_CODE).append(YssCons.YSS_ITEMSPLITMARK1);
			buf.append(C_NAME).append(YssCons.YSS_ITEMSPLITMARK1);
			buf.append(C_TYPE).append(YssCons.YSS_ITEMSPLITMARK1);
			return buf.toString();
		}
	}

	/* START 数据服务方法 */
	public List<AdvAlgo> getAllDataList() throws Exception {
		List<AdvAlgo> pojoList = new ArrayList<AdvAlgo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			//sql = dsServiceBuilder.getAllDataSql();
			/**
			 * buf.append(" select a.*,zh.c_formula "); 
			buf.append(" from T_V_AA_ADV_ALGO a  left join T_V_AA_ADV_ALGO_ZH zh on a.c_algo_code=zh.c_algo_code and");
			buf.append(" a.N_CHECK_STATE = 1 ");
			 */
			// STORY #31713 【产品优化】算法公式配置优化  添加算法中文代码  20170906 马向峰
			sql = " select a.*,zh.c_formula  from T_V_AA_ADV_ALGO a  left join T_V_AA_ADV_ALGO_ZH zh on a.c_algo_code=zh.c_algo_code and a.N_CHECK_STATE = 1 ";

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);//c_formula
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				// STORY #31713 【产品优化】算法公式配置优化  添加算法中文代码  20170906 马向峰
				String c_formula_zh = clobStrValue(rs.getClob("c_formula"));
				((AdvAlgo) t).setC_ALGO_FORMULA_ZH(c_formula_zh);
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public List<AdvAlgo> getDataListByTypes(String[] types) throws Exception {
		List<AdvAlgo> pojoList = new ArrayList<AdvAlgo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(types,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				//STORY #31713 【产品优化】算法公式配置优化  modify by maxiangfeng 20170919
				//如果是新算法 则从关联表内取出算法的描述
				String desc = clobStrValue(rs.getClob("new_desc"));
				if(null != desc && desc.length()>0){
					((AdvAlgo) t).setC_DESC(desc);
					((AdvAlgo) t).setC_ALGO_FORMULA_ZH(clobStrValue(rs.getClob("new_c_formula")));
				}
				
				pojoList.add(t);
			}
			
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	public AdvAlgo getDataByCode(String code) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataByCode();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, code);

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				//STORY #31713 【产品优化】算法公式配置优化  modify by maxiangfeng 20170919
				//如果是新算法 则从关联表内取出算法的描述
				String desc = clobStrValue(rs.getClob("new_desc"));
				if(null != desc && desc.length()>0){
					((AdvAlgo) t).setC_DESC(desc);
					((AdvAlgo) t).setC_ALGO_FORMULA_ZH(clobStrValue(rs.getClob("new_c_formula")));
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return t;
	}

	public HashMap<String, String> getKeyConvertMap() throws Exception {
		HashMap<String, String> keyValueMap = new HashMap<String, String>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getAllDataSql();

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);
				keyValueMap.put(t.getC_ALGO_CODE(), t.getC_ALGO_NAME());
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

	public List<AdvAlgo> getDataListByKeys(String[] keys) throws Exception {
		List<AdvAlgo> pojoList = new ArrayList<AdvAlgo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			sql = dsServiceBuilder.getDataListByTypes();

			pstmt = conn.prepareStatement(sql);
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(keys,conn));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				//STORY #31713 【产品优化】算法公式配置优化  modify by maxiangfeng 20170919
				//如果是新算法 则从关联表内取出算法的描述
				String desc = clobStrValue(rs.getClob("new_desc"));
				if(null != desc && desc.length()>0){
					((AdvAlgo) t).setC_DESC(desc);
					((AdvAlgo) t).setC_ALGO_FORMULA_ZH(clobStrValue(rs.getClob("new_c_formula")));
				}
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return pojoList;
	}
	/* END 数据服务方法 */

	public List<BasePojo> getDataListByTimestamp(String timestamp) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;

		AdvAlgoSqlBuilder dsServiceBuilder = null;
		AdvAlgo t = null;
		try {
			dsServiceBuilder = new AdvAlgoSqlBuilder();
			rsTools = new ResultSetTools(dbNameResolver, dsServiceBuilder);

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getDataListByTimestamp();

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, timestamp);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				t = rsTools.ResultToBeanGeneric(rs, AdvAlgo.class);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				//STORY #31713 【产品优化】算法公式配置优化  modify by maxiangfeng 20170919
				//如果是新算法 则从关联表内取出算法的描述
				String desc = clobStrValue(rs.getClob("new_desc"));
				if(null != desc && desc.length()>0){
					((AdvAlgo) t).setC_DESC(desc);
					((AdvAlgo) t).setC_ALGO_FORMULA_ZH(clobStrValue(rs.getClob("new_c_formula")));
				}
				pojoList.add(t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}

	@Override
	public List<BasePojo> queryByConditionPage(HashMap<String, Object> paraMap,
			PageInation page, Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();

			sql = sqlbuilder.getQueryConditionSql(paraNameList);
			sql = buildPagingSql(sql, page);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}
			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue;
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
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}

			rs = pstmt.executeQuery();

			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				BasePojo t = setResultSet(rsTools,rs, clazz);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
				pojoList.add(t);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("查询算法公式失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
	@Override
	public List<BasePojo> queryByCondition(HashMap<String, Object> paraMap,
			Class<?> clazz) {
		List<BasePojo> pojoList = new ArrayList<BasePojo>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";
		ResultSetTools rsTools = null;
		try {
			rsTools = new ResultSetTools(dbNameResolver, sqlbuilder);
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = sqlbuilder.getQueryConditionSql(paraNameList);

			pstmt = conn.prepareStatement(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

			//// 如果SQL中有问号时才进行赋值
			if (sql.indexOf("?") > -1) {
				int index = 1;
				Object paraValue = null;
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
							Date dateValue = new Date(
									((java.util.Date) paraValue).getTime());
							pstmt.setDate(index, dateValue);
						} else {
							pstmt.setObject(index, paraValue);
						}
					}

					index++;
				}
			}

			rs = pstmt.executeQuery();
			while (rs.next()) {
				String algoFormula = clobStrValue(rs.getClob("C_ALGO_FORMULA")); 
				BasePojo t = setResultSet(rsTools,rs, clazz);
				((AdvAlgo) t).setC_ALGO_FORMULA(algoFormula);
				getConvertKey(PojoUtils.getPropertyDescriptors(t),t);
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
	
	@Override
	public <T extends BaseBean> String insert(T baseBean, Connection conn)
			throws DataAccessException {
		String ciden = "";
		try {
			AdvAlgo advAlgo = (AdvAlgo) baseBean;
			StringBuffer buf = new StringBuffer();
			PreparedStatement pstmt = null;
			PreparedStatement stm = null;
			ResultSet rs = null;
			try {

				/*
				 * Author : ChenLong
				 * Date   : 2013-11-18
				 * Status : Add
				 * Comment: 插入数据的CIDEN返回值
				 * */
				ciden = getSequenceNextNumber(conn, "sequ_V_AA_ADV_ALGO");
				
				buf.append("INSERT INTO T_V_AA_ADV_ALGO(c_iden, ");
				buf.append(" c_algo_code,c_algo_name,c_algo_formula,");
				buf.append(" c_desc, n_check_state,c_update_by, c_update_time,");
				buf.append(" c_check_by,c_check_time,c_algo_formula_transform,");
				buf.append(" c_dv_algo_type) ");
				buf.append(" values(?, ");
				buf.append(" ?,?,EMPTY_CLOB(),?,?,?,?,?,?,?,?)");
				pstmt = conn.prepareStatement(buf.toString());
				pstmt.setString(1, ciden);
				pstmt.setString(2, advAlgo.getC_ALGO_CODE());
				pstmt.setString(3, advAlgo.getC_ALGO_NAME());
				pstmt.setString(4, advAlgo.getC_DESC());
				pstmt.setInt(5, advAlgo.getAuditState());
				pstmt.setString(6, advAlgo.getModifier());
				pstmt.setString(7, advAlgo.getModifyDate());
				pstmt.setString(8, advAlgo.getOperator());
				pstmt.setString(9, advAlgo.getAuditDate());
				pstmt.setString(10, advAlgo.getC_ALGO_FORMULA_TRANSFORM());
				pstmt.setString(11, advAlgo.getC_DV_ALGO_TYPE());
				pstmt.executeUpdate();
				
				/**
				 * Author : 马向峰
				 * Date : 2017-08-14
				 * Status : Add
				 * CommentSTORY #31713 【产品优化】算法公式配置优化  往T_V_AA_ADV_ALGO_ZH插入数据，判断新旧算法依据
				 */
				if (null != advAlgo.getC_ALGO_FORMULA_ZH() && advAlgo.getC_ALGO_FORMULA_ZH().length() > 0){
					//有参考算法的情况
					String iSql = "insert into T_V_AA_ADV_ALGO_ZH(C_ALGO_CODE,C_FORMULA) values(?,?)";
					pstmt = conn.prepareStatement(iSql);
					pstmt.setString(1, advAlgo.getC_ALGO_CODE());
					pstmt.setClob(2, new StringReader(advAlgo.getC_ALGO_FORMULA_ZH()));
					pstmt.execute();
					
					String descSql = "insert into T_V_AA_ADV_ALGO_desc(C_ALGO_CODE,C_DESC) values(?,?)";
					pstmt = conn.prepareStatement(descSql);
					pstmt.setString(1, advAlgo.getC_ALGO_CODE());
					pstmt.setClob(2,new StringReader(null == advAlgo.getC_DESC() || advAlgo.getC_DESC().length()<=0 ? "" : advAlgo.getC_DESC()));
					pstmt.execute();
				}
				else{
					//无参考算法的情况
					String iSql = "insert into T_V_AA_ADV_ALGO_ZH(C_ALGO_CODE,C_FORMULA) values(?,EMPTY_CLOB())";
					pstmt = conn.prepareStatement(iSql);
					pstmt.setString(1, advAlgo.getC_ALGO_CODE());
//					pstmt.setClob(2, new StringReader(null == advAlgo.getC_ALGO_FORMULA_ZH() || advAlgo.getC_ALGO_FORMULA_ZH().length() <= 0 ? "EMPTY_CLOB()" : advAlgo.getC_ALGO_FORMULA_ZH()));
					pstmt.execute();
					
					String descSql = "insert into T_V_AA_ADV_ALGO_desc(C_ALGO_CODE,C_DESC) values(?,EMPTY_CLOB())";
					pstmt = conn.prepareStatement(descSql);
					pstmt.setString(1, advAlgo.getC_ALGO_CODE());
//					pstmt.setClob(2,new StringReader(null == advAlgo.getC_DESC() || advAlgo.getC_DESC().length() <= 0 ? "EMPTY_CLOB()" : advAlgo.getC_DESC()));
					pstmt.execute();
				}

				
				buf.setLength(0);
				buf.append("SELECT c_algo_formula FROM T_V_AA_ADV_ALGO ");
				buf.append(" WHERE C_IDEN = ?");
				stm = conn.prepareStatement(buf.toString());
				stm.setString(1, ciden);
				rs = stm.executeQuery();
				if (rs.next()) {
					Clob clob = rs.getClob("c_algo_formula");
					clob.setString(1, advAlgo.getC_ALGO_FORMULA());
					buf.setLength(0);
					buf.append("UPDATE T_V_AA_ADV_ALGO SET c_algo_formula = ? ");
					buf.append(" WHERE C_IDEN = ?");
					pstmt = conn.prepareStatement(buf.toString());
					pstmt.setClob(1, clob);
					pstmt.setString(2, ciden);
					pstmt.executeUpdate();
				}

			} catch (Exception ex) {
				logger.log("算法公式：插入算法公式失败", ex);
				throw new DataAccessException("插入算法公式失败：" + ex.getMessage(), ex);
			} finally {
				closeResultSetFinal(rs);
				closeStatementFinal(stm,pstmt);
				StringUtil.clearStringBuffer(buf);
			}

		} catch (Exception e) {
			throw new DataAccessException(e.getMessage(), e);
		}
		return ciden;
	}

	@Override
	public <T extends BasePojo> void updateById(T basePojo, Connection conn)
			throws DataAccessException {
		AdvAlgo advAlgo = (AdvAlgo) basePojo;
		StringBuffer buf = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			if (basePojo instanceof IEffectivable) {
				if (n_check == 0) {
                    autDateProc = new AutoDateProc(dbNameResolver, sqlbuilder, conn);
					autDateProc.inEffectiveDate((ParamPojo) basePojo);
					autDateProc.effectiveData((ParamPojo) basePojo);
				}
			}

			buf.append("UPDATE T_V_AA_ADV_ALGO SET ");
			buf.append("c_algo_code=?,");
			buf.append("c_algo_name=?,");
			buf.append("c_desc=?,");
			buf.append("n_check_state=?,");
			buf.append("C_UPDATE_BY=?,");
			buf.append("C_UPDATE_TIME=?,");
			buf.append("C_CHECK_BY=?,");
			buf.append("C_CHECK_TIME=?,");
			buf.append("c_algo_formula_transform = ?,");
			buf.append("c_dv_algo_type = ?,");
			buf.append("c_algo_formula= EMPTY_CLOB() WHERE C_IDEN=? ");
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, advAlgo.getC_ALGO_CODE());
			pstmt.setString(2, advAlgo.getC_ALGO_NAME());
			pstmt.setString(3, advAlgo.getC_DESC());
			pstmt.setInt(4, advAlgo.getAuditState());
			pstmt.setString(5, advAlgo.getModifier());
			pstmt.setString(6, advAlgo.getModifyDate());
			pstmt.setString(7, advAlgo.getOperator());
			pstmt.setString(8, advAlgo.getAuditDate());
			pstmt.setString(9, advAlgo.getC_ALGO_FORMULA_TRANSFORM());
			pstmt.setString(10, advAlgo.getC_DV_ALGO_TYPE());
			pstmt.setString(11, advAlgo.getId());
			pstmt.executeUpdate();
			
			DbFun.closeStatementFinal(pstmt);
			
			buf.setLength(0);
			buf.append("SELECT c_algo_formula FROM T_V_AA_ADV_ALGO ");
			buf.append(" WHERE C_IDEN = ?");
			pstmt = conn.prepareStatement(buf.toString());
			pstmt.setString(1, advAlgo.getId());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				Clob clob = rs.getClob("c_algo_formula");
				clob.setString(1, advAlgo.getC_ALGO_FORMULA());
				buf.setLength(0);
				buf.append("UPDATE T_V_AA_ADV_ALGO SET c_algo_formula = ? ");
				buf.append(" WHERE C_IDEN = ?");
				pstmt = conn.prepareStatement(buf.toString());
				pstmt.setClob(1, clob);
				pstmt.setString(2, advAlgo.getId());
				pstmt.executeUpdate();
			}

		} catch (Exception ex) {
			throw new DataAccessException("更新失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			StringUtil.clearStringBuffer(buf);
		}
	}

	public List<AdvAlgo> getAllDataTree_A() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<AdvAlgo> list = null;
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement("select a.c_iden, a.c_algo_code,a.n_check_state,a.c_algo_name,dv.C_DV_CODE,dv.C_DV_NAME from T_V_AA_ADV_ALGO a left join v_s_dv_voc dv on a.c_dv_algo_type=dv.C_DV_CODE order by a.n_check_state");
			rs = pstmt.executeQuery();
			list = new ArrayList<AdvAlgo>();
			Map<String,String> parentMap = new HashMap<String,String>();
			List<AdvAlgo> parentList = new ArrayList<AdvAlgo>();
			while (rs.next()) {
				AdvAlgo a1 = new AdvAlgo();
				String dvCode = rs.getString("C_DV_CODE");
				String dvName = rs.getString("c_dv_name");
				a1.setId(rs.getString("c_iden"));
				a1.setC_ALGO_CODE(rs.getString("c_algo_code"));
				a1.setC_ALGO_NAME(rs.getString("c_algo_name"));
				a1.setC_DV_ALGO_TYPE(dvCode);
				a1.setAuditState(rs.getInt("n_check_state"));
				if(!parentMap.containsKey(dvCode)){
					parentMap.put(dvCode, dvName);
					AdvAlgo a2 = new AdvAlgo();
					a2.setC_ALGO_CODE(dvCode);
					a2.setC_ALGO_NAME(dvName);
					a2.setC_DV_ALGO_TYPE("Root");
					a2.setAuditState(1);
					parentList.add(a2);
				}			
				
				list.add(a1);
			}
			
			if(parentList.size() > 0){
				list.addAll(parentList);
			}
		} catch (Exception e) {
			logger.log("算法公式：查询A区数据失败", e);
			throw new DataAccessException("查询A区数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * @author 马向峰
	 * @date 2017-07-19 根据参数的code获取控件信息
	 * @author 马向峰 20170715 STORY #31713 【产品优化】算法公式配置优化
	 * @param code
	 * @return
	 */
	public Map<String, String> getParasByCode(String code) {
		Map<String, String> result = new HashMap<String, String>();
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		try {
			conn = loadNewConnection();
			String sql = "select * from t_s_para p where p.c_para_code='"
					+ code + "'";
			rs = openResultSet(sql, conn);
			while (rs.next()) {
				ResultSetMetaData metaData = rs.getMetaData();
				for (int i = 0; i < metaData.getColumnCount(); i++) {
					String columnLabel = metaData.getColumnLabel(i + 1);
					result.put(columnLabel, rs.getString(columnLabel));
				}
			}
		} catch (Exception e) {
			logger.log("算法公式：查询参数数据失败", e);
			throw new DataAccessException("查询参数数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			this.releaseConnection(conn);
		}
		return result;
	}

	public List<AdvAlgo> getAlgos() {
		List<AdvAlgo> result = new ArrayList<AdvAlgo>();
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		try {
			conn = loadNewConnection();
			String sql = "select * from T_V_AA_ADV_ALGO a where a.n_check_state=1";
			rs = openResultSet(sql, conn);
			while (rs.next()) {
				AdvAlgo a = new AdvAlgo();
				a.setC_ALGO_CODE(rs.getString("C_ALGO_CODE"));
				a.setC_ALGO_NAME(rs.getString("C_ALGO_NAME"));
				a.setC_DESC(rs.getString("C_DESC"));
				a.setAuditState(1);
				
				result.add(a);
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法数据失败", e);
			throw new DataAccessException("查询算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			this.releaseConnection(conn);
		}
		return result;
	}

	public String getAlgoDesc(String code) {
		String result = "";
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select nvl(a.c_desc,p.c_desc) algodesc from T_V_AA_ADV_ALGO a left join T_V_AA_ADV_ALGO_DESC p on a.c_algo_code = p.c_algo_code where a.n_check_state = 1 and a.c_algo_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			rs.next();
			result = rs.getString("algodesc");
		} catch (Exception e) {
			logger.log("算法公式：查询算法数据失败", e);
			throw new DataAccessException("查询算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return result;
	}

	public AdvAlgo getAlgoByCode(String code) {
		
		AdvAlgo algo = null;
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			String result = isNewAlgo(code);
			conn = loadNewConnection();
			String sql = "";
//			if(result.equals("True")){
				sql = "select a.*, p.c_desc newalgodesc,a.c_desc oldalgodesc, zh.c_formula from T_V_AA_ADV_ALGO a  "
						+ "left join T_V_AA_ADV_ALGO_DESC p on a.c_algo_code = p.c_algo_code   "
						+ "left join T_V_AA_ADV_ALGO_ZH zh  on a.c_algo_code = zh.c_algo_code where "
						+ "a.c_algo_code = ? ";
//			}else{
//				sql = "select a.*, a.c_desc oldalgodesc, zh.c_formula from T_V_AA_ADV_ALGO a  "
//						+ "left join T_V_AA_ADV_ALGO_DESC p on a.c_algo_code = p.c_algo_code   "
//						+ "left join T_V_AA_ADV_ALGO_ZH zh  on a.c_algo_code = zh.c_algo_code where "
//						+ "a.c_algo_code = ? ";
//			}
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if(rs.next()){
				algo = new AdvAlgo();
				algo.setC_DV_ALGO_TYPE(rs.getString("C_DV_ALGO_TYPE"));
				algo.setC_ALGO_CODE(rs.getString("C_ALGO_CODE"));
				algo.setC_ALGO_NAME(rs.getString("C_ALGO_NAME"));//clobStrValue(rs.getClob("C_ALGO_FORMULA"));
				if (result.equals("True")){
					algo.setC_DESC(clobStrValue(rs.getClob("newalgodesc"))/*rs.getString("algodesc").equals("EMPTY_CLOB()") == true ? "" : rs.getString("algodesc")*/);
				}else{
					algo.setC_DESC(rs.getString("oldalgodesc"));
				}
				algo.setC_ALGO_FORMULA(clobStrValue(rs.getClob("C_ALGO_FORMULA"))/*rs.getString("C_ALGO_FORMULA").equals("EMPTY_CLOB()") == true ? "" : rs.getString("C_ALGO_FORMULA")*/);
				algo.setC_ALGO_FORMULA_ZH(clobStrValue(rs.getClob("C_FORMULA"))/*rs.getString("C_FORMULA").equals("EMPTY_CLOB()") == true ? "" : rs.getString("C_FORMULA")*/);
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法数据失败", e);
			throw new DataAccessException("查询算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return algo;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends BasePojo> void deleteById(List<T> pojoList)
			throws DataAccessException {
		List<AdvAlgo> list = (List<AdvAlgo>) pojoList;
		List<String> codes = new ArrayList<String>();
		
		//根据ID查出要删除算法的code，用于删除关联表信息
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = loadNewConnection();
			StringBuffer sb = new StringBuffer("select c_algo_code from T_V_AA_ADV_ALGO a where a.c_iden in (");
			for(int i=0;i<list.size();i++){
				sb.append("?");
				if(i != list.size()-1){
					sb.append(",");
				}
			}
			sb.append(")");
			ps = conn.prepareStatement(sb.toString());
			for(int i=1;i<=list.size();i++){
				ps.setString(i, list.get(i-1).getId());
			}
			rs = ps.executeQuery();
			
			while (rs.next()) {
				codes.add(rs.getString("c_algo_code"));
			}
			
			//删除算法表
			StringBuffer delAlgoSql = new StringBuffer("delete from T_V_AA_ADV_ALGO a where a.c_iden in (");
			
			for(int i=0;i<list.size();i++){
				delAlgoSql.append("?");
				if(i != list.size()-1){
					delAlgoSql.append(",");
				}
			}
			delAlgoSql.append(")");
			ps = conn.prepareStatement(delAlgoSql.toString());
			for(int i=1;i<=list.size();i++){
				ps.setString(i, list.get(i-1).getId());
			}
			ps.execute();
			
			//删除算法中文表
			deleteAlgo_ZH(codes,conn,ps);
			//删除算法描述关联表
			deleteAlgo_DESC(codes,conn,ps);
		} catch (Exception e) {
			logger.log("算法公式：删除算法数据失败", e);
			throw new DataAccessException("删除算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		
	}
	
	/**
	 * 删除算法中文
	 * @param list
	 * @param conn
	 * @param ps
	 */
	private void deleteAlgo_ZH(List<String> list ,Connection conn,PreparedStatement ps){
		try {
			StringBuffer sql = new StringBuffer("delete from T_V_AA_ADV_ALGO_ZH a where a.c_algo_code in (");
			for(int i=0;i<list.size();i++){
				sql.append("?");
				if(i != list.size()-1){
					sql.append(",");
				}
			}
			sql.append(")");
			ps = conn.prepareStatement(sql.toString());
			for(int i=1;i<=list.size();i++){
				ps.setString(i, list.get(i-1));
			}
			ps.execute();
		} catch (Exception e) {
			logger.log("算法公式：删除算法中文数据失败", e);
			throw new DataAccessException("删除算法中文数据失败！", e);
		}
		
	}

	/**
	 * 删除算法关联描述
	 * @param list
	 * @param conn
	 * @param ps
	 */
	private void deleteAlgo_DESC(List<String> list ,Connection conn,PreparedStatement ps){
		try {
			StringBuffer sql = new StringBuffer("delete from T_V_AA_ADV_ALGO_DESC a where a.c_algo_code in (");
			for(int i=0;i<list.size();i++){
				sql.append("?");
				if(i != list.size()-1){
					sql.append(",");
				}
			}
			sql.append(")");
			ps = conn.prepareStatement(sql.toString());
			for(int i=1;i<=list.size();i++){
				ps.setString(i, list.get(i-1));
			}
			ps.execute();
		} catch (Exception e) {
			logger.log("算法公式：删除关联算法数据失败", e);
			throw new DataAccessException("删除关联算法数据失败！", e);
		}
	}
	
	public String isNewAlgo(String code) {
		String result = "True";
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select * from T_V_AA_ADV_ALGO_ZH zh where zh.c_algo_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if(rs.next()){
				result = "True";
			}else{
				result = "False";
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法数据失败", e);
			throw new DataAccessException("查询算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return result;
	}
	
	/**
	 * 更新算法表，插入关联表
	 */
	@Override
	public <T extends BasePojo> void updateById(List<T> pojoList)
			throws DataAccessException {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdvAlgo algo = (AdvAlgo) pojoList.get(0);
		String algo_code = null;
		try {
			conn = loadNewConnection();
			String id = "";
			if((null == algo.getId() || algo.getId().length() <= 0) && null != algo.getC_ALGO_CODE() && algo.getC_ALGO_CODE().length() > 0){
				String id_sql = "select a.c_iden from T_V_AA_ADV_ALGO a where a.c_algo_code=?";
				ps = conn.prepareStatement(id_sql);
				ps.setString(1, algo.getC_ALGO_CODE());
				rs = ps.executeQuery();
				if(rs.next()){
					id = rs.getString("c_iden");
				}
			}
			
			String isNew = isNewAlgo(algo.getC_ALGO_CODE());
			if (null != algo.getC_ALGO_UPDATE_TARGET() && algo.getC_ALGO_UPDATE_TARGET().length() > 0 && "list".equals(algo.getC_ALGO_UPDATE_TARGET())){
				/**
				 * list界面修改
				 * isNewAlgo
				 */
				StringBuffer lsql = new StringBuffer("update T_V_AA_ADV_ALGO a set  a.c_algo_formula = ?, a.C_UPDATE_BY=? , a.C_UPDATE_TIME=? ");//where a.c_iden=?
				
				if("False".equals(isNew)){
					lsql.append(" ,a.c_desc='"+ algo.getC_DESC()+"'");
				}
				lsql.append(" where a.c_iden=?");
				ps = conn.prepareStatement(lsql.toString());
				ps.setClob(1, new StringReader(algo.getC_ALGO_FORMULA()));
				ps.setString(2, YssContextFactory.getInstance().getUserCode());
				ps.setString(3, DateUtil.dateToString(new java.util.Date(), "yyyy-MM-dd"));
				ps.setString(4, null == algo.getId() || algo.getId().length() <= 0 ? id : algo.getId());
				ps.execute();
				if("True".equals(isNew)){
					//更新辅助表  描述表
					String desc_sql = "update T_V_AA_ADV_ALGO_DESC d set d.c_desc=? where d.c_algo_code=?";
					ps = conn.prepareStatement(desc_sql);
					ps.setClob(1, new StringReader(algo.getC_DESC()));
					ps.setString(2, algo.getC_ALGO_CODE());
					ps.execute();
					
					//更新辅助表  算法中文表
					String zh_sql = "update T_V_AA_ADV_ALGO_ZH zh set zh.c_formula=?  where zh.c_algo_code=?";
					ps = conn.prepareStatement(zh_sql);
					ps.setClob(1, new StringReader(algo.getC_ALGO_FORMULA_ZH()));
					ps.setString(2, algo.getC_ALGO_CODE());
					ps.execute();
				}
			}else{
				/**
				 * set界面修改
				 */
				
				/**
				 * 修改算法：允许修改算法代码，先通过ID查出算法代码，进行保存，
				 * 然后T_V_AA_ADV_ALGO表根据ID进行修改，两个辅助表根据code进行修改
				 */
				String selSql = "select ao.c_algo_code from T_V_AA_ADV_ALGO ao where ao.c_iden=?";
				ps = conn.prepareStatement(selSql);
				ps.setString(1, algo.getId());
				rs = ps.executeQuery();
				if(rs.next()){
					algo_code = rs.getString("c_algo_code");
				}
				//修改算法表
				String s_algo_sql = "update T_V_AA_ADV_ALGO a set  a.C_ALGO_CODE = ?,a.C_ALGO_NAME=?,"
						+ "a.C_DV_ALGO_TYPE=?, a.C_UPDATE_BY=? , a.C_UPDATE_TIME=? where a.c_iden=?";
				ps = conn.prepareStatement(s_algo_sql);
				ps.setString(1, algo.getC_ALGO_CODE());
				ps.setString(2, algo.getC_ALGO_NAME());
				ps.setString(3, algo.getC_DV_ALGO_TYPE());
				ps.setString(4, YssContextFactory.getInstance().getUserCode());
				ps.setString(5, DateUtil.dateToString(new java.util.Date(), "yyyy-MM-dd"));
				ps.setString(6, algo.getId());
				ps.execute();
				if("True".equals(isNew)){
					//修改算法描述表
					String desc_sql = "update T_V_AA_ADV_ALGO_DESC d set d.c_algo_code=? where d.c_algo_code=?";
					ps = conn.prepareStatement(desc_sql);
					ps.setString(1, algo.getC_ALGO_CODE());
					ps.setString(2, algo_code);
					ps.execute();
					//修改算法中文表
					String zh_sql = "update T_V_AA_ADV_ALGO_ZH zh set zh.c_algo_code=?  where zh.c_algo_code=?";
					ps = conn.prepareStatement(zh_sql);
					ps.setString(1, algo.getC_ALGO_CODE());
					ps.setString(2, algo_code);
					ps.execute();
				}
				
			}
			
			
			
		} catch (Exception e) {
			logger.log("算法公式：更新算法数据失败", e);
			throw new DataAccessException("更新算法数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
	}
	
	public boolean isFromT_S_Param(String keyWord) {
		boolean result = false;
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select * from t_s_para p where p.c_para_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, keyWord);
			rs = ps.executeQuery();
			if(rs.next()){
				result = true;
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法参数数据失败", e);
			throw new DataAccessException("查询参数数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return result;
	}

	public Map<String, String> getT_S_PARAM_Data() {
		Map<String,String> result = new HashMap<String, String>();
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select p.c_para_code,p.c_dv_ctl_type from t_s_para p where p.c_dv_ctl_type='TEXTBOX'";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				result.put(rs.getString("c_para_code"), rs.getString("c_dv_ctl_type"));
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法参数数据失败", e);
			throw new DataAccessException("查询参数数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return result;
	}

	public String checkAlgoStatus(String code) {
		String result = "0";
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = " select a.n_check_state from T_V_AA_ADV_ALGO a where a.c_algo_code=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, code);
			rs = ps.executeQuery();
			if (rs.next()){
				result = rs.getString("n_check_state");
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法状态失败", e);
			throw new DataAccessException("查询算法状态失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		return result;
	}

	public List<ParamFromSql> getParamFromSql(String sql) {
		List<ParamFromSql> result = new ArrayList<ParamFromSql>();
		
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()){
				ParamFromSql paramFromSql = new ParamFromSql();
				//paramFromSql.setCode(rs.getString("c_index_code"));
				paramFromSql.setValue(rs.getString("code"));
				paramFromSql.setName(rs.getString("name"));
				result.add(paramFromSql);
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法参数数据失败", e);
			throw new DataAccessException("查询算法参数数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		
		return result;
	}

	public String getC_ALGO_FORMUL_ByCode(String c_ALGO_CODE) {
		String result = "";
		
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select a.C_ALGO_FORMULA from T_V_AA_ADV_ALGO a where a.C_ALGO_CODE=?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, c_ALGO_CODE);
			rs = ps.executeQuery();
			if(rs.next()){
				result = rs.getString("C_ALGO_FORMULA");
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法代码数据失败", e);
			throw new DataAccessException("查询算法代码数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		
		return result;
	}

	/**
	 * STORY #31713 【产品优化】算法公式配置优化  马向峰 20170908
	 * 重写基类查询方法，获取完整的算法信息
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <K extends BasePojo> List<K> queryByIds(String ids, Class<?> clazz) {
		List<K> klist = new ArrayList<K>();
		try {
			List<K> list = super.queryByIds(ids, clazz);
			if (null == list || list.size()<=0){
				return null;
			}
			
			for(K k : list){
				String id = k.getId();
				K pojo = (K) getAlgoById(id);
				klist.add(pojo);
			}
			
		} catch (Exception e) {
			logger.log("算法公式：查询算法代码数据失败", e);
			throw new DataAccessException("查询算法代码数据失败！", e);
		}
		
		return klist;
	}
	
	private BasePojo getAlgoById(String id){
		AdvAlgo advAlgo = new AdvAlgo();
		
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = loadNewConnection();
			String sql = "select a.c_algo_code,a.c_algo_name,a.n_check_state,a.c_update_by,a.c_update_time,"
					+ "a.c_check_by,a.c_check_time,a.c_algo_formula_transform, a.c_dv_algo_type,a.c_iden,"
					+ "a.c_algo_formula,zh.c_formula,ph.c_desc from T_V_AA_ADV_ALGO a left join t_v_Aa_Adv_Algo_Zh zh"
					+ " on a.c_algo_code = zh.c_algo_code left join T_V_AA_ADV_ALGO_DESC ph on ph.c_algo_code = a.c_algo_code  where a.c_iden = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			while(rs.next()){
				advAlgo.setId(id);
				advAlgo.setC_ALGO_CODE(rs.getString("c_algo_code"));
				advAlgo.setC_ALGO_NAME(rs.getString("c_algo_name"));
				advAlgo.setAuditState(rs.getInt("n_check_state"));
				advAlgo.setModifier(rs.getString("c_update_by"));
				advAlgo.setModifyDate(rs.getString("c_update_time"));
				advAlgo.setAuditDate(rs.getString("c_check_time"));
				advAlgo.setC_ALGO_FORMULA_TRANSFORM(rs.getString("c_algo_formula_transform"));
				advAlgo.setC_DV_ALGO_TYPE(rs.getString("c_dv_algo_type"));
				advAlgo.setC_ALGO_FORMULA(rs.getString("c_algo_formula"));
				advAlgo.setC_ALGO_FORMULA_ZH(rs.getString("c_formula"));
				advAlgo.setC_DESC(rs.getString("c_desc"));
			}
		} catch (Exception e) {
			logger.log("算法公式：查询算法代码数据失败", e);
			throw new DataAccessException("查询算法代码数据失败！", e);
		}finally{
			closeResultSetFinal(rs);
			closeStatementFinal(ps);
			this.releaseConnection(conn);
		}
		
		return advAlgo;
	}
	
	
	 /**
     * @Title getAllAchivmentAgio 
     * @Description 查询算法公式
     * @author lff
     * @date 2019年3月3日上午11:13:41
     * @return List<BasePojo>
     * @throws Exception 
     */
	public List<AdvAlgo> getAllAchivmentAlgo() throws Exception {
		logger.info("只查询了算法公式的部分字段！");
		Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "";
        List<AdvAlgo> resultList = new ArrayList<AdvAlgo>();
        try {
            sql = ((AdvAlgoSqlBuilder)sqlbuilder).getAllAchivmentAlgo();
            logger.info(sql);
            conn = this.loadNewConnection();
            preparedStatement = conn.prepareStatement(sql); 
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	AdvAlgo ag = new AdvAlgo();
				ag.setC_ALGO_CODE(rs.getString("C_ALGO_CODE"));
				ag.setC_ALGO_NAME(rs.getString("C_ALGO_NAME"));
				ag.setC_DESC(rs.getString("C_DESC"));
				ag.setAuditState(1);
				resultList.add(ag);
            }
        }
        catch (Exception e) {
        	logger.error("根据业绩报酬的id获取公式的参数值错误："+e.getMessage());
        	throw new Exception("根据业绩报酬的id获取公式的参数值错误！");
        }
        finally {
            DbFun.closeResultSetFinal(rs);
            DbFun.closeStatementFinal(preparedStatement);
            DbFun.releaseConnection(conn);
        }
    	return resultList;
	}
	
	/**
     * @Title getAllAchivmentAgio 
     * @Description 查询算法公式
     * @author lff
     * @date 2019年3月3日上午11:13:41
     * @return List<BasePojo>
     * @throws Exception 
     */
	public AdvAlgo getAchivmentAlgoByCode(String code) throws Exception {
		logger.info("只查询了算法公式的所有字段！");
		Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "";
        AdvAlgo ag = new AdvAlgo();
        try {
            sql = ((AdvAlgoSqlBuilder)sqlbuilder).getAchivmentAlgoByCode();
            logger.info(sql);
            conn = this.loadNewConnection();
            ps = conn.prepareStatement(sql); 
            ps.setString(1, code);
            rs = ps.executeQuery();
            while (rs.next()) {
				ag.setC_ALGO_CODE(rs.getString("C_ALGO_CODE"));
				ag.setC_ALGO_NAME(rs.getString("C_ALGO_NAME"));
				ag.setC_DESC(rs.getString("C_DESC"));
				ag.setId(rs.getString("C_IDEN"));
				ag.setAuditState(rs.getInt("N_CHECK_STATE"));
				ag.setModifier(rs.getString("C_UPDATE_BY"));
				ag.setModifyDate(rs.getString("C_UPDATE_time"));
				ag.setAuditDate(rs.getString("C_CHECK_TIME"));
				ag.setC_ALGO_FORMULA_TRANSFORM(rs.getString("C_ALGO_FORMULA_TRANSFORM"));
				ag.setC_DV_ALGO_TYPE(rs.getString("C_DV_ALGO_TYPE"));
				ag.setC_ALGO_FORMULA(rs.getClob("C_ALGO_FORMULA").getSubString(1,(int)rs.getClob("C_ALGO_FORMULA").length()));
            }
        }
        catch (Exception e) {
        	logger.error("根据业绩报酬的id获取公式的参数值错误："+e.getMessage());
        	throw new Exception("根据业绩报酬的id获取公式的参数值错误！");
        }
        finally {
            DbFun.closeResultSetFinal(rs);
            DbFun.closeStatementFinal(ps);
            DbFun.releaseConnection(conn);
        }
    	return ag;
	}
	
	/**
     * @Title getParamListByCode 
     * @Description 根据公式代码获取到相应的公式参数
     * @author lff
     * @date 2019年3月2日上午11:36:30
     * @param algoCode 公式代码
     * @return List<TaAchivmentPara>
     * @throws Exception 
     */
    public List<AdvAlgoPara> getParamListByCode(List<String> paramCodeList) throws Exception {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        String sql = "";
        List<AdvAlgoPara> paraList = new ArrayList<AdvAlgoPara>();
        try {
            sql = ((AdvAlgoSqlBuilder)sqlbuilder).getParamListByCode();
            logger.info(sql);
            conn = this.loadNewConnection();
            preparedStatement = conn.prepareStatement(sql); 
            preparedStatement.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(paramCodeList.toArray(new String[paramCodeList.size()]), conn));
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                AdvAlgoPara temp = new AdvAlgoPara();
                temp.setC_PARA_CODE(rs.getString("C_PARA_CODE"));
                temp.setC_PARA_NAME(rs.getString("C_PARA_NAME"));
                temp.setC_DV_PARA_TYPE(rs.getString("C_DV_PARA_TYPE"));
                temp.setC_DESC(rs.getString("C_DESC"));
                temp.setC_DV_CTL_TYPE(rs.getString("C_DV_CTL_TYPE"));
                temp.setC_DV_VALUE_TYPE(rs.getString("C_DV_VALUE_TYPE"));
                temp.setC_CTL_ATTR(rs.getString("C_CTL_ATTR"));
                temp.setC_DS_ATTR(rs.getString("C_DS_ATTR"));
                temp.setC_EXPAND_COND(rs.getString("C_EXPAND_COND"));
                paraList.add(temp);
            }
        }
        catch (Exception e) {
            logger.error("根据公式代码获取公式的参数错误：", e);
            throw new Exception("");
        }
        finally {
            DbFun.closeResultSetFinal(rs);
            DbFun.closeStatementFinal(preparedStatement);
            DbFun.releaseConnection(conn);
        }
        return paraList;
    }
}
