/**
 * @author zhangqingchun
 */
package com.comp.admin.utils;

import java.util.HashMap;
import java.util.Map;

public class BaseException extends RuntimeException {

	private static final long serialVersionUID = 7026776142859331595L;

	private static final int DEFAULT_ERROR_CODE = 500;

	private int reCode;
	private String reMsg;

	// 返回值对象
	private Map<String, Object> extendParams = new HashMap<String, Object>();

	public BaseException(int code, String message, Object... args) {
		super();
		initCodeMsg(code, message, args);
	}

	public BaseException(Throwable cause, int code, String message, Object... args) {
		super(cause);
		initCodeMsg(code, message, args);
	}

	public BaseException(String message, Object... args) {
		super();
		initCodeMsg(getDefaultErrorCode(), message, args);
	}

	public BaseException(Throwable cause, String message, Object... args) {
		super(cause);
		initCodeMsg(getDefaultErrorCode(), message, args);
	}

	protected int getDefaultErrorCode() {
		return DEFAULT_ERROR_CODE;
	}

	private void initCodeMsg(int code, String msg, Object[] args) {
		this.reCode = code;
		if (args == null || args.length == 0) {
			this.reMsg = msg;
		} else {
			this.reMsg = String.format(msg, args);
		}
	}

	public BaseException addParam(String name, Object param) {
		this.extendParams.put(name, param);
		return this;
	}

	public Object getParam(String name) {
		return this.extendParams.get(name);
	}

	public String getString(String name) {
		Object obj = this.extendParams.get(name);
		return obj == null ? null : obj.toString();
	}

	public Integer getInt(String name) {
		Object obj = this.extendParams.get(name);
		return obj == null ? null : Integer.valueOf(obj.toString());
	}

	public Map<String, Object> getParamMap() {
		return this.extendParams;
	}

	public int getReturnCode() {
		return reCode;
	}

	public String getReturnMessage() {
		return reMsg;
	}

	@Override
	public String getMessage() {
		String statusLine = String.format(" %s | %s ", reCode, reMsg);
		StringBuilder message = new StringBuilder(statusLine);
		if (!extendParams.isEmpty()) {
			message.append('[');
			boolean start = true;
			for (String key : extendParams.keySet()) {
				if (start) {
					start = false;
				} else {
					message.append(" | ");
				}
				message.append(key).append("=").append(extendParams.get(key));
			}
			message.append(']');
		}
		return message.toString();
	}
}
