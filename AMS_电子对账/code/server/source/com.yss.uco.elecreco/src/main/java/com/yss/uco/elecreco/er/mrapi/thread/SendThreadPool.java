package com.yss.uco.elecreco.er.mrapi.thread;

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
import com.yss.uco.elecreco.support.bean.ErBbInfo;

/**
 * 发送大量数据时采用线程池
 * @author weijj
 *
 */
public class SendThreadPool {
	private static int maxCount = 10;
	private static Logger logger = LogManager.getLogger(SendThreadPool.class);
	private static ExecutorService srevice = null;
	/**
	 * 互斥锁
	 */
	private static final Lock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();
	/**
	 * 深证通发送线程
	 */
	private static List<SendThread> threadList = new ArrayList<SendThread>();

	/**
	 * 多线程共享的。
	 */
	private static List<ErBbInfo> sendDataList = new CopyOnWriteArrayList<ErBbInfo>();

	public static void sendData(List<ErBbInfo> list) {
		sendDataList.addAll(list);
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
			logger.error("获取线程失败", e);
			count = 10;
		}
		return count;
	}
	public static void start(){
		maxCount = getThread();
		srevice = Executors.newFixedThreadPool(maxCount);
		// List<Future<Object>> futureList = new ArrayList<Future<Object>>();
		/**
		 * 定义一个接收对象 供接收线程调用方法。
		 */
		if (threadList.size() == 0) {
			for (int i = 0; i < maxCount; i++) {
				SendThread call = new SendThread(i);
				threadList.add(call);
				srevice.submit(call);
			}
		}
		logger.debug("发送线程池准备就绪!");
	}
	
	/**
	 * 停止时 使用 
	 * 唤醒所有线程，使得线程走完当前循环自动结束
	 */
	static void signalAll() {
//		try {
//			lock.lock();
//			condition.signalAll();
//			logger.info("唤醒向伺服器发磅数据的所有线程成功. ");
//		} catch (Exception e) {
//			logger.error("唤醒向伺服器发送数据的所有线程出错 :"+e.getMessage(), e);
//		} finally {
//			lock.unlock();
//		}
	}
	/**
	 * 停止所有线程
	 */
	public static void stop() {
		// //修改线程的循环标识
		try{
			for (SendThread thread : threadList) {
				thread.stop();
			}
	
			////按顺序停止所有线程
			srevice.shutdown();
			////清空线程列表
			threadList.clear();
		}
		finally{
			logger.error("停止向伺服器发送数据的线程池！");
		}
	}

	/**从等待发送的数据池中取出10数据，并唤醒一个线程
	 * @return
	 */
	public static List<ErBbInfo> getThreadDataList() {
		List<ErBbInfo> dataList = new ArrayList<ErBbInfo>();
		try {
			lock.lock();
			if (sendDataList.size() > 0) {
				int num = 0;
				// /加锁 给当前线程添加10笔数据用来发送
				Iterator<ErBbInfo> iter = sendDataList.iterator();
				while (iter.hasNext()) {
					num++;
					ErBbInfo data = iter.next();
					dataList.add(data);
					sendDataList.remove(data);
					if (num > 9) {
						break;
					}
				}
				
//				 if (runThreadCount < maxCount) {
//					 runThreadCount++;
//					 condition.signal();
//				}
			}else{
//				if (runThreadCount > 1) {
//					runThreadCount--;
//					condition.await();
//				}
			}
		} catch (Exception e) {
			logger.error("发送线程出错 :", e);
		} finally {
			lock.unlock();
		}
		return dataList;
	}
}
