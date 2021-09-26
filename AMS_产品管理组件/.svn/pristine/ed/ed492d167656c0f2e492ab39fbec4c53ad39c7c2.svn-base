package com.yss.ams.product.information.modules.aa.portcustom.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.context.AppContext;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.db.OraDbTool;

/**
 * <用户自定义组合>DAO层
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustomDao extends GeneralDao {

	private PortCustomSqlBuilder dsServiceBuilder = null;

	public PortCustomDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
		dsServiceBuilder = (PortCustomSqlBuilder) sqlBuilder;
	}

	// public String getPortCode(String code) throws ServiceException {
	// Connection conn = null;
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// String sql = "";
	//
	// String t = null;
	// String[] paramArr = null;
	// try {
	// paramArr = code.split("-");
	//
	// conn = this.loadNewConnection();
	//
	// sql = dsServiceBuilder.getPortCodeSql();
	//
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setString(1, paramArr[0]);
	// pstmt.setString(2, paramArr[1]);
	// pstmt.setString(3, paramArr[2]);
	//
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// t = rs.getString(PortCustomColumnName.c_PORT_CODE.toString());
	// }
	//
	// } catch (Exception ex) {
	// throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
	// } finally {
	// closeResultSetFinal(rs);
	// closeStatementFinal(pstmt);
	// releaseConnection(conn);
	// }
	// return t;
	// }
	//
	// public String getPortCodeByMenuid(String code) throws ServiceException {
	// Connection conn = null;
	// PreparedStatement pstmt = null;
	// ResultSet rs = null;
	//
	// String sql = "";
	//
	// String t = null;
	// String[] paramArr = null;
	// try {
	// paramArr = code.split("-");
	//
	// conn = this.loadNewConnection();
	//
	// sql = dsServiceBuilder.getPortCodeByMenuidSql();
	//
	// pstmt = conn.prepareStatement(sql);
	// pstmt.setString(1, paramArr[0]);
	// pstmt.setString(2, paramArr[1]);
	// pstmt.setString(3, paramArr[2]);
	// pstmt.setString(4, paramArr[3]);
	//
	// rs = pstmt.executeQuery();
	//
	// while (rs.next()) {
	// t = rs.getString(PortCustomColumnName.c_PORT_CODE.toString());
	// }
	//
	// } catch (Exception ex) {
	// throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
	// } finally {
	// closeResultSetFinal(rs);
	// closeStatementFinal(pstmt);
	// releaseConnection(conn);
	// }
	// return t;
	// }

	// public ArrayList<String> getPortCodeByArray(ArrayList<String> keyArray)
	// throws ServiceException {
	//
	// ArrayList<String> list = new ArrayList<String>();
	//
	// for (String string : keyArray) {
	// String s = this.getPortCode(string);
	// list.add(null == s ? "" : s);
	// }
	//
	// return list;
	// }

	public String getShowType(HashMap<String, String> codeMap) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		String sql = "";

		String t = null;
		try {

			conn = this.loadNewConnection();

			sql = dsServiceBuilder.getPortCodesSql();

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, codeMap.get("C_POST_CODE"));
			pstmt.setString(2, codeMap.get("C_USER_CODE"));
			pstmt.setString(3, codeMap.get("C_FUN_CODE"));

			logger.debug(sql);

			rs = pstmt.executeQuery();

			while (rs.next()) {
				t = rs.getString("C_TR_CODE_R");
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

	public String getShowType(HashMap<String, String> codeMap, Connection conn)
			throws Exception {
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		ResultSet rs2 = null;

		String sql = "";
		PortCustomSqlBuilder dsServiceBuilder = null;
		String t = "";
		try {
			dsServiceBuilder = new PortCustomSqlBuilder();

//			sql = dsServiceBuilder.getPortCodesSql();
			sql = dsServiceBuilder.getShowTypeSql();
			
			pstmt = conn.prepareStatement(sql);
			/*
			 * Author : ChenLong
			 * Date   : 2015-01-22
			 * Status : Add
			 * Comment: 单岗位改成多岗位时  资产池不再需要依据岗位 故这里注销岗位
			 * */
//			pstmt.setString(1, codeMap.get("C_POST_CODE"));
			pstmt.setString(1, codeMap.get("C_USER_CODE"));
			pstmt.setString(2, codeMap.get("C_FUN_CODE"));

			logger.debug(sql);

			rs = pstmt.executeQuery();
			if( rs.next() )
			{
				t = rs.getString("C_TR_CODE_R");
			}else
			{
				if( "ddfasz".equals(codeMap.get("C_FUN_CODE")) )
				{
					String sql1 = "SELECT count(*),C_TR_CODE_R FROM T_P_AB_PORT_CUSTOM  WHERE  C_USER_CODE = ? group by C_TR_CODE_R order by count(*) desc";
					pstmt2 = conn.prepareStatement(sql1);
					pstmt2.setString(1, codeMap.get("C_USER_CODE"));
					rs2 = pstmt2.executeQuery();
					if(rs2.next())
					{
						t = rs2.getString("C_TR_CODE_R");
					}
					String sql2 = "insert into T_P_AB_PORT_CUSTOM values(SEQU_P_AB_PORT_CUSTOM.nextval,?,' ',' ','ddfasz',?)";
					int index = 1 ;
					pstmt3 = conn.prepareStatement(sql2);
					pstmt3.setString(index++, codeMap.get("C_USER_CODE"));
					pstmt3.setString(index++, t);
					pstmt3.executeUpdate();
					conn.commit();
				}
			}

		} catch (Exception ex) {
			throw new DataAccessException("查询失败：" + ex.getMessage(), ex);
		} finally {
			closeResultSetFinal(rs, rs2);
			closeStatementFinal(pstmt);
			closeStatementFinal(pstmt2, pstmt3);
		}
		return t;
	}

	public boolean deleteCustomPort(HashMap<String, String> param)
			throws ServiceException {
		boolean bTrans = false; // 代表是否开始了事务
		PreparedStatement delPst = null;// 定义预处理对象
		Connection conn = null; // 获取数据库连接
		String strDel = "";// 删除数据
		try {
			conn = loadNewConnection();
			conn.setAutoCommit(bTrans);
			String C_POST_CODE = param.get("C_POST_CODE");
			String C_USER_CODE = param.get("C_USER_CODE");
			String C_FUN_CODE = param.get("C_FUN_CODE");
			boolean funCodeTag = Boolean.parseBoolean(param.get("TYPE"));
			bTrans = true;
			if (!funCodeTag) {
				strDel = "delete from t_p_ab_port_custom where C_POST_CODE = ? and C_USER_CODE = ? and (C_FUN_CODE = ' ' or C_FUN_CODE = ?)";
			} else {
				strDel = "delete from t_p_ab_port_custom where C_POST_CODE = ? and C_USER_CODE = ? and C_FUN_CODE = ?";
			}
			delPst = openPreparedStatement(strDel, conn);
			delPst.setString(1, C_POST_CODE);
			delPst.setString(2, C_USER_CODE);
			delPst.setString(3, C_FUN_CODE);
			delPst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
			return true;
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			endTransFinal(conn, bTrans);
			closeStatementFinal(delPst);
			releaseConnection(conn);
		}
	}

	public boolean insertCustomPort(List<PortCustom> list, String type)
			throws ServiceException {
		boolean bTrans = false; // 代表是否开始了事务
		PreparedStatement pst = null;// 定义预处理对象
		Connection conn = null; // 获取数据库连接
		String strDel = "";// 删除数据
		String strIns = "";
		String strIns1 = "";
		boolean bType = Boolean.parseBoolean(type);
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			if (list.size() > 0) {
				//liuxiang 2015-10-17 BUG #120500 海通4.5系统，A区显示逻辑需要修改
				//原按岗位加载、默认选中组合功能不考虑，后续再实现（表C_PORT_CODE,C_POST_CODE不再保存数据）
				//当A区界面选择“本功能专用”则默认保存一条记录；当不选择本功能专用，则插入所有功能菜单的数据，使查询的逻辑简单化。
				/*String postCodes = list.get(0).getC_POST_CODE();
				String[] postCodeArray = postCodes.split(",");*/
				
				if (!bType) {
					strDel = "delete from t_p_ab_port_custom where C_USER_CODE = ? ";
				} else {
					strDel = "delete from t_p_ab_port_custom where C_USER_CODE = ? and C_FUN_CODE = ?";
				}
				pst = conn.prepareStatement(strDel);
				int index = 1;
				//pst.setArray(1, sqlOverLongCondition(postCodeArray));
				pst.setString(index++, list.get(0).getC_USER_CODE());
				if(bType) {
					pst.setString(index++, list.get(0).getC_FUN_CODE());
				}
				
				pst.executeUpdate();
				closeStatementFinal(pst);
				if(bType){
					strIns = "INSERT INTO T_P_AB_PORT_CUSTOM(C_USER_CODE,C_PORT_CODE,C_POST_CODE,C_FUN_CODE,C_TR_CODE_R,C_IDEN)"
							+ " VALUES(?,' ',' ',?,?,Sequ_p_Ab_Port_Custom.Nextval)";
				} else {
					strIns = "INSERT INTO T_P_AB_PORT_CUSTOM(C_USER_CODE,C_PORT_CODE,C_POST_CODE,C_FUN_CODE,C_TR_CODE_R,C_IDEN)"
							+ " SELECT ?,' ',' ',C_FUN_CODE,?,Sequ_p_Ab_Port_Custom.Nextval FROM V_S_FUN fun WHERE fun.c_dv_fun_type='MEU_FUN' "
							+ "  ";
				}
				
				pst = conn.prepareStatement(strIns);
				
				/*for(String postCode : postCodeArray){
					for (PortCustom cus : list) {
						pst.setString(1, cus.getC_USER_CODE());
						pst.setString(2, cus.getC_PORT_CODE());
						pst.setString(3, postCode);
						pst.setString(4, !bType ? " " : cus.getC_FUN_CODE());
						pst.setString(5, cus.getC_TR_CODE_R());
						pst.executeUpdate();
					}
				}*/	
				
				index = 1;
				pst.setString(index++, list.get(0).getC_USER_CODE());
				if(bType){
					pst.setString(index++, list.get(0).getC_FUN_CODE());
				}
				pst.setString(index++, list.get(0).getC_TR_CODE_R());
				
				pst.executeUpdate();
				if(!bType)
				{
					closeStatementFinal(pst);
					strIns1 = "INSERT INTO T_P_AB_PORT_CUSTOM(C_USER_CODE,C_PORT_CODE,C_POST_CODE,C_FUN_CODE,C_TR_CODE_R,C_IDEN)"
							+ " values(?, ' ', ' ', 'ddfasz', ?, Sequ_p_Ab_Port_Custom.Nextval) ";
					pst = conn.prepareStatement(strIns1);
					pst.setString(1, list.get(0).getC_USER_CODE());
					pst.setString(2, list.get(0).getC_TR_CODE_R());
					pst.executeUpdate();
				}
				conn.commit();
				conn.setAutoCommit(bTrans);
				bTrans = false;
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			endTransFinal(conn, bTrans);
			closeStatementFinal(pst);
			releaseConnection(conn);
		}

		return true;
	}

	public ArrayList<String> getAssetType() throws ServiceException {
		String strSql = null;// 定义查询语句字符串
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		PreparedStatement pst = null;
		//java.sql.Statement pst = null; //addbyleeyu20151015
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			strSql = dsServiceBuilder.getAssSql();
			//rs = openResultSet(strSql, conn);//byleeyu20151015
			//pst = conn.createStatement(); //byleeyu20151015
			//BUG #162973 by liulei
			pst = conn.prepareStatement(strSql);
			pst.setString(1, AppContext.getInstance().getUserCode());
			rs = pst.executeQuery();//byleeyu20151015
			list.add("ASS\t资产类型");
			list.add("ZCTG\t资产类型-托管行");
			list.add("TGZC\t托管行-资产类型");
			while (rs.next()) {
				list.add(rs.getString("C_TR_CODE") + "\t"
						+ rs.getString("C_TR_NAME"));
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			closeResultSetFinal(rs);
			this.closeStatementFinal(pst);//byleeyu20151015
			releaseConnection(conn);
		}
		return list;
	}


	public ArrayList<String> getAssetTypeOnlyCode() throws ServiceException {
		String strSql = null;// 定义查询语句字符串
		ResultSet rs = null;// 定义结果集
		Connection conn = null;
		//java.sql.Statement pst = null; //addbyleeyu20151015
		PreparedStatement pst = null;
		ArrayList<String> list = new ArrayList<String>();
		try {
			conn = this.loadNewConnection();
			strSql = dsServiceBuilder.getAssOnlyCodeSql();
			pst = conn.prepareStatement(strSql);
			rs = pst.executeQuery(strSql);//byleeyu20151015
			list.add("ASS");
			list.add("ZCTG");
			//add by xyh 2018-1-22
			list.add("ZCGL");
			list.add("TGZC");
			//add by chenyoucai 20180120 STORY #51993 财务报表优化-区分不同管理人出具纳税报表
			list.add("GLZC");
			//add by xyh 2018-1-23
			list.add("NSPL");
			list.add("ZCZL");
			list.add("ZCWT");
			list.add("TAQS");
			list.add("CPZT");
			while (rs.next()) {
				list.add(rs.getString("C_TR_CODE"));
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			closeResultSetFinal(rs);
			this.closeStatementFinal(pst);//byleeyu20151015
			releaseConnection(conn);
		}
		return list;
	}
	
	public ArrayList<String> getUserDefaultPort(HashMap<String, String> paradict)
			throws ServiceException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = "";

		ArrayList<String> lstRet = new ArrayList<String>(); // 返回数据
		// String ereryKey = ""; // 每个键值
		// String everyRes = "";// 每个对象的数据
		// ArrayList<String> keyList = new ArrayList<String>();
		String c_FunCode = paradict.get("C_FUN_CODE");
		String c_PostCode = paradict.get("C_POST_CODE");
		String c_UserCode = paradict.get("C_USER_CODE");
		String c_DataRightStr = paradict.get("DataRightString");
		String c_tr_code_r = paradict.get("C_TR_CODE_R");
		boolean para = true;
		if (c_tr_code_r == null || c_tr_code_r.isEmpty()) {
			para = false;
		}
		try {
			conn = this.loadNewConnection();
			sql = dsServiceBuilder.getPortCodeByMenuidSql(para);
			pstmt = conn.prepareStatement(sql);
			int i = 1;
			pstmt.setArray(i++, OraDbTool.newInstance().sqlOverLongCondition(c_DataRightStr,conn));
			pstmt.setString(i++, c_UserCode);
			pstmt.setString(i++, c_PostCode);
			pstmt.setString(i++, c_FunCode);
			if (para) {
				pstmt.setString(i++, c_tr_code_r);
			}
			pstmt.setArray(i++, OraDbTool.newInstance().sqlOverLongCondition(c_DataRightStr,conn));
			pstmt.setString(i++, c_UserCode);
			pstmt.setString(i++, c_PostCode);
			if (para) {
				pstmt.setString(i++, c_tr_code_r);
			}
			rs = pstmt.executeQuery();
			while (rs.next()) {
				String portCode = rs.getString(PortCustomColumnName.c_PORT_CODE
						.toString());
				lstRet.add(portCode == null ? "" : portCode);
			}
		} catch (Exception ex) {
			throw new ServiceException(ex);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pstmt);
			this.releaseConnection(conn);
		}
		// String menuKey = c_UserCode + "-" + c_PostCode + "-" + c_FunCode;
		// String lastKey = c_UserCode + "-" + c_PostCode;
		// 循环取出缓存中的通过菜单获取缓存中的数据
		//
		// if (c_DataRightStr.length() > 0) {
		// String[] portData = c_DataRightStr.split(",");
		//
		// for (String string : portData) {
		// // 获取每个数据对应没有菜单的键值
		// keyList.add(string + "-" + lastKey);
		// ereryKey = string + "-" + menuKey;// 拼接键值
		// everyRes = getPortCodeByMenuid(ereryKey); // 获取对应的vaule值
		// // 判断当前是否有值
		// if (everyRes != null && everyRes.length() > 0) {
		// lstRet.add(getPortCodeByMenuid(ereryKey));
		// }
		// }
		// }

		// // 获取到相关的数据
		// if (lstRet.size() > 0) {
		// return lstRet; // 去掉多余的数据
		// } else {
		// return getPortCodeByArray(keyList);// 获取没有菜单的默认数据
		// }
		return lstRet;
	}
}
