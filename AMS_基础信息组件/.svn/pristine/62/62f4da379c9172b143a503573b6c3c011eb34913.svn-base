package servlet;

import java.io.File;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.eclipse.equinox.servletbridge.BridgeServlet;

/**
 * Author : ChenLong
 * Date   : 2015-03-27
 * Status : Add
 * Comment: 重写桥接Servlet *
 */
public class AMSServletBridge extends BridgeServlet{
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException{
		ServletConfig servletConfig = getServletConfig();
		/**
		 * STORY #41626 PLATFORM中的系统初始化功能OSGI化 liyaojin add for
		 * 动态指定bridge项目的名字到YssConstant中，使得该名字无需配置运行时进行初始化(20170508)
		 */
		String bridgePath = servletConfig.getServletContext().getContextPath();
		if (!"".equals(bridgePath) && null != bridgePath && bridgePath.startsWith("/")) {
			bridgePath = bridgePath.substring(1, bridgePath.length());
		}
		if(null != bridgePath){
			System.setProperty("bridgePath", bridgePath);
		}
		// end
		ServletContext context = servletConfig.getServletContext();
		File servletTemp = (File) context.getAttribute("javax.servlet.context.tempdir");
		deleteAllFiles(servletTemp);
		super.init();
	}
	
	/**
	 * 删除OSGI缓存
	 * @param servletTemp
	 */
	private void deleteAllFiles(File servletTemp){
		if(servletTemp.isDirectory()){
			File[] files = servletTemp.listFiles();
			if(files != null){
				for(File file : files){
					deleteAllFiles(file);
				}
			}
			servletTemp.delete();
		}else{
			servletTemp.delete();
		}		
	}
}
