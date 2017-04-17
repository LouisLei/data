package com.lakala.ipos.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtil<T> {
	
	private Class<T> tClass;

	public JsonUtil(Class<T> tClass) {
		this.tClass = tClass;
	}
	
	/**
	 * json字符串转换成list
	 * 
	 * @param source
	 *            json数组字符串
	 * @return 泛型list
	 * @throws Exception
	 */
	public List<T> parseToList(String source)
			throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			ObjectMapper objectMapper = new ObjectMapper();
			JavaType javaType = objectMapper.getTypeFactory()
					.constructParametricType(ArrayList.class, tClass);
			List<T> tList = objectMapper.readValue(source, javaType);
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
	 * @return 泛型类实例
	 * @throws Exception
	 */
	public T parseToBean(String source)
			throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			ObjectMapper objectMapper = new ObjectMapper();
			T t = objectMapper.readValue(source, tClass);
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
	 * 或者
	 * {"A":{"AA":"1","AAA":"11"},
	 * 	"B":{"BB":"2","BBB":"22"}
	 * }
	 * @param source 源json数组字符串
	 * @return 返回map：key-子节点key，value-子节点value。格式：
	 * {A=1, B=2, C=3}
	 * 或者
	 * {AA=1,BB=2,AAA=11,BBB=22}
	 * @throws Exception
	 */
	public Map<String, String> parseChildJsonToMap(String source) throws Exception {
		try {
			if (source == null || "".equalsIgnoreCase(source))
				return null;
			Map<String, String> bizMap = new LinkedHashMap<String, String>();
			ObjectMapper objectMapper = new ObjectMapper();
			JsonNode rootNode = objectMapper.readTree(source);// 根节点
			for (JsonNode jsonNode : rootNode) {
				Iterator<String> keys = jsonNode.fieldNames();
				while(keys.hasNext()){
					String key = keys.next();    
		            String value = jsonNode.path(key).toString();
		            bizMap.put(key, value);
				}
			}
			return bizMap;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
}
