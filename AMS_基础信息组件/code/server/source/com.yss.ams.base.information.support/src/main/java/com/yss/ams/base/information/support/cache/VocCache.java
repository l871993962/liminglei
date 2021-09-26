package com.yss.ams.base.information.support.cache;

import java.util.ArrayList;
import java.util.List;

import com.yss.ams.base.information.support.sys.voc.service.IUcoVocService;
import com.yss.framework.api.cache.CacheRefreshInfo;
import com.yss.framework.api.cache.assist.BaseCache;
import com.yss.framework.api.cache.assist.CacheGroup;
import com.yss.framework.api.cache.assist.CacheOper;
import com.yss.framework.api.cache.ehcache.EHCache;
import com.yss.framework.api.common.co.BasePojo;
import com.yss.framework.api.common.co.CacheData;
import com.yss.framework.api.service.ServiceException;
import com.yss.framework.api.service.YssServiceFactory;
import com.yss.framework.api.util.StringUtil;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocDataService;


/**
 * 词汇字典缓存
 * 
 * @author leeyu
 * 
 */
public class VocCache extends BaseCache<Vocabulary> {

	//Orlando 20140411 子类属性必须是静态的，父类构造器中会用到
	// 内部改为按序号排序的方式byleeyu20140421
// 同一词汇类型的词汇不能以序号作为主键,查询结果已经按词汇类型、序号排序  liuxiang 2016-6-27 BUG #133240 资金追加提取业务新增界面中业务类型智能加载资金追加
//	private static EHCache<String, TreeMap<Integer, Vocabulary>> mapvoctype = new EHCache<String, TreeMap<Integer,Vocabulary>>(
//			"VocabularyType");
	private static EHCache<String, List<Vocabulary>> mapvoctype = new EHCache<String, List<Vocabulary>>(
			"VocabularyType");

	private IVocDataService vocDataService = null;
	//增加一个新的词汇接口，维护估值词汇数据
	private IUcoVocService ucoVocService = null;

	@Override
	protected void loadData() {
		try{
			if(!YssServiceFactory.getInstance().hasOsgiService(IVocDataService.class)){
				Thread.sleep(5000);
			}
			if(!YssServiceFactory.getInstance().hasOsgiService(IVocDataService.class)){
				Thread.sleep(5000);
			}
			//BUG #333673 银华基金估值核算【300.7-20200630】重启后缓存加载失败
			if(!YssServiceFactory.getInstance().hasOsgiService(IVocDataService.class)){
				Thread.sleep(5000);
			}
			vocDataService = YssServiceFactory.getInstance().createService(
					IVocDataService.class);
			ucoVocService = YssServiceFactory.getInstance().createService(
					IUcoVocService.class);
			
			/**
			 * 是否首次更新
			 */
			boolean isFistLoad = StringUtil.IsNullOrEmptyT(getTimestamp()) ? true : false;
			CacheData data = vocDataService.updateByTimestamp(getTimestamp());
			//TASK #539745 估值核算Fortify 代码安全检测改造 edit by suixin 20181129 检测有空指针风险，故加了个校验
			
			
			List<Vocabulary> vocAddList = new  ArrayList<Vocabulary>();//待新增的词汇集合列表
			List<Vocabulary> vocUpdList = new  ArrayList<Vocabulary>();//待更新的词汇集合列表
			
			if(data != null){
				List<Vocabulary> lstvoc = new  ArrayList<Vocabulary>();
				if(this != null){
					this.setTimestamp(data.getTimestamp());
					lstvoc = this.cacheData2List(data.getDataList(),Vocabulary.class);
				}
//				mapvoctype.clear();这里不清除，因为删除不再走这个方法
				//BUG #336000 统计分析处理，净值统计表及统计余额表出现重复 20200927 jiangjin
				//缓存监控页面触发刷新，调用refreshAll，会清空getTimestamp，如果不清空mapvoctype，缓存将重复
				if(isFistLoad){
					mapvoctype.clear();
				}
				if(lstvoc != null){
					for (Vocabulary voc : lstvoc) {
						if(voc != null){
							this.mapT.put(voc.getC_DV_CODE(), voc);
							this.keyMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
							this.idMap.put(voc.getId(), voc);
							
							List<Vocabulary> list = null;
							if(!StringUtil.IsNullOrEmpty(voc.getC_DV_TYPE())){
								if (mapvoctype.containsKey(voc.getC_DV_TYPE())) {
									list = mapvoctype.get(voc.getC_DV_TYPE());
								} else {
									list = new ArrayList<Vocabulary>();
									mapvoctype.put(voc.getC_DV_TYPE(), list);
								}
								if(isFistLoad){
									list.add(voc);
									vocAddList.add(voc);
								}else {
									//updateOrAddVoc(list, voc);
									//STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志  edit by sunyanlin 20191128
									//将待新增或者待更新的词汇分别归类放入对应的几个列表中 在后面统一更新到估值词汇表T_S_DV_VOC_UCO中 
									updateOrAddVoc(list, voc, vocAddList, vocUpdList);
								}
							}
							
						}
					}
				}
			}
			
			//STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志 edit by sunyanlin 2019-11-28
			if(vocAddList.size() > 0 || vocUpdList.size()>0){
				ucoVocService.addAndUpdUcoVoc(isFistLoad,vocAddList, vocUpdList);
			}
			
		} catch (Exception e) {
			//e.printStackTrace();
			throw new ServiceException(e.getMessage(),e);
		}
	}
	
	/**
	 * 更新或者删除词汇
	 * @Title updateOrAddVoc 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月24日下午4:27:03
	 * @param list
	 * @param vocNew
	 * @return void
	 */
	private void updateOrAddVoc(List<Vocabulary> list, Vocabulary vocNew){
		boolean exist = false;
		for(Vocabulary voc : list){
			if(voc.getC_DV_CODE().equals(vocNew.getC_DV_CODE())){
				voc = vocNew;
				exist = true;
				break;
			}
		}
		
		if(!exist){
			list.add(vocNew);
		}
	}
	
	/**
	 * STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志
	 * 更新或者删除词汇
	 * 拷贝上面的方法，增加两个集合列表，分贝存入待更新和待新增的词汇数据
	 * add  by sunyanlin 20191228
	 */
	private void updateOrAddVoc(List<Vocabulary> list, Vocabulary vocNew, List<Vocabulary> vocAddlist, List<Vocabulary> VocUpdlist){
		boolean exist = false;
		for(Vocabulary voc : list){
			if(voc.getC_DV_CODE().equals(vocNew.getC_DV_CODE())){
				//避免错误数据导致后台报错
				if(null == voc.getC_AUTH_ORG_CODE()){
					voc.setC_AUTH_ORG_CODE("[root]");
				}
				if(null == vocNew.getC_AUTH_ORG_CODE()){
					vocNew.setC_AUTH_ORG_CODE("[root]");
				}
				//原逻辑系全量更新，未过滤出哪些词汇是需要更新的
				//若原有的词汇与现有的词汇代码相同，有属性不同，则认为其属于已变更数据，需要同步到估值词汇表T_S_DV_VOC_UCO
				if(!isSameVoc(voc, vocNew)){
					VocUpdlist.add(vocNew);
				}
				voc = vocNew;
				exist = true;
				break;
			}
		}
		
		if(!exist){
			list.add(vocNew);
			vocAddlist.add(vocNew);
		}
	}
	

	
	/**
	 * STORY #83002 【兴全基金】【1012版本】系统业务处理过程中关联的词汇表，产生大量归档日志
	 * add  by sunyanlin 20191228
	 * 比较两个词汇是否相同
	 * @param v1
	 * @param v2
	 * @return
	 */
	//间接实现Equals方法，认为以下属性相同，即可认为是相同的词汇
	public static boolean isSameVoc(Vocabulary v1, Vocabulary v2){
		boolean flag = false;
		if(v1.getC_DV_CODE().equals(v2.getC_DV_CODE()) &&
		   v1.getC_DV_NAME().equals(v2.getC_DV_NAME()) &&
		   v1.getC_DV_TYPE().equals(v2.getC_DV_TYPE()) &&
		   v1.getC_DESC().equals(v2.getC_DESC()) &&
		   v1.getN_ORDER().equals(v2.getN_ORDER()) &&
		   v1.getC_AUTH_ORG_CODE().equals(v2.getC_AUTH_ORG_CODE()))
		{
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 移除List<Vocabulary>中词汇代码相同的元素
	 * @Title removeVoc 
	 * @Description 
	 * @author leijianhua@ysstech.com
	 * @date 2017年3月24日下午4:26:37
	 * @param list
	 * @param vocDel
	 * @return void
	 */
	private void removeVoc(List<Vocabulary> list,Vocabulary vocDel){
		if(list!=null && list.size()>0){
//			int index = -1;
			for(int i =0;i < list.size();i++){
				Vocabulary voc = list.get(i);
				if(voc.getC_DV_CODE().equals(vocDel.getC_DV_CODE())){
					list.remove(i);
				}
			}
		}
	}
	
	/**
	 * 重写更新方法，在删除中对mapvoctype进行处理
	 */
	@Override
	public void update(CacheRefreshInfo info) {
		rwl.writeLock();
		try {
			// 如果删除了数据，将时间戳置为空
			// 当时间戳为空时取所有数据
			if (info.getOper() == CacheOper.DEL) {
				if(info.getIdList()!=null && info.getIdList().size()>0){
					for(String id : info.getIdList()){
						Vocabulary voc = (Vocabulary)idMap.get(id);
						String key = voc.getC_DV_CODE();
						if(!StringUtil.IsNullOrEmpty(key)){
							this.mapT.remove(key);
							this.keyMap.remove(key);
						}
						
						removeVoc(getCacheByType(voc.getC_DV_TYPE()), voc);
					}
				}
				
//				this.mapT.clear();
				this.timestamp = newTimeStamp();
			
			}else if(info.getOper() == CacheOper.AUDIT){
				try {
					if(info.getIdList()!=null && info.getIdList().size()>0){
						loadDataByIds(StringUtil.join(info.getIdList(), ","));
					}
				} catch (Exception e) {
					logger.log("缓存" + info.getGroups()[0] + "更新失败：" + e.getMessage(), e);
				}
			}else {
				loadData();
			}
		} finally {
			rwl.writeUnLock();
		}
	}

	@Override
	public CacheGroup getCacheGroup() {
		return CacheGroup.VOC;
	}

	@Override
	public List<String> bindServiceNames() {
		List<String> list = new ArrayList<String>();
		list.add(IVocDataService.class.getSimpleName());
		return list;
	}

	@Override
	public Vocabulary getCacheByKey(String key) {
		Vocabulary voc = null;
		voc = this.mapT.get(key);
		return voc;
	}

	/**
	 * 获取单个词汇类型的词汇列表
	 * 
	 * @param vocType
	 *            词汇类型
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Vocabulary> getCacheByType(String vocType) {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		rwl.readLock();
		try {
			if (mapvoctype.containsKey(vocType)) {
				list = mapvoctype.get(vocType);				
			}
		} finally {
			rwl.readUnLock();
		}
		return new ArrayList(list);
	}

	/**
	 * 获取一组词汇类型下的词汇列表
	 * 
	 * @param vocTypes
	 *            词汇类型组
	 * @return
	 */
	public List<Vocabulary> getCacheByTypes(String[] vocTypes) {
		List<Vocabulary> list = new ArrayList<Vocabulary>();
		rwl.readLock();
		try {
			for (String vocType : vocTypes) {
				if (mapvoctype.containsKey(vocType)) {
					list.addAll(getCacheByType(vocType));
				}
			}
		} finally {
			rwl.readUnLock();
		}
		return list;
	}

	@Override
	public List<Vocabulary> getCacheList() {
		List<Vocabulary> list = new ArrayList<Vocabulary>();

		rwl.readLock();
		try {
			list = this.mapT.getAllValues();
		} finally {
			rwl.readUnLock();
		}

		return list;
	}

	//STORY #40717 缓存刷新增加删除和审核数据时增量刷新 雷建华 20170329
	@Override
	protected String getCacheDataCode(BasePojo pojo) {
		return ((Vocabulary)pojo).getC_DV_CODE();
	}

	@Override
	protected void loadDataByIds(String ids) {
		List<Vocabulary> lstvoc = vocDataService.queryByIds(ids);
		
		//TASK #539745 估值核算Fortify 代码安全检测改造 edit by suixin 20181129 检测有空指针风险，故加了个校验
		if(lstvoc != null){
//			mapvoctype.clear();这里不清除，因为删除不再走这个方法
			for (Vocabulary voc : lstvoc) {
				if(voc != null){
					this.mapT.put(voc.getC_DV_CODE(), voc);
					this.keyMap.put(voc.getC_DV_CODE(), voc.getC_DV_NAME());
					this.idMap.put(voc.getId(), voc);
					
					List<Vocabulary> list = null;
					if (mapvoctype.containsKey(voc.getC_DV_TYPE())) {
						list = mapvoctype.get(voc.getC_DV_TYPE());
					} else {
						list = new ArrayList<Vocabulary>();
						mapvoctype.put(voc.getC_DV_TYPE(), list);
					}
					
					updateOrAddVoc(list, voc);
				}
			}	
		}
	}
}
