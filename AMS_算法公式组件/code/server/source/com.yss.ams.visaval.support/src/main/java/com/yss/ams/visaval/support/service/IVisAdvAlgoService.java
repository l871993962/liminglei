package com.yss.ams.visaval.support.service;

import java.util.List;
import java.util.Map;

import com.yss.ams.visaval.support.pojo.AdvAlgo;
import com.yss.ams.visaval.support.pojo.AdvAlgoPara;
import com.yss.ams.visaval.support.util.pojo.Parameter;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.mvc.biz.IServiceBus;

public interface IVisAdvAlgoService extends IServiceBus{
	public String getAllDate() throws YssException ;
	public String checkFormula(String isNew,String targetStr) throws YssException ;
	public String getFunc(String funType ) throws YssException;
	public String getRealExpression(String customize) throws YssException;
	public String getKeyWords(String keyType) throws YssException;
	public List<Parameter> getAllParameters();
	public AdvAlgo getAdvAlgo(String key);
	
	/**
	 * 获取A区树所有数据
	 * @return
	 */
	public String getAllDataTree_A();
	
	/**
	 *  算法界面优化  树加载
	 *  @author 马向峰   STORY #31713 【产品优化】算法公式配置优化
	 *  @Date 20170713
	 * @return	List<AlgoAPI>
	 */
	public String getTreeData(String algo_type);
	
	/**
	 * 算法界面，根据树节点代码查找参数和返回值
	 * @author 马向峰   STORY #31713 【产品优化】算法公式配置优化
	 *  @Date 20170713
	 * @param code	节点代码
	 * @return	DataAPI  参数和返回值的包装类
	 */
	public String getDataAPIByCode(String code);
	
	public Map<String,String> getParasByCode(String code);
	
	/**
	 * 参考算法
	 * @return
	 */
	public String getAlgos();
	
	public String getAlgoDesc(String code);
	
	/**
	 * 根据算法代码查找算法的所有信息,内包含算法的中英文形式（若中文字段有值则表示新算法，否则为旧算法）
	 * @param code
	 * @return
	 */
	public BasePojo getAlgoByCode(String code);
	
	/**
	 * 根据函数代码查询参数显示规则
	 * @param code
	 * @return
	 */
	public Map<String,Boolean> getRuleByFunCode(String code);
	
	/**
	 * 根据正则表达式解析合法的函数表达式
	 * @param fun
	 * @return
	 */
	public List<String> FunRegex(String fun);
	
	/**
	 * 处理算法预览
	 * @param mapStr 多个下拉框信息
	 * @param msg 算法信息
	 * @param oneSel 单个下拉框信息
	 * @return
	 */
	public String preview(String msg);
	
	/**
	 * 判断新旧算法：True(新算法),False(旧算法)
	 * @param code
	 * @return
	 */
	public String isNewAlgo(String code);
	
	/**
	 * 检查该参数是否由关键字
	 * @param code
	 * @return
	 */
	public String hasKeyWord(String code);
	
	/**
	 * 根据算法代码查找算法状态是否被审核
	 * @param code
	 * @return
	 */
	public String checkAlgoStatus(String code);
	
	/**
	 * 检查该函数是否为新算法的函数
	 * @param funName
	 * @return
	 */
	public String checkFunName(String funName);
	
	/**
	 * 根据函数信息，获取每个单个函数字符串
	 * @param funData
	 * @return
	 */
	public String getFunList(String funData);
	
	/**
	 * 根据参数关键字以及参数中文值去获取参数英文
	 * 如果在上下文中找不到就从参数数据表中找然后在放入上下文中供下次使用
	 * 上下文和参数数据表都没有找到返回空值
	 * @param keyword 参数关键字
	 * @param name    参数中文
	 * @return
	 */
	public String getCnToEnCode(String keyword,String name);
	
	/**
	 * 根据参数的中文名判断该参数是否有明细
	 * @param paramName_CH
	 * @return
	 */
	public String paramHasDetail(String paramName_CH);
	
	/**
	 * 根据参数中文名获取参数code
	 * @param paramName_CH
	 * @return
	 */
	public String getParamCodeByParamName(String paramName_CH);
	
	
	 /**
	  * @Title getAllAchivmentAgio 
	  * @Description 获取所有的业绩提成算法公式
	  * @author lff
	  * @date 2019年3月3日上午11:02:49
	  * @throws Exception
	  * @return List<BasePojo>
	  */
	 public List<AdvAlgo>  getAllAchivmentAlgo()throws Exception;
    
	 /**
	  * @Title getAchivmentAgioByCode 
	  * @Description 根据公式代码获取公式详细数据
	  * @author lff
	  * @date 2019年3月3日下午2:30:45
	  * @param code
	  * @return
	  * @throws Exception
	  * @return AdvAlgo
	  */
	 public AdvAlgo getAchivmentAlgoByCode(String code) throws Exception;
	 
	 /**
	  * 
	  * @Title getParamListByCode 
	  * @Description 根据公式查询出对应的参数信息
	  * @author yinyuyi
	  * @date 2019年7月5日下午3:40:23
	  * @param paramCodeList
	  * @return
	  * @throws Exception
	  * @return List<AdvAlgoPara>
	  */
	 public List<AdvAlgoPara> getParamListByCode(List<String> paramCodeList) throws Exception;
}
