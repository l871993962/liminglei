package listener;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.yss.ams.bridge.support.util.FileUtil;

public class ContainerListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String serverName = context.getServerInfo();
		saveDeployServerInfo(serverName);
		saveProRootPath(serverName);
	}

	/**
	 * 保存服务器类型
	 * @param name 服务器名称
	 */
	private void saveDeployServerInfo(String name){
		String path = "";
		/* 服务器类型 */
		String serverName = "";
		if (name.lastIndexOf("Tomcat") > -1) {
			serverName = "TOMCAT";
			path = FileUtil.getResource("../../WEB-INF/config/server/").getPath();
		}else{
			if (name.lastIndexOf("WebSphere") > -1){
				serverName = "WEBSPHERE";
			} else {
				serverName = "WEBLOGIC";
			}
			path = FileUtil.getResource("../").getPath() + "/config/server/";
		}
		
		try {
			/* 部署的服务类型存储文件 */
			path = path + "info.properties";
			path = java.net.URLDecoder.decode(path,"UTF-8");
			Properties pro = new Properties();
			pro.setProperty("serverName", serverName);
			PrintStream ps = new PrintStream(new File(path));
			pro.list(ps);
			ps.close();
		} catch (FileNotFoundException e) {
		} catch (UnsupportedEncodingException e) {
		}
		
	}
	
	/**
	 * 保存项目根目录路径到文件
	 */
	private void saveProRootPath(String name){
		String rootPath = "";
		String filePath = "";
		/* 服务器类型 */
		if (name.lastIndexOf("Tomcat") > -1) {
			filePath = FileUtil.getResource("../../WEB-INF/eclipse/configuration/").getPath();
			rootPath = FileUtil.getResource("../../../").getPath();
			File file = new File(rootPath + "/WebRoot/WEB-INF");
			if(!file.exists()){
				rootPath = FileUtil.getResource("../../").getPath();
			}
		} else {
			rootPath = FileUtil.getResource("../../").getPath();
			filePath = rootPath + "/WEB-INF/eclipse/configuration/";
		}
		
		try {
			/* com.yss.uco.bridge根目录存储文件 */
			String path = filePath + "/projectpath.txt";
			BufferedWriter output = new BufferedWriter(new FileWriter(java.net.URLDecoder.decode(path,"UTF-8")));
			output.write(rootPath);
			output.close();
		} catch (FileNotFoundException e) {
		} catch (IOException e) {
		}
	}
}
