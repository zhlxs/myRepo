package com.data.masterandslave.util;

import java.io.Serializable;

/**
 * <p>Copyright © JinNuoFeng Network Technology Co.,Ltd.</p>
 * RESTful API 统一响应体
 * @author Alan on 2017/11/1.
 */
public class ResponseResult<T> implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 数据
     */
    private T data;
    /**
     * 信息
     */
    private String message;
    /**
     * 状态码 默认失败400
     */
    private int code = ResultCode.FAILED;

    /**
     * 默认构造方法
     */
    public ResponseResult() {
        super();
    }

    /**
     * 构造器
     * @param code 状态码
     */
    public ResponseResult(int code) {
        this.code = code;
    }

    public ResponseResult(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public ResponseResult(T data, String message, int code) {
        this.data = data;
        this.message = message;
        this.code = code;
    }

    public ResponseResult(T data, String message) {
        this.data = data;
        this.message = message;
        this.code = ResultCode.SUCCESS;
    }

    public ResponseResult(T data) {
        this.data = data;
        this.code = ResultCode.SUCCESS;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
