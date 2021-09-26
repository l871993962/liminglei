package com.yss.ams.product.information.modules.aa.portcustom.admin;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.modules.aa.portcustom.dao.PortCustomDao;
import com.yss.ams.product.information.support.modules.aa.portcustom.pojo.PortCustom;
import com.yss.framework.api.common.co.BaseAdmin;
import com.yss.framework.api.database.DbPool;
import com.yss.framework.api.mvc.dao.sql.SQLBuilder;
import com.yss.framework.api.service.ServiceException;

/**
 * <用户自定义组合>管理类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortCustomDataAdmin extends BaseAdmin {
	PortCustomDao svcDao = null;

	public PortCustomDataAdmin(DbPool pool, SQLBuilder sqlBuilder) {
		svcDao = new PortCustomDao(pool, sqlBuilder);
	}

//	public ArrayList<String> getPortCodeByArray(ArrayList<String> keyArray)
//			throws ServiceException {
//		return svcDao.getPortCodeByArray(keyArray);
//	}
//
//	public String getPortCodeByMenuid(String code) throws ServiceException {
//		return svcDao.getPortCodeByMenuid(code);
//	}

	public ArrayList<String> getAssetType() throws ServiceException {
		return svcDao.getAssetType();
	}

//	public String getPortCode(String code) throws Exception {
//		return svcDao.getPortCode(code);
//	}

	public String getShowType(HashMap<String, String> codeMap)
			throws ServiceException {
		String result = "";
		Connection conn = null;
		try {
			conn = svcDao.loadNewConnection();
			result = svcDao.getShowType(codeMap, conn);

			if (result == null || "".equals(result)) {
				if (codeMap.containsKey("C_FUN_CODE")) {
					codeMap.remove("C_FUN_CODE");
					codeMap.put("C_FUN_CODE", " ");
				}

				result = svcDao.getShowType(codeMap, conn);
			}
		} catch (Exception e) {
			throw new ServiceException(e);
		} finally {
			svcDao.releaseConnection(conn);
		}

		return (result == null || result.trim().length() == 0) ? "ASS" : result;
	}

	public String deleteCustomPort(HashMap<String, String> param)
			throws ServiceException {
		return Boolean.toString(svcDao.deleteCustomPort(param));
	}

	public ArrayList<String> getUserDefaultPort(HashMap<String, String> paradict) throws ServiceException {
		return svcDao.getUserDefaultPort(paradict);
	}

	public String insertCustomPort(List<PortCustom> list, String type)
			throws ServiceException {
		return Boolean.toString(svcDao.insertCustomPort(list, type));
	}
	
	public ArrayList<String> getAssetTypeOnlyCode() throws ServiceException {
		return svcDao.getAssetTypeOnlyCode();
	}
}
