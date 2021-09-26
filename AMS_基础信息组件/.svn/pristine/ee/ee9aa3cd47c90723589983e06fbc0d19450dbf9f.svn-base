package com.yss.ams.base.information.modules.bi.org.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.yss.framework.api.commonInfo.pojo.FastPortOrg;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.db.OraDbTool;

/**
 * STORY #69702 华宝兴业：会计大屏只看关注的组合进度 (#2 #1 ) 
 * @author lenovo
 *
 */
public class FastPortOrgDaoImpl extends GeneralDao {

	public FastPortOrgDaoImpl(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	
	/**
	 * 实现查询组合托管行信息功能
	 * @param ports
	 * @return
	 */
	public List<FastPortOrg> queryTrusteeByPorts(List<String> ports) {
		List<FastPortOrg> list = new ArrayList<FastPortOrg>();
		if (ports.isEmpty()) {
			return list;
		}
		// 获取组合信息
		StringBuffer buf = new StringBuffer();
		for (String portCode : ports) {
			buf.append(portCode).append(",");
		}
		if (buf.length() > 0) {
			buf.deleteCharAt(buf.length() - 1);
		}
		Connection conn = null;
		PreparedStatement pt = null;
		ResultSet rs = null;
		StringBuffer sql = new StringBuffer();
		sql.append(" select a.c_port_code, b.c_org_code, b.c_org_name, b.c_org_name_st from t_p_ab_port_rela a ");
		sql.append(" left join t_p_bi_org b on b.c_org_code = a.c_rela_code ");
		sql.append(" where a.c_rela_type = 'RELA_ORG' and a.c_dv_type_code = 'TRUSTEE' and a.c_port_code in (select * from table(?)) ");
		try {
			conn = this.loadNewConnection();
			pt = conn.prepareStatement(sql.toString());
			pt.setArray(1, OraDbTool.newInstance().sqlOverLongCondition(buf.toString(), conn));
			rs = pt.executeQuery();
			while (rs.next()) {
				FastPortOrg fastPortOrg = new FastPortOrg();
				fastPortOrg.setPortCode(rs.getString("C_PORT_CODE"));
				fastPortOrg.setOrgCode(rs.getString("C_ORG_CODE"));
				fastPortOrg.setOrgFullName(rs.getString("C_ORG_NAME"));
				fastPortOrg.setOrgName(rs.getString("C_ORG_NAME_ST"));
				fastPortOrg.setContactInfo(getContactInfo(fastPortOrg.getOrgCode()));
				list.add(fastPortOrg);
			}
		} catch (Exception e) {
			logger.info("queryTrusteeByPorts失败", e);
		} finally {
			this.closeResultSetFinal(rs);
			this.closeStatementFinal(pt);
			this.releaseConnection(conn);
		}
		return list;
	}
	
	/**
	 * STORY #81023 【业务驾驶舱】增加快捷查看组合对应托管人联系方式功能 (#2 #1 )
	 * 根据机构代码获取联系人信息
	 * @param orgCode
	 * @return
	 */
	private List<String> getContactInfo(String orgCode) {
		List<String> contactInfo = new ArrayList<String>();
		if (orgCode != null && !"".equals(orgCode)) {
			PreparedStatement pt = null;
			Connection conn = null;
			ResultSet rs = null;
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT * FROM ( ");
			sql.append(" SELECT A.C_LINK_MAN, '' C_POST_NAME, A.C_LINK_TEL, A.C_MO_TEL, A.C_EMAIL, 0 N_ORDER ");
			sql.append(" FROM T_P_BI_ORG A WHERE A.C_ORG_CODE = ? ");
//			sql.append(" AND NOT EXISTS (SELECT 1 FROM T_P_BI_ORG_LINK_RELA B WHERE B.C_ORG_CODE = A.C_ORG_CODE) ");
			sql.append(" UNION ");
			sql.append(" SELECT A.C_LINK_MAN, A.C_POST_NAME, A.C_LINK_TEL, A.C_MO_TEL, A.C_EMAIL, A.N_ORDER ");
			sql.append(" FROM T_P_BI_ORG_LINK_RELA A WHERE A.C_ORG_CODE = ? ");
			sql.append(" ) T ORDER BY T.N_ORDER ");
			try {
				conn = this.loadNewConnection();
				pt = conn.prepareStatement(sql.toString());
				pt.setString(1, orgCode);
				pt.setString(2, orgCode);
				rs = pt.executeQuery();
				while (rs.next()) {
					StringBuffer contactBuf = new StringBuffer();
					String linkMan = rs.getString("C_LINK_MAN");
					String postName = rs.getString("C_POST_NAME");
					String linkTel = rs.getString("C_LINK_TEL");
					String moTel = rs.getString("C_MO_TEL");
					String email = rs.getString("C_EMAIL");
					contactBuf.append(linkMan == null ? "" : linkMan);
					if (postName != null && !"".equals(postName)) {
						contactBuf.append("（").append(postName).append("）");
					}
					contactBuf.append("：");
					if (linkTel != null && !"".equals(linkTel)) {
						contactBuf.append(linkTel);
					}
					else if (moTel != null && !"".equals(moTel)) {
						contactBuf.append(moTel);
					}
					else if (email != null && !"".equals(email)) {
						contactBuf.append(email);
					}
					String contactStr = contactBuf.toString().trim();
					if ("：".equals(contactStr)) {
						continue;
					}
					contactInfo.add(contactStr);
				}
				if (contactInfo.isEmpty()) {
					contactInfo.add("【关联机构设置】模块未维护联系人信息");
				}
			} catch (Exception e) {
				logger.info("getContactInfo失败", e);
			} finally {
				this.closeResultSetFinal(rs);
				this.closeStatementFinal(pt);
				this.releaseConnection(conn);
			}
		}
		return contactInfo;
	}

}
