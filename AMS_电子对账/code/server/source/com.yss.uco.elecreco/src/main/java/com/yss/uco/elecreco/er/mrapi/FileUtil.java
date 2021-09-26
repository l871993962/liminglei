package com.yss.uco.elecreco.er.mrapi;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;

public class FileUtil {
	private FileOutputStream outputStream;
	private int flushCount = 1;
	private String dir;
	private String fileName;

	public FileUtil(String dir, String fileName) throws Exception {
		this.dir = dir;
		this.fileName = fileName;
		this.delete();
		File file = new File(dir);
		if (!file.exists()) {
			file.mkdirs();
		}
		openOutStream();
	}

	public FileUtil delete() throws Exception {
		File file = new File(dir + fileName);
		file.delete();
		// file.createNewFile();
		return this;
	}

	public void openOutStream() throws Exception {
		if (outputStream != null) {
			return;
		}
		File file = new File(dir + fileName);
		outputStream = new FileOutputStream(file);
	}

	public void write(String content) throws Exception {

		flushCount++;
		outputStream.write(content.getBytes());
		if (flushCount >= 100) {
			outputStream.flush();
			flushCount = 0;
		}

	}

	public static void deleteDir(File file, boolean deleSelf) {
		if (file != null && file.isDirectory()) {
			File[] files = file.listFiles();
			if (files != null) {
				for (File f : files) {
					FileUtil.deleteDir(f, true);
				}
			}
			long fileTime = System.currentTimeMillis() - file.lastModified();
			long day = fileTime / 3600 / 24;
			if (day < 10) {
				return;
			}
			if (deleSelf) {
				file.delete();
			}
		}
		if (file != null && file.isFile()) {
			file.delete();
		}

	}

	public void close() {
		try {
			if (outputStream != null) {
				outputStream.flush();
				outputStream.close();
			}
			outputStream = null;
		} catch (Exception ex) {
			logger.error("关闭出错:" + ex.getMessage(), ex);
		}
	}
	
	private static Logger logger = LogManager.getLogger(FileUtil.class);

	/**
	 * 以行 为单位读取文件，常用于读面向行的格式化文件
	 * 删除重建的方式
	 */
	public static void writeFile(String path, String content) {
		BufferedWriter output = null;
		try {
			File f = new File(path);
			if (f.exists()) {
				 f.delete();
			}
			if (!f.createNewFile()) {
				logger.info("文件创建失败！文件名:"+path);
			}
			output = new BufferedWriter(new FileWriter(f));
			output.write(content);
			output.close();
		} catch (Exception e) {
			logger.error("写入数据出错:" + e.getMessage(), e);
		}finally{
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					logger.error("关闭出错:" + e.getMessage(), e);
				}
			}
		}
	}

	/**
	 * 以行为单位读取文件，常用于读面向行的格式化文件
	 */
	public static String readFileByLines(String fileName) {
		File file = new File(fileName);
		StringBuffer sb = new StringBuffer();
		BufferedReader reader = null;
		try {
			if (file.exists()) {
				reader = new BufferedReader(new FileReader(file));
				String tempString = null;
//				int line = 1;
				// 一次读入一行，直到读入null为文件结束
				while ((tempString = reader.readLine()) != null) {
					// 显示行号
					sb.append(tempString).append("\n");
//					line++;
				}
				reader.close();
			}
		} catch (IOException e) {
			logger.error("读取文件出错:" + e.getMessage(), e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("关闭出错:" + e.getMessage(), e);
				}
			}
		}
		return sb.toString();
	}
}
