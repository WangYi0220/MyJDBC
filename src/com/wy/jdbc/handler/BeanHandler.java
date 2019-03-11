package com.wy.jdbc.handler;

import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

public class BeanHandler<T> {
	
	private Class<T> class1;

	public BeanHandler() {
		super();
	}

	public BeanHandler(Class<T> class1) {
		super();
		this.class1 = class1;
	}
	
	public Object mapToBean(Map<String, Object> dataMap) throws Exception {
		Object object = this.class1.newInstance();
		BeanUtils.populate(object, dataMap);
		return object;
	}
}
