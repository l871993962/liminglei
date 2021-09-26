package com.yss.ams.product.information.support.modules.aa.portcls.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.yss.ams.product.information.support.modules.aa.portcls.pojo.PortCls;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetDataListByPortsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.GetPortClsByDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.PortClsRecordsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryFjSyfpInfoVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClassVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByClsLevelVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsAndDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByDvClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsByLiquidVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsDateVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsSortVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPortClsYxqVo;
import com.yss.ams.product.information.support.modules.aa.portcls.vo.QueryPreviousPortClsVo;
import com.yss.framework.api.cache.IDataServiceForCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.dataservice.IControlDataService;
import com.yss.framework.api.dataservice.IDataService;
import com.yss.framework.api.dataservice.IKeyConvertDataService;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;

/**
 * <产品分级信息>数据服务接口，主要进行跨应用数据获取
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */
@RestfulSupported
@GenericPojo(pojo=PortCls.class)
public interface IClsPortDataService extends IDataService, 
		IControlDataService,IKeyConvertDataService,IDataServiceForCache {
	
	/**
	 * 根据组合代码获取其分级代码
	 * 
	 * @author liuxiang
	 * @date 2014-6-9
	 * @param code 组合代码
	 * @return
	 */
	public String getPortClsCode(String code) throws ServiceException;

	/**
	 * 取得所有数据（只包含代码和名称）
	 * @author liuxiang
	 * @date 2014-6-9
	 * @param portCode 组合代码
	 * @return
	 */
	public HashMap<String, String> getShortDataMap(String portCode) throws ServiceException;
	
	/**
	 * 取得所选组合对应的分级组合代码
	 * @author xhb
	 * @date 2016-1-29
	 * @param ports 组合代码
	 * @return
	 */
	@LinkControllerMethod(value = "getDataListByPorts", arguTypes = String[].class)
	public <K extends BasePojo> List<K> getDataListByPorts(String[] ports)
			throws ServiceException;
	
	/**
	 * 根据组合代码和级别类型获取分级代码
	 * 
	 * @author liuxiang
	 * @date 2016-9-1 STORY #28429 【广发证券】TA净值表导出设置中导出级别的优化
	 * @param portCode
	 *            组合代码
	 * @param types
	 *            级别类型(多个类型以逗号分割)
	 * @return 分级代码(以逗号分割)
	 * @throws ServiceException
	 */
	public String getClsCodesByPortCodeAndType(String portCode, String types)
			throws ServiceException;
	
	/**
	 * @Title STORY36440【南方基金】增加参数控制，当分级产品某一级别全部赎回后，被赎回的分级单位净值直接取其他级别 
	 * @Description 获取所选分组下的所有分级组合+‘系统默认算法’
	 * @author zhaijiajia@ysstech.com
	 * @date 2016年12月8日上午11:08:11
	 * @param types
	 * @param clsPort
	 * @return
	 * @throws ServiceException
	 * @return List<K>
	 */
	@LinkControllerMethod(value = "getDataListByPorts", arguTypes = GetDataListByPortsVo.class)
	public <K extends BasePojo> List<K> getDataListByPorts(@LinkControllerMethodArgu("types")String[] types, @LinkControllerMethodArgu("clsPort")String[] clsPort)
			throws ServiceException;
	/**
	 * 根据组合日期返回对应的分级组合
	 * @author shiliang
	 * @date 2017-6-14
	 * @param PortCode 组合代码
	 * @param dueDate 日期
	 * @return
	 */
	@LinkControllerMethod(value="getPortClsByDate",arguTypes = GetPortClsByDateVo.class)
	public List<PortCls> getPortClsByDate(@LinkControllerMethodArgu("PortCode")String PortCode, @LinkControllerMethodArgu("dueDate")Date dueDate)
		   throws ServiceException;
	
	/**
	 * 根据组合代码,分级组合代码,上市日期 获取分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @param actDate
	 *            上市日期
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryPortCls", arguTypes = {String.class,String.class,Date.class})
	public PortCls queryPortCls(String portCode, String classPort, Date actDate)
		  throws ServiceException;
	
	/**
	 * 根据组合代码,分级组合代码 获取分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryPortCls", arguTypes = {String.class,String.class})
	public PortCls queryPortCls(String portCode, String classPort)
			throws ServiceException;
	
	/**
	 * 查询清算日期为核算日的分级产品参数设置
	 * author dingshalu
	 * 2016年3月14日
	 * @return List<PortCls>
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="portClsRecords",arguTypes = PortClsRecordsVo.class)
	public List<PortCls> portClsRecords(@LinkControllerMethodArgu("actDate")Date actDate, @LinkControllerMethodArgu("port")String port)
			throws ServiceException;
	
	/**
	 * 根据组合代码,分级组合代码,日期区间 获取分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="queryPortCls_Date",arguTypes = QueryPortClsDateVo.class)
	public PortCls queryPortCls_Date(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("classPort")String classPort, @LinkControllerMethodArgu("actDate")Date actDate)
			throws ServiceException;
	
	/**
	 * 根据组合代码获取组合下所有分级组合列表
	 * 
	 * @param portCode
	 *            组合代码
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryPortCls", arguTypes = QueryPortClsVo.class)
	public List<PortCls> queryPortCls(@LinkControllerMethodArgu("portCode")String portCode)
			throws ServiceException;
	
	/**
	 * 根据分级组合代码,分级组合类型,分级级别获取分级组合List
	 * 
	 * @param portCode
	 *            组合代码
	 * @param portClsType
	 *            分级组合类型
	 * @param classPort
	 *            分级级别
	 * @return
	 * @throws ServiceException
	 */
	public PortCls queryPortClsByTypeAndClass(String portCode,String portClsType, String classPort)
			throws ServiceException;
	
	/**
	 * 根据分级组合代码,分级级别获取未到期分级组合列表
	 * STORY33196（紧急）受证监会13号公告影响，海通“赢财升鑫”产品改造方案。
	 * xiaozhilong 20160831
	 * @param portCode
	 *            组合代码
	 * @param actDate
	 *            核算日期
	 * @param classPort
	 *            分级级别
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="queryPortClsByClass",arguTypes = QueryPortClsByClassVo.class)
	public List<PortCls> queryPortClsByClass(@LinkControllerMethodArgu("portCode")String portCode,
			@LinkControllerMethodArgu("actDate")Date actDate, @LinkControllerMethodArgu("classPort")String classPort) throws ServiceException;
	
	/**
	 * 根据分级组合代码,分级组合类型,分级级别获取分级组合List
	 * 
	 * @param portCode
	 *            组合代码
	 * @param portClsType
	 *            分级组合类型
	 * @param portClsLevel
	 *            分级级别
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryPortCls", arguTypes = {String.class,String.class,String.class})
	public List<PortCls> queryPortCls(String portCode, String portClsType,
			String portClsLevel) throws ServiceException;
	
	/**
	 * 功能简述：根据组合代码获取该组合下所有的最末级组合的分级信息(海融系列产品估值方案)
	 * 
	 * @param portCode
	 * @return
	 * @throws ServiceException
	 */
	public List<PortCls> queryPortClsMx(String portCode) throws ServiceException;
	
	/**
	 * 根据组合代码获取组合下所有分级组合列表
	 * 
	 * @param portCode
	 *            组合代码
	 * @return
	 * @throws ServiceException
	 */
	public List<PortCls> queryPortClsList(String portCode) throws ServiceException;
	
	/**
	 * 根据组合代码,分级组合代码,业务日期 获取运行期的分级组合对象
	 * 
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @param actDate
	 *            上市日期
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value="queryPortClsYxq",arguTypes = QueryPortClsYxqVo.class)
	public PortCls queryPortClsYxq(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("classPort")String classPort,
			@LinkControllerMethodArgu("actDate")Date actDate) throws ServiceException;
	
	/**
	 * 获取分级产品 不能从缓存获取，缓存保存的分级产品 分级产品code 一样会丢失数据 根据组合和日期获取运作期间的所有分级组合 BY
	 * Jinghehe 2014-6-21
	 * 
	 * @param port 组合代码
	 * @param accDate 指定日期
	 * @return
	 * @throws ServiceException
	 */
	@LinkControllerMethod(value = "queryPortCls", arguTypes = QueryPortClsVo.class)
	public List<PortCls> queryPortCls(@LinkControllerMethodArgu("port")String port, @LinkControllerMethodArgu("accDate")Date accDate)
			throws ServiceException;
	
	/**
	 * 根据组合代码，并且指定日期在成立日期和清算日期之间获取分级组合列表
	 * 
	 * @param port 组合代码
	 * @param accDate 指定日期
	 * @return
	 * @throws SQLException
	 */
	@LinkControllerMethod(value="queryPortClsByLiquid",arguTypes = QueryPortClsByLiquidVo.class)
	public List<PortCls> queryPortClsByLiquid(@LinkControllerMethodArgu("port")String port, @LinkControllerMethodArgu("accDate")Date accDate)
			throws ServiceException;
	
	/**
	 * 根据组合代码,分级组合代码,业务日期 获取上一个到期的分级组合对象
	 * By Jinghehe 2014-6-28
	 * @param portCode
	 *            组合代码
	 * @param classPort
	 *            分级组合代码
	 * @param actDate
	 *            业务日期
	 * @return
	 * @throws SQLException
	 */
	@LinkControllerMethod(value="queryPreviousPortCls",arguTypes = QueryPreviousPortClsVo.class)
	public PortCls queryPreviousPortCls(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("classPort")String classPort, @LinkControllerMethodArgu("actDate")Date actDate)
			throws ServiceException;
	
	/**
	 * 根据组合代码、分级级别代码和业务日期查询分级组合的集合
	 * 
	 * @param portCode 组合代码
	 * @param dvCls 分级级别代码
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */
	@LinkControllerMethod(value = "queryPortClsByDvCls", arguTypes = QueryPortClsByDvClsVo.class)
	public List<PortCls> queryPortClsByDvCls(@LinkControllerMethodArgu("portCode")String portCode,@LinkControllerMethodArgu("dvCls")String dvCls,@LinkControllerMethodArgu("actDate")Date actDate) 
			throws ServiceException;
	
	/**
	 * 根据组合代码和业务日期查询分级组合的集合
	 * 
	 * @param portCode 组合代码
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */
	@LinkControllerMethod(value = "queryPortClsByDvCls", arguTypes = QueryPortClsByDvClsVo.class)
	public List<PortCls> queryPortClsByDvCls(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("actDate")Date actDate)
			throws ServiceException;
	
	/**
	 * 根据组合代码、分级级别代码和业务日期查询产品下运行期某级别分级组合的集合
	 * 
	 * xiaozhilong STORY29633【广发证券】【紧急】量化避险资产估值需求
	 * @param portCode 组合代码
	 * @param clsLevel 分级级别
	 * @param actDate 核算日期
	 * @return 分级组合集合（以成立日期排序）
	 * @throws SQLException
	 */
	@LinkControllerMethod(value="queryPortClsByDvClsAndDate",arguTypes = QueryPortClsByDvClsAndDateVo.class)
	public List<PortCls> queryPortClsByDvClsAndDate(@LinkControllerMethodArgu("portCode")String portCode,@LinkControllerMethodArgu("clsLevel")String clsLevel, @LinkControllerMethodArgu("actDate")Date actDate) 
			throws ServiceException;
	
	/**
	 * 根据组合代码、分级级别代码和业务日期查询产品下运行期所有明细分级产品级别分级组合
	 * 
	 * xiaozhilong STORY29633【广发证券】【紧急】量化避险资产估值需求
	 * @param portCode 组合代码
	 * @param clsLevel 分级级别
	 * @param actDate 核算日期
	 * @return 分级组合
	 * @throws SQLException
	 */
	@LinkControllerMethod(value="queryPortClsByClsLevel",arguTypes = QueryPortClsByClsLevelVo.class)
	public PortCls queryPortClsByClsLevel(@LinkControllerMethodArgu("portCode")String portCode,@LinkControllerMethodArgu("clsLevel")String clsLevel, @LinkControllerMethodArgu("actDate")Date actDate) 
			throws ServiceException;
	
	/**
	 * 查询业务日期内所有分级组合成立日最早或早晚的分级组合
	 * edit by wangtangyao 20160726 STORY #31921  
	 * 根据成立日期先后顺序判断轧差分级或是基准分级时用
	 * @param portCode 组合代码
	 * @param gradePortCode 分级组合代码
	 * @param actDate 上市日期
	 * @return portCls 分级
	 * @throws SQLException
	 */
	@LinkControllerMethod(value="queryPortClsSort",arguTypes = QueryPortClsSortVo.class)
	public PortCls queryPortClsSort(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("actDate")Date actDate, @LinkControllerMethodArgu("sort")boolean sort)
			throws ServiceException;
	
	/**
	 * 根据组合代码和业务日期查询分级收益分配信息
	 * add by fangjiang 2013.06.18 STORY #4066 资产估值_应付投资者收益
	 * 
	 * @param portCode
	 * @param actDate
	 * @return
	 * @throws YssException
	 */
	@LinkControllerMethod(value="queryFjSyfpInfo",arguTypes = QueryFjSyfpInfoVo.class)
	public List<PortCls> queryFjSyfpInfo(@LinkControllerMethodArgu("portCode")String portCode, @LinkControllerMethodArgu("actDate")Date actDate)
			throws ServiceException;
	
	/**
     * @Title: insert 
     * @Desc: 保存分级产品信息
     * @param pojo
     * @throws ServiceException
     */
    public <K extends BasePojo> void insert(K pojo) throws ServiceException;
    
    /**
     * @Title: updateById 
     * @Desc: 更新保存分级产品信息
     * @param pojo
     * @throws ServiceException
     */
    public <K extends BasePojo> void updateById(K pojo) throws ServiceException;
    
    /**
     * @Title: deleteById 
     * @Desc: 删除分级产品信息
     * @param pojo
     * @throws ServiceException
     */
    public <K extends BasePojo> void deleteById(K pojo) throws ServiceException;
	
}
