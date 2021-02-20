package com.atguigu.crowd.util;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-15 下午 08:50
 */

/**
 * 该类主要是用来封装AJAX请求返回的数据
 * @param <T>
 */
public class ResultEntity<T> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAILED = "FAILED";

    private String result;

    private String message;

    private T data;

    public static <E> ResultEntity<E> successWithoutData() {
        return new ResultEntity<>(SUCCESS, null, null);
    }

    public static <E> ResultEntity<E> successWithData(E data) {
        return new ResultEntity<>(SUCCESS, null, data);
    }

    public static <E> ResultEntity<E> failed(String message) {
        return new ResultEntity<>(FAILED, message, null);
    }

    public ResultEntity() {
    }

    public ResultEntity(String result, String message, T data) {
        this.result = result;
        this.message = message;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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
}
