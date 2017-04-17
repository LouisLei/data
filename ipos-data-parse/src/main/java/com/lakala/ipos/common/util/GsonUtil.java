package com.lakala.ipos.common.util;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class GsonUtil<T> {

	private Class<T> tClass;

	public GsonUtil(Class<T> tClass) {
		this.tClass = tClass;
	}

	/**
	 * 解析单个gson对象字符串
	 * @param source 源json字符串
	 * @return
	 * @throws Exception
	 */
	public T parseToBean(String source) throws Exception {
		try {
			Gson gson = new Gson();
			T t = gson.fromJson(source, tClass);
			return t;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
	/**
	 * 解析单个gson对象
	 * @param element 要解析的元素
	 * @return 泛型类实例
	 * @throws Exception
	 */
	public T parseToBean(JsonElement element) throws Exception {
		try {
			Gson gson = new Gson();
			T t = gson.fromJson(element, tClass);
			return t;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
	
	/**
	 * 解析单个gson数组字符串.格式：
	 * [
	 * {},
	 * {},
	 * {}
	 * ]
	 * @param source 源json数组字符串
	 * @return 泛型类list
	 * @throws Exception
	 */
	public List<T> parseToList(String source) throws Exception {
		try {
			JsonParser parser = new JsonParser();
			JsonElement root = parser.parse(source);
			List<T> tlist = new ArrayList<T>();
			if(root.isJsonNull()) 
				return null;
			if(root.isJsonArray()) {
				JsonArray jsonArray = (JsonArray)root;
				for (JsonElement jsonElement : jsonArray)
					tlist.add(parseToBean(jsonElement));
			} else if(root.isJsonObject())
				tlist.add(parseToBean(root));
			return tlist;
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
			JsonParser parser = new JsonParser();
			JsonElement root = parser.parse(source);
			Map<String, String> bizMap = new LinkedHashMap<String, String>();
			if(root.isJsonNull()) 
				return null;
			if(root.isJsonArray()) {
				JsonArray jsonArray = (JsonArray)root;
				for (JsonElement jsonElement : jsonArray) {
					JsonObject JsonObject = jsonElement.getAsJsonObject();
					Set<Map.Entry<String, JsonElement>> set = JsonObject.entrySet();
					for (Entry<String, JsonElement> entry : set)
						bizMap.put(entry.getKey(), entry.getValue().toString());
				}
			} else if(root.isJsonObject()) {
				Set<Map.Entry<String, JsonElement>> set = ((JsonObject)root).entrySet();
				for (Entry<String, JsonElement> entry : set) {
					JsonObject secondNode = entry.getValue().getAsJsonObject();
					Set<Map.Entry<String, JsonElement>> secondSet = secondNode.entrySet();
					for (Entry<String, JsonElement> secondEntry : secondSet)
						bizMap.put(secondEntry.getKey(), secondEntry.getValue().toString());
				}
			}
			return bizMap;
		} catch (Exception e) {
			throw new Exception("json格式错误");
		}
	}
}
