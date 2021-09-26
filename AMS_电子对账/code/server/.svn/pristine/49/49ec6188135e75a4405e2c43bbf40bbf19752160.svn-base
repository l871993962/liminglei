package com.yss.uco.elecreco.support.dzdz.common;

import java.sql.Connection;
import java.util.Map;

import com.yss.ifa.szt.tool.pojo.TransPojo;
import com.yss.uco.elecreco.support.bean.ErBbInfo;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlInRoot;

/**
 * 创建报文的入口
 * @author weijj
 *
 */
public interface IBaoWenBuilder {
	XmlInRoot builderBaoWen(Connection conn,String fsn,String fileType,String portCode,Map<String, String> kmMap) throws Exception; 
	TransPojo builderTransPojo(Connection conn,String fsn,String fileType,String portCode,String gzCode, ErBbInfo bbInfo) throws Exception;
}
