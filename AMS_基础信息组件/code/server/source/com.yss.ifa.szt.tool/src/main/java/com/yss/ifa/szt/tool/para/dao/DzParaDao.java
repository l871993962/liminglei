package com.yss.ifa.szt.tool.para.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.YssRuntimeException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.ifa.szt.tool.para.service.IErParaService;
import com.yss.ifa.szt.tool.pojo.DzPara;
import com.yss.ifa.szt.tool.pojo.TransPojo;

public class DzParaDao extends GeneralDao {

	public DzParaDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}

	public List<TransPojo> getInitMrApiData() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<TransPojo> list = new ArrayList<TransPojo>();
		TransPojo pojo;
		try {
			IErParaService erParaService = YssServiceFactory.getInstance().createService(IErParaService.class);
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((DzParaSqlBuilder) sqlbuilder).getInitSql());
			rs = stat.executeQuery();
			while (rs.next()) {
				pojo = new TransPojo();
				pojo.setFromApp(rs.getString("C_SRC_APP_LOGO"));
				pojo.setFromUser(rs.getString("C_TARGET_USER_LOGO"));//源用户  wlx 20160907 STORY34149【广发证券】【紧急】申请修改电子对账支持多用户的需求
				pojo.setPkgPassword(rs.getString("C_PKG_PASSWORD"));
				pojo.setToApp(rs.getString("C_TARGET_APP_LOGO"));
				pojo.setToUser(rs.getString("C_TARGET_USER"));
				pojo.setMrInfoList(erParaService.queryMrInfos(rs.getString("C_PARA_CODE")));
				//STORY60297【富国基金】支持电子对账参数设置支持民生银行多管理人不同秘钥配置
				pojo.setCharSet(rs.getString("C_DV_CHARSET"));
				pojo.setSecretKey(rs.getString("C_SECRETKEY"));
				pojo.setSecretType(rs.getString("C_DV_SECRETTYPE"));
				list.add(pojo);
			}
		} catch (Exception e) {
			throw new YssRuntimeException(e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return list;
	}

//	/**
//	 * 删除机构同时删除电子对账参数设置信息
//	 * @param orgCode
//	 * @return
//	 */
//	public  List<BasePojo> getElecParaPojo(String orgCode) {
//		List<BasePojo> list = new ArrayList<BasePojo>();
//		Connection conn = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//		DzPara pojo = null;
//		StringBuffer bufSql = new StringBuffer();
//		ResultSetTools tool = new ResultSetTools(dbNameResolver,new DzParaSqlBuilder());
//		try{
//			conn = loadNewConnection();
//			bufSql.append(" select t.* ");
//			bufSql.append(" from T_D_ER_RELA t ");
//			bufSql.append(" where t.C_TGH_CODE = ? ");
//			String sql = bufSql.toString();
//			ps = conn.prepareStatement(sql);
//			ps.setString(1, orgCode);
//			rs = ps.executeQuery();
//			while(rs.next()){				
//				pojo = tool.ResultToBeanGeneric(rs, DzPara.class);
//				setDzParaPojo(pojo, rs);
//				list.add(pojo);
//			}
//		}catch(Exception ex){
//			throw new DataAccessException(ex);
//		}finally{
//			closeResultSetFinal(rs);
//			closeStatementFinal(ps);
//			releaseConnection(conn);
//		}
//		return list;
//	}
	
	/**
	 * /// STORY42660【中国银行】深证通伺服器要求采用热备模式
	* @Title: setDzParaPojo 
	* @Description: 获取深圳通参数信息
	* @param @param pojo
	* @param @param rs
	* @param @throws SQLException    设定文件 
	* @return void    返回类型 
	* @throws
	 */
	private void setDzParaPojo(DzPara pojo, ResultSet rs) throws SQLException{
		pojo.setC_CERT_ID(rs.getString("C_CERT_ID")); //证书ID
		pojo.setC_DEPT_CODE(rs.getString("C_DEPT_CODE"));//公司代码
		pojo.setC_PKG_PASSWORD(rs.getString("C_PKG_PASSWORD"));//发送密码
		pojo.setC_SRC_APP_LOGO(rs.getString("C_SRC_APPID"));//源应用
		pojo.setC_SRC_USER(rs.getString("C_SRC_USERID"));//源用户
		IErParaService erParaService = YssServiceFactory.getInstance().createService(IErParaService.class);
		pojo.setMrInfoList(erParaService.queryMrInfos(rs.getString("C_PARA_CODE")));
	}

	/**
	 * 根据资产代码查询电子对账参数设置-资托互动
	 * add by chenyoucai  2018-9-13 STORY #30828 资管和托管互动
	 * @return
	 */
	public List<DzPara> getParaByAssCode(String assCode) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		List<DzPara> list = new ArrayList<DzPara>();
		DzPara pojo;
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((DzParaSqlBuilder) sqlbuilder).getParaByAssCodeSql());
			stat.setString(1, assCode);
			rs = stat.executeQuery();
			while (rs.next()) {
				pojo = new DzPara();
				pojo.setC_ASS_CODE(rs.getString("C_ASS_CODE"));
				pojo.setC_BUS_TYPE(rs.getString("C_BUS_TYPE"));
				pojo.setC_COMM_TYPE(rs.getString("C_COMM_TYPE")); 
				pojo.setC_TARGET_APP_LOGO(rs.getString("C_TARGET_APP_LOGO"));
				pojo.setC_TARGET_USER(rs.getString("C_TARGET_USER"));
				pojo.setC_TGH_CODE(rs.getString("C_TGH_CODE"));
				pojo.setC_TGH_NAME(rs.getString("C_TGH_NAME"));
				pojo.setId(rs.getString("C_IDEN"));
				setDzParaPojo(pojo, rs);
				list.add(pojo);
			}
		} catch (Exception e) {
			logger.error("获取电子对账参数信息出错", e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
			
		}
		return list;
	}

	/**
	 * 获取电子对账参数设置业务类型为电子对账的数量
	 * @return
	 */
	public int getUseCount() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		int count = 0;
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((DzParaSqlBuilder) sqlbuilder).getUseCountSql());
			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getInt(1);
			}
		} catch (Exception e) {
			logger.error("获取电子对账参数数量出错", e);
			throw new DataAccessException(e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return count;
	}

	/**
	 * @param busType 业务类型
	 * @param licOrg 许可码
	 * @param id 数据ID
	 * @return 该许可码是否已经被使用
	 */
	public boolean isUsedLicOrg(String busType, String licOrg, String id) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((DzParaSqlBuilder) sqlbuilder).getIsUsedLicOrgSql());
			stat.setString(1, busType);
			stat.setString(2, licOrg);
			rs = stat.executeQuery();
			if (rs.next()) {
				if(!StringUtil.IsNullOrEmptyT(id) && id.equalsIgnoreCase(rs.getString(1)))//是修改，不是新增
				{
					return false;
				}
				return true;
			}
		} catch (Exception e) {
			logger.error("获取电子对账参数数量出错", e);
			throw new DataAccessException(e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return false;
	}

	/**
	 * 根据ID获取参数的业务类型
	 * @param id
	 * @return
	 */
	public String getBusiTypeById(String id) {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet rs = null;
		try {
			conn = this.loadNewConnection();
			stat = conn.prepareStatement(((DzParaSqlBuilder) sqlbuilder).getBusiTypeByIdSql());
			stat.setString(1, id);
			rs = stat.executeQuery();
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			logger.error("获取电子对账参数对账类型出错", e);
			throw new DataAccessException(e);
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(stat);
			this.releaseConnection(conn);
		}
		return "";
	}
}
