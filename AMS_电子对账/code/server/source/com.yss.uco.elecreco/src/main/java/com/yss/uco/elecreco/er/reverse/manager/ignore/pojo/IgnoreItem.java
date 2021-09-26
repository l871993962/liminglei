package com.yss.uco.elecreco.er.reverse.manager.ignore.pojo;
import com.yss.framework.api.common.co.AuditableParamPojo;
public class IgnoreItem extends AuditableParamPojo  {
	private static final long serialVersionUID = 1L;

	/**
	*对账类型
	*/
	private String c_FILE_TYPE = "";
	/**
	*产品组合
	*/
	private String c_PORT_CODE = "";
	/**
	*托管机构
	*/
	private String c_TGH_CODE = "";
	/**
	*忽略类型(HL_ROW:行忽略;HL_COL:列忽略;HL_CELL:单元格忽略)
	*/
	private String c_DV_IGNORE_TYPE = "";
	/**
	*忽略方向(IGNORE_SCOPE_INNER：本方;IGNORE_SCOPE_OUT:对方)
	*/
	private String c_DV_IGNORE_SCOPE = "";
	/**
	*应用下级科目(SUB_SUIT_YES:是；SUB_SUIT_NO:否)
	*/
	private String c_DV_SUB_SUIT = "";
	/**
	*列标识
	*/
	private String c_COL_FLAG = "";
	/**
	*行标识
	*/
	private String c_ROW_FLAG = "";
	public String getC_FILE_TYPE(){
		return c_FILE_TYPE;
	}
	public void setC_FILE_TYPE(String c_FILE_TYPE){
		 this.c_FILE_TYPE = c_FILE_TYPE;
	}
	public String getC_PORT_CODE(){
		return c_PORT_CODE;
	}
	public void setC_PORT_CODE(String c_PORT_CODE){
		 this.c_PORT_CODE = c_PORT_CODE;
	}
	public String getC_TGH_CODE(){
		return c_TGH_CODE;
	}
	public void setC_TGH_CODE(String c_TGH_CODE){
		 this.c_TGH_CODE = c_TGH_CODE;
	}
	public String getC_DV_IGNORE_TYPE(){
		return c_DV_IGNORE_TYPE;
	}
	public void setC_DV_IGNORE_TYPE(String c_DV_IGNORE_TYPE){
		 this.c_DV_IGNORE_TYPE = c_DV_IGNORE_TYPE;
	}
	public String getC_DV_IGNORE_SCOPE(){
		return c_DV_IGNORE_SCOPE;
	}
	public void setC_DV_IGNORE_SCOPE(String c_DV_IGNORE_SCOPE){
		 this.c_DV_IGNORE_SCOPE = c_DV_IGNORE_SCOPE;
	}
	public String getC_DV_SUB_SUIT(){
		return c_DV_SUB_SUIT;
	}
	public void setC_DV_SUB_SUIT(String c_DV_SUB_SUIT){
		 this.c_DV_SUB_SUIT = c_DV_SUB_SUIT;
	}
	public String getC_COL_FLAG(){
		return c_COL_FLAG;
	}
	public void setC_COL_FLAG(String c_COL_FLAG){
		 this.c_COL_FLAG = c_COL_FLAG;
	}
	public String getC_ROW_FLAG(){
		return c_ROW_FLAG;
	}
	public void setC_ROW_FLAG(String c_ROW_FLAG){
		 this.c_ROW_FLAG = c_ROW_FLAG;
	}
}