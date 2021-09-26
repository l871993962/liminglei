package com.yss.ams.product.information.modules.pg.portgroup.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import com.yss.ams.product.information.activator.ProductInfoActivator;
import com.yss.ams.product.information.modules.ab.port.admin.PortAdmin;
import com.yss.ams.product.information.modules.ab.port.dao.PortSqlBuilder;
import com.yss.ams.product.information.modules.pg.portgroup.admin.PortGroupAdmin;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupDao;
import com.yss.ams.product.information.modules.pg.portgroup.dao.PortGroupSqlBuilder;
import com.yss.ams.product.information.modules.pg.portgrouprela.service.impl.PortGroupRelaService;
import com.yss.framework.api.common.co.Port;
import com.yss.ams.product.information.support.modules.ab.port.service.IPortDataService;
import com.yss.ams.product.information.support.modules.pg.portgroup.pojo.PortGroup;
import com.yss.ams.product.information.support.modules.pg.portgroup.service.IPortGroupDataService;
import com.yss.fast.right.support.right.pojo.UserPostData;
import com.yss.fast.right.support.right.service.IFASTDataAuthorityService;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.database.YssDbPoolFactory;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.context.ContextFactory;
import com.yss.framework.db.DbPoolFactory;
import com.yss.mvc.service.ServiceAssistance;
import com.yss.right.constants.RightConstants;

/**
 * <群组管理>数据服务类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class PortGroupDataService implements IPortGroupDataService {

	private PortGroupAdmin portGroupAdmin = null ;
	private PortAdmin portAdmin = null;
	private Logger logger = LogManager.getLogger(PortGroupService.class);

	public PortGroupDataService() {
		portGroupAdmin = new PortGroupAdmin(YssDbPoolFactory.getInstance().getDbPool(ProductInfoActivator.class), new PortGroupSqlBuilder());
		portAdmin = new PortAdmin(DbPoolFactory.getInstance().getPool(),
				new PortSqlBuilder());
	}
	
	/**
	 * 查询群组A区数据
	 */
	@Override
	public QueryRes getPortGroupA(HashMap<String, Object> paraMap) {
		return portGroupAdmin.getPortGroupA(paraMap);
	}
	
	/**
	 * 根据群组代码查询所有组合代码
	 * @return
	 */
	public List<String> querySelectedPortCode(String c_group_code){
		List<String> portList = null;
		PortGroupRelaService portGroupRelaService;
		try {
			portGroupRelaService = new PortGroupRelaService();
			portList = portGroupRelaService.querySelectedPortCode(c_group_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码查询组合失败", e);
		}
		return  portList;
	}
	
	@Override
	public QueryRes querySelectedPort(String c_group_code) {
		//// 根据群组代码查询所有组合代码 返回对象QueryRes
		QueryRes res = null;
		PortGroupRelaService portGroupRelaService;
		try {
			portGroupRelaService = new PortGroupRelaService();
			res = portGroupRelaService.querySelectedPort(c_group_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码查询组合失败", e);
		}
		return res;
	}
	
	@Override
	public QueryRes querySelectedPortWithoutRight(String c_group_code) {
		//// 根据群组代码查询所有组合代码 返回对象QueryRes
		QueryRes res = null;
		PortGroupRelaService portGroupRelaService;
		try {
			portGroupRelaService = new PortGroupRelaService();
			res = portGroupRelaService.querySelectedPortWithoutRight(c_group_code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.log("产品群组模块：根据群组代码查询组合失败", e);
		}
		return res;
	}
	
	@Override
	public QueryRes getGroupDataTree() {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			IPortDataService portService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			List<Port> list = portService.getGroupDataTree("");
			//STORY #47085 【数据权限分配】增加“产品群组”--群组权限过滤
			//21.6鹏华测试 A区群组不要根据权限控制  edit by sunyanlin 20180322
			//list =  filterPortByDataRight(list);//STORY #54295 【紧急】【数据权限分配】增加“产品群组”，群组不进行权限判断
			pojoList = convertToBasePojoList(list);
			res.setDataList(pojoList);
			res.setMenuId("pd_portfolio");
			res.setHeadKeyList(ServiceAssistance.getListHead("pd_portfolio_A",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return res;
	}
	
	@Override
	public QueryRes getGroupDataTreeWithoutRight() {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			IPortDataService portService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			List<Port> list = portService.getGroupDataTreeWithoutRight("");
			//STORY #47085 【数据权限分配】增加“产品群组”--群组权限过滤
			//21.6鹏华测试 A区群组不要根据权限控制  edit by sunyanlin 20180322
			//list =  filterPortByDataRight(list);//STORY #54295 【紧急】【数据权限分配】增加“产品群组”，群组不进行权限判断
			pojoList = convertToBasePojoList(list);
			res.setDataList(pojoList);
			res.setMenuId("pd_portfolio");
			res.setHeadKeyList(ServiceAssistance.getListHead("pd_portfolio_A",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		
		return res;
	}
	
	@Override
	public QueryRes getGroupDataTree(List<String> portList) {
		QueryRes res = new QueryRes();
		List<BasePojo> pojoList;
		try {
			IPortDataService portService = YssServiceFactory.getInstance()
					.createService(IPortDataService.class);
			List<Port> list = portService.getGroupDataTree("",portList);
			//STORY #47085 【数据权限分配】增加“产品群组”--群组权限过滤
			//21.6鹏华测试 A区群组不要根据权限控制  edit by sunyanlin 20180322
			//list =  filterPortByDataRight(list);//STORY #54295 【紧急】【数据权限分配】增加“产品群组”，群组不进行权限判断
			pojoList = convertToBasePojoList(list);
			res.setDataList(pojoList);
			res.setMenuId("pd_portfolio");
			res.setHeadKeyList(ServiceAssistance.getListHead("pd_portfolio_A",ProductInfoActivator.class));
		} catch (Exception e) {
			throw new ServiceException(e);
		}
		return res;
	}
	/**
	 * STORY #47085 【数据权限分配】增加“产品群组”--群组权限过滤
	 * @param list
	 * @return
	 */
	private List<Port> filterPortByDataRight(List<Port> list){
		List<Port> filterList = null;
		if(null != list && list.size() > 0){
			List<String> groupDatas = portAdmin.getDataRightList(RightConstants.groupType);
			if(null != groupDatas){
				filterList = new ArrayList<Port>();
				for(Port port : list){
					if(groupDatas.contains(port.getC_PORT_CODE())){
						filterList.add(port);
					}
				}
			}
			
		}
		return filterList;
	}
	
	
	private List<BasePojo> convertToBasePojoList(List<Port> portList){
		List<BasePojo> basePojoList = new ArrayList<BasePojo>();
		for(Port port : portList){
			basePojoList.add(port);
		}
		return basePojoList;
	}

	@Override
	public String checkPortCode(String groupCode) {
		IPortDataService portService = YssServiceFactory.getInstance()
				.createService(IPortDataService.class);
		return portService.checkPortCode(groupCode);
	}


	@Override
	public <K extends BasePojo> List<K> getDataList() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getDataListRes() throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> K getDataByCode(String dataCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getMenuId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setMenuId(String menuId) {
		// TODO Auto-generated method stub

	}

	@Override
	public <K extends BasePojo> K getPojoByCode(String pojoCode)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByTypes(String[] types)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByTypes(String[] types) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <K extends BasePojo> List<K> getDataListByKeys(String[] keys)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public QueryRes getQueryResByKeys(String[] keys) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getKeyConvertMap() throws ServiceException {
		////BUG #115003 新增产品群组管理后群组名称菜单中显示的是群组代码
		return portGroupAdmin.getKeyConvertMap();
	}

	@Override
	public HashMap<String, String> getKeyConvertMap(List<String> listKey)
			throws ServiceException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String deleteByPortCodes(String[] portCodes){
		String result = "";
		try {
			PortGroupRelaService portGroupRelaService = new PortGroupRelaService();
			result = portGroupRelaService.deleteByPortCodes(portCodes);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.log("产品群组模块：根据群组代码删除组合失败", e);
		}
		return result;
	}

	/**
	 * 获取当前登录用户下有权限的群组
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	@Override
	public <K extends BasePojo> List<K> getAuthorityGroup() {
		Set<String> set = new HashSet<String>();
		List<BasePojo> list = new ArrayList<BasePojo>();
		try {
			IFASTDataAuthorityService service = YssServiceFactory.getInstance().createService(IFASTDataAuthorityService.class);
			String userCode = ContextFactory.getContext().getUserCode();
			List<UserPostData> userPostDatas =service.getUserDataRightByUserAndType(userCode,RightConstants.groupType);
			if(userPostDatas !=null && !userPostDatas.isEmpty()){
			for (UserPostData upd : userPostDatas) {
				set.add(upd.getC_DATA_CODE());
			}
		  }
			 String[] strsTrue = set.toArray(new String[set.size()]);
			 list =getDataListByCodes(strsTrue);
		} catch (Exception e) {
			logger.log("获取用户权限下的群组失败", e);
			//e.printStackTrace();
		}
		return (List<K>)list;
	}
	
	/**
	 * 根据群组代码查询群组
	 * src：STORY #62048 新增加的组合自动关联自动化估值方案
	 * author：shijian@ysstech.com
	 * date：2018年10月22日
	 */
	public <K extends BasePojo> List<K> getDataListByCodes(String[] codes)
			throws ServiceException {
		try {
			return portGroupAdmin.getDataListByCodes(codes);
		} catch (Exception e) {
			throw new ServiceException(e);
		}
	}
}
