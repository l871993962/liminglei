package com.yss.ams.product.information.support.modules.cp.fax.pojo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.common.co.AuditableParamPojo;

public class BaseSealRelaInfo extends AuditableParamPojo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 关联的签章代码
	 */
	private String c_SEAL_CODE;
	
	/**
	 * 对应的功能菜单
	 */
	private String c_FUN_CODE;
	
	/**
	 * 对应关联的扩展的类名称
	 */
	private String c_RELA_CLASS;

	/**
	 * 页面显示类型
	 */
	private String c_Dis_Page;
	
	/**
	 * 签章位置
	 */
	private String c_Position;
	
	/**
	 * 公章X轴边距
	 */
	private int n_MarginX;
	
	/**
	 * 公章Y轴边距
	 */
	private int n_MarginY;
	
	
	/**
	 * 签章宽度
	 */
	private int n_Width;
	
	/**
	 * 签章高度
	 */
	private int n_Height;
	
	/**
	 * String形式的扩展属性
	 */
	private Map<String, String> ExtAttrDict = new HashMap<String, String>();
	
	/**
	 * List形式的扩展属性
	 */
	private Map<String, List<String>> extListAttrDict = new HashMap<String, List<String>>();

	public String getC_SEAL_CODE() {
		return c_SEAL_CODE;
	}

	public void setC_SEAL_CODE(String c_SEAL_CODE) {
		this.c_SEAL_CODE = c_SEAL_CODE;
	}

	public String getC_FUN_CODE() {
		return c_FUN_CODE;
	}

	public void setC_FUN_CODE(String c_FUN_CODE) {
		this.c_FUN_CODE = c_FUN_CODE;
	}

	public String getC_Dis_Page() {
		return c_Dis_Page;
	}

	public void setC_Dis_Page(String c_Dis_Page) {
		this.c_Dis_Page = c_Dis_Page;
	}

	public String getC_Position() {
		return c_Position;
	}

	public void setC_Position(String c_Position) {
		this.c_Position = c_Position;
	}

	public int getN_MarginX() {
		return n_MarginX;
	}

	public void setN_MarginX(int n_MarginX) {
		this.n_MarginX = n_MarginX;
	}

	public int getN_MarginY() {
		return n_MarginY;
	}

	public void setN_MarginY(int n_MarginY) {
		this.n_MarginY = n_MarginY;
	}

	public int getN_Width() {
		return n_Width;
	}

	public void setN_Width(int n_Width) {
		this.n_Width = n_Width;
	}

	public int getN_Height() {
		return n_Height;
	}

	public void setN_Height(int n_Height) {
		this.n_Height = n_Height;
	}

	public Map<String, String> getExtAttrDict() {
		return ExtAttrDict;
	}

	public void setExtAttrDict(Map<String, String> extAttrDict) {
		ExtAttrDict = extAttrDict;
	}

	public Map<String, List<String>> getExtListAttrDict() {
		return extListAttrDict;
	}

	public void setExtListAttrDict(Map<String, List<String>> extListAttrDict) {
		this.extListAttrDict = extListAttrDict;
	}

	public String getC_RELA_CLASS() {
		return c_RELA_CLASS;
	}

	public void setC_RELA_CLASS(String c_RELA_CLASS) {
		this.c_RELA_CLASS = c_RELA_CLASS;
	}
	
	
}
