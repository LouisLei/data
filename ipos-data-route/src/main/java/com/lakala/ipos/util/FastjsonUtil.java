package com.lakala.ipos.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class FastjsonUtil<T> {
	
	private Class<T> tClass;

	public FastjsonUtil(Class<T> tClass) {
		this.tClass = tClass;
	}
	
	/**
	 * json字符串转换成map
	 * 
	 * @param source
	 *            json数组字符串
	 * @return map
	 * @throws Exception
	 */
	public Map<String, String> parseToMap(String source)
			throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			Map<String, String> propertyMap = new HashMap<String, String>();
			JSONObject jsonObject = JSON.parseObject(source);
			for (Entry<String, Object> entry : jsonObject.entrySet()) {
				propertyMap.put(entry.getKey(), entry.getValue()+"");
			}
			return propertyMap;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
	
	/**
	 * json字符串转换成list
	 * 
	 * @param source
	 *            json数组字符串
	 * @param sourceMap
	 *            泛型类实例中需要额外放入属性值,若无则为null
	 * @return 泛型list
	 * @throws Exception
	 */
	public List<T> parseToList(String source)
			throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			List<T> tList = JSON.parseArray(source, tClass);
			return tList;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}

	/**
	 * json字符串转换成java bean
	 * 
	 * @param source
	 *            json字符串
	 * @param sourceMap
	 *            泛型类实例中需要额外放入属性值,若无则为null
	 * @return 泛型类实例
	 * @throws Exception
	 */
	public T parseToBean(String source)
			throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			T t = JSON.parseObject(source, tClass);
			return t;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}

	/**
	 * 解析json数组字符串的子节点。格式：
	 * [
	 * {"A":"1"},
	 * {"B":"2"},
	 * {"C":"3"}
	 * ]
	 * @param source 源json数组字符串
	 * @return 返回map：key-子节点key，value-子节点value。格式：
	 * {A=1, B=2, C=3}
	 * @throws Exception
	 */
	public Map<String, String> parseChildJsonToMap(String source) throws Exception {
		if (source == null || "".equalsIgnoreCase(source))
			return null;
		try {
			Map<String, String> bizMap = new HashMap<String, String>();
			JSONArray jsonArray = JSON.parseArray(source);// 解析数组
			for (Object object : jsonArray) {
				JSONObject jsonObject = (JSONObject) object;
				for (Entry<String, Object> entry : jsonObject.entrySet()) {
					bizMap.put(entry.getKey(), entry.getValue()+"");
				}
			}
			return bizMap;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
}
