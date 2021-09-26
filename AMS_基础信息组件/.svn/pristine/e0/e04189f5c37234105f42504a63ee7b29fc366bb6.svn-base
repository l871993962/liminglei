package com.yss.ifa.szt.tool.thread;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import com.yss.framework.api.common.YssConstant;
import com.yss.framework.api.logger.LogManager;
import com.yss.framework.api.logger.Logger;
import com.yss.framework.api.util.FileStorePathUtil;
import com.yss.framework.api.util.file.PropertiesUtil;
import com.yss.ifa.szt.tool.pojo.TransPojo;

/**
 * 接收伺服器数据的线程池对象
 * @author weijj
 *
 */
public class ReviceThreadPool {
	//Orlando 20150910 将线程并发数从10调整到5 因为太平资产在并发时连接增加过大超出限制，这里把并发调小
	private static int maxCount = 10;
	private static Logger logger = LogManager.getLogger(ReviceThreadPool.class);
	private static ExecutorService srevice = null;
	/**
	 * 互斥锁
	 */
	private static final Lock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();
	/**
	 * 深证通发送线程
	 */
	private static List<ReviceThread> threadList = new ArrayList<ReviceThread>();

	/**
	 * 多线程共享的。
	 */
	private static List<TransPojo> dataList = new CopyOnWriteArrayList<TransPojo>();
	
	public static void reviceData(List<TransPojo> list) {
		logger.log("收到消息数量：" + list.size());
		dataList.addAll(list);
	}
	/**
	 * Author : wulongxing
	 * Date   : 2016-12-1
	 * Status : Add
	 * Comment: 查询生成电子对账数据模式 true则使用存储过程模式，否则使用代码逻辑生成
	 * @throws Exception
	 */
	private static int getThread(){
		int count = 10;
		try {
			FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
			PropertiesUtil propertiesUtil = new PropertiesUtil();
			String fileName = fileUtil.getFilePath() + "runtime.properties";
			File file = new File(fileName);
			if(file.exists()){
				Properties properties = propertiesUtil.Properties(fileName);
				count = Integer.valueOf(properties.getProperty("elec_max_thread","10"));
			}
		} catch (Throwable e) {
			logger.log("获取线程失败", e);
			count = 10;
		}
		return count;
	}
	
	/**
	 * BUG #299473 KM系统推送的一笔指令发送时报文重复
	 * @return
	 */
	private static long getCacheSaveTime(){
		long time = 600000L;
		try {
			FileStorePathUtil fileUtil = new FileStorePathUtil(YssConstant.GLOABL_PATH);
			PropertiesUtil propertiesUtil = new PropertiesUtil();
			String fileName = fileUtil.getFilePath() + "runtime.properties";
			File file = new File(fileName);
			if(file.exists()){
				Properties properties = propertiesUtil.Properties(fileName);
				time = Long.valueOf(properties.getProperty("catch_save_time","600000"));
			}
		} catch (Throwable e) {
			logger.log("获取线程失败", e);
			time = 600000L;
		}
		return time;
	}
	
	public static void start(){
		maxCount = getThread();
		long time = getCacheSaveTime();
		srevice = Executors.newFixedThreadPool(maxCount);
		// List<Future<Object>> futureList = new ArrayList<Future<Object>>();
		/**
		 * 定义一个接收对象 供接收线程调用方法。
		 */
		if (threadList.size() == 0) {
			for (int i = 0; i < maxCount; i++) {
				ReviceThread call = new ReviceThread(i, time);
				threadList.add(call);
				srevice.submit(call);
			}
		}
		logger.debug("接收线程池准备就绪.");
	}
	
	/**
	 * 停止时使用 
	 * 唤醒所有线程，使得线程走完当前循环自动结束
	 */
	static void signalAll() {
//		try {
//			lock.lock();
//			condition.signalAll();
//			logger.log(" 接收伺服器线程池唤醒所有接收线程 ");
//		} catch (Exception e) {
//			logger.log("接收伺服器线程池唤醒所有接收线程 出错 :", e);
//		} finally {
//			lock.unlock();
//		}
	}
	/**
	 * 停止所有线程
	 */
	public static void stop() {
		try{
			// //修改线程的循环标识
			for (ReviceThread thread : threadList) {
				thread.stop();
			}

			////按顺序停止所有线程
			srevice.shutdown();
			////清空线程列表
			threadList.clear();
		} catch (Exception e) {
			logger.log("关闭接收数据线程池出错：" + e.getMessage(), e);
		} finally {
			logger.log(" 关闭伺服器接收数据线程池！");
		}
	}
	
	/**从等待接收的数据池中获取10条数据，并唤醒一个线程
	 * @return
	 */
	public static List<TransPojo> getThreadDataList() {
		List<TransPojo> list = new ArrayList<TransPojo>();
		try {
			lock.lock();
			if (dataList.size() > 0) {
				int num = 0;
				// /加锁 给当前线程添加10笔数据用来发送
				Iterator<TransPojo> iter = dataList.iterator();
				while (iter.hasNext()) {
					num++;
					TransPojo data = iter.next();
					list.add(data);
					dataList.remove(data);
					if (num > 9) {
						break;
					}
				}
				
//				 if (runThreadCount < maxCount) {
//					 runThreadCount++;
//					 condition.signal();
//				}
			logger.log(String.format("取%d条数据，还剩%s条", num, dataList.size()));
			}else{
//				if (runThreadCount > 1) {
//					runThreadCount--;
//					condition.await();
//				}
			}
		} catch (Exception e) {
			logger.log("获取待处理的伺服器返回数据出错："+e.getMessage(), e);
		} finally {
			lock.unlock();
		}
		return list;
	}
}
