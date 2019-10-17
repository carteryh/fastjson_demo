package com.fastjson.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fastjson.dto.SearchCondition;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ObjectToMapUtils {

	/**
	 * 对象转map
	 * @param object
	 * @return
	 */
	public static Map<String, Object> getMap(Object object) {
		Map<String, Object> maps = JSON.parseObject(JSON.toJSONString(object), Map.class);
		Map<String, Object> result = new HashMap<>();

		maps.forEach((k, v) -> {
			if (maps.get(k) != null && v instanceof JSONObject) {
				Map<String, Object> subMaps = (Map)maps.get(k);
				subMaps.forEach((key, value) -> {
					result.put(k + "." + key, value);
				});
			} else {
				result.put(k, maps.get(k));
			}
		});

		return result;
	}

	/**
	 * json字符串转map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> getMap(String json) {
		Map<String, Object> maps = JSON.parseObject(json, Map.class);
		Map<String, Object> result = new HashMap<>();

		maps.forEach((k, v) -> {
			if (maps.get(k) != null && v instanceof JSONObject) {
				Map<String, Object> subMaps = (Map)maps.get(k);
				subMaps.forEach((key, value) -> {
					result.put(k + "." + key, value);
				});
			} else {
				result.put(k, maps.get(k));
			}
		});

		return result;
	}

	/**
	 * 对象转map
	 * @param object
	 * @return
	 */
	public static Map<String, Object> trfMap(Object object, String connector) {
		Map<String, Object> maps = JSON.parseObject(JSON.toJSONString(object), Map.class);
		Map<String, Object> result = new HashMap<>();

		maps.forEach((k, v) -> {
			if (maps.get(k) != null && v instanceof JSONObject) {

				Map<String, Object> subMaps = (Map)maps.get(k);
//				subMaps.forEach((key, value) -> {
//					result.put(k + "." + key, value);
//				});
				Map<String, Object> map = trfMap(subMaps, connector, k);
				if (map != null && !map.isEmpty()) {
					result.putAll(map);
				}
			} else {
				result.put(k, maps.get(k));
			}
		});

		return result;
	}

	/**
	 * json转map
	 * @param json
	 * @return
	 */
	public static Map<String, Object> trfMap(String json, String connector) {
		Map<String, Object> maps = JSON.parseObject(json, Map.class);
		Map<String, Object> result = new HashMap<>();

		maps.forEach((k, v) -> {
			if (maps.get(k) != null && v instanceof JSONObject) {

				Map<String, Object> subMaps = (Map)maps.get(k);
//				subMaps.forEach((key, value) -> {
//					result.put(k + "." + key, value);
//				});
				Map<String, Object> map = trfMap(subMaps, connector, k);
				if (map != null && !map.isEmpty()) {
					result.putAll(map);
				}
			} if (maps.get(k) != null && v instanceof JSONArray) {
				JSONArray array = (JSONArray)maps.get(k);

//				Map<String, Object> map = trfMap(subMaps, connector, k);
//				if (map != null && !map.isEmpty()) {
//					result.putAll(map);
//				}
			} else {
				result.put(k, maps.get(k));
			}
		});

		return result;
	}

	/**
	 * 对象转map
	 * @param maps
	 * @return
	 */
	public static Map<String, Object> trfMap(Map<String, Object> maps, String connector, String prefix) {
		Map<String, Object> result = new HashMap<>();

		String keyPrefix = prefix + connector;
		maps.forEach((k, v) -> {
			String key = keyPrefix + k;
			if (maps.get(k) != null && v instanceof JSONObject) {
				Map<String, Object> subMaps = (Map)maps.get(k);
				Map<String, Object> map = trfMap(subMaps, connector, key);
				if (map != null && !map.isEmpty()) {
					result.putAll(map);
				}
//				subMaps.forEach((key, value) -> {
//					result.put(k + connector + key, value);
//				});
			} else {
				result.put(key, maps.get(k));
			}
		});

		return result;
	}

	public static Object getValue(Object object, SearchCondition condition, String key, boolean isLastKey) {
		if (object instanceof JSONObject) {
			JSONObject obj = (JSONObject)object;
			if (obj.containsKey(key)) {
				Object o = obj.get(key);
				if (isLastKey && StringUtils.isNotBlank(condition.getValue())) {
					if (condition.getValue().equals(obj.getString(key))) {
						return obj;
					}

					return null;
				}
				return o;
			}
			return null;
		} if (object instanceof JSONArray) {
			JSONArray obj = (JSONArray)object;
			JSONArray items = new JSONArray();
			for (int i = 0; i < obj.size(); i++ ){
				Object value = ObjectToMapUtils.getValue(obj.get(i), condition, key, isLastKey);
				if (value != null) {
					items.add(value);
				}
//				JSONObject jsonObject = obj.getJSONObject(i);
//				if (condition.getValue() != null && condition.getValue().equals(jsonObject.getString(key))) {
//					items.add(jsonObject);
//				} else {
//					items.add(jsonObject);
//				}
			}
			return items;
		} else {
			JSONObject obj = (JSONObject)object;
			return obj.getString(key);
		}
	}

	public static Object getObject(Object object, List<SearchCondition> conditions, String connector) {
		if (object == null) {
			return null;
		}
		if (conditions != null && !conditions.isEmpty()) {
			for (SearchCondition searchCondition : conditions) {
				if (object == null) {
					return null;
				}

				String[] keys = searchCondition.getKey().split("\\.");
//				for (String key : keys) {
				for (int k=0; k<keys.length; k++) {
					if (object == null) {
						return null;
					}

					String key = keys[k];
					if (object instanceof JSONObject) {
						JSONObject obj = (JSONObject)object;
						object = obj.get(key);
					} else if (object instanceof JSONArray) {
						JSONArray obj = (JSONArray)object;
						JSONArray items = new JSONArray();
						for (int i=0; i<obj.size(); i++) {
							Object value = ObjectToMapUtils.getValue(obj.get(i), searchCondition, key, (k + 1) == keys.length);
							if (value == null) {
								continue;
							}
							if (value instanceof JSONArray) {
								items.addAll((JSONArray)value);
							} else {
								items.add(value);
							}
						}
						object = items;
					} else {
						JSONObject obj = (JSONObject)object;
						object = obj.get(key);
					}
				}
			}
		}
		return object;
	}

	public static Object getObjValue(String json, List<SearchCondition> conditions, String connector) {
		JSONObject object = JSON.parseObject(json);
		if (object == null) {
			return null;
		}
		return ObjectToMapUtils.getObject(object, conditions, connector);
	}

	public static Object getObjValue(Object object, List<SearchCondition> conditions, String connector) {
		if (object == null) {
			return null;
		}
		return ObjectToMapUtils.getObject(object, conditions, connector);
	}

//	public static Map<String, Object> trfMap(String json, ConditionParams conditionParam, String connector) {
//
//		Object object = JSON.parseObject(json);
//		if (object == null) {
//			return null;
//		}
//		if (conditionParam != null && CollectionUtils.isNotEmpty(conditionParam.getConditions())) {
//			for (SearchCondition searchCondition : conditionParam.getConditions()) {
//				String[] keys = searchCondition.getKey().split(".");
//				for (String key : keys) {
//					if (object instanceof JSONObject) {
//						JSONObject obj = (JSONObject)object;
//						ObjectToMapUtils.getValue(obj, searchCondition, key);
//					} if (object instanceof JSONArray) {
//						JSONArray obj = (JSONArray)object;
//
//					} else {
//
//					}
//
//				}
//			}
//		}
//
//
//
//
//
//
//
//
//		Map<String, Object> maps = JSON.parseObject(json, Map.class);
//		Map<String, Object> result = new HashMap<>();
////		Map<String, Object> map = new HashMap<>();
//		Object obj = null;
//		if (conditionParam != null && CollectionUtils.isNotEmpty(conditionParam.getConditions())) {
//			for (SearchCondition searchCondition: conditionParam.getConditions()) {
//				String[] keys = searchCondition.getKey().split(".");
//				for (String key: keys) {
////					maps.get(key);
//
//					 obj = obj == null ? maps.get(key) : obj;
//					if (obj != null && obj instanceof JSONObject) {
//						Map<String, Object> map = (Map)obj;
////						map.get(key);
////						Map<String, Object> map = trfMap(subMaps, connector, k);
////						if (map != null && !map.isEmpty()) {
////							result.putAll(map);
////						}
//					} if (obj != null && obj instanceof JSONArray) {
//						JSONArray array = (JSONArray)obj;
//						for (int i=0; i<array.size(); i++) {
//							JSONObject jsonObject = array.getJSONObject(i);
//							if (jsonObject.containsKey(key)) {
//
//								if () {
//
//								}
//							}
//						}
//					} else {
////						result.put(k, maps.get(k));
//					}
//
////					maps.forEach((k, v) -> {
////						if (maps.get(k) != null && v instanceof JSONObject) {
////							Map<String, Object> subMaps = (Map)maps.get(k);
////							Map<String, Object> map = trfMap(subMaps, connector, k);
////							if (map != null && !map.isEmpty()) {
////								result.putAll(map);
////							}
////						} if (maps.get(k) != null && v instanceof JSONArray) {
////							JSONArray array = (JSONArray)maps.get(k);
////
////						} else {
////							result.put(k, maps.get(k));
////						}
////					});
//				}
//
//			}
//
//
//
//
//
//			maps.forEach((k, v) -> {
//				if (maps.get(k) != null && v instanceof JSONObject) {
//					Map<String, Object> subMaps = (Map)maps.get(k);
//					Map<String, Object> map = trfMap(subMaps, connector, k);
//					if (map != null && !map.isEmpty()) {
//						result.putAll(map);
//					}
//				} if (maps.get(k) != null && v instanceof JSONArray) {
//					JSONArray array = (JSONArray)maps.get(k);
//
//				} else {
//					result.put(k, maps.get(k));
//				}
//			});
//
//		}
//
//		maps.forEach((k, v) -> {
//			if (maps.get(k) != null && v instanceof JSONObject) {
//
//				Map<String, Object> subMaps = (Map)maps.get(k);
////				subMaps.forEach((key, value) -> {
////					result.put(k + "." + key, value);
////				});
//				Map<String, Object> map = trfMap(subMaps, connector, k);
//				if (map != null && !map.isEmpty()) {
//					result.putAll(map);
//				}
//			} if (maps.get(k) != null && v instanceof JSONArray) {
//				JSONArray array = (JSONArray)maps.get(k);
//
////				Map<String, Object> map = trfMap(subMaps, connector, k);
////				if (map != null && !map.isEmpty()) {
////					result.putAll(map);
////				}
//			} else {
//				result.put(k, maps.get(k));
//			}
//		});
//
//		return result;
//	}

	public static Object getValue(Object obj, String key) {
		Map<String, Object> result = new HashMap<>();

		if (obj != null && obj instanceof JSONObject) {

		} if (obj != null && obj instanceof JSONArray) {
			JSONArray array = (JSONArray)obj;
			for (int i=0; i<array.size(); i++) {
				JSONObject jsonObject = array.getJSONObject(i);
				if (jsonObject.containsKey(key)) {

				}
			}
		} else {
//						result.put(k, maps.get(k));
		}

		return result;
	}

}
