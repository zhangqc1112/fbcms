/**
 * @author zhangqingchun
 */
package com.fbcms.util;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.util.TypeUtils;

public class DataResult implements Serializable {

	private static final long serialVersionUID = -356912317056399140L;

	public static final int SUCCESS_STATUS = 200;

	private static final SerializerFeature[] features = { SerializerFeature.WriteMapNullValue };
	private static final Feature[] parseFeatures = { Feature.InitStringFieldAsEmpty, Feature.OrderedField };

	protected Object data;

	protected JSONObject dataMap = new JSONObject();

	protected int status = SUCCESS_STATUS; // 响应状态码 默认200代表成功

	protected String message; // 状态描述信息
	
	protected boolean success; // 成功还是失败

	private Map<Class<?>, PropertyPreFilter> propFilterMap = new HashMap<Class<?>, PropertyPreFilter>();

	public void addIncludes(String... props) {
		this.addClassIncludes(null, props);
	}

	public void addExcludes(String... props) {
		this.addClassIncludes(null, props);
	}

	public void addClassIncludes(Class<?> clazz, String... props) {
		if (props.length > 0) {
			PropertyPreFilter filter = new SimplePropertyPreFilter(clazz, props);
			propFilterMap.put(clazz, filter);
		}
	}

	public void addClassExcludes(Class<?> clazz, String... props) {
		if (props.length > 0) {
			SimplePropertyPreFilter filter = new SimplePropertyPreFilter(clazz, new String[] {});
			Set<String> excludes = filter.getExcludes();
			for (String item : props) {
				if (item != null) {
					excludes.add(item);
				}
			}
			propFilterMap.put(clazz, filter);
		}
	}

	public DataResult() {
	}

	public DataResult(Object data) {
		this.data = data;
	}

	public DataResult(int code, String message) {
		this.status = code;
		this.message = message;
		if(code == 200){
		    this.success = true;
		}else{
		    this.success = false;
		}
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message, Object... args) {
		if (args == null || args.length == 0) {
			this.message = message;
		} else {
			this.message = String.format(message, args);
		}
	}

	public boolean success() {
		return SUCCESS_STATUS == status;
	}

	@SuppressWarnings("unchecked")
	public void setData(Object data) {
		if (data == null) {
			this.data = null;
			dataMap.clear();
			return;
		}
		if (data instanceof Map) {
			try {
				Map<String, Object> mapData = (Map<String, Object>) data;
				dataMap.clear();
				dataMap.putAll(mapData);
				this.data = null;
				return;
			} catch (Exception e) {
			}
		}
		this.data = data;
		dataMap.clear();
	}

	public Object getData() {
		Object dataObj = determineData();
		if (dataObj != null && !propFilterMap.isEmpty()) {
			String text = JSON.toJSONString(dataObj,
					propFilterMap.values().toArray(new SerializeFilter[propFilterMap.size()]), features);
			return JSON.parse(text, parseFeatures);
		}
		return dataObj;
	}

	protected Object determineData() {
		return data == null ? dataMap : data;
	}

	public Map<String, Object> fetchDataMap() {
		return dataMap;
	}

	public DataResult put(String key, Object value) {
		dataMap.put(key, value);
		return this;
	}

	public DataResult putAll(Map<String, ?> map) {
		dataMap.putAll(map);
		return this;
	}

	public DataResult putBean(Object bean) {
		if (bean != null) {
			Map<String, Object> map = MapUtil.convertBeanToMap(bean);
			dataMap.putAll(map);
		}
		return this;
	}

	public DataResult putBeanWith(Object bean, String... includes) {
		if (bean != null) {
			Map<String, Object> map = MapUtil.convertBeanToMapWith(bean, includes);
			dataMap.putAll(map);
		}
		return this;
	}

	public DataResult putBeanWithOut(Object bean, String... excludes) {
		if (bean != null) {
			Map<String, Object> map = MapUtil.convertBeanToMapWithout(bean);
			dataMap.putAll(map);
		}
		return this;
	}

	public <T> T getData(Class<T> clazz) {
		return TypeUtils.<T> cast(getData(), clazz, ParserConfig.getGlobalInstance());
	}

	@SuppressWarnings("unchecked")
	public <T> T get(String key) {
		return (T) dataMap.get(key);
	}

	public <T> T get(String key, Class<T> clazz) {
		return TypeUtils.<T> cast(dataMap.get(key), clazz, ParserConfig.getGlobalInstance());
	}

	public String getString(String key) {
		return TypeUtils.castToString(dataMap.get(key));
	}

	public Integer getInt(String key) {
		return TypeUtils.castToInt(dataMap.get(key));
	}

	public Long getLong(String key) {
		return TypeUtils.castToLong(dataMap.get(key));
	}

	public Double getDouble(String key) {
		return TypeUtils.castToDouble(dataMap.get(key));
	}

	public Float getFloat(String key) {
		return TypeUtils.castToFloat(dataMap.get(key));
	}

	public Boolean getBoolean(String key) {
		return TypeUtils.castToBoolean(dataMap.get(key));
	}

	public Byte getByte(String key) {
		return TypeUtils.castToByte(dataMap.get(key));
	}

	public Character getChar(String key) {
		return TypeUtils.castToChar(dataMap.get(key));
	}

	public Short getShort(String key) {
		return TypeUtils.castToShort(dataMap.get(key));
	}

	public BigDecimal getBigDecimal(String key) {
		return TypeUtils.castToBigDecimal(dataMap.get(key));
	}

	public BigInteger getBigInteger(String key) {
		return TypeUtils.castToBigInteger(dataMap.get(key));
	}

	public Date getDate(String key) {
		return TypeUtils.castToDate(dataMap.get(key));
	}

	@Override
	public String toString() {
		final int maxLen = 10;
		return "DataResult [data=" + data + ", dataMap="
				+ (dataMap != null ? toString(dataMap.entrySet(), maxLen) : null) + ", status=" + status + ", message="
				+ message + "]";
	}

	private String toString(Collection<?> collection, int maxLen) {
		StringBuilder builder = new StringBuilder();
		builder.append("[");
		int i = 0;
		for (Iterator<?> iterator = collection.iterator(); iterator.hasNext() && i < maxLen; i++) {
			if (i > 0)
				builder.append(", ");
			builder.append(iterator.next());
		}
		builder.append("]");
		return builder.toString();
	}

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

}
