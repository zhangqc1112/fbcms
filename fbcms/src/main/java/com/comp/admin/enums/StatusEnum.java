package com.comp.admin.enums;

import java.util.HashMap;
import java.util.Map;

public enum StatusEnum {
	STATUS_INVALID(0, "禁用"), STATUS_VALID(1, "启用"), STATUS_DELETE(-1, "删除"), ;

	public static final Map<Integer, String> valueMap = new HashMap<Integer, String>();

	static {
		valueMap.put(STATUS_INVALID.getCode(), STATUS_INVALID.getDesc());
		valueMap.put(STATUS_VALID.getCode(), STATUS_VALID.getDesc());
		valueMap.put(STATUS_DELETE.getCode(), STATUS_DELETE.getDesc());
	}

	private final Integer code;
	private final String desc;

	private StatusEnum(Integer code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public Integer getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}
}
