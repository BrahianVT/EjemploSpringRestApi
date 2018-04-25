package com.rest.utilities;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class WrapperClass<T>{
	
	private String message;
	private T data;
	private String erroCode;
	protected Class<? extends T>daoType;
	
	//@SuppressWarnings("unchecked")
	public WrapperClass(){
		/*
		Type t = getClass().getGenericSuperclass();
		ParameterizedType pt = (ParameterizedType) t;
		daoType = (Class<T>) pt.getActualTypeArguments()[0];
		*/
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErroCode() {
		return erroCode;
	}

	public void setErroCode(String erroCode) {
		this.erroCode = erroCode;
	}
	
}
