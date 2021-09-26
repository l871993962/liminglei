/**
 * @Title: CacheUtil.java
 * @Desc: TODO
 * @Package: com.yss.ams.product.information.util.cache
 * @author: zhouxudong
 * @date: 2019年2月19日 上午10:51:52
 * @version 1.0
 */
 package com.yss.ams.base.information.util.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yss.framework.api.service.YssServiceFactory;
import com.yss.platform.support.dataservice.pojo.dict.Vocabulary;
import com.yss.platform.support.dataservice.service.IVocCacheDataService;

 /**
 * @ClassName: CacheUtil
 * @Desc: 缓存工具类
 * @author: zhouxudong
 * @date: 2019年2月19日 上午10:51:52
 * @version 1.0
 */
public class CacheUtil {
    /**
     * 根据数据字典类型获取Map<KEY, Vocabulary>结果集
     * @Title: getVocCacheByType 
     * @param vocType 数据字典类型
     * @return Map<String, Vocabulary>
     */
    public static Map<String, Vocabulary> getVocCacheByType(String vocType){
        Map<String, Vocabulary> map = new HashMap<String, Vocabulary>();
        
        IVocCacheDataService vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService"); 
        List<Vocabulary> vocList = vocService.getDataListByTypes(new String [] {vocType});
        if (vocList!= null && vocList.size()>0) {
            for (Vocabulary voc : vocList) {
                map.put(voc.getC_DV_CODE(), voc);
            }
        }
        return map;
    }
    
    /**
     * 根据数据字典类型获取List<Vocabulary>结果集
     * @Title: getDataListByTypes 
     * @param vocType
     * @return
     */
    public static List<Vocabulary> getDataListByTypes(String vocType){
        IVocCacheDataService vocService = YssServiceFactory.getInstance().createServiceByImplClass(IVocCacheDataService.class, "com.yss.fast.atomicdata.dict.service.impl.VocCacheDataService"); 
        List<Vocabulary> vocList = vocService.getDataListByTypes(new String [] {vocType});
        return vocList;
    }
}
