package com.yss.uco.elecreco.automic.util;

import java.util.List;

public class FieldCheck {
	
	private String startStr = "";
	
	private String splitStr = "";
	
	private StringBuffer result = new StringBuffer();
	
	public FieldCheck(String startStr, String splitStr) {
		super();
		this.startStr = startStr;
		this.splitStr = splitStr;
	}

	public FieldCheck checkListNull(List<String> list,String msg)
	{
		if(list == null || list.size() == 0)
		{
			result.append(startStr).append(msg).append(splitStr);
		}
		return this;
	}

	public StringBuffer getResult() {
		return result;
	}
	
	public String getResultStr() {
		if(result.toString().endsWith(splitStr))
		{
			return result.substring(0, result.lastIndexOf(splitStr));
		}else
		{
			return result.toString();
		}
	}

}
