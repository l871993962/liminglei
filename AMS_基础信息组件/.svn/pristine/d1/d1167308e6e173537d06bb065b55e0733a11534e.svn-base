package com.yss.ifa.szt.tool.log;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Date;
import java.util.regex.Pattern;

import com.yss.framework.api.util.DateUtil;

public class CustomFilenameFilter implements FilenameFilter {
	/**
	 * 保留天数
	 */
	private int day = 0;
	
	public CustomFilenameFilter(int day)
	{
		this.day = day;
	}

	@Override
	public boolean accept(File dir, String name) {
		String string = DateUtil.dateToString(DateUtil.nextDay(new Date(), -day), DateUtil.LONG_DATE_FORMAT);
		boolean b = Pattern.matches("\\d{4}-\\d{2}-\\d{2}",name);
		if(b && string.compareTo(name) > 0)
		{
			return true;
		}
		return false;
	}
	
}
