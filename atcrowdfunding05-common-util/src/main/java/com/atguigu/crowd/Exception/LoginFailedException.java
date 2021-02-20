package com.atguigu.crowd.Exception;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-16 上午 11:03
 */
public class LoginFailedException extends RuntimeException {

    private static  final  long serialVersionUID=1L;

    public LoginFailedException() {
    }

    public LoginFailedException(String message) {
        super(message);
    }

    public LoginFailedException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFailedException(Throwable cause) {
        super(cause);
    }

    public LoginFailedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
