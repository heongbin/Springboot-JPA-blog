package com.blog.blogtest.dto;

public class ResponseDto<T> {
	int status;
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public T getData() {
		return data;
	}
	public ResponseDto(int status, T data) {
		this.status = status;
		this.data = data;
	}
	public void setData(T data) {
		this.data = data;
	}
	T data;
}
