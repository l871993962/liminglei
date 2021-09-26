package com.yss.ams.api.deploy.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import org.apache.commons.fileupload.FileItem;
import org.eclipse.osgi.internal.baseadaptor.DefaultClassLoader;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import com.yss.ams.api.deploy.service.IAPIBundleOperService;
import com.yss.ams.api.deploy.service.impl.APIBundleOperService;
import com.yss.framework.api.context.YssContextFactory;
import com.yss.framework.api.deploy.IAdmin;
import com.yss.framework.api.deploy.MessageException;
import com.yss.framework.api.deploy.pojo.BundleInfo;
import com.yss.framework.api.deploy.pojo.BundleMETA;
import com.yss.framework.api.deploy.pojo.Grid;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.JsonUtil;
import com.yss.framework.msg.cluster.ClusterTopicPublisher;
import com.yss.framework.msg.cluster.DeployInfo;
import com.yss.framework.msg.cluster.FileInfo;
import com.yss.framework.msg.cluster.ListenerType;
import com.yss.framework.msg.cluster.MsgListener;
import com.yss.framework.msg.mq.TopicNames;
import com.yss.framework.util.FileUtil;

@SuppressWarnings({ "restriction" })
public class DeployAdmin implements IAdmin, MsgListener {

	private Logger logger = LogManager.getLogger(DeployAdmin.class);

	public DeployAdmin() {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		String appCode = bb.getHeaders("Bundle-AppCode").get("Bundle-AppCode");
		FileStorePathUtil pathUtil = new FileStorePathUtil("plugins/" + appCode);
		this.tp = pathUtil.getFilePath();
	}

	public Grid list() throws Exception {
		List<BundleInfo> result = this.loadBundleList();

		Grid grid = new Grid("code|name|state|level");
		for (BundleInfo info : result) {
			try {
				grid.addRow(info.getCode(), info);
			} catch (Exception e) {
				throw e;
			}
		}

		return grid;
	}

	private List<BundleInfo> loadBundleList() {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext bundleContext = bb.getBundleContext();

		File directory = new File(this.tp);

		List<BundleInfo> bundleList = new ArrayList<BundleInfo>();

		File[] files = directory.listFiles();
		if (null != files && files.length > 0) {
			for (File file : files) {
				Attributes atts = this.readMFFile(file.getPath());
				String bundleCode = atts.getValue("Bundle-SymbolicName").split(
						";")[0];
				String bundleVersion = atts.getValue("Bundle-version");
				bundleCode = bundleCode + "_" + bundleVersion;

				Bundle bundle = bundleContext.getBundle(bundleCode);
				BundleInfo bundleInfo = new BundleInfo();

				String bundleCHName = atts.getValue("Bundle-ChName");
				bundleInfo.setName(decodeUnicode(bundleCHName));
				bundleInfo.setCode(bundleCode);
				bundleInfo.setVersion(bundleVersion);
				bundleInfo.setLevel(Integer.parseInt((atts
						.getValue("Bundle-level") == null ? "9" : atts
						.getValue("Bundle-level"))));
				if (bundle != null) {
					bundleInfo.setState(this.getState(bundle.getState()));
				} else {
					bundleInfo.setState(this.getState(1));
				}
				bundleList.add(bundleInfo);
			}
		}

		return bundleList;
	}

	/**
	 * 十六进制的中文转换
	 * 
	 * @param theString
	 * @return
	 */
	private String decodeUnicode(String str) {
		if (str == null) {
			return "Bundle未命名";
		}

		char aChar;

		int len = str.length();

		StringBuffer outBuffer = new StringBuffer(len);

		for (int x = 0; x < len;) {

			aChar = str.charAt(x++);

			if (aChar == '\\') {

				aChar = str.charAt(x++);

				if (aChar == 'u') {

					int value = 0;

					for (int i = 0; i < 4; i++) {

						aChar = str.charAt(x++);

						switch (aChar) {

						case '0':

						case '1':

						case '2':

						case '3':

						case '4':

						case '5':

						case '6':

						case '7':

						case '8':

						case '9':

							value = (value << 4) + aChar - '0';

							break;

						case 'a':

						case 'b':

						case 'c':

						case 'd':

						case 'e':

						case 'f':

							value = (value << 4) + 10 + aChar - 'a';

							break;

						case 'A':

						case 'B':

						case 'C':

						case 'D':

						case 'E':

						case 'F':

							value = (value << 4) + 10 + aChar - 'A';

							break;

						default:

							throw new IllegalArgumentException(

							"Malformed   \\uxxxx   encoding.");

						}

					}

					outBuffer.append((char) value);

				} else {

					if (aChar == 't')

						aChar = '\t';

					else if (aChar == 'r')

						aChar = '\r';

					else if (aChar == 'n')
						aChar = '\n';

					else if (aChar == 'f')
						aChar = '\f';
					outBuffer.append(aChar);

				}
			} else

				outBuffer.append(aChar);
		}
		return outBuffer.toString();
	}

	private String tp = "";

	private List<BundleInfo> bundleInfolist = new ArrayList<BundleInfo>();

	// /** OSGI容器启动时，默认启动所有bundle
	// * @param sourcePath 默认路径
	// * @param targetPath 工作路径
	// * @param bundleContext
	// * @throws Exception
	// */
	// public void startAll(String sourcePath, String targetPath,BundleContext
	// bundleContext) throws Exception {
	//
	// this.sp = sourcePath;
	// this.tp = targetPath;
	//
	// //更改bundle启动级别
	// ServiceReference<StartLevel>
	// serviceRef=bundleContext.getServiceReference(StartLevel.class);
	// StartLevel startLevelSrv=(StartLevel)
	// bundleContext.getService(serviceRef);
	//
	// int level = startLevelSrv.getStartLevel();
	//
	// //将当前启动级别设置为8，激活当前bundle
	// //startLevelSrv.setStartLevel(8);
	// File td = new File(tp);
	// if(!td.exists()){
	// td.mkdirs();
	// }
	//
	// //if(td.listFiles().length == 0){
	// File sd = new File(sp);
	// for(File sf : sd.listFiles()){
	// String target = tp + sf.getName();
	// File tf = new File(target);
	// //Orlando 20141008 开发期采用全覆盖的方式处理bundle，此为临时方案
	// if(tf.exists()){
	// tf.delete();
	// }
	//
	// tf.createNewFile();
	// this.fileChannelCopy(sf, tf);
	// }
	// //}
	//
	// Map<BundleInfo, File> files = new HashMap<BundleInfo, File>();
	//
	// for(File tf : td.listFiles()){
	// BundleInfo levelInfo = new BundleInfo();
	//
	// Attributes attrs = this.readMFFile(tf.getPath());
	//
	// level = Integer.valueOf(attrs.getValue("Bundle-level"));
	// levelInfo.setLevel(level);
	// String code = attrs.getValue("Bundle-SymbolicName").split(";")[0];
	// levelInfo.setCode(code +"_" +attrs.getValue("Bundle-Version"));
	// levelInfo.setName(attrs.getValue("Bundle-Name"));
	// levelInfo.setVersion(attrs.getValue("Bundle-Version"));
	//
	// files.put(levelInfo, tf);
	//
	// bundleInfolist.add(levelInfo);
	// }
	//
	// /* 排序启动的Bundle顺序 */
	// Collections.sort(bundleInfolist);
	//
	// List<String> errs = new ArrayList<String>();
	//
	// for(BundleInfo info : bundleInfolist){
	// File file = files.get(info);
	//
	// /* 安装Bundle */
	// //String bundleName = info.getCode() + "_" + info.getVersion();
	//
	// FileInputStream fileInputStream = new FileInputStream(file);
	// bundleContext.installBundle(info.getCode(),fileInputStream);
	// fileInputStream.close();
	//
	// /* 启动Bundle */
	// Bundle bundle = bundleContext.getBundle(info.getCode());
	// //info.setVersion(bundle.getHeaders().get("Bundle-Version"));
	// startLevelSrv.setBundleStartLevel(bundle, info.getLevel());
	// try{
	// bundle.start();
	// }
	// catch(Exception ex){
	// errs.add(ex.getMessage());
	// }
	//
	// this.doDependence(bundle);
	// }
	//
	// if(errs.size() > 0){
	// String msg = JsonUtil.toString(errs);
	// logger.log(msg);
	// }
	// }

	private Map<String, List<String>> dependences = new HashMap<String, List<String>>();

	private void doDependence(Bundle b) throws MessageException {
		BundleMETA meta = this.getBundleDetail(b);
		for (String str : meta.getRequireBundles()) {
			if (str.startsWith("com.yss.")) {
				String[] sts = str.split(";");
				String code = str.split(";")[0];
				String version = "";
				if (sts.length == 2) {
					version = str.split(";")[1].replace("bundle-version=", "");
				}
				String key = code + "_" + version;
				List<String> list = dependences.get(key);
				if (list == null) {
					list = new ArrayList<String>();
					dependences.put(key, list);
				}
				list.add(meta.getCode());
			}
		}
	}

	/**
	 * 文件复制
	 * 
	 * @param s
	 *            源文件
	 * @param t
	 *            目标文件
	 * @throws Exception
	 */
	private void fileChannelCopy(File s, File t) throws Exception {
		FileInputStream fi = null;
		FileOutputStream fo = null;
		FileChannel in = null;
		FileChannel out = null;

		try {
			fi = new FileInputStream(s);
			fo = new FileOutputStream(t);
			in = fi.getChannel();// 得到对应的文件通道
			out = fo.getChannel();// 得到对应的文件通道
			in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
		} catch (Exception ex) {
			throw ex;
		} finally {
			try {
				if (null != fi) {
					fi.close();
				}

				if (null != in) {
					in.close();
				}

				if (null != fo) {
					fo.close();
				}

				if (null != out) {
					out.close();
				}

			} catch (IOException e) {
				// e.printStackTrace();
				logger.log("文件复制时失败!", e);
			}
		}
	}

	private String getState(int state) {
		String sstate = "";
		switch (state) {
		case 32:
			sstate = "ACTIVE";
			break;
		case 16:
			sstate = "STOPPING";
			break;
		case 8:
			sstate = "STARTING";
			break;
		case 4:
			sstate = "RESOLVED";
			break;
		case 2:
			sstate = "INSTALLED";
			break;
		case 1:
			sstate = "UNINSTALLED";
			break;
		}

		return sstate;
	}

	public void stopByCodes(String[] codes) throws MessageException {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext context = bb.getBundleContext();

		List<String> errs = new ArrayList<String>();

		Bundle b = null;
		for (String code : codes) {
			b = context.getBundle(code);

			try {
				b.stop();
			} catch (BundleException e) {
				errs.add("[" + code + "]停止失败:" + e.getMessage());
			}
		}

		// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复
		// 却掉重复的判断
		// if(errs.size() > 0){
		// if(errs.size() > 0){
		// String msg = JsonUtil.toString(errs);
		// // logger.log(msg);
		// throw new MessageException(msg);
		// }
		// }

		if (errs.size() > 0) {
			String msg = JsonUtil.toString(errs);
			// logger.log(msg);
			throw new MessageException(msg);
		}
	}

	private List<String> checkDependence(Bundle b, BundleContext context)
			throws MessageException {
		BundleMETA meta = this.getBundleDetail(b);
		List<String> ret = new ArrayList<String>();
		for (String str : meta.getRequireBundles()) {
			String[] sta = str.split(";");
			String code = str.split(";")[0];
			String version = "";
			if (sta.length == 2) {
				version = str.split(";")[1].replace("bundle-version=", "");
			}
			code = code + "_" + version.replace("\"", "");
			Bundle bb = context.getBundle(code);
			if (code.startsWith("com.yss.")) {
				if (bb == null) {
					ret.add(code);
				}
			}
		}
		return ret;
	}

	private IAPIBundleOperService service = new APIBundleOperService();

	public void startByCodes(String[] codes) throws Exception {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext context = bb.getBundleContext();

		List<String> errs = new ArrayList<String>();
		Bundle b = null;
		for (String code : codes) {
			b = context.getBundle(code);

			List<String> dep = this.checkDependence(b, context);
			if (dep.size() > 0) {
				errs.add("缺失依赖组件:");
				errs.addAll(dep);
			} else {
				try {
					b.start();

				} catch (BundleException e) {
					String msg = "[" + code + "]启动失败:" + e.getMessage();
					errs.add(msg);
				}
			}

			this.doDependence(b);
		}

		if (errs.size() > 0) {
			String msg = JsonUtil.toString(errs);
			// logger.log(msg);
			throw new MessageException(msg);
		}
	}

	public void install(List<FileItem> items) throws Exception {

		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext context = bb.getBundleContext();

		List<String> errs = new ArrayList<String>();

		File tempDir = new File(System.getProperty("java.io.tmpdir"));
		if (!tempDir.exists()) {
			tempDir.mkdirs();
		}
		String dir = tempDir.getPath();
		/* 生成拷贝文件 */
		Iterator<FileItem> n = items.iterator();
		/*- Added by huangsq STORY #37656 负载均衡环境下的功能优化 */
		List<FileInfo> files = new ArrayList<FileInfo>();
		String tempFileName = "";
		int total = 0;
		int errorCount = 0;
		while (n.hasNext()) {
			FileItem fi = (FileItem) n.next();
			if (!fi.isFormField()) {
				String fileName = fi.getName();
				String[] tmps = fileName.split("\\\\");
				fileName = tmps[tmps.length - 1];
				if (fileName != null) {
					total++;
					/* 保存Bundle */
					tempFileName = dir + "/" + fileName;
					File tempFile = new File(tempFileName);

					fi.write(tempFile);

					Attributes attrs = this.readMFFile(tempFileName);

					String value = attrs.getValue("Bundle-Vendor");
					if (value == null || !value.equalsIgnoreCase("YSSUCO")) {
						errorCount++;
						errs.add("[" + fileName + "]不是有效的OSGI插件.");
						continue;
					}

					String isLimitedUpload = attrs
							.getValue("Bundle-IsLimitedUpload");
					if (isLimitedUpload == null) {
						value = attrs.getValue("Bundle-Category");
						if (value != null
								&& !value.equalsIgnoreCase("PLUGIN_API")) {
							errs.add("[" + fileName + "]不是有效的API插件.");
							errorCount++;
							continue;
						}
					} else {
						if (isLimitedUpload.equalsIgnoreCase("TRUE")) {
							errs.add("[" + fileName + "]不是有效的API插件.");
							errorCount++;
							continue;
						}
					}
					BundleInfo levelInfo = new BundleInfo();
					int level = Integer
							.valueOf((attrs.getValue("Bundle-level") == null ? "9"
									: attrs.getValue("Bundle-level")));
					levelInfo.setLevel(level);
					levelInfo.setCode(attrs.getValue("Bundle-SymbolicName")
							.split(";")[0]
							+ "_"
							+ attrs.getValue("Bundle-Version"));
					levelInfo.setName(attrs.getValue("Bundle-Name"));
					levelInfo.setVersion(attrs.getValue("Bundle-Version"));

					File tf = new File(this.tp + fileName);
					if (tf.exists()) {
						// errs.add("["+ fileName + "]已经存在.");
						String[] codes = new String[] { levelInfo.getCode() };
						try {
							stopByCodes(codes);
							uninstallByCodes(codes);
						} catch (Exception e) {
							errorCount++;
							errs.add("[" + fileName + "]卸载失败.");
							continue;
						}
					}
					File tdir = new File(this.tp);
					if (!tdir.exists()) {
						tdir.mkdirs();
					}

					bundleInfolist.add(levelInfo);

					/*- Added Start by huangsq STORY #37656 负载均衡环境下的功能优化 */
					FileInfo fi1 = new FileInfo();
					fi1.setCode(levelInfo.getCode());
					fi1.setFileName(fileName);

					byte[] byteContent = FileUtil.readFileToBytes(tempFile);

					fi1.setData(byteContent);
					files.add(fi1);

					tf.createNewFile();
					this.fileChannelCopy(tempFile, tf);
				}
			}
		}

		DeployInfo di = new DeployInfo();
		di.setType(ListenerType.CLEAR);
		di.setOper(DeployInfo.OPER_INSTALL);
		di.setFileInfoList(files);
		ClusterTopicPublisher.getInstance(TopicNames.CLUSTER_SYNCFILE).send(di);

		errorCount = errorCount + install(files, context, errs);

		errs.add("<p> 安装完成，接口总数：" + total + "个，成功：" + (total - errorCount)
				+ "个，失败：" + errorCount + "</p>");
		if (errs.size() > 0) {
			String msg = JsonUtil.toString(errs);
			throw new MessageException(msg);
		}
		/*- Added End by huangsq STORY #37656 负载均衡环境下的功能优化 */
	}

	/*- Added by huangsq STORY #37656 负载均衡环境下的功能优化 */
	private int install(List<FileInfo> bundleInfolist, BundleContext context,
			List<String> errs) throws FileNotFoundException, BundleException,
			IOException {
		int errorCount = 0;
		for (FileInfo fi : bundleInfolist) {
			String fileName = fi.getFileName();
			try {
				File tf = new File(this.tp + fileName);
				// 输出文件到这个目录
				FileUtil.writeFileByByte(tf, fi.getData());
				install(context, tf, fi.getCode());
				errs.add("[" + fileName + "]安装完成.");
			} catch (Exception e) {
				errorCount++;
				errs.add("[" + fileName + "]安装失败.");
			}
		}
		return errorCount;
	}

	/*- Added by huangsq STORY #37656 负载均衡环境下的功能优化 */
	public void install(BundleContext context, File tf, String code)
			throws FileNotFoundException, BundleException, IOException {
		FileInputStream fileInputStream = new FileInputStream(tf);
		context.installBundle(code, fileInputStream);
		fileInputStream.close();

		service.afterInstall(context.getBundle(code));
	}

	private Attributes readMFFile(String path) {
		Manifest manifest;
		Attributes attrs = null;
		JarFile jarfile = null;
		try {
			jarfile = new JarFile(path);
			ZipEntry entry = jarfile.getEntry("META-INF/MANIFEST.MF");
			manifest = new Manifest(jarfile.getInputStream(entry));
			attrs = manifest.getMainAttributes();
			jarfile.close();
		} catch (MalformedURLException e) {
			// e.printStackTrace();
			logger.log("读取MF文件配置时失败!", e);
		} catch (IOException e) {
			// e.printStackTrace();
			logger.log("读取MF文件配置时失败!", e);
		} finally {
			if (jarfile != null) {
				try {
					jarfile.close();
				} catch (IOException e) {
					// e.printStackTrace();
					logger.log("时失败!", e);
				}
			}
		}
		return attrs;
	}

	public void uninstallByCodes(String[] codes) throws MessageException {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext context = bb.getBundleContext();

		List<String> errs = new ArrayList<String>();

		Bundle b = null;
		for (String code : codes) {

			b = context.getBundle(code);
			if (b != null && b.getState() == 32) {
				errs.add("[" + code + "]已经启动，不能卸载.");
			} else if (b != null) {
				try {
					service.afterUninstall(b);

					b.uninstall();

					Iterator<BundleInfo> it = this.bundleInfolist.iterator();
					while (it.hasNext()) {
						BundleInfo info = it.next();
						if (info.getCode().equalsIgnoreCase(code)) {
							this.bundleInfolist.remove(info);
							break;
						}
					}

					File tf = new File(this.tp + code.split("_")[0] + ".jar");
					if (tf.exists()) {
						tf.delete();
					} else {
						tf = new File(this.tp + code + ".jar");
						if (tf.exists()) {
							tf.delete();
						}
					}

				} catch (BundleException e) {
					errs.add("[" + code + "]停止失败:" + e.getMessage());
				}
			} else {
				Iterator<BundleInfo> it = this.bundleInfolist.iterator();
				while (it.hasNext()) {
					BundleInfo info = it.next();
					if (info.getCode().equalsIgnoreCase(code)) {
						this.bundleInfolist.remove(info);
						break;
					}
				}

				File tf = new File(this.tp + code.split("_")[0] + ".jar");
				if (tf.exists()) {
					tf.delete();
				} else {
					tf = new File(this.tp + code + ".jar");
					if (tf.exists()) {
						tf.delete();
					}
				}
			}
		}

		// BUG #134663 findbugs代码检查和PMD数据库连接检查问题修复
		// 却掉重复的判断
		// if(errs.size() > 0){
		// if(errs.size() > 0){
		// String msg = JsonUtil.toString(errs);
		// // logger.log(msg);
		// throw new MessageException(msg);
		// }
		// }
		if (errs.size() > 0) {
			String msg = JsonUtil.toString(errs);
			throw new MessageException(msg);
		}
	}

	public BundleMETA getBundleDetailByCode(String code)
			throws MessageException {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		BundleContext context = bb.getBundleContext();
		Bundle b = context.getBundle(code);
		return this.getBundleDetail(b);
	}

	private BundleMETA getBundleDetail(Bundle b) throws MessageException {
		BundleMETA meta = null;

		try {
			Dictionary<String, String> headers = b.getHeaders();

			meta = new BundleMETA();

			String info = headers.get("Require-Bundle");
			String[] requirBundles = (info == null ? new String[0] : info
					.split(","));

			meta.setRequireBundles(Arrays.asList(requirBundles));
			meta.setCode(headers.get("Bundle-SymbolicName").split(";")[0]);
			meta.setName(headers.get("Bundle-Name"));
			meta.setVersion(headers.get("Bundle-Version"));

		} catch (Exception e) {
			throw new MessageException(e.getMessage());
		}

		return meta;
	}

	protected BundleContext getBundleContex() {
		Bundle bb = ((DefaultClassLoader) this.getClass().getClassLoader())
				.getBundle();
		return bb.getBundleContext();
	}

	/*- Added by huangsq STORY #37656 负载均衡环境下的功能优化 */
	@Override
	public void doMessage(String message) {
		DeployInfo info = JsonUtil.toBean(message, DeployInfo.class);
		String oper = info.getOper();
		// 判断如果在上下文中存在UUID，则为自己发送的消息，不处理
		// liyaojin udpate 2018年2月11日 14:27:05
		if (YssContextFactory.getInstance().getMsgIds()
				.contains(info.getUuid())) {
			YssContextFactory.getInstance().getMsgIds().remove(info.getUuid());
			return;
		}
		try {
			if (DeployInfo.OPER_STOP.equals(oper)) {
				stopByCodes(info.getCodes());
			} else if (DeployInfo.OPER_START.equals(oper)) {
				startByCodes(info.getCodes());
			} else if (DeployInfo.OPER_INSTALL.equals(oper)) {
				BundleContext contex = getBundleContex();
				List<String> errs = new ArrayList<String>();
				install(info.getFileInfoList(), contex, errs);
			} else if (DeployInfo.OPER_UNINSTALL.equals(oper)) {
				uninstallByCodes(info.getCodes());
			}
			logger.log("执行" + oper + "操作成功");
		} catch (Exception e) {
			logger.log("处理部署JAR包消息失败：", e);
		}
	}

}
