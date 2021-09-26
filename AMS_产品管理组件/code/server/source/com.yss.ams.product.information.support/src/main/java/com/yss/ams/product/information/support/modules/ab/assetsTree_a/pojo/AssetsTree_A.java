package com.yss.ams.product.information.support.modules.ab.assetsTree_a.pojo;

import com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.pojo.AssetsTree_A_Rule;
import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * <A区资产树型结构>实体类
 * 
 * 2017-06-23
 * STORY #42921 产品信息组件拆分开发
 * @author HeLiang
 *
 */
public class AssetsTree_A extends AuditableParamPojo {
	/**
	 * 结构代码
	 */
	private String c_TR_CODE = "";

	/**
	 * 结构名称
	 */
	private String c_TR_NAME = "";

	/**
	 * 上级结构
	 */
	private String c_TR_CODE_P = "";

	/**
	 * 分类规则
	 */
	private String c_DV_TR = "";

	/**
	 * 描述
	 */
	private String c_DESC = "";

	/**
	 * 顶级节点代码
	 */
	private String c_TR_CODE_R = "";
	
	/**
	 * 用户
	 */
	private String c_USER = "";
	
	/**
	 * add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
	 * 显示未分配
	 */
	private String c_DV_UN_DIS = "";
	
	/**
	 * add by zhoushuhang 2018-03-09 STORY49928产品树形结构界面优化
	 * 记录产品类型执行顺序
	 */
	private int n_ORDER = 0;
	
	/**
	 * 自动转入类型
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 */
	private String c_AUTO_ZR_TYPE = "";
	
	/**
	 * 分类规则
	 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
	 * add by yangru 20190717
	 */
	private AssetsTree_A_Rule assetsTreeARule= null;

	private static final long serialVersionUID = 1L;

	public String getC_TR_CODE() {
		return c_TR_CODE;
	}

	public void setC_TR_CODE(String cTRCODE) {
		c_TR_CODE = cTRCODE;
	}

	public String getC_TR_NAME() {
		return c_TR_NAME;
	}

	public void setC_TR_NAME(String cTRNAME) {
		c_TR_NAME = cTRNAME;
	}

	public String getC_TR_CODE_P() {
		return c_TR_CODE_P;
	}

	public void setC_TR_CODE_P(String cTRCODEP) {
		c_TR_CODE_P = cTRCODEP;
	}

	public String getC_DV_TR() {
		return c_DV_TR;
	}

	public void setC_DV_TR(String cDVTR) {
		c_DV_TR = cDVTR;
	}

	public String getC_DESC() {
		return c_DESC;
	}

	public void setC_DESC(String cDESC) {
		c_DESC = cDESC;
	}

	public String getC_TR_CODE_R() {
		return c_TR_CODE_R;
	}

	public void setC_TR_CODE_R(String cTRCODER) {
		c_TR_CODE_R = cTRCODER;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getC_DV_UN_DIS() {
		return c_DV_UN_DIS;
	}

	public void setC_DV_UN_DIS(String c_DV_UN_DIS) {
		this.c_DV_UN_DIS = c_DV_UN_DIS;
	}

	public int getN_ORDER() {
		return n_ORDER;
	}

	public void setN_ORDER(int n_ORDER) {
		this.n_ORDER = n_ORDER;
	}
	
	public String getC_USER() {
		return c_USER;
	}

	public void setC_USER(String c_USER) {
		this.c_USER = c_USER;
	}
	
	public AssetsTree_A_Rule getAssetsTreeARule() {
		return assetsTreeARule;
	}

	public void setAssetsTreeARule(AssetsTree_A_Rule assetsTreeARule) {
		this.assetsTreeARule = assetsTreeARule;
	}

	public String getC_AUTO_ZR_TYPE() {
		return c_AUTO_ZR_TYPE;
	}

	public void setC_AUTO_ZR_TYPE(String c_AUTO_ZR_TYPE) {
		this.c_AUTO_ZR_TYPE = c_AUTO_ZR_TYPE;
	}
}
