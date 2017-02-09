package com.fbcms.util;

import java.util.List;

public class PageQueryResponseVo<T> {

	private Integer listCount;
	private List<T> listData;

	public Integer getListCount() {
		return listCount;
	}

	public void setListCount(Integer listCount) {
		this.listCount = listCount;
	}

	public List<T> getListData() {
		return listData;
	}

	public void setListData(List<T> listData) {
		this.listData = listData;
	}

}
