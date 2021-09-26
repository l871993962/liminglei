package com.yss.uco.elecreco.er.reverse.manager.ignore.service.impl;
import java.util.List;

import com.yss.uco.elecreco.er.reverse.manager.ignore.dao.IgnoreItemDao;
import com.yss.uco.elecreco.er.reverse.manager.ignore.dao.IgnoreItemSqlBuilder;
import com.yss.uco.elecreco.er.reverse.manager.ignore.pojo.IgnoreItem;
import com.yss.uco.elecreco.er.reverse.manager.ignore.service.IIgnoreItemService;
import com.yss.framework.api.mvc.biz.ServiceBus;
import com.yss.framework.db.DbPoolFactory;

public class IgnoreItemService extends ServiceBus<IgnoreItemService> implements IIgnoreItemService {

	private IgnoreItemDao serviceDao = null;
	public IgnoreItemService() throws Exception {
		serviceDao = new IgnoreItemDao(DbPoolFactory.getInstance().getPool(),new IgnoreItemSqlBuilder());
		dao = serviceDao;
	}
	@Override
	public List<IgnoreItem> getCompareIgnoreItem(String portCode, String tgh,
			String fileType) {
		return this.serviceDao.getCompareIgnoreItem(portCode, tgh, fileType);
	}
	
	
//	private HashMap<String, Object> setParaMap(HashMap<String, Object> paraMap)
//	{
//		if(paraMap.containsKey("ARRAY_C_PORT_CODE_OR_NULL"))//是否
//		{
//			if(!paraMap.containsKey("ARRAY_C_TGH_CODE_OR_NULL"))
//			{
//				paraMap.put("ARRAY_C_TGH_CODE_OR_NULL", ReveElecUtil.getOrgCodeByPortCodes((String) paraMap.get("ARRAY_C_PORT_CODE_OR_NULL")));
//			}
//		}
//		return paraMap;
//	}
//	
//	/**
//	 * 在查询数据时向上查找（要包含其上级的数据）
//	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
//	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
//	 * 查找特定托管行数据时，同时要加上公共数据
//	 * @author lwz
//	 *
//	 */
//	@Override
//	public QueryRes queryByCondition(HashMap<String, Object> paraMap) {
//		return super.queryByCondition(setParaMap(paraMap));
//	}
//	/**
//	 * 在查询数据时向上查找（要包含其上级的数据）
//	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
//	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
//	 * 查找特定托管行数据时，同时要加上公共数据
//	 * @author lwz
//	 *
//	 */
//	@Override
//	public QueryRes queryByCondition(HashMap<String, Object> paraMap,
//			PageInation page) {
//		return super.queryByCondition(setParaMap(paraMap), page);
//	}
//	/**
//	 * 在查询数据时向上查找（要包含其上级的数据）
//	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
//	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
//	 * 查找特定托管行数据时，同时要加上公共数据
//	 * @author lwz
//	 *
//	 */
//	@Override
//	protected List<BasePojo> query(HashMap<String, Object> paraMap,
//			Class<?> clazz) {
//		// TODO Auto-generated method stub
//		return super.query(setParaMap(paraMap), clazz);
//	}
//	/**
//	 * 在查询数据时向上查找（要包含其上级的数据）
//	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
//	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
//	 * 查找特定托管行数据时，同时要加上公共数据
//	 * @author lwz
//	 *
//	 */
//	@Override
//	protected List<BasePojo> query(HashMap<String, Object> paraMap,
//			PageInation page, Class<?> clazz) {
//		// TODO Auto-generated method stub
//		return super.query(setParaMap(paraMap), page, clazz);
//	}
//	/**
//	 * 在查询数据时向上查找（要包含其上级的数据）
//	 * 优先级：产品数据>托管行数据>公共数据（托管行和组合为空的数据）
//	 * 如：查找特定产品时，同时要加上其对应托管行下的数据，再加上公共数据
//	 * 查找特定托管行数据时，同时要加上公共数据
//	 * @author lwz
//	 *
//	 */
//	@Override
//	protected int queryCount(HashMap<String, Object> paraMap) {
//		// TODO Auto-generated method stub
//		return super.queryCount(setParaMap(paraMap));
//	}
	
	

}