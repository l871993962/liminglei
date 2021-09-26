package com.yss.ams.product.information.support.modules.ab.portrela.pojo;

/**
 * 
 * @author liuxiang 2016-5-20 STORY #28860 上交所跨市场ETF基金产品整体需求
 *
 */
public class PortRelaMember extends PortRela {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 会员代码
	 */
	private String c_MBR_CODE = "";

	/**
     * 机构代码
     */
    private String c_ORG_CODE = "";

    /**
     * 券商代码
     */
    private String c_BROKER_CODE = "";

	public String getC_MBR_CODE() {
		return c_MBR_CODE;
	}

	public void setC_MBR_CODE(String c_MBR_CODE) {
		this.c_MBR_CODE = c_MBR_CODE;
	}

	public String getC_ORG_CODE() {
		return c_ORG_CODE;
	}

	public void setC_ORG_CODE(String c_ORG_CODE) {
		this.c_ORG_CODE = c_ORG_CODE;
	}

	public String getC_BROKER_CODE() {
		return c_BROKER_CODE;
	}

	public void setC_BROKER_CODE(String c_BROKER_CODE) {
		this.c_BROKER_CODE = c_BROKER_CODE;
	}
}
