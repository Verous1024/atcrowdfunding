package com.atguigu.crowd.Exception;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-16 下午 01:28
 */
public class AccessForbiddenExcepiton extends RuntimeException {

    private static  final  long serialVersionUID=1L;

    public AccessForbiddenExcepiton() {
    }

    public AccessForbiddenExcepiton(String message) {
        super(message);
    }

    public AccessForbiddenExcepiton(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessForbiddenExcepiton(Throwable cause) {
        super(cause);
    }

    public AccessForbiddenExcepiton(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
