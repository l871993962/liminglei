package com.yss.uco.elecreco.er.template.pojo;

import com.yss.framework.api.common.co.BasePojo;

public class FileStreamParam extends BasePojo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 993998879294879709L;

	/**
	 * 文件名称
	 */
	private String fileName = "";

	/**
	 * 文件路径
	 */
	private String filePath = "";

	/**
	 * 文件流
	 */
	private byte[] fileData = null;

	/**
	 * 数据块的索引，当partIndex=0表示是文件的第一部分
	 */
	private int partIndex = -1;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public byte[] getFileData() {
		return fileData;
	}

	public void setFileData(byte[] fileData) {
		this.fileData = fileData;
	}

	public int getPartIndex() {
		return partIndex;
	}

	public void setPartIndex(int partIndex) {
		this.partIndex = partIndex;
	}

}
