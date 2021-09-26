package com.yss.uco.elecreco.er.mrapi;

import java.io.File;

import com.yss.framework.api.exception.YssException;
import com.yss.framework.api.util.DateUtil;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JAXBProcessor;
import com.yss.framework.api.util.WebResourceLoader;
import com.yss.uco.elecreco.er.erdata.service.impl.ErDataService;
import com.yss.uco.elecreco.support.dzdz.common.pojo.XmlFile;

/**
 * 
 * @author weijj
 * 
 */
public class ErFileService {
	private static final String path = WebResourceLoader
			.getResourcePath("../../file/er/");

	public String getFile(XmlFile root) throws YssException {
		String result = "";
		if (root != null) {
//			JAXBProcessor jproc = new JAXBProcessor();
//			String cErrInfo = root.getC_ERR_INFO();
			result = JAXBProcessor.marshalWithReturnString(root.getClass(), root,
					"UTF-8");
			result = result.replace("<失败原因>", "<![CDATA[提示信息:");
			result = result.replace("</失败原因>", "]]>");
		} else {
			result = "<?xml version=\"1.0\" encoding = \"UTF-8\"?>\r\n<提示信息>请设置对账参数！</提示信息>";
		}
		return result;
	}

	public static String saveFile(String data, String fileName) throws Exception {
		FileStorePathUtil fspu = new FileStorePathUtil("");
		///YSS_APP路径
		String saveTime = "_"+DateUtil.getNow("yyyyMMdd-HHmmssSSS")+".xml";
		File file = new File(fspu.getFilePath() + fileName + saveTime);
		FileUtil fu = null;
		try {
			String dir = file.getPath().replaceAll(file.getName(), "");
			fu = new FileUtil(dir, file.getName());
			if (!file.exists()) {
				file.createNewFile();
			}
			fu.write(data);
		} catch (Exception e) {
			throw e;
		} finally {
			if (fu != null) {
				fu.close();
			}
		}
		return "";
	}

	public String getFileContent(String fsn, String fileType, String cAssCode)
			throws YssException {
		String fileName = path + fsn + "_" + fileType + ".xml";
		File file = new File(fileName);
		String content = "";
		if (file.exists()) {
			content = FileUtil.readFileByLines(fileName);
		}
		if (content.isEmpty()) {
			ErDataService dservice = new ErDataService();
			content = getFile(dservice.getXmlFileRoot(fsn, fileType, cAssCode));
		}
		return content;
	}
}
