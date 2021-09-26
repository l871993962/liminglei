package com.yss.uco.elecreco.er.template.util;

import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.List;

import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipFile;
import org.apache.tools.zip.ZipOutputStream;

import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;

/**
 * 此类用于ZIP文件的压缩与解压缩实现
 * 
 * @author leeyu
 * @date 2013-2-26
 * @version V4.5.0.1
 */
public class ZipTool {

	private static Logger logger = LogManager.getLogger(ZipTool.class);
	/**
	 * 压缩一组文件
	 * 
	 * @param listFiles
	 *            待压缩的文件列表
	 * @param zipFile
	 *            ZIP包的全路径（文件名与路径）
	 * @return 执行状态，若成功 返回真值
	 */
	public static boolean Zip(List<String> listFiles, String zipFile)
			throws Exception {
		boolean bResult = false;
		File file = null;
		ZipOutputStream output = null; // 定义输出流
		FileInputStream input = null; // 定义读包内项内容的流
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(zipFile);
			output = new ZipOutputStream(fileOutputStream);
			output.setEncoding("GBK");
			for (String fileName : listFiles) {
				file = new File(fileName);
				if (file.exists()) {
					output.putNextEntry(new ZipEntry(file.getName()));
					input = new FileInputStream(file);
					int readLen = 0;
					byte[] buffer = new byte[1024 * 8];
					while ((readLen = input.read(buffer, 0, 1024 * 8)) != -1){
						output.write(buffer, 0, readLen);
					}
					closeStream(input);
				}
			}
			output.flush();
			closeStream(output);
			closeStream(fileOutputStream);
			bResult = true;
		} catch (Exception ex) {
			bResult = false;
		} finally {
			closeStream(input);
			closeStream(output);
			closeStream(fileOutputStream);
		}

		return bResult;
	}

	/**
	 * 解压缩一个ZIP文件并将文件放到newDir目录下面
	 * 
	 * @param zipFile
	 *            ZIP包的全路径（文件名与路径）
	 * @param newDir
	 *            解压缩后的文件夹路径
	 * @return 执行状态，若成功 返回真值
	 */
	public static boolean UnZip(String zipFile, String newDir) throws Exception {
		boolean bResult = false;
		// 定义输入输出流对象
		InputStream input = null;
		OutputStream output = null;
		ZipFile zip = null;
		try {
			// 创建文件对象
			File file = new File(zipFile);
			if (file == null || !file.exists()) {
				return bResult;
			}

			// 创建zip文件对象
			zip = new ZipFile(zipFile,"GBK");
			// 创建本zip文件解压目录
			File unzipFile = new File(newDir);
			if (unzipFile.exists())
				unzipFile.delete();
			unzipFile.mkdirs();
			// 得到zip文件条目枚举对象
			Enumeration<?> zipEnum = zip.getEntries();
			// 定义对象
			ZipEntry entry = null;
			String entryName = null;
			String names[] = null;
			int length;
			// 循环读取条目
			while (zipEnum.hasMoreElements()) {
				// 得到当前条目
				entry = (ZipEntry) zipEnum.nextElement();
				entryName = entry.getName();
				// 用/分隔条目名称
				names = entryName.split("\\/");
				length = names.length;
				for (int v = 0; v < length; v++) {
					input = zip.getInputStream(entry);
					File file2 = new File(unzipFile.getAbsolutePath() + "/" + entryName);
					try {
						output = new FileOutputStream(file2);

						int nLen = (int) entry.getSize();
						byte[] buffer = new byte[nLen];
						int count = -1;
						while ((count = input.read(buffer, 0, nLen)) > -1) {
							output.write(buffer, 0, count);
						}
						output.flush();
						closeStream(input);
						closeStream(output);
					} catch (Exception ex) {
						logger.error("创建文件流失败！", ex);
						closeStream(output);
						continue;
					}finally{
						closeStream(output);
					}
				}
				
				entry.clone();
			}
			bResult = true;
		} catch (Exception ex) {
			bResult = false;
		} finally {
			closeStream(input);
			closeStream(output);
			closeStream(zip);
		}
		return bResult;
	}
	
	private static void closeStream(Closeable stream){
		if (stream != null){
			try {
				stream.close();
			} catch (Exception e) {
				logger.error("关闭流出错！", e);
			}
		}
	}
}
