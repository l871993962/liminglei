package com.yss.ams.product.information.support.modules.ab.assetsTree_a_rule.pojo;

import com.yss.framework.api.common.co.AuditableParamPojo;

/**
 * 树形结构分类规则
 * STORY #72829 资产结构新增仅包含“存续期+待发行”的组合 
 * add by yangru 20190717
 * @author lenovo
 *
 */
public class AssetsTree_A_Rule extends AuditableParamPojo {
	/**
	 * 树形结构设置id
	 */
	private String c_IDEN_RELA = "";

	/**
	 *产品时间维度
	 */
	private String c_CPSJWD = "";

	/**
	 * 产品时间维度分类层级
	 */
	private String c_CPSJWD_FLCJ = "";

	/**
	 * 资产属性维度
	 */
	private String c_ZCSXWD = "";
	
	/**
	 * 资产属性维度分类层级
	 */
	private String c_ZCSXWD_FLCJ = "";


	private static final long serialVersionUID = 1L;


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	public String getC_IDEN_RELA() {
		return c_IDEN_RELA;
	}


	public void setC_IDEN_RELA(String c_IDEN_RELA) {
		this.c_IDEN_RELA = c_IDEN_RELA;
	}


	public String getC_CPSJWD() {
		return c_CPSJWD;
	}


	public void setC_CPSJWD(String c_CPSJWD) {
		this.c_CPSJWD = c_CPSJWD;
	}


	public String getC_CPSJWD_FLCJ() {
		return c_CPSJWD_FLCJ;
	}


	public void setC_CPSJWD_FLCJ(String c_CPSJWD_FLCJ) {
		this.c_CPSJWD_FLCJ = c_CPSJWD_FLCJ;
	}


	public String getC_ZCSXWD() {
		return c_ZCSXWD;
	}


	public void setC_ZCSXWD(String c_ZCSXWD) {
		this.c_ZCSXWD = c_ZCSXWD;
	}


	public String getC_ZCSXWD_FLCJ() {
		return c_ZCSXWD_FLCJ;
	}


	public void setC_ZCSXWD_FLCJ(String c_ZCSXWD_FLCJ) {
		this.c_ZCSXWD_FLCJ = c_ZCSXWD_FLCJ;
	}
	
	


}
