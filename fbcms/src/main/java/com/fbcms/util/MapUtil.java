/**
 * @author zhangqingchun
 */
package com.fbcms.util;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.TypeUtils;


public class MapUtil {

	public static MapBuilder<String, String> build() {
		return build(String.class, String.class);
	}

	public static <K, V> MapBuilder<K, V> build(Class<K> keyType, Class<V> valueType) {
		MapBuilder<K, V> mapBuilder = new MapBuilder<K, V>();
		return mapBuilder;
	}

	public static class MapBuilder<K, V> {

		private LinkedHashMap<K, V> cache = new LinkedHashMap<K, V>();

		public MapBuilder<K, V> add(K key, V value) {
			cache.put(key, value);
			return this;
		}

		public MapBuilder<K, V> addAll(Map<K, V> map) {
			if (map != null) {
				cache.putAll(map);
			}
			return this;
		}

		public HashMap<K, V> toHashMap() {
			HashMap<K, V> map = new HashMap<K, V>();
			map.putAll(cache);
			return map;
		}

		public TreeMap<K, V> toTreeMap() {
			TreeMap<K, V> map = new TreeMap<K, V>();
			map.putAll(cache);
			return map;
		}

		public LinkedHashMap<K, V> toLinkedHashMap() {
			LinkedHashMap<K, V> map = new LinkedHashMap<K, V>();
			map.putAll(cache);
			return map;
		}
	}

	/**
	 * bean转map
	 * 
	 * @param bean
	 * @return
	 */
	public static Map<String, Object> convertBeanToMap(Object bean) {
		return convertBeanToMap(bean, null, true);
	}

	/**
	 * bean转map，只包含指定的属性
	 * 
	 * @param bean
	 * @param includes
	 * @return
	 */
	public static Map<String, Object> convertBeanToMapWith(Object bean, String... includes) {
		return convertBeanToMap(bean, includes, true);
	}

	/**
	 * bean转map，不包含指定的属性
	 * 
	 * @param bean
	 * @param includes
	 * @return
	 */
	public static Map<String, Object> convertBeanToMapWithout(Object bean, String... excludes) {
		return convertBeanToMap(bean, excludes, false);
	}

	private static Map<String, Object> convertBeanToMap(Object bean, String[] keys, boolean include) {
		if (bean == null) {
			return null;
		}
		Set<String> keySet = null;
		if (keys != null) {
			keySet = new HashSet<String>();
			Collections.addAll(keySet, keys);
		}
		List<FieldInfo> getters = TypeUtils.computeGetters(bean.getClass(), null);
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			for (FieldInfo field : getters) {
				String name = field.getName();
				if (keySet == null || include == keySet.contains(name)) {
					Object value = field.get(bean);
					map.put(name, value);
				}
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException("parse bean error.", e);
		}
		return map;
	}

}
