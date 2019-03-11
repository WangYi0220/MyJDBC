package com.wy.jdbc.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanListHandler<T> {

	private Class<T> class1;

	public BeanListHandler() {
		super();
	}

	public BeanListHandler(Class<T> class1) {
		super();
		this.class1 = class1;
	}

	public List<Object> mapListToBean(List<Map<String, Object>> dataMapList) throws Exception {
		List<Object> list = new  ArrayList<Object>();
		for (Map<String, Object> map : dataMapList) {
			Object object = this.class1.newInstance();
			BeanUtils.populate(object, map);
			list.add(object);
		}
		return list;
	}

}
