package com.yss.ams.api.deploy;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.yss.ams.api.deploy.admin.AdminFactory;
import com.yss.ams.api.deploy.admin.DeployAdmin;
import com.yss.framework.api.deploy.MessageException;
import com.yss.framework.api.deploy.pojo.BundleMETA;
import com.yss.framework.api.deploy.pojo.Grid;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.api.util.SysUtil;
import com.yss.framework.msg.cluster.ClusterTopicPublisher;
import com.yss.framework.msg.cluster.DeployInfo;
import com.yss.framework.msg.cluster.ListenerType;
import com.yss.framework.msg.mq.TopicNames;

public class DeployServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DeployAdmin admin = AdminFactory.getInstance().getAdmin(AdminFactory.ADMIN_TYPE_API);
	private ClusterTopicPublisher deployPublisher = ClusterTopicPublisher.getInstance(TopicNames.CLUSTER_SYNCFILE);


	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(UTF8);
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding(UTF8);

		PrintWriter pw = null;

		try {
			pw = response.getWriter();

			String realUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");

			String msg = "";

			if (Pattern.matches("/api/bundle/list/".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				try {
					Grid grid = admin.list();
					msg = grid.toString();
				} catch (Exception e) {
					throw new ServletException(e);
				}
			} else if (Pattern.matches("/api/bundle/list/*".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				String code = realUrl.replaceAll("/api/bundle/list/", "");
				BundleMETA meta = admin.getBundleDetailByCode(code);
				msg = JsonUtil.toString(meta);
			}

			pw.write(msg);
		} catch (MessageException e) {
			response.setStatus(777);
			pw.write(e.getMessage());
		} catch (Exception ex) {
			String msg = ex.getMessage();
			if (null != pw) {
				pw.write(JsonUtil.toString(msg));
			}
		} finally {
			if (null != pw) {
				pw.flush();
				pw.close();
			}
		}
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(UTF8);
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding(UTF8);

		PrintWriter pw = null;

		try {
			pw = response.getWriter();

			String realUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");

			if (Pattern.matches("/api/bundle/stop/*".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				// BUG #140673 清算接口部署启动报错 2016-9-9 jiangjin 将数据从流中传送，避免采用url传送是长度超过限制，导致无法成功请求
				String param = SysUtil.getBytesStringFromClient(request);
				if (!param.equals("")) {
					String[] codes = param.split(",");

					for (int i = 0; i < codes.length; i++) {
						String code = codes[i];
						codes[i] = code.substring(3);
					}
					/*- Added Start by huangsq STORY #37656 负载均衡环境下的功能优化 */
					DeployInfo di = new DeployInfo();
					di.setType(ListenerType.API);
					di.setOper(DeployInfo.OPER_STOP);
					di.setCodes(codes);
					deployPublisher.send(di);
					/*- Added End by huangsq STORY #37656 负载均衡环境下的功能优化 */
					admin.stopByCodes(codes);
				}
			} else if (Pattern.matches("/api/bundle/start/*".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				// BUG #140673 清算接口部署启动报错 2016-9-9 jiangjin 将数据从流中传送，避免采用url传送是长度超过限制，导致无法成功请求
				String param = SysUtil.getBytesStringFromClient(request);
				if (!param.equals("")) {
					String[] codes = param.split(",");

					for (int i = 0; i < codes.length; i++) {
						String code = codes[i];
						codes[i] = code.substring(3);
					}
					/*- Added Start by huangsq STORY #37656 负载均衡环境下的功能优化 */
					DeployInfo di = new DeployInfo();
					di.setOper("start");
					di.setCodes(codes);
					di.setType(ListenerType.API);
					deployPublisher.send(di);
					/*- Added End by huangsq STORY #37656 负载均衡环境下的功能优化 */
					admin.startByCodes(codes);
				}
			}
		} catch (MessageException e) {
			response.setStatus(777);
			pw.write(e.getMessage());
		} catch (Exception ex) {
			String msg = ex.getMessage();
			if (null != pw) {
				pw.write(JsonUtil.toString(msg));
			}
		} finally {
			if (null != pw) {
				pw.flush();
				pw.close();
			}
		}
	}

	private final static String UTF8 = "UTF-8";

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(UTF8);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding(UTF8);
		PrintWriter pw = null;

		try {
			pw = response.getWriter();
			String realUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");
			if (Pattern.matches("/api/bundle/install".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				/* 操作结果类型 success fail */
				DiskFileItemFactory factory = new DiskFileItemFactory();
				File tempfile = new File(System.getProperty("java.io.tmpdir"));
				if (!tempfile.exists()) {
					tempfile.mkdirs();
				}
				factory.setRepository(tempfile);
				ServletFileUpload upload = new ServletFileUpload(factory);
				List<FileItem> items = upload.parseRequest(request);
				admin.install(items);
			}
		} catch (MessageException e) {
			response.setStatus(777);
			pw.write(e.getMessage());
		} catch (Exception ex) {
			String msg = ex.getMessage();
			if (null != pw) {
				pw.write(JsonUtil.toString(msg));
			}
		} finally {
			if (null != pw) {
				pw.flush();
				pw.close();
			}
		}
	}

	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(UTF8);
		response.setContentType("application/json; charset=utf-8");
		response.setCharacterEncoding(UTF8);

		PrintWriter pw = null;

		try {
			pw = response.getWriter();

			String realUrl = request.getRequestURI().replaceFirst(request.getContextPath(), "");

			if (Pattern.matches("/api/bundle/uninstall/*".replaceAll("\\*", "([^\\/]\\*)"), realUrl)) {
				// BUG #140673 清算接口部署启动报错 2016-9-9 jiangjin 将数据从流中传送，避免采用url传送是长度超过限制，导致无法成功请求
				String param = SysUtil.getBytesStringFromClient(request);
				if (!param.equals("")) {
					String[] codes = param.split(",");

					for (int i = 0; i < codes.length; i++) {
						String code = codes[i];
						codes[i] = code.substring(3);
					}
					/*- Added Start by huangsq STORY #37656 负载均衡环境下的功能优化 */
					DeployInfo di = new DeployInfo();
					di.setType(ListenerType.CLEAR);
					di.setOper(DeployInfo.OPER_UNINSTALL);
					di.setCodes(codes);
					deployPublisher.send(di);
					/*- Added End by huangsq STORY #37656 负载均衡环境下的功能优化 */
					admin.uninstallByCodes(codes);
				}
			}
		} catch (MessageException e) {
			response.setStatus(777);
			pw.write(e.getMessage());
			pw.flush();
		} catch (Exception ex) {
			String msg = ex.getMessage();
			if (null != pw) {
				pw.write(JsonUtil.toString(msg));
			}
		} finally {
			if (null != pw) {
				pw.close();
			}
		}
	}
}
