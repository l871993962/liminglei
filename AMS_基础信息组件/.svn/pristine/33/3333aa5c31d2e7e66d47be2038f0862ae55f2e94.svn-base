package com.yss.ifa.szt.tool.log;

import java.io.File;
import java.io.FilenameFilter;

public class MyFileNameFilter implements FilenameFilter{

	String contains = "";
	public MyFileNameFilter(String contains){
		this.contains = contains;
	}
	@Override
	public boolean accept(File dir, String name) {
		return name.contains(contains);
	}

}
