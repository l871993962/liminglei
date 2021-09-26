package com.yss.ams.product.information.support.modules.ab.portrela.pojo;

public class PortRelaCashAcc extends PortRela{

	/**
	 * 产品关联信息_现金账户信息
	 * STORY #37768 【南方基金】组合可以设置绑定多个现金账户，当有界面筛选现金账户时，只显示绑定的现金账户
	 * add by guohui 2017-09-04
	 */
	private static final long serialVersionUID = 1L;
	
	private String c_CA_CODE_A = "";     			//账户代码(区别主表的c_CA_CODE)
	private String c_CA_NAME = "";     				//账户名称
	private String c_DV_ACC_TYPE = "";              //账户类型
	private String c_DC_CODE = "";                  //货币代码
	private String c_DV_ACC_NATURE = "";            //账户性质
	private String c_DESC_A = "";            		//描述        (区别主表的C_DESE)
	
	public String getC_CA_CODE_A() {
		return c_CA_CODE_A;
	}
	public void setC_CA_CODE_A(String c_CA_CODE_A) {
		this.c_CA_CODE_A = c_CA_CODE_A;
	}
	public String getC_CA_NAME() {
		return c_CA_NAME;
	}
	public void setC_CA_NAME(String c_CA_NAME) {
		this.c_CA_NAME = c_CA_NAME;
	}
	public String getC_DV_ACC_TYPE() {
		return c_DV_ACC_TYPE;
	}
	public void setC_DV_ACC_TYPE(String c_DV_ACC_TYPE) {
		this.c_DV_ACC_TYPE = c_DV_ACC_TYPE;
	}
	public String getC_DC_CODE() {
		return c_DC_CODE;
	}
	public void setC_DC_CODE(String c_DC_CODE) {
		this.c_DC_CODE = c_DC_CODE;
	}
	public String getC_DV_ACC_NATURE() {
		return c_DV_ACC_NATURE;
	}
	public void setC_DV_ACC_NATURE(String c_DV_ACC_NATURE) {
		this.c_DV_ACC_NATURE = c_DV_ACC_NATURE;
	}
	public String getC_DESC_A() {
		return c_DESC_A;
	}
	public void setC_DESC_A(String c_DESC_A) {
		this.c_DESC_A = c_DESC_A;
	}
	
}
