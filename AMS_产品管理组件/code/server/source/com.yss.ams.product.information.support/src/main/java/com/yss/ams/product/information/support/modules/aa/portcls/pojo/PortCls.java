package com.yss.ams.product.information.support.modules.aa.portcls.pojo;

import java.math.BigDecimal;
import java.util.Date;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 分级产品pojo类
 * 
 * Added by shiliang,产品管理组件拆分2017-06-23
 * 
 * */

////implements IEffectivable
public class PortCls extends AuditableParamPojo {

	/**
	 * 组合代码
	 */
	private String c_PORT_CODE = "";

	/**
	 * 分级组合代码
	 */
	private String c_PORT_CLS_CODE = "";

	/**
	 * 上级分级组合
	 */
	private String c_PORT_CLS_CODE_P = "";

	/**
	 * 分级组合名称
	 */
	private String c_PORT_CLS_NAME = "";

	/**
	 * 分级类型
	 */
	private String c_DV_PORT_CLS_TYPE = "";

	/**
	 * 分级级别
	 */
	private String c_DV_PORT_CLS_LEVEL = "";

	/**
	 * 计算公式
	 */
	private String c_ALGO_CODE = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 轧差
	 */
	private String c_DV_NETTING = "";

	/**
	 * 由于取消IEffectivable 接口 所以要用以下字段表示 开始日期 成立日期
	 */
	private Date d_TO_LIST;

	/**
	 * 终止日期
	 */
	private Date d_OFF_LIST;

	/**
	 * 级别类型
	 */
	private String c_DV_PORT_CLS = "";

	/**
	 * 收益分配
	 */
	private String c_DV_INC_DIS = "";

	/**
	 * 分级币种
	 */
	private String c_DC_CODE = "";

	/**
	 * 年化收益率
	 * 
	 * @author tangshifeng 20130613
	 */
	private BigDecimal n_YEAR_INCOME = BigDecimal.ZERO;;

	/**
	 * 收益率公式
	 * 
	 * @author tangshifeng 20130613
	 */
	private String c_ALGO_CODE_I = "";

	/**
	 * 收益率类型
	 * 
	 * @author meipeng 20131224
	 */
	private String c_INCOME_TYPE = "";

	/**
	 * 比率公式
	 * 
	 * @author meipeng 20131224
	 */
	private String c_FORMULA_CODE = "";

	 /// <summary>
    /// 清盘日期
    /// add by wjj STORY #22055
    /// 20150411
    /// </summary>
    private Date d_LIQUID_DATE;
    
    /**
     * 收益转份额
     */
    private String c_FEJZ_PARAM = "";
    
    /**
     * 月份
     */
    private String c_FEJZ_MONTH = "";
    
    /**
     * 天数
     */
    private int n_FEJZ_DAY = 0;

    /**
	 * 数据来源
	 * add by qinxinglin STORY #39626 【广发证券】分级产品参数导入的问题
	 * 20170614
	 */
	private String c_DATA_IDF = "";

	/**
	 * 接口代码
	 * add by qinxinglin STORY #39626 【广发证券】分级产品参数导入的问题
	 * 20170614
	 */
	private String c_CFG_CODE = "";
    
	 private String c_DV_LevelNETTING;
	
	/**
	 * 制作时间
	 * add by zimingliang STORY #78385 嘉实基金-紧急-嘉实离岸组合分级、计息公式、计费公式需求处理
	 * 20170614
	 */
	private String c_UPDATE_TIME = "";
	
	/*
     * 信用评级 add by lujianhao 20180705 STORY #51721 光大证券-监管类信息完善
     */
    private String c_XYPJ = "";
    
    /**
     * 期货公司
     */
    private String c_DV_QHGS = "";
    
    /**
     * 分级编码
     */
    private String c_FJBM = "";
    
    /**
     * 销售对象
     */
    private String c_XSDX = "";
    
    /**
     * 同业客户类型
     */
    private String c_TYKHLX = "";
    
	public String getC_UPDATE_TIME() {
		return c_UPDATE_TIME;
	}

	public void setC_UPDATE_TIME(String c_UPDATE_TIME) {
		this.c_UPDATE_TIME = c_UPDATE_TIME;
	}
	public String getC_DV_LevelNETTING() {
		return c_DV_LevelNETTING;
	}

	public void setC_DV_LevelNETTING(String c_DV_LevelNETTING) {
		this.c_DV_LevelNETTING = c_DV_LevelNETTING;
	}
		
	public String getC_FEJZ_PARAM() {
		return c_FEJZ_PARAM;
	}

	public void setC_FEJZ_PARAM(String c_FEJZ_PARAM) {
		this.c_FEJZ_PARAM = c_FEJZ_PARAM;
	}

	public String getC_FEJZ_MONTH() {
		return c_FEJZ_MONTH;
	}

	public void setC_FEJZ_MONTH(String c_FEJZ_MONTH) {
		this.c_FEJZ_MONTH = c_FEJZ_MONTH;
	}

	public int getN_FEJZ_DAY() {
		return n_FEJZ_DAY;
	}

	public void setN_FEJZ_DAY(int n_FEJZ_DAY) {
		this.n_FEJZ_DAY = n_FEJZ_DAY;
	}

	public Date getD_LIQUID_DATE() {
		return d_LIQUID_DATE;
	}

	public void setD_LIQUID_DATE(Date d_LIQUID_DATE) {
		this.d_LIQUID_DATE = d_LIQUID_DATE;
	}
	
	public String getC_ALGO_CODE_I() {
		return c_ALGO_CODE_I;
	}

	public BigDecimal getN_YEAR_INCOME() {
		return n_YEAR_INCOME;
	}

	public void setN_YEAR_INCOME(BigDecimal n_YEAR_INCOME) {
		this.n_YEAR_INCOME = n_YEAR_INCOME;
	}

	public void setC_ALGO_CODE_I(String c_ALGO_CODE_I) {
		this.c_ALGO_CODE_I = c_ALGO_CODE_I;
	}

	private static final long serialVersionUID = 1L;

	public String getC_PORT_CODE() {
		return c_PORT_CODE;
	}

	public void setC_PORT_CODE(String cPORTCODE) {
		c_PORT_CODE = cPORTCODE;
	}

	public String getC_PORT_CLS_CODE() {
		return c_PORT_CLS_CODE;
	}

	public void setC_PORT_CLS_CODE(String cPORTCLSCODE) {
		c_PORT_CLS_CODE = cPORTCLSCODE;
	}

	public String getC_PORT_CLS_NAME() {
		return c_PORT_CLS_NAME;
	}

	public void setC_PORT_CLS_NAME(String cPORTCLSNAME) {
		c_PORT_CLS_NAME = cPORTCLSNAME;
	}

	public String getC_DV_PORT_CLS_TYPE() {
		return c_DV_PORT_CLS_TYPE;
	}

	public void setC_DV_PORT_CLS_TYPE(String cDVPORTCLSTYPE) {
		c_DV_PORT_CLS_TYPE = cDVPORTCLSTYPE;
	}

	public String getC_DV_PORT_CLS_LEVEL() {
		return c_DV_PORT_CLS_LEVEL;
	}

	public void setC_DV_PORT_CLS_LEVEL(String cDVPORTCLSLEVEL) {
		c_DV_PORT_CLS_LEVEL = cDVPORTCLSLEVEL;
	}

	public String getC_ALGO_CODE() {
		return c_ALGO_CODE;
	}

	public void setC_ALGO_CODE(String cALGOCODE) {
		c_ALGO_CODE = cALGOCODE;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_DV_PORT_CLS() {
		return c_DV_PORT_CLS;
	}

	public void setC_DV_PORT_CLS(String cDVPORTCLS) {
		c_DV_PORT_CLS = cDVPORTCLS;
	}

	public String getC_DC_CODE() {
		return c_DC_CODE;
	}

	public void setC_DC_CODE(String cDCCODE) {
		c_DC_CODE = cDCCODE;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String bizPrimeKeyNames() {
		// TODO Auto-generated method stub
		return "c_PORT_CLS_CODE,c_PORT_CODE";
	}

	public String getC_PORT_CLS_CODE_P() {
		return c_PORT_CLS_CODE_P;
	}

	public void setC_PORT_CLS_CODE_P(String c_PORT_CLS_CODE_P) {
		this.c_PORT_CLS_CODE_P = c_PORT_CLS_CODE_P;
	}

	public String getC_DV_NETTING() {
		return c_DV_NETTING;
	}

	public void setC_DV_NETTING(String c_DV_NETTING) {
		this.c_DV_NETTING = c_DV_NETTING;
	}

	public String getC_DV_INC_DIS() {
		return c_DV_INC_DIS;
	}

	public void setC_DV_INC_DIS(String c_INC_DIS) {
		this.c_DV_INC_DIS = c_INC_DIS;
	}

	public String getC_INCOME_TYPE() {
		return c_INCOME_TYPE;
	}

	public void setC_INCOME_TYPE(String c_INCOME_TYPE) {
		this.c_INCOME_TYPE = c_INCOME_TYPE;
	}

	public String getC_FORMULA_CODE() {
		return c_FORMULA_CODE;
	}

	public void setC_FORMULA_CODE(String c_FORMULA_CODE) {
		this.c_FORMULA_CODE = c_FORMULA_CODE;
	}

	public Date getD_TO_LIST() {
		return d_TO_LIST;
	}

	public void setD_TO_LIST(Date dTOLIST) {
		d_TO_LIST = dTOLIST;
	}

	public Date getD_OFF_LIST() {
		return d_OFF_LIST;
	}

	public void setD_OFF_LIST(Date dOFFLIST) {
		d_OFF_LIST = dOFFLIST;
	}
	
	public String getC_DATA_IDF() {
		return c_DATA_IDF;
	}

	public void setC_DATA_IDF(String cDATAIDF) {
		c_DATA_IDF = cDATAIDF;
	}

	public String getC_CFG_CODE() {
		return c_CFG_CODE;
	}

	public void setC_CFG_CODE(String cCFGCODE) {
		c_CFG_CODE = cCFGCODE;
	}

	public String getC_XYPJ() {
		return c_XYPJ;
	}

	public void setC_XYPJ(String c_XYPJ) {
		this.c_XYPJ = c_XYPJ;
	}
	
	public String getC_DV_QHGS() {
		return c_DV_QHGS;
	}

	public void setC_DV_QHGS(String c_DV_QHGS) {
		this.c_DV_QHGS = c_DV_QHGS;
	}
	
	public String getC_FJBM() {
		return c_FJBM;
	}

	public void setC_FJBM(String c_FJBM) {
		this.c_FJBM = c_FJBM;
	}

	public String getC_XSDX() {
		return c_XSDX;
	}

	public void setC_XSDX(String c_XSDX) {
		this.c_XSDX = c_XSDX;
	}

	public String getC_TYKHLX() {
		return c_TYKHLX;
	}

	public void setC_TYKHLX(String c_TYKHLX) {
		this.c_TYKHLX = c_TYKHLX;
	}
}
