package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.yss.ams.bridge.support.util.FileUtil;


/**
 * 文件下载servlet,platformOSGI化之后，该SERVLET用于处理前台下载的请求，放置都被AMSServletBridge拦截
 * 
 * @author liyaojin
 */
public class DownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownloadServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String getWebRootPath(String name){
		String rootPath = "";
		/* 服务器类型 */
		if (name.lastIndexOf("Tomcat") > -1) {
			rootPath = FileUtil.getResource("../../../").getPath();
			
			File file = new File(rootPath + "/WebRoot/WEB-INF");
			if(!file.exists()){
				rootPath = FileUtil.getResource("../../").getPath();
			}
			
			if(new File(rootPath+"/WebRoot").exists()){
				rootPath += "/WebRoot";
			}
		} else if (name.lastIndexOf("WebSphere") > -1) {
			rootPath = FileUtil.getResource("../../").getPath();
		} else if(name.lastIndexOf("WebLogic") > -1) {
			rootPath = FileUtil.getResource("../../").getPath();
		}
		return rootPath;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String requestUrl = request.getRequestURL().toString();
		// 解析文件下载的路径
		String contextPath = request.getContextPath();
		String downloadPath = requestUrl.substring(
				requestUrl.indexOf(contextPath) + contextPath.length() + 1,
				requestUrl.length());
		downloadPath = downloadPath.replaceAll("\\+", "%2b");
		downloadPath = URLDecoder.decode(downloadPath, "utf-8");
		if (null != downloadPath && !"".equals(downloadPath)) {
			String serverPath = getWebRootPath(request.getSession().getServletContext().getServerInfo());
			// 开发环境会包含webroot文件夹路径，直接替换为空
//			if (serverPath.toLowerCase().contains("webroot")) {
//				serverPath = serverPath.substring(0,
//						serverPath.lastIndexOf(File.separator));
//				serverPath = serverPath.substring(0,
//						serverPath.lastIndexOf(File.separator));
//			}
			downloadPath = serverPath + File.separator + downloadPath;
		}
		if (downloadPath.indexOf("\\") != -1) {
			downloadPath = downloadPath.replaceAll("/", "\\" + File.separator);
		}
		File file2Download = new File(downloadPath);
		InputStream inputStream = null;
		try {
			if (file2Download.exists()) {
//				response.setContentType("text/plain");
//				response.setHeader("Location", file2Download.getName());
//				response.setHeader("Content-Disposition",
//						"attachment; filename=" + file2Download.getName());
				response.setContentType("multipart/form-data");
				response.setHeader("Content-Length", String.valueOf(file2Download.length()));
				OutputStream outputStream = response.getOutputStream();
				inputStream = new FileInputStream(file2Download);
				byte[] buffer = new byte[1024];
				int i = -1;
				while ((i = inputStream.read(buffer)) != -1) {
					outputStream.write(buffer, 0, i);
				}
				outputStream.flush();
				outputStream.close();
			}
		} catch(FileNotFoundException e){
			//BUG #243509 【歌斐资产】支付21.6版本安全轮巡时存在系统漏洞; 不要把路径暴露
			throw new IOException("文件路径不存在:"+file2Download.getName());
		}finally {
			if (null != inputStream) {
				inputStream.close();
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	public static void main(String[] args) {
		String url = "http://127.0.0.1:8080/liyaojin/file/liyaojin.zip";
		String contextPath = "/liyaojin";
		System.out.println(url.indexOf(contextPath));
		System.out.println(url.substring(
				url.indexOf(contextPath) + contextPath.length(), url.length()));
	}
}
