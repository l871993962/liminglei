package com.yss.ams.visaval.jython;

import java.util.HashMap;

import org.python.core.PyDictionary;
import org.python.core.PyFloat;
import org.python.core.PyList;
import org.python.core.PyObject;
import org.python.core.PyString;

public class JythonTypeProcess {
	
	public static HashMap<String,Double> toMapString_Double(Object pyObject){
		return PyDictionary.class.isInstance(pyObject) ? toHashMap(pyObject) : null;
	}
	
	/**
	 * add by yewenke 20160530
	 * @param pyObject
	 * @return
	 */
	public static boolean isInteger(Object pyObject){
		if(pyObject instanceof Integer){
			return true;
		}
		else{
			return false;
		}
	}
	public static HashMap<String,String> toMapString_String(Object pyObject){
		return PyDictionary.class.isInstance(pyObject) ? toHashMapString_String(pyObject) : null;
	}
	
	/**
	 * 添加返回类型为HashMap<String, HashMap<String, Double>>的支持
	 */
	public static HashMap<String, HashMap<String, Object>> toMapString_Map(Object pyObject){
		return PyDictionary.class.isInstance(pyObject) ? toHashMapString_Map(pyObject) : null;
	}
	
	/**
	 * Orlando 20160329 改变逻辑为判断是否double
	 * modified by liyanjun 2016-8-22 BUG #137748 【严重】【招商证券】算法优化版本统计不出分级的单位净值
	 * @param pyObject
	 * @return
	 */
	public static boolean isNumber(Object pyObject){
		if(pyObject instanceof Number){
			return true;
		}
		else{
			return false;
		}
	}

	private static HashMap<String, Double> toHashMap(Object pyDictObject) {
		HashMap<String, Double> javaMap = null;
		
		javaMap = new HashMap<String, Double>();
		PyDictionary pyDict = (PyDictionary) pyDictObject;
		PyList pyKeyList = pyDict.keys();
		for (Object key : pyKeyList) {
			PyString pyKey = new PyString(key.toString());
			String keyString = pyKey.toString();
			Double valueDouble = toDouble(pyDict.pop(pyKey));
			javaMap.put(keyString, valueDouble);
		}
		return javaMap;
	}
	private static HashMap<String, Object> toMapString_Object(Object pyDictObject) {
		HashMap<String, Object> javaMap = new HashMap<String, Object>();
		PyDictionary pyDict = (PyDictionary) pyDictObject;
		PyList pyKeyList = pyDict.keys();
		for (Object key : pyKeyList) {
			PyString pyKey = new PyString(key.toString());
			String keyString = pyKey.toString();
			PyObject pyObject = pyDict.pop(pyKey);
			if (PyFloat.class.isInstance(pyObject)) {
				PyFloat pyFloat = (PyFloat) pyObject;
				Double dou = pyFloat.getValue();
				javaMap.put(keyString, dou);
			}else if (PyString.class.isInstance(pyObject)) {
				PyString pyString = (PyString) pyObject;
				String str = pyString.toString();
				javaMap.put(keyString, str);
			} else {
				javaMap.put(keyString, pyObject);
			}
		}
		return javaMap;
	}
	
	private static HashMap<String, String> toHashMapString_String(Object pyDictObject) {
		HashMap<String, String> javaMap = null;
		
		javaMap = new HashMap<String, String>();
		PyDictionary pyDict = (PyDictionary) pyDictObject;
		PyList pyKeyList = pyDict.keys();
		for (Object key : pyKeyList) {
			PyString pyKey = new PyString(key.toString());
			String keyString = pyKey.toString();
			String value = toString(pyDict.pop(pyKey));
			javaMap.put(keyString, value);
		}
		return javaMap;
	}
	
	/**
	 * 添加返回类型为HashMap<String, HashMap<String, Double>>的支持
	 * STORY37961财税140号文件，针对资管增值税系统改造需求
	 * xiaozhilong 20140428
	 * @param pyDictObject
	 */
	private static HashMap<String, HashMap<String, Object>> toHashMapString_Map(Object pyDictObject) {
		HashMap<String, HashMap<String, Object>> javaMap = new HashMap<String, HashMap<String, Object>>();
		PyDictionary pyDict = (PyDictionary) pyDictObject;
		PyList pyKeyList = pyDict.keys();
		for (Object key : pyKeyList) {
			PyString pyKey = new PyString(key.toString());
			String keyString = pyKey.toString();
			PyDictionary value = (PyDictionary)(pyDict.pop(pyKey));
			javaMap.put(keyString, toMapString_Object(value));
		}
		return javaMap;
	}

	private static Double toDouble(Object pyObject) {
		Double dou = null;
		if (PyFloat.class.isInstance(pyObject)) {
			PyFloat pyFloat = (PyFloat) pyObject;
			dou = pyFloat.getValue();
		}
		return dou;
	}
	
	private static String toString(Object pyObject) {
	String str = null;
	if (PyString.class.isInstance(pyObject)) {
		PyString pyString = (PyString) pyObject;
		str = pyString.toString();
	}
	return str;
}

//	private static String toString(PyObject pyObject) {
//		String str = null;
//		if (PyString.class.isInstance(pyObject)) {
//			PyString pyString = (PyString) pyObject;
//			str = pyString.toString();
//		}
//		return str;
//	}
	
//	private static Integer toInterger(PyObject pyObject) {
//		Integer integer = null;
//		if (PyInteger.class.isInstance(pyObject)) {
//			PyInteger pyInt = (PyInteger) pyObject;
//			integer = pyInt.getValue();
//		}
//		return integer;
//	}
}
