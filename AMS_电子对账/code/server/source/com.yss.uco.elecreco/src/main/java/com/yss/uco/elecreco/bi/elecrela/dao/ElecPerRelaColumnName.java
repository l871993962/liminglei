package com.yss.uco.elecreco.bi.elecrela.dao;

public enum ElecPerRelaColumnName {
	
	c_ZB_CODE("C_ZB_CODE"),
	
	c_ZB_NAME("C_ZB_NAME"),
	
	c_DZ_CODE("C_DZ_CODE"),
	
	c_PORT_CODE("C_PORT_CODE"),
	
	c_SEND_MODE("C_SEND_MODE"),
	
	c_ZB_KEY("C_ZB_KEY"),
	
	c_ZB_VALUE("C_ZB_VALUE"),
	   
	c_ZB_ELEMENT1 ("c_ZB_ELEM1"),
        
    c_ZB_VALUE1("c_ZB_VALUE1"),

    c_ZB_ELEMENT2 ("c_ZB_ELEM2"),

    c_ZB_VALUE2("c_ZB_VALUE2"),

    c_ZB_ELEMENT3 ("c_ZB_ELEM3"),

    c_ZB_VALUE3("c_ZB_VALUE3"),

    c_ZB_ELEMENT4 ("c_ZB_ELEM4"),

    c_ZB_VALUE4("c_ZB_VALUE4"),

    c_ZB_ELEMENT5 ("c_ZB_ELEM5"),

    c_ZB_VALUE5("c_ZB_VALUE5"),

    c_ZB_ELEMENT6 ("c_ZB_ELEM6"),

    c_ZB_VALUE6("c_ZB_VALUE6"),

    c_ZB_ELEMENT7 ("c_ZB_ELEM7"),

    c_ZB_VALUE7("c_ZB_VALUE7"),

    c_ZB_ELEMENT8 ("c_ZB_ELEM8"),

    c_ZB_VALUE8("c_ZB_VALUE8"),

    c_ZB_ELEMENT9 ("c_ZB_ELEM9"),

    c_ZB_VALUE9("c_ZB_VALUE9"),

    id("C_IDEN"),
    
    startUseDate(""),

    endUseDate(""),

	operator("C_CHECK_BY"),

	auditDate("C_CHECK_TIME"),

	modifier("C_UPDATE_BY"),

	modifyDate("C_UPDATE_TIME"),

	auditState("N_CHECK_STATE");
	
	private String value ;

	private ElecPerRelaColumnName(String value){
		this.value = value;
	}

	public String toString(){
		return this.value.toString();
	}
}
