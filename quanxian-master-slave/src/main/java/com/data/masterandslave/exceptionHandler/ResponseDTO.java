package com.data.masterandslave.exceptionHandler;

import lombok.Data;

import java.io.Serializable;

// 2
@Data
public class ResponseDTO<T> implements Serializable {

	private static final long serialVersionUID = -3436143993984825439L;
	
	private boolean ok = false;

	private T data;

	private String errorMessage = "";

	public static ResponseDTO successResponse() {
		ResponseDTO message = new ResponseDTO();
		message.setOk(true);
		return message;
	}

	public static ResponseDTO failedResponse() {
		ResponseDTO message = new ResponseDTO();
		message.setOk(false);
		return message;
	}

	public ResponseDTO withData(T data) {
		this.data = data;
		return this;
	}

	public ResponseDTO withErrorMessage(String errorMsg) {
		this.errorMessage = errorMsg;
		return this;
	}
}

