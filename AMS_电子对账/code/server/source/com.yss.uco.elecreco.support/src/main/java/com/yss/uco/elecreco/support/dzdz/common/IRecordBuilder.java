package com.yss.uco.elecreco.support.dzdz.common;

import java.sql.Connection;
import java.util.Map;

import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlRoot;

/**
 * 创建具体数据
 * @author weijj
 *
 */
public interface IRecordBuilder {
	XmlRoot setRecords(Connection conn,XmlRoot root,Map<String, String> kmMap);

	XmlFile setRecords(Connection conn, XmlFile root);
	
}
