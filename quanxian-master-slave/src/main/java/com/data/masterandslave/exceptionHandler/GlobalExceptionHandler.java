package com.data.masterandslave.exceptionHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // Http Status Code 500
	public ResponseDTO handleException(Exception e) {
		// 兜底逻辑，通常用于处理未预期的异常，比如不知道哪儿冒出来的空指针异常
		log.error("", e);
		return ResponseDTO.failedResponse().withErrorMessage("服务器开小差了");
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)  // Http Status Code 400
	public ResponseDTO handleBizException(BizException e) {
		// 可预期的业务异常，根据实际情况，决定是否要打印异常堆栈
		log.warn("业务异常:{}", e);
		return ResponseDTO.failedResponse().withErrorMessage(e.getMessage());
	}
}
