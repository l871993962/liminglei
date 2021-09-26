package com.yss.ams.base.information.support.bi.account.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.ams.base.information.restFul.vo.FundAccRetFulVo;
import com.yss.ams.base.information.support.bi.account.pojo.CashAcc;
import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.ams.base.information.support.bi.account.pojo.PortRela;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.dataservice.GenericPojo;
import com.yss.framework.api.mvc.biz.IServiceBus;
import com.yss.framework.api.restful.annotations.LinkControllerMethod;
import com.yss.framework.api.restful.annotations.LinkControllerMethodArgu;
import com.yss.framework.api.restful.annotations.RestfulSupported;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.servlet.pojo.QueryRes;
import com.yss.framework.resource.mgr.pojo.ResourceMgr;
import com.yss.platform.support.enclosure.pojo.DataEnclosure;


/**
 * @ClassName IFundAccUnifyPayService
 * @Description 产品账户信息
 * @author liminghong@ysstech.com
 * @CreateDate 2017年5月22日
 * @Version V1.21.5.0
 * @Copyright (c) 2017, 深圳赢时胜 All Rights Reserved.
 */
@RestfulSupported
@GenericPojo(pojo = FundAcc.class)
public interface IFundAccService extends IServiceBus {
	
	/**
	 * BUG #181064 综合指令-待划款确认，选择调入DVP指令，点击划款确认，系统返回发送成功，返回小心为“null”，但指令的状态没有发生变化
	 * @param accNo
	 * @return
	 */
	public String getFundAccOrgcodeByAccNo(String accNo);
	
	/**
	 * 更新账户使用标记为已使用
	 * @param accountNo 账户代码
	 */
	public String updateFundAccUnifyPay(String accountNo);
	
	/** 
	 * @Title getDataListByAccTypes 
	 * @Description 根据账户类型获取账户信息
	 * @author liminghong@ysstech.com
	 * @date 2017年5月22日
	 * @param types 账户类型
	 * @return 账户信息 False-不存在 Success-存在
	 */
	public <K extends BasePojo> List<K> getDataListByAccTypes(String[] types);
	
	public <K extends BasePojo> List<K> getDataListByAccTypes2(String[] types);
	
	/**
	 * 判断是否存在指定账户类型的账户
	 * @param type 账户类型
	 * @return
	 */
	public String isAccOfAccTypeExit(String type);
	
	/**
	 * 查询指定账户类型及开户地址的账户
	 * @param types 账户类型
	 * @param accAddr 开户地址
	 * @return 账户信息
	 */
	@LinkControllerMethod(value="getFundAccByType",arguTypes = FundAccRetFulVo.class)
	public List<FundAcc> getFundAccByType(@LinkControllerMethodArgu("types")String[] types,@LinkControllerMethodArgu("accAddr")String accAddr);
	
	/**
	 * STORY #35492 南方基金-产品账户信息中大额支付号与关联机构联动
	 * 根据id更新机构信息的大额支付号和联行行号
	 * @param cPAYCODE 大额支付号
	 * @param cBANKCODE 联行号
	 * @param id c_iden
	 * @return Fail-失败 Success-成功
	 */
	String updateOrgInfo(String cPAYCODE,String cBANKCODE,String id);
	
	/** 
	 * @Title getDataListByPort 
	 * @Description 获取组合的托管账户信息
	 * @author liminghong@ysstech.com
	 * @date 2016年12月12日上午10:10:03
	 * @param portCode 组合代码
	 * @return List<FundAcc> 托管账户信息
	 */
	public List<FundAcc> getDataListByPort(String portCode);
	
	/**
	 * @Title getDataListByPortlist 
	 * @Description 获取组合的托管户账户信息
	 * @author caomanhong
	 * @date 2019年12月12日上午10:10:03
	 * @param portCodeList 组合代码
	 * @return HashMap<String ,List<FundAcc>> 托管账户信息
	 */
	@LinkControllerMethod(value="getDataListByPortlist",arguTypes = List.class)
	public HashMap<String ,List<FundAcc>> getDataListByPortlist(String[] portCodeList);
	
		
	/**
	 * STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
	 * @param AccNo
	 * @return
	 */
	FundAcc getFundAccByAccNo(String accNo);
	
	/**
	 * STORY #42242 歌斐支付平台-能在同一个界面查询所有账户的余额以及发生额 
	 * @param id
	 * @return
	 */
	FundAcc getFundAccById(String id);
	/**
	 * 根据账户的开户行，户名，账号查询账户信息（必须全匹配）
	 * @return
	 */
	FundAcc getFundAccByAcc(FundAcc fundAcc);
	/**
	 * 
	 * @param pojo
	 * @return
	 */
    String getIdAfterSave(FundAcc pojo);
	
	/**
	 * STORY #41401 产品信息-产品账户设置，批量关联账户
	 * 保存账户信息的同时，将组合关联关系也保存
	 * @param portRela
	 * @param userCode
	 * @param updateTime
	 * @return
	 */
    Boolean savePortFundRela(String portCodes, String fundAccID, String accountType);
    
    /**
     * STORY #41401 产品信息-产品账户设置，批量关联账户
     * 在产品账户设置里，删除的是关联关系，而不是账户信息
     * @param relaCode
     * @param port
     * @return
     */
    Boolean deletePortFundRela(String relaCode,String port);
    
    /**
     * STORY #41401 产品信息-产品账户设置，批量关联账户
     * 在产品账户设置里，删除的是关联关系，而不是账户信息
     * @param relaCode
     * @param port
     * @return
     */
    Boolean deletePortsFundRela(String relaCode,String port);
    
    /**
     * STORY #41401 产品信息-产品账户设置，批量关联账户
     * BUG #275153 【30.7】--30.7版本余额和明细返回，存入余额和明细表组合代码取值逻辑优化
     * 通过账户信息的id和组合代码获取关联关系里的组合
     * @param id
     * @return
     */
    @LinkControllerMethod(value = "getPortsByRelaCode", arguTypes = {String.class,String.class})
    String getPortsByRelaCode(String id, String portCode);
    
    /**
     * STORY #41401 产品信息-产品账户设置，批量关联账户
     * BUG #275153 【30.7】--30.7版本余额和明细返回，存入余额和明细表组合代码取值逻辑优化
     * 通过账户信息的id和组合代码获取关联关系里的组合
     * @param id
     * @return
     */
    @LinkControllerMethod(value = "getPortsByRelaCode", arguTypes = String.class)
    String getPortsByRelaCode(String id);
    /**
     * STORY #41401 产品信息-产品账户设置，批量关联账户
     * 支付产品账户删除时，删掉关联关系数据
     * @return
     */
    Boolean deleteUnusePortRela();
    
    /** 
	 * @Title insert 
	 * @Description 插入账户信息及账户机构关联信息<br/>
	 * @author zhouning_cs@ysstech.com
	 * @date 2017年8月29日
	 * @param orgCode-机构代码 
	 * @param pojoList-账户信息
	 * @return String
	 */
    @LinkControllerMethod(value="insert",arguTypes = FundAccRetFulVo.class)
	String insert(@LinkControllerMethodArgu("orgCode")String orgCode,@LinkControllerMethodArgu("pojoList")List<FundAcc> pojoList);

	<K extends BasePojo> List<K> getDataListByAssCode(String portCode)
			throws ServiceException;
	
	/**
	 * 根据存款账户类型和已经关联到该组合或者没有关联到任一组合的现金账户 从估值迁移 器去除依赖    20171026 add by zhouning
	 * @param portCode 组合代码
	 * @param dvAccType 现金账户类型
	 * @return 现金账户集合
	 */
	List<CashAcc> getCADataListByPortCode(String portCode, String dcCode) throws ServiceException;
	
	/** 
     * @Title queryZfbyPort 
     * @Description 查询自动划款指令设置是否使用某账号
     *   从支付平台IAutoHkzlUnifyPayService迁移到账户信息中来 by zhouning 20171107
     * @author liminghong@ysstech.com
     * @date 2017年5月22日
     * @param c_open_addr 账户开户地址
     * @param c_open_acc_no 账户代码
     * @param c_open_acc_name 账户名称
     * @return "true"-存在 "false"-不存在
     */
    String queryZfbyPort(String c_open_addr, String c_open_acc_no,String c_open_acc_name);
 
    /** 
	 * @Title saveRelaInfo 
	 * @Description 保存关联信息
	 * @author liminghong@ysstech.com
	 * @date 2017年8月25日
	 * @param fa
	 * @param portCode
	 */
    @LinkControllerMethod(value="saveRelaInfo",arguTypes = FundAccRetFulVo.class)
    String saveRelaInfo(@LinkControllerMethodArgu("pojo")FundAcc pojo,@LinkControllerMethodArgu("portCode")String portCode);
    
	
	/**
	 * 根据关联id删除数据
	 * 2017年11月14日
	 */
	Boolean deleteByRealId(String[] realIds);
    
	/**
	 * 产品生命周期账户管理查询
	 * zhangyongzhao
	 * 2017年12月5日
	 */
	public List<PortRela> getFundAcc(String[] recordIds);
	
	/**
	 * 产品生命周期账户管理查询
	 * zhangyongzhao
	 * 2017年12月5日
	 */
	public List<PortRela> getFundAccPort(String portCode);
	
	/**
	 * 读取 账户是否二次录入的 配置文件
	 * BUG #187667 支付参数“账户是否二次录入”参数值的维护需优化
	 * @return
	 */
	public String readProperty();
	
	public String updateProperty(String checkInfo);
	
	/**
	 * 查询是否显示A区，默认显示<br>
	 * BUG #194507 账户信息设置屏蔽A区设置
	 * @Title showAreaA 
	 * @Description 该方法用于测试方法注释格式
	 * @author papaya
	 * @date 2018年3月8日上午11:09:35
	 * @return
	 * @return String
	 */
	public String showAreaA();
	
	/**
	 * 
	 * @param id
	 * @param fundId
	 */
	public void updatePortCode(String id,String fundId);
	
	/**
	 * 
	 * @param id
	 * @param accountType
	 */
	public void updateFundAccType(String id,String accountType);
	
	/**
	 * 
	 * @param paraMap2
	 * @return
	 */
	@LinkControllerMethod(value = "getFundAccByInfo", arguTypes = HashMap.class)
	public FundAcc getFundAccByInfo(HashMap<String,String> paraMap);
	
	/**
	 * 
	 * @param paraMap2
	 * @return
	 */
	@LinkControllerMethod(value = "getFundAccByInfo", arguTypes = Map.class)
	public FundAcc getFundAccByInfo(Map<String,Object> paraMap);
	
	/**
	 * 缓存获取账户集合
	 * @return
	 */
    List<FundAcc> getFundAccListByCache();
    
  
	/**
	 * 账户字符串中，去除中间的款项类型
	 * @param search
	 * @return
	 */
    public String getAccByRmHkType(String search);
    
	/**
	 * 获取以开户地址为父节点，账户为子节点的树形结构
	 * @return
	 */
	public List<BasePojo> getFundAccNoAndAddrList();

	/**
	 * 获得产品唯一的对应类型的账户，如果有多个则返回空
	 * @param portCodes accountType
	 * @return
	 */
	public HashMap<String, FundAcc> getUniqueAccountTypeByPorts(String portCodes,String accountType);
	
	/**
	  * 加载账户类型为托管户、副托管户的账户且关联的组合的【组合级别】为‘单元层’的账户
	  * @return
	  */
	 public ArrayList<FundAcc>  getAllFundAccByType(HashMap<String,String> paraMap) throws Exception;


	/**
	 * 先删除，后插入t_p_ab_port_acc_rela表中
	 * @param id
	 * @param c_PORTFOLIOID
	 * @param string
	 */
	void deleteThenSaveFundRela(String id, String c_PORTFOLIOID, String string);
	 
	 public HashMap<String,String> getFundAccByNoAddrName(List<FundAcc> list);
	 
	 /**
	  * 查询账户属性结构的账户数据
	  * @param paraMap
	  * @return
	  */
	 public QueryRes getAccInAccountTreeView(HashMap<String, Object> paraMap);
	 
	 /**
	  * [合并代码]STORY71636【中银基金】【高】运营类指数许可费支付指令生成。 add by lijinpeng 2019年5月6日
	  * 根据组合和账户类型使用f_get216_acc_no获得对应的账户
	  * @param paraMap
	  * @return
	  */
	 public ArrayList<FundAcc>  queryAccNoByfun(HashMap<String,String> paraMap);
	 
	 /**
	 * 根据账户信息更新大额支付号
	 * @param map
	 * @return
	 */
	 public	String updatePaycodeByAcc(HashMap<String, String> map);
	 
	 /**
      * STORY #72919 【国泰基金】支付产品账户信息界面新增上传附件功能，增加每个账户可以上传附件，并且支持附件下载 
      * @param delFileList
      * @param addFileList
      * @return String
      */
	 @LinkControllerMethod(value="updateFileMsg",arguTypes = FundAccRetFulVo.class)
	 public String updateFileMsg(@LinkControllerMethodArgu("delFileList")List<DataEnclosure> delFileList, @LinkControllerMethodArgu("addFileList")List<DataEnclosure> addFileList);
	
	 /**
	   * 将文件下载到指定目录下：/file/updowntemp/
	   * @param filePath 文件路径
	   * @return 复制后文件路径
	   */
	 public String fileDown(String filePath);
	 
	 /**
	   * 批量下载
	   * @param fileList
	   * @return
	   */
	 public HashMap<String, String> filesDownLoad(List<String> fileList);

	 /**
	   * 批量删除文件
	   * @param path
	   * @return
	   */
	 public String delFile(List<String> filePathList);

	 /**
	   * 上传文件的方法，如果远程服务的虚拟路径已存在该文件，则直接返回
	   * @param remoteFilePath
	   * @param cjdFile
	   * @param userCode
	   * @return
	   * @throws Exception 
	   */
	 @LinkControllerMethod(value="UpLoadByNewPath",arguTypes = FundAccRetFulVo.class)
	 public ResourceMgr UpLoadByNewPath(@LinkControllerMethodArgu("remoteFilePath")String remoteFilePath, @LinkControllerMethodArgu("cjdFile")File cjdFile, @LinkControllerMethodArgu("userCode")String userCode) throws Exception;
	 
	 /**
	  * 根据组合代码获取资产代码
	  * @param codes
	  * @return
	  */
	 public HashMap<String,String> getAssCodeByPortcode(String codes,String type);
	 
	 /**
      * 更新账户缓存
      * @param idList
      * @param type
      */
	 @LinkControllerMethod(value="updateFundAccCache",arguTypes = FundAccRetFulVo.class)
     public void updateFundAccCache(@LinkControllerMethodArgu("idList")List<String> idList,@LinkControllerMethodArgu("type")String type);

     /**
	  * STORY #91838 【汇添富基金】账户插入restful接口
	  * @author zmk
	  * @date 2020-09-24
      * @param runningAccs 交易编号
      * @return
      */
	 public List<FundAcc> getAccByRunningAccs(String openAccNo,String openAddr, String openAccName, String dcCode);
	 
	 /**
	  * BUG346545【汇添富基金】【营运平台二期】新增支付产品账户信息后界面选择账户类型查不到新增数据
	  * @author lzy
	  * @date 2020-12-09
	  * @param runningAccs 交易编号
	  * @return
	  */
	 public void updateFundAccById(FundAcc funAcc);
	
	 /**
	  * 根据组合代码，账户id获取关联信息中的开始时间，结束时间
	  * @param codes
	  * @return
	  */
	 String getTimeByRelaPort(String portCode, String fundAccId);
	 
	 /**
	  * 根据组合代码，账户id更新关联信息中的开始时间，结束时间
	  * @param codes
	  * @return
	  */
	 String updateTimeByRelaPort(HashMap<String,String> paraMap);
 
		/**
		 * 保存账户信息的同时，将组合关联关系也保存
		 * @param portRela
		 * @param userCode
		 * @param updateTime
		 * @return
		 */
	  Boolean savePortFundRelaWithDate(HashMap<String,String> paraMap);
	  /**
	   * 根据开户账号和开户行查询数据
	   * @param openNo开户账号
	   * @param openAddr开户行
	   * @return
	   */
	  String getAccListByOpenNoAndOpenAddr(String openNo, String openAddr, String iden);

	  /**
	   * 插入账户表
	   * @param fa
	   * @return
	   */
	  public String insertFundAcc(FundAcc fa);
	  
	  
      /**
       * 插入账户类型和组合关联表
       * @param id
       * @param fundId
       * @return
       */
	 public String addPortCodeRela(String id, String fundId);

		/**
		 * 
		 * @param paraMap2
		 * @return
		 */
	 public FundAcc getFundAccByGF(Map<String, String> paraMap);
	
}
