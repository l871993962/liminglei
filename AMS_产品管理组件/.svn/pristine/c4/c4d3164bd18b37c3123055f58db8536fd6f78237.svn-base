package com.yss.ams.product.information.modules.pg.portgrouprela.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.pg.portgrouprela.pojo.PortGroupRela;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.mvc.biz.BusinessException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.mvc.dao.sql.SqlUtil;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.db.OraDbTool;
import com.yss.right.constants.RightConstants;

/**
 * <产品群组管理>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupRelaDao extends GeneralDao{
	
	private PortGroupRelaSqlBuilder sqlBuilder;

	public PortGroupRelaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		this.sqlBuilder = (PortGroupRelaSqlBuilder)sqlBuilder;
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
		try {
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
				PortGroupRela portGroup = new PortGroupRela();
				portGroup.setId(rs.getString("C_IDEN"));
				portGroup.setC_GROUP_CODE(rs.getString("C_GROUP_CODE"));
				portGroup.setC_GROUP_NAME(rs.getString("C_GROUP_NAME"));
				portGroup.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
//				portGroup.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				portGroup.setAuditDate(rs.getString("C_CHECK_TIME"));
				portGroup.setAuditState(rs.getInt("N_CHECK_STATE"));
				portGroup.setOperator(rs.getString("C_CHECK_BY"));
				portGroup.setModifyDate(rs.getString("C_UPDATE_TIME"));
				portGroup.setModifier(rs.getString("C_UPDATE_BY"));
				pojoList.add(portGroup);
			}

		} catch (Exception ex) {
//			ex.printStackTrace();
			logger.log("产品群组模块：查询群组关联组合信息失败", ex);
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs);
			closeStatementFinal(pstmt);
			releaseConnection(conn);
		}

		return pojoList;
	}
	
    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
    //分布式不再支持旧权限体系，使用FAST新权限体系
//	/**
//	 * 根据群组代码查询所有的组合代码
//	 * @param c_group_code
//	 * @return
//	 */
//	public List<String> querySelectedPortCodeList(String c_group_code){
//		List<String> selectPortList = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = " SELECT * FROM T_P_AB_GROUP_RELA R "
//				+ "JOIN t_s_user_rela A ON A.C_PORT_CODE = R.C_PORT_CODE  AND A.C_USER_CODE = ?  WHERE R.C_GROUP_CODE = ? ";
//		try {
//			conn = this.loadNewConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getUserCode());
//			pstmt.setString(2, c_group_code);
//			rs = pstmt.executeQuery();
//			selectPortList = new ArrayList<String>();
//			while(rs.next()){
//				selectPortList.add(rs.getString("C_PORT_CODE"));
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			logger.log("产品群组模块：根据群组代码查询关联组合失败", e);
//		}finally{
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//		return selectPortList;
//	}
	
	/**
	 * 根据群组代码查询所有的可以选择的组合代码
	 * @param c_group_code
	 * @return
	 */
	public List<String> querySelectPortCodeList(String c_group_code){
		List<String> selectPortList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT C_PORT_CODE FROM T_P_AB_GROUP_RELA WHERE C_GROUP_CODE = ?";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_group_code);
			rs = pstmt.executeQuery();
			selectPortList = new ArrayList<String>();
			while(rs.next()){
				selectPortList.add(rs.getString("C_PORT_CODE"));
			}
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码查询所有的可以选择的组合代码失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return selectPortList;
	}
    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
    //分布式不再支持旧权限体系，使用FAST新权限体系
//	public List<BasePojo> querySelectedPortList(String c_group_code){
//		List<BasePojo> selectPortList = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		// STORY #44974 文件接口导出时设置产品组合分组 by huangjunxiong 2017-09-20
//		// 当岗位代码为多个时a.c_post_code = 就查询不到数据，故改成in
//		String sql = "SELECT * FROM T_P_AB_PORT P  LEFT JOIN T_P_AB_GROUP_RELA R ON P.C_PORT_CODE = R.C_PORT_CODE "
//				+ "  JOIN t_s_user_rela A ON A.C_PORT_CODE = R.C_PORT_CODE  AND A.C_USER_CODE = ? and a.c_post_code in (SELECT * FROM TABLE(?)) and R.N_CHECK_STATE = 1 WHERE R.C_GROUP_CODE = ?";
//		try {
//			conn = this.loadNewConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getLogInfo().getLoggingUserCode());
//			String postCode = YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getLogInfo().getLoggingPostCode();
//			if(postCode == null || postCode.equals("")){
//				//Logger logger = LogManager.getLogger(getClass());
//				logger.error("岗代码为空。");
//			}
//			pstmt.setArray(2, OraDbTool.newInstance()
//					.sqlOverLongCondition(postCode, conn));
//			pstmt.setString(3, c_group_code);
//			rs = pstmt.executeQuery();
//			selectPortList = new ArrayList<BasePojo>();
//			while(rs.next()){
//				Port p = new Port();
//				p.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
//				p.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
//				p.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
//				p.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
//				p.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
//				p.setAuditState(rs.getInt("N_CHECK_STATE"));
//				p.setAuditDate(rs.getString("C_CHECK_TIME"));
//				p.setOperator(rs.getString("C_CHECK_BY"));
//				p.setId(rs.getString("C_IDEN"));
//				p.setD_CLEAR(new java.util.Date());
//				if(null == p.getC_PORT_CODE_P() || "".equals(p.getC_PORT_CODE_P().trim())){
//					p.setC_PORT_CODE_P("[root]");
//				}
//				selectPortList.add(p);
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			logger.log("产品群组模块：根据群组代码查询关联组合失败", e);
//		}finally{
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//		return selectPortList;
//	}
	
	/**
	 * 根据群组代码删除所有群组记录
	 * @param c_group_code
	 */
	public void deleteByGroupCode(String c_group_code){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from T_P_AB_GROUP_RELA r where r.c_group_code = ? ";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_group_code);
			pstmt.execute();
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码删除所有群组记录失败", e);
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
	/**
	 * 根据群组代码和组合代码删除记录
	 * @param c_group_code
	 */
	public void deleteByGroupPortCode(String c_group_code, List<String> c_port_code_list){
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = "delete from T_P_AB_GROUP_RELA r where r.c_group_code = ? and r.c_port_code = ? ";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			for(String c_port_code : c_port_code_list){
				pstmt.setString(1, c_group_code);
				pstmt.setString(2, c_port_code);
				pstmt.addBatch();
			}
			pstmt.executeBatch();
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码和组合代码删除记录失败", e);
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}
	
    //STORY #69753 业务基础组件对FAST平台表结构的解耦 
    //分布式不再支持旧权限体系，使用FAST新权限体系
//	/**
//	 * 根据权限获取当前用户的组合
//	 * @param c_group_code
//	 * @return
//	 */
//	public List<Port> queryPortList(){
//		List<Port> selectPortList = null;
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = "SELECT *  FROM T_P_AB_PORT P  JOIN t_s_user_rela A    ON A.C_PORT_CODE = p.C_PORT_CODE   AND A.C_USER_CODE = ? and a.n_check_state = 1 ";
//		try {
//			conn = this.loadNewConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getUserCode());
//			rs = pstmt.executeQuery();
//			selectPortList = new ArrayList<Port>();
//			while(rs.next()){
//				Port p = new Port();
//				p.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
//				p.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
//				p.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
//				////BUG #143833 产品群组管理新增
//				p.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
//				p.setC_DAT_CLS(rs.getString("C_DAT_CLS"));
//				p.setAuditState(rs.getInt("N_CHECK_STATE"));
//				p.setAuditDate(rs.getString("C_CHECK_TIME"));
//				p.setOperator(rs.getString("C_CHECK_BY"));
//				p.setId(rs.getString("C_IDEN"));
//				p.setD_CLEAR(new java.util.Date());
//				if(null == p.getC_PORT_CODE_P() || "".equals(p.getC_PORT_CODE_P().trim())){
//					p.setC_PORT_CODE_P("[root]");
//				}
//				selectPortList.add(p);
//			}
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
////			e.printStackTrace();
//			logger.log("产品群组模块：根据权限获取当前用户的组合失败", e);
//		}finally{
//			this.closeResultSetFinal(rs);
//			this.closeStatementFinal(pstmt);
//			this.releaseConnection(conn);
//		}
//		return selectPortList;
//	}

	public void deleteByPortCodes(String[] portCodes) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = sqlBuilder.getDeleteByPortCodesSql();
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			
			StringBuffer appStr = new StringBuffer();
			for (String string : portCodes) {
				appStr.append(string).append(",");
			}
			appStr.deleteCharAt(appStr.lastIndexOf(","));
			
			pstmt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(appStr.toString(),conn));
			pstmt.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
//			e.printStackTrace();
			logger.log("产品群组模块：根据条件删除组合信息失败", e);
			
		}finally{
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
	}

	/**
	 * Author : ChenLong
	 * Date   : 2016-01-22
	 * Status : Add
	 * Comment: 根据群组代码获取组合信息
	 * @param groupCode
	 * @return
	 */
	public Map<String,Port> getPortByGroupCode(String groupCode){
		Map<String,Port> map = new HashMap<String,Port>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			conn = this.loadNewConnection();
			StringBuffer sqlBuff = new StringBuffer();
			sqlBuff.append(" select c_port_code, c_port_name");
			sqlBuff.append(" from t_p_ab_port p");
			sqlBuff.append(" where exists (select 1");
			sqlBuff.append(" from t_p_ab_group_rela pgr");
			sqlBuff.append(" where c_group_code = ?");
			sqlBuff.append(" and p.c_port_code = pgr.c_port_code)");
			pstmt = conn.prepareStatement(sqlBuff.toString());
			pstmt.setString(1, groupCode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				String code = rs.getString("c_port_code");
				String name = rs.getString("c_port_name");
				Port port = new Port();
				port.setC_PORT_CODE(code);
				port.setC_PORT_NAME(name);
				map.put(code, port);
			}
		}catch(Exception ex){
			throw new BusinessException("根据组合群组查询组合信息失败!" + ex.getMessage());
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return map;
	}
	
	/**
	 * BUG #173916 A区产品群组未实现新老权限兼容
	 * 新权限体系过滤组合
	 * @param c_group_code
	 * @return
	 */
	public List<String> querySelectedPortCodeListNewRihgt(String c_group_code){
		List<String> list = querySelectPortCodeList(c_group_code);
		//权限过滤后的组合列表
		List<String> rightLists = new ArrayList<String>();
		
		try {
			//当前用户
			String userCode =  YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getUserCode();
			//根据当前用户获取用户下的组合（已根据权限过滤）
			IFASTDataAuthorityService authorService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
			List<String> userPortCodes = authorService.queryByUser(RightConstants.portType, userCode);
			if(null != userPortCodes && null != list){
				for(String code : list){
					if(userPortCodes.contains(code)){
						rightLists.add(code);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log("querySelectedPortCodeListNewRihgt->", e);
		}
		
		return rightLists;
	}
	
	/**
	 * BUG #173916 A区产品群组未实现新老权限兼容 begin
	 * 获取所有的组合，根据权限过滤放到了service中
	 * @param c_group_code
	 * @return
	 */
	public List<Port> queryAllPortList(){
		List<Port> selectPortList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "SELECT *  FROM T_P_AB_PORT P ";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			selectPortList = new ArrayList<Port>();
			while(rs.next()){
				Port p = new Port();
				p.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				p.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				p.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
				////BUG #143833 产品群组管理新增
				p.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
				p.setC_DAT_CLS(rs.getString("C_DAT_CLS"));
				p.setAuditState(rs.getInt("N_CHECK_STATE"));
				p.setAuditDate(rs.getString("C_CHECK_TIME"));
				p.setOperator(rs.getString("C_CHECK_BY"));
				p.setId(rs.getString("C_IDEN"));
				p.setD_CLEAR(new java.util.Date());
				if(null == p.getC_PORT_CODE_P() || "".equals(p.getC_PORT_CODE_P().trim())){
					p.setC_PORT_CODE_P("[root]");
				}
				selectPortList.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：根据权限获取当前用户的组合失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return selectPortList;
	}
	
	/**
	 * BUG #173916 A区产品群组未实现新老权限兼容
	 * 新权限体系过滤组合
	 * @param c_group_code
	 * @return
	 */
	public List<BasePojo> querySelectedPortListNewRihgt(String c_group_code){
		List<BasePojo> list = querySelectedPortListWithoutRight(c_group_code);
		//权限过滤后的组合列表
		List<BasePojo> rightLists = new ArrayList<BasePojo>();
		
		try {
			//当前用户
			String userCode =  YssContextFactory.getInstance().getAppContext(ProductInfoActivator.class).getUserCode();
			//根据当前用户获取用户下的组合（已根据权限过滤）
			IFASTDataAuthorityService authorService = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
			List<String> userPortCodes = authorService.queryByUser(RightConstants.portType, userCode);
			if(null != userPortCodes && null != list){
				for(BasePojo pojo : list){
					Port p = (Port)pojo;
					if(userPortCodes.contains(p.getC_PORT_CODE())){
						rightLists.add(pojo);
					}
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			logger.log("querySelectedPortListNewRihgt->", e);
		}
		
		return rightLists;
	}
	
	/**
	 * BUG #173916 A区产品群组未实现新老权限兼容
	 * 根据群组获取所有组合
	 * @param c_group_code
	 * @return
	 */
	public List<BasePojo> querySelectedPortListWithoutRight(String c_group_code){
		List<BasePojo> selectPortList = null;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "SELECT * FROM T_P_AB_PORT P  LEFT JOIN T_P_AB_GROUP_RELA R ON P.C_PORT_CODE = R.C_PORT_CODE "
				+ " and R.N_CHECK_STATE = 1 WHERE R.C_GROUP_CODE = ?";
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, c_group_code);
			rs = pstmt.executeQuery();
			selectPortList = new ArrayList<BasePojo>();
			while(rs.next()){
				Port p = new Port();
				p.setC_PORT_CODE(rs.getString("C_PORT_CODE"));
				p.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
				p.setC_PORT_NAME_EN(rs.getString("C_PORT_NAME_EN"));
				p.setC_PORT_NAME_ST(rs.getString("C_PORT_NAME_ST"));
				p.setC_PORT_CODE_P(rs.getString("C_PORT_CODE_P"));
				p.setAuditState(rs.getInt("N_CHECK_STATE"));
				p.setAuditDate(rs.getString("C_CHECK_TIME"));
				p.setOperator(rs.getString("C_CHECK_BY"));
				p.setId(rs.getString("C_IDEN"));
				p.setD_CLEAR(new java.util.Date());
				if(null == p.getC_PORT_CODE_P() || "".equals(p.getC_PORT_CODE_P().trim())){
					p.setC_PORT_CODE_P("[root]");
				}
				selectPortList.add(p);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码查询关联组合失败", e);
		}finally{
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return selectPortList;
	}
	/**
	 * 根据组合代码查询组合所属的群组
	 * @param portCode
	 * @return
	 */
	public List<String> queryPortGroupByPortCode(String portCode) {
		List<String> portGroup = new ArrayList<String>();
		PreparedStatement pstmt = null;
		Connection conn = null;
		ResultSet rs = null;
		
		String sql = "select a.c_group_code from T_P_AB_GROUP_RELA a where a.c_port_code = ? and a.n_check_state = 1";
		
		try {
			conn = this.loadNewConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, portCode);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				portGroup.add(rs.getString("C_GROUP_CODE"));
			}
		} catch (Exception e) {
			logger.log("产品群组模块：根据组合代码查询关联群组代码失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		return portGroup;
	}

	/**
	 * 组合通过临时表处理
	 * @param portCodes
	 */
	private void insertPort(Set<String> lstPort, Connection conn){
		String sql = "INSERT INTO R_D_CLR_PORT VALUES (?) ";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for(String port : lstPort){
				pst.setString(1, port);
				pst.addBatch();
			}
			conn.setAutoCommit(false);
			pst.executeBatch();
			pst.clearBatch();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("插入产品组合失败!"+e.getMessage());
		} finally{
			this.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 组合通过临时表处理
	 * @param portCodes
	 */
	private void insertPortbyList(List<String> lstPort, Connection conn){
		String sql = "INSERT INTO R_D_CLR_PORT VALUES (?) ";
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql);
			for(String port : lstPort){
				pst.setString(1, port);
				pst.addBatch();
			}
			conn.setAutoCommit(false);
			pst.executeBatch();
			pst.clearBatch();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			logger.error("插入产品组合失败!"+e.getMessage());
		} finally{
			this.closeStatementFinal(pst);
		}
	}
	
	/**
	 * 
	 * 根据组合代码集合查询组合所属的群组
	 * @param portCodeList
	 * @return
	 */
	public Map<String, List<String>> queryGroupCodeByPortCodeList(HashMap<String, String> portCodeList) {
		Map<String, List<String>> groupCodeList = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select A.c_port_code,a.c_group_code from T_P_AB_GROUP_RELA a where a.n_check_state = 1 and a.c_port_code in  (select * from R_D_CLR_PORT)";			
		try {
			conn = this.loadNewConnection();	
			conn.setAutoCommit(false);
			//
			this.insertPort(portCodeList.keySet(), conn);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (!groupCodeList.containsKey(rs.getString("c_port_code"))) {
					List<String> groupCode=new ArrayList<String>();
					groupCode.add(rs.getString("C_GROUP_CODE"));	
					groupCodeList.put(rs.getString("c_port_code"),groupCode);
				}else{
					List<String> groupCode = groupCodeList.get(rs.getString("c_port_code"));
					groupCode.add(rs.getString("C_GROUP_CODE"));
				}													
			}
			conn.setAutoCommit(true);				
			conn.commit();
		} catch (Exception e) {
			logger.log("产品群组模块：根据组合代码查询关联群组代码失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return groupCodeList;
	}
	
	/**
	 * STORY #103420 产品组件提供批量查找群组的方法
	 * 根据组合代码集合查询组合所属的群组
	 * @param portCodeList
	 * @return
	 */
	public Map<String, List<String>> queryGroupCodeByPortCodeList(List<String> portCodeList) {
		Map<String, List<String>> groupCodeList = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select A.c_port_code,a.c_group_code from T_P_AB_GROUP_RELA a  join R_D_CLR_PORT r on a.c_port_code = r.c_port_code where a.n_check_state = 1 ";			
		try {
			conn = this.loadNewConnection();	
			conn.setAutoCommit(false);
			this.insertPortbyList(portCodeList, conn);
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (!groupCodeList.containsKey(rs.getString("c_port_code"))) {
					List<String> groupCode=new ArrayList<String>();
					groupCode.add(rs.getString("C_GROUP_CODE"));	
					groupCodeList.put(rs.getString("c_port_code"),groupCode);
				}else{
					List<String> groupCode = groupCodeList.get(rs.getString("c_port_code"));
					groupCode.add(rs.getString("C_GROUP_CODE"));
				}													
			}
			conn.setAutoCommit(true);				
			conn.commit();
		} catch (Exception e) {
			logger.log("产品群组模块：根据组合代码查询关联群组代码失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return groupCodeList;
	}
	
	
	/**
	 * STORY #103420 产品组件提供批量查找群组的方法
	 * 根据所有组合代码集合查询组合所属的群组
	 * @param portCodeList
	 * @return
	 */
	public Map<String, List<String>> queryAllGroupCode() {
		Map<String, List<String>> groupCodeList = new HashMap<String, List<String>>();
		PreparedStatement pst = null;
		Connection conn = null;
		ResultSet rs = null;
		String sql = "select A.c_port_code,a.c_group_code from T_P_AB_GROUP_RELA a where a.n_check_state = 1 ";			
		try {
			conn = this.loadNewConnection();	
			//conn.setAutoCommit(false);
			//
			pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
			while (rs.next()) {
				if (!groupCodeList.containsKey(rs.getString("c_port_code"))) {
					List<String> groupCode=new ArrayList<String>();
					groupCode.add(rs.getString("C_GROUP_CODE"));	
					groupCodeList.put(rs.getString("c_port_code"),groupCode);
				}else{
					List<String> groupCode = groupCodeList.get(rs.getString("c_port_code"));
					groupCode.add(rs.getString("C_GROUP_CODE"));
				}													
			}
			//conn.setAutoCommit(true);				
			//conn.commit();
		} catch (Exception e) {
			logger.log("产品群组模块：根据组合代码查询关联群组代码失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pst);
			this.releaseConnection(conn);
		}
		return groupCodeList;
	}
}