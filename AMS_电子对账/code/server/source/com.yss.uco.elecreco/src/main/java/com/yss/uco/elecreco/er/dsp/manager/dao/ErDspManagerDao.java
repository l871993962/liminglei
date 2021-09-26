package com.yss.uco.elecreco.er.dsp.manager.dao;

import java.beans.PropertyDescriptor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.PageInation;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.database.ResultSetTools;
import com.yss.framework.api.exception.DataAccessException;
import com.yss.framework.api.exception.InvalidParametersException;
import com.yss.framework.api.mvc.dao.GeneralDao;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.util.StringUtil;
import com.yss.uco.elecreco.support.dzdz.common.pojo.ErDspManager;

public class ErDspManagerDao extends GeneralDao{
	
	public ErDspManagerDao(DbPool pool, SQLBuilder sqlBuilder) {
		super(pool, sqlBuilder);
	}
	/**
	 * 排序，保证组合在第一位
	 */
	@Override
	protected List<String> getParaName(HashMap<String, Object> paraMap)
	{
		List<String> list = new ArrayList<String>();
		HashMap<String, Object> temp = new HashMap<String, Object>();
		temp.putAll(paraMap);
		//保证组合参数是在第一位
		list.add("ARRAY_C_PORT_CODE");
		list.add("ARRAY_C_PORT_CODE");
		if(!paraMap.containsKey("ARRAY_C_PORT_CODE"))
		{
			paraMap.put("ARRAY_C_PORT_CODE", "");
		}else
		{
			temp.remove("ARRAY_C_PORT_CODE");
		}
		List<String> paras = super.getParaName(temp);
		list.addAll(paras);
		return list;
	}

	/**
	 * 填充需要在界面展示的值,不需要保存到自定参数表中的字段
	 */
	@Override
	protected BasePojo setResultSet(ResultSetTools rsTools, ResultSet rs,
			Class<?> clazz, PropertyDescriptor[] props) throws Exception {
		ErDspManager pojo = (ErDspManager) super.setResultSet(rsTools, rs, clazz, props);
		addValue(pojo,rs);
		return pojo;
	}

	private void addValue(ErDspManager pojo, ResultSet rs) throws SQLException {
		pojo.setC_DSP_NAME(rs.getString("C_DSP_NAME"));
		pojo.setC_DSP_VALUE_TYPE(rs.getString("C_DSP_VALUE_TYPE"));
		pojo.setC_DV_TYPE(rs.getString("C_DV_TYPE"));
		pojo.setC_DV_PLAT_VALUE(rs.getString("C_DV_PLAT_VALUE"));
		pojo.setC_DESC(rs.getString("C_DESC"));
		pojo.setC_DS_TPYE(rs.getString("C_DS_TPYE"));	
		pojo.setC_PORT_NAME(rs.getString("C_PORT_NAME"));
	}
	
	public void upadteParam(List<ErDspManager> list)
	{
		if (list == null || list.size() == 0) {
			throw new InvalidParametersException("baseBean数据实例不能为空");
		}
		List<BasePojo> adds = new ArrayList<BasePojo>();
		List<BasePojo> mods = new ArrayList<BasePojo>();
		for(ErDspManager pojo : list)
		{
			if(!StringUtil.IsNullOrEmpty(pojo.getId()))
			{
				mods.add(pojo);
			}else
			{
				adds.add(pojo);
			}
		}
		Connection conn = null;
		boolean bTrans = false;
		try {
			conn = this.loadNewConnection();
			conn.setAutoCommit(bTrans);
			bTrans = true;
			if(mods.size() > 0)
			{
				this.updateByIds(mods, conn);
			}
			if(adds.size() > 0)
			{
				this.insert(adds, conn);
			}
			conn.commit();
			conn.setAutoCommit(bTrans);
			bTrans = false;
		} catch (Exception ex) {
			throw new DataAccessException("保存失败：" + ex.getMessage(), ex);
		} finally {
			this.endTransFinal(conn, bTrans);
			this.releaseConnection(conn);
		}
	}
	
	
}
