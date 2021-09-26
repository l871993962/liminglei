package com.yss.uco.elecreco.er.generate.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.yss.dayf.actProvider.statistic.asset.pojo.Asset;
import com.yss.framework.api.database.DbFun;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.mvc.dao.sql.DefaultDBNameResolver;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.bi.elecrela.dao.ElecRelaSqlBuilder;
import com.yss.uco.elecreco.support.bean.ElecRela;

public class ElecRelaManager {
	
	private Map<String, List<ElecRela>> map = new HashMap<String, List<ElecRela>>();
	
	private Map<String,Map<String,ElecRela>> portClsMap = new HashMap<String, Map<String,ElecRela>>();
	
	private String orgCode = "";
	
	private Map<String, Set<String>> clsInfos = new HashMap<String, Set<String>>();
	
	public ElecRelaManager()
	{
	}
	
	private void initOrgCode(Connection conn,String portCode) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = " select C_RELA_CODE from T_P_AB_PORT_RELA where c_rela_Type = 'RELA_ORG' and C_DV_TYPE_CODE = 'TRUSTEE' and c_port_code = ? and N_CHECK_STATE = 1 ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			if (rs.next()) {
				this.orgCode = rs.getString("C_RELA_CODE");
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}

	public void initDblGz(Connection conn,String portCode) throws Exception {
		initOrgCode(conn, portCode);
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = "select A.* from T_Z_BI_RELA A where (trim(C_ORG_CODE) is null or C_ORG_CODE = ?) and A.C_DZ_CODE = ? and N_CHECK_STATE = 1 ";
		try {
			initPortClsInfo(conn, portCode);
			pst = conn.prepareStatement(sql);
			pst.setString(1, this.orgCode);
			pst.setString(2, "1013");
			rs = pst.executeQuery();
			ResultSetTools rsTools = new ResultSetTools(new DefaultDBNameResolver(), new ElecRelaSqlBuilder());
			while (rs.next()) {
				ElecRela rela = (ElecRela) rsTools.ResultToPojoObject(rs, ElecRela.class);
				String cls_Code = rela.getC_PORT_CODE_CLS();
				String zb_Code = rela.getC_DV_ZB_CODE();
				Set<String> set = getClsInfo(rela);
				if(set.size() == 0 && StringUtil.IsNullOrEmptyT(cls_Code))//非分级组合指标
				{
					if(StringUtil.IsNullOrEmptyT(rela.getC_ORG_CODE()))
					{
						if(!this.map.containsKey(zb_Code))
						{
							ArrayList<ElecRela> list = new ArrayList<ElecRela>();
							list.add(rela);
							this.map.put(zb_Code, list);
						}else
						{
							if(StringUtil.IsNullOrEmptyT(this.map.get(zb_Code).get(0).getC_ORG_CODE()))
							{
								this.map.get(zb_Code).add(rela);
							}
						}
					}else if(this.orgCode.equalsIgnoreCase(rela.getC_ORG_CODE()))
					{
						if(!this.map.containsKey(zb_Code))
						{
							ArrayList<ElecRela> list = new ArrayList<ElecRela>();
							list.add(rela);
							this.map.put(zb_Code, list);
						}else
						{
							if(StringUtil.IsNullOrEmptyT(this.map.get(zb_Code).get(0).getC_ORG_CODE()))
							{
								this.map.get(zb_Code).clear();
								this.map.get(zb_Code).add(rela);
							}
						}
					}
				}else if(clsInfos.size() > 0)//分级组合指标且有分级组合
				{
					for (String clsCode : clsInfos.keySet()) {
						Set<String> clsInfo = clsInfos.get(clsCode);
						Map<String, ElecRela> clsMap = this.portClsMap.get(clsCode);
						if(StringUtil.IsNullOrEmptyT(cls_Code))//无分级组合代码，需要匹配三个分级参数，有值参与判断
						{
							if(!clsInfo.containsAll(set))//不适用该分级组合
							{
								continue;
							}
							if(clsMap.containsKey(zb_Code))
							{
								if(StringUtil.IsNullOrEmptyT(clsMap.get(zb_Code).getC_PORT_CODE_CLS()) && set.size() > getClsInfo(clsMap.get(zb_Code)).size())
								{
									clsMap.put(zb_Code, rela);
								}
							}else{
								clsMap.put(zb_Code, rela);
							}
						}else if(clsCode.equalsIgnoreCase(cls_Code))//有分级组合，只要分级代码一致就可以
						{
							this.portClsMap.get(clsCode).put(rela.getC_DV_ZB_CODE(), rela);
						}
						
					}
				}
				
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}
	
	private Set<String> getClsInfo(ElecRela rela)
	{
		Set<String> relaSet = new HashSet<String>();
		String port_CLS = rela.getC_DV_PORT_CLS();
		String cls_LEVEL = rela.getC_DV_PORT_CLS_LEVEL();
		String cls_TYPE = rela.getC_DV_PORT_CLS_TYPE();
		put(relaSet, "C_DV_PORT_CLS_TYPE", cls_TYPE);
		put(relaSet, "C_DV_PORT_CLS", port_CLS);
		put(relaSet, "C_DV_PORT_CLS_LEVEL", cls_LEVEL);
		return relaSet;
	}
	
	private void put(Set<String> set,String key,String value)
	{
		if(!StringUtil.IsNullOrEmptyT(value))
		{
			set.add(key + ":" + value);
		}
	}
	
	public List<ElecRela> getDblGzElecRela(Asset asset)
	{
		ArrayList<ElecRela> list = new ArrayList<ElecRela>();
		String code = asset.getC_KEY_CODE();
		if(asset.getC_KM_CODE().contains("_"))//分级组合
		{
			String clsCode = asset.getC_KM_CODE().substring(asset.getC_KM_CODE().indexOf("_")+1);
			if(this.portClsMap.get(clsCode).get(code) == null)
			{
				return null;
			}
			list.add(this.portClsMap.get(clsCode).get(code));
			return list;
		}else//非分级
		{
			return this.map.get(code);
		}
	}
	
	private void initPortClsInfo(Connection conn,String portCode) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String sql = " select C_PORT_CLS_CODE, C_DV_PORT_CLS_TYPE, C_DV_PORT_CLS, C_DV_PORT_CLS_LEVEL from t_p_Aa_Port_Cls where C_PORT_CODE = ? ";
		try {
			pst = conn.prepareStatement(sql);
			pst.setString(1, portCode);
			rs = pst.executeQuery();
			while (rs.next()) {
				Set<String> set = new HashSet<String>();
				String code = rs.getString("C_PORT_CLS_CODE");
				set.add("C_PORT_CLS_CODE" + ":" + code);
				set.add("C_DV_PORT_CLS_TYPE" + ":" + rs.getString("C_DV_PORT_CLS_TYPE"));
				set.add("C_DV_PORT_CLS" + ":" + rs.getString("C_DV_PORT_CLS"));
				set.add("C_DV_PORT_CLS_LEVEL" + ":" + rs.getString("C_DV_PORT_CLS_LEVEL"));
				this.portClsMap.put(code, new HashMap<String, ElecRela>());
				clsInfos.put(code, set);
			}
		} catch (Exception e) {
			throw e;
		} finally {
			DbFun.closeResultSetFinal(rs);
			DbFun.closeStatementFinal(pst);
		}
	}
}
