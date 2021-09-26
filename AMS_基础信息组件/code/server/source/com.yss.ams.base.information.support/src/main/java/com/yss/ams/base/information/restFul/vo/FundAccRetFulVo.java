package com.yss.ams.base.information.restFul.vo;

import java.io.File;
import java.util.List;

import com.yss.ams.base.information.support.bi.account.pojo.FundAcc;
import com.yss.framework.api.restful.annotations.ControllerMixArgu;
import com.yss.platform.support.enclosure.pojo.DataEnclosure;
@ControllerMixArgu
public class FundAccRetFulVo {

	private String orgCode;
	private FundAcc pojo;
	private String portCode;
	private String remoteFilePath; 
	private File cjdFile;
	private String userCode;
	private String type;
	private List<FundAcc> pojoList;
	private List<String> idList; 
	private List<DataEnclosure> delFileList;
	private List<DataEnclosure> addFileList;
	private List<String> types;
	private String accAddr;
	
	
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		this.types = types;
	}
	public String getAccAddr() {
		return accAddr;
	}
	public void setAccAddr(String accAddr) {
		this.accAddr = accAddr;
	}
	public String getOrgCode() {
		return orgCode;
	}
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}
	public List<FundAcc> getPojoList() {
		return pojoList;
	}
	public void setPojoList(List<FundAcc> pojoList) {
		this.pojoList = pojoList;
	}
	public FundAcc getPojo() {
		return pojo;
	}
	public void setPojo(FundAcc pojo) {
		this.pojo = pojo;
	}
	public String getPortCode() {
		return portCode;
	}
	public void setPortCode(String portCode) {
		this.portCode = portCode;
	}
	public String getRemoteFilePath() {
		return remoteFilePath;
	}
	public void setRemoteFilePath(String remoteFilePath) {
		this.remoteFilePath = remoteFilePath;
	}
	public File getCjdFile() {
		return cjdFile;
	}
	public void setCjdFile(File cjdFile) {
		this.cjdFile = cjdFile;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public List<DataEnclosure> getDelFileList() {
		return delFileList;
	}
	public void setDelFileList(List<DataEnclosure> delFileList) {
		this.delFileList = delFileList;
	}
	public List<DataEnclosure> getAddFileList() {
		return addFileList;
	}
	public void setAddFileList(List<DataEnclosure> addFileList) {
		this.addFileList = addFileList;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<String> getIdList() {
		return idList;
	}
	public void setIdList(List<String> idList) {
		this.idList = idList;
	}
	
	
}
