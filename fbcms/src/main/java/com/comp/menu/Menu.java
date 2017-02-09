package com.comp.menu;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.BeanUtils;

import com.alibaba.fastjson.annotation.JSONType;

@JSONType(alphabetic = false)
public class Menu implements Serializable {

	private static final long serialVersionUID = -8606479888124536047L;

	private String id;// 功能id，全局唯一
	private String name;// 功能名称
	private String desc;// 功能描述
	private String url="";// url访问路径
	private boolean isEnable = true;// 是否可用
	private boolean isVisible = true;// 是否可见
	private boolean isDir = true;
	private boolean isDefault = false;
	private String tip;
	private String icon;
	private Map<String, String> subFuncs; // 功能点

	public Menu() {
	}

	public Menu(Menu source) {
		BeanUtils.copyProperties(source, this);
	}

	public Menu(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public Menu setId(String id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Menu setName(String name) {
		this.name = name;
		return this;
	}

	public String getDesc() {
		return desc;
	}

	public Menu setDesc(String desc) {
		this.desc = desc;
		return this;
	}

	public String getUrl() {
		return url;
	}

	public Menu setUrl(String url) {
		this.url = url;
		return this;
	}

	public boolean isEnable() {
		return isEnable;
	}

	public Menu setEnable(boolean isEnable) {
		this.isEnable = isEnable;
		return this;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public Menu setVisible(boolean isVisible) {
		this.isVisible = isVisible;
		return this;
	}

	public boolean isDir() {
		return isDir;
	}

	public Menu setDir(boolean isDir) {
		this.isDir = isDir;
		return this;
	}

	public String getTip() {
		return tip;
	}

	public Menu setTip(String tip) {
		this.tip = tip;
		return this;
	}

	public String getIcon() {
		return icon;
	}

	public Menu setIcon(String icon) {
		this.icon = icon;
		return this;
	}

	public Map<String, String> getSubFuncs() {
		return subFuncs;
	}

	public void setSubFuncs(LinkedHashMap<String, String> subFuncs) {
		if (subFuncs != null) {
			this.subFuncs = new LinkedHashMap<>(subFuncs);
		}
	}

	public String getSubFuncsStr() {
		if (subFuncs != null && !subFuncs.isEmpty()) {
			int i = 0;
			StringBuilder builder = new StringBuilder();
			for (Map.Entry<String, String> entry : subFuncs.entrySet()) {
				if (i++ > 0) {
					builder.append(",");
				}
				builder.append(entry.getKey()).append(":").append(entry.getValue());
			}
			return builder.toString();
		}
		return "";
	}

	public boolean isDefault() {
		return isDefault;
	}

	public void setDefault(boolean isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @see Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Menu [id=").append(id).append(", name=").append(name).append(", desc=").append(desc)
				.append(", url=").append(url).append(", isEnable=").append(isEnable).append(", isVisible=")
				.append(isVisible).append(", isDir=").append(isDir).append(", tip=").append(tip).append(", icon=")
				.append(icon).append(", subFuncs=").append(subFuncs).append("]");
		return builder.toString();
	}

}
