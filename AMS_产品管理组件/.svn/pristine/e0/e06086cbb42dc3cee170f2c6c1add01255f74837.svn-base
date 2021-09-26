package com.yss.ams.product.information.modules.ab.assetsTree_a.dao;

import java.beans.PropertyDescriptor;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetTreeATreeView;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo.AssetsTree_A;
import com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.pojo.AssetsTree_A_Rule;
import com.yss.framework.api.common.co.BaseBean;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.ParamPojo;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.MvcConstant;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.util.StringUtil;
import com.yss.framework.db.OraDbTool;
import com.yss.framework.util.DateUtil;

/**
 * <A区资产树型结构>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_ADao extends GeneralDao {
	private AssetsTree_ASqlBuilder assetTreeASqlBuilder;

	public AssetsTree_ADao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		// TODO Auto-generated constructor stub
		assetTreeASqlBuilder = (AssetsTree_ASqlBuilder) sqlBuilder;
	}

	public boolean quertListDate(HashMap<String, Object> paraMap) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		boolean booDtae = false;
		try {
			conn = this.loadNewConnection();
			sql = "SELECT COUNT(C_TR_CODE) AS C_TR_CODE FROM T_P_AB_ASS_TR_SUB A WHERE A.C_TR_CODE = ? ";
			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);
			pstmt.setString(1, paraMap.get("C_TR_CODE").toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				if (rs.getDouble("C_TR_CODE") > 0) {
					booDtae = true;
				}
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
			// try
			// {
			// if (conn != null)
			// {
			// conn.close();
			// }
			// }
			// catch (SQLException e)
			// {
			// e.printStackTrace();
			// }
		}
		return booDtae;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public <T> List<T> queryTreeViewData(HashMap<String, Object> paraMap, Class T) {
		List<T> pojoList = new ArrayList<T>();
		List<String> paraNameList;

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);
			sql = assetTreeASqlBuilder.getTreeViewQuerySql(paraNameList);

			pstmt = conn.prepareStatement(sql);
			logger.debug(sql);

			if (SqlUtil.isSearchTypeValueExists(paraNameList)) {
				paraNameList.remove(paraNameList.size() - 1);
			}

//			int index = 1;
//			for (String valueFieldName : paraNameList) {
//				if (valueFieldName.startsWith("ARRAY_")) {
//					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
//							.valueOf(paraMap.get(valueFieldName)),conn));
//				} else {
//					pstmt.setObject(index, paraMap.get(valueFieldName));
//				}
//
//				index++;
//			}
			pstmt.setString(1, AppContext.getInstance().getUserCode());
			rs = pstmt.executeQuery();

			conn.commit();
			conn.setAutoCommit(true);

			while (rs.next()) {
				AssetTreeATreeView t = new AssetTreeATreeView();

				PropertyDescriptor[] propertys = this.getPropertyDescriptors(t);

				for (int i = 0; i < propertys.length; i++) {
					PropertyDescriptor prop = propertys[i];
					if (prop.getPropertyType().isAssignableFrom(Class.class)) {
						continue;
					}

					if ("c_USER_PWD".equals(prop.getName())) {
						continue;
					}

					String name = prop.getName();
					String columnName = assetTreeASqlBuilder
							.getTreeViewColumnNameByProperty(dbNameResolver,
									name);
					
					//add by yangru STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
					if("assetsTreeARule".equals(name)){
						if(rs.getString("FLGZ_C_IDEN_RELA") != null){
							if(!StringUtil.IsNullOrEmpty(rs.getString("FLGZ_C_IDEN_RELA").trim())){
								AssetsTree_A_Rule assetsTreeARule = new AssetsTree_A_Rule();
								assetsTreeARule.setC_IDEN_RELA(rs.getString("FLGZ_C_IDEN_RELA"));
								assetsTreeARule.setC_CPSJWD(rs.getString("C_CPSJWD"));
								assetsTreeARule.setC_CPSJWD_FLCJ(rs.getString("C_CPSJWD_FLCJ"));
								assetsTreeARule.setC_ZCSXWD(rs.getString("C_ZCSXWD"));
								assetsTreeARule.setC_ZCSXWD_FLCJ(rs.getString("C_ZCSXWD_FLCJ"));
								t.setAssetsTreeARule(assetsTreeARule);
							}
						}
					}
					
					if ("".equals(columnName)) {
						continue;
					}
					Object resValue = rs.getObject(columnName);
					if (resValue != null) {
						if (resValue.getClass().equals(byte.class)
								|| resValue.getClass().equals(byte[].class)) {
							resValue = resValue.toString();
						} else if (resValue.getClass().equals(Timestamp.class)) {
							if (prop.getPropertyType().equals(String.class)) {
								Timestamp time = new Timestamp(DateUtil
										.stringtoDate(resValue.toString(),
												DateUtil.FORMAT_ONE).getTime());
								resValue = DateUtil.dateToString(time,
										"yyyy-MM-dd");
							}
							// YssFun.formatDate(resValue.toString());
						}

						if (int.class.equals(prop.getPropertyType())) {
							resValue = new BigDecimal(resValue.toString())
									.intValue();
						}

						prop.getWriteMethod().invoke(t, resValue);
					}

				}

				pojoList.add((T) t);
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
			// try
			// {
			// if (conn != null)
			// {
			// conn.close();
			// }
			// }
			// catch (SQLException e)
			// {
			// e.printStackTrace();
			// }
		}

		return pojoList;
	}

	/**
	 * 获取常用组合的组合代码list add by chenwenhai 20140528 产品群组需求
	 * 
	 * @param paraMap
	 * @return
	 */
	public List<String> getOftenUsePortList(HashMap<String, Object> paraMap) {
		List<String> portCodeList = new ArrayList<String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";
		List<String> paraNameList;
		try {
			paraNameList = getParaName(paraMap);
			conn = this.loadNewConnection();
			sql = assetTreeASqlBuilder.getOftenUsePortSql(paraNameList);
			pstmt = conn.prepareStatement(sql);
			int index = 1;
			for (String valueFieldName : paraNameList) {
				if (valueFieldName.startsWith("ARRAY_")) {
					pstmt.setArray(index, OraDbTool.newInstance().sqlOverLongCondition(String
							.valueOf(paraMap.get(valueFieldName)),conn));
				} else {
					pstmt.setObject(index, paraMap.get(valueFieldName));
				}

				index++;
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				portCodeList.add(rs.getString("C_TR_CODE"));
			}
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("资产属性结构A区：获取常用组合的组合代码出错", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return portCodeList;
	}

	/**
	 * 将产品从常用产品中删除 add by chenwenhai 20140528 产品群组需求
	 * 
	 * @param basePojoList
	 * @return
	 */
	public String deleteOftenUsePortByCode(List<BasePojo> basePojoList) {
		String returnInfo = "";
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = assetTreeASqlBuilder.getDelOftenUsePorSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			for (BasePojo pojo : basePojoList) {
				AssetsTree_A asset = (AssetsTree_A) pojo;
				pstmt.setString(1, asset.getC_TR_CODE());
				pstmt.setString(2, asset.getC_DV_TR());
				pstmt.addBatch();
				logger.log("testet");
			}
			pstmt.executeBatch();
			pstmt.clearBatch();//addbyleeyu20151015
			conn.commit();
			returnInfo = "sucess";
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("资产属性结构A区：将产品从常用产品中删除出错", e);
		}finally{
			this.closeStatementFinal(pstmt);//addbyleeyu20151015
			this.releaseConnection(conn);//addbyleeyu20151015
		}
		return returnInfo;
	}

	/**
	 * 将产品从常用产品中删除 add by chenwenhai 20140528 产品群组需求
	 * 
	 * @param basePojoList
	 * @return
	 */
	public String deleteOftenUsePortByCode(BasePojo pojo, Connection conn) {
		String returnInfo = "";
		PreparedStatement pstmt = null;
		String sql = assetTreeASqlBuilder.getDelOftenUsePorSql();
		try {
			pstmt = conn.prepareStatement(sql);
			AssetsTree_A asset = (AssetsTree_A) pojo;
			pstmt.setString(1, asset.getC_TR_CODE());
			pstmt.setString(2, asset.getC_DV_TR());
			pstmt.executeUpdate();
		} catch (Exception ex) {
			throw new DataAccessException("删除失败：" + ex.getMessage(), ex);
		} finally {
			this.closeStatementFinal(pstmt);
		}
		return returnInfo;
	}

	// /先删除后新增
	public <T extends BaseBean> List<String> deleteAndInsert(List<T> list)
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

				deleteOftenUsePortByCode((BasePojo) pojo, conn);
				String ciden = insert(pojo, conn);
				cidenList.add(ciden);
			}

			conn.commit();
			conn.setAutoCommit(true);

		} catch (Exception ex) {
			if (ex instanceof YssRuntimeException) {
				throw (DataAccessException) ex;
			} else {
				logger.log("保存失败：" + ex.getMessage());
				throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
			}

		} finally {
			this.releaseConnection(conn);
		}
		return cidenList;
	}

	/**
	 * code-name 转换方法
	 * @return
	 */
	public HashMap<String, String> getKeyConvertMap() {
		HashMap<String, String> map = new HashMap<String, String>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			String sql = sqlbuilder
					.getQueryConditionSql(new ArrayList<String>());
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				map.put(rs.getString("C_TR_CODE"), rs.getString("C_TR_NAME"));
			}
		} catch (Exception ex) {
			throw new DataAccessException("查询数据失败", ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}
		return map;
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-09-26
	 * Status : Add
	 * Comment: 获取轧差分组
	 * @return
	 */
	public List<AssetsTree_A> getNettingGroup(){
		List<AssetsTree_A> list = new ArrayList<AssetsTree_A>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			StringBuilder sqlBuilder = new StringBuilder();
			sqlBuilder.append(" select c_tr_code,c_tr_name from t_p_ab_ass_tr");
			sqlBuilder.append(" where c_tr_code in(");
			sqlBuilder.append(" select distinct c_tr_code from t_p_ab_ass_tr_sub a1");
			sqlBuilder.append(" where exists(");
			sqlBuilder.append(" select 1 from t_p_ab_port_rela a2");
			sqlBuilder.append(" where a1.c_port_code = a2.c_port_code and a2.c_dv_type_code = 'CONSIGNER'");
			sqlBuilder.append(" )");
			//添加产品树分类规则为委托层条件(AND C_DV_TR='TREE_TYPE_WTR') BUG #149118 【紧急】太平保险-增值税计提设置轧差分组显示错误 edit by dingxukun 20161230
			sqlBuilder.append(" ) and n_check_state = 1 AND C_DV_TR='TREE_TYPE_WTR' ");
			//sqlBuilder.append(" ) and n_check_state = 1");
			String sql = sqlBuilder.toString();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				AssetsTree_A assertTree = new AssetsTree_A();
				assertTree.setC_TR_CODE(rs.getString("c_tr_code"));
				assertTree.setC_TR_NAME(rs.getString("c_tr_name"));
				list.add(assertTree);
			}
		}catch(Exception ex){
			throw new BusinessException("查询轧差分组失败!",ex);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-03-13
	 * Status : Add
	 * Task	  : STORY49928产品树形结构界面优化
	 * Comment: 更新【产品树形结构】A区产品类型中的执行顺序
	 * @param conn		数据库连接
	 * @param rowOrder	待拖入行执行顺序
	 * @param trCode	结构代码
	 * @return
	 */
	public String updateAssOrder(Connection conn, String trCode){
		PreparedStatement pstmt = null;
		try{
			String sql = assetTreeASqlBuilder.getUpdateOrderSql();
			pstmt = conn.prepareStatement(sql);
			//获取下一个sequence值
			String order = getSequenceNextNumber(conn,"SEQU_P_AB_ASS_TR");
			pstmt.setDouble(1, Double.valueOf(order));
			pstmt.setString(2, trCode);
			pstmt.executeUpdate();
		}catch(Exception ex){
//			throw new BusinessException("更新产品类型执行顺序失败!" + ex.getMessage(), ex);
			logger.log("更新产品类型执行顺序失败!", ex);
		}finally{
			closeStatementFinal(pstmt);
		}
		
		return " ";
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-06-13
	 * Status : Add
	 * Task	  : BUG206147节点code不能与组合代码相同
	 * Comment: 根据顶级父节点删除对应所有子节点设置的组合信息
	 * @param conn		数据库连接
	 * @param topTrCode	最顶级父节点代码
	 * @return
	 */
	public void deleteAllRelaPortDataByTopParent(Connection conn, String topTrCode) throws Exception{
		PreparedStatement pstmt = null;
		try{
			String sql = "DELETE FROM T_P_AB_ASS_TR_SUB WHERE C_TR_CODE_R IN "
					+ " (SELECT C_TR_CODE_P FROM T_P_AB_ASS_TR WHERE C_TR_CODE_R = ?) ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, topTrCode);
			pstmt.executeUpdate();
		}catch(Exception ex){
			logger.log("执行根据顶级父节点删除对应所有子节点设置的组合信息失败!", ex);
			throw ex;
		}finally{
			closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * Author : zhoushuhang
	 * Date   : 2018-06-13
	 * Status : Add
	 * Task	  : BUG206147节点code不能与组合代码相同
	 * Comment: 根据顶级父节点删除对应所有子节点信息
	 * @param conn		数据库连接
	 * @param topTrCode	最顶级父节点代码
	 * @return
	 */
	public void deleteAllNodeByTopParent(Connection conn, String topTrCode) throws Exception{
		PreparedStatement pstmt = null;
		try{
			String sql = "DELETE FROM T_P_AB_ASS_TR WHERE C_TR_CODE_R = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, topTrCode);
			pstmt.executeUpdate();
		}catch(Exception ex){
			logger.log("根据顶级父节点删除对应所有子节点信息失败!", ex);
			throw ex;
		}finally{
			closeStatementFinal(pstmt);
		}
	}
	
	/**
	 * 返回产品树形结构设置的id
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 */
	public <T extends BaseBean> String insertReturnId(T baseBean)
			throws DataAccessException {
		Connection conn = null;
		String returnId = "";
		try {
			if (baseBean == null) {
				throw new InvalidParametersException("数据实例不能为空");
			}
			conn = this.loadNewConnection();
			conn.setAutoCommit(false);

			insert(baseBean, conn);
			returnId = getSequenceLastNumber(conn, getSequanceName(sqlbuilder
					.getTableName(dbNameResolver)));

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

		return returnId;
	}
}
