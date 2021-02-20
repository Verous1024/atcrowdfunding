package com.atguigu.crowd.Exception;

/**
 * Descriptions:
 *
 * @author Verous
 * @version 1.0 2021-02-16 下午 09:43
 */
public class LoginAcctAlreadyInUseException extends RuntimeException {
    private static  final  long serialVersionUID=1L;
    public LoginAcctAlreadyInUseException() {
    }

    public LoginAcctAlreadyInUseException(String message) {
        super(message);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginAcctAlreadyInUseException(Throwable cause) {
        super(cause);
    }

    public LoginAcctAlreadyInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
